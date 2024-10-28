package io.ktor.server.response;

import io.ktor.http.ContentType;
import io.ktor.http.HttpHeaders;
import io.ktor.http.HttpStatusCode;
import io.ktor.http.URLBuilder;
import io.ktor.http.Url;
import io.ktor.http.content.ByteArrayContent;
import io.ktor.http.content.ChannelWriterContent;
import io.ktor.http.content.NullBody;
import io.ktor.http.content.OutgoingContent;
import io.ktor.http.content.TextContent;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.util.URLBuilderKt;
import io.ktor.util.reflect.TypeInfo;
import io.ktor.util.reflect.TypeInfoJvmKt;
import io.ktor.utils.io.ByteWriteChannel;
import java.lang.reflect.Type;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KType;
import kotlin.reflect.TypesJVMKt;

@Metadata(d1 = {"\u0000v\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0001\u001a+\u0010\u0004\u001a\u00020\u0005\"\n\b\u0000\u0010\u0006\u0018\u0001*\u00020\u0007*\u00020\u00022\u0006\u0010\b\u001a\u0002H\u0006HHø\u0001\u0000¢\u0006\u0004\b\t\u0010\n\u001a3\u0010\u0004\u001a\u00020\u0005\"\n\b\u0000\u0010\u0006\u0018\u0001*\u00020\u0007*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\b\u001a\u0002H\u0006HHø\u0001\u0000¢\u0006\u0004\b\t\u0010\r\u001a/\u0010\u0004\u001a\u00020\u0005*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\b\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u000e\u001a\u00020\u000fH@ø\u0001\u0000¢\u0006\u0002\u0010\u0010\u001a'\u0010\u0004\u001a\u00020\u0005*\u00020\u00022\b\u0010\b\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u000e\u001a\u00020\u000fH@ø\u0001\u0000¢\u0006\u0002\u0010\u0011\u001aK\u0010\u0012\u001a\u00020\u0005*\u00020\u00022\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\u001c\u0010\u0013\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u0015\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0014H@ø\u0001\u0000¢\u0006\u0002\u0010\u0017\u001aP\u0010\u0012\u001a\u00020\u0005*\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u00162\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0019\b\u0002\u0010\u0019\u001a\u0013\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u00050\u0014¢\u0006\u0002\b\u001bH@ø\u0001\u0000¢\u0006\u0002\u0010\u001c\u001ab\u0010\u001d\u001a\u00020\u0005*\u00020\u00022\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f2'\u0010 \u001a#\b\u0001\u0012\u0004\u0012\u00020\"\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0015\u0012\u0006\u0012\u0004\u0018\u00010\u00070!¢\u0006\u0002\b\u001bH@ø\u0001\u0000¢\u0006\u0002\u0010#\u001a%\u0010$\u001a\u00020\u0005\"\u0006\b\u0000\u0010\u0006\u0018\u0001*\u00020\u00022\u0006\u0010\b\u001a\u0002H\u0006HHø\u0001\u0000¢\u0006\u0002\u0010\n\u001a-\u0010$\u001a\u00020\u0005\"\u0006\b\u0000\u0010\u0006\u0018\u0001*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\b\u001a\u0002H\u0006HHø\u0001\u0000¢\u0006\u0002\u0010\r\u001a'\u0010%\u001a\u00020\u0005*\u00020\u00022\u0006\u0010&\u001a\u00020'2\b\b\u0002\u0010(\u001a\u00020)H@ø\u0001\u0000¢\u0006\u0002\u0010*\u001a8\u0010%\u001a\u00020\u0005*\u00020\u00022\b\b\u0002\u0010(\u001a\u00020)2\u0017\u0010+\u001a\u0013\u0012\u0004\u0012\u00020,\u0012\u0004\u0012\u00020\u00050\u0014¢\u0006\u0002\b\u001bHHø\u0001\u0000¢\u0006\u0002\u0010-\u001a'\u0010%\u001a\u00020\u0005*\u00020\u00022\u0006\u0010&\u001a\u00020.2\b\b\u0002\u0010(\u001a\u00020)H@ø\u0001\u0000¢\u0006\u0002\u0010/\u001aK\u00100\u001a\u00020\u0005*\u00020\u00022\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\u001c\u0010\u0013\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020.0\u0015\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0014H@ø\u0001\u0000¢\u0006\u0002\u0010\u0017\u001aP\u00100\u001a\u00020\u0005*\u00020\u00022\u0006\u00101\u001a\u00020.2\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0019\b\u0002\u0010\u0019\u001a\u0013\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u00050\u0014¢\u0006\u0002\b\u001bH@ø\u0001\u0000¢\u0006\u0002\u00102\u0002\u0004\n\u0002\b\u0019¨\u00063"}, d2 = {"defaultTextContentType", "Lio/ktor/http/ContentType;", "Lio/ktor/server/application/ApplicationCall;", "contentType", "respond", "", "T", "", "message", "respondWithType", "(Lio/ktor/server/application/ApplicationCall;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "status", "Lio/ktor/http/HttpStatusCode;", "(Lio/ktor/server/application/ApplicationCall;Lio/ktor/http/HttpStatusCode;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "messageType", "Lio/ktor/util/reflect/TypeInfo;", "(Lio/ktor/server/application/ApplicationCall;Lio/ktor/http/HttpStatusCode;Ljava/lang/Object;Lio/ktor/util/reflect/TypeInfo;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "(Lio/ktor/server/application/ApplicationCall;Ljava/lang/Object;Lio/ktor/util/reflect/TypeInfo;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "respondBytes", "provider", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "", "(Lio/ktor/server/application/ApplicationCall;Lio/ktor/http/ContentType;Lio/ktor/http/HttpStatusCode;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "bytes", "configure", "Lio/ktor/http/content/OutgoingContent;", "Lkotlin/ExtensionFunctionType;", "(Lio/ktor/server/application/ApplicationCall;[BLio/ktor/http/ContentType;Lio/ktor/http/HttpStatusCode;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "respondBytesWriter", "contentLength", "", "producer", "Lkotlin/Function2;", "Lio/ktor/utils/io/ByteWriteChannel;", "(Lio/ktor/server/application/ApplicationCall;Lio/ktor/http/ContentType;Lio/ktor/http/HttpStatusCode;Ljava/lang/Long;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "respondNullable", "respondRedirect", "url", "Lio/ktor/http/Url;", "permanent", "", "(Lio/ktor/server/application/ApplicationCall;Lio/ktor/http/Url;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "block", "Lio/ktor/http/URLBuilder;", "(Lio/ktor/server/application/ApplicationCall;ZLkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "", "(Lio/ktor/server/application/ApplicationCall;Ljava/lang/String;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "respondText", "text", "(Lio/ktor/server/application/ApplicationCall;Ljava/lang/String;Lio/ktor/http/ContentType;Lio/ktor/http/HttpStatusCode;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ktor-server-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: ApplicationResponseFunctions.kt */
public final class ApplicationResponseFunctionsKt {
    public static final /* synthetic */ <T> Object respondWithType(ApplicationCall applicationCall, T t, Continuation<? super Unit> continuation) {
        if (!(t instanceof OutgoingContent) && !(t instanceof byte[])) {
            ApplicationResponse response = applicationCall.getResponse();
            Intrinsics.reifiedOperationMarker(6, "T");
            Type javaType = TypesJVMKt.getJavaType((KType) null);
            Intrinsics.reifiedOperationMarker(4, "T");
            ResponseTypeKt.setResponseType(response, TypeInfoJvmKt.typeInfoImpl(javaType, Reflection.getOrCreateKotlinClass(Object.class), (KType) null));
        }
        ApplicationSendPipeline pipeline = applicationCall.getResponse().getPipeline();
        Intrinsics.checkNotNull(t, "null cannot be cast to non-null type kotlin.Any");
        Object obj = (Object) t;
        InlineMarker.mark(0);
        pipeline.execute(applicationCall, t, continuation);
        InlineMarker.mark(1);
        return Unit.INSTANCE;
    }

    public static final Object respond(ApplicationCall applicationCall, Object obj, TypeInfo typeInfo, Continuation<? super Unit> continuation) {
        ResponseTypeKt.setResponseType(applicationCall.getResponse(), typeInfo);
        ApplicationSendPipeline pipeline = applicationCall.getResponse().getPipeline();
        if (obj == null) {
            obj = NullBody.INSTANCE;
        }
        Object execute = pipeline.execute(applicationCall, obj, continuation);
        return execute == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? execute : Unit.INSTANCE;
    }

    public static final /* synthetic */ <T> Object respondNullable(ApplicationCall applicationCall, T t, Continuation<? super Unit> continuation) {
        if (!(t instanceof OutgoingContent) && !(t instanceof byte[])) {
            ApplicationResponse response = applicationCall.getResponse();
            Intrinsics.reifiedOperationMarker(6, "T");
            Type javaType = TypesJVMKt.getJavaType((KType) null);
            Intrinsics.reifiedOperationMarker(4, "T");
            ResponseTypeKt.setResponseType(response, TypeInfoJvmKt.typeInfoImpl(javaType, Reflection.getOrCreateKotlinClass(Object.class), (KType) null));
        }
        ApplicationSendPipeline pipeline = applicationCall.getResponse().getPipeline();
        if (t == null) {
            t = NullBody.INSTANCE;
        }
        InlineMarker.mark(0);
        pipeline.execute(applicationCall, t, continuation);
        InlineMarker.mark(1);
        return Unit.INSTANCE;
    }

    public static final /* synthetic */ <T> Object respondWithType(ApplicationCall applicationCall, HttpStatusCode httpStatusCode, T t, Continuation<? super Unit> continuation) {
        applicationCall.getResponse().status(httpStatusCode);
        if (!(t instanceof OutgoingContent) && !(t instanceof byte[])) {
            ApplicationResponse response = applicationCall.getResponse();
            Intrinsics.reifiedOperationMarker(6, "T");
            Type javaType = TypesJVMKt.getJavaType((KType) null);
            Intrinsics.reifiedOperationMarker(4, "T");
            ResponseTypeKt.setResponseType(response, TypeInfoJvmKt.typeInfoImpl(javaType, Reflection.getOrCreateKotlinClass(Object.class), (KType) null));
        }
        ApplicationSendPipeline pipeline = applicationCall.getResponse().getPipeline();
        Intrinsics.checkNotNull(t, "null cannot be cast to non-null type kotlin.Any");
        Object obj = (Object) t;
        InlineMarker.mark(0);
        pipeline.execute(applicationCall, t, continuation);
        InlineMarker.mark(1);
        Unit unit = Unit.INSTANCE;
        return Unit.INSTANCE;
    }

    public static final Object respond(ApplicationCall applicationCall, HttpStatusCode httpStatusCode, Object obj, TypeInfo typeInfo, Continuation<? super Unit> continuation) {
        applicationCall.getResponse().status(httpStatusCode);
        Object respond = respond(applicationCall, obj, typeInfo, continuation);
        return respond == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? respond : Unit.INSTANCE;
    }

    public static final /* synthetic */ <T> Object respondNullable(ApplicationCall applicationCall, HttpStatusCode httpStatusCode, T t, Continuation<? super Unit> continuation) {
        applicationCall.getResponse().status(httpStatusCode);
        if (!(t instanceof OutgoingContent) && !(t instanceof byte[])) {
            ApplicationResponse response = applicationCall.getResponse();
            Intrinsics.reifiedOperationMarker(6, "T");
            Type javaType = TypesJVMKt.getJavaType((KType) null);
            Intrinsics.reifiedOperationMarker(4, "T");
            ResponseTypeKt.setResponseType(response, TypeInfoJvmKt.typeInfoImpl(javaType, Reflection.getOrCreateKotlinClass(Object.class), (KType) null));
        }
        ApplicationSendPipeline pipeline = applicationCall.getResponse().getPipeline();
        if (t == null) {
            t = NullBody.INSTANCE;
        }
        InlineMarker.mark(0);
        pipeline.execute(applicationCall, t, continuation);
        InlineMarker.mark(1);
        Unit unit = Unit.INSTANCE;
        return Unit.INSTANCE;
    }

    public static /* synthetic */ Object respondRedirect$default(ApplicationCall applicationCall, String str, boolean z, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return respondRedirect(applicationCall, str, z, (Continuation<? super Unit>) continuation);
    }

    public static final Object respondRedirect(ApplicationCall applicationCall, String str, boolean z, Continuation<? super Unit> continuation) {
        ResponseHeaders.append$default(applicationCall.getResponse().getHeaders(), HttpHeaders.INSTANCE.getLocation(), str, false, 4, (Object) null);
        HttpStatusCode.Companion companion = HttpStatusCode.Companion;
        HttpStatusCode movedPermanently = z ? companion.getMovedPermanently() : companion.getFound();
        if (!(movedPermanently instanceof OutgoingContent) && !(movedPermanently instanceof byte[])) {
            ApplicationResponse response = applicationCall.getResponse();
            KType typeOf = Reflection.typeOf(HttpStatusCode.class);
            ResponseTypeKt.setResponseType(response, TypeInfoJvmKt.typeInfoImpl(TypesJVMKt.getJavaType(typeOf), Reflection.getOrCreateKotlinClass(HttpStatusCode.class), typeOf));
        }
        ApplicationSendPipeline pipeline = applicationCall.getResponse().getPipeline();
        Intrinsics.checkNotNull(movedPermanently, "null cannot be cast to non-null type kotlin.Any");
        Object execute = pipeline.execute(applicationCall, movedPermanently, continuation);
        if (execute == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            return execute;
        }
        return Unit.INSTANCE;
    }

    public static /* synthetic */ Object respondRedirect$default(ApplicationCall applicationCall, Url url, boolean z, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return respondRedirect(applicationCall, url, z, (Continuation<? super Unit>) continuation);
    }

    public static final Object respondRedirect(ApplicationCall applicationCall, Url url, boolean z, Continuation<? super Unit> continuation) {
        Object respondRedirect = respondRedirect(applicationCall, url.toString(), z, continuation);
        return respondRedirect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? respondRedirect : Unit.INSTANCE;
    }

    public static /* synthetic */ Object respondText$default(ApplicationCall applicationCall, String str, ContentType contentType, HttpStatusCode httpStatusCode, Function1 function1, Continuation continuation, int i, Object obj) {
        ContentType contentType2 = (i & 2) != 0 ? null : contentType;
        HttpStatusCode httpStatusCode2 = (i & 4) != 0 ? null : httpStatusCode;
        if ((i & 8) != 0) {
            function1 = ApplicationResponseFunctionsKt$respondText$2.INSTANCE;
        }
        return respondText(applicationCall, str, contentType2, httpStatusCode2, function1, continuation);
    }

    public static final Object respondText(ApplicationCall applicationCall, String str, ContentType contentType, HttpStatusCode httpStatusCode, Function1<? super OutgoingContent, Unit> function1, Continuation<? super Unit> continuation) {
        TextContent textContent = new TextContent(str, defaultTextContentType(applicationCall, contentType), httpStatusCode);
        function1.invoke(textContent);
        if (!(textContent instanceof OutgoingContent) && !(textContent instanceof byte[])) {
            ApplicationResponse response = applicationCall.getResponse();
            KType typeOf = Reflection.typeOf(TextContent.class);
            ResponseTypeKt.setResponseType(response, TypeInfoJvmKt.typeInfoImpl(TypesJVMKt.getJavaType(typeOf), Reflection.getOrCreateKotlinClass(TextContent.class), typeOf));
        }
        Object execute = applicationCall.getResponse().getPipeline().execute(applicationCall, textContent, continuation);
        if (execute == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            return execute;
        }
        return Unit.INSTANCE;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v4, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v6, resolved type: io.ktor.http.HttpStatusCode} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v5, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v8, resolved type: io.ktor.http.ContentType} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0047  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x00a0 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object respondText(io.ktor.server.application.ApplicationCall r5, io.ktor.http.ContentType r6, io.ktor.http.HttpStatusCode r7, kotlin.jvm.functions.Function1<? super kotlin.coroutines.Continuation<? super java.lang.String>, ? extends java.lang.Object> r8, kotlin.coroutines.Continuation<? super kotlin.Unit> r9) {
        /*
            boolean r0 = r9 instanceof io.ktor.server.response.ApplicationResponseFunctionsKt$respondText$3
            if (r0 == 0) goto L_0x0014
            r0 = r9
            io.ktor.server.response.ApplicationResponseFunctionsKt$respondText$3 r0 = (io.ktor.server.response.ApplicationResponseFunctionsKt$respondText$3) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L_0x0019
        L_0x0014:
            io.ktor.server.response.ApplicationResponseFunctionsKt$respondText$3 r0 = new io.ktor.server.response.ApplicationResponseFunctionsKt$respondText$3
            r0.<init>(r9)
        L_0x0019:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x0047
            if (r2 == r4) goto L_0x0035
            if (r2 != r3) goto L_0x002d
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x00a1
        L_0x002d:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x0035:
            java.lang.Object r5 = r0.L$2
            r7 = r5
            io.ktor.http.HttpStatusCode r7 = (io.ktor.http.HttpStatusCode) r7
            java.lang.Object r5 = r0.L$1
            r6 = r5
            io.ktor.http.ContentType r6 = (io.ktor.http.ContentType) r6
            java.lang.Object r5 = r0.L$0
            io.ktor.server.application.ApplicationCall r5 = (io.ktor.server.application.ApplicationCall) r5
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x0059
        L_0x0047:
            kotlin.ResultKt.throwOnFailure(r9)
            r0.L$0 = r5
            r0.L$1 = r6
            r0.L$2 = r7
            r0.label = r4
            java.lang.Object r9 = r8.invoke(r0)
            if (r9 != r1) goto L_0x0059
            return r1
        L_0x0059:
            java.lang.String r9 = (java.lang.String) r9
            io.ktor.http.ContentType r6 = defaultTextContentType(r5, r6)
            io.ktor.http.content.TextContent r8 = new io.ktor.http.content.TextContent
            r8.<init>(r9, r6, r7)
            boolean r6 = r8 instanceof io.ktor.http.content.OutgoingContent
            if (r6 != 0) goto L_0x0087
            boolean r6 = r8 instanceof byte[]
            if (r6 != 0) goto L_0x0087
            io.ktor.server.response.ApplicationResponse r6 = r5.getResponse()
            java.lang.Class<io.ktor.http.content.TextContent> r7 = io.ktor.http.content.TextContent.class
            kotlin.reflect.KType r7 = kotlin.jvm.internal.Reflection.typeOf((java.lang.Class) r7)
            java.lang.reflect.Type r9 = kotlin.reflect.TypesJVMKt.getJavaType((kotlin.reflect.KType) r7)
            java.lang.Class<io.ktor.http.content.TextContent> r2 = io.ktor.http.content.TextContent.class
            kotlin.reflect.KClass r2 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r2)
            io.ktor.util.reflect.TypeInfo r7 = io.ktor.util.reflect.TypeInfoJvmKt.typeInfoImpl(r9, r2, r7)
            io.ktor.server.response.ResponseTypeKt.setResponseType(r6, r7)
        L_0x0087:
            io.ktor.server.response.ApplicationResponse r6 = r5.getResponse()
            io.ktor.server.response.ApplicationSendPipeline r6 = r6.getPipeline()
            java.lang.Object r8 = (java.lang.Object) r8
            r7 = 0
            r0.L$0 = r7
            r0.L$1 = r7
            r0.L$2 = r7
            r0.label = r3
            java.lang.Object r5 = r6.execute(r5, r8, r0)
            if (r5 != r1) goto L_0x00a1
            return r1
        L_0x00a1:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.response.ApplicationResponseFunctionsKt.respondText(io.ktor.server.application.ApplicationCall, io.ktor.http.ContentType, io.ktor.http.HttpStatusCode, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Object respondText$default(ApplicationCall applicationCall, ContentType contentType, HttpStatusCode httpStatusCode, Function1 function1, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            contentType = null;
        }
        if ((i & 2) != 0) {
            httpStatusCode = null;
        }
        return respondText(applicationCall, contentType, httpStatusCode, function1, continuation);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v7, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v6, resolved type: io.ktor.http.HttpStatusCode} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0046  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x009b A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object respondBytes(io.ktor.server.application.ApplicationCall r5, io.ktor.http.ContentType r6, io.ktor.http.HttpStatusCode r7, kotlin.jvm.functions.Function1<? super kotlin.coroutines.Continuation<? super byte[]>, ? extends java.lang.Object> r8, kotlin.coroutines.Continuation<? super kotlin.Unit> r9) {
        /*
            boolean r0 = r9 instanceof io.ktor.server.response.ApplicationResponseFunctionsKt$respondBytes$1
            if (r0 == 0) goto L_0x0014
            r0 = r9
            io.ktor.server.response.ApplicationResponseFunctionsKt$respondBytes$1 r0 = (io.ktor.server.response.ApplicationResponseFunctionsKt$respondBytes$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L_0x0019
        L_0x0014:
            io.ktor.server.response.ApplicationResponseFunctionsKt$respondBytes$1 r0 = new io.ktor.server.response.ApplicationResponseFunctionsKt$respondBytes$1
            r0.<init>(r9)
        L_0x0019:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x0046
            if (r2 == r4) goto L_0x0035
            if (r2 != r3) goto L_0x002d
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x009c
        L_0x002d:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x0035:
            java.lang.Object r5 = r0.L$2
            io.ktor.server.application.ApplicationCall r5 = (io.ktor.server.application.ApplicationCall) r5
            java.lang.Object r6 = r0.L$1
            r7 = r6
            io.ktor.http.HttpStatusCode r7 = (io.ktor.http.HttpStatusCode) r7
            java.lang.Object r6 = r0.L$0
            io.ktor.http.ContentType r6 = (io.ktor.http.ContentType) r6
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x0058
        L_0x0046:
            kotlin.ResultKt.throwOnFailure(r9)
            r0.L$0 = r6
            r0.L$1 = r7
            r0.L$2 = r5
            r0.label = r4
            java.lang.Object r9 = r8.invoke(r0)
            if (r9 != r1) goto L_0x0058
            return r1
        L_0x0058:
            byte[] r9 = (byte[]) r9
            io.ktor.http.content.ByteArrayContent r8 = new io.ktor.http.content.ByteArrayContent
            r8.<init>(r9, r6, r7)
            boolean r6 = r8 instanceof io.ktor.http.content.OutgoingContent
            if (r6 != 0) goto L_0x0082
            boolean r6 = r8 instanceof byte[]
            if (r6 != 0) goto L_0x0082
            io.ktor.server.response.ApplicationResponse r6 = r5.getResponse()
            java.lang.Class<io.ktor.http.content.ByteArrayContent> r7 = io.ktor.http.content.ByteArrayContent.class
            kotlin.reflect.KType r7 = kotlin.jvm.internal.Reflection.typeOf((java.lang.Class) r7)
            java.lang.reflect.Type r9 = kotlin.reflect.TypesJVMKt.getJavaType((kotlin.reflect.KType) r7)
            java.lang.Class<io.ktor.http.content.ByteArrayContent> r2 = io.ktor.http.content.ByteArrayContent.class
            kotlin.reflect.KClass r2 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r2)
            io.ktor.util.reflect.TypeInfo r7 = io.ktor.util.reflect.TypeInfoJvmKt.typeInfoImpl(r9, r2, r7)
            io.ktor.server.response.ResponseTypeKt.setResponseType(r6, r7)
        L_0x0082:
            io.ktor.server.response.ApplicationResponse r6 = r5.getResponse()
            io.ktor.server.response.ApplicationSendPipeline r6 = r6.getPipeline()
            java.lang.Object r8 = (java.lang.Object) r8
            r7 = 0
            r0.L$0 = r7
            r0.L$1 = r7
            r0.L$2 = r7
            r0.label = r3
            java.lang.Object r5 = r6.execute(r5, r8, r0)
            if (r5 != r1) goto L_0x009c
            return r1
        L_0x009c:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.response.ApplicationResponseFunctionsKt.respondBytes(io.ktor.server.application.ApplicationCall, io.ktor.http.ContentType, io.ktor.http.HttpStatusCode, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Object respondBytes$default(ApplicationCall applicationCall, ContentType contentType, HttpStatusCode httpStatusCode, Function1 function1, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            contentType = null;
        }
        if ((i & 2) != 0) {
            httpStatusCode = null;
        }
        return respondBytes(applicationCall, contentType, httpStatusCode, function1, continuation);
    }

    public static /* synthetic */ Object respondBytes$default(ApplicationCall applicationCall, byte[] bArr, ContentType contentType, HttpStatusCode httpStatusCode, Function1 function1, Continuation continuation, int i, Object obj) {
        ContentType contentType2 = (i & 2) != 0 ? null : contentType;
        HttpStatusCode httpStatusCode2 = (i & 4) != 0 ? null : httpStatusCode;
        if ((i & 8) != 0) {
            function1 = ApplicationResponseFunctionsKt$respondBytes$3.INSTANCE;
        }
        return respondBytes(applicationCall, bArr, contentType2, httpStatusCode2, function1, continuation);
    }

    public static final Object respondBytes(ApplicationCall applicationCall, byte[] bArr, ContentType contentType, HttpStatusCode httpStatusCode, Function1<? super OutgoingContent, Unit> function1, Continuation<? super Unit> continuation) {
        ByteArrayContent byteArrayContent = new ByteArrayContent(bArr, contentType, httpStatusCode);
        function1.invoke(byteArrayContent);
        if (!(byteArrayContent instanceof OutgoingContent) && !(byteArrayContent instanceof byte[])) {
            ApplicationResponse response = applicationCall.getResponse();
            KType typeOf = Reflection.typeOf(ByteArrayContent.class);
            ResponseTypeKt.setResponseType(response, TypeInfoJvmKt.typeInfoImpl(TypesJVMKt.getJavaType(typeOf), Reflection.getOrCreateKotlinClass(ByteArrayContent.class), typeOf));
        }
        Object execute = applicationCall.getResponse().getPipeline().execute(applicationCall, byteArrayContent, continuation);
        if (execute == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            return execute;
        }
        return Unit.INSTANCE;
    }

    public static /* synthetic */ Object respondBytesWriter$default(ApplicationCall applicationCall, ContentType contentType, HttpStatusCode httpStatusCode, Long l, Function2 function2, Continuation continuation, int i, Object obj) {
        return respondBytesWriter(applicationCall, (i & 1) != 0 ? null : contentType, (i & 2) != 0 ? null : httpStatusCode, (i & 4) != 0 ? null : l, function2, continuation);
    }

    public static final Object respondBytesWriter(ApplicationCall applicationCall, ContentType contentType, HttpStatusCode httpStatusCode, Long l, Function2<? super ByteWriteChannel, ? super Continuation<? super Unit>, ? extends Object> function2, Continuation<? super Unit> continuation) {
        if (contentType == null) {
            contentType = ContentType.Application.INSTANCE.getOctetStream();
        }
        ChannelWriterContent channelWriterContent = new ChannelWriterContent(function2, contentType, httpStatusCode, l);
        if (!(channelWriterContent instanceof OutgoingContent) && !(channelWriterContent instanceof byte[])) {
            ApplicationResponse response = applicationCall.getResponse();
            KType typeOf = Reflection.typeOf(ChannelWriterContent.class);
            ResponseTypeKt.setResponseType(response, TypeInfoJvmKt.typeInfoImpl(TypesJVMKt.getJavaType(typeOf), Reflection.getOrCreateKotlinClass(ChannelWriterContent.class), typeOf));
        }
        Object execute = applicationCall.getResponse().getPipeline().execute(applicationCall, channelWriterContent, continuation);
        if (execute == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            return execute;
        }
        return Unit.INSTANCE;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0024, code lost:
        if (r2 == null) goto L_0x0026;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final io.ktor.http.ContentType defaultTextContentType(io.ktor.server.application.ApplicationCall r1, io.ktor.http.ContentType r2) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r0)
            if (r2 != 0) goto L_0x002c
            io.ktor.server.response.ApplicationResponse r1 = r1.getResponse()
            io.ktor.server.response.ResponseHeaders r1 = r1.getHeaders()
            io.ktor.http.HttpHeaders r2 = io.ktor.http.HttpHeaders.INSTANCE
            java.lang.String r2 = r2.getContentType()
            java.lang.String r1 = r1.get(r2)
            if (r1 == 0) goto L_0x0026
            io.ktor.http.ContentType$Companion r2 = io.ktor.http.ContentType.Companion     // Catch:{ BadContentTypeFormatException -> 0x0022 }
            io.ktor.http.ContentType r1 = r2.parse(r1)     // Catch:{ BadContentTypeFormatException -> 0x0022 }
            goto L_0x0023
        L_0x0022:
            r1 = 0
        L_0x0023:
            r2 = r1
            if (r2 != 0) goto L_0x002c
        L_0x0026:
            io.ktor.http.ContentType$Text r1 = io.ktor.http.ContentType.Text.INSTANCE
            io.ktor.http.ContentType r2 = r1.getPlain()
        L_0x002c:
            r1 = r2
            io.ktor.http.HeaderValueWithParameters r1 = (io.ktor.http.HeaderValueWithParameters) r1
            java.nio.charset.Charset r1 = io.ktor.http.ContentTypesKt.charset(r1)
            if (r1 != 0) goto L_0x0047
            io.ktor.http.ContentType$Text r1 = io.ktor.http.ContentType.Text.INSTANCE
            io.ktor.http.ContentType r1 = r1.getAny()
            boolean r1 = r2.match((io.ktor.http.ContentType) r1)
            if (r1 == 0) goto L_0x0047
            java.nio.charset.Charset r1 = kotlin.text.Charsets.UTF_8
            io.ktor.http.ContentType r2 = io.ktor.http.ContentTypesKt.withCharset(r2, r1)
        L_0x0047:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.response.ApplicationResponseFunctionsKt.defaultTextContentType(io.ktor.server.application.ApplicationCall, io.ktor.http.ContentType):io.ktor.http.ContentType");
    }

    public static final Object respondRedirect(ApplicationCall applicationCall, boolean z, Function1<? super URLBuilder, Unit> function1, Continuation<? super Unit> continuation) {
        URLBuilder createFromCall = URLBuilderKt.createFromCall(URLBuilder.Companion, applicationCall);
        function1.invoke(createFromCall);
        Object respondRedirect = respondRedirect(applicationCall, createFromCall.buildString(), z, continuation);
        return respondRedirect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? respondRedirect : Unit.INSTANCE;
    }

    private static final Object respondRedirect$$forInline(ApplicationCall applicationCall, boolean z, Function1<? super URLBuilder, Unit> function1, Continuation<? super Unit> continuation) {
        URLBuilder createFromCall = URLBuilderKt.createFromCall(URLBuilder.Companion, applicationCall);
        function1.invoke(createFromCall);
        URLBuilder uRLBuilder = createFromCall;
        String buildString = createFromCall.buildString();
        InlineMarker.mark(0);
        respondRedirect(applicationCall, buildString, z, continuation);
        InlineMarker.mark(1);
        return Unit.INSTANCE;
    }

    public static /* synthetic */ Object respondRedirect$default(ApplicationCall applicationCall, boolean z, Function1 function1, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        URLBuilder createFromCall = URLBuilderKt.createFromCall(URLBuilder.Companion, applicationCall);
        function1.invoke(createFromCall);
        String buildString = createFromCall.buildString();
        InlineMarker.mark(0);
        respondRedirect(applicationCall, buildString, z, (Continuation<? super Unit>) continuation);
        InlineMarker.mark(1);
        return Unit.INSTANCE;
    }
}
