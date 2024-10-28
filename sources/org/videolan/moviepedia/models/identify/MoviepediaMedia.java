package org.videolan.moviepedia.models.identify;

import android.net.Uri;
import androidx.core.app.NotificationCompat;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import com.squareup.moshi.Json;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.UInt$$ExternalSyntheticBackport0;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.moviepedia.models.resolver.ResolverMedia;
import org.videolan.moviepedia.models.resolver.ResolverMediaType;
import org.videolan.vlc.ArtworkProvider;

@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b+\n\u0002\u0018\u0002\n\u0002\b#\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001B\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u0007\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0003\u0012\u000e\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u0007\u0012\u0006\u0010\u0011\u001a\u00020\u0005\u0012\u0006\u0010\u0012\u001a\u00020\n\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014\u0012\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\n0\u0007\u0012\u0006\u0010\u0016\u001a\u00020\n\u0012\u0006\u0010\u0017\u001a\u00020\n\u0012\u0006\u0010\u0018\u001a\u00020\n\u0012\u0006\u0010\u0019\u001a\u00020\u0005\u0012\u0006\u0010\u001a\u001a\u00020\u0005\u0012\u0006\u0010\u001b\u001a\u00020\n\u0012\b\u0010\u001c\u001a\u0004\u0018\u00010\n\u0012\u0006\u0010\u001d\u001a\u00020\n\u0012\u0006\u0010\u001e\u001a\u00020\u001f\u0012\f\u0010 \u001a\b\u0012\u0004\u0012\u00020!0\u0007\u0012\u0006\u0010\"\u001a\u00020\u0005\u0012\u0006\u0010#\u001a\u00020\u0005\u0012\u0006\u0010$\u001a\u00020\n\u0012\u0006\u0010%\u001a\u00020\n\u0012\u0006\u0010&\u001a\u00020\b¢\u0006\u0002\u0010'J\u0018\u0010L\u001a\u0004\u0018\u00010M2\f\u0010N\u001a\b\u0012\u0004\u0012\u00020\n0\u0007H\u0016J\t\u0010O\u001a\u00020\u0003HÆ\u0003J\t\u0010P\u001a\u00020\nHÆ\u0003J\u000b\u0010Q\u001a\u0004\u0018\u00010\u0014HÆ\u0003J\u000f\u0010R\u001a\b\u0012\u0004\u0012\u00020\n0\u0007HÆ\u0003J\t\u0010S\u001a\u00020\nHÆ\u0003J\t\u0010T\u001a\u00020\nHÆ\u0003J\t\u0010U\u001a\u00020\nHÆ\u0003J\t\u0010V\u001a\u00020\u0005HÆ\u0003J\t\u0010W\u001a\u00020\u0005HÆ\u0003J\t\u0010X\u001a\u00020\nHÆ\u0003J\u000b\u0010Y\u001a\u0004\u0018\u00010\nHÆ\u0003J\t\u0010Z\u001a\u00020\u0005HÆ\u0003J\t\u0010[\u001a\u00020\nHÆ\u0003J\t\u0010\\\u001a\u00020\u001fHÆ\u0003J\u000f\u0010]\u001a\b\u0012\u0004\u0012\u00020!0\u0007HÆ\u0003J\t\u0010^\u001a\u00020\u0005HÆ\u0003J\t\u0010_\u001a\u00020\u0005HÆ\u0003J\t\u0010`\u001a\u00020\nHÆ\u0003J\t\u0010a\u001a\u00020\nHÆ\u0003J\t\u0010b\u001a\u00020\bHÆ\u0003J\u000f\u0010c\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0003J\u0011\u0010d\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u0007HÆ\u0003J\u000b\u0010e\u001a\u0004\u0018\u00010\fHÆ\u0003J\t\u0010f\u001a\u00020\u000eHÆ\u0003J\t\u0010g\u001a\u00020\u0003HÆ\u0003J\u0011\u0010h\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u0007HÆ\u0003J\t\u0010i\u001a\u00020\u0005HÆ\u0003J¿\u0002\u0010j\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0010\b\u0002\u0010\t\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u00072\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00032\u0010\b\u0002\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u00072\b\b\u0002\u0010\u0011\u001a\u00020\u00052\b\b\u0002\u0010\u0012\u001a\u00020\n2\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u000e\b\u0002\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\n0\u00072\b\b\u0002\u0010\u0016\u001a\u00020\n2\b\b\u0002\u0010\u0017\u001a\u00020\n2\b\b\u0002\u0010\u0018\u001a\u00020\n2\b\b\u0002\u0010\u0019\u001a\u00020\u00052\b\b\u0002\u0010\u001a\u001a\u00020\u00052\b\b\u0002\u0010\u001b\u001a\u00020\n2\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010\u001d\u001a\u00020\n2\b\b\u0002\u0010\u001e\u001a\u00020\u001f2\u000e\b\u0002\u0010 \u001a\b\u0012\u0004\u0012\u00020!0\u00072\b\b\u0002\u0010\"\u001a\u00020\u00052\b\b\u0002\u0010#\u001a\u00020\u00052\b\b\u0002\u0010$\u001a\u00020\n2\b\b\u0002\u0010%\u001a\u00020\n2\b\b\u0002\u0010&\u001a\u00020\bHÆ\u0001J\b\u0010k\u001a\u00020\nH\u0016J\n\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016J\r\u0010#\u001a\u00020\u0005H\u0016¢\u0006\u0002\u0010lJ\u0013\u0010m\u001a\u00020\u00032\b\u0010n\u001a\u0004\u0018\u00010\bHÖ\u0003J\b\u0010o\u001a\u00020\nH\u0016J\u001e\u0010p\u001a\n\u0012\u0004\u0012\u00020q\u0018\u00010\u00072\f\u0010N\u001a\b\u0012\u0004\u0012\u00020\n0\u0007H\u0016J\u0010\u0010r\u001a\n s*\u0004\u0018\u00010\n0\nH\u0016J\u0010\u0010t\u001a\u00020\n2\u0006\u0010u\u001a\u00020\nH\u0016J\u001e\u0010v\u001a\n\u0012\u0004\u0012\u00020w\u0018\u00010\u00072\f\u0010N\u001a\b\u0012\u0004\u0012\u00020\n0\u0007H\u0016J\t\u0010x\u001a\u00020\u0005HÖ\u0001J\u0018\u0010y\u001a\u0004\u0018\u00010M2\f\u0010N\u001a\b\u0012\u0004\u0012\u00020\n0\u0007H\u0016J\b\u0010\u0016\u001a\u00020\nH\u0016J\b\u0010\u001e\u001a\u00020zH\u0016J\r\u0010\"\u001a\u00020\u0005H\u0016¢\u0006\u0002\u0010lJ\b\u0010%\u001a\u00020\nH\u0016J\b\u0010\u001c\u001a\u00020\nH\u0016J\b\u0010\u001d\u001a\u00020\nH\u0016J\t\u0010{\u001a\u00020\nHÖ\u0001J\u0010\u0010|\u001a\n s*\u0004\u0018\u00010\n0\nH\u0016R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b(\u0010)R\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b*\u0010+R\u001c\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00078\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b,\u0010-R\u001e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u00078\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b.\u0010-R\u0018\u0010\u000b\u001a\u0004\u0018\u00010\f8\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b/\u00100R\u0016\u0010#\u001a\u00020\u00058\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b1\u0010+R\u0016\u0010\r\u001a\u00020\u000e8\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b2\u00103R\u0016\u0010\u000f\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b4\u0010)R\u001e\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u00078\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b5\u0010-R\u0016\u0010\u0011\u001a\u00020\u00058\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b6\u0010+R\u0016\u0010\u0012\u001a\u00020\n8\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b7\u00108R\u0018\u0010\u0013\u001a\u0004\u0018\u00010\u00148\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b9\u0010:R\u001c\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\n0\u00078\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b;\u0010-R\u0016\u0010\u0016\u001a\u00020\n8\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b<\u00108R\u0016\u0010\u001e\u001a\u00020\u001f8\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b=\u0010>R\u0016\u0010\u0017\u001a\u00020\n8\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b?\u00108R\u0016\u0010\u0018\u001a\u00020\n8\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b@\u00108R\u0016\u0010\u0019\u001a\u00020\u00058\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\bA\u0010+R\u0016\u0010\u001a\u001a\u00020\u00058\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\bB\u0010+R\u0016\u0010\"\u001a\u00020\u00058\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\bC\u0010+R\u0016\u0010%\u001a\u00020\n8\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\bD\u00108R\u0016\u0010$\u001a\u00020\n8\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\bE\u00108R\u0016\u0010\u001b\u001a\u00020\n8\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\bF\u00108R\u0018\u0010\u001c\u001a\u0004\u0018\u00010\n8\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\bG\u00108R\u0016\u0010\u001d\u001a\u00020\n8\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\bH\u00108R\u001c\u0010 \u001a\b\u0012\u0004\u0012\u00020!0\u00078\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\bI\u0010-R\u0016\u0010&\u001a\u00020\b8\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\bJ\u0010K¨\u0006}"}, d2 = {"Lorg/videolan/moviepedia/models/identify/MoviepediaMedia;", "Lorg/videolan/moviepedia/models/resolver/ResolverMedia;", "adult", "", "budget", "", "childrenId", "", "", "country", "", "date", "Ljava/util/Date;", "externalids", "Lorg/videolan/moviepedia/models/identify/Externalids;", "followed", "genre", "globalRating", "imageEndpoint", "images", "Lorg/videolan/moviepedia/models/identify/Images;", "language", "mediaId", "nbFollower", "nbWatches", "rating", "runtime", "status", "summary", "title", "mediaType", "Lorg/videolan/moviepedia/models/identify/MediaType;", "videos", "Lorg/videolan/moviepedia/models/identify/Video;", "season", "episode", "showTitle", "showId", "wished", "(ZILjava/util/List;Ljava/util/List;Ljava/util/Date;Lorg/videolan/moviepedia/models/identify/Externalids;ZLjava/util/List;ILjava/lang/String;Lorg/videolan/moviepedia/models/identify/Images;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Lorg/videolan/moviepedia/models/identify/MediaType;Ljava/util/List;IILjava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V", "getAdult", "()Z", "getBudget", "()I", "getChildrenId", "()Ljava/util/List;", "getCountry", "getDate", "()Ljava/util/Date;", "getEpisode", "getExternalids", "()Lorg/videolan/moviepedia/models/identify/Externalids;", "getFollowed", "getGenre", "getGlobalRating", "getImageEndpoint", "()Ljava/lang/String;", "getImages", "()Lorg/videolan/moviepedia/models/identify/Images;", "getLanguage", "getMediaId", "getMediaType", "()Lorg/videolan/moviepedia/models/identify/MediaType;", "getNbFollower", "getNbWatches", "getRating", "getRuntime", "getSeason", "getShowId", "getShowTitle", "getStatus", "getSummary", "getTitle", "getVideos", "getWished", "()Ljava/lang/Object;", "backdropUri", "Landroid/net/Uri;", "languages", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component21", "component22", "component23", "component24", "component25", "component26", "component27", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "countries", "()Ljava/lang/Integer;", "equals", "other", "genres", "getBackdrops", "Lorg/videolan/moviepedia/models/identify/Backdrop;", "getCardSubtitle", "kotlin.jvm.PlatformType", "getImageUriFromPath", "path", "getPosters", "Lorg/videolan/moviepedia/models/identify/Poster;", "hashCode", "imageUri", "Lorg/videolan/moviepedia/models/resolver/ResolverMediaType;", "toString", "year", "moviepedia_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: IdentifyResult.kt */
public final class MoviepediaMedia extends ResolverMedia {
    @Json(name = "adult")
    private final boolean adult;
    @Json(name = "budget")
    private final int budget;
    @Json(name = "childrenId")
    private final List<Object> childrenId;
    @Json(name = "country")
    private final List<String> country;
    @Json(name = "date")
    private final Date date;
    @Json(name = "episode")
    private final int episode;
    @Json(name = "externalids")
    private final Externalids externalids;
    @Json(name = "followed")
    private final boolean followed;
    @Json(name = "genre")
    private final List<String> genre;
    @Json(name = "globalRating")
    private final int globalRating;
    @Json(name = "imageEndpoint")
    private final String imageEndpoint;
    @Json(name = "images")
    private final Images images;
    @Json(name = "language")
    private final List<String> language;
    @Json(name = "mediaId")
    private final String mediaId;
    @Json(name = "type")
    private final MediaType mediaType;
    @Json(name = "nbFollower")
    private final String nbFollower;
    @Json(name = "nbWatches")
    private final String nbWatches;
    @Json(name = "rating")
    private final int rating;
    @Json(name = "runtime")
    private final int runtime;
    @Json(name = "season")
    private final int season;
    @Json(name = "showId")
    private final String showId;
    @Json(name = "showTitle")
    private final String showTitle;
    @Json(name = "status")
    private final String status;
    @Json(name = "summary")
    private final String summary;
    @Json(name = "title")
    private final String title;
    @Json(name = "videos")
    private final List<Video> videos;
    @Json(name = "wished")
    private final Object wished;

    public static /* synthetic */ MoviepediaMedia copy$default(MoviepediaMedia moviepediaMedia, boolean z, int i, List list, List list2, Date date2, Externalids externalids2, boolean z2, List list3, int i2, String str, Images images2, List list4, String str2, String str3, String str4, int i3, int i4, String str5, String str6, String str7, MediaType mediaType2, List list5, int i5, int i6, String str8, String str9, Object obj, int i7, Object obj2) {
        MoviepediaMedia moviepediaMedia2 = moviepediaMedia;
        int i8 = i7;
        return moviepediaMedia.copy((i8 & 1) != 0 ? moviepediaMedia2.adult : z, (i8 & 2) != 0 ? moviepediaMedia2.budget : i, (i8 & 4) != 0 ? moviepediaMedia2.childrenId : list, (i8 & 8) != 0 ? moviepediaMedia2.country : list2, (i8 & 16) != 0 ? moviepediaMedia2.date : date2, (i8 & 32) != 0 ? moviepediaMedia2.externalids : externalids2, (i8 & 64) != 0 ? moviepediaMedia2.followed : z2, (i8 & 128) != 0 ? moviepediaMedia2.genre : list3, (i8 & 256) != 0 ? moviepediaMedia2.globalRating : i2, (i8 & 512) != 0 ? moviepediaMedia2.imageEndpoint : str, (i8 & 1024) != 0 ? moviepediaMedia2.images : images2, (i8 & 2048) != 0 ? moviepediaMedia2.language : list4, (i8 & 4096) != 0 ? moviepediaMedia2.mediaId : str2, (i8 & 8192) != 0 ? moviepediaMedia2.nbFollower : str3, (i8 & 16384) != 0 ? moviepediaMedia2.nbWatches : str4, (i8 & 32768) != 0 ? moviepediaMedia2.rating : i3, (i8 & 65536) != 0 ? moviepediaMedia2.runtime : i4, (i8 & 131072) != 0 ? moviepediaMedia2.status : str5, (i8 & 262144) != 0 ? moviepediaMedia2.summary : str6, (i8 & 524288) != 0 ? moviepediaMedia2.title : str7, (i8 & 1048576) != 0 ? moviepediaMedia2.mediaType : mediaType2, (i8 & 2097152) != 0 ? moviepediaMedia2.videos : list5, (i8 & 4194304) != 0 ? moviepediaMedia2.season : i5, (i8 & 8388608) != 0 ? moviepediaMedia2.episode : i6, (i8 & 16777216) != 0 ? moviepediaMedia2.showTitle : str8, (i8 & 33554432) != 0 ? moviepediaMedia2.showId : str9, (i8 & AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL) != 0 ? moviepediaMedia2.wished : obj);
    }

    public final boolean component1() {
        return this.adult;
    }

    public final String component10() {
        return this.imageEndpoint;
    }

    public final Images component11() {
        return this.images;
    }

    public final List<String> component12() {
        return this.language;
    }

    public final String component13() {
        return this.mediaId;
    }

    public final String component14() {
        return this.nbFollower;
    }

    public final String component15() {
        return this.nbWatches;
    }

    public final int component16() {
        return this.rating;
    }

    public final int component17() {
        return this.runtime;
    }

    public final String component18() {
        return this.status;
    }

    public final String component19() {
        return this.summary;
    }

    public final int component2() {
        return this.budget;
    }

    public final String component20() {
        return this.title;
    }

    public final MediaType component21() {
        return this.mediaType;
    }

    public final List<Video> component22() {
        return this.videos;
    }

    public final int component23() {
        return this.season;
    }

    public final int component24() {
        return this.episode;
    }

    public final String component25() {
        return this.showTitle;
    }

    public final String component26() {
        return this.showId;
    }

    public final Object component27() {
        return this.wished;
    }

    public final List<Object> component3() {
        return this.childrenId;
    }

    public final List<String> component4() {
        return this.country;
    }

    public final Date component5() {
        return this.date;
    }

    public final Externalids component6() {
        return this.externalids;
    }

    public final boolean component7() {
        return this.followed;
    }

    public final List<String> component8() {
        return this.genre;
    }

    public final int component9() {
        return this.globalRating;
    }

    public final MoviepediaMedia copy(boolean z, int i, List<? extends Object> list, List<String> list2, Date date2, Externalids externalids2, boolean z2, List<String> list3, int i2, String str, Images images2, List<String> list4, String str2, String str3, String str4, int i3, int i4, String str5, String str6, String str7, MediaType mediaType2, List<Video> list5, int i5, int i6, String str8, String str9, Object obj) {
        boolean z3 = z;
        Intrinsics.checkNotNullParameter(list, "childrenId");
        Intrinsics.checkNotNullParameter(externalids2, "externalids");
        Intrinsics.checkNotNullParameter(str, "imageEndpoint");
        Intrinsics.checkNotNullParameter(list4, "language");
        Intrinsics.checkNotNullParameter(str2, "mediaId");
        Intrinsics.checkNotNullParameter(str3, "nbFollower");
        Intrinsics.checkNotNullParameter(str4, "nbWatches");
        Intrinsics.checkNotNullParameter(str5, NotificationCompat.CATEGORY_STATUS);
        Intrinsics.checkNotNullParameter(str7, "title");
        Intrinsics.checkNotNullParameter(mediaType2, "mediaType");
        Intrinsics.checkNotNullParameter(list5, "videos");
        Intrinsics.checkNotNullParameter(str8, "showTitle");
        Intrinsics.checkNotNullParameter(str9, "showId");
        Intrinsics.checkNotNullParameter(obj, "wished");
        return new MoviepediaMedia(z, i, list, list2, date2, externalids2, z2, list3, i2, str, images2, list4, str2, str3, str4, i3, i4, str5, str6, str7, mediaType2, list5, i5, i6, str8, str9, obj);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MoviepediaMedia)) {
            return false;
        }
        MoviepediaMedia moviepediaMedia = (MoviepediaMedia) obj;
        return this.adult == moviepediaMedia.adult && this.budget == moviepediaMedia.budget && Intrinsics.areEqual((Object) this.childrenId, (Object) moviepediaMedia.childrenId) && Intrinsics.areEqual((Object) this.country, (Object) moviepediaMedia.country) && Intrinsics.areEqual((Object) this.date, (Object) moviepediaMedia.date) && Intrinsics.areEqual((Object) this.externalids, (Object) moviepediaMedia.externalids) && this.followed == moviepediaMedia.followed && Intrinsics.areEqual((Object) this.genre, (Object) moviepediaMedia.genre) && this.globalRating == moviepediaMedia.globalRating && Intrinsics.areEqual((Object) this.imageEndpoint, (Object) moviepediaMedia.imageEndpoint) && Intrinsics.areEqual((Object) this.images, (Object) moviepediaMedia.images) && Intrinsics.areEqual((Object) this.language, (Object) moviepediaMedia.language) && Intrinsics.areEqual((Object) this.mediaId, (Object) moviepediaMedia.mediaId) && Intrinsics.areEqual((Object) this.nbFollower, (Object) moviepediaMedia.nbFollower) && Intrinsics.areEqual((Object) this.nbWatches, (Object) moviepediaMedia.nbWatches) && this.rating == moviepediaMedia.rating && this.runtime == moviepediaMedia.runtime && Intrinsics.areEqual((Object) this.status, (Object) moviepediaMedia.status) && Intrinsics.areEqual((Object) this.summary, (Object) moviepediaMedia.summary) && Intrinsics.areEqual((Object) this.title, (Object) moviepediaMedia.title) && this.mediaType == moviepediaMedia.mediaType && Intrinsics.areEqual((Object) this.videos, (Object) moviepediaMedia.videos) && this.season == moviepediaMedia.season && this.episode == moviepediaMedia.episode && Intrinsics.areEqual((Object) this.showTitle, (Object) moviepediaMedia.showTitle) && Intrinsics.areEqual((Object) this.showId, (Object) moviepediaMedia.showId) && Intrinsics.areEqual(this.wished, moviepediaMedia.wished);
    }

    public int hashCode() {
        int m = ((((UInt$$ExternalSyntheticBackport0.m(this.adult) * 31) + this.budget) * 31) + this.childrenId.hashCode()) * 31;
        List<String> list = this.country;
        int i = 0;
        int hashCode = (m + (list == null ? 0 : list.hashCode())) * 31;
        Date date2 = this.date;
        int hashCode2 = (((((hashCode + (date2 == null ? 0 : date2.hashCode())) * 31) + this.externalids.hashCode()) * 31) + UInt$$ExternalSyntheticBackport0.m(this.followed)) * 31;
        List<String> list2 = this.genre;
        int hashCode3 = (((((hashCode2 + (list2 == null ? 0 : list2.hashCode())) * 31) + this.globalRating) * 31) + this.imageEndpoint.hashCode()) * 31;
        Images images2 = this.images;
        int hashCode4 = (((((((((((((((hashCode3 + (images2 == null ? 0 : images2.hashCode())) * 31) + this.language.hashCode()) * 31) + this.mediaId.hashCode()) * 31) + this.nbFollower.hashCode()) * 31) + this.nbWatches.hashCode()) * 31) + this.rating) * 31) + this.runtime) * 31) + this.status.hashCode()) * 31;
        String str = this.summary;
        if (str != null) {
            i = str.hashCode();
        }
        return ((((((((((((((((hashCode4 + i) * 31) + this.title.hashCode()) * 31) + this.mediaType.hashCode()) * 31) + this.videos.hashCode()) * 31) + this.season) * 31) + this.episode) * 31) + this.showTitle.hashCode()) * 31) + this.showId.hashCode()) * 31) + this.wished.hashCode();
    }

    public String toString() {
        return "MoviepediaMedia(adult=" + this.adult + ", budget=" + this.budget + ", childrenId=" + this.childrenId + ", country=" + this.country + ", date=" + this.date + ", externalids=" + this.externalids + ", followed=" + this.followed + ", genre=" + this.genre + ", globalRating=" + this.globalRating + ", imageEndpoint=" + this.imageEndpoint + ", images=" + this.images + ", language=" + this.language + ", mediaId=" + this.mediaId + ", nbFollower=" + this.nbFollower + ", nbWatches=" + this.nbWatches + ", rating=" + this.rating + ", runtime=" + this.runtime + ", status=" + this.status + ", summary=" + this.summary + ", title=" + this.title + ", mediaType=" + this.mediaType + ", videos=" + this.videos + ", season=" + this.season + ", episode=" + this.episode + ", showTitle=" + this.showTitle + ", showId=" + this.showId + ", wished=" + this.wished + ')';
    }

    public final boolean getAdult() {
        return this.adult;
    }

    public final int getBudget() {
        return this.budget;
    }

    public final List<Object> getChildrenId() {
        return this.childrenId;
    }

    public final List<String> getCountry() {
        return this.country;
    }

    public final Date getDate() {
        return this.date;
    }

    public final Externalids getExternalids() {
        return this.externalids;
    }

    public final boolean getFollowed() {
        return this.followed;
    }

    public final List<String> getGenre() {
        return this.genre;
    }

    public final int getGlobalRating() {
        return this.globalRating;
    }

    public final String getImageEndpoint() {
        return this.imageEndpoint;
    }

    public final Images getImages() {
        return this.images;
    }

    public final List<String> getLanguage() {
        return this.language;
    }

    public final String getMediaId() {
        return this.mediaId;
    }

    public final String getNbFollower() {
        return this.nbFollower;
    }

    public final String getNbWatches() {
        return this.nbWatches;
    }

    public final int getRating() {
        return this.rating;
    }

    public final int getRuntime() {
        return this.runtime;
    }

    public final String getStatus() {
        return this.status;
    }

    public final String getSummary() {
        return this.summary;
    }

    public final String getTitle() {
        return this.title;
    }

    public final MediaType getMediaType() {
        return this.mediaType;
    }

    public final List<Video> getVideos() {
        return this.videos;
    }

    public final int getSeason() {
        return this.season;
    }

    public final int getEpisode() {
        return this.episode;
    }

    public final String getShowTitle() {
        return this.showTitle;
    }

    public final String getShowId() {
        return this.showId;
    }

    public final Object getWished() {
        return this.wished;
    }

    public MoviepediaMedia(boolean z, int i, List<? extends Object> list, List<String> list2, Date date2, Externalids externalids2, boolean z2, List<String> list3, int i2, String str, Images images2, List<String> list4, String str2, String str3, String str4, int i3, int i4, String str5, String str6, String str7, MediaType mediaType2, List<Video> list5, int i5, int i6, String str8, String str9, Object obj) {
        List<? extends Object> list6 = list;
        Externalids externalids3 = externalids2;
        String str10 = str;
        List<String> list7 = list4;
        String str11 = str2;
        String str12 = str3;
        String str13 = str4;
        String str14 = str5;
        String str15 = str7;
        MediaType mediaType3 = mediaType2;
        List<Video> list8 = list5;
        String str16 = str8;
        String str17 = str9;
        Object obj2 = obj;
        Intrinsics.checkNotNullParameter(list6, "childrenId");
        Intrinsics.checkNotNullParameter(externalids3, "externalids");
        Intrinsics.checkNotNullParameter(str10, "imageEndpoint");
        Intrinsics.checkNotNullParameter(list7, "language");
        Intrinsics.checkNotNullParameter(str11, "mediaId");
        Intrinsics.checkNotNullParameter(str12, "nbFollower");
        Intrinsics.checkNotNullParameter(str13, "nbWatches");
        Intrinsics.checkNotNullParameter(str14, NotificationCompat.CATEGORY_STATUS);
        Intrinsics.checkNotNullParameter(str15, "title");
        Intrinsics.checkNotNullParameter(mediaType3, "mediaType");
        Intrinsics.checkNotNullParameter(list8, "videos");
        Intrinsics.checkNotNullParameter(str16, "showTitle");
        Intrinsics.checkNotNullParameter(str17, "showId");
        Intrinsics.checkNotNullParameter(obj2, "wished");
        this.adult = z;
        this.budget = i;
        this.childrenId = list6;
        this.country = list2;
        this.date = date2;
        this.externalids = externalids3;
        this.followed = z2;
        this.genre = list3;
        this.globalRating = i2;
        this.imageEndpoint = str10;
        this.images = images2;
        this.language = list7;
        this.mediaId = str11;
        this.nbFollower = str12;
        this.nbWatches = str13;
        this.rating = i3;
        this.runtime = i4;
        this.status = str14;
        this.summary = str6;
        this.title = str15;
        this.mediaType = mediaType3;
        this.videos = list8;
        this.season = i5;
        this.episode = i6;
        this.showTitle = str16;
        this.showId = str17;
        this.wished = obj2;
    }

    public ResolverMediaType mediaType() {
        return IdentifyResultKt.toResolverClass(this.mediaType);
    }

    public String showId() {
        return this.showId;
    }

    public String mediaId() {
        return this.mediaId;
    }

    public String title() {
        return this.title;
    }

    public String summary() {
        String str = this.summary;
        return str == null ? "" : str;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
        r0 = kotlin.collections.CollectionsKt.joinToString$default(r0, (java.lang.CharSequence) null, (java.lang.CharSequence) null, (java.lang.CharSequence) null, 0, (java.lang.CharSequence) null, org.videolan.moviepedia.models.identify.MoviepediaMedia$genres$1.INSTANCE, 31, (java.lang.Object) null);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String genres() {
        /*
            r10 = this;
            java.util.List<java.lang.String> r0 = r10.genre
            if (r0 == 0) goto L_0x001a
            r1 = r0
            java.lang.Iterable r1 = (java.lang.Iterable) r1
            org.videolan.moviepedia.models.identify.MoviepediaMedia$genres$1 r0 = org.videolan.moviepedia.models.identify.MoviepediaMedia$genres$1.INSTANCE
            r7 = r0
            kotlin.jvm.functions.Function1 r7 = (kotlin.jvm.functions.Function1) r7
            r8 = 31
            r9 = 0
            r2 = 0
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            java.lang.String r0 = kotlin.collections.CollectionsKt.joinToString$default(r1, r2, r3, r4, r5, r6, r7, r8, r9)
            if (r0 != 0) goto L_0x001c
        L_0x001a:
            java.lang.String r0 = ""
        L_0x001c:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.moviepedia.models.identify.MoviepediaMedia.genres():java.lang.String");
    }

    public Date date() {
        return this.date;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
        r0 = kotlin.collections.CollectionsKt.joinToString$default(r0, (java.lang.CharSequence) null, (java.lang.CharSequence) null, (java.lang.CharSequence) null, 0, (java.lang.CharSequence) null, org.videolan.moviepedia.models.identify.MoviepediaMedia$countries$1.INSTANCE, 31, (java.lang.Object) null);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String countries() {
        /*
            r10 = this;
            java.util.List<java.lang.String> r0 = r10.country
            if (r0 == 0) goto L_0x001a
            r1 = r0
            java.lang.Iterable r1 = (java.lang.Iterable) r1
            org.videolan.moviepedia.models.identify.MoviepediaMedia$countries$1 r0 = org.videolan.moviepedia.models.identify.MoviepediaMedia$countries$1.INSTANCE
            r7 = r0
            kotlin.jvm.functions.Function1 r7 = (kotlin.jvm.functions.Function1) r7
            r8 = 31
            r9 = 0
            r2 = 0
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            java.lang.String r0 = kotlin.collections.CollectionsKt.joinToString$default(r1, r2, r3, r4, r5, r6, r7, r8, r9)
            if (r0 != 0) goto L_0x001c
        L_0x001a:
            java.lang.String r0 = ""
        L_0x001c:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.moviepedia.models.identify.MoviepediaMedia.countries():java.lang.String");
    }

    public Integer season() {
        return Integer.valueOf(this.season);
    }

    public Integer episode() {
        return Integer.valueOf(this.episode);
    }

    public String year() {
        return new SimpleDateFormat("yyyy", Locale.getDefault()).format(this.date);
    }

    public Uri imageUri(List<String> list) {
        Intrinsics.checkNotNullParameter(list, "languages");
        return IdentifyResultKt.retrieveImageUri(this, list);
    }

    public Uri backdropUri(List<String> list) {
        Intrinsics.checkNotNullParameter(list, "languages");
        return IdentifyResultKt.getBackdropUri(this, list);
    }

    public List<Backdrop> getBackdrops(List<String> list) {
        Intrinsics.checkNotNullParameter(list, "languages");
        return IdentifyResultKt.retrieveBackdrops(this, list);
    }

    public List<Poster> getPosters(List<String> list) {
        Intrinsics.checkNotNullParameter(list, "languages");
        return IdentifyResultKt.retrievePosters(this, list);
    }

    public String getImageUriFromPath(String str) {
        Intrinsics.checkNotNullParameter(str, ArtworkProvider.PATH);
        return IdentifyResultKt.retrieveImageUriFromPath(this, str);
    }

    public String getCardSubtitle() {
        return this.mediaType == MediaType.TV_EPISODE ? IdentifyResultKt.getShow(this) : year();
    }
}
