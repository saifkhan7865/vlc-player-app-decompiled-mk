package org.videolan.vlc.gui.browser;

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
import org.videolan.tools.Strings;
import org.videolan.vlc.gui.dialogs.ContextSheetKt;
import org.videolan.vlc.util.ContextOption;
import org.videolan.vlc.util.FlagSet;
import org.videolan.vlc.viewmodels.browser.BrowserModel;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.browser.MLStorageBrowserFragment$getBrowserContainer$1$onCtxClick$1", f = "MLStorageBrowserFragment.kt", i = {}, l = {258}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: MLStorageBrowserFragment.kt */
final class MLStorageBrowserFragment$getBrowserContainer$1$onCtxClick$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MediaLibraryItem $item;
    final /* synthetic */ String $path;
    final /* synthetic */ int $position;
    int label;
    final /* synthetic */ MLStorageBrowserFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MLStorageBrowserFragment$getBrowserContainer$1$onCtxClick$1(MLStorageBrowserFragment mLStorageBrowserFragment, String str, int i, MediaLibraryItem mediaLibraryItem, Continuation<? super MLStorageBrowserFragment$getBrowserContainer$1$onCtxClick$1> continuation) {
        super(2, continuation);
        this.this$0 = mLStorageBrowserFragment;
        this.$path = str;
        this.$position = i;
        this.$item = mediaLibraryItem;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MLStorageBrowserFragment$getBrowserContainer$1$onCtxClick$1(this.this$0, this.$path, this.$position, this.$item, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MLStorageBrowserFragment$getBrowserContainer$1$onCtxClick$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            BrowserModel access$getLocalViewModel$p = this.this$0.localViewModel;
            if (access$getLocalViewModel$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException("localViewModel");
                access$getLocalViewModel$p = null;
            }
            this.label = 1;
            obj = access$getLocalViewModel$p.customDirectoryExists(Strings.stripTrailingSlash(this.$path), this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        if (((Boolean) obj).booleanValue() && this.this$0.isAdded()) {
            FragmentActivity requireActivity = this.this$0.requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
            int i2 = this.$position;
            MediaLibraryItem mediaLibraryItem = this.$item;
            FlagSet flagSet = new FlagSet(ContextOption.class);
            flagSet.add(ContextOption.CTX_CUSTOM_REMOVE);
            Unit unit = Unit.INSTANCE;
            ContextSheetKt.showContext(requireActivity, this.this$0, i2, mediaLibraryItem, flagSet);
        }
        return Unit.INSTANCE;
    }
}
