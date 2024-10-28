package io.ktor.http.cio.internals;

import io.ktor.utils.io.pool.ObjectPool;
import java.util.ArrayList;
import java.util.List;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0019\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\f\n\u0002\b\t\n\u0002\u0010\u0000\n\u0002\b\n\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0001\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u00012\u00060\u0002j\u0002`\u0003:\u00013B\u0015\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\u0014\u0010\u0017\u001a\u00060\u0002j\u0002`\u00032\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\u0016\u0010\u0017\u001a\u00060\u0002j\u0002`\u00032\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001H\u0016J&\u0010\u0017\u001a\u00060\u0002j\u0002`\u00032\b\u0010\u0018\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u001a\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\fH\u0016J\b\u0010\u001c\u001a\u00020\u0006H\u0002J\u0010\u0010\u001d\u001a\u00020\u00062\u0006\u0010\u001e\u001a\u00020\fH\u0002J\u0018\u0010\u001f\u001a\u00020\u00012\u0006\u0010\u001a\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\fH\u0002J\b\u0010 \u001a\u00020\fH\u0002J\u0013\u0010!\u001a\u00020\u00132\b\u0010\"\u001a\u0004\u0018\u00010#H\u0002J\u0011\u0010$\u001a\u00020\u00192\u0006\u0010\u001e\u001a\u00020\fH\u0002J\u0010\u0010%\u001a\u00020\u00192\u0006\u0010\u001e\u001a\u00020\fH\u0002J\b\u0010&\u001a\u00020\fH\u0016J\u0018\u0010'\u001a\u00020\f2\u0006\u0010(\u001a\u00020\f2\u0006\u0010)\u001a\u00020\fH\u0002J\b\u0010*\u001a\u00020\u0006H\u0002J(\u0010+\u001a\u00020\u00132\u0006\u0010(\u001a\u00020\f2\u0006\u0010\"\u001a\u00020\u00012\u0006\u0010,\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0002J\u0006\u0010-\u001a\u00020.J\u0018\u0010/\u001a\u00020\u00012\u0006\u0010\u001a\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\fH\u0016J\u0010\u00100\u001a\u0002012\u0006\u0010\u001e\u001a\u00020\fH\u0002J\b\u00102\u001a\u00020\u0016H\u0016R\u0016\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\tX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\f@RX\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\u0013X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u000e¢\u0006\u0002\n\u0000¨\u00064"}, d2 = {"Lio/ktor/http/cio/internals/CharArrayBuilder;", "", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "pool", "Lio/ktor/utils/io/pool/ObjectPool;", "", "(Lio/ktor/utils/io/pool/ObjectPool;)V", "buffers", "", "current", "<set-?>", "", "length", "getLength", "()I", "getPool", "()Lio/ktor/utils/io/pool/ObjectPool;", "released", "", "remaining", "stringified", "", "append", "value", "", "startIndex", "endIndex", "appendNewArray", "bufferForIndex", "index", "copy", "currentPosition", "equals", "other", "", "get", "getImpl", "hashCode", "hashCodeImpl", "start", "end", "nonFullBuffer", "rangeEqualsImpl", "otherStart", "release", "", "subSequence", "throwSingleBuffer", "", "toString", "SubSequenceImpl", "ktor-http-cio"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: CharArrayBuilder.kt */
public final class CharArrayBuilder implements CharSequence, Appendable {
    private List<char[]> buffers;
    private char[] current;
    private int length;
    private final ObjectPool<char[]> pool;
    private boolean released;
    private int remaining;
    private String stringified;

    public CharArrayBuilder() {
        this((ObjectPool) null, 1, (DefaultConstructorMarker) null);
    }

    public CharArrayBuilder(ObjectPool<char[]> objectPool) {
        Intrinsics.checkNotNullParameter(objectPool, "pool");
        this.pool = objectPool;
    }

    public final /* bridge */ char charAt(int i) {
        return get(i);
    }

    public final /* bridge */ int length() {
        return getLength();
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ CharArrayBuilder(ObjectPool<char[]> objectPool, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? CharArrayPoolKt.getCharArrayPool() : objectPool);
    }

    public final ObjectPool<char[]> getPool() {
        return this.pool;
    }

    public int getLength() {
        return this.length;
    }

    public char get(int i) {
        if (i < 0) {
            throw new IllegalArgumentException(("index is negative: " + i).toString());
        } else if (i < length()) {
            return getImpl(i);
        } else {
            throw new IllegalArgumentException(("index " + i + " is not in range [0, " + length() + ')').toString());
        }
    }

    /* access modifiers changed from: private */
    public final char getImpl(int i) {
        char[] bufferForIndex = bufferForIndex(i);
        char[] cArr = this.current;
        Intrinsics.checkNotNull(cArr);
        return bufferForIndex[i % cArr.length];
    }

    public CharSequence subSequence(int i, int i2) {
        if (i > i2) {
            throw new IllegalArgumentException(("startIndex (" + i + ") should be less or equal to endIndex (" + i2 + ')').toString());
        } else if (i < 0) {
            throw new IllegalArgumentException(("startIndex is negative: " + i).toString());
        } else if (i2 <= length()) {
            return new SubSequenceImpl(i, i2);
        } else {
            throw new IllegalArgumentException(("endIndex (" + i2 + ") is greater than length (" + length() + ')').toString());
        }
    }

    public String toString() {
        String str = this.stringified;
        if (str != null) {
            return str;
        }
        String obj = copy(0, length()).toString();
        this.stringified = obj;
        return obj;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof CharSequence)) {
            return false;
        }
        CharSequence charSequence = (CharSequence) obj;
        if (length() != charSequence.length()) {
            return false;
        }
        return rangeEqualsImpl(0, charSequence, 0, length());
    }

    public int hashCode() {
        String str = this.stringified;
        return str != null ? str.hashCode() : hashCodeImpl(0, length());
    }

    public Appendable append(char c) {
        char[] nonFullBuffer = nonFullBuffer();
        char[] cArr = this.current;
        Intrinsics.checkNotNull(cArr);
        int length2 = cArr.length;
        int i = this.remaining;
        nonFullBuffer[length2 - i] = c;
        this.stringified = null;
        this.remaining = i - 1;
        this.length = length() + 1;
        return this;
    }

    public Appendable append(CharSequence charSequence, int i, int i2) {
        if (charSequence == null) {
            return this;
        }
        int i3 = i;
        while (i3 < i2) {
            char[] nonFullBuffer = nonFullBuffer();
            int length2 = nonFullBuffer.length;
            int i4 = this.remaining;
            int i5 = length2 - i4;
            int min = Math.min(i2 - i3, i4);
            for (int i6 = 0; i6 < min; i6++) {
                nonFullBuffer[i5 + i6] = charSequence.charAt(i3 + i6);
            }
            i3 += min;
            this.remaining -= min;
        }
        this.stringified = null;
        this.length = length() + (i2 - i);
        return this;
    }

    public Appendable append(CharSequence charSequence) {
        if (charSequence == null) {
            return this;
        }
        return append(charSequence, 0, charSequence.length());
    }

    public final void release() {
        List<char[]> list = this.buffers;
        if (list != null) {
            this.current = null;
            int size = list.size();
            for (int i = 0; i < size; i++) {
                this.pool.recycle(list.get(i));
            }
        } else {
            char[] cArr = this.current;
            if (cArr != null) {
                this.pool.recycle(cArr);
            }
            this.current = null;
        }
        this.released = true;
        this.buffers = null;
        this.stringified = null;
        this.length = 0;
        this.remaining = 0;
    }

    /* access modifiers changed from: private */
    public final CharSequence copy(int i, int i2) {
        if (i == i2) {
            return "";
        }
        StringBuilder sb = new StringBuilder(i2 - i);
        for (int i3 = i - (i % 2048); i3 < i2; i3 += 2048) {
            char[] bufferForIndex = bufferForIndex(i3);
            int min = Math.min(i2 - i3, 2048);
            for (int max = Math.max(0, i - i3); max < min; max++) {
                sb.append(bufferForIndex[max]);
            }
        }
        return sb;
    }

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\f\n\u0002\b\u0007\b\u0004\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0002J\u0011\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0003H\u0002J\b\u0010\u0014\u001a\u00020\u0003H\u0016J\u0018\u0010\u0015\u001a\u00020\u00012\u0006\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u0003H\u0016J\b\u0010\u0018\u001a\u00020\fH\u0016R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\u00038VX\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0007R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u000e¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lio/ktor/http/cio/internals/CharArrayBuilder$SubSequenceImpl;", "", "start", "", "end", "(Lio/ktor/http/cio/internals/CharArrayBuilder;II)V", "getEnd", "()I", "length", "getLength", "getStart", "stringified", "", "equals", "", "other", "", "get", "", "index", "hashCode", "subSequence", "startIndex", "endIndex", "toString", "ktor-http-cio"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: CharArrayBuilder.kt */
    private final class SubSequenceImpl implements CharSequence {
        private final int end;
        private final int start;
        private String stringified;

        public final /* bridge */ char charAt(int i) {
            return get(i);
        }

        public final /* bridge */ int length() {
            return getLength();
        }

        public SubSequenceImpl(int i, int i2) {
            this.start = i;
            this.end = i2;
        }

        public final int getEnd() {
            return this.end;
        }

        public final int getStart() {
            return this.start;
        }

        public int getLength() {
            return this.end - this.start;
        }

        public char get(int i) {
            int i2 = this.start + i;
            if (i < 0) {
                throw new IllegalArgumentException(("index is negative: " + i).toString());
            } else if (i2 < this.end) {
                return CharArrayBuilder.this.getImpl(i2);
            } else {
                throw new IllegalArgumentException(("index (" + i + ") should be less than length (" + length() + ')').toString());
            }
        }

        public CharSequence subSequence(int i, int i2) {
            if (i < 0) {
                throw new IllegalArgumentException(("start is negative: " + i).toString());
            } else if (i <= i2) {
                int i3 = this.end;
                int i4 = this.start;
                if (i2 > i3 - i4) {
                    throw new IllegalArgumentException(("end should be less than length (" + length() + ')').toString());
                } else if (i == i2) {
                    return "";
                } else {
                    return new SubSequenceImpl(i + i4, i4 + i2);
                }
            } else {
                throw new IllegalArgumentException(("start (" + i + ") should be less or equal to end (" + i2 + ')').toString());
            }
        }

        public String toString() {
            String str = this.stringified;
            if (str != null) {
                return str;
            }
            String obj = CharArrayBuilder.this.copy(this.start, this.end).toString();
            this.stringified = obj;
            return obj;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof CharSequence)) {
                return false;
            }
            CharSequence charSequence = (CharSequence) obj;
            if (charSequence.length() != length()) {
                return false;
            }
            return CharArrayBuilder.this.rangeEqualsImpl(this.start, charSequence, 0, length());
        }

        public int hashCode() {
            String str = this.stringified;
            return str != null ? str.hashCode() : CharArrayBuilder.this.hashCodeImpl(this.start, this.end);
        }
    }

    private final char[] bufferForIndex(int i) {
        List<char[]> list = this.buffers;
        if (list != null) {
            char[] cArr = this.current;
            Intrinsics.checkNotNull(cArr);
            return list.get(i / cArr.length);
        } else if (i < 2048) {
            char[] cArr2 = this.current;
            if (cArr2 != null) {
                return cArr2;
            }
            throwSingleBuffer(i);
            throw new KotlinNothingValueException();
        } else {
            throwSingleBuffer(i);
            throw new KotlinNothingValueException();
        }
    }

    private final Void throwSingleBuffer(int i) {
        if (this.released) {
            throw new IllegalStateException("Buffer is already released");
        }
        throw new IndexOutOfBoundsException(i + " is not in range [0; " + currentPosition() + ')');
    }

    private final char[] nonFullBuffer() {
        if (this.remaining == 0) {
            return appendNewArray();
        }
        char[] cArr = this.current;
        Intrinsics.checkNotNull(cArr);
        return cArr;
    }

    private final char[] appendNewArray() {
        char[] borrow = this.pool.borrow();
        char[] cArr = this.current;
        this.current = borrow;
        this.remaining = borrow.length;
        this.released = false;
        if (cArr != null) {
            List<char[]> list = this.buffers;
            if (list == null) {
                ArrayList arrayList = new ArrayList();
                List<char[]> list2 = arrayList;
                this.buffers = list2;
                arrayList.add(cArr);
                list = list2;
            }
            list.add(borrow);
        }
        return borrow;
    }

    /* access modifiers changed from: private */
    public final boolean rangeEqualsImpl(int i, CharSequence charSequence, int i2, int i3) {
        for (int i4 = 0; i4 < i3; i4++) {
            if (getImpl(i + i4) != charSequence.charAt(i2 + i4)) {
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: private */
    public final int hashCodeImpl(int i, int i2) {
        int i3 = 0;
        while (i < i2) {
            i3 = (i3 * 31) + getImpl(i);
            i++;
        }
        return i3;
    }

    private final int currentPosition() {
        char[] cArr = this.current;
        Intrinsics.checkNotNull(cArr);
        return cArr.length - this.remaining;
    }
}
