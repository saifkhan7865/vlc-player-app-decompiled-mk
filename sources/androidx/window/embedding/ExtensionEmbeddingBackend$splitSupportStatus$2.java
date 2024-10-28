package androidx.window.embedding;

import android.os.Build;
import androidx.window.embedding.ExtensionEmbeddingBackend;
import androidx.window.embedding.SplitController;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Landroidx/window/embedding/SplitController$SplitSupportStatus;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: ExtensionEmbeddingBackend.kt */
final class ExtensionEmbeddingBackend$splitSupportStatus$2 extends Lambda implements Function0<SplitController.SplitSupportStatus> {
    final /* synthetic */ ExtensionEmbeddingBackend this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ExtensionEmbeddingBackend$splitSupportStatus$2(ExtensionEmbeddingBackend extensionEmbeddingBackend) {
        super(0);
        this.this$0 = extensionEmbeddingBackend;
    }

    public final SplitController.SplitSupportStatus invoke() {
        if (!this.this$0.areExtensionsAvailable()) {
            return SplitController.SplitSupportStatus.SPLIT_UNAVAILABLE;
        }
        if (Build.VERSION.SDK_INT >= 31) {
            return ExtensionEmbeddingBackend.Api31Impl.INSTANCE.isSplitPropertyEnabled(this.this$0.applicationContext);
        }
        return SplitController.SplitSupportStatus.SPLIT_AVAILABLE;
    }
}
