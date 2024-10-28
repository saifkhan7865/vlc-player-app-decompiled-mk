package org.videolan.vlc.viewmodels.browser;

import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.tools.livedata.LiveDataset;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.viewmodels.browser.BrowserModel$reSort$1", f = "BrowserModel.kt", i = {}, l = {90}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: BrowserModel.kt */
final class BrowserModel$reSort$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;
    final /* synthetic */ BrowserModel this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BrowserModel$reSort$1(BrowserModel browserModel, Continuation<? super BrowserModel$reSort$1> continuation) {
        super(2, continuation);
        this.this$0 = browserModel;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new BrowserModel$reSort$1(this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((BrowserModel$reSort$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H@"}, d2 = {"<anonymous>", "", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "org.videolan.vlc.viewmodels.browser.BrowserModel$reSort$1$1", f = "BrowserModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: org.videolan.vlc.viewmodels.browser.BrowserModel$reSort$1$1  reason: invalid class name */
    /* compiled from: BrowserModel.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<MediaLibraryItem>>, Object> {
        int label;

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(browserModel, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super List<MediaLibraryItem>> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                List value = browserModel.getDataset().getValue();
                browserModel.getProvider().sort(value);
                BrowserModel browserModel = browserModel;
                browserModel.getProvider().computeHeaders(browserModel.getDataset().getValue());
                return value;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public final Object invokeSuspend(Object obj) {
        LiveDataset liveDataset;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            LiveDataset dataset = this.this$0.getDataset();
            final BrowserModel browserModel = this.this$0;
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
        return Unit.INSTANCE;
    }
}
