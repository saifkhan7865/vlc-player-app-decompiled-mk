package org.videolan.vlc.viewmodels;

import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModelKt;
import java.util.List;
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
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import org.videolan.resources.opensubtitles.OpenSubtitle;
import org.videolan.vlc.gui.dialogs.SubtitleItem;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u001a\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004 \u0005*\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "it", "", "Lorg/videolan/resources/opensubtitles/OpenSubtitle;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: SubtitlesModel.kt */
final class SubtitlesModel$3$1 extends Lambda implements Function1<List<? extends OpenSubtitle>, Unit> {
    final /* synthetic */ MediatorLiveData<List<SubtitleItem>> $this_apply;
    final /* synthetic */ SubtitlesModel this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SubtitlesModel$3$1(SubtitlesModel subtitlesModel, MediatorLiveData<List<SubtitleItem>> mediatorLiveData) {
        super(1);
        this.this$0 = subtitlesModel;
        this.$this_apply = mediatorLiveData;
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "org.videolan.vlc.viewmodels.SubtitlesModel$3$1$1", f = "SubtitlesModel.kt", i = {}, l = {86}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: org.videolan.vlc.viewmodels.SubtitlesModel$3$1$1  reason: invalid class name */
    /* compiled from: SubtitlesModel.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$0;
        int label;

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(mediatorLiveData, subtitlesModel, list, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            MediatorLiveData<List<SubtitleItem>> mediatorLiveData;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                MediatorLiveData<List<SubtitleItem>> mediatorLiveData2 = mediatorLiveData;
                SubtitlesModel subtitlesModel = subtitlesModel;
                this.L$0 = mediatorLiveData2;
                this.label = 1;
                Object access$updateListState = subtitlesModel.updateListState(list, subtitlesModel.getHistory().getValue(), this);
                if (access$updateListState == coroutine_suspended) {
                    return coroutine_suspended;
                }
                mediatorLiveData = mediatorLiveData2;
                obj = access$updateListState;
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
        invoke((List<OpenSubtitle>) (List) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(final List<OpenSubtitle> list) {
        CoroutineScope viewModelScope = ViewModelKt.getViewModelScope(this.this$0);
        final MediatorLiveData<List<SubtitleItem>> mediatorLiveData = this.$this_apply;
        final SubtitlesModel subtitlesModel = this.this$0;
        Job unused = BuildersKt__Builders_commonKt.launch$default(viewModelScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass1((Continuation<? super AnonymousClass1>) null), 3, (Object) null);
    }
}
