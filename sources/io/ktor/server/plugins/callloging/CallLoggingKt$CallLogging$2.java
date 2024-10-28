package io.ktor.server.plugins.callloging;

import io.ktor.server.application.ApplicationCall;
import io.ktor.server.application.ApplicationKt;
import io.ktor.server.application.PluginBuilder;
import io.ktor.server.application.hooks.CallSetup;
import io.ktor.server.http.content.StaticContentKt;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.slf4j.Logger;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "Lio/ktor/server/application/PluginBuilder;", "Lio/ktor/server/plugins/callloging/CallLoggingConfig;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: CallLogging.kt */
final class CallLoggingKt$CallLogging$2 extends Lambda implements Function1<PluginBuilder<CallLoggingConfig>, Unit> {
    public static final CallLoggingKt$CallLogging$2 INSTANCE = new CallLoggingKt$CallLogging$2();

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* compiled from: CallLogging.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            /*
                org.slf4j.event.Level[] r0 = org.slf4j.event.Level.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                org.slf4j.event.Level r1 = org.slf4j.event.Level.ERROR     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                org.slf4j.event.Level r1 = org.slf4j.event.Level.WARN     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                org.slf4j.event.Level r1 = org.slf4j.event.Level.INFO     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                org.slf4j.event.Level r1 = org.slf4j.event.Level.DEBUG     // Catch:{ NoSuchFieldError -> 0x002b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002b }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002b }
            L_0x002b:
                org.slf4j.event.Level r1 = org.slf4j.event.Level.TRACE     // Catch:{ NoSuchFieldError -> 0x0034 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0034 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0034 }
            L_0x0034:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.plugins.callloging.CallLoggingKt$CallLogging$2.WhenMappings.<clinit>():void");
        }
    }

    CallLoggingKt$CallLogging$2() {
        super(1);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((PluginBuilder<CallLoggingConfig>) (PluginBuilder) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(final PluginBuilder<CallLoggingConfig> pluginBuilder) {
        Intrinsics.checkNotNullParameter(pluginBuilder, "$this$createApplicationPlugin");
        Logger logger = pluginBuilder.getPluginConfig().getLogger();
        if (logger == null) {
            logger = ApplicationKt.getLog(pluginBuilder.getApplication());
        }
        final Logger logger2 = logger;
        final List<Function1<ApplicationCall, Boolean>> filters$ktor_server_call_logging = pluginBuilder.getPluginConfig().getFilters$ktor_server_call_logging();
        final Function1<ApplicationCall, String> formatCall$ktor_server_call_logging = pluginBuilder.getPluginConfig().getFormatCall$ktor_server_call_logging();
        final Function0<Long> clock$ktor_server_call_logging = pluginBuilder.getPluginConfig().getClock$ktor_server_call_logging();
        final boolean ignoreStaticContent$ktor_server_call_logging = pluginBuilder.getPluginConfig().getIgnoreStaticContent$ktor_server_call_logging();
        MDCProviderKt.setupMDCProvider(pluginBuilder);
        CallLoggingKt.setupLogging(pluginBuilder.getApplication().getEnvironment().getMonitor(), new Function1<String, Unit>() {
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((String) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(String str) {
                Intrinsics.checkNotNullParameter(str, "p0");
                CallLoggingKt$CallLogging$2.invoke$log(pluginBuilder, logger2, str);
            }
        });
        pluginBuilder.on(CallSetup.INSTANCE, new AnonymousClass2((Continuation<? super AnonymousClass2>) null));
        if (pluginBuilder.getPluginConfig().getMdcEntries$ktor_server_call_logging().isEmpty()) {
            final PluginBuilder<CallLoggingConfig> pluginBuilder2 = pluginBuilder;
            CallLoggingKt.logCompletedCalls(pluginBuilder, new Function1<ApplicationCall, Unit>() {
                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    invoke((ApplicationCall) obj);
                    return Unit.INSTANCE;
                }

                public final void invoke(ApplicationCall applicationCall) {
                    Intrinsics.checkNotNullParameter(applicationCall, "p0");
                    CallLoggingKt$CallLogging$2.invoke$logSuccess(ignoreStaticContent$ktor_server_call_logging, filters$ktor_server_call_logging, formatCall$ktor_server_call_logging, pluginBuilder2, logger2, applicationCall);
                }
            });
            return;
        }
        final PluginBuilder<CallLoggingConfig> pluginBuilder3 = pluginBuilder;
        CallLoggingKt.logCallsWithMDC(pluginBuilder, new Function1<ApplicationCall, Unit>() {
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((ApplicationCall) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(ApplicationCall applicationCall) {
                Intrinsics.checkNotNullParameter(applicationCall, "p0");
                CallLoggingKt$CallLogging$2.invoke$logSuccess(ignoreStaticContent$ktor_server_call_logging, filters$ktor_server_call_logging, formatCall$ktor_server_call_logging, pluginBuilder3, logger2, applicationCall);
            }
        });
    }

    /* access modifiers changed from: private */
    public static final void invoke$log(PluginBuilder<CallLoggingConfig> pluginBuilder, Logger logger, String str) {
        int i = WhenMappings.$EnumSwitchMapping$0[pluginBuilder.getPluginConfig().getLevel().ordinal()];
        if (i == 1) {
            logger.error(str);
        } else if (i == 2) {
            logger.warn(str);
        } else if (i == 3) {
            logger.info(str);
        } else if (i == 4) {
            logger.debug(str);
        } else if (i == 5) {
            logger.trace(str);
        } else {
            throw new NoWhenBranchMatchedException();
        }
    }

    /* access modifiers changed from: private */
    public static final void invoke$logSuccess(boolean z, List<Function1<ApplicationCall, Boolean>> list, Function1<? super ApplicationCall, String> function1, PluginBuilder<CallLoggingConfig> pluginBuilder, Logger logger, ApplicationCall applicationCall) {
        if (!z || !StaticContentKt.isStaticContent(applicationCall)) {
            if (!list.isEmpty()) {
                Iterable<Function1> iterable = list;
                if (!(iterable instanceof Collection) || !((Collection) iterable).isEmpty()) {
                    for (Function1 invoke : iterable) {
                        if (((Boolean) invoke.invoke(applicationCall)).booleanValue()) {
                        }
                    }
                    return;
                }
                return;
            }
            invoke$log(pluginBuilder, logger, function1.invoke(applicationCall));
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H@"}, d2 = {"<anonymous>", "", "call", "Lio/ktor/server/application/ApplicationCall;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "io.ktor.server.plugins.callloging.CallLoggingKt$CallLogging$2$2", f = "CallLogging.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: io.ktor.server.plugins.callloging.CallLoggingKt$CallLogging$2$2  reason: invalid class name */
    /* compiled from: CallLogging.kt */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<ApplicationCall, Continuation<? super Unit>, Object> {
        /* synthetic */ Object L$0;
        int label;

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 r0 = new AnonymousClass2(clock$ktor_server_call_logging, continuation);
            r0.L$0 = obj;
            return r0;
        }

        public final Object invoke(ApplicationCall applicationCall, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(applicationCall, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                ((ApplicationCall) this.L$0).getAttributes().put(CallLoggingKt.getCALL_START_TIME(), clock$ktor_server_call_logging.invoke());
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
