package org.videolan.television.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ProgressBar;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LifecycleOwnerKt;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import org.videolan.medialibrary.interfaces.Medialibrary;
import org.videolan.television.R;
import org.videolan.television.ui.browser.BaseTvActivity;
import org.videolan.vlc.MediaParsingServiceKt;
import org.videolan.vlc.ScanProgress;
import org.videolan.vlc.StartActivity;
import org.videolan.vlc.gui.helpers.hf.StoragePermissionsDelegate;
import org.videolan.vlc.util.LifecycleAwareScheduler;
import org.videolan.vlc.util.SchedulerCallback;
import org.videolan.vlc.util.Util;

@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0007\u0018\u0000 )2\u00020\u00012\u00020\u00022\u00020\u0003:\u0001)B\u0005¢\u0006\u0002\u0010\u0004J\u0006\u0010\u000f\u001a\u00020\u0010J\"\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00132\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0014J\u0012\u0010\u0017\u001a\u00020\u00102\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0014J\u0018\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00132\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\b\u0010\u001f\u001a\u00020\u0010H\u0014J\u0012\u0010 \u001a\u00020\u00102\b\u0010!\u001a\u0004\u0018\u00010\"H\u0014J\b\u0010#\u001a\u00020\u0010H\u0014J\b\u0010$\u001a\u00020\u0010H\u0016J\u0018\u0010%\u001a\u00020\u00102\u0006\u0010&\u001a\u00020'2\u0006\u0010\u0015\u001a\u00020\u0019H\u0016J\b\u0010(\u001a\u00020\u0010H\u0014R\u000e\u0010\u0005\u001a\u00020\u0006X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX.¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u00020\nX.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006*"}, d2 = {"Lorg/videolan/television/ui/MainTvActivity;", "Lorg/videolan/television/ui/browser/BaseTvActivity;", "Lorg/videolan/vlc/gui/helpers/hf/StoragePermissionsDelegate$CustomActionController;", "Lorg/videolan/vlc/util/SchedulerCallback;", "()V", "browseFragment", "Lorg/videolan/television/ui/MainTvFragment;", "progressBar", "Landroid/widget/ProgressBar;", "scheduler", "Lorg/videolan/vlc/util/LifecycleAwareScheduler;", "getScheduler", "()Lorg/videolan/vlc/util/LifecycleAwareScheduler;", "setScheduler", "(Lorg/videolan/vlc/util/LifecycleAwareScheduler;)V", "hideLoading", "", "onActivityResult", "requestCode", "", "resultCode", "data", "Landroid/content/Intent;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onKeyDown", "", "keyCode", "event", "Landroid/view/KeyEvent;", "onParsingServiceFinished", "onParsingServiceProgress", "scanProgress", "Lorg/videolan/vlc/ScanProgress;", "onParsingServiceStarted", "onStorageAccessGranted", "onTaskTriggered", "id", "", "refresh", "Companion", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MainTvActivity.kt */
public final class MainTvActivity extends BaseTvActivity implements StoragePermissionsDelegate.CustomActionController, SchedulerCallback {
    public static final int ACTIVITY_RESULT_PREFERENCES = 1;
    public static final String BROWSER_TYPE = "browser_type";
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String HIDE_LOADING = "hide_loading";
    private static final String SHOW_LOADING = "show_loading";
    public static final String TAG = "VLC/MainTvActivity";
    private MainTvFragment browseFragment;
    private ProgressBar progressBar;
    public LifecycleAwareScheduler scheduler;

    public void onTaskCancelled(String str) {
        SchedulerCallback.DefaultImpls.onTaskCancelled(this, str);
    }

    public final LifecycleAwareScheduler getScheduler() {
        LifecycleAwareScheduler lifecycleAwareScheduler = this.scheduler;
        if (lifecycleAwareScheduler != null) {
            return lifecycleAwareScheduler;
        }
        Intrinsics.throwUninitializedPropertyAccessException("scheduler");
        return null;
    }

    public final void setScheduler(LifecycleAwareScheduler lifecycleAwareScheduler) {
        Intrinsics.checkNotNullParameter(lifecycleAwareScheduler, "<set-?>");
        this.scheduler = lifecycleAwareScheduler;
    }

    public void onTaskTriggered(String str, Bundle bundle) {
        Intrinsics.checkNotNullParameter(str, "id");
        Intrinsics.checkNotNullParameter(bundle, "data");
        ProgressBar progressBar2 = null;
        if (Intrinsics.areEqual((Object) str, (Object) SHOW_LOADING)) {
            ProgressBar progressBar3 = this.progressBar;
            if (progressBar3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("progressBar");
            } else {
                progressBar2 = progressBar3;
            }
            progressBar2.setVisibility(0);
        } else if (Intrinsics.areEqual((Object) str, (Object) HIDE_LOADING)) {
            getScheduler().cancelAction(SHOW_LOADING);
            ProgressBar progressBar4 = this.progressBar;
            if (progressBar4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("progressBar");
            } else {
                progressBar2 = progressBar4;
            }
            progressBar2.setVisibility(8);
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setScheduler(new LifecycleAwareScheduler(this));
        Util.INSTANCE.checkCpuCompatibility(this);
        setContentView(R.layout.tv_main);
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "getSupportFragmentManager(...)");
        Fragment findFragmentById = supportFragmentManager.findFragmentById(R.id.browse_fragment);
        Intrinsics.checkNotNull(findFragmentById, "null cannot be cast to non-null type org.videolan.television.ui.MainTvFragment");
        this.browseFragment = (MainTvFragment) findFragmentById;
        View findViewById = findViewById(R.id.tv_main_progress);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        this.progressBar = (ProgressBar) findViewById;
        Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), (CoroutineContext) null, (CoroutineStart) null, new MainTvActivity$onCreate$1(this, (Continuation<? super MainTvActivity$onCreate$1>) null), 3, (Object) null);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i != 1) {
            return;
        }
        if (i2 == 2) {
            MediaParsingServiceKt.reloadLibrary(this);
        } else if (i2 == 3 || i2 == 4) {
            Intent intent2 = new Intent(this, i2 == 4 ? StartActivity.class : MainTvActivity.class);
            finish();
            startActivity(intent2);
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        Intrinsics.checkNotNullParameter(keyEvent, NotificationCompat.CATEGORY_EVENT);
        if (i != 85 && i != 100) {
            return super.onKeyDown(i, keyEvent);
        }
        MainTvFragment mainTvFragment = this.browseFragment;
        if (mainTvFragment == null) {
            Intrinsics.throwUninitializedPropertyAccessException("browseFragment");
            mainTvFragment = null;
        }
        return mainTvFragment.showDetails();
    }

    /* access modifiers changed from: protected */
    public void onParsingServiceStarted() {
        LifecycleAwareScheduler.startAction$default(getScheduler(), SHOW_LOADING, (Bundle) null, 2, (Object) null);
    }

    /* access modifiers changed from: protected */
    public void onParsingServiceProgress(ScanProgress scanProgress) {
        ProgressBar progressBar2 = this.progressBar;
        if (progressBar2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("progressBar");
            progressBar2 = null;
        }
        if (progressBar2.getVisibility() == 8 && Medialibrary.getInstance().isWorking()) {
            LifecycleAwareScheduler.startAction$default(getScheduler(), SHOW_LOADING, (Bundle) null, 2, (Object) null);
        }
    }

    /* access modifiers changed from: protected */
    public void onParsingServiceFinished() {
        if (!Medialibrary.getInstance().isWorking()) {
            LifecycleAwareScheduler.scheduleAction$default(getScheduler(), HIDE_LOADING, 500, (Bundle) null, 4, (Object) null);
        }
    }

    public final void hideLoading() {
        LifecycleAwareScheduler.scheduleAction$default(getScheduler(), HIDE_LOADING, 500, (Bundle) null, 4, (Object) null);
    }

    public void onStorageAccessGranted() {
        refresh();
    }

    /* access modifiers changed from: protected */
    public void refresh() {
        MediaParsingServiceKt.reloadLibrary(this);
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lorg/videolan/television/ui/MainTvActivity$Companion;", "", "()V", "ACTIVITY_RESULT_PREFERENCES", "", "BROWSER_TYPE", "", "HIDE_LOADING", "SHOW_LOADING", "TAG", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: MainTvActivity.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
