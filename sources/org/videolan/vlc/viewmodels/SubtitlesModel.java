package org.videolan.vlc.viewmodels;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.text.Html;
import android.text.Spanned;
import android.text.SpannedString;
import androidx.databinding.Observable;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import androidx.lifecycle.ViewModelProvider;
import io.ktor.http.ContentDisposition;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.Set;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import org.videolan.resources.opensubtitles.OpenSubtitle;
import org.videolan.resources.opensubtitles.OpenSubtitleRepository;
import org.videolan.tools.CoroutineContextProvider;
import org.videolan.tools.Settings;
import org.videolan.tools.SettingsKt;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.dialogs.SubtitleItem;
import org.videolan.vlc.repository.ExternalSubRepository;

@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010!\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001RB'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0016\u00107\u001a\u0002082\u0006\u00109\u001a\u00020\u00072\u0006\u0010:\u001a\u00020\u0007J\f\u0010;\u001a\b\u0012\u0004\u0012\u00020\u00070\rJ6\u0010<\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010=\u001a\u00020\u00162\b\u0010>\u001a\u0004\u0018\u00010\u00072\u000e\u0010?\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\rH@¢\u0006\u0002\u0010@J@\u0010A\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010B\u001a\u0004\u0018\u00010C2\b\u0010D\u001a\u0004\u0018\u00010C2\u000e\u0010?\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\rH@¢\u0006\u0002\u0010EJ4\u0010F\u001a\b\u0012\u0004\u0012\u00020\u00130\r2\u000e\u0010G\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\r2\u000e\u0010H\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\rH@¢\u0006\u0002\u0010IJ\u0006\u0010J\u001a\u000208J\u0014\u0010K\u001a\u0002082\f\u0010L\u001a\b\u0012\u0004\u0012\u00020\u00070\rJ\u000e\u0010M\u001a\u0002082\u0006\u0010N\u001a\u00020\u001cJ4\u0010O\u001a\b\u0012\u0004\u0012\u00020\u00130P2\u000e\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\r2\u000e\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\rH@¢\u0006\u0002\u0010IJ\f\u0010Q\u001a\u00020\u0007*\u00020\u0007H\u0002R\u001a\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\r0\u0012X\u0004¢\u0006\u0002\n\u0000R \u0010\u0014\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00130\u00150\u0012X\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\r0\u0018¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0017\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001c0\u0018¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001aR\u0011\u0010\u001d\u001a\u00020\u001e¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u0017\u0010!\u001a\b\u0012\u0004\u0012\u00020\u001c0\"¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0017\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00070\"¢\u0006\b\n\u0000\u001a\u0004\b&\u0010$R\u0017\u0010'\u001a\b\u0012\u0004\u0012\u00020(0\"¢\u0006\b\n\u0000\u001a\u0004\b)\u0010$R\u0017\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00070\"¢\u0006\b\n\u0000\u001a\u0004\b+\u0010$R\u001d\u0010,\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\r0\"¢\u0006\b\n\u0000\u001a\u0004\b-\u0010$R\u0017\u0010.\u001a\b\u0012\u0004\u0012\u00020\u00070\"¢\u0006\b\n\u0000\u001a\u0004\b/\u0010$R\u0017\u00100\u001a\b\u0012\u0004\u0012\u00020\u00070\"¢\u0006\b\n\u0000\u001a\u0004\b1\u0010$R\u0016\u00102\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\rX\u000e¢\u0006\u0002\n\u0000R\u001d\u00103\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\r0\u0018¢\u0006\b\n\u0000\u001a\u0004\b4\u0010\u001aR\u0010\u00105\u001a\u0004\u0018\u000106X\u000e¢\u0006\u0002\n\u0000¨\u0006S"}, d2 = {"Lorg/videolan/vlc/viewmodels/SubtitlesModel;", "Landroidx/lifecycle/ViewModel;", "context", "Landroid/content/Context;", "mediaUri", "Landroid/net/Uri;", "name", "", "coroutineContextProvider", "Lorg/videolan/tools/CoroutineContextProvider;", "(Landroid/content/Context;Landroid/net/Uri;Ljava/lang/String;Lorg/videolan/tools/CoroutineContextProvider;)V", "apiResultLiveData", "Landroidx/lifecycle/MutableLiveData;", "", "Lorg/videolan/resources/opensubtitles/OpenSubtitle;", "getCoroutineContextProvider", "()Lorg/videolan/tools/CoroutineContextProvider;", "downloadedLiveData", "Landroidx/lifecycle/LiveData;", "Lorg/videolan/vlc/gui/dialogs/SubtitleItem;", "downloadingLiveData", "", "", "history", "Landroidx/lifecycle/MediatorLiveData;", "getHistory", "()Landroidx/lifecycle/MediatorLiveData;", "isApiLoading", "", "manualSearchEnabled", "Landroidx/databinding/ObservableBoolean;", "getManualSearchEnabled", "()Landroidx/databinding/ObservableBoolean;", "observableError", "Landroidx/databinding/ObservableField;", "getObservableError", "()Landroidx/databinding/ObservableField;", "observableMessage", "getObservableMessage", "observableResultDescription", "Landroid/text/Spanned;", "getObservableResultDescription", "observableSearchEpisode", "getObservableSearchEpisode", "observableSearchLanguage", "getObservableSearchLanguage", "observableSearchName", "getObservableSearchName", "observableSearchSeason", "getObservableSearchSeason", "previousSearchLanguage", "result", "getResult", "searchJob", "Lkotlinx/coroutines/Job;", "deleteSubtitle", "", "mediaPath", "idSubtitle", "getLastUsedLanguage", "getSubtitleByHash", "movieByteSize", "movieHash", "languageIds", "(JLjava/lang/String;Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getSubtitleByName", "episode", "", "season", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "merge", "downloadedResult", "downloadingResult", "(Ljava/util/List;Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onRefresh", "saveLastUsedLanguage", "lastUsedLanguages", "search", "byFile", "updateListState", "", "getCompliantLanguageID", "Factory", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: SubtitlesModel.kt */
public final class SubtitlesModel extends ViewModel {
    /* access modifiers changed from: private */
    public final MutableLiveData<List<OpenSubtitle>> apiResultLiveData;
    /* access modifiers changed from: private */
    public final Context context;
    private final CoroutineContextProvider coroutineContextProvider;
    /* access modifiers changed from: private */
    public final LiveData<List<SubtitleItem>> downloadedLiveData;
    /* access modifiers changed from: private */
    public final LiveData<Map<Long, SubtitleItem>> downloadingLiveData;
    private final MediatorLiveData<List<SubtitleItem>> history;
    private final MediatorLiveData<Boolean> isApiLoading;
    private final ObservableBoolean manualSearchEnabled;
    /* access modifiers changed from: private */
    public final Uri mediaUri;
    /* access modifiers changed from: private */
    public final String name;
    private final ObservableField<Boolean> observableError;
    private final ObservableField<String> observableMessage;
    private final ObservableField<Spanned> observableResultDescription;
    private final ObservableField<String> observableSearchEpisode;
    private final ObservableField<List<String>> observableSearchLanguage;
    private final ObservableField<String> observableSearchName;
    private final ObservableField<String> observableSearchSeason;
    /* access modifiers changed from: private */
    public List<String> previousSearchLanguage;
    private final MediatorLiveData<List<SubtitleItem>> result;
    private Job searchJob;

    public SubtitlesModel(Context context2, Uri uri, String str, CoroutineContextProvider coroutineContextProvider2) {
        Intrinsics.checkNotNullParameter(context2, "context");
        Intrinsics.checkNotNullParameter(uri, "mediaUri");
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        Intrinsics.checkNotNullParameter(coroutineContextProvider2, "coroutineContextProvider");
        this.context = context2;
        this.mediaUri = uri;
        this.name = str;
        this.coroutineContextProvider = coroutineContextProvider2;
        this.observableSearchName = new ObservableField<>();
        this.observableSearchEpisode = new ObservableField<>();
        this.observableSearchSeason = new ObservableField<>();
        ObservableField<List<String>> observableField = new ObservableField<>();
        this.observableSearchLanguage = observableField;
        this.manualSearchEnabled = new ObservableBoolean(false);
        this.isApiLoading = new MediatorLiveData<>();
        this.observableMessage = new ObservableField<>();
        this.observableError = new ObservableField<>();
        this.observableResultDescription = new ObservableField<>();
        MutableLiveData<List<OpenSubtitle>> mutableLiveData = new MutableLiveData<>();
        this.apiResultLiveData = mutableLiveData;
        LiveData<List<SubtitleItem>> map = Transformations.map(((ExternalSubRepository) ExternalSubRepository.Companion.getInstance(context2)).getDownloadedSubtitles(uri), new SubtitlesModel$downloadedLiveData$1(this));
        this.downloadedLiveData = map;
        LiveData<Map<Long, SubtitleItem>> downloadingSubtitles = ((ExternalSubRepository) ExternalSubRepository.Companion.getInstance(context2)).getDownloadingSubtitles();
        this.downloadingLiveData = downloadingSubtitles;
        MediatorLiveData<List<SubtitleItem>> mediatorLiveData = new MediatorLiveData<>();
        this.result = mediatorLiveData;
        MediatorLiveData<List<SubtitleItem>> mediatorLiveData2 = new MediatorLiveData<>();
        this.history = mediatorLiveData2;
        observableField.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback(this) {
            final /* synthetic */ SubtitlesModel this$0;

            {
                this.this$0 = r1;
            }

            public void onPropertyChanged(Observable observable, int i) {
                if (!Intrinsics.areEqual((Object) this.this$0.getObservableSearchLanguage().get(), (Object) this.this$0.previousSearchLanguage)) {
                    SubtitlesModel subtitlesModel = this.this$0;
                    subtitlesModel.previousSearchLanguage = subtitlesModel.getObservableSearchLanguage().get();
                    SubtitlesModel subtitlesModel2 = this.this$0;
                    List list = subtitlesModel2.getObservableSearchLanguage().get();
                    if (list == null) {
                        list = CollectionsKt.emptyList();
                    }
                    subtitlesModel2.saveLastUsedLanguage(list);
                    SubtitlesModel subtitlesModel3 = this.this$0;
                    subtitlesModel3.search(!subtitlesModel3.getManualSearchEnabled().get());
                }
            }
        });
        mediatorLiveData2.addSource(map, new SubtitlesModelKt$sam$androidx_lifecycle_Observer$0(new SubtitlesModel$2$1(this, mediatorLiveData2)));
        mediatorLiveData2.addSource(downloadingSubtitles, new SubtitlesModelKt$sam$androidx_lifecycle_Observer$0(new SubtitlesModel$2$2(this, mediatorLiveData2)));
        mediatorLiveData.addSource(mutableLiveData, new SubtitlesModelKt$sam$androidx_lifecycle_Observer$0(new SubtitlesModel$3$1(this, mediatorLiveData)));
        mediatorLiveData.addSource(mediatorLiveData2, new SubtitlesModelKt$sam$androidx_lifecycle_Observer$0(new SubtitlesModel$3$2(this, mediatorLiveData)));
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ SubtitlesModel(Context context2, Uri uri, String str, CoroutineContextProvider coroutineContextProvider2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context2, uri, str, (i & 8) != 0 ? new CoroutineContextProvider() : coroutineContextProvider2);
    }

    public final CoroutineContextProvider getCoroutineContextProvider() {
        return this.coroutineContextProvider;
    }

    public final ObservableField<String> getObservableSearchName() {
        return this.observableSearchName;
    }

    public final ObservableField<String> getObservableSearchEpisode() {
        return this.observableSearchEpisode;
    }

    public final ObservableField<String> getObservableSearchSeason() {
        return this.observableSearchSeason;
    }

    public final ObservableField<List<String>> getObservableSearchLanguage() {
        return this.observableSearchLanguage;
    }

    public final ObservableBoolean getManualSearchEnabled() {
        return this.manualSearchEnabled;
    }

    public final MediatorLiveData<Boolean> isApiLoading() {
        return this.isApiLoading;
    }

    public final ObservableField<String> getObservableMessage() {
        return this.observableMessage;
    }

    public final ObservableField<Boolean> getObservableError() {
        return this.observableError;
    }

    public final ObservableField<Spanned> getObservableResultDescription() {
        return this.observableResultDescription;
    }

    public final MediatorLiveData<List<SubtitleItem>> getResult() {
        return this.result;
    }

    public final MediatorLiveData<List<SubtitleItem>> getHistory() {
        return this.history;
    }

    /* access modifiers changed from: private */
    public final Object merge(List<SubtitleItem> list, List<SubtitleItem> list2, Continuation<? super List<SubtitleItem>> continuation) {
        return BuildersKt.withContext(this.coroutineContextProvider.getDefault(), new SubtitlesModel$merge$2(list, list2, (Continuation<? super SubtitlesModel$merge$2>) null), continuation);
    }

    /* access modifiers changed from: private */
    public final Object updateListState(List<OpenSubtitle> list, List<SubtitleItem> list2, Continuation<? super List<SubtitleItem>> continuation) {
        return BuildersKt.withContext(this.coroutineContextProvider.getDefault(), new SubtitlesModel$updateListState$2(list, list2, this, (Continuation<? super SubtitlesModel$updateListState$2>) null), continuation);
    }

    /* JADX WARNING: type inference failed for: r1v4, types: [org.videolan.resources.opensubtitles.OpenSubtitleRepository] */
    /* access modifiers changed from: private */
    public final Object getSubtitleByName(String str, Integer num, Integer num2, List<String> list, Continuation<? super List<OpenSubtitle>> continuation) {
        Context context2 = this.context;
        int i = R.string.sub_result_by_name;
        StringBuilder sb = new StringBuilder(context2.getString(i, new Object[]{"<i>" + str + "</i>"}));
        if (num2 != null) {
            int intValue = num2.intValue();
            sb.append(" - ");
            Context context3 = this.context;
            int i2 = R.string.sub_result_by_name_season;
            sb.append(context3.getString(i2, new Object[]{"<i>" + intValue + "</i>"}));
        }
        if (num != null) {
            int intValue2 = num.intValue();
            sb.append(" - ");
            Context context4 = this.context;
            int i3 = R.string.sub_result_by_name_episode;
            sb.append(context4.getString(i3, new Object[]{"<i>" + intValue2 + "</i>"}));
        }
        this.observableResultDescription.set(Html.fromHtml(sb.toString()));
        this.manualSearchEnabled.set(true);
        return OpenSubtitleRepository.Companion.getInstance().queryWithName(str, num, num2, list, continuation);
    }

    /* JADX WARNING: type inference failed for: r1v6, types: [org.videolan.resources.opensubtitles.OpenSubtitleRepository] */
    /* access modifiers changed from: private */
    public final Object getSubtitleByHash(long j, String str, List<String> list, Continuation<? super List<OpenSubtitle>> continuation) {
        this.manualSearchEnabled.set(false);
        ObservableField<Spanned> observableField = this.observableResultDescription;
        CharSequence string = this.context.getString(R.string.sub_result_by_file);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        observableField.set(SpannedString.valueOf(string));
        return OpenSubtitleRepository.Companion.getInstance().queryWithHash(j, str, list, continuation);
    }

    public final void onRefresh() {
        CharSequence charSequence;
        if (!this.manualSearchEnabled.get() || !((charSequence = this.observableSearchName.get()) == null || charSequence.length() == 0)) {
            search(!this.manualSearchEnabled.get());
        } else {
            this.isApiLoading.postValue(false);
        }
    }

    public final void search(boolean z) {
        Job job = this.searchJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.isApiLoading.postValue(true);
        this.observableMessage.set("");
        this.observableError.set(false);
        this.apiResultLiveData.postValue(CollectionsKt.emptyList());
        this.searchJob = BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), (CoroutineContext) null, (CoroutineStart) null, new SubtitlesModel$search$1(z, this, (Continuation<? super SubtitlesModel$search$1>) null), 3, (Object) null);
    }

    public final void deleteSubtitle(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "mediaPath");
        Intrinsics.checkNotNullParameter(str2, "idSubtitle");
        ((ExternalSubRepository) ExternalSubRepository.Companion.getInstance(this.context)).deleteSubtitle(str, str2);
    }

    public final List<String> getLastUsedLanguage() {
        String str;
        try {
            str = Locale.getDefault().getISO3Language();
        } catch (MissingResourceException unused) {
            str = "eng";
        }
        Set<String> stringSet = ((SharedPreferences) Settings.INSTANCE.getInstance(this.context)).getStringSet("last_used_subtitles", SetsKt.setOf(str));
        if (stringSet == null) {
            return CollectionsKt.emptyList();
        }
        Iterable<String> iterable = stringSet;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (String str2 : iterable) {
            Intrinsics.checkNotNull(str2);
            arrayList.add(getCompliantLanguageID(str2));
        }
        return (List) arrayList;
    }

    public final void saveLastUsedLanguage(List<String> list) {
        Intrinsics.checkNotNullParameter(list, "lastUsedLanguages");
        SettingsKt.putSingle((SharedPreferences) Settings.INSTANCE.getInstance(this.context), "last_used_subtitles", list);
    }

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ%\u0010\t\u001a\u0002H\n\"\b\b\u0000\u0010\n*\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\n0\rH\u0016¢\u0006\u0002\u0010\u000eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lorg/videolan/vlc/viewmodels/SubtitlesModel$Factory;", "Landroidx/lifecycle/ViewModelProvider$NewInstanceFactory;", "context", "Landroid/content/Context;", "mediaUri", "Landroid/net/Uri;", "name", "", "(Landroid/content/Context;Landroid/net/Uri;Ljava/lang/String;)V", "create", "T", "Landroidx/lifecycle/ViewModel;", "modelClass", "Ljava/lang/Class;", "(Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: SubtitlesModel.kt */
    public static final class Factory extends ViewModelProvider.NewInstanceFactory {
        private final Context context;
        private final Uri mediaUri;
        private final String name;

        public Factory(Context context2, Uri uri, String str) {
            Intrinsics.checkNotNullParameter(context2, "context");
            Intrinsics.checkNotNullParameter(uri, "mediaUri");
            Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
            this.context = context2;
            this.mediaUri = uri;
            this.name = str;
        }

        public <T extends ViewModel> T create(Class<T> cls) {
            Intrinsics.checkNotNullParameter(cls, "modelClass");
            Context applicationContext = this.context.getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
            return (ViewModel) new SubtitlesModel(applicationContext, this.mediaUri, this.name, (CoroutineContextProvider) null, 8, (DefaultConstructorMarker) null);
        }
    }

    private final String getCompliantLanguageID(String str) {
        switch (str.hashCode()) {
            case 98385:
                return !str.equals("ces") ? str : "cze";
            case 99348:
                if (!str.equals("deu")) {
                    return str;
                }
                return "ger";
            case 101144:
                if (!str.equals("fas")) {
                    return str;
                }
                return "per";
            case 101653:
                if (!str.equals("fra")) {
                    return str;
                }
                return "fre";
            case 109158:
                if (!str.equals("nld")) {
                    return str;
                }
                return "dut";
            case 113105:
                if (!str.equals("ron")) {
                    return str;
                }
                return "rum";
            case 113970:
                if (!str.equals("slk")) {
                    return str;
                }
                return "slo";
            case 120577:
                if (!str.equals("zho")) {
                    return str;
                }
                return "chi";
            default:
                return str;
        }
    }
}
