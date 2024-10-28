package io.netty.handler.codec.http2;

import io.netty.handler.codec.CharSequenceValueConverter;
import io.netty.handler.codec.DefaultHeaders;
import io.netty.handler.codec.http.HttpHeaderValidationUtil;
import io.netty.handler.codec.http2.Http2Headers;
import io.netty.util.AsciiString;
import io.netty.util.ByteProcessor;
import io.netty.util.internal.PlatformDependent;
import java.util.Iterator;

public class DefaultHttp2Headers extends DefaultHeaders<CharSequence, CharSequence, Http2Headers> implements Http2Headers {
    static final DefaultHeaders.NameValidator<CharSequence> HTTP2_NAME_VALIDATOR = new DefaultHeaders.NameValidator<CharSequence>() {
        public void validateName(CharSequence charSequence) {
            if (charSequence == null || charSequence.length() == 0) {
                PlatformDependent.throwException(Http2Exception.connectionError(Http2Error.PROTOCOL_ERROR, "empty headers are not allowed [%s]", charSequence));
            }
            if (charSequence instanceof AsciiString) {
                try {
                    if (((AsciiString) charSequence).forEachByte(DefaultHttp2Headers.HTTP2_NAME_VALIDATOR_PROCESSOR) != -1) {
                        PlatformDependent.throwException(Http2Exception.connectionError(Http2Error.PROTOCOL_ERROR, "invalid header name [%s]", charSequence));
                    }
                } catch (Http2Exception e) {
                    PlatformDependent.throwException(e);
                    return;
                } catch (Throwable th) {
                    PlatformDependent.throwException(Http2Exception.connectionError(Http2Error.PROTOCOL_ERROR, th, "unexpected error. invalid header name [%s]", charSequence));
                    return;
                }
            } else {
                for (int i = 0; i < charSequence.length(); i++) {
                    if (AsciiString.isUpperCase(charSequence.charAt(i))) {
                        PlatformDependent.throwException(Http2Exception.connectionError(Http2Error.PROTOCOL_ERROR, "invalid header name [%s]", charSequence));
                    }
                }
            }
            if (Http2Headers.PseudoHeaderName.hasPseudoHeaderFormat(charSequence) && Http2Headers.PseudoHeaderName.getPseudoHeader(charSequence) == null) {
                PlatformDependent.throwException(Http2Exception.connectionError(Http2Error.PROTOCOL_ERROR, "Invalid HTTP/2 pseudo-header '%s' encountered.", charSequence));
            }
        }
    };
    /* access modifiers changed from: private */
    public static final ByteProcessor HTTP2_NAME_VALIDATOR_PROCESSOR = new ByteProcessor() {
        public boolean process(byte b) {
            return !AsciiString.isUpperCase(b);
        }
    };
    private static final DefaultHeaders.ValueValidator<CharSequence> VALUE_VALIDATOR = new DefaultHeaders.ValueValidator<CharSequence>() {
        public void validate(CharSequence charSequence) {
            int validateValidHeaderValue = HttpHeaderValidationUtil.validateValidHeaderValue(charSequence);
            if (validateValidHeaderValue != -1) {
                throw new IllegalArgumentException("a header value contains prohibited character 0x" + Integer.toHexString(charSequence.charAt(validateValidHeaderValue)) + " at index " + validateValidHeaderValue + '.');
            }
        }
    };
    /* access modifiers changed from: private */
    public DefaultHeaders.HeaderEntry<CharSequence, CharSequence> firstNonPseudo;

    public /* bridge */ /* synthetic */ Iterator valueIterator(CharSequence charSequence) {
        return super.valueIterator(charSequence);
    }

    public DefaultHttp2Headers() {
        this(true);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public DefaultHttp2Headers(boolean z) {
        super(AsciiString.CASE_SENSITIVE_HASHER, CharSequenceValueConverter.INSTANCE, z ? HTTP2_NAME_VALIDATOR : DefaultHeaders.NameValidator.NOT_NULL);
        this.firstNonPseudo = this.head;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public DefaultHttp2Headers(boolean z, int i) {
        super(AsciiString.CASE_SENSITIVE_HASHER, CharSequenceValueConverter.INSTANCE, z ? HTTP2_NAME_VALIDATOR : DefaultHeaders.NameValidator.NOT_NULL, i);
        this.firstNonPseudo = this.head;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public DefaultHttp2Headers(boolean z, boolean z2, int i) {
        super(AsciiString.CASE_SENSITIVE_HASHER, CharSequenceValueConverter.INSTANCE, z ? HTTP2_NAME_VALIDATOR : DefaultHeaders.NameValidator.NOT_NULL, i, z2 ? VALUE_VALIDATOR : DefaultHeaders.ValueValidator.NO_VALIDATION);
        this.firstNonPseudo = this.head;
    }

    /* access modifiers changed from: protected */
    public void validateName(DefaultHeaders.NameValidator<CharSequence> nameValidator, boolean z, CharSequence charSequence) {
        super.validateName(nameValidator, z, charSequence);
        if (nameValidator() == HTTP2_NAME_VALIDATOR && z && Http2Headers.PseudoHeaderName.hasPseudoHeaderFormat(charSequence) && contains(charSequence)) {
            PlatformDependent.throwException(Http2Exception.connectionError(Http2Error.PROTOCOL_ERROR, "Duplicate HTTP/2 pseudo-header '%s' encountered.", charSequence));
        }
    }

    /* access modifiers changed from: protected */
    public void validateValue(DefaultHeaders.ValueValidator<CharSequence> valueValidator, CharSequence charSequence, CharSequence charSequence2) {
        super.validateValue(valueValidator, charSequence, charSequence2);
        if (nameValidator() != HTTP2_NAME_VALIDATOR) {
            return;
        }
        if ((charSequence2 == null || charSequence2.length() == 0) && Http2Headers.PseudoHeaderName.hasPseudoHeaderFormat(charSequence)) {
            PlatformDependent.throwException(Http2Exception.connectionError(Http2Error.PROTOCOL_ERROR, "HTTP/2 pseudo-header '%s' must not be empty.", charSequence));
        }
    }

    public Http2Headers clear() {
        this.firstNonPseudo = this.head;
        return (Http2Headers) super.clear();
    }

    public boolean equals(Object obj) {
        return (obj instanceof Http2Headers) && equals((Http2Headers) obj, AsciiString.CASE_SENSITIVE_HASHER);
    }

    public int hashCode() {
        return hashCode(AsciiString.CASE_SENSITIVE_HASHER);
    }

    public Http2Headers method(CharSequence charSequence) {
        set(Http2Headers.PseudoHeaderName.METHOD.value(), charSequence);
        return this;
    }

    public Http2Headers scheme(CharSequence charSequence) {
        set(Http2Headers.PseudoHeaderName.SCHEME.value(), charSequence);
        return this;
    }

    public Http2Headers authority(CharSequence charSequence) {
        set(Http2Headers.PseudoHeaderName.AUTHORITY.value(), charSequence);
        return this;
    }

    public Http2Headers path(CharSequence charSequence) {
        set(Http2Headers.PseudoHeaderName.PATH.value(), charSequence);
        return this;
    }

    public Http2Headers status(CharSequence charSequence) {
        set(Http2Headers.PseudoHeaderName.STATUS.value(), charSequence);
        return this;
    }

    public CharSequence method() {
        return (CharSequence) get(Http2Headers.PseudoHeaderName.METHOD.value());
    }

    public CharSequence scheme() {
        return (CharSequence) get(Http2Headers.PseudoHeaderName.SCHEME.value());
    }

    public CharSequence authority() {
        return (CharSequence) get(Http2Headers.PseudoHeaderName.AUTHORITY.value());
    }

    public CharSequence path() {
        return (CharSequence) get(Http2Headers.PseudoHeaderName.PATH.value());
    }

    public CharSequence status() {
        return (CharSequence) get(Http2Headers.PseudoHeaderName.STATUS.value());
    }

    public boolean contains(CharSequence charSequence, CharSequence charSequence2) {
        return contains(charSequence, charSequence2, false);
    }

    public boolean contains(CharSequence charSequence, CharSequence charSequence2, boolean z) {
        return contains(charSequence, charSequence2, z ? AsciiString.CASE_INSENSITIVE_HASHER : AsciiString.CASE_SENSITIVE_HASHER);
    }

    /* access modifiers changed from: protected */
    public final DefaultHeaders.HeaderEntry<CharSequence, CharSequence> newHeaderEntry(int i, CharSequence charSequence, CharSequence charSequence2, DefaultHeaders.HeaderEntry<CharSequence, CharSequence> headerEntry) {
        return new Http2HeaderEntry(i, charSequence, charSequence2, headerEntry);
    }

    private final class Http2HeaderEntry extends DefaultHeaders.HeaderEntry<CharSequence, CharSequence> {
        Http2HeaderEntry(int i, CharSequence charSequence, CharSequence charSequence2, DefaultHeaders.HeaderEntry<CharSequence, CharSequence> headerEntry) {
            super(i, charSequence);
            this.value = charSequence2;
            this.next = headerEntry;
            if (Http2Headers.PseudoHeaderName.hasPseudoHeaderFormat(charSequence)) {
                this.after = DefaultHttp2Headers.this.firstNonPseudo;
                this.before = DefaultHttp2Headers.this.firstNonPseudo.before();
            } else {
                this.after = DefaultHttp2Headers.this.head;
                this.before = DefaultHttp2Headers.this.head.before();
                if (DefaultHttp2Headers.this.firstNonPseudo == DefaultHttp2Headers.this.head) {
                    DefaultHeaders.HeaderEntry unused = DefaultHttp2Headers.this.firstNonPseudo = this;
                }
            }
            pointNeighborsToThis();
        }

        /* access modifiers changed from: protected */
        public void remove() {
            if (this == DefaultHttp2Headers.this.firstNonPseudo) {
                DefaultHttp2Headers defaultHttp2Headers = DefaultHttp2Headers.this;
                DefaultHeaders.HeaderEntry unused = defaultHttp2Headers.firstNonPseudo = defaultHttp2Headers.firstNonPseudo.after();
            }
            super.remove();
        }
    }
}
