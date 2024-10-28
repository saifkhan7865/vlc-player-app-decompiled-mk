package io.netty.handler.codec.http.multipart;

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http.multipart.InterfaceHttpData;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

public class MixedFileUpload extends AbstractMixedHttpData<FileUpload> implements FileUpload {
    public /* bridge */ /* synthetic */ void addContent(ByteBuf byteBuf, boolean z) throws IOException {
        super.addContent(byteBuf, z);
    }

    public /* bridge */ /* synthetic */ void checkSize(long j) throws IOException {
        super.checkSize(j);
    }

    public /* bridge */ /* synthetic */ int compareTo(InterfaceHttpData interfaceHttpData) {
        return super.compareTo(interfaceHttpData);
    }

    public /* bridge */ /* synthetic */ ByteBuf content() {
        return super.content();
    }

    public /* bridge */ /* synthetic */ long definedLength() {
        return super.definedLength();
    }

    public /* bridge */ /* synthetic */ void delete() {
        super.delete();
    }

    public /* bridge */ /* synthetic */ boolean equals(Object obj) {
        return super.equals(obj);
    }

    public /* bridge */ /* synthetic */ byte[] get() throws IOException {
        return super.get();
    }

    public /* bridge */ /* synthetic */ ByteBuf getByteBuf() throws IOException {
        return super.getByteBuf();
    }

    public /* bridge */ /* synthetic */ Charset getCharset() {
        return super.getCharset();
    }

    public /* bridge */ /* synthetic */ ByteBuf getChunk(int i) throws IOException {
        return super.getChunk(i);
    }

    public /* bridge */ /* synthetic */ File getFile() throws IOException {
        return super.getFile();
    }

    public /* bridge */ /* synthetic */ InterfaceHttpData.HttpDataType getHttpDataType() {
        return super.getHttpDataType();
    }

    public /* bridge */ /* synthetic */ long getMaxSize() {
        return super.getMaxSize();
    }

    public /* bridge */ /* synthetic */ String getName() {
        return super.getName();
    }

    public /* bridge */ /* synthetic */ String getString() throws IOException {
        return super.getString();
    }

    public /* bridge */ /* synthetic */ String getString(Charset charset) throws IOException {
        return super.getString(charset);
    }

    public /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    public /* bridge */ /* synthetic */ boolean isCompleted() {
        return super.isCompleted();
    }

    public /* bridge */ /* synthetic */ boolean isInMemory() {
        return super.isInMemory();
    }

    public /* bridge */ /* synthetic */ long length() {
        return super.length();
    }

    public /* bridge */ /* synthetic */ boolean renameTo(File file) throws IOException {
        return super.renameTo(file);
    }

    public /* bridge */ /* synthetic */ void setCharset(Charset charset) {
        super.setCharset(charset);
    }

    public /* bridge */ /* synthetic */ void setContent(ByteBuf byteBuf) throws IOException {
        super.setContent(byteBuf);
    }

    public /* bridge */ /* synthetic */ void setContent(File file) throws IOException {
        super.setContent(file);
    }

    public /* bridge */ /* synthetic */ void setContent(InputStream inputStream) throws IOException {
        super.setContent(inputStream);
    }

    public /* bridge */ /* synthetic */ void setMaxSize(long j) {
        super.setMaxSize(j);
    }

    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    public MixedFileUpload(String str, String str2, String str3, String str4, Charset charset, long j, long j2) {
        this(str, str2, str3, str4, charset, j, j2, DiskFileUpload.baseDirectory, DiskFileUpload.deleteOnExitTemporaryFile);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v0, resolved type: io.netty.handler.codec.http.multipart.MemoryFileUpload} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v1, resolved type: io.netty.handler.codec.http.multipart.MemoryFileUpload} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: io.netty.handler.codec.http.multipart.DiskFileUpload} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v3, resolved type: io.netty.handler.codec.http.multipart.MemoryFileUpload} */
    /* JADX WARNING: Illegal instructions before constructor call */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public MixedFileUpload(java.lang.String r12, java.lang.String r13, java.lang.String r14, java.lang.String r15, java.nio.charset.Charset r16, long r17, long r19, java.lang.String r21, boolean r22) {
        /*
            r11 = this;
            int r0 = (r17 > r19 ? 1 : (r17 == r19 ? 0 : -1))
            if (r0 <= 0) goto L_0x0018
            io.netty.handler.codec.http.multipart.DiskFileUpload r10 = new io.netty.handler.codec.http.multipart.DiskFileUpload
            r0 = r10
            r1 = r12
            r2 = r13
            r3 = r14
            r4 = r15
            r5 = r16
            r6 = r17
            r8 = r21
            r9 = r22
            r0.<init>(r1, r2, r3, r4, r5, r6, r8, r9)
            r8 = r10
            goto L_0x0026
        L_0x0018:
            io.netty.handler.codec.http.multipart.MemoryFileUpload r8 = new io.netty.handler.codec.http.multipart.MemoryFileUpload
            r0 = r8
            r1 = r12
            r2 = r13
            r3 = r14
            r4 = r15
            r5 = r16
            r6 = r17
            r0.<init>(r1, r2, r3, r4, r5, r6)
        L_0x0026:
            r12 = r11
            r13 = r19
            r15 = r21
            r16 = r22
            r17 = r8
            r12.<init>(r13, r15, r16, r17)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.handler.codec.http.multipart.MixedFileUpload.<init>(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.nio.charset.Charset, long, long, java.lang.String, boolean):void");
    }

    public String getContentTransferEncoding() {
        return ((FileUpload) this.wrapped).getContentTransferEncoding();
    }

    public String getFilename() {
        return ((FileUpload) this.wrapped).getFilename();
    }

    public void setContentTransferEncoding(String str) {
        ((FileUpload) this.wrapped).setContentTransferEncoding(str);
    }

    public void setFilename(String str) {
        ((FileUpload) this.wrapped).setFilename(str);
    }

    public void setContentType(String str) {
        ((FileUpload) this.wrapped).setContentType(str);
    }

    public String getContentType() {
        return ((FileUpload) this.wrapped).getContentType();
    }

    /* access modifiers changed from: package-private */
    public FileUpload makeDiskData() {
        DiskFileUpload diskFileUpload = new DiskFileUpload(getName(), getFilename(), getContentType(), getContentTransferEncoding(), getCharset(), definedLength(), this.baseDir, this.deleteOnExit);
        diskFileUpload.setMaxSize(getMaxSize());
        return diskFileUpload;
    }

    public FileUpload copy() {
        return (FileUpload) super.copy();
    }

    public FileUpload duplicate() {
        return (FileUpload) super.duplicate();
    }

    public FileUpload retainedDuplicate() {
        return (FileUpload) super.retainedDuplicate();
    }

    public FileUpload replace(ByteBuf byteBuf) {
        return (FileUpload) super.replace(byteBuf);
    }

    public FileUpload touch() {
        return (FileUpload) super.touch();
    }

    public FileUpload touch(Object obj) {
        return (FileUpload) super.touch(obj);
    }

    public FileUpload retain() {
        return (FileUpload) super.retain();
    }

    public FileUpload retain(int i) {
        return (FileUpload) super.retain(i);
    }
}
