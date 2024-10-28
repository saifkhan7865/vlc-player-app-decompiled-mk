package org.videolan.vlc.gui.video;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.videolan.medialibrary.interfaces.media.VideoGroup;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.vlc.viewmodels.mobile.VideosViewModel;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "item", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "name", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: VideoGridFragment.kt */
final class VideoGridFragment$renameGroup$1 extends Lambda implements Function2<MediaLibraryItem, String, Unit> {
    final /* synthetic */ VideoGridFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    VideoGridFragment$renameGroup$1(VideoGridFragment videoGridFragment) {
        super(2);
        this.this$0 = videoGridFragment;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((MediaLibraryItem) obj, (String) obj2);
        return Unit.INSTANCE;
    }

    public final void invoke(MediaLibraryItem mediaLibraryItem, String str) {
        ActionBar supportActionBar;
        Intrinsics.checkNotNullParameter(mediaLibraryItem, "item");
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        ((VideosViewModel) this.this$0.getViewModel()).renameGroup((VideoGroup) mediaLibraryItem, str);
        FragmentActivity activity = this.this$0.getActivity();
        AppCompatActivity appCompatActivity = activity instanceof AppCompatActivity ? (AppCompatActivity) activity : null;
        if (appCompatActivity != null && (supportActionBar = appCompatActivity.getSupportActionBar()) != null) {
            supportActionBar.setTitle((CharSequence) str);
        }
    }
}
