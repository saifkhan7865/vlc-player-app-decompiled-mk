package org.videolan.vlc.viewmodels;

import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModelKt;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import org.videolan.vlc.gui.dialogs.SubtitleItem;

@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012&\u0010\u0002\u001a\"\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005 \u0006*\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "it", "", "", "Lorg/videolan/vlc/gui/dialogs/SubtitleItem;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: SubtitlesModel.kt */
final class SubtitlesModel$2$2 extends Lambda implements Function1<Map<Long, ? extends SubtitleItem>, Unit> {
    final /* synthetic */ MediatorLiveData<List<SubtitleItem>> $this_apply;
    final /* synthetic */ SubtitlesModel this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SubtitlesModel$2$2(SubtitlesModel subtitlesModel, MediatorLiveData<List<SubtitleItem>> mediatorLiveData) {
        super(1);
        this.this$0 = subtitlesModel;
        this.$this_apply = mediatorLiveData;
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "org.videolan.vlc.viewmodels.SubtitlesModel$2$2$1", f = "SubtitlesModel.kt", i = {}, l = {78}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: org.videolan.vlc.viewmodels.SubtitlesModel$2$2$1  reason: invalid class name */
    /* compiled from: SubtitlesModel.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$0;
        int label;

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(mediatorLiveData, subtitlesModel, map, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            MediatorLiveData<List<SubtitleItem>> mediatorLiveData;
            List list;
            Collection<SubtitleItem> values;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                MediatorLiveData<List<SubtitleItem>> mediatorLiveData2 = mediatorLiveData;
                SubtitlesModel subtitlesModel = subtitlesModel;
                List list2 = (List) subtitlesModel.downloadedLiveData.getValue();
                Map<Long, SubtitleItem> map = map;
                if (map == null || (values = map.values()) == null) {
                    list = null;
                } else {
                    SubtitlesModel subtitlesModel2 = subtitlesModel;
                    Collection arrayList = new ArrayList();
                    for (Object next : values) {
                        if (Intrinsics.areEqual((Object) ((SubtitleItem) next).getMediaUri(), (Object) subtitlesModel2.mediaUri)) {
                            arrayList.add(next);
                        }
                    }
                    list = (List) arrayList;
                }
                this.L$0 = mediatorLiveData2;
                this.label = 1;
                Object access$merge = subtitlesModel.merge(list2, list, this);
                if (access$merge == coroutine_suspended) {
                    return coroutine_suspended;
                }
                mediatorLiveData = mediatorLiveData2;
                obj = access$merge;
            } else if (i == 1) {
                mediatorLiveData = (MediatorLiveData) this.L$0;
                ResultKt.throwOnFailure(obj);
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            mediatorLiveData.setValue(obj);
            return Unit.INSTANCE;
        }
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Map<Long, SubtitleItem>) (Map) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(final Map<Long, SubtitleItem> map) {
        CoroutineScope viewModelScope = ViewModelKt.getViewModelScope(this.this$0);
        final MediatorLiveData<List<SubtitleItem>> mediatorLiveData = this.$this_apply;
        final SubtitlesModel subtitlesModel = this.this$0;
        Job unused = BuildersKt__Builders_commonKt.launch$default(viewModelScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass1((Continuation<? super AnonymousClass1>) null), 3, (Object) null);
    }
}
