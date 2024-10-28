package io.ktor.server.application;

import androidx.leanback.preference.LeanbackPreferenceDialogFragment;
import io.ktor.server.application.debug.DebugPhaseNamesKt;
import io.ktor.server.config.ApplicationConfig;
import io.ktor.server.request.ApplicationReceivePipeline;
import io.ktor.server.response.ApplicationSendPipeline;
import io.ktor.util.AttributeKey;
import io.ktor.util.KtorDsl;
import io.ktor.util.pipeline.PipelineContext;
import io.ktor.util.pipeline.PipelinePhase;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;

@Metadata(d1 = {"\u0000Â\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b'\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002B\u0015\b\u0000\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0002\u0010\u0006J\r\u0010.\u001a\u00020/H\u0000¢\u0006\u0002\b0J'\u00101\u001a\u00020\u0016\"\u0004\b\u0001\u001022\f\u00103\u001a\b\u0012\u0004\u0012\u0002H2042\u0006\u00105\u001a\u0002H2¢\u0006\u0002\u00106JR\u00107\u001a\u00020\u00162B\u00108\u001a>\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000:\u0012\u0013\u0012\u00110;¢\u0006\f\b<\u0012\b\b=\u0012\u0004\b\b(>\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160?\u0012\u0006\u0012\u0004\u0018\u00010\u000209¢\u0006\u0002\b@ø\u0001\u0000¢\u0006\u0002\u0010AJR\u0010B\u001a\u00020\u00162B\u00108\u001a>\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000C\u0012\u0013\u0012\u00110;¢\u0006\f\b<\u0012\b\b=\u0012\u0004\b\b(>\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160?\u0012\u0006\u0012\u0004\u0018\u00010\u000209¢\u0006\u0002\b@ø\u0001\u0000¢\u0006\u0002\u0010AJg\u0010B\u001a\u00020\u00162W\u00108\u001aS\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000C\u0012\u0013\u0012\u00110;¢\u0006\f\b<\u0012\b\b=\u0012\u0004\b\b(>\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b<\u0012\b\b=\u0012\u0004\b\b(E\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160?\u0012\u0006\u0012\u0004\u0018\u00010\u00020D¢\u0006\u0002\b@ø\u0001\u0000¢\u0006\u0002\u0010FJR\u0010G\u001a\u00020\u00162B\u00108\u001a>\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000H\u0012\u0013\u0012\u00110;¢\u0006\f\b<\u0012\b\b=\u0012\u0004\b\b(>\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160?\u0012\u0006\u0012\u0004\u0018\u00010\u000209¢\u0006\u0002\b@ø\u0001\u0000¢\u0006\u0002\u0010AJg\u0010G\u001a\u00020\u00162W\u00108\u001aS\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000H\u0012\u0013\u0012\u00110;¢\u0006\f\b<\u0012\b\b=\u0012\u0004\b\b(>\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b<\u0012\b\b=\u0012\u0004\b\b(E\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160?\u0012\u0006\u0012\u0004\u0018\u00010\u00020D¢\u0006\u0002\b@ø\u0001\u0000¢\u0006\u0002\u0010FJÖ\u0001\u0010I\u001a\u00020\u0016\"\b\b\u0001\u0010J*\u00020\u0002\"\u000e\b\u0002\u0010K*\b\u0012\u0004\u0012\u00028\u00000L2\u0012\u0010M\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002HJ0\t0\b2\u0006\u0010N\u001a\u00020/2\u0006\u0010O\u001a\u00020P23\u0010Q\u001a/\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b<\u0012\b\b=\u0012\u0004\b\b(+\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002HJ\u0012\u0004\u0012\u00020;0S\u0012\u0004\u0012\u0002HK0R2Q\u00108\u001aM\b\u0001\u0012\u0004\u0012\u0002HK\u0012\u0013\u0012\u00110;¢\u0006\f\b<\u0012\b\b=\u0012\u0004\b\b(>\u0012\u0013\u0012\u0011HJ¢\u0006\f\b<\u0012\b\b=\u0012\u0004\b\b(E\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160?\u0012\u0006\u0012\u0004\u0018\u00010\u00020D¢\u0006\u0002\b@H\u0002ø\u0001\u0000¢\u0006\u0002\u0010TJ¸\u0001\u0010U\u001a\u00020\u0016\"\b\b\u0001\u0010J*\u00020\u0002\"\u000e\b\u0002\u0010K*\b\u0012\u0004\u0012\u00028\u00000L2\u0012\u0010M\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002HJ0\t0\b2\u0006\u0010N\u001a\u00020/2\u0006\u0010O\u001a\u00020P23\u0010Q\u001a/\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b<\u0012\b\b=\u0012\u0004\b\b(+\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002HJ\u0012\u0004\u0012\u00020;0S\u0012\u0004\u0012\u0002HK0R23\u00108\u001a/\b\u0001\u0012\u0004\u0012\u0002HK\u0012\u0004\u0012\u00020;\u0012\u0004\u0012\u0002HJ\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160?\u0012\u0006\u0012\u0004\u0018\u00010\u00020D¢\u0006\u0002\b@H\u0002ø\u0001\u0000¢\u0006\u0002\u0010TR$\u0010\u0007\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00020\tj\u0002`\n0\bX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0012\u0010\r\u001a\u00020\u000eX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u00128F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R$\u0010\u0015\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00160\tj\u0002`\u00170\bX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\fR\u0013\u0010\u0019\u001a\u0004\u0018\u00010\u001a8F¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001cR\u001e\u0010\u001d\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u001e0\bX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\fR\u001a\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R$\u0010\"\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00020\tj\u0002`#0\bX\u0004¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\fR$\u0010%\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00020\tj\u0002`\n0\bX\u0004¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\fR\u0012\u0010'\u001a\u00020(X \u0004¢\u0006\u0006\u001a\u0004\b)\u0010*R\u0012\u0010+\u001a\u00028\u0000X¦\u0004¢\u0006\u0006\u001a\u0004\b,\u0010-\u0002\u0004\n\u0002\b\u0019¨\u0006V"}, d2 = {"Lio/ktor/server/application/PluginBuilder;", "PluginConfig", "", "key", "Lio/ktor/util/AttributeKey;", "Lio/ktor/server/application/PluginInstance;", "(Lio/ktor/util/AttributeKey;)V", "afterResponseInterceptions", "", "Lio/ktor/server/application/Interception;", "Lio/ktor/server/application/ResponseInterception;", "getAfterResponseInterceptions$ktor_server_core", "()Ljava/util/List;", "application", "Lio/ktor/server/application/Application;", "getApplication", "()Lio/ktor/server/application/Application;", "applicationConfig", "Lio/ktor/server/config/ApplicationConfig;", "getApplicationConfig", "()Lio/ktor/server/config/ApplicationConfig;", "callInterceptions", "", "Lio/ktor/server/application/CallInterception;", "getCallInterceptions$ktor_server_core", "environment", "Lio/ktor/server/application/ApplicationEnvironment;", "getEnvironment", "()Lio/ktor/server/application/ApplicationEnvironment;", "hooks", "Lio/ktor/server/application/HookHandler;", "getHooks$ktor_server_core", "getKey$ktor_server_core", "()Lio/ktor/util/AttributeKey;", "onReceiveInterceptions", "Lio/ktor/server/application/ReceiveInterception;", "getOnReceiveInterceptions$ktor_server_core", "onResponseInterceptions", "getOnResponseInterceptions$ktor_server_core", "pipeline", "Lio/ktor/server/application/ApplicationCallPipeline;", "getPipeline$ktor_server_core", "()Lio/ktor/server/application/ApplicationCallPipeline;", "pluginConfig", "getPluginConfig", "()Ljava/lang/Object;", "newPhase", "Lio/ktor/util/pipeline/PipelinePhase;", "newPhase$ktor_server_core", "on", "HookHandler", "hook", "Lio/ktor/server/application/Hook;", "handler", "(Lio/ktor/server/application/Hook;Ljava/lang/Object;)V", "onCall", "block", "Lkotlin/Function3;", "Lio/ktor/server/application/OnCallContext;", "Lio/ktor/server/application/ApplicationCall;", "Lkotlin/ParameterName;", "name", "call", "Lkotlin/coroutines/Continuation;", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function3;)V", "onCallReceive", "Lio/ktor/server/application/OnCallReceiveContext;", "Lkotlin/Function4;", "body", "(Lkotlin/jvm/functions/Function4;)V", "onCallRespond", "Lio/ktor/server/application/OnCallRespondContext;", "onDefaultPhase", "T", "ContextT", "Lio/ktor/server/application/CallContext;", "interceptions", "phase", "handlerName", "", "contextInit", "Lkotlin/Function2;", "Lio/ktor/util/pipeline/PipelineContext;", "(Ljava/util/List;Lio/ktor/util/pipeline/PipelinePhase;Ljava/lang/String;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function4;)V", "onDefaultPhaseWithMessage", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
@KtorDsl
/* compiled from: PluginBuilder.kt */
public abstract class PluginBuilder<PluginConfig> {
    private final List<Interception<Object>> afterResponseInterceptions = new ArrayList();
    private final List<Interception<Unit>> callInterceptions = new ArrayList();
    private final List<HookHandler<?>> hooks = new ArrayList();
    private final AttributeKey<PluginInstance> key;
    private final List<Interception<Object>> onReceiveInterceptions = new ArrayList();
    private final List<Interception<Object>> onResponseInterceptions = new ArrayList();

    public abstract Application getApplication();

    public abstract ApplicationCallPipeline getPipeline$ktor_server_core();

    public abstract PluginConfig getPluginConfig();

    public PluginBuilder(AttributeKey<PluginInstance> attributeKey) {
        Intrinsics.checkNotNullParameter(attributeKey, LeanbackPreferenceDialogFragment.ARG_KEY);
        this.key = attributeKey;
    }

    public final AttributeKey<PluginInstance> getKey$ktor_server_core() {
        return this.key;
    }

    public final ApplicationEnvironment getEnvironment() {
        return getPipeline$ktor_server_core().getEnvironment();
    }

    public final ApplicationConfig getApplicationConfig() {
        ApplicationEnvironment environment = getEnvironment();
        if (environment != null) {
            return environment.getConfig();
        }
        return null;
    }

    public final List<Interception<Unit>> getCallInterceptions$ktor_server_core() {
        return this.callInterceptions;
    }

    public final List<Interception<Object>> getOnReceiveInterceptions$ktor_server_core() {
        return this.onReceiveInterceptions;
    }

    public final List<Interception<Object>> getOnResponseInterceptions$ktor_server_core() {
        return this.onResponseInterceptions;
    }

    public final List<Interception<Object>> getAfterResponseInterceptions$ktor_server_core() {
        return this.afterResponseInterceptions;
    }

    public final List<HookHandler<?>> getHooks$ktor_server_core() {
        return this.hooks;
    }

    public final void onCall(Function3<? super OnCallContext<PluginConfig>, ? super ApplicationCall, ? super Continuation<? super Unit>, ? extends Object> function3) {
        Intrinsics.checkNotNullParameter(function3, "block");
        onDefaultPhase(this.callInterceptions, ApplicationCallPipeline.ApplicationPhase.getPlugins(), DebugPhaseNamesKt.PHASE_ON_CALL, PluginBuilder$onCall$1.INSTANCE, new PluginBuilder$onCall$2(function3, (Continuation<? super PluginBuilder$onCall$2>) null));
    }

    public final void onCallReceive(Function4<? super OnCallReceiveContext<PluginConfig>, ? super ApplicationCall, Object, ? super Continuation<? super Unit>, ? extends Object> function4) {
        Intrinsics.checkNotNullParameter(function4, "block");
        onDefaultPhase(this.onReceiveInterceptions, ApplicationReceivePipeline.Phases.getTransform(), DebugPhaseNamesKt.PHASE_ON_CALL_RECEIVE, PluginBuilder$onCallReceive$1.INSTANCE, new PluginBuilder$onCallReceive$2(function4, (Continuation<? super PluginBuilder$onCallReceive$2>) null));
    }

    public final void onCallRespond(Function4<? super OnCallRespondContext<PluginConfig>, ? super ApplicationCall, Object, ? super Continuation<? super Unit>, ? extends Object> function4) {
        Intrinsics.checkNotNullParameter(function4, "block");
        onDefaultPhase(this.onResponseInterceptions, ApplicationSendPipeline.Phases.getTransform(), DebugPhaseNamesKt.PHASE_ON_CALL_RESPOND, PluginBuilder$onCallRespond$1.INSTANCE, function4);
    }

    public final <HookHandler> void on(Hook<HookHandler> hook, HookHandler hookhandler) {
        Intrinsics.checkNotNullParameter(hook, "hook");
        this.hooks.add(new HookHandler(hook, hookhandler));
    }

    public final void onCallReceive(Function3<? super OnCallReceiveContext<PluginConfig>, ? super ApplicationCall, ? super Continuation<? super Unit>, ? extends Object> function3) {
        Intrinsics.checkNotNullParameter(function3, "block");
        onCallReceive(new PluginBuilder$onCallReceive$3(function3, (Continuation<? super PluginBuilder$onCallReceive$3>) null));
    }

    public final void onCallRespond(Function3<? super OnCallRespondContext<PluginConfig>, ? super ApplicationCall, ? super Continuation<? super Unit>, ? extends Object> function3) {
        Intrinsics.checkNotNullParameter(function3, "block");
        onCallRespond(new PluginBuilder$onCallRespond$2(function3, (Continuation<? super PluginBuilder$onCallRespond$2>) null));
    }

    private final <T, ContextT extends CallContext<PluginConfig>> void onDefaultPhaseWithMessage(List<Interception<T>> list, PipelinePhase pipelinePhase, String str, Function2<? super PluginConfig, ? super PipelineContext<T, ApplicationCall>, ? extends ContextT> function2, Function4<? super ContextT, ? super ApplicationCall, ? super T, ? super Continuation<? super Unit>, ? extends Object> function4) {
        list.add(new Interception(pipelinePhase, new PluginBuilder$onDefaultPhaseWithMessage$1(pipelinePhase, this, str, function4, function2)));
    }

    private final <T, ContextT extends CallContext<PluginConfig>> void onDefaultPhase(List<Interception<T>> list, PipelinePhase pipelinePhase, String str, Function2<? super PluginConfig, ? super PipelineContext<T, ApplicationCall>, ? extends ContextT> function2, Function4<? super ContextT, ? super ApplicationCall, ? super T, ? super Continuation<? super Unit>, ? extends Object> function4) {
        onDefaultPhaseWithMessage(list, pipelinePhase, str, function2, new PluginBuilder$onDefaultPhase$1(function4, (Continuation<? super PluginBuilder$onDefaultPhase$1>) null));
    }

    public final PipelinePhase newPhase$ktor_server_core() {
        return new PipelinePhase(this.key.getName() + "Phase" + Random.Default.nextInt());
    }
}
