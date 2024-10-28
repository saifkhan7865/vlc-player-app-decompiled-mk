package org.videolan.television.ui.browser;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.fragment.app.FragmentActivity;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.resources.util.HeaderProvider;
import org.videolan.television.ui.TvUtil;
import org.videolan.television.viewmodel.MediaBrowserViewModel;
import org.videolan.tools.Settings;
import org.videolan.tools.SettingsKt;
import org.videolan.vlc.providers.medialibrary.MedialibraryProvider;
import org.videolan.vlc.viewmodels.tv.TvBrowserModel;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.television.ui.browser.MediaBrowserTvFragment$onClick$1", f = "MediaBrowserTvFragment.kt", i = {}, l = {107}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: MediaBrowserTvFragment.kt */
final class MediaBrowserTvFragment$onClick$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MediaLibraryItem $item;
    int label;
    final /* synthetic */ MediaBrowserTvFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MediaBrowserTvFragment$onClick$1(MediaBrowserTvFragment mediaBrowserTvFragment, MediaLibraryItem mediaLibraryItem, Continuation<? super MediaBrowserTvFragment$onClick$1> continuation) {
        super(2, continuation);
        this.this$0 = mediaBrowserTvFragment;
        this.$item = mediaLibraryItem;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MediaBrowserTvFragment$onClick$1(this.this$0, this.$item, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MediaBrowserTvFragment$onClick$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            TvBrowserModel viewModel = this.this$0.getViewModel();
            Intrinsics.checkNotNull(viewModel, "null cannot be cast to non-null type org.videolan.television.viewmodel.MediaBrowserViewModel");
            if (((MediaBrowserViewModel) viewModel).getCategory() == 25) {
                Settings settings = Settings.INSTANCE;
                Context requireContext = this.this$0.requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext(...)");
                if (!((SharedPreferences) settings.getInstance(requireContext)).getBoolean(SettingsKt.FORCE_PLAY_ALL_VIDEO, Settings.INSTANCE.getTvUI())) {
                    TvUtil tvUtil = TvUtil.INSTANCE;
                    FragmentActivity requireActivity = this.this$0.requireActivity();
                    Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
                    MediaLibraryItem mediaLibraryItem = this.$item;
                    Intrinsics.checkNotNull(mediaLibraryItem, "null cannot be cast to non-null type org.videolan.medialibrary.interfaces.media.MediaWrapper");
                    tvUtil.playMedia(requireActivity, (MediaWrapper) mediaLibraryItem);
                }
            }
            TvBrowserModel viewModel2 = this.this$0.getViewModel();
            Intrinsics.checkNotNull(viewModel2, "null cannot be cast to non-null type org.videolan.television.viewmodel.MediaBrowserViewModel");
            if (((MediaBrowserViewModel) viewModel2).getCategory() == 24) {
                Settings settings2 = Settings.INSTANCE;
                Context requireContext2 = this.this$0.requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext(...)");
                if (!((SharedPreferences) settings2.getInstance(requireContext2)).getBoolean(SettingsKt.FORCE_PLAY_ALL_AUDIO, Settings.INSTANCE.getTvUI())) {
                    TvUtil tvUtil2 = TvUtil.INSTANCE;
                    FragmentActivity requireActivity2 = this.this$0.requireActivity();
                    Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity(...)");
                    MediaLibraryItem mediaLibraryItem2 = this.$item;
                    Intrinsics.checkNotNull(mediaLibraryItem2, "null cannot be cast to non-null type org.videolan.medialibrary.interfaces.media.MediaWrapper");
                    tvUtil2.playMedia(requireActivity2, (MediaWrapper) mediaLibraryItem2);
                }
            }
            TvUtil tvUtil3 = TvUtil.INSTANCE;
            FragmentActivity requireActivity3 = this.this$0.requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity3, "requireActivity(...)");
            MediaLibraryItem mediaLibraryItem3 = this.$item;
            HeaderProvider provider = this.this$0.getViewModel().getProvider();
            Intrinsics.checkNotNull(provider, "null cannot be cast to non-null type org.videolan.vlc.providers.medialibrary.MedialibraryProvider<out org.videolan.medialibrary.media.MediaLibraryItem>");
            this.label = 1;
            if (tvUtil3.openMediaFromPaged(requireActivity3, mediaLibraryItem3, (MedialibraryProvider) provider, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
