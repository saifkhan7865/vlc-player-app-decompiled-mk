package org.videolan.vlc.media;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwnerKt;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import org.videolan.medialibrary.media.MediaLibraryItem;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002¨\u0006\u0003"}, d2 = {"<anonymous>", "", "run", "kotlinx/coroutines/RunnableKt$Runnable$1"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: Runnable.kt */
public final class MediaUtils$deleteItem$$inlined$Runnable$1 implements Runnable {
    final /* synthetic */ FragmentActivity $activity$inlined;
    final /* synthetic */ MediaLibraryItem $item$inlined;
    final /* synthetic */ Function1 $onDeleteFailed$inlined;

    public MediaUtils$deleteItem$$inlined$Runnable$1(FragmentActivity fragmentActivity, MediaLibraryItem mediaLibraryItem, Function1 function1) {
        this.$activity$inlined = fragmentActivity;
        this.$item$inlined = mediaLibraryItem;
        this.$onDeleteFailed$inlined = function1;
    }

    public final void run() {
        LifecycleOwnerKt.getLifecycleScope(this.$activity$inlined).launchWhenStarted(new MediaUtils$deleteItem$deletionAction$1$1(this.$item$inlined, this.$onDeleteFailed$inlined, (Continuation<? super MediaUtils$deleteItem$deletionAction$1$1>) null));
    }
}
