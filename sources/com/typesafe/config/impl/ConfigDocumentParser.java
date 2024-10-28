package com.typesafe.config.impl;

import com.typesafe.config.ConfigException;
import com.typesafe.config.ConfigOrigin;
import com.typesafe.config.ConfigParseOptions;
import com.typesafe.config.ConfigSyntax;
import com.typesafe.config.ConfigValueType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Stack;

final class ConfigDocumentParser {
    ConfigDocumentParser() {
    }

    static ConfigNodeRoot parse(Iterator<Token> it, ConfigOrigin configOrigin, ConfigParseOptions configParseOptions) {
        return new ParseContext(configParseOptions.getSyntax() == null ? ConfigSyntax.CONF : configParseOptions.getSyntax(), configOrigin, it).parse();
    }

    static AbstractConfigNodeValue parseValue(Iterator<Token> it, ConfigOrigin configOrigin, ConfigParseOptions configParseOptions) {
        return new ParseContext(configParseOptions.getSyntax() == null ? ConfigSyntax.CONF : configParseOptions.getSyntax(), configOrigin, it).parseSingleValue();
    }

    private static final class ParseContext {
        private final String ExpectingClosingParenthesisError = "expecting a close parentheses ')' here, not: ";
        private final ConfigOrigin baseOrigin;
        private final Stack<Token> buffer = new Stack<>();
        int equalsCount;
        private final ConfigSyntax flavor;
        private int lineNumber = 1;
        private final Iterator<Token> tokens;

        ParseContext(ConfigSyntax configSyntax, ConfigOrigin configOrigin, Iterator<Token> it) {
            this.tokens = it;
            this.flavor = configSyntax;
            this.equalsCount = 0;
            this.baseOrigin = configOrigin;
        }

        private Token popToken() {
            if (this.buffer.isEmpty()) {
                return this.tokens.next();
            }
            return this.buffer.pop();
        }

        private Token nextToken() {
            Token popToken = popToken();
            if (this.flavor == ConfigSyntax.JSON) {
                if (Tokens.isUnquotedText(popToken) && !isUnquotedWhitespace(popToken)) {
                    throw parseError("Token not allowed in valid JSON: '" + Tokens.getUnquotedText(popToken) + "'");
                } else if (Tokens.isSubstitution(popToken)) {
                    throw parseError("Substitutions (${} syntax) not allowed in JSON");
                }
            }
            return popToken;
        }

        private Token nextTokenCollectingWhitespace(Collection<AbstractConfigNode> collection) {
            Token nextToken;
            while (true) {
                nextToken = nextToken();
                if (!Tokens.isIgnoredWhitespace(nextToken) && !Tokens.isNewline(nextToken) && !isUnquotedWhitespace(nextToken)) {
                    if (!Tokens.isComment(nextToken)) {
                        break;
                    }
                    collection.add(new ConfigNodeComment(nextToken));
                } else {
                    collection.add(new ConfigNodeSingleToken(nextToken));
                    if (Tokens.isNewline(nextToken)) {
                        this.lineNumber = nextToken.lineNumber() + 1;
                    }
                }
            }
            int lineNumber2 = nextToken.lineNumber();
            if (lineNumber2 >= 0) {
                this.lineNumber = lineNumber2;
            }
            return nextToken;
        }

        private void putBack(Token token) {
            this.buffer.push(token);
        }

        private boolean checkElementSeparator(Collection<AbstractConfigNode> collection) {
            boolean z = false;
            if (this.flavor == ConfigSyntax.JSON) {
                Token nextTokenCollectingWhitespace = nextTokenCollectingWhitespace(collection);
                if (nextTokenCollectingWhitespace == Tokens.COMMA) {
                    collection.add(new ConfigNodeSingleToken(nextTokenCollectingWhitespace));
                    return true;
                }
                putBack(nextTokenCollectingWhitespace);
                return false;
            }
            Token nextToken = nextToken();
            while (true) {
                if (!Tokens.isIgnoredWhitespace(nextToken) && !isUnquotedWhitespace(nextToken)) {
                    if (!Tokens.isComment(nextToken)) {
                        if (!Tokens.isNewline(nextToken)) {
                            break;
                        }
                        this.lineNumber++;
                        collection.add(new ConfigNodeSingleToken(nextToken));
                        z = true;
                    } else {
                        collection.add(new ConfigNodeComment(nextToken));
                    }
                } else {
                    collection.add(new ConfigNodeSingleToken(nextToken));
                }
                nextToken = nextToken();
            }
            if (nextToken == Tokens.COMMA) {
                collection.add(new ConfigNodeSingleToken(nextToken));
                return true;
            }
            putBack(nextToken);
            return z;
        }

        private AbstractConfigNodeValue consolidateValues(Collection<AbstractConfigNode> collection) {
            AbstractConfigNodeValue abstractConfigNodeValue = null;
            if (this.flavor == ConfigSyntax.JSON) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            Token nextTokenCollectingWhitespace = nextTokenCollectingWhitespace(collection);
            int i = 0;
            while (true) {
                if (Tokens.isIgnoredWhitespace(nextTokenCollectingWhitespace)) {
                    arrayList.add(new ConfigNodeSingleToken(nextTokenCollectingWhitespace));
                    nextTokenCollectingWhitespace = nextToken();
                } else if (Tokens.isValue(nextTokenCollectingWhitespace) || Tokens.isUnquotedText(nextTokenCollectingWhitespace) || Tokens.isSubstitution(nextTokenCollectingWhitespace) || nextTokenCollectingWhitespace == Tokens.OPEN_CURLY || nextTokenCollectingWhitespace == Tokens.OPEN_SQUARE) {
                    AbstractConfigNodeValue parseValue = parseValue(nextTokenCollectingWhitespace);
                    i++;
                    if (parseValue != null) {
                        arrayList.add(parseValue);
                        nextTokenCollectingWhitespace = nextToken();
                    } else {
                        throw new ConfigException.BugOrBroken("no value");
                    }
                } else {
                    putBack(nextTokenCollectingWhitespace);
                    if (i < 2) {
                        Iterator it = arrayList.iterator();
                        while (it.hasNext()) {
                            AbstractConfigNode abstractConfigNode = (AbstractConfigNode) it.next();
                            if (abstractConfigNode instanceof AbstractConfigNodeValue) {
                                abstractConfigNodeValue = (AbstractConfigNodeValue) abstractConfigNode;
                            } else if (abstractConfigNodeValue == null) {
                                collection.add(abstractConfigNode);
                            } else {
                                putBack((Token) new ArrayList(abstractConfigNode.tokens()).get(0));
                            }
                        }
                        return abstractConfigNodeValue;
                    }
                    int size = arrayList.size() - 1;
                    while (size >= 0 && (arrayList.get(size) instanceof ConfigNodeSingleToken)) {
                        putBack(((ConfigNodeSingleToken) arrayList.get(size)).token());
                        arrayList.remove(size);
                        size--;
                    }
                    return new ConfigNodeConcatenation(arrayList);
                }
            }
        }

        private ConfigException parseError(String str) {
            return parseError(str, (Throwable) null);
        }

        private ConfigException parseError(String str, Throwable th) {
            return new ConfigException.Parse(this.baseOrigin.withLineNumber(this.lineNumber), str, th);
        }

        private String addQuoteSuggestion(String str, String str2) {
            return addQuoteSuggestion((Path) null, this.equalsCount > 0, str, str2);
        }

        private String addQuoteSuggestion(Path path, boolean z, String str, String str2) {
            String str3;
            String render = path != null ? path.render() : null;
            if (str.equals(Tokens.END.toString())) {
                if (render == null) {
                    return str2;
                }
                str3 = str2 + " (if you intended '" + render + "' to be part of a value, instead of a key, try adding double quotes around the whole value";
            } else if (render != null) {
                str3 = str2 + " (if you intended " + str + " to be part of the value for '" + render + "', try enclosing the value in double quotes";
            } else {
                str3 = str2 + " (if you intended " + str + " to be part of a key or string value, try enclosing the key or value in double quotes";
            }
            if (z) {
                return str3 + ", or you may be able to rename the file .properties rather than .conf)";
            }
            return str3 + ")";
        }

        private AbstractConfigNodeValue parseValue(Token token) {
            AbstractConfigNodeValue abstractConfigNodeValue;
            int i = this.equalsCount;
            if (Tokens.isValue(token) || Tokens.isUnquotedText(token) || Tokens.isSubstitution(token)) {
                abstractConfigNodeValue = new ConfigNodeSimpleValue(token);
            } else if (token == Tokens.OPEN_CURLY) {
                abstractConfigNodeValue = parseObject(true);
            } else if (token == Tokens.OPEN_SQUARE) {
                abstractConfigNodeValue = parseArray();
            } else {
                String token2 = token.toString();
                throw parseError(addQuoteSuggestion(token2, "Expecting a value but got wrong token: " + token));
            }
            if (this.equalsCount == i) {
                return abstractConfigNodeValue;
            }
            throw new ConfigException.BugOrBroken("Bug in config parser: unbalanced equals count");
        }

        private ConfigNodePath parseKey(Token token) {
            if (this.flavor != ConfigSyntax.JSON) {
                ArrayList arrayList = new ArrayList();
                while (true) {
                    if (!Tokens.isValue(token) && !Tokens.isUnquotedText(token)) {
                        break;
                    }
                    arrayList.add(token);
                    token = nextToken();
                }
                if (!arrayList.isEmpty()) {
                    putBack(token);
                    return PathParser.parsePathNodeExpression(arrayList.iterator(), this.baseOrigin.withLineNumber(this.lineNumber));
                }
                throw parseError("expecting a close parentheses ')' here, not: " + token);
            } else if (Tokens.isValueWithType(token, ConfigValueType.STRING)) {
                return PathParser.parsePathNodeExpression(Collections.singletonList(token).iterator(), this.baseOrigin.withLineNumber(this.lineNumber));
            } else {
                throw parseError("Expecting close brace } or a field name here, got " + token);
            }
        }

        private static boolean isIncludeKeyword(Token token) {
            return Tokens.isUnquotedText(token) && Tokens.getUnquotedText(token).equals("include");
        }

        private static boolean isUnquotedWhitespace(Token token) {
            if (!Tokens.isUnquotedText(token)) {
                return false;
            }
            String unquotedText = Tokens.getUnquotedText(token);
            for (int i = 0; i < unquotedText.length(); i++) {
                if (!ConfigImplUtil.isWhitespace(unquotedText.charAt(i))) {
                    return false;
                }
            }
            return true;
        }

        private boolean isKeyValueSeparatorToken(Token token) {
            if (this.flavor == ConfigSyntax.JSON) {
                if (token == Tokens.COLON) {
                    return true;
                }
                return false;
            } else if (token == Tokens.COLON || token == Tokens.EQUALS || token == Tokens.PLUS_EQUALS) {
                return true;
            } else {
                return false;
            }
        }

        private ConfigNodeInclude parseInclude(ArrayList<AbstractConfigNode> arrayList) {
            Token nextTokenCollectingWhitespace = nextTokenCollectingWhitespace(arrayList);
            if (Tokens.isUnquotedText(nextTokenCollectingWhitespace)) {
                String unquotedText = Tokens.getUnquotedText(nextTokenCollectingWhitespace);
                if (unquotedText.startsWith("required(")) {
                    String replaceFirst = unquotedText.replaceFirst("required\\(", "");
                    if (replaceFirst.length() > 0) {
                        putBack(Tokens.newUnquotedText(nextTokenCollectingWhitespace.origin(), replaceFirst));
                    }
                    arrayList.add(new ConfigNodeSingleToken(nextTokenCollectingWhitespace));
                    ConfigNodeInclude parseIncludeResource = parseIncludeResource(arrayList, true);
                    Token nextTokenCollectingWhitespace2 = nextTokenCollectingWhitespace(arrayList);
                    if (Tokens.isUnquotedText(nextTokenCollectingWhitespace2) && Tokens.getUnquotedText(nextTokenCollectingWhitespace2).equals(")")) {
                        return parseIncludeResource;
                    }
                    throw parseError("expecting a close parentheses ')' here, not: " + nextTokenCollectingWhitespace2);
                }
                putBack(nextTokenCollectingWhitespace);
                return parseIncludeResource(arrayList, false);
            }
            putBack(nextTokenCollectingWhitespace);
            return parseIncludeResource(arrayList, false);
        }

        private ConfigNodeInclude parseIncludeResource(ArrayList<AbstractConfigNode> arrayList, boolean z) {
            ConfigIncludeKind configIncludeKind;
            Token nextTokenCollectingWhitespace = nextTokenCollectingWhitespace(arrayList);
            if (Tokens.isUnquotedText(nextTokenCollectingWhitespace)) {
                String unquotedText = Tokens.getUnquotedText(nextTokenCollectingWhitespace);
                String str = "url(";
                if (unquotedText.startsWith(str)) {
                    configIncludeKind = ConfigIncludeKind.URL;
                } else {
                    str = "file(";
                    if (unquotedText.startsWith(str)) {
                        configIncludeKind = ConfigIncludeKind.FILE;
                    } else {
                        str = "classpath(";
                        if (unquotedText.startsWith(str)) {
                            configIncludeKind = ConfigIncludeKind.CLASSPATH;
                        } else {
                            throw parseError("expecting include parameter to be quoted filename, file(), classpath(), or url(). No spaces are allowed before the open paren. Not expecting: " + nextTokenCollectingWhitespace);
                        }
                    }
                }
                String replaceFirst = unquotedText.replaceFirst("[^(]*\\(", "");
                if (replaceFirst.length() > 0) {
                    putBack(Tokens.newUnquotedText(nextTokenCollectingWhitespace.origin(), replaceFirst));
                }
                arrayList.add(new ConfigNodeSingleToken(nextTokenCollectingWhitespace));
                Token nextTokenCollectingWhitespace2 = nextTokenCollectingWhitespace(arrayList);
                if (Tokens.isValueWithType(nextTokenCollectingWhitespace2, ConfigValueType.STRING)) {
                    arrayList.add(new ConfigNodeSimpleValue(nextTokenCollectingWhitespace2));
                    Token nextTokenCollectingWhitespace3 = nextTokenCollectingWhitespace(arrayList);
                    if (!Tokens.isUnquotedText(nextTokenCollectingWhitespace3) || !Tokens.getUnquotedText(nextTokenCollectingWhitespace3).startsWith(")")) {
                        throw parseError("expecting a close parentheses ')' here, not: " + nextTokenCollectingWhitespace3);
                    }
                    String substring = Tokens.getUnquotedText(nextTokenCollectingWhitespace3).substring(1);
                    if (substring.length() > 0) {
                        putBack(Tokens.newUnquotedText(nextTokenCollectingWhitespace3.origin(), substring));
                    }
                    return new ConfigNodeInclude(arrayList, configIncludeKind, z);
                }
                throw parseError("expecting include " + str + ") parameter to be a quoted string, rather than: " + nextTokenCollectingWhitespace2);
            } else if (Tokens.isValueWithType(nextTokenCollectingWhitespace, ConfigValueType.STRING)) {
                arrayList.add(new ConfigNodeSimpleValue(nextTokenCollectingWhitespace));
                return new ConfigNodeInclude(arrayList, ConfigIncludeKind.HEURISTIC, z);
            } else {
                throw parseError("include keyword is not followed by a quoted string, but by: " + nextTokenCollectingWhitespace);
            }
        }

        private ConfigNodeComplexValue parseObject(boolean z) {
            AbstractConfigNodeValue abstractConfigNodeValue;
            boolean z2;
            ArrayList arrayList = new ArrayList();
            HashMap hashMap = new HashMap();
            if (z) {
                arrayList.add(new ConfigNodeSingleToken(Tokens.OPEN_CURLY));
            }
            boolean z3 = false;
            boolean z4 = false;
            while (true) {
                Token nextTokenCollectingWhitespace = nextTokenCollectingWhitespace(arrayList);
                if (nextTokenCollectingWhitespace != Tokens.CLOSE_CURLY) {
                    if (nextTokenCollectingWhitespace == Tokens.END && !z) {
                        putBack(nextTokenCollectingWhitespace);
                        break;
                    }
                    if (this.flavor == ConfigSyntax.JSON || !isIncludeKeyword(nextTokenCollectingWhitespace)) {
                        ArrayList arrayList2 = new ArrayList();
                        ConfigNodePath parseKey = parseKey(nextTokenCollectingWhitespace);
                        arrayList2.add(parseKey);
                        Token nextTokenCollectingWhitespace2 = nextTokenCollectingWhitespace(arrayList2);
                        if (this.flavor == ConfigSyntax.CONF && nextTokenCollectingWhitespace2 == Tokens.OPEN_CURLY) {
                            abstractConfigNodeValue = parseValue(nextTokenCollectingWhitespace2);
                            z2 = false;
                        } else if (isKeyValueSeparatorToken(nextTokenCollectingWhitespace2)) {
                            arrayList2.add(new ConfigNodeSingleToken(nextTokenCollectingWhitespace2));
                            if (nextTokenCollectingWhitespace2 == Tokens.EQUALS) {
                                this.equalsCount++;
                                z2 = true;
                            } else {
                                z2 = false;
                            }
                            abstractConfigNodeValue = consolidateValues(arrayList2);
                            if (abstractConfigNodeValue == null) {
                                abstractConfigNodeValue = parseValue(nextTokenCollectingWhitespace(arrayList2));
                            }
                        } else {
                            throw parseError(addQuoteSuggestion(nextTokenCollectingWhitespace2.toString(), "Key '" + parseKey.render() + "' may not be followed by token: " + nextTokenCollectingWhitespace2));
                        }
                        arrayList2.add(abstractConfigNodeValue);
                        if (z2) {
                            this.equalsCount--;
                        }
                        String first = parseKey.value().first();
                        if (parseKey.value().remainder() == null) {
                            if (((Boolean) hashMap.get(first)) == null || this.flavor != ConfigSyntax.JSON) {
                                hashMap.put(first, true);
                            } else {
                                throw parseError("JSON does not allow duplicate fields: '" + first + "' was already seen");
                            }
                        } else if (this.flavor != ConfigSyntax.JSON) {
                            hashMap.put(first, true);
                        } else {
                            throw new ConfigException.BugOrBroken("somehow got multi-element path in JSON mode");
                        }
                        arrayList.add(new ConfigNodeField(arrayList2));
                        z4 = z2;
                    } else {
                        ArrayList arrayList3 = new ArrayList();
                        arrayList3.add(new ConfigNodeSingleToken(nextTokenCollectingWhitespace));
                        arrayList.add(parseInclude(arrayList3));
                    }
                    if (checkElementSeparator(arrayList)) {
                        z3 = true;
                    } else {
                        Token nextTokenCollectingWhitespace3 = nextTokenCollectingWhitespace(arrayList);
                        if (nextTokenCollectingWhitespace3 == Tokens.CLOSE_CURLY) {
                            if (z) {
                                arrayList.add(new ConfigNodeSingleToken(nextTokenCollectingWhitespace3));
                            } else {
                                throw parseError(addQuoteSuggestion((Path) null, z4, nextTokenCollectingWhitespace3.toString(), "unbalanced close brace '}' with no open brace"));
                            }
                        } else if (z) {
                            throw parseError(addQuoteSuggestion((Path) null, z4, nextTokenCollectingWhitespace3.toString(), "Expecting close brace } or a comma, got " + nextTokenCollectingWhitespace3));
                        } else if (nextTokenCollectingWhitespace3 == Tokens.END) {
                            putBack(nextTokenCollectingWhitespace3);
                        } else {
                            throw parseError(addQuoteSuggestion((Path) null, z4, nextTokenCollectingWhitespace3.toString(), "Expecting end of input or a comma, got " + nextTokenCollectingWhitespace3));
                        }
                    }
                } else if (this.flavor == ConfigSyntax.JSON && z3) {
                    throw parseError(addQuoteSuggestion(nextTokenCollectingWhitespace.toString(), "expecting a field name after a comma, got a close brace } instead"));
                } else if (z) {
                    arrayList.add(new ConfigNodeSingleToken(Tokens.CLOSE_CURLY));
                } else {
                    throw parseError(addQuoteSuggestion(nextTokenCollectingWhitespace.toString(), "unbalanced close brace '}' with no open brace"));
                }
            }
            return new ConfigNodeObject(arrayList);
        }

        private ConfigNodeComplexValue parseArray() {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new ConfigNodeSingleToken(Tokens.OPEN_SQUARE));
            AbstractConfigNodeValue consolidateValues = consolidateValues(arrayList);
            if (consolidateValues != null) {
                arrayList.add(consolidateValues);
            } else {
                Token nextTokenCollectingWhitespace = nextTokenCollectingWhitespace(arrayList);
                if (nextTokenCollectingWhitespace == Tokens.CLOSE_SQUARE) {
                    arrayList.add(new ConfigNodeSingleToken(nextTokenCollectingWhitespace));
                    return new ConfigNodeArray(arrayList);
                } else if (Tokens.isValue(nextTokenCollectingWhitespace) || nextTokenCollectingWhitespace == Tokens.OPEN_CURLY || nextTokenCollectingWhitespace == Tokens.OPEN_SQUARE || Tokens.isUnquotedText(nextTokenCollectingWhitespace) || Tokens.isSubstitution(nextTokenCollectingWhitespace)) {
                    arrayList.add(parseValue(nextTokenCollectingWhitespace));
                } else {
                    throw parseError("List should have ] or a first element after the open [, instead had token: " + nextTokenCollectingWhitespace + " (if you want " + nextTokenCollectingWhitespace + " to be part of a string value, then double-quote it)");
                }
            }
            while (checkElementSeparator(arrayList)) {
                AbstractConfigNodeValue consolidateValues2 = consolidateValues(arrayList);
                if (consolidateValues2 != null) {
                    arrayList.add(consolidateValues2);
                } else {
                    Token nextTokenCollectingWhitespace2 = nextTokenCollectingWhitespace(arrayList);
                    if (Tokens.isValue(nextTokenCollectingWhitespace2) || nextTokenCollectingWhitespace2 == Tokens.OPEN_CURLY || nextTokenCollectingWhitespace2 == Tokens.OPEN_SQUARE || Tokens.isUnquotedText(nextTokenCollectingWhitespace2) || Tokens.isSubstitution(nextTokenCollectingWhitespace2)) {
                        arrayList.add(parseValue(nextTokenCollectingWhitespace2));
                    } else if (this.flavor == ConfigSyntax.JSON || nextTokenCollectingWhitespace2 != Tokens.CLOSE_SQUARE) {
                        throw parseError("List should have had new element after a comma, instead had token: " + nextTokenCollectingWhitespace2 + " (if you want the comma or " + nextTokenCollectingWhitespace2 + " to be part of a string value, then double-quote it)");
                    } else {
                        putBack(nextTokenCollectingWhitespace2);
                    }
                }
            }
            Token nextTokenCollectingWhitespace3 = nextTokenCollectingWhitespace(arrayList);
            if (nextTokenCollectingWhitespace3 == Tokens.CLOSE_SQUARE) {
                arrayList.add(new ConfigNodeSingleToken(nextTokenCollectingWhitespace3));
                return new ConfigNodeArray(arrayList);
            }
            throw parseError("List should have ended with ] or had a comma, instead had token: " + nextTokenCollectingWhitespace3 + " (if you want " + nextTokenCollectingWhitespace3 + " to be part of a string value, then double-quote it)");
        }

        /* access modifiers changed from: package-private */
        public ConfigNodeRoot parse() {
            Object obj;
            ArrayList arrayList = new ArrayList();
            Token nextToken = nextToken();
            if (nextToken == Tokens.START) {
                Token nextTokenCollectingWhitespace = nextTokenCollectingWhitespace(arrayList);
                boolean z = false;
                if (nextTokenCollectingWhitespace == Tokens.OPEN_CURLY || nextTokenCollectingWhitespace == Tokens.OPEN_SQUARE) {
                    obj = parseValue(nextTokenCollectingWhitespace);
                } else if (this.flavor != ConfigSyntax.JSON) {
                    putBack(nextTokenCollectingWhitespace);
                    obj = parseObject(false);
                    z = true;
                } else if (nextTokenCollectingWhitespace == Tokens.END) {
                    throw parseError("Empty document");
                } else {
                    throw parseError("Document must have an object or array at root, unexpected token: " + nextTokenCollectingWhitespace);
                }
                if (!(obj instanceof ConfigNodeObject) || !z) {
                    arrayList.add(obj);
                } else {
                    arrayList.addAll(((ConfigNodeComplexValue) obj).children());
                }
                Token nextTokenCollectingWhitespace2 = nextTokenCollectingWhitespace(arrayList);
                if (nextTokenCollectingWhitespace2 != Tokens.END) {
                    throw parseError("Document has trailing tokens after first object or array: " + nextTokenCollectingWhitespace2);
                } else if (z) {
                    return new ConfigNodeRoot(Collections.singletonList(new ConfigNodeObject(arrayList)), this.baseOrigin);
                } else {
                    return new ConfigNodeRoot(arrayList, this.baseOrigin);
                }
            } else {
                throw new ConfigException.BugOrBroken("token stream did not begin with START, had " + nextToken);
            }
        }

        /* access modifiers changed from: package-private */
        public AbstractConfigNodeValue parseSingleValue() {
            Token nextToken = nextToken();
            if (nextToken == Tokens.START) {
                Token nextToken2 = nextToken();
                if (Tokens.isIgnoredWhitespace(nextToken2) || Tokens.isNewline(nextToken2) || isUnquotedWhitespace(nextToken2) || Tokens.isComment(nextToken2)) {
                    throw parseError("The value from withValueText cannot have leading or trailing newlines, whitespace, or comments");
                } else if (nextToken2 == Tokens.END) {
                    throw parseError("Empty value");
                } else if (this.flavor == ConfigSyntax.JSON) {
                    AbstractConfigNodeValue parseValue = parseValue(nextToken2);
                    if (nextToken() == Tokens.END) {
                        return parseValue;
                    }
                    throw parseError("Parsing JSON and the value set in withValueText was either a concatenation or had trailing whitespace, newlines, or comments");
                } else {
                    putBack(nextToken2);
                    AbstractConfigNodeValue consolidateValues = consolidateValues(new ArrayList());
                    if (nextToken() == Tokens.END) {
                        return consolidateValues;
                    }
                    throw parseError("The value from withValueText cannot have leading or trailing newlines, whitespace, or comments");
                }
            } else {
                throw new ConfigException.BugOrBroken("token stream did not begin with START, had " + nextToken);
            }
        }
    }
}
