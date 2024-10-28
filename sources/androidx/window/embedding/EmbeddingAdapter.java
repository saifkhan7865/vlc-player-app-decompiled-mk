package androidx.window.embedding;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.util.Pair;
import android.view.WindowMetrics;
import androidx.transition.WindowIdApi18$$ExternalSyntheticApiModelOutline0;
import androidx.window.core.ExtensionsUtil;
import androidx.window.core.PredicateAdapter;
import androidx.window.embedding.SplitAttributes;
import androidx.window.embedding.SplitRule;
import androidx.window.extensions.core.util.function.Function;
import androidx.window.extensions.embedding.ActivityRule;
import androidx.window.extensions.embedding.ActivityStack;
import androidx.window.extensions.embedding.EmbeddingRule;
import androidx.window.extensions.embedding.SplitAttributes;
import androidx.window.extensions.embedding.SplitAttributesCalculatorParams;
import androidx.window.extensions.embedding.SplitInfo;
import androidx.window.extensions.embedding.SplitPairRule;
import androidx.window.extensions.embedding.SplitPlaceholderRule;
import androidx.window.extensions.layout.WindowLayoutInfo;
import androidx.window.layout.WindowMetricsCalculator;
import androidx.window.layout.adapter.extensions.ExtensionsWindowLayoutInfoAdapter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

@Metadata(d1 = {"\u0000ª\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001:\u000267B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\"\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u000e\u001a\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\fJ\u0015\u0010\u000b\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0000¢\u0006\u0002\b\u0015J\u0010\u0010\u000b\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0007J\u0010\u0010\u000b\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J\u001a\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00190\u001c2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001cJ\u001c\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!2\n\u0010\"\u001a\u0006\u0012\u0002\b\u00030#H\u0002J\u000e\u0010$\u001a\u00020\n2\u0006\u0010%\u001a\u00020&J\u000e\u0010'\u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00020\u0012J&\u0010(\u001a\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00140)2\u0012\u0010*\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00120+J$\u0010,\u001a\u00020-2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010 \u001a\u00020.2\n\u0010\"\u001a\u0006\u0012\u0002\b\u00030#H\u0002J$\u0010/\u001a\u0002002\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010 \u001a\u0002012\n\u0010\"\u001a\u0006\u0012\u0002\b\u00030#H\u0002J\u0010\u00102\u001a\u0002032\u0006\u00104\u001a\u000205H\u0002R\u0012\u0010\u0005\u001a\u00060\u0006R\u00020\u0000X\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0007\u001a\u00060\bR\u00020\u0000X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000¨\u00068"}, d2 = {"Landroidx/window/embedding/EmbeddingAdapter;", "", "predicateAdapter", "Landroidx/window/core/PredicateAdapter;", "(Landroidx/window/core/PredicateAdapter;)V", "api1Impl", "Landroidx/window/embedding/EmbeddingAdapter$VendorApiLevel1Impl;", "api2Impl", "Landroidx/window/embedding/EmbeddingAdapter$VendorApiLevel2Impl;", "vendorApiLevel", "", "translate", "", "Landroidx/window/extensions/embedding/EmbeddingRule;", "context", "Landroid/content/Context;", "rules", "Landroidx/window/embedding/EmbeddingRule;", "Landroidx/window/embedding/SplitAttributes;", "splitAttributes", "Landroidx/window/extensions/embedding/SplitAttributes;", "translate$window_release", "Landroidx/window/embedding/SplitAttributesCalculatorParams;", "params", "Landroidx/window/extensions/embedding/SplitAttributesCalculatorParams;", "Landroidx/window/embedding/SplitInfo;", "splitInfo", "Landroidx/window/extensions/embedding/SplitInfo;", "", "splitInfoList", "translateActivityRule", "Landroidx/window/extensions/embedding/ActivityRule;", "rule", "Landroidx/window/embedding/ActivityRule;", "predicateClass", "Ljava/lang/Class;", "translateFinishBehavior", "behavior", "Landroidx/window/embedding/SplitRule$FinishBehavior;", "translateSplitAttributes", "translateSplitAttributesCalculator", "Landroidx/window/extensions/core/util/function/Function;", "calculator", "Lkotlin/Function1;", "translateSplitPairRule", "Landroidx/window/extensions/embedding/SplitPairRule;", "Landroidx/window/embedding/SplitPairRule;", "translateSplitPlaceholderRule", "Landroidx/window/extensions/embedding/SplitPlaceholderRule;", "Landroidx/window/embedding/SplitPlaceholderRule;", "translateSplitType", "Landroidx/window/extensions/embedding/SplitAttributes$SplitType;", "splitType", "Landroidx/window/embedding/SplitAttributes$SplitType;", "VendorApiLevel1Impl", "VendorApiLevel2Impl", "window_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: EmbeddingAdapter.kt */
public final class EmbeddingAdapter {
    private final VendorApiLevel1Impl api1Impl;
    private final VendorApiLevel2Impl api2Impl;
    private final PredicateAdapter predicateAdapter;
    private final int vendorApiLevel = ExtensionsUtil.INSTANCE.getSafeVendorApiLevel();

    public EmbeddingAdapter(PredicateAdapter predicateAdapter2) {
        Intrinsics.checkNotNullParameter(predicateAdapter2, "predicateAdapter");
        this.predicateAdapter = predicateAdapter2;
        this.api1Impl = new VendorApiLevel1Impl(this, predicateAdapter2);
        this.api2Impl = new VendorApiLevel2Impl();
    }

    public final List<SplitInfo> translate(List<? extends SplitInfo> list) {
        Intrinsics.checkNotNullParameter(list, "splitInfoList");
        Iterable<SplitInfo> iterable = list;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (SplitInfo translate : iterable) {
            arrayList.add(translate(translate));
        }
        return (List) arrayList;
    }

    private final SplitInfo translate(SplitInfo splitInfo) {
        int i = this.vendorApiLevel;
        if (i == 1) {
            return this.api1Impl.translateCompat(splitInfo);
        }
        if (i == 2) {
            return this.api2Impl.translateCompat(splitInfo);
        }
        ActivityStack primaryActivityStack = splitInfo.getPrimaryActivityStack();
        Intrinsics.checkNotNullExpressionValue(primaryActivityStack, "splitInfo.primaryActivityStack");
        ActivityStack secondaryActivityStack = splitInfo.getSecondaryActivityStack();
        Intrinsics.checkNotNullExpressionValue(secondaryActivityStack, "splitInfo.secondaryActivityStack");
        List activities = primaryActivityStack.getActivities();
        Intrinsics.checkNotNullExpressionValue(activities, "primaryActivityStack.activities");
        ActivityStack activityStack = new ActivityStack(activities, primaryActivityStack.isEmpty());
        List activities2 = secondaryActivityStack.getActivities();
        Intrinsics.checkNotNullExpressionValue(activities2, "secondaryActivityStack.activities");
        ActivityStack activityStack2 = new ActivityStack(activities2, secondaryActivityStack.isEmpty());
        SplitAttributes splitAttributes = splitInfo.getSplitAttributes();
        Intrinsics.checkNotNullExpressionValue(splitAttributes, "splitInfo.splitAttributes");
        return new SplitInfo(activityStack, activityStack2, translate$window_release(splitAttributes));
    }

    public final SplitAttributes translate$window_release(SplitAttributes splitAttributes) {
        SplitAttributes.SplitType splitType;
        SplitAttributes.LayoutDirection layoutDirection;
        Intrinsics.checkNotNullParameter(splitAttributes, "splitAttributes");
        SplitAttributes.Builder builder = new SplitAttributes.Builder();
        SplitAttributes.SplitType.RatioSplitType splitType2 = splitAttributes.getSplitType();
        Intrinsics.checkNotNullExpressionValue(splitType2, "splitAttributes.splitType");
        if (splitType2 instanceof SplitAttributes.SplitType.HingeSplitType) {
            splitType = SplitAttributes.SplitType.SPLIT_TYPE_HINGE;
        } else if (splitType2 instanceof SplitAttributes.SplitType.ExpandContainersSplitType) {
            splitType = SplitAttributes.SplitType.SPLIT_TYPE_EXPAND;
        } else if (splitType2 instanceof SplitAttributes.SplitType.RatioSplitType) {
            splitType = SplitAttributes.SplitType.Companion.ratio(splitType2.getRatio());
        } else {
            throw new IllegalArgumentException("Unknown split type: " + splitType2);
        }
        SplitAttributes.Builder splitType3 = builder.setSplitType(splitType);
        int layoutDirection2 = splitAttributes.getLayoutDirection();
        if (layoutDirection2 == 0) {
            layoutDirection = SplitAttributes.LayoutDirection.LEFT_TO_RIGHT;
        } else if (layoutDirection2 == 1) {
            layoutDirection = SplitAttributes.LayoutDirection.RIGHT_TO_LEFT;
        } else if (layoutDirection2 == 3) {
            layoutDirection = SplitAttributes.LayoutDirection.LOCALE;
        } else if (layoutDirection2 == 4) {
            layoutDirection = SplitAttributes.LayoutDirection.TOP_TO_BOTTOM;
        } else if (layoutDirection2 == 5) {
            layoutDirection = SplitAttributes.LayoutDirection.BOTTOM_TO_TOP;
        } else {
            throw new IllegalArgumentException("Unknown layout direction: " + layoutDirection2);
        }
        return splitType3.setLayoutDirection(layoutDirection).build();
    }

    public final Function<SplitAttributesCalculatorParams, androidx.window.extensions.embedding.SplitAttributes> translateSplitAttributesCalculator(Function1<? super SplitAttributesCalculatorParams, SplitAttributes> function1) {
        Intrinsics.checkNotNullParameter(function1, "calculator");
        return new EmbeddingAdapter$$ExternalSyntheticLambda3(this, function1);
    }

    /* access modifiers changed from: private */
    public static final androidx.window.extensions.embedding.SplitAttributes translateSplitAttributesCalculator$lambda$0(EmbeddingAdapter embeddingAdapter, Function1 function1, SplitAttributesCalculatorParams splitAttributesCalculatorParams) {
        Intrinsics.checkNotNullParameter(embeddingAdapter, "this$0");
        Intrinsics.checkNotNullParameter(function1, "$calculator");
        Intrinsics.checkNotNullExpressionValue(splitAttributesCalculatorParams, "oemParams");
        return embeddingAdapter.translateSplitAttributes((SplitAttributes) function1.invoke(embeddingAdapter.translate(splitAttributesCalculatorParams)));
    }

    public final SplitAttributesCalculatorParams translate(SplitAttributesCalculatorParams splitAttributesCalculatorParams) {
        Intrinsics.checkNotNullParameter(splitAttributesCalculatorParams, "params");
        EmbeddingAdapter embeddingAdapter = this;
        WindowMetrics parentWindowMetrics = splitAttributesCalculatorParams.getParentWindowMetrics();
        Intrinsics.checkNotNullExpressionValue(parentWindowMetrics, "params.parentWindowMetrics");
        Configuration parentConfiguration = splitAttributesCalculatorParams.getParentConfiguration();
        Intrinsics.checkNotNullExpressionValue(parentConfiguration, "params.parentConfiguration");
        WindowLayoutInfo parentWindowLayoutInfo = splitAttributesCalculatorParams.getParentWindowLayoutInfo();
        Intrinsics.checkNotNullExpressionValue(parentWindowLayoutInfo, "params.parentWindowLayoutInfo");
        androidx.window.extensions.embedding.SplitAttributes defaultSplitAttributes = splitAttributesCalculatorParams.getDefaultSplitAttributes();
        Intrinsics.checkNotNullExpressionValue(defaultSplitAttributes, "params.defaultSplitAttributes");
        boolean areDefaultConstraintsSatisfied = splitAttributesCalculatorParams.areDefaultConstraintsSatisfied();
        String splitRuleTag = splitAttributesCalculatorParams.getSplitRuleTag();
        androidx.window.layout.WindowMetrics translateWindowMetrics$window_release = WindowMetricsCalculator.Companion.translateWindowMetrics$window_release(parentWindowMetrics);
        return new SplitAttributesCalculatorParams(translateWindowMetrics$window_release, parentConfiguration, ExtensionsWindowLayoutInfoAdapter.INSTANCE.translate$window_release(translateWindowMetrics$window_release, parentWindowLayoutInfo), translate$window_release(defaultSplitAttributes), areDefaultConstraintsSatisfied, splitRuleTag);
    }

    private final SplitPairRule translateSplitPairRule(Context context, SplitPairRule splitPairRule, Class<?> cls) {
        if (this.vendorApiLevel < 2) {
            return this.api1Impl.translateSplitPairRuleCompat(context, splitPairRule, cls);
        }
        EmbeddingAdapter$$ExternalSyntheticLambda4 embeddingAdapter$$ExternalSyntheticLambda4 = new EmbeddingAdapter$$ExternalSyntheticLambda4(splitPairRule);
        EmbeddingAdapter$$ExternalSyntheticLambda5 embeddingAdapter$$ExternalSyntheticLambda5 = new EmbeddingAdapter$$ExternalSyntheticLambda5(splitPairRule);
        EmbeddingAdapter$$ExternalSyntheticLambda6 embeddingAdapter$$ExternalSyntheticLambda6 = new EmbeddingAdapter$$ExternalSyntheticLambda6(splitPairRule, context);
        String tag = splitPairRule.getTag();
        SplitPairRule.Builder shouldClearTop = new SplitPairRule.Builder(embeddingAdapter$$ExternalSyntheticLambda4, embeddingAdapter$$ExternalSyntheticLambda5, embeddingAdapter$$ExternalSyntheticLambda6).setDefaultSplitAttributes(translateSplitAttributes(splitPairRule.getDefaultSplitAttributes())).setFinishPrimaryWithSecondary(translateFinishBehavior(splitPairRule.getFinishPrimaryWithSecondary())).setFinishSecondaryWithPrimary(translateFinishBehavior(splitPairRule.getFinishSecondaryWithPrimary())).setShouldClearTop(splitPairRule.getClearTop());
        Intrinsics.checkNotNullExpressionValue(shouldClearTop, "SplitPairRuleBuilder(\n  …ldClearTop(rule.clearTop)");
        if (tag != null) {
            shouldClearTop.setTag(tag);
        }
        SplitPairRule build = shouldClearTop.build();
        Intrinsics.checkNotNullExpressionValue(build, "builder.build()");
        return build;
    }

    /* access modifiers changed from: private */
    public static final boolean translateSplitPairRule$lambda$3(SplitPairRule splitPairRule, Pair pair) {
        Intrinsics.checkNotNullParameter(splitPairRule, "$rule");
        Iterable<SplitPairFilter> filters = splitPairRule.getFilters();
        if ((filters instanceof Collection) && ((Collection) filters).isEmpty()) {
            return false;
        }
        for (SplitPairFilter matchesActivityPair : filters) {
            Object obj = pair.first;
            Intrinsics.checkNotNullExpressionValue(obj, "activitiesPair.first");
            Object obj2 = pair.second;
            Intrinsics.checkNotNullExpressionValue(obj2, "activitiesPair.second");
            if (matchesActivityPair.matchesActivityPair((Activity) obj, (Activity) obj2)) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static final boolean translateSplitPairRule$lambda$5(SplitPairRule splitPairRule, Pair pair) {
        Intrinsics.checkNotNullParameter(splitPairRule, "$rule");
        Iterable<SplitPairFilter> filters = splitPairRule.getFilters();
        if ((filters instanceof Collection) && ((Collection) filters).isEmpty()) {
            return false;
        }
        for (SplitPairFilter matchesActivityIntentPair : filters) {
            Object obj = pair.first;
            Intrinsics.checkNotNullExpressionValue(obj, "activityIntentPair.first");
            Object obj2 = pair.second;
            Intrinsics.checkNotNullExpressionValue(obj2, "activityIntentPair.second");
            if (matchesActivityIntentPair.matchesActivityIntentPair((Activity) obj, (Intent) obj2)) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static final boolean translateSplitPairRule$lambda$6(SplitPairRule splitPairRule, Context context, WindowMetrics windowMetrics) {
        Intrinsics.checkNotNullParameter(splitPairRule, "$rule");
        Intrinsics.checkNotNullParameter(context, "$context");
        Intrinsics.checkNotNullExpressionValue(windowMetrics, "windowMetrics");
        return splitPairRule.checkParentMetrics$window_release(context, windowMetrics);
    }

    public final androidx.window.extensions.embedding.SplitAttributes translateSplitAttributes(SplitAttributes splitAttributes) {
        int i;
        Intrinsics.checkNotNullParameter(splitAttributes, "splitAttributes");
        if (this.vendorApiLevel >= 2) {
            SplitAttributes.Builder splitType = new SplitAttributes.Builder().setSplitType(translateSplitType(splitAttributes.getSplitType()));
            SplitAttributes.LayoutDirection layoutDirection = splitAttributes.getLayoutDirection();
            if (Intrinsics.areEqual((Object) layoutDirection, (Object) SplitAttributes.LayoutDirection.LOCALE)) {
                i = 3;
            } else if (Intrinsics.areEqual((Object) layoutDirection, (Object) SplitAttributes.LayoutDirection.LEFT_TO_RIGHT)) {
                i = 0;
            } else if (Intrinsics.areEqual((Object) layoutDirection, (Object) SplitAttributes.LayoutDirection.RIGHT_TO_LEFT)) {
                i = 1;
            } else if (Intrinsics.areEqual((Object) layoutDirection, (Object) SplitAttributes.LayoutDirection.TOP_TO_BOTTOM)) {
                i = 4;
            } else if (Intrinsics.areEqual((Object) layoutDirection, (Object) SplitAttributes.LayoutDirection.BOTTOM_TO_TOP)) {
                i = 5;
            } else {
                throw new IllegalArgumentException("Unsupported layoutDirection:" + splitAttributes + ".layoutDirection");
            }
            androidx.window.extensions.embedding.SplitAttributes build = splitType.setLayoutDirection(i).build();
            Intrinsics.checkNotNullExpressionValue(build, "Builder()\n            .s…   )\n            .build()");
            return build;
        }
        throw new IllegalArgumentException("Failed requirement.".toString());
    }

    private final SplitAttributes.SplitType translateSplitType(SplitAttributes.SplitType splitType) {
        if (this.vendorApiLevel < 2) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        } else if (Intrinsics.areEqual((Object) splitType, (Object) SplitAttributes.SplitType.SPLIT_TYPE_HINGE)) {
            return new SplitAttributes.SplitType.HingeSplitType(translateSplitType(SplitAttributes.SplitType.SPLIT_TYPE_EQUAL));
        } else {
            if (Intrinsics.areEqual((Object) splitType, (Object) SplitAttributes.SplitType.SPLIT_TYPE_EXPAND)) {
                return new SplitAttributes.SplitType.ExpandContainersSplitType();
            }
            float value$window_release = splitType.getValue$window_release();
            double d = (double) value$window_release;
            if (d > 0.0d && d < 1.0d) {
                return new SplitAttributes.SplitType.RatioSplitType(value$window_release);
            }
            throw new IllegalArgumentException("Unsupported SplitType: " + splitType + " with value: " + splitType.getValue$window_release());
        }
    }

    private final SplitPlaceholderRule translateSplitPlaceholderRule(Context context, SplitPlaceholderRule splitPlaceholderRule, Class<?> cls) {
        if (this.vendorApiLevel < 2) {
            return this.api1Impl.translateSplitPlaceholderRuleCompat(context, splitPlaceholderRule, cls);
        }
        EmbeddingAdapter$$ExternalSyntheticLambda0 embeddingAdapter$$ExternalSyntheticLambda0 = new EmbeddingAdapter$$ExternalSyntheticLambda0(splitPlaceholderRule);
        EmbeddingAdapter$$ExternalSyntheticLambda1 embeddingAdapter$$ExternalSyntheticLambda1 = new EmbeddingAdapter$$ExternalSyntheticLambda1(splitPlaceholderRule);
        EmbeddingAdapter$$ExternalSyntheticLambda2 embeddingAdapter$$ExternalSyntheticLambda2 = new EmbeddingAdapter$$ExternalSyntheticLambda2(splitPlaceholderRule, context);
        String tag = splitPlaceholderRule.getTag();
        SplitPlaceholderRule.Builder finishPrimaryWithPlaceholder = new SplitPlaceholderRule.Builder(splitPlaceholderRule.getPlaceholderIntent(), embeddingAdapter$$ExternalSyntheticLambda0, embeddingAdapter$$ExternalSyntheticLambda1, embeddingAdapter$$ExternalSyntheticLambda2).setSticky(splitPlaceholderRule.isSticky()).setDefaultSplitAttributes(translateSplitAttributes(splitPlaceholderRule.getDefaultSplitAttributes())).setFinishPrimaryWithPlaceholder(translateFinishBehavior(splitPlaceholderRule.getFinishPrimaryWithPlaceholder()));
        Intrinsics.checkNotNullExpressionValue(finishPrimaryWithPlaceholder, "SplitPlaceholderRuleBuil…holder)\n                )");
        if (tag != null) {
            finishPrimaryWithPlaceholder.setTag(tag);
        }
        SplitPlaceholderRule build = finishPrimaryWithPlaceholder.build();
        Intrinsics.checkNotNullExpressionValue(build, "builder.build()");
        return build;
    }

    /* access modifiers changed from: private */
    public static final boolean translateSplitPlaceholderRule$lambda$8(SplitPlaceholderRule splitPlaceholderRule, Activity activity) {
        Intrinsics.checkNotNullParameter(splitPlaceholderRule, "$rule");
        Iterable<ActivityFilter> filters = splitPlaceholderRule.getFilters();
        if ((filters instanceof Collection) && ((Collection) filters).isEmpty()) {
            return false;
        }
        for (ActivityFilter matchesActivity : filters) {
            Intrinsics.checkNotNullExpressionValue(activity, "activity");
            if (matchesActivity.matchesActivity(activity)) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static final boolean translateSplitPlaceholderRule$lambda$10(SplitPlaceholderRule splitPlaceholderRule, Intent intent) {
        Intrinsics.checkNotNullParameter(splitPlaceholderRule, "$rule");
        Iterable<ActivityFilter> filters = splitPlaceholderRule.getFilters();
        if ((filters instanceof Collection) && ((Collection) filters).isEmpty()) {
            return false;
        }
        for (ActivityFilter matchesIntent : filters) {
            Intrinsics.checkNotNullExpressionValue(intent, "intent");
            if (matchesIntent.matchesIntent(intent)) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static final boolean translateSplitPlaceholderRule$lambda$11(SplitPlaceholderRule splitPlaceholderRule, Context context, WindowMetrics windowMetrics) {
        Intrinsics.checkNotNullParameter(splitPlaceholderRule, "$rule");
        Intrinsics.checkNotNullParameter(context, "$context");
        Intrinsics.checkNotNullExpressionValue(windowMetrics, "windowMetrics");
        return splitPlaceholderRule.checkParentMetrics$window_release(context, windowMetrics);
    }

    public final int translateFinishBehavior(SplitRule.FinishBehavior finishBehavior) {
        Intrinsics.checkNotNullParameter(finishBehavior, "behavior");
        if (Intrinsics.areEqual((Object) finishBehavior, (Object) SplitRule.FinishBehavior.NEVER)) {
            return 0;
        }
        if (Intrinsics.areEqual((Object) finishBehavior, (Object) SplitRule.FinishBehavior.ALWAYS)) {
            return 1;
        }
        if (Intrinsics.areEqual((Object) finishBehavior, (Object) SplitRule.FinishBehavior.ADJACENT)) {
            return 2;
        }
        throw new IllegalArgumentException("Unknown finish behavior:" + finishBehavior);
    }

    private final ActivityRule translateActivityRule(ActivityRule activityRule, Class<?> cls) {
        if (this.vendorApiLevel < 2) {
            return this.api1Impl.translateActivityRuleCompat(activityRule, cls);
        }
        ActivityRule.Builder shouldAlwaysExpand = new ActivityRule.Builder(new EmbeddingAdapter$$ExternalSyntheticLambda7(activityRule), new EmbeddingAdapter$$ExternalSyntheticLambda8(activityRule)).setShouldAlwaysExpand(activityRule.getAlwaysExpand());
        Intrinsics.checkNotNullExpressionValue(shouldAlwaysExpand, "ActivityRuleBuilder(acti…Expand(rule.alwaysExpand)");
        String tag = activityRule.getTag();
        if (tag != null) {
            shouldAlwaysExpand.setTag(tag);
        }
        ActivityRule build = shouldAlwaysExpand.build();
        Intrinsics.checkNotNullExpressionValue(build, "builder.build()");
        return build;
    }

    /* access modifiers changed from: private */
    public static final boolean translateActivityRule$lambda$13(ActivityRule activityRule, Activity activity) {
        Intrinsics.checkNotNullParameter(activityRule, "$rule");
        Iterable<ActivityFilter> filters = activityRule.getFilters();
        if ((filters instanceof Collection) && ((Collection) filters).isEmpty()) {
            return false;
        }
        for (ActivityFilter matchesActivity : filters) {
            Intrinsics.checkNotNullExpressionValue(activity, "activity");
            if (matchesActivity.matchesActivity(activity)) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static final boolean translateActivityRule$lambda$15(ActivityRule activityRule, Intent intent) {
        Intrinsics.checkNotNullParameter(activityRule, "$rule");
        Iterable<ActivityFilter> filters = activityRule.getFilters();
        if ((filters instanceof Collection) && ((Collection) filters).isEmpty()) {
            return false;
        }
        for (ActivityFilter matchesIntent : filters) {
            Intrinsics.checkNotNullExpressionValue(intent, "intent");
            if (matchesIntent.matchesIntent(intent)) {
                return true;
            }
        }
        return false;
    }

    public final Set<EmbeddingRule> translate(Context context, Set<? extends EmbeddingRule> set) {
        SplitPairRule splitPairRule;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(set, "rules");
        Class<?> predicateClassOrNull$window_release = this.predicateAdapter.predicateClassOrNull$window_release();
        if (predicateClassOrNull$window_release == null) {
            return SetsKt.emptySet();
        }
        Iterable<EmbeddingRule> iterable = set;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (EmbeddingRule embeddingRule : iterable) {
            if (embeddingRule instanceof SplitPairRule) {
                splitPairRule = translateSplitPairRule(context, (SplitPairRule) embeddingRule, predicateClassOrNull$window_release);
            } else if (embeddingRule instanceof SplitPlaceholderRule) {
                splitPairRule = translateSplitPlaceholderRule(context, (SplitPlaceholderRule) embeddingRule, predicateClassOrNull$window_release);
            } else if (embeddingRule instanceof ActivityRule) {
                splitPairRule = translateActivityRule((ActivityRule) embeddingRule, predicateClassOrNull$window_release);
            } else {
                throw new IllegalArgumentException("Unsupported rule type");
            }
            arrayList.add((EmbeddingRule) splitPairRule);
        }
        return CollectionsKt.toSet((List) arrayList);
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Landroidx/window/embedding/EmbeddingAdapter$VendorApiLevel2Impl;", "", "(Landroidx/window/embedding/EmbeddingAdapter;)V", "translateCompat", "Landroidx/window/embedding/SplitInfo;", "splitInfo", "Landroidx/window/extensions/embedding/SplitInfo;", "window_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: EmbeddingAdapter.kt */
    private final class VendorApiLevel2Impl {
        public VendorApiLevel2Impl() {
        }

        public final SplitInfo translateCompat(SplitInfo splitInfo) {
            Intrinsics.checkNotNullParameter(splitInfo, "splitInfo");
            ActivityStack primaryActivityStack = splitInfo.getPrimaryActivityStack();
            Intrinsics.checkNotNullExpressionValue(primaryActivityStack, "splitInfo.primaryActivityStack");
            List activities = primaryActivityStack.getActivities();
            Intrinsics.checkNotNullExpressionValue(activities, "primaryActivityStack.activities");
            ActivityStack activityStack = new ActivityStack(activities, primaryActivityStack.isEmpty());
            ActivityStack secondaryActivityStack = splitInfo.getSecondaryActivityStack();
            Intrinsics.checkNotNullExpressionValue(secondaryActivityStack, "splitInfo.secondaryActivityStack");
            List activities2 = secondaryActivityStack.getActivities();
            Intrinsics.checkNotNullExpressionValue(activities2, "secondaryActivityStack.activities");
            ActivityStack activityStack2 = new ActivityStack(activities2, secondaryActivityStack.isEmpty());
            EmbeddingAdapter embeddingAdapter = EmbeddingAdapter.this;
            androidx.window.extensions.embedding.SplitAttributes splitAttributes = splitInfo.getSplitAttributes();
            Intrinsics.checkNotNullExpressionValue(splitAttributes, "splitInfo.splitAttributes");
            return new SplitInfo(activityStack, activityStack2, embeddingAdapter.translate$window_release(splitAttributes));
        }
    }

    @Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bH\u0002J\u0016\u0010\u000e\u001a\u00020\u00012\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0003J\u0016\u0010\u0012\u001a\u00020\u00012\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0003J\u0016\u0010\u0013\u001a\u00020\u00012\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\u0010H\u0003J\u001a\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\n\u0010\u001a\u001a\u0006\u0012\u0002\b\u00030\u001bJ\u000e\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\t\u001a\u00020\nJ\u0016\u0010\u001e\u001a\u00020\u00012\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\u0010H\u0003J\u0018\u0010\u001f\u001a\u00020\u00012\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#H\u0003J\u001c\u0010$\u001a\u000e\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020'0%2\u0006\u0010\r\u001a\u00020\bH\u0002J\"\u0010(\u001a\u00020)2\u0006\u0010 \u001a\u00020!2\u0006\u0010\u0018\u001a\u00020*2\n\u0010\u001a\u001a\u0006\u0012\u0002\b\u00030\u001bJ\"\u0010+\u001a\u00020,2\u0006\u0010 \u001a\u00020!2\u0006\u0010\u0018\u001a\u00020-2\n\u0010\u001a\u001a\u0006\u0012\u0002\b\u00030\u001bJ\u0014\u0010.\u001a\u00020/*\u00020/2\u0006\u00100\u001a\u00020\bH\u0002J\u0014\u0010.\u001a\u000201*\u0002012\u0006\u00100\u001a\u00020\bH\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u00062"}, d2 = {"Landroidx/window/embedding/EmbeddingAdapter$VendorApiLevel1Impl;", "", "predicateAdapter", "Landroidx/window/core/PredicateAdapter;", "(Landroidx/window/embedding/EmbeddingAdapter;Landroidx/window/core/PredicateAdapter;)V", "getPredicateAdapter", "()Landroidx/window/core/PredicateAdapter;", "getSplitAttributesCompat", "Landroidx/window/embedding/SplitAttributes;", "splitInfo", "Landroidx/window/extensions/embedding/SplitInfo;", "isSplitAttributesSupported", "", "attrs", "translateActivityIntentPredicates", "splitPairFilters", "", "Landroidx/window/embedding/SplitPairFilter;", "translateActivityPairPredicates", "translateActivityPredicates", "activityFilters", "Landroidx/window/embedding/ActivityFilter;", "translateActivityRuleCompat", "Landroidx/window/extensions/embedding/ActivityRule;", "rule", "Landroidx/window/embedding/ActivityRule;", "predicateClass", "Ljava/lang/Class;", "translateCompat", "Landroidx/window/embedding/SplitInfo;", "translateIntentPredicates", "translateParentMetricsPredicate", "context", "Landroid/content/Context;", "splitRule", "Landroidx/window/embedding/SplitRule;", "translateSplitAttributesCompatInternal", "Lkotlin/Pair;", "", "", "translateSplitPairRuleCompat", "Landroidx/window/extensions/embedding/SplitPairRule;", "Landroidx/window/embedding/SplitPairRule;", "translateSplitPlaceholderRuleCompat", "Landroidx/window/extensions/embedding/SplitPlaceholderRule;", "Landroidx/window/embedding/SplitPlaceholderRule;", "setDefaultSplitAttributesCompat", "Landroidx/window/extensions/embedding/SplitPairRule$Builder;", "defaultAttrs", "Landroidx/window/extensions/embedding/SplitPlaceholderRule$Builder;", "window_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: EmbeddingAdapter.kt */
    private final class VendorApiLevel1Impl {
        private final PredicateAdapter predicateAdapter;
        final /* synthetic */ EmbeddingAdapter this$0;

        public VendorApiLevel1Impl(EmbeddingAdapter embeddingAdapter, PredicateAdapter predicateAdapter2) {
            Intrinsics.checkNotNullParameter(predicateAdapter2, "predicateAdapter");
            this.this$0 = embeddingAdapter;
            this.predicateAdapter = predicateAdapter2;
        }

        public final PredicateAdapter getPredicateAdapter() {
            return this.predicateAdapter;
        }

        public final SplitAttributes getSplitAttributesCompat(SplitInfo splitInfo) {
            Intrinsics.checkNotNullParameter(splitInfo, "splitInfo");
            return new SplitAttributes.Builder().setSplitType(SplitAttributes.SplitType.Companion.buildSplitTypeFromValue$window_release(splitInfo.getSplitRatio())).setLayoutDirection(SplitAttributes.LayoutDirection.LOCALE).build();
        }

        public final ActivityRule translateActivityRuleCompat(ActivityRule activityRule, Class<?> cls) {
            Intrinsics.checkNotNullParameter(activityRule, "rule");
            Intrinsics.checkNotNullParameter(cls, "predicateClass");
            ActivityRule build = ActivityRule.Builder.class.getConstructor(new Class[]{cls, cls}).newInstance(new Object[]{translateActivityPredicates(activityRule.getFilters()), translateIntentPredicates(activityRule.getFilters())}).setShouldAlwaysExpand(activityRule.getAlwaysExpand()).build();
            Intrinsics.checkNotNullExpressionValue(build, "ActivityRuleBuilder::cla…\n                .build()");
            return build;
        }

        public final SplitPlaceholderRule translateSplitPlaceholderRuleCompat(Context context, SplitPlaceholderRule splitPlaceholderRule, Class<?> cls) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(splitPlaceholderRule, "rule");
            Intrinsics.checkNotNullParameter(cls, "predicateClass");
            SplitPlaceholderRule.Builder finishPrimaryWithSecondary = SplitPlaceholderRule.Builder.class.getConstructor(new Class[]{Intent.class, cls, cls, cls}).newInstance(new Object[]{splitPlaceholderRule.getPlaceholderIntent(), translateActivityPredicates(splitPlaceholderRule.getFilters()), translateIntentPredicates(splitPlaceholderRule.getFilters()), translateParentMetricsPredicate(context, splitPlaceholderRule)}).setSticky(splitPlaceholderRule.isSticky()).setFinishPrimaryWithSecondary(this.this$0.translateFinishBehavior(splitPlaceholderRule.getFinishPrimaryWithPlaceholder()));
            Intrinsics.checkNotNullExpressionValue(finishPrimaryWithSecondary, "SplitPlaceholderRuleBuil…holder)\n                )");
            SplitPlaceholderRule build = setDefaultSplitAttributesCompat(finishPrimaryWithSecondary, splitPlaceholderRule.getDefaultSplitAttributes()).build();
            Intrinsics.checkNotNullExpressionValue(build, "SplitPlaceholderRuleBuil…\n                .build()");
            return build;
        }

        private final SplitPlaceholderRule.Builder setDefaultSplitAttributesCompat(SplitPlaceholderRule.Builder builder, SplitAttributes splitAttributes) {
            kotlin.Pair<Float, Integer> translateSplitAttributesCompatInternal = translateSplitAttributesCompatInternal(splitAttributes);
            float floatValue = translateSplitAttributesCompatInternal.component1().floatValue();
            int intValue = translateSplitAttributesCompatInternal.component2().intValue();
            builder.setSplitRatio(floatValue);
            builder.setLayoutDirection(intValue);
            return builder;
        }

        public final SplitPairRule translateSplitPairRuleCompat(Context context, SplitPairRule splitPairRule, Class<?> cls) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(splitPairRule, "rule");
            Intrinsics.checkNotNullParameter(cls, "predicateClass");
            SplitPairRule.Builder newInstance = SplitPairRule.Builder.class.getConstructor(new Class[]{cls, cls, cls}).newInstance(new Object[]{translateActivityPairPredicates(splitPairRule.getFilters()), translateActivityIntentPredicates(splitPairRule.getFilters()), translateParentMetricsPredicate(context, splitPairRule)});
            Intrinsics.checkNotNullExpressionValue(newInstance, "SplitPairRuleBuilder::cl…text, rule)\n            )");
            SplitPairRule build = setDefaultSplitAttributesCompat(newInstance, splitPairRule.getDefaultSplitAttributes()).setShouldClearTop(splitPairRule.getClearTop()).setFinishPrimaryWithSecondary(this.this$0.translateFinishBehavior(splitPairRule.getFinishPrimaryWithSecondary())).setFinishSecondaryWithPrimary(this.this$0.translateFinishBehavior(splitPairRule.getFinishSecondaryWithPrimary())).build();
            Intrinsics.checkNotNullExpressionValue(build, "SplitPairRuleBuilder::cl…                ).build()");
            return build;
        }

        private final Object translateActivityPairPredicates(Set<SplitPairFilter> set) {
            return this.predicateAdapter.buildPairPredicate(Reflection.getOrCreateKotlinClass(Activity.class), Reflection.getOrCreateKotlinClass(Activity.class), new EmbeddingAdapter$VendorApiLevel1Impl$translateActivityPairPredicates$1(set));
        }

        private final Object translateActivityIntentPredicates(Set<SplitPairFilter> set) {
            return this.predicateAdapter.buildPairPredicate(Reflection.getOrCreateKotlinClass(Activity.class), Reflection.getOrCreateKotlinClass(Intent.class), new EmbeddingAdapter$VendorApiLevel1Impl$translateActivityIntentPredicates$1(set));
        }

        private final SplitPairRule.Builder setDefaultSplitAttributesCompat(SplitPairRule.Builder builder, SplitAttributes splitAttributes) {
            kotlin.Pair<Float, Integer> translateSplitAttributesCompatInternal = translateSplitAttributesCompatInternal(splitAttributes);
            float floatValue = translateSplitAttributesCompatInternal.component1().floatValue();
            int intValue = translateSplitAttributesCompatInternal.component2().intValue();
            builder.setSplitRatio(floatValue);
            builder.setLayoutDirection(intValue);
            return builder;
        }

        private final kotlin.Pair<Float, Integer> translateSplitAttributesCompatInternal(SplitAttributes splitAttributes) {
            int i = 3;
            if (!isSplitAttributesSupported(splitAttributes)) {
                return new kotlin.Pair<>(Float.valueOf(0.0f), 3);
            }
            Float valueOf = Float.valueOf(splitAttributes.getSplitType().getValue$window_release());
            SplitAttributes.LayoutDirection layoutDirection = splitAttributes.getLayoutDirection();
            if (!Intrinsics.areEqual((Object) layoutDirection, (Object) SplitAttributes.LayoutDirection.LOCALE)) {
                if (Intrinsics.areEqual((Object) layoutDirection, (Object) SplitAttributes.LayoutDirection.LEFT_TO_RIGHT)) {
                    i = 0;
                } else if (Intrinsics.areEqual((Object) layoutDirection, (Object) SplitAttributes.LayoutDirection.RIGHT_TO_LEFT)) {
                    i = 1;
                } else {
                    throw new IllegalStateException("Unsupported layout direction must be covered in @isSplitAttributesSupported!");
                }
            }
            return new kotlin.Pair<>(valueOf, Integer.valueOf(i));
        }

        private final boolean isSplitAttributesSupported(SplitAttributes splitAttributes) {
            double value$window_release = (double) splitAttributes.getSplitType().getValue$window_release();
            if (0.0d > value$window_release || value$window_release > 1.0d || splitAttributes.getSplitType().getValue$window_release() == 1.0f) {
                return false;
            }
            if (ArraysKt.contains((T[]) new SplitAttributes.LayoutDirection[]{SplitAttributes.LayoutDirection.LEFT_TO_RIGHT, SplitAttributes.LayoutDirection.RIGHT_TO_LEFT, SplitAttributes.LayoutDirection.LOCALE}, splitAttributes.getLayoutDirection())) {
                return true;
            }
            return false;
        }

        private final Object translateActivityPredicates(Set<ActivityFilter> set) {
            return this.predicateAdapter.buildPredicate(Reflection.getOrCreateKotlinClass(Activity.class), new EmbeddingAdapter$VendorApiLevel1Impl$translateActivityPredicates$1(set));
        }

        private final Object translateIntentPredicates(Set<ActivityFilter> set) {
            return this.predicateAdapter.buildPredicate(Reflection.getOrCreateKotlinClass(Intent.class), new EmbeddingAdapter$VendorApiLevel1Impl$translateIntentPredicates$1(set));
        }

        private final Object translateParentMetricsPredicate(Context context, SplitRule splitRule) {
            return this.predicateAdapter.buildPredicate(Reflection.getOrCreateKotlinClass(WindowIdApi18$$ExternalSyntheticApiModelOutline0.m()), new EmbeddingAdapter$VendorApiLevel1Impl$translateParentMetricsPredicate$1(splitRule, context));
        }

        public final SplitInfo translateCompat(SplitInfo splitInfo) {
            Intrinsics.checkNotNullParameter(splitInfo, "splitInfo");
            List activities = splitInfo.getPrimaryActivityStack().getActivities();
            Intrinsics.checkNotNullExpressionValue(activities, "splitInfo.primaryActivityStack.activities");
            ActivityStack activityStack = new ActivityStack(activities, splitInfo.getPrimaryActivityStack().isEmpty());
            List activities2 = splitInfo.getSecondaryActivityStack().getActivities();
            Intrinsics.checkNotNullExpressionValue(activities2, "splitInfo.secondaryActivityStack.activities");
            return new SplitInfo(activityStack, new ActivityStack(activities2, splitInfo.getSecondaryActivityStack().isEmpty()), getSplitAttributesCompat(splitInfo));
        }
    }
}
