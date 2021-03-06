package com.codex.nexus.core;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.codex.nexus.utility.Methods;

public class EventBus {

	private Map<Class<?>, Set<Invocation>> subscribers;

	public EventBus() {
		subscribers = new ConcurrentHashMap<>();
	}

	public void register(Object object) {
		Class<?> clazz = object.getClass();

		while (clazz != null) {
			List<Method> methods = Methods.findAllWithParameterCount(1,
					Methods.findAllWithAnnotation(Subscribe.class, clazz));

			methods.forEach(method -> {
				Class<?> parameterType = method.getParameterTypes()[0];

				subscribers
						.computeIfAbsent(parameterType, key -> new HashSet<>())
						.add(new Invocation(method, object));
			});

			clazz = clazz.getSuperclass();
		}
	}

	public void unregister(Object object) {
		Class<?> clazz = object.getClass();

		while (clazz != null) {
			List<Method> methods = Methods.findAllWithParameterCount(1,
					Methods.findAllWithAnnotation(Subscribe.class, clazz));

			methods.forEach(method -> {
				Class<?> parameterType = method.getParameterTypes()[0];

				if (subscribers.containsKey(parameterType)) {
					Set<Invocation> set = subscribers.get(parameterType);

					set.remove(new Invocation(method, object));

					if (set.isEmpty()) {
						subscribers.remove(parameterType);
					}
				}
			});

			clazz = clazz.getSuperclass();
		}
	}

	public void publish(Object object) {
		Class<?> clazz = object.getClass();

		if (subscribers.containsKey(clazz)) {
			Set<Invocation> invocations = subscribers.get(clazz);

			invocations.forEach(invocation -> invocation.invoke(object));
		}
	}

}
