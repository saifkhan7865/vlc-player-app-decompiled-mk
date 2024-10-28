package androidx.window.embedding;

import android.content.Context;
import androidx.window.embedding.EmbeddingBackend;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\u0005\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u0006\u0010\t\u001a\u00020\u0006J\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\b0\u000bJ\u000e\u0010\f\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u0014\u0010\r\u001a\u00020\u00062\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\b0\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Landroidx/window/embedding/RuleController;", "", "embeddingBackend", "Landroidx/window/embedding/EmbeddingBackend;", "(Landroidx/window/embedding/EmbeddingBackend;)V", "addRule", "", "rule", "Landroidx/window/embedding/EmbeddingRule;", "clearRules", "getRules", "", "removeRule", "setRules", "rules", "Companion", "window_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: RuleController.kt */
public final class RuleController {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final EmbeddingBackend embeddingBackend;

    public /* synthetic */ RuleController(EmbeddingBackend embeddingBackend2, DefaultConstructorMarker defaultConstructorMarker) {
        this(embeddingBackend2);
    }

    @JvmStatic
    public static final RuleController getInstance(Context context) {
        return Companion.getInstance(context);
    }

    @JvmStatic
    public static final Set<EmbeddingRule> parseRules(Context context, int i) {
        return Companion.parseRules(context, i);
    }

    private RuleController(EmbeddingBackend embeddingBackend2) {
        this.embeddingBackend = embeddingBackend2;
    }

    public final Set<EmbeddingRule> getRules() {
        return CollectionsKt.toSet(this.embeddingBackend.getRules());
    }

    public final void addRule(EmbeddingRule embeddingRule) {
        Intrinsics.checkNotNullParameter(embeddingRule, "rule");
        this.embeddingBackend.addRule(embeddingRule);
    }

    public final void removeRule(EmbeddingRule embeddingRule) {
        Intrinsics.checkNotNullParameter(embeddingRule, "rule");
        this.embeddingBackend.removeRule(embeddingRule);
    }

    public final void setRules(Set<? extends EmbeddingRule> set) {
        Intrinsics.checkNotNullParameter(set, "rules");
        this.embeddingBackend.setRules(set);
    }

    public final void clearRules() {
        this.embeddingBackend.setRules(SetsKt.emptySet());
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J \u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\n\u001a\u00020\u000bH\u0007¨\u0006\f"}, d2 = {"Landroidx/window/embedding/RuleController$Companion;", "", "()V", "getInstance", "Landroidx/window/embedding/RuleController;", "context", "Landroid/content/Context;", "parseRules", "", "Landroidx/window/embedding/EmbeddingRule;", "staticRuleResourceId", "", "window_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: RuleController.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final RuleController getInstance(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            Context applicationContext = context.getApplicationContext();
            EmbeddingBackend.Companion companion = EmbeddingBackend.Companion;
            Intrinsics.checkNotNullExpressionValue(applicationContext, "applicationContext");
            return new RuleController(companion.getInstance(applicationContext), (DefaultConstructorMarker) null);
        }

        @JvmStatic
        public final Set<EmbeddingRule> parseRules(Context context, int i) {
            Intrinsics.checkNotNullParameter(context, "context");
            RuleParser ruleParser = RuleParser.INSTANCE;
            Context applicationContext = context.getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext, "context.applicationContext");
            Set<EmbeddingRule> parseRules$window_release = ruleParser.parseRules$window_release(applicationContext, i);
            return parseRules$window_release == null ? SetsKt.emptySet() : parseRules$window_release;
        }
    }
}
