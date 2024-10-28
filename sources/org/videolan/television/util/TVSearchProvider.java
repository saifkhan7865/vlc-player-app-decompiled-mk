package org.videolan.television.util;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.net.Uri;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.resources.Constants;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J/\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\nH\u0016¢\u0006\u0002\u0010\u000bJ\u0012\u0010\f\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u001c\u0010\r\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u0011H\u0016JK\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0005\u001a\u00020\u00062\u000e\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\n2\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\n2\b\u0010\u0015\u001a\u0004\u0018\u00010\bH\u0016¢\u0006\u0002\u0010\u0016J9\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\nH\u0016¢\u0006\u0002\u0010\u0018¨\u0006\u0019"}, d2 = {"Lorg/videolan/television/util/TVSearchProvider;", "Landroid/content/ContentProvider;", "()V", "delete", "", "uri", "Landroid/net/Uri;", "selection", "", "selectionArgs", "", "(Landroid/net/Uri;Ljava/lang/String;[Ljava/lang/String;)I", "getType", "insert", "values", "Landroid/content/ContentValues;", "onCreate", "", "query", "Landroid/database/Cursor;", "projection", "sortOrder", "(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;", "update", "(Landroid/net/Uri;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: TVSearchProvider.kt */
public final class TVSearchProvider extends ContentProvider {
    public String getType(Uri uri) {
        Intrinsics.checkNotNullParameter(uri, Constants.KEY_URI);
        return null;
    }

    public boolean onCreate() {
        return true;
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        Intrinsics.checkNotNullParameter(uri, Constants.KEY_URI);
        throw new UnsupportedOperationException("Requested operation not supported");
    }

    /* JADX WARNING: Removed duplicated region for block: B:80:0x0468  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.database.Cursor query(android.net.Uri r25, java.lang.String[] r26, java.lang.String r27, java.lang.String[] r28, java.lang.String r29) {
        /*
            r24 = this;
            r0 = r25
            java.lang.String r1 = "uri"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r1)
            java.util.List r1 = r25.getPathSegments()
            java.lang.String r2 = "getPathSegments(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            java.lang.Object r1 = kotlin.collections.CollectionsKt.firstOrNull(r1)
            java.lang.String r2 = "search"
            boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1, (java.lang.Object) r2)
            if (r1 == 0) goto L_0x04f0
            if (r28 == 0) goto L_0x04eb
            java.lang.Object r1 = kotlin.collections.ArraysKt.firstOrNull((T[]) r28)
            java.lang.String r1 = (java.lang.String) r1
            if (r1 == 0) goto L_0x04eb
            org.videolan.medialibrary.interfaces.Medialibrary r2 = org.videolan.medialibrary.interfaces.Medialibrary.getInstance()
            java.lang.String r3 = "getInstance(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r3)
            r3 = 7
            java.lang.String[] r4 = new java.lang.String[r3]
            java.lang.String r5 = "_id"
            r6 = 0
            r4[r6] = r5
            java.lang.String r5 = "suggest_intent_data_id"
            r7 = 1
            r4[r7] = r5
            java.lang.String r5 = "suggest_text_1"
            r8 = 2
            r4[r8] = r5
            java.lang.String r5 = "suggest_text_2"
            r9 = 3
            r4[r9] = r5
            java.lang.String r5 = "suggest_result_card_image"
            r10 = 4
            r4[r10] = r5
            java.lang.String r5 = "suggest_production_year"
            r11 = 5
            r4[r11] = r5
            java.lang.String r5 = "suggest_duration"
            r12 = 6
            r4[r12] = r5
            android.database.MatrixCursor r5 = new android.database.MatrixCursor
            r5.<init>(r4)
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
            kotlin.text.Regex r4 = new kotlin.text.Regex
            java.lang.String r13 = "[^A-Za-z0-9 ]"
            r4.<init>((java.lang.String) r13)
            java.lang.String r13 = ""
            java.lang.String r1 = r4.replace((java.lang.CharSequence) r1, (java.lang.String) r13)
            java.util.Locale r4 = java.util.Locale.getDefault()
            java.lang.String r14 = "getDefault(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r14)
            java.lang.String r1 = r1.toLowerCase(r4)
            java.lang.String r4 = "toLowerCase(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r4)
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            android.content.Context r14 = r24.getContext()
            java.lang.String r15 = "media_"
            if (r14 == 0) goto L_0x02a7
            org.videolan.moviepedia.repository.MediaMetadataRepository$Companion r0 = org.videolan.moviepedia.repository.MediaMetadataRepository.Companion
            kotlin.jvm.internal.Intrinsics.checkNotNull(r14)
            java.lang.Object r0 = r0.getInstance(r14)
            org.videolan.moviepedia.repository.MediaMetadataRepository r0 = (org.videolan.moviepedia.repository.MediaMetadataRepository) r0
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            java.lang.String r11 = "%"
            r12.<init>(r11)
            r12.append(r1)
            r11 = 37
            r12.append(r11)
            java.lang.String r16 = r12.toString()
            r20 = 4
            r21 = 0
            java.lang.String r17 = " "
            java.lang.String r18 = "%"
            r19 = 0
            java.lang.String r11 = kotlin.text.StringsKt.replace$default((java.lang.String) r16, (java.lang.String) r17, (java.lang.String) r18, (boolean) r19, (int) r20, (java.lang.Object) r21)
            java.util.List r11 = r0.searchMedia(r11)
            java.lang.Iterable r11 = (java.lang.Iterable) r11
            java.util.Iterator r11 = r11.iterator()
        L_0x00be:
            boolean r12 = r11.hasNext()
            if (r12 == 0) goto L_0x02a0
            java.lang.Object r12 = r11.next()
            org.videolan.moviepedia.database.models.MediaMetadataWithImages r12 = (org.videolan.moviepedia.database.models.MediaMetadataWithImages) r12
            org.videolan.moviepedia.database.models.MediaMetadata r16 = r12.getMetadata()
            java.lang.Long r16 = r16.getMlId()
            if (r16 == 0) goto L_0x0145
            java.lang.Number r16 = (java.lang.Number) r16
            r29 = r11
            long r10 = r16.longValue()
            java.lang.Long r9 = java.lang.Long.valueOf(r10)
            r4.add(r9)
            org.videolan.medialibrary.interfaces.media.MediaWrapper r9 = r2.getMedia((long) r10)
            org.videolan.moviepedia.database.models.MediaMetadata r10 = r12.getMetadata()
            java.lang.String r10 = r10.getCurrentBackdrop()
            long r17 = r9.getId()
            java.lang.Long r11 = java.lang.Long.valueOf(r17)
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>(r15)
            long r6 = r9.getId()
            r8.append(r6)
            java.lang.String r6 = r8.toString()
            org.videolan.moviepedia.database.models.MediaMetadata r7 = r12.getMetadata()
            java.lang.String r7 = r7.getTitle()
            java.lang.String r8 = org.videolan.moviepedia.database.models.MediaMetadataKt.subtitle(r12)
            org.videolan.moviepedia.database.models.MediaMetadata r20 = r12.getMetadata()
            java.lang.String r20 = org.videolan.moviepedia.database.models.MediaMetadataKt.getYear(r20)
            long r21 = r9.getLength()
            java.lang.Long r9 = java.lang.Long.valueOf(r21)
            r21 = r15
            java.lang.Object[] r15 = new java.lang.Object[r3]
            r18 = 0
            r15[r18] = r11
            r11 = 1
            r15[r11] = r6
            r6 = 2
            r15[r6] = r7
            r6 = 3
            r15[r6] = r8
            r6 = 4
            r15[r6] = r10
            r6 = 5
            r15[r6] = r20
            r6 = 6
            r15[r6] = r9
            r5.addRow(r15)
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            goto L_0x014a
        L_0x0145:
            r29 = r11
            r21 = r15
            r6 = 0
        L_0x014a:
            if (r6 != 0) goto L_0x028c
            org.videolan.moviepedia.database.models.MediaMetadata r6 = r12.getMetadata()
            org.videolan.moviepedia.database.models.MediaMetadataType r6 = r6.getType()
            org.videolan.moviepedia.database.models.MediaMetadataType r7 = org.videolan.moviepedia.database.models.MediaMetadataType.TV_SHOW
            if (r6 != r7) goto L_0x028c
            org.videolan.moviepedia.provider.MediaScrapingTvshowProvider r6 = new org.videolan.moviepedia.provider.MediaScrapingTvshowProvider
            r6.<init>(r14)
            org.videolan.moviepedia.database.models.MediaMetadata r7 = r12.getMetadata()
            java.lang.String r7 = r7.getMoviepediaId()
            java.util.List r7 = r0.getTvShowEpisodes(r7)
            org.videolan.moviepedia.database.models.MediaMetadataWithImages r6 = r6.getFirstResumableEpisode(r2, r7)
            if (r6 == 0) goto L_0x01f8
            org.videolan.moviepedia.database.models.MediaMetadata r8 = r6.getMetadata()
            java.lang.Long r8 = r8.getMlId()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r8)
            long r8 = r8.longValue()
            org.videolan.medialibrary.interfaces.media.MediaWrapper r8 = r2.getMedia((long) r8)
            org.videolan.moviepedia.database.models.MediaMetadata r9 = r12.getMetadata()
            java.lang.String r9 = r9.getCurrentBackdrop()
            long r10 = r8.getId()
            java.lang.Long r10 = java.lang.Long.valueOf(r10)
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            java.lang.String r15 = "content_resume_"
            r11.<init>(r15)
            org.videolan.moviepedia.database.models.MediaMetadata r15 = r12.getMetadata()
            java.lang.String r15 = r15.getMoviepediaId()
            r11.append(r15)
            java.lang.String r11 = r11.toString()
            org.videolan.moviepedia.database.models.MediaMetadata r12 = r12.getMetadata()
            java.lang.String r12 = r12.getTitle()
            int r15 = org.videolan.vlc.R.string.resume_episode
            java.lang.String r20 = org.videolan.moviepedia.database.models.MediaMetadataKt.tvEpisodeSubtitle(r6)
            r23 = r0
            r3 = 1
            java.lang.Object[] r0 = new java.lang.Object[r3]
            r18 = 0
            r0[r18] = r20
            java.lang.String r0 = r14.getString(r15, r0)
            java.lang.String r15 = "getString(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r15)
            org.videolan.moviepedia.database.models.MediaMetadata r6 = r6.getMetadata()
            java.lang.String r6 = org.videolan.moviepedia.database.models.MediaMetadataKt.getYear(r6)
            long r19 = r8.getLength()
            java.lang.Long r8 = java.lang.Long.valueOf(r19)
            r20 = r14
            r15 = 7
            java.lang.Object[] r14 = new java.lang.Object[r15]
            r14[r18] = r10
            r14[r3] = r11
            r3 = 2
            r14[r3] = r12
            r3 = 3
            r14[r3] = r0
            r0 = 4
            r14[r0] = r9
            r0 = 5
            r14[r0] = r6
            r0 = 6
            r14[r0] = r8
            r5.addRow(r14)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            goto L_0x01fc
        L_0x01f8:
            r23 = r0
            r20 = r14
        L_0x01fc:
            java.lang.Iterable r7 = (java.lang.Iterable) r7
            java.util.Iterator r0 = r7.iterator()
        L_0x0202:
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto L_0x0290
            java.lang.Object r3 = r0.next()
            org.videolan.moviepedia.database.models.MediaMetadataWithImages r3 = (org.videolan.moviepedia.database.models.MediaMetadataWithImages) r3
            org.videolan.moviepedia.database.models.MediaMetadata r6 = r3.getMetadata()
            java.lang.Long r6 = r6.getMlId()
            if (r6 == 0) goto L_0x0202
            java.lang.Number r6 = (java.lang.Number) r6
            long r6 = r6.longValue()
            java.lang.Long r8 = java.lang.Long.valueOf(r6)
            r4.add(r8)
            org.videolan.medialibrary.interfaces.media.MediaWrapper r6 = r2.getMedia((long) r6)
            org.videolan.moviepedia.database.models.MediaMetadata r7 = r3.getMetadata()
            java.lang.String r7 = r7.getCurrentBackdrop()
            long r8 = r6.getId()
            java.lang.Long r8 = java.lang.Long.valueOf(r8)
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            java.lang.String r10 = "content_episode_"
            r9.<init>(r10)
            org.videolan.moviepedia.database.models.MediaMetadata r10 = r3.getMetadata()
            java.lang.String r10 = r10.getMoviepediaId()
            r9.append(r10)
            java.lang.String r9 = r9.toString()
            org.videolan.moviepedia.database.models.MediaMetadata r10 = r3.getMetadata()
            java.lang.String r10 = r10.getTitle()
            java.lang.String r11 = org.videolan.moviepedia.database.models.MediaMetadataKt.subtitle(r3)
            org.videolan.moviepedia.database.models.MediaMetadata r3 = r3.getMetadata()
            java.lang.String r3 = org.videolan.moviepedia.database.models.MediaMetadataKt.getYear(r3)
            long r14 = r6.getLength()
            java.lang.Long r6 = java.lang.Long.valueOf(r14)
            r12 = 7
            java.lang.Object[] r14 = new java.lang.Object[r12]
            r12 = 0
            r14[r12] = r8
            r8 = 1
            r14[r8] = r9
            r8 = 2
            r14[r8] = r10
            r8 = 3
            r14[r8] = r11
            r8 = 4
            r14[r8] = r7
            r7 = 5
            r14[r7] = r3
            r3 = 6
            r14[r3] = r6
            r5.addRow(r14)
            kotlin.Unit r3 = kotlin.Unit.INSTANCE
            kotlin.Unit r3 = kotlin.Unit.INSTANCE
            goto L_0x0202
        L_0x028c:
            r23 = r0
            r20 = r14
        L_0x0290:
            r11 = r29
            r14 = r20
            r15 = r21
            r0 = r23
            r3 = 7
            r6 = 0
            r7 = 1
            r8 = 2
            r9 = 3
            r10 = 4
            goto L_0x00be
        L_0x02a0:
            r21 = r15
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            goto L_0x02a9
        L_0x02a7:
            r21 = r15
        L_0x02a9:
            org.videolan.tools.Settings r0 = org.videolan.tools.Settings.INSTANCE
            boolean r0 = r0.getIncludeMissing()
            r3 = 0
            org.videolan.medialibrary.media.SearchAggregate r0 = r2.search(r1, r0, r3)
            if (r0 != 0) goto L_0x02b8
            r1 = 0
            return r1
        L_0x02b8:
            org.videolan.medialibrary.interfaces.media.Artist[] r1 = r0.getArtists()
            java.lang.String r2 = "getArtworkMrl(...)"
            if (r1 == 0) goto L_0x0339
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
            java.util.List r1 = kotlin.collections.ArraysKt.filterNotNull(r1)
            if (r1 == 0) goto L_0x0339
            java.lang.Iterable r1 = (java.lang.Iterable) r1
            java.util.Iterator r1 = r1.iterator()
        L_0x02cf:
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto L_0x0335
            java.lang.Object r3 = r1.next()
            org.videolan.medialibrary.interfaces.media.Artist r3 = (org.videolan.medialibrary.interfaces.media.Artist) r3
            java.lang.String r6 = r3.getArtworkMrl()
            if (r6 == 0) goto L_0x02ef
            java.lang.String r6 = r3.getArtworkMrl()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r2)
            android.net.Uri r6 = org.videolan.vlc.FileProviderKt.getFileUri(r6)
            java.lang.Comparable r6 = (java.lang.Comparable) r6
            goto L_0x02f2
        L_0x02ef:
            r6 = r13
            java.lang.Comparable r6 = (java.lang.Comparable) r6
        L_0x02f2:
            r7 = 7
            java.lang.Comparable[] r8 = new java.lang.Comparable[r7]
            long r9 = r3.getId()
            java.lang.Long r7 = java.lang.Long.valueOf(r9)
            r9 = 0
            r8[r9] = r7
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            java.lang.String r9 = "artist_"
            r7.<init>(r9)
            long r9 = r3.getId()
            r7.append(r9)
            java.lang.String r7 = r7.toString()
            r9 = 1
            r8[r9] = r7
            java.lang.String r7 = r3.getTitle()
            r9 = 2
            r8[r9] = r7
            java.lang.String r3 = r3.getDescription()
            r7 = 3
            r8[r7] = r3
            r3 = 4
            r8[r3] = r6
            r3 = 5
            r8[r3] = r13
            r3 = -1
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r6 = 6
            r8[r6] = r3
            r5.addRow(r8)
            goto L_0x02cf
        L_0x0335:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
        L_0x0339:
            org.videolan.medialibrary.interfaces.media.Album[] r1 = r0.getAlbums()
            if (r1 == 0) goto L_0x03c3
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
            java.util.List r1 = kotlin.collections.ArraysKt.filterNotNull(r1)
            if (r1 == 0) goto L_0x03c3
            java.lang.Iterable r1 = (java.lang.Iterable) r1
            java.util.Iterator r1 = r1.iterator()
        L_0x034e:
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto L_0x03bf
            java.lang.Object r3 = r1.next()
            org.videolan.medialibrary.interfaces.media.Album r3 = (org.videolan.medialibrary.interfaces.media.Album) r3
            java.lang.String r6 = r3.getArtworkMrl()
            if (r6 == 0) goto L_0x036e
            java.lang.String r6 = r3.getArtworkMrl()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r2)
            android.net.Uri r6 = org.videolan.vlc.FileProviderKt.getFileUri(r6)
            java.lang.Comparable r6 = (java.lang.Comparable) r6
            goto L_0x0371
        L_0x036e:
            r6 = r13
            java.lang.Comparable r6 = (java.lang.Comparable) r6
        L_0x0371:
            r7 = 7
            java.lang.Comparable[] r8 = new java.lang.Comparable[r7]
            long r9 = r3.getId()
            java.lang.Long r7 = java.lang.Long.valueOf(r9)
            r9 = 0
            r8[r9] = r7
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            java.lang.String r9 = "album_"
            r7.<init>(r9)
            long r9 = r3.getId()
            r7.append(r9)
            java.lang.String r7 = r7.toString()
            r9 = 1
            r8[r9] = r7
            java.lang.String r7 = r3.getTitle()
            r9 = 2
            r8[r9] = r7
            java.lang.String r7 = r3.getDescription()
            r9 = 3
            r8[r9] = r7
            r7 = 4
            r8[r7] = r6
            int r6 = r3.getReleaseYear()
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            r7 = 5
            r8[r7] = r6
            long r6 = r3.getDuration()
            java.lang.Long r3 = java.lang.Long.valueOf(r6)
            r6 = 6
            r8[r6] = r3
            r5.addRow(r8)
            goto L_0x034e
        L_0x03bf:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
        L_0x03c3:
            org.videolan.medialibrary.interfaces.media.MediaWrapper[] r1 = r0.getVideos()
            java.lang.String r2 = "getArtworkURL(...)"
            if (r1 == 0) goto L_0x0460
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
            java.util.List r1 = kotlin.collections.ArraysKt.filterNotNull(r1)
            if (r1 == 0) goto L_0x0460
            java.lang.Iterable r1 = (java.lang.Iterable) r1
            java.util.Iterator r1 = r1.iterator()
        L_0x03da:
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto L_0x0459
            java.lang.Object r3 = r1.next()
            org.videolan.medialibrary.interfaces.media.MediaWrapper r3 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r3
            long r6 = r3.getId()
            java.lang.Long r6 = java.lang.Long.valueOf(r6)
            boolean r6 = r4.contains(r6)
            if (r6 != 0) goto L_0x0454
            java.lang.String r6 = r3.getArtworkURL()
            if (r6 == 0) goto L_0x0406
            java.lang.String r6 = r3.getArtworkURL()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r2)
            android.net.Uri r6 = org.videolan.vlc.FileProviderKt.getFileUri(r6)
            goto L_0x040a
        L_0x0406:
            android.net.Uri r6 = org.videolan.television.util.TVSearchProviderKt.getThumb(r3)
        L_0x040a:
            r7 = 7
            java.lang.Comparable[] r8 = new java.lang.Comparable[r7]
            long r9 = r3.getId()
            java.lang.Long r7 = java.lang.Long.valueOf(r9)
            r9 = 0
            r8[r9] = r7
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r9 = r21
            r7.<init>(r9)
            long r10 = r3.getId()
            r7.append(r10)
            java.lang.String r7 = r7.toString()
            r10 = 1
            r8[r10] = r7
            java.lang.String r7 = r3.getTitle()
            r10 = 2
            r8[r10] = r7
            java.lang.String r7 = r3.getDescription()
            r10 = 3
            r8[r10] = r7
            r7 = 4
            r8[r7] = r6
            java.lang.String r6 = r3.getDate()
            r7 = 5
            r8[r7] = r6
            long r6 = r3.getLength()
            java.lang.Long r3 = java.lang.Long.valueOf(r6)
            r6 = 6
            r8[r6] = r3
            r5.addRow(r8)
            goto L_0x0456
        L_0x0454:
            r9 = r21
        L_0x0456:
            r21 = r9
            goto L_0x03da
        L_0x0459:
            r9 = r21
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            goto L_0x0462
        L_0x0460:
            r9 = r21
        L_0x0462:
            org.videolan.medialibrary.interfaces.media.MediaWrapper[] r0 = r0.getTracks()
            if (r0 == 0) goto L_0x04e9
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
            java.util.List r0 = kotlin.collections.ArraysKt.filterNotNull(r0)
            if (r0 == 0) goto L_0x04e9
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            java.util.Iterator r0 = r0.iterator()
        L_0x0477:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x04e5
            java.lang.Object r1 = r0.next()
            org.videolan.medialibrary.interfaces.media.MediaWrapper r1 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r1
            java.lang.String r3 = r1.getArtworkURL()
            if (r3 == 0) goto L_0x0495
            java.lang.String r3 = r1.getArtworkURL()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r2)
            android.net.Uri r3 = org.videolan.vlc.FileProviderKt.getFileUri(r3)
            goto L_0x0499
        L_0x0495:
            android.net.Uri r3 = org.videolan.television.util.TVSearchProviderKt.getThumb(r1)
        L_0x0499:
            r4 = 7
            java.lang.Comparable[] r6 = new java.lang.Comparable[r4]
            long r7 = r1.getId()
            java.lang.Long r7 = java.lang.Long.valueOf(r7)
            r8 = 0
            r6[r8] = r7
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>(r9)
            long r10 = r1.getId()
            r7.append(r10)
            java.lang.String r7 = r7.toString()
            r10 = 1
            r6[r10] = r7
            java.lang.String r7 = r1.getTitle()
            r11 = 2
            r6[r11] = r7
            java.lang.String r7 = r1.getDescription()
            r12 = 3
            r6[r12] = r7
            r7 = 4
            r6[r7] = r3
            int r3 = r1.getReleaseYear()
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r13 = 5
            r6[r13] = r3
            long r14 = r1.getLength()
            java.lang.Long r1 = java.lang.Long.valueOf(r14)
            r3 = 6
            r6[r3] = r1
            r5.addRow(r6)
            goto L_0x0477
        L_0x04e5:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
        L_0x04e9:
            r0 = r5
            goto L_0x04ed
        L_0x04eb:
            r1 = 0
            r0 = r1
        L_0x04ed:
            android.database.Cursor r0 = (android.database.Cursor) r0
            return r0
        L_0x04f0:
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "Invalid URI: "
            r2.<init>(r3)
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            r1.<init>(r0)
            goto L_0x0505
        L_0x0504:
            throw r1
        L_0x0505:
            goto L_0x0504
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.television.util.TVSearchProvider.query(android.net.Uri, java.lang.String[], java.lang.String, java.lang.String[], java.lang.String):android.database.Cursor");
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        Intrinsics.checkNotNullParameter(uri, Constants.KEY_URI);
        throw new UnsupportedOperationException("Requested operation not supported");
    }

    public int delete(Uri uri, String str, String[] strArr) {
        Intrinsics.checkNotNullParameter(uri, Constants.KEY_URI);
        throw new UnsupportedOperationException("Requested operation not supported");
    }
}
