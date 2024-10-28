package io.netty.handler.codec.serialization;

@Deprecated
public interface ClassResolver {
    Class<?> resolve(String str) throws ClassNotFoundException;
}
