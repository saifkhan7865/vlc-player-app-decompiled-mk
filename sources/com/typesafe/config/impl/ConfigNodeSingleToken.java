package com.typesafe.config.impl;

import java.util.Collection;
import java.util.Collections;

class ConfigNodeSingleToken extends AbstractConfigNode {
    final Token token;

    ConfigNodeSingleToken(Token token2) {
        this.token = token2;
    }

    /* access modifiers changed from: protected */
    public Collection<Token> tokens() {
        return Collections.singletonList(this.token);
    }

    /* access modifiers changed from: protected */
    public Token token() {
        return this.token;
    }
}
