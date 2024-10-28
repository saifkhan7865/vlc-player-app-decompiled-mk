package androidx.window.embedding;

import android.app.Activity;
import android.content.Context;
import androidx.core.util.Consumer;
import androidx.window.embedding.SplitController;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bg\u0018\u0000 #2\u00020\u0001:\u0001#J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&J,\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0012\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00110\u0010H&J\b\u0010\u0013\u001a\u00020\u0007H&J\u000e\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\t0\u0015H&J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u000b\u001a\u00020\fH&J\b\u0010\u0018\u001a\u00020\u0017H&J\u0010\u0010\u0019\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&J\u001c\u0010\u001a\u001a\u00020\u00072\u0012\u0010\u001b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00110\u0010H&J\u0016\u0010\u001c\u001a\u00020\u00072\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\t0\u0015H&J\u001c\u0010\u001e\u001a\u00020\u00072\u0012\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\"0 H'R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005ø\u0001\u0000\u0002\u0006\n\u0004\b!0\u0001¨\u0006$À\u0006\u0001"}, d2 = {"Landroidx/window/embedding/EmbeddingBackend;", "", "splitSupportStatus", "Landroidx/window/embedding/SplitController$SplitSupportStatus;", "getSplitSupportStatus", "()Landroidx/window/embedding/SplitController$SplitSupportStatus;", "addRule", "", "rule", "Landroidx/window/embedding/EmbeddingRule;", "addSplitListenerForActivity", "activity", "Landroid/app/Activity;", "executor", "Ljava/util/concurrent/Executor;", "callback", "Landroidx/core/util/Consumer;", "", "Landroidx/window/embedding/SplitInfo;", "clearSplitAttributesCalculator", "getRules", "", "isActivityEmbedded", "", "isSplitAttributesCalculatorSupported", "removeRule", "removeSplitListenerForActivity", "consumer", "setRules", "rules", "setSplitAttributesCalculator", "calculator", "Lkotlin/Function1;", "Landroidx/window/embedding/SplitAttributesCalculatorParams;", "Landroidx/window/embedding/SplitAttributes;", "Companion", "window_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: EmbeddingBackend.kt */
public interface EmbeddingBackend {
    public static final Companion Companion = Companion.$$INSTANCE;

    /* renamed from: androidx.window.embedding.EmbeddingBackend$-CC  reason: invalid class name */
    /* compiled from: EmbeddingBackend.kt */
    public final /* synthetic */ class CC {
        static {
            Companion companion = EmbeddingBackend.Companion;
        }

        @JvmStatic
        public static EmbeddingBackend getInstance(Context context) {
            return EmbeddingBackend.Companion.getInstance(context);
        }

        @JvmStatic
        public static void overrideDecorator(EmbeddingBackendDecorator embeddingBackendDecorator) {
            EmbeddingBackend.Companion.overrideDecorator(embeddingBackendDecorator);
        }

        @JvmStatic
        public static void reset() {
            EmbeddingBackend.Companion.reset();
        }
    }

    void addRule(EmbeddingRule embeddingRule);

    void addSplitListenerForActivity(Activity activity, Executor executor, Consumer<List<SplitInfo>> consumer);

    void clearSplitAttributesCalculator();

    Set<EmbeddingRule> getRules();

    SplitController.SplitSupportStatus getSplitSupportStatus();

    boolean isActivityEmbedded(Activity activity);

    boolean isSplitAttributesCalculatorSupported();

    void removeRule(EmbeddingRule embeddingRule);

    void removeSplitListenerForActivity(Consumer<List<SplitInfo>> consumer);

    void setRules(Set<? extends EmbeddingRule> set);

    void setSplitAttributesCalculator(Function1<? super SplitAttributesCalculatorParams, SplitAttributes> function1);

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0007J\b\u0010\r\u001a\u00020\nH\u0007R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004X\u000e¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Landroidx/window/embedding/EmbeddingBackend$Companion;", "", "()V", "decorator", "Lkotlin/Function1;", "Landroidx/window/embedding/EmbeddingBackend;", "getInstance", "context", "Landroid/content/Context;", "overrideDecorator", "", "overridingDecorator", "Landroidx/window/embedding/EmbeddingBackendDecorator;", "reset", "window_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: EmbeddingBackend.kt */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        private static Function1<? super EmbeddingBackend, ? extends EmbeddingBackend> decorator = EmbeddingBackend$Companion$decorator$1.INSTANCE;

        private Companion() {
        }

        @JvmStatic
        public final EmbeddingBackend getInstance(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return (EmbeddingBackend) decorator.invoke(ExtensionEmbeddingBackend.Companion.getInstance(context));
        }

        @JvmStatic
        public final void overrideDecorator(EmbeddingBackendDecorator embeddingBackendDecorator) {
            Intrinsics.checkNotNullParameter(embeddingBackendDecorator, "overridingDecorator");
            decorator = new EmbeddingBackend$Companion$overrideDecorator$1(embeddingBackendDecorator);
        }

        @JvmStatic
        public final void reset() {
            decorator = EmbeddingBackend$Companion$reset$1.INSTANCE;
        }
    }
}
