package io.ktor.server.sessions;

import java.util.ArrayList;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlin.reflect.KType;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0016\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002B\u0015\b\u0017\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\u0002\u0010\u0005B\u001d\b\u0001\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u000e\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u000bR\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0004¢\u0006\u0002\n\u0000R \u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\rX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00138F¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019¨\u0006\u001d"}, d2 = {"Lio/ktor/server/sessions/HeaderSessionBuilder;", "S", "", "type", "Lkotlin/reflect/KClass;", "(Lkotlin/reflect/KClass;)V", "typeInfo", "Lkotlin/reflect/KType;", "(Lkotlin/reflect/KClass;Lkotlin/reflect/KType;)V", "_transformers", "", "Lio/ktor/server/sessions/SessionTransportTransformer;", "serializer", "Lio/ktor/server/sessions/SessionSerializer;", "getSerializer", "()Lio/ktor/server/sessions/SessionSerializer;", "setSerializer", "(Lio/ktor/server/sessions/SessionSerializer;)V", "transformers", "", "getTransformers", "()Ljava/util/List;", "getType", "()Lkotlin/reflect/KClass;", "getTypeInfo", "()Lkotlin/reflect/KType;", "transform", "", "transformer", "ktor-server-sessions"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: SessionsBuilder.kt */
public class HeaderSessionBuilder<S> {
    private final List<SessionTransportTransformer> _transformers;
    private SessionSerializer<S> serializer;
    private final KClass<S> type;
    private final KType typeInfo;

    public HeaderSessionBuilder(KClass<S> kClass, KType kType) {
        Intrinsics.checkNotNullParameter(kClass, "type");
        Intrinsics.checkNotNullParameter(kType, "typeInfo");
        this.type = kClass;
        this.typeInfo = kType;
        this.serializer = SessionSerializerReflectionKt.defaultSessionSerializer(kType);
        this._transformers = new ArrayList();
    }

    public final KClass<S> getType() {
        return this.type;
    }

    public final KType getTypeInfo() {
        return this.typeInfo;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use builder functions instead.")
    public HeaderSessionBuilder(KClass<S> kClass) {
        Intrinsics.checkNotNullParameter(kClass, "type");
        throw new IllegalStateException("Use builder functions with reified type parameter instead.");
    }

    public final SessionSerializer<S> getSerializer() {
        return this.serializer;
    }

    public final void setSerializer(SessionSerializer<S> sessionSerializer) {
        Intrinsics.checkNotNullParameter(sessionSerializer, "<set-?>");
        this.serializer = sessionSerializer;
    }

    public final List<SessionTransportTransformer> getTransformers() {
        return this._transformers;
    }

    public final void transform(SessionTransportTransformer sessionTransportTransformer) {
        Intrinsics.checkNotNullParameter(sessionTransportTransformer, "transformer");
        this._transformers.add(sessionTransportTransformer);
    }
}
