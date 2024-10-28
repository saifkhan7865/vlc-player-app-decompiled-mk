package com.typesafe.config.impl;

import com.typesafe.config.ConfigException;
import com.typesafe.config.ConfigOrigin;
import com.typesafe.config.ConfigSyntax;
import io.netty.util.internal.StringUtil;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

final class Tokenizer {
    Tokenizer() {
    }

    private static class ProblemException extends Exception {
        private static final long serialVersionUID = 1;
        private final Token problem;

        ProblemException(Token token) {
            this.problem = token;
        }

        /* access modifiers changed from: package-private */
        public Token problem() {
            return this.problem;
        }
    }

    /* access modifiers changed from: private */
    public static String asString(int i) {
        if (i == 10) {
            return "newline";
        }
        if (i == 9) {
            return "tab";
        }
        if (i == -1) {
            return "end of file";
        }
        if (ConfigImplUtil.isC0Control(i)) {
            return String.format("control character 0x%x", new Object[]{Integer.valueOf(i)});
        }
        return String.format("%c", new Object[]{Integer.valueOf(i)});
    }

    static Iterator<Token> tokenize(ConfigOrigin configOrigin, Reader reader, ConfigSyntax configSyntax) {
        return new TokenIterator(configOrigin, reader, configSyntax != ConfigSyntax.JSON);
    }

    static String render(Iterator<Token> it) {
        StringBuilder sb = new StringBuilder();
        while (it.hasNext()) {
            sb.append(it.next().tokenText());
        }
        return sb.toString();
    }

    private static class TokenIterator implements Iterator<Token> {
        static final String firstNumberChars = "0123456789-";
        static final String notInUnquotedText = "$\"{}[]:=,+#`^?!@*&\\";
        static final String numberChars = "0123456789eE+-.";
        private final boolean allowComments;
        private final LinkedList<Integer> buffer = new LinkedList<>();
        private final Reader input;
        private int lineNumber = 1;
        private ConfigOrigin lineOrigin;
        private final SimpleConfigOrigin origin;
        private final Queue<Token> tokens;
        private final WhitespaceSaver whitespaceSaver;

        private static class WhitespaceSaver {
            private boolean lastTokenWasSimpleValue = false;
            private StringBuilder whitespace = new StringBuilder();

            WhitespaceSaver() {
            }

            /* access modifiers changed from: package-private */
            public void add(int i) {
                this.whitespace.appendCodePoint(i);
            }

            /* access modifiers changed from: package-private */
            public Token check(Token token, ConfigOrigin configOrigin, int i) {
                if (TokenIterator.isSimpleValue(token)) {
                    return nextIsASimpleValue(configOrigin, i);
                }
                return nextIsNotASimpleValue(configOrigin, i);
            }

            private Token nextIsNotASimpleValue(ConfigOrigin configOrigin, int i) {
                this.lastTokenWasSimpleValue = false;
                return createWhitespaceTokenFromSaver(configOrigin, i);
            }

            private Token nextIsASimpleValue(ConfigOrigin configOrigin, int i) {
                Token createWhitespaceTokenFromSaver = createWhitespaceTokenFromSaver(configOrigin, i);
                if (!this.lastTokenWasSimpleValue) {
                    this.lastTokenWasSimpleValue = true;
                }
                return createWhitespaceTokenFromSaver;
            }

            private Token createWhitespaceTokenFromSaver(ConfigOrigin configOrigin, int i) {
                Token token;
                if (this.whitespace.length() <= 0) {
                    return null;
                }
                if (this.lastTokenWasSimpleValue) {
                    token = Tokens.newUnquotedText(TokenIterator.lineOrigin(configOrigin, i), this.whitespace.toString());
                } else {
                    token = Tokens.newIgnoredWhitespace(TokenIterator.lineOrigin(configOrigin, i), this.whitespace.toString());
                }
                this.whitespace.setLength(0);
                return token;
            }
        }

        TokenIterator(ConfigOrigin configOrigin, Reader reader, boolean z) {
            SimpleConfigOrigin simpleConfigOrigin = (SimpleConfigOrigin) configOrigin;
            this.origin = simpleConfigOrigin;
            this.input = reader;
            this.allowComments = z;
            this.lineOrigin = simpleConfigOrigin.withLineNumber(1);
            LinkedList linkedList = new LinkedList();
            this.tokens = linkedList;
            linkedList.add(Tokens.START);
            this.whitespaceSaver = new WhitespaceSaver();
        }

        private int nextCharRaw() {
            if (!this.buffer.isEmpty()) {
                return this.buffer.pop().intValue();
            }
            try {
                return this.input.read();
            } catch (IOException e) {
                SimpleConfigOrigin simpleConfigOrigin = this.origin;
                throw new ConfigException.IO(simpleConfigOrigin, "read error: " + e.getMessage(), e);
            }
        }

        private void putBack(int i) {
            if (this.buffer.size() <= 2) {
                this.buffer.push(Integer.valueOf(i));
                return;
            }
            throw new ConfigException.BugOrBroken("bug: putBack() three times, undesirable look-ahead");
        }

        static boolean isWhitespace(int i) {
            return ConfigImplUtil.isWhitespace(i);
        }

        static boolean isWhitespaceNotNewline(int i) {
            return i != 10 && ConfigImplUtil.isWhitespace(i);
        }

        private boolean startOfComment(int i) {
            if (i != -1 && this.allowComments) {
                if (i == 35) {
                    return true;
                }
                if (i == 47) {
                    int nextCharRaw = nextCharRaw();
                    putBack(nextCharRaw);
                    if (nextCharRaw == 47) {
                        return true;
                    }
                }
            }
            return false;
        }

        private int nextCharAfterWhitespace(WhitespaceSaver whitespaceSaver2) {
            while (true) {
                int nextCharRaw = nextCharRaw();
                if (nextCharRaw == -1) {
                    return -1;
                }
                if (!isWhitespaceNotNewline(nextCharRaw)) {
                    return nextCharRaw;
                }
                whitespaceSaver2.add(nextCharRaw);
            }
        }

        private ProblemException problem(String str) {
            return problem("", str, (Throwable) null);
        }

        private ProblemException problem(String str, String str2) {
            return problem(str, str2, (Throwable) null);
        }

        private ProblemException problem(String str, String str2, boolean z) {
            return problem(str, str2, z, (Throwable) null);
        }

        private ProblemException problem(String str, String str2, Throwable th) {
            return problem(this.lineOrigin, str, str2, th);
        }

        private ProblemException problem(String str, String str2, boolean z, Throwable th) {
            return problem(this.lineOrigin, str, str2, z, th);
        }

        private static ProblemException problem(ConfigOrigin configOrigin, String str, String str2, Throwable th) {
            return problem(configOrigin, str, str2, false, th);
        }

        private static ProblemException problem(ConfigOrigin configOrigin, String str, String str2, boolean z, Throwable th) {
            if (str != null && str2 != null) {
                return new ProblemException(Tokens.newProblem(configOrigin, str, str2, z, th));
            }
            throw new ConfigException.BugOrBroken("internal error, creating bad ProblemException");
        }

        private static ProblemException problem(ConfigOrigin configOrigin, String str) {
            return problem(configOrigin, "", str, (Throwable) null);
        }

        /* access modifiers changed from: private */
        public static ConfigOrigin lineOrigin(ConfigOrigin configOrigin, int i) {
            return ((SimpleConfigOrigin) configOrigin).withLineNumber(i);
        }

        private Token pullComment(int i) {
            boolean z;
            int nextCharRaw;
            if (i != 47) {
                z = false;
            } else if (nextCharRaw() == 47) {
                z = true;
            } else {
                throw new ConfigException.BugOrBroken("called pullComment but // not seen");
            }
            StringBuilder sb = new StringBuilder();
            while (true) {
                nextCharRaw = nextCharRaw();
                if (nextCharRaw == -1 || nextCharRaw == 10) {
                    putBack(nextCharRaw);
                } else {
                    sb.appendCodePoint(nextCharRaw);
                }
            }
            putBack(nextCharRaw);
            if (z) {
                return Tokens.newCommentDoubleSlash(this.lineOrigin, sb.toString());
            }
            return Tokens.newCommentHash(this.lineOrigin, sb.toString());
        }

        private Token pullUnquotedText() {
            ConfigOrigin configOrigin = this.lineOrigin;
            StringBuilder sb = new StringBuilder();
            int nextCharRaw = nextCharRaw();
            while (nextCharRaw != -1 && notInUnquotedText.indexOf(nextCharRaw) < 0 && !isWhitespace(nextCharRaw) && !startOfComment(nextCharRaw)) {
                sb.appendCodePoint(nextCharRaw);
                if (sb.length() == 4) {
                    String sb2 = sb.toString();
                    if (sb2.equals("true")) {
                        return Tokens.newBoolean(configOrigin, true);
                    }
                    if (sb2.equals(AbstractJsonLexerKt.NULL)) {
                        return Tokens.newNull(configOrigin);
                    }
                } else if (sb.length() == 5 && sb.toString().equals("false")) {
                    return Tokens.newBoolean(configOrigin, false);
                }
                nextCharRaw = nextCharRaw();
            }
            putBack(nextCharRaw);
            return Tokens.newUnquotedText(configOrigin, sb.toString());
        }

        private Token pullNumber(int i) throws ProblemException {
            StringBuilder sb = new StringBuilder();
            sb.appendCodePoint(i);
            int nextCharRaw = nextCharRaw();
            int i2 = 0;
            boolean z = false;
            while (nextCharRaw != -1 && numberChars.indexOf(nextCharRaw) >= 0) {
                if (nextCharRaw == 46 || nextCharRaw == 101 || nextCharRaw == 69) {
                    z = true;
                }
                sb.appendCodePoint(nextCharRaw);
                nextCharRaw = nextCharRaw();
            }
            putBack(nextCharRaw);
            String sb2 = sb.toString();
            if (!z) {
                return Tokens.newLong(this.lineOrigin, Long.parseLong(sb2), sb2);
            }
            try {
                return Tokens.newDouble(this.lineOrigin, Double.parseDouble(sb2), sb2);
            } catch (NumberFormatException unused) {
                char[] charArray = sb2.toCharArray();
                int length = charArray.length;
                while (i2 < length) {
                    char c = charArray[i2];
                    if (notInUnquotedText.indexOf(c) < 0) {
                        i2++;
                    } else {
                        String access$200 = Tokenizer.asString(c);
                        throw problem(access$200, "Reserved character '" + Tokenizer.asString(c) + "' is not allowed outside quotes", true);
                    }
                }
                return Tokens.newUnquotedText(this.lineOrigin, sb2);
            }
        }

        private void pullEscapeSequence(StringBuilder sb, StringBuilder sb2) throws ProblemException {
            int nextCharRaw = nextCharRaw();
            if (nextCharRaw != -1) {
                sb2.appendCodePoint(92);
                sb2.appendCodePoint(nextCharRaw);
                if (nextCharRaw == 34) {
                    sb.append('\"');
                } else if (nextCharRaw == 47) {
                    sb.append('/');
                } else if (nextCharRaw == 92) {
                    sb.append(AbstractJsonLexerKt.STRING_ESC);
                } else if (nextCharRaw == 98) {
                    sb.append(8);
                } else if (nextCharRaw == 102) {
                    sb.append(12);
                } else if (nextCharRaw == 110) {
                    sb.append(10);
                } else if (nextCharRaw == 114) {
                    sb.append(StringUtil.CARRIAGE_RETURN);
                } else if (nextCharRaw == 116) {
                    sb.append(9);
                } else if (nextCharRaw == 117) {
                    char[] cArr = new char[4];
                    int i = 0;
                    while (i < 4) {
                        int nextCharRaw2 = nextCharRaw();
                        if (nextCharRaw2 != -1) {
                            cArr[i] = (char) nextCharRaw2;
                            i++;
                        } else {
                            throw problem("End of input but expecting 4 hex digits for \\uXXXX escape");
                        }
                    }
                    String str = new String(cArr);
                    sb2.append(cArr);
                    try {
                        sb.appendCodePoint(Integer.parseInt(str, 16));
                    } catch (NumberFormatException e) {
                        throw problem(str, String.format("Malformed hex digits after \\u escape in string: '%s'", new Object[]{str}), (Throwable) e);
                    }
                } else {
                    throw problem(Tokenizer.asString(nextCharRaw), String.format("backslash followed by '%s', this is not a valid escape sequence (quoted strings use JSON escaping, so use double-backslash \\\\ for literal backslash)", new Object[]{Tokenizer.asString(nextCharRaw)}));
                }
            } else {
                throw problem("End of input but backslash in string had nothing after it");
            }
        }

        private void appendTripleQuotedString(StringBuilder sb, StringBuilder sb2) throws ProblemException {
            int i = 0;
            while (true) {
                int nextCharRaw = nextCharRaw();
                if (nextCharRaw == 34) {
                    i++;
                } else if (i >= 3) {
                    sb.setLength(sb.length() - 3);
                    putBack(nextCharRaw);
                    return;
                } else if (nextCharRaw != -1) {
                    if (nextCharRaw == 10) {
                        int i2 = this.lineNumber + 1;
                        this.lineNumber = i2;
                        this.lineOrigin = this.origin.withLineNumber(i2);
                    }
                    i = 0;
                } else {
                    throw problem("End of input but triple-quoted string was still open");
                }
                sb.appendCodePoint(nextCharRaw);
                sb2.appendCodePoint(nextCharRaw);
            }
        }

        private Token pullQuotedString() throws ProblemException {
            StringBuilder sb = new StringBuilder();
            StringBuilder sb2 = new StringBuilder();
            sb2.appendCodePoint(34);
            while (true) {
                int nextCharRaw = nextCharRaw();
                if (nextCharRaw == -1) {
                    throw problem("End of input but string quote was still open");
                } else if (nextCharRaw == 92) {
                    pullEscapeSequence(sb, sb2);
                } else if (nextCharRaw == 34) {
                    sb2.appendCodePoint(nextCharRaw);
                    if (sb.length() == 0) {
                        int nextCharRaw2 = nextCharRaw();
                        if (nextCharRaw2 == 34) {
                            sb2.appendCodePoint(nextCharRaw2);
                            appendTripleQuotedString(sb, sb2);
                        } else {
                            putBack(nextCharRaw2);
                        }
                    }
                    return Tokens.newString(this.lineOrigin, sb.toString(), sb2.toString());
                } else if (!ConfigImplUtil.isC0Control(nextCharRaw)) {
                    sb.appendCodePoint(nextCharRaw);
                    sb2.appendCodePoint(nextCharRaw);
                } else {
                    String access$200 = Tokenizer.asString(nextCharRaw);
                    throw problem(access$200, "JSON does not allow unescaped " + Tokenizer.asString(nextCharRaw) + " in quoted strings, use a backslash escape");
                }
            }
        }

        private Token pullPlusEquals() throws ProblemException {
            int nextCharRaw = nextCharRaw();
            if (nextCharRaw == 61) {
                return Tokens.PLUS_EQUALS;
            }
            String access$200 = Tokenizer.asString(nextCharRaw);
            throw problem(access$200, "'+' not followed by =, '" + Tokenizer.asString(nextCharRaw) + "' not allowed after '+'", true);
        }

        private Token pullSubstitution() throws ProblemException {
            ConfigOrigin configOrigin = this.lineOrigin;
            int nextCharRaw = nextCharRaw();
            boolean z = true;
            if (nextCharRaw == 123) {
                int nextCharRaw2 = nextCharRaw();
                if (nextCharRaw2 != 63) {
                    putBack(nextCharRaw2);
                    z = false;
                }
                WhitespaceSaver whitespaceSaver2 = new WhitespaceSaver();
                ArrayList arrayList = new ArrayList();
                while (true) {
                    Token pullNextToken = pullNextToken(whitespaceSaver2);
                    if (pullNextToken == Tokens.CLOSE_CURLY) {
                        return Tokens.newSubstitution(configOrigin, z, arrayList);
                    }
                    if (pullNextToken != Tokens.END) {
                        Token check = whitespaceSaver2.check(pullNextToken, configOrigin, this.lineNumber);
                        if (check != null) {
                            arrayList.add(check);
                        }
                        arrayList.add(pullNextToken);
                    } else {
                        throw problem(configOrigin, "Substitution ${ was not closed with a }");
                    }
                }
            } else {
                String access$200 = Tokenizer.asString(nextCharRaw);
                throw problem(access$200, "'$' not followed by {, '" + Tokenizer.asString(nextCharRaw) + "' not allowed after '$'", true);
            }
        }

        private Token pullNextToken(WhitespaceSaver whitespaceSaver2) throws ProblemException {
            Token token;
            Token token2;
            int nextCharAfterWhitespace = nextCharAfterWhitespace(whitespaceSaver2);
            if (nextCharAfterWhitespace == -1) {
                return Tokens.END;
            }
            if (nextCharAfterWhitespace == 10) {
                Token newLine = Tokens.newLine(this.lineOrigin);
                int i = this.lineNumber + 1;
                this.lineNumber = i;
                this.lineOrigin = this.origin.withLineNumber(i);
                return newLine;
            }
            if (startOfComment(nextCharAfterWhitespace)) {
                token = pullComment(nextCharAfterWhitespace);
            } else {
                if (nextCharAfterWhitespace == 34) {
                    token2 = pullQuotedString();
                } else if (nextCharAfterWhitespace == 36) {
                    token2 = pullSubstitution();
                } else if (nextCharAfterWhitespace == 58) {
                    token2 = Tokens.COLON;
                } else if (nextCharAfterWhitespace == 61) {
                    token2 = Tokens.EQUALS;
                } else if (nextCharAfterWhitespace == 91) {
                    token2 = Tokens.OPEN_SQUARE;
                } else if (nextCharAfterWhitespace == 93) {
                    token2 = Tokens.CLOSE_SQUARE;
                } else if (nextCharAfterWhitespace == 123) {
                    token2 = Tokens.OPEN_CURLY;
                } else if (nextCharAfterWhitespace == 125) {
                    token2 = Tokens.CLOSE_CURLY;
                } else if (nextCharAfterWhitespace != 43) {
                    token2 = nextCharAfterWhitespace != 44 ? null : Tokens.COMMA;
                } else {
                    token2 = pullPlusEquals();
                }
                if (token2 != null) {
                    token = token2;
                } else if (firstNumberChars.indexOf(nextCharAfterWhitespace) >= 0) {
                    token = pullNumber(nextCharAfterWhitespace);
                } else if (notInUnquotedText.indexOf(nextCharAfterWhitespace) < 0) {
                    putBack(nextCharAfterWhitespace);
                    token = pullUnquotedText();
                } else {
                    String access$200 = Tokenizer.asString(nextCharAfterWhitespace);
                    throw problem(access$200, "Reserved character '" + Tokenizer.asString(nextCharAfterWhitespace) + "' is not allowed outside quotes", true);
                }
            }
            if (token != null) {
                return token;
            }
            throw new ConfigException.BugOrBroken("bug: failed to generate next token");
        }

        /* access modifiers changed from: private */
        public static boolean isSimpleValue(Token token) {
            return Tokens.isSubstitution(token) || Tokens.isUnquotedText(token) || Tokens.isValue(token);
        }

        private void queueNextToken() throws ProblemException {
            Token pullNextToken = pullNextToken(this.whitespaceSaver);
            Token check = this.whitespaceSaver.check(pullNextToken, this.origin, this.lineNumber);
            if (check != null) {
                this.tokens.add(check);
            }
            this.tokens.add(pullNextToken);
        }

        public boolean hasNext() {
            return !this.tokens.isEmpty();
        }

        public Token next() {
            Token remove = this.tokens.remove();
            if (this.tokens.isEmpty() && remove != Tokens.END) {
                try {
                    queueNextToken();
                } catch (ProblemException e) {
                    this.tokens.add(e.problem());
                }
                if (this.tokens.isEmpty()) {
                    throw new ConfigException.BugOrBroken("bug: tokens queue should not be empty here");
                }
            }
            return remove;
        }

        public void remove() {
            throw new UnsupportedOperationException("Does not make sense to remove items from token stream");
        }
    }
}
