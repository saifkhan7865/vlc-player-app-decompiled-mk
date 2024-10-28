package androidx.car.app.messaging.model;

import android.os.Bundle;
import androidx.car.app.annotations.RequiresCarApi;
import androidx.car.app.model.CarText;
import androidx.core.app.Person;
import j$.util.Objects;

@RequiresCarApi(6)
public class CarMessage {
    private final CarText mBody;
    private final boolean mIsRead;
    private final long mReceivedTimeEpochMillis;
    private final Bundle mSender;

    CarMessage(Builder builder) {
        this.mSender = ((Person) Objects.requireNonNull(builder.mSender)).toBundle();
        this.mBody = (CarText) Objects.requireNonNull(builder.mBody);
        this.mReceivedTimeEpochMillis = builder.mReceivedTimeEpochMillis;
        this.mIsRead = builder.mIsRead;
    }

    private CarMessage() {
        this.mSender = new Person.Builder().setName("").build().toBundle();
        this.mBody = new CarText.Builder("").build();
        this.mReceivedTimeEpochMillis = 0;
        this.mIsRead = false;
    }

    public Person getSender() {
        return Person.fromBundle(this.mSender);
    }

    public CarText getBody() {
        return this.mBody;
    }

    public long getReceivedTimeEpochMillis() {
        return this.mReceivedTimeEpochMillis;
    }

    public boolean isRead() {
        return this.mIsRead;
    }

    public static final class Builder {
        CarText mBody;
        boolean mIsRead;
        long mReceivedTimeEpochMillis;
        Person mSender;

        public Builder setSender(Person person) {
            this.mSender = person;
            return this;
        }

        public Builder setBody(CarText carText) {
            this.mBody = carText;
            return this;
        }

        public Builder setReceivedTimeEpochMillis(long j) {
            this.mReceivedTimeEpochMillis = j;
            return this;
        }

        public Builder setRead(boolean z) {
            this.mIsRead = z;
            return this;
        }

        public CarMessage build() {
            return new CarMessage(this);
        }
    }
}
