package org.videolan.moviepedia.database.models;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.videolan.vlc.util.TextUtils;

@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\n\u0010\u0005\u001a\u00020\u0001*\u00020\u0006\u001a\n\u0010\u0007\u001a\u00020\u0001*\u00020\b\u001a\n\u0010\t\u001a\u00020\u0001*\u00020\b\u001a\n\u0010\n\u001a\u00020\u0001*\u00020\b\u001a\n\u0010\u000b\u001a\u00020\u0001*\u00020\b\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"MEDIA_METADATA_IMAGE_TABLE_NAME", "", "MEDIA_METADATA_PERSON_JOIN_TABLE_NAME", "MEDIA_METADATA_PERSON_TABLE_NAME", "MEDIA_METADATA_TABLE_NAME", "getYear", "Lorg/videolan/moviepedia/database/models/MediaMetadata;", "movieSubtitle", "Lorg/videolan/moviepedia/database/models/MediaMetadataWithImages;", "subtitle", "tvEpisodeSubtitle", "tvshowSubtitle", "moviepedia_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaMetadata.kt */
public final class MediaMetadataKt {
    private static final String MEDIA_METADATA_IMAGE_TABLE_NAME = "media_metadata_image";
    private static final String MEDIA_METADATA_PERSON_JOIN_TABLE_NAME = "media_person_join";
    private static final String MEDIA_METADATA_PERSON_TABLE_NAME = "media_metadata_person";
    private static final String MEDIA_METADATA_TABLE_NAME = "media_metadata";

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    /* compiled from: MediaMetadata.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[MediaMetadataType.values().length];
            try {
                iArr[MediaMetadataType.TV_EPISODE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final String getYear(MediaMetadata mediaMetadata) {
        Intrinsics.checkNotNullParameter(mediaMetadata, "<this>");
        Date releaseDate = mediaMetadata.getReleaseDate();
        String format = releaseDate != null ? new SimpleDateFormat("yyyy", Locale.getDefault()).format(releaseDate) : null;
        return format == null ? "" : format;
    }

    public static final String subtitle(MediaMetadataWithImages mediaMetadataWithImages) {
        Intrinsics.checkNotNullParameter(mediaMetadataWithImages, "<this>");
        return mediaMetadataWithImages.getMetadata().getType() == MediaMetadataType.MOVIE ? movieSubtitle(mediaMetadataWithImages) : tvshowSubtitle(mediaMetadataWithImages);
    }

    public static final String movieSubtitle(MediaMetadataWithImages mediaMetadataWithImages) {
        Intrinsics.checkNotNullParameter(mediaMetadataWithImages, "<this>");
        ArrayList arrayList = new ArrayList();
        Date releaseDate = mediaMetadataWithImages.getMetadata().getReleaseDate();
        if (releaseDate != null) {
            arrayList.add(new SimpleDateFormat("yyyy", Locale.getDefault()).format(releaseDate));
        }
        arrayList.add(mediaMetadataWithImages.getMetadata().getGenres());
        arrayList.add(mediaMetadataWithImages.getMetadata().getCountries());
        return TextUtils.INSTANCE.separatedString((String[]) arrayList.toArray(new String[0]));
    }

    public static final String tvshowSubtitle(MediaMetadataWithImages mediaMetadataWithImages) {
        Intrinsics.checkNotNullParameter(mediaMetadataWithImages, "<this>");
        ArrayList arrayList = new ArrayList();
        Date releaseDate = mediaMetadataWithImages.getMetadata().getReleaseDate();
        if (releaseDate != null) {
            arrayList.add(new SimpleDateFormat("yyyy", Locale.getDefault()).format(releaseDate));
        }
        arrayList.add(mediaMetadataWithImages.getShow().getTitle());
        arrayList.add("S" + StringsKt.padStart(String.valueOf(mediaMetadataWithImages.getMetadata().getSeason()), 2, '0') + 'E' + StringsKt.padStart(String.valueOf(mediaMetadataWithImages.getMetadata().getEpisode()), 2, '0'));
        return TextUtils.INSTANCE.separatedString((String[]) arrayList.toArray(new String[0]));
    }

    public static final String tvEpisodeSubtitle(MediaMetadataWithImages mediaMetadataWithImages) {
        Intrinsics.checkNotNullParameter(mediaMetadataWithImages, "<this>");
        if (WhenMappings.$EnumSwitchMapping$0[mediaMetadataWithImages.getMetadata().getType().ordinal()] == 1) {
            return "S" + StringsKt.padStart(String.valueOf(mediaMetadataWithImages.getMetadata().getSeason()), 2, '0') + 'E' + StringsKt.padStart(String.valueOf(mediaMetadataWithImages.getMetadata().getEpisode()), 2, '0');
        }
        Date releaseDate = mediaMetadataWithImages.getMetadata().getReleaseDate();
        String format = releaseDate != null ? new SimpleDateFormat("yyyy", Locale.getDefault()).format(releaseDate) : null;
        return format == null ? "" : format;
    }
}
