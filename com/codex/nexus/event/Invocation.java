package com.codex.nexus.event;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

public class Invocation {

    private Method method;
    private Object object;

    public Invocation(Method method, Object object) {
        this.method = method;
        this.object = object;
    }

    public Method getMethod() {
        return method;
    }

    public Object getObject() {
        return object;
    }

    public void invoke(Object... parameters) {
        try {
            method.setAccessible(true);
            method.invoke(object, parameters);
        } catch (IllegalAccessException | InvocationTargetException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        Invocation invocation = (Invocation) other;

        return Objects.equals(method, invocation.method) && Objects.equals(object, invocation.object);
    }

    @Override
    public int hashCode() {
        return Objects.hash(method, object);
    }

}
