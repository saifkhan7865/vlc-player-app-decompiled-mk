package com.typesafe.config.impl;

import com.typesafe.config.ConfigException;
import com.typesafe.config.ConfigOrigin;

class Token {
    private final String debugString;
    private final ConfigOrigin origin;
    private final String tokenText;
    private final TokenType tokenType;

    Token(TokenType tokenType2, ConfigOrigin configOrigin) {
        this(tokenType2, configOrigin, (String) null);
    }

    Token(TokenType tokenType2, ConfigOrigin configOrigin, String str) {
        this(tokenType2, configOrigin, str, (String) null);
    }

    Token(TokenType tokenType2, ConfigOrigin configOrigin, String str, String str2) {
        this.tokenType = tokenType2;
        this.origin = configOrigin;
        this.debugString = str2;
        this.tokenText = str;
    }

    static Token newWithoutOrigin(TokenType tokenType2, String str, String str2) {
        return new Token(tokenType2, (ConfigOrigin) null, str2, str);
    }

    /* access modifiers changed from: package-private */
    public final TokenType tokenType() {
        return this.tokenType;
    }

    public String tokenText() {
        return this.tokenText;
    }

    /* access modifiers changed from: package-private */
    public final ConfigOrigin origin() {
        ConfigOrigin configOrigin = this.origin;
        if (configOrigin != null) {
            return configOrigin;
        }
        throw new ConfigException.BugOrBroken("tried to get origin from token that doesn't have one: " + this);
    }

    /* access modifiers changed from: package-private */
    public final int lineNumber() {
        ConfigOrigin configOrigin = this.origin;
        if (configOrigin != null) {
            return configOrigin.lineNumber();
        }
        return -1;
    }

    public String toString() {
        String str = this.debugString;
        if (str != null) {
            return str;
        }
        return this.tokenType.name();
    }

    /* access modifiers changed from: protected */
    public boolean canEqual(Object obj) {
        return obj instanceof Token;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Token) || !canEqual(obj) || this.tokenType != ((Token) obj).tokenType) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.tokenType.hashCode();
    }
}
