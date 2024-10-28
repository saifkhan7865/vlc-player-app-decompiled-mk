package com.typesafe.config.impl;

import com.typesafe.config.ConfigException;
import com.typesafe.config.ConfigOrigin;
import com.typesafe.config.ConfigValueType;
import com.typesafe.config.impl.ConfigString;
import java.util.List;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.fusesource.jansi.AnsiRenderer;

final class Tokens {
    static final Token CLOSE_CURLY = Token.newWithoutOrigin(TokenType.CLOSE_CURLY, "'}'", "}");
    static final Token CLOSE_SQUARE = Token.newWithoutOrigin(TokenType.CLOSE_SQUARE, "']'", "]");
    static final Token COLON = Token.newWithoutOrigin(TokenType.COLON, "':'", ":");
    static final Token COMMA = Token.newWithoutOrigin(TokenType.COMMA, "','", AnsiRenderer.CODE_LIST_SEPARATOR);
    static final Token END = Token.newWithoutOrigin(TokenType.END, "end of file", "");
    static final Token EQUALS = Token.newWithoutOrigin(TokenType.EQUALS, "'='", "=");
    static final Token OPEN_CURLY = Token.newWithoutOrigin(TokenType.OPEN_CURLY, "'{'", "{");
    static final Token OPEN_SQUARE = Token.newWithoutOrigin(TokenType.OPEN_SQUARE, "'['", "[");
    static final Token PLUS_EQUALS = Token.newWithoutOrigin(TokenType.PLUS_EQUALS, "'+='", "+=");
    static final Token START = Token.newWithoutOrigin(TokenType.START, "start of file", "");

    Tokens() {
    }

    private static class Value extends Token {
        private final AbstractConfigValue value;

        Value(AbstractConfigValue abstractConfigValue) {
            this(abstractConfigValue, (String) null);
        }

        Value(AbstractConfigValue abstractConfigValue, String str) {
            super(TokenType.VALUE, abstractConfigValue.origin(), str);
            this.value = abstractConfigValue;
        }

        /* access modifiers changed from: package-private */
        public AbstractConfigValue value() {
            return this.value;
        }

        public String toString() {
            if (value().resolveStatus() == ResolveStatus.RESOLVED) {
                return "'" + value().unwrapped() + "' (" + this.value.valueType().name() + ")";
            }
            return "'<unresolved value>' (" + this.value.valueType().name() + ")";
        }

        /* access modifiers changed from: protected */
        public boolean canEqual(Object obj) {
            return obj instanceof Value;
        }

        public boolean equals(Object obj) {
            return super.equals(obj) && ((Value) obj).value.equals(this.value);
        }

        public int hashCode() {
            return ((super.hashCode() + 41) * 41) + this.value.hashCode();
        }
    }

    private static class Line extends Token {
        Line(ConfigOrigin configOrigin) {
            super(TokenType.NEWLINE, configOrigin);
        }

        public String toString() {
            return "'\\n'@" + lineNumber();
        }

        /* access modifiers changed from: protected */
        public boolean canEqual(Object obj) {
            return obj instanceof Line;
        }

        public boolean equals(Object obj) {
            return super.equals(obj) && ((Line) obj).lineNumber() == lineNumber();
        }

        public int hashCode() {
            return ((super.hashCode() + 41) * 41) + lineNumber();
        }

        public String tokenText() {
            return "\n";
        }
    }

    private static class UnquotedText extends Token {
        private final String value;

        UnquotedText(ConfigOrigin configOrigin, String str) {
            super(TokenType.UNQUOTED_TEXT, configOrigin);
            this.value = str;
        }

        /* access modifiers changed from: package-private */
        public String value() {
            return this.value;
        }

        public String toString() {
            return "'" + this.value + "'";
        }

        /* access modifiers changed from: protected */
        public boolean canEqual(Object obj) {
            return obj instanceof UnquotedText;
        }

        public boolean equals(Object obj) {
            return super.equals(obj) && ((UnquotedText) obj).value.equals(this.value);
        }

        public int hashCode() {
            return ((super.hashCode() + 41) * 41) + this.value.hashCode();
        }

        public String tokenText() {
            return this.value;
        }
    }

    private static class IgnoredWhitespace extends Token {
        private final String value;

        IgnoredWhitespace(ConfigOrigin configOrigin, String str) {
            super(TokenType.IGNORED_WHITESPACE, configOrigin);
            this.value = str;
        }

        public String toString() {
            return "'" + this.value + "' (WHITESPACE)";
        }

        /* access modifiers changed from: protected */
        public boolean canEqual(Object obj) {
            return obj instanceof IgnoredWhitespace;
        }

        public boolean equals(Object obj) {
            return super.equals(obj) && ((IgnoredWhitespace) obj).value.equals(this.value);
        }

        public int hashCode() {
            return ((super.hashCode() + 41) * 41) + this.value.hashCode();
        }

        public String tokenText() {
            return this.value;
        }
    }

    private static class Problem extends Token {
        private final Throwable cause;
        private final String message;
        private final boolean suggestQuotes;
        private final String what;

        Problem(ConfigOrigin configOrigin, String str, String str2, boolean z, Throwable th) {
            super(TokenType.PROBLEM, configOrigin);
            this.what = str;
            this.message = str2;
            this.suggestQuotes = z;
            this.cause = th;
        }

        /* access modifiers changed from: package-private */
        public String what() {
            return this.what;
        }

        /* access modifiers changed from: package-private */
        public String message() {
            return this.message;
        }

        /* access modifiers changed from: package-private */
        public boolean suggestQuotes() {
            return this.suggestQuotes;
        }

        /* access modifiers changed from: package-private */
        public Throwable cause() {
            return this.cause;
        }

        public String toString() {
            return "'" + this.what + "' (" + this.message + ")";
        }

        /* access modifiers changed from: protected */
        public boolean canEqual(Object obj) {
            return obj instanceof Problem;
        }

        public boolean equals(Object obj) {
            if (super.equals(obj)) {
                Problem problem = (Problem) obj;
                return problem.what.equals(this.what) && problem.message.equals(this.message) && problem.suggestQuotes == this.suggestQuotes && ConfigImplUtil.equalsHandlingNull(problem.cause, this.cause);
            }
        }

        public int hashCode() {
            int hashCode = (((((((super.hashCode() + 41) * 41) + this.what.hashCode()) * 41) + this.message.hashCode()) * 41) + Boolean.valueOf(this.suggestQuotes).hashCode()) * 41;
            Throwable th = this.cause;
            return th != null ? (hashCode + th.hashCode()) * 41 : hashCode;
        }
    }

    private static abstract class Comment extends Token {
        /* access modifiers changed from: private */
        public final String text;

        Comment(ConfigOrigin configOrigin, String str) {
            super(TokenType.COMMENT, configOrigin);
            this.text = str;
        }

        static final class DoubleSlashComment extends Comment {
            DoubleSlashComment(ConfigOrigin configOrigin, String str) {
                super(configOrigin, str);
            }

            public String tokenText() {
                return "//" + this.text;
            }
        }

        static final class HashComment extends Comment {
            HashComment(ConfigOrigin configOrigin, String str) {
                super(configOrigin, str);
            }

            public String tokenText() {
                return "#" + this.text;
            }
        }

        /* access modifiers changed from: package-private */
        public String text() {
            return this.text;
        }

        public String toString() {
            return "'#" + this.text + "' (COMMENT)";
        }

        /* access modifiers changed from: protected */
        public boolean canEqual(Object obj) {
            return obj instanceof Comment;
        }

        public boolean equals(Object obj) {
            return super.equals(obj) && ((Comment) obj).text.equals(this.text);
        }

        public int hashCode() {
            return (((super.hashCode() + 41) * 41) + this.text.hashCode()) * 41;
        }
    }

    private static class Substitution extends Token {
        private final boolean optional;
        private final List<Token> value;

        Substitution(ConfigOrigin configOrigin, boolean z, List<Token> list) {
            super(TokenType.SUBSTITUTION, configOrigin);
            this.optional = z;
            this.value = list;
        }

        /* access modifiers changed from: package-private */
        public boolean optional() {
            return this.optional;
        }

        /* access modifiers changed from: package-private */
        public List<Token> value() {
            return this.value;
        }

        public String tokenText() {
            StringBuilder sb = new StringBuilder("${");
            sb.append(this.optional ? "?" : "");
            sb.append(Tokenizer.render(this.value.iterator()));
            sb.append("}");
            return sb.toString();
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (Token token : this.value) {
                sb.append(token.toString());
            }
            return "'${" + sb.toString() + "}'";
        }

        /* access modifiers changed from: protected */
        public boolean canEqual(Object obj) {
            return obj instanceof Substitution;
        }

        public boolean equals(Object obj) {
            return super.equals(obj) && ((Substitution) obj).value.equals(this.value);
        }

        public int hashCode() {
            return ((super.hashCode() + 41) * 41) + this.value.hashCode();
        }
    }

    static boolean isValue(Token token) {
        return token instanceof Value;
    }

    static AbstractConfigValue getValue(Token token) {
        if (token instanceof Value) {
            return ((Value) token).value();
        }
        throw new ConfigException.BugOrBroken("tried to get value of non-value token " + token);
    }

    static boolean isValueWithType(Token token, ConfigValueType configValueType) {
        return isValue(token) && getValue(token).valueType() == configValueType;
    }

    static boolean isNewline(Token token) {
        return token instanceof Line;
    }

    static boolean isProblem(Token token) {
        return token instanceof Problem;
    }

    static String getProblemWhat(Token token) {
        if (token instanceof Problem) {
            return ((Problem) token).what();
        }
        throw new ConfigException.BugOrBroken("tried to get problem what from " + token);
    }

    static String getProblemMessage(Token token) {
        if (token instanceof Problem) {
            return ((Problem) token).message();
        }
        throw new ConfigException.BugOrBroken("tried to get problem message from " + token);
    }

    static boolean getProblemSuggestQuotes(Token token) {
        if (token instanceof Problem) {
            return ((Problem) token).suggestQuotes();
        }
        throw new ConfigException.BugOrBroken("tried to get problem suggestQuotes from " + token);
    }

    static Throwable getProblemCause(Token token) {
        if (token instanceof Problem) {
            return ((Problem) token).cause();
        }
        throw new ConfigException.BugOrBroken("tried to get problem cause from " + token);
    }

    static boolean isComment(Token token) {
        return token instanceof Comment;
    }

    static String getCommentText(Token token) {
        if (token instanceof Comment) {
            return ((Comment) token).text();
        }
        throw new ConfigException.BugOrBroken("tried to get comment text from " + token);
    }

    static boolean isUnquotedText(Token token) {
        return token instanceof UnquotedText;
    }

    static String getUnquotedText(Token token) {
        if (token instanceof UnquotedText) {
            return ((UnquotedText) token).value();
        }
        throw new ConfigException.BugOrBroken("tried to get unquoted text from " + token);
    }

    static boolean isIgnoredWhitespace(Token token) {
        return token instanceof IgnoredWhitespace;
    }

    static boolean isSubstitution(Token token) {
        return token instanceof Substitution;
    }

    static List<Token> getSubstitutionPathExpression(Token token) {
        if (token instanceof Substitution) {
            return ((Substitution) token).value();
        }
        throw new ConfigException.BugOrBroken("tried to get substitution from " + token);
    }

    static boolean getSubstitutionOptional(Token token) {
        if (token instanceof Substitution) {
            return ((Substitution) token).optional();
        }
        throw new ConfigException.BugOrBroken("tried to get substitution optionality from " + token);
    }

    static Token newLine(ConfigOrigin configOrigin) {
        return new Line(configOrigin);
    }

    static Token newProblem(ConfigOrigin configOrigin, String str, String str2, boolean z, Throwable th) {
        return new Problem(configOrigin, str, str2, z, th);
    }

    static Token newCommentDoubleSlash(ConfigOrigin configOrigin, String str) {
        return new Comment.DoubleSlashComment(configOrigin, str);
    }

    static Token newCommentHash(ConfigOrigin configOrigin, String str) {
        return new Comment.HashComment(configOrigin, str);
    }

    static Token newUnquotedText(ConfigOrigin configOrigin, String str) {
        return new UnquotedText(configOrigin, str);
    }

    static Token newIgnoredWhitespace(ConfigOrigin configOrigin, String str) {
        return new IgnoredWhitespace(configOrigin, str);
    }

    static Token newSubstitution(ConfigOrigin configOrigin, boolean z, List<Token> list) {
        return new Substitution(configOrigin, z, list);
    }

    static Token newValue(AbstractConfigValue abstractConfigValue) {
        return new Value(abstractConfigValue);
    }

    static Token newValue(AbstractConfigValue abstractConfigValue, String str) {
        return new Value(abstractConfigValue, str);
    }

    static Token newString(ConfigOrigin configOrigin, String str, String str2) {
        return newValue(new ConfigString.Quoted(configOrigin, str), str2);
    }

    static Token newInt(ConfigOrigin configOrigin, int i, String str) {
        return newValue(ConfigNumber.newNumber(configOrigin, (long) i, str), str);
    }

    static Token newDouble(ConfigOrigin configOrigin, double d, String str) {
        return newValue(ConfigNumber.newNumber(configOrigin, d, str), str);
    }

    static Token newLong(ConfigOrigin configOrigin, long j, String str) {
        return newValue(ConfigNumber.newNumber(configOrigin, j, str), str);
    }

    static Token newNull(ConfigOrigin configOrigin) {
        return newValue(new ConfigNull(configOrigin), AbstractJsonLexerKt.NULL);
    }

    static Token newBoolean(ConfigOrigin configOrigin, boolean z) {
        ConfigBoolean configBoolean = new ConfigBoolean(configOrigin, z);
        return newValue(configBoolean, "" + z);
    }
}
