package okhttp3.internal.cache;

import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "run"}, k = 3, mv = {1, 1, 15})
/* compiled from: DiskLruCache.kt */
final class DiskLruCache$cleanupRunnable$1 implements Runnable {
    final /* synthetic */ DiskLruCache this$0;

    DiskLruCache$cleanupRunnable$1(DiskLruCache diskLruCache) {
        this.this$0 = diskLruCache;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        r4.this$0.mostRecentRebuildFailed = true;
        r4.this$0.journalWriter = okio.Okio.buffer(okio.Okio.blackhole());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x004b, code lost:
        return;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:10:0x001b */
    /* JADX WARNING: Missing exception handler attribute for start block: B:16:0x0034 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
            r4 = this;
            okhttp3.internal.cache.DiskLruCache r0 = r4.this$0
            monitor-enter(r0)
            okhttp3.internal.cache.DiskLruCache r1 = r4.this$0     // Catch:{ all -> 0x004c }
            boolean r1 = r1.initialized     // Catch:{ all -> 0x004c }
            if (r1 == 0) goto L_0x004a
            okhttp3.internal.cache.DiskLruCache r1 = r4.this$0     // Catch:{ all -> 0x004c }
            boolean r1 = r1.getClosed$okhttp()     // Catch:{ all -> 0x004c }
            if (r1 == 0) goto L_0x0014
            goto L_0x004a
        L_0x0014:
            r1 = 1
            okhttp3.internal.cache.DiskLruCache r2 = r4.this$0     // Catch:{ IOException -> 0x001b }
            r2.trimToSize()     // Catch:{ IOException -> 0x001b }
            goto L_0x0020
        L_0x001b:
            okhttp3.internal.cache.DiskLruCache r2 = r4.this$0     // Catch:{ all -> 0x004c }
            r2.mostRecentTrimFailed = r1     // Catch:{ all -> 0x004c }
        L_0x0020:
            okhttp3.internal.cache.DiskLruCache r2 = r4.this$0     // Catch:{ IOException -> 0x0034 }
            boolean r2 = r2.journalRebuildRequired()     // Catch:{ IOException -> 0x0034 }
            if (r2 == 0) goto L_0x0046
            okhttp3.internal.cache.DiskLruCache r2 = r4.this$0     // Catch:{ IOException -> 0x0034 }
            r2.rebuildJournal$okhttp()     // Catch:{ IOException -> 0x0034 }
            okhttp3.internal.cache.DiskLruCache r2 = r4.this$0     // Catch:{ IOException -> 0x0034 }
            r3 = 0
            r2.redundantOpCount = r3     // Catch:{ IOException -> 0x0034 }
            goto L_0x0046
        L_0x0034:
            okhttp3.internal.cache.DiskLruCache r2 = r4.this$0     // Catch:{ all -> 0x004c }
            r2.mostRecentRebuildFailed = r1     // Catch:{ all -> 0x004c }
            okhttp3.internal.cache.DiskLruCache r1 = r4.this$0     // Catch:{ all -> 0x004c }
            okio.Sink r2 = okio.Okio.blackhole()     // Catch:{ all -> 0x004c }
            okio.BufferedSink r2 = okio.Okio.buffer((okio.Sink) r2)     // Catch:{ all -> 0x004c }
            r1.journalWriter = r2     // Catch:{ all -> 0x004c }
        L_0x0046:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x004c }
            monitor-exit(r0)
            return
        L_0x004a:
            monitor-exit(r0)
            return
        L_0x004c:
            r1 = move-exception
            monitor-exit(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.cache.DiskLruCache$cleanupRunnable$1.run():void");
    }
}
