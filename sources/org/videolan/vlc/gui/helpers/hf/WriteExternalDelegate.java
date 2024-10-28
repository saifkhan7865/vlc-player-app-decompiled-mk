package org.videolan.vlc.gui.helpers.hf;

import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.UriPermission;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.appcompat.app.AlertDialog;
import androidx.documentfile.provider.DocumentFile;
import androidx.fragment.app.FragmentActivity;
import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import org.videolan.libvlc.util.AndroidUtil;
import org.videolan.resources.AndroidDevices;
import org.videolan.resources.Constants;
import org.videolan.tools.AppScope;
import org.videolan.tools.AppUtils$$ExternalSyntheticApiModelOutline0;
import org.videolan.tools.Settings;
import org.videolan.tools.SettingsKt;
import org.videolan.vlc.R;
import org.videolan.vlc.util.FileUtils;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\u0005¢\u0006\u0002\u0010\u0002J\"\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0017J\u0012\u0010\f\u001a\u00020\u00062\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0017J\b\u0010\u000f\u001a\u00020\u0006H\u0003J\b\u0010\u0010\u001a\u00020\u0006H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lorg/videolan/vlc/gui/helpers/hf/WriteExternalDelegate;", "Lorg/videolan/vlc/gui/helpers/hf/BaseHeadlessFragment;", "()V", "storage", "", "onActivityResult", "", "requestCode", "", "resultCode", "data", "Landroid/content/Intent;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "showDialog", "showHelpDialog", "Companion", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: WriteExternalDelegate.kt */
public final class WriteExternalDelegate extends BaseHeadlessFragment {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String KEY_STORAGE_PATH = "VLC/storage_path";
    private static final int REQUEST_CODE_STORAGE_ACCESS = 42;
    public static final String TAG = "VLC/WriteExternal";
    private String storage;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        showDialog();
    }

    private final void showDialog() {
        if (isAdded() && !isDetached()) {
            new AlertDialog.Builder(requireActivity()).setMessage(R.string.sdcard_permission_dialog_message).setTitle(R.string.sdcard_permission_dialog_title).setPositiveButton(R.string.ok, (DialogInterface.OnClickListener) new WriteExternalDelegate$$ExternalSyntheticLambda5(this)).setNeutralButton((CharSequence) getString(R.string.dialog_sd_wizard), (DialogInterface.OnClickListener) new WriteExternalDelegate$$ExternalSyntheticLambda6(this)).create().show();
        }
    }

    /* access modifiers changed from: private */
    public static final void showDialog$lambda$1(WriteExternalDelegate writeExternalDelegate, DialogInterface dialogInterface, int i) {
        String str;
        Intrinsics.checkNotNullParameter(writeExternalDelegate, "this$0");
        if (writeExternalDelegate.isAdded() && !writeExternalDelegate.isDetached()) {
            Intent intent = new Intent("android.intent.action.OPEN_DOCUMENT_TREE");
            Bundle arguments = writeExternalDelegate.getArguments();
            if (arguments == null || (str = arguments.getString(KEY_STORAGE_PATH)) == null) {
                str = null;
            } else {
                intent.putExtra("android.provider.extra.INITIAL_URI", Uri.parse(str));
            }
            writeExternalDelegate.storage = str;
            writeExternalDelegate.startActivityForResult(intent, 42);
        }
    }

    /* access modifiers changed from: private */
    public static final void showDialog$lambda$2(WriteExternalDelegate writeExternalDelegate, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(writeExternalDelegate, "this$0");
        writeExternalDelegate.showHelpDialog();
    }

    private final void showHelpDialog() {
        FragmentActivity activity;
        if (isAdded() && (activity = getActivity()) != null) {
            LayoutInflater layoutInflater = activity.getLayoutInflater();
            Intrinsics.checkNotNullExpressionValue(layoutInflater, "getLayoutInflater(...)");
            new AlertDialog.Builder(activity).setView(layoutInflater.inflate(R.layout.dialog_sd_write, (ViewGroup) null)).setOnDismissListener(new WriteExternalDelegate$$ExternalSyntheticLambda7(this)).create().show();
        }
    }

    /* access modifiers changed from: private */
    public static final void showHelpDialog$lambda$4$lambda$3(WriteExternalDelegate writeExternalDelegate, DialogInterface dialogInterface) {
        Intrinsics.checkNotNullParameter(writeExternalDelegate, "this$0");
        writeExternalDelegate.showDialog();
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        Uri data;
        super.onActivityResult(i, i2, intent);
        if (intent != null && i == 42 && i2 == -1) {
            Context context = getContext();
            if (context != null && (data = intent.getData()) != null) {
                String uri = data.toString();
                Intrinsics.checkNotNullExpressionValue(uri, "toString(...)");
                SettingsKt.putSingle((SharedPreferences) Settings.INSTANCE.getInstance(context), "tree_uri_" + this.storage, uri);
                DocumentFile fromTreeUri = DocumentFile.fromTreeUri(context, data);
                ContentResolver contentResolver = context.getContentResolver();
                List<Object> m = contentResolver.getPersistedUriPermissions();
                Intrinsics.checkNotNullExpressionValue(m, "getPersistedUriPermissions(...)");
                for (Object m2 : m) {
                    UriPermission m3 = AppUtils$$ExternalSyntheticApiModelOutline0.m(m2);
                    DocumentFile fromTreeUri2 = DocumentFile.fromTreeUri(context, m3.getUri());
                    String str = null;
                    String name = fromTreeUri != null ? fromTreeUri.getName() : null;
                    if (fromTreeUri2 != null) {
                        str = fromTreeUri2.getName();
                    }
                    if (Intrinsics.areEqual((Object) name, (Object) str)) {
                        contentResolver.releasePersistableUriPermission(m3.getUri(), 3);
                        getModel().getDeferredGrant().complete(false);
                        exit();
                        return;
                    }
                }
                contentResolver.takePersistableUriPermission(data, 3);
                getModel().getDeferredGrant().complete(true);
                exit();
                return;
            }
            return;
        }
        getModel().getDeferredGrant().complete(false);
        exit();
    }

    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\"\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000fJ\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\f\u001a\u00020\rR\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lorg/videolan/vlc/gui/helpers/hf/WriteExternalDelegate$Companion;", "", "()V", "KEY_STORAGE_PATH", "", "REQUEST_CODE_STORAGE_ACCESS", "", "TAG", "askForExtWrite", "", "activity", "Landroidx/fragment/app/FragmentActivity;", "uri", "Landroid/net/Uri;", "cb", "Ljava/lang/Runnable;", "needsWritePermission", "", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: WriteExternalDelegate.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public static /* synthetic */ void askForExtWrite$default(Companion companion, FragmentActivity fragmentActivity, Uri uri, Runnable runnable, int i, Object obj) {
            if ((i & 4) != 0) {
                runnable = null;
            }
            companion.askForExtWrite(fragmentActivity, uri, runnable);
        }

        public final void askForExtWrite(FragmentActivity fragmentActivity, Uri uri, Runnable runnable) {
            Intrinsics.checkNotNullParameter(fragmentActivity, "activity");
            Intrinsics.checkNotNullParameter(uri, Constants.KEY_URI);
            Job unused = BuildersKt__Builders_commonKt.launch$default(AppScope.INSTANCE, (CoroutineContext) null, (CoroutineStart) null, new WriteExternalDelegate$Companion$askForExtWrite$1(fragmentActivity, uri, runnable, (Continuation<? super WriteExternalDelegate$Companion$askForExtWrite$1>) null), 3, (Object) null);
        }

        public final boolean needsWritePermission(Uri uri) {
            Intrinsics.checkNotNullParameter(uri, Constants.KEY_URI);
            String path = uri.getPath();
            if (path == null || !AndroidUtil.isLolliPopOrLater) {
                return false;
            }
            if (!Intrinsics.areEqual((Object) "file", (Object) uri.getScheme()) && uri.getScheme() != null) {
                return false;
            }
            CharSequence charSequence = path;
            if (charSequence.length() <= 0 || !StringsKt.startsWith$default(charSequence, '/', false, 2, (Object) null) || StringsKt.startsWith$default(path, AndroidDevices.INSTANCE.getEXTERNAL_PUBLIC_DIRECTORY(), false, 2, (Object) null)) {
                return false;
            }
            DocumentFile findFile = FileUtils.INSTANCE.findFile(uri);
            if (findFile == null || !findFile.canWrite()) {
                return true;
            }
            return false;
        }
    }
}
