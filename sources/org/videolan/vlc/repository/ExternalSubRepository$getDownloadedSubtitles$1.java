package org.videolan.vlc.repository;

import android.net.Uri;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.videolan.vlc.mediadb.models.ExternalSub;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\r\u0012\u0004\u0012\u00020\u00020\u0001¢\u0006\u0002\b\u00032\u0011\u0010\u0004\u001a\r\u0012\u0004\u0012\u00020\u00020\u0001¢\u0006\u0002\b\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "Lorg/videolan/vlc/mediadb/models/ExternalSub;", "Lkotlin/jvm/JvmSuppressWildcards;", "list", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ExternalSubRepository.kt */
final class ExternalSubRepository$getDownloadedSubtitles$1 extends Lambda implements Function1<List<ExternalSub>, List<ExternalSub>> {
    final /* synthetic */ ExternalSubRepository this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ExternalSubRepository$getDownloadedSubtitles$1(ExternalSubRepository externalSubRepository) {
        super(1);
        this.this$0 = externalSubRepository;
    }

    public final List<ExternalSub> invoke(List<ExternalSub> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        List<ExternalSub> arrayList = new ArrayList<>();
        ExternalSubRepository externalSubRepository = this.this$0;
        for (ExternalSub externalSub : list) {
            if (new File(Uri.decode(externalSub.getSubtitlePath())).exists()) {
                arrayList.add(externalSub);
            } else {
                externalSubRepository.deleteSubtitle(externalSub.getMediaPath(), externalSub.getIdSubtitle());
            }
        }
        return arrayList;
    }
}
