package androidx.core.util;

@FunctionalInterface
public interface Function<T, R> {
    R apply(T t);
}
