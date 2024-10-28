package org.videolan.vlc.gui.dialogs;

import java.util.LinkedList;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.medialibrary.interfaces.Medialibrary;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.interfaces.media.VideoGroup;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.dialogs.AddToGroupDialog$addToGroup$1", f = "AddToGroupDialog.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: AddToGroupDialog.kt */
final class AddToGroupDialog$addToGroup$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ VideoGroup $videoGroup;
    int label;
    final /* synthetic */ AddToGroupDialog this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    AddToGroupDialog$addToGroup$1(AddToGroupDialog addToGroupDialog, VideoGroup videoGroup, Continuation<? super AddToGroupDialog$addToGroup$1> continuation) {
        super(2, continuation);
        this.this$0 = addToGroupDialog;
        this.$videoGroup = videoGroup;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AddToGroupDialog$addToGroup$1(this.this$0, this.$videoGroup, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AddToGroupDialog$addToGroup$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            MediaWrapper[] access$getNewTrack$p = this.this$0.newTrack;
            if (access$getNewTrack$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException("newTrack");
                access$getNewTrack$p = null;
            }
            if (access$getNewTrack$p.length == 0) {
                return Unit.INSTANCE;
            }
            LinkedList<Number> linkedList = new LinkedList<>();
            MediaWrapper[] access$getNewTrack$p2 = this.this$0.newTrack;
            if (access$getNewTrack$p2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("newTrack");
                access$getNewTrack$p2 = null;
            }
            for (MediaWrapper mediaWrapper : access$getNewTrack$p2) {
                long id = mediaWrapper.getId();
                if (id == 0) {
                    Medialibrary access$getMedialibrary$p = this.this$0.medialibrary;
                    if (access$getMedialibrary$p == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("medialibrary");
                        access$getMedialibrary$p = null;
                    }
                    MediaWrapper media = access$getMedialibrary$p.getMedia(mediaWrapper.getUri());
                    if (media != null) {
                        linkedList.add(Boxing.boxLong(media.getId()));
                    } else {
                        Medialibrary access$getMedialibrary$p2 = this.this$0.medialibrary;
                        if (access$getMedialibrary$p2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("medialibrary");
                            access$getMedialibrary$p2 = null;
                        }
                        MediaWrapper addMedia = access$getMedialibrary$p2.addMedia(mediaWrapper.getLocation(), -1);
                        if (addMedia != null) {
                            linkedList.add(Boxing.boxLong(addMedia.getId()));
                        }
                    }
                } else {
                    linkedList.add(Boxing.boxLong(id));
                }
            }
            VideoGroup videoGroup = this.$videoGroup;
            for (Number longValue : linkedList) {
                videoGroup.add(longValue.longValue());
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
