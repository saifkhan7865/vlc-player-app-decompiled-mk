package io.ktor.server.sessions;

import kotlin.Metadata;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.reflect.KClass;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: SessionSerializerReflection.kt */
/* synthetic */ class SessionSerializerReflection$findConstructor$getter$1 extends PropertyReference1Impl {
    public static final SessionSerializerReflection$findConstructor$getter$1 INSTANCE = new SessionSerializerReflection$findConstructor$getter$1();

    SessionSerializerReflection$findConstructor$getter$1() {
        super(KClass.class, "objectInstance", "getObjectInstance()Ljava/lang/Object;", 0);
    }

    public Object get(Object obj) {
        return ((KClass) obj).getObjectInstance();
    }
}
