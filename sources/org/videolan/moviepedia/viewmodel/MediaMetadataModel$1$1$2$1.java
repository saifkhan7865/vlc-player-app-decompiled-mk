package org.videolan.moviepedia.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.videolan.moviepedia.MediaScraper;
import org.videolan.moviepedia.database.models.MediaMetadata;
import org.videolan.moviepedia.database.models.Person;
import org.videolan.moviepedia.database.models.PersonType;
import org.videolan.moviepedia.repository.MediaPersonRepository;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.moviepedia.viewmodel.MediaMetadataModel$1$1$2$1", f = "MediaMetadataModel.kt", i = {}, l = {83}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: MediaMetadataModel.kt */
final class MediaMetadataModel$1$1$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MediaMetadata $it;
    final /* synthetic */ MediaMetadataFull $mediaMetadataFull;
    int label;
    final /* synthetic */ MediaMetadataModel this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MediaMetadataModel$1$1$2$1(MediaMetadata mediaMetadata, MediaMetadataModel mediaMetadataModel, MediaMetadataFull mediaMetadataFull, Continuation<? super MediaMetadataModel$1$1$2$1> continuation) {
        super(2, continuation);
        this.$it = mediaMetadata;
        this.this$0 = mediaMetadataModel;
        this.$mediaMetadataFull = mediaMetadataFull;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MediaMetadataModel$1$1$2$1(this.$it, this.this$0, this.$mediaMetadataFull, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MediaMetadataModel$1$1$2$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            if (!this.$it.getHasCast() && this.$it.getMlId() != null) {
                final MediaMetadataModel mediaMetadataModel = this.this$0;
                final MediaMetadata mediaMetadata = this.$it;
                this.label = 1;
                if (BuildersKt.withContext(Dispatchers.getIO(), new AnonymousClass1((Continuation<? super AnonymousClass1>) null), this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        MediatorLiveData<MediaMetadataFull> updateLiveData = this.this$0.getUpdateLiveData();
        LiveData<List<Person>> personsByType = ((MediaPersonRepository) MediaPersonRepository.Companion.getInstance(this.this$0.context)).getPersonsByType(this.$it.getMoviepediaId(), PersonType.ACTOR);
        final MediaMetadataFull mediaMetadataFull = this.$mediaMetadataFull;
        final MediaMetadataModel mediaMetadataModel2 = this.this$0;
        updateLiveData.addSource(personsByType, new MediaMetadataModel$sam$androidx_lifecycle_Observer$0(new Function1<List<? extends Person>, Unit>() {
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((List<Person>) (List) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(List<Person> list) {
                mediaMetadataFull.setActors(list);
                mediaMetadataModel2.updateActor.m1139trySendJP2dKIU(mediaMetadataFull);
            }
        }));
        MediatorLiveData<MediaMetadataFull> updateLiveData2 = this.this$0.getUpdateLiveData();
        LiveData<List<Person>> personsByType2 = ((MediaPersonRepository) MediaPersonRepository.Companion.getInstance(this.this$0.context)).getPersonsByType(this.$it.getMoviepediaId(), PersonType.WRITER);
        final MediaMetadataFull mediaMetadataFull2 = this.$mediaMetadataFull;
        final MediaMetadataModel mediaMetadataModel3 = this.this$0;
        updateLiveData2.addSource(personsByType2, new MediaMetadataModel$sam$androidx_lifecycle_Observer$0(new Function1<List<? extends Person>, Unit>() {
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((List<Person>) (List) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(List<Person> list) {
                mediaMetadataFull2.setWriters(list);
                mediaMetadataModel3.updateActor.m1139trySendJP2dKIU(mediaMetadataFull2);
            }
        }));
        MediatorLiveData<MediaMetadataFull> updateLiveData3 = this.this$0.getUpdateLiveData();
        LiveData<List<Person>> personsByType3 = ((MediaPersonRepository) MediaPersonRepository.Companion.getInstance(this.this$0.context)).getPersonsByType(this.$it.getMoviepediaId(), PersonType.PRODUCER);
        final MediaMetadataFull mediaMetadataFull3 = this.$mediaMetadataFull;
        final MediaMetadataModel mediaMetadataModel4 = this.this$0;
        updateLiveData3.addSource(personsByType3, new MediaMetadataModel$sam$androidx_lifecycle_Observer$0(new Function1<List<? extends Person>, Unit>() {
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((List<Person>) (List) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(List<Person> list) {
                mediaMetadataFull3.setProducers(list);
                mediaMetadataModel4.updateActor.m1139trySendJP2dKIU(mediaMetadataFull3);
            }
        }));
        MediatorLiveData<MediaMetadataFull> updateLiveData4 = this.this$0.getUpdateLiveData();
        LiveData<List<Person>> personsByType4 = ((MediaPersonRepository) MediaPersonRepository.Companion.getInstance(this.this$0.context)).getPersonsByType(this.$it.getMoviepediaId(), PersonType.MUSICIAN);
        final MediaMetadataFull mediaMetadataFull4 = this.$mediaMetadataFull;
        final MediaMetadataModel mediaMetadataModel5 = this.this$0;
        updateLiveData4.addSource(personsByType4, new MediaMetadataModel$sam$androidx_lifecycle_Observer$0(new Function1<List<? extends Person>, Unit>() {
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((List<Person>) (List) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(List<Person> list) {
                mediaMetadataFull4.setMusicians(list);
                mediaMetadataModel5.updateActor.m1139trySendJP2dKIU(mediaMetadataFull4);
            }
        }));
        MediatorLiveData<MediaMetadataFull> updateLiveData5 = this.this$0.getUpdateLiveData();
        LiveData<List<Person>> personsByType5 = ((MediaPersonRepository) MediaPersonRepository.Companion.getInstance(this.this$0.context)).getPersonsByType(this.$it.getMoviepediaId(), PersonType.DIRECTOR);
        final MediaMetadataFull mediaMetadataFull5 = this.$mediaMetadataFull;
        final MediaMetadataModel mediaMetadataModel6 = this.this$0;
        updateLiveData5.addSource(personsByType5, new MediaMetadataModel$sam$androidx_lifecycle_Observer$0(new Function1<List<? extends Person>, Unit>() {
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((List<Person>) (List) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(List<Person> list) {
                mediaMetadataFull5.setDirectors(list);
                mediaMetadataModel6.updateActor.m1139trySendJP2dKIU(mediaMetadataFull5);
            }
        }));
        return Unit.INSTANCE;
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "org.videolan.moviepedia.viewmodel.MediaMetadataModel$1$1$2$1$1", f = "MediaMetadataModel.kt", i = {}, l = {83}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: org.videolan.moviepedia.viewmodel.MediaMetadataModel$1$1$2$1$1  reason: invalid class name */
    /* compiled from: MediaMetadataModel.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(mediaMetadataModel, mediaMetadata, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (MediaScraper.INSTANCE.retrieveCasting(mediaMetadataModel.context, mediaMetadata, this) == coroutine_suspended) {
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
}
