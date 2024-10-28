package org.videolan.vlc.viewmodels;

import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.videolan.medialibrary.interfaces.media.Bookmark;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.tools.livedata.LiveDataset;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.viewmodels.BookmarkModel$refresh$1$1", f = "BookmarkModel.kt", i = {}, l = {63}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: BookmarkModel.kt */
final class BookmarkModel$refresh$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MediaWrapper $it;
    Object L$0;
    int label;
    final /* synthetic */ BookmarkModel this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BookmarkModel$refresh$1$1(BookmarkModel bookmarkModel, MediaWrapper mediaWrapper, Continuation<? super BookmarkModel$refresh$1$1> continuation) {
        super(2, continuation);
        this.this$0 = bookmarkModel;
        this.$it = mediaWrapper;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new BookmarkModel$refresh$1$1(this.this$0, this.$it, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((BookmarkModel$refresh$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a&\u0012\f\u0012\n \u0003*\u0004\u0018\u00010\u00020\u0002 \u0003*\u0012\u0012\u000e\b\u0001\u0012\n \u0003*\u0004\u0018\u00010\u00020\u00020\u00010\u0001*\u00020\u0004H@"}, d2 = {"<anonymous>", "", "Lorg/videolan/medialibrary/interfaces/media/Bookmark;", "kotlin.jvm.PlatformType", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "org.videolan.vlc.viewmodels.BookmarkModel$refresh$1$1$1", f = "BookmarkModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: org.videolan.vlc.viewmodels.BookmarkModel$refresh$1$1$1  reason: invalid class name */
    /* compiled from: BookmarkModel.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Bookmark[]>, Object> {
        int label;

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(mediaWrapper, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Bookmark[]> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                Bookmark[] bookmarks = mediaWrapper.getBookmarks();
                return bookmarks == null ? new Bookmark[0] : bookmarks;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public final Object invokeSuspend(Object obj) {
        LiveDataset<Bookmark> liveDataset;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            LiveDataset<Bookmark> dataset = this.this$0.getDataset();
            final MediaWrapper mediaWrapper = this.$it;
            this.L$0 = dataset;
            this.label = 1;
            Object withContext = BuildersKt.withContext(Dispatchers.getIO(), new AnonymousClass1((Continuation<? super AnonymousClass1>) null), this);
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
        liveDataset.setValue((List<Bookmark>) ArraysKt.toMutableList((T[]) (Object[]) obj));
        return Unit.INSTANCE;
    }
}
