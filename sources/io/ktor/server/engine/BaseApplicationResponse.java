package io.ktor.server.engine;

import androidx.core.app.NotificationCompat;
import io.ktor.http.ContentDisposition;
import io.ktor.http.HttpStatusCode;
import io.ktor.http.LinkHeader;
import io.ktor.http.content.OutgoingContent;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.http.LinkHeaderKt;
import io.ktor.server.response.ApplicationResponse;
import io.ktor.server.response.ApplicationSendPipeline;
import io.ktor.server.response.ResponseCookies;
import io.ktor.server.response.ResponsePushBuilder;
import io.ktor.server.response.UseHttp2Push;
import io.ktor.util.AttributeKey;
import io.ktor.util.internal.ExceptionUtilsJvmKt;
import io.ktor.utils.io.ByteReadChannel;
import io.ktor.utils.io.ByteWriteChannel;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CopyableThrowable;

@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\b&\u0018\u0000 A2\u00020\u0001:\u0005?@ABCB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0004J\u0018\u0010\u001d\u001a\u00020\u001a2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u001fH\u0002J\u0010\u0010!\u001a\u00020\u001a2\u0006\u0010\"\u001a\u00020#H\u0017J\u0019\u0010$\u001a\u00020\u001a2\u0006\u0010%\u001a\u00020&H@ø\u0001\u0000¢\u0006\u0002\u0010'J\u0019\u0010(\u001a\u00020\u001a2\u0006\u0010)\u001a\u00020*H@ø\u0001\u0000¢\u0006\u0002\u0010+J\u0019\u0010,\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020-H@ø\u0001\u0000¢\u0006\u0002\u0010.J\u0019\u0010/\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH@ø\u0001\u0000¢\u0006\u0002\u00100J\u0019\u00101\u001a\u00020\u001a2\u0006\u00102\u001a\u000203H¤@ø\u0001\u0000¢\u0006\u0002\u00104J\u0019\u00105\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u000206H@ø\u0001\u0000¢\u0006\u0002\u00107J\u0011\u00108\u001a\u000209H¤@ø\u0001\u0000¢\u0006\u0002\u0010:J\u0010\u0010;\u001a\u00020\u001a2\u0006\u0010<\u001a\u00020\u0006H$J\n\u0010=\u001a\u0004\u0018\u00010\u0006H\u0016J\u0010\u0010=\u001a\u00020\u001a2\u0006\u0010>\u001a\u00020\u0006H\u0016R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001b\u0010\t\u001a\u00020\n8VX\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u000f\u001a\u00020\u00108VX\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0011R\u001e\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u0010@BX\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u0011\u0010\u0014\u001a\u00020\u0015¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u000e\u0010\u0018\u001a\u00020\u0010X\u000e¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006D"}, d2 = {"Lio/ktor/server/engine/BaseApplicationResponse;", "Lio/ktor/server/response/ApplicationResponse;", "call", "Lio/ktor/server/application/ApplicationCall;", "(Lio/ktor/server/application/ApplicationCall;)V", "_status", "Lio/ktor/http/HttpStatusCode;", "getCall", "()Lio/ktor/server/application/ApplicationCall;", "cookies", "Lio/ktor/server/response/ResponseCookies;", "getCookies", "()Lio/ktor/server/response/ResponseCookies;", "cookies$delegate", "Lkotlin/Lazy;", "isCommitted", "", "()Z", "<set-?>", "isSent", "pipeline", "Lio/ktor/server/response/ApplicationSendPipeline;", "getPipeline", "()Lio/ktor/server/response/ApplicationSendPipeline;", "responded", "commitHeaders", "", "content", "Lio/ktor/http/content/OutgoingContent;", "ensureLength", "expected", "", "actual", "push", "builder", "Lio/ktor/server/response/ResponsePushBuilder;", "respondFromBytes", "bytes", "", "([BLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "respondFromChannel", "readChannel", "Lio/ktor/utils/io/ByteReadChannel;", "(Lio/ktor/utils/io/ByteReadChannel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "respondNoContent", "Lio/ktor/http/content/OutgoingContent$NoContent;", "(Lio/ktor/http/content/OutgoingContent$NoContent;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "respondOutgoingContent", "(Lio/ktor/http/content/OutgoingContent;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "respondUpgrade", "upgrade", "Lio/ktor/http/content/OutgoingContent$ProtocolUpgrade;", "(Lio/ktor/http/content/OutgoingContent$ProtocolUpgrade;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "respondWriteChannelContent", "Lio/ktor/http/content/OutgoingContent$WriteChannelContent;", "(Lio/ktor/http/content/OutgoingContent$WriteChannelContent;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "responseChannel", "Lio/ktor/utils/io/ByteWriteChannel;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setStatus", "statusCode", "status", "value", "BodyLengthIsTooLong", "BodyLengthIsTooSmall", "Companion", "InvalidHeaderForContent", "ResponseAlreadySentException", "ktor-server-host-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: BaseApplicationResponse.kt */
public abstract class BaseApplicationResponse implements ApplicationResponse {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final AttributeKey<BaseApplicationResponse> EngineResponseAttributeKey = new AttributeKey<>("EngineResponse");
    private HttpStatusCode _status;
    private final ApplicationCall call;
    private final Lazy cookies$delegate = LazyKt.lazy(new BaseApplicationResponse$cookies$2(this));
    private boolean isSent;
    private final ApplicationSendPipeline pipeline;
    private boolean responded;

    /* access modifiers changed from: protected */
    public Object respondFromBytes(byte[] bArr, Continuation<? super Unit> continuation) {
        return respondFromBytes$suspendImpl(this, bArr, continuation);
    }

    /* access modifiers changed from: protected */
    public Object respondFromChannel(ByteReadChannel byteReadChannel, Continuation<? super Unit> continuation) {
        return respondFromChannel$suspendImpl(this, byteReadChannel, continuation);
    }

    /* access modifiers changed from: protected */
    public Object respondNoContent(OutgoingContent.NoContent noContent, Continuation<? super Unit> continuation) {
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: protected */
    public Object respondOutgoingContent(OutgoingContent outgoingContent, Continuation<? super Unit> continuation) {
        return respondOutgoingContent$suspendImpl(this, outgoingContent, continuation);
    }

    /* access modifiers changed from: protected */
    public abstract Object respondUpgrade(OutgoingContent.ProtocolUpgrade protocolUpgrade, Continuation<? super Unit> continuation);

    /* access modifiers changed from: protected */
    public Object respondWriteChannelContent(OutgoingContent.WriteChannelContent writeChannelContent, Continuation<? super Unit> continuation) {
        return respondWriteChannelContent$suspendImpl(this, writeChannelContent, continuation);
    }

    /* access modifiers changed from: protected */
    public abstract Object responseChannel(Continuation<? super ByteWriteChannel> continuation);

    /* access modifiers changed from: protected */
    public abstract void setStatus(HttpStatusCode httpStatusCode);

    public BaseApplicationResponse(ApplicationCall applicationCall) {
        Intrinsics.checkNotNullParameter(applicationCall, NotificationCompat.CATEGORY_CALL);
        this.call = applicationCall;
        ApplicationSendPipeline applicationSendPipeline = new ApplicationSendPipeline(applicationCall.getApplication().getEnvironment().getDevelopmentMode());
        applicationSendPipeline.resetFrom(applicationCall.getApplication().getSendPipeline());
        this.pipeline = applicationSendPipeline;
    }

    public final ApplicationCall getCall() {
        return this.call;
    }

    public boolean isCommitted() {
        return this.responded;
    }

    public final boolean isSent() {
        return this.isSent;
    }

    public ResponseCookies getCookies() {
        return (ResponseCookies) this.cookies$delegate.getValue();
    }

    public HttpStatusCode status() {
        return this._status;
    }

    public void status(HttpStatusCode httpStatusCode) {
        Intrinsics.checkNotNullParameter(httpStatusCode, "value");
        this._status = httpStatusCode;
        setStatus(httpStatusCode);
    }

    public final ApplicationSendPipeline getPipeline() {
        return this.pipeline;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x003f  */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0055  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0086  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00c3  */
    /* JADX WARNING: Removed duplicated region for block: B:36:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void commitHeaders(io.ktor.http.content.OutgoingContent r8) {
        /*
            r7 = this;
            java.lang.String r0 = "content"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r0)
            boolean r0 = r7.responded
            if (r0 != 0) goto L_0x00e3
            r0 = 1
            r7.responded = r0
            kotlin.jvm.internal.Ref$BooleanRef r1 = new kotlin.jvm.internal.Ref$BooleanRef
            r1.<init>()
            io.ktor.http.HttpStatusCode r2 = r8.getStatus()
            if (r2 == 0) goto L_0x001d
        L_0x0017:
            r7.status(r2)
            kotlin.Unit r2 = kotlin.Unit.INSTANCE
            goto L_0x002a
        L_0x001d:
            io.ktor.http.HttpStatusCode r2 = r7.status()
            if (r2 != 0) goto L_0x002a
            io.ktor.http.HttpStatusCode$Companion r2 = io.ktor.http.HttpStatusCode.Companion
            io.ktor.http.HttpStatusCode r2 = r2.getOK()
            goto L_0x0017
        L_0x002a:
            io.ktor.http.Headers r2 = r8.getHeaders()
            io.ktor.server.engine.BaseApplicationResponse$commitHeaders$2 r3 = new io.ktor.server.engine.BaseApplicationResponse$commitHeaders$2
            r3.<init>(r1, r8, r7)
            kotlin.jvm.functions.Function2 r3 = (kotlin.jvm.functions.Function2) r3
            r2.forEach(r3)
            java.lang.Long r2 = r8.getContentLength()
            r3 = 0
            if (r2 == 0) goto L_0x0055
            io.ktor.server.response.ResponseHeaders r1 = r7.getHeaders()
            io.ktor.http.HttpHeaders r4 = io.ktor.http.HttpHeaders.INSTANCE
            java.lang.String r4 = r4.getContentLength()
            long r5 = r2.longValue()
            java.lang.String r2 = io.ktor.server.engine.LongKt.toStringFast(r5)
            r1.append(r4, r2, r3)
            goto L_0x0080
        L_0x0055:
            boolean r1 = r1.element
            if (r1 != 0) goto L_0x0080
            boolean r1 = r8 instanceof io.ktor.http.content.OutgoingContent.ProtocolUpgrade
            if (r1 != 0) goto L_0x0080
            boolean r1 = r8 instanceof io.ktor.http.content.OutgoingContent.NoContent
            if (r1 == 0) goto L_0x0071
            io.ktor.server.response.ResponseHeaders r1 = r7.getHeaders()
            io.ktor.http.HttpHeaders r2 = io.ktor.http.HttpHeaders.INSTANCE
            java.lang.String r2 = r2.getContentLength()
            java.lang.String r4 = "0"
            r1.append(r2, r4, r3)
            goto L_0x0080
        L_0x0071:
            io.ktor.server.response.ResponseHeaders r1 = r7.getHeaders()
            io.ktor.http.HttpHeaders r2 = io.ktor.http.HttpHeaders.INSTANCE
            java.lang.String r2 = r2.getTransferEncoding()
            java.lang.String r4 = "chunked"
            r1.append(r2, r4, r3)
        L_0x0080:
            io.ktor.http.ContentType r8 = r8.getContentType()
            if (r8 == 0) goto L_0x0097
            io.ktor.server.response.ResponseHeaders r1 = r7.getHeaders()
            io.ktor.http.HttpHeaders r2 = io.ktor.http.HttpHeaders.INSTANCE
            java.lang.String r2 = r2.getContentType()
            java.lang.String r8 = r8.toString()
            r1.append(r2, r8, r3)
        L_0x0097:
            io.ktor.server.application.ApplicationCall r8 = r7.call
            io.ktor.server.request.ApplicationRequest r8 = r8.getRequest()
            io.ktor.http.Headers r8 = r8.getHeaders()
            io.ktor.http.HttpHeaders r1 = io.ktor.http.HttpHeaders.INSTANCE
            java.lang.String r1 = r1.getConnection()
            java.lang.String r8 = r8.get(r1)
            if (r8 == 0) goto L_0x00e2
            io.ktor.server.application.ApplicationCall r1 = r7.call
            io.ktor.server.response.ApplicationResponse r1 = r1.getResponse()
            io.ktor.server.response.ResponseHeaders r1 = r1.getHeaders()
            io.ktor.http.HttpHeaders r2 = io.ktor.http.HttpHeaders.INSTANCE
            java.lang.String r2 = r2.getConnection()
            boolean r1 = r1.contains(r2)
            if (r1 != 0) goto L_0x00e2
            java.lang.String r1 = "close"
            boolean r2 = kotlin.text.StringsKt.equals(r8, r1, r0)
            java.lang.String r3 = "Connection"
            if (r2 == 0) goto L_0x00d4
            r8 = r7
            io.ktor.server.response.ApplicationResponse r8 = (io.ktor.server.response.ApplicationResponse) r8
            io.ktor.server.response.ApplicationResponsePropertiesKt.header((io.ktor.server.response.ApplicationResponse) r8, (java.lang.String) r3, (java.lang.String) r1)
            goto L_0x00e2
        L_0x00d4:
            java.lang.String r1 = "keep-alive"
            boolean r8 = kotlin.text.StringsKt.equals(r8, r1, r0)
            if (r8 == 0) goto L_0x00e2
            r8 = r7
            io.ktor.server.response.ApplicationResponse r8 = (io.ktor.server.response.ApplicationResponse) r8
            io.ktor.server.response.ApplicationResponsePropertiesKt.header((io.ktor.server.response.ApplicationResponse) r8, (java.lang.String) r3, (java.lang.String) r1)
        L_0x00e2:
            return
        L_0x00e3:
            io.ktor.server.engine.BaseApplicationResponse$ResponseAlreadySentException r8 = new io.ktor.server.engine.BaseApplicationResponse$ResponseAlreadySentException
            r8.<init>()
            goto L_0x00ea
        L_0x00e9:
            throw r8
        L_0x00ea:
            goto L_0x00e9
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.engine.BaseApplicationResponse.commitHeaders(io.ktor.http.content.OutgoingContent):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0053  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0028 A[ADDED_TO_REGION] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static /* synthetic */ java.lang.Object respondOutgoingContent$suspendImpl(io.ktor.server.engine.BaseApplicationResponse r8, io.ktor.http.content.OutgoingContent r9, kotlin.coroutines.Continuation<? super kotlin.Unit> r10) {
        /*
            boolean r0 = r10 instanceof io.ktor.server.engine.BaseApplicationResponse$respondOutgoingContent$1
            if (r0 == 0) goto L_0x0014
            r0 = r10
            io.ktor.server.engine.BaseApplicationResponse$respondOutgoingContent$1 r0 = (io.ktor.server.engine.BaseApplicationResponse$respondOutgoingContent$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r10 = r0.label
            int r10 = r10 - r2
            r0.label = r10
            goto L_0x0019
        L_0x0014:
            io.ktor.server.engine.BaseApplicationResponse$respondOutgoingContent$1 r0 = new io.ktor.server.engine.BaseApplicationResponse$respondOutgoingContent$1
            r0.<init>(r8, r10)
        L_0x0019:
            java.lang.Object r10 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 5
            r4 = 4
            r5 = 3
            r6 = 2
            r7 = 1
            if (r2 == 0) goto L_0x0053
            if (r2 == r7) goto L_0x004a
            if (r2 == r6) goto L_0x004a
            if (r2 == r5) goto L_0x004a
            if (r2 == r4) goto L_0x003b
            if (r2 != r3) goto L_0x0033
            goto L_0x004a
        L_0x0033:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L_0x003b:
            java.lang.Object r8 = r0.L$1
            io.ktor.utils.io.ByteReadChannel r8 = (io.ktor.utils.io.ByteReadChannel) r8
            java.lang.Object r9 = r0.L$0
            io.ktor.server.engine.BaseApplicationResponse r9 = (io.ktor.server.engine.BaseApplicationResponse) r9
            kotlin.ResultKt.throwOnFailure(r10)     // Catch:{ all -> 0x0047 }
            goto L_0x00b4
        L_0x0047:
            r9 = move-exception
            goto L_0x00bb
        L_0x004a:
            java.lang.Object r8 = r0.L$0
            io.ktor.server.engine.BaseApplicationResponse r8 = (io.ktor.server.engine.BaseApplicationResponse) r8
            kotlin.ResultKt.throwOnFailure(r10)
            goto L_0x00d3
        L_0x0053:
            kotlin.ResultKt.throwOnFailure(r10)
            boolean r10 = r9 instanceof io.ktor.http.content.OutgoingContent.ProtocolUpgrade
            if (r10 == 0) goto L_0x006a
            r8.commitHeaders(r9)
            io.ktor.http.content.OutgoingContent$ProtocolUpgrade r9 = (io.ktor.http.content.OutgoingContent.ProtocolUpgrade) r9
            r0.L$0 = r8
            r0.label = r7
            java.lang.Object r9 = r8.respondUpgrade(r9, r0)
            if (r9 != r1) goto L_0x00d3
            return r1
        L_0x006a:
            boolean r10 = r9 instanceof io.ktor.http.content.OutgoingContent.ByteArrayContent
            if (r10 == 0) goto L_0x0083
            r10 = r9
            io.ktor.http.content.OutgoingContent$ByteArrayContent r10 = (io.ktor.http.content.OutgoingContent.ByteArrayContent) r10
            byte[] r10 = r10.bytes()
            r8.commitHeaders(r9)
            r0.L$0 = r8
            r0.label = r6
            java.lang.Object r9 = r8.respondFromBytes(r10, r0)
            if (r9 != r1) goto L_0x00d3
            return r1
        L_0x0083:
            boolean r10 = r9 instanceof io.ktor.http.content.OutgoingContent.WriteChannelContent
            if (r10 == 0) goto L_0x0097
            r8.commitHeaders(r9)
            io.ktor.http.content.OutgoingContent$WriteChannelContent r9 = (io.ktor.http.content.OutgoingContent.WriteChannelContent) r9
            r0.L$0 = r8
            r0.label = r5
            java.lang.Object r9 = r8.respondWriteChannelContent(r9, r0)
            if (r9 != r1) goto L_0x00d3
            return r1
        L_0x0097:
            boolean r10 = r9 instanceof io.ktor.http.content.OutgoingContent.ReadChannelContent
            if (r10 == 0) goto L_0x00bf
            r10 = r9
            io.ktor.http.content.OutgoingContent$ReadChannelContent r10 = (io.ktor.http.content.OutgoingContent.ReadChannelContent) r10
            io.ktor.utils.io.ByteReadChannel r10 = r10.readFrom()
            r8.commitHeaders(r9)     // Catch:{ all -> 0x00b9 }
            r0.L$0 = r8     // Catch:{ all -> 0x00b9 }
            r0.L$1 = r10     // Catch:{ all -> 0x00b9 }
            r0.label = r4     // Catch:{ all -> 0x00b9 }
            java.lang.Object r9 = r8.respondFromChannel(r10, r0)     // Catch:{ all -> 0x00b9 }
            if (r9 != r1) goto L_0x00b2
            return r1
        L_0x00b2:
            r9 = r8
            r8 = r10
        L_0x00b4:
            io.ktor.utils.io.ByteReadChannelKt.cancel(r8)
            r8 = r9
            goto L_0x00d3
        L_0x00b9:
            r9 = move-exception
            r8 = r10
        L_0x00bb:
            io.ktor.utils.io.ByteReadChannelKt.cancel(r8)
            throw r9
        L_0x00bf:
            boolean r10 = r9 instanceof io.ktor.http.content.OutgoingContent.NoContent
            if (r10 == 0) goto L_0x00d3
            r8.commitHeaders(r9)
            io.ktor.http.content.OutgoingContent$NoContent r9 = (io.ktor.http.content.OutgoingContent.NoContent) r9
            r0.L$0 = r8
            r0.label = r3
            java.lang.Object r9 = r8.respondNoContent(r9, r0)
            if (r9 != r1) goto L_0x00d3
            return r1
        L_0x00d3:
            r8.isSent = r7
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.engine.BaseApplicationResponse.respondOutgoingContent$suspendImpl(io.ktor.server.engine.BaseApplicationResponse, io.ktor.http.content.OutgoingContent, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v6, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v7, resolved type: io.ktor.http.content.OutgoingContent$WriteChannelContent} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0047  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0071 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static /* synthetic */ java.lang.Object respondWriteChannelContent$suspendImpl(io.ktor.server.engine.BaseApplicationResponse r6, io.ktor.http.content.OutgoingContent.WriteChannelContent r7, kotlin.coroutines.Continuation<? super kotlin.Unit> r8) {
        /*
            boolean r0 = r8 instanceof io.ktor.server.engine.BaseApplicationResponse$respondWriteChannelContent$1
            if (r0 == 0) goto L_0x0014
            r0 = r8
            io.ktor.server.engine.BaseApplicationResponse$respondWriteChannelContent$1 r0 = (io.ktor.server.engine.BaseApplicationResponse$respondWriteChannelContent$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L_0x0019
        L_0x0014:
            io.ktor.server.engine.BaseApplicationResponse$respondWriteChannelContent$1 r0 = new io.ktor.server.engine.BaseApplicationResponse$respondWriteChannelContent$1
            r0.<init>(r6, r8)
        L_0x0019:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 0
            r5 = 1
            if (r2 == 0) goto L_0x0047
            if (r2 == r5) goto L_0x003e
            if (r2 != r3) goto L_0x0036
            java.lang.Object r6 = r0.L$0
            io.ktor.utils.io.ByteWriteChannel r6 = (io.ktor.utils.io.ByteWriteChannel) r6
            kotlin.ResultKt.throwOnFailure(r8)     // Catch:{ ClosedWriteChannelException -> 0x0034 }
            goto L_0x0072
        L_0x0032:
            r7 = move-exception
            goto L_0x0080
        L_0x0034:
            r7 = move-exception
            goto L_0x0078
        L_0x0036:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x003e:
            java.lang.Object r6 = r0.L$0
            r7 = r6
            io.ktor.http.content.OutgoingContent$WriteChannelContent r7 = (io.ktor.http.content.OutgoingContent.WriteChannelContent) r7
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x0055
        L_0x0047:
            kotlin.ResultKt.throwOnFailure(r8)
            r0.L$0 = r7
            r0.label = r5
            java.lang.Object r8 = r6.responseChannel(r0)
            if (r8 != r1) goto L_0x0055
            return r1
        L_0x0055:
            r6 = r8
            io.ktor.utils.io.ByteWriteChannel r6 = (io.ktor.utils.io.ByteWriteChannel) r6
            kotlinx.coroutines.Dispatchers r8 = kotlinx.coroutines.Dispatchers.INSTANCE     // Catch:{ ClosedWriteChannelException -> 0x0034 }
            kotlinx.coroutines.CoroutineDispatcher r8 = io.ktor.server.engine.internal.ApplicationUtilsJvmKt.getIOBridge(r8)     // Catch:{ ClosedWriteChannelException -> 0x0034 }
            kotlin.coroutines.CoroutineContext r8 = (kotlin.coroutines.CoroutineContext) r8     // Catch:{ ClosedWriteChannelException -> 0x0034 }
            io.ktor.server.engine.BaseApplicationResponse$respondWriteChannelContent$2$1 r2 = new io.ktor.server.engine.BaseApplicationResponse$respondWriteChannelContent$2$1     // Catch:{ ClosedWriteChannelException -> 0x0034 }
            r2.<init>(r7, r6, r4)     // Catch:{ ClosedWriteChannelException -> 0x0034 }
            kotlin.jvm.functions.Function2 r2 = (kotlin.jvm.functions.Function2) r2     // Catch:{ ClosedWriteChannelException -> 0x0034 }
            r0.L$0 = r6     // Catch:{ ClosedWriteChannelException -> 0x0034 }
            r0.label = r3     // Catch:{ ClosedWriteChannelException -> 0x0034 }
            java.lang.Object r7 = kotlinx.coroutines.BuildersKt.withContext(r8, r2, r0)     // Catch:{ ClosedWriteChannelException -> 0x0034 }
            if (r7 != r1) goto L_0x0072
            return r1
        L_0x0072:
            io.ktor.utils.io.ByteWriteChannelKt.close(r6)
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        L_0x0078:
            io.ktor.util.cio.ChannelWriteException r8 = new io.ktor.util.cio.ChannelWriteException     // Catch:{ all -> 0x0032 }
            java.lang.Throwable r7 = (java.lang.Throwable) r7     // Catch:{ all -> 0x0032 }
            r8.<init>(r4, r7, r5, r4)     // Catch:{ all -> 0x0032 }
            throw r8     // Catch:{ all -> 0x0032 }
        L_0x0080:
            r6.close(r7)     // Catch:{ all -> 0x0084 }
            throw r7     // Catch:{ all -> 0x0084 }
        L_0x0084:
            r7 = move-exception
            io.ktor.utils.io.ByteWriteChannelKt.close(r6)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.engine.BaseApplicationResponse.respondWriteChannelContent$suspendImpl(io.ktor.server.engine.BaseApplicationResponse, io.ktor.http.content.OutgoingContent$WriteChannelContent, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v6, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v5, resolved type: byte[]} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0044  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0086 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static /* synthetic */ java.lang.Object respondFromBytes$suspendImpl(io.ktor.server.engine.BaseApplicationResponse r9, byte[] r10, kotlin.coroutines.Continuation<? super kotlin.Unit> r11) {
        /*
            boolean r0 = r11 instanceof io.ktor.server.engine.BaseApplicationResponse$respondFromBytes$1
            if (r0 == 0) goto L_0x0014
            r0 = r11
            io.ktor.server.engine.BaseApplicationResponse$respondFromBytes$1 r0 = (io.ktor.server.engine.BaseApplicationResponse$respondFromBytes$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r11 = r0.label
            int r11 = r11 - r2
            r0.label = r11
            goto L_0x0019
        L_0x0014:
            io.ktor.server.engine.BaseApplicationResponse$respondFromBytes$1 r0 = new io.ktor.server.engine.BaseApplicationResponse$respondFromBytes$1
            r0.<init>(r9, r11)
        L_0x0019:
            java.lang.Object r11 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x0044
            if (r2 == r4) goto L_0x003b
            if (r2 != r3) goto L_0x0033
            java.lang.Object r9 = r0.L$0
            io.ktor.utils.io.ByteWriteChannel r9 = (io.ktor.utils.io.ByteWriteChannel) r9
            kotlin.ResultKt.throwOnFailure(r11)     // Catch:{ all -> 0x0031 }
            goto L_0x0087
        L_0x0031:
            r10 = move-exception
            goto L_0x008d
        L_0x0033:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L_0x003b:
            java.lang.Object r9 = r0.L$0
            r10 = r9
            byte[] r10 = (byte[]) r10
            kotlin.ResultKt.throwOnFailure(r11)
            goto L_0x006b
        L_0x0044:
            kotlin.ResultKt.throwOnFailure(r11)
            io.ktor.server.response.ResponseHeaders r11 = r9.getHeaders()
            io.ktor.http.HttpHeaders r2 = io.ktor.http.HttpHeaders.INSTANCE
            java.lang.String r2 = r2.getContentLength()
            java.lang.String r11 = r11.get(r2)
            if (r11 == 0) goto L_0x0060
            long r5 = java.lang.Long.parseLong(r11)
            int r11 = r10.length
            long r7 = (long) r11
            r9.ensureLength(r5, r7)
        L_0x0060:
            r0.L$0 = r10
            r0.label = r4
            java.lang.Object r11 = r9.responseChannel(r0)
            if (r11 != r1) goto L_0x006b
            return r1
        L_0x006b:
            r9 = r11
            io.ktor.utils.io.ByteWriteChannel r9 = (io.ktor.utils.io.ByteWriteChannel) r9
            kotlinx.coroutines.CoroutineDispatcher r11 = kotlinx.coroutines.Dispatchers.getUnconfined()     // Catch:{ all -> 0x0031 }
            kotlin.coroutines.CoroutineContext r11 = (kotlin.coroutines.CoroutineContext) r11     // Catch:{ all -> 0x0031 }
            io.ktor.server.engine.BaseApplicationResponse$respondFromBytes$3$1 r2 = new io.ktor.server.engine.BaseApplicationResponse$respondFromBytes$3$1     // Catch:{ all -> 0x0031 }
            r4 = 0
            r2.<init>(r9, r10, r4)     // Catch:{ all -> 0x0031 }
            kotlin.jvm.functions.Function2 r2 = (kotlin.jvm.functions.Function2) r2     // Catch:{ all -> 0x0031 }
            r0.L$0 = r9     // Catch:{ all -> 0x0031 }
            r0.label = r3     // Catch:{ all -> 0x0031 }
            java.lang.Object r10 = kotlinx.coroutines.BuildersKt.withContext(r11, r2, r0)     // Catch:{ all -> 0x0031 }
            if (r10 != r1) goto L_0x0087
            return r1
        L_0x0087:
            io.ktor.utils.io.ByteWriteChannelKt.close(r9)
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        L_0x008d:
            r9.close(r10)     // Catch:{ all -> 0x0091 }
            throw r10     // Catch:{ all -> 0x0091 }
        L_0x0091:
            r10 = move-exception
            io.ktor.utils.io.ByteWriteChannelKt.close(r9)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.engine.BaseApplicationResponse.respondFromBytes$suspendImpl(io.ktor.server.engine.BaseApplicationResponse, byte[], kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v11, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v3, resolved type: io.ktor.utils.io.ByteReadChannel} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v5, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v10, resolved type: io.ktor.utils.io.ByteWriteChannel} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0071  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0094 A[Catch:{ all -> 0x0061 }] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x009d A[Catch:{ all -> 0x0061 }] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00bb A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00bc  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00c8 A[Catch:{ all -> 0x00f9 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0027  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static /* synthetic */ java.lang.Object respondFromChannel$suspendImpl(io.ktor.server.engine.BaseApplicationResponse r10, io.ktor.utils.io.ByteReadChannel r11, kotlin.coroutines.Continuation<? super kotlin.Unit> r12) {
        /*
            boolean r0 = r12 instanceof io.ktor.server.engine.BaseApplicationResponse$respondFromChannel$1
            if (r0 == 0) goto L_0x0014
            r0 = r12
            io.ktor.server.engine.BaseApplicationResponse$respondFromChannel$1 r0 = (io.ktor.server.engine.BaseApplicationResponse$respondFromChannel$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r12 = r0.label
            int r12 = r12 - r2
            r0.label = r12
            goto L_0x0019
        L_0x0014:
            io.ktor.server.engine.BaseApplicationResponse$respondFromChannel$1 r0 = new io.ktor.server.engine.BaseApplicationResponse$respondFromChannel$1
            r0.<init>(r10, r12)
        L_0x0019:
            java.lang.Object r12 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 3
            r4 = 2
            r5 = 1
            r6 = 0
            if (r2 == 0) goto L_0x0071
            if (r2 == r5) goto L_0x0064
            if (r2 == r4) goto L_0x0048
            if (r2 != r3) goto L_0x0040
            long r10 = r0.J$0
            java.lang.Object r1 = r0.L$2
            java.lang.Long r1 = (java.lang.Long) r1
            java.lang.Object r2 = r0.L$1
            io.ktor.utils.io.ByteWriteChannel r2 = (io.ktor.utils.io.ByteWriteChannel) r2
            java.lang.Object r0 = r0.L$0
            io.ktor.server.engine.BaseApplicationResponse r0 = (io.ktor.server.engine.BaseApplicationResponse) r0
            kotlin.ResultKt.throwOnFailure(r12)     // Catch:{ all -> 0x0061 }
            goto L_0x00e4
        L_0x0040:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L_0x0048:
            java.lang.Object r10 = r0.L$3
            java.lang.Long r10 = (java.lang.Long) r10
            java.lang.Object r11 = r0.L$2
            r2 = r11
            io.ktor.utils.io.ByteWriteChannel r2 = (io.ktor.utils.io.ByteWriteChannel) r2
            java.lang.Object r11 = r0.L$1
            io.ktor.utils.io.ByteReadChannel r11 = (io.ktor.utils.io.ByteReadChannel) r11
            java.lang.Object r4 = r0.L$0
            io.ktor.server.engine.BaseApplicationResponse r4 = (io.ktor.server.engine.BaseApplicationResponse) r4
            kotlin.ResultKt.throwOnFailure(r12)     // Catch:{ all -> 0x0061 }
            r9 = r2
            r2 = r10
            r10 = r4
        L_0x005f:
            r4 = r9
            goto L_0x00c0
        L_0x0061:
            r10 = move-exception
            goto L_0x00fb
        L_0x0064:
            java.lang.Object r10 = r0.L$1
            r11 = r10
            io.ktor.utils.io.ByteReadChannel r11 = (io.ktor.utils.io.ByteReadChannel) r11
            java.lang.Object r10 = r0.L$0
            io.ktor.server.engine.BaseApplicationResponse r10 = (io.ktor.server.engine.BaseApplicationResponse) r10
            kotlin.ResultKt.throwOnFailure(r12)
            goto L_0x0081
        L_0x0071:
            kotlin.ResultKt.throwOnFailure(r12)
            r0.L$0 = r10
            r0.L$1 = r11
            r0.label = r5
            java.lang.Object r12 = r10.responseChannel(r0)
            if (r12 != r1) goto L_0x0081
            return r1
        L_0x0081:
            r2 = r12
            io.ktor.utils.io.ByteWriteChannel r2 = (io.ktor.utils.io.ByteWriteChannel) r2
            io.ktor.server.response.ResponseHeaders r12 = r10.getHeaders()     // Catch:{ all -> 0x0061 }
            io.ktor.http.HttpHeaders r5 = io.ktor.http.HttpHeaders.INSTANCE     // Catch:{ all -> 0x0061 }
            java.lang.String r5 = r5.getContentLength()     // Catch:{ all -> 0x0061 }
            java.lang.String r12 = r12.get(r5)     // Catch:{ all -> 0x0061 }
            if (r12 == 0) goto L_0x009d
            long r7 = java.lang.Long.parseLong(r12)     // Catch:{ all -> 0x0061 }
            java.lang.Long r12 = kotlin.coroutines.jvm.internal.Boxing.boxLong(r7)     // Catch:{ all -> 0x0061 }
            goto L_0x009e
        L_0x009d:
            r12 = r6
        L_0x009e:
            kotlinx.coroutines.CoroutineDispatcher r5 = kotlinx.coroutines.Dispatchers.getUnconfined()     // Catch:{ all -> 0x0061 }
            kotlin.coroutines.CoroutineContext r5 = (kotlin.coroutines.CoroutineContext) r5     // Catch:{ all -> 0x0061 }
            io.ktor.server.engine.BaseApplicationResponse$respondFromChannel$2$copied$1 r7 = new io.ktor.server.engine.BaseApplicationResponse$respondFromChannel$2$copied$1     // Catch:{ all -> 0x0061 }
            r7.<init>(r11, r2, r12, r6)     // Catch:{ all -> 0x0061 }
            kotlin.jvm.functions.Function2 r7 = (kotlin.jvm.functions.Function2) r7     // Catch:{ all -> 0x0061 }
            r0.L$0 = r10     // Catch:{ all -> 0x0061 }
            r0.L$1 = r11     // Catch:{ all -> 0x0061 }
            r0.L$2 = r2     // Catch:{ all -> 0x0061 }
            r0.L$3 = r12     // Catch:{ all -> 0x0061 }
            r0.label = r4     // Catch:{ all -> 0x0061 }
            java.lang.Object r4 = kotlinx.coroutines.BuildersKt.withContext(r5, r7, r0)     // Catch:{ all -> 0x0061 }
            if (r4 != r1) goto L_0x00bc
            return r1
        L_0x00bc:
            r9 = r2
            r2 = r12
            r12 = r4
            goto L_0x005f
        L_0x00c0:
            java.lang.Number r12 = (java.lang.Number) r12     // Catch:{ all -> 0x00f9 }
            long r7 = r12.longValue()     // Catch:{ all -> 0x00f9 }
            if (r2 == 0) goto L_0x00f3
            r2.longValue()     // Catch:{ all -> 0x00f9 }
            r0.L$0 = r10     // Catch:{ all -> 0x00f9 }
            r0.L$1 = r4     // Catch:{ all -> 0x00f9 }
            r0.L$2 = r2     // Catch:{ all -> 0x00f9 }
            r0.L$3 = r6     // Catch:{ all -> 0x00f9 }
            r0.J$0 = r7     // Catch:{ all -> 0x00f9 }
            r0.label = r3     // Catch:{ all -> 0x00f9 }
            r5 = 1
            java.lang.Object r12 = r11.discard(r5, r0)     // Catch:{ all -> 0x00f9 }
            if (r12 != r1) goto L_0x00e0
            return r1
        L_0x00e0:
            r0 = r10
            r1 = r2
            r2 = r4
            r10 = r7
        L_0x00e4:
            java.lang.Number r12 = (java.lang.Number) r12     // Catch:{ all -> 0x0061 }
            long r3 = r12.longValue()     // Catch:{ all -> 0x0061 }
            long r5 = r1.longValue()     // Catch:{ all -> 0x0061 }
            long r10 = r10 + r3
            r0.ensureLength(r5, r10)     // Catch:{ all -> 0x0061 }
            r4 = r2
        L_0x00f3:
            io.ktor.utils.io.ByteWriteChannelKt.close(r4)
            kotlin.Unit r10 = kotlin.Unit.INSTANCE
            return r10
        L_0x00f9:
            r10 = move-exception
            r2 = r4
        L_0x00fb:
            r2.close(r10)     // Catch:{ all -> 0x00ff }
            throw r10     // Catch:{ all -> 0x00ff }
        L_0x00ff:
            r10 = move-exception
            io.ktor.utils.io.ByteWriteChannelKt.close(r2)
            goto L_0x0105
        L_0x0104:
            throw r10
        L_0x0105:
            goto L_0x0104
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.engine.BaseApplicationResponse.respondFromChannel$suspendImpl(io.ktor.server.engine.BaseApplicationResponse, io.ktor.utils.io.ByteReadChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final void ensureLength(long j, long j2) {
        if (j < j2) {
            throw new BodyLengthIsTooLong(j);
        } else if (j > j2) {
            throw new BodyLengthIsTooSmall(j, j2);
        }
    }

    @UseHttp2Push
    public void push(ResponsePushBuilder responsePushBuilder) {
        Intrinsics.checkNotNullParameter(responsePushBuilder, "builder");
        LinkHeaderKt.link(this, responsePushBuilder.getUrl().buildString(), LinkHeader.Rel.Prefetch);
    }

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00060\u0001j\u0002`\u0002B\u0005¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lio/ktor/server/engine/BaseApplicationResponse$ResponseAlreadySentException;", "Ljava/lang/IllegalStateException;", "Lkotlin/IllegalStateException;", "()V", "ktor-server-host-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: BaseApplicationResponse.kt */
    public static final class ResponseAlreadySentException extends IllegalStateException {
        public ResponseAlreadySentException() {
            super("Response has already been sent");
        }
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u00002\u00060\u0001j\u0002`\u00022\b\u0012\u0004\u0012\u00020\u00000\u0003B\u0015\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007J\b\u0010\b\u001a\u00020\u0000H\u0016R\u000e\u0010\u0006\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lio/ktor/server/engine/BaseApplicationResponse$InvalidHeaderForContent;", "Ljava/lang/IllegalStateException;", "Lkotlin/IllegalStateException;", "Lkotlinx/coroutines/CopyableThrowable;", "name", "", "content", "(Ljava/lang/String;Ljava/lang/String;)V", "createCopy", "ktor-server-host-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: BaseApplicationResponse.kt */
    public static final class InvalidHeaderForContent extends IllegalStateException implements CopyableThrowable<InvalidHeaderForContent> {
        private final String content;
        private final String name;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public InvalidHeaderForContent(String str, String str2) {
            super("Header " + str + " is not allowed for " + str2);
            Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
            Intrinsics.checkNotNullParameter(str2, "content");
            this.name = str;
            this.content = str2;
        }

        public InvalidHeaderForContent createCopy() {
            InvalidHeaderForContent invalidHeaderForContent = new InvalidHeaderForContent(this.name, this.content);
            ExceptionUtilsJvmKt.initCauseBridge(invalidHeaderForContent, this);
            return invalidHeaderForContent;
        }
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\u0018\u00002\u00060\u0001j\u0002`\u00022\b\u0012\u0004\u0012\u00020\u00000\u0003B\u0015\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007J\b\u0010\b\u001a\u00020\u0000H\u0016R\u000e\u0010\u0006\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lio/ktor/server/engine/BaseApplicationResponse$BodyLengthIsTooSmall;", "Ljava/lang/IllegalStateException;", "Lkotlin/IllegalStateException;", "Lkotlinx/coroutines/CopyableThrowable;", "expected", "", "actual", "(JJ)V", "createCopy", "ktor-server-host-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: BaseApplicationResponse.kt */
    public static final class BodyLengthIsTooSmall extends IllegalStateException implements CopyableThrowable<BodyLengthIsTooSmall> {
        private final long actual;
        private final long expected;

        public BodyLengthIsTooSmall(long j, long j2) {
            super("Body.size is too small. Body: " + j2 + ", Content-Length: " + j);
            this.expected = j;
            this.actual = j2;
        }

        public BodyLengthIsTooSmall createCopy() {
            BodyLengthIsTooSmall bodyLengthIsTooSmall = new BodyLengthIsTooSmall(this.expected, this.actual);
            ExceptionUtilsJvmKt.initCauseBridge(bodyLengthIsTooSmall, this);
            return bodyLengthIsTooSmall;
        }
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\u0018\u00002\u00060\u0001j\u0002`\u00022\b\u0012\u0004\u0012\u00020\u00000\u0003B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001a\u00020\u0000H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lio/ktor/server/engine/BaseApplicationResponse$BodyLengthIsTooLong;", "Ljava/lang/IllegalStateException;", "Lkotlin/IllegalStateException;", "Lkotlinx/coroutines/CopyableThrowable;", "expected", "", "(J)V", "createCopy", "ktor-server-host-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: BaseApplicationResponse.kt */
    public static final class BodyLengthIsTooLong extends IllegalStateException implements CopyableThrowable<BodyLengthIsTooLong> {
        private final long expected;

        public BodyLengthIsTooLong(long j) {
            super("Body.size is too long. Expected " + j);
            this.expected = j;
        }

        public BodyLengthIsTooLong createCopy() {
            BodyLengthIsTooLong bodyLengthIsTooLong = new BodyLengthIsTooLong(this.expected);
            ExceptionUtilsJvmKt.initCauseBridge(bodyLengthIsTooLong, this);
            return bodyLengthIsTooLong;
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bR\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\f"}, d2 = {"Lio/ktor/server/engine/BaseApplicationResponse$Companion;", "", "()V", "EngineResponseAttributeKey", "Lio/ktor/util/AttributeKey;", "Lio/ktor/server/engine/BaseApplicationResponse;", "getEngineResponseAttributeKey", "()Lio/ktor/util/AttributeKey;", "setupSendPipeline", "", "sendPipeline", "Lio/ktor/server/response/ApplicationSendPipeline;", "ktor-server-host-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: BaseApplicationResponse.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final AttributeKey<BaseApplicationResponse> getEngineResponseAttributeKey() {
            return BaseApplicationResponse.EngineResponseAttributeKey;
        }

        public final void setupSendPipeline(ApplicationSendPipeline applicationSendPipeline) {
            Intrinsics.checkNotNullParameter(applicationSendPipeline, "sendPipeline");
            applicationSendPipeline.intercept(ApplicationSendPipeline.Phases.getEngine(), new BaseApplicationResponse$Companion$setupSendPipeline$1((Continuation<? super BaseApplicationResponse$Companion$setupSendPipeline$1>) null));
        }
    }
}
