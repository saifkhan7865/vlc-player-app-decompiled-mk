package org.videolan.vlc.gui.audio;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.RecyclerView;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.vlc.providers.medialibrary.MedialibraryProvider;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.audio.AudioBrowserFragment$setupProvider$4", f = "AudioBrowserFragment.kt", i = {}, l = {487}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: AudioBrowserFragment.kt */
final class AudioBrowserFragment$setupProvider$4 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ int $index;
    final /* synthetic */ MedialibraryProvider<? extends MediaLibraryItem> $provider;
    int label;
    final /* synthetic */ AudioBrowserFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    AudioBrowserFragment$setupProvider$4(MedialibraryProvider<? extends MediaLibraryItem> medialibraryProvider, AudioBrowserFragment audioBrowserFragment, int i, Continuation<? super AudioBrowserFragment$setupProvider$4> continuation) {
        super(2, continuation);
        this.$provider = medialibraryProvider;
        this.this$0 = audioBrowserFragment;
        this.$index = i;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AudioBrowserFragment$setupProvider$4(this.$provider, this.this$0, this.$index, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AudioBrowserFragment$setupProvider$4) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            if (BuildersKt.withContext(Dispatchers.getIO(), new AudioBrowserFragment$setupProvider$4$invokeSuspend$$inlined$waitForML$1((Continuation) null), this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        LiveData<PagedList<? extends MediaLibraryItem>> pagedList = this.$provider.getPagedList();
        LifecycleOwner viewLifecycleOwner = this.this$0.getViewLifecycleOwner();
        final AudioBrowserFragment audioBrowserFragment = this.this$0;
        final int i2 = this.$index;
        pagedList.observe(viewLifecycleOwner, new AudioBrowserFragment$sam$androidx_lifecycle_Observer$0(new Function1<PagedList<? extends MediaLibraryItem>, Unit>() {
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((PagedList<? extends MediaLibraryItem>) (PagedList) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(PagedList<? extends MediaLibraryItem> pagedList) {
                AudioBrowserAdapter audioBrowserAdapter;
                if (!(pagedList == null || (audioBrowserAdapter = (AudioBrowserAdapter) ArraysKt.getOrNull((T[]) audioBrowserFragment.getAdapters$vlc_android_release(), i2)) == null)) {
                    audioBrowserAdapter.submitList(pagedList);
                }
                audioBrowserFragment.updateEmptyView();
                Integer num = (Integer) audioBrowserFragment.restorePositions.get(i2);
                if (num != null) {
                    AudioBrowserFragment audioBrowserFragment = audioBrowserFragment;
                    int i = i2;
                    ((RecyclerView) audioBrowserFragment.lists.get(i)).scrollToPosition(num.intValue());
                    audioBrowserFragment.restorePositions.delete(i);
                }
                AudioBrowserFragment audioBrowserFragment2 = audioBrowserFragment;
                Intrinsics.checkNotNull(pagedList);
                audioBrowserFragment2.setFabPlayShuffleAllVisibility(!pagedList.isEmpty());
            }
        }));
        return Unit.INSTANCE;
    }
}
