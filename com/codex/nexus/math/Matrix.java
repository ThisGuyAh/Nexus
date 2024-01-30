package com.codex.nexus.math;

/**
 * A {@code Matrix} is a base for a matrix representation.
 * 
 * @author Christopher Ruley
 */
public abstract class Matrix {

    /**
     * Sets this {@code Matrix} to zero.
     *
     * @return this {@code Matrix}.
     */
    public abstract Matrix setZero();

    /**
     * Sets this {@code Matrix} to the identity.
     *
     * @return this {@code Matrix}.
     */
    public abstract Matrix setIdentity();

    /**
     * Negates this {@code Matrix}.
     *
     * @return this {@code Matrix}.
     */
    public abstract Matrix negate();

    /**
     * Inverts this {@code Matrix}.
     *
     * @return this {@code Matrix}.
     */
    public abstract Matrix invert();

    /**
     * Gets a float array from this {@code Matrix}.
     *
     * @return a float array from this {@code Matrix}.
     */
    public abstract float[] toArray();

}
