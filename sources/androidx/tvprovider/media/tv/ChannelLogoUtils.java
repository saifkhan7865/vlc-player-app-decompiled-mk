package androidx.tvprovider.media.tv;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.tv.TvContract;
import android.util.Log;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class ChannelLogoUtils {
    private static final int CONNECTION_TIMEOUT_MS_FOR_URLCONNECTION = 3000;
    private static final int READ_TIMEOUT_MS_FOR_URLCONNECTION = 10000;
    private static final String TAG = "ChannelLogoUtils";

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v2, resolved type: android.graphics.Bitmap} */
    /* JADX WARNING: type inference failed for: r2v0 */
    /* JADX WARNING: type inference failed for: r2v1, types: [java.io.InputStream] */
    /* JADX WARNING: type inference failed for: r2v3 */
    /* JADX WARNING: type inference failed for: r2v4 */
    /* JADX WARNING: type inference failed for: r2v5 */
    /* JADX WARNING: type inference failed for: r2v7 */
    /* JADX WARNING: type inference failed for: r2v8 */
    /* JADX WARNING: type inference failed for: r2v9 */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x004d, code lost:
        if ((r1 instanceof java.net.HttpURLConnection) == false) goto L_0x0085;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x004f, code lost:
        ((java.net.HttpURLConnection) r1).disconnect();
        r2 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0082, code lost:
        if ((r1 instanceof java.net.HttpURLConnection) == false) goto L_0x0085;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0085, code lost:
        if (r2 == 0) goto L_0x008f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x008b, code lost:
        if (storeChannelLogo(r8, r9, r2) == false) goto L_0x008f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x008d, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x008f, code lost:
        return false;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0046 A[SYNTHETIC, Splitter:B:21:0x0046] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x007b A[SYNTHETIC, Splitter:B:39:0x007b] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0095 A[SYNTHETIC, Splitter:B:52:0x0095] */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x009e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean storeChannelLogo(android.content.Context r8, long r9, android.net.Uri r11) {
        /*
            java.lang.String r0 = "Failed to get logo from the URI: "
            android.net.Uri r1 = r11.normalizeScheme()
            java.lang.String r1 = r1.getScheme()
            r2 = 0
            java.lang.String r3 = "android.resource"
            boolean r3 = r3.equals(r1)     // Catch:{ IOException -> 0x0060, all -> 0x005d }
            if (r3 != 0) goto L_0x0037
            java.lang.String r3 = "file"
            boolean r3 = r3.equals(r1)     // Catch:{ IOException -> 0x0060, all -> 0x005d }
            if (r3 != 0) goto L_0x0037
            java.lang.String r3 = "content"
            boolean r1 = r3.equals(r1)     // Catch:{ IOException -> 0x0060, all -> 0x005d }
            if (r1 == 0) goto L_0x0024
            goto L_0x0037
        L_0x0024:
            java.lang.String r1 = r11.toString()     // Catch:{ IOException -> 0x0060, all -> 0x005d }
            java.net.URLConnection r1 = getUrlConnection(r1)     // Catch:{ IOException -> 0x0060, all -> 0x005d }
            java.io.InputStream r3 = r1.getInputStream()     // Catch:{ IOException -> 0x0034, all -> 0x0031 }
            goto L_0x0040
        L_0x0031:
            r8 = move-exception
            goto L_0x0093
        L_0x0034:
            r3 = move-exception
            r4 = r2
            goto L_0x0063
        L_0x0037:
            android.content.ContentResolver r1 = r8.getContentResolver()     // Catch:{ IOException -> 0x0060, all -> 0x005d }
            java.io.InputStream r3 = r1.openInputStream(r11)     // Catch:{ IOException -> 0x0060, all -> 0x005d }
            r1 = r2
        L_0x0040:
            android.graphics.Bitmap r2 = android.graphics.BitmapFactory.decodeStream(r3)     // Catch:{ IOException -> 0x0058, all -> 0x0055 }
            if (r3 == 0) goto L_0x004b
            r3.close()     // Catch:{ IOException -> 0x004a }
            goto L_0x004b
        L_0x004a:
        L_0x004b:
            boolean r11 = r1 instanceof java.net.HttpURLConnection
            if (r11 == 0) goto L_0x0085
        L_0x004f:
            java.net.HttpURLConnection r1 = (java.net.HttpURLConnection) r1
            r1.disconnect()
            goto L_0x0085
        L_0x0055:
            r8 = move-exception
            r2 = r3
            goto L_0x0093
        L_0x0058:
            r4 = move-exception
            r7 = r4
            r4 = r3
            r3 = r7
            goto L_0x0063
        L_0x005d:
            r8 = move-exception
            r1 = r2
            goto L_0x0093
        L_0x0060:
            r3 = move-exception
            r1 = r2
            r4 = r1
        L_0x0063:
            java.lang.String r5 = "ChannelLogoUtils"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x0091 }
            r6.<init>(r0)     // Catch:{ all -> 0x0091 }
            r6.append(r11)     // Catch:{ all -> 0x0091 }
            java.lang.String r11 = "\n"
            r6.append(r11)     // Catch:{ all -> 0x0091 }
            java.lang.String r11 = r6.toString()     // Catch:{ all -> 0x0091 }
            android.util.Log.i(r5, r11, r3)     // Catch:{ all -> 0x0091 }
            if (r4 == 0) goto L_0x0080
            r4.close()     // Catch:{ IOException -> 0x007f }
            goto L_0x0080
        L_0x007f:
        L_0x0080:
            boolean r11 = r1 instanceof java.net.HttpURLConnection
            if (r11 == 0) goto L_0x0085
            goto L_0x004f
        L_0x0085:
            if (r2 == 0) goto L_0x008f
            boolean r8 = storeChannelLogo((android.content.Context) r8, (long) r9, (android.graphics.Bitmap) r2)
            if (r8 == 0) goto L_0x008f
            r8 = 1
            goto L_0x0090
        L_0x008f:
            r8 = 0
        L_0x0090:
            return r8
        L_0x0091:
            r8 = move-exception
            r2 = r4
        L_0x0093:
            if (r2 == 0) goto L_0x009a
            r2.close()     // Catch:{ IOException -> 0x0099 }
            goto L_0x009a
        L_0x0099:
        L_0x009a:
            boolean r9 = r1 instanceof java.net.HttpURLConnection
            if (r9 == 0) goto L_0x00a3
            java.net.HttpURLConnection r1 = (java.net.HttpURLConnection) r1
            r1.disconnect()
        L_0x00a3:
            goto L_0x00a5
        L_0x00a4:
            throw r8
        L_0x00a5:
            goto L_0x00a4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.tvprovider.media.tv.ChannelLogoUtils.storeChannelLogo(android.content.Context, long, android.net.Uri):boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0020, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0021, code lost:
        if (r1 != null) goto L_0x0023;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x002b, code lost:
        throw r4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean storeChannelLogo(android.content.Context r1, long r2, android.graphics.Bitmap r4) {
        /*
            android.net.Uri r2 = android.media.tv.TvContract.buildChannelLogoUri(r2)
            r3 = 0
            android.content.ContentResolver r1 = r1.getContentResolver()     // Catch:{ SQLiteException -> 0x002e, IOException -> 0x002c }
            java.io.OutputStream r1 = r1.openOutputStream(r2)     // Catch:{ SQLiteException -> 0x002e, IOException -> 0x002c }
            android.graphics.Bitmap$CompressFormat r2 = android.graphics.Bitmap.CompressFormat.PNG     // Catch:{ all -> 0x001e }
            r0 = 100
            boolean r3 = r4.compress(r2, r0, r1)     // Catch:{ all -> 0x001e }
            r1.flush()     // Catch:{ all -> 0x001e }
            if (r1 == 0) goto L_0x0036
            r1.close()     // Catch:{ SQLiteException -> 0x002e, IOException -> 0x002c }
            goto L_0x0036
        L_0x001e:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x0020 }
        L_0x0020:
            r4 = move-exception
            if (r1 == 0) goto L_0x002b
            r1.close()     // Catch:{ all -> 0x0027 }
            goto L_0x002b
        L_0x0027:
            r1 = move-exception
            kotlin.UInt$$ExternalSyntheticBackport0.m((java.lang.Throwable) r2, (java.lang.Throwable) r1)     // Catch:{ SQLiteException -> 0x002e, IOException -> 0x002c }
        L_0x002b:
            throw r4     // Catch:{ SQLiteException -> 0x002e, IOException -> 0x002c }
        L_0x002c:
            r1 = move-exception
            goto L_0x002f
        L_0x002e:
            r1 = move-exception
        L_0x002f:
            java.lang.String r2 = "ChannelLogoUtils"
            java.lang.String r4 = "Failed to store the logo to the system content provider.\n"
            android.util.Log.i(r2, r4, r1)
        L_0x0036:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.tvprovider.media.tv.ChannelLogoUtils.storeChannelLogo(android.content.Context, long, android.graphics.Bitmap):boolean");
    }

    public static Bitmap loadChannelLogo(Context context, long j) {
        try {
            return BitmapFactory.decodeStream(context.getContentResolver().openInputStream(TvContract.buildChannelLogoUri(j)));
        } catch (FileNotFoundException e) {
            Log.i(TAG, "Channel logo for channel (ID:" + j + ") not found.", e);
            return null;
        }
    }

    private static URLConnection getUrlConnection(String str) throws IOException {
        URLConnection openConnection = new URL(str).openConnection();
        openConnection.setConnectTimeout(3000);
        openConnection.setReadTimeout(10000);
        return openConnection;
    }
}
