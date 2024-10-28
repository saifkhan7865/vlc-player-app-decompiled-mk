package io.ktor.gson;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlin.reflect.jvm.KClassesJvm;
import kotlinx.coroutines.CopyableThrowable;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00060\u0001j\u0002`\u00022\b\u0012\u0004\u0012\u00020\u00000\u0003B\u0011\u0012\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001a\u00020\u0000H\u0016R\u0012\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lio/ktor/gson/ExcludedTypeGsonException;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "Lkotlinx/coroutines/CopyableThrowable;", "type", "Lkotlin/reflect/KClass;", "(Lkotlin/reflect/KClass;)V", "createCopy", "ktor-gson"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: GsonSupport.kt */
public final class ExcludedTypeGsonException extends Exception implements CopyableThrowable<ExcludedTypeGsonException> {
    private final KClass<?> type;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ExcludedTypeGsonException(KClass<?> kClass) {
        super("Type " + KClassesJvm.getJvmName(kClass) + " is excluded so couldn't be used in receive");
        Intrinsics.checkNotNullParameter(kClass, "type");
        this.type = kClass;
    }

    public ExcludedTypeGsonException createCopy() {
        ExcludedTypeGsonException excludedTypeGsonException = new ExcludedTypeGsonException(this.type);
        excludedTypeGsonException.initCause(this);
        return excludedTypeGsonException;
    }
}
