package io.ktor.gson;

import com.google.gson.Gson;
import io.ktor.application.ApplicationCall;
import io.ktor.features.ContentConverter;
import io.ktor.features.ContentNegotiationKt;
import io.ktor.http.ContentType;
import io.ktor.http.ContentTypesKt;
import io.ktor.http.HttpStatusCode;
import io.ktor.http.content.TextContent;
import io.ktor.request.ApplicationReceiveRequest;
import io.ktor.util.pipeline.PipelineContext;
import io.ktor.utils.io.ByteReadChannel;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlin.reflect.jvm.KTypesJvm;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Dispatchers;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J'\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bH@ø\u0001\u0000¢\u0006\u0002\u0010\u000bJ7\u0010\f\u001a\u0004\u0018\u00010\u00062\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\n0\b2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0006H@ø\u0001\u0000¢\u0006\u0002\u0010\u0010R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u0011"}, d2 = {"Lio/ktor/gson/GsonConverter;", "Lio/ktor/features/ContentConverter;", "gson", "Lcom/google/gson/Gson;", "(Lcom/google/gson/Gson;)V", "convertForReceive", "", "context", "Lio/ktor/util/pipeline/PipelineContext;", "Lio/ktor/request/ApplicationReceiveRequest;", "Lio/ktor/application/ApplicationCall;", "(Lio/ktor/util/pipeline/PipelineContext;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "convertForSend", "contentType", "Lio/ktor/http/ContentType;", "value", "(Lio/ktor/util/pipeline/PipelineContext;Lio/ktor/http/ContentType;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ktor-gson"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: GsonSupport.kt */
public final class GsonConverter implements ContentConverter {
    /* access modifiers changed from: private */
    public final Gson gson;

    public GsonConverter() {
        this((Gson) null, 1, (DefaultConstructorMarker) null);
    }

    public GsonConverter(Gson gson2) {
        Intrinsics.checkNotNullParameter(gson2, "gson");
        this.gson = gson2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ GsonConverter(Gson gson2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new Gson() : gson2);
    }

    public Object convertForSend(PipelineContext<Object, ApplicationCall> pipelineContext, ContentType contentType, Object obj, Continuation<Object> continuation) {
        String json = this.gson.toJson(obj);
        Intrinsics.checkNotNullExpressionValue(json, "gson.toJson(value)");
        return new TextContent(json, ContentTypesKt.withCharset(contentType, ContentNegotiationKt.suitableCharset$default(pipelineContext.getContext(), (Charset) null, 1, (Object) null)), (HttpStatusCode) null, 4, (DefaultConstructorMarker) null);
    }

    public Object convertForReceive(PipelineContext<ApplicationReceiveRequest, ApplicationCall> pipelineContext, Continuation<Object> continuation) {
        ApplicationReceiveRequest subject = pipelineContext.getSubject();
        Object value = subject.getValue();
        ByteReadChannel byteReadChannel = value instanceof ByteReadChannel ? (ByteReadChannel) value : null;
        if (byteReadChannel == null) {
            return null;
        }
        KClass<?> jvmErasure = KTypesJvm.getJvmErasure(subject.getTypeInfo());
        if (!GsonSupportKt.isExcluded(this.gson, jvmErasure)) {
            return BuildersKt.withContext(Dispatchers.getIO(), new GsonConverter$convertForReceive$2(byteReadChannel, pipelineContext, this, jvmErasure, (Continuation<? super GsonConverter$convertForReceive$2>) null), continuation);
        }
        throw new ExcludedTypeGsonException(jvmErasure);
    }
}
