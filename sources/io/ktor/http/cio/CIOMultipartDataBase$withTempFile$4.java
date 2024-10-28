package io.ktor.http.cio;

import io.ktor.http.cio.MultipartEvent;
import io.ktor.utils.io.core.Input;
import java.io.File;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: CIOMultipartDataBase.kt */
final class CIOMultipartDataBase$withTempFile$4 extends Lambda implements Function0<Unit> {
    final /* synthetic */ Ref.BooleanRef $closed;
    final /* synthetic */ Lazy<Input> $lazyInput;
    final /* synthetic */ MultipartEvent.MultipartPart $part;
    final /* synthetic */ File $tmp;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CIOMultipartDataBase$withTempFile$4(Ref.BooleanRef booleanRef, Lazy<? extends Input> lazy, MultipartEvent.MultipartPart multipartPart, File file) {
        super(0);
        this.$closed = booleanRef;
        this.$lazyInput = lazy;
        this.$part = multipartPart;
        this.$tmp = file;
    }

    public final void invoke() {
        this.$closed.element = true;
        if (this.$lazyInput.isInitialized()) {
            this.$lazyInput.getValue().close();
        }
        this.$part.release();
        this.$tmp.delete();
    }
}