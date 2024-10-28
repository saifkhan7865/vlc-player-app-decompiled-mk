package com.typesafe.config.impl;

import org.fusesource.jansi.AnsiRenderer;

final class MemoKey {
    private final Path restrictToChildOrNull;
    private final AbstractConfigValue value;

    MemoKey(AbstractConfigValue abstractConfigValue, Path path) {
        this.value = abstractConfigValue;
        this.restrictToChildOrNull = path;
    }

    public final int hashCode() {
        int identityHashCode = System.identityHashCode(this.value);
        Path path = this.restrictToChildOrNull;
        return path != null ? identityHashCode + ((path.hashCode() + 41) * 41) : identityHashCode;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof MemoKey) {
            MemoKey memoKey = (MemoKey) obj;
            if (memoKey.value != this.value) {
                return false;
            }
            Path path = memoKey.restrictToChildOrNull;
            Path path2 = this.restrictToChildOrNull;
            if (path == path2) {
                return true;
            }
            if (!(path == null || path2 == null)) {
                return path.equals(path2);
            }
        }
        return false;
    }

    public final String toString() {
        return "MemoKey(" + this.value + "@" + System.identityHashCode(this.value) + AnsiRenderer.CODE_LIST_SEPARATOR + this.restrictToChildOrNull + ")";
    }
}
