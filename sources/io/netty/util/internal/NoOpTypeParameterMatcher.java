package io.netty.util.internal;

public final class NoOpTypeParameterMatcher extends TypeParameterMatcher {
    public boolean match(Object obj) {
        return true;
    }
}
