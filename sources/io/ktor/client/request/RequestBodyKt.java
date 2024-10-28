package io.ktor.client.request;

import io.ktor.http.content.NullBody;
import io.ktor.http.content.OutgoingContent;
import io.ktor.util.AttributeKey;
import io.ktor.util.reflect.TypeInfo;
import io.ktor.util.reflect.TypeInfoJvmKt;
import java.lang.reflect.Type;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KType;
import kotlin.reflect.TypesJVMKt;

@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\u001a\"\u0010\u0005\u001a\u00020\u0006\"\u0006\b\u0000\u0010\u0007\u0018\u0001*\u00020\b2\u0006\u0010\t\u001a\u0002H\u0007H\b¢\u0006\u0002\u0010\n\u001a\u001c\u0010\u0005\u001a\u00020\u0006*\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\u0002\"\u001a\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004¨\u0006\r"}, d2 = {"BodyTypeAttributeKey", "Lio/ktor/util/AttributeKey;", "Lio/ktor/util/reflect/TypeInfo;", "getBodyTypeAttributeKey", "()Lio/ktor/util/AttributeKey;", "setBody", "", "T", "Lio/ktor/client/request/HttpRequestBuilder;", "body", "(Lio/ktor/client/request/HttpRequestBuilder;Ljava/lang/Object;)V", "", "bodyType", "ktor-client-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: RequestBody.kt */
public final class RequestBodyKt {
    private static final AttributeKey<TypeInfo> BodyTypeAttributeKey = new AttributeKey<>("BodyTypeAttributeKey");

    public static final AttributeKey<TypeInfo> getBodyTypeAttributeKey() {
        return BodyTypeAttributeKey;
    }

    public static final /* synthetic */ <T> void setBody(HttpRequestBuilder httpRequestBuilder, T t) {
        Intrinsics.checkNotNullParameter(httpRequestBuilder, "<this>");
        if (t == null) {
            httpRequestBuilder.setBody(NullBody.INSTANCE);
            Intrinsics.reifiedOperationMarker(6, "T");
            Type javaType = TypesJVMKt.getJavaType((KType) null);
            Intrinsics.reifiedOperationMarker(4, "T");
            httpRequestBuilder.setBodyType(TypeInfoJvmKt.typeInfoImpl(javaType, Reflection.getOrCreateKotlinClass(Object.class), (KType) null));
        } else if (t instanceof OutgoingContent) {
            httpRequestBuilder.setBody(t);
            httpRequestBuilder.setBodyType((TypeInfo) null);
        } else {
            httpRequestBuilder.setBody(t);
            Intrinsics.reifiedOperationMarker(6, "T");
            Type javaType2 = TypesJVMKt.getJavaType((KType) null);
            Intrinsics.reifiedOperationMarker(4, "T");
            httpRequestBuilder.setBodyType(TypeInfoJvmKt.typeInfoImpl(javaType2, Reflection.getOrCreateKotlinClass(Object.class), (KType) null));
        }
    }

    public static final void setBody(HttpRequestBuilder httpRequestBuilder, Object obj, TypeInfo typeInfo) {
        Intrinsics.checkNotNullParameter(httpRequestBuilder, "<this>");
        Intrinsics.checkNotNullParameter(typeInfo, "bodyType");
        if (obj == null) {
            obj = NullBody.INSTANCE;
        }
        httpRequestBuilder.setBody(obj);
        httpRequestBuilder.setBodyType(typeInfo);
    }
}
