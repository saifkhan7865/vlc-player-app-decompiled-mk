package io.netty.handler.codec.http.multipart;

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http.multipart.HttpData;
import io.netty.handler.codec.http.multipart.InterfaceHttpData;
import io.netty.util.AbstractReferenceCounted;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

abstract class AbstractMixedHttpData<D extends HttpData> extends AbstractReferenceCounted implements HttpData {
    final String baseDir;
    final boolean deleteOnExit;
    private final long limitSize;
    D wrapped;

    /* access modifiers changed from: package-private */
    public abstract D makeDiskData();

    AbstractMixedHttpData(long j, String str, boolean z, D d) {
        this.limitSize = j;
        this.wrapped = d;
        this.baseDir = str;
        this.deleteOnExit = z;
    }

    public long getMaxSize() {
        return this.wrapped.getMaxSize();
    }

    public void setMaxSize(long j) {
        this.wrapped.setMaxSize(j);
    }

    public ByteBuf content() {
        return this.wrapped.content();
    }

    public void checkSize(long j) throws IOException {
        this.wrapped.checkSize(j);
    }

    public long definedLength() {
        return this.wrapped.definedLength();
    }

    public Charset getCharset() {
        return this.wrapped.getCharset();
    }

    public String getName() {
        return this.wrapped.getName();
    }

    public void addContent(ByteBuf byteBuf, boolean z) throws IOException {
        D d = this.wrapped;
        if (d instanceof AbstractMemoryHttpData) {
            try {
                checkSize(d.length() + ((long) byteBuf.readableBytes()));
                if (this.wrapped.length() + ((long) byteBuf.readableBytes()) > this.limitSize) {
                    D makeDiskData = makeDiskData();
                    ByteBuf byteBuf2 = ((AbstractMemoryHttpData) this.wrapped).getByteBuf();
                    if (byteBuf2 != null && byteBuf2.isReadable()) {
                        makeDiskData.addContent(byteBuf2.retain(), false);
                    }
                    this.wrapped.release();
                    this.wrapped = makeDiskData;
                }
            } catch (IOException e) {
                byteBuf.release();
                throw e;
            }
        }
        this.wrapped.addContent(byteBuf, z);
    }

    /* access modifiers changed from: protected */
    public void deallocate() {
        delete();
    }

    public void delete() {
        this.wrapped.delete();
    }

    public byte[] get() throws IOException {
        return this.wrapped.get();
    }

    public ByteBuf getByteBuf() throws IOException {
        return this.wrapped.getByteBuf();
    }

    public String getString() throws IOException {
        return this.wrapped.getString();
    }

    public String getString(Charset charset) throws IOException {
        return this.wrapped.getString(charset);
    }

    public boolean isInMemory() {
        return this.wrapped.isInMemory();
    }

    public long length() {
        return this.wrapped.length();
    }

    public boolean renameTo(File file) throws IOException {
        return this.wrapped.renameTo(file);
    }

    public void setCharset(Charset charset) {
        this.wrapped.setCharset(charset);
    }

    public void setContent(ByteBuf byteBuf) throws IOException {
        try {
            checkSize((long) byteBuf.readableBytes());
            if (((long) byteBuf.readableBytes()) > this.limitSize) {
                D d = this.wrapped;
                if (d instanceof AbstractMemoryHttpData) {
                    d.release();
                    this.wrapped = makeDiskData();
                }
            }
            this.wrapped.setContent(byteBuf);
        } catch (IOException e) {
            byteBuf.release();
            throw e;
        }
    }

    public void setContent(File file) throws IOException {
        checkSize(file.length());
        if (file.length() > this.limitSize) {
            D d = this.wrapped;
            if (d instanceof AbstractMemoryHttpData) {
                d.release();
                this.wrapped = makeDiskData();
            }
        }
        this.wrapped.setContent(file);
    }

    public void setContent(InputStream inputStream) throws IOException {
        D d = this.wrapped;
        if (d instanceof AbstractMemoryHttpData) {
            d.release();
            this.wrapped = makeDiskData();
        }
        this.wrapped.setContent(inputStream);
    }

    public boolean isCompleted() {
        return this.wrapped.isCompleted();
    }

    public InterfaceHttpData.HttpDataType getHttpDataType() {
        return this.wrapped.getHttpDataType();
    }

    public int hashCode() {
        return this.wrapped.hashCode();
    }

    public boolean equals(Object obj) {
        return this.wrapped.equals(obj);
    }

    public int compareTo(InterfaceHttpData interfaceHttpData) {
        return this.wrapped.compareTo(interfaceHttpData);
    }

    public String toString() {
        return "Mixed: " + this.wrapped;
    }

    public ByteBuf getChunk(int i) throws IOException {
        return this.wrapped.getChunk(i);
    }

    public File getFile() throws IOException {
        return this.wrapped.getFile();
    }

    public D copy() {
        return this.wrapped.copy();
    }

    public D duplicate() {
        return this.wrapped.duplicate();
    }

    public D retainedDuplicate() {
        return this.wrapped.retainedDuplicate();
    }

    public D replace(ByteBuf byteBuf) {
        return this.wrapped.replace(byteBuf);
    }

    public D touch() {
        this.wrapped.touch();
        return this;
    }

    public D touch(Object obj) {
        this.wrapped.touch(obj);
        return this;
    }

    public D retain() {
        return (HttpData) super.retain();
    }

    public D retain(int i) {
        return (HttpData) super.retain(i);
    }
}
