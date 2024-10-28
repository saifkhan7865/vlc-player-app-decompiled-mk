package io.ktor.gson;

import io.ktor.application.ApplicationCall;
import io.ktor.request.ApplicationReceiveRequest;
import io.ktor.request.ApplicationRequestPropertiesKt;
import io.ktor.util.pipeline.PipelineContext;
import io.ktor.utils.io.ByteReadChannel;
import io.ktor.utils.io.jvm.javaio.BlockingKt;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function2;
import kotlin.reflect.KClass;
import kotlin.text.Charsets;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.gson.GsonConverter$convertForReceive$2", f = "GsonSupport.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: GsonSupport.kt */
final class GsonConverter$convertForReceive$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Object>, Object> {
    final /* synthetic */ ByteReadChannel $channel;
    final /* synthetic */ PipelineContext<ApplicationReceiveRequest, ApplicationCall> $context;
    final /* synthetic */ KClass<?> $javaType;
    int label;
    final /* synthetic */ GsonConverter this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    GsonConverter$convertForReceive$2(ByteReadChannel byteReadChannel, PipelineContext<ApplicationReceiveRequest, ApplicationCall> pipelineContext, GsonConverter gsonConverter, KClass<?> kClass, Continuation<? super GsonConverter$convertForReceive$2> continuation) {
        super(2, continuation);
        this.$channel = byteReadChannel;
        this.$context = pipelineContext;
        this.this$0 = gsonConverter;
        this.$javaType = kClass;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new GsonConverter$convertForReceive$2(this.$channel, this.$context, this.this$0, this.$javaType, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<Object> continuation) {
        return ((GsonConverter$convertForReceive$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: type inference failed for: r4v5, types: [io.ktor.gson.UnsupportedNullValuesException, java.lang.Throwable] */
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            InputStream inputStream$default = BlockingKt.toInputStream$default(this.$channel, (Job) null, 1, (Object) null);
            Charset contentCharset = ApplicationRequestPropertiesKt.contentCharset(this.$context.getContext().getRequest());
            if (contentCharset == null) {
                contentCharset = Charsets.UTF_8;
            }
            Object fromJson = this.this$0.gson.fromJson(new InputStreamReader(inputStream$default, contentCharset), JvmClassMappingKt.getJavaObjectType(this.$javaType));
            if (fromJson != null) {
                return fromJson;
            }
            throw new UnsupportedNullValuesException();
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
