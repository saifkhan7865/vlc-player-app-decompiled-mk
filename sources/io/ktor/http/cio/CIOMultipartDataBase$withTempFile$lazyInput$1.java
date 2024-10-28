package io.ktor.http.cio;

import io.ktor.utils.io.core.Input;
import io.ktor.utils.io.pool.ObjectPool;
import io.ktor.utils.io.streams.InputKt;
import java.io.File;
import java.io.FileInputStream;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lio/ktor/utils/io/core/Input;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: CIOMultipartDataBase.kt */
final class CIOMultipartDataBase$withTempFile$lazyInput$1 extends Lambda implements Function0<Input> {
    final /* synthetic */ Ref.BooleanRef $closed;
    final /* synthetic */ File $tmp;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CIOMultipartDataBase$withTempFile$lazyInput$1(Ref.BooleanRef booleanRef, File file) {
        super(0);
        this.$closed = booleanRef;
        this.$tmp = file;
    }

    public final Input invoke() {
        if (!this.$closed.element) {
            return InputKt.asInput$default(new FileInputStream(this.$tmp), (ObjectPool) null, 1, (Object) null);
        }
        throw new IllegalStateException("Already disposed");
    }
}
