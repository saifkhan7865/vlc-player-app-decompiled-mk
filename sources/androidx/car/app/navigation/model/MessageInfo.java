package androidx.car.app.navigation.model;

import androidx.car.app.model.CarIcon;
import androidx.car.app.model.CarText;
import androidx.car.app.model.constraints.CarIconConstraints;
import androidx.car.app.model.constraints.CarTextConstraints;
import androidx.car.app.navigation.model.NavigationTemplate;
import j$.util.Objects;

public final class MessageInfo implements NavigationTemplate.NavigationInfo {
    private final CarIcon mImage;
    private final CarText mText;
    private final CarText mTitle;

    public CarText getTitle() {
        return this.mTitle;
    }

    public CarText getText() {
        return this.mText;
    }

    public CarIcon getImage() {
        return this.mImage;
    }

    public String toString() {
        return "MessageInfo";
    }

    public int hashCode() {
        return Objects.hash(this.mTitle, this.mText, this.mImage);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MessageInfo)) {
            return false;
        }
        MessageInfo messageInfo = (MessageInfo) obj;
        if (!Objects.equals(this.mTitle, messageInfo.mTitle) || !Objects.equals(this.mText, messageInfo.mText) || !Objects.equals(this.mImage, messageInfo.mImage)) {
            return false;
        }
        return true;
    }

    MessageInfo(Builder builder) {
        this.mTitle = builder.mTitle;
        this.mText = builder.mText;
        this.mImage = builder.mImage;
    }

    private MessageInfo() {
        this.mTitle = null;
        this.mText = null;
        this.mImage = null;
    }

    public static final class Builder {
        CarIcon mImage;
        CarText mText;
        CarText mTitle;

        public Builder setTitle(CharSequence charSequence) {
            this.mTitle = CarText.create((CharSequence) Objects.requireNonNull(charSequence));
            CarTextConstraints.TEXT_ONLY.validateOrThrow(this.mTitle);
            return this;
        }

        public Builder setText(CharSequence charSequence) {
            this.mText = CarText.create((CharSequence) Objects.requireNonNull(charSequence));
            CarTextConstraints.TEXT_ONLY.validateOrThrow(this.mText);
            return this;
        }

        public Builder setText(CarText carText) {
            this.mText = (CarText) Objects.requireNonNull(carText);
            CarTextConstraints.TEXT_ONLY.validateOrThrow(this.mText);
            return this;
        }

        public Builder setImage(CarIcon carIcon) {
            CarIconConstraints.DEFAULT.validateOrThrow((CarIcon) Objects.requireNonNull(carIcon));
            this.mImage = carIcon;
            return this;
        }

        public MessageInfo build() {
            return new MessageInfo(this);
        }

        public Builder(CharSequence charSequence) {
            this.mTitle = CarText.create((CharSequence) Objects.requireNonNull(charSequence));
            CarTextConstraints.TEXT_ONLY.validateOrThrow(this.mTitle);
        }

        public Builder(CarText carText) {
            this.mTitle = (CarText) Objects.requireNonNull(carText);
        }
    }
}
