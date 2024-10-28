package org.videolan.vlc.repository;

import android.content.Context;
import android.net.Uri;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;
import org.videolan.tools.CoroutineContextProvider;
import org.videolan.tools.SingletonHolder;
import org.videolan.tools.livedata.LiveDataMap;
import org.videolan.vlc.database.ExternalSubDao;
import org.videolan.vlc.gui.dialogs.State;
import org.videolan.vlc.gui.dialogs.SubtitleItem;
import org.videolan.vlc.mediadb.models.ExternalSub;

@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 &2\u00020\u0001:\u0001&B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0016\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\t2\u0006\u0010\u0015\u001a\u00020\nJ\u0016\u0010\u0016\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0018J\u001a\u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001c0\u001b0\f2\u0006\u0010\u001d\u001a\u00020\u001eJ\u0010\u0010\u001f\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0014\u001a\u00020\tJ\u000e\u0010 \u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\tJ.\u0010!\u001a\u00020\"2\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010#\u001a\u00020\u00182\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010$\u001a\u00020\u00182\u0006\u0010%\u001a\u00020\u0018R\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R)\u0010\u000b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\r0\f8F¢\u0006\f\u0012\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lorg/videolan/vlc/repository/ExternalSubRepository;", "", "externalSubDao", "Lorg/videolan/vlc/database/ExternalSubDao;", "coroutineContextProvider", "Lorg/videolan/tools/CoroutineContextProvider;", "(Lorg/videolan/vlc/database/ExternalSubDao;Lorg/videolan/tools/CoroutineContextProvider;)V", "_downloadingSubtitles", "Lorg/videolan/tools/livedata/LiveDataMap;", "", "Lorg/videolan/vlc/gui/dialogs/SubtitleItem;", "downloadingSubtitles", "Landroidx/lifecycle/LiveData;", "", "getDownloadingSubtitles$annotations", "()V", "getDownloadingSubtitles", "()Landroidx/lifecycle/LiveData;", "addDownloadingItem", "", "key", "item", "deleteSubtitle", "mediaPath", "", "idSubtitle", "getDownloadedSubtitles", "", "Lorg/videolan/vlc/mediadb/models/ExternalSub;", "mediaUri", "Landroid/net/Uri;", "getDownloadingSubtitle", "removeDownloadingItem", "saveDownloadedSubtitle", "Lkotlinx/coroutines/Job;", "subtitlePath", "language", "movieReleaseName", "Companion", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ExternalSubRepository.kt */
public final class ExternalSubRepository {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private LiveDataMap<Long, SubtitleItem> _downloadingSubtitles;
    private final CoroutineContextProvider coroutineContextProvider;
    /* access modifiers changed from: private */
    public final ExternalSubDao externalSubDao;

    public static /* synthetic */ void getDownloadingSubtitles$annotations() {
    }

    public ExternalSubRepository(ExternalSubDao externalSubDao2, CoroutineContextProvider coroutineContextProvider2) {
        Intrinsics.checkNotNullParameter(externalSubDao2, "externalSubDao");
        Intrinsics.checkNotNullParameter(coroutineContextProvider2, "coroutineContextProvider");
        this.externalSubDao = externalSubDao2;
        this.coroutineContextProvider = coroutineContextProvider2;
        this._downloadingSubtitles = new LiveDataMap<>();
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ExternalSubRepository(ExternalSubDao externalSubDao2, CoroutineContextProvider coroutineContextProvider2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(externalSubDao2, (i & 2) != 0 ? new CoroutineContextProvider() : coroutineContextProvider2);
    }

    public final LiveData<Map<Long, SubtitleItem>> getDownloadingSubtitles() {
        LiveDataMap<Long, SubtitleItem> liveDataMap = this._downloadingSubtitles;
        Intrinsics.checkNotNull(liveDataMap, "null cannot be cast to non-null type androidx.lifecycle.LiveData<kotlin.collections.Map<kotlin.Long, org.videolan.vlc.gui.dialogs.SubtitleItem>>");
        return liveDataMap;
    }

    public final Job saveDownloadedSubtitle(String str, String str2, String str3, String str4, String str5) {
        Intrinsics.checkNotNullParameter(str, "idSubtitle");
        Intrinsics.checkNotNullParameter(str2, "subtitlePath");
        Intrinsics.checkNotNullParameter(str3, "mediaPath");
        String str6 = str4;
        Intrinsics.checkNotNullParameter(str6, "language");
        String str7 = str5;
        Intrinsics.checkNotNullParameter(str7, "movieReleaseName");
        return BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, this.coroutineContextProvider.getIO(), (CoroutineStart) null, new ExternalSubRepository$saveDownloadedSubtitle$1(this, str, str2, str3, str6, str7, (Continuation<? super ExternalSubRepository$saveDownloadedSubtitle$1>) null), 2, (Object) null);
    }

    public final LiveData<List<ExternalSub>> getDownloadedSubtitles(Uri uri) {
        Intrinsics.checkNotNullParameter(uri, "mediaUri");
        ExternalSubDao externalSubDao2 = this.externalSubDao;
        String path = uri.getPath();
        Intrinsics.checkNotNull(path);
        return Transformations.map(externalSubDao2.get(path), new ExternalSubRepository$getDownloadedSubtitles$1(this));
    }

    public final void deleteSubtitle(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "mediaPath");
        Intrinsics.checkNotNullParameter(str2, "idSubtitle");
        Job unused = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, (CoroutineContext) null, (CoroutineStart) null, new ExternalSubRepository$deleteSubtitle$1(this, str, str2, (Continuation<? super ExternalSubRepository$deleteSubtitle$1>) null), 3, (Object) null);
    }

    public final void addDownloadingItem(long j, SubtitleItem subtitleItem) {
        Intrinsics.checkNotNullParameter(subtitleItem, "item");
        this._downloadingSubtitles.add(Long.valueOf(j), SubtitleItem.copy$default(subtitleItem, (String) null, (Uri) null, (String) null, (String) null, State.Downloading, (String) null, 47, (Object) null));
    }

    public final void removeDownloadingItem(long j) {
        this._downloadingSubtitles.remove(Long.valueOf(j));
    }

    public final SubtitleItem getDownloadingSubtitle(long j) {
        return this._downloadingSubtitles.get(Long.valueOf(j));
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lorg/videolan/vlc/repository/ExternalSubRepository$Companion;", "Lorg/videolan/tools/SingletonHolder;", "Lorg/videolan/vlc/repository/ExternalSubRepository;", "Landroid/content/Context;", "()V", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: ExternalSubRepository.kt */
    public static final class Companion extends SingletonHolder<ExternalSubRepository, Context> {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
            super(AnonymousClass1.INSTANCE);
        }
    }
}
