package androidx.window.embedding;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import androidx.window.R;
import androidx.window.embedding.ActivityRule;
import androidx.window.embedding.SplitAttributes;
import androidx.window.embedding.SplitPairRule;
import androidx.window.embedding.SplitPlaceholderRule;
import androidx.window.embedding.SplitRule;
import java.util.HashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0002J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002J'\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u00122\u0006\u0010\u000b\u001a\u00020\f2\b\b\u0001\u0010\u0014\u001a\u00020\u0015H\u0000¢\u0006\u0002\b\u0016J\u0018\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0018\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0018\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002J$\u0010\u001d\u001a\u00020\u001e*\u0012\u0012\u0004\u0012\u00020\u00130\u001fj\b\u0012\u0004\u0012\u00020\u0013` 2\u0006\u0010!\u001a\u00020\u0013H\u0002¨\u0006\""}, d2 = {"Landroidx/window/embedding/RuleParser;", "", "()V", "buildClassName", "Landroid/content/ComponentName;", "pkg", "", "clsSeq", "", "parseActivityFilter", "Landroidx/window/embedding/ActivityFilter;", "context", "Landroid/content/Context;", "parser", "Landroid/content/res/XmlResourceParser;", "parseActivityRule", "Landroidx/window/embedding/ActivityRule;", "parseRules", "", "Landroidx/window/embedding/EmbeddingRule;", "staticRuleResourceId", "", "parseRules$window_release", "parseSplitPairFilter", "Landroidx/window/embedding/SplitPairFilter;", "parseSplitPairRule", "Landroidx/window/embedding/SplitPairRule;", "parseSplitPlaceholderRule", "Landroidx/window/embedding/SplitPlaceholderRule;", "addRuleWithDuplicatedTagCheck", "", "Ljava/util/HashSet;", "Lkotlin/collections/HashSet;", "rule", "window_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: RuleParser.kt */
public final class RuleParser {
    public static final RuleParser INSTANCE = new RuleParser();

    private RuleParser() {
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0069, code lost:
        r6 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0082, code lost:
        r4 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00c0, code lost:
        r5 = r3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.Set<androidx.window.embedding.EmbeddingRule> parseRules$window_release(android.content.Context r9, int r10) {
        /*
            r8 = this;
            java.lang.String r0 = "context"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r0)
            android.content.res.Resources r0 = r9.getResources()
            r1 = 0
            android.content.res.XmlResourceParser r10 = r0.getXml(r10)     // Catch:{ NotFoundException -> 0x010c }
            java.lang.String r0 = "resources.getXml(staticRuleResourceId)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r10, r0)     // Catch:{ NotFoundException -> 0x010c }
            java.util.HashSet r0 = new java.util.HashSet
            r0.<init>()
            int r2 = r10.getDepth()
            int r3 = r10.next()
            r4 = r1
            r5 = r4
            r6 = r5
        L_0x0023:
            r7 = 1
            if (r3 == r7) goto L_0x0109
            r7 = 3
            if (r3 != r7) goto L_0x002f
            int r3 = r10.getDepth()
            if (r3 <= r2) goto L_0x0109
        L_0x002f:
            int r3 = r10.getEventType()
            r7 = 2
            if (r3 != r7) goto L_0x0103
            java.lang.String r3 = "split-config"
            java.lang.String r7 = r10.getName()
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r3, (java.lang.Object) r7)
            if (r3 == 0) goto L_0x0044
            goto L_0x0103
        L_0x0044:
            java.lang.String r3 = r10.getName()
            if (r3 == 0) goto L_0x00fd
            int r7 = r3.hashCode()
            switch(r7) {
                case 511422343: goto L_0x00c2;
                case 520447504: goto L_0x00ab;
                case 1579230604: goto L_0x0085;
                case 1793077963: goto L_0x006c;
                case 2050988213: goto L_0x0053;
                default: goto L_0x0051;
            }
        L_0x0051:
            goto L_0x00fd
        L_0x0053:
            java.lang.String r7 = "SplitPlaceholderRule"
            boolean r3 = r3.equals(r7)
            if (r3 != 0) goto L_0x005d
            goto L_0x00fd
        L_0x005d:
            androidx.window.embedding.SplitPlaceholderRule r3 = r8.parseSplitPlaceholderRule(r9, r10)
            r4 = r3
            androidx.window.embedding.EmbeddingRule r4 = (androidx.window.embedding.EmbeddingRule) r4
            r8.addRuleWithDuplicatedTagCheck(r0, r4)
            r4 = r1
            r5 = r4
        L_0x0069:
            r6 = r3
            goto L_0x00fd
        L_0x006c:
            java.lang.String r7 = "ActivityRule"
            boolean r3 = r3.equals(r7)
            if (r3 != 0) goto L_0x0076
            goto L_0x00fd
        L_0x0076:
            androidx.window.embedding.ActivityRule r3 = r8.parseActivityRule(r9, r10)
            r4 = r3
            androidx.window.embedding.EmbeddingRule r4 = (androidx.window.embedding.EmbeddingRule) r4
            r8.addRuleWithDuplicatedTagCheck(r0, r4)
            r5 = r1
            r6 = r5
        L_0x0082:
            r4 = r3
            goto L_0x00fd
        L_0x0085:
            java.lang.String r7 = "SplitPairFilter"
            boolean r3 = r3.equals(r7)
            if (r3 != 0) goto L_0x008f
            goto L_0x00fd
        L_0x008f:
            if (r5 == 0) goto L_0x00a3
            androidx.window.embedding.SplitPairFilter r3 = r8.parseSplitPairFilter(r9, r10)
            r0.remove(r5)
            androidx.window.embedding.SplitPairRule r3 = r5.plus$window_release(r3)
            r5 = r3
            androidx.window.embedding.EmbeddingRule r5 = (androidx.window.embedding.EmbeddingRule) r5
            r8.addRuleWithDuplicatedTagCheck(r0, r5)
            goto L_0x00c0
        L_0x00a3:
            java.lang.IllegalArgumentException r9 = new java.lang.IllegalArgumentException
            java.lang.String r10 = "Found orphaned SplitPairFilter outside of SplitPairRule"
            r9.<init>(r10)
            throw r9
        L_0x00ab:
            java.lang.String r7 = "SplitPairRule"
            boolean r3 = r3.equals(r7)
            if (r3 != 0) goto L_0x00b4
            goto L_0x00fd
        L_0x00b4:
            androidx.window.embedding.SplitPairRule r3 = r8.parseSplitPairRule(r9, r10)
            r4 = r3
            androidx.window.embedding.EmbeddingRule r4 = (androidx.window.embedding.EmbeddingRule) r4
            r8.addRuleWithDuplicatedTagCheck(r0, r4)
            r4 = r1
            r6 = r4
        L_0x00c0:
            r5 = r3
            goto L_0x00fd
        L_0x00c2:
            java.lang.String r7 = "ActivityFilter"
            boolean r3 = r3.equals(r7)
            if (r3 != 0) goto L_0x00cb
            goto L_0x00fd
        L_0x00cb:
            if (r4 != 0) goto L_0x00d8
            if (r6 == 0) goto L_0x00d0
            goto L_0x00d8
        L_0x00d0:
            java.lang.IllegalArgumentException r9 = new java.lang.IllegalArgumentException
            java.lang.String r10 = "Found orphaned ActivityFilter"
            r9.<init>(r10)
            throw r9
        L_0x00d8:
            androidx.window.embedding.ActivityFilter r3 = r8.parseActivityFilter(r9, r10)
            if (r4 == 0) goto L_0x00ec
            r0.remove(r4)
            androidx.window.embedding.ActivityRule r3 = r4.plus$window_release(r3)
            r4 = r3
            androidx.window.embedding.EmbeddingRule r4 = (androidx.window.embedding.EmbeddingRule) r4
            r8.addRuleWithDuplicatedTagCheck(r0, r4)
            goto L_0x0082
        L_0x00ec:
            if (r6 == 0) goto L_0x00fd
            r0.remove(r6)
            androidx.window.embedding.SplitPlaceholderRule r3 = r6.plus$window_release(r3)
            r6 = r3
            androidx.window.embedding.EmbeddingRule r6 = (androidx.window.embedding.EmbeddingRule) r6
            r8.addRuleWithDuplicatedTagCheck(r0, r6)
            goto L_0x0069
        L_0x00fd:
            int r3 = r10.next()
            goto L_0x0023
        L_0x0103:
            int r3 = r10.next()
            goto L_0x0023
        L_0x0109:
            java.util.Set r0 = (java.util.Set) r0
            return r0
        L_0x010c:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.window.embedding.RuleParser.parseRules$window_release(android.content.Context, int):java.util.Set");
    }

    private final void addRuleWithDuplicatedTagCheck(HashSet<EmbeddingRule> hashSet, EmbeddingRule embeddingRule) {
        String tag = embeddingRule.getTag();
        for (EmbeddingRule embeddingRule2 : hashSet) {
            if (tag != null && Intrinsics.areEqual((Object) tag, (Object) embeddingRule2.getTag())) {
                throw new IllegalArgumentException("Duplicated tag: " + tag + " for " + embeddingRule + ". The tag must be unique in XML rule definition.");
            }
        }
        hashSet.add(embeddingRule);
    }

    private final SplitPairRule parseSplitPairRule(Context context, XmlResourceParser xmlResourceParser) {
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(xmlResourceParser, R.styleable.SplitPairRule, 0, 0);
        String string = obtainStyledAttributes.getString(R.styleable.SplitPairRule_tag);
        float f = obtainStyledAttributes.getFloat(R.styleable.SplitPairRule_splitRatio, 0.5f);
        int integer = obtainStyledAttributes.getInteger(R.styleable.SplitPairRule_splitMinWidthDp, 600);
        int integer2 = obtainStyledAttributes.getInteger(R.styleable.SplitPairRule_splitMinHeightDp, 600);
        int integer3 = obtainStyledAttributes.getInteger(R.styleable.SplitPairRule_splitMinSmallestWidthDp, 600);
        float f2 = obtainStyledAttributes.getFloat(R.styleable.SplitPairRule_splitMaxAspectRatioInPortrait, SplitRule.SPLIT_MAX_ASPECT_RATIO_PORTRAIT_DEFAULT.getValue$window_release());
        float f3 = obtainStyledAttributes.getFloat(R.styleable.SplitPairRule_splitMaxAspectRatioInLandscape, SplitRule.SPLIT_MAX_ASPECT_RATIO_LANDSCAPE_DEFAULT.getValue$window_release());
        int i = obtainStyledAttributes.getInt(R.styleable.SplitPairRule_splitLayoutDirection, SplitAttributes.LayoutDirection.LOCALE.getValue$window_release());
        int i2 = obtainStyledAttributes.getInt(R.styleable.SplitPairRule_finishPrimaryWithSecondary, SplitRule.FinishBehavior.NEVER.getValue$window_release());
        int i3 = obtainStyledAttributes.getInt(R.styleable.SplitPairRule_finishSecondaryWithPrimary, SplitRule.FinishBehavior.ALWAYS.getValue$window_release());
        boolean z = obtainStyledAttributes.getBoolean(R.styleable.SplitPairRule_clearTop, false);
        return new SplitPairRule.Builder(SetsKt.emptySet()).setTag(string).setMinWidthDp(integer).setMinHeightDp(integer2).setMinSmallestWidthDp(integer3).setMaxAspectRatioInPortrait(EmbeddingAspectRatio.Companion.buildAspectRatioFromValue$window_release(f2)).setMaxAspectRatioInLandscape(EmbeddingAspectRatio.Companion.buildAspectRatioFromValue$window_release(f3)).setFinishPrimaryWithSecondary(SplitRule.FinishBehavior.Companion.getFinishBehaviorFromValue$window_release(i2)).setFinishSecondaryWithPrimary(SplitRule.FinishBehavior.Companion.getFinishBehaviorFromValue$window_release(i3)).setClearTop(z).setDefaultSplitAttributes(new SplitAttributes.Builder().setSplitType(SplitAttributes.SplitType.Companion.buildSplitTypeFromValue$window_release(f)).setLayoutDirection(SplitAttributes.LayoutDirection.Companion.getLayoutDirectionFromValue$window_release(i)).build()).build();
    }

    private final SplitPlaceholderRule parseSplitPlaceholderRule(Context context, XmlResourceParser xmlResourceParser) {
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(xmlResourceParser, R.styleable.SplitPlaceholderRule, 0, 0);
        String string = obtainStyledAttributes.getString(R.styleable.SplitPlaceholderRule_tag);
        String string2 = obtainStyledAttributes.getString(R.styleable.SplitPlaceholderRule_placeholderActivityName);
        boolean z = obtainStyledAttributes.getBoolean(R.styleable.SplitPlaceholderRule_stickyPlaceholder, false);
        int i = obtainStyledAttributes.getInt(R.styleable.SplitPlaceholderRule_finishPrimaryWithPlaceholder, SplitRule.FinishBehavior.ALWAYS.getValue$window_release());
        if (i != SplitRule.FinishBehavior.NEVER.getValue$window_release()) {
            float f = obtainStyledAttributes.getFloat(R.styleable.SplitPlaceholderRule_splitRatio, 0.5f);
            int integer = obtainStyledAttributes.getInteger(R.styleable.SplitPlaceholderRule_splitMinWidthDp, 600);
            int integer2 = obtainStyledAttributes.getInteger(R.styleable.SplitPlaceholderRule_splitMinHeightDp, 600);
            int integer3 = obtainStyledAttributes.getInteger(R.styleable.SplitPlaceholderRule_splitMinSmallestWidthDp, 600);
            float f2 = obtainStyledAttributes.getFloat(R.styleable.SplitPlaceholderRule_splitMaxAspectRatioInPortrait, SplitRule.SPLIT_MAX_ASPECT_RATIO_PORTRAIT_DEFAULT.getValue$window_release());
            float f3 = obtainStyledAttributes.getFloat(R.styleable.SplitPlaceholderRule_splitMaxAspectRatioInLandscape, SplitRule.SPLIT_MAX_ASPECT_RATIO_LANDSCAPE_DEFAULT.getValue$window_release());
            SplitAttributes build = new SplitAttributes.Builder().setSplitType(SplitAttributes.SplitType.Companion.buildSplitTypeFromValue$window_release(f)).setLayoutDirection(SplitAttributes.LayoutDirection.Companion.getLayoutDirectionFromValue$window_release(obtainStyledAttributes.getInt(R.styleable.SplitPlaceholderRule_splitLayoutDirection, SplitAttributes.LayoutDirection.LOCALE.getValue$window_release()))).build();
            String packageName = context.getApplicationContext().getPackageName();
            RuleParser ruleParser = INSTANCE;
            Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
            ComponentName buildClassName = ruleParser.buildClassName(packageName, string2);
            Set emptySet = SetsKt.emptySet();
            Intent component = new Intent().setComponent(buildClassName);
            Intrinsics.checkNotNullExpressionValue(component, "Intent().setComponent(pl…eholderActivityClassName)");
            return new SplitPlaceholderRule.Builder(emptySet, component).setTag(string).setMinWidthDp(integer).setMinHeightDp(integer2).setMinSmallestWidthDp(integer3).setMaxAspectRatioInPortrait(EmbeddingAspectRatio.Companion.buildAspectRatioFromValue$window_release(f2)).setMaxAspectRatioInLandscape(EmbeddingAspectRatio.Companion.buildAspectRatioFromValue$window_release(f3)).setSticky(z).setFinishPrimaryWithPlaceholder(SplitRule.FinishBehavior.Companion.getFinishBehaviorFromValue$window_release(i)).setDefaultSplitAttributes(build).build();
        }
        throw new IllegalArgumentException("Never is not a valid configuration for Placeholder activities. Please use FINISH_ALWAYS or FINISH_ADJACENT instead or refer to the current API");
    }

    private final SplitPairFilter parseSplitPairFilter(Context context, XmlResourceParser xmlResourceParser) {
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(xmlResourceParser, R.styleable.SplitPairFilter, 0, 0);
        String string = obtainStyledAttributes.getString(R.styleable.SplitPairFilter_primaryActivityName);
        String string2 = obtainStyledAttributes.getString(R.styleable.SplitPairFilter_secondaryActivityName);
        String string3 = obtainStyledAttributes.getString(R.styleable.SplitPairFilter_secondaryActivityAction);
        String packageName = context.getApplicationContext().getPackageName();
        Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
        return new SplitPairFilter(buildClassName(packageName, string), buildClassName(packageName, string2), string3);
    }

    private final ActivityRule parseActivityRule(Context context, XmlResourceParser xmlResourceParser) {
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(xmlResourceParser, R.styleable.ActivityRule, 0, 0);
        String string = obtainStyledAttributes.getString(R.styleable.ActivityRule_tag);
        boolean z = obtainStyledAttributes.getBoolean(R.styleable.ActivityRule_alwaysExpand, false);
        obtainStyledAttributes.recycle();
        ActivityRule.Builder alwaysExpand = new ActivityRule.Builder(SetsKt.emptySet()).setAlwaysExpand(z);
        if (string != null) {
            alwaysExpand.setTag(string);
        }
        return alwaysExpand.build();
    }

    private final ActivityFilter parseActivityFilter(Context context, XmlResourceParser xmlResourceParser) {
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(xmlResourceParser, R.styleable.ActivityFilter, 0, 0);
        String string = obtainStyledAttributes.getString(R.styleable.ActivityFilter_activityName);
        String string2 = obtainStyledAttributes.getString(R.styleable.ActivityFilter_activityAction);
        String packageName = context.getApplicationContext().getPackageName();
        Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
        return new ActivityFilter(buildClassName(packageName, string), string2);
    }

    private final ComponentName buildClassName(String str, CharSequence charSequence) {
        if (charSequence == null || charSequence.length() == 0) {
            throw new IllegalArgumentException("Activity name must not be null");
        }
        String obj = charSequence.toString();
        if (obj.charAt(0) == '.') {
            return new ComponentName(str, str + obj);
        }
        int indexOf$default = StringsKt.indexOf$default((CharSequence) obj, '/', 0, false, 6, (Object) null);
        if (indexOf$default > 0) {
            str = obj.substring(0, indexOf$default);
            Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String…ing(startIndex, endIndex)");
            obj = obj.substring(indexOf$default + 1);
            Intrinsics.checkNotNullExpressionValue(obj, "this as java.lang.String).substring(startIndex)");
        }
        if (Intrinsics.areEqual((Object) obj, (Object) "*") || StringsKt.indexOf$default((CharSequence) obj, '.', 0, false, 6, (Object) null) >= 0) {
            return new ComponentName(str, obj);
        }
        return new ComponentName(str, str + '.' + obj);
    }
}
