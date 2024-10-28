package org.videolan.vlc.webserver.websockets;

import j$.util.function.Predicate$CC;
import java.util.function.Predicate;
import kotlin.jvm.functions.Function1;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class RemoteAccessWebSockets$$ExternalSyntheticLambda0 implements Predicate {
    public final /* synthetic */ Function1 f$0;

    public /* synthetic */ RemoteAccessWebSockets$$ExternalSyntheticLambda0(Function1 function1) {
        this.f$0 = function1;
    }

    public /* synthetic */ Predicate and(Predicate predicate) {
        return Predicate$CC.$default$and(this, predicate);
    }

    public /* synthetic */ Predicate negate() {
        return Predicate$CC.$default$negate(this);
    }

    public /* synthetic */ Predicate or(Predicate predicate) {
        return Predicate$CC.$default$or(this, predicate);
    }

    public final boolean test(Object obj) {
        return RemoteAccessWebSockets.addToQueue$lambda$20(this.f$0, obj);
    }
}
