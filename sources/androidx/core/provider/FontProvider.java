package androidx.core.provider;

import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.os.CancellationSignal;
import androidx.core.content.res.FontResourcesParserCompat;
import androidx.core.provider.FontsContractCompat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class FontProvider {
    private static final Comparator<byte[]> sByteArrayComparator = new FontProvider$$ExternalSyntheticLambda0();

    private FontProvider() {
    }

    static FontsContractCompat.FontFamilyResult getFontFamilyResult(Context context, FontRequest fontRequest, CancellationSignal cancellationSignal) throws PackageManager.NameNotFoundException {
        ProviderInfo provider = getProvider(context.getPackageManager(), fontRequest, context.getResources());
        if (provider == null) {
            return FontsContractCompat.FontFamilyResult.create(1, (FontsContractCompat.FontInfo[]) null);
        }
        return FontsContractCompat.FontFamilyResult.create(0, query(context, fontRequest, provider.authority, cancellationSignal));
    }

    static ProviderInfo getProvider(PackageManager packageManager, FontRequest fontRequest, Resources resources) throws PackageManager.NameNotFoundException {
        String providerAuthority = fontRequest.getProviderAuthority();
        ProviderInfo resolveContentProvider = packageManager.resolveContentProvider(providerAuthority, 0);
        if (resolveContentProvider == null) {
            throw new PackageManager.NameNotFoundException("No package found for authority: " + providerAuthority);
        } else if (resolveContentProvider.packageName.equals(fontRequest.getProviderPackage())) {
            List<byte[]> convertToByteArrayList = convertToByteArrayList(packageManager.getPackageInfo(resolveContentProvider.packageName, 64).signatures);
            Collections.sort(convertToByteArrayList, sByteArrayComparator);
            List<List<byte[]>> certificates = getCertificates(fontRequest, resources);
            for (int i = 0; i < certificates.size(); i++) {
                ArrayList arrayList = new ArrayList(certificates.get(i));
                Collections.sort(arrayList, sByteArrayComparator);
                if (equalsByteArrayList(convertToByteArrayList, arrayList)) {
                    return resolveContentProvider;
                }
            }
            return null;
        } else {
            throw new PackageManager.NameNotFoundException("Found content provider " + providerAuthority + ", but package was not " + fontRequest.getProviderPackage());
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:36:0x00f1  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0102  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static androidx.core.provider.FontsContractCompat.FontInfo[] query(android.content.Context r18, androidx.core.provider.FontRequest r19, java.lang.String r20, android.os.CancellationSignal r21) {
        /*
            r0 = r20
            java.lang.String r1 = "result_code"
            java.lang.String r2 = "font_italic"
            java.lang.String r3 = "font_weight"
            java.lang.String r4 = "font_ttc_index"
            java.lang.String r5 = "file_id"
            java.lang.String r6 = "_id"
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
            android.net.Uri$Builder r8 = new android.net.Uri$Builder
            r8.<init>()
            java.lang.String r9 = "content"
            android.net.Uri$Builder r8 = r8.scheme(r9)
            android.net.Uri$Builder r8 = r8.authority(r0)
            android.net.Uri r8 = r8.build()
            android.net.Uri$Builder r10 = new android.net.Uri$Builder
            r10.<init>()
            android.net.Uri$Builder r9 = r10.scheme(r9)
            android.net.Uri$Builder r0 = r9.authority(r0)
            java.lang.String r9 = "file"
            android.net.Uri$Builder r0 = r0.appendPath(r9)
            android.net.Uri r0 = r0.build()
            r10 = 7
            java.lang.String[] r12 = new java.lang.String[r10]     // Catch:{ all -> 0x00fe }
            r15 = 0
            r12[r15] = r6     // Catch:{ all -> 0x00fe }
            r14 = 1
            r12[r14] = r5     // Catch:{ all -> 0x00fe }
            r10 = 2
            r12[r10] = r4     // Catch:{ all -> 0x00fe }
            java.lang.String r10 = "font_variation_settings"
            r11 = 3
            r12[r11] = r10     // Catch:{ all -> 0x00fe }
            r10 = 4
            r12[r10] = r3     // Catch:{ all -> 0x00fe }
            r10 = 5
            r12[r10] = r2     // Catch:{ all -> 0x00fe }
            r10 = 6
            r12[r10] = r1     // Catch:{ all -> 0x00fe }
            android.content.ContentResolver r10 = r18.getContentResolver()     // Catch:{ all -> 0x00fe }
            java.lang.String r13 = "query = ?"
            java.lang.String r11 = r19.getQuery()     // Catch:{ all -> 0x00fe }
            java.lang.String[] r9 = new java.lang.String[r14]     // Catch:{ all -> 0x00fe }
            r9[r15] = r11     // Catch:{ all -> 0x00fe }
            r16 = 0
            r11 = r8
            r17 = r7
            r7 = 1
            r14 = r9
            r9 = 0
            r15 = r16
            r16 = r21
            android.database.Cursor r10 = androidx.core.provider.FontProvider.Api16Impl.query(r10, r11, r12, r13, r14, r15, r16)     // Catch:{ all -> 0x00fe }
            if (r10 == 0) goto L_0x00ed
            int r11 = r10.getCount()     // Catch:{ all -> 0x00ea }
            if (r11 <= 0) goto L_0x00ed
            int r1 = r10.getColumnIndex(r1)     // Catch:{ all -> 0x00ea }
            java.util.ArrayList r11 = new java.util.ArrayList     // Catch:{ all -> 0x00ea }
            r11.<init>()     // Catch:{ all -> 0x00ea }
            int r6 = r10.getColumnIndex(r6)     // Catch:{ all -> 0x00ea }
            int r5 = r10.getColumnIndex(r5)     // Catch:{ all -> 0x00ea }
            int r4 = r10.getColumnIndex(r4)     // Catch:{ all -> 0x00ea }
            int r3 = r10.getColumnIndex(r3)     // Catch:{ all -> 0x00ea }
            int r2 = r10.getColumnIndex(r2)     // Catch:{ all -> 0x00ea }
        L_0x009a:
            boolean r12 = r10.moveToNext()     // Catch:{ all -> 0x00ea }
            if (r12 == 0) goto L_0x00e8
            r12 = -1
            if (r1 == r12) goto L_0x00a8
            int r15 = r10.getInt(r1)     // Catch:{ all -> 0x00ea }
            goto L_0x00a9
        L_0x00a8:
            r15 = 0
        L_0x00a9:
            if (r4 == r12) goto L_0x00b0
            int r13 = r10.getInt(r4)     // Catch:{ all -> 0x00ea }
            goto L_0x00b1
        L_0x00b0:
            r13 = 0
        L_0x00b1:
            if (r5 != r12) goto L_0x00bd
            r14 = r13
            long r12 = r10.getLong(r6)     // Catch:{ all -> 0x00ea }
            android.net.Uri r12 = android.content.ContentUris.withAppendedId(r8, r12)     // Catch:{ all -> 0x00ea }
            goto L_0x00c6
        L_0x00bd:
            r14 = r13
            long r12 = r10.getLong(r5)     // Catch:{ all -> 0x00ea }
            android.net.Uri r12 = android.content.ContentUris.withAppendedId(r0, r12)     // Catch:{ all -> 0x00ea }
        L_0x00c6:
            r13 = -1
            if (r3 == r13) goto L_0x00d0
            int r16 = r10.getInt(r3)     // Catch:{ all -> 0x00ea }
            r9 = r16
            goto L_0x00d4
        L_0x00d0:
            r16 = 400(0x190, float:5.6E-43)
            r9 = 400(0x190, float:5.6E-43)
        L_0x00d4:
            if (r2 == r13) goto L_0x00de
            int r13 = r10.getInt(r2)     // Catch:{ all -> 0x00ea }
            if (r13 != r7) goto L_0x00de
            r13 = 1
            goto L_0x00df
        L_0x00de:
            r13 = 0
        L_0x00df:
            androidx.core.provider.FontsContractCompat$FontInfo r9 = androidx.core.provider.FontsContractCompat.FontInfo.create(r12, r14, r9, r13, r15)     // Catch:{ all -> 0x00ea }
            r11.add(r9)     // Catch:{ all -> 0x00ea }
            r9 = 0
            goto L_0x009a
        L_0x00e8:
            r7 = r11
            goto L_0x00ef
        L_0x00ea:
            r0 = move-exception
            r9 = r10
            goto L_0x0100
        L_0x00ed:
            r7 = r17
        L_0x00ef:
            if (r10 == 0) goto L_0x00f4
            r10.close()
        L_0x00f4:
            r0 = 0
            androidx.core.provider.FontsContractCompat$FontInfo[] r0 = new androidx.core.provider.FontsContractCompat.FontInfo[r0]
            java.lang.Object[] r0 = r7.toArray(r0)
            androidx.core.provider.FontsContractCompat$FontInfo[] r0 = (androidx.core.provider.FontsContractCompat.FontInfo[]) r0
            return r0
        L_0x00fe:
            r0 = move-exception
            r9 = 0
        L_0x0100:
            if (r9 == 0) goto L_0x0105
            r9.close()
        L_0x0105:
            goto L_0x0107
        L_0x0106:
            throw r0
        L_0x0107:
            goto L_0x0106
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.provider.FontProvider.query(android.content.Context, androidx.core.provider.FontRequest, java.lang.String, android.os.CancellationSignal):androidx.core.provider.FontsContractCompat$FontInfo[]");
    }

    private static List<List<byte[]>> getCertificates(FontRequest fontRequest, Resources resources) {
        if (fontRequest.getCertificates() != null) {
            return fontRequest.getCertificates();
        }
        return FontResourcesParserCompat.readCerts(resources, fontRequest.getCertificatesArrayResId());
    }

    static /* synthetic */ int lambda$static$0(byte[] bArr, byte[] bArr2) {
        if (bArr.length != bArr2.length) {
            return bArr.length - bArr2.length;
        }
        for (int i = 0; i < bArr.length; i++) {
            byte b = bArr[i];
            byte b2 = bArr2[i];
            if (b != b2) {
                return b - b2;
            }
        }
        return 0;
    }

    private static boolean equalsByteArrayList(List<byte[]> list, List<byte[]> list2) {
        if (list.size() != list2.size()) {
            return false;
        }
        for (int i = 0; i < list.size(); i++) {
            if (!Arrays.equals(list.get(i), list2.get(i))) {
                return false;
            }
        }
        return true;
    }

    private static List<byte[]> convertToByteArrayList(Signature[] signatureArr) {
        ArrayList arrayList = new ArrayList();
        for (Signature byteArray : signatureArr) {
            arrayList.add(byteArray.toByteArray());
        }
        return arrayList;
    }

    static class Api16Impl {
        private Api16Impl() {
        }

        static Cursor query(ContentResolver contentResolver, Uri uri, String[] strArr, String str, String[] strArr2, String str2, Object obj) {
            return contentResolver.query(uri, strArr, str, strArr2, str2, (CancellationSignal) obj);
        }
    }
}
