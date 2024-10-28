package org.videolan.moviepedia.viewmodel;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import org.videolan.moviepedia.database.models.MediaMetadataWithImages;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lorg/videolan/moviepedia/database/models/MediaMetadataWithImages;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaMetadataModel.kt */
final class MediaMetadataModel$2$1 extends Lambda implements Function1<MediaMetadataWithImages, Unit> {
    final /* synthetic */ String $mId;
    final /* synthetic */ MediaMetadataFull $mediaMetadataFull;
    final /* synthetic */ MediaMetadataModel this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MediaMetadataModel$2$1(MediaMetadataFull mediaMetadataFull, MediaMetadataModel mediaMetadataModel, String str) {
        super(1);
        this.$mediaMetadataFull = mediaMetadataFull;
        this.this$0 = mediaMetadataModel;
        this.$mId = str;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((MediaMetadataWithImages) obj);
        return Unit.INSTANCE;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0012, code lost:
        r5 = r5.getMetadata();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void invoke(org.videolan.moviepedia.database.models.MediaMetadataWithImages r5) {
        /*
            r4 = this;
            org.videolan.moviepedia.viewmodel.MediaMetadataFull r0 = r4.$mediaMetadataFull
            r0.setMetadata(r5)
            org.videolan.moviepedia.viewmodel.MediaMetadataModel r0 = r4.this$0
            kotlinx.coroutines.channels.SendChannel r0 = r0.updateActor
            org.videolan.moviepedia.viewmodel.MediaMetadataFull r1 = r4.$mediaMetadataFull
            r0.m1139trySendJP2dKIU(r1)
            if (r5 == 0) goto L_0x001d
            org.videolan.moviepedia.database.models.MediaMetadata r5 = r5.getMetadata()
            if (r5 == 0) goto L_0x001d
            org.videolan.moviepedia.database.models.MediaMetadataType r5 = r5.getType()
            goto L_0x001e
        L_0x001d:
            r5 = 0
        L_0x001e:
            org.videolan.moviepedia.database.models.MediaMetadataType r0 = org.videolan.moviepedia.database.models.MediaMetadataType.TV_SHOW
            if (r5 != r0) goto L_0x0051
            org.videolan.moviepedia.repository.MediaMetadataRepository$Companion r5 = org.videolan.moviepedia.repository.MediaMetadataRepository.Companion
            org.videolan.moviepedia.viewmodel.MediaMetadataModel r0 = r4.this$0
            android.content.Context r0 = r0.context
            java.lang.Object r5 = r5.getInstance(r0)
            org.videolan.moviepedia.repository.MediaMetadataRepository r5 = (org.videolan.moviepedia.repository.MediaMetadataRepository) r5
            java.lang.String r0 = r4.$mId
            androidx.lifecycle.LiveData r5 = r5.getEpisodesLive(r0)
            org.videolan.moviepedia.viewmodel.MediaMetadataModel r0 = r4.this$0
            androidx.lifecycle.MediatorLiveData r0 = r0.getUpdateLiveData()
            org.videolan.moviepedia.viewmodel.MediaMetadataModel$2$1$1 r1 = new org.videolan.moviepedia.viewmodel.MediaMetadataModel$2$1$1
            org.videolan.moviepedia.viewmodel.MediaMetadataModel r2 = r4.this$0
            org.videolan.moviepedia.viewmodel.MediaMetadataFull r3 = r4.$mediaMetadataFull
            r1.<init>(r2, r3)
            kotlin.jvm.functions.Function1 r1 = (kotlin.jvm.functions.Function1) r1
            org.videolan.moviepedia.viewmodel.MediaMetadataModel$sam$androidx_lifecycle_Observer$0 r2 = new org.videolan.moviepedia.viewmodel.MediaMetadataModel$sam$androidx_lifecycle_Observer$0
            r2.<init>(r1)
            androidx.lifecycle.Observer r2 = (androidx.lifecycle.Observer) r2
            r0.addSource(r5, r2)
        L_0x0051:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.moviepedia.viewmodel.MediaMetadataModel$2$1.invoke(org.videolan.moviepedia.database.models.MediaMetadataWithImages):void");
    }
}
