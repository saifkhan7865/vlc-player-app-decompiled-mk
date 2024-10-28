package com.google.android.material.color;

final class ColorResourcesLoaderCreator {
    private static final String TAG = "ColorResLoaderCreator";

    private ColorResourcesLoaderCreator() {
    }

    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0068, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:?, code lost:
        kotlin.UInt$$ExternalSyntheticBackport0.m(r5, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x006d, code lost:
        r5 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:?, code lost:
        android.system.Os.close(r6);
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:10:0x0028, B:40:0x0064] */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x0073 A[SYNTHETIC, Splitter:B:50:0x0073] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static android.content.res.loader.ResourcesLoader create(android.content.Context r5, java.util.Map<java.lang.Integer, java.lang.Integer> r6) {
        /*
            java.lang.String r0 = "ColorResLoaderCreator"
            java.lang.String r1 = "Table created, length: "
            r2 = 0
            byte[] r5 = com.google.android.material.color.ColorResourcesTableCreator.create(r5, r6)     // Catch:{ Exception -> 0x0077 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0077 }
            r6.<init>(r1)     // Catch:{ Exception -> 0x0077 }
            int r1 = r5.length     // Catch:{ Exception -> 0x0077 }
            r6.append(r1)     // Catch:{ Exception -> 0x0077 }
            java.lang.String r6 = r6.toString()     // Catch:{ Exception -> 0x0077 }
            android.util.Log.i(r0, r6)     // Catch:{ Exception -> 0x0077 }
            int r6 = r5.length     // Catch:{ Exception -> 0x0077 }
            if (r6 != 0) goto L_0x001d
            return r2
        L_0x001d:
            java.lang.String r6 = "temp.arsc"
            r1 = 0
            java.io.FileDescriptor r6 = android.system.Os.memfd_create(r6, r1)     // Catch:{ all -> 0x006f }
            if (r6 != 0) goto L_0x0031
            java.lang.String r5 = "Cannot create memory file descriptor."
            android.util.Log.w(r0, r5)     // Catch:{ all -> 0x006d }
            if (r6 == 0) goto L_0x0030
            android.system.Os.close(r6)     // Catch:{ Exception -> 0x0077 }
        L_0x0030:
            return r2
        L_0x0031:
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ all -> 0x006d }
            r1.<init>(r6)     // Catch:{ all -> 0x006d }
            r1.write(r5)     // Catch:{ all -> 0x0063 }
            android.os.ParcelFileDescriptor r5 = android.os.ParcelFileDescriptor.dup(r6)     // Catch:{ all -> 0x0063 }
            android.content.res.loader.ResourcesLoader r3 = new android.content.res.loader.ResourcesLoader     // Catch:{ all -> 0x0057 }
            r3.<init>()     // Catch:{ all -> 0x0057 }
            android.content.res.loader.ResourcesProvider r4 = android.content.res.loader.ResourcesProvider.loadFromTable(r5, r2)     // Catch:{ all -> 0x0057 }
            r3.addProvider(r4)     // Catch:{ all -> 0x0057 }
            if (r5 == 0) goto L_0x004e
            r5.close()     // Catch:{ all -> 0x0063 }
        L_0x004e:
            r1.close()     // Catch:{ all -> 0x006d }
            if (r6 == 0) goto L_0x0056
            android.system.Os.close(r6)     // Catch:{ Exception -> 0x0077 }
        L_0x0056:
            return r3
        L_0x0057:
            r3 = move-exception
            if (r5 == 0) goto L_0x0062
            r5.close()     // Catch:{ all -> 0x005e }
            goto L_0x0062
        L_0x005e:
            r5 = move-exception
            kotlin.UInt$$ExternalSyntheticBackport0.m((java.lang.Throwable) r3, (java.lang.Throwable) r5)     // Catch:{ all -> 0x0063 }
        L_0x0062:
            throw r3     // Catch:{ all -> 0x0063 }
        L_0x0063:
            r5 = move-exception
            r1.close()     // Catch:{ all -> 0x0068 }
            goto L_0x006c
        L_0x0068:
            r1 = move-exception
            kotlin.UInt$$ExternalSyntheticBackport0.m((java.lang.Throwable) r5, (java.lang.Throwable) r1)     // Catch:{ all -> 0x006d }
        L_0x006c:
            throw r5     // Catch:{ all -> 0x006d }
        L_0x006d:
            r5 = move-exception
            goto L_0x0071
        L_0x006f:
            r5 = move-exception
            r6 = r2
        L_0x0071:
            if (r6 == 0) goto L_0x0076
            android.system.Os.close(r6)     // Catch:{ Exception -> 0x0077 }
        L_0x0076:
            throw r5     // Catch:{ Exception -> 0x0077 }
        L_0x0077:
            r5 = move-exception
            java.lang.String r6 = "Failed to create the ColorResourcesTableCreator."
            android.util.Log.e(r0, r6, r5)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.color.ColorResourcesLoaderCreator.create(android.content.Context, java.util.Map):android.content.res.loader.ResourcesLoader");
    }
}
