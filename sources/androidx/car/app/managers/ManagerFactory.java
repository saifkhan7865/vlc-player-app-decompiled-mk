package androidx.car.app.managers;

import androidx.car.app.managers.Manager;

public interface ManagerFactory<T extends Manager> {
    T create();
}
