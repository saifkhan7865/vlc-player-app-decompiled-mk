package videolan.org.commontools;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Lvideolan/org/commontools/TvPreviewProgram;", "", "internalId", "", "programId", "title", "", "(JJLjava/lang/String;)V", "getInternalId", "()J", "getProgramId", "getTitle", "()Ljava/lang/String;", "tools_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: TvChannelUtils.kt */
public final class TvPreviewProgram {
    private final long internalId;
    private final long programId;
    private final String title;

    public TvPreviewProgram(long j, long j2, String str) {
        Intrinsics.checkNotNullParameter(str, "title");
        this.internalId = j;
        this.programId = j2;
        this.title = str;
    }

    public final long getInternalId() {
        return this.internalId;
    }

    public final long getProgramId() {
        return this.programId;
    }

    public final String getTitle() {
        return this.title;
    }
}
