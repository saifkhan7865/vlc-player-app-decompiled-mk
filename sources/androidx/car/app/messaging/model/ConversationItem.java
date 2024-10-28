package androidx.car.app.messaging.model;

import androidx.car.app.annotations.RequiresCarApi;
import androidx.car.app.model.CarIcon;
import androidx.car.app.model.CarText;
import androidx.car.app.model.Item;
import j$.util.Objects;
import java.util.ArrayList;
import java.util.List;

@RequiresCarApi(6)
public class ConversationItem implements Item {
    private final CarIcon mIcon;
    private final String mId;
    private final boolean mIsGroupConversation;
    private final List<CarMessage> mMessages;
    private final CarText mTitle;

    ConversationItem(Builder builder) {
        this.mId = (String) Objects.requireNonNull(builder.mId);
        this.mTitle = (CarText) Objects.requireNonNull(builder.mTitle);
        this.mIcon = builder.mIcon;
        this.mIsGroupConversation = builder.mIsGroupConversation;
        this.mMessages = (List) Objects.requireNonNull(builder.mMessages);
    }

    private ConversationItem() {
        this.mId = "";
        this.mTitle = new CarText.Builder("").build();
        this.mIcon = null;
        this.mIsGroupConversation = false;
        this.mMessages = new ArrayList();
    }

    public String getId() {
        return this.mId;
    }

    public CarText getTitle() {
        return this.mTitle;
    }

    public CarIcon getIcon() {
        return this.mIcon;
    }

    public boolean isGroupConversation() {
        return this.mIsGroupConversation;
    }

    public List<CarMessage> getMessages() {
        return this.mMessages;
    }

    public static final class Builder {
        CarIcon mIcon;
        String mId;
        boolean mIsGroupConversation;
        List<CarMessage> mMessages;
        CarText mTitle;

        public Builder setId(String str) {
            this.mId = str;
            return this;
        }

        public Builder setTitle(CarText carText) {
            this.mTitle = carText;
            return this;
        }

        public Builder setIcon(CarIcon carIcon) {
            this.mIcon = carIcon;
            return this;
        }

        public Builder setGroupConversation(boolean z) {
            this.mIsGroupConversation = z;
            return this;
        }

        public Builder setMessages(List<CarMessage> list) {
            this.mMessages = list;
            return this;
        }

        public ConversationItem build() {
            return new ConversationItem(this);
        }
    }
}
