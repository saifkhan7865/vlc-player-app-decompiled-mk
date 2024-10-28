package com.typesafe.config;

public final class ConfigRenderOptions {
    private final boolean comments;
    private final boolean formatted;
    private final boolean json;
    private final boolean originComments;

    private ConfigRenderOptions(boolean z, boolean z2, boolean z3, boolean z4) {
        this.originComments = z;
        this.comments = z2;
        this.formatted = z3;
        this.json = z4;
    }

    public static ConfigRenderOptions defaults() {
        return new ConfigRenderOptions(true, true, true, true);
    }

    public static ConfigRenderOptions concise() {
        return new ConfigRenderOptions(false, false, false, true);
    }

    public ConfigRenderOptions setComments(boolean z) {
        if (z == this.comments) {
            return this;
        }
        return new ConfigRenderOptions(this.originComments, z, this.formatted, this.json);
    }

    public boolean getComments() {
        return this.comments;
    }

    public ConfigRenderOptions setOriginComments(boolean z) {
        if (z == this.originComments) {
            return this;
        }
        return new ConfigRenderOptions(z, this.comments, this.formatted, this.json);
    }

    public boolean getOriginComments() {
        return this.originComments;
    }

    public ConfigRenderOptions setFormatted(boolean z) {
        if (z == this.formatted) {
            return this;
        }
        return new ConfigRenderOptions(this.originComments, this.comments, z, this.json);
    }

    public boolean getFormatted() {
        return this.formatted;
    }

    public ConfigRenderOptions setJson(boolean z) {
        if (z == this.json) {
            return this;
        }
        return new ConfigRenderOptions(this.originComments, this.comments, this.formatted, z);
    }

    public boolean getJson() {
        return this.json;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("ConfigRenderOptions(");
        if (this.originComments) {
            sb.append("originComments,");
        }
        if (this.comments) {
            sb.append("comments,");
        }
        if (this.formatted) {
            sb.append("formatted,");
        }
        if (this.json) {
            sb.append("json,");
        }
        if (sb.charAt(sb.length() - 1) == ',') {
            sb.setLength(sb.length() - 1);
        }
        sb.append(")");
        return sb.toString();
    }
}
