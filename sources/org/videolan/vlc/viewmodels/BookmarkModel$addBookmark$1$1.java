package org.videolan.vlc.viewmodels;

import android.content.Context;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.videolan.medialibrary.Tools;
import org.videolan.medialibrary.interfaces.media.Bookmark;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.vlc.PlaybackService;
import org.videolan.vlc.R;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.viewmodels.BookmarkModel$addBookmark$1$1", f = "BookmarkModel.kt", i = {}, l = {114}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: BookmarkModel.kt */
final class BookmarkModel$addBookmark$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Context $context;
    final /* synthetic */ MediaWrapper $it;
    int label;
    final /* synthetic */ BookmarkModel this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BookmarkModel$addBookmark$1$1(BookmarkModel bookmarkModel, MediaWrapper mediaWrapper, Context context, Continuation<? super BookmarkModel$addBookmark$1$1> continuation) {
        super(2, continuation);
        this.this$0 = bookmarkModel;
        this.$it = mediaWrapper;
        this.$context = context;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new BookmarkModel$addBookmark$1$1(this.this$0, this.$it, this.$context, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((BookmarkModel$addBookmark$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "org.videolan.vlc.viewmodels.BookmarkModel$addBookmark$1$1$1", f = "BookmarkModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: org.videolan.vlc.viewmodels.BookmarkModel$addBookmark$1$1$1  reason: invalid class name */
    /* compiled from: BookmarkModel.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
        int label;

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(mediaWrapper, bookmarkModel, context, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Boolean> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                MediaWrapper mediaWrapper = mediaWrapper;
                PlaybackService service = bookmarkModel.getService();
                Intrinsics.checkNotNull(service);
                Bookmark addBookmark = mediaWrapper.addBookmark(service.getTime());
                if (addBookmark == null) {
                    return null;
                }
                Context context = context;
                int i = R.string.bookmark_default_name;
                PlaybackService service2 = bookmarkModel.getService();
                Intrinsics.checkNotNull(service2);
                return Boxing.boxBoolean(addBookmark.setName(context.getString(i, new Object[]{Tools.millisToString(service2.getTime())})));
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final MediaWrapper mediaWrapper = this.$it;
            final BookmarkModel bookmarkModel = this.this$0;
            final Context context = this.$context;
            this.label = 1;
            if (BuildersKt.withContext(Dispatchers.getIO(), new AnonymousClass1((Continuation<? super AnonymousClass1>) null), this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        this.this$0.refresh();
        return Unit.INSTANCE;
    }
}
