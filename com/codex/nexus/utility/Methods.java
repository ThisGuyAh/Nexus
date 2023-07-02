package com.codex.nexus.utility;

import static java.util.Arrays.*;
import static java.util.stream.Collectors.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;

public class Methods {

    private Methods() {
    }

    public static List<Method> findWithAnnotation(Class<? extends Annotation> annotationClass, Class<?> clazz) {
        Method[] methods = clazz.getMethods();

        return stream(methods).filter(method -> method.isAnnotationPresent(annotationClass)).collect(toList());
    }

    public static List<Method> findWithAnnotation(Class<? extends Annotation> annotationClass,
                                                  List<Method> methods) {
        return methods.stream().filter(method -> method.isAnnotationPresent(annotationClass)).collect(toList());
    }

    public static List<Method> findWithParameterCount(int parameterCount, Class<?> clazz) {
        Method[] methods = clazz.getMethods();

        return stream(methods).filter(method -> method.getParameterCount() == parameterCount).collect(toList());
    }

    public static List<Method> findWithParameterCount(int parameterCount, List<Method> methods) {
        return methods.stream().filter(method -> method.getParameterCount() == parameterCount).collect(toList());
    }

}
