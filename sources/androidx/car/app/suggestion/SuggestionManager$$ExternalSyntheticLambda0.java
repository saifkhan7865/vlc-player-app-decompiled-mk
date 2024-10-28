package androidx.car.app.suggestion;

import androidx.car.app.HostCall;
import androidx.car.app.serialization.Bundleable;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SuggestionManager$$ExternalSyntheticLambda0 implements HostCall {
    public final /* synthetic */ Bundleable f$0;

    public /* synthetic */ SuggestionManager$$ExternalSyntheticLambda0(Bundleable bundleable) {
        this.f$0 = bundleable;
    }

    public final Object dispatch(Object obj) {
        return ((ISuggestionHost) obj).updateSuggestions(this.f$0);
    }
}
