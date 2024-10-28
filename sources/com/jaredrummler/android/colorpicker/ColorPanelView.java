package com.jaredrummler.android.colorpicker;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.Toast;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.view.ViewCompat;
import java.util.Locale;

public class ColorPanelView extends View {
    private static final int DEFAULT_BORDER_COLOR = -9539986;
    private Paint alphaPaint;
    private Drawable alphaPattern;
    private int borderColor;
    private Paint borderPaint;
    private int borderWidthPx;
    private RectF centerRect;
    private int color;
    private Paint colorPaint;
    private Rect colorRect;
    private Rect drawingRect;
    private Paint originalPaint;
    private int shape;
    private boolean showOldColor;

    public ColorPanelView(Context context) {
        this(context, (AttributeSet) null);
    }

    public ColorPanelView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ColorPanelView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.centerRect = new RectF();
        this.borderColor = DEFAULT_BORDER_COLOR;
        this.color = ViewCompat.MEASURED_STATE_MASK;
        init(context, attributeSet);
    }

    public Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("instanceState", super.onSaveInstanceState());
        bundle.putInt(TypedValues.Custom.S_COLOR, this.color);
        return bundle;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            this.color = bundle.getInt(TypedValues.Custom.S_COLOR);
            parcelable = bundle.getParcelable("instanceState");
        }
        super.onRestoreInstanceState(parcelable);
    }

    private void init(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.ColorPanelView);
        this.shape = obtainStyledAttributes.getInt(R.styleable.ColorPanelView_cpv_colorShape, 1);
        boolean z = obtainStyledAttributes.getBoolean(R.styleable.ColorPanelView_cpv_showOldColor, false);
        this.showOldColor = z;
        if (!z || this.shape == 1) {
            this.borderColor = obtainStyledAttributes.getColor(R.styleable.ColorPanelView_cpv_borderColor, DEFAULT_BORDER_COLOR);
            obtainStyledAttributes.recycle();
            if (this.borderColor == DEFAULT_BORDER_COLOR) {
                TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes(new TypedValue().data, new int[]{16842808});
                this.borderColor = obtainStyledAttributes2.getColor(0, this.borderColor);
                obtainStyledAttributes2.recycle();
            }
            this.borderWidthPx = DrawingUtils.dpToPx(context, 1.0f);
            Paint paint = new Paint();
            this.borderPaint = paint;
            paint.setAntiAlias(true);
            Paint paint2 = new Paint();
            this.colorPaint = paint2;
            paint2.setAntiAlias(true);
            if (this.showOldColor) {
                this.originalPaint = new Paint();
            }
            if (this.shape == 1) {
                Bitmap bitmap = ((BitmapDrawable) context.getResources().getDrawable(R.drawable.cpv_alpha)).getBitmap();
                Paint paint3 = new Paint();
                this.alphaPaint = paint3;
                paint3.setAntiAlias(true);
                this.alphaPaint.setShader(new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT));
                return;
            }
            return;
        }
        throw new IllegalStateException("Color preview is only available in circle mode");
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        this.borderPaint.setColor(this.borderColor);
        this.colorPaint.setColor(this.color);
        int i = this.shape;
        if (i == 0) {
            if (this.borderWidthPx > 0) {
                canvas.drawRect(this.drawingRect, this.borderPaint);
            }
            Drawable drawable = this.alphaPattern;
            if (drawable != null) {
                drawable.draw(canvas);
            }
            canvas.drawRect(this.colorRect, this.colorPaint);
        } else if (i == 1) {
            int measuredWidth = getMeasuredWidth() / 2;
            if (this.borderWidthPx > 0) {
                canvas.drawCircle((float) (getMeasuredWidth() / 2), (float) (getMeasuredHeight() / 2), (float) measuredWidth, this.borderPaint);
            }
            if (Color.alpha(this.color) < 255) {
                canvas.drawCircle((float) (getMeasuredWidth() / 2), (float) (getMeasuredHeight() / 2), (float) (measuredWidth - this.borderWidthPx), this.alphaPaint);
            }
            if (this.showOldColor) {
                canvas.drawArc(this.centerRect, 90.0f, 180.0f, true, this.originalPaint);
                canvas.drawArc(this.centerRect, 270.0f, 180.0f, true, this.colorPaint);
                return;
            }
            canvas.drawCircle((float) (getMeasuredWidth() / 2), (float) (getMeasuredHeight() / 2), (float) (measuredWidth - this.borderWidthPx), this.colorPaint);
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int i3 = this.shape;
        if (i3 == 0) {
            setMeasuredDimension(View.MeasureSpec.getSize(i), View.MeasureSpec.getSize(i2));
        } else if (i3 == 1) {
            super.onMeasure(i, i);
            setMeasuredDimension(getMeasuredWidth(), getMeasuredWidth());
        } else {
            super.onMeasure(i, i2);
        }
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (this.shape == 0 || this.showOldColor) {
            Rect rect = new Rect();
            this.drawingRect = rect;
            rect.left = getPaddingLeft();
            this.drawingRect.right = i - getPaddingRight();
            this.drawingRect.top = getPaddingTop();
            this.drawingRect.bottom = i2 - getPaddingBottom();
            if (this.showOldColor) {
                setUpCenterRect();
            } else {
                setUpColorRect();
            }
        }
    }

    private void setUpCenterRect() {
        Rect rect = this.drawingRect;
        this.centerRect = new RectF((float) (rect.left + this.borderWidthPx), (float) (rect.top + this.borderWidthPx), (float) (rect.right - this.borderWidthPx), (float) (rect.bottom - this.borderWidthPx));
    }

    private void setUpColorRect() {
        Rect rect = this.drawingRect;
        this.colorRect = new Rect(rect.left + this.borderWidthPx, rect.top + this.borderWidthPx, rect.right - this.borderWidthPx, rect.bottom - this.borderWidthPx);
        AlphaPatternDrawable alphaPatternDrawable = new AlphaPatternDrawable(DrawingUtils.dpToPx(getContext(), 4.0f));
        this.alphaPattern = alphaPatternDrawable;
        alphaPatternDrawable.setBounds(Math.round((float) this.colorRect.left), Math.round((float) this.colorRect.top), Math.round((float) this.colorRect.right), Math.round((float) this.colorRect.bottom));
    }

    public int getColor() {
        return this.color;
    }

    public void setColor(int i) {
        this.color = i;
        invalidate();
    }

    public void setOriginalColor(int i) {
        Paint paint = this.originalPaint;
        if (paint != null) {
            paint.setColor(i);
        }
    }

    public int getBorderColor() {
        return this.borderColor;
    }

    public void setBorderColor(int i) {
        this.borderColor = i;
        invalidate();
    }

    public int getShape() {
        return this.shape;
    }

    public void setShape(int i) {
        this.shape = i;
        invalidate();
    }

    public void showHint() {
        int[] iArr = new int[2];
        Rect rect = new Rect();
        getLocationOnScreen(iArr);
        getWindowVisibleDisplayFrame(rect);
        Context context = getContext();
        int width = getWidth();
        int height = getHeight();
        int i = iArr[1] + (height / 2);
        int i2 = iArr[0] + (width / 2);
        if (ViewCompat.getLayoutDirection(this) == 0) {
            i2 = context.getResources().getDisplayMetrics().widthPixels - i2;
        }
        StringBuilder sb = new StringBuilder("#");
        if (Color.alpha(this.color) != 255) {
            sb.append(Integer.toHexString(this.color).toUpperCase(Locale.ENGLISH));
        } else {
            sb.append(String.format("%06X", new Object[]{Integer.valueOf(16777215 & this.color)}).toUpperCase(Locale.ENGLISH));
        }
        Toast makeText = Toast.makeText(context, sb.toString(), 0);
        if (i < rect.height()) {
            makeText.setGravity(8388661, i2, (iArr[1] + height) - rect.top);
        } else {
            makeText.setGravity(81, 0, height);
        }
        makeText.show();
    }
}
