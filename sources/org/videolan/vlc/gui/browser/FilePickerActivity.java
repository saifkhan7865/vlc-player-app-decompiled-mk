package org.videolan.vlc.gui.browser;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import androidx.core.os.BundleKt;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.jvm.KClassesJvm;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.tools.AppUtils$$ExternalSyntheticApiModelOutline0;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.BaseActivity;
import org.videolan.vlc.gui.video.VideoPlayerActivity;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\u0016¢\u0006\u0002\u0010\u0005J\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0007J\u0012\u0010\r\u001a\u00020\u000b2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016¨\u0006\u0010"}, d2 = {"Lorg/videolan/vlc/gui/browser/FilePickerActivity;", "Lorg/videolan/vlc/gui/BaseActivity;", "()V", "forcedTheme", "", "()Ljava/lang/Integer;", "getSnackAnchorView", "Landroid/view/View;", "overAudioPlayer", "", "onCloseClick", "", "v", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: FilePickerActivity.kt */
public final class FilePickerActivity extends BaseActivity {
    public View getSnackAnchorView(boolean z) {
        return findViewById(16908290);
    }

    public Integer forcedTheme() {
        if ((getResources().getConfiguration().uiMode & 48) != 16) {
            return null;
        }
        ComponentName callingActivity = getCallingActivity();
        if (Intrinsics.areEqual((Object) callingActivity != null ? callingActivity.getClassName() : null, (Object) KClassesJvm.getJvmName(Reflection.getOrCreateKotlinClass(VideoPlayerActivity.class)))) {
            return Integer.valueOf(R.style.Theme_VLC_PickerDialog_Dark);
        }
        return null;
    }

    public void onCreate(Bundle bundle) {
        Parcelable parcelable;
        super.onCreate(bundle);
        setContentView(R.layout.file_picker_activity);
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        Intrinsics.checkNotNullExpressionValue(beginTransaction, "beginTransaction(...)");
        int i = R.id.fragment_placeholder;
        FilePickerFragment filePickerFragment = new FilePickerFragment();
        Pair[] pairArr = new Pair[2];
        Intent intent = getIntent();
        Intrinsics.checkNotNullExpressionValue(intent, "getIntent(...)");
        if (Build.VERSION.SDK_INT >= 33) {
            parcelable = (Parcelable) AppUtils$$ExternalSyntheticApiModelOutline0.m(intent, BaseBrowserFragmentKt.KEY_MEDIA, MediaWrapper.class);
        } else {
            Parcelable parcelableExtra = intent.getParcelableExtra(BaseBrowserFragmentKt.KEY_MEDIA);
            if (!(parcelableExtra instanceof MediaWrapper)) {
                parcelableExtra = null;
            }
            parcelable = (MediaWrapper) parcelableExtra;
        }
        pairArr[0] = TuplesKt.to(BaseBrowserFragmentKt.KEY_MEDIA, parcelable);
        pairArr[1] = TuplesKt.to(BaseBrowserFragmentKt.KEY_PICKER_TYPE, Integer.valueOf(getIntent().getIntExtra(BaseBrowserFragmentKt.KEY_PICKER_TYPE, 0)));
        filePickerFragment.setArguments(BundleKt.bundleOf(pairArr));
        Unit unit = Unit.INSTANCE;
        beginTransaction.replace(i, (Fragment) filePickerFragment, "picker");
        beginTransaction.commit();
        getWindow().getAttributes().gravity = 80;
        getOnBackPressedDispatcher().addCallback(this, new FilePickerActivity$onCreate$2(this));
    }

    public final void onCloseClick(View view) {
        Intrinsics.checkNotNullParameter(view, "v");
        finish();
    }
}
