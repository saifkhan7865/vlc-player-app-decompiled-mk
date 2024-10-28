package org.videolan.moviepedia.viewmodel;

import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import org.videolan.moviepedia.database.models.Person;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u001a\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004 \u0005*\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "it", "", "Lorg/videolan/moviepedia/database/models/Person;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaMetadataModel.kt */
final class MediaMetadataModel$2$2 extends Lambda implements Function1<List<? extends Person>, Unit> {
    final /* synthetic */ MediaMetadataFull $mediaMetadataFull;
    final /* synthetic */ MediaMetadataModel this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MediaMetadataModel$2$2(MediaMetadataFull mediaMetadataFull, MediaMetadataModel mediaMetadataModel) {
        super(1);
        this.$mediaMetadataFull = mediaMetadataFull;
        this.this$0 = mediaMetadataModel;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((List<Person>) (List) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(List<Person> list) {
        this.$mediaMetadataFull.setActors(list);
        this.this$0.updateActor.m1139trySendJP2dKIU(this.$mediaMetadataFull);
    }
}
