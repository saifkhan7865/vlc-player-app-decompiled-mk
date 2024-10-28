package org.videolan.vlc.viewmodels;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.videolan.vlc.gui.dialogs.State;
import org.videolan.vlc.gui.dialogs.SubtitleItem;
import org.videolan.vlc.mediadb.models.ExternalSub;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\r\u0012\u0004\u0012\u00020\u00020\u0001¢\u0006\u0002\b\u00032\u0011\u0010\u0004\u001a\r\u0012\u0004\u0012\u00020\u00050\u0001¢\u0006\u0002\b\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "Lorg/videolan/vlc/gui/dialogs/SubtitleItem;", "Lkotlin/jvm/JvmSuppressWildcards;", "list", "Lorg/videolan/vlc/mediadb/models/ExternalSub;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: SubtitlesModel.kt */
final class SubtitlesModel$downloadedLiveData$1 extends Lambda implements Function1<List<ExternalSub>, List<SubtitleItem>> {
    final /* synthetic */ SubtitlesModel this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SubtitlesModel$downloadedLiveData$1(SubtitlesModel subtitlesModel) {
        super(1);
        this.this$0 = subtitlesModel;
    }

    public final List<SubtitleItem> invoke(List<ExternalSub> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        Iterable<ExternalSub> iterable = list;
        SubtitlesModel subtitlesModel = this.this$0;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (ExternalSub externalSub : iterable) {
            arrayList.add(new SubtitleItem(externalSub.getIdSubtitle(), subtitlesModel.mediaUri, externalSub.getSubLanguageID(), externalSub.getMovieReleaseName(), State.Downloaded, ""));
        }
        return (List) arrayList;
    }
}
