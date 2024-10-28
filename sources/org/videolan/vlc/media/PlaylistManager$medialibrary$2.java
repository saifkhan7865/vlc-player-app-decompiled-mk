package org.videolan.vlc.media;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.videolan.medialibrary.interfaces.Medialibrary;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lorg/videolan/medialibrary/interfaces/Medialibrary;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: PlaylistManager.kt */
final class PlaylistManager$medialibrary$2 extends Lambda implements Function0<Medialibrary> {
    public static final PlaylistManager$medialibrary$2 INSTANCE = new PlaylistManager$medialibrary$2();

    PlaylistManager$medialibrary$2() {
        super(0);
    }

    public final Medialibrary invoke() {
        return Medialibrary.getInstance();
    }
}
