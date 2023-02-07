package com.github.jordanpottruff.tracing.common;

import com.github.jordanpottruff.jgml.Vec2;
import com.github.jordanpottruff.jgml.Vec3;

import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;

/**
 * Represents a 3D face defined by three vertices.
 * <p>
 * Allows for interpolation of various graphical information (defined in
 * {@link FacePoint} using u-v coordinates.
 */
public class Face {

    private static final double EPSILON = 0.001;

    private final Vertex uVertex;
    private final Vertex vVertex;
    private final Vertex wVertex;

    /**
     * Constructs a Face using three vertices, whose order is important.
     *
     * @param uVertex the vertex located at the uv coordinate (1,0).
     * @param vVertex the vertex located at the uv coordinate (0,1).
     * @param wVertex the vertex located at the uv coordinate (0,0).
     */
    public Face(Vertex uVertex, Vertex vVertex, Vertex wVertex) {
        this.uVertex = uVertex;
        this.vVertex = vVertex;
        this.wVertex = wVertex;
    }

    /**
     * Return the vertex located at the uv coordinate (1,0).
     */
    public Vertex getUVertex() {
        return uVertex;
    }

    /**
     * Return the vertex located at the uv coordinate (0,1).
     */
    public Vertex getVVertex() {
        return vVertex;
    }

    /**
     * Return the vertex located at the uv coordinate (0,0).
     */
    public Vertex getWVertex() {
        return wVertex;
    }

    /**
     * Return the {@link FacePoint} calculated by interpolating with the given
     * uv coordinates.
     *
     * @param u the u-coordinate.
     * @param v the v-coordinate.
     * @return the interpolated graphical values found at the uv coordinate.
     */
    public FacePoint getPoint(double u, double v) {
        return new FacePoint(lerpVec3(u, v, FacePoint::getNormal),
                new Color(lerpVec3(u, v, point -> point.getColor().getRGB())),
                lerpDouble(u, v, FacePoint::getSpecularRatio),
                lerpDouble(u, v, FacePoint::getShine),
                lerpDouble(u, v, FacePoint::getOpacity),
                lerpDouble(u, v, FacePoint::getReflectance),
                lerpVec2(u, v, FacePoint::getTextureUV));
    }

    // Interpolates double values.
    private double lerpDouble(double u, double v,
                              Function<FacePoint, Double> getter) {
        return lerp(u, v, getter, (val, scalar) -> val * scalar, Double::sum);
    }

    // Interpolates 2-dimensional vectors.
    private Vec2 lerpVec2(double u, double v,
                          Function<FacePoint, Vec2> getter) {
        return lerp(u, v, getter, Vec2::scale, Vec2::add);
    }

    // Interpolates 3-dimensional vectors.
    private Vec3 lerpVec3(double u, double v,
                          Function<FacePoint, Vec3> getter) {
        return lerp(u, v, getter, Vec3::scale, Vec3::add);
    }

    // Interpolates generic values using uv coordinates.
    private <T> T lerp(double u, double v, Function<FacePoint, T> getter,
                       BiFunction<T, Double, T> scaler,
                       BinaryOperator<T> adder) {
        double w = getW(u, v);
        T uResult = scaler.apply(getter.apply(uVertex.getPointData()), u);
        T vResult = scaler.apply(getter.apply(vVertex.getPointData()), v);
        T wResult = scaler.apply(getter.apply(wVertex.getPointData()), w);
        return adder.apply(adder.apply(uResult, vResult), wResult);
    }

    // Retrieves the w-coordinate, verifying the given uv-coordinates as well.
    private double getW(double u, double v) {
        if (u < EPSILON) {
            throw new IllegalArgumentException(
                    "Expected u-coordinate to be positive, but found: " + u);
        }
        if (v < EPSILON) {
            throw new IllegalArgumentException(
                    "Expected v-coordinate to be positive, but found: " + v);
        }
        double w = 1 - u - v;
        if (w < EPSILON) {
            throw new IllegalArgumentException(String.format(
                    "Expected uv coordinates to not exceed a sum of 1, but " +
                            "found: %s and %s", u, v));
        }
        return w;
    }
}
