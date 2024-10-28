package org.videolan.moviepedia.database;

import android.net.Uri;
import java.util.Date;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.moviepedia.database.models.MediaImageType;
import org.videolan.moviepedia.database.models.MediaMetadataType;
import org.videolan.moviepedia.database.models.PersonType;
import org.videolan.resources.Constants;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0019\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0007¢\u0006\u0002\u0010\u0007J\u0019\u0010\b\u001a\u0004\u0018\u00010\u00062\b\u0010\t\u001a\u0004\u0018\u00010\u0004H\u0007¢\u0006\u0002\u0010\nJ\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0007J\u0010\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\fH\u0007J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\r\u001a\u00020\u000eH\u0007J\u0010\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\u0012H\u0007J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\r\u001a\u00020\u000eH\u0007J\u0010\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u0018\u001a\u00020\u0016H\u0007J\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\t\u001a\u00020\u001bH\u0007J\u0010\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u001aH\u0007¨\u0006\u001e"}, d2 = {"Lorg/videolan/moviepedia/database/Converters;", "", "()V", "dateToTimestamp", "", "date", "Ljava/util/Date;", "(Ljava/util/Date;)Ljava/lang/Long;", "fromTimestamp", "value", "(Ljava/lang/Long;)Ljava/util/Date;", "mediaImageTypeFromKey", "Lorg/videolan/moviepedia/database/models/MediaImageType;", "key", "", "mediaImageTypeToKey", "mediaImageType", "mediaTypeFromKey", "Lorg/videolan/moviepedia/database/models/MediaMetadataType;", "mediaTypeToKey", "mediaType", "personTypeFromKey", "Lorg/videolan/moviepedia/database/models/PersonType;", "personTypeToKey", "personType", "stringToUri", "Landroid/net/Uri;", "", "uriToString", "uri", "moviepedia_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: Converters.kt */
public final class Converters {
    public final String uriToString(Uri uri) {
        Intrinsics.checkNotNullParameter(uri, Constants.KEY_URI);
        String uri2 = uri.toString();
        Intrinsics.checkNotNullExpressionValue(uri2, "toString(...)");
        return uri2;
    }

    public final Date fromTimestamp(Long l) {
        if (l != null) {
            return new Date(l.longValue());
        }
        return null;
    }

    public final Long dateToTimestamp(Date date) {
        if (date != null) {
            return Long.valueOf(date.getTime());
        }
        return null;
    }

    public final int personTypeToKey(PersonType personType) {
        Intrinsics.checkNotNullParameter(personType, "personType");
        return personType.getKey();
    }

    public final PersonType personTypeFromKey(int i) {
        return PersonType.Companion.fromKey(i);
    }

    public final int mediaImageTypeToKey(MediaImageType mediaImageType) {
        Intrinsics.checkNotNullParameter(mediaImageType, "mediaImageType");
        return mediaImageType.getKey();
    }

    public final MediaImageType mediaImageTypeFromKey(int i) {
        return MediaImageType.Companion.fromKey(i);
    }

    public final int mediaTypeToKey(MediaMetadataType mediaMetadataType) {
        Intrinsics.checkNotNullParameter(mediaMetadataType, "mediaType");
        return mediaMetadataType.getKey();
    }

    public final MediaMetadataType mediaTypeFromKey(int i) {
        return MediaMetadataType.Companion.fromKey(i);
    }

    public final Uri stringToUri(String str) {
        Intrinsics.checkNotNullParameter(str, "value");
        return Uri.parse(str);
    }
}
