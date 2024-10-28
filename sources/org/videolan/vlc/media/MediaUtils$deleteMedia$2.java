package org.videolan.vlc.media;

import android.net.Uri;
import java.io.File;
import java.util.Iterator;
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
import org.videolan.medialibrary.interfaces.media.Album;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.vlc.util.FileUtils;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.media.MediaUtils$deleteMedia$2", f = "MediaUtils.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: MediaUtils.kt */
final class MediaUtils$deleteMedia$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
    final /* synthetic */ Runnable $failCB;
    final /* synthetic */ MediaLibraryItem $mw;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MediaUtils$deleteMedia$2(MediaLibraryItem mediaLibraryItem, Runnable runnable, Continuation<? super MediaUtils$deleteMedia$2> continuation) {
        super(2, continuation);
        this.$mw = mediaLibraryItem;
        this.$failCB = runnable;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MediaUtils$deleteMedia$2(this.$mw, this.$failCB, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Boolean> continuation) {
        return ((MediaUtils$deleteMedia$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            LinkedList<String> linkedList = new LinkedList<>();
            LinkedList linkedList2 = new LinkedList();
            MediaWrapper[] tracks = this.$mw.getTracks();
            Intrinsics.checkNotNullExpressionValue(tracks, "getTracks(...)");
            boolean z = false;
            for (MediaWrapper mediaWrapper : tracks) {
                String parent = FileUtils.INSTANCE.getParent(mediaWrapper.getUri().getPath());
                FileUtils fileUtils = FileUtils.INSTANCE;
                Uri uri = mediaWrapper.getUri();
                Intrinsics.checkNotNullExpressionValue(uri, "getUri(...)");
                if (fileUtils.deleteFile(uri) && parent != null) {
                    if (mediaWrapper.getId() > 0 && !linkedList.contains(parent)) {
                        linkedList.add(parent);
                    }
                    Boxing.boxBoolean(linkedList2.add(mediaWrapper.getLocation()));
                }
            }
            Medialibrary instance = Medialibrary.getInstance();
            Intrinsics.checkNotNullExpressionValue(instance, "getInstance(...)");
            Iterator it = linkedList.iterator();
            while (it.hasNext()) {
                instance.reload((String) it.next());
            }
            if (this.$mw instanceof Album) {
                for (String str : linkedList) {
                    String[] list = new File(str).list();
                    if (list == null || list.length == 0) {
                        FileUtils.INSTANCE.deleteFile(str);
                    }
                }
            }
            if (linkedList2.isEmpty()) {
                Runnable runnable = this.$failCB;
                if (runnable != null) {
                    runnable.run();
                }
            } else {
                z = true;
            }
            return Boxing.boxBoolean(z);
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
