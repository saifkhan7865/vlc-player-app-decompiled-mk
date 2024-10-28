package org.videolan.moviepedia.database.models;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R$\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR \u0010\n\u001a\u0004\u0018\u00010\u000b8\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001e\u0010\u0010\u001a\u00020\u00118\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001e\u0010\u0016\u001a\u00020\u00118\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0013\"\u0004\b\u0018\u0010\u0015¨\u0006\u0019"}, d2 = {"Lorg/videolan/moviepedia/database/models/MediaMetadataWithImages;", "", "()V", "images", "", "Lorg/videolan/moviepedia/database/models/MediaImage;", "getImages", "()Ljava/util/List;", "setImages", "(Ljava/util/List;)V", "media", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "getMedia", "()Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "setMedia", "(Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;)V", "metadata", "Lorg/videolan/moviepedia/database/models/MediaMetadata;", "getMetadata", "()Lorg/videolan/moviepedia/database/models/MediaMetadata;", "setMetadata", "(Lorg/videolan/moviepedia/database/models/MediaMetadata;)V", "show", "getShow", "setShow", "moviepedia_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaMetadata.kt */
public final class MediaMetadataWithImages {
    private List<MediaImage> images = new ArrayList();
    private MediaWrapper media;
    public MediaMetadata metadata;
    public MediaMetadata show;

    public final MediaMetadata getMetadata() {
        MediaMetadata mediaMetadata = this.metadata;
        if (mediaMetadata != null) {
            return mediaMetadata;
        }
        Intrinsics.throwUninitializedPropertyAccessException("metadata");
        return null;
    }

    public final void setMetadata(MediaMetadata mediaMetadata) {
        Intrinsics.checkNotNullParameter(mediaMetadata, "<set-?>");
        this.metadata = mediaMetadata;
    }

    public final MediaMetadata getShow() {
        MediaMetadata mediaMetadata = this.show;
        if (mediaMetadata != null) {
            return mediaMetadata;
        }
        Intrinsics.throwUninitializedPropertyAccessException("show");
        return null;
    }

    public final void setShow(MediaMetadata mediaMetadata) {
        Intrinsics.checkNotNullParameter(mediaMetadata, "<set-?>");
        this.show = mediaMetadata;
    }

    public final MediaWrapper getMedia() {
        return this.media;
    }

    public final void setMedia(MediaWrapper mediaWrapper) {
        this.media = mediaWrapper;
    }

    public final List<MediaImage> getImages() {
        return this.images;
    }

    public final void setImages(List<MediaImage> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.images = list;
    }
}
