package com.codex.nexus.core;

/**
 * {@code Logical} provides methods to be executed at various stages within {@code Application}.
 * 
 * @author Christopher Ruley
 */
public interface Logical {

    /**
     * Called during the initialize stage of {@code Application}.
     */
    void onInitialize();

    /**
     * Called during the input stage of {@code Application}.
     */
    void onInput();

    /**
     * Called during the update stage of {@code Application}.
     */
    void onUpdate();

    /**
     * Called during the render stage of {@code Application}.
     */
    void onRender();

    /**
     * Called during the destroy stage of {@code Application}.
     */
    void onDestroy();

}
