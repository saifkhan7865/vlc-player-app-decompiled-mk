package io.ktor.server.sessions;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlin.reflect.KType;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u0015\b\u0017\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005¢\u0006\u0002\u0010\u0006B\u001d\b\u0001\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u0014\u0010\u0010\u001a\u00020\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\f0\u000bR*\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b@BX\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0013"}, d2 = {"Lio/ktor/server/sessions/HeaderIdSessionBuilder;", "S", "", "Lio/ktor/server/sessions/HeaderSessionBuilder;", "type", "Lkotlin/reflect/KClass;", "(Lkotlin/reflect/KClass;)V", "typeInfo", "Lkotlin/reflect/KType;", "(Lkotlin/reflect/KClass;Lkotlin/reflect/KType;)V", "<set-?>", "Lkotlin/Function0;", "", "sessionIdProvider", "getSessionIdProvider", "()Lkotlin/jvm/functions/Function0;", "identity", "", "f", "ktor-server-sessions"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: SessionsBuilder.kt */
public final class HeaderIdSessionBuilder<S> extends HeaderSessionBuilder<S> {
    private Function0<String> sessionIdProvider;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public HeaderIdSessionBuilder(KClass<S> kClass, KType kType) {
        super(kClass, kType);
        Intrinsics.checkNotNullParameter(kClass, "type");
        Intrinsics.checkNotNullParameter(kType, "typeInfo");
        this.sessionIdProvider = HeaderIdSessionBuilder$sessionIdProvider$1.INSTANCE;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use builder functions instead.")
    public HeaderIdSessionBuilder(KClass<S> kClass) {
        Intrinsics.checkNotNullParameter(kClass, "type");
        throw new IllegalStateException("Use builder functions with reified type parameter instead.");
    }

    public final void identity(Function0<String> function0) {
        Intrinsics.checkNotNullParameter(function0, "f");
        this.sessionIdProvider = function0;
    }

    public final Function0<String> getSessionIdProvider() {
        return this.sessionIdProvider;
    }
}
