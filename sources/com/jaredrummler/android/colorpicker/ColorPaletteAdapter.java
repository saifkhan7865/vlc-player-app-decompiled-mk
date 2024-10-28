package com.jaredrummler.android.colorpicker;

import android.content.Context;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import androidx.core.graphics.ColorUtils;
import androidx.core.view.ViewCompat;

class ColorPaletteAdapter extends BaseAdapter {
    int colorShape;
    final int[] colors;
    final OnColorSelectedListener listener;
    int selectedPosition;

    interface OnColorSelectedListener {
        void onColorSelected(int i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    ColorPaletteAdapter(OnColorSelectedListener onColorSelectedListener, int[] iArr, int i, int i2) {
        this.listener = onColorSelectedListener;
        this.colors = iArr;
        this.selectedPosition = i;
        this.colorShape = i2;
    }

    public int getCount() {
        return this.colors.length;
    }

    public Object getItem(int i) {
        return Integer.valueOf(this.colors[i]);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        View view2;
        ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder(viewGroup.getContext());
            view2 = viewHolder.view;
        } else {
            view2 = view;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.setup(i);
        return view2;
    }

    /* access modifiers changed from: package-private */
    public void selectNone() {
        this.selectedPosition = -1;
        notifyDataSetChanged();
    }

    private final class ViewHolder {
        ColorPanelView colorPanelView;
        ImageView imageView;
        int originalBorderColor;
        View view;

        ViewHolder(Context context) {
            int i;
            if (ColorPaletteAdapter.this.colorShape == 0) {
                i = R.layout.cpv_color_item_square;
            } else {
                i = R.layout.cpv_color_item_circle;
            }
            View inflate = View.inflate(context, i, (ViewGroup) null);
            this.view = inflate;
            this.colorPanelView = (ColorPanelView) inflate.findViewById(R.id.cpv_color_panel_view);
            this.imageView = (ImageView) this.view.findViewById(R.id.cpv_color_image_view);
            this.originalBorderColor = this.colorPanelView.getBorderColor();
            this.view.setTag(this);
        }

        /* access modifiers changed from: package-private */
        public void setup(int i) {
            int i2 = ColorPaletteAdapter.this.colors[i];
            int alpha = Color.alpha(i2);
            this.colorPanelView.setColor(i2);
            this.imageView.setImageResource(ColorPaletteAdapter.this.selectedPosition == i ? R.drawable.cpv_preset_checked : 0);
            if (alpha == 255) {
                setColorFilter(i);
            } else if (alpha <= 165) {
                this.colorPanelView.setBorderColor(i2 | ViewCompat.MEASURED_STATE_MASK);
                this.imageView.setColorFilter(ViewCompat.MEASURED_STATE_MASK, PorterDuff.Mode.SRC_IN);
            } else {
                this.colorPanelView.setBorderColor(this.originalBorderColor);
                this.imageView.setColorFilter(-1, PorterDuff.Mode.SRC_IN);
            }
            setOnClickListener(i);
        }

        private void setOnClickListener(final int i) {
            this.colorPanelView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (ColorPaletteAdapter.this.selectedPosition != i) {
                        ColorPaletteAdapter.this.selectedPosition = i;
                        ColorPaletteAdapter.this.notifyDataSetChanged();
                    }
                    ColorPaletteAdapter.this.listener.onColorSelected(ColorPaletteAdapter.this.colors[i]);
                }
            });
            this.colorPanelView.setOnLongClickListener(new View.OnLongClickListener() {
                public boolean onLongClick(View view) {
                    ViewHolder.this.colorPanelView.showHint();
                    return true;
                }
            });
        }

        private void setColorFilter(int i) {
            if (i != ColorPaletteAdapter.this.selectedPosition || ColorUtils.calculateLuminance(ColorPaletteAdapter.this.colors[i]) < 0.65d) {
                this.imageView.setColorFilter((ColorFilter) null);
            } else {
                this.imageView.setColorFilter(ViewCompat.MEASURED_STATE_MASK, PorterDuff.Mode.SRC_IN);
            }
        }
    }
}
