package androidx.car.app.model;

import androidx.car.app.annotations.RequiresCarApi;
import j$.util.Objects;

@RequiresCarApi(2)
public final class ClickableSpan extends CarSpan {
    private final OnClickDelegate mOnClickDelegate;

    public static ClickableSpan create(OnClickListener onClickListener) {
        return new ClickableSpan((OnClickListener) Objects.requireNonNull(onClickListener));
    }

    public OnClickDelegate getOnClickDelegate() {
        return (OnClickDelegate) Objects.requireNonNull(this.mOnClickDelegate);
    }

    public String toString() {
        return "[clickable]";
    }

    public int hashCode() {
        return Objects.hash(Boolean.valueOf(this.mOnClickDelegate == null));
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ClickableSpan)) {
            return false;
        }
        ClickableSpan clickableSpan = (ClickableSpan) obj;
        Boolean valueOf = Boolean.valueOf(this.mOnClickDelegate == null);
        if (clickableSpan.mOnClickDelegate != null) {
            z = false;
        }
        return Objects.equals(valueOf, Boolean.valueOf(z));
    }

    private ClickableSpan(OnClickListener onClickListener) {
        this.mOnClickDelegate = OnClickDelegateImpl.create(onClickListener);
    }

    private ClickableSpan() {
        this.mOnClickDelegate = null;
    }
}
