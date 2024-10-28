package androidx.car.app.suggestion.model;

import android.app.PendingIntent;
import androidx.car.app.model.CarIcon;
import androidx.car.app.model.CarText;
import androidx.car.app.model.constraints.CarIconConstraints;
import j$.util.Objects;

public final class Suggestion {
    private final PendingIntent mAction;
    private final CarIcon mIcon;
    private final String mIdentifier;
    private final CarText mSubtitle;
    private final CarText mTitle;

    public String getIdentifier() {
        return this.mIdentifier;
    }

    public CarText getTitle() {
        return this.mTitle;
    }

    public CarText getSubtitle() {
        return this.mSubtitle;
    }

    public CarIcon getIcon() {
        return this.mIcon;
    }

    public PendingIntent getAction() {
        return this.mAction;
    }

    public String toString() {
        return "[id: " + this.mIdentifier + ", title: " + CarText.toShortString(this.mTitle) + ", subtitle: " + CarText.toShortString(this.mSubtitle) + ", pendingIntent: " + this.mAction + ", icon: " + this.mIcon + "]";
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Suggestion)) {
            return false;
        }
        Suggestion suggestion = (Suggestion) obj;
        if (!Objects.equals(this.mIdentifier, suggestion.mIdentifier) || !Objects.equals(this.mTitle, suggestion.mTitle) || !Objects.equals(this.mSubtitle, suggestion.mSubtitle) || !Objects.equals(this.mAction, suggestion.mAction) || !Objects.equals(this.mIcon, suggestion.mIcon)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Objects.hash(this.mIdentifier, this.mTitle, this.mSubtitle, this.mIcon, this.mAction);
    }

    Suggestion(Builder builder) {
        this.mIdentifier = (String) Objects.requireNonNull(builder.mId);
        this.mTitle = (CarText) Objects.requireNonNull(builder.mTitle);
        this.mSubtitle = builder.mSubtitle;
        this.mIcon = builder.mIcon;
        this.mAction = (PendingIntent) Objects.requireNonNull(builder.mAction);
    }

    private Suggestion() {
        this.mIdentifier = "";
        this.mTitle = CarText.create("");
        this.mSubtitle = null;
        this.mIcon = null;
        this.mAction = null;
    }

    public static final class Builder {
        PendingIntent mAction;
        CarIcon mIcon;
        String mId;
        CarText mSubtitle;
        CarText mTitle;

        public Builder setIdentifier(String str) {
            this.mId = (String) Objects.requireNonNull(str);
            return this;
        }

        public Builder setTitle(CharSequence charSequence) {
            this.mTitle = CarText.create((CharSequence) Objects.requireNonNull(charSequence));
            return this;
        }

        public Builder setSubtitle(CharSequence charSequence) {
            this.mSubtitle = CarText.create((CharSequence) Objects.requireNonNull(charSequence));
            return this;
        }

        public Builder setAction(PendingIntent pendingIntent) {
            this.mAction = (PendingIntent) Objects.requireNonNull(pendingIntent);
            return this;
        }

        public Builder setIcon(CarIcon carIcon) {
            CarIconConstraints.DEFAULT.validateOrThrow((CarIcon) Objects.requireNonNull(carIcon));
            this.mIcon = carIcon;
            return this;
        }

        public Suggestion build() {
            if (this.mId != null) {
                CarText carText = this.mTitle;
                if (carText == null || carText.isEmpty()) {
                    throw new IllegalStateException("Title is a required field");
                } else if (this.mAction != null) {
                    return new Suggestion(this);
                } else {
                    throw new IllegalStateException("Action is a required field");
                }
            } else {
                throw new IllegalStateException("Identifier is a required field");
            }
        }
    }
}
