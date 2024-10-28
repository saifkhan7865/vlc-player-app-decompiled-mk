package org.videolan.moviepedia.repository;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.moviepedia.database.models.Person;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.moviepedia.repository.PersonRepository$addPerson$1", f = "PersonRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: PersonRepository.kt */
final class PersonRepository$addPerson$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Person $person;
    int label;
    final /* synthetic */ PersonRepository this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PersonRepository$addPerson$1(PersonRepository personRepository, Person person, Continuation<? super PersonRepository$addPerson$1> continuation) {
        super(2, continuation);
        this.this$0 = personRepository;
        this.$person = person;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PersonRepository$addPerson$1(this.this$0, this.$person, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PersonRepository$addPerson$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            this.this$0.personDao.insert(this.$person);
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
