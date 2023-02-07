package com.github.jordanpottruff.tracing.common;

import com.github.jordanpottruff.jgml.Vec3;

/**
 * Represents an RGB color. Each component is a double value, expected to be in
 * the range of 0 (no contribution of that component) through 1 (component is
 * entirely present).
 */
public class Color {

    /**
     * Color definition for 100% red.
     **/
    public static final Color RED = new Color(1, 0, 0);
    /**
     * Color definition for 100% green.
     **/
    public static final Color GREEN = new Color(0, 1, 0);
    /**
     * Color definition for 100% blue.
     **/
    public static final Color BLUE = new Color(0, 0, 1);
    /**
     * Color definition for black, the absence of red, green, and blue.
     **/
    public static final Color BLACK = new Color(0, 0, 0);
    /**
     * Color definiton for white, the presence of 100% red, green, and blue.
     **/
    public static final Color WHITE = new Color(1, 1, 1);

    private final Vec3 rgb;

    /**
     * Creates a Color instance from a {@link Vec3} defining the red, green,
     * and blue components.
     *
     * @param rgb the vector defining all three color components, each a
     *            value of 0 through 1.
     */
    public Color(Vec3 rgb) {
        verify(rgb);
        this.rgb = rgb;
    }

    /**
     * Creates a Color instance from three given values for the red, green, and
     * blue components.
     *
     * @param red   the red component, from 0 through 1.
     * @param green the green component, from 0 through 1.
     * @param blue  the blue component, from 0 through 1.
     */
    public Color(double red, double green, double blue) {
        this(new Vec3(red, green, blue));
    }

    // Validates that each component is in the range [0, 1].
    private void verify(Vec3 rgb) {
        verifyComponent(rgb.x());
        verifyComponent(rgb.y());
        verifyComponent(rgb.z());
    }

    // Verifies that a component value is in the range [0, 1].
    private void verifyComponent(double componentValue) {
        if (componentValue < 0.0 || componentValue > 0.0) {
            throw new IllegalArgumentException(String.format("Expected RGB " +
                            "component values to be between 0 and 1, but " +
                            "found %s",
                    componentValue));
        }
    }

    /**
     * Returns the red component of the color.
     *
     * @return the red value for the color.
     */
    public double getRed() {
        return rgb.x();
    }

    /**
     * Returns the green component of the color.
     *
     * @return the green value for the color.
     */
    public double getGreen() {
        return rgb.y();
    }

    /**
     * Returns the blue component of the color.
     *
     * @return the blue value for the color.
     */
    public double getBlue() {
        return rgb.z();
    }

    /**
     * Returns the RGB components as a {@link Vec3}.
     */
    public Vec3 getRGB() {
        return rgb;
    }
}
