package com.codex.nexus.utility;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Methods {

	private Methods() {
	}

	public static List<Method> findAllWithAnnotation(
			Class<? extends Annotation> annotationClass, Class<?> clazz) {
		Method[] methods = clazz.getMethods();

		return Arrays.stream(methods)
				.filter(method -> method.isAnnotationPresent(annotationClass))
				.collect(Collectors.toList());
	}

	public static List<Method> findAllWithAnnotation(
			Class<? extends Annotation> annotationClass, List<Method> methods) {
		return methods.stream()
				.filter(method -> method.isAnnotationPresent(annotationClass))
				.collect(Collectors.toList());
	}

	public static List<Method> findAllWithParameterCount(int parameterCount,
			Class<?> clazz) {
		Method[] methods = clazz.getMethods();

		return Arrays.stream(methods)
				.filter(method -> method.getParameterCount() == parameterCount)
				.collect(Collectors.toList());
	}

	public static List<Method> findAllWithParameterCount(int parameterCount,
			List<Method> methods) {
		return methods.stream()
				.filter(method -> method.getParameterCount() == parameterCount)
				.collect(Collectors.toList());
	}

}
