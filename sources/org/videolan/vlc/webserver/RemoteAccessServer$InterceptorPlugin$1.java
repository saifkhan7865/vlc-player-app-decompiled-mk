package org.videolan.vlc.webserver;

import io.ktor.server.application.ApplicationCall;
import io.ktor.server.application.OnCallContext;
import io.ktor.server.application.PluginBuilder;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00010\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "Lio/ktor/server/application/PluginBuilder;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: RemoteAccessServer.kt */
final class RemoteAccessServer$InterceptorPlugin$1 extends Lambda implements Function1<PluginBuilder<Unit>, Unit> {
    final /* synthetic */ RemoteAccessServer this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RemoteAccessServer$InterceptorPlugin$1(RemoteAccessServer remoteAccessServer) {
        super(1);
        this.this$0 = remoteAccessServer;
    }

    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00010\u00022\u0006\u0010\u0003\u001a\u00020\u0004H@"}, d2 = {"<anonymous>", "", "Lio/ktor/server/application/OnCallContext;", "call", "Lio/ktor/server/application/ApplicationCall;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "org.videolan.vlc.webserver.RemoteAccessServer$InterceptorPlugin$1$1", f = "RemoteAccessServer.kt", i = {}, l = {275}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: org.videolan.vlc.webserver.RemoteAccessServer$InterceptorPlugin$1$1  reason: invalid class name */
    /* compiled from: RemoteAccessServer.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function3<OnCallContext<Unit>, ApplicationCall, Continuation<? super Unit>, Object> {
        /* synthetic */ Object L$0;
        int label;

        public final Object invoke(OnCallContext<Unit> onCallContext, ApplicationCall applicationCall, Continuation<? super Unit> continuation) {
            AnonymousClass1 r2 = new AnonymousClass1(remoteAccessServer, continuation);
            r2.L$0 = applicationCall;
            return r2.invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:15:0x006c, code lost:
            if (((java.util.List) r5).size() == 0) goto L_0x006e;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final java.lang.Object invokeSuspend(java.lang.Object r10) {
            /*
                r9 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r9.label
                r2 = 1
                if (r1 == 0) goto L_0x001c
                if (r1 != r2) goto L_0x0014
                java.lang.Object r0 = r9.L$0
                io.ktor.http.RequestConnectionPoint r0 = (io.ktor.http.RequestConnectionPoint) r0
                kotlin.ResultKt.throwOnFailure(r10)
                goto L_0x0090
            L_0x0014:
                java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r10.<init>(r0)
                throw r10
            L_0x001c:
                kotlin.ResultKt.throwOnFailure(r10)
                java.lang.Object r10 = r9.L$0
                io.ktor.server.application.ApplicationCall r10 = (io.ktor.server.application.ApplicationCall) r10
                io.ktor.server.request.ApplicationRequest r10 = r10.getRequest()
                io.ktor.http.RequestConnectionPoint r10 = io.ktor.server.plugins.OriginConnectionPointKt.getOrigin(r10)
                org.videolan.vlc.webserver.RemoteAccessServer r1 = r1
                androidx.lifecycle.MutableLiveData r3 = r1._serverConnections
                java.lang.Object r3 = r3.getValue()
                java.util.List r3 = (java.util.List) r3
                if (r3 == 0) goto L_0x006e
                r4 = r3
                java.lang.Iterable r4 = (java.lang.Iterable) r4
                java.util.ArrayList r5 = new java.util.ArrayList
                r5.<init>()
                java.util.Collection r5 = (java.util.Collection) r5
                java.util.Iterator r4 = r4.iterator()
            L_0x0047:
                boolean r6 = r4.hasNext()
                if (r6 == 0) goto L_0x0066
                java.lang.Object r6 = r4.next()
                r7 = r6
                org.videolan.vlc.webserver.RemoteAccessServer$RemoteAccessConnection r7 = (org.videolan.vlc.webserver.RemoteAccessServer.RemoteAccessConnection) r7
                java.lang.String r7 = r7.getIp()
                java.lang.String r8 = r10.getRemoteHost()
                boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7, (java.lang.Object) r8)
                if (r7 == 0) goto L_0x0047
                r5.add(r6)
                goto L_0x0047
            L_0x0066:
                java.util.List r5 = (java.util.List) r5
                int r4 = r5.size()
                if (r4 != 0) goto L_0x0090
            L_0x006e:
                org.videolan.vlc.webserver.RemoteAccessServer$RemoteAccessConnection r4 = new org.videolan.vlc.webserver.RemoteAccessServer$RemoteAccessConnection
                java.lang.String r5 = r10.getRemoteHost()
                r4.<init>(r5)
                kotlinx.coroutines.MainCoroutineDispatcher r5 = kotlinx.coroutines.Dispatchers.getMain()
                kotlin.coroutines.CoroutineContext r5 = (kotlin.coroutines.CoroutineContext) r5
                org.videolan.vlc.webserver.RemoteAccessServer$InterceptorPlugin$1$1$1$2 r6 = new org.videolan.vlc.webserver.RemoteAccessServer$InterceptorPlugin$1$1$1$2
                r7 = 0
                r6.<init>(r1, r3, r4, r7)
                kotlin.jvm.functions.Function2 r6 = (kotlin.jvm.functions.Function2) r6
                r9.L$0 = r10
                r9.label = r2
                java.lang.Object r10 = kotlinx.coroutines.BuildersKt.withContext(r5, r6, r9)
                if (r10 != r0) goto L_0x0090
                return r0
            L_0x0090:
                kotlin.Unit r10 = kotlin.Unit.INSTANCE
                return r10
            */
            throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.webserver.RemoteAccessServer$InterceptorPlugin$1.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((PluginBuilder<Unit>) (PluginBuilder) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(PluginBuilder<Unit> pluginBuilder) {
        Intrinsics.checkNotNullParameter(pluginBuilder, "$this$createApplicationPlugin");
        final RemoteAccessServer remoteAccessServer = this.this$0;
        pluginBuilder.onCall(new AnonymousClass1((Continuation<? super AnonymousClass1>) null));
    }
}
