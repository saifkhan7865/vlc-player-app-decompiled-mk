package com.jaredrummler.android.colorpicker;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

class AlphaPatternDrawable extends Drawable {
    private Bitmap bitmap;
    private int numRectanglesHorizontal;
    private int numRectanglesVertical;
    private Paint paint = new Paint();
    private Paint paintGray = new Paint();
    private Paint paintWhite = new Paint();
    private int rectangleSize = 10;

    public int getOpacity() {
        return 0;
    }

    AlphaPatternDrawable(int i) {
        this.rectangleSize = i;
        this.paintWhite.setColor(-1);
        this.paintGray.setColor(-3421237);
    }

    public void draw(Canvas canvas) {
        Bitmap bitmap2 = this.bitmap;
        if (bitmap2 != null && !bitmap2.isRecycled()) {
            canvas.drawBitmap(this.bitmap, (Rect) null, getBounds(), this.paint);
        }
    }

    public void setAlpha(int i) {
        throw new UnsupportedOperationException("Alpha is not supported by this drawable.");
    }

    public void setColorFilter(ColorFilter colorFilter) {
        throw new UnsupportedOperationException("ColorFilter is not supported by this drawable.");
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        int height = rect.height();
        this.numRectanglesHorizontal = (int) Math.ceil((double) (rect.width() / this.rectangleSize));
        this.numRectanglesVertical = (int) Math.ceil((double) (height / this.rectangleSize));
        generatePatternBitmap();
    }

    private void generatePatternBitmap() {
        if (getBounds().width() > 0 && getBounds().height() > 0) {
            this.bitmap = Bitmap.createBitmap(getBounds().width(), getBounds().height(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(this.bitmap);
            Rect rect = new Rect();
            boolean z = true;
            for (int i = 0; i <= this.numRectanglesVertical; i++) {
                boolean z2 = z;
                for (int i2 = 0; i2 <= this.numRectanglesHorizontal; i2++) {
                    rect.top = this.rectangleSize * i;
                    rect.left = this.rectangleSize * i2;
                    rect.bottom = rect.top + this.rectangleSize;
                    rect.right = rect.left + this.rectangleSize;
                    canvas.drawRect(rect, z2 ? this.paintWhite : this.paintGray);
                    z2 = !z2;
                }
                z = !z;
            }
        }
    }
}
