package io.netty.handler.codec.http.multipart;

import io.ktor.http.ContentDisposition;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelException;
import io.netty.handler.codec.http.HttpConstants;
import io.netty.util.AbstractReferenceCounted;
import io.netty.util.internal.ObjectUtil;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.regex.Pattern;
import org.fusesource.jansi.AnsiRenderer;

public abstract class AbstractHttpData extends AbstractReferenceCounted implements HttpData {
    private static final Pattern REPLACE_PATTERN = Pattern.compile("[\\r\\t]");
    private static final Pattern STRIP_PATTERN = Pattern.compile("(?:^\\s+|\\s+$|\\n)");
    private Charset charset = HttpConstants.DEFAULT_CHARSET;
    private boolean completed;
    protected long definedSize;
    private long maxSize = -1;
    private final String name;
    protected long size;

    public abstract HttpData touch();

    public abstract HttpData touch(Object obj);

    protected AbstractHttpData(String str, Charset charset2, long j) {
        ObjectUtil.checkNotNull(str, ContentDisposition.Parameters.Name);
        this.name = ObjectUtil.checkNonEmpty(STRIP_PATTERN.matcher(REPLACE_PATTERN.matcher(str).replaceAll(AnsiRenderer.CODE_TEXT_SEPARATOR)).replaceAll(""), ContentDisposition.Parameters.Name);
        if (charset2 != null) {
            setCharset(charset2);
        }
        this.definedSize = j;
    }

    public long getMaxSize() {
        return this.maxSize;
    }

    public void setMaxSize(long j) {
        this.maxSize = j;
    }

    public void checkSize(long j) throws IOException {
        long j2 = this.maxSize;
        if (j2 >= 0 && j > j2) {
            throw new IOException("Size exceed allowed maximum capacity");
        }
    }

    public String getName() {
        return this.name;
    }

    public boolean isCompleted() {
        return this.completed;
    }

    /* access modifiers changed from: protected */
    public void setCompleted() {
        setCompleted(true);
    }

    /* access modifiers changed from: protected */
    public void setCompleted(boolean z) {
        this.completed = z;
    }

    public Charset getCharset() {
        return this.charset;
    }

    public void setCharset(Charset charset2) {
        this.charset = (Charset) ObjectUtil.checkNotNull(charset2, "charset");
    }

    public long length() {
        return this.size;
    }

    public long definedLength() {
        return this.definedSize;
    }

    public ByteBuf content() {
        try {
            return getByteBuf();
        } catch (IOException e) {
            throw new ChannelException((Throwable) e);
        }
    }

    /* access modifiers changed from: protected */
    public void deallocate() {
        delete();
    }

    public HttpData retain() {
        super.retain();
        return this;
    }

    public HttpData retain(int i) {
        super.retain(i);
        return this;
    }
}
