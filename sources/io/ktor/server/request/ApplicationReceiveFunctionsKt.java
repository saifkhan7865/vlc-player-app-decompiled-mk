package io.ktor.server.request;

import io.ktor.http.BadContentTypeFormatException;
import io.ktor.http.HttpHeaders;
import io.ktor.http.Parameters;
import io.ktor.http.content.MultiPartData;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.plugins.BadRequestException;
import io.ktor.server.plugins.CannotTransformContentToTypeException;
import io.ktor.util.AttributeKey;
import io.ktor.util.reflect.TypeInfo;
import io.ktor.util.reflect.TypeInfoJvmKt;
import io.ktor.utils.io.ByteReadChannel;
import io.ktor.utils.io.core.Input;
import io.ktor.utils.io.core.StringsKt;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KType;
import kotlin.reflect.TypesJVMKt;
import kotlin.text.Charsets;

@Metadata(d1 = {"\u0000J\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a!\u0010\u0003\u001a\u0002H\u0004\"\n\b\u0000\u0010\u0004\u0018\u0001*\u00020\u0005*\u00020\u0006HHø\u0001\u0000¢\u0006\u0002\u0010\u0007\u001a#\u0010\u0003\u001a\u0002H\u0004\"\u0004\b\u0000\u0010\u0004*\u00020\u00062\u0006\u0010\b\u001a\u00020\tH@ø\u0001\u0000¢\u0006\u0002\u0010\n\u001a-\u0010\u0003\u001a\u0002H\u0004\"\b\b\u0000\u0010\u0004*\u00020\u0005*\u00020\u00062\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00040\fH@ø\u0001\u0000¢\u0006\u0002\u0010\r\u001a\u0015\u0010\u000e\u001a\u00020\u000f*\u00020\u0006HHø\u0001\u0000¢\u0006\u0002\u0010\u0007\u001a\u0015\u0010\u0010\u001a\u00020\u0011*\u00020\u0006HHø\u0001\u0000¢\u0006\u0002\u0010\u0007\u001a\u001f\u0010\u0012\u001a\u0004\u0018\u0001H\u0004\"\u0006\b\u0000\u0010\u0004\u0018\u0001*\u00020\u0006HHø\u0001\u0000¢\u0006\u0002\u0010\u0007\u001a%\u0010\u0012\u001a\u0004\u0018\u0001H\u0004\"\u0004\b\u0000\u0010\u0004*\u00020\u00062\u0006\u0010\b\u001a\u00020\tH@ø\u0001\u0000¢\u0006\u0002\u0010\n\u001a#\u0010\u0013\u001a\u0004\u0018\u0001H\u0004\"\n\b\u0000\u0010\u0004\u0018\u0001*\u00020\u0005*\u00020\u0006HHø\u0001\u0000¢\u0006\u0002\u0010\u0007\u001a)\u0010\u0013\u001a\u0004\u0018\u0001H\u0004\"\b\b\u0000\u0010\u0004*\u00020\u0005*\u00020\u00062\u0006\u0010\b\u001a\u00020\tH@ø\u0001\u0000¢\u0006\u0002\u0010\n\u001a/\u0010\u0013\u001a\u0004\u0018\u0001H\u0004\"\b\b\u0000\u0010\u0004*\u00020\u0005*\u00020\u00062\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00040\fH@ø\u0001\u0000¢\u0006\u0002\u0010\r\u001a\u0015\u0010\u0014\u001a\u00020\u0015*\u00020\u0006HHø\u0001\u0000¢\u0006\u0002\u0010\u0007\u001a\u0015\u0010\u0016\u001a\u00020\u0017*\u00020\u0006HHø\u0001\u0000¢\u0006\u0002\u0010\u0007\"\u0014\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0004¢\u0006\u0002\n\u0000*\n\u0010\u0018\"\u00020\u00192\u00020\u0019\u0002\u0004\n\u0002\b\u0019¨\u0006\u001a"}, d2 = {"DoubleReceivePreventionTokenKey", "Lio/ktor/util/AttributeKey;", "Lio/ktor/server/request/DoubleReceivePreventionToken;", "receive", "T", "", "Lio/ktor/server/application/ApplicationCall;", "(Lio/ktor/server/application/ApplicationCall;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "typeInfo", "Lio/ktor/util/reflect/TypeInfo;", "(Lio/ktor/server/application/ApplicationCall;Lio/ktor/util/reflect/TypeInfo;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "type", "Lkotlin/reflect/KClass;", "(Lio/ktor/server/application/ApplicationCall;Lkotlin/reflect/KClass;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "receiveChannel", "Lio/ktor/utils/io/ByteReadChannel;", "receiveMultipart", "Lio/ktor/http/content/MultiPartData;", "receiveNullable", "receiveOrNull", "receiveParameters", "Lio/ktor/http/Parameters;", "receiveText", "", "ContentTransformationException", "Lio/ktor/server/plugins/ContentTransformationException;", "ktor-server-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: ApplicationReceiveFunctions.kt */
public final class ApplicationReceiveFunctionsKt {
    private static final AttributeKey<DoubleReceivePreventionToken> DoubleReceivePreventionTokenKey = new AttributeKey<>("DoubleReceivePreventionToken");

    @Deprecated(level = DeprecationLevel.WARNING, message = "receiveOrNull is ambiguous with receiveNullable and going to be removed in 3.0.0. Please consider replacing it with runCatching with receive or receiveNullable", replaceWith = @ReplaceWith(expression = "kotlin.runCatching { this.receiveNullable<T>() }.getOrNull()", imports = {}))
    public static final /* synthetic */ <T> Object receiveOrNull(ApplicationCall applicationCall, Continuation<? super T> continuation) {
        Intrinsics.reifiedOperationMarker(6, "T");
        Type javaType = TypesJVMKt.getJavaType((KType) null);
        Intrinsics.reifiedOperationMarker(4, "T");
        TypeInfo typeInfoImpl = TypeInfoJvmKt.typeInfoImpl(javaType, Reflection.getOrCreateKotlinClass(Object.class), (KType) null);
        InlineMarker.mark(0);
        Object receiveOrNull = receiveOrNull(applicationCall, typeInfoImpl, continuation);
        InlineMarker.mark(1);
        return receiveOrNull;
    }

    public static final /* synthetic */ <T> Object receive(ApplicationCall applicationCall, Continuation<? super T> continuation) {
        Intrinsics.reifiedOperationMarker(6, "T");
        Type javaType = TypesJVMKt.getJavaType((KType) null);
        Intrinsics.reifiedOperationMarker(4, "T");
        TypeInfo typeInfoImpl = TypeInfoJvmKt.typeInfoImpl(javaType, Reflection.getOrCreateKotlinClass(Object.class), (KType) null);
        InlineMarker.mark(0);
        Object receiveNullable = receiveNullable(applicationCall, typeInfoImpl, continuation);
        InlineMarker.mark(1);
        if (receiveNullable != null) {
            return receiveNullable;
        }
        Intrinsics.reifiedOperationMarker(6, "T");
        Type javaType2 = TypesJVMKt.getJavaType((KType) null);
        Intrinsics.reifiedOperationMarker(4, "T");
        KType kotlinType = TypeInfoJvmKt.typeInfoImpl(javaType2, Reflection.getOrCreateKotlinClass(Object.class), (KType) null).getKotlinType();
        Intrinsics.checkNotNull(kotlinType);
        throw new CannotTransformContentToTypeException(kotlinType);
    }

    public static final /* synthetic */ <T> Object receiveNullable(ApplicationCall applicationCall, Continuation<? super T> continuation) {
        Intrinsics.reifiedOperationMarker(6, "T");
        Type javaType = TypesJVMKt.getJavaType((KType) null);
        Intrinsics.reifiedOperationMarker(4, "T");
        TypeInfo typeInfoImpl = TypeInfoJvmKt.typeInfoImpl(javaType, Reflection.getOrCreateKotlinClass(Object.class), (KType) null);
        InlineMarker.mark(0);
        Object receiveNullable = receiveNullable(applicationCall, typeInfoImpl, continuation);
        InlineMarker.mark(1);
        return receiveNullable;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0032  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <T> java.lang.Object receive(io.ktor.server.application.ApplicationCall r5, kotlin.reflect.KClass<T> r6, kotlin.coroutines.Continuation<? super T> r7) {
        /*
            boolean r0 = r7 instanceof io.ktor.server.request.ApplicationReceiveFunctionsKt$receive$2
            if (r0 == 0) goto L_0x0014
            r0 = r7
            io.ktor.server.request.ApplicationReceiveFunctionsKt$receive$2 r0 = (io.ktor.server.request.ApplicationReceiveFunctionsKt$receive$2) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L_0x0019
        L_0x0014:
            io.ktor.server.request.ApplicationReceiveFunctionsKt$receive$2 r0 = new io.ktor.server.request.ApplicationReceiveFunctionsKt$receive$2
            r0.<init>(r7)
        L_0x0019:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0032
            if (r2 != r3) goto L_0x002a
            kotlin.ResultKt.throwOnFailure(r7)
            goto L_0x004b
        L_0x002a:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x0032:
            kotlin.ResultKt.throwOnFailure(r7)
            kotlin.reflect.KType r7 = io.ktor.server.application.internal.TypeUtilsJvmKt.starProjectedTypeBridge(r6)
            io.ktor.util.reflect.TypeInfo r2 = new io.ktor.util.reflect.TypeInfo
            java.lang.reflect.Type r4 = io.ktor.util.reflect.TypeInfoJvmKt.getPlatformType(r7)
            r2.<init>(r6, r4, r7)
            r0.label = r3
            java.lang.Object r7 = receiveNullable(r5, r2, r0)
            if (r7 != r1) goto L_0x004b
            return r1
        L_0x004b:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r7)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.request.ApplicationReceiveFunctionsKt.receive(io.ktor.server.application.ApplicationCall, kotlin.reflect.KClass, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v10, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v4, resolved type: io.ktor.util.reflect.TypeInfo} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0037  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0079 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x007b  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <T> java.lang.Object receiveNullable(io.ktor.server.application.ApplicationCall r6, io.ktor.util.reflect.TypeInfo r7, kotlin.coroutines.Continuation<? super T> r8) {
        /*
            boolean r0 = r8 instanceof io.ktor.server.request.ApplicationReceiveFunctionsKt$receiveNullable$2
            if (r0 == 0) goto L_0x0014
            r0 = r8
            io.ktor.server.request.ApplicationReceiveFunctionsKt$receiveNullable$2 r0 = (io.ktor.server.request.ApplicationReceiveFunctionsKt$receiveNullable$2) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L_0x0019
        L_0x0014:
            io.ktor.server.request.ApplicationReceiveFunctionsKt$receiveNullable$2 r0 = new io.ktor.server.request.ApplicationReceiveFunctionsKt$receiveNullable$2
            r0.<init>(r8)
        L_0x0019:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0037
            if (r2 != r3) goto L_0x002f
            java.lang.Object r6 = r0.L$0
            r7 = r6
            io.ktor.util.reflect.TypeInfo r7 = (io.ktor.util.reflect.TypeInfo) r7
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x0071
        L_0x002f:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x0037:
            kotlin.ResultKt.throwOnFailure(r8)
            io.ktor.util.Attributes r8 = r6.getAttributes()
            io.ktor.util.AttributeKey<io.ktor.server.request.DoubleReceivePreventionToken> r2 = DoubleReceivePreventionTokenKey
            java.lang.Object r8 = r8.getOrNull(r2)
            io.ktor.server.request.DoubleReceivePreventionToken r8 = (io.ktor.server.request.DoubleReceivePreventionToken) r8
            if (r8 != 0) goto L_0x0051
            io.ktor.util.Attributes r4 = r6.getAttributes()
            io.ktor.server.request.DoubleReceivePreventionToken r5 = io.ktor.server.request.DoubleReceivePreventionToken.INSTANCE
            r4.put(r2, r5)
        L_0x0051:
            io.ktor.server.application.ApplicationCallKt.setReceiveType(r6, r7)
            if (r8 != 0) goto L_0x005e
            io.ktor.server.request.ApplicationRequest r8 = r6.getRequest()
            io.ktor.utils.io.ByteReadChannel r8 = r8.receiveChannel()
        L_0x005e:
            io.ktor.server.request.ApplicationRequest r2 = r6.getRequest()
            io.ktor.server.request.ApplicationReceivePipeline r2 = r2.getPipeline()
            r0.L$0 = r7
            r0.label = r3
            java.lang.Object r8 = r2.execute(r6, r8, r0)
            if (r8 != r1) goto L_0x0071
            return r1
        L_0x0071:
            io.ktor.http.content.NullBody r6 = io.ktor.http.content.NullBody.INSTANCE
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r8, (java.lang.Object) r6)
            if (r6 == 0) goto L_0x007b
            r6 = 0
            return r6
        L_0x007b:
            io.ktor.server.request.DoubleReceivePreventionToken r6 = io.ktor.server.request.DoubleReceivePreventionToken.INSTANCE
            if (r8 == r6) goto L_0x0097
            kotlin.reflect.KClass r6 = r7.getType()
            boolean r6 = r6.isInstance(r8)
            if (r6 == 0) goto L_0x008a
            return r8
        L_0x008a:
            io.ktor.server.plugins.CannotTransformContentToTypeException r6 = new io.ktor.server.plugins.CannotTransformContentToTypeException
            kotlin.reflect.KType r7 = r7.getKotlinType()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r7)
            r6.<init>(r7)
            throw r6
        L_0x0097:
            io.ktor.server.request.RequestAlreadyConsumedException r6 = new io.ktor.server.request.RequestAlreadyConsumedException
            r6.<init>()
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.request.ApplicationReceiveFunctionsKt.receiveNullable(io.ktor.server.application.ApplicationCall, io.ktor.util.reflect.TypeInfo, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0032  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <T> java.lang.Object receive(io.ktor.server.application.ApplicationCall r4, io.ktor.util.reflect.TypeInfo r5, kotlin.coroutines.Continuation<? super T> r6) {
        /*
            boolean r0 = r6 instanceof io.ktor.server.request.ApplicationReceiveFunctionsKt$receive$3
            if (r0 == 0) goto L_0x0014
            r0 = r6
            io.ktor.server.request.ApplicationReceiveFunctionsKt$receive$3 r0 = (io.ktor.server.request.ApplicationReceiveFunctionsKt$receive$3) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L_0x0019
        L_0x0014:
            io.ktor.server.request.ApplicationReceiveFunctionsKt$receive$3 r0 = new io.ktor.server.request.ApplicationReceiveFunctionsKt$receive$3
            r0.<init>(r6)
        L_0x0019:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0032
            if (r2 != r3) goto L_0x002a
            kotlin.ResultKt.throwOnFailure(r6)
            goto L_0x003e
        L_0x002a:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L_0x0032:
            kotlin.ResultKt.throwOnFailure(r6)
            r0.label = r3
            java.lang.Object r6 = receiveNullable(r4, r5, r0)
            if (r6 != r1) goto L_0x003e
            return r1
        L_0x003e:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r6)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.request.ApplicationReceiveFunctionsKt.receive(io.ktor.server.application.ApplicationCall, io.ktor.util.reflect.TypeInfo, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.WARNING, message = "receiveOrNull is ambiguous with receiveNullable and going to be removed in 3.0.0. Please consider replacing it with runCatching with receive or receiveNullable", replaceWith = @kotlin.ReplaceWith(expression = "kotlin.runCatching { this.receiveNullable<T>() }.getOrNull()", imports = {}))
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <T> java.lang.Object receiveOrNull(io.ktor.server.application.ApplicationCall r4, io.ktor.util.reflect.TypeInfo r5, kotlin.coroutines.Continuation<? super T> r6) {
        /*
            boolean r0 = r6 instanceof io.ktor.server.request.ApplicationReceiveFunctionsKt$receiveOrNull$2
            if (r0 == 0) goto L_0x0014
            r0 = r6
            io.ktor.server.request.ApplicationReceiveFunctionsKt$receiveOrNull$2 r0 = (io.ktor.server.request.ApplicationReceiveFunctionsKt$receiveOrNull$2) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L_0x0019
        L_0x0014:
            io.ktor.server.request.ApplicationReceiveFunctionsKt$receiveOrNull$2 r0 = new io.ktor.server.request.ApplicationReceiveFunctionsKt$receiveOrNull$2
            r0.<init>(r6)
        L_0x0019:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0036
            if (r2 != r3) goto L_0x002e
            java.lang.Object r4 = r0.L$0
            io.ktor.server.application.ApplicationCall r4 = (io.ktor.server.application.ApplicationCall) r4
            kotlin.ResultKt.throwOnFailure(r6)     // Catch:{ ContentTransformationException -> 0x0044 }
            goto L_0x0055
        L_0x002e:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L_0x0036:
            kotlin.ResultKt.throwOnFailure(r6)
            r0.L$0 = r4     // Catch:{ ContentTransformationException -> 0x0044 }
            r0.label = r3     // Catch:{ ContentTransformationException -> 0x0044 }
            java.lang.Object r6 = receiveNullable(r4, r5, r0)     // Catch:{ ContentTransformationException -> 0x0044 }
            if (r6 != r1) goto L_0x0055
            return r1
        L_0x0044:
            r5 = move-exception
            io.ktor.server.application.Application r4 = r4.getApplication()
            org.slf4j.Logger r4 = io.ktor.server.application.ApplicationKt.getLog(r4)
            java.lang.String r6 = "Conversion failed, null returned"
            java.lang.Throwable r5 = (java.lang.Throwable) r5
            r4.debug((java.lang.String) r6, (java.lang.Throwable) r5)
            r6 = 0
        L_0x0055:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.request.ApplicationReceiveFunctionsKt.receiveOrNull(io.ktor.server.application.ApplicationCall, io.ktor.util.reflect.TypeInfo, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.WARNING, message = "receiveOrNull is ambiguous with receiveNullable and going to be removed in 3.0.0. Please consider replacing it with runCatching with receive or receiveNullable", replaceWith = @kotlin.ReplaceWith(expression = "kotlin.runCatching { this.receiveNullable<T>() }.getOrNull()", imports = {}))
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <T> java.lang.Object receiveOrNull(io.ktor.server.application.ApplicationCall r4, kotlin.reflect.KClass<T> r5, kotlin.coroutines.Continuation<? super T> r6) {
        /*
            boolean r0 = r6 instanceof io.ktor.server.request.ApplicationReceiveFunctionsKt$receiveOrNull$3
            if (r0 == 0) goto L_0x0014
            r0 = r6
            io.ktor.server.request.ApplicationReceiveFunctionsKt$receiveOrNull$3 r0 = (io.ktor.server.request.ApplicationReceiveFunctionsKt$receiveOrNull$3) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L_0x0019
        L_0x0014:
            io.ktor.server.request.ApplicationReceiveFunctionsKt$receiveOrNull$3 r0 = new io.ktor.server.request.ApplicationReceiveFunctionsKt$receiveOrNull$3
            r0.<init>(r6)
        L_0x0019:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0036
            if (r2 != r3) goto L_0x002e
            java.lang.Object r4 = r0.L$0
            io.ktor.server.application.ApplicationCall r4 = (io.ktor.server.application.ApplicationCall) r4
            kotlin.ResultKt.throwOnFailure(r6)     // Catch:{ ContentTransformationException -> 0x0044 }
            goto L_0x0055
        L_0x002e:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L_0x0036:
            kotlin.ResultKt.throwOnFailure(r6)
            r0.L$0 = r4     // Catch:{ ContentTransformationException -> 0x0044 }
            r0.label = r3     // Catch:{ ContentTransformationException -> 0x0044 }
            java.lang.Object r6 = receive((io.ktor.server.application.ApplicationCall) r4, r5, r0)     // Catch:{ ContentTransformationException -> 0x0044 }
            if (r6 != r1) goto L_0x0055
            return r1
        L_0x0044:
            r5 = move-exception
            io.ktor.server.application.Application r4 = r4.getApplication()
            org.slf4j.Logger r4 = io.ktor.server.application.ApplicationKt.getLog(r4)
            java.lang.String r6 = "Conversion failed, null returned"
            java.lang.Throwable r5 = (java.lang.Throwable) r5
            r4.debug((java.lang.String) r6, (java.lang.Throwable) r5)
            r6 = 0
        L_0x0055:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.request.ApplicationReceiveFunctionsKt.receiveOrNull(io.ktor.server.application.ApplicationCall, kotlin.reflect.KClass, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0042  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0075  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0090  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object receiveText(io.ktor.server.application.ApplicationCall r9, kotlin.coroutines.Continuation<? super java.lang.String> r10) {
        /*
            boolean r0 = r10 instanceof io.ktor.server.request.ApplicationReceiveFunctionsKt$receiveText$1
            if (r0 == 0) goto L_0x0014
            r0 = r10
            io.ktor.server.request.ApplicationReceiveFunctionsKt$receiveText$1 r0 = (io.ktor.server.request.ApplicationReceiveFunctionsKt$receiveText$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r10 = r0.label
            int r10 = r10 - r2
            r0.label = r10
            goto L_0x0019
        L_0x0014:
            io.ktor.server.request.ApplicationReceiveFunctionsKt$receiveText$1 r0 = new io.ktor.server.request.ApplicationReceiveFunctionsKt$receiveText$1
            r0.<init>(r10)
        L_0x0019:
            r4 = r0
            java.lang.Object r10 = r4.result
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r4.label
            r2 = 1
            r7 = 2
            if (r1 == 0) goto L_0x0042
            if (r1 == r2) goto L_0x003a
            if (r1 != r7) goto L_0x0032
            java.lang.Object r9 = r4.L$0
            java.nio.charset.Charset r9 = (java.nio.charset.Charset) r9
            kotlin.ResultKt.throwOnFailure(r10)
            goto L_0x0087
        L_0x0032:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L_0x003a:
            java.lang.Object r9 = r4.L$0
            java.nio.charset.Charset r9 = (java.nio.charset.Charset) r9
            kotlin.ResultKt.throwOnFailure(r10)
            goto L_0x0073
        L_0x0042:
            kotlin.ResultKt.throwOnFailure(r10)
            io.ktor.server.request.ApplicationRequest r10 = r9.getRequest()     // Catch:{ BadContentTypeFormatException -> 0x00b1 }
            java.nio.charset.Charset r10 = io.ktor.server.request.ApplicationRequestPropertiesKt.contentCharset(r10)     // Catch:{ BadContentTypeFormatException -> 0x00b1 }
            if (r10 != 0) goto L_0x0051
            java.nio.charset.Charset r10 = kotlin.text.Charsets.UTF_8     // Catch:{ BadContentTypeFormatException -> 0x00b1 }
        L_0x0051:
            java.lang.Class<io.ktor.utils.io.ByteReadChannel> r1 = io.ktor.utils.io.ByteReadChannel.class
            kotlin.reflect.KType r1 = kotlin.jvm.internal.Reflection.typeOf((java.lang.Class) r1)
            java.lang.reflect.Type r3 = kotlin.reflect.TypesJVMKt.getJavaType((kotlin.reflect.KType) r1)
            java.lang.Class<io.ktor.utils.io.ByteReadChannel> r5 = io.ktor.utils.io.ByteReadChannel.class
            kotlin.reflect.KClass r5 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r5)
            io.ktor.util.reflect.TypeInfo r1 = io.ktor.util.reflect.TypeInfoJvmKt.typeInfoImpl(r3, r5, r1)
            r4.L$0 = r10
            r4.label = r2
            java.lang.Object r9 = receiveNullable(r9, r1, r4)
            if (r9 != r0) goto L_0x0070
            return r0
        L_0x0070:
            r8 = r10
            r10 = r9
            r9 = r8
        L_0x0073:
            if (r10 == 0) goto L_0x0090
            r1 = r10
            io.ktor.utils.io.ByteReadChannel r1 = (io.ktor.utils.io.ByteReadChannel) r1
            r4.L$0 = r9
            r4.label = r7
            r2 = 0
            r5 = 1
            r6 = 0
            java.lang.Object r10 = io.ktor.utils.io.ByteReadChannel.DefaultImpls.readRemaining$default(r1, r2, r4, r5, r6)
            if (r10 != r0) goto L_0x0087
            return r0
        L_0x0087:
            io.ktor.utils.io.core.Input r10 = (io.ktor.utils.io.core.Input) r10
            r0 = 0
            r1 = 0
            java.lang.String r9 = io.ktor.utils.io.core.StringsKt.readText$default((io.ktor.utils.io.core.Input) r10, (java.nio.charset.Charset) r9, (int) r0, (int) r7, (java.lang.Object) r1)
            return r9
        L_0x0090:
            io.ktor.server.plugins.CannotTransformContentToTypeException r9 = new io.ktor.server.plugins.CannotTransformContentToTypeException
            java.lang.Class<io.ktor.utils.io.ByteReadChannel> r10 = io.ktor.utils.io.ByteReadChannel.class
            kotlin.reflect.KType r10 = kotlin.jvm.internal.Reflection.typeOf((java.lang.Class) r10)
            java.lang.reflect.Type r0 = kotlin.reflect.TypesJVMKt.getJavaType((kotlin.reflect.KType) r10)
            java.lang.Class<io.ktor.utils.io.ByteReadChannel> r1 = io.ktor.utils.io.ByteReadChannel.class
            kotlin.reflect.KClass r1 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r1)
            io.ktor.util.reflect.TypeInfo r10 = io.ktor.util.reflect.TypeInfoJvmKt.typeInfoImpl(r0, r1, r10)
            kotlin.reflect.KType r10 = r10.getKotlinType()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r10)
            r9.<init>(r10)
            throw r9
        L_0x00b1:
            r10 = move-exception
            io.ktor.server.plugins.BadRequestException r0 = new io.ktor.server.plugins.BadRequestException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Illegal Content-Type format: "
            r1.<init>(r2)
            io.ktor.server.request.ApplicationRequest r9 = r9.getRequest()
            io.ktor.http.Headers r9 = r9.getHeaders()
            io.ktor.http.HttpHeaders r2 = io.ktor.http.HttpHeaders.INSTANCE
            java.lang.String r2 = r2.getContentType()
            java.lang.String r9 = r9.get(r2)
            r1.append(r9)
            java.lang.String r9 = r1.toString()
            java.lang.Throwable r10 = (java.lang.Throwable) r10
            r0.<init>(r9, r10)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.request.ApplicationReceiveFunctionsKt.receiveText(io.ktor.server.application.ApplicationCall, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private static final Object receiveText$$forInline(ApplicationCall applicationCall, Continuation<? super String> continuation) {
        try {
            Charset contentCharset = ApplicationRequestPropertiesKt.contentCharset(applicationCall.getRequest());
            if (contentCharset == null) {
                contentCharset = Charsets.UTF_8;
            }
            KType typeOf = Reflection.typeOf(ByteReadChannel.class);
            TypeInfo typeInfoImpl = TypeInfoJvmKt.typeInfoImpl(TypesJVMKt.getJavaType(typeOf), Reflection.getOrCreateKotlinClass(ByteReadChannel.class), typeOf);
            InlineMarker.mark(0);
            Object receiveNullable = receiveNullable(applicationCall, typeInfoImpl, continuation);
            InlineMarker.mark(1);
            if (receiveNullable != null) {
                InlineMarker.mark(0);
                Object readRemaining$default = ByteReadChannel.DefaultImpls.readRemaining$default((ByteReadChannel) receiveNullable, 0, continuation, 1, (Object) null);
                InlineMarker.mark(1);
                return StringsKt.readText$default((Input) readRemaining$default, contentCharset, 0, 2, (Object) null);
            }
            KType typeOf2 = Reflection.typeOf(ByteReadChannel.class);
            KType kotlinType = TypeInfoJvmKt.typeInfoImpl(TypesJVMKt.getJavaType(typeOf2), Reflection.getOrCreateKotlinClass(ByteReadChannel.class), typeOf2).getKotlinType();
            Intrinsics.checkNotNull(kotlinType);
            throw new CannotTransformContentToTypeException(kotlinType);
        } catch (BadContentTypeFormatException e) {
            throw new BadRequestException("Illegal Content-Type format: " + applicationCall.getRequest().getHeaders().get(HttpHeaders.INSTANCE.getContentType()), e);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0032  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0054 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0055  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object receiveChannel(io.ktor.server.application.ApplicationCall r5, kotlin.coroutines.Continuation<? super io.ktor.utils.io.ByteReadChannel> r6) {
        /*
            boolean r0 = r6 instanceof io.ktor.server.request.ApplicationReceiveFunctionsKt$receiveChannel$1
            if (r0 == 0) goto L_0x0014
            r0 = r6
            io.ktor.server.request.ApplicationReceiveFunctionsKt$receiveChannel$1 r0 = (io.ktor.server.request.ApplicationReceiveFunctionsKt$receiveChannel$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L_0x0019
        L_0x0014:
            io.ktor.server.request.ApplicationReceiveFunctionsKt$receiveChannel$1 r0 = new io.ktor.server.request.ApplicationReceiveFunctionsKt$receiveChannel$1
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
            java.lang.Class<io.ktor.utils.io.ByteReadChannel> r6 = io.ktor.utils.io.ByteReadChannel.class
            kotlin.reflect.KType r6 = kotlin.jvm.internal.Reflection.typeOf((java.lang.Class) r6)
            java.lang.reflect.Type r2 = kotlin.reflect.TypesJVMKt.getJavaType((kotlin.reflect.KType) r6)
            java.lang.Class<io.ktor.utils.io.ByteReadChannel> r4 = io.ktor.utils.io.ByteReadChannel.class
            kotlin.reflect.KClass r4 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r4)
            io.ktor.util.reflect.TypeInfo r6 = io.ktor.util.reflect.TypeInfoJvmKt.typeInfoImpl(r2, r4, r6)
            r0.label = r3
            java.lang.Object r6 = receiveNullable(r5, r6, r0)
            if (r6 != r1) goto L_0x0052
            return r1
        L_0x0052:
            if (r6 == 0) goto L_0x0055
            return r6
        L_0x0055:
            io.ktor.server.plugins.CannotTransformContentToTypeException r5 = new io.ktor.server.plugins.CannotTransformContentToTypeException
            java.lang.Class<io.ktor.utils.io.ByteReadChannel> r6 = io.ktor.utils.io.ByteReadChannel.class
            kotlin.reflect.KType r6 = kotlin.jvm.internal.Reflection.typeOf((java.lang.Class) r6)
            java.lang.reflect.Type r0 = kotlin.reflect.TypesJVMKt.getJavaType((kotlin.reflect.KType) r6)
            java.lang.Class<io.ktor.utils.io.ByteReadChannel> r1 = io.ktor.utils.io.ByteReadChannel.class
            kotlin.reflect.KClass r1 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r1)
            io.ktor.util.reflect.TypeInfo r6 = io.ktor.util.reflect.TypeInfoJvmKt.typeInfoImpl(r0, r1, r6)
            kotlin.reflect.KType r6 = r6.getKotlinType()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r6)
            r5.<init>(r6)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.request.ApplicationReceiveFunctionsKt.receiveChannel(io.ktor.server.application.ApplicationCall, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0032  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0054 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0055  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object receiveMultipart(io.ktor.server.application.ApplicationCall r5, kotlin.coroutines.Continuation<? super io.ktor.http.content.MultiPartData> r6) {
        /*
            boolean r0 = r6 instanceof io.ktor.server.request.ApplicationReceiveFunctionsKt$receiveMultipart$1
            if (r0 == 0) goto L_0x0014
            r0 = r6
            io.ktor.server.request.ApplicationReceiveFunctionsKt$receiveMultipart$1 r0 = (io.ktor.server.request.ApplicationReceiveFunctionsKt$receiveMultipart$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L_0x0019
        L_0x0014:
            io.ktor.server.request.ApplicationReceiveFunctionsKt$receiveMultipart$1 r0 = new io.ktor.server.request.ApplicationReceiveFunctionsKt$receiveMultipart$1
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
            java.lang.Class<io.ktor.http.content.MultiPartData> r6 = io.ktor.http.content.MultiPartData.class
            kotlin.reflect.KType r6 = kotlin.jvm.internal.Reflection.typeOf((java.lang.Class) r6)
            java.lang.reflect.Type r2 = kotlin.reflect.TypesJVMKt.getJavaType((kotlin.reflect.KType) r6)
            java.lang.Class<io.ktor.http.content.MultiPartData> r4 = io.ktor.http.content.MultiPartData.class
            kotlin.reflect.KClass r4 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r4)
            io.ktor.util.reflect.TypeInfo r6 = io.ktor.util.reflect.TypeInfoJvmKt.typeInfoImpl(r2, r4, r6)
            r0.label = r3
            java.lang.Object r6 = receiveNullable(r5, r6, r0)
            if (r6 != r1) goto L_0x0052
            return r1
        L_0x0052:
            if (r6 == 0) goto L_0x0055
            return r6
        L_0x0055:
            io.ktor.server.plugins.CannotTransformContentToTypeException r5 = new io.ktor.server.plugins.CannotTransformContentToTypeException
            java.lang.Class<io.ktor.http.content.MultiPartData> r6 = io.ktor.http.content.MultiPartData.class
            kotlin.reflect.KType r6 = kotlin.jvm.internal.Reflection.typeOf((java.lang.Class) r6)
            java.lang.reflect.Type r0 = kotlin.reflect.TypesJVMKt.getJavaType((kotlin.reflect.KType) r6)
            java.lang.Class<io.ktor.http.content.MultiPartData> r1 = io.ktor.http.content.MultiPartData.class
            kotlin.reflect.KClass r1 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r1)
            io.ktor.util.reflect.TypeInfo r6 = io.ktor.util.reflect.TypeInfoJvmKt.typeInfoImpl(r0, r1, r6)
            kotlin.reflect.KType r6 = r6.getKotlinType()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r6)
            r5.<init>(r6)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.request.ApplicationReceiveFunctionsKt.receiveMultipart(io.ktor.server.application.ApplicationCall, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0032  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0054 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0055  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object receiveParameters(io.ktor.server.application.ApplicationCall r5, kotlin.coroutines.Continuation<? super io.ktor.http.Parameters> r6) {
        /*
            boolean r0 = r6 instanceof io.ktor.server.request.ApplicationReceiveFunctionsKt$receiveParameters$1
            if (r0 == 0) goto L_0x0014
            r0 = r6
            io.ktor.server.request.ApplicationReceiveFunctionsKt$receiveParameters$1 r0 = (io.ktor.server.request.ApplicationReceiveFunctionsKt$receiveParameters$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L_0x0019
        L_0x0014:
            io.ktor.server.request.ApplicationReceiveFunctionsKt$receiveParameters$1 r0 = new io.ktor.server.request.ApplicationReceiveFunctionsKt$receiveParameters$1
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
            java.lang.Class<io.ktor.http.Parameters> r6 = io.ktor.http.Parameters.class
            kotlin.reflect.KType r6 = kotlin.jvm.internal.Reflection.typeOf((java.lang.Class) r6)
            java.lang.reflect.Type r2 = kotlin.reflect.TypesJVMKt.getJavaType((kotlin.reflect.KType) r6)
            java.lang.Class<io.ktor.http.Parameters> r4 = io.ktor.http.Parameters.class
            kotlin.reflect.KClass r4 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r4)
            io.ktor.util.reflect.TypeInfo r6 = io.ktor.util.reflect.TypeInfoJvmKt.typeInfoImpl(r2, r4, r6)
            r0.label = r3
            java.lang.Object r6 = receiveNullable(r5, r6, r0)
            if (r6 != r1) goto L_0x0052
            return r1
        L_0x0052:
            if (r6 == 0) goto L_0x0055
            return r6
        L_0x0055:
            io.ktor.server.plugins.CannotTransformContentToTypeException r5 = new io.ktor.server.plugins.CannotTransformContentToTypeException
            java.lang.Class<io.ktor.http.Parameters> r6 = io.ktor.http.Parameters.class
            kotlin.reflect.KType r6 = kotlin.jvm.internal.Reflection.typeOf((java.lang.Class) r6)
            java.lang.reflect.Type r0 = kotlin.reflect.TypesJVMKt.getJavaType((kotlin.reflect.KType) r6)
            java.lang.Class<io.ktor.http.Parameters> r1 = io.ktor.http.Parameters.class
            kotlin.reflect.KClass r1 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r1)
            io.ktor.util.reflect.TypeInfo r6 = io.ktor.util.reflect.TypeInfoJvmKt.typeInfoImpl(r0, r1, r6)
            kotlin.reflect.KType r6 = r6.getKotlinType()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r6)
            r5.<init>(r6)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.request.ApplicationReceiveFunctionsKt.receiveParameters(io.ktor.server.application.ApplicationCall, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private static final Object receiveChannel$$forInline(ApplicationCall applicationCall, Continuation<? super ByteReadChannel> continuation) {
        KType typeOf = Reflection.typeOf(ByteReadChannel.class);
        TypeInfo typeInfoImpl = TypeInfoJvmKt.typeInfoImpl(TypesJVMKt.getJavaType(typeOf), Reflection.getOrCreateKotlinClass(ByteReadChannel.class), typeOf);
        InlineMarker.mark(0);
        Object receiveNullable = receiveNullable(applicationCall, typeInfoImpl, continuation);
        InlineMarker.mark(1);
        if (receiveNullable != null) {
            return receiveNullable;
        }
        KType typeOf2 = Reflection.typeOf(ByteReadChannel.class);
        KType kotlinType = TypeInfoJvmKt.typeInfoImpl(TypesJVMKt.getJavaType(typeOf2), Reflection.getOrCreateKotlinClass(ByteReadChannel.class), typeOf2).getKotlinType();
        Intrinsics.checkNotNull(kotlinType);
        throw new CannotTransformContentToTypeException(kotlinType);
    }

    private static final Object receiveMultipart$$forInline(ApplicationCall applicationCall, Continuation<? super MultiPartData> continuation) {
        KType typeOf = Reflection.typeOf(MultiPartData.class);
        TypeInfo typeInfoImpl = TypeInfoJvmKt.typeInfoImpl(TypesJVMKt.getJavaType(typeOf), Reflection.getOrCreateKotlinClass(MultiPartData.class), typeOf);
        InlineMarker.mark(0);
        Object receiveNullable = receiveNullable(applicationCall, typeInfoImpl, continuation);
        InlineMarker.mark(1);
        if (receiveNullable != null) {
            return receiveNullable;
        }
        KType typeOf2 = Reflection.typeOf(MultiPartData.class);
        KType kotlinType = TypeInfoJvmKt.typeInfoImpl(TypesJVMKt.getJavaType(typeOf2), Reflection.getOrCreateKotlinClass(MultiPartData.class), typeOf2).getKotlinType();
        Intrinsics.checkNotNull(kotlinType);
        throw new CannotTransformContentToTypeException(kotlinType);
    }

    private static final Object receiveParameters$$forInline(ApplicationCall applicationCall, Continuation<? super Parameters> continuation) {
        KType typeOf = Reflection.typeOf(Parameters.class);
        TypeInfo typeInfoImpl = TypeInfoJvmKt.typeInfoImpl(TypesJVMKt.getJavaType(typeOf), Reflection.getOrCreateKotlinClass(Parameters.class), typeOf);
        InlineMarker.mark(0);
        Object receiveNullable = receiveNullable(applicationCall, typeInfoImpl, continuation);
        InlineMarker.mark(1);
        if (receiveNullable != null) {
            return receiveNullable;
        }
        KType typeOf2 = Reflection.typeOf(Parameters.class);
        KType kotlinType = TypeInfoJvmKt.typeInfoImpl(TypesJVMKt.getJavaType(typeOf2), Reflection.getOrCreateKotlinClass(Parameters.class), typeOf2).getKotlinType();
        Intrinsics.checkNotNull(kotlinType);
        throw new CannotTransformContentToTypeException(kotlinType);
    }
}
