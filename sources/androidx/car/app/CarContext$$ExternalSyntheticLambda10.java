package androidx.car.app;

import androidx.car.app.managers.Manager;
import androidx.car.app.managers.ManagerFactory;
import androidx.lifecycle.Lifecycle;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class CarContext$$ExternalSyntheticLambda10 implements ManagerFactory {
    public final /* synthetic */ CarContext f$0;
    public final /* synthetic */ HostDispatcher f$1;
    public final /* synthetic */ Lifecycle f$2;

    public /* synthetic */ CarContext$$ExternalSyntheticLambda10(CarContext carContext, HostDispatcher hostDispatcher, Lifecycle lifecycle) {
        this.f$0 = carContext;
        this.f$1 = hostDispatcher;
        this.f$2 = lifecycle;
    }

    public final Manager create() {
        return this.f$0.m22lambda$new$4$androidxcarappCarContext(this.f$1, this.f$2);
    }
}
