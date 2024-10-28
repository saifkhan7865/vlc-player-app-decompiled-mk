package androidx.car.app;

import android.graphics.Rect;
import androidx.car.app.annotations.RequiresCarApi;

public interface SurfaceCallback {

    /* renamed from: androidx.car.app.SurfaceCallback$-CC  reason: invalid class name */
    public final /* synthetic */ class CC {
        @RequiresCarApi(5)
        public static void $default$onClick(SurfaceCallback _this, float f, float f2) {
        }

        @RequiresCarApi(2)
        public static void $default$onFling(SurfaceCallback _this, float f, float f2) {
        }

        @RequiresCarApi(2)
        public static void $default$onScale(SurfaceCallback _this, float f, float f2, float f3) {
        }

        @RequiresCarApi(2)
        public static void $default$onScroll(SurfaceCallback _this, float f, float f2) {
        }

        public static void $default$onStableAreaChanged(SurfaceCallback _this, Rect rect) {
        }

        public static void $default$onSurfaceAvailable(SurfaceCallback _this, SurfaceContainer surfaceContainer) {
        }

        public static void $default$onSurfaceDestroyed(SurfaceCallback _this, SurfaceContainer surfaceContainer) {
        }

        public static void $default$onVisibleAreaChanged(SurfaceCallback _this, Rect rect) {
        }
    }

    @RequiresCarApi(5)
    void onClick(float f, float f2);

    @RequiresCarApi(2)
    void onFling(float f, float f2);

    @RequiresCarApi(2)
    void onScale(float f, float f2, float f3);

    @RequiresCarApi(2)
    void onScroll(float f, float f2);

    void onStableAreaChanged(Rect rect);

    void onSurfaceAvailable(SurfaceContainer surfaceContainer);

    void onSurfaceDestroyed(SurfaceContainer surfaceContainer);

    void onVisibleAreaChanged(Rect rect);
}
