package androidx.car.app;

import android.util.Log;
import androidx.car.app.managers.Manager;
import androidx.car.app.model.TemplateWrapper;
import androidx.car.app.utils.LogTags;
import androidx.car.app.utils.ThreadUtils;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import j$.util.Objects;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public class ScreenManager implements Manager {
    private final Lifecycle mAppLifecycle;
    private final CarContext mCarContext;
    private final Deque<Screen> mScreenStack = new ArrayDeque();

    public Screen getTop() {
        ThreadUtils.checkMainThread();
        return (Screen) Objects.requireNonNull(this.mScreenStack.peek());
    }

    public void push(Screen screen) {
        ThreadUtils.checkMainThread();
        if (!this.mAppLifecycle.getCurrentState().equals(Lifecycle.State.DESTROYED)) {
            pushInternal((Screen) Objects.requireNonNull(screen));
        } else if (Log.isLoggable(LogTags.TAG, 3)) {
            Log.d(LogTags.TAG, "Pushing screens after the DESTROYED state is a no-op");
        }
    }

    public void pushForResult(Screen screen, OnScreenResultListener onScreenResultListener) {
        ThreadUtils.checkMainThread();
        if (!this.mAppLifecycle.getCurrentState().equals(Lifecycle.State.DESTROYED)) {
            ((Screen) Objects.requireNonNull(screen)).setOnScreenResultListener((OnScreenResultListener) Objects.requireNonNull(onScreenResultListener));
            pushInternal(screen);
        } else if (Log.isLoggable(LogTags.TAG, 3)) {
            Log.d(LogTags.TAG, "Pushing screens after the DESTROYED state is a no-op");
        }
    }

    public void pop() {
        ThreadUtils.checkMainThread();
        if (this.mAppLifecycle.getCurrentState().equals(Lifecycle.State.DESTROYED)) {
            if (Log.isLoggable(LogTags.TAG, 3)) {
                Log.d(LogTags.TAG, "Popping screens after the DESTROYED state is a no-op");
            }
        } else if (this.mScreenStack.size() > 1) {
            popInternal(Collections.singletonList(this.mScreenStack.pop()));
        }
    }

    public void popTo(String str) {
        ThreadUtils.checkMainThread();
        Objects.requireNonNull(str);
        if (!this.mAppLifecycle.getCurrentState().equals(Lifecycle.State.DESTROYED)) {
            ArrayList arrayList = new ArrayList();
            while (this.mScreenStack.size() > 1 && !foundMarker(str)) {
                arrayList.add(this.mScreenStack.pop());
            }
            if (!arrayList.isEmpty()) {
                popInternal(arrayList);
            }
        } else if (Log.isLoggable(LogTags.TAG, 3)) {
            Log.d(LogTags.TAG, "Popping screens after the DESTROYED state is a no-op");
        }
    }

    public void popToRoot() {
        ThreadUtils.checkMainThread();
        if (this.mAppLifecycle.getCurrentState().equals(Lifecycle.State.DESTROYED)) {
            if (Log.isLoggable(LogTags.TAG, 3)) {
                Log.d(LogTags.TAG, "Popping screens after the DESTROYED state is a no-op");
            }
        } else if (this.mScreenStack.size() > 1) {
            ArrayList arrayList = new ArrayList();
            while (this.mScreenStack.size() > 1) {
                arrayList.add(this.mScreenStack.pop());
            }
            popInternal(arrayList);
        }
    }

    public void remove(Screen screen) {
        ThreadUtils.checkMainThread();
        Objects.requireNonNull(screen);
        if (this.mAppLifecycle.getCurrentState().equals(Lifecycle.State.DESTROYED)) {
            if (Log.isLoggable(LogTags.TAG, 3)) {
                Log.d(LogTags.TAG, "Popping screens after the DESTROYED state is a no-op");
            }
        } else if (this.mScreenStack.size() > 1) {
            if (screen.equals(getTop())) {
                this.mScreenStack.pop();
                popInternal(Collections.singletonList(screen));
            } else if (this.mScreenStack.remove(screen)) {
                screen.dispatchLifecycleEvent(Lifecycle.Event.ON_DESTROY);
            }
        }
    }

    public int getStackSize() {
        return this.mScreenStack.size();
    }

    static ScreenManager create(CarContext carContext, Lifecycle lifecycle) {
        return new ScreenManager(carContext, lifecycle);
    }

    /* access modifiers changed from: package-private */
    public TemplateWrapper getTopTemplate() {
        ThreadUtils.checkMainThread();
        Screen top = getTop();
        if (Log.isLoggable(LogTags.TAG, 3)) {
            Log.d(LogTags.TAG, "Requesting template from Screen " + top);
        }
        TemplateWrapper templateWrapper = top.getTemplateWrapper();
        ArrayList arrayList = new ArrayList();
        for (Screen lastTemplateInfo : this.mScreenStack) {
            arrayList.add(lastTemplateInfo.getLastTemplateInfo());
        }
        templateWrapper.setTemplateInfosForScreenStack(arrayList);
        return templateWrapper;
    }

    /* access modifiers changed from: package-private */
    public void destroyAndClearScreenStack() {
        for (Screen stop : new ArrayDeque(this.mScreenStack)) {
            stop(stop, true);
        }
        this.mScreenStack.clear();
    }

    /* access modifiers changed from: protected */
    public Deque<Screen> getScreenStack() {
        return this.mScreenStack;
    }

    private boolean foundMarker(String str) {
        return str.equals(getTop().getMarker());
    }

    private void pushInternal(Screen screen) {
        if (Log.isLoggable(LogTags.TAG, 3)) {
            Log.d(LogTags.TAG, "Pushing screen " + screen + " to the top of the screen stack");
        }
        if (this.mScreenStack.contains(screen)) {
            moveToTop(screen);
            return;
        }
        Screen peek = this.mScreenStack.peek();
        pushAndStart(screen, true);
        if (this.mScreenStack.contains(screen)) {
            if (peek != null) {
                stop(peek, false);
            }
            if (this.mAppLifecycle.getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
                screen.dispatchLifecycleEvent(Lifecycle.Event.ON_RESUME);
            }
        }
    }

    private void popInternal(List<Screen> list) {
        Screen top = getTop();
        top.setUseLastTemplateId(true);
        ((AppManager) this.mCarContext.getCarService(AppManager.class)).invalidate();
        if (this.mAppLifecycle.getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
            top.dispatchLifecycleEvent(Lifecycle.Event.ON_START);
        }
        for (Screen next : list) {
            if (Log.isLoggable(LogTags.TAG, 3)) {
                Log.d(LogTags.TAG, "Popping screen " + next + " off the screen stack");
            }
            stop(next, true);
        }
        if (Log.isLoggable(LogTags.TAG, 3)) {
            Log.d(LogTags.TAG, "Screen " + top + " is at the top of the screen stack");
        }
        if (this.mAppLifecycle.getCurrentState().isAtLeast(Lifecycle.State.RESUMED) && this.mScreenStack.contains(top)) {
            top.dispatchLifecycleEvent(Lifecycle.Event.ON_RESUME);
        }
    }

    private void pushAndStart(Screen screen, boolean z) {
        this.mScreenStack.push(screen);
        if (z && this.mAppLifecycle.getCurrentState().isAtLeast(Lifecycle.State.CREATED)) {
            screen.dispatchLifecycleEvent(Lifecycle.Event.ON_CREATE);
        }
        if (screen.getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.CREATED) && this.mAppLifecycle.getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
            ((AppManager) this.mCarContext.getCarService(AppManager.class)).invalidate();
            screen.dispatchLifecycleEvent(Lifecycle.Event.ON_START);
        }
    }

    private void stop(Screen screen, boolean z) {
        Lifecycle.State currentState = screen.getLifecycle().getCurrentState();
        if (currentState.isAtLeast(Lifecycle.State.RESUMED)) {
            screen.dispatchLifecycleEvent(Lifecycle.Event.ON_PAUSE);
        }
        if (currentState.isAtLeast(Lifecycle.State.STARTED)) {
            screen.dispatchLifecycleEvent(Lifecycle.Event.ON_STOP);
        }
        if (z) {
            screen.dispatchLifecycleEvent(Lifecycle.Event.ON_DESTROY);
        }
    }

    private void moveToTop(Screen screen) {
        Screen peek = this.mScreenStack.peek();
        if (peek != null && peek != screen) {
            this.mScreenStack.remove(screen);
            pushAndStart(screen, false);
            stop(peek, false);
            if (this.mAppLifecycle.getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
                screen.dispatchLifecycleEvent(Lifecycle.Event.ON_RESUME);
            }
        }
    }

    protected ScreenManager(CarContext carContext, Lifecycle lifecycle) {
        this.mCarContext = carContext;
        this.mAppLifecycle = lifecycle;
        lifecycle.addObserver(new LifecycleObserverImpl());
    }

    class LifecycleObserverImpl implements DefaultLifecycleObserver {
        public void onCreate(LifecycleOwner lifecycleOwner) {
        }

        LifecycleObserverImpl() {
        }

        public void onStart(LifecycleOwner lifecycleOwner) {
            Screen peek = ScreenManager.this.getScreenStack().peek();
            if (peek == null) {
                Log.e(LogTags.TAG, "Screen stack was empty during lifecycle onStart");
            } else {
                peek.dispatchLifecycleEvent(Lifecycle.Event.ON_START);
            }
        }

        public void onResume(LifecycleOwner lifecycleOwner) {
            Screen peek = ScreenManager.this.getScreenStack().peek();
            if (peek == null) {
                Log.e(LogTags.TAG, "Screen stack was empty during lifecycle onResume");
            } else {
                peek.dispatchLifecycleEvent(Lifecycle.Event.ON_RESUME);
            }
        }

        public void onPause(LifecycleOwner lifecycleOwner) {
            Screen peek = ScreenManager.this.getScreenStack().peek();
            if (peek == null) {
                Log.e(LogTags.TAG, "Screen stack was empty during lifecycle onPause");
            } else {
                peek.dispatchLifecycleEvent(Lifecycle.Event.ON_PAUSE);
            }
        }

        public void onStop(LifecycleOwner lifecycleOwner) {
            Screen peek = ScreenManager.this.getScreenStack().peek();
            if (peek == null) {
                Log.e(LogTags.TAG, "Screen stack was empty during lifecycle onStop");
            } else {
                peek.dispatchLifecycleEvent(Lifecycle.Event.ON_STOP);
            }
        }

        public void onDestroy(LifecycleOwner lifecycleOwner) {
            ScreenManager.this.destroyAndClearScreenStack();
            lifecycleOwner.getLifecycle().removeObserver(this);
        }
    }
}
