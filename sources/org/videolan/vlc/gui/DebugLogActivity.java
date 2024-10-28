package org.videolan.vlc.gui;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import com.google.android.material.snackbar.Snackbar;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.videolan.libvlc.util.AndroidUtil;
import org.videolan.resources.AndroidDevices;
import org.videolan.vlc.ArtworkProvider;
import org.videolan.vlc.DebugLogService;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.helpers.UiTools;
import org.videolan.vlc.util.Permissions;

@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0005\u0018\u0000 )2\u00020\u00012\u00020\u0002:\u0001)B\u0005¢\u0006\u0002\u0010\u0003J\u0012\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0014J\b\u0010\u001d\u001a\u00020\u001aH\u0014J\u0010\u0010\u001e\u001a\u00020\u001a2\u0006\u0010\u001f\u001a\u00020\u000eH\u0016J\u0018\u0010 \u001a\u00020\u001a2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u000eH\u0016J\u0016\u0010$\u001a\u00020\u001a2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0%H\u0016J\b\u0010&\u001a\u00020\u001aH\u0016J\u0010\u0010'\u001a\u00020\u001a2\u0006\u0010(\u001a\u00020\"H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX.¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X.¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX.¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0010X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0005X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0005X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0005X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000¨\u0006*"}, d2 = {"Lorg/videolan/vlc/gui/DebugLogActivity;", "Landroidx/fragment/app/FragmentActivity;", "Lorg/videolan/vlc/DebugLogService$Client$Callback;", "()V", "clearButton", "Landroid/widget/Button;", "clearClickListener", "Landroid/view/View$OnClickListener;", "client", "Lorg/videolan/vlc/DebugLogService$Client;", "copyButton", "copyClickListener", "logAdapter", "Landroid/widget/ArrayAdapter;", "", "logList", "", "logView", "Landroid/widget/ListView;", "saveButton", "saveClickListener", "startButton", "startClickListener", "stopButton", "stopClickListener", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onLog", "msg", "onSaved", "success", "", "path", "onStarted", "", "onStopped", "setOptionsButtonsEnabled", "enabled", "Companion", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: DebugLogActivity.kt */
public final class DebugLogActivity extends FragmentActivity implements DebugLogService.Client.Callback {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String TAG = "VLC/DebugLogActivity";
    private Button clearButton;
    private final View.OnClickListener clearClickListener = new DebugLogActivity$$ExternalSyntheticLambda2(this);
    private DebugLogService.Client client;
    private Button copyButton;
    private final View.OnClickListener copyClickListener = new DebugLogActivity$$ExternalSyntheticLambda4(this);
    private ArrayAdapter<String> logAdapter;
    private List<String> logList = new ArrayList();
    private ListView logView;
    private Button saveButton;
    private final View.OnClickListener saveClickListener = new DebugLogActivity$$ExternalSyntheticLambda3(this);
    private Button startButton;
    private final View.OnClickListener startClickListener = new DebugLogActivity$$ExternalSyntheticLambda0(this);
    private Button stopButton;
    private final View.OnClickListener stopClickListener = new DebugLogActivity$$ExternalSyntheticLambda1(this);

    /* access modifiers changed from: private */
    public static final void startClickListener$lambda$0(DebugLogActivity debugLogActivity, View view) {
        Intrinsics.checkNotNullParameter(debugLogActivity, "this$0");
        Button button = debugLogActivity.startButton;
        DebugLogService.Client client2 = null;
        if (button == null) {
            Intrinsics.throwUninitializedPropertyAccessException("startButton");
            button = null;
        }
        button.setEnabled(false);
        Button button2 = debugLogActivity.stopButton;
        if (button2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("stopButton");
            button2 = null;
        }
        button2.setEnabled(false);
        DebugLogService.Client client3 = debugLogActivity.client;
        if (client3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("client");
        } else {
            client2 = client3;
        }
        client2.start();
    }

    /* access modifiers changed from: private */
    public static final void stopClickListener$lambda$1(DebugLogActivity debugLogActivity, View view) {
        Intrinsics.checkNotNullParameter(debugLogActivity, "this$0");
        Button button = debugLogActivity.startButton;
        DebugLogService.Client client2 = null;
        if (button == null) {
            Intrinsics.throwUninitializedPropertyAccessException("startButton");
            button = null;
        }
        button.setEnabled(false);
        Button button2 = debugLogActivity.stopButton;
        if (button2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("stopButton");
            button2 = null;
        }
        button2.setEnabled(false);
        DebugLogService.Client client3 = debugLogActivity.client;
        if (client3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("client");
        } else {
            client2 = client3;
        }
        client2.stop();
    }

    /* access modifiers changed from: private */
    public static final void clearClickListener$lambda$2(DebugLogActivity debugLogActivity, View view) {
        Intrinsics.checkNotNullParameter(debugLogActivity, "this$0");
        DebugLogService.Client client2 = debugLogActivity.client;
        ArrayAdapter<String> arrayAdapter = null;
        if (client2 != null) {
            if (client2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("client");
                client2 = null;
            }
            client2.clear();
        }
        debugLogActivity.logList.clear();
        ArrayAdapter<String> arrayAdapter2 = debugLogActivity.logAdapter;
        if (arrayAdapter2 != null) {
            if (arrayAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("logAdapter");
            } else {
                arrayAdapter = arrayAdapter2;
            }
            arrayAdapter.notifyDataSetChanged();
        }
        debugLogActivity.setOptionsButtonsEnabled(false);
    }

    /* access modifiers changed from: private */
    public static final void saveClickListener$lambda$4(DebugLogActivity debugLogActivity, View view) {
        Intrinsics.checkNotNullParameter(debugLogActivity, "this$0");
        DebugLogService.Client client2 = null;
        if (!AndroidUtil.isOOrLater || Permissions.canWriteStorage$default(Permissions.INSTANCE, (Context) null, 1, (Object) null)) {
            DebugLogService.Client client3 = debugLogActivity.client;
            if (client3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("client");
            } else {
                client2 = client3;
            }
            client2.save();
            return;
        }
        Permissions.INSTANCE.askWriteStoragePermission(debugLogActivity, false, new DebugLogActivity$$ExternalSyntheticLambda5(debugLogActivity));
    }

    /* access modifiers changed from: private */
    public static final void saveClickListener$lambda$4$lambda$3(DebugLogActivity debugLogActivity) {
        Intrinsics.checkNotNullParameter(debugLogActivity, "this$0");
        DebugLogService.Client client2 = debugLogActivity.client;
        if (client2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("client");
            client2 = null;
        }
        client2.save();
    }

    /* access modifiers changed from: private */
    public static final void copyClickListener$lambda$5(DebugLogActivity debugLogActivity, View view) {
        Intrinsics.checkNotNullParameter(debugLogActivity, "this$0");
        StringBuffer stringBuffer = new StringBuffer();
        for (String append : debugLogActivity.logList) {
            stringBuffer.append(append).append("\n");
        }
        Context applicationContext = debugLogActivity.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
        Object systemService = ContextCompat.getSystemService(applicationContext, ClipboardManager.class);
        Intrinsics.checkNotNull(systemService);
        ((ClipboardManager) systemService).setPrimaryClip(ClipData.newPlainText((CharSequence) null, stringBuffer));
        UiTools.snacker$default(UiTools.INSTANCE, debugLogActivity, R.string.copied_to_clipboard, false, 4, (Object) null);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.debug_log);
        View findViewById = findViewById(R.id.start_log);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        this.startButton = (Button) findViewById;
        View findViewById2 = findViewById(R.id.stop_log);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
        this.stopButton = (Button) findViewById2;
        View findViewById3 = findViewById(R.id.log_list);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(...)");
        this.logView = (ListView) findViewById3;
        View findViewById4 = findViewById(R.id.copy_to_clipboard);
        Intrinsics.checkNotNullExpressionValue(findViewById4, "findViewById(...)");
        this.copyButton = (Button) findViewById4;
        View findViewById5 = findViewById(R.id.clear_log);
        Intrinsics.checkNotNullExpressionValue(findViewById5, "findViewById(...)");
        this.clearButton = (Button) findViewById5;
        View findViewById6 = findViewById(R.id.save_to_file);
        Intrinsics.checkNotNullExpressionValue(findViewById6, "findViewById(...)");
        this.saveButton = (Button) findViewById6;
        this.client = new DebugLogService.Client(this, this);
        Button button = this.startButton;
        Button button2 = null;
        if (button == null) {
            Intrinsics.throwUninitializedPropertyAccessException("startButton");
            button = null;
        }
        button.setEnabled(false);
        Button button3 = this.stopButton;
        if (button3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("stopButton");
            button3 = null;
        }
        button3.setEnabled(false);
        setOptionsButtonsEnabled(false);
        Button button4 = this.startButton;
        if (button4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("startButton");
            button4 = null;
        }
        button4.setOnClickListener(this.startClickListener);
        Button button5 = this.stopButton;
        if (button5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("stopButton");
            button5 = null;
        }
        button5.setOnClickListener(this.stopClickListener);
        Button button6 = this.clearButton;
        if (button6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("clearButton");
            button6 = null;
        }
        button6.setOnClickListener(this.clearClickListener);
        Button button7 = this.saveButton;
        if (button7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("saveButton");
            button7 = null;
        }
        button7.setOnClickListener(this.saveClickListener);
        Button button8 = this.copyButton;
        if (button8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("copyButton");
        } else {
            button2 = button8;
        }
        button2.setOnClickListener(this.copyClickListener);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        DebugLogService.Client client2 = this.client;
        if (client2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("client");
            client2 = null;
        }
        client2.release();
        super.onDestroy();
    }

    private final void setOptionsButtonsEnabled(boolean z) {
        Button button = this.clearButton;
        Button button2 = null;
        if (button == null) {
            Intrinsics.throwUninitializedPropertyAccessException("clearButton");
            button = null;
        }
        button.setEnabled(z);
        Button button3 = this.copyButton;
        if (button3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("copyButton");
            button3 = null;
        }
        button3.setEnabled(z);
        Button button4 = this.saveButton;
        if (button4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("saveButton");
        } else {
            button2 = button4;
        }
        button2.setEnabled(z);
    }

    public void onStarted(List<String> list) {
        Intrinsics.checkNotNullParameter(list, "logList");
        Button button = this.startButton;
        ListView listView = null;
        if (button == null) {
            Intrinsics.throwUninitializedPropertyAccessException("startButton");
            button = null;
        }
        button.setEnabled(false);
        Button button2 = this.stopButton;
        if (button2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("stopButton");
            button2 = null;
        }
        button2.setEnabled(true);
        Collection collection = list;
        if (!collection.isEmpty()) {
            setOptionsButtonsEnabled(true);
        }
        this.logList = new ArrayList(collection);
        this.logAdapter = new ArrayAdapter<>(this, R.layout.debug_log_item, this.logList);
        ListView listView2 = this.logView;
        if (listView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("logView");
            listView2 = null;
        }
        ArrayAdapter<String> arrayAdapter = this.logAdapter;
        if (arrayAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("logAdapter");
            arrayAdapter = null;
        }
        listView2.setAdapter(arrayAdapter);
        ListView listView3 = this.logView;
        if (listView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("logView");
            listView3 = null;
        }
        listView3.setTranscriptMode(1);
        if (this.logList.size() > 0) {
            ListView listView4 = this.logView;
            if (listView4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("logView");
            } else {
                listView = listView4;
            }
            listView.setSelection(this.logList.size() - 1);
        }
    }

    public void onStopped() {
        Button button = this.startButton;
        Button button2 = null;
        if (button == null) {
            Intrinsics.throwUninitializedPropertyAccessException("startButton");
            button = null;
        }
        button.setEnabled(true);
        Button button3 = this.stopButton;
        if (button3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("stopButton");
        } else {
            button2 = button3;
        }
        button2.setEnabled(false);
    }

    public void onLog(String str) {
        Intrinsics.checkNotNullParameter(str, NotificationCompat.CATEGORY_MESSAGE);
        this.logList.add(str);
        ArrayAdapter<String> arrayAdapter = this.logAdapter;
        if (arrayAdapter != null) {
            if (arrayAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("logAdapter");
                arrayAdapter = null;
            }
            arrayAdapter.notifyDataSetChanged();
        }
        setOptionsButtonsEnabled(true);
    }

    public void onSaved(boolean z, String str) {
        Intrinsics.checkNotNullParameter(str, ArtworkProvider.PATH);
        if (!z) {
            UiTools uiTools = UiTools.INSTANCE;
            View findViewById = getWindow().getDecorView().findViewById(16908290);
            Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
            UiTools.snacker$default(uiTools, (Activity) findViewById, R.string.dump_logcat_failure, false, 4, (Object) null);
        } else if (AndroidDevices.INSTANCE.isAndroidTv()) {
            ListView listView = this.logView;
            if (listView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("logView");
                listView = null;
            }
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String string = getString(R.string.dump_logcat_success);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            String format = String.format(string, Arrays.copyOf(new Object[]{str}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(...)");
            Snackbar.make((View) listView, (CharSequence) format, 0).show();
        } else {
            UiTools uiTools2 = UiTools.INSTANCE;
            StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
            String string2 = getString(R.string.dump_logcat_success);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
            String format2 = String.format(string2, Arrays.copyOf(new Object[]{str}, 1));
            Intrinsics.checkNotNullExpressionValue(format2, "format(...)");
            uiTools2.snackerConfirm(this, format2, false, R.string.share, new DebugLogActivity$onSaved$1(this, str));
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lorg/videolan/vlc/gui/DebugLogActivity$Companion;", "", "()V", "TAG", "", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: DebugLogActivity.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
