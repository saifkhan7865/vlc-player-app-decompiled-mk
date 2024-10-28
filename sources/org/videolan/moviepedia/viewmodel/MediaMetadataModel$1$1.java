package org.videolan.moviepedia.viewmodel;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import org.videolan.moviepedia.database.models.MediaMetadataWithImages;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "mediaMetadataWithImages", "Lorg/videolan/moviepedia/database/models/MediaMetadataWithImages;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaMetadataModel.kt */
final class MediaMetadataModel$1$1 extends Lambda implements Function1<MediaMetadataWithImages, Unit> {
    final /* synthetic */ MediaMetadataFull $mediaMetadataFull;
    final /* synthetic */ MediaMetadataModel this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MediaMetadataModel$1$1(MediaMetadataFull mediaMetadataFull, MediaMetadataModel mediaMetadataModel) {
        super(1);
        this.$mediaMetadataFull = mediaMetadataFull;
        this.this$0 = mediaMetadataModel;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((MediaMetadataWithImages) obj);
        return Unit.INSTANCE;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0019, code lost:
        r0 = r0.getMetadata();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void invoke(org.videolan.moviepedia.database.models.MediaMetadataWithImages r11) {
        /*
            r10 = this;
            org.videolan.moviepedia.viewmodel.MediaMetadataFull r0 = r10.$mediaMetadataFull
            r0.setMetadata(r11)
            org.videolan.moviepedia.viewmodel.MediaMetadataModel r0 = r10.this$0
            kotlinx.coroutines.channels.SendChannel r0 = r0.updateActor
            org.videolan.moviepedia.viewmodel.MediaMetadataFull r1 = r10.$mediaMetadataFull
            r0.m1139trySendJP2dKIU(r1)
            org.videolan.moviepedia.viewmodel.MediaMetadataFull r0 = r10.$mediaMetadataFull
            org.videolan.moviepedia.database.models.MediaMetadataWithImages r0 = r0.getMetadata()
            r1 = 0
            if (r0 == 0) goto L_0x0024
            org.videolan.moviepedia.database.models.MediaMetadata r0 = r0.getMetadata()
            if (r0 == 0) goto L_0x0024
            org.videolan.moviepedia.database.models.MediaMetadataType r0 = r0.getType()
            goto L_0x0025
        L_0x0024:
            r0 = r1
        L_0x0025:
            org.videolan.moviepedia.database.models.MediaMetadataType r2 = org.videolan.moviepedia.database.models.MediaMetadataType.TV_EPISODE
            if (r0 != r2) goto L_0x0080
            org.videolan.moviepedia.viewmodel.MediaMetadataFull r0 = r10.$mediaMetadataFull
            org.videolan.moviepedia.database.models.MediaMetadataWithImages r0 = r0.getMetadata()
            if (r0 == 0) goto L_0x0080
            org.videolan.moviepedia.database.models.MediaMetadata r0 = r0.getShow()
            if (r0 == 0) goto L_0x0080
            org.videolan.moviepedia.viewmodel.MediaMetadataFull r0 = r10.$mediaMetadataFull
            org.videolan.moviepedia.viewmodel.MediaMetadataModel r2 = r10.this$0
            org.videolan.moviepedia.database.models.MediaMetadataWithImages r3 = r0.getMetadata()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3)
            org.videolan.moviepedia.database.models.MediaMetadata r3 = r3.getMetadata()
            java.lang.String r3 = r3.getShowId()
            if (r3 == 0) goto L_0x0080
            org.videolan.moviepedia.database.models.MediaMetadataWithImages r3 = r0.getMetadata()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3)
            org.videolan.moviepedia.database.models.MediaMetadata r3 = r3.getMetadata()
            java.lang.Integer r3 = r3.getSeason()
            if (r3 == 0) goto L_0x0080
            org.videolan.moviepedia.database.models.MediaMetadataWithImages r3 = r0.getMetadata()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3)
            org.videolan.moviepedia.database.models.MediaMetadata r3 = r3.getMetadata()
            java.lang.Integer r3 = r3.getEpisode()
            if (r3 == 0) goto L_0x0080
            r4 = r2
            kotlinx.coroutines.CoroutineScope r4 = (kotlinx.coroutines.CoroutineScope) r4
            org.videolan.moviepedia.viewmodel.MediaMetadataModel$1$1$1$1 r3 = new org.videolan.moviepedia.viewmodel.MediaMetadataModel$1$1$1$1
            r3.<init>(r2, r0, r1)
            r7 = r3
            kotlin.jvm.functions.Function2 r7 = (kotlin.jvm.functions.Function2) r7
            r8 = 3
            r9 = 0
            r5 = 0
            r6 = 0
            kotlinx.coroutines.Job unused = kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r4, r5, r6, r7, r8, r9)
        L_0x0080:
            if (r11 == 0) goto L_0x009e
            org.videolan.moviepedia.database.models.MediaMetadata r11 = r11.getMetadata()
            if (r11 == 0) goto L_0x009e
            org.videolan.moviepedia.viewmodel.MediaMetadataModel r0 = r10.this$0
            org.videolan.moviepedia.viewmodel.MediaMetadataFull r2 = r10.$mediaMetadataFull
            r3 = r0
            kotlinx.coroutines.CoroutineScope r3 = (kotlinx.coroutines.CoroutineScope) r3
            org.videolan.moviepedia.viewmodel.MediaMetadataModel$1$1$2$1 r4 = new org.videolan.moviepedia.viewmodel.MediaMetadataModel$1$1$2$1
            r4.<init>(r11, r0, r2, r1)
            r6 = r4
            kotlin.jvm.functions.Function2 r6 = (kotlin.jvm.functions.Function2) r6
            r7 = 3
            r8 = 0
            r4 = 0
            r5 = 0
            kotlinx.coroutines.Job unused = kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r3, r4, r5, r6, r7, r8)
        L_0x009e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.moviepedia.viewmodel.MediaMetadataModel$1$1.invoke(org.videolan.moviepedia.database.models.MediaMetadataWithImages):void");
    }
}
