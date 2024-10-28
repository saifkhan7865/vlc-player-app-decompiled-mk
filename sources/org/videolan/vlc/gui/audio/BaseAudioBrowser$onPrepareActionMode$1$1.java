package org.videolan.vlc.gui.audio;

import androidx.appcompat.view.ActionMode;
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
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.tools.MultiSelectHelper;
import org.videolan.vlc.gui.helpers.UiToolsKt;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u00020\u0004H@"}, d2 = {"<anonymous>", "", "T", "Lorg/videolan/vlc/viewmodels/MedialibraryViewModel;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.audio.BaseAudioBrowser$onPrepareActionMode$1$1", f = "BaseAudioBrowser.kt", i = {}, l = {288}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: BaseAudioBrowser.kt */
final class BaseAudioBrowser$onPrepareActionMode$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MultiSelectHelper<MediaLibraryItem> $it;
    final /* synthetic */ ActionMode $mode;
    int label;
    final /* synthetic */ BaseAudioBrowser<T> this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BaseAudioBrowser$onPrepareActionMode$1$1(BaseAudioBrowser<T> baseAudioBrowser, ActionMode actionMode, MultiSelectHelper<MediaLibraryItem> multiSelectHelper, Continuation<? super BaseAudioBrowser$onPrepareActionMode$1$1> continuation) {
        super(2, continuation);
        this.this$0 = baseAudioBrowser;
        this.$mode = actionMode;
        this.$it = multiSelectHelper;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new BaseAudioBrowser$onPrepareActionMode$1$1(this.this$0, this.$mode, this.$it, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((BaseAudioBrowser$onPrepareActionMode$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FragmentActivity requireActivity = this.this$0.requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
            this.label = 1;
            if (UiToolsKt.fillActionMode(requireActivity, this.$mode, this.$it, this) == coroutine_suspended) {
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
