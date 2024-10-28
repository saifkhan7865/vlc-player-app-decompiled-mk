package org.videolan.vlc.viewmodels;

import androidx.lifecycle.ViewModelKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.channels.ActorKt;
import kotlinx.coroutines.channels.ActorScope;
import kotlinx.coroutines.channels.SendChannel;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001\"\b\b\u0000\u0010\u0003*\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "Lkotlinx/coroutines/channels/SendChannel;", "Lorg/videolan/vlc/viewmodels/Update;", "T", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: BaseModel.kt */
final class BaseModel$updateActor$2 extends Lambda implements Function0<SendChannel<? super Update>> {
    final /* synthetic */ BaseModel<T> this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BaseModel$updateActor$2(BaseModel<T> baseModel) {
        super(0);
        this.this$0 = baseModel;
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u00020\u00050\u0004H@"}, d2 = {"<anonymous>", "", "T", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "Lkotlinx/coroutines/channels/ActorScope;", "Lorg/videolan/vlc/viewmodels/Update;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "org.videolan.vlc.viewmodels.BaseModel$updateActor$2$1", f = "BaseModel.kt", i = {0, 1, 2, 3, 4, 5}, l = {60, 61, 62, 63, 64, 65}, m = "invokeSuspend", n = {"$this$actor", "$this$actor", "$this$actor", "$this$actor", "$this$actor", "$this$actor"}, s = {"L$0", "L$0", "L$0", "L$0", "L$0", "L$0"})
    /* renamed from: org.videolan.vlc.viewmodels.BaseModel$updateActor$2$1  reason: invalid class name */
    /* compiled from: BaseModel.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<ActorScope<Update>, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 r0 = new AnonymousClass1(baseModel, continuation);
            r0.L$0 = obj;
            return r0;
        }

        public final Object invoke(ActorScope<Update> actorScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(actorScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARNING: Removed duplicated region for block: B:10:0x004a  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final java.lang.Object invokeSuspend(java.lang.Object r9) {
            /*
                r8 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r8.label
                r2 = 1
                switch(r1) {
                    case 0: goto L_0x002b;
                    case 1: goto L_0x001f;
                    case 2: goto L_0x0012;
                    case 3: goto L_0x0012;
                    case 4: goto L_0x0012;
                    case 5: goto L_0x0012;
                    case 6: goto L_0x0012;
                    default: goto L_0x000a;
                }
            L_0x000a:
                java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r9.<init>(r0)
                throw r9
            L_0x0012:
                java.lang.Object r1 = r8.L$1
                kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
                java.lang.Object r3 = r8.L$0
                kotlinx.coroutines.channels.ActorScope r3 = (kotlinx.coroutines.channels.ActorScope) r3
                kotlin.ResultKt.throwOnFailure(r9)
                goto L_0x0123
            L_0x001f:
                java.lang.Object r1 = r8.L$1
                kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
                java.lang.Object r3 = r8.L$0
                kotlinx.coroutines.channels.ActorScope r3 = (kotlinx.coroutines.channels.ActorScope) r3
                kotlin.ResultKt.throwOnFailure(r9)
                goto L_0x004d
            L_0x002b:
                kotlin.ResultKt.throwOnFailure(r9)
                java.lang.Object r9 = r8.L$0
                kotlinx.coroutines.channels.ActorScope r9 = (kotlinx.coroutines.channels.ActorScope) r9
                kotlinx.coroutines.channels.Channel r1 = r9.getChannel()
                kotlinx.coroutines.channels.ChannelIterator r1 = r1.iterator()
            L_0x003a:
                r3 = r8
                kotlin.coroutines.Continuation r3 = (kotlin.coroutines.Continuation) r3
                r8.L$0 = r9
                r8.L$1 = r1
                r8.label = r2
                java.lang.Object r3 = r1.hasNext(r3)
                if (r3 != r0) goto L_0x004a
                return r0
            L_0x004a:
                r7 = r3
                r3 = r9
                r9 = r7
            L_0x004d:
                java.lang.Boolean r9 = (java.lang.Boolean) r9
                boolean r9 = r9.booleanValue()
                if (r9 == 0) goto L_0x0126
                java.lang.Object r9 = r1.next()
                org.videolan.vlc.viewmodels.Update r9 = (org.videolan.vlc.viewmodels.Update) r9
                r4 = r3
                kotlinx.coroutines.CoroutineScope r4 = (kotlinx.coroutines.CoroutineScope) r4
                boolean r4 = kotlinx.coroutines.CoroutineScopeKt.isActive(r4)
                if (r4 == 0) goto L_0x0119
                org.videolan.vlc.viewmodels.Refresh r4 = org.videolan.vlc.viewmodels.Refresh.INSTANCE
                boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r9, (java.lang.Object) r4)
                if (r4 == 0) goto L_0x007f
                org.videolan.vlc.viewmodels.BaseModel<T> r9 = r2
                r4 = r8
                kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
                r8.L$0 = r3
                r8.L$1 = r1
                r5 = 2
                r8.label = r5
                java.lang.Object r9 = r9.updateList(r4)
                if (r9 != r0) goto L_0x0123
                return r0
            L_0x007f:
                boolean r4 = r9 instanceof org.videolan.vlc.viewmodels.Filter
                if (r4 == 0) goto L_0x00a2
                org.videolan.vlc.viewmodels.BaseModel<T> r4 = r2
                org.videolan.vlc.util.FilterDelegate r4 = r4.getFilter()
                org.videolan.vlc.viewmodels.Filter r9 = (org.videolan.vlc.viewmodels.Filter) r9
                java.lang.String r9 = r9.getQuery()
                java.lang.CharSequence r9 = (java.lang.CharSequence) r9
                r5 = r8
                kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
                r8.L$0 = r3
                r8.L$1 = r1
                r6 = 3
                r8.label = r6
                java.lang.Object r9 = r4.filter(r9, r5)
                if (r9 != r0) goto L_0x0123
                return r0
            L_0x00a2:
                boolean r4 = r9 instanceof org.videolan.vlc.viewmodels.MediaUpdate
                java.lang.String r5 = "null cannot be cast to non-null type kotlin.collections.List<T of org.videolan.vlc.viewmodels.BaseModel>"
                if (r4 == 0) goto L_0x00c4
                org.videolan.vlc.viewmodels.BaseModel<T> r4 = r2
                org.videolan.vlc.viewmodels.MediaUpdate r9 = (org.videolan.vlc.viewmodels.MediaUpdate) r9
                java.util.List r9 = r9.getMediaList()
                kotlin.jvm.internal.Intrinsics.checkNotNull(r9, r5)
                r5 = r8
                kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
                r8.L$0 = r3
                r8.L$1 = r1
                r6 = 4
                r8.label = r6
                java.lang.Object r9 = r4.updateItems(r9, r5)
                if (r9 != r0) goto L_0x0123
                return r0
            L_0x00c4:
                boolean r4 = r9 instanceof org.videolan.vlc.viewmodels.MediaAddition
                java.lang.String r6 = "null cannot be cast to non-null type T of org.videolan.vlc.viewmodels.BaseModel"
                if (r4 == 0) goto L_0x00e6
                org.videolan.vlc.viewmodels.BaseModel<T> r4 = r2
                org.videolan.vlc.viewmodels.MediaAddition r9 = (org.videolan.vlc.viewmodels.MediaAddition) r9
                org.videolan.medialibrary.media.MediaLibraryItem r9 = r9.getMedia()
                kotlin.jvm.internal.Intrinsics.checkNotNull(r9, r6)
                r5 = r8
                kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
                r8.L$0 = r3
                r8.L$1 = r1
                r6 = 5
                r8.label = r6
                java.lang.Object r9 = r4.addMedia(r9, (kotlin.coroutines.Continuation<? super kotlin.Unit>) r5)
                if (r9 != r0) goto L_0x0123
                return r0
            L_0x00e6:
                boolean r4 = r9 instanceof org.videolan.vlc.viewmodels.MediaListAddition
                if (r4 == 0) goto L_0x0106
                org.videolan.vlc.viewmodels.BaseModel<T> r4 = r2
                org.videolan.vlc.viewmodels.MediaListAddition r9 = (org.videolan.vlc.viewmodels.MediaListAddition) r9
                java.util.List r9 = r9.getMediaList()
                kotlin.jvm.internal.Intrinsics.checkNotNull(r9, r5)
                r5 = r8
                kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
                r8.L$0 = r3
                r8.L$1 = r1
                r6 = 6
                r8.label = r6
                java.lang.Object r9 = r4.addMedia(r9, (kotlin.coroutines.Continuation<? super kotlin.Unit>) r5)
                if (r9 != r0) goto L_0x0123
                return r0
            L_0x0106:
                boolean r4 = r9 instanceof org.videolan.vlc.viewmodels.Remove
                if (r4 == 0) goto L_0x0123
                org.videolan.vlc.viewmodels.BaseModel<T> r4 = r2
                org.videolan.vlc.viewmodels.Remove r9 = (org.videolan.vlc.viewmodels.Remove) r9
                org.videolan.medialibrary.media.MediaLibraryItem r9 = r9.getMedia()
                kotlin.jvm.internal.Intrinsics.checkNotNull(r9, r6)
                r4.removeMedia(r9)
                goto L_0x0123
            L_0x0119:
                kotlinx.coroutines.channels.Channel r9 = r3.getChannel()
                kotlinx.coroutines.channels.SendChannel r9 = (kotlinx.coroutines.channels.SendChannel) r9
                r4 = 0
                kotlinx.coroutines.channels.SendChannel.DefaultImpls.close$default(r9, r4, r2, r4)
            L_0x0123:
                r9 = r3
                goto L_0x003a
            L_0x0126:
                kotlin.Unit r9 = kotlin.Unit.INSTANCE
                return r9
            */
            throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.viewmodels.BaseModel$updateActor$2.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public final SendChannel<Update> invoke() {
        CoroutineScope viewModelScope = ViewModelKt.getViewModelScope(this.this$0);
        final BaseModel<T> baseModel = this.this$0;
        return ActorKt.actor$default(viewModelScope, (CoroutineContext) null, Integer.MAX_VALUE, (CoroutineStart) null, (Function1) null, new AnonymousClass1((Continuation<? super AnonymousClass1>) null), 13, (Object) null);
    }
}
