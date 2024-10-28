package okio;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.RandomAccess;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\b\u0018\u0000 \u00142\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u0001\u0014B\u001f\b\u0002\u0012\u000e\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0011\u0010\u0012\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\rH\u0002R\u001e\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00020\u0005X\u0004¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\nR\u0014\u0010\f\u001a\u00020\r8VX\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0015"}, d2 = {"Lokio/Options;", "Ljava/util/AbstractList;", "Lokio/ByteString;", "Ljava/util/RandomAccess;", "byteStrings", "", "trie", "", "([Lokio/ByteString;[I)V", "getByteStrings$jvm", "()[Lokio/ByteString;", "[Lokio/ByteString;", "size", "", "getSize", "()I", "getTrie$jvm", "()[I", "get", "index", "Companion", "jvm"}, k = 1, mv = {1, 1, 11})
/* compiled from: Options.kt */
public final class Options extends AbstractList<ByteString> implements RandomAccess {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final ByteString[] byteStrings;
    private final int[] trie;

    @JvmStatic
    public static final Options of(ByteString... byteStringArr) {
        return Companion.of(byteStringArr);
    }

    public /* synthetic */ Options(ByteString[] byteStringArr, int[] iArr, DefaultConstructorMarker defaultConstructorMarker) {
        this(byteStringArr, iArr);
    }

    public final /* bridge */ boolean contains(Object obj) {
        if (obj == null || (obj instanceof ByteString)) {
            return contains((ByteString) obj);
        }
        return false;
    }

    public /* bridge */ boolean contains(ByteString byteString) {
        return super.contains(byteString);
    }

    public final /* bridge */ int indexOf(Object obj) {
        if (obj == null || (obj instanceof ByteString)) {
            return indexOf((ByteString) obj);
        }
        return -1;
    }

    public /* bridge */ int indexOf(ByteString byteString) {
        return super.indexOf(byteString);
    }

    public final /* bridge */ int lastIndexOf(Object obj) {
        if (obj == null || (obj instanceof ByteString)) {
            return lastIndexOf((ByteString) obj);
        }
        return -1;
    }

    public /* bridge */ int lastIndexOf(ByteString byteString) {
        return super.lastIndexOf(byteString);
    }

    public final /* bridge */ ByteString remove(int i) {
        return removeAt(i);
    }

    public final /* bridge */ boolean remove(Object obj) {
        if (obj == null || (obj instanceof ByteString)) {
            return remove((ByteString) obj);
        }
        return false;
    }

    public /* bridge */ boolean remove(ByteString byteString) {
        return super.remove(byteString);
    }

    public /* bridge */ ByteString removeAt(int i) {
        return (ByteString) super.remove(i);
    }

    public final /* bridge */ int size() {
        return getSize();
    }

    public final ByteString[] getByteStrings$jvm() {
        return this.byteStrings;
    }

    public final int[] getTrie$jvm() {
        return this.trie;
    }

    private Options(ByteString[] byteStringArr, int[] iArr) {
        this.byteStrings = byteStringArr;
        this.trie = iArr;
    }

    public int getSize() {
        return this.byteStrings.length;
    }

    public ByteString get(int i) {
        return this.byteStrings[i];
    }

    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002JT\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u00052\b\b\u0002\u0010\f\u001a\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\b\b\u0002\u0010\u0011\u001a\u00020\r2\b\b\u0002\u0010\u0012\u001a\u00020\r2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\r0\u000fH\u0002J!\u0010\u0014\u001a\u00020\u00152\u0012\u0010\u000e\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00100\u0016\"\u00020\u0010H\u0007¢\u0006\u0002\u0010\u0017R\u0018\u0010\u0003\u001a\u00020\u0004*\u00020\u00058BX\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0018"}, d2 = {"Lokio/Options$Companion;", "", "()V", "intCount", "", "Lokio/Buffer;", "getIntCount", "(Lokio/Buffer;)J", "buildTrieRecursive", "", "nodeOffset", "node", "byteStringOffset", "", "byteStrings", "", "Lokio/ByteString;", "fromIndex", "toIndex", "indexes", "of", "Lokio/Options;", "", "([Lokio/ByteString;)Lokio/Options;", "jvm"}, k = 1, mv = {1, 1, 11})
    /* compiled from: Options.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final Options of(ByteString... byteStringArr) {
            ByteString[] byteStringArr2 = byteStringArr;
            Intrinsics.checkParameterIsNotNull(byteStringArr2, "byteStrings");
            int i = 0;
            if (byteStringArr2.length == 0) {
                return new Options(new ByteString[0], new int[]{0, -1}, (DefaultConstructorMarker) null);
            }
            List mutableList = ArraysKt.toMutableList((T[]) byteStringArr);
            CollectionsKt.sort(mutableList);
            Collection arrayList = new ArrayList(byteStringArr2.length);
            for (ByteString byteString : byteStringArr2) {
                arrayList.add(-1);
            }
            Object[] array = ((List) arrayList).toArray(new Integer[0]);
            if (array != null) {
                Integer[] numArr = (Integer[]) array;
                List mutableListOf = CollectionsKt.mutableListOf((Integer[]) Arrays.copyOf(numArr, numArr.length));
                int length = byteStringArr2.length;
                int i2 = 0;
                int i3 = 0;
                while (i2 < length) {
                    mutableListOf.set(CollectionsKt.binarySearch$default(mutableList, (Comparable) byteStringArr2[i2], 0, 0, 6, (Object) null), Integer.valueOf(i3));
                    i2++;
                    i3++;
                }
                if (((ByteString) mutableList.get(0)).size() > 0) {
                    int i4 = 0;
                    while (i4 < mutableList.size()) {
                        ByteString byteString2 = (ByteString) mutableList.get(i4);
                        int i5 = i4 + 1;
                        int i6 = i5;
                        while (i6 < mutableList.size()) {
                            ByteString byteString3 = (ByteString) mutableList.get(i6);
                            if (!byteString3.startsWith(byteString2)) {
                                continue;
                                break;
                            } else if (byteString3.size() == byteString2.size()) {
                                throw new IllegalArgumentException(("duplicate option: " + byteString3).toString());
                            } else if (((Number) mutableListOf.get(i6)).intValue() > ((Number) mutableListOf.get(i4)).intValue()) {
                                mutableList.remove(i6);
                                mutableListOf.remove(i6);
                            } else {
                                i6++;
                            }
                        }
                        i4 = i5;
                    }
                    Buffer buffer = new Buffer();
                    Companion companion = this;
                    buildTrieRecursive$default(this, 0, buffer, 0, mutableList, 0, 0, mutableListOf, 53, (Object) null);
                    int[] iArr = new int[((int) getIntCount(buffer))];
                    while (!buffer.exhausted()) {
                        iArr[i] = buffer.readInt();
                        i++;
                    }
                    return new Options((ByteString[]) byteStringArr.clone(), iArr, (DefaultConstructorMarker) null);
                }
                throw new IllegalArgumentException("the empty byte string is not a supported option".toString());
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
        }

        static /* bridge */ /* synthetic */ void buildTrieRecursive$default(Companion companion, long j, Buffer buffer, int i, List list, int i2, int i3, List list2, int i4, Object obj) {
            companion.buildTrieRecursive((i4 & 1) != 0 ? 0 : j, buffer, (i4 & 4) != 0 ? 0 : i, list, (i4 & 16) != 0 ? 0 : i2, (i4 & 32) != 0 ? list.size() : i3, list2);
        }

        private final void buildTrieRecursive(long j, Buffer buffer, int i, List<? extends ByteString> list, int i2, int i3, List<Integer> list2) {
            int i4;
            int i5;
            int i6;
            Buffer buffer2;
            int i7;
            Buffer buffer3 = buffer;
            int i8 = i;
            List<? extends ByteString> list3 = list;
            int i9 = i2;
            int i10 = i3;
            List<Integer> list4 = list2;
            if (i9 < i10) {
                int i11 = i9;
                while (i11 < i10) {
                    if (((ByteString) list3.get(i11)).size() >= i8) {
                        i11++;
                    } else {
                        throw new IllegalArgumentException("Failed requirement.".toString());
                    }
                }
                ByteString byteString = (ByteString) list.get(i2);
                ByteString byteString2 = (ByteString) list3.get(i10 - 1);
                if (i8 == byteString.size()) {
                    int intValue = list4.get(i9).intValue();
                    int i12 = i9 + 1;
                    i4 = i12;
                    i5 = intValue;
                    byteString = (ByteString) list3.get(i12);
                } else {
                    i4 = i9;
                    i5 = -1;
                }
                if (byteString.getByte(i8) != byteString2.getByte(i8)) {
                    int i13 = 1;
                    for (int i14 = i4 + 1; i14 < i10; i14++) {
                        if (((ByteString) list3.get(i14 - 1)).getByte(i8) != ((ByteString) list3.get(i14)).getByte(i8)) {
                            i13++;
                        }
                    }
                    Companion companion = this;
                    long intCount = j + getIntCount(buffer3) + ((long) 2) + ((long) (i13 * 2));
                    buffer3.writeInt(i13);
                    buffer3.writeInt(i5);
                    for (int i15 = i4; i15 < i10; i15++) {
                        byte b = ((ByteString) list3.get(i15)).getByte(i8);
                        if (i15 == i4 || b != ((ByteString) list3.get(i15 - 1)).getByte(i8)) {
                            buffer3.writeInt((int) b & 255);
                        }
                    }
                    Buffer buffer4 = new Buffer();
                    while (i4 < i10) {
                        byte b2 = ((ByteString) list3.get(i4)).getByte(i8);
                        int i16 = i4 + 1;
                        int i17 = i16;
                        while (true) {
                            if (i17 >= i10) {
                                i6 = i10;
                                break;
                            } else if (b2 != ((ByteString) list3.get(i17)).getByte(i8)) {
                                i6 = i17;
                                break;
                            } else {
                                i17++;
                            }
                        }
                        if (i16 == i6 && i8 + 1 == ((ByteString) list3.get(i4)).size()) {
                            buffer3.writeInt(list4.get(i4).intValue());
                            i7 = i6;
                            buffer2 = buffer4;
                        } else {
                            buffer3.writeInt(((int) (intCount + getIntCount(buffer4))) * -1);
                            i7 = i6;
                            buffer2 = buffer4;
                            buildTrieRecursive(intCount, buffer4, i8 + 1, list, i4, i6, list2);
                        }
                        i4 = i7;
                        buffer4 = buffer2;
                    }
                    buffer3.writeAll(buffer4);
                    return;
                }
                int min = Math.min(byteString.size(), byteString2.size());
                int i18 = 0;
                int i19 = i8;
                while (i19 < min && byteString.getByte(i19) == byteString2.getByte(i19)) {
                    i18++;
                    i19++;
                }
                Companion companion2 = this;
                long intCount2 = j + getIntCount(buffer3) + ((long) 2) + ((long) i18) + 1;
                buffer3.writeInt(-i18);
                buffer3.writeInt(i5);
                int i20 = i18 + i8;
                while (i8 < i20) {
                    buffer3.writeInt((int) byteString.getByte(i8) & 255);
                    i8++;
                }
                if (i4 + 1 != i10) {
                    Buffer buffer5 = new Buffer();
                    buffer3.writeInt(((int) (getIntCount(buffer5) + intCount2)) * -1);
                    buildTrieRecursive(intCount2, buffer5, i20, list, i4, i3, list2);
                    buffer3.writeAll(buffer5);
                } else if (i20 == ((ByteString) list3.get(i4)).size()) {
                    buffer3.writeInt(list4.get(i4).intValue());
                } else {
                    throw new IllegalStateException("Check failed.".toString());
                }
            } else {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
        }

        private final long getIntCount(Buffer buffer) {
            return buffer.size() / ((long) 4);
        }
    }
}
