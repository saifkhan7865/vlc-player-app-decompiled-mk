package org.videolan.moviepedia.viewmodel;

import android.net.Uri;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import org.videolan.moviepedia.MediaScraper;
import org.videolan.moviepedia.models.resolver.ResolverMedia;
import org.videolan.moviepedia.models.resolver.ResolverResult;
import org.videolan.moviepedia.repository.MediaResolverApi;
import org.videolan.resources.Constants;
import videolan.org.commontools.LiveEvent;

@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cJ\u000e\u0010\u001d\u001a\u00020\u001a2\u0006\u0010\u001e\u001a\u00020\u001fJ\u000e\u0010\u001d\u001a\u00020\u001a2\u0006\u0010 \u001a\u00020\u001cR\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001f\u0010\b\u001a\u0010\u0012\f\u0012\n\u0018\u00010\nj\u0004\u0018\u0001`\u000b0\t¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\"\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f@BX\u000e¢\u0006\b\n\u0000\"\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0004¢\u0006\u0002\n\u0000R\"\u0010\u0017\u001a\u0004\u0018\u00010\u000f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f@BX\u000e¢\u0006\b\n\u0000\"\u0004\b\u0018\u0010\u0012¨\u0006!"}, d2 = {"Lorg/videolan/moviepedia/viewmodel/MediaScrapingModel;", "Landroidx/lifecycle/ViewModel;", "()V", "apiResult", "Landroidx/lifecycle/MutableLiveData;", "Lorg/videolan/moviepedia/models/resolver/ResolverResult;", "getApiResult", "()Landroidx/lifecycle/MutableLiveData;", "exceptionLiveData", "Lvideolan/org/commontools/LiveEvent;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "getExceptionLiveData", "()Lvideolan/org/commontools/LiveEvent;", "value", "Lkotlinx/coroutines/Job;", "mediaJob", "setMediaJob", "(Lkotlinx/coroutines/Job;)V", "mediaResult", "Lorg/videolan/moviepedia/models/resolver/ResolverMedia;", "repo", "Lorg/videolan/moviepedia/repository/MediaResolverApi;", "searchJob", "setSearchJob", "getMedia", "", "mediaId", "", "search", "uri", "Landroid/net/Uri;", "query", "moviepedia_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaScrapingModel.kt */
public final class MediaScrapingModel extends ViewModel {
    private final MutableLiveData<ResolverResult> apiResult = new MutableLiveData<>();
    private final LiveEvent<Exception> exceptionLiveData = new LiveEvent<>();
    private Job mediaJob;
    /* access modifiers changed from: private */
    public final MutableLiveData<ResolverMedia> mediaResult = new MutableLiveData<>();
    /* access modifiers changed from: private */
    public final MediaResolverApi repo = MediaScraper.INSTANCE.getMediaResolverApi();
    private Job searchJob;

    public final MutableLiveData<ResolverResult> getApiResult() {
        return this.apiResult;
    }

    public final LiveEvent<Exception> getExceptionLiveData() {
        return this.exceptionLiveData;
    }

    private final void setSearchJob(Job job) {
        Job job2 = this.searchJob;
        if (job2 != null) {
            Job.DefaultImpls.cancel$default(job2, (CancellationException) null, 1, (Object) null);
        }
        this.searchJob = job;
    }

    private final void setMediaJob(Job job) {
        Job job2 = this.mediaJob;
        if (job2 != null) {
            Job.DefaultImpls.cancel$default(job2, (CancellationException) null, 1, (Object) null);
        }
        this.mediaJob = job;
    }

    public final void search(String str) {
        Intrinsics.checkNotNullParameter(str, "query");
        setSearchJob(BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), (CoroutineContext) null, (CoroutineStart) null, new MediaScrapingModel$search$1(this, str, (Continuation<? super MediaScrapingModel$search$1>) null), 3, (Object) null));
    }

    public final void search(Uri uri) {
        Intrinsics.checkNotNullParameter(uri, Constants.KEY_URI);
        setSearchJob(BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), (CoroutineContext) null, (CoroutineStart) null, new MediaScrapingModel$search$2(this, uri, (Continuation<? super MediaScrapingModel$search$2>) null), 3, (Object) null));
    }

    public final void getMedia(String str) {
        Intrinsics.checkNotNullParameter(str, "mediaId");
        setMediaJob(BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), (CoroutineContext) null, (CoroutineStart) null, new MediaScrapingModel$getMedia$1(this, str, (Continuation<? super MediaScrapingModel$getMedia$1>) null), 3, (Object) null));
    }
}
