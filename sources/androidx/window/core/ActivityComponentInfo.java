package androidx.window.core;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0015\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0002\u0010\bJ\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001H\u0002J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0006H\u0016R\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\n¨\u0006\u0012"}, d2 = {"Landroidx/window/core/ActivityComponentInfo;", "", "componentName", "Landroid/content/ComponentName;", "(Landroid/content/ComponentName;)V", "packageName", "", "className", "(Ljava/lang/String;Ljava/lang/String;)V", "getClassName", "()Ljava/lang/String;", "getPackageName", "equals", "", "other", "hashCode", "", "toString", "window_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: ActivityComponentInfo.kt */
public final class ActivityComponentInfo {
    private final String className;
    private final String packageName;

    public ActivityComponentInfo(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "packageName");
        Intrinsics.checkNotNullParameter(str2, "className");
        this.packageName = str;
        this.className = str2;
    }

    public final String getPackageName() {
        return this.packageName;
    }

    public final String getClassName() {
        return this.className;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ActivityComponentInfo(android.content.ComponentName r3) {
        /*
            r2 = this;
            java.lang.String r0 = "componentName"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            java.lang.String r0 = r3.getPackageName()
            java.lang.String r1 = "componentName.packageName"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            java.lang.String r3 = r3.getClassName()
            java.lang.String r1 = "componentName.className"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r1)
            r2.<init>(r0, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.window.core.ActivityComponentInfo.<init>(android.content.ComponentName):void");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!Intrinsics.areEqual((Object) getClass(), (Object) obj != null ? obj.getClass() : null)) {
            return false;
        }
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type androidx.window.core.ActivityComponentInfo");
        ActivityComponentInfo activityComponentInfo = (ActivityComponentInfo) obj;
        return Intrinsics.areEqual((Object) this.packageName, (Object) activityComponentInfo.packageName) && Intrinsics.areEqual((Object) this.className, (Object) activityComponentInfo.className);
    }

    public int hashCode() {
        return (this.packageName.hashCode() * 31) + this.className.hashCode();
    }

    public String toString() {
        return "ClassInfo { packageName: " + this.packageName + ", className: " + this.className + " }";
    }
}
