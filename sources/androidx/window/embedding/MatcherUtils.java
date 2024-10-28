package androidx.window.embedding;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import androidx.window.core.ActivityComponentInfo;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001f\u0010\u0007\u001a\u00020\u00042\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\tH\u0000¢\u0006\u0002\b\u000bJ\u001d\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\n\u001a\u00020\tH\u0000¢\u0006\u0002\b\u000fJ\u001d\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\n\u001a\u00020\tH\u0000¢\u0006\u0002\b\u0013J\u001d\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0006H\u0000¢\u0006\u0002\b\u0018J\u0018\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u00062\u0006\u0010\u001b\u001a\u00020\u0006H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Landroidx/window/embedding/MatcherUtils;", "", "()V", "sDebugMatchers", "", "sMatchersTag", "", "areComponentsMatching", "activityComponent", "Landroidx/window/core/ActivityComponentInfo;", "ruleComponent", "areComponentsMatching$window_release", "isActivityMatching", "activity", "Landroid/app/Activity;", "isActivityMatching$window_release", "isIntentMatching", "intent", "Landroid/content/Intent;", "isIntentMatching$window_release", "validateComponentName", "", "packageName", "className", "validateComponentName$window_release", "wildcardMatch", "name", "pattern", "window_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: MatcherUtils.kt */
public final class MatcherUtils {
    public static final MatcherUtils INSTANCE = new MatcherUtils();
    public static final boolean sDebugMatchers = false;
    public static final String sMatchersTag = "SplitRuleResolution";

    private MatcherUtils() {
    }

    public final boolean areComponentsMatching$window_release(ActivityComponentInfo activityComponentInfo, ActivityComponentInfo activityComponentInfo2) {
        Intrinsics.checkNotNullParameter(activityComponentInfo2, "ruleComponent");
        if (activityComponentInfo == null) {
            if (!Intrinsics.areEqual((Object) activityComponentInfo2.getPackageName(), (Object) "*") || !Intrinsics.areEqual((Object) activityComponentInfo2.getClassName(), (Object) "*")) {
                return false;
            }
            return true;
        } else if (!StringsKt.contains$default((CharSequence) activityComponentInfo.toString(), (CharSequence) "*", false, 2, (Object) null)) {
            boolean z = Intrinsics.areEqual((Object) activityComponentInfo.getPackageName(), (Object) activityComponentInfo2.getPackageName()) || wildcardMatch(activityComponentInfo.getPackageName(), activityComponentInfo2.getPackageName());
            boolean z2 = Intrinsics.areEqual((Object) activityComponentInfo.getClassName(), (Object) activityComponentInfo2.getClassName()) || wildcardMatch(activityComponentInfo.getClassName(), activityComponentInfo2.getClassName());
            if (!z || !z2) {
                return false;
            }
            return true;
        } else {
            throw new IllegalArgumentException("Wildcard can only be part of the rule.".toString());
        }
    }

    public final boolean isActivityMatching$window_release(Activity activity, ActivityComponentInfo activityComponentInfo) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(activityComponentInfo, "ruleComponent");
        ComponentName componentName = activity.getComponentName();
        Intrinsics.checkNotNullExpressionValue(componentName, "activity.componentName");
        if (areComponentsMatching$window_release(new ActivityComponentInfo(componentName), activityComponentInfo)) {
            return true;
        }
        Intent intent = activity.getIntent();
        if (intent != null) {
            return INSTANCE.isIntentMatching$window_release(intent, activityComponentInfo);
        }
        return false;
    }

    public final boolean isIntentMatching$window_release(Intent intent, ActivityComponentInfo activityComponentInfo) {
        String str;
        Intrinsics.checkNotNullParameter(intent, "intent");
        Intrinsics.checkNotNullParameter(activityComponentInfo, "ruleComponent");
        ComponentName component = intent.getComponent();
        if (areComponentsMatching$window_release(component != null ? new ActivityComponentInfo(component) : null, activityComponentInfo)) {
            return true;
        }
        if (intent.getComponent() != null || (str = intent.getPackage()) == null) {
            return false;
        }
        if ((Intrinsics.areEqual((Object) str, (Object) activityComponentInfo.getPackageName()) || wildcardMatch(str, activityComponentInfo.getPackageName())) && Intrinsics.areEqual((Object) activityComponentInfo.getClassName(), (Object) "*")) {
            return true;
        }
        return false;
    }

    private final boolean wildcardMatch(String str, String str2) {
        CharSequence charSequence = str2;
        if (!StringsKt.contains$default(charSequence, (CharSequence) "*", false, 2, (Object) null)) {
            return false;
        }
        if (Intrinsics.areEqual((Object) str2, (Object) "*")) {
            return true;
        }
        CharSequence charSequence2 = charSequence;
        if (StringsKt.indexOf$default(charSequence2, "*", 0, false, 6, (Object) null) != StringsKt.lastIndexOf$default(charSequence2, "*", 0, false, 6, (Object) null) || !StringsKt.endsWith$default(str2, "*", false, 2, (Object) null)) {
            throw new IllegalArgumentException("Name pattern with a wildcard must only contain a single wildcard in the end".toString());
        }
        String substring = str2.substring(0, str2.length() - 1);
        Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
        return StringsKt.startsWith$default(str, substring, false, 2, (Object) null);
    }

    public final void validateComponentName$window_release(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "packageName");
        Intrinsics.checkNotNullParameter(str2, "className");
        CharSequence charSequence = str;
        if (charSequence.length() > 0) {
            CharSequence charSequence2 = str2;
            if (charSequence2.length() <= 0) {
                throw new IllegalArgumentException("Activity class name must not be empty".toString());
            } else if (StringsKt.contains$default(charSequence, (CharSequence) "*", false, 2, (Object) null) && StringsKt.indexOf$default(charSequence, "*", 0, false, 6, (Object) null) != str.length() - 1) {
                throw new IllegalArgumentException("Wildcard in package name is only allowed at the end.".toString());
            } else if (StringsKt.contains$default(charSequence2, (CharSequence) "*", false, 2, (Object) null) && StringsKt.indexOf$default(charSequence2, "*", 0, false, 6, (Object) null) != str2.length() - 1) {
                throw new IllegalArgumentException("Wildcard in class name is only allowed at the end.".toString());
            }
        } else {
            throw new IllegalArgumentException("Package name must not be empty".toString());
        }
    }
}
