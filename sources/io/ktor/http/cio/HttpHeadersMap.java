package io.ktor.http.cio;

import io.ktor.http.ContentDisposition;
import io.ktor.http.cio.internals.CharArrayBuilder;
import io.ktor.http.cio.internals.CharsKt;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\n\u0018\u00002\u00020\u0001B\u000f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\bJ\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u00132\u0006\u0010\r\u001a\u00020\u000eJ\u000e\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\bJ6\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\b2\u0006\u0010\u001a\u001a\u00020\b2\u0006\u0010\u001b\u001a\u00020\b2\u0006\u0010\u001c\u001a\u00020\b2\u0006\u0010\u001d\u001a\u00020\bJ\u0006\u0010\u001e\u001a\u00020\u0017J\b\u0010\u001f\u001a\u00020\u000eH\u0016J\u000e\u0010 \u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b@BX\u000e¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006!"}, d2 = {"Lio/ktor/http/cio/HttpHeadersMap;", "", "builder", "Lio/ktor/http/cio/internals/CharArrayBuilder;", "(Lio/ktor/http/cio/internals/CharArrayBuilder;)V", "indexes", "", "<set-?>", "", "size", "getSize", "()I", "find", "name", "", "fromIndex", "get", "", "getAll", "Lkotlin/sequences/Sequence;", "nameAt", "idx", "put", "", "nameHash", "valueHash", "nameStartIndex", "nameEndIndex", "valueStartIndex", "valueEndIndex", "release", "toString", "valueAt", "ktor-http-cio"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: HttpHeadersMap.kt */
public final class HttpHeadersMap {
    /* access modifiers changed from: private */
    public final CharArrayBuilder builder;
    /* access modifiers changed from: private */
    public int[] indexes = ((int[]) HttpHeadersMapKt.IntArrayPool.borrow());
    private int size;

    public HttpHeadersMap(CharArrayBuilder charArrayBuilder) {
        Intrinsics.checkNotNullParameter(charArrayBuilder, "builder");
        this.builder = charArrayBuilder;
    }

    public final int getSize() {
        return this.size;
    }

    public final void put(int i, int i2, int i3, int i4, int i5, int i6) {
        int i7 = this.size;
        int i8 = i7 * 8;
        int[] iArr = this.indexes;
        if (i8 < iArr.length) {
            iArr[i8] = i;
            iArr[i8 + 1] = i2;
            iArr[i8 + 2] = i3;
            iArr[i8 + 3] = i4;
            iArr[i8 + 4] = i5;
            iArr[i8 + 5] = i6;
            iArr[i8 + 6] = -1;
            iArr[i8 + 7] = -1;
            this.size = i7 + 1;
            return;
        }
        throw new NotImplementedError("An operation is not implemented: Implement headers overflow");
    }

    public static /* synthetic */ int find$default(HttpHeadersMap httpHeadersMap, String str, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        return httpHeadersMap.find(str, i);
    }

    public final int find(String str, int i) {
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        int hashCodeLowerCase$default = CharsKt.hashCodeLowerCase$default(str, 0, 0, 3, (Object) null);
        int i2 = this.size;
        while (i < i2) {
            if (this.indexes[i * 8] == hashCodeLowerCase$default) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public final CharSequence get(String str) {
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        int hashCodeLowerCase$default = CharsKt.hashCodeLowerCase$default(str, 0, 0, 3, (Object) null);
        int i = this.size;
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = i2 * 8;
            int[] iArr = this.indexes;
            if (iArr[i3] == hashCodeLowerCase$default) {
                return this.builder.subSequence(iArr[i3 + 4], iArr[i3 + 5]);
            }
        }
        return null;
    }

    public final Sequence<CharSequence> getAll(String str) {
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        return SequencesKt.map(SequencesKt.filter(SequencesKt.map(SequencesKt.generateSequence(0, new HttpHeadersMap$getAll$1(this)), HttpHeadersMap$getAll$2.INSTANCE), new HttpHeadersMap$getAll$3(this, CharsKt.hashCodeLowerCase$default(str, 0, 0, 3, (Object) null))), new HttpHeadersMap$getAll$4(this));
    }

    public final CharSequence nameAt(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        } else if (i < this.size) {
            int i2 = i * 8;
            int[] iArr = this.indexes;
            return this.builder.subSequence(iArr[i2 + 2], iArr[i2 + 3]);
        } else {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
    }

    public final CharSequence valueAt(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        } else if (i < this.size) {
            int i2 = i * 8;
            int[] iArr = this.indexes;
            return this.builder.subSequence(iArr[i2 + 4], iArr[i2 + 5]);
        } else {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
    }

    public final void release() {
        this.size = 0;
        int[] iArr = this.indexes;
        this.indexes = HttpHeadersMapKt.EMPTY_INT_LIST;
        if (iArr != HttpHeadersMapKt.EMPTY_INT_LIST) {
            HttpHeadersMapKt.IntArrayPool.recycle(iArr);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        HttpHeadersMapKt.dumpTo(this, "", sb);
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "StringBuilder().apply(builderAction).toString()");
        return sb2;
    }
}
