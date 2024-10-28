package androidx.window.embedding;

import androidx.window.embedding.SplitAttributes;
import androidx.window.embedding.SplitRule;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.UInt$$ExternalSyntheticBackport0;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0000\n\u0002\b\u0007\u0018\u00002\u00020\u0001:\u0001%By\b\u0000\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\n\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u0012\b\b\u0003\u0010\u000e\u001a\u00020\u000f\u0012\b\b\u0003\u0010\u0010\u001a\u00020\u000f\u0012\b\b\u0003\u0010\u0011\u001a\u00020\u000f\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0013\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0013¢\u0006\u0002\u0010\u0015J\u0013\u0010\u001d\u001a\u00020\r2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0002J\b\u0010 \u001a\u00020\u000fH\u0016J\u0016\u0010!\u001a\u00020\u00002\u0006\u0010\"\u001a\u00020\u0004H\u0002¢\u0006\u0002\b#J\b\u0010$\u001a\u00020\bH\u0016R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u000b\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001b¨\u0006&"}, d2 = {"Landroidx/window/embedding/SplitPairRule;", "Landroidx/window/embedding/SplitRule;", "filters", "", "Landroidx/window/embedding/SplitPairFilter;", "defaultSplitAttributes", "Landroidx/window/embedding/SplitAttributes;", "tag", "", "finishPrimaryWithSecondary", "Landroidx/window/embedding/SplitRule$FinishBehavior;", "finishSecondaryWithPrimary", "clearTop", "", "minWidthDp", "", "minHeightDp", "minSmallestWidthDp", "maxAspectRatioInPortrait", "Landroidx/window/embedding/EmbeddingAspectRatio;", "maxAspectRatioInLandscape", "(Ljava/util/Set;Landroidx/window/embedding/SplitAttributes;Ljava/lang/String;Landroidx/window/embedding/SplitRule$FinishBehavior;Landroidx/window/embedding/SplitRule$FinishBehavior;ZIIILandroidx/window/embedding/EmbeddingAspectRatio;Landroidx/window/embedding/EmbeddingAspectRatio;)V", "getClearTop", "()Z", "getFilters", "()Ljava/util/Set;", "getFinishPrimaryWithSecondary", "()Landroidx/window/embedding/SplitRule$FinishBehavior;", "getFinishSecondaryWithPrimary", "equals", "other", "", "hashCode", "plus", "filter", "plus$window_release", "toString", "Builder", "window_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: SplitPairRule.kt */
public final class SplitPairRule extends SplitRule {
    private final boolean clearTop;
    private final Set<SplitPairFilter> filters;
    private final SplitRule.FinishBehavior finishPrimaryWithSecondary;
    private final SplitRule.FinishBehavior finishSecondaryWithPrimary;

    public final Set<SplitPairFilter> getFilters() {
        return this.filters;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ SplitPairRule(java.util.Set r15, androidx.window.embedding.SplitAttributes r16, java.lang.String r17, androidx.window.embedding.SplitRule.FinishBehavior r18, androidx.window.embedding.SplitRule.FinishBehavior r19, boolean r20, int r21, int r22, int r23, androidx.window.embedding.EmbeddingAspectRatio r24, androidx.window.embedding.EmbeddingAspectRatio r25, int r26, kotlin.jvm.internal.DefaultConstructorMarker r27) {
        /*
            r14 = this;
            r0 = r26
            r1 = r0 & 4
            if (r1 == 0) goto L_0x0009
            r1 = 0
            r5 = r1
            goto L_0x000b
        L_0x0009:
            r5 = r17
        L_0x000b:
            r1 = r0 & 8
            if (r1 == 0) goto L_0x0013
            androidx.window.embedding.SplitRule$FinishBehavior r1 = androidx.window.embedding.SplitRule.FinishBehavior.NEVER
            r6 = r1
            goto L_0x0015
        L_0x0013:
            r6 = r18
        L_0x0015:
            r1 = r0 & 16
            if (r1 == 0) goto L_0x001d
            androidx.window.embedding.SplitRule$FinishBehavior r1 = androidx.window.embedding.SplitRule.FinishBehavior.ALWAYS
            r7 = r1
            goto L_0x001f
        L_0x001d:
            r7 = r19
        L_0x001f:
            r1 = r0 & 32
            if (r1 == 0) goto L_0x0026
            r1 = 0
            r8 = 0
            goto L_0x0028
        L_0x0026:
            r8 = r20
        L_0x0028:
            r1 = r0 & 64
            r2 = 600(0x258, float:8.41E-43)
            if (r1 == 0) goto L_0x0031
            r9 = 600(0x258, float:8.41E-43)
            goto L_0x0033
        L_0x0031:
            r9 = r21
        L_0x0033:
            r1 = r0 & 128(0x80, float:1.794E-43)
            if (r1 == 0) goto L_0x003a
            r10 = 600(0x258, float:8.41E-43)
            goto L_0x003c
        L_0x003a:
            r10 = r22
        L_0x003c:
            r1 = r0 & 256(0x100, float:3.59E-43)
            if (r1 == 0) goto L_0x0043
            r11 = 600(0x258, float:8.41E-43)
            goto L_0x0045
        L_0x0043:
            r11 = r23
        L_0x0045:
            r1 = r0 & 512(0x200, float:7.175E-43)
            if (r1 == 0) goto L_0x004d
            androidx.window.embedding.EmbeddingAspectRatio r1 = androidx.window.embedding.SplitRule.SPLIT_MAX_ASPECT_RATIO_PORTRAIT_DEFAULT
            r12 = r1
            goto L_0x004f
        L_0x004d:
            r12 = r24
        L_0x004f:
            r0 = r0 & 1024(0x400, float:1.435E-42)
            if (r0 == 0) goto L_0x0057
            androidx.window.embedding.EmbeddingAspectRatio r0 = androidx.window.embedding.SplitRule.SPLIT_MAX_ASPECT_RATIO_LANDSCAPE_DEFAULT
            r13 = r0
            goto L_0x0059
        L_0x0057:
            r13 = r25
        L_0x0059:
            r2 = r14
            r3 = r15
            r4 = r16
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.window.embedding.SplitPairRule.<init>(java.util.Set, androidx.window.embedding.SplitAttributes, java.lang.String, androidx.window.embedding.SplitRule$FinishBehavior, androidx.window.embedding.SplitRule$FinishBehavior, boolean, int, int, int, androidx.window.embedding.EmbeddingAspectRatio, androidx.window.embedding.EmbeddingAspectRatio, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public final SplitRule.FinishBehavior getFinishPrimaryWithSecondary() {
        return this.finishPrimaryWithSecondary;
    }

    public final SplitRule.FinishBehavior getFinishSecondaryWithPrimary() {
        return this.finishSecondaryWithPrimary;
    }

    public final boolean getClearTop() {
        return this.clearTop;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public SplitPairRule(java.util.Set<androidx.window.embedding.SplitPairFilter> r13, androidx.window.embedding.SplitAttributes r14, java.lang.String r15, androidx.window.embedding.SplitRule.FinishBehavior r16, androidx.window.embedding.SplitRule.FinishBehavior r17, boolean r18, int r19, int r20, int r21, androidx.window.embedding.EmbeddingAspectRatio r22, androidx.window.embedding.EmbeddingAspectRatio r23) {
        /*
            r12 = this;
            r8 = r12
            r9 = r13
            r10 = r16
            r11 = r17
            java.lang.String r0 = "filters"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r13, r0)
            java.lang.String r0 = "defaultSplitAttributes"
            r7 = r14
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r14, r0)
            java.lang.String r0 = "finishPrimaryWithSecondary"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r0)
            java.lang.String r0 = "finishSecondaryWithPrimary"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r11, r0)
            java.lang.String r0 = "maxAspectRatioInPortrait"
            r5 = r22
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            java.lang.String r0 = "maxAspectRatioInLandscape"
            r6 = r23
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
            r0 = r12
            r1 = r15
            r2 = r19
            r3 = r20
            r4 = r21
            r0.<init>(r1, r2, r3, r4, r5, r6, r7)
            r8.filters = r9
            r8.finishPrimaryWithSecondary = r10
            r8.finishSecondaryWithPrimary = r11
            r0 = r18
            r8.clearTop = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.window.embedding.SplitPairRule.<init>(java.util.Set, androidx.window.embedding.SplitAttributes, java.lang.String, androidx.window.embedding.SplitRule$FinishBehavior, androidx.window.embedding.SplitRule$FinishBehavior, boolean, int, int, int, androidx.window.embedding.EmbeddingAspectRatio, androidx.window.embedding.EmbeddingAspectRatio):void");
    }

    @Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u0006\u0010\u0016\u001a\u00020\u0017J\u000e\u0010\u0018\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\u0019\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\u001a\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u000bJ\u000e\u0010\u001b\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\u000bJ\u000e\u0010\u001c\u001a\u00020\u00002\u0006\u0010\u001d\u001a\u00020\u000eJ\u000e\u0010\u001e\u001a\u00020\u00002\u0006\u0010\u001d\u001a\u00020\u000eJ\u0010\u0010\u001f\u001a\u00020\u00002\b\b\u0001\u0010\u0010\u001a\u00020\u0011J\u0010\u0010 \u001a\u00020\u00002\b\b\u0001\u0010\u0012\u001a\u00020\u0011J\u0010\u0010!\u001a\u00020\u00002\b\b\u0001\u0010\u0013\u001a\u00020\u0011J\u0010\u0010\"\u001a\u00020\u00002\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015R\u000e\u0010\u0006\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000eX\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0010\u001a\u00020\u00118\u0002@\u0002X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0012\u001a\u00020\u00118\u0002@\u0002X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0013\u001a\u00020\u00118\u0002@\u0002X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u000e¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Landroidx/window/embedding/SplitPairRule$Builder;", "", "filters", "", "Landroidx/window/embedding/SplitPairFilter;", "(Ljava/util/Set;)V", "clearTop", "", "defaultSplitAttributes", "Landroidx/window/embedding/SplitAttributes;", "finishPrimaryWithSecondary", "Landroidx/window/embedding/SplitRule$FinishBehavior;", "finishSecondaryWithPrimary", "maxAspectRatioInLandscape", "Landroidx/window/embedding/EmbeddingAspectRatio;", "maxAspectRatioInPortrait", "minHeightDp", "", "minSmallestWidthDp", "minWidthDp", "tag", "", "build", "Landroidx/window/embedding/SplitPairRule;", "setClearTop", "setDefaultSplitAttributes", "setFinishPrimaryWithSecondary", "setFinishSecondaryWithPrimary", "setMaxAspectRatioInLandscape", "aspectRatio", "setMaxAspectRatioInPortrait", "setMinHeightDp", "setMinSmallestWidthDp", "setMinWidthDp", "setTag", "window_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: SplitPairRule.kt */
    public static final class Builder {
        private boolean clearTop;
        private SplitAttributes defaultSplitAttributes = new SplitAttributes.Builder().build();
        private final Set<SplitPairFilter> filters;
        private SplitRule.FinishBehavior finishPrimaryWithSecondary = SplitRule.FinishBehavior.NEVER;
        private SplitRule.FinishBehavior finishSecondaryWithPrimary = SplitRule.FinishBehavior.ALWAYS;
        private EmbeddingAspectRatio maxAspectRatioInLandscape = SplitRule.SPLIT_MAX_ASPECT_RATIO_LANDSCAPE_DEFAULT;
        private EmbeddingAspectRatio maxAspectRatioInPortrait = SplitRule.SPLIT_MAX_ASPECT_RATIO_PORTRAIT_DEFAULT;
        private int minHeightDp = 600;
        private int minSmallestWidthDp = 600;
        private int minWidthDp = 600;
        private String tag;

        public Builder(Set<SplitPairFilter> set) {
            Intrinsics.checkNotNullParameter(set, "filters");
            this.filters = set;
        }

        public final Builder setMinWidthDp(int i) {
            Builder builder = this;
            this.minWidthDp = i;
            return this;
        }

        public final Builder setMinHeightDp(int i) {
            Builder builder = this;
            this.minHeightDp = i;
            return this;
        }

        public final Builder setMinSmallestWidthDp(int i) {
            Builder builder = this;
            this.minSmallestWidthDp = i;
            return this;
        }

        public final Builder setMaxAspectRatioInPortrait(EmbeddingAspectRatio embeddingAspectRatio) {
            Intrinsics.checkNotNullParameter(embeddingAspectRatio, "aspectRatio");
            Builder builder = this;
            this.maxAspectRatioInPortrait = embeddingAspectRatio;
            return this;
        }

        public final Builder setMaxAspectRatioInLandscape(EmbeddingAspectRatio embeddingAspectRatio) {
            Intrinsics.checkNotNullParameter(embeddingAspectRatio, "aspectRatio");
            Builder builder = this;
            this.maxAspectRatioInLandscape = embeddingAspectRatio;
            return this;
        }

        public final Builder setFinishPrimaryWithSecondary(SplitRule.FinishBehavior finishBehavior) {
            Intrinsics.checkNotNullParameter(finishBehavior, "finishPrimaryWithSecondary");
            Builder builder = this;
            this.finishPrimaryWithSecondary = finishBehavior;
            return this;
        }

        public final Builder setFinishSecondaryWithPrimary(SplitRule.FinishBehavior finishBehavior) {
            Intrinsics.checkNotNullParameter(finishBehavior, "finishSecondaryWithPrimary");
            Builder builder = this;
            this.finishSecondaryWithPrimary = finishBehavior;
            return this;
        }

        public final Builder setClearTop(boolean z) {
            Builder builder = this;
            this.clearTop = z;
            return this;
        }

        public final Builder setDefaultSplitAttributes(SplitAttributes splitAttributes) {
            Intrinsics.checkNotNullParameter(splitAttributes, "defaultSplitAttributes");
            Builder builder = this;
            this.defaultSplitAttributes = splitAttributes;
            return this;
        }

        public final Builder setTag(String str) {
            Builder builder = this;
            this.tag = str;
            return this;
        }

        public final SplitPairRule build() {
            return new SplitPairRule(this.filters, this.defaultSplitAttributes, this.tag, this.finishPrimaryWithSecondary, this.finishSecondaryWithPrimary, this.clearTop, this.minWidthDp, this.minHeightDp, this.minSmallestWidthDp, this.maxAspectRatioInPortrait, this.maxAspectRatioInLandscape);
        }
    }

    public final SplitPairRule plus$window_release(SplitPairFilter splitPairFilter) {
        Intrinsics.checkNotNullParameter(splitPairFilter, "filter");
        Set linkedHashSet = new LinkedHashSet();
        linkedHashSet.addAll(this.filters);
        linkedHashSet.add(splitPairFilter);
        return new Builder(CollectionsKt.toSet(linkedHashSet)).setTag(getTag()).setMinWidthDp(getMinWidthDp()).setMinHeightDp(getMinHeightDp()).setMinSmallestWidthDp(getMinSmallestWidthDp()).setMaxAspectRatioInPortrait(getMaxAspectRatioInPortrait()).setMaxAspectRatioInLandscape(getMaxAspectRatioInLandscape()).setFinishPrimaryWithSecondary(this.finishPrimaryWithSecondary).setFinishSecondaryWithPrimary(this.finishSecondaryWithPrimary).setClearTop(this.clearTop).setDefaultSplitAttributes(getDefaultSplitAttributes()).build();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SplitPairRule) || !super.equals(obj)) {
            return false;
        }
        SplitPairRule splitPairRule = (SplitPairRule) obj;
        return Intrinsics.areEqual((Object) this.filters, (Object) splitPairRule.filters) && Intrinsics.areEqual((Object) this.finishPrimaryWithSecondary, (Object) splitPairRule.finishPrimaryWithSecondary) && Intrinsics.areEqual((Object) this.finishSecondaryWithPrimary, (Object) splitPairRule.finishSecondaryWithPrimary) && this.clearTop == splitPairRule.clearTop;
    }

    public int hashCode() {
        return (((((((super.hashCode() * 31) + this.filters.hashCode()) * 31) + this.finishPrimaryWithSecondary.hashCode()) * 31) + this.finishSecondaryWithPrimary.hashCode()) * 31) + UInt$$ExternalSyntheticBackport0.m(this.clearTop);
    }

    public String toString() {
        return "SplitPairRule{tag=" + getTag() + ", defaultSplitAttributes=" + getDefaultSplitAttributes() + ", minWidthDp=" + getMinWidthDp() + ", minHeightDp=" + getMinHeightDp() + ", minSmallestWidthDp=" + getMinSmallestWidthDp() + ", maxAspectRatioInPortrait=" + getMaxAspectRatioInPortrait() + ", maxAspectRatioInLandscape=" + getMaxAspectRatioInLandscape() + ", clearTop=" + this.clearTop + ", finishPrimaryWithSecondary=" + this.finishPrimaryWithSecondary + ", finishSecondaryWithPrimary=" + this.finishSecondaryWithPrimary + ", filters=" + this.filters + AbstractJsonLexerKt.END_OBJ;
    }
}
