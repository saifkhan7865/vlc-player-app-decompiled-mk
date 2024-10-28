package androidx.window.embedding;

import android.content.Intent;
import androidx.window.embedding.SplitAttributes;
import androidx.window.embedding.SplitRule;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.UInt$$ExternalSyntheticBackport0;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0000\n\u0002\b\u0007\u0018\u00002\u00020\u0001:\u0001&Bu\b\u0010\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u0012\b\b\u0003\u0010\r\u001a\u00020\u000e\u0012\b\b\u0003\u0010\u000f\u001a\u00020\u000e\u0012\b\b\u0003\u0010\u0010\u001a\u00020\u000e\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0012\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0012\u0012\u0006\u0010\u0014\u001a\u00020\u0015¢\u0006\u0002\u0010\u0016J\u0013\u0010\u001e\u001a\u00020\n2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0002J\b\u0010!\u001a\u00020\u000eH\u0016J\u0016\u0010\"\u001a\u00020\u00002\u0006\u0010#\u001a\u00020\u0006H\u0002¢\u0006\u0002\b$J\b\u0010%\u001a\u00020\u0003H\u0016R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u001bR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001d¨\u0006'"}, d2 = {"Landroidx/window/embedding/SplitPlaceholderRule;", "Landroidx/window/embedding/SplitRule;", "tag", "", "filters", "", "Landroidx/window/embedding/ActivityFilter;", "placeholderIntent", "Landroid/content/Intent;", "isSticky", "", "finishPrimaryWithPlaceholder", "Landroidx/window/embedding/SplitRule$FinishBehavior;", "minWidthDp", "", "minHeightDp", "minSmallestWidthDp", "maxAspectRatioInPortrait", "Landroidx/window/embedding/EmbeddingAspectRatio;", "maxAspectRatioInLandscape", "defaultSplitAttributes", "Landroidx/window/embedding/SplitAttributes;", "(Ljava/lang/String;Ljava/util/Set;Landroid/content/Intent;ZLandroidx/window/embedding/SplitRule$FinishBehavior;IIILandroidx/window/embedding/EmbeddingAspectRatio;Landroidx/window/embedding/EmbeddingAspectRatio;Landroidx/window/embedding/SplitAttributes;)V", "getFilters", "()Ljava/util/Set;", "getFinishPrimaryWithPlaceholder", "()Landroidx/window/embedding/SplitRule$FinishBehavior;", "()Z", "getPlaceholderIntent", "()Landroid/content/Intent;", "equals", "other", "", "hashCode", "plus", "filter", "plus$window_release", "toString", "Builder", "window_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: SplitPlaceholderRule.kt */
public final class SplitPlaceholderRule extends SplitRule {
    private final Set<ActivityFilter> filters;
    private final SplitRule.FinishBehavior finishPrimaryWithPlaceholder;
    private final boolean isSticky;
    private final Intent placeholderIntent;

    public final Set<ActivityFilter> getFilters() {
        return this.filters;
    }

    public final Intent getPlaceholderIntent() {
        return this.placeholderIntent;
    }

    public final boolean isSticky() {
        return this.isSticky;
    }

    public final SplitRule.FinishBehavior getFinishPrimaryWithPlaceholder() {
        return this.finishPrimaryWithPlaceholder;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ SplitPlaceholderRule(java.lang.String r15, java.util.Set r16, android.content.Intent r17, boolean r18, androidx.window.embedding.SplitRule.FinishBehavior r19, int r20, int r21, int r22, androidx.window.embedding.EmbeddingAspectRatio r23, androidx.window.embedding.EmbeddingAspectRatio r24, androidx.window.embedding.SplitAttributes r25, int r26, kotlin.jvm.internal.DefaultConstructorMarker r27) {
        /*
            r14 = this;
            r0 = r26
            r1 = r0 & 1
            if (r1 == 0) goto L_0x0009
            r1 = 0
            r3 = r1
            goto L_0x000a
        L_0x0009:
            r3 = r15
        L_0x000a:
            r1 = r0 & 16
            if (r1 == 0) goto L_0x0012
            androidx.window.embedding.SplitRule$FinishBehavior r1 = androidx.window.embedding.SplitRule.FinishBehavior.ALWAYS
            r7 = r1
            goto L_0x0014
        L_0x0012:
            r7 = r19
        L_0x0014:
            r1 = r0 & 32
            r2 = 600(0x258, float:8.41E-43)
            if (r1 == 0) goto L_0x001d
            r8 = 600(0x258, float:8.41E-43)
            goto L_0x001f
        L_0x001d:
            r8 = r20
        L_0x001f:
            r1 = r0 & 64
            if (r1 == 0) goto L_0x0026
            r9 = 600(0x258, float:8.41E-43)
            goto L_0x0028
        L_0x0026:
            r9 = r21
        L_0x0028:
            r1 = r0 & 128(0x80, float:1.794E-43)
            if (r1 == 0) goto L_0x002f
            r10 = 600(0x258, float:8.41E-43)
            goto L_0x0031
        L_0x002f:
            r10 = r22
        L_0x0031:
            r1 = r0 & 256(0x100, float:3.59E-43)
            if (r1 == 0) goto L_0x0039
            androidx.window.embedding.EmbeddingAspectRatio r1 = androidx.window.embedding.SplitRule.SPLIT_MAX_ASPECT_RATIO_PORTRAIT_DEFAULT
            r11 = r1
            goto L_0x003b
        L_0x0039:
            r11 = r23
        L_0x003b:
            r0 = r0 & 512(0x200, float:7.175E-43)
            if (r0 == 0) goto L_0x0043
            androidx.window.embedding.EmbeddingAspectRatio r0 = androidx.window.embedding.SplitRule.SPLIT_MAX_ASPECT_RATIO_LANDSCAPE_DEFAULT
            r12 = r0
            goto L_0x0045
        L_0x0043:
            r12 = r24
        L_0x0045:
            r2 = r14
            r4 = r16
            r5 = r17
            r6 = r18
            r13 = r25
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.window.embedding.SplitPlaceholderRule.<init>(java.lang.String, java.util.Set, android.content.Intent, boolean, androidx.window.embedding.SplitRule$FinishBehavior, int, int, int, androidx.window.embedding.EmbeddingAspectRatio, androidx.window.embedding.EmbeddingAspectRatio, androidx.window.embedding.SplitAttributes, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public SplitPlaceholderRule(java.lang.String r13, java.util.Set<androidx.window.embedding.ActivityFilter> r14, android.content.Intent r15, boolean r16, androidx.window.embedding.SplitRule.FinishBehavior r17, int r18, int r19, int r20, androidx.window.embedding.EmbeddingAspectRatio r21, androidx.window.embedding.EmbeddingAspectRatio r22, androidx.window.embedding.SplitAttributes r23) {
        /*
            r12 = this;
            r8 = r12
            r9 = r14
            r10 = r15
            r11 = r17
            java.lang.String r0 = "filters"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r14, r0)
            java.lang.String r0 = "placeholderIntent"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r15, r0)
            java.lang.String r0 = "finishPrimaryWithPlaceholder"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r11, r0)
            java.lang.String r0 = "maxAspectRatioInPortrait"
            r5 = r21
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            java.lang.String r0 = "maxAspectRatioInLandscape"
            r6 = r22
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
            java.lang.String r0 = "defaultSplitAttributes"
            r7 = r23
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
            r0 = r12
            r1 = r13
            r2 = r18
            r3 = r19
            r4 = r20
            r0.<init>(r1, r2, r3, r4, r5, r6, r7)
            androidx.window.embedding.SplitRule$FinishBehavior r0 = androidx.window.embedding.SplitRule.FinishBehavior.NEVER
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r11, (java.lang.Object) r0)
            r0 = r0 ^ 1
            r1 = 0
            java.lang.Object[] r1 = new java.lang.Object[r1]
            java.lang.String r2 = "NEVER is not a valid configuration for SplitPlaceholderRule. Please use FINISH_ALWAYS or FINISH_ADJACENT instead or refer to the current API."
            androidx.core.util.Preconditions.checkArgument(r0, r2, r1)
            r0 = r9
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            java.util.Set r0 = kotlin.collections.CollectionsKt.toSet(r0)
            r8.filters = r0
            r8.placeholderIntent = r10
            r0 = r16
            r8.isSticky = r0
            r8.finishPrimaryWithPlaceholder = r11
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.window.embedding.SplitPlaceholderRule.<init>(java.lang.String, java.util.Set, android.content.Intent, boolean, androidx.window.embedding.SplitRule$FinishBehavior, int, int, int, androidx.window.embedding.EmbeddingAspectRatio, androidx.window.embedding.EmbeddingAspectRatio, androidx.window.embedding.SplitAttributes):void");
    }

    @Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u00002\u00020\u0001B\u001b\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0006\u0010\u0017\u001a\u00020\u0018J\u000e\u0010\u0019\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\u001a\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u000bJ\u000e\u0010\u001b\u001a\u00020\u00002\u0006\u0010\u001c\u001a\u00020\u000fJ\u000e\u0010\u001d\u001a\u00020\u00002\u0006\u0010\u001c\u001a\u00020\u000fJ\u0010\u0010\u001e\u001a\u00020\u00002\b\b\u0001\u0010\u0011\u001a\u00020\u0012J\u0010\u0010\u001f\u001a\u00020\u00002\b\b\u0001\u0010\u0013\u001a\u00020\u0012J\u0010\u0010 \u001a\u00020\u00002\b\b\u0001\u0010\u0014\u001a\u00020\u0012J\u000e\u0010!\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\rJ\u0010\u0010\"\u001a\u00020\u00002\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016R\u000e\u0010\b\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000fX\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0011\u001a\u00020\u00128\u0002@\u0002X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0013\u001a\u00020\u00128\u0002@\u0002X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0014\u001a\u00020\u00128\u0002@\u0002X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u000e¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Landroidx/window/embedding/SplitPlaceholderRule$Builder;", "", "filters", "", "Landroidx/window/embedding/ActivityFilter;", "placeholderIntent", "Landroid/content/Intent;", "(Ljava/util/Set;Landroid/content/Intent;)V", "defaultSplitAttributes", "Landroidx/window/embedding/SplitAttributes;", "finishPrimaryWithPlaceholder", "Landroidx/window/embedding/SplitRule$FinishBehavior;", "isSticky", "", "maxAspectRatioInLandscape", "Landroidx/window/embedding/EmbeddingAspectRatio;", "maxAspectRatioInPortrait", "minHeightDp", "", "minSmallestWidthDp", "minWidthDp", "tag", "", "build", "Landroidx/window/embedding/SplitPlaceholderRule;", "setDefaultSplitAttributes", "setFinishPrimaryWithPlaceholder", "setMaxAspectRatioInLandscape", "aspectRatio", "setMaxAspectRatioInPortrait", "setMinHeightDp", "setMinSmallestWidthDp", "setMinWidthDp", "setSticky", "setTag", "window_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: SplitPlaceholderRule.kt */
    public static final class Builder {
        private SplitAttributes defaultSplitAttributes = new SplitAttributes.Builder().build();
        private final Set<ActivityFilter> filters;
        private SplitRule.FinishBehavior finishPrimaryWithPlaceholder = SplitRule.FinishBehavior.ALWAYS;
        private boolean isSticky;
        private EmbeddingAspectRatio maxAspectRatioInLandscape = SplitRule.SPLIT_MAX_ASPECT_RATIO_LANDSCAPE_DEFAULT;
        private EmbeddingAspectRatio maxAspectRatioInPortrait = SplitRule.SPLIT_MAX_ASPECT_RATIO_PORTRAIT_DEFAULT;
        private int minHeightDp = 600;
        private int minSmallestWidthDp = 600;
        private int minWidthDp = 600;
        private final Intent placeholderIntent;
        private String tag;

        public Builder(Set<ActivityFilter> set, Intent intent) {
            Intrinsics.checkNotNullParameter(set, "filters");
            Intrinsics.checkNotNullParameter(intent, "placeholderIntent");
            this.filters = set;
            this.placeholderIntent = intent;
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

        public final Builder setFinishPrimaryWithPlaceholder(SplitRule.FinishBehavior finishBehavior) {
            Intrinsics.checkNotNullParameter(finishBehavior, "finishPrimaryWithPlaceholder");
            Builder builder = this;
            this.finishPrimaryWithPlaceholder = finishBehavior;
            return this;
        }

        public final Builder setSticky(boolean z) {
            Builder builder = this;
            this.isSticky = z;
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

        public final SplitPlaceholderRule build() {
            return new SplitPlaceholderRule(this.tag, this.filters, this.placeholderIntent, this.isSticky, this.finishPrimaryWithPlaceholder, this.minWidthDp, this.minHeightDp, this.minSmallestWidthDp, this.maxAspectRatioInPortrait, this.maxAspectRatioInLandscape, this.defaultSplitAttributes);
        }
    }

    public final SplitPlaceholderRule plus$window_release(ActivityFilter activityFilter) {
        Intrinsics.checkNotNullParameter(activityFilter, "filter");
        Set linkedHashSet = new LinkedHashSet();
        linkedHashSet.addAll(this.filters);
        linkedHashSet.add(activityFilter);
        return new Builder(CollectionsKt.toSet(linkedHashSet), this.placeholderIntent).setTag(getTag()).setMinWidthDp(getMinWidthDp()).setMinHeightDp(getMinHeightDp()).setMinSmallestWidthDp(getMinSmallestWidthDp()).setMaxAspectRatioInPortrait(getMaxAspectRatioInPortrait()).setMaxAspectRatioInLandscape(getMaxAspectRatioInLandscape()).setSticky(this.isSticky).setFinishPrimaryWithPlaceholder(this.finishPrimaryWithPlaceholder).setDefaultSplitAttributes(getDefaultSplitAttributes()).build();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SplitPlaceholderRule) || !super.equals(obj)) {
            return false;
        }
        SplitPlaceholderRule splitPlaceholderRule = (SplitPlaceholderRule) obj;
        return Intrinsics.areEqual((Object) this.placeholderIntent, (Object) splitPlaceholderRule.placeholderIntent) && this.isSticky == splitPlaceholderRule.isSticky && Intrinsics.areEqual((Object) this.finishPrimaryWithPlaceholder, (Object) splitPlaceholderRule.finishPrimaryWithPlaceholder) && Intrinsics.areEqual((Object) this.filters, (Object) splitPlaceholderRule.filters);
    }

    public int hashCode() {
        return (((((((super.hashCode() * 31) + this.placeholderIntent.hashCode()) * 31) + UInt$$ExternalSyntheticBackport0.m(this.isSticky)) * 31) + this.finishPrimaryWithPlaceholder.hashCode()) * 31) + this.filters.hashCode();
    }

    public String toString() {
        return "SplitPlaceholderRule{tag=" + getTag() + ", defaultSplitAttributes=" + getDefaultSplitAttributes() + ", minWidthDp=" + getMinWidthDp() + ", minHeightDp=" + getMinHeightDp() + ", minSmallestWidthDp=" + getMinSmallestWidthDp() + ", maxAspectRatioInPortrait=" + getMaxAspectRatioInPortrait() + ", maxAspectRatioInLandscape=" + getMaxAspectRatioInLandscape() + ", placeholderIntent=" + this.placeholderIntent + ", isSticky=" + this.isSticky + ", finishPrimaryWithPlaceholder=" + this.finishPrimaryWithPlaceholder + ", filters=" + this.filters + AbstractJsonLexerKt.END_OBJ;
    }
}
