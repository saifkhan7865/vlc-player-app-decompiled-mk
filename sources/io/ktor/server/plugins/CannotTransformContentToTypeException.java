package io.ktor.server.plugins;

import io.ktor.util.internal.ExceptionUtilsJvmKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KType;
import kotlinx.coroutines.CopyableThrowable;

@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00000\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0000H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lio/ktor/server/plugins/CannotTransformContentToTypeException;", "Lio/ktor/server/plugins/ContentTransformationException;", "Lkotlinx/coroutines/CopyableThrowable;", "type", "Lkotlin/reflect/KType;", "(Lkotlin/reflect/KType;)V", "createCopy", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: Errors.kt */
public final class CannotTransformContentToTypeException extends ContentTransformationException implements CopyableThrowable<CannotTransformContentToTypeException> {
    private final KType type;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public CannotTransformContentToTypeException(KType kType) {
        super("Cannot transform this request's content to " + kType);
        Intrinsics.checkNotNullParameter(kType, "type");
        this.type = kType;
    }

    public CannotTransformContentToTypeException createCopy() {
        CannotTransformContentToTypeException cannotTransformContentToTypeException = new CannotTransformContentToTypeException(this.type);
        ExceptionUtilsJvmKt.initCauseBridge(cannotTransformContentToTypeException, this);
        return cannotTransformContentToTypeException;
    }
}
