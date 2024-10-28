package org.videolan.vlc.viewmodels.browser;

import android.content.SharedPreferences;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.tools.SettingsKt;
import org.videolan.tools.livedata.LiveDataset;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.viewmodels.browser.BrowserModel$sort$1", f = "BrowserModel.kt", i = {}, l = {112}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: BrowserModel.kt */
final class BrowserModel$sort$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ int $sort;
    Object L$0;
    int label;
    final /* synthetic */ BrowserModel this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BrowserModel$sort$1(BrowserModel browserModel, int i, Continuation<? super BrowserModel$sort$1> continuation) {
        super(2, continuation);
        this.this$0 = browserModel;
        this.$sort = i;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new BrowserModel$sort$1(this.this$0, this.$sort, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((BrowserModel$sort$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        LiveDataset liveDataset;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.this$0.setSort(this.$sort);
            BrowserModel browserModel = this.this$0;
            boolean z = false;
            if (this.$sort != 0 && !browserModel.getDesc()) {
                z = true;
            }
            browserModel.setDesc(z);
            this.this$0.getProvider().setSort(this.$sort);
            this.this$0.getProvider().setDesc(this.this$0.getDesc());
            LiveDataset dataset = this.this$0.getDataset();
            final BrowserModel browserModel2 = this.this$0;
            this.L$0 = dataset;
            this.label = 1;
            Object withContext = BuildersKt.withContext(this.this$0.getCoroutineContextProvider().getDefault(), new AnonymousClass1((Continuation<? super AnonymousClass1>) null), this);
            if (withContext == coroutine_suspended) {
                return coroutine_suspended;
            }
            liveDataset = dataset;
            obj = withContext;
        } else if (i == 1) {
            liveDataset = (LiveDataset) this.L$0;
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        liveDataset.setValue((List) obj);
        SettingsKt.putSingle(this.this$0.getSettings(), this.this$0.getSortKey(), Boxing.boxInt(this.$sort));
        SharedPreferences settings = this.this$0.getSettings();
        SettingsKt.putSingle(settings, this.this$0.getSortKey() + "_desc", Boxing.boxBoolean(this.this$0.getDesc()));
        return Unit.INSTANCE;
    }

    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H@"}, d2 = {"<anonymous>", "", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "org.videolan.vlc.viewmodels.browser.BrowserModel$sort$1$1", f = "BrowserModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: org.videolan.vlc.viewmodels.browser.BrowserModel$sort$1$1  reason: invalid class name */
    /* compiled from: BrowserModel.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<MediaLibraryItem>>, Object> {
        int label;

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(browserModel2, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super List<MediaLibraryItem>> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                List value = browserModel2.getDataset().getValue();
                browserModel2.getProvider().sort(value);
                BrowserModel browserModel = browserModel2;
                browserModel.getProvider().computeHeaders(browserModel.getDataset().getValue());
                return value;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
