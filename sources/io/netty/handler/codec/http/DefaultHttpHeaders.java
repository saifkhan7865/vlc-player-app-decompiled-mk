package io.netty.handler.codec.http;

import io.netty.handler.codec.CharSequenceValueConverter;
import io.netty.handler.codec.DateFormatter;
import io.netty.handler.codec.DefaultHeaders;
import io.netty.handler.codec.DefaultHeadersImpl;
import io.netty.handler.codec.HeadersUtils;
import io.netty.handler.codec.ValueConverter;
import io.netty.util.AsciiString;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

public class DefaultHttpHeaders extends HttpHeaders {
    static final DefaultHeaders.NameValidator<CharSequence> HttpNameValidator = new DefaultHeaders.NameValidator<CharSequence>() {
        public void validateName(CharSequence charSequence) {
            if (charSequence == null || charSequence.length() == 0) {
                throw new IllegalArgumentException("empty headers are not allowed [" + charSequence + AbstractJsonLexerKt.END_LIST);
            }
            int validateToken = HttpHeaderValidationUtil.validateToken(charSequence);
            if (validateToken != -1) {
                throw new IllegalArgumentException("a header name can only contain \"token\" characters, but found invalid character 0x" + Integer.toHexString(charSequence.charAt(validateToken)) + " at index " + validateToken + " of header '" + charSequence + "'.");
            }
        }
    };
    private final DefaultHeaders<CharSequence, CharSequence, ?> headers;

    public DefaultHttpHeaders() {
        this(true);
    }

    public DefaultHttpHeaders(boolean z) {
        this(z, nameValidator(z));
    }

    protected DefaultHttpHeaders(boolean z, DefaultHeaders.NameValidator<CharSequence> nameValidator) {
        this((DefaultHeaders<CharSequence, CharSequence, ?>) new DefaultHeadersImpl(AsciiString.CASE_INSENSITIVE_HASHER, HeaderValueConverter.INSTANCE, nameValidator, 16, valueValidator(z)));
    }

    protected DefaultHttpHeaders(DefaultHeaders<CharSequence, CharSequence, ?> defaultHeaders) {
        this.headers = defaultHeaders;
    }

    public HttpHeaders add(HttpHeaders httpHeaders) {
        if (!(httpHeaders instanceof DefaultHttpHeaders)) {
            return super.add(httpHeaders);
        }
        this.headers.add(((DefaultHttpHeaders) httpHeaders).headers);
        return this;
    }

    public HttpHeaders set(HttpHeaders httpHeaders) {
        if (!(httpHeaders instanceof DefaultHttpHeaders)) {
            return super.set(httpHeaders);
        }
        this.headers.set(((DefaultHttpHeaders) httpHeaders).headers);
        return this;
    }

    public HttpHeaders add(String str, Object obj) {
        this.headers.addObject(str, obj);
        return this;
    }

    public HttpHeaders add(CharSequence charSequence, Object obj) {
        this.headers.addObject(charSequence, obj);
        return this;
    }

    public HttpHeaders add(String str, Iterable<?> iterable) {
        this.headers.addObject(str, iterable);
        return this;
    }

    public HttpHeaders add(CharSequence charSequence, Iterable<?> iterable) {
        this.headers.addObject(charSequence, iterable);
        return this;
    }

    public HttpHeaders addInt(CharSequence charSequence, int i) {
        this.headers.addInt(charSequence, i);
        return this;
    }

    public HttpHeaders addShort(CharSequence charSequence, short s) {
        this.headers.addShort(charSequence, s);
        return this;
    }

    public HttpHeaders remove(String str) {
        this.headers.remove(str);
        return this;
    }

    public HttpHeaders remove(CharSequence charSequence) {
        this.headers.remove(charSequence);
        return this;
    }

    public HttpHeaders set(String str, Object obj) {
        this.headers.setObject(str, obj);
        return this;
    }

    public HttpHeaders set(CharSequence charSequence, Object obj) {
        this.headers.setObject(charSequence, obj);
        return this;
    }

    public HttpHeaders set(String str, Iterable<?> iterable) {
        this.headers.setObject(str, iterable);
        return this;
    }

    public HttpHeaders set(CharSequence charSequence, Iterable<?> iterable) {
        this.headers.setObject(charSequence, iterable);
        return this;
    }

    public HttpHeaders setInt(CharSequence charSequence, int i) {
        this.headers.setInt(charSequence, i);
        return this;
    }

    public HttpHeaders setShort(CharSequence charSequence, short s) {
        this.headers.setShort(charSequence, s);
        return this;
    }

    public HttpHeaders clear() {
        this.headers.clear();
        return this;
    }

    public String get(String str) {
        return get((CharSequence) str);
    }

    public String get(CharSequence charSequence) {
        return HeadersUtils.getAsString(this.headers, charSequence);
    }

    public Integer getInt(CharSequence charSequence) {
        return this.headers.getInt(charSequence);
    }

    public int getInt(CharSequence charSequence, int i) {
        return this.headers.getInt(charSequence, i);
    }

    public Short getShort(CharSequence charSequence) {
        return this.headers.getShort(charSequence);
    }

    public short getShort(CharSequence charSequence, short s) {
        return this.headers.getShort(charSequence, s);
    }

    public Long getTimeMillis(CharSequence charSequence) {
        return this.headers.getTimeMillis(charSequence);
    }

    public long getTimeMillis(CharSequence charSequence, long j) {
        return this.headers.getTimeMillis(charSequence, j);
    }

    public List<String> getAll(String str) {
        return getAll((CharSequence) str);
    }

    public List<String> getAll(CharSequence charSequence) {
        return HeadersUtils.getAllAsString(this.headers, charSequence);
    }

    public List<Map.Entry<String, String>> entries() {
        if (isEmpty()) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(this.headers.size());
        Iterator<Map.Entry<String, String>> it = iterator();
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
        return arrayList;
    }

    @Deprecated
    public Iterator<Map.Entry<String, String>> iterator() {
        return HeadersUtils.iteratorAsString(this.headers);
    }

    public Iterator<Map.Entry<CharSequence, CharSequence>> iteratorCharSequence() {
        return this.headers.iterator();
    }

    public Iterator<String> valueStringIterator(CharSequence charSequence) {
        final Iterator<CharSequence> valueCharSequenceIterator = valueCharSequenceIterator(charSequence);
        return new Iterator<String>() {
            public boolean hasNext() {
                return valueCharSequenceIterator.hasNext();
            }

            public String next() {
                return ((CharSequence) valueCharSequenceIterator.next()).toString();
            }

            public void remove() {
                valueCharSequenceIterator.remove();
            }
        };
    }

    public Iterator<CharSequence> valueCharSequenceIterator(CharSequence charSequence) {
        return this.headers.valueIterator(charSequence);
    }

    public boolean contains(String str) {
        return contains((CharSequence) str);
    }

    public boolean contains(CharSequence charSequence) {
        return this.headers.contains(charSequence);
    }

    public boolean isEmpty() {
        return this.headers.isEmpty();
    }

    public int size() {
        return this.headers.size();
    }

    public boolean contains(String str, String str2, boolean z) {
        return contains((CharSequence) str, (CharSequence) str2, z);
    }

    public boolean contains(CharSequence charSequence, CharSequence charSequence2, boolean z) {
        return this.headers.contains(charSequence, charSequence2, z ? AsciiString.CASE_INSENSITIVE_HASHER : AsciiString.CASE_SENSITIVE_HASHER);
    }

    public Set<String> names() {
        return HeadersUtils.namesAsString(this.headers);
    }

    public boolean equals(Object obj) {
        return (obj instanceof DefaultHttpHeaders) && this.headers.equals(((DefaultHttpHeaders) obj).headers, AsciiString.CASE_SENSITIVE_HASHER);
    }

    public int hashCode() {
        return this.headers.hashCode(AsciiString.CASE_SENSITIVE_HASHER);
    }

    public HttpHeaders copy() {
        return new DefaultHttpHeaders(this.headers.copy());
    }

    static ValueConverter<CharSequence> valueConverter() {
        return HeaderValueConverter.INSTANCE;
    }

    static DefaultHeaders.ValueValidator<CharSequence> valueValidator(boolean z) {
        return z ? HeaderValueValidator.INSTANCE : DefaultHeaders.ValueValidator.NO_VALIDATION;
    }

    static DefaultHeaders.NameValidator<CharSequence> nameValidator(boolean z) {
        return z ? HttpNameValidator : DefaultHeaders.NameValidator.NOT_NULL;
    }

    private static class HeaderValueConverter extends CharSequenceValueConverter {
        static final HeaderValueConverter INSTANCE = new HeaderValueConverter();

        private HeaderValueConverter() {
        }

        public CharSequence convertObject(Object obj) {
            if (obj instanceof CharSequence) {
                return (CharSequence) obj;
            }
            if (obj instanceof Date) {
                return DateFormatter.format((Date) obj);
            }
            if (obj instanceof Calendar) {
                return DateFormatter.format(((Calendar) obj).getTime());
            }
            return obj.toString();
        }
    }

    private static final class HeaderValueValidator implements DefaultHeaders.ValueValidator<CharSequence> {
        static final HeaderValueValidator INSTANCE = new HeaderValueValidator();

        private HeaderValueValidator() {
        }

        public void validate(CharSequence charSequence) {
            int validateValidHeaderValue = HttpHeaderValidationUtil.validateValidHeaderValue(charSequence);
            if (validateValidHeaderValue != -1) {
                throw new IllegalArgumentException("a header value contains prohibited character 0x" + Integer.toHexString(charSequence.charAt(validateValidHeaderValue)) + " at index " + validateValidHeaderValue + '.');
            }
        }
    }
}
