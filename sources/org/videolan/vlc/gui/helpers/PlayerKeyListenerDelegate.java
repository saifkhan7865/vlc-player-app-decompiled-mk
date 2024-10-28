package org.videolan.vlc.gui.helpers;

import android.view.KeyEvent;
import androidx.core.app.NotificationCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lorg/videolan/vlc/gui/helpers/PlayerKeyListenerDelegate;", "", "keycodeListener", "Lorg/videolan/vlc/gui/helpers/KeycodeListener;", "(Lorg/videolan/vlc/gui/helpers/KeycodeListener;)V", "onKeyDown", "", "keyCode", "", "event", "Landroid/view/KeyEvent;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: PlayerKeyListenerDelegate.kt */
public final class PlayerKeyListenerDelegate {
    private final KeycodeListener keycodeListener;

    public PlayerKeyListenerDelegate(KeycodeListener keycodeListener2) {
        Intrinsics.checkNotNullParameter(keycodeListener2, "keycodeListener");
        this.keycodeListener = keycodeListener2;
    }

    public final boolean onKeyDown(int i, KeyEvent keyEvent) {
        Intrinsics.checkNotNullParameter(keyEvent, NotificationCompat.CATEGORY_EVENT);
        if (!this.keycodeListener.isReady()) {
            return false;
        }
        if (i == 30) {
            this.keycodeListener.bookmark();
        } else if (i != 33) {
            if (i != 47) {
                if (i != 62) {
                    if (i != 100) {
                        if (i == 69) {
                            this.keycodeListener.decreaseRate();
                        } else if (i != 70) {
                            if (i == 81) {
                                this.keycodeListener.increaseRate();
                            } else if (i != 82) {
                                if (i != 85) {
                                    if (i != 86) {
                                        if (i == 89) {
                                            this.keycodeListener.seek(-10000);
                                        } else if (i == 90) {
                                            this.keycodeListener.seek(10000);
                                        } else if (!(i == 126 || i == 127)) {
                                            if (i != 166) {
                                                if (i != 167) {
                                                    switch (i) {
                                                        case 42:
                                                            break;
                                                        case 43:
                                                            break;
                                                        case 44:
                                                            break;
                                                        default:
                                                            switch (i) {
                                                                case 102:
                                                                    this.keycodeListener.seek(-60000);
                                                                    break;
                                                                case 103:
                                                                    this.keycodeListener.seek(60000);
                                                                    break;
                                                                case 104:
                                                                    break;
                                                                case 105:
                                                                    break;
                                                                default:
                                                                    return false;
                                                            }
                                                    }
                                                }
                                                this.keycodeListener.previous();
                                            }
                                            this.keycodeListener.next();
                                        }
                                    }
                                }
                            }
                        } else if (keyEvent.isShiftPressed()) {
                            this.keycodeListener.increaseRate();
                        } else {
                            this.keycodeListener.resetRate();
                        }
                    }
                    this.keycodeListener.showAdvancedOptions();
                }
                this.keycodeListener.togglePlayPause();
            }
            this.keycodeListener.stop();
        } else if (keyEvent.isCtrlPressed()) {
            this.keycodeListener.showEqualizer();
        }
        return true;
    }
}
