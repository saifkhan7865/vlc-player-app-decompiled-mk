package io.netty.handler.codec.http2;

import io.netty.handler.codec.DefaultHeaders;
import io.netty.handler.codec.UnsupportedValueConverter;
import io.netty.handler.codec.ValueConverter;
import io.netty.util.AsciiString;

public final class CharSequenceMap<V> extends DefaultHeaders<CharSequence, V, CharSequenceMap<V>> {
    public CharSequenceMap() {
        this(true);
    }

    public CharSequenceMap(boolean z) {
        this(z, UnsupportedValueConverter.instance());
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public CharSequenceMap(boolean z, ValueConverter<V> valueConverter) {
        super(z ? AsciiString.CASE_SENSITIVE_HASHER : AsciiString.CASE_INSENSITIVE_HASHER, valueConverter);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public CharSequenceMap(boolean z, ValueConverter<V> valueConverter, int i) {
        super(z ? AsciiString.CASE_SENSITIVE_HASHER : AsciiString.CASE_INSENSITIVE_HASHER, valueConverter, DefaultHeaders.NameValidator.NOT_NULL, i);
    }
}
