package org.videolan.vlc.gui.browser;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.vlc.R;

@Metadata(d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, d2 = {"org/videolan/vlc/gui/browser/FilePickerActivity$onCreate$2", "Landroidx/activity/OnBackPressedCallback;", "handleOnBackPressed", "", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: FilePickerActivity.kt */
public final class FilePickerActivity$onCreate$2 extends OnBackPressedCallback {
    final /* synthetic */ FilePickerActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    FilePickerActivity$onCreate$2(FilePickerActivity filePickerActivity) {
        super(true);
        this.this$0 = filePickerActivity;
    }

    public void handleOnBackPressed() {
        Fragment findFragmentById = this.this$0.getSupportFragmentManager().findFragmentById(R.id.fragment_placeholder);
        Intrinsics.checkNotNull(findFragmentById, "null cannot be cast to non-null type org.videolan.vlc.gui.browser.FilePickerFragment");
        FilePickerFragment filePickerFragment = (FilePickerFragment) findFragmentById;
        if (filePickerFragment.isRootDirectory()) {
            this.this$0.finish();
        } else {
            filePickerFragment.browseUp();
        }
    }
}
