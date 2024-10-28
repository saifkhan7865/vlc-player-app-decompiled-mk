package io.netty.handler.codec.http2;

import android.support.v4.media.session.PlaybackStateCompat;
import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http2.HpackUtil;
import io.netty.handler.codec.http2.Http2HeadersEncoder;
import io.netty.util.AsciiString;
import io.netty.util.CharsetUtil;
import io.netty.util.internal.MathUtil;
import java.util.Map;

final class HpackEncoder {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static final int HUFF_CODE_THRESHOLD = 512;
    static final int NOT_FOUND = -1;
    private final byte hashMask;
    private final NameValueEntry head;
    private final HpackHuffmanEncoder hpackHuffmanEncoder;
    private final int huffCodeThreshold;
    private final boolean ignoreMaxHeaderListSize;
    private NameValueEntry latest;
    private long maxHeaderListSize;
    private long maxHeaderTableSize;
    private final NameEntry[] nameEntries;
    private final NameValueEntry[] nameValueEntries;
    private long size;

    private static int hash(int i, int i2) {
        return (i * 31) + i2;
    }

    HpackEncoder() {
        this(false);
    }

    HpackEncoder(boolean z) {
        this(z, 64, 512);
    }

    HpackEncoder(boolean z, int i, int i2) {
        NameValueEntry nameValueEntry = new NameValueEntry(-1, AsciiString.EMPTY_STRING, AsciiString.EMPTY_STRING, Integer.MAX_VALUE, (NameValueEntry) null);
        this.head = nameValueEntry;
        this.latest = nameValueEntry;
        this.hpackHuffmanEncoder = new HpackHuffmanEncoder();
        this.ignoreMaxHeaderListSize = z;
        this.maxHeaderTableSize = PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
        this.maxHeaderListSize = 4294967295L;
        NameEntry[] nameEntryArr = new NameEntry[MathUtil.findNextPositivePowerOfTwo(Math.max(2, Math.min(i, 128)))];
        this.nameEntries = nameEntryArr;
        this.nameValueEntries = new NameValueEntry[nameEntryArr.length];
        this.hashMask = (byte) (nameEntryArr.length - 1);
        this.huffCodeThreshold = i2;
    }

    public void encodeHeaders(int i, ByteBuf byteBuf, Http2Headers http2Headers, Http2HeadersEncoder.SensitivityDetector sensitivityDetector) throws Http2Exception {
        if (this.ignoreMaxHeaderListSize) {
            encodeHeadersIgnoreMaxHeaderListSize(byteBuf, http2Headers, sensitivityDetector);
        } else {
            encodeHeadersEnforceMaxHeaderListSize(i, byteBuf, http2Headers, sensitivityDetector);
        }
    }

    private void encodeHeadersEnforceMaxHeaderListSize(int i, ByteBuf byteBuf, Http2Headers http2Headers, Http2HeadersEncoder.SensitivityDetector sensitivityDetector) throws Http2Exception {
        long j = 0;
        for (Map.Entry next : http2Headers) {
            j += HpackHeaderField.sizeOf((CharSequence) next.getKey(), (CharSequence) next.getValue());
            long j2 = this.maxHeaderListSize;
            if (j > j2) {
                Http2CodecUtil.headerListSizeExceeded(i, j2, false);
            }
        }
        encodeHeadersIgnoreMaxHeaderListSize(byteBuf, http2Headers, sensitivityDetector);
    }

    private void encodeHeadersIgnoreMaxHeaderListSize(ByteBuf byteBuf, Http2Headers http2Headers, Http2HeadersEncoder.SensitivityDetector sensitivityDetector) {
        for (Map.Entry next : http2Headers) {
            CharSequence charSequence = (CharSequence) next.getKey();
            CharSequence charSequence2 = (CharSequence) next.getValue();
            encodeHeader(byteBuf, charSequence, charSequence2, sensitivityDetector.isSensitive(charSequence, charSequence2), HpackHeaderField.sizeOf(charSequence, charSequence2));
        }
    }

    private void encodeHeader(ByteBuf byteBuf, CharSequence charSequence, CharSequence charSequence2, boolean z, long j) {
        ByteBuf byteBuf2 = byteBuf;
        CharSequence charSequence3 = charSequence;
        long j2 = j;
        if (z) {
            encodeLiteral(byteBuf, charSequence, charSequence2, HpackUtil.IndexType.NEVER, getNameIndex(charSequence));
            return;
        }
        long j3 = this.maxHeaderTableSize;
        if (j3 == 0) {
            int indexInsensitive = HpackStaticTable.getIndexInsensitive(charSequence, charSequence2);
            if (indexInsensitive == -1) {
                encodeLiteral(byteBuf, charSequence, charSequence2, HpackUtil.IndexType.NONE, HpackStaticTable.getIndex(charSequence));
                return;
            }
            encodeInteger(byteBuf, 128, 7, indexInsensitive);
        } else if (j2 > j3) {
            encodeLiteral(byteBuf, charSequence, charSequence2, HpackUtil.IndexType.NONE, getNameIndex(charSequence));
        } else {
            int hashCode = AsciiString.hashCode(charSequence);
            int hashCode2 = AsciiString.hashCode(charSequence2);
            NameValueEntry entryInsensitive = getEntryInsensitive(charSequence, hashCode, charSequence2, hashCode2);
            if (entryInsensitive != null) {
                encodeInteger(byteBuf, 128, 7, getIndexPlusOffset(entryInsensitive.counter));
                return;
            }
            int indexInsensitive2 = HpackStaticTable.getIndexInsensitive(charSequence, charSequence2);
            if (indexInsensitive2 != -1) {
                encodeInteger(byteBuf, 128, 7, indexInsensitive2);
                return;
            }
            ensureCapacity(j2);
            encodeAndAddEntries(byteBuf, charSequence, hashCode, charSequence2, hashCode2);
            this.size += j2;
        }
    }

    private void encodeAndAddEntries(ByteBuf byteBuf, CharSequence charSequence, int i, CharSequence charSequence2, int i2) {
        CharSequence charSequence3 = charSequence;
        int i3 = i;
        int index = HpackStaticTable.getIndex(charSequence);
        int latestCounter = latestCounter() - 1;
        if (index == -1) {
            NameEntry entry = getEntry(charSequence, i);
            if (entry == null) {
                encodeLiteral(byteBuf, charSequence, charSequence2, HpackUtil.IndexType.INCREMENTAL, -1);
                addNameEntry(charSequence, i, latestCounter);
                addNameValueEntry(charSequence, charSequence2, i, i2, latestCounter);
                return;
            }
            encodeLiteral(byteBuf, charSequence, charSequence2, HpackUtil.IndexType.INCREMENTAL, getIndexPlusOffset(entry.counter));
            addNameValueEntry(entry.name, charSequence2, i, i2, latestCounter);
            entry.counter = latestCounter;
            return;
        }
        encodeLiteral(byteBuf, charSequence, charSequence2, HpackUtil.IndexType.INCREMENTAL, index);
        addNameValueEntry(HpackStaticTable.getEntry(index).name, charSequence2, i, i2, latestCounter);
    }

    public void setMaxHeaderTableSize(ByteBuf byteBuf, long j) throws Http2Exception {
        if (j < 0 || j > 4294967295L) {
            throw Http2Exception.connectionError(Http2Error.PROTOCOL_ERROR, "Header Table Size must be >= %d and <= %d but was %d", 0L, 4294967295L, Long.valueOf(j));
        } else if (this.maxHeaderTableSize != j) {
            this.maxHeaderTableSize = j;
            ensureCapacity(0);
            encodeInteger(byteBuf, 32, 5, j);
        }
    }

    public long getMaxHeaderTableSize() {
        return this.maxHeaderTableSize;
    }

    public void setMaxHeaderListSize(long j) throws Http2Exception {
        if (j < 0 || j > 4294967295L) {
            throw Http2Exception.connectionError(Http2Error.PROTOCOL_ERROR, "Header List Size must be >= %d and <= %d but was %d", 0L, 4294967295L, Long.valueOf(j));
        }
        this.maxHeaderListSize = j;
    }

    public long getMaxHeaderListSize() {
        return this.maxHeaderListSize;
    }

    private static void encodeInteger(ByteBuf byteBuf, int i, int i2, int i3) {
        encodeInteger(byteBuf, i, i2, (long) i3);
    }

    private static void encodeInteger(ByteBuf byteBuf, int i, int i2, long j) {
        int i3 = 255 >>> (8 - i2);
        long j2 = (long) i3;
        if (j < j2) {
            byteBuf.writeByte((int) (((long) i) | j));
            return;
        }
        byteBuf.writeByte(i | i3);
        long j3 = j - j2;
        while ((-128 & j3) != 0) {
            byteBuf.writeByte((int) ((127 & j3) | 128));
            j3 >>>= 7;
        }
        byteBuf.writeByte((int) j3);
    }

    private void encodeStringLiteral(ByteBuf byteBuf, CharSequence charSequence) {
        int encodedLength;
        if (charSequence.length() < this.huffCodeThreshold || (encodedLength = this.hpackHuffmanEncoder.getEncodedLength(charSequence)) >= charSequence.length()) {
            encodeInteger(byteBuf, 0, 7, charSequence.length());
            if (charSequence instanceof AsciiString) {
                AsciiString asciiString = (AsciiString) charSequence;
                byteBuf.writeBytes(asciiString.array(), asciiString.arrayOffset(), asciiString.length());
                return;
            }
            byteBuf.writeCharSequence(charSequence, CharsetUtil.ISO_8859_1);
            return;
        }
        encodeInteger(byteBuf, 128, 7, encodedLength);
        this.hpackHuffmanEncoder.encode(byteBuf, charSequence);
    }

    /* renamed from: io.netty.handler.codec.http2.HpackEncoder$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$io$netty$handler$codec$http2$HpackUtil$IndexType;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                io.netty.handler.codec.http2.HpackUtil$IndexType[] r0 = io.netty.handler.codec.http2.HpackUtil.IndexType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$io$netty$handler$codec$http2$HpackUtil$IndexType = r0
                io.netty.handler.codec.http2.HpackUtil$IndexType r1 = io.netty.handler.codec.http2.HpackUtil.IndexType.INCREMENTAL     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$io$netty$handler$codec$http2$HpackUtil$IndexType     // Catch:{ NoSuchFieldError -> 0x001d }
                io.netty.handler.codec.http2.HpackUtil$IndexType r1 = io.netty.handler.codec.http2.HpackUtil.IndexType.NONE     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$io$netty$handler$codec$http2$HpackUtil$IndexType     // Catch:{ NoSuchFieldError -> 0x0028 }
                io.netty.handler.codec.http2.HpackUtil$IndexType r1 = io.netty.handler.codec.http2.HpackUtil.IndexType.NEVER     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.netty.handler.codec.http2.HpackEncoder.AnonymousClass1.<clinit>():void");
        }
    }

    private void encodeLiteral(ByteBuf byteBuf, CharSequence charSequence, CharSequence charSequence2, HpackUtil.IndexType indexType, int i) {
        boolean z = i != -1;
        int i2 = AnonymousClass1.$SwitchMap$io$netty$handler$codec$http2$HpackUtil$IndexType[indexType.ordinal()];
        if (i2 == 1) {
            if (!z) {
                i = 0;
            }
            encodeInteger(byteBuf, 64, 6, i);
        } else if (i2 == 2) {
            if (!z) {
                i = 0;
            }
            encodeInteger(byteBuf, 0, 4, i);
        } else if (i2 == 3) {
            if (!z) {
                i = 0;
            }
            encodeInteger(byteBuf, 16, 4, i);
        } else {
            throw new Error("should not reach here");
        }
        if (!z) {
            encodeStringLiteral(byteBuf, charSequence);
        }
        encodeStringLiteral(byteBuf, charSequence2);
    }

    private int getNameIndex(CharSequence charSequence) {
        int index = HpackStaticTable.getIndex(charSequence);
        if (index != -1) {
            return index;
        }
        NameEntry entry = getEntry(charSequence, AsciiString.hashCode(charSequence));
        if (entry == null) {
            return -1;
        }
        return getIndexPlusOffset(entry.counter);
    }

    private void ensureCapacity(long j) {
        while (this.maxHeaderTableSize - this.size < j) {
            remove();
        }
    }

    /* access modifiers changed from: package-private */
    public int length() {
        if (isEmpty()) {
            return 0;
        }
        return getIndex(this.head.after.counter);
    }

    /* access modifiers changed from: package-private */
    public long size() {
        return this.size;
    }

    /* access modifiers changed from: package-private */
    public HpackHeaderField getHeaderField(int i) {
        NameValueEntry nameValueEntry = this.head;
        while (true) {
            int i2 = i + 1;
            if (i >= length()) {
                return nameValueEntry;
            }
            nameValueEntry = nameValueEntry.after;
            i = i2;
        }
    }

    private NameValueEntry getEntryInsensitive(CharSequence charSequence, int i, CharSequence charSequence2, int i2) {
        int hash = hash(i, i2);
        for (NameValueEntry nameValueEntry = this.nameValueEntries[bucket(hash)]; nameValueEntry != null; nameValueEntry = nameValueEntry.next) {
            if (nameValueEntry.hash == hash && HpackUtil.equalsVariableTime(charSequence2, nameValueEntry.value) && HpackUtil.equalsVariableTime(charSequence, nameValueEntry.name)) {
                return nameValueEntry;
            }
        }
        return null;
    }

    private NameEntry getEntry(CharSequence charSequence, int i) {
        for (NameEntry nameEntry = this.nameEntries[bucket(i)]; nameEntry != null; nameEntry = nameEntry.next) {
            if (nameEntry.hash == i && HpackUtil.equalsConstantTime(charSequence, nameEntry.name) != 0) {
                return nameEntry;
            }
        }
        return null;
    }

    private int getIndexPlusOffset(int i) {
        return getIndex(i) + HpackStaticTable.length;
    }

    private int getIndex(int i) {
        return (i - latestCounter()) + 1;
    }

    private int latestCounter() {
        return this.latest.counter;
    }

    private void addNameEntry(CharSequence charSequence, int i, int i2) {
        int bucket = bucket(i);
        NameEntry[] nameEntryArr = this.nameEntries;
        nameEntryArr[bucket] = new NameEntry(i, charSequence, i2, nameEntryArr[bucket]);
    }

    private void addNameValueEntry(CharSequence charSequence, CharSequence charSequence2, int i, int i2, int i3) {
        int hash = hash(i, i2);
        int bucket = bucket(hash);
        NameValueEntry nameValueEntry = new NameValueEntry(hash, charSequence, charSequence2, i3, this.nameValueEntries[bucket]);
        this.nameValueEntries[bucket] = nameValueEntry;
        this.latest.after = nameValueEntry;
        this.latest = nameValueEntry;
    }

    private void remove() {
        NameValueEntry nameValueEntry = this.head.after;
        removeNameValueEntry(nameValueEntry);
        removeNameEntryMatchingCounter(nameValueEntry.name, nameValueEntry.counter);
        this.head.after = nameValueEntry.after;
        nameValueEntry.unlink();
        this.size -= (long) nameValueEntry.size();
        if (isEmpty()) {
            this.latest = this.head;
        }
    }

    private boolean isEmpty() {
        return this.size == 0;
    }

    private void removeNameValueEntry(NameValueEntry nameValueEntry) {
        int bucket = bucket(nameValueEntry.hash);
        NameValueEntry[] nameValueEntryArr = this.nameValueEntries;
        NameValueEntry nameValueEntry2 = nameValueEntryArr[bucket];
        if (nameValueEntry2 == nameValueEntry) {
            nameValueEntryArr[bucket] = nameValueEntry.next;
            return;
        }
        while (nameValueEntry2.next != nameValueEntry) {
            nameValueEntry2 = nameValueEntry2.next;
        }
        nameValueEntry2.next = nameValueEntry.next;
    }

    private void removeNameEntryMatchingCounter(CharSequence charSequence, int i) {
        int bucket = bucket(AsciiString.hashCode(charSequence));
        NameEntry nameEntry = this.nameEntries[bucket];
        if (nameEntry != null) {
            if (i == nameEntry.counter) {
                this.nameEntries[bucket] = nameEntry.next;
                nameEntry.unlink();
                return;
            }
            NameEntry nameEntry2 = nameEntry.next;
            while (true) {
                NameEntry nameEntry3 = nameEntry;
                nameEntry = nameEntry2;
                NameEntry nameEntry4 = nameEntry3;
                if (nameEntry == null) {
                    return;
                }
                if (i == nameEntry.counter) {
                    nameEntry4.next = nameEntry.next;
                    nameEntry.unlink();
                    return;
                }
                nameEntry2 = nameEntry.next;
            }
        }
    }

    private int bucket(int i) {
        return i & this.hashMask;
    }

    private static final class NameEntry {
        int counter;
        final int hash;
        final CharSequence name;
        NameEntry next;

        NameEntry(int i, CharSequence charSequence, int i2, NameEntry nameEntry) {
            this.hash = i;
            this.name = charSequence;
            this.counter = i2;
            this.next = nameEntry;
        }

        /* access modifiers changed from: package-private */
        public void unlink() {
            this.next = null;
        }
    }

    private static final class NameValueEntry extends HpackHeaderField {
        NameValueEntry after;
        final int counter;
        final int hash;
        NameValueEntry next;

        NameValueEntry(int i, CharSequence charSequence, CharSequence charSequence2, int i2, NameValueEntry nameValueEntry) {
            super(charSequence, charSequence2);
            this.next = nameValueEntry;
            this.hash = i;
            this.counter = i2;
        }

        /* access modifiers changed from: package-private */
        public void unlink() {
            this.after = null;
            this.next = null;
        }
    }
}
