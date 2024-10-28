package okio;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.leanback.preference.LeanbackPreferenceDialogFragment;
import androidx.tvprovider.media.tv.TvContractCompat;
import com.google.common.base.Ascii;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.util.Arrays;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.TypeCastException;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.Charsets;
import kotlin.text.Typography;
import org.bouncycastle.asn1.BERTags;
import org.bouncycastle.pqc.jcajce.spec.McElieceCCA2KeyGenParameterSpec;
import org.videolan.vlc.gui.dialogs.PickTimeFragment;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000¶\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u001a\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0005\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\n\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0018\u0018\u0000 \u00012\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004:\u0004\u0001\u0001B\u0005¢\u0006\u0002\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0000H\u0016J\u0006\u0010\u0011\u001a\u00020\u0012J\b\u0010\u0013\u001a\u00020\u0000H\u0016J\b\u0010\u0014\u001a\u00020\u0012H\u0016J\u0006\u0010\u0015\u001a\u00020\fJ$\u0010\u0016\u001a\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u0019\u001a\u00020\f2\b\b\u0002\u0010\u001a\u001a\u00020\fH\u0007J\"\u0010\u0016\u001a\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0019\u001a\u00020\f2\b\b\u0002\u0010\u001a\u001a\u00020\fJ\u0010\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J\b\u0010\u001f\u001a\u00020\u0000H\u0016J\b\u0010 \u001a\u00020\u0000H\u0016J\u0013\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010$H\u0002J\b\u0010%\u001a\u00020\"H\u0016J\b\u0010&\u001a\u00020\u0012H\u0016J\u0016\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020\fH\u0002¢\u0006\u0002\b*J\u0015\u0010*\u001a\u00020(2\u0006\u0010+\u001a\u00020\fH\u0007¢\u0006\u0002\b,J\b\u0010-\u001a\u00020.H\u0016J\u0018\u0010/\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u00100\u001a\u00020\u001cH\u0002J\u000e\u00101\u001a\u00020\u001c2\u0006\u00100\u001a\u00020\u001cJ\u000e\u00102\u001a\u00020\u001c2\u0006\u00100\u001a\u00020\u001cJ\u000e\u00103\u001a\u00020\u001c2\u0006\u00100\u001a\u00020\u001cJ\u0010\u00104\u001a\u00020\f2\u0006\u00105\u001a\u00020(H\u0016J\u0018\u00104\u001a\u00020\f2\u0006\u00105\u001a\u00020(2\u0006\u00106\u001a\u00020\fH\u0016J \u00104\u001a\u00020\f2\u0006\u00105\u001a\u00020(2\u0006\u00106\u001a\u00020\f2\u0006\u00107\u001a\u00020\fH\u0016J\u0010\u00104\u001a\u00020\f2\u0006\u00108\u001a\u00020\u001cH\u0016J\u0018\u00104\u001a\u00020\f2\u0006\u00108\u001a\u00020\u001c2\u0006\u00106\u001a\u00020\fH\u0016J\u0010\u00109\u001a\u00020\f2\u0006\u0010:\u001a\u00020\u001cH\u0016J\u0018\u00109\u001a\u00020\f2\u0006\u0010:\u001a\u00020\u001c2\u0006\u00106\u001a\u00020\fH\u0016J\b\u0010;\u001a\u00020<H\u0016J\b\u0010=\u001a\u00020\"H\u0016J\u0006\u0010>\u001a\u00020\u001cJ\b\u0010?\u001a\u00020\u0018H\u0016J\b\u0010@\u001a\u00020\u0001H\u0016J\u0018\u0010A\u001a\u00020\"2\u0006\u0010\u0019\u001a\u00020\f2\u0006\u00108\u001a\u00020\u001cH\u0016J(\u0010A\u001a\u00020\"2\u0006\u0010\u0019\u001a\u00020\f2\u0006\u00108\u001a\u00020\u001c2\u0006\u0010B\u001a\u00020.2\u0006\u0010\u001a\u001a\u00020.H\u0016J0\u0010A\u001a\u00020\"2\u0006\u0010C\u001a\u00020\n2\u0006\u0010D\u001a\u00020.2\u0006\u00108\u001a\u00020E2\u0006\u0010B\u001a\u00020.2\u0006\u0010F\u001a\u00020.H\u0002J\u0010\u0010G\u001a\u00020.2\u0006\u0010H\u001a\u00020IH\u0016J\u0010\u0010G\u001a\u00020.2\u0006\u0010H\u001a\u00020EH\u0016J \u0010G\u001a\u00020.2\u0006\u0010H\u001a\u00020E2\u0006\u0010\u0019\u001a\u00020.2\u0006\u0010\u001a\u001a\u00020.H\u0016J\u0018\u0010G\u001a\u00020\f2\u0006\u0010H\u001a\u00020\u00002\u0006\u0010\u001a\u001a\u00020\fH\u0016J\u0010\u0010J\u001a\u00020\f2\u0006\u0010H\u001a\u00020KH\u0016J\u0012\u0010L\u001a\u00020M2\b\b\u0002\u0010N\u001a\u00020MH\u0007J\b\u0010O\u001a\u00020(H\u0016J\b\u0010P\u001a\u00020EH\u0016J\u0010\u0010P\u001a\u00020E2\u0006\u0010\u001a\u001a\u00020\fH\u0016J\b\u0010Q\u001a\u00020\u001cH\u0016J\u0010\u0010Q\u001a\u00020\u001c2\u0006\u0010\u001a\u001a\u00020\fH\u0016J\b\u0010R\u001a\u00020\fH\u0016J\u000e\u0010S\u001a\u00020\u00002\u0006\u0010T\u001a\u00020<J\u0016\u0010S\u001a\u00020\u00002\u0006\u0010T\u001a\u00020<2\u0006\u0010\u001a\u001a\u00020\fJ \u0010S\u001a\u00020\u00122\u0006\u0010T\u001a\u00020<2\u0006\u0010\u001a\u001a\u00020\f2\u0006\u0010U\u001a\u00020\"H\u0002J\u0010\u0010V\u001a\u00020\u00122\u0006\u0010H\u001a\u00020EH\u0016J\u0018\u0010V\u001a\u00020\u00122\u0006\u0010H\u001a\u00020\u00002\u0006\u0010\u001a\u001a\u00020\fH\u0016J\b\u0010W\u001a\u00020\fH\u0016J\b\u0010X\u001a\u00020.H\u0016J\b\u0010Y\u001a\u00020.H\u0016J\b\u0010Z\u001a\u00020\fH\u0016J\b\u0010[\u001a\u00020\fH\u0016J\b\u0010\\\u001a\u00020]H\u0016J\b\u0010^\u001a\u00020]H\u0016J\u0010\u0010_\u001a\u00020\u001e2\u0006\u0010`\u001a\u00020aH\u0016J\u0018\u0010_\u001a\u00020\u001e2\u0006\u0010\u001a\u001a\u00020\f2\u0006\u0010`\u001a\u00020aH\u0016J\u0012\u0010b\u001a\u00020M2\b\b\u0002\u0010N\u001a\u00020MH\u0007J\b\u0010c\u001a\u00020\u001eH\u0016J\u0010\u0010c\u001a\u00020\u001e2\u0006\u0010\u001a\u001a\u00020\fH\u0016J\b\u0010d\u001a\u00020.H\u0016J\n\u0010e\u001a\u0004\u0018\u00010\u001eH\u0016J\u0015\u0010e\u001a\u00020\u001e2\u0006\u0010f\u001a\u00020\fH\u0000¢\u0006\u0002\bgJ\b\u0010h\u001a\u00020\u001eH\u0016J\u0010\u0010h\u001a\u00020\u001e2\u0006\u0010i\u001a\u00020\fH\u0016J\u0010\u0010j\u001a\u00020\"2\u0006\u0010\u001a\u001a\u00020\fH\u0016J\u0010\u0010k\u001a\u00020\u00122\u0006\u0010\u001a\u001a\u00020\fH\u0016J8\u0010l\u001a\u0002Hm\"\u0004\b\u0000\u0010m2\u0006\u00106\u001a\u00020\f2\u001a\u0010n\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010\n\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u0002Hm0oH\b¢\u0006\u0002\u0010pJ\u0010\u0010q\u001a\u00020.2\u0006\u0010r\u001a\u00020sH\u0016J\u001f\u0010t\u001a\u00020.2\u0006\u0010r\u001a\u00020s2\b\b\u0002\u0010u\u001a\u00020\"H\u0000¢\u0006\u0002\bvJ\u0006\u0010w\u001a\u00020\u001cJ\u0006\u0010x\u001a\u00020\u001cJ\u0006\u0010y\u001a\u00020\u001cJ\r\u0010\r\u001a\u00020\fH\u0007¢\u0006\u0002\bzJ\u0010\u0010{\u001a\u00020\u00122\u0006\u0010\u001a\u001a\u00020\fH\u0016J\u0006\u0010|\u001a\u00020\u001cJ\u000e\u0010|\u001a\u00020\u001c2\u0006\u0010\u001a\u001a\u00020.J\b\u0010}\u001a\u00020~H\u0016J\b\u0010\u001a\u00020\u001eH\u0016J\u0018\u0010\u0001\u001a\u00020\n2\u0007\u0010\u0001\u001a\u00020.H\u0000¢\u0006\u0003\b\u0001J\u0012\u0010\u0001\u001a\u00020.2\u0007\u0010\u0001\u001a\u00020IH\u0016J\u0012\u0010\u0001\u001a\u00020\u00002\u0007\u0010\u0001\u001a\u00020EH\u0016J\"\u0010\u0001\u001a\u00020\u00002\u0007\u0010\u0001\u001a\u00020E2\u0006\u0010\u0019\u001a\u00020.2\u0006\u0010\u001a\u001a\u00020.H\u0016J\u001a\u0010\u0001\u001a\u00020\u00122\u0007\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u001a\u001a\u00020\fH\u0016J\u0012\u0010\u0001\u001a\u00020\u00002\u0007\u0010\u0001\u001a\u00020\u001cH\u0016J\u001b\u0010\u0001\u001a\u00020\u00022\b\u0010\u0001\u001a\u00030\u00012\u0006\u0010\u001a\u001a\u00020\fH\u0016J\u0013\u0010\u0001\u001a\u00020\f2\b\u0010\u0001\u001a\u00030\u0001H\u0016J\u0011\u0010\u0001\u001a\u00020\u00002\u0006\u00105\u001a\u00020.H\u0016J\u0012\u0010\u0001\u001a\u00020\u00002\u0007\u0010\u0001\u001a\u00020\fH\u0016J\u0012\u0010\u0001\u001a\u00020\u00002\u0007\u0010\u0001\u001a\u00020\fH\u0016J\u0012\u0010\u0001\u001a\u00020\u00002\u0007\u0010\u0001\u001a\u00020.H\u0016J\u0012\u0010\u0001\u001a\u00020\u00002\u0007\u0010\u0001\u001a\u00020.H\u0016J\u0012\u0010\u0001\u001a\u00020\u00002\u0007\u0010\u0001\u001a\u00020\fH\u0016J\u0012\u0010\u0001\u001a\u00020\u00002\u0007\u0010\u0001\u001a\u00020\fH\u0016J\u0012\u0010\u0001\u001a\u00020\u00002\u0007\u0010\u0001\u001a\u00020.H\u0016J\u0012\u0010\u0001\u001a\u00020\u00002\u0007\u0010\u0001\u001a\u00020.H\u0016J\u001a\u0010\u0001\u001a\u00020\u00002\u0007\u0010\u0001\u001a\u00020\u001e2\u0006\u0010`\u001a\u00020aH\u0016J,\u0010\u0001\u001a\u00020\u00002\u0007\u0010\u0001\u001a\u00020\u001e2\u0007\u0010\u0001\u001a\u00020.2\u0007\u0010\u0001\u001a\u00020.2\u0006\u0010`\u001a\u00020aH\u0016J\u001b\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u001a\u001a\u00020\fH\u0007J\u0012\u0010\u0001\u001a\u00020\u00002\u0007\u0010\u0001\u001a\u00020\u001eH\u0016J$\u0010\u0001\u001a\u00020\u00002\u0007\u0010\u0001\u001a\u00020\u001e2\u0007\u0010\u0001\u001a\u00020.2\u0007\u0010\u0001\u001a\u00020.H\u0016J\u0012\u0010\u0001\u001a\u00020\u00002\u0007\u0010\u0001\u001a\u00020.H\u0016R\u0014\u0010\u0006\u001a\u00020\u00008VX\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u0004\u0018\u00010\n8\u0000@\u0000X\u000e¢\u0006\u0002\n\u0000R&\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\f8\u0007@@X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010¨\u0006\u0001"}, d2 = {"Lokio/Buffer;", "Lokio/BufferedSource;", "Lokio/BufferedSink;", "", "Ljava/nio/channels/ByteChannel;", "()V", "buffer", "getBuffer", "()Lokio/Buffer;", "head", "Lokio/Segment;", "<set-?>", "", "size", "()J", "setSize$jvm", "(J)V", "clear", "", "clone", "close", "completeSegmentByteCount", "copyTo", "out", "Ljava/io/OutputStream;", "offset", "byteCount", "digest", "Lokio/ByteString;", "algorithm", "", "emit", "emitCompleteSegments", "equals", "", "other", "", "exhausted", "flush", "get", "", "pos", "getByte", "index", "-deprecated_getByte", "hashCode", "", "hmac", "key", "hmacSha1", "hmacSha256", "hmacSha512", "indexOf", "b", "fromIndex", "toIndex", "bytes", "indexOfElement", "targetBytes", "inputStream", "Ljava/io/InputStream;", "isOpen", "md5", "outputStream", "peek", "rangeEquals", "bytesOffset", "segment", "segmentPos", "", "bytesLimit", "read", "sink", "Ljava/nio/ByteBuffer;", "readAll", "Lokio/Sink;", "readAndWriteUnsafe", "Lokio/Buffer$UnsafeCursor;", "unsafeCursor", "readByte", "readByteArray", "readByteString", "readDecimalLong", "readFrom", "input", "forever", "readFully", "readHexadecimalUnsignedLong", "readInt", "readIntLe", "readLong", "readLongLe", "readShort", "", "readShortLe", "readString", "charset", "Ljava/nio/charset/Charset;", "readUnsafe", "readUtf8", "readUtf8CodePoint", "readUtf8Line", "newline", "readUtf8Line$jvm", "readUtf8LineStrict", "limit", "request", "require", "seek", "T", "lambda", "Lkotlin/Function2;", "(JLkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "select", "options", "Lokio/Options;", "selectPrefix", "selectTruncated", "selectPrefix$jvm", "sha1", "sha256", "sha512", "-deprecated_size", "skip", "snapshot", "timeout", "Lokio/Timeout;", "toString", "writableSegment", "minimumCapacity", "writableSegment$jvm", "write", "source", "byteString", "Lokio/Source;", "writeAll", "writeByte", "writeDecimalLong", "v", "writeHexadecimalUnsignedLong", "writeInt", "i", "writeIntLe", "writeLong", "writeLongLe", "writeShort", "s", "writeShortLe", "writeString", "string", "beginIndex", "endIndex", "writeTo", "writeUtf8", "writeUtf8CodePoint", "codePoint", "Companion", "UnsafeCursor", "jvm"}, k = 1, mv = {1, 1, 11})
/* compiled from: Buffer.kt */
public final class Buffer implements BufferedSource, BufferedSink, Cloneable, ByteChannel {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final byte[] DIGITS;
    public Segment head;
    private long size;

    public Buffer buffer() {
        return this;
    }

    public void close() {
    }

    public final Buffer copyTo(OutputStream outputStream) throws IOException {
        return copyTo$default(this, outputStream, 0, 0, 6, (Object) null);
    }

    public final Buffer copyTo(OutputStream outputStream, long j) throws IOException {
        return copyTo$default(this, outputStream, j, 0, 4, (Object) null);
    }

    public Buffer emit() {
        return this;
    }

    public Buffer emitCompleteSegments() {
        return this;
    }

    public void flush() {
    }

    public Buffer getBuffer() {
        return this;
    }

    public boolean isOpen() {
        return true;
    }

    public final UnsafeCursor readAndWriteUnsafe() {
        return readAndWriteUnsafe$default(this, (UnsafeCursor) null, 1, (Object) null);
    }

    public final UnsafeCursor readUnsafe() {
        return readUnsafe$default(this, (UnsafeCursor) null, 1, (Object) null);
    }

    public final Buffer writeTo(OutputStream outputStream) throws IOException {
        return writeTo$default(this, outputStream, 0, 2, (Object) null);
    }

    public final void setSize$jvm(long j) {
        this.size = j;
    }

    public final long size() {
        return this.size;
    }

    public OutputStream outputStream() {
        return new Buffer$outputStream$1(this);
    }

    public boolean exhausted() {
        return this.size == 0;
    }

    public void require(long j) throws EOFException {
        if (this.size < j) {
            throw new EOFException();
        }
    }

    public boolean request(long j) {
        return this.size >= j;
    }

    public BufferedSource peek() {
        return Okio.buffer((Source) new PeekSource(this));
    }

    public InputStream inputStream() {
        return new Buffer$inputStream$1(this);
    }

    public static /* bridge */ /* synthetic */ Buffer copyTo$default(Buffer buffer, OutputStream outputStream, long j, long j2, int i, Object obj) throws IOException {
        if ((i & 2) != 0) {
            j = 0;
        }
        long j3 = j;
        if ((i & 4) != 0) {
            j2 = buffer.size - j3;
        }
        return buffer.copyTo(outputStream, j3, j2);
    }

    public final Buffer copyTo(OutputStream outputStream, long j, long j2) throws IOException {
        Intrinsics.checkParameterIsNotNull(outputStream, "out");
        Util.checkOffsetAndCount(this.size, j, j2);
        if (j2 == 0) {
            return this;
        }
        Segment segment = this.head;
        while (true) {
            if (segment == null) {
                Intrinsics.throwNpe();
            }
            if (j < ((long) (segment.limit - segment.pos))) {
                break;
            }
            j -= (long) (segment.limit - segment.pos);
            segment = segment.next;
        }
        while (j2 > 0) {
            if (segment == null) {
                Intrinsics.throwNpe();
            }
            int i = (int) (((long) segment.pos) + j);
            int min = (int) Math.min((long) (segment.limit - i), j2);
            outputStream.write(segment.data, i, min);
            j2 -= (long) min;
            segment = segment.next;
            j = 0;
        }
        return this;
    }

    public static /* bridge */ /* synthetic */ Buffer copyTo$default(Buffer buffer, Buffer buffer2, long j, long j2, int i, Object obj) {
        if ((i & 2) != 0) {
            j = 0;
        }
        long j3 = j;
        if ((i & 4) != 0) {
            j2 = buffer.size - j3;
        }
        return buffer.copyTo(buffer2, j3, j2);
    }

    public final Buffer copyTo(Buffer buffer, long j, long j2) {
        Intrinsics.checkParameterIsNotNull(buffer, "out");
        Util.checkOffsetAndCount(this.size, j, j2);
        if (j2 == 0) {
            return this;
        }
        buffer.size += j2;
        Segment segment = this.head;
        while (true) {
            if (segment == null) {
                Intrinsics.throwNpe();
            }
            if (j < ((long) (segment.limit - segment.pos))) {
                break;
            }
            j -= (long) (segment.limit - segment.pos);
            segment = segment.next;
        }
        while (j2 > 0) {
            if (segment == null) {
                Intrinsics.throwNpe();
            }
            Segment sharedCopy = segment.sharedCopy();
            sharedCopy.pos += (int) j;
            sharedCopy.limit = Math.min(sharedCopy.pos + ((int) j2), sharedCopy.limit);
            Segment segment2 = buffer.head;
            if (segment2 == null) {
                sharedCopy.prev = sharedCopy;
                sharedCopy.next = sharedCopy.prev;
                buffer.head = sharedCopy.next;
            } else {
                if (segment2 == null) {
                    Intrinsics.throwNpe();
                }
                Segment segment3 = segment2.prev;
                if (segment3 == null) {
                    Intrinsics.throwNpe();
                }
                segment3.push(sharedCopy);
            }
            j2 -= (long) (sharedCopy.limit - sharedCopy.pos);
            segment = segment.next;
            j = 0;
        }
        return this;
    }

    public static /* bridge */ /* synthetic */ Buffer writeTo$default(Buffer buffer, OutputStream outputStream, long j, int i, Object obj) throws IOException {
        if ((i & 2) != 0) {
            j = buffer.size;
        }
        return buffer.writeTo(outputStream, j);
    }

    public final Buffer writeTo(OutputStream outputStream, long j) throws IOException {
        Intrinsics.checkParameterIsNotNull(outputStream, "out");
        Util.checkOffsetAndCount(this.size, 0, j);
        Segment segment = this.head;
        while (j > 0) {
            if (segment == null) {
                Intrinsics.throwNpe();
            }
            int min = (int) Math.min(j, (long) (segment.limit - segment.pos));
            outputStream.write(segment.data, segment.pos, min);
            segment.pos += min;
            long j2 = (long) min;
            this.size -= j2;
            j -= j2;
            if (segment.pos == segment.limit) {
                Segment pop = segment.pop();
                this.head = pop;
                SegmentPool.recycle(segment);
                segment = pop;
            }
        }
        return this;
    }

    public final Buffer readFrom(InputStream inputStream) throws IOException {
        Intrinsics.checkParameterIsNotNull(inputStream, TvContractCompat.PARAM_INPUT);
        readFrom(inputStream, Long.MAX_VALUE, true);
        return this;
    }

    public final Buffer readFrom(InputStream inputStream, long j) throws IOException {
        Intrinsics.checkParameterIsNotNull(inputStream, TvContractCompat.PARAM_INPUT);
        if (j >= 0) {
            readFrom(inputStream, j, false);
            return this;
        }
        throw new IllegalArgumentException(("byteCount < 0: " + j).toString());
    }

    private final void readFrom(InputStream inputStream, long j, boolean z) throws IOException {
        while (true) {
            if (j > 0 || z) {
                Segment writableSegment$jvm = writableSegment$jvm(1);
                int read = inputStream.read(writableSegment$jvm.data, writableSegment$jvm.limit, (int) Math.min(j, (long) (8192 - writableSegment$jvm.limit)));
                if (read != -1) {
                    writableSegment$jvm.limit += read;
                    long j2 = (long) read;
                    this.size += j2;
                    j -= j2;
                } else if (!z) {
                    throw new EOFException();
                } else {
                    return;
                }
            } else {
                return;
            }
        }
    }

    public final long completeSegmentByteCount() {
        long j = this.size;
        if (j == 0) {
            return 0;
        }
        Segment segment = this.head;
        if (segment == null) {
            Intrinsics.throwNpe();
        }
        Segment segment2 = segment.prev;
        if (segment2 == null) {
            Intrinsics.throwNpe();
        }
        return (segment2.limit >= 8192 || !segment2.owner) ? j : j - ((long) (segment2.limit - segment2.pos));
    }

    public byte readByte() throws EOFException {
        if (this.size != 0) {
            Segment segment = this.head;
            if (segment == null) {
                Intrinsics.throwNpe();
            }
            int i = segment.pos;
            int i2 = segment.limit;
            int i3 = i + 1;
            byte b = segment.data[i];
            this.size--;
            if (i3 == i2) {
                this.head = segment.pop();
                SegmentPool.recycle(segment);
            } else {
                segment.pos = i3;
            }
            return b;
        }
        throw new EOFException();
    }

    public final byte getByte(long j) {
        Util.checkOffsetAndCount(this.size, j, 1);
        Segment segment = this.head;
        if (segment == null) {
            Segment segment2 = null;
            Intrinsics.throwNpe();
            byte[] bArr = null.data;
            throw null;
        } else if (size() - j < j) {
            long size2 = size();
            while (size2 > j) {
                segment = segment.prev;
                if (segment == null) {
                    Intrinsics.throwNpe();
                }
                size2 -= (long) (segment.limit - segment.pos);
            }
            if (segment == null) {
                Intrinsics.throwNpe();
            }
            return segment.data[(int) ((((long) segment.pos) + j) - size2)];
        } else {
            long j2 = 0;
            while (true) {
                long j3 = ((long) (segment.limit - segment.pos)) + j2;
                if (j3 > j) {
                    break;
                }
                segment = segment.next;
                if (segment == null) {
                    Intrinsics.throwNpe();
                }
                j2 = j3;
            }
            if (segment == null) {
                Intrinsics.throwNpe();
            }
            return segment.data[(int) ((((long) segment.pos) + j) - j2)];
        }
    }

    public short readShort() throws EOFException {
        if (this.size >= 2) {
            Segment segment = this.head;
            if (segment == null) {
                Intrinsics.throwNpe();
            }
            int i = segment.pos;
            int i2 = segment.limit;
            if (i2 - i < 2) {
                return (short) (((readByte() & 255) << 8) | (readByte() & 255));
            }
            byte[] bArr = segment.data;
            int i3 = i + 1;
            int i4 = i + 2;
            byte b = (bArr[i3] & 255) | ((bArr[i] & 255) << 8);
            this.size -= 2;
            if (i4 == i2) {
                this.head = segment.pop();
                SegmentPool.recycle(segment);
            } else {
                segment.pos = i4;
            }
            return (short) b;
        }
        throw new EOFException();
    }

    public int readInt() throws EOFException {
        if (this.size >= 4) {
            Segment segment = this.head;
            if (segment == null) {
                Intrinsics.throwNpe();
            }
            int i = segment.pos;
            int i2 = segment.limit;
            if (((long) (i2 - i)) < 4) {
                return ((readByte() & 255) << Ascii.CAN) | ((readByte() & 255) << Ascii.DLE) | ((readByte() & 255) << 8) | (readByte() & 255);
            }
            byte[] bArr = segment.data;
            byte b = ((bArr[i + 1] & 255) << Ascii.DLE) | ((bArr[i] & 255) << Ascii.CAN);
            int i3 = i + 3;
            int i4 = i + 4;
            byte b2 = (bArr[i3] & 255) | b | ((bArr[i + 2] & 255) << 8);
            this.size -= 4;
            if (i4 == i2) {
                this.head = segment.pop();
                SegmentPool.recycle(segment);
            } else {
                segment.pos = i4;
            }
            return b2;
        }
        throw new EOFException();
    }

    public long readLong() throws EOFException {
        if (this.size >= 8) {
            Segment segment = this.head;
            if (segment == null) {
                Intrinsics.throwNpe();
            }
            int i = segment.pos;
            int i2 = segment.limit;
            if (((long) (i2 - i)) < 8) {
                return ((((long) readInt()) & 4294967295L) << 32) | (4294967295L & ((long) readInt()));
            }
            byte[] bArr = segment.data;
            long j = ((((long) bArr[i + 3]) & 255) << 32) | ((((long) bArr[i]) & 255) << 56) | ((((long) bArr[i + 1]) & 255) << 48) | ((((long) bArr[i + 2]) & 255) << 40);
            int i3 = i + 7;
            int i4 = i + 8;
            long j2 = j | ((((long) bArr[i + 4]) & 255) << 24) | ((((long) bArr[i + 5]) & 255) << 16) | ((((long) bArr[i + 6]) & 255) << 8) | (((long) bArr[i3]) & 255);
            this.size -= 8;
            if (i4 == i2) {
                this.head = segment.pop();
                SegmentPool.recycle(segment);
            } else {
                segment.pos = i4;
            }
            return j2;
        }
        throw new EOFException();
    }

    public short readShortLe() throws EOFException {
        return Util.reverseBytes(readShort());
    }

    public int readIntLe() throws EOFException {
        return Util.reverseBytes(readInt());
    }

    public long readLongLe() throws EOFException {
        return Util.reverseBytes(readLong());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0054, code lost:
        if (r2 != false) goto L_0x0059;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0056, code lost:
        r1.readByte();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0072, code lost:
        throw new java.lang.NumberFormatException("Number too large: " + r1.readUtf8());
     */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00a4  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00ae  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00a2 A[EDGE_INSN: B:51:0x00a2->B:33:0x00a2 ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:5:0x0015  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0020  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public long readDecimalLong() throws java.io.EOFException {
        /*
            r17 = this;
            r0 = r17
            long r1 = r0.size
            r3 = 0
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 == 0) goto L_0x00c1
            r1 = 0
            r5 = -7
            r6 = r5
            r2 = 0
            r4 = r3
            r3 = 0
        L_0x0011:
            okio.Segment r8 = r0.head
            if (r8 != 0) goto L_0x0018
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x0018:
            byte[] r9 = r8.data
            int r10 = r8.pos
            int r11 = r8.limit
        L_0x001e:
            if (r10 >= r11) goto L_0x00a2
            byte r12 = r9[r10]
            r13 = 48
            byte r13 = (byte) r13
            if (r12 < r13) goto L_0x0073
            r14 = 57
            byte r14 = (byte) r14
            if (r12 > r14) goto L_0x0073
            int r13 = r13 - r12
            r14 = -922337203685477580(0xf333333333333334, double:-8.390303882365713E246)
            int r16 = (r4 > r14 ? 1 : (r4 == r14 ? 0 : -1))
            if (r16 < 0) goto L_0x0047
            int r16 = (r4 > r14 ? 1 : (r4 == r14 ? 0 : -1))
            if (r16 != 0) goto L_0x0040
            long r14 = (long) r13
            int r16 = (r14 > r6 ? 1 : (r14 == r6 ? 0 : -1))
            if (r16 >= 0) goto L_0x0040
            goto L_0x0047
        L_0x0040:
            r14 = 10
            long r4 = r4 * r14
            long r12 = (long) r13
            long r4 = r4 + r12
            goto L_0x007f
        L_0x0047:
            okio.Buffer r1 = new okio.Buffer
            r1.<init>()
            okio.Buffer r1 = r1.writeDecimalLong((long) r4)
            okio.Buffer r1 = r1.writeByte((int) r12)
            if (r2 != 0) goto L_0x0059
            r1.readByte()
        L_0x0059:
            java.lang.NumberFormatException r2 = new java.lang.NumberFormatException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "Number too large: "
            r3.<init>(r4)
            java.lang.String r1 = r1.readUtf8()
            r3.append(r1)
            java.lang.String r1 = r3.toString()
            r2.<init>(r1)
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            throw r2
        L_0x0073:
            r13 = 45
            byte r13 = (byte) r13
            r14 = 1
            if (r12 != r13) goto L_0x0084
            if (r1 != 0) goto L_0x0084
            r12 = 1
            long r6 = r6 - r12
            r2 = 1
        L_0x007f:
            int r10 = r10 + 1
            int r1 = r1 + 1
            goto L_0x001e
        L_0x0084:
            if (r1 == 0) goto L_0x0088
            r3 = 1
            goto L_0x00a2
        L_0x0088:
            java.lang.NumberFormatException r1 = new java.lang.NumberFormatException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "Expected leading [0-9] or '-' character but was 0x"
            r2.<init>(r3)
            java.lang.String r3 = java.lang.Integer.toHexString(r12)
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r1.<init>(r2)
            java.lang.Throwable r1 = (java.lang.Throwable) r1
            throw r1
        L_0x00a2:
            if (r10 != r11) goto L_0x00ae
            okio.Segment r9 = r8.pop()
            r0.head = r9
            okio.SegmentPool.recycle(r8)
            goto L_0x00b0
        L_0x00ae:
            r8.pos = r10
        L_0x00b0:
            if (r3 != 0) goto L_0x00b6
            okio.Segment r8 = r0.head
            if (r8 != 0) goto L_0x0011
        L_0x00b6:
            long r6 = r0.size
            long r8 = (long) r1
            long r6 = r6 - r8
            r0.size = r6
            if (r2 == 0) goto L_0x00bf
            goto L_0x00c0
        L_0x00bf:
            long r4 = -r4
        L_0x00c0:
            return r4
        L_0x00c1:
            java.io.EOFException r1 = new java.io.EOFException
            r1.<init>()
            java.lang.Throwable r1 = (java.lang.Throwable) r1
            goto L_0x00ca
        L_0x00c9:
            throw r1
        L_0x00ca:
            goto L_0x00c9
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.Buffer.readDecimalLong():long");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0098, code lost:
        if (r8 != r9) goto L_0x00a4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x009a, code lost:
        r15.head = r6.pop();
        okio.SegmentPool.recycle(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00a4, code lost:
        r6.pos = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00a6, code lost:
        if (r1 != false) goto L_0x00ac;
     */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x007c  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x007e A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public long readHexadecimalUnsignedLong() throws java.io.EOFException {
        /*
            r15 = this;
            long r0 = r15.size
            r2 = 0
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 == 0) goto L_0x00b3
            r0 = 0
            r4 = r2
            r1 = 0
        L_0x000b:
            okio.Segment r6 = r15.head
            if (r6 != 0) goto L_0x0012
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x0012:
            byte[] r7 = r6.data
            int r8 = r6.pos
            int r9 = r6.limit
        L_0x0018:
            if (r8 >= r9) goto L_0x0098
            byte r10 = r7[r8]
            r11 = 48
            byte r11 = (byte) r11
            if (r10 < r11) goto L_0x0029
            r12 = 57
            byte r12 = (byte) r12
            if (r10 > r12) goto L_0x0029
            int r11 = r10 - r11
            goto L_0x0043
        L_0x0029:
            r11 = 97
            byte r11 = (byte) r11
            if (r10 < r11) goto L_0x0038
            r12 = 102(0x66, float:1.43E-43)
            byte r12 = (byte) r12
            if (r10 > r12) goto L_0x0038
        L_0x0033:
            int r11 = r10 - r11
            int r11 = r11 + 10
            goto L_0x0043
        L_0x0038:
            r11 = 65
            byte r11 = (byte) r11
            if (r10 < r11) goto L_0x007a
            r12 = 70
            byte r12 = (byte) r12
            if (r10 > r12) goto L_0x007a
            goto L_0x0033
        L_0x0043:
            r12 = -1152921504606846976(0xf000000000000000, double:-3.105036184601418E231)
            long r12 = r12 & r4
            int r14 = (r12 > r2 ? 1 : (r12 == r2 ? 0 : -1))
            if (r14 != 0) goto L_0x0053
            r10 = 4
            long r4 = r4 << r10
            long r10 = (long) r11
            long r4 = r4 | r10
            int r8 = r8 + 1
            int r0 = r0 + 1
            goto L_0x0018
        L_0x0053:
            okio.Buffer r0 = new okio.Buffer
            r0.<init>()
            okio.Buffer r0 = r0.writeHexadecimalUnsignedLong((long) r4)
            okio.Buffer r0 = r0.writeByte((int) r10)
            java.lang.NumberFormatException r1 = new java.lang.NumberFormatException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "Number too large: "
            r2.<init>(r3)
            java.lang.String r0 = r0.readUtf8()
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            r1.<init>(r0)
            java.lang.Throwable r1 = (java.lang.Throwable) r1
            throw r1
        L_0x007a:
            if (r0 == 0) goto L_0x007e
            r1 = 1
            goto L_0x0098
        L_0x007e:
            java.lang.NumberFormatException r0 = new java.lang.NumberFormatException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Expected leading [0-9a-fA-F] character but was 0x"
            r1.<init>(r2)
            java.lang.String r2 = java.lang.Integer.toHexString(r10)
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            throw r0
        L_0x0098:
            if (r8 != r9) goto L_0x00a4
            okio.Segment r7 = r6.pop()
            r15.head = r7
            okio.SegmentPool.recycle(r6)
            goto L_0x00a6
        L_0x00a4:
            r6.pos = r8
        L_0x00a6:
            if (r1 != 0) goto L_0x00ac
            okio.Segment r6 = r15.head
            if (r6 != 0) goto L_0x000b
        L_0x00ac:
            long r1 = r15.size
            long r6 = (long) r0
            long r1 = r1 - r6
            r15.size = r1
            return r4
        L_0x00b3:
            java.io.EOFException r0 = new java.io.EOFException
            r0.<init>()
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            goto L_0x00bc
        L_0x00bb:
            throw r0
        L_0x00bc:
            goto L_0x00bb
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.Buffer.readHexadecimalUnsignedLong():long");
    }

    public ByteString readByteString() {
        return new ByteString(readByteArray());
    }

    public ByteString readByteString(long j) throws EOFException {
        return new ByteString(readByteArray(j));
    }

    public int select(Options options) {
        Intrinsics.checkParameterIsNotNull(options, "options");
        int selectPrefix$jvm$default = selectPrefix$jvm$default(this, options, false, 2, (Object) null);
        if (selectPrefix$jvm$default == -1) {
            return -1;
        }
        skip((long) options.getByteStrings$jvm()[selectPrefix$jvm$default].size());
        return selectPrefix$jvm$default;
    }

    public static /* bridge */ /* synthetic */ int selectPrefix$jvm$default(Buffer buffer, Options options, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return buffer.selectPrefix$jvm(options, z);
    }

    public final int selectPrefix$jvm(Options options, boolean z) {
        int i;
        int i2;
        int i3;
        Segment segment;
        int i4;
        Intrinsics.checkParameterIsNotNull(options, "options");
        Segment segment2 = this.head;
        int i5 = -2;
        if (segment2 == null) {
            return z ? -2 : -1;
        }
        byte[] bArr = segment2.data;
        int i6 = segment2.pos;
        int i7 = segment2.limit;
        int[] trie$jvm = options.getTrie$jvm();
        Segment segment3 = segment2;
        int i8 = 0;
        int i9 = -1;
        loop0:
        while (true) {
            int i10 = i8 + 1;
            int i11 = trie$jvm[i8];
            int i12 = i8 + 2;
            int i13 = trie$jvm[i10];
            if (i13 != -1) {
                i9 = i13;
            }
            if (segment3 == null) {
                break;
            }
            if (i11 < 0) {
                int i14 = i12 + (i11 * -1);
                while (true) {
                    int i15 = i6 + 1;
                    int i16 = i12 + 1;
                    if ((bArr[i6] & 255) != trie$jvm[i12]) {
                        return i9;
                    }
                    boolean z2 = i16 == i14;
                    if (i15 == i7) {
                        if (segment3 == null) {
                            Intrinsics.throwNpe();
                        }
                        Segment segment4 = segment3.next;
                        if (segment4 == null) {
                            Intrinsics.throwNpe();
                        }
                        i4 = segment4.pos;
                        byte[] bArr2 = segment4.data;
                        i3 = segment4.limit;
                        if (segment4 != segment2) {
                            byte[] bArr3 = bArr2;
                            segment = segment4;
                            bArr = bArr3;
                        } else if (!z2) {
                            break loop0;
                        } else {
                            Segment segment5 = null;
                            bArr = bArr2;
                            segment = null;
                        }
                    } else {
                        segment = segment3;
                        i3 = i7;
                        i4 = i15;
                    }
                    if (z2) {
                        i = trie$jvm[i16];
                        i2 = i4;
                        i7 = i3;
                        segment3 = segment;
                        break;
                    }
                    i6 = i4;
                    i7 = i3;
                    segment3 = segment;
                    i12 = i16;
                }
            } else {
                int i17 = i6 + 1;
                byte b = bArr[i6] & 255;
                int i18 = i12 + i11;
                while (i12 != i18) {
                    if (b == trie$jvm[i12]) {
                        i = trie$jvm[i12 + i11];
                        if (i17 == i7) {
                            segment3 = segment3.next;
                            if (segment3 == null) {
                                Intrinsics.throwNpe();
                            }
                            i2 = segment3.pos;
                            bArr = segment3.data;
                            i7 = segment3.limit;
                            if (segment3 == segment2) {
                                Segment segment6 = null;
                                segment3 = null;
                            }
                        } else {
                            i2 = i17;
                        }
                    } else {
                        i12++;
                    }
                }
                return i9;
            }
            if (i >= 0) {
                return i;
            }
            i8 = -i;
            i6 = i2;
            i5 = -2;
        }
        return z ? i5 : i9;
    }

    public void readFully(Buffer buffer, long j) throws EOFException {
        Intrinsics.checkParameterIsNotNull(buffer, "sink");
        long j2 = this.size;
        if (j2 >= j) {
            buffer.write(this, j);
        } else {
            buffer.write(this, j2);
            throw new EOFException();
        }
    }

    public long readAll(Sink sink) throws IOException {
        Intrinsics.checkParameterIsNotNull(sink, "sink");
        long j = this.size;
        if (j > 0) {
            sink.write(this, j);
        }
        return j;
    }

    public String readUtf8() {
        return readString(this.size, Charsets.UTF_8);
    }

    public String readUtf8(long j) throws EOFException {
        return readString(j, Charsets.UTF_8);
    }

    public String readString(Charset charset) {
        Intrinsics.checkParameterIsNotNull(charset, "charset");
        return readString(this.size, charset);
    }

    public String readString(long j, Charset charset) throws EOFException {
        Intrinsics.checkParameterIsNotNull(charset, "charset");
        if (j < 0 || j > ((long) Integer.MAX_VALUE)) {
            throw new IllegalArgumentException(("byteCount: " + j).toString());
        } else if (this.size < j) {
            throw new EOFException();
        } else if (j == 0) {
            return "";
        } else {
            Segment segment = this.head;
            if (segment == null) {
                Intrinsics.throwNpe();
            }
            if (((long) segment.pos) + j > ((long) segment.limit)) {
                return new String(readByteArray(j), charset);
            }
            int i = (int) j;
            String str = new String(segment.data, segment.pos, i, charset);
            segment.pos += i;
            this.size -= j;
            if (segment.pos == segment.limit) {
                this.head = segment.pop();
                SegmentPool.recycle(segment);
            }
            return str;
        }
    }

    public String readUtf8Line() throws EOFException {
        long indexOf = indexOf((byte) 10);
        if (indexOf != -1) {
            return readUtf8Line$jvm(indexOf);
        }
        long j = this.size;
        if (j != 0) {
            return readUtf8(j);
        }
        return null;
    }

    public String readUtf8LineStrict() throws EOFException {
        return readUtf8LineStrict(Long.MAX_VALUE);
    }

    public String readUtf8LineStrict(long j) throws EOFException {
        if (j >= 0) {
            long j2 = Long.MAX_VALUE;
            if (j != Long.MAX_VALUE) {
                j2 = j + 1;
            }
            byte b = (byte) 10;
            long indexOf = indexOf(b, 0, j2);
            if (indexOf != -1) {
                return readUtf8Line$jvm(indexOf);
            }
            if (j2 < this.size && getByte(j2 - 1) == ((byte) 13) && getByte(j2) == b) {
                return readUtf8Line$jvm(j2);
            }
            Buffer buffer = new Buffer();
            copyTo(buffer, 0, Math.min((long) 32, this.size));
            throw new EOFException("\\n not found: limit=" + Math.min(this.size, j) + " content=" + buffer.readByteString().hex() + Typography.ellipsis);
        }
        throw new IllegalArgumentException(("limit < 0: " + j).toString());
    }

    public final String readUtf8Line$jvm(long j) throws EOFException {
        if (j > 0) {
            long j2 = j - 1;
            if (getByte(j2) == ((byte) 13)) {
                String readUtf8 = readUtf8(j2);
                skip(2);
                return readUtf8;
            }
        }
        String readUtf82 = readUtf8(j);
        skip(1);
        return readUtf82;
    }

    public int readUtf8CodePoint() throws EOFException {
        byte b;
        int i;
        byte b2;
        if (this.size != 0) {
            byte b3 = getByte(0);
            int i2 = 1;
            if ((b3 & 128) == 0) {
                b2 = b3 & Byte.MAX_VALUE;
                i = 1;
                b = 0;
            } else if ((b3 & 224) == 192) {
                b2 = b3 & Ascii.US;
                i = 2;
                b = 128;
            } else if ((b3 & 240) == 224) {
                b2 = b3 & Ascii.SI;
                i = 3;
                b = 2048;
            } else if ((b3 & 248) == 240) {
                b2 = b3 & 7;
                i = 4;
                b = 65536;
            } else {
                skip(1);
                return Utf8.REPLACEMENT_CODE_POINT;
            }
            long j = (long) i;
            if (this.size >= j) {
                while (i2 < i) {
                    long j2 = (long) i2;
                    byte b4 = getByte(j2);
                    if ((b4 & 192) == 128) {
                        b2 = (b2 << 6) | (b4 & Utf8.REPLACEMENT_BYTE);
                        i2++;
                    } else {
                        skip(j2);
                        return Utf8.REPLACEMENT_CODE_POINT;
                    }
                }
                skip(j);
                if (b2 > 1114111) {
                    return Utf8.REPLACEMENT_CODE_POINT;
                }
                if ((55296 <= b2 && 57343 >= b2) || b2 < b) {
                    return Utf8.REPLACEMENT_CODE_POINT;
                }
                return b2;
            }
            throw new EOFException("size < " + i + ": " + this.size + " (to read code point prefixed 0x" + Integer.toHexString(b3) + ")");
        }
        throw new EOFException();
    }

    public byte[] readByteArray() {
        return readByteArray(this.size);
    }

    public byte[] readByteArray(long j) throws EOFException {
        if (j < 0 || j > ((long) Integer.MAX_VALUE)) {
            throw new IllegalArgumentException(("byteCount: " + j).toString());
        } else if (this.size >= j) {
            byte[] bArr = new byte[((int) j)];
            readFully(bArr);
            return bArr;
        } else {
            throw new EOFException();
        }
    }

    public int read(byte[] bArr) {
        Intrinsics.checkParameterIsNotNull(bArr, "sink");
        return read(bArr, 0, bArr.length);
    }

    public void readFully(byte[] bArr) throws EOFException {
        Intrinsics.checkParameterIsNotNull(bArr, "sink");
        int i = 0;
        while (i < bArr.length) {
            int read = read(bArr, i, bArr.length - i);
            if (read != -1) {
                i += read;
            } else {
                throw new EOFException();
            }
        }
    }

    public int read(byte[] bArr, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(bArr, "sink");
        Util.checkOffsetAndCount((long) bArr.length, (long) i, (long) i2);
        Segment segment = this.head;
        if (segment == null) {
            return -1;
        }
        int min = Math.min(i2, segment.limit - segment.pos);
        System.arraycopy(segment.data, segment.pos, bArr, i, min);
        segment.pos += min;
        this.size -= (long) min;
        if (segment.pos == segment.limit) {
            this.head = segment.pop();
            SegmentPool.recycle(segment);
        }
        return min;
    }

    public int read(ByteBuffer byteBuffer) throws IOException {
        Intrinsics.checkParameterIsNotNull(byteBuffer, "sink");
        Segment segment = this.head;
        if (segment == null) {
            return -1;
        }
        int min = Math.min(byteBuffer.remaining(), segment.limit - segment.pos);
        byteBuffer.put(segment.data, segment.pos, min);
        segment.pos += min;
        this.size -= (long) min;
        if (segment.pos == segment.limit) {
            this.head = segment.pop();
            SegmentPool.recycle(segment);
        }
        return min;
    }

    public final void clear() {
        skip(this.size);
    }

    public void skip(long j) throws EOFException {
        while (j > 0) {
            Segment segment = this.head;
            if (segment != null) {
                int min = (int) Math.min(j, (long) (segment.limit - segment.pos));
                long j2 = (long) min;
                this.size -= j2;
                j -= j2;
                segment.pos += min;
                if (segment.pos == segment.limit) {
                    this.head = segment.pop();
                    SegmentPool.recycle(segment);
                }
            } else {
                throw new EOFException();
            }
        }
    }

    public Buffer write(ByteString byteString) {
        Intrinsics.checkParameterIsNotNull(byteString, "byteString");
        byteString.write$jvm(this);
        return this;
    }

    public Buffer writeUtf8(String str) {
        Intrinsics.checkParameterIsNotNull(str, TypedValues.Custom.S_STRING);
        return writeUtf8(str, 0, str.length());
    }

    public Buffer writeUtf8(String str, int i, int i2) {
        char c;
        Intrinsics.checkParameterIsNotNull(str, TypedValues.Custom.S_STRING);
        if (i < 0) {
            throw new IllegalArgumentException(("beginIndex < 0: " + i).toString());
        } else if (i2 < i) {
            throw new IllegalArgumentException(("endIndex < beginIndex: " + i2 + " < " + i).toString());
        } else if (i2 <= str.length()) {
            while (i < i2) {
                char charAt = str.charAt(i);
                if (charAt < 128) {
                    Segment writableSegment$jvm = writableSegment$jvm(1);
                    byte[] bArr = writableSegment$jvm.data;
                    int i3 = writableSegment$jvm.limit - i;
                    int min = Math.min(i2, 8192 - i3);
                    int i4 = i + 1;
                    bArr[i + i3] = (byte) charAt;
                    while (i4 < min) {
                        char charAt2 = str.charAt(i4);
                        if (charAt2 >= 128) {
                            break;
                        }
                        bArr[i4 + i3] = (byte) charAt2;
                        i4++;
                    }
                    int i5 = (i3 + i4) - writableSegment$jvm.limit;
                    writableSegment$jvm.limit += i5;
                    this.size += (long) i5;
                    i = i4;
                } else {
                    if (charAt < 2048) {
                        Segment writableSegment$jvm2 = writableSegment$jvm(2);
                        writableSegment$jvm2.data[writableSegment$jvm2.limit] = (byte) ((charAt >> 6) | 192);
                        writableSegment$jvm2.data[writableSegment$jvm2.limit + 1] = (byte) ((charAt & '?') | 128);
                        writableSegment$jvm2.limit += 2;
                        this.size += 2;
                    } else if (charAt < 55296 || charAt > 57343) {
                        Segment writableSegment$jvm3 = writableSegment$jvm(3);
                        writableSegment$jvm3.data[writableSegment$jvm3.limit] = (byte) ((charAt >> 12) | BERTags.FLAGS);
                        writableSegment$jvm3.data[writableSegment$jvm3.limit + 1] = (byte) (((charAt >> 6) & 63) | 128);
                        writableSegment$jvm3.data[writableSegment$jvm3.limit + 2] = (byte) ((charAt & '?') | 128);
                        writableSegment$jvm3.limit += 3;
                        this.size += 3;
                    } else {
                        int i6 = i + 1;
                        if (i6 < i2) {
                            c = str.charAt(i6);
                        } else {
                            c = 0;
                        }
                        if (charAt > 56319 || 56320 > c || 57343 < c) {
                            writeByte(63);
                            i = i6;
                        } else {
                            int i7 = (((charAt & 1023) << 10) | (c & 1023)) + 0;
                            Segment writableSegment$jvm4 = writableSegment$jvm(4);
                            writableSegment$jvm4.data[writableSegment$jvm4.limit] = (byte) ((i7 >> 18) | 240);
                            writableSegment$jvm4.data[writableSegment$jvm4.limit + 1] = (byte) (((i7 >> 12) & 63) | 128);
                            writableSegment$jvm4.data[writableSegment$jvm4.limit + 2] = (byte) (((i7 >> 6) & 63) | 128);
                            writableSegment$jvm4.data[writableSegment$jvm4.limit + 3] = (byte) ((i7 & 63) | 128);
                            writableSegment$jvm4.limit += 4;
                            this.size += 4;
                            i += 2;
                        }
                    }
                    i++;
                }
            }
            return this;
        } else {
            throw new IllegalArgumentException(("endIndex > string.length: " + i2 + " > " + str.length()).toString());
        }
    }

    public Buffer writeUtf8CodePoint(int i) {
        if (i < 128) {
            writeByte(i);
        } else if (i < 2048) {
            Segment writableSegment$jvm = writableSegment$jvm(2);
            writableSegment$jvm.data[writableSegment$jvm.limit] = (byte) ((i >> 6) | 192);
            writableSegment$jvm.data[writableSegment$jvm.limit + 1] = (byte) ((i & 63) | 128);
            writableSegment$jvm.limit += 2;
            this.size += 2;
        } else if (55296 <= i && 57343 >= i) {
            writeByte(63);
        } else if (i < 65536) {
            Segment writableSegment$jvm2 = writableSegment$jvm(3);
            writableSegment$jvm2.data[writableSegment$jvm2.limit] = (byte) ((i >> 12) | BERTags.FLAGS);
            writableSegment$jvm2.data[writableSegment$jvm2.limit + 1] = (byte) (((i >> 6) & 63) | 128);
            writableSegment$jvm2.data[writableSegment$jvm2.limit + 2] = (byte) ((i & 63) | 128);
            writableSegment$jvm2.limit += 3;
            this.size += 3;
        } else if (i <= 1114111) {
            Segment writableSegment$jvm3 = writableSegment$jvm(4);
            writableSegment$jvm3.data[writableSegment$jvm3.limit] = (byte) ((i >> 18) | 240);
            writableSegment$jvm3.data[writableSegment$jvm3.limit + 1] = (byte) (((i >> 12) & 63) | 128);
            writableSegment$jvm3.data[writableSegment$jvm3.limit + 2] = (byte) (((i >> 6) & 63) | 128);
            writableSegment$jvm3.data[writableSegment$jvm3.limit + 3] = (byte) ((i & 63) | 128);
            writableSegment$jvm3.limit += 4;
            this.size += 4;
        } else {
            throw new IllegalArgumentException("Unexpected code point: " + Integer.toHexString(i));
        }
        return this;
    }

    public Buffer writeString(String str, Charset charset) {
        Intrinsics.checkParameterIsNotNull(str, TypedValues.Custom.S_STRING);
        Intrinsics.checkParameterIsNotNull(charset, "charset");
        return writeString(str, 0, str.length(), charset);
    }

    public Buffer writeString(String str, int i, int i2, Charset charset) {
        Intrinsics.checkParameterIsNotNull(str, TypedValues.Custom.S_STRING);
        Intrinsics.checkParameterIsNotNull(charset, "charset");
        if (i < 0) {
            throw new IllegalArgumentException(("beginIndex < 0: " + i).toString());
        } else if (i2 < i) {
            throw new IllegalArgumentException(("endIndex < beginIndex: " + i2 + " < " + i).toString());
        } else if (i2 > str.length()) {
            throw new IllegalArgumentException(("endIndex > string.length: " + i2 + " > " + str.length()).toString());
        } else if (Intrinsics.areEqual((Object) charset, (Object) Charsets.UTF_8)) {
            return writeUtf8(str, i, i2);
        } else {
            String substring = str.substring(i, i2);
            Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            if (substring != null) {
                byte[] bytes = substring.getBytes(charset);
                Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
                return write(bytes, 0, bytes.length);
            }
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
    }

    public Buffer write(byte[] bArr) {
        Intrinsics.checkParameterIsNotNull(bArr, "source");
        return write(bArr, 0, bArr.length);
    }

    public Buffer write(byte[] bArr, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(bArr, "source");
        long j = (long) i2;
        Util.checkOffsetAndCount((long) bArr.length, (long) i, j);
        int i3 = i2 + i;
        while (i < i3) {
            Segment writableSegment$jvm = writableSegment$jvm(1);
            int min = Math.min(i3 - i, 8192 - writableSegment$jvm.limit);
            System.arraycopy(bArr, i, writableSegment$jvm.data, writableSegment$jvm.limit, min);
            i += min;
            writableSegment$jvm.limit += min;
        }
        this.size += j;
        return this;
    }

    public int write(ByteBuffer byteBuffer) throws IOException {
        Intrinsics.checkParameterIsNotNull(byteBuffer, "source");
        int remaining = byteBuffer.remaining();
        int i = remaining;
        while (i > 0) {
            Segment writableSegment$jvm = writableSegment$jvm(1);
            int min = Math.min(i, 8192 - writableSegment$jvm.limit);
            byteBuffer.get(writableSegment$jvm.data, writableSegment$jvm.limit, min);
            i -= min;
            writableSegment$jvm.limit += min;
        }
        this.size += (long) remaining;
        return remaining;
    }

    public long writeAll(Source source) throws IOException {
        Intrinsics.checkParameterIsNotNull(source, "source");
        long j = 0;
        while (true) {
            long read = source.read(this, (long) 8192);
            if (read == -1) {
                return j;
            }
            j += read;
        }
    }

    public BufferedSink write(Source source, long j) throws IOException {
        Intrinsics.checkParameterIsNotNull(source, "source");
        while (j > 0) {
            long read = source.read(this, j);
            if (read != -1) {
                j -= read;
            } else {
                throw new EOFException();
            }
        }
        return this;
    }

    public Buffer writeByte(int i) {
        Segment writableSegment$jvm = writableSegment$jvm(1);
        byte[] bArr = writableSegment$jvm.data;
        int i2 = writableSegment$jvm.limit;
        writableSegment$jvm.limit = i2 + 1;
        bArr[i2] = (byte) i;
        this.size++;
        return this;
    }

    public Buffer writeShort(int i) {
        Segment writableSegment$jvm = writableSegment$jvm(2);
        byte[] bArr = writableSegment$jvm.data;
        int i2 = writableSegment$jvm.limit;
        bArr[i2] = (byte) ((i >>> 8) & 255);
        bArr[i2 + 1] = (byte) (i & 255);
        writableSegment$jvm.limit = i2 + 2;
        this.size += 2;
        return this;
    }

    public Buffer writeShortLe(int i) {
        return writeShort((int) Util.reverseBytes((short) i));
    }

    public Buffer writeInt(int i) {
        Segment writableSegment$jvm = writableSegment$jvm(4);
        byte[] bArr = writableSegment$jvm.data;
        int i2 = writableSegment$jvm.limit;
        bArr[i2] = (byte) ((i >>> 24) & 255);
        bArr[i2 + 1] = (byte) ((i >>> 16) & 255);
        bArr[i2 + 2] = (byte) ((i >>> 8) & 255);
        bArr[i2 + 3] = (byte) (i & 255);
        writableSegment$jvm.limit = i2 + 4;
        this.size += 4;
        return this;
    }

    public Buffer writeIntLe(int i) {
        return writeInt(Util.reverseBytes(i));
    }

    public Buffer writeLong(long j) {
        Segment writableSegment$jvm = writableSegment$jvm(8);
        byte[] bArr = writableSegment$jvm.data;
        int i = writableSegment$jvm.limit;
        bArr[i] = (byte) ((int) ((j >>> 56) & 255));
        bArr[i + 1] = (byte) ((int) ((j >>> 48) & 255));
        bArr[i + 2] = (byte) ((int) ((j >>> 40) & 255));
        bArr[i + 3] = (byte) ((int) ((j >>> 32) & 255));
        bArr[i + 4] = (byte) ((int) ((j >>> 24) & 255));
        bArr[i + 5] = (byte) ((int) ((j >>> 16) & 255));
        bArr[i + 6] = (byte) ((int) ((j >>> 8) & 255));
        bArr[i + 7] = (byte) ((int) (j & 255));
        writableSegment$jvm.limit = i + 8;
        this.size += 8;
        return this;
    }

    public Buffer writeLongLe(long j) {
        return writeLong(Util.reverseBytes(j));
    }

    public Buffer writeDecimalLong(long j) {
        boolean z;
        if (j == 0) {
            return writeByte(48);
        }
        int i = 1;
        if (j < 0) {
            j = -j;
            if (j < 0) {
                return writeUtf8("-9223372036854775808");
            }
            z = true;
        } else {
            z = false;
        }
        if (j >= 100000000) {
            i = j < 1000000000000L ? j < 10000000000L ? j < 1000000000 ? 9 : 10 : j < 100000000000L ? 11 : 12 : j < 1000000000000000L ? j < 10000000000000L ? 13 : j < 100000000000000L ? 14 : 15 : j < 100000000000000000L ? j < 10000000000000000L ? 16 : 17 : j < 1000000000000000000L ? 18 : 19;
        } else if (j >= 10000) {
            i = j < PickTimeFragment.SECONDS_IN_MICROS ? j < 100000 ? 5 : 6 : j < 10000000 ? 7 : 8;
        } else if (j >= 100) {
            i = j < 1000 ? 3 : 4;
        } else if (j >= 10) {
            i = 2;
        }
        if (z) {
            i++;
        }
        Segment writableSegment$jvm = writableSegment$jvm(i);
        byte[] bArr = writableSegment$jvm.data;
        int i2 = writableSegment$jvm.limit + i;
        while (j != 0) {
            long j2 = (long) 10;
            i2--;
            bArr[i2] = DIGITS[(int) (j % j2)];
            j /= j2;
        }
        if (z) {
            bArr[i2 - 1] = (byte) 45;
        }
        writableSegment$jvm.limit += i;
        this.size += (long) i;
        return this;
    }

    public Buffer writeHexadecimalUnsignedLong(long j) {
        if (j == 0) {
            return writeByte(48);
        }
        int numberOfTrailingZeros = (Long.numberOfTrailingZeros(Long.highestOneBit(j)) / 4) + 1;
        Segment writableSegment$jvm = writableSegment$jvm(numberOfTrailingZeros);
        byte[] bArr = writableSegment$jvm.data;
        int i = writableSegment$jvm.limit;
        for (int i2 = (writableSegment$jvm.limit + numberOfTrailingZeros) - 1; i2 >= i; i2--) {
            bArr[i2] = DIGITS[(int) (15 & j)];
            j >>>= 4;
        }
        writableSegment$jvm.limit += numberOfTrailingZeros;
        this.size += (long) numberOfTrailingZeros;
        return this;
    }

    public final Segment writableSegment$jvm(int i) {
        if (i < 1 || i > 8192) {
            throw new IllegalArgumentException("unexpected capacity".toString());
        }
        Segment segment = this.head;
        if (segment == null) {
            Segment take = SegmentPool.take();
            this.head = take;
            take.prev = take;
            take.next = take;
            return take;
        }
        if (segment == null) {
            Intrinsics.throwNpe();
        }
        Segment segment2 = segment.prev;
        if (segment2 == null) {
            Intrinsics.throwNpe();
        }
        return (segment2.limit + i > 8192 || !segment2.owner) ? segment2.push(SegmentPool.take()) : segment2;
    }

    public void write(Buffer buffer, long j) {
        Segment segment;
        Intrinsics.checkParameterIsNotNull(buffer, "source");
        Buffer buffer2 = this;
        if (buffer != this) {
            Util.checkOffsetAndCount(buffer.size, 0, j);
            while (j > 0) {
                Segment segment2 = buffer.head;
                if (segment2 == null) {
                    Intrinsics.throwNpe();
                }
                int i = segment2.limit;
                Segment segment3 = buffer.head;
                if (segment3 == null) {
                    Intrinsics.throwNpe();
                }
                if (j < ((long) (i - segment3.pos))) {
                    Segment segment4 = this.head;
                    if (segment4 != null) {
                        if (segment4 == null) {
                            Intrinsics.throwNpe();
                        }
                        segment = segment4.prev;
                    } else {
                        segment = null;
                    }
                    if (segment != null && segment.owner) {
                        if ((((long) segment.limit) + j) - ((long) (segment.shared ? 0 : segment.pos)) <= ((long) 8192)) {
                            Segment segment5 = buffer.head;
                            if (segment5 == null) {
                                Intrinsics.throwNpe();
                            }
                            segment5.writeTo(segment, (int) j);
                            buffer.size -= j;
                            this.size += j;
                            return;
                        }
                    }
                    Segment segment6 = buffer.head;
                    if (segment6 == null) {
                        Intrinsics.throwNpe();
                    }
                    buffer.head = segment6.split((int) j);
                }
                Segment segment7 = buffer.head;
                if (segment7 == null) {
                    Intrinsics.throwNpe();
                }
                long j2 = (long) (segment7.limit - segment7.pos);
                buffer.head = segment7.pop();
                Segment segment8 = this.head;
                if (segment8 == null) {
                    this.head = segment7;
                    segment7.prev = segment7;
                    segment7.next = segment7.prev;
                } else {
                    if (segment8 == null) {
                        Intrinsics.throwNpe();
                    }
                    Segment segment9 = segment8.prev;
                    if (segment9 == null) {
                        Intrinsics.throwNpe();
                    }
                    segment9.push(segment7).compact();
                }
                buffer.size -= j2;
                this.size += j2;
                j -= j2;
            }
            return;
        }
        throw new IllegalArgumentException("source == this".toString());
    }

    public long read(Buffer buffer, long j) {
        Intrinsics.checkParameterIsNotNull(buffer, "sink");
        if (j >= 0) {
            long j2 = this.size;
            if (j2 == 0) {
                return -1;
            }
            if (j > j2) {
                j = j2;
            }
            buffer.write(this, j);
            return j;
        }
        throw new IllegalArgumentException(("byteCount < 0: " + j).toString());
    }

    public long indexOf(byte b) {
        return indexOf(b, 0, Long.MAX_VALUE);
    }

    private final <T> T seek(long j, Function2<? super Segment, ? super Long, ? extends T> function2) {
        Segment segment = this.head;
        if (segment == null) {
            return function2.invoke(null, -1L);
        }
        if (size() - j < j) {
            long size2 = size();
            while (size2 > j) {
                segment = segment.prev;
                if (segment == null) {
                    Intrinsics.throwNpe();
                }
                size2 -= (long) (segment.limit - segment.pos);
            }
            return function2.invoke(segment, Long.valueOf(size2));
        }
        long j2 = 0;
        while (true) {
            long j3 = ((long) (segment.limit - segment.pos)) + j2;
            if (j3 > j) {
                return function2.invoke(segment, Long.valueOf(j2));
            }
            segment = segment.next;
            if (segment == null) {
                Intrinsics.throwNpe();
            }
            j2 = j3;
        }
    }

    public long indexOf(byte b, long j) {
        return indexOf(b, j, Long.MAX_VALUE);
    }

    public long indexOf(byte b, long j, long j2) {
        long j3;
        int i;
        long j4 = 0;
        if (0 > j || j2 < j) {
            throw new IllegalArgumentException(("size=" + this.size + " fromIndex=" + j + " toIndex=" + j2).toString());
        }
        long j5 = this.size;
        if (j2 > j5) {
            j2 = j5;
        }
        if (j == j2) {
            return -1;
        }
        Segment segment = this.head;
        if (segment != null) {
            if (size() - j < j) {
                j3 = size();
                while (j3 > j) {
                    segment = segment.prev;
                    if (segment == null) {
                        Intrinsics.throwNpe();
                    }
                    j3 -= (long) (segment.limit - segment.pos);
                }
                if (segment != null) {
                    while (j3 < j2) {
                        byte[] bArr = segment.data;
                        int min = (int) Math.min((long) segment.limit, (((long) segment.pos) + j2) - j3);
                        i = (int) ((((long) segment.pos) + j) - j3);
                        while (i < min) {
                            if (bArr[i] != b) {
                                i++;
                            }
                        }
                        j3 += (long) (segment.limit - segment.pos);
                        segment = segment.next;
                        if (segment == null) {
                            Intrinsics.throwNpe();
                        }
                        j = j3;
                    }
                }
                return -1;
            }
            while (true) {
                long j6 = ((long) (segment.limit - segment.pos)) + j4;
                if (j6 > j) {
                    break;
                }
                segment = segment.next;
                if (segment == null) {
                    Intrinsics.throwNpe();
                }
                j4 = j6;
            }
            if (segment != null) {
                while (j3 < j2) {
                    byte[] bArr2 = segment.data;
                    int min2 = (int) Math.min((long) segment.limit, (((long) segment.pos) + j2) - j3);
                    int i2 = (int) ((((long) segment.pos) + j) - j3);
                    while (i < min2) {
                        if (bArr2[i] != b) {
                            i2 = i + 1;
                        }
                    }
                    j4 = j3 + ((long) (segment.limit - segment.pos));
                    segment = segment.next;
                    if (segment == null) {
                        Intrinsics.throwNpe();
                    }
                    j = j4;
                }
            }
            return -1;
            return ((long) (i - segment.pos)) + j3;
        }
        Segment segment2 = null;
        return -1;
    }

    public long indexOf(ByteString byteString) throws IOException {
        Intrinsics.checkParameterIsNotNull(byteString, "bytes");
        return indexOf(byteString, 0);
    }

    public long indexOf(ByteString byteString, long j) throws IOException {
        Buffer buffer = this;
        long j2 = j;
        Intrinsics.checkParameterIsNotNull(byteString, "bytes");
        if (byteString.size() > 0) {
            long j3 = 0;
            if (j2 >= 0) {
                Segment segment = buffer.head;
                if (segment == null) {
                    Segment segment2 = null;
                    return -1;
                } else if (size() - j2 < j2) {
                    long size2 = size();
                    while (size2 > j2) {
                        segment = segment.prev;
                        if (segment == null) {
                            Intrinsics.throwNpe();
                        }
                        size2 -= (long) (segment.limit - segment.pos);
                    }
                    if (segment == null) {
                        return -1;
                    }
                    byte[] internalArray$jvm = byteString.internalArray$jvm();
                    byte b = internalArray$jvm[0];
                    int size3 = byteString.size();
                    long j4 = (buffer.size - ((long) size3)) + 1;
                    Segment segment3 = segment;
                    long j5 = size2;
                    while (j5 < j4) {
                        byte[] bArr = segment3.data;
                        long j6 = j4;
                        int min = (int) Math.min((long) segment3.limit, (((long) segment3.pos) + j4) - j5);
                        for (int i = (int) ((((long) segment3.pos) + j2) - j5); i < min; i++) {
                            if (bArr[i] == b) {
                                if (rangeEquals(segment3, i + 1, internalArray$jvm, 1, size3)) {
                                    return ((long) (i - segment3.pos)) + j5;
                                }
                            }
                        }
                        j5 += (long) (segment3.limit - segment3.pos);
                        segment3 = segment3.next;
                        if (segment3 == null) {
                            Intrinsics.throwNpe();
                        }
                        j2 = j5;
                        j4 = j6;
                    }
                    return -1;
                } else {
                    while (true) {
                        long j7 = ((long) (segment.limit - segment.pos)) + j3;
                        if (j7 > j2) {
                            break;
                        }
                        segment = segment.next;
                        if (segment == null) {
                            Intrinsics.throwNpe();
                        }
                        buffer = this;
                        j3 = j7;
                    }
                    if (segment == null) {
                        return -1;
                    }
                    byte[] internalArray$jvm2 = byteString.internalArray$jvm();
                    byte b2 = internalArray$jvm2[0];
                    int size4 = byteString.size();
                    long j8 = j3;
                    long j9 = (buffer.size - ((long) size4)) + 1;
                    Segment segment4 = segment;
                    while (j8 < j9) {
                        byte[] bArr2 = segment4.data;
                        int min2 = (int) Math.min((long) segment4.limit, (((long) segment4.pos) + j9) - j8);
                        for (int i2 = (int) ((((long) segment4.pos) + j2) - j8); i2 < min2; i2++) {
                            if (bArr2[i2] == b2) {
                                if (rangeEquals(segment4, i2 + 1, internalArray$jvm2, 1, size4)) {
                                    return ((long) (i2 - segment4.pos)) + j8;
                                }
                            }
                        }
                        j8 += (long) (segment4.limit - segment4.pos);
                        segment4 = segment4.next;
                        if (segment4 == null) {
                            Intrinsics.throwNpe();
                        }
                        j2 = j8;
                    }
                    return -1;
                }
            } else {
                throw new IllegalArgumentException(("fromIndex < 0: " + j2).toString());
            }
        } else {
            throw new IllegalArgumentException("bytes is empty".toString());
        }
    }

    public long indexOfElement(ByteString byteString) {
        Intrinsics.checkParameterIsNotNull(byteString, "targetBytes");
        return indexOfElement(byteString, 0);
    }

    public boolean rangeEquals(long j, ByteString byteString) {
        Intrinsics.checkParameterIsNotNull(byteString, "bytes");
        return rangeEquals(j, byteString, 0, byteString.size());
    }

    public boolean rangeEquals(long j, ByteString byteString, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(byteString, "bytes");
        if (j < 0 || i < 0 || i2 < 0 || this.size - j < ((long) i2) || byteString.size() - i < i2) {
            return false;
        }
        for (int i3 = 0; i3 < i2; i3++) {
            if (getByte(((long) i3) + j) != byteString.getByte(i + i3)) {
                return false;
            }
        }
        return true;
    }

    private final boolean rangeEquals(Segment segment, int i, byte[] bArr, int i2, int i3) {
        int i4 = segment.limit;
        byte[] bArr2 = segment.data;
        while (i2 < i3) {
            if (i == i4) {
                segment = segment.next;
                if (segment == null) {
                    Intrinsics.throwNpe();
                }
                byte[] bArr3 = segment.data;
                int i5 = segment.pos;
                bArr2 = bArr3;
                i = i5;
                i4 = segment.limit;
            }
            if (bArr2[i] != bArr[i2]) {
                return false;
            }
            i++;
            i2++;
        }
        return true;
    }

    public Timeout timeout() {
        return Timeout.NONE;
    }

    public final ByteString md5() {
        return digest("MD5");
    }

    public final ByteString sha1() {
        return digest(McElieceCCA2KeyGenParameterSpec.SHA1);
    }

    public final ByteString sha256() {
        return digest("SHA-256");
    }

    public final ByteString sha512() {
        return digest("SHA-512");
    }

    private final ByteString digest(String str) {
        MessageDigest instance = MessageDigest.getInstance(str);
        Segment segment = this.head;
        if (segment != null) {
            instance.update(segment.data, segment.pos, segment.limit - segment.pos);
            Segment segment2 = segment.next;
            if (segment2 == null) {
                Intrinsics.throwNpe();
            }
            while (segment2 != segment) {
                instance.update(segment2.data, segment2.pos, segment2.limit - segment2.pos);
                segment2 = segment2.next;
                if (segment2 == null) {
                    Intrinsics.throwNpe();
                }
            }
        }
        byte[] digest = instance.digest();
        Intrinsics.checkExpressionValueIsNotNull(digest, "messageDigest.digest()");
        return new ByteString(digest);
    }

    public final ByteString hmacSha1(ByteString byteString) {
        Intrinsics.checkParameterIsNotNull(byteString, LeanbackPreferenceDialogFragment.ARG_KEY);
        return hmac("HmacSHA1", byteString);
    }

    public final ByteString hmacSha256(ByteString byteString) {
        Intrinsics.checkParameterIsNotNull(byteString, LeanbackPreferenceDialogFragment.ARG_KEY);
        return hmac("HmacSHA256", byteString);
    }

    public final ByteString hmacSha512(ByteString byteString) {
        Intrinsics.checkParameterIsNotNull(byteString, LeanbackPreferenceDialogFragment.ARG_KEY);
        return hmac("HmacSHA512", byteString);
    }

    private final ByteString hmac(String str, ByteString byteString) {
        try {
            Mac instance = Mac.getInstance(str);
            instance.init(new SecretKeySpec(byteString.internalArray$jvm(), str));
            Segment segment = this.head;
            if (segment != null) {
                instance.update(segment.data, segment.pos, segment.limit - segment.pos);
                Segment segment2 = segment.next;
                if (segment2 == null) {
                    Intrinsics.throwNpe();
                }
                while (segment2 != segment) {
                    instance.update(segment2.data, segment2.pos, segment2.limit - segment2.pos);
                    segment2 = segment2.next;
                    if (segment2 == null) {
                        Intrinsics.throwNpe();
                    }
                }
            }
            byte[] doFinal = instance.doFinal();
            Intrinsics.checkExpressionValueIsNotNull(doFinal, "mac.doFinal()");
            return new ByteString(doFinal);
        } catch (InvalidKeyException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /* JADX WARNING: type inference failed for: r19v0, types: [java.lang.Object] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r19) {
        /*
            r18 = this;
            r0 = r18
            r1 = r19
            r2 = r0
            okio.Buffer r2 = (okio.Buffer) r2
            r2 = 1
            if (r0 != r1) goto L_0x000b
            return r2
        L_0x000b:
            boolean r3 = r1 instanceof okio.Buffer
            r4 = 0
            if (r3 != 0) goto L_0x0011
            return r4
        L_0x0011:
            long r5 = r0.size
            okio.Buffer r1 = (okio.Buffer) r1
            long r7 = r1.size
            int r3 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r3 == 0) goto L_0x001c
            return r4
        L_0x001c:
            r7 = 0
            int r3 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r3 != 0) goto L_0x0023
            return r2
        L_0x0023:
            okio.Segment r3 = r0.head
            if (r3 != 0) goto L_0x002a
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x002a:
            okio.Segment r1 = r1.head
            if (r1 != 0) goto L_0x0031
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x0031:
            int r5 = r3.pos
            int r6 = r1.pos
            r9 = r7
        L_0x0036:
            long r11 = r0.size
            int r13 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r13 >= 0) goto L_0x007f
            int r11 = r3.limit
            int r11 = r11 - r5
            int r12 = r1.limit
            int r12 = r12 - r6
            int r11 = java.lang.Math.min(r11, r12)
            long r11 = (long) r11
            r13 = r7
        L_0x0048:
            int r15 = (r13 > r11 ? 1 : (r13 == r11 ? 0 : -1))
            if (r15 >= 0) goto L_0x0063
            byte[] r15 = r3.data
            int r16 = r5 + 1
            byte r5 = r15[r5]
            byte[] r15 = r1.data
            int r17 = r6 + 1
            byte r6 = r15[r6]
            if (r5 == r6) goto L_0x005b
            return r4
        L_0x005b:
            r5 = 1
            long r13 = r13 + r5
            r5 = r16
            r6 = r17
            goto L_0x0048
        L_0x0063:
            int r13 = r3.limit
            if (r5 != r13) goto L_0x0070
            okio.Segment r3 = r3.next
            if (r3 != 0) goto L_0x006e
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x006e:
            int r5 = r3.pos
        L_0x0070:
            int r13 = r1.limit
            if (r6 != r13) goto L_0x007d
            okio.Segment r1 = r1.next
            if (r1 != 0) goto L_0x007b
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x007b:
            int r6 = r1.pos
        L_0x007d:
            long r9 = r9 + r11
            goto L_0x0036
        L_0x007f:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.Buffer.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        Segment segment = this.head;
        if (segment == null) {
            return 0;
        }
        int i = 1;
        do {
            int i2 = segment.limit;
            for (int i3 = segment.pos; i3 < i2; i3++) {
                i = (i * 31) + segment.data[i3];
            }
            segment = segment.next;
            if (segment == null) {
                Intrinsics.throwNpe();
            }
        } while (segment != this.head);
        return i;
    }

    public String toString() {
        return snapshot().toString();
    }

    public Buffer clone() {
        Buffer buffer = new Buffer();
        if (this.size == 0) {
            return buffer;
        }
        Segment segment = this.head;
        if (segment == null) {
            Intrinsics.throwNpe();
        }
        Segment sharedCopy = segment.sharedCopy();
        buffer.head = sharedCopy;
        if (sharedCopy == null) {
            Intrinsics.throwNpe();
        }
        sharedCopy.prev = buffer.head;
        Segment segment2 = buffer.head;
        if (segment2 == null) {
            Intrinsics.throwNpe();
        }
        Segment segment3 = buffer.head;
        if (segment3 == null) {
            Intrinsics.throwNpe();
        }
        segment2.next = segment3.prev;
        Segment segment4 = this.head;
        if (segment4 == null) {
            Intrinsics.throwNpe();
        }
        for (Segment segment5 = segment4.next; segment5 != this.head; segment5 = segment5.next) {
            Segment segment6 = buffer.head;
            if (segment6 == null) {
                Intrinsics.throwNpe();
            }
            Segment segment7 = segment6.prev;
            if (segment7 == null) {
                Intrinsics.throwNpe();
            }
            if (segment5 == null) {
                Intrinsics.throwNpe();
            }
            segment7.push(segment5.sharedCopy());
        }
        buffer.size = this.size;
        return buffer;
    }

    public final ByteString snapshot() {
        long j = this.size;
        if (j <= ((long) Integer.MAX_VALUE)) {
            return snapshot((int) j);
        }
        throw new IllegalStateException(("size > Integer.MAX_VALUE: " + this.size).toString());
    }

    public final ByteString snapshot(int i) {
        return i == 0 ? ByteString.EMPTY : SegmentedByteString.Companion.of(this, i);
    }

    public static /* bridge */ /* synthetic */ UnsafeCursor readUnsafe$default(Buffer buffer, UnsafeCursor unsafeCursor, int i, Object obj) {
        if ((i & 1) != 0) {
            unsafeCursor = new UnsafeCursor();
        }
        return buffer.readUnsafe(unsafeCursor);
    }

    public final UnsafeCursor readUnsafe(UnsafeCursor unsafeCursor) {
        Intrinsics.checkParameterIsNotNull(unsafeCursor, "unsafeCursor");
        if (unsafeCursor.buffer == null) {
            Buffer buffer = this;
            unsafeCursor.buffer = this;
            unsafeCursor.readWrite = false;
            return unsafeCursor;
        }
        throw new IllegalStateException("already attached to a buffer".toString());
    }

    public static /* bridge */ /* synthetic */ UnsafeCursor readAndWriteUnsafe$default(Buffer buffer, UnsafeCursor unsafeCursor, int i, Object obj) {
        if ((i & 1) != 0) {
            unsafeCursor = new UnsafeCursor();
        }
        return buffer.readAndWriteUnsafe(unsafeCursor);
    }

    public final UnsafeCursor readAndWriteUnsafe(UnsafeCursor unsafeCursor) {
        Intrinsics.checkParameterIsNotNull(unsafeCursor, "unsafeCursor");
        if (unsafeCursor.buffer == null) {
            Buffer buffer = this;
            unsafeCursor.buffer = this;
            unsafeCursor.readWrite = true;
            return unsafeCursor;
        }
        throw new IllegalStateException("already attached to a buffer".toString());
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to operator function", replaceWith = @ReplaceWith(expression = "this[index]", imports = {}))
    /* renamed from: -deprecated_getByte  reason: not valid java name */
    public final byte m1281deprecated_getByte(long j) {
        return getByte(j);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "size", imports = {}))
    /* renamed from: -deprecated_size  reason: not valid java name */
    public final long m1282deprecated_size() {
        return this.size;
    }

    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\u000e\u0010\u0012\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\bJ\u0006\u0010\u0014\u001a\u00020\bJ\u000e\u0010\u0015\u001a\u00020\n2\u0006\u0010\u0016\u001a\u00020\nJ\u000e\u0010\u0017\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nR\u0014\u0010\u0003\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u0004\u0018\u00010\u00068\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0007\u001a\u00020\b8\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\t\u001a\u00020\n8\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u000b\u001a\u00020\f8\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u000f\u001a\u00020\b8\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lokio/Buffer$UnsafeCursor;", "Ljava/io/Closeable;", "()V", "buffer", "Lokio/Buffer;", "data", "", "end", "", "offset", "", "readWrite", "", "segment", "Lokio/Segment;", "start", "close", "", "expandBuffer", "minByteCount", "next", "resizeBuffer", "newSize", "seek", "jvm"}, k = 1, mv = {1, 1, 11})
    /* compiled from: Buffer.kt */
    public static final class UnsafeCursor implements Closeable {
        public Buffer buffer;
        public byte[] data;
        public int end = -1;
        public long offset = -1;
        public boolean readWrite;
        private Segment segment;
        public int start = -1;

        public final int next() {
            long j = this.offset;
            Buffer buffer2 = this.buffer;
            if (buffer2 == null) {
                Intrinsics.throwNpe();
            }
            if (j != buffer2.size()) {
                long j2 = this.offset;
                return seek(j2 == -1 ? 0 : j2 + ((long) (this.end - this.start)));
            }
            throw new IllegalStateException("no more bytes".toString());
        }

        public final int seek(long j) {
            Buffer buffer2 = this.buffer;
            if (buffer2 == null) {
                throw new IllegalStateException("not attached to a buffer".toString());
            } else if (j < ((long) -1) || j > buffer2.size()) {
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String format = String.format("offset=%s > size=%s", Arrays.copyOf(new Object[]{Long.valueOf(j), Long.valueOf(buffer2.size())}, 2));
                Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
                throw new ArrayIndexOutOfBoundsException(format);
            } else if (j == -1 || j == buffer2.size()) {
                Segment segment2 = null;
                this.segment = null;
                this.offset = j;
                byte[] bArr = null;
                this.data = null;
                this.start = -1;
                this.end = -1;
                return -1;
            } else {
                long size = buffer2.size();
                Segment segment3 = buffer2.head;
                Segment segment4 = buffer2.head;
                Segment segment5 = this.segment;
                long j2 = 0;
                if (segment5 != null) {
                    long j3 = this.offset;
                    int i = this.start;
                    if (segment5 == null) {
                        Intrinsics.throwNpe();
                    }
                    long j4 = j3 - ((long) (i - segment5.pos));
                    if (j4 > j) {
                        segment4 = this.segment;
                        size = j4;
                    } else {
                        segment3 = this.segment;
                        j2 = j4;
                    }
                }
                if (size - j > j - j2) {
                    while (true) {
                        if (segment3 == null) {
                            Intrinsics.throwNpe();
                        }
                        if (j < ((long) (segment3.limit - segment3.pos)) + j2) {
                            break;
                        }
                        j2 += (long) (segment3.limit - segment3.pos);
                        segment3 = segment3.next;
                    }
                } else {
                    while (size > j) {
                        if (segment4 == null) {
                            Intrinsics.throwNpe();
                        }
                        segment4 = segment4.prev;
                        if (segment4 == null) {
                            Intrinsics.throwNpe();
                        }
                        size -= (long) (segment4.limit - segment4.pos);
                    }
                    j2 = size;
                    segment3 = segment4;
                }
                if (this.readWrite) {
                    if (segment3 == null) {
                        Intrinsics.throwNpe();
                    }
                    if (segment3.shared) {
                        Segment unsharedCopy = segment3.unsharedCopy();
                        if (buffer2.head == segment3) {
                            buffer2.head = unsharedCopy;
                        }
                        segment3 = segment3.push(unsharedCopy);
                        Segment segment6 = segment3.prev;
                        if (segment6 == null) {
                            Intrinsics.throwNpe();
                        }
                        segment6.pop();
                    }
                }
                this.segment = segment3;
                this.offset = j;
                if (segment3 == null) {
                    Intrinsics.throwNpe();
                }
                this.data = segment3.data;
                this.start = segment3.pos + ((int) (j - j2));
                int i2 = segment3.limit;
                this.end = i2;
                return i2 - this.start;
            }
        }

        public final long resizeBuffer(long j) {
            long j2 = j;
            Buffer buffer2 = this.buffer;
            if (buffer2 == null) {
                throw new IllegalStateException("not attached to a buffer".toString());
            } else if (this.readWrite) {
                long size = buffer2.size();
                if (j2 <= size) {
                    if (j2 >= 0) {
                        long j3 = size - j2;
                        while (true) {
                            if (j3 > 0) {
                                Segment segment2 = buffer2.head;
                                if (segment2 == null) {
                                    Intrinsics.throwNpe();
                                }
                                Segment segment3 = segment2.prev;
                                if (segment3 == null) {
                                    Intrinsics.throwNpe();
                                }
                                long j4 = (long) (segment3.limit - segment3.pos);
                                if (j4 > j3) {
                                    segment3.limit -= (int) j3;
                                    break;
                                }
                                buffer2.head = segment3.pop();
                                SegmentPool.recycle(segment3);
                                j3 -= j4;
                            } else {
                                break;
                            }
                        }
                        Segment segment4 = null;
                        this.segment = null;
                        this.offset = j2;
                        byte[] bArr = null;
                        this.data = null;
                        this.start = -1;
                        this.end = -1;
                    } else {
                        throw new IllegalArgumentException(("newSize < 0: " + j2).toString());
                    }
                } else if (j2 > size) {
                    long j5 = j2 - size;
                    boolean z = true;
                    for (long j6 = 0; j5 > j6; j6 = 0) {
                        Segment writableSegment$jvm = buffer2.writableSegment$jvm(1);
                        int min = (int) Math.min(j5, (long) (8192 - writableSegment$jvm.limit));
                        writableSegment$jvm.limit += min;
                        j5 -= (long) min;
                        if (z) {
                            this.segment = writableSegment$jvm;
                            this.offset = size;
                            this.data = writableSegment$jvm.data;
                            this.start = writableSegment$jvm.limit - min;
                            this.end = writableSegment$jvm.limit;
                            z = false;
                        }
                    }
                }
                buffer2.setSize$jvm(j2);
                return size;
            } else {
                throw new IllegalStateException("resizeBuffer() only permitted for read/write buffers".toString());
            }
        }

        public final long expandBuffer(int i) {
            if (i <= 0) {
                throw new IllegalArgumentException(("minByteCount <= 0: " + i).toString());
            } else if (i <= 8192) {
                Buffer buffer2 = this.buffer;
                if (buffer2 == null) {
                    throw new IllegalStateException("not attached to a buffer".toString());
                } else if (this.readWrite) {
                    long size = buffer2.size();
                    Segment writableSegment$jvm = buffer2.writableSegment$jvm(i);
                    int i2 = 8192 - writableSegment$jvm.limit;
                    writableSegment$jvm.limit = 8192;
                    long j = (long) i2;
                    buffer2.setSize$jvm(size + j);
                    this.segment = writableSegment$jvm;
                    this.offset = size;
                    this.data = writableSegment$jvm.data;
                    this.start = 8192 - i2;
                    this.end = 8192;
                    return j;
                } else {
                    throw new IllegalStateException("expandBuffer() only permitted for read/write buffers".toString());
                }
            } else {
                throw new IllegalArgumentException(("minByteCount > Segment.SIZE: " + i).toString());
            }
        }

        public void close() {
            if (this.buffer != null) {
                Buffer buffer2 = null;
                this.buffer = null;
                Segment segment2 = null;
                this.segment = null;
                this.offset = -1;
                byte[] bArr = null;
                this.data = null;
                this.start = -1;
                this.end = -1;
                return;
            }
            throw new IllegalStateException("not attached to a buffer".toString());
        }
    }

    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lokio/Buffer$Companion;", "", "()V", "DIGITS", "", "jvm"}, k = 1, mv = {1, 1, 11})
    /* compiled from: Buffer.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    static {
        byte[] bytes = "0123456789abcdef".getBytes(Charsets.UTF_8);
        Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
        DIGITS = bytes;
    }

    public long indexOfElement(ByteString byteString, long j) {
        long j2;
        int i;
        int i2;
        int i3;
        Intrinsics.checkParameterIsNotNull(byteString, "targetBytes");
        long j3 = 0;
        if (j >= 0) {
            Segment segment = this.head;
            if (segment != null) {
                if (size() - j < j) {
                    j2 = size();
                    while (j2 > j) {
                        segment = segment.prev;
                        if (segment == null) {
                            Intrinsics.throwNpe();
                        }
                        j2 -= (long) (segment.limit - segment.pos);
                    }
                    if (segment != null) {
                        if (byteString.size() == 2) {
                            byte b = byteString.getByte(0);
                            byte b2 = byteString.getByte(1);
                            while (j2 < this.size) {
                                byte[] bArr = segment.data;
                                i2 = (int) ((((long) segment.pos) + j) - j2);
                                int i4 = segment.limit;
                                while (i2 < i4) {
                                    byte b3 = bArr[i2];
                                    if (!(b3 == b || b3 == b2)) {
                                        i2++;
                                    }
                                }
                                j2 += (long) (segment.limit - segment.pos);
                                segment = segment.next;
                                if (segment == null) {
                                    Intrinsics.throwNpe();
                                }
                                j = j2;
                            }
                        } else {
                            byte[] internalArray$jvm = byteString.internalArray$jvm();
                            while (j2 < this.size) {
                                byte[] bArr2 = segment.data;
                                i = (int) ((((long) segment.pos) + j) - j2);
                                int i5 = segment.limit;
                                while (i < i5) {
                                    byte b4 = bArr2[i];
                                    for (byte b5 : internalArray$jvm) {
                                        if (b4 == b5) {
                                            i3 = segment.pos;
                                            return ((long) (i2 - i3)) + j2;
                                        }
                                    }
                                    i++;
                                }
                                j2 += (long) (segment.limit - segment.pos);
                                segment = segment.next;
                                if (segment == null) {
                                    Intrinsics.throwNpe();
                                }
                                j = j2;
                            }
                        }
                    }
                    return -1;
                }
                while (true) {
                    long j4 = ((long) (segment.limit - segment.pos)) + j3;
                    if (j4 > j) {
                        break;
                    }
                    segment = segment.next;
                    if (segment == null) {
                        Intrinsics.throwNpe();
                    }
                    j3 = j4;
                }
                if (segment != null) {
                    if (byteString.size() == 2) {
                        byte b6 = byteString.getByte(0);
                        byte b7 = byteString.getByte(1);
                        while (j2 < this.size) {
                            byte[] bArr3 = segment.data;
                            int i6 = (int) ((((long) segment.pos) + j) - j2);
                            int i7 = segment.limit;
                            while (i2 < i7) {
                                byte b8 = bArr3[i2];
                                if (!(b8 == b6 || b8 == b7)) {
                                    i6 = i2 + 1;
                                }
                            }
                            j3 = j2 + ((long) (segment.limit - segment.pos));
                            segment = segment.next;
                            if (segment == null) {
                                Intrinsics.throwNpe();
                            }
                            j = j3;
                        }
                    } else {
                        byte[] internalArray$jvm2 = byteString.internalArray$jvm();
                        while (j2 < this.size) {
                            byte[] bArr4 = segment.data;
                            int i8 = (int) ((((long) segment.pos) + j) - j2);
                            int i9 = segment.limit;
                            while (i < i9) {
                                byte b9 = bArr4[i];
                                for (byte b10 : internalArray$jvm2) {
                                    if (b9 == b10) {
                                        i3 = segment.pos;
                                        return ((long) (i2 - i3)) + j2;
                                    }
                                }
                                i8 = i + 1;
                            }
                            j3 = j2 + ((long) (segment.limit - segment.pos));
                            segment = segment.next;
                            if (segment == null) {
                                Intrinsics.throwNpe();
                            }
                            j = j3;
                        }
                    }
                }
                return -1;
                i3 = segment.pos;
                return ((long) (i2 - i3)) + j2;
            }
            Segment segment2 = null;
            return -1;
        }
        throw new IllegalArgumentException(("fromIndex < 0: " + j).toString());
    }
}
