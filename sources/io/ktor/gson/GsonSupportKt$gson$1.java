package io.ktor.gson;

import com.google.gson.GsonBuilder;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "Lcom/google/gson/GsonBuilder;", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
/* compiled from: GsonSupport.kt */
final class GsonSupportKt$gson$1 extends Lambda implements Function1<GsonBuilder, Unit> {
    public static final GsonSupportKt$gson$1 INSTANCE = new GsonSupportKt$gson$1();

    GsonSupportKt$gson$1() {
        super(1);
    }

    public final void invoke(GsonBuilder gsonBuilder) {
        Intrinsics.checkNotNullParameter(gsonBuilder, "$this$null");
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((GsonBuilder) obj);
        return Unit.INSTANCE;
    }
}
