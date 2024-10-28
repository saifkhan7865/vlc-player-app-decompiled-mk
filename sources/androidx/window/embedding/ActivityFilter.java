package androidx.window.embedding;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import androidx.window.core.ActivityComponentInfo;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006B\u0019\b\u0000\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\tJ\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001H\u0002J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\u000e\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u0017J\u000e\u0010\u0018\u001a\u00020\u00112\u0006\u0010\u0019\u001a\u00020\u001aJ\b\u0010\u001b\u001a\u00020\u0005H\u0016R\u0014\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001c"}, d2 = {"Landroidx/window/embedding/ActivityFilter;", "", "componentName", "Landroid/content/ComponentName;", "intentAction", "", "(Landroid/content/ComponentName;Ljava/lang/String;)V", "activityComponentInfo", "Landroidx/window/core/ActivityComponentInfo;", "(Landroidx/window/core/ActivityComponentInfo;Ljava/lang/String;)V", "getActivityComponentInfo$window_release", "()Landroidx/window/core/ActivityComponentInfo;", "getComponentName", "()Landroid/content/ComponentName;", "getIntentAction", "()Ljava/lang/String;", "equals", "", "other", "hashCode", "", "matchesActivity", "activity", "Landroid/app/Activity;", "matchesIntent", "intent", "Landroid/content/Intent;", "toString", "window_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: ActivityFilter.kt */
public final class ActivityFilter {
    private final ActivityComponentInfo activityComponentInfo;
    private final String intentAction;

    public ActivityFilter(ActivityComponentInfo activityComponentInfo2, String str) {
        Intrinsics.checkNotNullParameter(activityComponentInfo2, "activityComponentInfo");
        this.activityComponentInfo = activityComponentInfo2;
        this.intentAction = str;
        MatcherUtils.INSTANCE.validateComponentName$window_release(activityComponentInfo2.getPackageName(), activityComponentInfo2.getClassName());
    }

    public final ActivityComponentInfo getActivityComponentInfo$window_release() {
        return this.activityComponentInfo;
    }

    public final String getIntentAction() {
        return this.intentAction;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public ActivityFilter(ComponentName componentName, String str) {
        this(new ActivityComponentInfo(componentName), str);
        Intrinsics.checkNotNullParameter(componentName, "componentName");
    }

    public final boolean matchesIntent(Intent intent) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        if (!MatcherUtils.INSTANCE.isIntentMatching$window_release(intent, this.activityComponentInfo)) {
            return false;
        }
        String str = this.intentAction;
        if (str == null || Intrinsics.areEqual((Object) str, (Object) intent.getAction())) {
            return true;
        }
        return false;
    }

    public final boolean matchesActivity(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        if (MatcherUtils.INSTANCE.isActivityMatching$window_release(activity, this.activityComponentInfo)) {
            String str = this.intentAction;
            if (str != null) {
                Intent intent = activity.getIntent();
                if (Intrinsics.areEqual((Object) str, (Object) intent != null ? intent.getAction() : null)) {
                    return true;
                }
            }
            return true;
        }
        return false;
    }

    public final ComponentName getComponentName() {
        return new ComponentName(this.activityComponentInfo.getPackageName(), this.activityComponentInfo.getClassName());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ActivityFilter)) {
            return false;
        }
        ActivityFilter activityFilter = (ActivityFilter) obj;
        return Intrinsics.areEqual((Object) this.activityComponentInfo, (Object) activityFilter.activityComponentInfo) && Intrinsics.areEqual((Object) this.intentAction, (Object) activityFilter.intentAction);
    }

    public int hashCode() {
        int hashCode = this.activityComponentInfo.hashCode() * 31;
        String str = this.intentAction;
        return hashCode + (str != null ? str.hashCode() : 0);
    }

    public String toString() {
        return "ActivityFilter(componentName=" + this.activityComponentInfo + ", intentAction=" + this.intentAction + ')';
    }
}
