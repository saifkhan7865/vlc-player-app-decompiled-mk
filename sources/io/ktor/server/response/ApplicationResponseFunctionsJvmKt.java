package io.ktor.server.response;

import io.ktor.http.ContentType;
import io.ktor.http.HttpStatusCode;
import io.ktor.http.content.OutgoingContent;
import io.ktor.http.content.OutputStreamContent;
import io.ktor.http.content.WriterContent;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.http.content.LocalFileContent;
import io.ktor.server.http.content.LocalFileContentKt;
import io.ktor.util.reflect.TypeInfoJvmKt;
import java.io.File;
import java.io.OutputStream;
import java.io.Writer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KType;
import kotlin.reflect.TypesJVMKt;

@Metadata(d1 = {"\u0000V\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u001a8\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0019\b\u0002\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00010\u0006¢\u0006\u0002\b\bH@ø\u0001\u0000¢\u0006\u0002\u0010\t\u001a@\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\f2\u0019\b\u0002\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00010\u0006¢\u0006\u0002\b\bH@ø\u0001\u0000¢\u0006\u0002\u0010\r\u001ab\u0010\u000e\u001a\u00020\u0001*\u00020\u00022\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00122\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00142'\u0010\u0015\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0017\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u0018\u0012\u0006\u0012\u0004\u0018\u00010\u00190\u0016¢\u0006\u0002\b\bH@ø\u0001\u0000¢\u0006\u0002\u0010\u001a\u001aV\u0010\u000e\u001a\u00020\u0001*\u00020\u00022\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00122'\u0010\u0015\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0017\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u0018\u0012\u0006\u0012\u0004\u0018\u00010\u00190\u0016¢\u0006\u0002\b\bH@ø\u0001\u0000¢\u0006\u0002\u0010\u001b\u001ab\u0010\u001c\u001a\u00020\u0001*\u00020\u00022\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00122\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00142'\u0010\u001d\u001a#\b\u0001\u0012\u0004\u0012\u00020\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u0018\u0012\u0006\u0012\u0004\u0018\u00010\u00190\u0016¢\u0006\u0002\b\bH@ø\u0001\u0000¢\u0006\u0002\u0010\u001a\u001aV\u0010\u001c\u001a\u00020\u0001*\u00020\u00022\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00122'\u0010\u001d\u001a#\b\u0001\u0012\u0004\u0012\u00020\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u0018\u0012\u0006\u0012\u0004\u0018\u00010\u00190\u0016¢\u0006\u0002\b\bH@ø\u0001\u0000¢\u0006\u0002\u0010\u001b\u0002\u0004\n\u0002\b\u0019¨\u0006\u001f"}, d2 = {"respondFile", "", "Lio/ktor/server/application/ApplicationCall;", "file", "Ljava/io/File;", "configure", "Lkotlin/Function1;", "Lio/ktor/http/content/OutgoingContent;", "Lkotlin/ExtensionFunctionType;", "(Lio/ktor/server/application/ApplicationCall;Ljava/io/File;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "baseDir", "fileName", "", "(Lio/ktor/server/application/ApplicationCall;Ljava/io/File;Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "respondOutputStream", "contentType", "Lio/ktor/http/ContentType;", "status", "Lio/ktor/http/HttpStatusCode;", "contentLength", "", "producer", "Lkotlin/Function2;", "Ljava/io/OutputStream;", "Lkotlin/coroutines/Continuation;", "", "(Lio/ktor/server/application/ApplicationCall;Lio/ktor/http/ContentType;Lio/ktor/http/HttpStatusCode;Ljava/lang/Long;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "(Lio/ktor/server/application/ApplicationCall;Lio/ktor/http/ContentType;Lio/ktor/http/HttpStatusCode;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "respondTextWriter", "writer", "Ljava/io/Writer;", "ktor-server-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: ApplicationResponseFunctionsJvm.kt */
public final class ApplicationResponseFunctionsJvmKt {
    public static /* synthetic */ Object respondTextWriter$default(ApplicationCall applicationCall, ContentType contentType, HttpStatusCode httpStatusCode, Function2 function2, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            contentType = null;
        }
        if ((i & 2) != 0) {
            httpStatusCode = null;
        }
        return respondTextWriter(applicationCall, contentType, httpStatusCode, function2, continuation);
    }

    public static final Object respondTextWriter(ApplicationCall applicationCall, ContentType contentType, HttpStatusCode httpStatusCode, Function2<? super Writer, ? super Continuation<? super Unit>, ? extends Object> function2, Continuation<? super Unit> continuation) {
        WriterContent writerContent = new WriterContent(function2, ApplicationResponseFunctionsKt.defaultTextContentType(applicationCall, contentType), httpStatusCode, (Long) null, 8, (DefaultConstructorMarker) null);
        if (!(writerContent instanceof OutgoingContent) && !(writerContent instanceof byte[])) {
            ApplicationResponse response = applicationCall.getResponse();
            KType typeOf = Reflection.typeOf(WriterContent.class);
            ResponseTypeKt.setResponseType(response, TypeInfoJvmKt.typeInfoImpl(TypesJVMKt.getJavaType(typeOf), Reflection.getOrCreateKotlinClass(WriterContent.class), typeOf));
        }
        Object execute = applicationCall.getResponse().getPipeline().execute(applicationCall, writerContent, continuation);
        if (execute == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            return execute;
        }
        return Unit.INSTANCE;
    }

    public static /* synthetic */ Object respondOutputStream$default(ApplicationCall applicationCall, ContentType contentType, HttpStatusCode httpStatusCode, Function2 function2, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            contentType = null;
        }
        if ((i & 2) != 0) {
            httpStatusCode = null;
        }
        return respondOutputStream(applicationCall, contentType, httpStatusCode, function2, continuation);
    }

    public static final Object respondOutputStream(ApplicationCall applicationCall, ContentType contentType, HttpStatusCode httpStatusCode, Function2<? super OutputStream, ? super Continuation<? super Unit>, ? extends Object> function2, Continuation<? super Unit> continuation) {
        if (contentType == null) {
            contentType = ContentType.Application.INSTANCE.getOctetStream();
        }
        OutputStreamContent outputStreamContent = new OutputStreamContent(function2, contentType, httpStatusCode, (Long) null, 8, (DefaultConstructorMarker) null);
        if (!(outputStreamContent instanceof OutgoingContent) && !(outputStreamContent instanceof byte[])) {
            ApplicationResponse response = applicationCall.getResponse();
            KType typeOf = Reflection.typeOf(OutputStreamContent.class);
            ResponseTypeKt.setResponseType(response, TypeInfoJvmKt.typeInfoImpl(TypesJVMKt.getJavaType(typeOf), Reflection.getOrCreateKotlinClass(OutputStreamContent.class), typeOf));
        }
        Object execute = applicationCall.getResponse().getPipeline().execute(applicationCall, outputStreamContent, continuation);
        if (execute == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            return execute;
        }
        return Unit.INSTANCE;
    }

    public static /* synthetic */ Object respondFile$default(ApplicationCall applicationCall, File file, String str, Function1 function1, Continuation continuation, int i, Object obj) {
        if ((i & 4) != 0) {
            function1 = ApplicationResponseFunctionsJvmKt$respondFile$2.INSTANCE;
        }
        return respondFile(applicationCall, file, str, function1, continuation);
    }

    public static final Object respondFile(ApplicationCall applicationCall, File file, String str, Function1<? super OutgoingContent, Unit> function1, Continuation<? super Unit> continuation) {
        LocalFileContent LocalFileContent$default = LocalFileContentKt.LocalFileContent$default(file, str, (ContentType) null, 4, (Object) null);
        function1.invoke(LocalFileContent$default);
        if (!(LocalFileContent$default instanceof OutgoingContent) && !(LocalFileContent$default instanceof byte[])) {
            ApplicationResponse response = applicationCall.getResponse();
            KType typeOf = Reflection.typeOf(LocalFileContent.class);
            ResponseTypeKt.setResponseType(response, TypeInfoJvmKt.typeInfoImpl(TypesJVMKt.getJavaType(typeOf), Reflection.getOrCreateKotlinClass(LocalFileContent.class), typeOf));
        }
        ApplicationSendPipeline pipeline = applicationCall.getResponse().getPipeline();
        Intrinsics.checkNotNull(LocalFileContent$default, "null cannot be cast to non-null type kotlin.Any");
        Object execute = pipeline.execute(applicationCall, LocalFileContent$default, continuation);
        if (execute == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            return execute;
        }
        return Unit.INSTANCE;
    }

    public static /* synthetic */ Object respondFile$default(ApplicationCall applicationCall, File file, Function1 function1, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = ApplicationResponseFunctionsJvmKt$respondFile$4.INSTANCE;
        }
        return respondFile(applicationCall, file, function1, continuation);
    }

    public static final Object respondFile(ApplicationCall applicationCall, File file, Function1<? super OutgoingContent, Unit> function1, Continuation<? super Unit> continuation) {
        LocalFileContent localFileContent = new LocalFileContent(file, (ContentType) null, 2, (DefaultConstructorMarker) null);
        function1.invoke(localFileContent);
        if (!(localFileContent instanceof OutgoingContent) && !(localFileContent instanceof byte[])) {
            ApplicationResponse response = applicationCall.getResponse();
            KType typeOf = Reflection.typeOf(LocalFileContent.class);
            ResponseTypeKt.setResponseType(response, TypeInfoJvmKt.typeInfoImpl(TypesJVMKt.getJavaType(typeOf), Reflection.getOrCreateKotlinClass(LocalFileContent.class), typeOf));
        }
        Object execute = applicationCall.getResponse().getPipeline().execute(applicationCall, localFileContent, continuation);
        if (execute == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            return execute;
        }
        return Unit.INSTANCE;
    }

    public static /* synthetic */ Object respondTextWriter$default(ApplicationCall applicationCall, ContentType contentType, HttpStatusCode httpStatusCode, Long l, Function2 function2, Continuation continuation, int i, Object obj) {
        return respondTextWriter(applicationCall, (i & 1) != 0 ? null : contentType, (i & 2) != 0 ? null : httpStatusCode, (i & 4) != 0 ? null : l, function2, continuation);
    }

    public static final Object respondTextWriter(ApplicationCall applicationCall, ContentType contentType, HttpStatusCode httpStatusCode, Long l, Function2<? super Writer, ? super Continuation<? super Unit>, ? extends Object> function2, Continuation<? super Unit> continuation) {
        WriterContent writerContent = new WriterContent(function2, ApplicationResponseFunctionsKt.defaultTextContentType(applicationCall, contentType), httpStatusCode, l);
        if (!(writerContent instanceof OutgoingContent) && !(writerContent instanceof byte[])) {
            ApplicationResponse response = applicationCall.getResponse();
            KType typeOf = Reflection.typeOf(WriterContent.class);
            ResponseTypeKt.setResponseType(response, TypeInfoJvmKt.typeInfoImpl(TypesJVMKt.getJavaType(typeOf), Reflection.getOrCreateKotlinClass(WriterContent.class), typeOf));
        }
        Object execute = applicationCall.getResponse().getPipeline().execute(applicationCall, writerContent, continuation);
        if (execute == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            return execute;
        }
        return Unit.INSTANCE;
    }

    public static /* synthetic */ Object respondOutputStream$default(ApplicationCall applicationCall, ContentType contentType, HttpStatusCode httpStatusCode, Long l, Function2 function2, Continuation continuation, int i, Object obj) {
        return respondOutputStream(applicationCall, (i & 1) != 0 ? null : contentType, (i & 2) != 0 ? null : httpStatusCode, (i & 4) != 0 ? null : l, function2, continuation);
    }

    public static final Object respondOutputStream(ApplicationCall applicationCall, ContentType contentType, HttpStatusCode httpStatusCode, Long l, Function2<? super OutputStream, ? super Continuation<? super Unit>, ? extends Object> function2, Continuation<? super Unit> continuation) {
        if (contentType == null) {
            contentType = ContentType.Application.INSTANCE.getOctetStream();
        }
        OutputStreamContent outputStreamContent = new OutputStreamContent(function2, contentType, httpStatusCode, l);
        if (!(outputStreamContent instanceof OutgoingContent) && !(outputStreamContent instanceof byte[])) {
            ApplicationResponse response = applicationCall.getResponse();
            KType typeOf = Reflection.typeOf(OutputStreamContent.class);
            ResponseTypeKt.setResponseType(response, TypeInfoJvmKt.typeInfoImpl(TypesJVMKt.getJavaType(typeOf), Reflection.getOrCreateKotlinClass(OutputStreamContent.class), typeOf));
        }
        Object execute = applicationCall.getResponse().getPipeline().execute(applicationCall, outputStreamContent, continuation);
        if (execute == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            return execute;
        }
        return Unit.INSTANCE;
    }
}
