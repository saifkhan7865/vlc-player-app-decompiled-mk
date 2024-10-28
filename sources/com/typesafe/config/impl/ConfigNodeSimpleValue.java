package com.typesafe.config.impl;

import com.typesafe.config.ConfigException;
import com.typesafe.config.impl.ConfigString;
import java.util.Collection;
import java.util.Collections;

final class ConfigNodeSimpleValue extends AbstractConfigNodeValue {
    final Token token;

    ConfigNodeSimpleValue(Token token2) {
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

    /* access modifiers changed from: protected */
    public AbstractConfigValue value() {
        if (Tokens.isValue(this.token)) {
            return Tokens.getValue(this.token);
        }
        if (Tokens.isUnquotedText(this.token)) {
            return new ConfigString.Unquoted(this.token.origin(), Tokens.getUnquotedText(this.token));
        }
        if (Tokens.isSubstitution(this.token)) {
            return new ConfigReference(this.token.origin(), new SubstitutionExpression(PathParser.parsePathExpression(Tokens.getSubstitutionPathExpression(this.token).iterator(), this.token.origin()), Tokens.getSubstitutionOptional(this.token)));
        }
        throw new ConfigException.BugOrBroken("ConfigNodeSimpleValue did not contain a valid value token");
    }
}
