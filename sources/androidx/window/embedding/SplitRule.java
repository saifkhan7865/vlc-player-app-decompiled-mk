package androidx.window.embedding;

import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.view.WindowMetrics;
import androidx.core.util.Preconditions;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b\u0006\b\u0016\u0018\u0000 ,2\u00020\u0001:\u0003+,-BM\b\u0000\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0003\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0003\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0003\u0010\u0007\u001a\u00020\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rJ\u001d\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0000¢\u0006\u0002\b\u001dJ\u001d\u0010\u001e\u001a\u00020\u00182\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"H\u0001¢\u0006\u0002\b#J\u001a\u0010$\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u001a2\b\b\u0001\u0010%\u001a\u00020\u0005H\u0002J\u0013\u0010&\u001a\u00020\u00182\b\u0010'\u001a\u0004\u0018\u00010(H\u0002J\b\u0010)\u001a\u00020\u0005H\u0016J\b\u0010*\u001a\u00020\u0003H\u0016R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\n\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0014¨\u0006."}, d2 = {"Landroidx/window/embedding/SplitRule;", "Landroidx/window/embedding/EmbeddingRule;", "tag", "", "minWidthDp", "", "minHeightDp", "minSmallestWidthDp", "maxAspectRatioInPortrait", "Landroidx/window/embedding/EmbeddingAspectRatio;", "maxAspectRatioInLandscape", "defaultSplitAttributes", "Landroidx/window/embedding/SplitAttributes;", "(Ljava/lang/String;IIILandroidx/window/embedding/EmbeddingAspectRatio;Landroidx/window/embedding/EmbeddingAspectRatio;Landroidx/window/embedding/SplitAttributes;)V", "getDefaultSplitAttributes", "()Landroidx/window/embedding/SplitAttributes;", "getMaxAspectRatioInLandscape", "()Landroidx/window/embedding/EmbeddingAspectRatio;", "getMaxAspectRatioInPortrait", "getMinHeightDp", "()I", "getMinSmallestWidthDp", "getMinWidthDp", "checkParentBounds", "", "density", "", "bounds", "Landroid/graphics/Rect;", "checkParentBounds$window_release", "checkParentMetrics", "context", "Landroid/content/Context;", "parentMetrics", "Landroid/view/WindowMetrics;", "checkParentMetrics$window_release", "convertDpToPx", "dimensionDp", "equals", "other", "", "hashCode", "toString", "Api30Impl", "Companion", "FinishBehavior", "window_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: SplitRule.kt */
public class SplitRule extends EmbeddingRule {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final EmbeddingAspectRatio SPLIT_MAX_ASPECT_RATIO_LANDSCAPE_DEFAULT = EmbeddingAspectRatio.ALWAYS_ALLOW;
    public static final EmbeddingAspectRatio SPLIT_MAX_ASPECT_RATIO_PORTRAIT_DEFAULT = EmbeddingAspectRatio.Companion.ratio(1.4f);
    public static final int SPLIT_MIN_DIMENSION_ALWAYS_ALLOW = 0;
    public static final int SPLIT_MIN_DIMENSION_DP_DEFAULT = 600;
    private final SplitAttributes defaultSplitAttributes;
    private final EmbeddingAspectRatio maxAspectRatioInLandscape;
    private final EmbeddingAspectRatio maxAspectRatioInPortrait;
    private final int minHeightDp;
    private final int minSmallestWidthDp;
    private final int minWidthDp;

    private final int convertDpToPx(float f, int i) {
        return (int) ((((float) i) * f) + 0.5f);
    }

    public final int getMinWidthDp() {
        return this.minWidthDp;
    }

    public final int getMinHeightDp() {
        return this.minHeightDp;
    }

    public final int getMinSmallestWidthDp() {
        return this.minSmallestWidthDp;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ SplitRule(java.lang.String r10, int r11, int r12, int r13, androidx.window.embedding.EmbeddingAspectRatio r14, androidx.window.embedding.EmbeddingAspectRatio r15, androidx.window.embedding.SplitAttributes r16, int r17, kotlin.jvm.internal.DefaultConstructorMarker r18) {
        /*
            r9 = this;
            r0 = r17 & 1
            if (r0 == 0) goto L_0x0007
            r0 = 0
            r2 = r0
            goto L_0x0008
        L_0x0007:
            r2 = r10
        L_0x0008:
            r0 = r17 & 2
            r1 = 600(0x258, float:8.41E-43)
            if (r0 == 0) goto L_0x0011
            r3 = 600(0x258, float:8.41E-43)
            goto L_0x0012
        L_0x0011:
            r3 = r11
        L_0x0012:
            r0 = r17 & 4
            if (r0 == 0) goto L_0x0019
            r4 = 600(0x258, float:8.41E-43)
            goto L_0x001a
        L_0x0019:
            r4 = r12
        L_0x001a:
            r0 = r17 & 8
            if (r0 == 0) goto L_0x0021
            r5 = 600(0x258, float:8.41E-43)
            goto L_0x0022
        L_0x0021:
            r5 = r13
        L_0x0022:
            r0 = r17 & 16
            if (r0 == 0) goto L_0x002a
            androidx.window.embedding.EmbeddingAspectRatio r0 = SPLIT_MAX_ASPECT_RATIO_PORTRAIT_DEFAULT
            r6 = r0
            goto L_0x002b
        L_0x002a:
            r6 = r14
        L_0x002b:
            r0 = r17 & 32
            if (r0 == 0) goto L_0x0033
            androidx.window.embedding.EmbeddingAspectRatio r0 = SPLIT_MAX_ASPECT_RATIO_LANDSCAPE_DEFAULT
            r7 = r0
            goto L_0x0034
        L_0x0033:
            r7 = r15
        L_0x0034:
            r1 = r9
            r8 = r16
            r1.<init>(r2, r3, r4, r5, r6, r7, r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.window.embedding.SplitRule.<init>(java.lang.String, int, int, int, androidx.window.embedding.EmbeddingAspectRatio, androidx.window.embedding.EmbeddingAspectRatio, androidx.window.embedding.SplitAttributes, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public final EmbeddingAspectRatio getMaxAspectRatioInPortrait() {
        return this.maxAspectRatioInPortrait;
    }

    public final EmbeddingAspectRatio getMaxAspectRatioInLandscape() {
        return this.maxAspectRatioInLandscape;
    }

    public final SplitAttributes getDefaultSplitAttributes() {
        return this.defaultSplitAttributes;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SplitRule(String str, int i, int i2, int i3, EmbeddingAspectRatio embeddingAspectRatio, EmbeddingAspectRatio embeddingAspectRatio2, SplitAttributes splitAttributes) {
        super(str);
        Intrinsics.checkNotNullParameter(embeddingAspectRatio, "maxAspectRatioInPortrait");
        Intrinsics.checkNotNullParameter(embeddingAspectRatio2, "maxAspectRatioInLandscape");
        Intrinsics.checkNotNullParameter(splitAttributes, "defaultSplitAttributes");
        this.minWidthDp = i;
        this.minHeightDp = i2;
        this.minSmallestWidthDp = i3;
        this.maxAspectRatioInPortrait = embeddingAspectRatio;
        this.maxAspectRatioInLandscape = embeddingAspectRatio2;
        this.defaultSplitAttributes = splitAttributes;
        Preconditions.checkArgumentNonnegative(i, "minWidthDp must be non-negative");
        Preconditions.checkArgumentNonnegative(i2, "minHeightDp must be non-negative");
        Preconditions.checkArgumentNonnegative(i3, "minSmallestWidthDp must be non-negative");
    }

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u00020\u00048\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Landroidx/window/embedding/SplitRule$Companion;", "", "()V", "SPLIT_MAX_ASPECT_RATIO_LANDSCAPE_DEFAULT", "Landroidx/window/embedding/EmbeddingAspectRatio;", "SPLIT_MAX_ASPECT_RATIO_PORTRAIT_DEFAULT", "SPLIT_MIN_DIMENSION_ALWAYS_ALLOW", "", "SPLIT_MIN_DIMENSION_DP_DEFAULT", "window_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: SplitRule.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\u0018\u0000 \n2\u00020\u0001:\u0001\nB\u0017\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\t\u001a\u00020\u0003H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u000b"}, d2 = {"Landroidx/window/embedding/SplitRule$FinishBehavior;", "", "description", "", "value", "", "(Ljava/lang/String;I)V", "getValue$window_release", "()I", "toString", "Companion", "window_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: SplitRule.kt */
    public static final class FinishBehavior {
        public static final FinishBehavior ADJACENT = new FinishBehavior("ADJACENT", 2);
        public static final FinishBehavior ALWAYS = new FinishBehavior("ALWAYS", 1);
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        public static final FinishBehavior NEVER = new FinishBehavior("NEVER", 0);
        private final String description;
        private final int value;

        @JvmStatic
        public static final FinishBehavior getFinishBehaviorFromValue$window_release(int i) {
            return Companion.getFinishBehaviorFromValue$window_release(i);
        }

        private FinishBehavior(String str, int i) {
            this.description = str;
            this.value = i;
        }

        public final int getValue$window_release() {
            return this.value;
        }

        public String toString() {
            return this.description;
        }

        @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0017\u0010\u0007\u001a\u00020\u00042\b\b\u0001\u0010\b\u001a\u00020\tH\u0001¢\u0006\u0002\b\nR\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u00020\u00048\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00020\u00048\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Landroidx/window/embedding/SplitRule$FinishBehavior$Companion;", "", "()V", "ADJACENT", "Landroidx/window/embedding/SplitRule$FinishBehavior;", "ALWAYS", "NEVER", "getFinishBehaviorFromValue", "value", "", "getFinishBehaviorFromValue$window_release", "window_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
        /* compiled from: SplitRule.kt */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            @JvmStatic
            public final FinishBehavior getFinishBehaviorFromValue$window_release(int i) {
                if (i == FinishBehavior.NEVER.getValue$window_release()) {
                    return FinishBehavior.NEVER;
                }
                if (i == FinishBehavior.ALWAYS.getValue$window_release()) {
                    return FinishBehavior.ALWAYS;
                }
                if (i == FinishBehavior.ADJACENT.getValue$window_release()) {
                    return FinishBehavior.ADJACENT;
                }
                throw new IllegalArgumentException("Unknown finish behavior:" + i);
            }
        }
    }

    public final boolean checkParentMetrics$window_release(Context context, WindowMetrics windowMetrics) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(windowMetrics, "parentMetrics");
        if (Build.VERSION.SDK_INT <= 30) {
            return false;
        }
        return checkParentBounds$window_release(context.getResources().getDisplayMetrics().density, Api30Impl.INSTANCE.getBounds(windowMetrics));
    }

    public final boolean checkParentBounds$window_release(float f, Rect rect) {
        Intrinsics.checkNotNullParameter(rect, "bounds");
        int width = rect.width();
        int height = rect.height();
        if (width == 0 || height == 0) {
            return false;
        }
        int convertDpToPx = convertDpToPx(f, this.minWidthDp);
        int convertDpToPx2 = convertDpToPx(f, this.minHeightDp);
        int convertDpToPx3 = convertDpToPx(f, this.minSmallestWidthDp);
        boolean z = this.minWidthDp == 0 || width >= convertDpToPx;
        boolean z2 = this.minHeightDp == 0 || height >= convertDpToPx2;
        boolean z3 = this.minSmallestWidthDp == 0 || Math.min(width, height) >= convertDpToPx3;
        boolean z4 = height < width ? Intrinsics.areEqual((Object) this.maxAspectRatioInLandscape, (Object) EmbeddingAspectRatio.ALWAYS_ALLOW) || (((float) width) * 1.0f) / ((float) height) <= this.maxAspectRatioInLandscape.getValue$window_release() : Intrinsics.areEqual((Object) this.maxAspectRatioInPortrait, (Object) EmbeddingAspectRatio.ALWAYS_ALLOW) || (((float) height) * 1.0f) / ((float) width) <= this.maxAspectRatioInPortrait.getValue$window_release();
        if (!z || !z2 || !z3 || !z4) {
            return false;
        }
        return true;
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÁ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, d2 = {"Landroidx/window/embedding/SplitRule$Api30Impl;", "", "()V", "getBounds", "Landroid/graphics/Rect;", "windowMetrics", "Landroid/view/WindowMetrics;", "window_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: SplitRule.kt */
    public static final class Api30Impl {
        public static final Api30Impl INSTANCE = new Api30Impl();

        private Api30Impl() {
        }

        public final Rect getBounds(WindowMetrics windowMetrics) {
            Intrinsics.checkNotNullParameter(windowMetrics, "windowMetrics");
            Rect bounds = windowMetrics.getBounds();
            Intrinsics.checkNotNullExpressionValue(bounds, "windowMetrics.bounds");
            return bounds;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SplitRule) || !super.equals(obj)) {
            return false;
        }
        SplitRule splitRule = (SplitRule) obj;
        return this.minWidthDp == splitRule.minWidthDp && this.minHeightDp == splitRule.minHeightDp && this.minSmallestWidthDp == splitRule.minSmallestWidthDp && Intrinsics.areEqual((Object) this.maxAspectRatioInPortrait, (Object) splitRule.maxAspectRatioInPortrait) && Intrinsics.areEqual((Object) this.maxAspectRatioInLandscape, (Object) splitRule.maxAspectRatioInLandscape) && Intrinsics.areEqual((Object) this.defaultSplitAttributes, (Object) splitRule.defaultSplitAttributes);
    }

    public int hashCode() {
        return (((((((((((super.hashCode() * 31) + this.minWidthDp) * 31) + this.minHeightDp) * 31) + this.minSmallestWidthDp) * 31) + this.maxAspectRatioInPortrait.hashCode()) * 31) + this.maxAspectRatioInLandscape.hashCode()) * 31) + this.defaultSplitAttributes.hashCode();
    }

    public String toString() {
        return "SplitRule{ tag=" + getTag() + ", defaultSplitAttributes=" + this.defaultSplitAttributes + ", minWidthDp=" + this.minWidthDp + ", minHeightDp=" + this.minHeightDp + ", minSmallestWidthDp=" + this.minSmallestWidthDp + ", maxAspectRatioInPortrait=" + this.maxAspectRatioInPortrait + ", maxAspectRatioInLandscape=" + this.maxAspectRatioInLandscape + AbstractJsonLexerKt.END_OBJ;
    }
}
