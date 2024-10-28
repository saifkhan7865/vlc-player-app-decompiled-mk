package io.ktor.http.cio;

import io.ktor.utils.io.core.Input;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lio/ktor/utils/io/core/Input;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: CIOMultipartDataBase.kt */
final class CIOMultipartDataBase$partToData$3 extends Lambda implements Function0<Input> {
    final /* synthetic */ Input $input;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CIOMultipartDataBase$partToData$3(Input input) {
        super(0);
        this.$input = input;
    }

    public final Input invoke() {
        return this.$input;
    }
}
