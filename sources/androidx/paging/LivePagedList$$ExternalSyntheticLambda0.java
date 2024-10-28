package androidx.paging;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class LivePagedList$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ LivePagedList f$0;

    public /* synthetic */ LivePagedList$$ExternalSyntheticLambda0(LivePagedList livePagedList) {
        this.f$0 = livePagedList;
    }

    public final void run() {
        LivePagedList.refreshRetryCallback$lambda$0(this.f$0);
    }
}
