package io.ktor.client.engine;

import androidx.leanback.preference.LeanbackPreferenceDialogFragment;
import io.ktor.http.HttpHeaders;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.fusesource.jansi.AnsiRenderer;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "key", "", "values", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: Utils.kt */
final class UtilsKt$mergeHeaders$2 extends Lambda implements Function2<String, List<? extends String>, Unit> {
    final /* synthetic */ Function2<String, String, Unit> $block;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    UtilsKt$mergeHeaders$2(Function2<? super String, ? super String, Unit> function2) {
        super(2);
        this.$block = function2;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((String) obj, (List<String>) (List) obj2);
        return Unit.INSTANCE;
    }

    public final void invoke(String str, List<String> list) {
        Intrinsics.checkNotNullParameter(str, LeanbackPreferenceDialogFragment.ARG_KEY);
        Intrinsics.checkNotNullParameter(list, "values");
        if (Intrinsics.areEqual((Object) HttpHeaders.INSTANCE.getContentLength(), (Object) str) || Intrinsics.areEqual((Object) HttpHeaders.INSTANCE.getContentType(), (Object) str)) {
            return;
        }
        if (UtilsKt.DATE_HEADERS.contains(str)) {
            Function2<String, String, Unit> function2 = this.$block;
            for (String invoke : list) {
                function2.invoke(str, invoke);
            }
            return;
        }
        this.$block.invoke(str, CollectionsKt.joinToString$default(list, Intrinsics.areEqual((Object) HttpHeaders.INSTANCE.getCookie(), (Object) str) ? "; " : AnsiRenderer.CODE_LIST_SEPARATOR, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null));
    }
}
