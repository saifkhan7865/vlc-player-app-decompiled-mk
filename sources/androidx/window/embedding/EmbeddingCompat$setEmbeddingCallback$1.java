package androidx.window.embedding;

import androidx.window.embedding.EmbeddingInterfaceCompat;
import androidx.window.extensions.embedding.SplitInfo;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0000\u0010\u0000\u001a\u00020\u00012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "values", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: EmbeddingCompat.kt */
final class EmbeddingCompat$setEmbeddingCallback$1 extends Lambda implements Function1<List<?>, Unit> {
    final /* synthetic */ EmbeddingInterfaceCompat.EmbeddingCallbackInterface $embeddingCallback;
    final /* synthetic */ EmbeddingCompat this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    EmbeddingCompat$setEmbeddingCallback$1(EmbeddingInterfaceCompat.EmbeddingCallbackInterface embeddingCallbackInterface, EmbeddingCompat embeddingCompat) {
        super(1);
        this.$embeddingCallback = embeddingCallbackInterface;
        this.this$0 = embeddingCompat;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((List<?>) (List) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(List<?> list) {
        Intrinsics.checkNotNullParameter(list, "values");
        Collection arrayList = new ArrayList();
        for (Object next : list) {
            if (next instanceof SplitInfo) {
                arrayList.add(next);
            }
        }
        this.$embeddingCallback.onSplitInfoChanged(this.this$0.adapter.translate((List<? extends SplitInfo>) (List) arrayList));
    }
}
