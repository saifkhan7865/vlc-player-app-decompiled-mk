package org.videolan.vlc.viewmodels;

import android.content.Context;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.viewmodel.CreationExtras;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import org.fusesource.jansi.AnsiRenderer;
import org.videolan.tools.livedata.LiveDataset;
import org.videolan.vlc.gui.preferences.search.PreferenceItem;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001\u001bB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014J\u000e\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0007J\u000e\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0007J\u0018\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0016\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u000e\u0010\u001a\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001c"}, d2 = {"Lorg/videolan/vlc/viewmodels/PreferenceSearchModel;", "Landroidx/lifecycle/ViewModel;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "dataset", "Lorg/videolan/tools/livedata/LiveDataset;", "Lorg/videolan/vlc/gui/preferences/search/PreferenceItem;", "getDataset", "()Lorg/videolan/tools/livedata/LiveDataset;", "filtered", "getFiltered", "showTranslations", "Landroidx/lifecycle/MutableLiveData;", "", "getShowTranslations", "()Landroidx/lifecycle/MutableLiveData;", "filter", "", "query", "", "getSummary", "item", "getTitle", "score", "", "switchTranslations", "Factory", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: PreferenceSearchModel.kt */
public final class PreferenceSearchModel extends ViewModel {
    private final LiveDataset<PreferenceItem> dataset = new LiveDataset<>();
    private final LiveDataset<PreferenceItem> filtered = new LiveDataset<>();
    private final MutableLiveData<Boolean> showTranslations;

    public PreferenceSearchModel(final Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        MutableLiveData<Boolean> mutableLiveData = new MutableLiveData<>();
        this.showTranslations = mutableLiveData;
        Job unused = BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass1(this, (Continuation<? super AnonymousClass1>) null), 3, (Object) null);
        mutableLiveData.setValue(false);
    }

    public final LiveDataset<PreferenceItem> getDataset() {
        return this.dataset;
    }

    public final LiveDataset<PreferenceItem> getFiltered() {
        return this.filtered;
    }

    public final MutableLiveData<Boolean> getShowTranslations() {
        return this.showTranslations;
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "org.videolan.vlc.viewmodels.PreferenceSearchModel$1", f = "PreferenceSearchModel.kt", i = {}, l = {47}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: org.videolan.vlc.viewmodels.PreferenceSearchModel$1  reason: invalid class name */
    /* compiled from: PreferenceSearchModel.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$0;
        int label;
        final /* synthetic */ PreferenceSearchModel this$0;

        {
            this.this$0 = r1;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.this$0, context, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            LiveDataset<PreferenceItem> liveDataset;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                LiveDataset<PreferenceItem> dataset = this.this$0.getDataset();
                final Context context = context;
                this.L$0 = dataset;
                this.label = 1;
                Object withContext = BuildersKt.withContext(Dispatchers.getIO(), new Function2<CoroutineScope, Continuation<? super ArrayList<PreferenceItem>>, Object>((Continuation<? super AnonymousClass1>) null) {
                    int label;

                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                        return 

                        @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J%\u0010\u0005\u001a\u0002H\u0006\"\b\b\u0000\u0010\u0006*\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00060\tH\u0016¢\u0006\u0002\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lorg/videolan/vlc/viewmodels/PreferenceSearchModel$Factory;", "Landroidx/lifecycle/ViewModelProvider$Factory;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "create", "T", "Landroidx/lifecycle/ViewModel;", "modelClass", "Ljava/lang/Class;", "(Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
                        /* compiled from: PreferenceSearchModel.kt */
                        public static final class Factory implements ViewModelProvider.Factory {
                            private final Context context;

                            public /* synthetic */ ViewModel create(Class cls, CreationExtras creationExtras) {
                                return ViewModelProvider.Factory.CC.$default$create(this, cls, creationExtras);
                            }

                            public Factory(Context context2) {
                                Intrinsics.checkNotNullParameter(context2, "context");
                                this.context = context2;
                            }

                            public <T extends ViewModel> T create(Class<T> cls) {
                                Intrinsics.checkNotNullParameter(cls, "modelClass");
                                Context applicationContext = this.context.getApplicationContext();
                                Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
                                return (ViewModel) new PreferenceSearchModel(applicationContext);
                            }
                        }

                        public final void filter(String str) {
                            Intrinsics.checkNotNullParameter(str, "query");
                            CharSequence charSequence = str;
                            if (StringsKt.isBlank(charSequence)) {
                                this.filtered.setValue(new ArrayList());
                                return;
                            }
                            LiveDataset<PreferenceItem> liveDataset = this.filtered;
                            Collection arrayList = new ArrayList();
                            for (Object next : this.dataset.getList()) {
                                PreferenceItem preferenceItem = (PreferenceItem) next;
                                String title = getTitle(preferenceItem);
                                Locale locale = Locale.getDefault();
                                Intrinsics.checkNotNullExpressionValue(locale, "getDefault(...)");
                                String lowerCase = title.toLowerCase(locale);
                                Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
                                if (!StringsKt.contains$default((CharSequence) lowerCase, charSequence, false, 2, (Object) null)) {
                                    String summary = getSummary(preferenceItem);
                                    Locale locale2 = Locale.getDefault();
                                    Intrinsics.checkNotNullExpressionValue(locale2, "getDefault(...)");
                                    String lowerCase2 = summary.toLowerCase(locale2);
                                    Intrinsics.checkNotNullExpressionValue(lowerCase2, "toLowerCase(...)");
                                    if (!StringsKt.contains$default((CharSequence) lowerCase2, charSequence, false, 2, (Object) null)) {
                                    }
                                }
                                arrayList.add(next);
                            }
                            liveDataset.setValue((List<PreferenceItem>) CollectionsKt.toMutableList(CollectionsKt.sortedWith((List) arrayList, new PreferenceSearchModel$$ExternalSyntheticLambda0(new PreferenceSearchModel$filter$2(this, str)))));
                        }

                        /* access modifiers changed from: private */
                        public static final int filter$lambda$1(Function2 function2, Object obj, Object obj2) {
                            Intrinsics.checkNotNullParameter(function2, "$tmp0");
                            return ((Number) function2.invoke(obj, obj2)).intValue();
                        }

                        public final String getSummary(PreferenceItem preferenceItem) {
                            Intrinsics.checkNotNullParameter(preferenceItem, "item");
                            return Intrinsics.areEqual((Object) this.showTranslations.getValue(), (Object) true) ? preferenceItem.getSummaryEng() : preferenceItem.getSummary();
                        }

                        public final String getTitle(PreferenceItem preferenceItem) {
                            Intrinsics.checkNotNullParameter(preferenceItem, "item");
                            return Intrinsics.areEqual((Object) this.showTranslations.getValue(), (Object) true) ? preferenceItem.getTitleEng() : preferenceItem.getTitle();
                        }

                        /* access modifiers changed from: private */
                        public final int score(PreferenceItem preferenceItem, String str) {
                            String str2 = str;
                            String summary = getSummary(preferenceItem);
                            Locale locale = Locale.getDefault();
                            Intrinsics.checkNotNullExpressionValue(locale, "getDefault(...)");
                            String lowerCase = summary.toLowerCase(locale);
                            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
                            CharSequence charSequence = str2;
                            int contains$default = StringsKt.contains$default((CharSequence) lowerCase, charSequence, false, 2, (Object) null);
                            String title = getTitle(preferenceItem);
                            Locale locale2 = Locale.getDefault();
                            Intrinsics.checkNotNullExpressionValue(locale2, "getDefault(...)");
                            String lowerCase2 = title.toLowerCase(locale2);
                            Intrinsics.checkNotNullExpressionValue(lowerCase2, "toLowerCase(...)");
                            if (StringsKt.contains$default((CharSequence) lowerCase2, charSequence, false, 2, (Object) null)) {
                                contains$default += 10;
                            }
                            String summary2 = getSummary(preferenceItem);
                            Locale locale3 = Locale.getDefault();
                            Intrinsics.checkNotNullExpressionValue(locale3, "getDefault(...)");
                            String lowerCase3 = summary2.toLowerCase(locale3);
                            Intrinsics.checkNotNullExpressionValue(lowerCase3, "toLowerCase(...)");
                            Iterable split$default = StringsKt.split$default((CharSequence) lowerCase3, new String[]{AnsiRenderer.CODE_TEXT_SEPARATOR}, false, 0, 6, (Object) null);
                            if (!(split$default instanceof Collection) || !((Collection) split$default).isEmpty()) {
                                Iterator it = split$default.iterator();
                                while (true) {
                                    if (it.hasNext()) {
                                        if (StringsKt.startsWith$default((String) it.next(), str2, false, 2, (Object) null)) {
                                            contains$default += 100;
                                            break;
                                        }
                                    } else {
                                        break;
                                    }
                                }
                            }
                            String title2 = getTitle(preferenceItem);
                            Locale locale4 = Locale.getDefault();
                            Intrinsics.checkNotNullExpressionValue(locale4, "getDefault(...)");
                            String lowerCase4 = title2.toLowerCase(locale4);
                            Intrinsics.checkNotNullExpressionValue(lowerCase4, "toLowerCase(...)");
                            Iterable<String> split$default2 = StringsKt.split$default((CharSequence) lowerCase4, new String[]{AnsiRenderer.CODE_TEXT_SEPARATOR}, false, 0, 6, (Object) null);
                            if ((split$default2 instanceof Collection) && ((Collection) split$default2).isEmpty()) {
                                return contains$default;
                            }
                            for (String startsWith$default : split$default2) {
                                if (StringsKt.startsWith$default(startsWith$default, str2, false, 2, (Object) null)) {
                                    return contains$default + 1000;
                                }
                            }
                            return contains$default;
                        }

                        public final void switchTranslations(String str) {
                            Intrinsics.checkNotNullParameter(str, "query");
                            MutableLiveData<Boolean> mutableLiveData = this.showTranslations;
                            mutableLiveData.setValue(Boolean.valueOf(Intrinsics.areEqual((Object) mutableLiveData.getValue(), (Object) false)));
                            filter(str);
                        }
                    }
