package com.typesafe.config.impl;

final class SubstitutionExpression {
    private final boolean optional;
    private final Path path;

    SubstitutionExpression(Path path2, boolean z) {
        this.path = path2;
        this.optional = z;
    }

    /* access modifiers changed from: package-private */
    public Path path() {
        return this.path;
    }

    /* access modifiers changed from: package-private */
    public boolean optional() {
        return this.optional;
    }

    /* access modifiers changed from: package-private */
    public SubstitutionExpression changePath(Path path2) {
        if (path2 == this.path) {
            return this;
        }
        return new SubstitutionExpression(path2, this.optional);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("${");
        sb.append(this.optional ? "?" : "");
        sb.append(this.path.render());
        sb.append("}");
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof SubstitutionExpression)) {
            return false;
        }
        SubstitutionExpression substitutionExpression = (SubstitutionExpression) obj;
        if (!substitutionExpression.path.equals(this.path) || substitutionExpression.optional != this.optional) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((this.path.hashCode() + 41) * 41) + (this.optional ? 1 : 0)) * 41;
    }
}
