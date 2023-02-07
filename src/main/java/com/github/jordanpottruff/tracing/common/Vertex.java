package com.github.jordanpottruff.tracing.common;

import com.github.jordanpottruff.jgml.Vec3;

/**
 * A vertex is a point used to form the boundary of a surface.
 * <p>
 * The vertex is defined as a combination of its position and the various
 * details of how the vertex should appear graphically, using {@link FacePoint}.
 */
public class Vertex {

    private final Vec3 position;
    private final FacePoint pointData;

    public Vertex(Vec3 position, FacePoint pointData) {
        this.position = position;
        this.pointData = pointData;
    }

    /**
     * Returns the position of the vertex.
     */
    public Vec3 getPosition() {
        return position;
    }

    /**
     * Returns the point-data for the vertex.
     */
    public FacePoint getPointData() {
        return pointData;
    }
}
