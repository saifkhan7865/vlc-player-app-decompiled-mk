package io.ktor.http.cio;

import io.ktor.http.cio.MultipartEvent;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: CIOMultipartDataBase.kt */
final class CIOMultipartDataBase$partToData$4 extends Lambda implements Function0<Unit> {
    final /* synthetic */ MultipartEvent.MultipartPart $part;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CIOMultipartDataBase$partToData$4(MultipartEvent.MultipartPart multipartPart) {
        super(0);
        this.$part = multipartPart;
    }

    public final void invoke() {
        this.$part.release();
    }
}