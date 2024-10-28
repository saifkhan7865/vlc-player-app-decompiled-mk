package androidx.window.embedding;

import android.app.Activity;
import java.util.List;
import kotlin.Metadata;
import kotlin.UInt$$ExternalSyntheticBackport0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u001d\b\u0007\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0011\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u0004H\u0002J\u0013\u0010\r\u001a\u00020\u00062\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001H\u0002J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0016R\u001a\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\n¨\u0006\u0013"}, d2 = {"Landroidx/window/embedding/ActivityStack;", "", "activitiesInProcess", "", "Landroid/app/Activity;", "isEmpty", "", "(Ljava/util/List;Z)V", "getActivitiesInProcess$window_release", "()Ljava/util/List;", "()Z", "contains", "activity", "equals", "other", "hashCode", "", "toString", "", "window_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: ActivityStack.kt */
public final class ActivityStack {
    private final List<Activity> activitiesInProcess;
    private final boolean isEmpty;

    public ActivityStack(List<? extends Activity> list, boolean z) {
        Intrinsics.checkNotNullParameter(list, "activitiesInProcess");
        this.activitiesInProcess = list;
        this.isEmpty = z;
    }

    public final List<Activity> getActivitiesInProcess$window_release() {
        return this.activitiesInProcess;
    }

    public final boolean isEmpty() {
        return this.isEmpty;
    }

    public final boolean contains(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        return this.activitiesInProcess.contains(activity);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ActivityStack)) {
            return false;
        }
        ActivityStack activityStack = (ActivityStack) obj;
        return Intrinsics.areEqual((Object) this.activitiesInProcess, (Object) activityStack.activitiesInProcess) && this.isEmpty == activityStack.isEmpty;
    }

    public int hashCode() {
        return (this.activitiesInProcess.hashCode() * 31) + UInt$$ExternalSyntheticBackport0.m(this.isEmpty);
    }

    public String toString() {
        return "ActivityStack{activitiesInProcess=" + this.activitiesInProcess + ", isEmpty=" + this.isEmpty + AbstractJsonLexerKt.END_OBJ;
    }
}
