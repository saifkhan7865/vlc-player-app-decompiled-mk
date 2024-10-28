package io.netty.handler.codec.http;

import io.ktor.http.ContentDisposition;
import io.netty.util.AsciiString;
import io.netty.util.internal.MathUtil;
import io.netty.util.internal.ObjectUtil;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

public class HttpMethod implements Comparable<HttpMethod> {
    public static final HttpMethod CONNECT;
    public static final HttpMethod DELETE;
    public static final HttpMethod GET;
    public static final HttpMethod HEAD;
    public static final HttpMethod OPTIONS;
    public static final HttpMethod PATCH;
    public static final HttpMethod POST;
    public static final HttpMethod PUT;
    public static final HttpMethod TRACE;
    private static final EnumNameMap<HttpMethod> methodMap;
    private final AsciiString name;

    static {
        HttpMethod httpMethod = new HttpMethod("OPTIONS");
        OPTIONS = httpMethod;
        HttpMethod httpMethod2 = new HttpMethod("GET");
        GET = httpMethod2;
        HttpMethod httpMethod3 = new HttpMethod("HEAD");
        HEAD = httpMethod3;
        HttpMethod httpMethod4 = new HttpMethod("POST");
        POST = httpMethod4;
        HttpMethod httpMethod5 = new HttpMethod("PUT");
        PUT = httpMethod5;
        HttpMethod httpMethod6 = new HttpMethod("PATCH");
        PATCH = httpMethod6;
        HttpMethod httpMethod7 = new HttpMethod("DELETE");
        DELETE = httpMethod7;
        HttpMethod httpMethod8 = new HttpMethod("TRACE");
        TRACE = httpMethod8;
        HttpMethod httpMethod9 = new HttpMethod("CONNECT");
        CONNECT = httpMethod9;
        methodMap = new EnumNameMap<>(new EnumNameMap.Node(httpMethod.toString(), httpMethod), new EnumNameMap.Node(httpMethod2.toString(), httpMethod2), new EnumNameMap.Node(httpMethod3.toString(), httpMethod3), new EnumNameMap.Node(httpMethod4.toString(), httpMethod4), new EnumNameMap.Node(httpMethod5.toString(), httpMethod5), new EnumNameMap.Node(httpMethod6.toString(), httpMethod6), new EnumNameMap.Node(httpMethod7.toString(), httpMethod7), new EnumNameMap.Node(httpMethod8.toString(), httpMethod8), new EnumNameMap.Node(httpMethod9.toString(), httpMethod9));
    }

    public static HttpMethod valueOf(String str) {
        HttpMethod httpMethod = GET;
        if (str == httpMethod.name()) {
            return httpMethod;
        }
        HttpMethod httpMethod2 = POST;
        if (str == httpMethod2.name()) {
            return httpMethod2;
        }
        HttpMethod httpMethod3 = methodMap.get(str);
        return httpMethod3 != null ? httpMethod3 : new HttpMethod(str);
    }

    public HttpMethod(String str) {
        String checkNonEmptyAfterTrim = ObjectUtil.checkNonEmptyAfterTrim(str, ContentDisposition.Parameters.Name);
        for (int i = 0; i < checkNonEmptyAfterTrim.length(); i++) {
            char charAt = checkNonEmptyAfterTrim.charAt(i);
            if (Character.isISOControl(charAt) || Character.isWhitespace(charAt)) {
                throw new IllegalArgumentException("invalid character in name");
            }
        }
        this.name = AsciiString.cached(checkNonEmptyAfterTrim);
    }

    public String name() {
        return this.name.toString();
    }

    public AsciiString asciiName() {
        return this.name;
    }

    public int hashCode() {
        return name().hashCode();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof HttpMethod)) {
            return false;
        }
        return name().equals(((HttpMethod) obj).name());
    }

    public String toString() {
        return this.name.toString();
    }

    public int compareTo(HttpMethod httpMethod) {
        if (httpMethod == this) {
            return 0;
        }
        return name().compareTo(httpMethod.name());
    }

    private static final class EnumNameMap<T> {
        private final Node<T>[] values;
        private final int valuesMask;

        EnumNameMap(Node<T>... nodeArr) {
            Node<T>[] nodeArr2 = (Node[]) new Node[MathUtil.findNextPositivePowerOfTwo(nodeArr.length)];
            this.values = nodeArr2;
            this.valuesMask = nodeArr2.length - 1;
            int length = nodeArr.length;
            int i = 0;
            while (i < length) {
                Node<T> node = nodeArr[i];
                int hashCode = hashCode(node.key) & this.valuesMask;
                Node<T>[] nodeArr3 = this.values;
                if (nodeArr3[hashCode] == null) {
                    nodeArr3[hashCode] = node;
                    i++;
                } else {
                    throw new IllegalArgumentException("index " + hashCode + " collision between values: [" + this.values[hashCode].key + ", " + node.key + AbstractJsonLexerKt.END_LIST);
                }
            }
        }

        /* access modifiers changed from: package-private */
        public T get(String str) {
            Node<T> node = this.values[hashCode(str) & this.valuesMask];
            if (node == null || !node.key.equals(str)) {
                return null;
            }
            return node.value;
        }

        private static int hashCode(String str) {
            return str.hashCode() >>> 6;
        }

        private static final class Node<T> {
            final String key;
            final T value;

            Node(String str, T t) {
                this.key = str;
                this.value = t;
            }
        }
    }
}
