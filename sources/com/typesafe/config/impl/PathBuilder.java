package com.typesafe.config.impl;

import com.typesafe.config.ConfigException;
import java.util.Stack;

final class PathBuilder {
    private final Stack<String> keys = new Stack<>();
    private Path result;

    PathBuilder() {
    }

    private void checkCanAppend() {
        if (this.result != null) {
            throw new ConfigException.BugOrBroken("Adding to PathBuilder after getting result");
        }
    }

    /* access modifiers changed from: package-private */
    public void appendKey(String str) {
        checkCanAppend();
        this.keys.push(str);
    }

    /* access modifiers changed from: package-private */
    public void appendPath(Path path) {
        checkCanAppend();
        String first = path.first();
        Path remainder = path.remainder();
        while (true) {
            this.keys.push(first);
            if (remainder != null) {
                first = remainder.first();
                remainder = remainder.remainder();
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public Path result() {
        if (this.result == null) {
            Path path = null;
            while (!this.keys.isEmpty()) {
                path = new Path(this.keys.pop(), path);
            }
            this.result = path;
        }
        return this.result;
    }
}
