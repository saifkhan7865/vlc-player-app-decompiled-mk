package com.typesafe.config.impl;

import com.typesafe.config.ConfigException;
import com.typesafe.config.ConfigOrigin;
import com.typesafe.config.ConfigSyntax;
import com.typesafe.config.ConfigValueType;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.fusesource.jansi.AnsiRenderer;

final class PathParser {
    static ConfigOrigin apiOrigin = SimpleConfigOrigin.newSimple("path parameter");

    PathParser() {
    }

    static class Element {
        boolean canBeEmpty;
        StringBuilder sb;

        Element(String str, boolean z) {
            this.canBeEmpty = z;
            this.sb = new StringBuilder(str);
        }

        public String toString() {
            return "Element(" + this.sb.toString() + AnsiRenderer.CODE_LIST_SEPARATOR + this.canBeEmpty + ")";
        }
    }

    static ConfigNodePath parsePathNode(String str) {
        return parsePathNode(str, ConfigSyntax.CONF);
    }

    static ConfigNodePath parsePathNode(String str, ConfigSyntax configSyntax) {
        StringReader stringReader = new StringReader(str);
        try {
            Iterator<Token> it = Tokenizer.tokenize(apiOrigin, stringReader, configSyntax);
            it.next();
            return parsePathNodeExpression(it, apiOrigin, str, configSyntax);
        } finally {
            stringReader.close();
        }
    }

    static Path parsePath(String str) {
        Path speculativeFastParsePath = speculativeFastParsePath(str);
        if (speculativeFastParsePath != null) {
            return speculativeFastParsePath;
        }
        StringReader stringReader = new StringReader(str);
        try {
            Iterator<Token> it = Tokenizer.tokenize(apiOrigin, stringReader, ConfigSyntax.CONF);
            it.next();
            return parsePathExpression(it, apiOrigin, str);
        } finally {
            stringReader.close();
        }
    }

    protected static Path parsePathExpression(Iterator<Token> it, ConfigOrigin configOrigin) {
        return parsePathExpression(it, configOrigin, (String) null, (ArrayList<Token>) null, ConfigSyntax.CONF);
    }

    protected static Path parsePathExpression(Iterator<Token> it, ConfigOrigin configOrigin, String str) {
        return parsePathExpression(it, configOrigin, str, (ArrayList<Token>) null, ConfigSyntax.CONF);
    }

    protected static ConfigNodePath parsePathNodeExpression(Iterator<Token> it, ConfigOrigin configOrigin) {
        return parsePathNodeExpression(it, configOrigin, (String) null, ConfigSyntax.CONF);
    }

    protected static ConfigNodePath parsePathNodeExpression(Iterator<Token> it, ConfigOrigin configOrigin, String str, ConfigSyntax configSyntax) {
        ArrayList arrayList = new ArrayList();
        return new ConfigNodePath(parsePathExpression(it, configOrigin, str, arrayList, configSyntax), arrayList);
    }

    protected static Path parsePathExpression(Iterator<Token> it, ConfigOrigin configOrigin, String str, ArrayList<Token> arrayList, ConfigSyntax configSyntax) {
        String str2;
        ArrayList<Element> arrayList2 = new ArrayList<>();
        arrayList2.add(new Element("", false));
        if (it.hasNext()) {
            while (it.hasNext()) {
                Token next = it.next();
                if (arrayList != null) {
                    arrayList.add(next);
                }
                if (!Tokens.isIgnoredWhitespace(next)) {
                    if (Tokens.isValueWithType(next, ConfigValueType.STRING)) {
                        addPathText(arrayList2, true, Tokens.getValue(next).transformToString());
                    } else if (next != Tokens.END) {
                        if (Tokens.isValue(next)) {
                            AbstractConfigValue value = Tokens.getValue(next);
                            if (arrayList != null) {
                                arrayList.remove(arrayList.size() - 1);
                                arrayList.addAll(splitTokenOnPeriod(next, configSyntax));
                            }
                            str2 = value.transformToString();
                        } else if (Tokens.isUnquotedText(next)) {
                            if (arrayList != null) {
                                arrayList.remove(arrayList.size() - 1);
                                arrayList.addAll(splitTokenOnPeriod(next, configSyntax));
                            }
                            str2 = Tokens.getUnquotedText(next);
                        } else {
                            throw new ConfigException.BadPath(configOrigin, str, "Token not allowed in path expression: " + next + " (you can double-quote this token if you really want it here)");
                        }
                        addPathText(arrayList2, false, str2);
                    } else {
                        continue;
                    }
                }
            }
            PathBuilder pathBuilder = new PathBuilder();
            for (Element element : arrayList2) {
                if (element.sb.length() != 0 || element.canBeEmpty) {
                    pathBuilder.appendKey(element.sb.toString());
                } else {
                    throw new ConfigException.BadPath(configOrigin, str, "path has a leading, trailing, or two adjacent period '.' (use quoted \"\" empty string if you want an empty element)");
                }
            }
            return pathBuilder.result();
        }
        throw new ConfigException.BadPath(configOrigin, str, "Expecting a field name or path here, but got nothing");
    }

    private static Collection<Token> splitTokenOnPeriod(Token token, ConfigSyntax configSyntax) {
        String str = token.tokenText();
        if (str.equals(".")) {
            return Collections.singletonList(token);
        }
        String[] split = str.split("\\.");
        ArrayList arrayList = new ArrayList();
        for (String str2 : split) {
            if (configSyntax == ConfigSyntax.CONF) {
                arrayList.add(Tokens.newUnquotedText(token.origin(), str2));
            } else {
                arrayList.add(Tokens.newString(token.origin(), str2, "\"" + str2 + "\""));
            }
            arrayList.add(Tokens.newUnquotedText(token.origin(), "."));
        }
        if (str.charAt(str.length() - 1) != '.') {
            arrayList.remove(arrayList.size() - 1);
        }
        return arrayList;
    }

    private static void addPathText(List<Element> list, boolean z, String str) {
        int indexOf = z ? -1 : str.indexOf(46);
        Element element = list.get(list.size() - 1);
        if (indexOf < 0) {
            element.sb.append(str);
            if (z && element.sb.length() == 0) {
                element.canBeEmpty = true;
                return;
            }
            return;
        }
        element.sb.append(str.substring(0, indexOf));
        list.add(new Element("", false));
        addPathText(list, false, str.substring(indexOf + 1));
    }

    private static boolean looksUnsafeForFastParser(String str) {
        int length = str.length();
        if (str.isEmpty() || str.charAt(0) == '.' || str.charAt(length - 1) == '.') {
            return true;
        }
        boolean z = true;
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if ((charAt >= 'a' && charAt <= 'z') || ((charAt >= 'A' && charAt <= 'Z') || charAt == '_')) {
                z = false;
            } else if (charAt == '.') {
                if (z) {
                    return true;
                }
                z = true;
            } else if (charAt != '-' || z) {
                return true;
            }
        }
        return z;
    }

    private static Path fastPathBuild(Path path, String str, int i) {
        int lastIndexOf = str.lastIndexOf(46, i - 1);
        Path path2 = new Path(str.substring(lastIndexOf + 1, i), path);
        if (lastIndexOf < 0) {
            return path2;
        }
        return fastPathBuild(path2, str, lastIndexOf);
    }

    private static Path speculativeFastParsePath(String str) {
        String unicodeTrim = ConfigImplUtil.unicodeTrim(str);
        if (looksUnsafeForFastParser(unicodeTrim)) {
            return null;
        }
        return fastPathBuild((Path) null, unicodeTrim, unicodeTrim.length());
    }
}
