package io.ktor.server.plugins.callloging;

import io.ktor.events.Events;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.application.ApplicationCallPipeline;
import io.ktor.server.application.ApplicationPlugin;
import io.ktor.server.application.CreatePluginUtilsKt;
import io.ktor.server.application.DefaultApplicationEventsKt;
import io.ktor.server.application.PluginBuilder;
import io.ktor.util.AttributeKey;
import io.netty.handler.codec.rtsp.RtspHeaders;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

@Metadata(d1 = {"\u0000D\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u001a$\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u000b0\u000fH\u0002\u001a&\u0010\u0011\u001a\u00020\u000b*\b\u0012\u0004\u0012\u00020\u00070\u00122\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u000b0\u000fH\u0002\u001a&\u0010\u0015\u001a\u00020\u000b*\b\u0012\u0004\u0012\u00020\u00070\u00122\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u000b0\u000fH\u0002\u001a\u001a\u0010\u0016\u001a\u00020\u0002*\u00020\u00142\u000e\b\u0002\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00020\u0018\"\u001a\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004\"\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u0019"}, d2 = {"CALL_START_TIME", "Lio/ktor/util/AttributeKey;", "", "getCALL_START_TIME", "()Lio/ktor/util/AttributeKey;", "CallLogging", "Lio/ktor/server/application/ApplicationPlugin;", "Lio/ktor/server/plugins/callloging/CallLoggingConfig;", "getCallLogging", "()Lio/ktor/server/application/ApplicationPlugin;", "setupLogging", "", "events", "Lio/ktor/events/Events;", "log", "Lkotlin/Function1;", "", "logCallsWithMDC", "Lio/ktor/server/application/PluginBuilder;", "logSuccess", "Lio/ktor/server/application/ApplicationCall;", "logCompletedCalls", "processingTimeMillis", "clock", "Lkotlin/Function0;", "ktor-server-call-logging"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: CallLogging.kt */
public final class CallLoggingKt {
    private static final AttributeKey<Long> CALL_START_TIME = new AttributeKey<>("CallStartTime");
    private static final ApplicationPlugin<CallLoggingConfig> CallLogging = CreatePluginUtilsKt.createApplicationPlugin("CallLogging", CallLoggingKt$CallLogging$1.INSTANCE, CallLoggingKt$CallLogging$2.INSTANCE);

    public static final AttributeKey<Long> getCALL_START_TIME() {
        return CALL_START_TIME;
    }

    public static /* synthetic */ long processingTimeMillis$default(ApplicationCall applicationCall, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            function0 = CallLoggingKt$processingTimeMillis$1.INSTANCE;
        }
        return processingTimeMillis(applicationCall, function0);
    }

    public static final long processingTimeMillis(ApplicationCall applicationCall, Function0<Long> function0) {
        Intrinsics.checkNotNullParameter(applicationCall, "<this>");
        Intrinsics.checkNotNullParameter(function0, RtspHeaders.Values.CLOCK);
        return function0.invoke().longValue() - ((Number) applicationCall.getAttributes().get(CALL_START_TIME)).longValue();
    }

    public static final ApplicationPlugin<CallLoggingConfig> getCallLogging() {
        return CallLogging;
    }

    /* access modifiers changed from: private */
    public static final void logCompletedCalls(PluginBuilder<CallLoggingConfig> pluginBuilder, Function1<? super ApplicationCall, Unit> function1) {
        pluginBuilder.on(ResponseSent.INSTANCE, new CallLoggingKt$logCompletedCalls$1(function1, (Continuation<? super CallLoggingKt$logCompletedCalls$1>) null));
    }

    /* access modifiers changed from: private */
    public static final void logCallsWithMDC(PluginBuilder<CallLoggingConfig> pluginBuilder, Function1<? super ApplicationCall, Unit> function1) {
        List<MDCEntry> mdcEntries$ktor_server_call_logging = pluginBuilder.getPluginConfig().getMdcEntries$ktor_server_call_logging();
        pluginBuilder.on(MDCHookKt.MDCHook(ApplicationCallPipeline.ApplicationPhase.getMonitoring()), new CallLoggingKt$logCallsWithMDC$1(mdcEntries$ktor_server_call_logging, (Continuation<? super CallLoggingKt$logCallsWithMDC$1>) null));
        pluginBuilder.on(MDCHookKt.MDCHook(ApplicationCallPipeline.ApplicationPhase.getCall()), new CallLoggingKt$logCallsWithMDC$2(mdcEntries$ktor_server_call_logging, (Continuation<? super CallLoggingKt$logCallsWithMDC$2>) null));
        pluginBuilder.on(ResponseSent.INSTANCE, new CallLoggingKt$logCallsWithMDC$3(mdcEntries$ktor_server_call_logging, function1, (Continuation<? super CallLoggingKt$logCallsWithMDC$3>) null));
    }

    /* access modifiers changed from: private */
    public static final void setupLogging(Events events, Function1<? super String, Unit> function1) {
        Function1 callLoggingKt$setupLogging$starting$1 = new CallLoggingKt$setupLogging$starting$1(function1);
        Function1 callLoggingKt$setupLogging$started$1 = new CallLoggingKt$setupLogging$started$1(function1);
        Function1 callLoggingKt$setupLogging$stopping$1 = new CallLoggingKt$setupLogging$stopping$1(function1);
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = CallLoggingKt$setupLogging$stopped$1.INSTANCE;
        objectRef.element = new CallLoggingKt$setupLogging$1(function1, events, callLoggingKt$setupLogging$starting$1, callLoggingKt$setupLogging$started$1, callLoggingKt$setupLogging$stopping$1, objectRef);
        events.subscribe(DefaultApplicationEventsKt.getApplicationStarting(), callLoggingKt$setupLogging$starting$1);
        events.subscribe(DefaultApplicationEventsKt.getApplicationStarted(), callLoggingKt$setupLogging$started$1);
        events.subscribe(DefaultApplicationEventsKt.getApplicationStopping(), callLoggingKt$setupLogging$stopping$1);
        events.subscribe(DefaultApplicationEventsKt.getApplicationStopped(), (Function1) objectRef.element);
    }
}
