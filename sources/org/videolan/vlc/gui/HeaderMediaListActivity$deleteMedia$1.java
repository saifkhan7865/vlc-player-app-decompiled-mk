package org.videolan.vlc.gui;

import java.util.Iterator;
import java.util.LinkedList;
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
import org.videolan.vlc.R;
import org.videolan.vlc.gui.helpers.UiTools;
import org.videolan.vlc.util.FileUtils;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.HeaderMediaListActivity$deleteMedia$1", f = "HeaderMediaListActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: HeaderMediaListActivity.kt */
final class HeaderMediaListActivity$deleteMedia$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MediaLibraryItem $mw;
    int label;
    final /* synthetic */ HeaderMediaListActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    HeaderMediaListActivity$deleteMedia$1(MediaLibraryItem mediaLibraryItem, HeaderMediaListActivity headerMediaListActivity, Continuation<? super HeaderMediaListActivity$deleteMedia$1> continuation) {
        super(2, continuation);
        this.$mw = mediaLibraryItem;
        this.this$0 = headerMediaListActivity;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new HeaderMediaListActivity$deleteMedia$1(this.$mw, this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((HeaderMediaListActivity$deleteMedia$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            LinkedList linkedList = new LinkedList();
            MediaWrapper[] tracks = this.$mw.getTracks();
            Intrinsics.checkNotNullExpressionValue(tracks, "getTracks(...)");
            for (MediaWrapper mediaWrapper : tracks) {
                String path = mediaWrapper.getUri().getPath();
                String parent = FileUtils.INSTANCE.getParent(path);
                if (parent == null || !FileUtils.INSTANCE.deleteFile(path) || mediaWrapper.getId() <= 0 || linkedList.contains(parent)) {
                    UiTools uiTools = UiTools.INSTANCE;
                    HeaderMediaListActivity headerMediaListActivity = this.this$0;
                    String string = headerMediaListActivity.getString(R.string.msg_delete_failed, new Object[]{mediaWrapper.getTitle()});
                    Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                    uiTools.snacker(headerMediaListActivity, string);
                } else {
                    linkedList.add(parent);
                }
            }
            Iterator it = linkedList.iterator();
            while (it.hasNext()) {
                this.this$0.mediaLibrary.reload((String) it.next());
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
