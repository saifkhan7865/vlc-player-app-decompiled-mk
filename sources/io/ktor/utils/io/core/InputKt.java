package io.ktor.utils.io.core;

import java.io.EOFException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00008\n\u0000\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\f\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\u0012\u0010\u0003\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006\u001a\u0012\u0010\u0003\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0001\u001a$\u0010\u0007\u001a\u00020\u0004*\u00020\u00022\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00040\tH\bø\u0001\u0000\u001a\n\u0010\u000b\u001a\u00020\f*\u00020\u0002\u001a\u0014\u0010\r\u001a\u00020\f*\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u0006H\u0002\u001a$\u0010\u000f\u001a\u00020\u0004*\u00020\u00022\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00110\tH\bø\u0001\u0000\u001a.\u0010\u0012\u001a\u00020\u0004*\u00020\u00022\b\b\u0002\u0010\u0013\u001a\u00020\u00062\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00060\tH\bø\u0001\u0000\u0002\u0007\n\u0005\b20\u0001¨\u0006\u0014"}, d2 = {"discard", "", "Lio/ktor/utils/io/core/Input;", "discardExact", "", "n", "", "forEach", "block", "Lkotlin/Function1;", "", "peekCharUtf8", "", "peekCharUtf8Impl", "first", "takeWhile", "Lio/ktor/utils/io/core/Buffer;", "", "takeWhileSize", "initialSize", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: Input.kt */
public final class InputKt {
    public static final long discard(Input input) {
        Intrinsics.checkNotNullParameter(input, "<this>");
        return input.discard(Long.MAX_VALUE);
    }

    public static final void discardExact(Input input, long j) {
        Intrinsics.checkNotNullParameter(input, "<this>");
        long discard = input.discard(j);
        if (discard != j) {
            throw new IllegalStateException("Only " + discard + " bytes were discarded of " + j + " requested");
        }
    }

    public static final void discardExact(Input input, int i) {
        Intrinsics.checkNotNullParameter(input, "<this>");
        discardExact(input, (long) i);
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x003e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void takeWhile(io.ktor.utils.io.core.Input r4, kotlin.jvm.functions.Function1<? super io.ktor.utils.io.core.Buffer, java.lang.Boolean> r5) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            java.lang.String r0 = "block"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            r0 = 1
            io.ktor.utils.io.core.internal.ChunkBuffer r1 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadFirstHead(r4, r0)
            if (r1 != 0) goto L_0x0012
            return
        L_0x0012:
            java.lang.Object r2 = r5.invoke(r1)     // Catch:{ all -> 0x0037 }
            java.lang.Boolean r2 = (java.lang.Boolean) r2     // Catch:{ all -> 0x0037 }
            boolean r2 = r2.booleanValue()     // Catch:{ all -> 0x0037 }
            if (r2 != 0) goto L_0x0020
            r2 = 1
            goto L_0x0027
        L_0x0020:
            r2 = 0
            io.ktor.utils.io.core.internal.ChunkBuffer r3 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadNextHead(r4, r1)     // Catch:{ all -> 0x0035 }
            if (r3 != 0) goto L_0x0033
        L_0x0027:
            kotlin.jvm.internal.InlineMarker.finallyStart(r0)
            if (r2 == 0) goto L_0x002f
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r4, r1)
        L_0x002f:
            kotlin.jvm.internal.InlineMarker.finallyEnd(r0)
            return
        L_0x0033:
            r1 = r3
            goto L_0x0012
        L_0x0035:
            r5 = move-exception
            goto L_0x0039
        L_0x0037:
            r5 = move-exception
            r2 = 1
        L_0x0039:
            kotlin.jvm.internal.InlineMarker.finallyStart(r0)
            if (r2 == 0) goto L_0x0041
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r4, r1)
        L_0x0041:
            kotlin.jvm.internal.InlineMarker.finallyEnd(r0)
            goto L_0x0046
        L_0x0045:
            throw r5
        L_0x0046:
            goto L_0x0045
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.core.InputKt.takeWhile(io.ktor.utils.io.core.Input, kotlin.jvm.functions.Function1):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:43:0x0092  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static /* synthetic */ void takeWhileSize$default(io.ktor.utils.io.core.Input r3, int r4, kotlin.jvm.functions.Function1 r5, int r6, java.lang.Object r7) {
        /*
            r7 = 1
            r6 = r6 & r7
            if (r6 == 0) goto L_0x0005
            r4 = 1
        L_0x0005:
            java.lang.String r6 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r6)
            java.lang.String r6 = "block"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r6)
            io.ktor.utils.io.core.internal.ChunkBuffer r6 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadFirstHead(r3, r4)
            if (r6 != 0) goto L_0x0016
            return
        L_0x0016:
            r0 = r6
            io.ktor.utils.io.core.Buffer r0 = (io.ktor.utils.io.core.Buffer) r0     // Catch:{ all -> 0x008b }
            int r1 = r0.getWritePosition()     // Catch:{ all -> 0x008b }
            int r0 = r0.getReadPosition()     // Catch:{ all -> 0x008b }
            int r1 = r1 - r0
            if (r1 < r4) goto L_0x0052
            java.lang.Object r4 = r5.invoke(r6)     // Catch:{ all -> 0x0041 }
            java.lang.Number r4 = (java.lang.Number) r4     // Catch:{ all -> 0x0041 }
            int r4 = r4.intValue()     // Catch:{ all -> 0x0041 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r7)     // Catch:{ all -> 0x008b }
            r0 = r6
            io.ktor.utils.io.core.Buffer r0 = (io.ktor.utils.io.core.Buffer) r0     // Catch:{ all -> 0x008b }
            int r1 = r0.getWritePosition()     // Catch:{ all -> 0x008b }
            int r0 = r0.getReadPosition()     // Catch:{ all -> 0x008b }
            int r1 = r1 - r0
            kotlin.jvm.internal.InlineMarker.finallyEnd(r7)     // Catch:{ all -> 0x008b }
            goto L_0x0052
        L_0x0041:
            r4 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r7)     // Catch:{ all -> 0x008b }
            r5 = r6
            io.ktor.utils.io.core.Buffer r5 = (io.ktor.utils.io.core.Buffer) r5     // Catch:{ all -> 0x008b }
            r5.getWritePosition()     // Catch:{ all -> 0x008b }
            r5.getReadPosition()     // Catch:{ all -> 0x008b }
            kotlin.jvm.internal.InlineMarker.finallyEnd(r7)     // Catch:{ all -> 0x008b }
            throw r4     // Catch:{ all -> 0x008b }
        L_0x0052:
            r0 = 0
            if (r1 != 0) goto L_0x005c
            io.ktor.utils.io.core.internal.ChunkBuffer r1 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadNextHead(r3, r6)     // Catch:{ all -> 0x005a }
            goto L_0x0078
        L_0x005a:
            r4 = move-exception
            goto L_0x008d
        L_0x005c:
            if (r1 < r4) goto L_0x0071
            r1 = r6
            io.ktor.utils.io.core.Buffer r1 = (io.ktor.utils.io.core.Buffer) r1     // Catch:{ all -> 0x005a }
            int r2 = r1.getCapacity()     // Catch:{ all -> 0x005a }
            int r1 = r1.getLimit()     // Catch:{ all -> 0x005a }
            int r2 = r2 - r1
            r1 = 8
            if (r2 >= r1) goto L_0x006f
            goto L_0x0071
        L_0x006f:
            r1 = r6
            goto L_0x0078
        L_0x0071:
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r3, r6)     // Catch:{ all -> 0x005a }
            io.ktor.utils.io.core.internal.ChunkBuffer r1 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadFirstHead(r3, r4)     // Catch:{ all -> 0x005a }
        L_0x0078:
            if (r1 != 0) goto L_0x007b
            goto L_0x007f
        L_0x007b:
            r6 = r1
            if (r4 > 0) goto L_0x0016
            r0 = 1
        L_0x007f:
            kotlin.jvm.internal.InlineMarker.finallyStart(r7)
            if (r0 == 0) goto L_0x0087
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r3, r6)
        L_0x0087:
            kotlin.jvm.internal.InlineMarker.finallyEnd(r7)
            return
        L_0x008b:
            r4 = move-exception
            r0 = 1
        L_0x008d:
            kotlin.jvm.internal.InlineMarker.finallyStart(r7)
            if (r0 == 0) goto L_0x0095
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r3, r6)
        L_0x0095:
            kotlin.jvm.internal.InlineMarker.finallyEnd(r7)
            goto L_0x009a
        L_0x0099:
            throw r4
        L_0x009a:
            goto L_0x0099
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.core.InputKt.takeWhileSize$default(io.ktor.utils.io.core.Input, int, kotlin.jvm.functions.Function1, int, java.lang.Object):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:40:0x008e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void takeWhileSize(io.ktor.utils.io.core.Input r5, int r6, kotlin.jvm.functions.Function1<? super io.ktor.utils.io.core.Buffer, java.lang.Integer> r7) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            java.lang.String r0 = "block"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
            io.ktor.utils.io.core.internal.ChunkBuffer r0 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadFirstHead(r5, r6)
            if (r0 != 0) goto L_0x0011
            return
        L_0x0011:
            r1 = 1
            r2 = r0
            io.ktor.utils.io.core.Buffer r2 = (io.ktor.utils.io.core.Buffer) r2     // Catch:{ all -> 0x0087 }
            int r3 = r2.getWritePosition()     // Catch:{ all -> 0x0087 }
            int r2 = r2.getReadPosition()     // Catch:{ all -> 0x0087 }
            int r3 = r3 - r2
            if (r3 < r6) goto L_0x004e
            java.lang.Object r6 = r7.invoke(r0)     // Catch:{ all -> 0x003d }
            java.lang.Number r6 = (java.lang.Number) r6     // Catch:{ all -> 0x003d }
            int r6 = r6.intValue()     // Catch:{ all -> 0x003d }
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)     // Catch:{ all -> 0x0087 }
            r2 = r0
            io.ktor.utils.io.core.Buffer r2 = (io.ktor.utils.io.core.Buffer) r2     // Catch:{ all -> 0x0087 }
            int r3 = r2.getWritePosition()     // Catch:{ all -> 0x0087 }
            int r2 = r2.getReadPosition()     // Catch:{ all -> 0x0087 }
            int r3 = r3 - r2
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)     // Catch:{ all -> 0x0087 }
            goto L_0x004e
        L_0x003d:
            r6 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)     // Catch:{ all -> 0x0087 }
            r7 = r0
            io.ktor.utils.io.core.Buffer r7 = (io.ktor.utils.io.core.Buffer) r7     // Catch:{ all -> 0x0087 }
            r7.getWritePosition()     // Catch:{ all -> 0x0087 }
            r7.getReadPosition()     // Catch:{ all -> 0x0087 }
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)     // Catch:{ all -> 0x0087 }
            throw r6     // Catch:{ all -> 0x0087 }
        L_0x004e:
            r2 = 0
            if (r3 != 0) goto L_0x0058
            io.ktor.utils.io.core.internal.ChunkBuffer r3 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadNextHead(r5, r0)     // Catch:{ all -> 0x0056 }
            goto L_0x0074
        L_0x0056:
            r6 = move-exception
            goto L_0x0089
        L_0x0058:
            if (r3 < r6) goto L_0x006d
            r3 = r0
            io.ktor.utils.io.core.Buffer r3 = (io.ktor.utils.io.core.Buffer) r3     // Catch:{ all -> 0x0056 }
            int r4 = r3.getCapacity()     // Catch:{ all -> 0x0056 }
            int r3 = r3.getLimit()     // Catch:{ all -> 0x0056 }
            int r4 = r4 - r3
            r3 = 8
            if (r4 >= r3) goto L_0x006b
            goto L_0x006d
        L_0x006b:
            r3 = r0
            goto L_0x0074
        L_0x006d:
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r5, r0)     // Catch:{ all -> 0x0056 }
            io.ktor.utils.io.core.internal.ChunkBuffer r3 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadFirstHead(r5, r6)     // Catch:{ all -> 0x0056 }
        L_0x0074:
            if (r3 != 0) goto L_0x0077
            goto L_0x007b
        L_0x0077:
            r0 = r3
            if (r6 > 0) goto L_0x0011
            r2 = 1
        L_0x007b:
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            if (r2 == 0) goto L_0x0083
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r5, r0)
        L_0x0083:
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            return
        L_0x0087:
            r6 = move-exception
            r2 = 1
        L_0x0089:
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            if (r2 == 0) goto L_0x0091
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r5, r0)
        L_0x0091:
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            goto L_0x0096
        L_0x0095:
            throw r6
        L_0x0096:
            goto L_0x0095
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.core.InputKt.takeWhileSize(io.ktor.utils.io.core.Input, int, kotlin.jvm.functions.Function1):void");
    }

    public static final char peekCharUtf8(Input input) {
        Intrinsics.checkNotNullParameter(input, "<this>");
        int tryPeek = input.tryPeek();
        if ((tryPeek & 128) == 0) {
            return (char) tryPeek;
        }
        if (tryPeek != -1) {
            return peekCharUtf8Impl(input, tryPeek);
        }
        throw new EOFException("Failed to peek a char: end of input");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0049, code lost:
        r5 = true;
     */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x0116  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final char peekCharUtf8Impl(io.ktor.utils.io.core.Input r17, int r18) {
        /*
            r1 = r17
            int r0 = io.ktor.utils.io.core.internal.UTF8Kt.byteCountUtf8(r18)
            io.ktor.utils.io.core.internal.ChunkBuffer r2 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadFirstHead(r1, r0)
            r3 = 63
            if (r2 != 0) goto L_0x0011
            r4 = 0
            goto L_0x0107
        L_0x0011:
            r5 = 0
        L_0x0012:
            r6 = 1
            r7 = r2
            io.ktor.utils.io.core.Buffer r7 = (io.ktor.utils.io.core.Buffer) r7     // Catch:{ all -> 0x0112 }
            int r8 = r7.getWritePosition()     // Catch:{ all -> 0x0112 }
            int r7 = r7.getReadPosition()     // Catch:{ all -> 0x0112 }
            int r8 = r8 - r7
            if (r8 < r0) goto L_0x00d3
            r0 = r2
            io.ktor.utils.io.core.Buffer r0 = (io.ktor.utils.io.core.Buffer) r0     // Catch:{ all -> 0x00c8 }
            java.nio.ByteBuffer r7 = r0.m155getMemorySK3TCg8()     // Catch:{ all -> 0x00c8 }
            int r8 = r0.getReadPosition()     // Catch:{ all -> 0x00c8 }
            int r9 = r0.getWritePosition()     // Catch:{ all -> 0x00c8 }
            r10 = r8
            r11 = 0
            r12 = 0
            r13 = 0
        L_0x0034:
            if (r10 >= r9) goto L_0x00b2
            byte r14 = r7.get(r10)     // Catch:{ all -> 0x00c8 }
            r15 = r14 & 255(0xff, float:3.57E-43)
            r4 = r14 & 128(0x80, float:1.794E-43)
            r16 = -1
            if (r4 != 0) goto L_0x0055
            if (r11 != 0) goto L_0x004c
            char r3 = (char) r15     // Catch:{ all -> 0x00c8 }
            int r10 = r10 - r8
            r0.discardExact(r10)     // Catch:{ all -> 0x00c8 }
        L_0x0049:
            r5 = 1
            goto L_0x00b8
        L_0x004c:
            io.ktor.utils.io.core.internal.UTF8Kt.malformedByteCount(r11)     // Catch:{ all -> 0x00c8 }
            kotlin.KotlinNothingValueException r0 = new kotlin.KotlinNothingValueException     // Catch:{ all -> 0x00c8 }
            r0.<init>()     // Catch:{ all -> 0x00c8 }
            throw r0     // Catch:{ all -> 0x00c8 }
        L_0x0055:
            if (r11 != 0) goto L_0x007c
            r4 = 128(0x80, float:1.794E-43)
            r12 = 1
        L_0x005a:
            r13 = 7
            if (r12 >= r13) goto L_0x006b
            r13 = r15 & r4
            if (r13 == 0) goto L_0x006b
            r13 = r4 ^ -1
            r15 = r15 & r13
            int r4 = r4 >> 1
            int r11 = r11 + 1
            int r12 = r12 + 1
            goto L_0x005a
        L_0x006b:
            int r4 = r11 + -1
            int r12 = r9 - r10
            if (r11 <= r12) goto L_0x0078
            int r10 = r10 - r8
            r0.discardExact(r10)     // Catch:{ all -> 0x00c8 }
            r16 = r11
            goto L_0x00b8
        L_0x0078:
            r13 = r11
            r12 = r15
            r11 = r4
            goto L_0x00af
        L_0x007c:
            int r4 = r12 << 6
            r12 = r14 & 127(0x7f, float:1.78E-43)
            r4 = r4 | r12
            int r11 = r11 + -1
            if (r11 != 0) goto L_0x00ae
            boolean r3 = io.ktor.utils.io.core.internal.UTF8Kt.isBmpCodePoint(r4)     // Catch:{ all -> 0x00c8 }
            if (r3 == 0) goto L_0x0093
            char r3 = (char) r4     // Catch:{ all -> 0x00c8 }
            int r10 = r10 - r8
            int r10 = r10 - r13
            int r10 = r10 + r6
            r0.discardExact(r10)     // Catch:{ all -> 0x00c8 }
            goto L_0x0049
        L_0x0093:
            boolean r3 = io.ktor.utils.io.core.internal.UTF8Kt.isValidCodePoint(r4)     // Catch:{ all -> 0x00c8 }
            if (r3 == 0) goto L_0x00a5
            int r3 = io.ktor.utils.io.core.internal.UTF8Kt.highSurrogate(r4)     // Catch:{ all -> 0x00c8 }
            char r3 = (char) r3     // Catch:{ all -> 0x00c8 }
            int r10 = r10 - r8
            int r10 = r10 - r13
            int r10 = r10 + r6
            r0.discardExact(r10)     // Catch:{ all -> 0x00c8 }
            goto L_0x0049
        L_0x00a5:
            io.ktor.utils.io.core.internal.UTF8Kt.malformedCodePoint(r4)     // Catch:{ all -> 0x00c8 }
            kotlin.KotlinNothingValueException r0 = new kotlin.KotlinNothingValueException     // Catch:{ all -> 0x00c8 }
            r0.<init>()     // Catch:{ all -> 0x00c8 }
            throw r0     // Catch:{ all -> 0x00c8 }
        L_0x00ae:
            r12 = r4
        L_0x00af:
            int r10 = r10 + 1
            goto L_0x0034
        L_0x00b2:
            int r9 = r9 - r8
            r0.discardExact(r9)     // Catch:{ all -> 0x00c8 }
            r16 = 0
        L_0x00b8:
            r0 = r2
            io.ktor.utils.io.core.Buffer r0 = (io.ktor.utils.io.core.Buffer) r0     // Catch:{ all -> 0x0112 }
            int r4 = r0.getWritePosition()     // Catch:{ all -> 0x0112 }
            int r0 = r0.getReadPosition()     // Catch:{ all -> 0x0112 }
            int r8 = r4 - r0
            r0 = r16
            goto L_0x00d3
        L_0x00c8:
            r0 = move-exception
            r3 = r2
            io.ktor.utils.io.core.Buffer r3 = (io.ktor.utils.io.core.Buffer) r3     // Catch:{ all -> 0x0112 }
            r3.getWritePosition()     // Catch:{ all -> 0x0112 }
            r3.getReadPosition()     // Catch:{ all -> 0x0112 }
            throw r0     // Catch:{ all -> 0x0112 }
        L_0x00d3:
            if (r8 != 0) goto L_0x00dd
            io.ktor.utils.io.core.internal.ChunkBuffer r4 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadNextHead(r1, r2)     // Catch:{ all -> 0x00da }
            goto L_0x00f9
        L_0x00da:
            r0 = move-exception
            r4 = 0
            goto L_0x0114
        L_0x00dd:
            if (r8 < r0) goto L_0x00f2
            r4 = r2
            io.ktor.utils.io.core.Buffer r4 = (io.ktor.utils.io.core.Buffer) r4     // Catch:{ all -> 0x00da }
            int r7 = r4.getCapacity()     // Catch:{ all -> 0x00da }
            int r4 = r4.getLimit()     // Catch:{ all -> 0x00da }
            int r7 = r7 - r4
            r4 = 8
            if (r7 >= r4) goto L_0x00f0
            goto L_0x00f2
        L_0x00f0:
            r4 = r2
            goto L_0x00f9
        L_0x00f2:
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r1, r2)     // Catch:{ all -> 0x00da }
            io.ktor.utils.io.core.internal.ChunkBuffer r4 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadFirstHead(r1, r0)     // Catch:{ all -> 0x00da }
        L_0x00f9:
            if (r4 != 0) goto L_0x00fd
            r4 = 0
            goto L_0x0101
        L_0x00fd:
            r2 = r4
            if (r0 > 0) goto L_0x0012
            r4 = 1
        L_0x0101:
            if (r4 == 0) goto L_0x0106
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r1, r2)
        L_0x0106:
            r4 = r5
        L_0x0107:
            if (r4 == 0) goto L_0x010a
            return r3
        L_0x010a:
            io.ktor.utils.io.core.internal.MalformedUTF8InputException r0 = new io.ktor.utils.io.core.internal.MalformedUTF8InputException
            java.lang.String r1 = "No UTF-8 character found"
            r0.<init>(r1)
            throw r0
        L_0x0112:
            r0 = move-exception
            r4 = 1
        L_0x0114:
            if (r4 == 0) goto L_0x0119
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r1, r2)
        L_0x0119:
            goto L_0x011b
        L_0x011a:
            throw r0
        L_0x011b:
            goto L_0x011a
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.core.InputKt.peekCharUtf8Impl(io.ktor.utils.io.core.Input, int):char");
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x004d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void forEach(io.ktor.utils.io.core.Input r8, kotlin.jvm.functions.Function1<? super java.lang.Byte, kotlin.Unit> r9) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r0)
            java.lang.String r0 = "block"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r0)
            r0 = 1
            io.ktor.utils.io.core.internal.ChunkBuffer r1 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadFirstHead(r8, r0)
            if (r1 != 0) goto L_0x0012
            goto L_0x0042
        L_0x0012:
            r2 = r1
            io.ktor.utils.io.core.Buffer r2 = (io.ktor.utils.io.core.Buffer) r2     // Catch:{ all -> 0x0046 }
            java.nio.ByteBuffer r3 = r2.m155getMemorySK3TCg8()     // Catch:{ all -> 0x0046 }
            int r4 = r2.getReadPosition()     // Catch:{ all -> 0x0046 }
            int r5 = r2.getWritePosition()     // Catch:{ all -> 0x0046 }
            r6 = r4
        L_0x0022:
            if (r6 >= r5) goto L_0x0032
            byte r7 = r3.get(r6)     // Catch:{ all -> 0x0046 }
            java.lang.Byte r7 = java.lang.Byte.valueOf(r7)     // Catch:{ all -> 0x0046 }
            r9.invoke(r7)     // Catch:{ all -> 0x0046 }
            int r6 = r6 + 1
            goto L_0x0022
        L_0x0032:
            int r5 = r5 - r4
            r2.discardExact(r5)     // Catch:{ all -> 0x0046 }
            io.ktor.utils.io.core.internal.ChunkBuffer r1 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadNextHead(r8, r1)     // Catch:{ all -> 0x0043 }
            if (r1 != 0) goto L_0x0012
            kotlin.jvm.internal.InlineMarker.finallyStart(r0)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r0)
        L_0x0042:
            return
        L_0x0043:
            r9 = move-exception
            r2 = 0
            goto L_0x0048
        L_0x0046:
            r9 = move-exception
            r2 = 1
        L_0x0048:
            kotlin.jvm.internal.InlineMarker.finallyStart(r0)
            if (r2 == 0) goto L_0x0050
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r8, r1)
        L_0x0050:
            kotlin.jvm.internal.InlineMarker.finallyEnd(r0)
            goto L_0x0055
        L_0x0054:
            throw r9
        L_0x0055:
            goto L_0x0054
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.core.InputKt.forEach(io.ktor.utils.io.core.Input, kotlin.jvm.functions.Function1):void");
    }
}
