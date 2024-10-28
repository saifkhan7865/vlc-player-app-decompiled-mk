package io.ktor.client.plugins.websocket;

import io.ktor.client.plugins.HttpClientPluginKt;
import io.ktor.serialization.ContentConverterKt;
import io.ktor.serialization.WebsocketContentConverter;
import io.ktor.serialization.WebsocketConverterNotFoundException;
import io.ktor.util.reflect.TypeInfo;
import io.ktor.util.reflect.TypeInfoJvmKt;
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

@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\u001a\u001d\u0010\u0005\u001a\u0002H\u0006\"\u0006\b\u0000\u0010\u0006\u0018\u0001*\u00020\u0002HHø\u0001\u0000¢\u0006\u0002\u0010\u0007\u001a#\u0010\u0005\u001a\u0002H\u0006\"\u0004\b\u0000\u0010\u0006*\u00020\u00022\u0006\u0010\b\u001a\u00020\tH@ø\u0001\u0000¢\u0006\u0002\u0010\n\u001a%\u0010\u000b\u001a\u00020\f\"\u0006\b\u0000\u0010\u0006\u0018\u0001*\u00020\u00022\u0006\u0010\r\u001a\u0002H\u0006HHø\u0001\u0000¢\u0006\u0002\u0010\u000e\u001a'\u0010\u000b\u001a\u00020\f*\u00020\u00022\b\u0010\r\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\b\u001a\u00020\tH@ø\u0001\u0000¢\u0006\u0002\u0010\u0010\"\u0017\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\u0002\u0004\n\u0002\b\u0019¨\u0006\u0011"}, d2 = {"converter", "Lio/ktor/serialization/WebsocketContentConverter;", "Lio/ktor/client/plugins/websocket/DefaultClientWebSocketSession;", "getConverter", "(Lio/ktor/client/plugins/websocket/DefaultClientWebSocketSession;)Lio/ktor/serialization/WebsocketContentConverter;", "receiveDeserialized", "T", "(Lio/ktor/client/plugins/websocket/DefaultClientWebSocketSession;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "typeInfo", "Lio/ktor/util/reflect/TypeInfo;", "(Lio/ktor/client/plugins/websocket/DefaultClientWebSocketSession;Lio/ktor/util/reflect/TypeInfo;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendSerialized", "", "data", "(Lio/ktor/client/plugins/websocket/DefaultClientWebSocketSession;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "", "(Lio/ktor/client/plugins/websocket/DefaultClientWebSocketSession;Ljava/lang/Object;Lio/ktor/util/reflect/TypeInfo;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ktor-client-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: ClientSessions.kt */
public final class ClientSessionsKt {
    public static final WebsocketContentConverter getConverter(DefaultClientWebSocketSession defaultClientWebSocketSession) {
        Intrinsics.checkNotNullParameter(defaultClientWebSocketSession, "<this>");
        WebSockets webSockets = (WebSockets) HttpClientPluginKt.pluginOrNull(defaultClientWebSocketSession.getCall().getClient(), WebSockets.Plugin);
        if (webSockets != null) {
            return webSockets.getContentConverter();
        }
        return null;
    }

    public static final Object sendSerialized(DefaultClientWebSocketSession defaultClientWebSocketSession, Object obj, TypeInfo typeInfo, Continuation<? super Unit> continuation) {
        WebsocketContentConverter converter = getConverter(defaultClientWebSocketSession);
        if (converter != null) {
            Object sendSerializedBase = WebsocketChannelSerializationKt.sendSerializedBase(defaultClientWebSocketSession, obj, typeInfo, converter, ContentConverterKt.suitableCharset$default(defaultClientWebSocketSession.getCall().getRequest().getHeaders(), (Charset) null, 1, (Object) null), continuation);
            return sendSerializedBase == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? sendSerializedBase : Unit.INSTANCE;
        }
        throw new WebsocketConverterNotFoundException("No converter was found for websocket", (Throwable) null, 2, (DefaultConstructorMarker) null);
    }

    public static final /* synthetic */ <T> Object sendSerialized(DefaultClientWebSocketSession defaultClientWebSocketSession, T t, Continuation<? super Unit> continuation) {
        Intrinsics.reifiedOperationMarker(6, "T");
        Type javaType = TypesJVMKt.getJavaType((KType) null);
        Intrinsics.reifiedOperationMarker(4, "T");
        TypeInfo typeInfoImpl = TypeInfoJvmKt.typeInfoImpl(javaType, Reflection.getOrCreateKotlinClass(Object.class), (KType) null);
        InlineMarker.mark(0);
        sendSerialized(defaultClientWebSocketSession, t, typeInfoImpl, continuation);
        InlineMarker.mark(1);
        return Unit.INSTANCE;
    }

    public static final <T> Object receiveDeserialized(DefaultClientWebSocketSession defaultClientWebSocketSession, TypeInfo typeInfo, Continuation<? super T> continuation) {
        WebsocketContentConverter converter = getConverter(defaultClientWebSocketSession);
        if (converter != null) {
            Object receiveDeserializedBase = WebsocketChannelSerializationKt.receiveDeserializedBase(defaultClientWebSocketSession, typeInfo, converter, ContentConverterKt.suitableCharset$default(defaultClientWebSocketSession.getCall().getRequest().getHeaders(), (Charset) null, 1, (Object) null), continuation);
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            return receiveDeserializedBase;
        }
        throw new WebsocketConverterNotFoundException("No converter was found for websocket", (Throwable) null, 2, (DefaultConstructorMarker) null);
    }

    public static final /* synthetic */ <T> Object receiveDeserialized(DefaultClientWebSocketSession defaultClientWebSocketSession, Continuation<? super T> continuation) {
        Intrinsics.reifiedOperationMarker(6, "T");
        Type javaType = TypesJVMKt.getJavaType((KType) null);
        Intrinsics.reifiedOperationMarker(4, "T");
        TypeInfo typeInfoImpl = TypeInfoJvmKt.typeInfoImpl(javaType, Reflection.getOrCreateKotlinClass(Object.class), (KType) null);
        InlineMarker.mark(0);
        Object receiveDeserialized = receiveDeserialized(defaultClientWebSocketSession, typeInfoImpl, continuation);
        InlineMarker.mark(1);
        return receiveDeserialized;
    }
}
