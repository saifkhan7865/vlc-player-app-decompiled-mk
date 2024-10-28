package io.netty.handler.codec.http.multipart;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.HttpConstants;
import io.netty.util.internal.EmptyArrays;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

public abstract class AbstractDiskHttpData extends AbstractHttpData {
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) AbstractDiskHttpData.class);
    private File file;
    private FileChannel fileChannel;
    private boolean isRenamed;

    /* access modifiers changed from: protected */
    public abstract boolean deleteOnExit();

    /* access modifiers changed from: protected */
    public abstract String getBaseDirectory();

    /* access modifiers changed from: protected */
    public abstract String getDiskFilename();

    /* access modifiers changed from: protected */
    public abstract String getPostfix();

    /* access modifiers changed from: protected */
    public abstract String getPrefix();

    public boolean isInMemory() {
        return false;
    }

    public HttpData touch() {
        return this;
    }

    public HttpData touch(Object obj) {
        return this;
    }

    protected AbstractDiskHttpData(String str, Charset charset, long j) {
        super(str, charset, j);
    }

    private File tempFile() throws IOException {
        String str;
        File file2;
        String diskFilename = getDiskFilename();
        if (diskFilename != null) {
            str = "_" + Integer.toString(diskFilename.hashCode());
        } else {
            str = getPostfix();
        }
        if (getBaseDirectory() == null) {
            file2 = PlatformDependent.createTempFile(getPrefix(), str, (File) null);
        } else {
            file2 = PlatformDependent.createTempFile(getPrefix(), str, new File(getBaseDirectory()));
        }
        if (deleteOnExit()) {
            DeleteFileOnExitHook.add(file2.getPath());
        }
        return file2;
    }

    public void setContent(ByteBuf byteBuf) throws IOException {
        RandomAccessFile randomAccessFile;
        ObjectUtil.checkNotNull(byteBuf, "buffer");
        try {
            this.size = (long) byteBuf.readableBytes();
            checkSize(this.size);
            if (this.definedSize > 0) {
                if (this.definedSize < this.size) {
                    throw new IOException("Out of size: " + this.size + " > " + this.definedSize);
                }
            }
            if (this.file == null) {
                this.file = tempFile();
            }
            if (byteBuf.readableBytes() == 0) {
                if (!this.file.createNewFile()) {
                    if (this.file.length() == 0) {
                        byteBuf.release();
                        return;
                    } else if (!this.file.delete() || !this.file.createNewFile()) {
                        throw new IOException("file exists already: " + this.file);
                    }
                }
                byteBuf.release();
                return;
            }
            randomAccessFile = new RandomAccessFile(this.file, "rw");
            randomAccessFile.setLength(0);
            FileChannel channel = randomAccessFile.getChannel();
            ByteBuffer nioBuffer = byteBuf.nioBuffer();
            int i = 0;
            while (((long) i) < this.size) {
                i += channel.write(nioBuffer);
            }
            byteBuf.readerIndex(byteBuf.readerIndex() + i);
            channel.force(false);
            randomAccessFile.close();
            setCompleted();
            byteBuf.release();
        } catch (Throwable th) {
            byteBuf.release();
            throw th;
        }
    }

    /* JADX INFO: finally extract failed */
    public void addContent(ByteBuf byteBuf, boolean z) throws IOException {
        if (byteBuf != null) {
            try {
                int readableBytes = byteBuf.readableBytes();
                long j = (long) readableBytes;
                checkSize(this.size + j);
                if (this.definedSize > 0) {
                    if (this.definedSize < this.size + j) {
                        throw new IOException("Out of size: " + (this.size + j) + " > " + this.definedSize);
                    }
                }
                if (this.file == null) {
                    this.file = tempFile();
                }
                if (this.fileChannel == null) {
                    this.fileChannel = new RandomAccessFile(this.file, "rw").getChannel();
                }
                long position = this.fileChannel.position();
                int readerIndex = byteBuf.readerIndex();
                int i = readableBytes;
                while (true) {
                    if (i <= 0) {
                        break;
                    }
                    int bytes = byteBuf.getBytes(readerIndex, this.fileChannel, position, i);
                    if (bytes < 0) {
                        break;
                    }
                    i -= bytes;
                    position += (long) bytes;
                    readerIndex += bytes;
                }
                this.fileChannel.position(position);
                byteBuf.readerIndex(readerIndex);
                this.size += (long) (readableBytes - i);
            } finally {
                byteBuf.release();
            }
        }
        if (z) {
            if (this.file == null) {
                this.file = tempFile();
            }
            if (this.fileChannel == null) {
                this.fileChannel = new RandomAccessFile(this.file, "rw").getChannel();
            }
            try {
                this.fileChannel.force(false);
                this.fileChannel.close();
                this.fileChannel = null;
                setCompleted();
            } catch (Throwable th) {
                this.fileChannel.close();
                throw th;
            }
        } else {
            ObjectUtil.checkNotNull(byteBuf, "buffer");
        }
    }

    public void setContent(File file2) throws IOException {
        long length = file2.length();
        checkSize(length);
        this.size = length;
        if (this.file != null) {
            delete();
        }
        this.file = file2;
        this.isRenamed = true;
        setCompleted();
    }

    /* JADX INFO: finally extract failed */
    public void setContent(InputStream inputStream) throws IOException {
        ObjectUtil.checkNotNull(inputStream, "inputStream");
        if (this.file != null) {
            delete();
        }
        this.file = tempFile();
        RandomAccessFile randomAccessFile = new RandomAccessFile(this.file, "rw");
        try {
            randomAccessFile.setLength(0);
            FileChannel channel = randomAccessFile.getChannel();
            byte[] bArr = new byte[16384];
            ByteBuffer wrap = ByteBuffer.wrap(bArr);
            int read = inputStream.read(bArr);
            int i = 0;
            while (read > 0) {
                wrap.position(read).flip();
                i += channel.write(wrap);
                checkSize((long) i);
                wrap.clear();
                read = inputStream.read(bArr);
            }
            channel.force(false);
            randomAccessFile.close();
            this.size = (long) i;
            if (this.definedSize <= 0 || this.definedSize >= this.size) {
                this.isRenamed = true;
                setCompleted();
                return;
            }
            if (!this.file.delete()) {
                logger.warn("Failed to delete: {}", (Object) this.file);
            }
            this.file = null;
            throw new IOException("Out of size: " + this.size + " > " + this.definedSize);
        } catch (Throwable th) {
            randomAccessFile.close();
            throw th;
        }
    }

    public void delete() {
        String str;
        FileChannel fileChannel2 = this.fileChannel;
        if (fileChannel2 != null) {
            try {
                fileChannel2.force(false);
                try {
                    this.fileChannel.close();
                } catch (IOException e) {
                    logger.warn("Failed to close a file.", (Throwable) e);
                }
            } catch (IOException e2) {
                logger.warn("Failed to force.", (Throwable) e2);
                this.fileChannel.close();
            } catch (Throwable th) {
                try {
                    this.fileChannel.close();
                } catch (IOException e3) {
                    logger.warn("Failed to close a file.", (Throwable) e3);
                }
                throw th;
            }
            this.fileChannel = null;
        }
        if (!this.isRenamed) {
            File file2 = this.file;
            if (file2 != null && file2.exists()) {
                str = this.file.getPath();
                if (!this.file.delete()) {
                    logger.warn("Failed to delete: {}", (Object) this.file);
                }
                if (deleteOnExit() && str != null) {
                    DeleteFileOnExitHook.remove(str);
                }
                this.file = null;
            }
            str = null;
            DeleteFileOnExitHook.remove(str);
            this.file = null;
        }
    }

    public byte[] get() throws IOException {
        File file2 = this.file;
        if (file2 == null) {
            return EmptyArrays.EMPTY_BYTES;
        }
        return readFrom(file2);
    }

    public ByteBuf getByteBuf() throws IOException {
        File file2 = this.file;
        if (file2 == null) {
            return Unpooled.EMPTY_BUFFER;
        }
        return Unpooled.wrappedBuffer(readFrom(file2));
    }

    public ByteBuf getChunk(int i) throws IOException {
        if (this.file == null || i == 0) {
            return Unpooled.EMPTY_BUFFER;
        }
        if (this.fileChannel == null) {
            this.fileChannel = new RandomAccessFile(this.file, "r").getChannel();
        }
        ByteBuffer allocate = ByteBuffer.allocate(i);
        int i2 = 0;
        while (true) {
            if (i2 >= i) {
                break;
            }
            try {
                int read = this.fileChannel.read(allocate);
                if (read == -1) {
                    this.fileChannel.close();
                    this.fileChannel = null;
                    break;
                }
                i2 += read;
            } catch (IOException e) {
                this.fileChannel.close();
                this.fileChannel = null;
                throw e;
            }
        }
        if (i2 == 0) {
            return Unpooled.EMPTY_BUFFER;
        }
        allocate.flip();
        ByteBuf wrappedBuffer = Unpooled.wrappedBuffer(allocate);
        wrappedBuffer.readerIndex(0);
        wrappedBuffer.writerIndex(i2);
        return wrappedBuffer;
    }

    public String getString() throws IOException {
        return getString(HttpConstants.DEFAULT_CHARSET);
    }

    public String getString(Charset charset) throws IOException {
        File file2 = this.file;
        if (file2 == null) {
            return "";
        }
        if (charset == null) {
            return new String(readFrom(file2), HttpConstants.DEFAULT_CHARSET.name());
        }
        return new String(readFrom(file2), charset.name());
    }

    /* JADX WARNING: Removed duplicated region for block: B:39:0x007d A[SYNTHETIC, Splitter:B:39:0x007d] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0085 A[SYNTHETIC, Splitter:B:44:0x0085] */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x009a A[SYNTHETIC, Splitter:B:55:0x009a] */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x00a7 A[SYNTHETIC, Splitter:B:60:0x00a7] */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x00b0  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x00d9  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean renameTo(java.io.File r20) throws java.io.IOException {
        /*
            r19 = this;
            r1 = r19
            r2 = r20
            java.lang.String r3 = "Multiple exceptions detected, the following will be suppressed {}"
            java.lang.String r0 = "dest"
            io.netty.util.internal.ObjectUtil.checkNotNull(r2, r0)
            java.io.File r0 = r1.file
            if (r0 == 0) goto L_0x00df
            boolean r0 = r0.renameTo(r2)
            r4 = 1
            if (r0 != 0) goto L_0x00da
            r5 = 0
            r6 = 0
            java.io.RandomAccessFile r8 = new java.io.RandomAccessFile     // Catch:{ IOException -> 0x0094, all -> 0x0077 }
            java.io.File r0 = r1.file     // Catch:{ IOException -> 0x0094, all -> 0x0077 }
            java.lang.String r9 = "r"
            r8.<init>(r0, r9)     // Catch:{ IOException -> 0x0094, all -> 0x0077 }
            java.io.RandomAccessFile r9 = new java.io.RandomAccessFile     // Catch:{ IOException -> 0x0074, all -> 0x0070 }
            java.lang.String r0 = "rw"
            r9.<init>(r2, r0)     // Catch:{ IOException -> 0x0074, all -> 0x0070 }
            java.nio.channels.FileChannel r0 = r8.getChannel()     // Catch:{ IOException -> 0x006e, all -> 0x006b }
            java.nio.channels.FileChannel r16 = r9.getChannel()     // Catch:{ IOException -> 0x006e, all -> 0x006b }
            r10 = 8196(0x2004, double:4.0494E-320)
        L_0x0033:
            long r12 = r1.size     // Catch:{ IOException -> 0x006e, all -> 0x006b }
            int r14 = (r6 > r12 ? 1 : (r6 == r12 ? 0 : -1))
            if (r14 >= 0) goto L_0x0053
            long r12 = r1.size     // Catch:{ IOException -> 0x006e, all -> 0x006b }
            long r12 = r12 - r6
            int r14 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r14 >= 0) goto L_0x0043
            long r10 = r1.size     // Catch:{ IOException -> 0x006e, all -> 0x006b }
            long r10 = r10 - r6
        L_0x0043:
            r17 = r10
            r10 = r0
            r11 = r6
            r13 = r17
            r15 = r16
            long r10 = r10.transferTo(r11, r13, r15)     // Catch:{ IOException -> 0x006e, all -> 0x006b }
            long r6 = r6 + r10
            r10 = r17
            goto L_0x0033
        L_0x0053:
            r8.close()     // Catch:{ IOException -> 0x0057 }
            goto L_0x0059
        L_0x0057:
            r0 = move-exception
            r5 = r0
        L_0x0059:
            r9.close()     // Catch:{ IOException -> 0x005e }
            goto L_0x00ae
        L_0x005e:
            r0 = move-exception
            r8 = r0
            if (r5 != 0) goto L_0x0065
            r5 = r8
            goto L_0x00ae
        L_0x0065:
            io.netty.util.internal.logging.InternalLogger r0 = logger
            r0.warn((java.lang.String) r3, (java.lang.Throwable) r8)
            goto L_0x00ae
        L_0x006b:
            r0 = move-exception
            r2 = r0
            goto L_0x007b
        L_0x006e:
            r0 = move-exception
            goto L_0x0097
        L_0x0070:
            r0 = move-exception
            r2 = r0
            r9 = r5
            goto L_0x007b
        L_0x0074:
            r0 = move-exception
            r9 = r5
            goto L_0x0097
        L_0x0077:
            r0 = move-exception
            r2 = r0
            r8 = r5
            r9 = r8
        L_0x007b:
            if (r8 == 0) goto L_0x0083
            r8.close()     // Catch:{ IOException -> 0x0081 }
            goto L_0x0083
        L_0x0081:
            r0 = move-exception
            r5 = r0
        L_0x0083:
            if (r9 == 0) goto L_0x0093
            r9.close()     // Catch:{ IOException -> 0x0089 }
            goto L_0x0093
        L_0x0089:
            r0 = move-exception
            r4 = r0
            if (r5 != 0) goto L_0x008e
            goto L_0x0093
        L_0x008e:
            io.netty.util.internal.logging.InternalLogger r0 = logger
            r0.warn((java.lang.String) r3, (java.lang.Throwable) r4)
        L_0x0093:
            throw r2
        L_0x0094:
            r0 = move-exception
            r8 = r5
            r9 = r8
        L_0x0097:
            r5 = r0
            if (r8 == 0) goto L_0x00a5
            r8.close()     // Catch:{ IOException -> 0x009e }
            goto L_0x00a5
        L_0x009e:
            r0 = move-exception
            r8 = r0
            io.netty.util.internal.logging.InternalLogger r0 = logger
            r0.warn((java.lang.String) r3, (java.lang.Throwable) r8)
        L_0x00a5:
            if (r9 == 0) goto L_0x00ae
            r9.close()     // Catch:{ IOException -> 0x00ab }
            goto L_0x00ae
        L_0x00ab:
            r0 = move-exception
            r8 = r0
            goto L_0x0065
        L_0x00ae:
            if (r5 != 0) goto L_0x00d9
            long r8 = r1.size
            java.lang.String r0 = "Failed to delete: {}"
            int r3 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r3 != 0) goto L_0x00cc
            java.io.File r3 = r1.file
            boolean r3 = r3.delete()
            if (r3 != 0) goto L_0x00c7
            io.netty.util.internal.logging.InternalLogger r3 = logger
            java.io.File r5 = r1.file
            r3.warn((java.lang.String) r0, (java.lang.Object) r5)
        L_0x00c7:
            r1.file = r2
            r1.isRenamed = r4
            return r4
        L_0x00cc:
            boolean r3 = r20.delete()
            if (r3 != 0) goto L_0x00d7
            io.netty.util.internal.logging.InternalLogger r3 = logger
            r3.warn((java.lang.String) r0, (java.lang.Object) r2)
        L_0x00d7:
            r0 = 0
            return r0
        L_0x00d9:
            throw r5
        L_0x00da:
            r1.file = r2
            r1.isRenamed = r4
            return r4
        L_0x00df:
            java.io.IOException r0 = new java.io.IOException
            java.lang.String r2 = "No file defined so cannot be renamed"
            r0.<init>(r2)
            goto L_0x00e8
        L_0x00e7:
            throw r0
        L_0x00e8:
            goto L_0x00e7
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.handler.codec.http.multipart.AbstractDiskHttpData.renameTo(java.io.File):boolean");
    }

    private static byte[] readFrom(File file2) throws IOException {
        long length = file2.length();
        if (length <= 2147483647L) {
            RandomAccessFile randomAccessFile = new RandomAccessFile(file2, "r");
            byte[] bArr = new byte[((int) length)];
            try {
                FileChannel channel = randomAccessFile.getChannel();
                ByteBuffer wrap = ByteBuffer.wrap(bArr);
                for (int i = 0; ((long) i) < length; i += channel.read(wrap)) {
                }
                return bArr;
            } finally {
                randomAccessFile.close();
            }
        } else {
            throw new IllegalArgumentException("File too big to be loaded in memory");
        }
    }

    public File getFile() throws IOException {
        return this.file;
    }
}
