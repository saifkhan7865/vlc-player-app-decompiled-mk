package io.netty.handler.codec.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.DefaultHeaders;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.StringUtil;
import java.util.Iterator;
import java.util.Map;

public class DefaultLastHttpContent extends DefaultHttpContent implements LastHttpContent {
    private final HttpHeaders trailingHeaders;
    private final boolean validateHeaders;

    public DefaultLastHttpContent() {
        this(Unpooled.buffer(0));
    }

    public DefaultLastHttpContent(ByteBuf byteBuf) {
        this(byteBuf, true);
    }

    public DefaultLastHttpContent(ByteBuf byteBuf, boolean z) {
        super(byteBuf);
        this.trailingHeaders = new TrailingHttpHeaders(z);
        this.validateHeaders = z;
    }

    public DefaultLastHttpContent(ByteBuf byteBuf, HttpHeaders httpHeaders) {
        super(byteBuf);
        this.trailingHeaders = (HttpHeaders) ObjectUtil.checkNotNull(httpHeaders, "trailingHeaders");
        this.validateHeaders = false;
    }

    public LastHttpContent copy() {
        return replace(content().copy());
    }

    public LastHttpContent duplicate() {
        return replace(content().duplicate());
    }

    public LastHttpContent retainedDuplicate() {
        return replace(content().retainedDuplicate());
    }

    public LastHttpContent replace(ByteBuf byteBuf) {
        DefaultLastHttpContent defaultLastHttpContent = new DefaultLastHttpContent(byteBuf, this.validateHeaders);
        defaultLastHttpContent.trailingHeaders().set(trailingHeaders());
        return defaultLastHttpContent;
    }

    public LastHttpContent retain(int i) {
        super.retain(i);
        return this;
    }

    public LastHttpContent retain() {
        super.retain();
        return this;
    }

    public LastHttpContent touch() {
        super.touch();
        return this;
    }

    public LastHttpContent touch(Object obj) {
        super.touch(obj);
        return this;
    }

    public HttpHeaders trailingHeaders() {
        return this.trailingHeaders;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append(StringUtil.NEWLINE);
        appendHeaders(sb);
        sb.setLength(sb.length() - StringUtil.NEWLINE.length());
        return sb.toString();
    }

    private void appendHeaders(StringBuilder sb) {
        Iterator<Map.Entry<String, String>> it = trailingHeaders().iterator();
        while (it.hasNext()) {
            Map.Entry next = it.next();
            sb.append((String) next.getKey());
            sb.append(": ");
            sb.append((String) next.getValue());
            sb.append(StringUtil.NEWLINE);
        }
    }

    private static final class TrailingHttpHeaders extends DefaultHttpHeaders {
        private static final DefaultHeaders.NameValidator<CharSequence> TrailerNameValidator = new DefaultHeaders.NameValidator<CharSequence>() {
            public void validateName(CharSequence charSequence) {
                DefaultHttpHeaders.HttpNameValidator.validateName(charSequence);
                if (HttpHeaderNames.CONTENT_LENGTH.contentEqualsIgnoreCase(charSequence) || HttpHeaderNames.TRANSFER_ENCODING.contentEqualsIgnoreCase(charSequence) || HttpHeaderNames.TRAILER.contentEqualsIgnoreCase(charSequence)) {
                    throw new IllegalArgumentException("prohibited trailing header: " + charSequence);
                }
            }
        };

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        TrailingHttpHeaders(boolean z) {
            super(z, z ? TrailerNameValidator : DefaultHeaders.NameValidator.NOT_NULL);
        }
    }
}
