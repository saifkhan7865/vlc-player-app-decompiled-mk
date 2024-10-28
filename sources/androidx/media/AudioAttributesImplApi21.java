package androidx.media;

import android.media.AudioAttributes;
import androidx.media.AudioAttributesImpl;
import androidx.tracing.Trace$$ExternalSyntheticApiModelOutline0;

public class AudioAttributesImplApi21 implements AudioAttributesImpl {
    public AudioAttributes mAudioAttributes;
    public int mLegacyStreamType;

    public AudioAttributesImplApi21() {
        this.mLegacyStreamType = -1;
    }

    AudioAttributesImplApi21(AudioAttributes audioAttributes) {
        this(audioAttributes, -1);
    }

    AudioAttributesImplApi21(AudioAttributes audioAttributes, int i) {
        this.mAudioAttributes = audioAttributes;
        this.mLegacyStreamType = i;
    }

    public Object getAudioAttributes() {
        return this.mAudioAttributes;
    }

    public int getVolumeControlStream() {
        return AudioAttributesCompat.toVolumeStreamType(true, getFlags(), getUsage());
    }

    public int getLegacyStreamType() {
        int i = this.mLegacyStreamType;
        if (i != -1) {
            return i;
        }
        return AudioAttributesCompat.toVolumeStreamType(false, getFlags(), getUsage());
    }

    public int getRawLegacyStreamType() {
        return this.mLegacyStreamType;
    }

    public int getContentType() {
        return this.mAudioAttributes.getContentType();
    }

    public int getUsage() {
        return this.mAudioAttributes.getUsage();
    }

    public int getFlags() {
        return this.mAudioAttributes.getFlags();
    }

    public int hashCode() {
        return this.mAudioAttributes.hashCode();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof AudioAttributesImplApi21)) {
            return false;
        }
        return this.mAudioAttributes.equals(((AudioAttributesImplApi21) obj).mAudioAttributes);
    }

    public String toString() {
        return "AudioAttributesCompat: audioattributes=" + this.mAudioAttributes;
    }

    static class Builder implements AudioAttributesImpl.Builder {
        final AudioAttributes.Builder mFwkBuilder;

        Builder() {
            this.mFwkBuilder = new AudioAttributes.Builder();
        }

        Builder(Object obj) {
            this.mFwkBuilder = new AudioAttributes.Builder(Trace$$ExternalSyntheticApiModelOutline0.m(obj));
        }

        public AudioAttributesImpl build() {
            return new AudioAttributesImplApi21(this.mFwkBuilder.build());
        }

        public Builder setUsage(int i) {
            if (i == 16) {
                i = 12;
            }
            AudioAttributes.Builder unused = this.mFwkBuilder.setUsage(i);
            return this;
        }

        public Builder setContentType(int i) {
            AudioAttributes.Builder unused = this.mFwkBuilder.setContentType(i);
            return this;
        }

        public Builder setFlags(int i) {
            AudioAttributes.Builder unused = this.mFwkBuilder.setFlags(i);
            return this;
        }

        public Builder setLegacyStreamType(int i) {
            AudioAttributes.Builder unused = this.mFwkBuilder.setLegacyStreamType(i);
            return this;
        }
    }
}
