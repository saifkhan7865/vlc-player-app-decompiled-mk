package io.ktor.server.http.content;

import androidx.leanback.preference.LeanbackPreferenceDialogFragment;
import io.ktor.http.ContentType;
import io.ktor.http.Headers;
import io.ktor.http.HttpMethod;
import io.ktor.http.HttpStatusCode;
import io.ktor.http.content.OutgoingContent;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.application.RouteScopedPluginBuilder;
import io.ktor.server.application.hooks.ResponseBodyReadyForSend;
import io.ktor.util.AttributeKey;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00010\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "Lio/ktor/server/application/RouteScopedPluginBuilder;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: StaticContent.kt */
final class StaticContentKt$StaticContentAutoHead$1 extends Lambda implements Function1<RouteScopedPluginBuilder<Unit>, Unit> {
    public static final StaticContentKt$StaticContentAutoHead$1 INSTANCE = new StaticContentKt$StaticContentAutoHead$1();

    StaticContentKt$StaticContentAutoHead$1() {
        super(1);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((RouteScopedPluginBuilder<Unit>) (RouteScopedPluginBuilder) obj);
        return Unit.INSTANCE;
    }

    @Metadata(d1 = {"\u0000I\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J'\u0010\u0017\u001a\u0004\u0018\u0001H\u0018\"\b\b\u0000\u0010\u0018*\u00020\u00192\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u0002H\u00180\u001bH\u0016¢\u0006\u0002\u0010\u001cJ/\u0010\u001d\u001a\u00020\u001e\"\b\b\u0000\u0010\u0018*\u00020\u00192\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u0002H\u00180\u001b2\b\u0010\u001f\u001a\u0004\u0018\u0001H\u0018H\u0016¢\u0006\u0002\u0010 R\u0016\u0010\u0005\u001a\u0004\u0018\u00010\u00068VX\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0016\u0010\t\u001a\u0004\u0018\u00010\n8VX\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\u000e8VX\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0016\u0010\u0013\u001a\u0004\u0018\u00010\u00148VX\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016¨\u0006!"}, d2 = {"io/ktor/server/http/content/StaticContentKt$StaticContentAutoHead$1$HeadResponse", "Lio/ktor/http/content/OutgoingContent$NoContent;", "original", "Lio/ktor/http/content/OutgoingContent;", "(Lio/ktor/http/content/OutgoingContent;)V", "contentLength", "", "getContentLength", "()Ljava/lang/Long;", "contentType", "Lio/ktor/http/ContentType;", "getContentType", "()Lio/ktor/http/ContentType;", "headers", "Lio/ktor/http/Headers;", "getHeaders", "()Lio/ktor/http/Headers;", "getOriginal", "()Lio/ktor/http/content/OutgoingContent;", "status", "Lio/ktor/http/HttpStatusCode;", "getStatus", "()Lio/ktor/http/HttpStatusCode;", "getProperty", "T", "", "key", "Lio/ktor/util/AttributeKey;", "(Lio/ktor/util/AttributeKey;)Ljava/lang/Object;", "setProperty", "", "value", "(Lio/ktor/util/AttributeKey;Ljava/lang/Object;)V", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: StaticContent.kt */
    public static final class HeadResponse extends OutgoingContent.NoContent {
        private final OutgoingContent original;

        public HeadResponse(OutgoingContent outgoingContent) {
            Intrinsics.checkNotNullParameter(outgoingContent, "original");
            this.original = outgoingContent;
        }

        public final OutgoingContent getOriginal() {
            return this.original;
        }

        public HttpStatusCode getStatus() {
            return this.original.getStatus();
        }

        public ContentType getContentType() {
            return this.original.getContentType();
        }

        public Long getContentLength() {
            return this.original.getContentLength();
        }

        public <T> T getProperty(AttributeKey<T> attributeKey) {
            Intrinsics.checkNotNullParameter(attributeKey, LeanbackPreferenceDialogFragment.ARG_KEY);
            return this.original.getProperty(attributeKey);
        }

        public <T> void setProperty(AttributeKey<T> attributeKey, T t) {
            Intrinsics.checkNotNullParameter(attributeKey, LeanbackPreferenceDialogFragment.ARG_KEY);
            this.original.setProperty(attributeKey, t);
        }

        public Headers getHeaders() {
            return this.original.getHeaders();
        }
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H@"}, d2 = {"<anonymous>", "", "Lio/ktor/server/application/hooks/ResponseBodyReadyForSend$Context;", "call", "Lio/ktor/server/application/ApplicationCall;", "content", "Lio/ktor/http/content/OutgoingContent;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "io.ktor.server.http.content.StaticContentKt$StaticContentAutoHead$1$1", f = "StaticContent.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: io.ktor.server.http.content.StaticContentKt$StaticContentAutoHead$1$1  reason: invalid class name */
    /* compiled from: StaticContent.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function4<ResponseBodyReadyForSend.Context, ApplicationCall, OutgoingContent, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        /* synthetic */ Object L$2;
        int label;

        public final Object invoke(ResponseBodyReadyForSend.Context context, ApplicationCall applicationCall, OutgoingContent outgoingContent, Continuation<? super Unit> continuation) {
            AnonymousClass1 r0 = new AnonymousClass1(continuation);
            r0.L$0 = context;
            r0.L$1 = applicationCall;
            r0.L$2 = outgoingContent;
            return r0.invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                ResponseBodyReadyForSend.Context context = (ResponseBodyReadyForSend.Context) this.L$0;
                OutgoingContent outgoingContent = (OutgoingContent) this.L$2;
                if (Intrinsics.areEqual((Object) ((ApplicationCall) this.L$1).getRequest().getLocal().getMethod(), (Object) HttpMethod.Companion.getHead())) {
                    if (outgoingContent instanceof OutgoingContent.ReadChannelContent) {
                        ((OutgoingContent.ReadChannelContent) outgoingContent).readFrom().cancel((Throwable) null);
                    }
                    context.transformBodyTo(new HeadResponse(outgoingContent));
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("Check failed.".toString());
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public final void invoke(RouteScopedPluginBuilder<Unit> routeScopedPluginBuilder) {
        Intrinsics.checkNotNullParameter(routeScopedPluginBuilder, "$this$createRouteScopedPlugin");
        routeScopedPluginBuilder.on(ResponseBodyReadyForSend.INSTANCE, new AnonymousClass1((Continuation<? super AnonymousClass1>) null));
    }
}
