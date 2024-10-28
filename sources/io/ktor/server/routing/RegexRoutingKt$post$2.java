package io.ktor.server.routing;

import io.ktor.server.application.ApplicationCall;
import io.ktor.server.plugins.CannotTransformContentToTypeException;
import io.ktor.server.request.ApplicationReceiveFunctionsKt;
import io.ktor.util.pipeline.PipelineContext;
import io.ktor.util.reflect.TypeInfoJvmKt;
import java.lang.reflect.Type;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KType;
import kotlin.reflect.TypesJVMKt;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003*\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u00020\u0001H@"}, d2 = {"<anonymous>", "", "R", "", "Lio/ktor/util/pipeline/PipelineContext;", "Lio/ktor/server/application/ApplicationCall;", "it"}, k = 3, mv = {1, 8, 0}, xi = 176)
@DebugMetadata(c = "io.ktor.server.routing.RegexRoutingKt$post$2", f = "RegexRouting.kt", i = {}, l = {287, 104}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: RegexRouting.kt */
public final class RegexRoutingKt$post$2 extends SuspendLambda implements Function3<PipelineContext<Unit, ApplicationCall>, Unit, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function3<PipelineContext<Unit, ApplicationCall>, R, Continuation<? super Unit>, Object> $body;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public RegexRoutingKt$post$2(Function3<? super PipelineContext<Unit, ApplicationCall>, ? super R, ? super Continuation<? super Unit>, ? extends Object> function3, Continuation<? super RegexRoutingKt$post$2> continuation) {
        super(3, continuation);
        this.$body = function3;
    }

    public final Object invoke(PipelineContext<Unit, ApplicationCall> pipelineContext, Unit unit, Continuation<? super Unit> continuation) {
        RegexRoutingKt$post$2 regexRoutingKt$post$2 = new RegexRoutingKt$post$2(this.$body, continuation);
        regexRoutingKt$post$2.L$0 = pipelineContext;
        return regexRoutingKt$post$2.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Function3<PipelineContext<Unit, ApplicationCall>, R, Continuation<? super Unit>, Object> function3;
        PipelineContext pipelineContext;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            pipelineContext = (PipelineContext) this.L$0;
            Function3<PipelineContext<Unit, ApplicationCall>, R, Continuation<? super Unit>, Object> function32 = this.$body;
            Intrinsics.reifiedOperationMarker(6, "R");
            Type javaType = TypesJVMKt.getJavaType((KType) null);
            Intrinsics.reifiedOperationMarker(4, "R");
            this.L$0 = function32;
            this.L$1 = pipelineContext;
            this.label = 1;
            Object receiveNullable = ApplicationReceiveFunctionsKt.receiveNullable((ApplicationCall) pipelineContext.getContext(), TypeInfoJvmKt.typeInfoImpl(javaType, Reflection.getOrCreateKotlinClass(Object.class), (KType) null), this);
            if (receiveNullable == coroutine_suspended) {
                return coroutine_suspended;
            }
            Object obj2 = receiveNullable;
            function3 = function32;
            obj = obj2;
        } else if (i == 1) {
            pipelineContext = (PipelineContext) this.L$1;
            function3 = (Function3) this.L$0;
            ResultKt.throwOnFailure(obj);
        } else if (i == 2) {
            ResultKt.throwOnFailure(obj);
            return Unit.INSTANCE;
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        if (obj != null) {
            this.L$0 = null;
            this.L$1 = null;
            this.label = 2;
            if (function3.invoke(pipelineContext, obj, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
        Intrinsics.reifiedOperationMarker(6, "R");
        Type javaType2 = TypesJVMKt.getJavaType((KType) null);
        Intrinsics.reifiedOperationMarker(4, "R");
        KType kotlinType = TypeInfoJvmKt.typeInfoImpl(javaType2, Reflection.getOrCreateKotlinClass(Object.class), (KType) null).getKotlinType();
        Intrinsics.checkNotNull(kotlinType);
        throw new CannotTransformContentToTypeException(kotlinType);
    }

    public final Object invokeSuspend$$forInline(Object obj) {
        PipelineContext pipelineContext = (PipelineContext) this.L$0;
        Function3<PipelineContext<Unit, ApplicationCall>, R, Continuation<? super Unit>, Object> function3 = this.$body;
        Intrinsics.reifiedOperationMarker(6, "R");
        Type javaType = TypesJVMKt.getJavaType((KType) null);
        Intrinsics.reifiedOperationMarker(4, "R");
        InlineMarker.mark(0);
        Object receiveNullable = ApplicationReceiveFunctionsKt.receiveNullable((ApplicationCall) pipelineContext.getContext(), TypeInfoJvmKt.typeInfoImpl(javaType, Reflection.getOrCreateKotlinClass(Object.class), (KType) null), this);
        InlineMarker.mark(1);
        if (receiveNullable != null) {
            function3.invoke(pipelineContext, receiveNullable, this);
            return Unit.INSTANCE;
        }
        Intrinsics.reifiedOperationMarker(6, "R");
        Type javaType2 = TypesJVMKt.getJavaType((KType) null);
        Intrinsics.reifiedOperationMarker(4, "R");
        KType kotlinType = TypeInfoJvmKt.typeInfoImpl(javaType2, Reflection.getOrCreateKotlinClass(Object.class), (KType) null).getKotlinType();
        Intrinsics.checkNotNull(kotlinType);
        throw new CannotTransformContentToTypeException(kotlinType);
    }
}
