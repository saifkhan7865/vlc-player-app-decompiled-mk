package androidx.paging;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class AsyncPagedListDiffer$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ PagedList f$0;
    public final /* synthetic */ PagedList f$1;
    public final /* synthetic */ AsyncPagedListDiffer f$2;
    public final /* synthetic */ int f$3;
    public final /* synthetic */ PagedList f$4;
    public final /* synthetic */ RecordingCallback f$5;
    public final /* synthetic */ Runnable f$6;

    public /* synthetic */ AsyncPagedListDiffer$$ExternalSyntheticLambda1(PagedList pagedList, PagedList pagedList2, AsyncPagedListDiffer asyncPagedListDiffer, int i, PagedList pagedList3, RecordingCallback recordingCallback, Runnable runnable) {
        this.f$0 = pagedList;
        this.f$1 = pagedList2;
        this.f$2 = asyncPagedListDiffer;
        this.f$3 = i;
        this.f$4 = pagedList3;
        this.f$5 = recordingCallback;
        this.f$6 = runnable;
    }

    public final void run() {
        AsyncPagedListDiffer.submitList$lambda$2(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6);
    }
}
