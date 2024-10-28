package androidx.paging;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J$\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000bH&ø\u0001\u0000\u0002\u0006\n\u0004\b!0\u0001¨\u0006\fÀ\u0006\u0003"}, d2 = {"Landroidx/paging/Logger;", "", "isLoggable", "", "level", "", "log", "", "message", "", "tr", "", "paging-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: Logger.kt */
public interface Logger {

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* compiled from: Logger.kt */
    public static final class DefaultImpls {
    }

    boolean isLoggable(int i);

    void log(int i, String str, Throwable th);

    /* renamed from: androidx.paging.Logger$-CC  reason: invalid class name */
    /* compiled from: Logger.kt */
    public final /* synthetic */ class CC {
        public static /* synthetic */ void log$default(Logger logger, int i, String str, Throwable th, int i2, Object obj) {
            if (obj == null) {
                if ((i2 & 4) != 0) {
                    th = null;
                }
                logger.log(i, str, th);
                return;
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: log");
        }
    }
}
