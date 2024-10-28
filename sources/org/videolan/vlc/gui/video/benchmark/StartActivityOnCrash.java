package org.videolan.vlc.gui.video.benchmark;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Process;
import java.lang.Thread;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\u0018\u0000 \r2\u00020\u0001:\u0001\rB\u000f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lorg/videolan/vlc/gui/video/benchmark/StartActivityOnCrash;", "Ljava/lang/Thread$UncaughtExceptionHandler;", "context", "Landroid/app/Activity;", "(Landroid/app/Activity;)V", "preferences", "Landroid/content/SharedPreferences;", "uncaughtException", "", "thread", "Ljava/lang/Thread;", "throwable", "", "Companion", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: StartActivityOnCrash.kt */
public final class StartActivityOnCrash implements Thread.UncaughtExceptionHandler {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final int MAX_STACK_TRACE_SIZE = 131071;
    private static final String SHARED_PREFERENCE = "org.videolab.vlc.gui.video.benchmark.UNCAUGHT_EXCEPTIONS";
    private static final String SHARED_PREFERENCE_STACK_TRACE = "org.videolab.vlc.gui.video.benchmark.STACK_TRACE";
    private final Activity context;
    private final SharedPreferences preferences;

    public StartActivityOnCrash(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "context");
        this.context = activity;
        SharedPreferences sharedPreferences = activity.getSharedPreferences(SHARED_PREFERENCE, 1);
        Intrinsics.checkNotNullExpressionValue(sharedPreferences, "getSharedPreferences(...)");
        this.preferences = sharedPreferences;
    }

    public void uncaughtException(Thread thread, Throwable th) {
        String str;
        Intrinsics.checkNotNullParameter(thread, "thread");
        Intrinsics.checkNotNullParameter(th, "throwable");
        String message = th.getMessage();
        if (message != null && message.length() > MAX_STACK_TRACE_SIZE) {
            StringBuilder sb = new StringBuilder();
            if (message != null) {
                str = message.substring(0, 131068);
                Intrinsics.checkNotNullExpressionValue(str, "substring(...)");
            } else {
                str = null;
            }
            sb.append(str);
            sb.append("...");
            message = sb.toString();
        }
        SharedPreferences.Editor edit = this.preferences.edit();
        edit.putString(SHARED_PREFERENCE_STACK_TRACE, message);
        edit.commit();
        Process.killProcess(Process.myPid());
        System.exit(10);
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bR\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lorg/videolan/vlc/gui/video/benchmark/StartActivityOnCrash$Companion;", "", "()V", "MAX_STACK_TRACE_SIZE", "", "SHARED_PREFERENCE", "", "SHARED_PREFERENCE_STACK_TRACE", "setUp", "", "context", "Landroid/app/Activity;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: StartActivityOnCrash.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final boolean setUp(Activity activity) {
            Intrinsics.checkNotNullParameter(activity, "context");
            try {
                Thread.setDefaultUncaughtExceptionHandler(new StartActivityOnCrash(activity));
                return true;
            } catch (Exception unused) {
                return false;
            }
        }
    }
}
