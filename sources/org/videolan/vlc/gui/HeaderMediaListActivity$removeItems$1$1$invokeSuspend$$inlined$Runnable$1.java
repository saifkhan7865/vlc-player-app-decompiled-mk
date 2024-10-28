package org.videolan.vlc.gui;

import androidx.lifecycle.LifecycleOwnerKt;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002¨\u0006\u0003"}, d2 = {"<anonymous>", "", "run", "kotlinx/coroutines/RunnableKt$Runnable$1"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: Runnable.kt */
public final class HeaderMediaListActivity$removeItems$1$1$invokeSuspend$$inlined$Runnable$1 implements Runnable {
    final /* synthetic */ MediaWrapper $item$inlined;
    final /* synthetic */ HeaderMediaListActivity this$0;

    public HeaderMediaListActivity$removeItems$1$1$invokeSuspend$$inlined$Runnable$1(HeaderMediaListActivity headerMediaListActivity, MediaWrapper mediaWrapper) {
        this.this$0 = headerMediaListActivity;
        this.$item$inlined = mediaWrapper;
    }

    public final void run() {
        Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this.this$0), (CoroutineContext) null, (CoroutineStart) null, new HeaderMediaListActivity$removeItems$1$1$deleteAction$1$1(this.this$0, this.$item$inlined, (Continuation<? super HeaderMediaListActivity$removeItems$1$1$deleteAction$1$1>) null), 3, (Object) null);
    }
}
