package org.videolan.vlc.gui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwnerKt;
import com.google.android.material.snackbar.Snackbar;
import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CompletableJob;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import org.videolan.libvlc.util.AndroidUtil;
import org.videolan.resources.Constants;
import org.videolan.vlc.ArtworkProvider;
import org.videolan.vlc.DebugLogService;
import org.videolan.vlc.R;
import org.videolan.vlc.databinding.SendCrashActivityBinding;
import org.videolan.vlc.util.Permissions;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0003\u0018\u0000 \u001e2\u00020\u00012\u00020\u0002:\u0001\u001eB\u0005¢\u0006\u0002\u0010\u0003J\u0012\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J\b\u0010\u0013\u001a\u00020\u0010H\u0014J\u0010\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\tH\u0016J\u0018\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\tH\u0016J\u0016\u0010\u001a\u001a\u00020\u00102\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\t0\u001cH\u0016J\b\u0010\u001d\u001a\u00020\u0010H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X.¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX.¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX.¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\tX.¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\tX.¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\tX.¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lorg/videolan/vlc/gui/SendCrashActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "Lorg/videolan/vlc/DebugLogService$Client$Callback;", "()V", "binding", "Lorg/videolan/vlc/databinding/SendCrashActivityBinding;", "client", "Lorg/videolan/vlc/DebugLogService$Client;", "dbPath", "", "dbZipPath", "errCtx", "errMsg", "logMessage", "logcatZipPath", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onLog", "msg", "onSaved", "success", "", "path", "onStarted", "logList", "", "onStopped", "Companion", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: SendCrashActivity.kt */
public final class SendCrashActivity extends AppCompatActivity implements DebugLogService.Client.Callback {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static CompletableJob job;
    /* access modifiers changed from: private */
    public SendCrashActivityBinding binding;
    /* access modifiers changed from: private */
    public DebugLogService.Client client;
    /* access modifiers changed from: private */
    public String dbPath;
    /* access modifiers changed from: private */
    public String dbZipPath;
    /* access modifiers changed from: private */
    public String errCtx;
    /* access modifiers changed from: private */
    public String errMsg;
    private String logMessage = "";
    /* access modifiers changed from: private */
    public String logcatZipPath;

    public void onStopped() {
    }

    public void onStarted(List<String> list) {
        Intrinsics.checkNotNullParameter(list, "logList");
        String str = "Starting collecting logs at " + System.currentTimeMillis();
        this.logMessage = str;
        Log.d("SendCrashActivity", str);
    }

    public void onLog(String str) {
        Intrinsics.checkNotNullParameter(str, NotificationCompat.CATEGORY_MESSAGE);
        DebugLogService.Client client2 = null;
        if (!StringsKt.contains$default((CharSequence) str, (CharSequence) this.logMessage, false, 2, (Object) null)) {
            return;
        }
        if (!AndroidUtil.isOOrLater || Permissions.canWriteStorage$default(Permissions.INSTANCE, (Context) null, 1, (Object) null)) {
            DebugLogService.Client client3 = this.client;
            if (client3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("client");
            } else {
                client2 = client3;
            }
            client2.save();
            return;
        }
        Permissions.INSTANCE.askWriteStoragePermission(this, false, new SendCrashActivity$$ExternalSyntheticLambda0(this));
    }

    /* access modifiers changed from: private */
    public static final void onLog$lambda$0(SendCrashActivity sendCrashActivity) {
        Intrinsics.checkNotNullParameter(sendCrashActivity, "this$0");
        DebugLogService.Client client2 = sendCrashActivity.client;
        if (client2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("client");
            client2 = null;
        }
        client2.save();
    }

    public void onSaved(boolean z, String str) {
        Intrinsics.checkNotNullParameter(str, ArtworkProvider.PATH);
        DebugLogService.Client client2 = null;
        if (!z) {
            Snackbar.make(getWindow().getDecorView(), R.string.dump_logcat_failure, 0).show();
            DebugLogService.Client client3 = this.client;
            if (client3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("client");
            } else {
                client2 = client3;
            }
            client2.stop();
            return;
        }
        Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), (CoroutineContext) null, CoroutineStart.UNDISPATCHED, new SendCrashActivity$onSaved$1(this, str, (Continuation<? super SendCrashActivity$onSaved$1>) null), 1, (Object) null);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ViewDataBinding contentView = DataBindingUtil.setContentView(this, R.layout.send_crash_activity);
        Intrinsics.checkNotNullExpressionValue(contentView, "setContentView(...)");
        SendCrashActivityBinding sendCrashActivityBinding = (SendCrashActivityBinding) contentView;
        this.binding = sendCrashActivityBinding;
        SendCrashActivityBinding sendCrashActivityBinding2 = null;
        if (sendCrashActivityBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            sendCrashActivityBinding = null;
        }
        sendCrashActivityBinding.reportBugButton.setOnClickListener(new SendCrashActivity$$ExternalSyntheticLambda1(this));
        SendCrashActivityBinding sendCrashActivityBinding3 = this.binding;
        if (sendCrashActivityBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            sendCrashActivityBinding3 = null;
        }
        sendCrashActivityBinding3.reportCrashButton.setOnClickListener(new SendCrashActivity$$ExternalSyntheticLambda2(this));
        SendCrashActivityBinding sendCrashActivityBinding4 = this.binding;
        if (sendCrashActivityBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            sendCrashActivityBinding4 = null;
        }
        sendCrashActivityBinding4.sendCrashButton.setOnClickListener(new SendCrashActivity$$ExternalSyntheticLambda3(this));
        Bundle extras = getIntent().getExtras();
        String string = extras != null ? extras.getString(Constants.CRASH_ML_MSG) : null;
        if (string != null) {
            this.errMsg = string;
            Bundle extras2 = getIntent().getExtras();
            String string2 = extras2 != null ? extras2.getString(Constants.CRASH_ML_CTX) : null;
            if (string2 != null) {
                this.errCtx = string2;
                SendCrashActivityBinding sendCrashActivityBinding5 = this.binding;
                if (sendCrashActivityBinding5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    sendCrashActivityBinding5 = null;
                }
                sendCrashActivityBinding5.crashFirstStepContainer.setVisibility(8);
                SendCrashActivityBinding sendCrashActivityBinding6 = this.binding;
                if (sendCrashActivityBinding6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    sendCrashActivityBinding6 = null;
                }
                sendCrashActivityBinding6.crashSecondStepContainer.setVisibility(0);
                SendCrashActivityBinding sendCrashActivityBinding7 = this.binding;
                if (sendCrashActivityBinding7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    sendCrashActivityBinding2 = sendCrashActivityBinding7;
                }
                sendCrashActivityBinding2.includeMedialibSwitch.setChecked(true);
                this.client = new DebugLogService.Client(this, this);
            }
        }
    }

    /* access modifiers changed from: private */
    public static final void onCreate$lambda$1(SendCrashActivity sendCrashActivity, View view) {
        Intrinsics.checkNotNullParameter(sendCrashActivity, "this$0");
        sendCrashActivity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://forum.videolan.org/viewforum.php?f=35")));
        sendCrashActivity.finish();
    }

    /* access modifiers changed from: private */
    public static final void onCreate$lambda$2(SendCrashActivity sendCrashActivity, View view) {
        Intrinsics.checkNotNullParameter(sendCrashActivity, "this$0");
        SendCrashActivityBinding sendCrashActivityBinding = sendCrashActivity.binding;
        SendCrashActivityBinding sendCrashActivityBinding2 = null;
        if (sendCrashActivityBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            sendCrashActivityBinding = null;
        }
        sendCrashActivityBinding.crashFirstStepContainer.setVisibility(8);
        SendCrashActivityBinding sendCrashActivityBinding3 = sendCrashActivity.binding;
        if (sendCrashActivityBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            sendCrashActivityBinding2 = sendCrashActivityBinding3;
        }
        sendCrashActivityBinding2.crashSecondStepContainer.setVisibility(0);
        sendCrashActivity.client = new DebugLogService.Client(sendCrashActivity, sendCrashActivity);
    }

    /* access modifiers changed from: private */
    public static final void onCreate$lambda$3(SendCrashActivity sendCrashActivity, View view) {
        Intrinsics.checkNotNullParameter(sendCrashActivity, "this$0");
        DebugLogService.Client client2 = sendCrashActivity.client;
        SendCrashActivityBinding sendCrashActivityBinding = null;
        if (client2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("client");
            client2 = null;
        }
        client2.start();
        SendCrashActivityBinding sendCrashActivityBinding2 = sendCrashActivity.binding;
        if (sendCrashActivityBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            sendCrashActivityBinding2 = null;
        }
        sendCrashActivityBinding2.sendCrashButton.setVisibility(8);
        SendCrashActivityBinding sendCrashActivityBinding3 = sendCrashActivity.binding;
        if (sendCrashActivityBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            sendCrashActivityBinding = sendCrashActivityBinding3;
        }
        sendCrashActivityBinding.sendCrashProgress.setVisibility(0);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        CompletableJob completableJob = job;
        if (completableJob != null) {
            completableJob.complete();
        }
        DebugLogService.Client client2 = null;
        job = null;
        DebugLogService.Client client3 = this.client;
        if (client3 != null) {
            if (client3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("client");
            } else {
                client2 = client3;
            }
            client2.release();
        }
        super.onDestroy();
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lorg/videolan/vlc/gui/SendCrashActivity$Companion;", "", "()V", "job", "Lkotlinx/coroutines/CompletableJob;", "getJob", "()Lkotlinx/coroutines/CompletableJob;", "setJob", "(Lkotlinx/coroutines/CompletableJob;)V", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: SendCrashActivity.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final CompletableJob getJob() {
            return SendCrashActivity.job;
        }

        public final void setJob(CompletableJob completableJob) {
            SendCrashActivity.job = completableJob;
        }
    }
}
