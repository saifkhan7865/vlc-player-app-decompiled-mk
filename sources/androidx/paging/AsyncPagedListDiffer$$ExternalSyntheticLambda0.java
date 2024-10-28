package androidx.paging;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class AsyncPagedListDiffer$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ AsyncPagedListDiffer f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ PagedList f$2;
    public final /* synthetic */ PagedList f$3;
    public final /* synthetic */ NullPaddedDiffResult f$4;
    public final /* synthetic */ RecordingCallback f$5;
    public final /* synthetic */ PagedList f$6;
    public final /* synthetic */ Runnable f$7;

    public /* synthetic */ AsyncPagedListDiffer$$ExternalSyntheticLambda0(AsyncPagedListDiffer asyncPagedListDiffer, int i, PagedList pagedList, PagedList pagedList2, NullPaddedDiffResult nullPaddedDiffResult, RecordingCallback recordingCallback, PagedList pagedList3, Runnable runnable) {
        this.f$0 = asyncPagedListDiffer;
        this.f$1 = i;
        this.f$2 = pagedList;
        this.f$3 = pagedList2;
        this.f$4 = nullPaddedDiffResult;
        this.f$5 = recordingCallback;
        this.f$6 = pagedList3;
        this.f$7 = runnable;
    }

    public final void run() {
        AsyncPagedListDiffer.submitList$lambda$2$lambda$1(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7);
    }
}
