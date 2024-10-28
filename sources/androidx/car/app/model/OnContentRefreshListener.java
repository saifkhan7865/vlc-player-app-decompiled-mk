package androidx.car.app.model;

import androidx.car.app.annotations.RequiresCarApi;

@RequiresCarApi(5)
public interface OnContentRefreshListener {
    void onContentRefreshRequested();
}
