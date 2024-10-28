package org.videolan.vlc.gui;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.tools.KotlinExtensionsKt;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.helpers.UiTools;
import org.videolan.vlc.media.MediaUtils;
import org.videolan.vlc.viewmodels.mobile.PlaylistViewModel;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.HeaderMediaListActivity$removeItems$1$1$deleteAction$1$1", f = "HeaderMediaListActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: HeaderMediaListActivity.kt */
final class HeaderMediaListActivity$removeItems$1$1$deleteAction$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MediaWrapper $item;
    int label;
    final /* synthetic */ HeaderMediaListActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    HeaderMediaListActivity$removeItems$1$1$deleteAction$1$1(HeaderMediaListActivity headerMediaListActivity, MediaWrapper mediaWrapper, Continuation<? super HeaderMediaListActivity$removeItems$1$1$deleteAction$1$1> continuation) {
        super(2, continuation);
        this.this$0 = headerMediaListActivity;
        this.$item = mediaWrapper;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new HeaderMediaListActivity$removeItems$1$1$deleteAction$1$1(this.this$0, this.$item, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((HeaderMediaListActivity$removeItems$1$1$deleteAction$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            final HeaderMediaListActivity headerMediaListActivity = this.this$0;
            MediaUtils.INSTANCE.deleteItem(this.this$0, this.$item, new Function1<MediaLibraryItem, Unit>() {
                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    invoke((MediaLibraryItem) obj);
                    return Unit.INSTANCE;
                }

                public final void invoke(MediaLibraryItem mediaLibraryItem) {
                    Intrinsics.checkNotNullParameter(mediaLibraryItem, "it");
                    UiTools uiTools = UiTools.INSTANCE;
                    HeaderMediaListActivity headerMediaListActivity = headerMediaListActivity;
                    String string = headerMediaListActivity.getString(R.string.msg_delete_failed, new Object[]{mediaLibraryItem.getTitle()});
                    Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                    uiTools.snacker(headerMediaListActivity, string);
                }
            });
            if (KotlinExtensionsKt.isStarted(this.this$0)) {
                PlaylistViewModel access$getViewModel$p = this.this$0.viewModel;
                if (access$getViewModel$p == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    access$getViewModel$p = null;
                }
                access$getViewModel$p.refresh();
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
