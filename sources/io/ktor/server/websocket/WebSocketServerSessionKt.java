package io.ktor.server.websocket;

import androidx.core.app.NotificationCompat;
import io.ktor.serialization.ContentConverterKt;
import io.ktor.serialization.WebsocketContentConverter;
import io.ktor.serialization.WebsocketConverterNotFoundException;
import io.ktor.server.application.Application;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.application.ApplicationPluginKt;
import io.ktor.util.reflect.TypeInfo;
import io.ktor.util.reflect.TypeInfoJvmKt;
import io.ktor.websocket.DefaultWebSocketSession;
import io.ktor.websocket.WebSocketSession;
import io.ktor.websocket.serialization.WebsocketChannelSerializationKt;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KType;
import kotlin.reflect.TypesJVMKt;

@Metadata(d1 = {"\u0000B\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u001d\u0010\t\u001a\u0002H\n\"\u0006\b\u0000\u0010\n\u0018\u0001*\u00020\u0002HHø\u0001\u0000¢\u0006\u0002\u0010\u000b\u001a#\u0010\t\u001a\u0002H\n\"\u0004\b\u0000\u0010\n*\u00020\u00022\u0006\u0010\f\u001a\u00020\rH@ø\u0001\u0000¢\u0006\u0002\u0010\u000e\u001a%\u0010\u000f\u001a\u00020\u0010\"\u0006\b\u0000\u0010\n\u0018\u0001*\u00020\u00022\u0006\u0010\u0011\u001a\u0002H\nHHø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001a'\u0010\u000f\u001a\u00020\u0010*\u00020\u00022\b\u0010\u0011\u001a\u0004\u0018\u00010\u00132\u0006\u0010\f\u001a\u00020\rH@ø\u0001\u0000¢\u0006\u0002\u0010\u0014\u001a\u0014\u0010\u0015\u001a\u00020\u0016*\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0000\u001a\u0014\u0010\u0015\u001a\u00020\u0002*\u00020\u001a2\u0006\u0010\u0018\u001a\u00020\u0019H\u0000\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0017\u0010\u0005\u001a\u0004\u0018\u00010\u0006*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b\u0002\u0004\n\u0002\b\u0019¨\u0006\u001b"}, d2 = {"application", "Lio/ktor/server/application/Application;", "Lio/ktor/server/websocket/WebSocketServerSession;", "getApplication", "(Lio/ktor/server/websocket/WebSocketServerSession;)Lio/ktor/server/application/Application;", "converter", "Lio/ktor/serialization/WebsocketContentConverter;", "getConverter", "(Lio/ktor/server/websocket/WebSocketServerSession;)Lio/ktor/serialization/WebsocketContentConverter;", "receiveDeserialized", "T", "(Lio/ktor/server/websocket/WebSocketServerSession;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "typeInfo", "Lio/ktor/util/reflect/TypeInfo;", "(Lio/ktor/server/websocket/WebSocketServerSession;Lio/ktor/util/reflect/TypeInfo;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendSerialized", "", "data", "(Lio/ktor/server/websocket/WebSocketServerSession;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "", "(Lio/ktor/server/websocket/WebSocketServerSession;Ljava/lang/Object;Lio/ktor/util/reflect/TypeInfo;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "toServerSession", "Lio/ktor/server/websocket/DefaultWebSocketServerSession;", "Lio/ktor/websocket/DefaultWebSocketSession;", "call", "Lio/ktor/server/application/ApplicationCall;", "Lio/ktor/websocket/WebSocketSession;", "ktor-server-websockets"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: WebSocketServerSession.kt */
public final class WebSocketServerSessionKt {
    public static final Application getApplication(WebSocketServerSession webSocketServerSession) {
        Intrinsics.checkNotNullParameter(webSocketServerSession, "<this>");
        return webSocketServerSession.getCall().getApplication();
    }

    public static final WebsocketContentConverter getConverter(WebSocketServerSession webSocketServerSession) {
        Intrinsics.checkNotNullParameter(webSocketServerSession, "<this>");
        return ((WebSockets) ApplicationPluginKt.plugin(getApplication(webSocketServerSession), WebSockets.Plugin)).getContentConverter();
    }

    public static final Object sendSerialized(WebSocketServerSession webSocketServerSession, Object obj, TypeInfo typeInfo, Continuation<? super Unit> continuation) {
        WebsocketContentConverter converter = getConverter(webSocketServerSession);
        if (converter != null) {
            Object sendSerializedBase = WebsocketChannelSerializationKt.sendSerializedBase(webSocketServerSession, obj, typeInfo, converter, ContentConverterKt.suitableCharset$default(webSocketServerSession.getCall().getRequest().getHeaders(), (Charset) null, 1, (Object) null), continuation);
            return sendSerializedBase == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? sendSerializedBase : Unit.INSTANCE;
        }
        throw new WebsocketConverterNotFoundException("No converter was found for websocket", (Throwable) null, 2, (DefaultConstructorMarker) null);
    }

    public static final /* synthetic */ <T> Object sendSerialized(WebSocketServerSession webSocketServerSession, T t, Continuation<? super Unit> continuation) {
        Intrinsics.reifiedOperationMarker(6, "T");
        Type javaType = TypesJVMKt.getJavaType((KType) null);
        Intrinsics.reifiedOperationMarker(4, "T");
        TypeInfo typeInfoImpl = TypeInfoJvmKt.typeInfoImpl(javaType, Reflection.getOrCreateKotlinClass(Object.class), (KType) null);
        InlineMarker.mark(0);
        sendSerialized(webSocketServerSession, t, typeInfoImpl, continuation);
        InlineMarker.mark(1);
        return Unit.INSTANCE;
    }

    public static final <T> Object receiveDeserialized(WebSocketServerSession webSocketServerSession, TypeInfo typeInfo, Continuation<? super T> continuation) {
        WebsocketContentConverter converter = getConverter(webSocketServerSession);
        if (converter != null) {
            Object receiveDeserializedBase = WebsocketChannelSerializationKt.receiveDeserializedBase(webSocketServerSession, typeInfo, converter, ContentConverterKt.suitableCharset$default(webSocketServerSession.getCall().getRequest().getHeaders(), (Charset) null, 1, (Object) null), continuation);
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            return receiveDeserializedBase;
        }
        throw new WebsocketConverterNotFoundException("No converter was found for websocket", (Throwable) null, 2, (DefaultConstructorMarker) null);
    }

    public static final /* synthetic */ <T> Object receiveDeserialized(WebSocketServerSession webSocketServerSession, Continuation<? super T> continuation) {
        Intrinsics.reifiedOperationMarker(6, "T");
        Type javaType = TypesJVMKt.getJavaType((KType) null);
        Intrinsics.reifiedOperationMarker(4, "T");
        TypeInfo typeInfoImpl = TypeInfoJvmKt.typeInfoImpl(javaType, Reflection.getOrCreateKotlinClass(Object.class), (KType) null);
        InlineMarker.mark(0);
        Object receiveDeserialized = receiveDeserialized(webSocketServerSession, typeInfoImpl, continuation);
        InlineMarker.mark(1);
        return receiveDeserialized;
    }

    public static final WebSocketServerSession toServerSession(WebSocketSession webSocketSession, ApplicationCall applicationCall) {
        Intrinsics.checkNotNullParameter(webSocketSession, "<this>");
        Intrinsics.checkNotNullParameter(applicationCall, NotificationCompat.CATEGORY_CALL);
        return new DelegatedWebSocketServerSession(applicationCall, webSocketSession);
    }

    public static final DefaultWebSocketServerSession toServerSession(DefaultWebSocketSession defaultWebSocketSession, ApplicationCall applicationCall) {
        Intrinsics.checkNotNullParameter(defaultWebSocketSession, "<this>");
        Intrinsics.checkNotNullParameter(applicationCall, NotificationCompat.CATEGORY_CALL);
        return new DelegatedDefaultWebSocketServerSession(applicationCall, defaultWebSocketSession);
    }
}
