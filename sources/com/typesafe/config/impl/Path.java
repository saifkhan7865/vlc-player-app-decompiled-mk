package com.typesafe.config.impl;

import com.typesafe.config.ConfigException;
import java.util.Iterator;
import java.util.List;

final class Path {
    private final String first;
    private final Path remainder;

    Path(String str, Path path) {
        this.first = str;
        this.remainder = path;
    }

    Path(String... strArr) {
        if (strArr.length != 0) {
            this.first = strArr[0];
            if (strArr.length > 1) {
                PathBuilder pathBuilder = new PathBuilder();
                for (int i = 1; i < strArr.length; i++) {
                    pathBuilder.appendKey(strArr[i]);
                }
                this.remainder = pathBuilder.result();
                return;
            }
            this.remainder = null;
            return;
        }
        throw new ConfigException.BugOrBroken("empty path");
    }

    Path(List<Path> list) {
        this(list.iterator());
    }

    Path(Iterator<Path> it) {
        if (it.hasNext()) {
            Path next = it.next();
            this.first = next.first;
            PathBuilder pathBuilder = new PathBuilder();
            Path path = next.remainder;
            if (path != null) {
                pathBuilder.appendPath(path);
            }
            while (it.hasNext()) {
                pathBuilder.appendPath(it.next());
            }
            this.remainder = pathBuilder.result();
            return;
        }
        throw new ConfigException.BugOrBroken("empty path");
    }

    /* access modifiers changed from: package-private */
    public String first() {
        return this.first;
    }

    /* access modifiers changed from: package-private */
    public Path remainder() {
        return this.remainder;
    }

    /* access modifiers changed from: package-private */
    public Path parent() {
        if (this.remainder == null) {
            return null;
        }
        PathBuilder pathBuilder = new PathBuilder();
        for (Path path = this; path.remainder != null; path = path.remainder) {
            pathBuilder.appendKey(path.first);
        }
        return pathBuilder.result();
    }

    /* access modifiers changed from: package-private */
    public String last() {
        Path path = this;
        while (true) {
            Path path2 = path.remainder;
            if (path2 == null) {
                return path.first;
            }
            path = path2;
        }
    }

    /* access modifiers changed from: package-private */
    public Path prepend(Path path) {
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.appendPath(path);
        pathBuilder.appendPath(this);
        return pathBuilder.result();
    }

    /* access modifiers changed from: package-private */
    public int length() {
        int i = 1;
        for (Path path = this.remainder; path != null; path = path.remainder) {
            i++;
        }
        return i;
    }

    /* access modifiers changed from: package-private */
    public Path subPath(int i) {
        Path path = this;
        while (path != null && i > 0) {
            i--;
            path = path.remainder;
        }
        return path;
    }

    /* access modifiers changed from: package-private */
    public Path subPath(int i, int i2) {
        if (i2 >= i) {
            Path subPath = subPath(i);
            PathBuilder pathBuilder = new PathBuilder();
            int i3 = i2 - i;
            while (i3 > 0) {
                i3--;
                pathBuilder.appendKey(subPath.first());
                subPath = subPath.remainder();
                if (subPath == null) {
                    throw new ConfigException.BugOrBroken("subPath lastIndex out of range " + i2);
                }
            }
            return pathBuilder.result();
        }
        throw new ConfigException.BugOrBroken("bad call to subPath");
    }

    /* access modifiers changed from: package-private */
    public boolean startsWith(Path path) {
        if (path.length() > length()) {
            return false;
        }
        Path path2 = this;
        while (path != null) {
            if (!path.first().equals(path2.first())) {
                return false;
            }
            path2 = path2.remainder();
            path = path.remainder();
        }
        return true;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Path)) {
            return false;
        }
        Path path = (Path) obj;
        if (!this.first.equals(path.first) || !ConfigImplUtil.equalsHandlingNull(this.remainder, path.remainder)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int i;
        int hashCode = (this.first.hashCode() + 41) * 41;
        Path path = this.remainder;
        if (path == null) {
            i = 0;
        } else {
            i = path.hashCode();
        }
        return hashCode + i;
    }

    static boolean hasFunkyChars(String str) {
        int length = str.length();
        if (length == 0) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (!Character.isLetterOrDigit(charAt) && charAt != '-' && charAt != '_') {
                return true;
            }
        }
        return false;
    }

    private void appendToStringBuilder(StringBuilder sb) {
        if (hasFunkyChars(this.first) || this.first.isEmpty()) {
            sb.append(ConfigImplUtil.renderJsonString(this.first));
        } else {
            sb.append(this.first);
        }
        if (this.remainder != null) {
            sb.append(".");
            this.remainder.appendToStringBuilder(sb);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Path(");
        appendToStringBuilder(sb);
        sb.append(")");
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public String render() {
        StringBuilder sb = new StringBuilder();
        appendToStringBuilder(sb);
        return sb.toString();
    }

    static Path newKey(String str) {
        return new Path(str, (Path) null);
    }

    static Path newPath(String str) {
        return PathParser.parsePath(str);
    }
}
