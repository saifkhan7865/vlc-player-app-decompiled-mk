package org.videolan.vlc.gui.helpers;

import androidx.lifecycle.LifecycleOwnerKt;
import io.ktor.http.ContentDisposition;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import org.videolan.medialibrary.interfaces.media.Bookmark;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.vlc.viewmodels.BookmarkModel;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "media", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "name", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: BookmarkListDelegate.kt */
final class BookmarkListDelegate$onPopupMenu$1$1 extends Lambda implements Function2<MediaLibraryItem, String, Unit> {
    final /* synthetic */ BookmarkListDelegate this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BookmarkListDelegate$onPopupMenu$1$1(BookmarkListDelegate bookmarkListDelegate) {
        super(2);
        this.this$0 = bookmarkListDelegate;
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "org.videolan.vlc.gui.helpers.BookmarkListDelegate$onPopupMenu$1$1$1", f = "BookmarkListDelegate.kt", i = {}, l = {144}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: org.videolan.vlc.gui.helpers.BookmarkListDelegate$onPopupMenu$1$1$1  reason: invalid class name */
    /* compiled from: BookmarkListDelegate.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(bookmarkListDelegate, mediaLibraryItem, str, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                BookmarkModel access$getBookmarkModel$p = bookmarkListDelegate.bookmarkModel;
                MediaLibraryItem mediaLibraryItem = mediaLibraryItem;
                Intrinsics.checkNotNull(mediaLibraryItem, "null cannot be cast to non-null type org.videolan.medialibrary.interfaces.media.Bookmark");
                this.label = 1;
                obj = access$getBookmarkModel$p.rename((Bookmark) mediaLibraryItem, str, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (i == 1) {
                ResultKt.throwOnFailure(obj);
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            List list = (List) obj;
            BookmarkAdapter access$getAdapter$p = bookmarkListDelegate.adapter;
            if (access$getAdapter$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                access$getAdapter$p = null;
            }
            access$getAdapter$p.update(list);
            bookmarkListDelegate.bookmarkModel.refresh();
            return Unit.INSTANCE;
        }
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((MediaLibraryItem) obj, (String) obj2);
        return Unit.INSTANCE;
    }

    public final void invoke(final MediaLibraryItem mediaLibraryItem, final String str) {
        Intrinsics.checkNotNullParameter(mediaLibraryItem, "media");
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        final BookmarkListDelegate bookmarkListDelegate = this.this$0;
        Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this.this$0.getActivity()), (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass1((Continuation<? super AnonymousClass1>) null), 3, (Object) null);
    }
}
