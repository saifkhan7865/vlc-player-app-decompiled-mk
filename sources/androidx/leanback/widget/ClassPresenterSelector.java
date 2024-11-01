package androidx.leanback.widget;

import java.util.ArrayList;
import java.util.HashMap;

public final class ClassPresenterSelector extends PresenterSelector {
    private final HashMap<Class<?>, Object> mClassMap = new HashMap<>();
    private final ArrayList<Presenter> mPresenters = new ArrayList<>();

    public ClassPresenterSelector addClassPresenter(Class<?> cls, Presenter presenter) {
        this.mClassMap.put(cls, presenter);
        if (!this.mPresenters.contains(presenter)) {
            this.mPresenters.add(presenter);
        }
        return this;
    }

    public ClassPresenterSelector addClassPresenterSelector(Class<?> cls, PresenterSelector presenterSelector) {
        this.mClassMap.put(cls, presenterSelector);
        Presenter[] presenters = presenterSelector.getPresenters();
        for (int i = 0; i < presenters.length; i++) {
            if (!this.mPresenters.contains(presenters[i])) {
                this.mPresenters.add(presenters[i]);
            }
        }
        return this;
    }

    public Presenter getPresenter(Object obj) {
        Object obj2;
        Presenter presenter;
        Class cls = obj.getClass();
        do {
            obj2 = this.mClassMap.get(cls);
            if (!(obj2 instanceof PresenterSelector) || (presenter = ((PresenterSelector) obj2).getPresenter(obj)) == null) {
                cls = cls.getSuperclass();
                if (obj2 != null) {
                    break;
                }
            } else {
                return presenter;
            }
        } while (cls != null);
        return (Presenter) obj2;
    }

    public Presenter[] getPresenters() {
        ArrayList<Presenter> arrayList = this.mPresenters;
        return (Presenter[]) arrayList.toArray(new Presenter[arrayList.size()]);
    }
}
