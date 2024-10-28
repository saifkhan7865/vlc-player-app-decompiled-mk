package com.typesafe.config.impl;

import com.typesafe.config.ConfigException;

final class ConfigNodeComment extends ConfigNodeSingleToken {
    ConfigNodeComment(Token token) {
        super(token);
        if (!Tokens.isComment(this.token)) {
            throw new ConfigException.BugOrBroken("Tried to create a ConfigNodeComment from a non-comment token");
        }
    }

    /* access modifiers changed from: protected */
    public String commentText() {
        return Tokens.getCommentText(this.token);
    }
}
