package androidx.car.app.model;

import androidx.car.app.annotations.RequiresCarApi;

@RequiresCarApi(6)
public interface Content {
    String getContentId();
}
