package com.typesafe.config.impl;

import com.typesafe.config.ConfigException;
import com.typesafe.config.ConfigOrigin;
import com.typesafe.config.ConfigSyntax;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

public final class ConfigImplUtil {
    static boolean isC0Control(int i) {
        return i >= 0 && i <= 31;
    }

    private static char underscoreMappings(int i) {
        if (i == 1) {
            return '.';
        }
        if (i != 2) {
            return i != 3 ? 0 : '_';
        }
        return '-';
    }

    static boolean equalsHandlingNull(Object obj, Object obj2) {
        if (obj == null && obj2 != null) {
            return false;
        }
        if (obj != null && obj2 == null) {
            return false;
        }
        if (obj == obj2) {
            return true;
        }
        return obj.equals(obj2);
    }

    public static String renderJsonString(String str) {
        StringBuilder sb = new StringBuilder("\"");
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (charAt == 12) {
                sb.append("\\f");
            } else if (charAt == 13) {
                sb.append("\\r");
            } else if (charAt == '\"') {
                sb.append("\\\"");
            } else if (charAt != '\\') {
                switch (charAt) {
                    case 8:
                        sb.append("\\b");
                        break;
                    case 9:
                        sb.append("\\t");
                        break;
                    case 10:
                        sb.append("\\n");
                        break;
                    default:
                        if (!isC0Control(charAt)) {
                            sb.append(charAt);
                            break;
                        } else {
                            sb.append(String.format("\\u%04x", new Object[]{Integer.valueOf(charAt)}));
                            break;
                        }
                }
            } else {
                sb.append("\\\\");
            }
        }
        sb.append('\"');
        return sb.toString();
    }

    static String renderStringUnquotedIfPossible(String str) {
        if (str.length() == 0) {
            return renderJsonString(str);
        }
        int codePointAt = str.codePointAt(0);
        if (Character.isDigit(codePointAt) || codePointAt == 45) {
            return renderJsonString(str);
        }
        if (str.startsWith("include") || str.startsWith("true") || str.startsWith("false") || str.startsWith(AbstractJsonLexerKt.NULL) || str.contains("//")) {
            return renderJsonString(str);
        }
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (!Character.isLetter(charAt) && !Character.isDigit(charAt) && charAt != '-') {
                return renderJsonString(str);
            }
        }
        return str;
    }

    static boolean isWhitespace(int i) {
        if (i == 10 || i == 32 || i == 160 || i == 8199 || i == 8239 || i == 65279) {
            return true;
        }
        return Character.isWhitespace(i);
    }

    public static String unicodeTrim(String str) {
        int i;
        int i2;
        int length = str.length();
        if (length == 0) {
            return str;
        }
        int i3 = 0;
        while (i3 < length) {
            char charAt = str.charAt(i3);
            if (charAt != ' ' && charAt != 10) {
                int codePointAt = str.codePointAt(i3);
                if (!isWhitespace(codePointAt)) {
                    break;
                }
                i3 += Character.charCount(codePointAt);
            } else {
                i3++;
            }
        }
        while (length > i3) {
            int i4 = length - 1;
            char charAt2 = str.charAt(i4);
            if (charAt2 != ' ' && charAt2 != 10) {
                if (Character.isLowSurrogate(charAt2)) {
                    i2 = str.codePointAt(length - 2);
                    i = 2;
                } else {
                    i2 = str.codePointAt(i4);
                    i = 1;
                }
                if (!isWhitespace(i2)) {
                    break;
                }
                length -= i;
            } else {
                length--;
            }
        }
        return str.substring(i3, length);
    }

    public static ConfigException extractInitializerError(ExceptionInInitializerError exceptionInInitializerError) {
        Throwable cause = exceptionInInitializerError.getCause();
        if (cause != null && (cause instanceof ConfigException)) {
            return (ConfigException) cause;
        }
        throw exceptionInInitializerError;
    }

    static File urlToFile(URL url) {
        try {
            return new File(url.toURI());
        } catch (URISyntaxException unused) {
            return new File(url.getPath());
        } catch (IllegalArgumentException unused2) {
            return new File(url.getPath());
        }
    }

    public static String joinPath(String... strArr) {
        return new Path(strArr).render();
    }

    public static String joinPath(List<String> list) {
        return joinPath((String[]) list.toArray(new String[0]));
    }

    public static List<String> splitPath(String str) {
        ArrayList arrayList = new ArrayList();
        for (Path newPath = Path.newPath(str); newPath != null; newPath = newPath.remainder()) {
            arrayList.add(newPath.first());
        }
        return arrayList;
    }

    public static ConfigOrigin readOrigin(ObjectInputStream objectInputStream) throws IOException {
        return SerializedConfigValue.readOrigin(objectInputStream, (SimpleConfigOrigin) null);
    }

    public static void writeOrigin(ObjectOutputStream objectOutputStream, ConfigOrigin configOrigin) throws IOException {
        SerializedConfigValue.writeOrigin(new DataOutputStream(objectOutputStream), (SimpleConfigOrigin) configOrigin, (SimpleConfigOrigin) null);
    }

    static String toCamelCase(String str) {
        String[] split = str.split("-+");
        StringBuilder sb = new StringBuilder(str.length());
        for (String str2 : split) {
            if (sb.length() == 0) {
                sb.append(str2);
            } else {
                sb.append(str2.substring(0, 1).toUpperCase());
                sb.append(str2.substring(1));
            }
        }
        return sb.toString();
    }

    static String envVariableAsProperty(String str, String str2) throws ConfigException {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (char c : str.substring(str2.length(), str.length()).toCharArray()) {
            if (c == '_') {
                i++;
            } else {
                if (i > 0 && i < 4) {
                    sb.append(underscoreMappings(i));
                } else if (i > 3) {
                    throw new ConfigException.BadPath(str, "Environment variable contains an un-mapped number of underscores.");
                }
                sb.append(c);
                i = 0;
            }
        }
        if (i > 0 && i < 4) {
            sb.append(underscoreMappings(i));
        } else if (i > 3) {
            throw new ConfigException.BadPath(str, "Environment variable contains an un-mapped number of underscores.");
        }
        return sb.toString();
    }

    public static ConfigSyntax syntaxFromExtension(String str) {
        if (str == null) {
            return null;
        }
        if (str.endsWith(".json")) {
            return ConfigSyntax.JSON;
        }
        if (str.endsWith(".conf")) {
            return ConfigSyntax.CONF;
        }
        if (str.endsWith(".properties")) {
            return ConfigSyntax.PROPERTIES;
        }
        return null;
    }
}
