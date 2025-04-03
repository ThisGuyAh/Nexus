package com.codex.nexus.utility;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;

import static java.util.Arrays.*;
import static java.util.stream.Collectors.*;

/**
 * {@code Methods} defines methods used to find and collect other methods based on certain attributes.
 * 
 * @author Christopher Ruley
 */
public class Methods {

    /**
     * Cannot construct {@code Methods}.
     */
    private Methods() {
    }

    /**
     * Finds all methods with a specific annotation in a class.
     *
     * @param annotationClass the annotation.
     * @param clazz           the class.
     * @return a {@code List} containing the methods found.
     */
    public static List<Method> findWithAnnotation(Class<? extends Annotation> annotationClass, Class<?> clazz) {
        Method[] methods = clazz.getDeclaredMethods();

        return stream(methods).filter(method -> method.isAnnotationPresent(annotationClass)).collect(toList());
    }

    /**
     * Finds all methods with a specific annotation in a {@code List} of methods.
     *
     * @param annotationClass the annotation.
     * @param methods         the {@code List} of methods.
     * @return a {@code List} containing the methods found.
     */
    public static List<Method> findWithAnnotation(Class<? extends Annotation> annotationClass, List<Method> methods) {
        return methods.stream().filter(method -> method.isAnnotationPresent(annotationClass)).collect(toList());
    }

    /**
     * Finds all methods with a specific number of parameters in a class.
     *
     * @param parameterCount the number of parameters.
     * @param clazz          the class.
     * @return a {@code List} containing the methods found.
     */
    public static List<Method> findWithParameterCount(int parameterCount, Class<?> clazz) {
        Method[] methods = clazz.getDeclaredMethods();

        return stream(methods).filter(method -> method.getParameterCount() == parameterCount).collect(toList());
    }

    /**
     * Finds all methods with a specific number of parameters in a {@code List} of methods.
     *
     * @param parameterCount the number of parameters.
     * @param methods        the {@code List} of methods.
     * @return a {@code List} containing the methods found.
     */
    public static List<Method> findWithParameterCount(int parameterCount, List<Method> methods) {
        return methods.stream().filter(method -> method.getParameterCount() == parameterCount).collect(toList());
    }

}
