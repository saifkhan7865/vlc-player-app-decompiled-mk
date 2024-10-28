package io.ktor.server.request;

import io.ktor.server.application.ApplicationCall;
import io.ktor.server.plugins.CannotTransformContentToTypeException;
import io.ktor.util.reflect.TypeInfo;
import io.ktor.util.reflect.TypeInfoJvmKt;
import java.io.InputStream;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KType;
import kotlin.reflect.TypesJVMKt;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u0002HHø\u0001\u0000¢\u0006\u0002\u0010\u0003\u0002\u0004\n\u0002\b\u0019¨\u0006\u0004"}, d2 = {"receiveStream", "Ljava/io/InputStream;", "Lio/ktor/server/application/ApplicationCall;", "(Lio/ktor/server/application/ApplicationCall;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ktor-server-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: ApplicationReceiveFunctionsJvm.kt */
public final class ApplicationReceiveFunctionsJvmKt {
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0032  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0054 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0055  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object receiveStream(io.ktor.server.application.ApplicationCall r5, kotlin.coroutines.Continuation<? super java.io.InputStream> r6) {
        /*
            boolean r0 = r6 instanceof io.ktor.server.request.ApplicationReceiveFunctionsJvmKt$receiveStream$1
            if (r0 == 0) goto L_0x0014
            r0 = r6
            io.ktor.server.request.ApplicationReceiveFunctionsJvmKt$receiveStream$1 r0 = (io.ktor.server.request.ApplicationReceiveFunctionsJvmKt$receiveStream$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L_0x0019
        L_0x0014:
            io.ktor.server.request.ApplicationReceiveFunctionsJvmKt$receiveStream$1 r0 = new io.ktor.server.request.ApplicationReceiveFunctionsJvmKt$receiveStream$1
            r0.<init>(r6)
        L_0x0019:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0032
            if (r2 != r3) goto L_0x002a
            kotlin.ResultKt.throwOnFailure(r6)
            goto L_0x0052
        L_0x002a:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x0032:
            kotlin.ResultKt.throwOnFailure(r6)
            java.lang.Class<java.io.InputStream> r6 = java.io.InputStream.class
            kotlin.reflect.KType r6 = kotlin.jvm.internal.Reflection.typeOf((java.lang.Class) r6)
            java.lang.reflect.Type r2 = kotlin.reflect.TypesJVMKt.getJavaType((kotlin.reflect.KType) r6)
            java.lang.Class<java.io.InputStream> r4 = java.io.InputStream.class
            kotlin.reflect.KClass r4 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r4)
            io.ktor.util.reflect.TypeInfo r6 = io.ktor.util.reflect.TypeInfoJvmKt.typeInfoImpl(r2, r4, r6)
            r0.label = r3
            java.lang.Object r6 = io.ktor.server.request.ApplicationReceiveFunctionsKt.receiveNullable(r5, r6, r0)
            if (r6 != r1) goto L_0x0052
            return r1
        L_0x0052:
            if (r6 == 0) goto L_0x0055
            return r6
        L_0x0055:
            io.ktor.server.plugins.CannotTransformContentToTypeException r5 = new io.ktor.server.plugins.CannotTransformContentToTypeException
            java.lang.Class<java.io.InputStream> r6 = java.io.InputStream.class
            kotlin.reflect.KType r6 = kotlin.jvm.internal.Reflection.typeOf((java.lang.Class) r6)
            java.lang.reflect.Type r0 = kotlin.reflect.TypesJVMKt.getJavaType((kotlin.reflect.KType) r6)
            java.lang.Class<java.io.InputStream> r1 = java.io.InputStream.class
            kotlin.reflect.KClass r1 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r1)
            io.ktor.util.reflect.TypeInfo r6 = io.ktor.util.reflect.TypeInfoJvmKt.typeInfoImpl(r0, r1, r6)
            kotlin.reflect.KType r6 = r6.getKotlinType()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r6)
            r5.<init>(r6)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.request.ApplicationReceiveFunctionsJvmKt.receiveStream(io.ktor.server.application.ApplicationCall, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private static final Object receiveStream$$forInline(ApplicationCall applicationCall, Continuation<? super InputStream> continuation) {
        KType typeOf = Reflection.typeOf(InputStream.class);
        TypeInfo typeInfoImpl = TypeInfoJvmKt.typeInfoImpl(TypesJVMKt.getJavaType(typeOf), Reflection.getOrCreateKotlinClass(InputStream.class), typeOf);
        InlineMarker.mark(0);
        Object receiveNullable = ApplicationReceiveFunctionsKt.receiveNullable(applicationCall, typeInfoImpl, continuation);
        InlineMarker.mark(1);
        if (receiveNullable != null) {
            return receiveNullable;
        }
        KType typeOf2 = Reflection.typeOf(InputStream.class);
        KType kotlinType = TypeInfoJvmKt.typeInfoImpl(TypesJVMKt.getJavaType(typeOf2), Reflection.getOrCreateKotlinClass(InputStream.class), typeOf2).getKotlinType();
        Intrinsics.checkNotNull(kotlinType);
        throw new CannotTransformContentToTypeException(kotlinType);
    }
}
