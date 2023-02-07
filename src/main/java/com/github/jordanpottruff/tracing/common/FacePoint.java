package com.github.jordanpottruff.tracing.common;

import com.github.jordanpottruff.jgml.Vec2;
import com.github.jordanpottruff.jgml.Vec3;

/**
 * Stores the data for a particular point on a surface.
 * <p>
 * Generally these are values that can be interpolated across a face using
 * uv-coordinates.
 */
public class FacePoint {

    private final Vec3 normal;
    private final Color color;
    private final double specularRatio;
    private final double shine;
    private final double opacity;
    private final double reflectance;
    private final Vec2 textureUV;

    /**
     * Constructs a FacePoint instance.
     *
     * @param normal      the normal vector for the point.
     * @param color       the color at the point.
     * @param opacity     the opacity at the point.
     * @param reflectance the reflectance at the point.
     * @param textureUV   the uv-coordinate of the point, for texture purposes.
     */
    public FacePoint(Vec3 normal, Color color, double specularRatio,
                     double shine, double opacity, double reflectance,
                     Vec2 textureUV) {
        this.normal = normal;
        this.color = color;
        this.specularRatio = specularRatio;
        this.shine = shine;
        this.opacity = opacity;
        this.reflectance = reflectance;
        this.textureUV = textureUV;
    }

    /**
     * Returns the normal vector of the current point.
     */
    public Vec3 getNormal() {
        return normal;
    }

    /**
     * Returns the color of the current point.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Returns the ratio of reflected light to all light being reflected.
     */
    public double getSpecularRatio() {
        return specularRatio;
    }

    /**
     * Returns the shininess of the point, for specular relfections.
     */
    public double getShine() {
        return shine;
    }

    /**
     * Returns the opacity at the current point.
     */
    public double getOpacity() {
        return opacity;
    }

    /**
     * Returns the reflectance at the current point.
     */
    public double getReflectance() {
        return reflectance;
    }

    /**
     * Returns the texture uv-coordinate vector of the current point.
     */
    public Vec2 getTextureUV() {
        return textureUV;
    }
}
