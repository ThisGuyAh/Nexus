package com.codex.nexus.core;

/**
 * {@code Logical} provides methods to be executed at various stages within {@code Application}.
 *
 * @author Christopher Ruley
 */
public interface Logical {

    /**
     * Called during the creation stage of {@code Application}.
     */
    void onCreate();

    /**
     * Called during the input-polling stage of {@code Application}.
     */
    void onInput();

    /**
     * Called during the update stage of {@code Application}.
     */
    void onUpdate(double delta);

    /**
     * Called during the render stage of {@code Application}.
     */
    void onRender(double alpha);

    /**
     * Called during the destruction stage of {@code Application}.
     */
    void onDestroy();

}
