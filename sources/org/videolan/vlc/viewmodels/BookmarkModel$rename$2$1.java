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
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.videolan.medialibrary.interfaces.media.Bookmark;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.viewmodels.BookmarkModel$rename$2$1", f = "BookmarkModel.kt", i = {}, l = {127}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: BookmarkModel.kt */
final class BookmarkModel$rename$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Bookmark $bookmark;
    final /* synthetic */ Ref.ObjectRef<List<Bookmark>> $bookmarks;
    final /* synthetic */ MediaWrapper $it;
    final /* synthetic */ String $name;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BookmarkModel$rename$2$1(Bookmark bookmark, String str, Ref.ObjectRef<List<Bookmark>> objectRef, MediaWrapper mediaWrapper, Continuation<? super BookmarkModel$rename$2$1> continuation) {
        super(2, continuation);
        this.$bookmark = bookmark;
        this.$name = str;
        this.$bookmarks = objectRef;
        this.$it = mediaWrapper;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new BookmarkModel$rename$2$1(this.$bookmark, this.$name, this.$bookmarks, this.$it, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((BookmarkModel$rename$2$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "org.videolan.vlc.viewmodels.BookmarkModel$rename$2$1$1", f = "BookmarkModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: org.videolan.vlc.viewmodels.BookmarkModel$rename$2$1$1  reason: invalid class name */
    /* compiled from: BookmarkModel.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(bookmark, str, objectRef, mediaWrapper, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                bookmark.setName(str);
                Ref.ObjectRef<List<Bookmark>> objectRef = objectRef;
                Bookmark[] bookmarks = mediaWrapper.getBookmarks();
                Intrinsics.checkNotNullExpressionValue(bookmarks, "getBookmarks(...)");
                objectRef.element = ArraysKt.toList((T[]) (Object[]) bookmarks);
                ((Bookmark) ((List) objectRef.element).get(((List) objectRef.element).indexOf(bookmark))).setName(str);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final Bookmark bookmark = this.$bookmark;
            final String str = this.$name;
            final Ref.ObjectRef<List<Bookmark>> objectRef = this.$bookmarks;
            final MediaWrapper mediaWrapper = this.$it;
            this.label = 1;
            if (BuildersKt.withContext(Dispatchers.getIO(), new AnonymousClass1((Continuation<? super AnonymousClass1>) null), this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
