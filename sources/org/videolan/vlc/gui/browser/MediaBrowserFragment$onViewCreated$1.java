package org.videolan.vlc.gui.browser;

import androidx.lifecycle.FlowExtKt;
import androidx.lifecycle.Lifecycle;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import org.videolan.vlc.viewmodels.DisplaySettingsViewModel;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u00020\u0004H@"}, d2 = {"<anonymous>", "", "T", "Lorg/videolan/vlc/viewmodels/SortableModel;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.browser.MediaBrowserFragment$onViewCreated$1", f = "MediaBrowserFragment.kt", i = {}, l = {94}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: MediaBrowserFragment.kt */
final class MediaBrowserFragment$onViewCreated$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ MediaBrowserFragment<T> this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MediaBrowserFragment$onViewCreated$1(MediaBrowserFragment<T> mediaBrowserFragment, Continuation<? super MediaBrowserFragment$onViewCreated$1> continuation) {
        super(2, continuation);
        this.this$0 = mediaBrowserFragment;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MediaBrowserFragment$onViewCreated$1(this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MediaBrowserFragment$onViewCreated$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Flow flowWithLifecycle = FlowExtKt.flowWithLifecycle(this.this$0.getDisplaySettingsViewModel().getSettingChangeFlow(), this.this$0.getViewLifecycleOwner().getLifecycle(), Lifecycle.State.STARTED);
            final MediaBrowserFragment<T> mediaBrowserFragment = this.this$0;
            this.label = 1;
            if (flowWithLifecycle.collect(new FlowCollector() {
                public final Object emit(DisplaySettingsViewModel.SettingChange settingChange, Continuation<? super Unit> continuation) {
                    if (!mediaBrowserFragment.isResumed()) {
                        return Unit.INSTANCE;
                    }
                    mediaBrowserFragment.onDisplaySettingChanged(settingChange.getKey(), settingChange.getValue());
                    Object consume = mediaBrowserFragment.getDisplaySettingsViewModel().consume(continuation);
                    return consume == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? consume : Unit.INSTANCE;
                }
            }, this) == coroutine_suspended) {
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
