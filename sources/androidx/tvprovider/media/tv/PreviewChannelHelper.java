package androidx.tvprovider.media.tv;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import androidx.tvprovider.media.tv.PreviewChannel;
import androidx.tvprovider.media.tv.TvContractCompat;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PreviewChannelHelper {
    private static final int DEFAULT_READ_TIMEOUT_MILLIS = 10000;
    private static final int DEFAULT_URL_CONNNECTION_TIMEOUT_MILLIS = 3000;
    private static final int INVALID_CONTENT_ID = -1;
    private static final String TAG = "PreviewChannelHelper";
    private final Context mContext;
    private final int mUrlConnectionTimeoutMillis;
    private final int mUrlReadTimeoutMillis;

    public PreviewChannelHelper(Context context) {
        this(context, 3000, 10000);
    }

    public PreviewChannelHelper(Context context, int i, int i2) {
        this.mContext = context;
        this.mUrlConnectionTimeoutMillis = i;
        this.mUrlReadTimeoutMillis = i2;
    }

    public long publishChannel(PreviewChannel previewChannel) throws IOException {
        try {
            Uri insert = this.mContext.getContentResolver().insert(TvContractCompat.Channels.CONTENT_URI, previewChannel.toContentValues());
            if (insert == null || insert.equals(Uri.EMPTY)) {
                throw new NullPointerException("Channel insertion failed");
            }
            long parseId = ContentUris.parseId(insert);
            if (addChannelLogo(parseId, previewChannel)) {
                return parseId;
            }
            deletePreviewChannel(parseId);
            throw new IOException("Failed to add logo, so channel (ID=" + parseId + ") was not created");
        } catch (SecurityException e) {
            Log.e(TAG, "Your app's ability to insert data into the TvProvider may have been revoked.", e);
            return -1;
        }
    }

    public long publishDefaultChannel(PreviewChannel previewChannel) throws IOException {
        long publishChannel = publishChannel(previewChannel);
        TvContractCompat.requestChannelBrowsable(this.mContext, publishChannel);
        return publishChannel;
    }

    public List<PreviewChannel> getAllChannels() {
        Cursor query = this.mContext.getContentResolver().query(TvContractCompat.Channels.CONTENT_URI, PreviewChannel.Columns.PROJECTION, (String) null, (String[]) null, (String) null);
        ArrayList arrayList = new ArrayList();
        if (query == null || !query.moveToFirst()) {
            return arrayList;
        }
        do {
            arrayList.add(PreviewChannel.fromCursor(query));
        } while (query.moveToNext());
        return arrayList;
    }

    public PreviewChannel getPreviewChannel(long j) {
        Cursor query = this.mContext.getContentResolver().query(TvContractCompat.buildChannelUri(j), PreviewChannel.Columns.PROJECTION, (String) null, (String[]) null, (String) null);
        if (query == null || !query.moveToFirst()) {
            return null;
        }
        return PreviewChannel.fromCursor(query);
    }

    public void updatePreviewChannel(long j, PreviewChannel previewChannel) throws IOException {
        PreviewChannel previewChannel2 = getPreviewChannel(j);
        if (previewChannel2 != null && previewChannel2.hasAnyUpdatedValues(previewChannel)) {
            updatePreviewChannelInternal(j, previewChannel);
        }
        if (previewChannel.isLogoChanged() && !addChannelLogo(j, previewChannel)) {
            throw new IOException("Fail to update channel (ID=" + j + ") logo.");
        }
    }

    /* access modifiers changed from: protected */
    public void updatePreviewChannelInternal(long j, PreviewChannel previewChannel) {
        this.mContext.getContentResolver().update(TvContractCompat.buildChannelUri(j), previewChannel.toContentValues(), (String) null, (String[]) null);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0039, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x003a, code lost:
        if (r7 != null) goto L_0x003c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        r7.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0044, code lost:
        throw r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean addChannelLogo(long r5, androidx.tvprovider.media.tv.PreviewChannel r7) {
        /*
            r4 = this;
            boolean r0 = r7.isLogoChanged()
            r1 = 0
            if (r0 != 0) goto L_0x0008
            return r1
        L_0x0008:
            android.content.Context r0 = r4.mContext
            android.graphics.Bitmap r0 = r7.getLogo(r0)
            if (r0 != 0) goto L_0x0018
            android.net.Uri r7 = r7.getLogoUri()
            android.graphics.Bitmap r0 = r4.getLogoFromUri(r7)
        L_0x0018:
            android.net.Uri r7 = androidx.tvprovider.media.tv.TvContractCompat.buildChannelLogoUri((long) r5)
            android.content.Context r2 = r4.mContext     // Catch:{ SQLiteException -> 0x0049, IOException -> 0x0047, NullPointerException -> 0x0045 }
            android.content.ContentResolver r2 = r2.getContentResolver()     // Catch:{ SQLiteException -> 0x0049, IOException -> 0x0047, NullPointerException -> 0x0045 }
            java.io.OutputStream r7 = r2.openOutputStream(r7)     // Catch:{ SQLiteException -> 0x0049, IOException -> 0x0047, NullPointerException -> 0x0045 }
            android.graphics.Bitmap$CompressFormat r2 = android.graphics.Bitmap.CompressFormat.PNG     // Catch:{ all -> 0x0037 }
            r3 = 100
            boolean r1 = r0.compress(r2, r3, r7)     // Catch:{ all -> 0x0037 }
            r7.flush()     // Catch:{ all -> 0x0037 }
            if (r7 == 0) goto L_0x0062
            r7.close()     // Catch:{ SQLiteException -> 0x0049, IOException -> 0x0047, NullPointerException -> 0x0045 }
            goto L_0x0062
        L_0x0037:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x0039 }
        L_0x0039:
            r2 = move-exception
            if (r7 == 0) goto L_0x0044
            r7.close()     // Catch:{ all -> 0x0040 }
            goto L_0x0044
        L_0x0040:
            r7 = move-exception
            kotlin.UInt$$ExternalSyntheticBackport0.m((java.lang.Throwable) r0, (java.lang.Throwable) r7)     // Catch:{ SQLiteException -> 0x0049, IOException -> 0x0047, NullPointerException -> 0x0045 }
        L_0x0044:
            throw r2     // Catch:{ SQLiteException -> 0x0049, IOException -> 0x0047, NullPointerException -> 0x0045 }
        L_0x0045:
            r7 = move-exception
            goto L_0x004a
        L_0x0047:
            r7 = move-exception
            goto L_0x004a
        L_0x0049:
            r7 = move-exception
        L_0x004a:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r2 = "Failed to add logo to the published channel (ID= "
            r0.<init>(r2)
            r0.append(r5)
            java.lang.String r5 = ")"
            r0.append(r5)
            java.lang.String r5 = r0.toString()
            java.lang.String r6 = "PreviewChannelHelper"
            android.util.Log.i(r6, r5, r7)
        L_0x0062:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.tvprovider.media.tv.PreviewChannelHelper.addChannelLogo(long, androidx.tvprovider.media.tv.PreviewChannel):boolean");
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x003a A[SYNTHETIC, Splitter:B:15:0x003a] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x005e A[SYNTHETIC, Splitter:B:30:0x005e] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0066 A[SYNTHETIC, Splitter:B:36:0x0066] */
    /* JADX WARNING: Removed duplicated region for block: B:43:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.graphics.Bitmap getLogoFromUri(android.net.Uri r8) {
        /*
            r7 = this;
            java.lang.String r0 = "Failed to get logo from the URI: "
            android.net.Uri r1 = r8.normalizeScheme()
            java.lang.String r1 = r1.getScheme()
            r2 = 0
            java.lang.String r3 = "android.resource"
            boolean r3 = r3.equals(r1)     // Catch:{ IOException -> 0x0049, all -> 0x0047 }
            if (r3 != 0) goto L_0x0029
            java.lang.String r3 = "file"
            boolean r3 = r3.equals(r1)     // Catch:{ IOException -> 0x0049, all -> 0x0047 }
            if (r3 != 0) goto L_0x0029
            java.lang.String r3 = "content"
            boolean r1 = r3.equals(r1)     // Catch:{ IOException -> 0x0049, all -> 0x0047 }
            if (r1 == 0) goto L_0x0024
            goto L_0x0029
        L_0x0024:
            android.graphics.Bitmap r8 = r7.downloadBitmap(r8)     // Catch:{ IOException -> 0x0049, all -> 0x0047 }
            goto L_0x0038
        L_0x0029:
            android.content.Context r1 = r7.mContext     // Catch:{ IOException -> 0x0049, all -> 0x0047 }
            android.content.ContentResolver r1 = r1.getContentResolver()     // Catch:{ IOException -> 0x0049, all -> 0x0047 }
            java.io.InputStream r1 = r1.openInputStream(r8)     // Catch:{ IOException -> 0x0049, all -> 0x0047 }
            android.graphics.Bitmap r8 = android.graphics.BitmapFactory.decodeStream(r1)     // Catch:{ IOException -> 0x0042, all -> 0x003f }
            r2 = r1
        L_0x0038:
            if (r2 == 0) goto L_0x003d
            r2.close()     // Catch:{ IOException -> 0x003d }
        L_0x003d:
            r2 = r8
            goto L_0x0061
        L_0x003f:
            r8 = move-exception
            r2 = r1
            goto L_0x0064
        L_0x0042:
            r3 = move-exception
            r6 = r3
            r3 = r1
            r1 = r6
            goto L_0x004b
        L_0x0047:
            r8 = move-exception
            goto L_0x0064
        L_0x0049:
            r1 = move-exception
            r3 = r2
        L_0x004b:
            java.lang.String r4 = "PreviewChannelHelper"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x0062 }
            r5.<init>(r0)     // Catch:{ all -> 0x0062 }
            r5.append(r8)     // Catch:{ all -> 0x0062 }
            java.lang.String r8 = r5.toString()     // Catch:{ all -> 0x0062 }
            android.util.Log.e(r4, r8, r1)     // Catch:{ all -> 0x0062 }
            if (r3 == 0) goto L_0x0061
            r3.close()     // Catch:{ IOException -> 0x0061 }
        L_0x0061:
            return r2
        L_0x0062:
            r8 = move-exception
            r2 = r3
        L_0x0064:
            if (r2 == 0) goto L_0x0069
            r2.close()     // Catch:{ IOException -> 0x0069 }
        L_0x0069:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.tvprovider.media.tv.PreviewChannelHelper.getLogoFromUri(android.net.Uri):android.graphics.Bitmap");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v1, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v3, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v5, resolved type: java.net.URLConnection} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v7, resolved type: java.io.InputStream} */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0037 A[SYNTHETIC, Splitter:B:17:0x0037] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0040  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.graphics.Bitmap downloadBitmap(android.net.Uri r3) throws java.io.IOException {
        /*
            r2 = this;
            r0 = 0
            java.net.URL r1 = new java.net.URL     // Catch:{ all -> 0x0033 }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x0033 }
            r1.<init>(r3)     // Catch:{ all -> 0x0033 }
            java.net.URLConnection r3 = r1.openConnection()     // Catch:{ all -> 0x0033 }
            int r1 = r2.mUrlConnectionTimeoutMillis     // Catch:{ all -> 0x0031 }
            r3.setConnectTimeout(r1)     // Catch:{ all -> 0x0031 }
            int r1 = r2.mUrlReadTimeoutMillis     // Catch:{ all -> 0x0031 }
            r3.setReadTimeout(r1)     // Catch:{ all -> 0x0031 }
            java.io.InputStream r0 = r3.getInputStream()     // Catch:{ all -> 0x0031 }
            android.graphics.Bitmap r1 = android.graphics.BitmapFactory.decodeStream(r0)     // Catch:{ all -> 0x0031 }
            if (r0 == 0) goto L_0x0027
            r0.close()     // Catch:{ IOException -> 0x0026 }
            goto L_0x0027
        L_0x0026:
        L_0x0027:
            boolean r0 = r3 instanceof java.net.HttpURLConnection
            if (r0 == 0) goto L_0x0030
            java.net.HttpURLConnection r3 = (java.net.HttpURLConnection) r3
            r3.disconnect()
        L_0x0030:
            return r1
        L_0x0031:
            r1 = move-exception
            goto L_0x0035
        L_0x0033:
            r1 = move-exception
            r3 = r0
        L_0x0035:
            if (r0 == 0) goto L_0x003c
            r0.close()     // Catch:{ IOException -> 0x003b }
            goto L_0x003c
        L_0x003b:
        L_0x003c:
            boolean r0 = r3 instanceof java.net.HttpURLConnection
            if (r0 == 0) goto L_0x0045
            java.net.HttpURLConnection r3 = (java.net.HttpURLConnection) r3
            r3.disconnect()
        L_0x0045:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.tvprovider.media.tv.PreviewChannelHelper.downloadBitmap(android.net.Uri):android.graphics.Bitmap");
    }

    public void deletePreviewChannel(long j) {
        this.mContext.getContentResolver().delete(TvContractCompat.buildChannelUri(j), (String) null, (String[]) null);
    }

    public long publishPreviewProgram(PreviewProgram previewProgram) {
        try {
            return ContentUris.parseId(this.mContext.getContentResolver().insert(TvContractCompat.PreviewPrograms.CONTENT_URI, previewProgram.toContentValues()));
        } catch (SecurityException e) {
            Log.e(TAG, "Your app's ability to insert data into the TvProvider may have been revoked.", e);
            return -1;
        }
    }

    public PreviewProgram getPreviewProgram(long j) {
        Cursor query = this.mContext.getContentResolver().query(TvContractCompat.buildPreviewProgramUri(j), (String[]) null, (String) null, (String[]) null, (String) null);
        if (query == null || !query.moveToFirst()) {
            return null;
        }
        return PreviewProgram.fromCursor(query);
    }

    public void updatePreviewProgram(long j, PreviewProgram previewProgram) {
        PreviewProgram previewProgram2 = getPreviewProgram(j);
        if (previewProgram2 != null && previewProgram2.hasAnyUpdatedValues(previewProgram)) {
            updatePreviewProgramInternal(j, previewProgram);
        }
    }

    /* access modifiers changed from: package-private */
    public void updatePreviewProgramInternal(long j, PreviewProgram previewProgram) {
        this.mContext.getContentResolver().update(TvContractCompat.buildPreviewProgramUri(j), previewProgram.toContentValues(), (String) null, (String[]) null);
    }

    public void deletePreviewProgram(long j) {
        this.mContext.getContentResolver().delete(TvContractCompat.buildPreviewProgramUri(j), (String) null, (String[]) null);
    }

    public long publishWatchNextProgram(WatchNextProgram watchNextProgram) {
        try {
            return ContentUris.parseId(this.mContext.getContentResolver().insert(TvContractCompat.WatchNextPrograms.CONTENT_URI, watchNextProgram.toContentValues()));
        } catch (SecurityException e) {
            Log.e(TAG, "Your app's ability to insert data into the TvProvider may have been revoked.", e);
            return -1;
        }
    }

    public WatchNextProgram getWatchNextProgram(long j) {
        Cursor query = this.mContext.getContentResolver().query(TvContractCompat.buildWatchNextProgramUri(j), (String[]) null, (String) null, (String[]) null, (String) null);
        if (query == null || !query.moveToFirst()) {
            return null;
        }
        return WatchNextProgram.fromCursor(query);
    }

    public void updateWatchNextProgram(WatchNextProgram watchNextProgram, long j) {
        WatchNextProgram watchNextProgram2 = getWatchNextProgram(j);
        if (watchNextProgram2 != null && watchNextProgram2.hasAnyUpdatedValues(watchNextProgram)) {
            updateWatchNextProgram(j, watchNextProgram);
        }
    }

    /* access modifiers changed from: package-private */
    public void updateWatchNextProgram(long j, WatchNextProgram watchNextProgram) {
        this.mContext.getContentResolver().update(TvContractCompat.buildWatchNextProgramUri(j), watchNextProgram.toContentValues(), (String) null, (String[]) null);
    }
}
