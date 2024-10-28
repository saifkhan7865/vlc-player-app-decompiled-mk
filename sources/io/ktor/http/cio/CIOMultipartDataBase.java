package io.ktor.http.cio;

import androidx.tvprovider.media.tv.TvContractCompat;
import io.ktor.http.cio.MultipartEvent;
import io.ktor.http.content.MultiPartData;
import io.ktor.http.content.PartData;
import io.ktor.util.InternalAPI;
import io.ktor.utils.io.ByteReadChannel;
import java.nio.ByteBuffer;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.ReceiveChannel;
import org.bouncycastle.cms.CMSAttributeTableGenerator;

@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002B;\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u0012\b\b\u0002\u0010\r\u001a\u00020\f¢\u0006\u0002\u0010\u000eJ\u001b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0016\u001a\u00020\u0013H@ø\u0001\u0000¢\u0006\u0002\u0010\u0017J\u0019\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\u001aH@ø\u0001\u0000¢\u0006\u0002\u0010\u001bJ\u0013\u0010\u001c\u001a\u0004\u0018\u00010\u0015H@ø\u0001\u0000¢\u0006\u0002\u0010\u001dJ\u0013\u0010\u001e\u001a\u0004\u0018\u00010\u0015H@ø\u0001\u0000¢\u0006\u0002\u0010\u001dJ)\u0010\u001f\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#H@ø\u0001\u0000¢\u0006\u0002\u0010$J \u0010%\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#H\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006&"}, d2 = {"Lio/ktor/http/cio/CIOMultipartDataBase;", "Lio/ktor/http/content/MultiPartData;", "Lkotlinx/coroutines/CoroutineScope;", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "channel", "Lio/ktor/utils/io/ByteReadChannel;", "contentType", "", "contentLength", "", "formFieldLimit", "", "inMemoryFileUploadLimit", "(Lkotlin/coroutines/CoroutineContext;Lio/ktor/utils/io/ByteReadChannel;Ljava/lang/CharSequence;Ljava/lang/Long;II)V", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "events", "Lkotlinx/coroutines/channels/ReceiveChannel;", "Lio/ktor/http/cio/MultipartEvent;", "eventToData", "Lio/ktor/http/content/PartData;", "event", "(Lio/ktor/http/cio/MultipartEvent;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "partToData", "part", "Lio/ktor/http/cio/MultipartEvent$MultipartPart;", "(Lio/ktor/http/cio/MultipartEvent$MultipartPart;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "readPart", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "readPartSuspend", "withTempFile", "headers", "Lio/ktor/http/cio/HttpHeadersMap;", "buffer", "Ljava/nio/ByteBuffer;", "(Lio/ktor/http/cio/MultipartEvent$MultipartPart;Lio/ktor/http/cio/HttpHeadersMap;Ljava/nio/ByteBuffer;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "withoutTempFile", "ktor-http-cio"}, k = 1, mv = {1, 8, 0}, xi = 48)
@InternalAPI
/* compiled from: CIOMultipartDataBase.kt */
public final class CIOMultipartDataBase implements MultiPartData, CoroutineScope {
    private final CoroutineContext coroutineContext;
    private final ReceiveChannel<MultipartEvent> events;
    private final int formFieldLimit;
    private final int inMemoryFileUploadLimit;

    public CIOMultipartDataBase(CoroutineContext coroutineContext2, ByteReadChannel byteReadChannel, CharSequence charSequence, Long l, int i, int i2) {
        Intrinsics.checkNotNullParameter(coroutineContext2, "coroutineContext");
        Intrinsics.checkNotNullParameter(byteReadChannel, TvContractCompat.PARAM_CHANNEL);
        Intrinsics.checkNotNullParameter(charSequence, CMSAttributeTableGenerator.CONTENT_TYPE);
        this.coroutineContext = coroutineContext2;
        this.formFieldLimit = i;
        this.inMemoryFileUploadLimit = i2;
        this.events = MultipartKt.parseMultipart((CoroutineScope) this, byteReadChannel, charSequence, l);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ CIOMultipartDataBase(kotlin.coroutines.CoroutineContext r8, io.ktor.utils.io.ByteReadChannel r9, java.lang.CharSequence r10, java.lang.Long r11, int r12, int r13, int r14, kotlin.jvm.internal.DefaultConstructorMarker r15) {
        /*
            r7 = this;
            r15 = r14 & 16
            if (r15 == 0) goto L_0x0009
            r12 = 65536(0x10000, float:9.18355E-41)
            r5 = 65536(0x10000, float:9.18355E-41)
            goto L_0x000a
        L_0x0009:
            r5 = r12
        L_0x000a:
            r12 = r14 & 32
            if (r12 == 0) goto L_0x0010
            r6 = r5
            goto L_0x0011
        L_0x0010:
            r6 = r13
        L_0x0011:
            r0 = r7
            r1 = r8
            r2 = r9
            r3 = r10
            r4 = r11
            r0.<init>(r1, r2, r3, r4, r5, r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.http.cio.CIOMultipartDataBase.<init>(kotlin.coroutines.CoroutineContext, io.ktor.utils.io.ByteReadChannel, java.lang.CharSequence, java.lang.Long, int, int, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public CoroutineContext getCoroutineContext() {
        return this.coroutineContext;
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x003d  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x004f  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x005a A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x005b A[PHI: r6 
      PHI: (r6v4 java.lang.Object) = (r6v11 java.lang.Object), (r6v1 java.lang.Object) binds: [B:18:0x0058, B:10:0x0029] A[DONT_GENERATE, DONT_INLINE], RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x005c  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x006b A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object readPart(kotlin.coroutines.Continuation<? super io.ktor.http.content.PartData> r6) {
        /*
            r5 = this;
            boolean r0 = r6 instanceof io.ktor.http.cio.CIOMultipartDataBase$readPart$1
            if (r0 == 0) goto L_0x0014
            r0 = r6
            io.ktor.http.cio.CIOMultipartDataBase$readPart$1 r0 = (io.ktor.http.cio.CIOMultipartDataBase$readPart$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L_0x0019
        L_0x0014:
            io.ktor.http.cio.CIOMultipartDataBase$readPart$1 r0 = new io.ktor.http.cio.CIOMultipartDataBase$readPart$1
            r0.<init>(r5, r6)
        L_0x0019:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x003d
            if (r2 == r4) goto L_0x0035
            if (r2 != r3) goto L_0x002d
            kotlin.ResultKt.throwOnFailure(r6)
            goto L_0x005b
        L_0x002d:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r0)
            throw r6
        L_0x0035:
            java.lang.Object r2 = r0.L$0
            io.ktor.http.cio.CIOMultipartDataBase r2 = (io.ktor.http.cio.CIOMultipartDataBase) r2
            kotlin.ResultKt.throwOnFailure(r6)
            goto L_0x0067
        L_0x003d:
            kotlin.ResultKt.throwOnFailure(r6)
            r2 = r5
        L_0x0041:
            kotlinx.coroutines.channels.ReceiveChannel<io.ktor.http.cio.MultipartEvent> r6 = r2.events
            java.lang.Object r6 = r6.m1138tryReceivePtdJZtk()
            java.lang.Object r6 = kotlinx.coroutines.channels.ChannelResult.m2341getOrNullimpl(r6)
            io.ktor.http.cio.MultipartEvent r6 = (io.ktor.http.cio.MultipartEvent) r6
            if (r6 != 0) goto L_0x005c
            r6 = 0
            r0.L$0 = r6
            r0.label = r3
            java.lang.Object r6 = r2.readPartSuspend(r0)
            if (r6 != r1) goto L_0x005b
            return r1
        L_0x005b:
            return r6
        L_0x005c:
            r0.L$0 = r2
            r0.label = r4
            java.lang.Object r6 = r2.eventToData(r6, r0)
            if (r6 != r1) goto L_0x0067
            return r1
        L_0x0067:
            io.ktor.http.content.PartData r6 = (io.ktor.http.content.PartData) r6
            if (r6 == 0) goto L_0x0041
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.http.cio.CIOMultipartDataBase.readPart(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0041  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0052 A[Catch:{ ClosedReceiveChannelException -> 0x0069 }] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0061 A[Catch:{ ClosedReceiveChannelException -> 0x0069 }, RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0066 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0067  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object readPartSuspend(kotlin.coroutines.Continuation<? super io.ktor.http.content.PartData> r7) {
        /*
            r6 = this;
            boolean r0 = r7 instanceof io.ktor.http.cio.CIOMultipartDataBase$readPartSuspend$1
            if (r0 == 0) goto L_0x0014
            r0 = r7
            io.ktor.http.cio.CIOMultipartDataBase$readPartSuspend$1 r0 = (io.ktor.http.cio.CIOMultipartDataBase$readPartSuspend$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L_0x0019
        L_0x0014:
            io.ktor.http.cio.CIOMultipartDataBase$readPartSuspend$1 r0 = new io.ktor.http.cio.CIOMultipartDataBase$readPartSuspend$1
            r0.<init>(r6, r7)
        L_0x0019:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x0041
            if (r2 == r4) goto L_0x0039
            if (r2 != r3) goto L_0x0031
            java.lang.Object r2 = r0.L$0
            io.ktor.http.cio.CIOMultipartDataBase r2 = (io.ktor.http.cio.CIOMultipartDataBase) r2
            kotlin.ResultKt.throwOnFailure(r7)     // Catch:{ ClosedReceiveChannelException -> 0x0069 }
            goto L_0x0062
        L_0x0031:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r0)
            throw r7
        L_0x0039:
            java.lang.Object r2 = r0.L$0
            io.ktor.http.cio.CIOMultipartDataBase r2 = (io.ktor.http.cio.CIOMultipartDataBase) r2
            kotlin.ResultKt.throwOnFailure(r7)     // Catch:{ ClosedReceiveChannelException -> 0x0069 }
            goto L_0x0055
        L_0x0041:
            kotlin.ResultKt.throwOnFailure(r7)
            r7 = r6
        L_0x0045:
            kotlinx.coroutines.channels.ReceiveChannel<io.ktor.http.cio.MultipartEvent> r2 = r7.events     // Catch:{ ClosedReceiveChannelException -> 0x0069 }
            r0.L$0 = r7     // Catch:{ ClosedReceiveChannelException -> 0x0069 }
            r0.label = r4     // Catch:{ ClosedReceiveChannelException -> 0x0069 }
            java.lang.Object r2 = r2.receive(r0)     // Catch:{ ClosedReceiveChannelException -> 0x0069 }
            if (r2 != r1) goto L_0x0052
            return r1
        L_0x0052:
            r5 = r2
            r2 = r7
            r7 = r5
        L_0x0055:
            io.ktor.http.cio.MultipartEvent r7 = (io.ktor.http.cio.MultipartEvent) r7     // Catch:{ ClosedReceiveChannelException -> 0x0069 }
            r0.L$0 = r2     // Catch:{ ClosedReceiveChannelException -> 0x0069 }
            r0.label = r3     // Catch:{ ClosedReceiveChannelException -> 0x0069 }
            java.lang.Object r7 = r2.eventToData(r7, r0)     // Catch:{ ClosedReceiveChannelException -> 0x0069 }
            if (r7 != r1) goto L_0x0062
            return r1
        L_0x0062:
            io.ktor.http.content.PartData r7 = (io.ktor.http.content.PartData) r7     // Catch:{ ClosedReceiveChannelException -> 0x0069 }
            if (r7 == 0) goto L_0x0067
            return r7
        L_0x0067:
            r7 = r2
            goto L_0x0045
        L_0x0069:
            r7 = 0
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.http.cio.CIOMultipartDataBase.readPartSuspend(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object eventToData(io.ktor.http.cio.MultipartEvent r5, kotlin.coroutines.Continuation<? super io.ktor.http.content.PartData> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof io.ktor.http.cio.CIOMultipartDataBase$eventToData$1
            if (r0 == 0) goto L_0x0014
            r0 = r6
            io.ktor.http.cio.CIOMultipartDataBase$eventToData$1 r0 = (io.ktor.http.cio.CIOMultipartDataBase$eventToData$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L_0x0019
        L_0x0014:
            io.ktor.http.cio.CIOMultipartDataBase$eventToData$1 r0 = new io.ktor.http.cio.CIOMultipartDataBase$eventToData$1
            r0.<init>(r4, r6)
        L_0x0019:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0036
            if (r2 != r3) goto L_0x002e
            java.lang.Object r5 = r0.L$0
            io.ktor.http.cio.MultipartEvent r5 = (io.ktor.http.cio.MultipartEvent) r5
            kotlin.ResultKt.throwOnFailure(r6)     // Catch:{ all -> 0x0053 }
            goto L_0x004b
        L_0x002e:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x0036:
            kotlin.ResultKt.throwOnFailure(r6)
            boolean r6 = r5 instanceof io.ktor.http.cio.MultipartEvent.MultipartPart     // Catch:{ all -> 0x0053 }
            if (r6 == 0) goto L_0x004e
            r6 = r5
            io.ktor.http.cio.MultipartEvent$MultipartPart r6 = (io.ktor.http.cio.MultipartEvent.MultipartPart) r6     // Catch:{ all -> 0x0053 }
            r0.L$0 = r5     // Catch:{ all -> 0x0053 }
            r0.label = r3     // Catch:{ all -> 0x0053 }
            java.lang.Object r6 = r4.partToData(r6, r0)     // Catch:{ all -> 0x0053 }
            if (r6 != r1) goto L_0x004b
            return r1
        L_0x004b:
            io.ktor.http.content.PartData r6 = (io.ktor.http.content.PartData) r6     // Catch:{ all -> 0x0053 }
            goto L_0x0052
        L_0x004e:
            r5.release()     // Catch:{ all -> 0x0053 }
            r6 = 0
        L_0x0052:
            return r6
        L_0x0053:
            r6 = move-exception
            r5.release()
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.http.cio.CIOMultipartDataBase.eventToData(io.ktor.http.cio.MultipartEvent, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x008c  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00ad  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00b8  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00bb  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00c2  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00c5  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0102  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x012c  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0152  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x0155  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x015b  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x018d  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0030  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object partToData(io.ktor.http.cio.MultipartEvent.MultipartPart r18, kotlin.coroutines.Continuation<? super io.ktor.http.content.PartData> r19) {
        /*
            r17 = this;
            r1 = r17
            r0 = r19
            boolean r2 = r0 instanceof io.ktor.http.cio.CIOMultipartDataBase$partToData$1
            if (r2 == 0) goto L_0x0018
            r2 = r0
            io.ktor.http.cio.CIOMultipartDataBase$partToData$1 r2 = (io.ktor.http.cio.CIOMultipartDataBase$partToData$1) r2
            int r3 = r2.label
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r3 & r4
            if (r3 == 0) goto L_0x0018
            int r0 = r2.label
            int r0 = r0 - r4
            r2.label = r0
            goto L_0x001d
        L_0x0018:
            io.ktor.http.cio.CIOMultipartDataBase$partToData$1 r2 = new io.ktor.http.cio.CIOMultipartDataBase$partToData$1
            r2.<init>(r1, r0)
        L_0x001d:
            java.lang.Object r0 = r2.result
            java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r4 = r2.label
            r5 = 5
            r6 = 4
            r7 = 2
            r8 = 3
            java.lang.String r9 = "buffer"
            r10 = 0
            r11 = 1
            r12 = 0
            if (r4 == 0) goto L_0x008c
            if (r4 == r11) goto L_0x0080
            if (r4 == r7) goto L_0x0074
            if (r4 == r8) goto L_0x005c
            if (r4 == r6) goto L_0x0047
            if (r4 != r5) goto L_0x003f
            kotlin.ResultKt.throwOnFailure(r0)
            goto L_0x01b7
        L_0x003f:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r2)
            throw r0
        L_0x0047:
            java.lang.Object r4 = r2.L$3
            java.nio.ByteBuffer r4 = (java.nio.ByteBuffer) r4
            java.lang.Object r6 = r2.L$2
            io.ktor.http.cio.HttpHeadersMap r6 = (io.ktor.http.cio.HttpHeadersMap) r6
            java.lang.Object r7 = r2.L$1
            io.ktor.http.cio.MultipartEvent$MultipartPart r7 = (io.ktor.http.cio.MultipartEvent.MultipartPart) r7
            java.lang.Object r8 = r2.L$0
            io.ktor.http.cio.CIOMultipartDataBase r8 = (io.ktor.http.cio.CIOMultipartDataBase) r8
            kotlin.ResultKt.throwOnFailure(r0)
            goto L_0x0149
        L_0x005c:
            java.lang.Object r4 = r2.L$3
            java.nio.ByteBuffer r4 = (java.nio.ByteBuffer) r4
            java.lang.Object r7 = r2.L$2
            io.ktor.http.cio.HttpHeadersMap r7 = (io.ktor.http.cio.HttpHeadersMap) r7
            java.lang.Object r8 = r2.L$1
            io.ktor.http.cio.MultipartEvent$MultipartPart r8 = (io.ktor.http.cio.MultipartEvent.MultipartPart) r8
            java.lang.Object r13 = r2.L$0
            io.ktor.http.cio.CIOMultipartDataBase r13 = (io.ktor.http.cio.CIOMultipartDataBase) r13
            kotlin.ResultKt.throwOnFailure(r0)
            r0 = r7
            r7 = r8
            r8 = r13
            goto L_0x0126
        L_0x0074:
            java.lang.Object r3 = r2.L$1
            io.ktor.http.cio.HttpHeadersMap r3 = (io.ktor.http.cio.HttpHeadersMap) r3
            java.lang.Object r2 = r2.L$0
            io.ktor.http.cio.MultipartEvent$MultipartPart r2 = (io.ktor.http.cio.MultipartEvent.MultipartPart) r2
            kotlin.ResultKt.throwOnFailure(r0)
            goto L_0x00dc
        L_0x0080:
            java.lang.Object r4 = r2.L$1
            io.ktor.http.cio.MultipartEvent$MultipartPart r4 = (io.ktor.http.cio.MultipartEvent.MultipartPart) r4
            java.lang.Object r13 = r2.L$0
            io.ktor.http.cio.CIOMultipartDataBase r13 = (io.ktor.http.cio.CIOMultipartDataBase) r13
            kotlin.ResultKt.throwOnFailure(r0)
            goto L_0x00a3
        L_0x008c:
            kotlin.ResultKt.throwOnFailure(r0)
            kotlinx.coroutines.Deferred r0 = r18.getHeaders()
            r2.L$0 = r1
            r4 = r18
            r2.L$1 = r4
            r2.label = r11
            java.lang.Object r0 = r0.await(r2)
            if (r0 != r3) goto L_0x00a2
            return r3
        L_0x00a2:
            r13 = r1
        L_0x00a3:
            io.ktor.http.cio.HttpHeadersMap r0 = (io.ktor.http.cio.HttpHeadersMap) r0
            java.lang.String r14 = "Content-Disposition"
            java.lang.CharSequence r14 = r0.get(r14)
            if (r14 == 0) goto L_0x00b8
            io.ktor.http.ContentDisposition$Companion r15 = io.ktor.http.ContentDisposition.Companion
            java.lang.String r14 = r14.toString()
            io.ktor.http.ContentDisposition r14 = r15.parse(r14)
            goto L_0x00b9
        L_0x00b8:
            r14 = r12
        L_0x00b9:
            if (r14 == 0) goto L_0x00c2
            java.lang.String r15 = "filename"
            java.lang.String r14 = r14.parameter(r15)
            goto L_0x00c3
        L_0x00c2:
            r14 = r12
        L_0x00c3:
            if (r14 != 0) goto L_0x0102
            io.ktor.utils.io.ByteReadChannel r5 = r4.getBody()
            int r6 = r13.formFieldLimit
            long r13 = (long) r6
            r2.L$0 = r4
            r2.L$1 = r0
            r2.label = r7
            java.lang.Object r2 = r5.readRemaining(r13, r2)
            if (r2 != r3) goto L_0x00d9
            return r3
        L_0x00d9:
            r3 = r0
            r0 = r2
            r2 = r4
        L_0x00dc:
            r4 = r0
            io.ktor.utils.io.core.ByteReadPacket r4 = (io.ktor.utils.io.core.ByteReadPacket) r4
            io.ktor.http.content.PartData$FormItem r0 = new io.ktor.http.content.PartData$FormItem     // Catch:{ all -> 0x00fd }
            r5 = r4
            io.ktor.utils.io.core.Input r5 = (io.ktor.utils.io.core.Input) r5     // Catch:{ all -> 0x00fd }
            java.lang.String r5 = io.ktor.utils.io.core.Input.readText$default(r5, r10, r10, r8, r12)     // Catch:{ all -> 0x00fd }
            io.ktor.http.cio.CIOMultipartDataBase$partToData$2 r6 = new io.ktor.http.cio.CIOMultipartDataBase$partToData$2     // Catch:{ all -> 0x00fd }
            r6.<init>(r2)     // Catch:{ all -> 0x00fd }
            kotlin.jvm.functions.Function0 r6 = (kotlin.jvm.functions.Function0) r6     // Catch:{ all -> 0x00fd }
            io.ktor.http.cio.CIOHeaders r2 = new io.ktor.http.cio.CIOHeaders     // Catch:{ all -> 0x00fd }
            r2.<init>(r3)     // Catch:{ all -> 0x00fd }
            io.ktor.http.Headers r2 = (io.ktor.http.Headers) r2     // Catch:{ all -> 0x00fd }
            r0.<init>(r5, r6, r2)     // Catch:{ all -> 0x00fd }
            r4.release()
            return r0
        L_0x00fd:
            r0 = move-exception
            r4.release()
            throw r0
        L_0x0102:
            int r7 = r13.inMemoryFileUploadLimit
            java.nio.ByteBuffer r7 = java.nio.ByteBuffer.allocate(r7)
            io.ktor.utils.io.ByteReadChannel r14 = r4.getBody()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r7, r9)
            r2.L$0 = r13
            r2.L$1 = r4
            r2.L$2 = r0
            r2.L$3 = r7
            r2.label = r8
            java.lang.Object r8 = r14.readAvailable((java.nio.ByteBuffer) r7, (kotlin.coroutines.Continuation<? super java.lang.Integer>) r2)
            if (r8 != r3) goto L_0x0120
            return r3
        L_0x0120:
            r8 = r13
            r16 = r7
            r7 = r4
            r4 = r16
        L_0x0126:
            int r13 = r4.remaining()
            if (r13 <= 0) goto L_0x0156
            io.ktor.utils.io.ByteReadChannel r13 = r7.getBody()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r9)
            r2.L$0 = r8
            r2.L$1 = r7
            r2.L$2 = r0
            r2.L$3 = r4
            r2.label = r6
            java.lang.Object r6 = r13.readAvailable((java.nio.ByteBuffer) r4, (kotlin.coroutines.Continuation<? super java.lang.Integer>) r2)
            if (r6 != r3) goto L_0x0144
            return r3
        L_0x0144:
            r16 = r6
            r6 = r0
            r0 = r16
        L_0x0149:
            java.lang.Number r0 = (java.lang.Number) r0
            int r0 = r0.intValue()
            r13 = -1
            if (r0 != r13) goto L_0x0155
            r0 = r6
            r10 = 1
            goto L_0x0156
        L_0x0155:
            r0 = r6
        L_0x0156:
            r4.flip()
            if (r10 == 0) goto L_0x018d
            java.io.ByteArrayInputStream r2 = new java.io.ByteArrayInputStream
            byte[] r3 = r4.array()
            int r5 = r4.arrayOffset()
            int r4 = r4.remaining()
            r2.<init>(r3, r5, r4)
            java.io.InputStream r2 = (java.io.InputStream) r2
            io.ktor.utils.io.core.Input r2 = io.ktor.utils.io.streams.InputKt.asInput$default(r2, r12, r11, r12)
            io.ktor.http.content.PartData$FileItem r3 = new io.ktor.http.content.PartData$FileItem
            io.ktor.http.cio.CIOMultipartDataBase$partToData$3 r4 = new io.ktor.http.cio.CIOMultipartDataBase$partToData$3
            r4.<init>(r2)
            kotlin.jvm.functions.Function0 r4 = (kotlin.jvm.functions.Function0) r4
            io.ktor.http.cio.CIOMultipartDataBase$partToData$4 r2 = new io.ktor.http.cio.CIOMultipartDataBase$partToData$4
            r2.<init>(r7)
            kotlin.jvm.functions.Function0 r2 = (kotlin.jvm.functions.Function0) r2
            io.ktor.http.cio.CIOHeaders r5 = new io.ktor.http.cio.CIOHeaders
            r5.<init>(r0)
            io.ktor.http.Headers r5 = (io.ktor.http.Headers) r5
            r3.<init>(r4, r2, r5)
            return r3
        L_0x018d:
            java.lang.String r6 = "io.ktor.http.content.multipart.skipTempFile"
            java.lang.String r6 = java.lang.System.getProperty(r6)
            java.lang.String r10 = "true"
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r6, (java.lang.Object) r10)
            if (r6 == 0) goto L_0x01a3
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r9)
            io.ktor.http.content.PartData r0 = r8.withoutTempFile(r7, r0, r4)
            return r0
        L_0x01a3:
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r9)
            r2.L$0 = r12
            r2.L$1 = r12
            r2.L$2 = r12
            r2.L$3 = r12
            r2.label = r5
            java.lang.Object r0 = r8.withTempFile(r7, r0, r4, r2)
            if (r0 != r3) goto L_0x01b7
            return r3
        L_0x01b7:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.http.cio.CIOMultipartDataBase.partToData(io.ktor.http.cio.MultipartEvent$MultipartPart, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final PartData withoutTempFile(MultipartEvent.MultipartPart multipartPart, HttpHeadersMap httpHeadersMap, ByteBuffer byteBuffer) {
        Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        Lazy lazy = LazyKt.lazy(new CIOMultipartDataBase$withoutTempFile$lazyInput$1(booleanRef, byteBuffer, multipartPart));
        return new PartData.FileItem(new CIOMultipartDataBase$withoutTempFile$1(lazy), new CIOMultipartDataBase$withoutTempFile$2(booleanRef, lazy, multipartPart), new CIOHeaders(httpHeadersMap));
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0064  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x009c A[Catch:{ all -> 0x0114 }, LOOP:0: B:27:0x0096->B:30:0x009c, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00be  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00cb A[Catch:{ all -> 0x010e }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object withTempFile(io.ktor.http.cio.MultipartEvent.MultipartPart r17, io.ktor.http.cio.HttpHeadersMap r18, java.nio.ByteBuffer r19, kotlin.coroutines.Continuation<? super io.ktor.http.content.PartData> r20) {
        /*
            r16 = this;
            r0 = r20
            boolean r1 = r0 instanceof io.ktor.http.cio.CIOMultipartDataBase$withTempFile$1
            if (r1 == 0) goto L_0x0018
            r1 = r0
            io.ktor.http.cio.CIOMultipartDataBase$withTempFile$1 r1 = (io.ktor.http.cio.CIOMultipartDataBase$withTempFile$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L_0x0018
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            r2 = r16
            goto L_0x001f
        L_0x0018:
            io.ktor.http.cio.CIOMultipartDataBase$withTempFile$1 r1 = new io.ktor.http.cio.CIOMultipartDataBase$withTempFile$1
            r2 = r16
            r1.<init>(r2, r0)
        L_0x001f:
            java.lang.Object r0 = r1.result
            java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r4 = r1.label
            r5 = 1
            r6 = 0
            if (r4 == 0) goto L_0x0064
            if (r4 != r5) goto L_0x005c
            java.lang.Object r4 = r1.L$6
            java.nio.channels.FileChannel r4 = (java.nio.channels.FileChannel) r4
            java.lang.Object r7 = r1.L$5
            java.io.Closeable r7 = (java.io.Closeable) r7
            java.lang.Object r8 = r1.L$4
            java.io.Closeable r8 = (java.io.Closeable) r8
            java.lang.Object r9 = r1.L$3
            java.io.File r9 = (java.io.File) r9
            java.lang.Object r10 = r1.L$2
            java.nio.ByteBuffer r10 = (java.nio.ByteBuffer) r10
            java.lang.Object r11 = r1.L$1
            io.ktor.http.cio.HttpHeadersMap r11 = (io.ktor.http.cio.HttpHeadersMap) r11
            java.lang.Object r12 = r1.L$0
            io.ktor.http.cio.MultipartEvent$MultipartPart r12 = (io.ktor.http.cio.MultipartEvent.MultipartPart) r12
            kotlin.ResultKt.throwOnFailure(r0)     // Catch:{ all -> 0x0058 }
            r13 = r9
            r9 = r6
            r6 = r1
            r1 = r11
            r11 = r8
            r8 = r9
            r15 = r7
            r7 = r4
            r4 = r10
            r10 = r15
            goto L_0x00c2
        L_0x0058:
            r0 = move-exception
            r1 = r0
            goto L_0x0119
        L_0x005c:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0064:
            kotlin.ResultKt.throwOnFailure(r0)
            java.lang.String r0 = "file-upload"
            java.lang.String r4 = ".tmp"
            java.io.File r9 = java.io.File.createTempFile(r0, r4)
            java.io.FileOutputStream r0 = new java.io.FileOutputStream     // Catch:{ all -> 0x0129 }
            r0.<init>(r9)     // Catch:{ all -> 0x0129 }
            r8 = r0
            java.io.Closeable r8 = (java.io.Closeable) r8     // Catch:{ all -> 0x0129 }
            r0 = r8
            java.io.FileOutputStream r0 = (java.io.FileOutputStream) r0     // Catch:{ all -> 0x0120 }
            java.nio.channels.FileChannel r0 = r0.getChannel()     // Catch:{ all -> 0x0120 }
            r7 = r0
            java.io.Closeable r7 = (java.io.Closeable) r7     // Catch:{ all -> 0x0120 }
            r0 = r7
            java.nio.channels.FileChannel r0 = (java.nio.channels.FileChannel) r0     // Catch:{ all -> 0x0058 }
            r10 = 0
            r0.truncate(r10)     // Catch:{ all -> 0x0058 }
            r4 = r19
            r10 = r7
            r11 = r8
            r12 = r9
            r7 = r0
            r8 = r6
            r9 = r8
            r0 = r17
            r6 = r1
            r1 = r18
        L_0x0096:
            boolean r13 = r4.hasRemaining()     // Catch:{ all -> 0x0114 }
            if (r13 == 0) goto L_0x00a0
            r7.write(r4)     // Catch:{ all -> 0x0114 }
            goto L_0x0096
        L_0x00a0:
            r4.clear()     // Catch:{ all -> 0x0114 }
            io.ktor.utils.io.ByteReadChannel r13 = r0.getBody()     // Catch:{ all -> 0x0114 }
            r6.L$0 = r0     // Catch:{ all -> 0x0114 }
            r6.L$1 = r1     // Catch:{ all -> 0x0114 }
            r6.L$2 = r4     // Catch:{ all -> 0x0114 }
            r6.L$3 = r12     // Catch:{ all -> 0x0114 }
            r6.L$4 = r11     // Catch:{ all -> 0x0114 }
            r6.L$5 = r10     // Catch:{ all -> 0x0114 }
            r6.L$6 = r7     // Catch:{ all -> 0x0114 }
            r6.label = r5     // Catch:{ all -> 0x0114 }
            java.lang.Object r13 = r13.readAvailable((java.nio.ByteBuffer) r4, (kotlin.coroutines.Continuation<? super java.lang.Integer>) r6)     // Catch:{ all -> 0x0114 }
            if (r13 != r3) goto L_0x00be
            return r3
        L_0x00be:
            r15 = r12
            r12 = r0
            r0 = r13
            r13 = r15
        L_0x00c2:
            java.lang.Number r0 = (java.lang.Number) r0     // Catch:{ all -> 0x010e }
            int r0 = r0.intValue()     // Catch:{ all -> 0x010e }
            r14 = -1
            if (r0 == r14) goto L_0x00d1
            r4.flip()     // Catch:{ all -> 0x010e }
            r0 = r12
            r12 = r13
            goto L_0x0096
        L_0x00d1:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x010e }
            kotlin.io.CloseableKt.closeFinally(r10, r9)     // Catch:{ all -> 0x0109 }
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0109 }
            kotlin.io.CloseableKt.closeFinally(r11, r8)     // Catch:{ all -> 0x0106 }
            kotlin.jvm.internal.Ref$BooleanRef r0 = new kotlin.jvm.internal.Ref$BooleanRef
            r0.<init>()
            io.ktor.http.cio.CIOMultipartDataBase$withTempFile$lazyInput$1 r3 = new io.ktor.http.cio.CIOMultipartDataBase$withTempFile$lazyInput$1
            r3.<init>(r0, r13)
            kotlin.jvm.functions.Function0 r3 = (kotlin.jvm.functions.Function0) r3
            kotlin.Lazy r3 = kotlin.LazyKt.lazy(r3)
            io.ktor.http.content.PartData$FileItem r4 = new io.ktor.http.content.PartData$FileItem
            io.ktor.http.cio.CIOMultipartDataBase$withTempFile$3 r5 = new io.ktor.http.cio.CIOMultipartDataBase$withTempFile$3
            r5.<init>(r3)
            kotlin.jvm.functions.Function0 r5 = (kotlin.jvm.functions.Function0) r5
            io.ktor.http.cio.CIOMultipartDataBase$withTempFile$4 r6 = new io.ktor.http.cio.CIOMultipartDataBase$withTempFile$4
            r6.<init>(r0, r3, r12, r13)
            kotlin.jvm.functions.Function0 r6 = (kotlin.jvm.functions.Function0) r6
            io.ktor.http.cio.CIOHeaders r0 = new io.ktor.http.cio.CIOHeaders
            r0.<init>(r1)
            io.ktor.http.Headers r0 = (io.ktor.http.Headers) r0
            r4.<init>(r5, r6, r0)
            return r4
        L_0x0106:
            r0 = move-exception
            r9 = r13
            goto L_0x012a
        L_0x0109:
            r0 = move-exception
            r1 = r0
            r8 = r11
            r9 = r13
            goto L_0x0122
        L_0x010e:
            r0 = move-exception
            r1 = r0
            r7 = r10
            r8 = r11
            r9 = r13
            goto L_0x0119
        L_0x0114:
            r0 = move-exception
            r1 = r0
            r7 = r10
            r8 = r11
            r9 = r12
        L_0x0119:
            throw r1     // Catch:{ all -> 0x011a }
        L_0x011a:
            r0 = move-exception
            r3 = r0
            kotlin.io.CloseableKt.closeFinally(r7, r1)     // Catch:{ all -> 0x0120 }
            throw r3     // Catch:{ all -> 0x0120 }
        L_0x0120:
            r0 = move-exception
            r1 = r0
        L_0x0122:
            throw r1     // Catch:{ all -> 0x0123 }
        L_0x0123:
            r0 = move-exception
            r3 = r0
            kotlin.io.CloseableKt.closeFinally(r8, r1)     // Catch:{ all -> 0x0129 }
            throw r3     // Catch:{ all -> 0x0129 }
        L_0x0129:
            r0 = move-exception
        L_0x012a:
            r9.delete()
            goto L_0x012f
        L_0x012e:
            throw r0
        L_0x012f:
            goto L_0x012e
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.http.cio.CIOMultipartDataBase.withTempFile(io.ktor.http.cio.MultipartEvent$MultipartPart, io.ktor.http.cio.HttpHeadersMap, java.nio.ByteBuffer, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
