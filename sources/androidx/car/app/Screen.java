package androidx.car.app;

import android.util.Log;
import androidx.car.app.model.Template;
import androidx.car.app.model.TemplateInfo;
import androidx.car.app.model.TemplateWrapper;
import androidx.car.app.utils.LogTags;
import androidx.car.app.utils.ThreadUtils;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import j$.util.Objects;

public abstract class Screen implements LifecycleOwner {
    private final CarContext mCarContext;
    private final LifecycleRegistry mLifecycleRegistry = new LifecycleRegistry(this);
    private String mMarker;
    private OnScreenResultListener mOnScreenResultListener = new Screen$$ExternalSyntheticLambda0();
    private Object mResult;
    private TemplateWrapper mTemplateWrapper;
    private boolean mUseLastTemplateId;

    static /* synthetic */ void lambda$new$0(Object obj) {
    }

    public abstract Template onGetTemplate();

    protected Screen(CarContext carContext) {
        this.mCarContext = (CarContext) Objects.requireNonNull(carContext);
    }

    public final void invalidate() {
        if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
            ((AppManager) this.mCarContext.getCarService(AppManager.class)).invalidate();
        }
    }

    public final void finish() {
        ((ScreenManager) this.mCarContext.getCarService(ScreenManager.class)).remove(this);
    }

    public void setResult(Object obj) {
        this.mResult = obj;
    }

    public Object getResultInternal() {
        return this.mResult;
    }

    public void setMarker(String str) {
        this.mMarker = str;
    }

    public String getMarker() {
        return this.mMarker;
    }

    public final Lifecycle getLifecycle() {
        return this.mLifecycleRegistry;
    }

    public final CarContext getCarContext() {
        return this.mCarContext;
    }

    public final ScreenManager getScreenManager() {
        return (ScreenManager) this.mCarContext.getCarService(ScreenManager.class);
    }

    /* access modifiers changed from: package-private */
    public void setOnScreenResultListener(OnScreenResultListener onScreenResultListener) {
        this.mOnScreenResultListener = onScreenResultListener;
    }

    public void dispatchLifecycleEvent(Lifecycle.Event event) {
        ThreadUtils.runOnMain(new Screen$$ExternalSyntheticLambda1(this, event));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$dispatchLifecycleEvent$1$androidx-car-app-Screen  reason: not valid java name */
    public /* synthetic */ void m34lambda$dispatchLifecycleEvent$1$androidxcarappScreen(Lifecycle.Event event) {
        if (this.mLifecycleRegistry.getCurrentState().isAtLeast(Lifecycle.State.INITIALIZED)) {
            if (event == Lifecycle.Event.ON_DESTROY) {
                this.mOnScreenResultListener.onScreenResult(this.mResult);
            }
            this.mLifecycleRegistry.handleLifecycleEvent(event);
        }
    }

    /* access modifiers changed from: package-private */
    public TemplateWrapper getTemplateWrapper() {
        TemplateWrapper templateWrapper;
        TemplateWrapper templateWrapper2;
        Template onGetTemplate = onGetTemplate();
        if (!this.mUseLastTemplateId || (templateWrapper2 = this.mTemplateWrapper) == null) {
            templateWrapper = TemplateWrapper.wrap(onGetTemplate);
        } else {
            templateWrapper = TemplateWrapper.wrap(onGetTemplate, getLastTemplateInfo(templateWrapper2).getTemplateId());
        }
        this.mUseLastTemplateId = false;
        this.mTemplateWrapper = templateWrapper;
        if (Log.isLoggable(LogTags.TAG, 3)) {
            Log.d(LogTags.TAG, "Returning " + onGetTemplate + " from screen " + this);
        }
        return templateWrapper;
    }

    /* access modifiers changed from: package-private */
    public TemplateInfo getLastTemplateInfo() {
        if (this.mTemplateWrapper == null) {
            this.mTemplateWrapper = TemplateWrapper.wrap(onGetTemplate());
        }
        return new TemplateInfo(this.mTemplateWrapper.getTemplate().getClass(), this.mTemplateWrapper.getId());
    }

    private static TemplateInfo getLastTemplateInfo(TemplateWrapper templateWrapper) {
        return new TemplateInfo(templateWrapper.getTemplate().getClass(), templateWrapper.getId());
    }

    /* access modifiers changed from: package-private */
    public void setUseLastTemplateId(boolean z) {
        this.mUseLastTemplateId = z;
    }
}
