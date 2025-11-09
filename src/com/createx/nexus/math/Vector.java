package com.createx.nexus.math;

/**
 * A {@code Vector} is a base for a vector representation.
 *
 * @author Christopher Ruley
 */
public abstract class Vector {

    /**
     * Gets the magnitude.
     *
     * @return the magnitude.
     */
    public abstract float getMagnitude();

    /**
     * Sets this {@code Vector} to zero.
     *
     * @return this {@code Vector}.
     */
    public abstract Vector setZero();

    /**
     * Negates this {@code Vector}.
     *
     * @return this {@code Vector}.
     */
    public abstract Vector negate();

    /**
     * Normalizes this {@code Vector}.
     *
     * @return this {@code Vector}.
     */
    public abstract Vector normalize();

    /**
     * Gets a float array from this {@code Vector}.
     *
     * @return a float array from this {@code Vector}.
     */
    public abstract float[] toArray();

}
