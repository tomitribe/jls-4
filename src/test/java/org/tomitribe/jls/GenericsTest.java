package org.tomitribe.jls;

import org.junit.Test;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Arrays.asList;

public class GenericsTest {
    @Test
    public void matchReflection() {
        final List<Class<?>>
            types = asList(MyRawBean.class, MyRawBean2.class, MyRawBean3.class, MyRawBean4.class, MyRawBean5.class,
                           MyRawBean6.class, MyRawBean7.class);
        for (Class<?> type : types) {
            System.out.println("==== " + type);
            logJavaHierarchy(type);
            System.out.println("\n");
        }

    }

    private static void logJavaHierarchy(final Type type) {
        if (type instanceof Class<?> c) {
            System.out.println(c.getName());
            Stream.of(c.getGenericInterfaces()).forEach(GenericsTest::logJavaHierarchy);
            logJavaHierarchy(c.getGenericSuperclass());
        } else if (type instanceof ParameterizedType pt) {
            System.out.println(pt);
        }
    }

    static class MyRawBean extends MyBean {
    }
    static class MyBean<T> implements MyInterface {
    }
    interface MyInterface extends MySuperInterface<Number> {
    }
    interface MySuperInterface<T> {
    }

    static class MyRawBean2 extends MyBean2 {
    }
    static class MyBean2 implements MyInterface2 {
    }
    interface MyInterface2 extends MySuperInterface2<Number> {
    }
    interface MySuperInterface2<T> {
    }

    static class MyRawBean3 extends MyBean3 {
    }
    static class MyBean3<T> implements MyInterface3<T> {
    }
    interface MyInterface3<T> extends MySuperInterface3<Number> {
    }
    interface MySuperInterface3<T> {
    }

    static class MyRawBean4 extends MyBean4 {
    }
    static class MyBean4<T> implements MyInterface4 {
    }
    interface MyInterface4<T> extends MySuperInterface4<Number> {
    }
    interface MySuperInterface4<T> {
    }

    static class MyRawBean5 extends MyBean5 {
    }
    static class MyBean5<T> implements MyInterface5 {
    }
    interface MyInterface5<T> extends MySuperInterface5<T> {
    }
    interface MySuperInterface5<T> {
    }

    static class MyRawBean6 extends MyBean6 {
    }
    static class MyBean6<T> implements MyInterface6 {
    }
    interface MyInterface6 extends MySuperInterface6 {
    }
    interface MySuperInterface6<T> {
    }

    static class MyRawBean7 extends MyBean7 {
    }
    static class MyBean7<T> implements MyInterface7 {
    }
    interface MyInterface7<T> extends MySuperInterface7 {
    }
    interface MySuperInterface7<T> {
    }
}
