package org.videolan.vlc.gui.dialogs;

import android.os.Bundle;
import androidx.core.os.BundleKt;
import androidx.fragment.app.FragmentActivity;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.tools.KotlinExtensionsKt;
import org.videolan.vlc.util.ContextOption;
import org.videolan.vlc.util.FlagSet;

@Metadata(d1 = {"\u00008\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a \u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002\u001a6\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"CTX_FLAGS_KEY", "", "CTX_MEDIA_KEY", "CTX_POSITION_KEY", "CTX_TITLE_KEY", "showContext", "", "activity", "Landroidx/fragment/app/FragmentActivity;", "receiver", "Lorg/videolan/vlc/gui/dialogs/CtxActionReceiver;", "arguments", "Landroid/os/Bundle;", "position", "", "media", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "flags", "Lorg/videolan/vlc/util/FlagSet;", "Lorg/videolan/vlc/util/ContextOption;", "vlc-android_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: ContextSheet.kt */
public final class ContextSheetKt {
    public static final String CTX_FLAGS_KEY = "CTX_FLAGS_KEY";
    public static final String CTX_MEDIA_KEY = "CTX_MEDIA_KEY";
    public static final String CTX_POSITION_KEY = "CTX_POSITION_KEY";
    public static final String CTX_TITLE_KEY = "CTX_TITLE_KEY";

    private static final void showContext(FragmentActivity fragmentActivity, CtxActionReceiver ctxActionReceiver, Bundle bundle) {
        if (KotlinExtensionsKt.isStarted(fragmentActivity)) {
            ContextSheet contextSheet = new ContextSheet();
            contextSheet.setArguments(bundle);
            contextSheet.setReceiver(ctxActionReceiver);
            contextSheet.show(fragmentActivity.getSupportFragmentManager(), "context");
        }
    }

    public static final void showContext(FragmentActivity fragmentActivity, CtxActionReceiver ctxActionReceiver, int i, MediaLibraryItem mediaLibraryItem, FlagSet<ContextOption> flagSet) {
        Bundle bundle;
        Intrinsics.checkNotNullParameter(fragmentActivity, "activity");
        Intrinsics.checkNotNullParameter(ctxActionReceiver, "receiver");
        Intrinsics.checkNotNullParameter(flagSet, "flags");
        if (KotlinExtensionsKt.isStarted(fragmentActivity)) {
            if (mediaLibraryItem != null) {
                bundle = BundleKt.bundleOf(TuplesKt.to(CTX_MEDIA_KEY, mediaLibraryItem), TuplesKt.to(CTX_POSITION_KEY, Integer.valueOf(i)), TuplesKt.to(CTX_FLAGS_KEY, Long.valueOf(flagSet.getCapabilities())));
            } else {
                Pair[] pairArr = new Pair[3];
                String title = mediaLibraryItem != null ? mediaLibraryItem.getTitle() : null;
                if (title == null) {
                    title = "";
                }
                pairArr[0] = TuplesKt.to(CTX_TITLE_KEY, title);
                pairArr[1] = TuplesKt.to(CTX_POSITION_KEY, Integer.valueOf(i));
                pairArr[2] = TuplesKt.to(CTX_FLAGS_KEY, Long.valueOf(flagSet.getCapabilities()));
                bundle = BundleKt.bundleOf(pairArr);
            }
            showContext(fragmentActivity, ctxActionReceiver, bundle);
        }
    }
}
