package org.videolan.vlc.gui.helpers;

import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.medialibrary.interfaces.media.Album;
import org.videolan.medialibrary.interfaces.media.Artist;
import org.videolan.medialibrary.interfaces.media.Folder;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.interfaces.media.VideoGroup;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.tools.MultiSelectHelper;
import org.videolan.vlc.media.MediaUtilsKt;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.helpers.UiToolsKt$fillActionMode$2", f = "UiTools.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: UiTools.kt */
final class UiToolsKt$fillActionMode$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Ref.LongRef $length;
    final /* synthetic */ MultiSelectHelper<MediaLibraryItem> $multiSelectHelper;
    final /* synthetic */ Ref.BooleanRef $ready;
    final /* synthetic */ Ref.IntRef $realCount;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    UiToolsKt$fillActionMode$2(MultiSelectHelper<MediaLibraryItem> multiSelectHelper, Ref.BooleanRef booleanRef, Ref.IntRef intRef, Ref.LongRef longRef, Continuation<? super UiToolsKt$fillActionMode$2> continuation) {
        super(2, continuation);
        this.$multiSelectHelper = multiSelectHelper;
        this.$ready = booleanRef;
        this.$realCount = intRef;
        this.$length = longRef;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new UiToolsKt$fillActionMode$2(this.$multiSelectHelper, this.$ready, this.$realCount, this.$length, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((UiToolsKt$fillActionMode$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            List<MediaLibraryItem> selection = this.$multiSelectHelper.getSelection();
            this.$ready.element = selection.size() == this.$multiSelectHelper.getSelectionCount();
            Iterable<MediaLibraryItem> iterable = selection;
            Ref.IntRef intRef = this.$realCount;
            for (MediaLibraryItem mediaLibraryItem : iterable) {
                if (mediaLibraryItem instanceof MediaWrapper) {
                    intRef.element++;
                } else if (mediaLibraryItem instanceof Album) {
                    intRef.element += ((Album) mediaLibraryItem).getRealTracksCount();
                } else if (mediaLibraryItem instanceof Artist) {
                    intRef.element += ((Artist) mediaLibraryItem).getTracksCount();
                } else if (mediaLibraryItem instanceof VideoGroup) {
                    intRef.element += ((VideoGroup) mediaLibraryItem).mediaCount();
                } else if (mediaLibraryItem instanceof Folder) {
                    intRef.element += ((Folder) mediaLibraryItem).mediaCount(Folder.TYPE_FOLDER_VIDEO);
                }
            }
            Ref.LongRef longRef = this.$length;
            for (MediaLibraryItem mediaLibraryItem2 : iterable) {
                if (mediaLibraryItem2 instanceof MediaWrapper) {
                    longRef.element += ((MediaWrapper) mediaLibraryItem2).getLength();
                } else if (mediaLibraryItem2 instanceof Album) {
                    for (MediaWrapper length : MediaUtilsKt.getAll$default((Album) mediaLibraryItem2, 0, false, false, false, 15, (Object) null)) {
                        longRef.element += length.getLength();
                    }
                } else if (mediaLibraryItem2 instanceof Artist) {
                    for (MediaWrapper length2 : MediaUtilsKt.getAll$default((Artist) mediaLibraryItem2, 0, false, false, false, 15, (Object) null)) {
                        longRef.element += length2.getLength();
                    }
                } else if (mediaLibraryItem2 instanceof VideoGroup) {
                    for (MediaWrapper length3 : MediaUtilsKt.getAll$default((VideoGroup) mediaLibraryItem2, 0, false, false, false, 15, (Object) null)) {
                        longRef.element += length3.getLength();
                    }
                } else if (mediaLibraryItem2 instanceof Folder) {
                    for (MediaWrapper length4 : MediaUtilsKt.getAll$default((Folder) mediaLibraryItem2, 0, 0, false, false, false, 31, (Object) null)) {
                        longRef.element += length4.getLength();
                    }
                }
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
