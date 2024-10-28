package org.videolan.vlc.util;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ThumbnailsProvider.kt */
final class ThumbnailsProvider$getPlaylistOrGenreImage$saltedKey$1 extends Lambda implements Function1<MediaWrapper, CharSequence> {
    public static final ThumbnailsProvider$getPlaylistOrGenreImage$saltedKey$1 INSTANCE = new ThumbnailsProvider$getPlaylistOrGenreImage$saltedKey$1();

    ThumbnailsProvider$getPlaylistOrGenreImage$saltedKey$1() {
        super(1);
    }

    public final CharSequence invoke(MediaWrapper mediaWrapper) {
        Intrinsics.checkNotNullParameter(mediaWrapper, "it");
        return String.valueOf(mediaWrapper.getId());
    }
}
