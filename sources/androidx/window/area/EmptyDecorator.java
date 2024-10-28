package androidx.window.area;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÃ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0016¨\u0006\u0006"}, d2 = {"Landroidx/window/area/EmptyDecorator;", "Landroidx/window/area/WindowAreaControllerDecorator;", "()V", "decorate", "Landroidx/window/area/WindowAreaController;", "controller", "window_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: WindowAreaController.kt */
final class EmptyDecorator implements WindowAreaControllerDecorator {
    public static final EmptyDecorator INSTANCE = new EmptyDecorator();

    public WindowAreaController decorate(WindowAreaController windowAreaController) {
        Intrinsics.checkNotNullParameter(windowAreaController, "controller");
        return windowAreaController;
    }

    private EmptyDecorator() {
    }
}
