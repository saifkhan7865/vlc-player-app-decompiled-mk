package androidx.window.embedding;

import androidx.core.util.Consumer;
import java.util.List;
import kotlinx.coroutines.channels.ProducerScope;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SplitController$splitInfoList$1$$ExternalSyntheticLambda0 implements Consumer {
    public final /* synthetic */ ProducerScope f$0;

    public /* synthetic */ SplitController$splitInfoList$1$$ExternalSyntheticLambda0(ProducerScope producerScope) {
        this.f$0 = producerScope;
    }

    public final void accept(Object obj) {
        SplitController$splitInfoList$1.invokeSuspend$lambda$0(this.f$0, (List) obj);
    }
}
