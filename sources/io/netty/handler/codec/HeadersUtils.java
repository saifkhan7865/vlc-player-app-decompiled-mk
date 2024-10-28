package io.netty.handler.codec;

import io.netty.util.internal.ObjectUtil;
import java.util.AbstractCollection;
import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import okhttp3.HttpUrl;

public final class HeadersUtils {
    private HeadersUtils() {
    }

    public static <K, V> List<String> getAllAsString(Headers<K, V, ?> headers, K k) {
        final List<V> all = headers.getAll(k);
        return new AbstractList<String>() {
            public String get(int i) {
                Object obj = all.get(i);
                if (obj != null) {
                    return obj.toString();
                }
                return null;
            }

            public int size() {
                return all.size();
            }
        };
    }

    public static <K, V> String getAsString(Headers<K, V, ?> headers, K k) {
        V v = headers.get(k);
        if (v != null) {
            return v.toString();
        }
        return null;
    }

    public static Iterator<Map.Entry<String, String>> iteratorAsString(Iterable<Map.Entry<CharSequence, CharSequence>> iterable) {
        return new StringEntryIterator(iterable.iterator());
    }

    public static <K, V> String toString(Class<?> cls, Iterator<Map.Entry<K, V>> it, int i) {
        String simpleName = cls.getSimpleName();
        if (i == 0) {
            return simpleName + HttpUrl.PATH_SEGMENT_ENCODE_SET_URI;
        }
        StringBuilder sb = new StringBuilder(simpleName.length() + 2 + (i * 20));
        sb.append(simpleName);
        sb.append(AbstractJsonLexerKt.BEGIN_LIST);
        while (it.hasNext()) {
            Map.Entry next = it.next();
            sb.append(next.getKey());
            sb.append(": ");
            sb.append(next.getValue());
            sb.append(", ");
        }
        sb.setLength(sb.length() - 2);
        sb.append(AbstractJsonLexerKt.END_LIST);
        return sb.toString();
    }

    public static Set<String> namesAsString(Headers<CharSequence, CharSequence, ?> headers) {
        return new DelegatingNameSet(headers);
    }

    private static final class StringEntryIterator implements Iterator<Map.Entry<String, String>> {
        private final Iterator<Map.Entry<CharSequence, CharSequence>> iter;

        StringEntryIterator(Iterator<Map.Entry<CharSequence, CharSequence>> it) {
            this.iter = it;
        }

        public boolean hasNext() {
            return this.iter.hasNext();
        }

        public Map.Entry<String, String> next() {
            return new StringEntry(this.iter.next());
        }

        public void remove() {
            this.iter.remove();
        }
    }

    private static final class StringEntry implements Map.Entry<String, String> {
        private final Map.Entry<CharSequence, CharSequence> entry;
        private String name;
        private String value;

        StringEntry(Map.Entry<CharSequence, CharSequence> entry2) {
            this.entry = entry2;
        }

        public String getKey() {
            if (this.name == null) {
                this.name = this.entry.getKey().toString();
            }
            return this.name;
        }

        public String getValue() {
            if (this.value == null && this.entry.getValue() != null) {
                this.value = this.entry.getValue().toString();
            }
            return this.value;
        }

        public String setValue(String str) {
            String value2 = getValue();
            this.entry.setValue(str);
            return value2;
        }

        public String toString() {
            return this.entry.toString();
        }
    }

    private static final class StringIterator<T> implements Iterator<String> {
        private final Iterator<T> iter;

        StringIterator(Iterator<T> it) {
            this.iter = it;
        }

        public boolean hasNext() {
            return this.iter.hasNext();
        }

        public String next() {
            T next = this.iter.next();
            if (next != null) {
                return next.toString();
            }
            return null;
        }

        public void remove() {
            this.iter.remove();
        }
    }

    private static final class DelegatingNameSet extends AbstractCollection<String> implements Set<String> {
        private final Headers<CharSequence, CharSequence, ?> headers;

        DelegatingNameSet(Headers<CharSequence, CharSequence, ?> headers2) {
            this.headers = (Headers) ObjectUtil.checkNotNull(headers2, "headers");
        }

        public int size() {
            return this.headers.names().size();
        }

        public boolean isEmpty() {
            return this.headers.isEmpty();
        }

        public boolean contains(Object obj) {
            return this.headers.contains(obj.toString());
        }

        public Iterator<String> iterator() {
            return new StringIterator(this.headers.names().iterator());
        }
    }
}
