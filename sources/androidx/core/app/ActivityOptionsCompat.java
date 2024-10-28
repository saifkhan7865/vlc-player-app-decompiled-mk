package androidx.core.app;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import androidx.core.util.Pair;

public class ActivityOptionsCompat {
    public static final String EXTRA_USAGE_TIME_REPORT = "android.activity.usage_time";
    public static final String EXTRA_USAGE_TIME_REPORT_PACKAGES = "android.usage_time_packages";

    public Rect getLaunchBounds() {
        return null;
    }

    public void requestUsageTimeReport(PendingIntent pendingIntent) {
    }

    public ActivityOptionsCompat setLaunchBounds(Rect rect) {
        return this;
    }

    public Bundle toBundle() {
        return null;
    }

    public void update(ActivityOptionsCompat activityOptionsCompat) {
    }

    public static ActivityOptionsCompat makeCustomAnimation(Context context, int i, int i2) {
        return new ActivityOptionsCompatImpl(Api16Impl.makeCustomAnimation(context, i, i2));
    }

    public static ActivityOptionsCompat makeScaleUpAnimation(View view, int i, int i2, int i3, int i4) {
        return new ActivityOptionsCompatImpl(Api16Impl.makeScaleUpAnimation(view, i, i2, i3, i4));
    }

    public static ActivityOptionsCompat makeClipRevealAnimation(View view, int i, int i2, int i3, int i4) {
        if (Build.VERSION.SDK_INT >= 23) {
            return new ActivityOptionsCompatImpl(Api23Impl.makeClipRevealAnimation(view, i, i2, i3, i4));
        }
        return new ActivityOptionsCompat();
    }

    public static ActivityOptionsCompat makeThumbnailScaleUpAnimation(View view, Bitmap bitmap, int i, int i2) {
        return new ActivityOptionsCompatImpl(Api16Impl.makeThumbnailScaleUpAnimation(view, bitmap, i, i2));
    }

    public static ActivityOptionsCompat makeSceneTransitionAnimation(Activity activity, View view, String str) {
        if (Build.VERSION.SDK_INT >= 21) {
            return new ActivityOptionsCompatImpl(Api21Impl.makeSceneTransitionAnimation(activity, view, str));
        }
        return new ActivityOptionsCompat();
    }

    public static ActivityOptionsCompat makeSceneTransitionAnimation(Activity activity, Pair<View, String>... pairArr) {
        android.util.Pair[] pairArr2;
        if (Build.VERSION.SDK_INT < 21) {
            return new ActivityOptionsCompat();
        }
        if (pairArr != null) {
            pairArr2 = new android.util.Pair[pairArr.length];
            for (int i = 0; i < pairArr.length; i++) {
                pairArr2[i] = android.util.Pair.create((View) pairArr[i].first, (String) pairArr[i].second);
            }
        } else {
            pairArr2 = null;
        }
        return new ActivityOptionsCompatImpl(Api21Impl.makeSceneTransitionAnimation(activity, pairArr2));
    }

    public static ActivityOptionsCompat makeTaskLaunchBehind() {
        if (Build.VERSION.SDK_INT >= 21) {
            return new ActivityOptionsCompatImpl(Api21Impl.makeTaskLaunchBehind());
        }
        return new ActivityOptionsCompat();
    }

    public static ActivityOptionsCompat makeBasic() {
        if (Build.VERSION.SDK_INT >= 23) {
            return new ActivityOptionsCompatImpl(Api23Impl.makeBasic());
        }
        return new ActivityOptionsCompat();
    }

    private static class ActivityOptionsCompatImpl extends ActivityOptionsCompat {
        private final ActivityOptions mActivityOptions;

        ActivityOptionsCompatImpl(ActivityOptions activityOptions) {
            this.mActivityOptions = activityOptions;
        }

        public Bundle toBundle() {
            return this.mActivityOptions.toBundle();
        }

        public void update(ActivityOptionsCompat activityOptionsCompat) {
            if (activityOptionsCompat instanceof ActivityOptionsCompatImpl) {
                this.mActivityOptions.update(((ActivityOptionsCompatImpl) activityOptionsCompat).mActivityOptions);
            }
        }

        public void requestUsageTimeReport(PendingIntent pendingIntent) {
            if (Build.VERSION.SDK_INT >= 23) {
                Api23Impl.requestUsageTimeReport(this.mActivityOptions, pendingIntent);
            }
        }

        public ActivityOptionsCompat setLaunchBounds(Rect rect) {
            if (Build.VERSION.SDK_INT < 24) {
                return this;
            }
            return new ActivityOptionsCompatImpl(Api24Impl.setLaunchBounds(this.mActivityOptions, rect));
        }

        public Rect getLaunchBounds() {
            if (Build.VERSION.SDK_INT < 24) {
                return null;
            }
            return Api24Impl.getLaunchBounds(this.mActivityOptions);
        }
    }

    protected ActivityOptionsCompat() {
    }

    static class Api16Impl {
        private Api16Impl() {
        }

        static ActivityOptions makeCustomAnimation(Context context, int i, int i2) {
            return ActivityOptions.makeCustomAnimation(context, i, i2);
        }

        static ActivityOptions makeScaleUpAnimation(View view, int i, int i2, int i3, int i4) {
            return ActivityOptions.makeScaleUpAnimation(view, i, i2, i3, i4);
        }

        static ActivityOptions makeThumbnailScaleUpAnimation(View view, Bitmap bitmap, int i, int i2) {
            return ActivityOptions.makeThumbnailScaleUpAnimation(view, bitmap, i, i2);
        }
    }

    static class Api23Impl {
        private Api23Impl() {
        }

        static ActivityOptions makeClipRevealAnimation(View view, int i, int i2, int i3, int i4) {
            return ActivityOptions.makeClipRevealAnimation(view, i, i2, i3, i4);
        }

        static ActivityOptions makeBasic() {
            return ActivityOptions.makeBasic();
        }

        static void requestUsageTimeReport(ActivityOptions activityOptions, PendingIntent pendingIntent) {
            activityOptions.requestUsageTimeReport(pendingIntent);
        }
    }

    static class Api21Impl {
        private Api21Impl() {
        }

        static ActivityOptions makeSceneTransitionAnimation(Activity activity, View view, String str) {
            return ActivityOptions.makeSceneTransitionAnimation(activity, view, str);
        }

        @SafeVarargs
        static ActivityOptions makeSceneTransitionAnimation(Activity activity, android.util.Pair<View, String>... pairArr) {
            return ActivityOptions.makeSceneTransitionAnimation(activity, pairArr);
        }

        static ActivityOptions makeTaskLaunchBehind() {
            return ActivityOptions.makeTaskLaunchBehind();
        }
    }

    static class Api24Impl {
        private Api24Impl() {
        }

        static ActivityOptions setLaunchBounds(ActivityOptions activityOptions, Rect rect) {
            return activityOptions.setLaunchBounds(rect);
        }

        static Rect getLaunchBounds(ActivityOptions activityOptions) {
            return activityOptions.getLaunchBounds();
        }
    }
}
