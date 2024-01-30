package com.codex.nexus.math;

/**
 * A {@code Vector} is a base for a vector respresentation.
 *
 * @author Christopher Ruley
 */
public abstract class Vector {

    /**
     * Sets this {@code Vector} to zero.
     *
     * @return this {@code Vector}.
     */
    public abstract Vector setZero();

    /**
     * Gets a float array from this {@code Vector}.
     *
     * @return a float array from this {@code Vector}.
     */
    public abstract float[] toArray();

}
