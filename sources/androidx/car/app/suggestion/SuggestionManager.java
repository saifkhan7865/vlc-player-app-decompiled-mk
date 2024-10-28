package androidx.car.app.suggestion;

import androidx.car.app.CarContext;
import androidx.car.app.HostDispatcher;
import androidx.car.app.annotations.RequiresCarApi;
import androidx.car.app.managers.Manager;
import androidx.car.app.serialization.Bundleable;
import androidx.car.app.serialization.BundlerException;
import androidx.car.app.suggestion.model.Suggestion;
import androidx.car.app.utils.ThreadUtils;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import j$.util.Objects;
import java.util.List;

@RequiresCarApi(5)
public class SuggestionManager implements Manager {
    private final HostDispatcher mHostDispatcher;

    public void updateSuggestions(List<Suggestion> list) {
        ThreadUtils.checkMainThread();
        try {
            this.mHostDispatcher.dispatch(CarContext.SUGGESTION_SERVICE, "updateSuggestions", new SuggestionManager$$ExternalSyntheticLambda0(Bundleable.create(list)));
        } catch (BundlerException e) {
            throw new IllegalArgumentException("Serialization failure", e);
        }
    }

    public static SuggestionManager create(CarContext carContext, HostDispatcher hostDispatcher, Lifecycle lifecycle) {
        Objects.requireNonNull(carContext);
        Objects.requireNonNull(hostDispatcher);
        Objects.requireNonNull(lifecycle);
        return new SuggestionManager(carContext, hostDispatcher, lifecycle);
    }

    protected SuggestionManager(CarContext carContext, HostDispatcher hostDispatcher, final Lifecycle lifecycle) {
        Objects.requireNonNull(carContext);
        this.mHostDispatcher = (HostDispatcher) Objects.requireNonNull(hostDispatcher);
        lifecycle.addObserver(new DefaultLifecycleObserver() {
            public /* synthetic */ void onCreate(LifecycleOwner lifecycleOwner) {
                DefaultLifecycleObserver.CC.$default$onCreate(this, lifecycleOwner);
            }

            public /* synthetic */ void onPause(LifecycleOwner lifecycleOwner) {
                DefaultLifecycleObserver.CC.$default$onPause(this, lifecycleOwner);
            }

            public /* synthetic */ void onResume(LifecycleOwner lifecycleOwner) {
                DefaultLifecycleObserver.CC.$default$onResume(this, lifecycleOwner);
            }

            public /* synthetic */ void onStart(LifecycleOwner lifecycleOwner) {
                DefaultLifecycleObserver.CC.$default$onStart(this, lifecycleOwner);
            }

            public /* synthetic */ void onStop(LifecycleOwner lifecycleOwner) {
                DefaultLifecycleObserver.CC.$default$onStop(this, lifecycleOwner);
            }

            public void onDestroy(LifecycleOwner lifecycleOwner) {
                lifecycle.removeObserver(this);
            }
        });
    }
}
