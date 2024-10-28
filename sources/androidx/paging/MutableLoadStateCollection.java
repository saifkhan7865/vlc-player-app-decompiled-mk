package androidx.paging;

import androidx.paging.LoadState;
import io.ktor.server.auth.OAuth2RequestParameters;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u0011J\u000e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015J\u0016\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u0004J\u0006\u0010\u0018\u001a\u00020\u0015R\u001a\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001a\u0010\f\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u0006\"\u0004\b\u000e\u0010\b¨\u0006\u0019"}, d2 = {"Landroidx/paging/MutableLoadStateCollection;", "", "()V", "append", "Landroidx/paging/LoadState;", "getAppend", "()Landroidx/paging/LoadState;", "setAppend", "(Landroidx/paging/LoadState;)V", "prepend", "getPrepend", "setPrepend", "refresh", "getRefresh", "setRefresh", "get", "loadType", "Landroidx/paging/LoadType;", "set", "", "states", "Landroidx/paging/LoadStates;", "type", "state", "snapshot", "paging-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: MutableLoadStateCollection.kt */
public final class MutableLoadStateCollection {
    private LoadState append = LoadState.NotLoading.Companion.getIncomplete$paging_common();
    private LoadState prepend = LoadState.NotLoading.Companion.getIncomplete$paging_common();
    private LoadState refresh = LoadState.NotLoading.Companion.getIncomplete$paging_common();

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* compiled from: MutableLoadStateCollection.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(9:0|1|2|3|4|5|6|7|9) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        static {
            /*
                androidx.paging.LoadType[] r0 = androidx.paging.LoadType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                androidx.paging.LoadType r1 = androidx.paging.LoadType.REFRESH     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                androidx.paging.LoadType r1 = androidx.paging.LoadType.APPEND     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                androidx.paging.LoadType r1 = androidx.paging.LoadType.PREPEND     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.paging.MutableLoadStateCollection.WhenMappings.<clinit>():void");
        }
    }

    public final LoadState getRefresh() {
        return this.refresh;
    }

    public final void setRefresh(LoadState loadState) {
        Intrinsics.checkNotNullParameter(loadState, "<set-?>");
        this.refresh = loadState;
    }

    public final LoadState getPrepend() {
        return this.prepend;
    }

    public final void setPrepend(LoadState loadState) {
        Intrinsics.checkNotNullParameter(loadState, "<set-?>");
        this.prepend = loadState;
    }

    public final LoadState getAppend() {
        return this.append;
    }

    public final void setAppend(LoadState loadState) {
        Intrinsics.checkNotNullParameter(loadState, "<set-?>");
        this.append = loadState;
    }

    public final LoadStates snapshot() {
        return new LoadStates(this.refresh, this.prepend, this.append);
    }

    public final LoadState get(LoadType loadType) {
        Intrinsics.checkNotNullParameter(loadType, "loadType");
        int i = WhenMappings.$EnumSwitchMapping$0[loadType.ordinal()];
        if (i == 1) {
            return this.refresh;
        }
        if (i == 2) {
            return this.append;
        }
        if (i == 3) {
            return this.prepend;
        }
        throw new NoWhenBranchMatchedException();
    }

    public final void set(LoadType loadType, LoadState loadState) {
        Intrinsics.checkNotNullParameter(loadType, "type");
        Intrinsics.checkNotNullParameter(loadState, OAuth2RequestParameters.State);
        int i = WhenMappings.$EnumSwitchMapping$0[loadType.ordinal()];
        if (i == 1) {
            this.refresh = loadState;
        } else if (i == 2) {
            this.append = loadState;
        } else if (i == 3) {
            this.prepend = loadState;
        } else {
            throw new NoWhenBranchMatchedException();
        }
    }

    public final void set(LoadStates loadStates) {
        Intrinsics.checkNotNullParameter(loadStates, "states");
        this.refresh = loadStates.getRefresh();
        this.append = loadStates.getAppend();
        this.prepend = loadStates.getPrepend();
    }
}
