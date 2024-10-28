package org.videolan.vlc.webserver;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import androidx.core.content.ContextCompat;
import io.ktor.http.ContentType;
import io.ktor.http.HttpStatusCode;
import io.ktor.http.content.OutgoingContent;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.response.ApplicationResponse;
import io.ktor.server.response.ApplicationResponseFunctionsKt;
import io.ktor.server.response.ApplicationSendPipeline;
import io.ktor.server.response.ResponseTypeKt;
import io.ktor.util.pipeline.PipelineContext;
import io.ktor.util.reflect.TypeInfoJvmKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KType;
import kotlin.reflect.TypesJVMKt;
import org.videolan.resources.Constants;
import org.videolan.tools.KotlinExtensionsKt;
import org.videolan.vlc.gui.helpers.BitmapUtil;
import org.videolan.vlc.gui.helpers.BitmapUtilKt;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\u0004\u001a\u00020\u0001H@"}, d2 = {"<anonymous>", "", "Lio/ktor/util/pipeline/PipelineContext;", "Lio/ktor/server/application/ApplicationCall;", "it"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.webserver.RemoteAccessRoutingKt$setupRouting$10", f = "RemoteAccessRouting.kt", i = {}, l = {1504, 1512, 298, 1521}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: RemoteAccessRouting.kt */
final class RemoteAccessRoutingKt$setupRouting$10 extends SuspendLambda implements Function3<PipelineContext<Unit, ApplicationCall>, Unit, Continuation<? super Unit>, Object> {
    final /* synthetic */ Context $appContext;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RemoteAccessRoutingKt$setupRouting$10(Context context, Continuation<? super RemoteAccessRoutingKt$setupRouting$10> continuation) {
        super(3, continuation);
        this.$appContext = context;
    }

    public final Object invoke(PipelineContext<Unit, ApplicationCall> pipelineContext, Unit unit, Continuation<? super Unit> continuation) {
        RemoteAccessRoutingKt$setupRouting$10 remoteAccessRoutingKt$setupRouting$10 = new RemoteAccessRoutingKt$setupRouting$10(this.$appContext, continuation);
        remoteAccessRoutingKt$setupRouting$10.L$0 = pipelineContext;
        return remoteAccessRoutingKt$setupRouting$10.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Bitmap bitmap;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            PipelineContext pipelineContext = (PipelineContext) this.L$0;
            String str = ((ApplicationCall) pipelineContext.getContext()).getRequest().getQueryParameters().get("id");
            String str2 = ((ApplicationCall) pipelineContext.getContext()).getRequest().getQueryParameters().get("width");
            int parseInt = str2 != null ? Integer.parseInt(str2) : 32;
            String str3 = ((ApplicationCall) pipelineContext.getContext()).getRequest().getQueryParameters().get("preventTint");
            boolean parseBoolean = str3 != null ? Boolean.parseBoolean(str3) : false;
            try {
                int resIdByName = KotlinExtensionsKt.resIdByName(this.$appContext, str, Constants.DRAWABLE);
                if (resIdByName == 0) {
                    ApplicationCall applicationCall = (ApplicationCall) pipelineContext.getContext();
                    HttpStatusCode notFound = HttpStatusCode.Companion.getNotFound();
                    if (!(notFound instanceof OutgoingContent) && !(notFound instanceof byte[])) {
                        ApplicationResponse response = applicationCall.getResponse();
                        KType typeOf = Reflection.typeOf(HttpStatusCode.class);
                        ResponseTypeKt.setResponseType(response, TypeInfoJvmKt.typeInfoImpl(TypesJVMKt.getJavaType(typeOf), Reflection.getOrCreateKotlinClass(HttpStatusCode.class), typeOf));
                    }
                    ApplicationSendPipeline pipeline = applicationCall.getResponse().getPipeline();
                    Intrinsics.checkNotNull(notFound, "null cannot be cast to non-null type kotlin.Any");
                    this.label = 2;
                    if (pipeline.execute(applicationCall, notFound, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (parseBoolean) {
                        bitmap = BitmapUtil.INSTANCE.vectorToBitmap(this.$appContext, resIdByName, Boxing.boxInt(parseInt), Boxing.boxInt(parseInt));
                    } else {
                        Context context = this.$appContext;
                        bitmap = BitmapUtilKt.getColoredBitmapFromColor(context, resIdByName, ContextCompat.getColor(context, R.color.black), Boxing.boxInt(parseInt), Boxing.boxInt(parseInt));
                    }
                    byte[] encodeImage$default = BitmapUtil.encodeImage$default(BitmapUtil.INSTANCE, bitmap, true, (Function0) null, 4, (Object) null);
                    if (encodeImage$default != null) {
                        this.label = 3;
                        if (ApplicationResponseFunctionsKt.respondBytes$default((ApplicationCall) pipelineContext.getContext(), ContentType.Image.INSTANCE.getPNG(), (HttpStatusCode) null, new RemoteAccessRoutingKt$setupRouting$10$1$1(encodeImage$default, (Continuation<? super RemoteAccessRoutingKt$setupRouting$10$1$1>) null), this, 2, (Object) null) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        return Unit.INSTANCE;
                    }
                    ApplicationCall applicationCall2 = (ApplicationCall) pipelineContext.getContext();
                    HttpStatusCode notFound2 = HttpStatusCode.Companion.getNotFound();
                    if (!(notFound2 instanceof OutgoingContent) && !(notFound2 instanceof byte[])) {
                        ApplicationResponse response2 = applicationCall2.getResponse();
                        KType typeOf2 = Reflection.typeOf(HttpStatusCode.class);
                        ResponseTypeKt.setResponseType(response2, TypeInfoJvmKt.typeInfoImpl(TypesJVMKt.getJavaType(typeOf2), Reflection.getOrCreateKotlinClass(HttpStatusCode.class), typeOf2));
                    }
                    ApplicationSendPipeline pipeline2 = applicationCall2.getResponse().getPipeline();
                    Intrinsics.checkNotNull(notFound2, "null cannot be cast to non-null type kotlin.Any");
                    this.label = 4;
                    if (pipeline2.execute(applicationCall2, notFound2, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    return Unit.INSTANCE;
                }
            } catch (Resources.NotFoundException unused) {
                ApplicationCall applicationCall3 = (ApplicationCall) pipelineContext.getContext();
                HttpStatusCode notFound3 = HttpStatusCode.Companion.getNotFound();
                if (!(notFound3 instanceof OutgoingContent) && !(notFound3 instanceof byte[])) {
                    ApplicationResponse response3 = applicationCall3.getResponse();
                    KType typeOf3 = Reflection.typeOf(HttpStatusCode.class);
                    ResponseTypeKt.setResponseType(response3, TypeInfoJvmKt.typeInfoImpl(TypesJVMKt.getJavaType(typeOf3), Reflection.getOrCreateKotlinClass(HttpStatusCode.class), typeOf3));
                }
                ApplicationSendPipeline pipeline3 = applicationCall3.getResponse().getPipeline();
                Intrinsics.checkNotNull(notFound3, "null cannot be cast to non-null type kotlin.Any");
                this.label = 1;
                if (pipeline3.execute(applicationCall3, notFound3, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
            return Unit.INSTANCE;
        } else if (i == 2) {
            ResultKt.throwOnFailure(obj);
        } else if (i == 3) {
            ResultKt.throwOnFailure(obj);
            return Unit.INSTANCE;
        } else if (i == 4) {
            ResultKt.throwOnFailure(obj);
            return Unit.INSTANCE;
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
