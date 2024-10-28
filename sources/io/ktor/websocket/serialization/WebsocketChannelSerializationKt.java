package io.ktor.websocket.serialization;

import io.ktor.serialization.WebsocketContentConverter;
import io.ktor.util.InternalAPI;
import io.ktor.util.reflect.TypeInfo;
import io.ktor.util.reflect.TypeInfoJvmKt;
import io.ktor.websocket.WebSocketSession;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KType;
import kotlin.reflect.TypesJVMKt;

@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\u001a3\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\n\u0010\u0006\u001a\u00060\u0007j\u0002`\bHHø\u0001\u0000¢\u0006\u0002\u0010\t\u001a3\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00032\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u00052\n\u0010\u0006\u001a\u00060\u0007j\u0002`\bH@ø\u0001\u0000¢\u0006\u0002\u0010\f\u001a;\u0010\r\u001a\u00020\u000e\"\u0006\b\u0000\u0010\u0002\u0018\u0001*\u00020\u00032\b\u0010\u000f\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0004\u001a\u00020\u00052\n\u0010\u0006\u001a\u00060\u0007j\u0002`\bHHø\u0001\u0000¢\u0006\u0002\u0010\u0010\u001a;\u0010\r\u001a\u00020\u000e*\u00020\u00032\b\u0010\u000f\u001a\u0004\u0018\u00010\u00012\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u00052\n\u0010\u0006\u001a\u00060\u0007j\u0002`\bH@ø\u0001\u0000¢\u0006\u0002\u0010\u0011\u0002\u0004\n\u0002\b\u0019¨\u0006\u0012"}, d2 = {"receiveDeserializedBase", "", "T", "Lio/ktor/websocket/WebSocketSession;", "converter", "Lio/ktor/serialization/WebsocketContentConverter;", "charset", "Ljava/nio/charset/Charset;", "Lio/ktor/utils/io/charsets/Charset;", "(Lio/ktor/websocket/WebSocketSession;Lio/ktor/serialization/WebsocketContentConverter;Ljava/nio/charset/Charset;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "typeInfo", "Lio/ktor/util/reflect/TypeInfo;", "(Lio/ktor/websocket/WebSocketSession;Lio/ktor/util/reflect/TypeInfo;Lio/ktor/serialization/WebsocketContentConverter;Ljava/nio/charset/Charset;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendSerializedBase", "", "data", "(Lio/ktor/websocket/WebSocketSession;Ljava/lang/Object;Lio/ktor/serialization/WebsocketContentConverter;Ljava/nio/charset/Charset;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "(Lio/ktor/websocket/WebSocketSession;Ljava/lang/Object;Lio/ktor/util/reflect/TypeInfo;Lio/ktor/serialization/WebsocketContentConverter;Ljava/nio/charset/Charset;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ktor-websocket-serialization"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: WebsocketChannelSerialization.kt */
public final class WebsocketChannelSerializationKt {
    @InternalAPI
    public static final /* synthetic */ <T> Object sendSerializedBase(WebSocketSession webSocketSession, Object obj, WebsocketContentConverter websocketContentConverter, Charset charset, Continuation<? super Unit> continuation) {
        Intrinsics.reifiedOperationMarker(6, "T");
        Type javaType = TypesJVMKt.getJavaType((KType) null);
        Intrinsics.reifiedOperationMarker(4, "T");
        TypeInfo typeInfoImpl = TypeInfoJvmKt.typeInfoImpl(javaType, Reflection.getOrCreateKotlinClass(Object.class), (KType) null);
        InlineMarker.mark(0);
        sendSerializedBase(webSocketSession, obj, typeInfoImpl, websocketContentConverter, charset, continuation);
        InlineMarker.mark(1);
        return Unit.INSTANCE;
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x003d  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x005c A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    @io.ktor.util.InternalAPI
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object sendSerializedBase(io.ktor.websocket.WebSocketSession r5, java.lang.Object r6, io.ktor.util.reflect.TypeInfo r7, io.ktor.serialization.WebsocketContentConverter r8, java.nio.charset.Charset r9, kotlin.coroutines.Continuation<? super kotlin.Unit> r10) {
        /*
            boolean r0 = r10 instanceof io.ktor.websocket.serialization.WebsocketChannelSerializationKt$sendSerializedBase$2
            if (r0 == 0) goto L_0x0014
            r0 = r10
            io.ktor.websocket.serialization.WebsocketChannelSerializationKt$sendSerializedBase$2 r0 = (io.ktor.websocket.serialization.WebsocketChannelSerializationKt$sendSerializedBase$2) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r10 = r0.label
            int r10 = r10 - r2
            r0.label = r10
            goto L_0x0019
        L_0x0014:
            io.ktor.websocket.serialization.WebsocketChannelSerializationKt$sendSerializedBase$2 r0 = new io.ktor.websocket.serialization.WebsocketChannelSerializationKt$sendSerializedBase$2
            r0.<init>(r10)
        L_0x0019:
            java.lang.Object r10 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x003d
            if (r2 == r4) goto L_0x0035
            if (r2 != r3) goto L_0x002d
            kotlin.ResultKt.throwOnFailure(r10)
            goto L_0x005d
        L_0x002d:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x0035:
            java.lang.Object r5 = r0.L$0
            io.ktor.websocket.WebSocketSession r5 = (io.ktor.websocket.WebSocketSession) r5
            kotlin.ResultKt.throwOnFailure(r10)
            goto L_0x004b
        L_0x003d:
            kotlin.ResultKt.throwOnFailure(r10)
            r0.L$0 = r5
            r0.label = r4
            java.lang.Object r10 = r8.serializeNullable(r9, r7, r6, r0)
            if (r10 != r1) goto L_0x004b
            return r1
        L_0x004b:
            io.ktor.websocket.Frame r10 = (io.ktor.websocket.Frame) r10
            kotlinx.coroutines.channels.SendChannel r5 = r5.getOutgoing()
            r6 = 0
            r0.L$0 = r6
            r0.label = r3
            java.lang.Object r5 = r5.send(r10, r0)
            if (r5 != r1) goto L_0x005d
            return r1
        L_0x005d:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.websocket.serialization.WebsocketChannelSerializationKt.sendSerializedBase(io.ktor.websocket.WebSocketSession, java.lang.Object, io.ktor.util.reflect.TypeInfo, io.ktor.serialization.WebsocketContentConverter, java.nio.charset.Charset, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @InternalAPI
    public static final /* synthetic */ <T> Object receiveDeserializedBase(WebSocketSession webSocketSession, WebsocketContentConverter websocketContentConverter, Charset charset, Continuation<Object> continuation) {
        Intrinsics.reifiedOperationMarker(6, "T");
        Type javaType = TypesJVMKt.getJavaType((KType) null);
        Intrinsics.reifiedOperationMarker(4, "T");
        TypeInfo typeInfoImpl = TypeInfoJvmKt.typeInfoImpl(javaType, Reflection.getOrCreateKotlinClass(Object.class), (KType) null);
        InlineMarker.mark(0);
        Object receiveDeserializedBase = receiveDeserializedBase(webSocketSession, typeInfoImpl, websocketContentConverter, charset, continuation);
        InlineMarker.mark(1);
        return receiveDeserializedBase;
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0054  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0079  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0094 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0095  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00e4  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0028  */
    @io.ktor.util.InternalAPI
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object receiveDeserializedBase(io.ktor.websocket.WebSocketSession r16, io.ktor.util.reflect.TypeInfo r17, io.ktor.serialization.WebsocketContentConverter r18, java.nio.charset.Charset r19, kotlin.coroutines.Continuation<java.lang.Object> r20) {
        /*
            r0 = r20
            boolean r1 = r0 instanceof io.ktor.websocket.serialization.WebsocketChannelSerializationKt$receiveDeserializedBase$2
            if (r1 == 0) goto L_0x0016
            r1 = r0
            io.ktor.websocket.serialization.WebsocketChannelSerializationKt$receiveDeserializedBase$2 r1 = (io.ktor.websocket.serialization.WebsocketChannelSerializationKt$receiveDeserializedBase$2) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L_0x0016
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            goto L_0x001b
        L_0x0016:
            io.ktor.websocket.serialization.WebsocketChannelSerializationKt$receiveDeserializedBase$2 r1 = new io.ktor.websocket.serialization.WebsocketChannelSerializationKt$receiveDeserializedBase$2
            r1.<init>(r0)
        L_0x001b:
            java.lang.Object r0 = r1.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r1.label
            r4 = 0
            r5 = 2
            r6 = 1
            if (r3 == 0) goto L_0x0054
            if (r3 == r6) goto L_0x0041
            if (r3 != r5) goto L_0x0039
            java.lang.Object r2 = r1.L$1
            io.ktor.websocket.Frame r2 = (io.ktor.websocket.Frame) r2
            java.lang.Object r1 = r1.L$0
            io.ktor.util.reflect.TypeInfo r1 = (io.ktor.util.reflect.TypeInfo) r1
            kotlin.ResultKt.throwOnFailure(r0)
            r10 = r2
            goto L_0x008a
        L_0x0039:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0041:
            java.lang.Object r3 = r1.L$2
            java.nio.charset.Charset r3 = (java.nio.charset.Charset) r3
            java.lang.Object r7 = r1.L$1
            io.ktor.serialization.WebsocketContentConverter r7 = (io.ktor.serialization.WebsocketContentConverter) r7
            java.lang.Object r8 = r1.L$0
            io.ktor.util.reflect.TypeInfo r8 = (io.ktor.util.reflect.TypeInfo) r8
            kotlin.ResultKt.throwOnFailure(r0)
            r15 = r8
            r8 = r3
            r3 = r15
            goto L_0x0070
        L_0x0054:
            kotlin.ResultKt.throwOnFailure(r0)
            kotlinx.coroutines.channels.ReceiveChannel r0 = r16.getIncoming()
            r3 = r17
            r1.L$0 = r3
            r7 = r18
            r1.L$1 = r7
            r8 = r19
            r1.L$2 = r8
            r1.label = r6
            java.lang.Object r0 = r0.receive(r1)
            if (r0 != r2) goto L_0x0070
            return r2
        L_0x0070:
            r12 = r0
            io.ktor.websocket.Frame r12 = (io.ktor.websocket.Frame) r12
            boolean r0 = r7.isApplicable(r12)
            if (r0 == 0) goto L_0x00e4
            r1.L$0 = r3
            r1.L$1 = r12
            r1.L$2 = r4
            r1.label = r5
            java.lang.Object r0 = r7.deserialize(r8, r3, r12, r1)
            if (r0 != r2) goto L_0x0088
            return r2
        L_0x0088:
            r1 = r3
            r10 = r12
        L_0x008a:
            kotlin.reflect.KClass r2 = r1.getType()
            boolean r2 = r2.isInstance(r0)
            if (r2 == 0) goto L_0x0095
            return r0
        L_0x0095:
            if (r0 != 0) goto L_0x00b0
            kotlin.reflect.KType r0 = r1.getKotlinType()
            if (r0 == 0) goto L_0x00a4
            boolean r0 = r0.isMarkedNullable()
            if (r0 != r6) goto L_0x00a4
            return r4
        L_0x00a4:
            io.ktor.serialization.WebsocketDeserializeException r0 = new io.ktor.serialization.WebsocketDeserializeException
            r11 = 2
            r12 = 0
            java.lang.String r8 = "Frame has null content"
            r9 = 0
            r7 = r0
            r7.<init>(r8, r9, r10, r11, r12)
            throw r0
        L_0x00b0:
            io.ktor.serialization.WebsocketDeserializeException r2 = new io.ktor.serialization.WebsocketDeserializeException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "Can't deserialize value: expected value of type "
            r3.<init>(r4)
            kotlin.reflect.KClass r1 = r1.getType()
            java.lang.String r1 = r1.getSimpleName()
            r3.append(r1)
            java.lang.String r1 = ", got "
            r3.append(r1)
            java.lang.Class r0 = r0.getClass()
            kotlin.reflect.KClass r0 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r0)
            java.lang.String r0 = r0.getSimpleName()
            r3.append(r0)
            java.lang.String r8 = r3.toString()
            r11 = 2
            r12 = 0
            r9 = 0
            r7 = r2
            r7.<init>(r8, r9, r10, r11, r12)
            throw r2
        L_0x00e4:
            io.ktor.serialization.WebsocketDeserializeException r0 = new io.ktor.serialization.WebsocketDeserializeException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Converter doesn't support frame type "
            r1.<init>(r2)
            io.ktor.websocket.FrameType r2 = r12.getFrameType()
            java.lang.String r2 = r2.name()
            r1.append(r2)
            java.lang.String r10 = r1.toString()
            r13 = 2
            r14 = 0
            r11 = 0
            r9 = r0
            r9.<init>(r10, r11, r12, r13, r14)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.websocket.serialization.WebsocketChannelSerializationKt.receiveDeserializedBase(io.ktor.websocket.WebSocketSession, io.ktor.util.reflect.TypeInfo, io.ktor.serialization.WebsocketContentConverter, java.nio.charset.Charset, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
