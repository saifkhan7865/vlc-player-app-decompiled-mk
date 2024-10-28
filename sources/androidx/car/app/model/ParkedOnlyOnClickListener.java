package androidx.car.app.model;

import j$.util.Objects;

public final class ParkedOnlyOnClickListener implements OnClickListener {
    private final OnClickListener mListener;

    public void onClick() {
        this.mListener.onClick();
    }

    public static ParkedOnlyOnClickListener create(OnClickListener onClickListener) {
        return new ParkedOnlyOnClickListener((OnClickListener) Objects.requireNonNull(onClickListener));
    }

    private ParkedOnlyOnClickListener(OnClickListener onClickListener) {
        this.mListener = onClickListener;
    }
}
