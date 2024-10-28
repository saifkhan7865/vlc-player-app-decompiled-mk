package org.videolan.television.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.resources.util.HelpersKt;
import org.videolan.television.R;
import org.videolan.television.databinding.ActivityColorPickerBinding;
import org.videolan.television.databinding.ColorPickerItemBinding;
import org.videolan.television.ui.views.ColorPickerItem;
import org.videolan.vlc.gui.DiffUtilAdapter;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0002\u001e\u001fB\u0005¢\u0006\u0002\u0010\u0002J\u001c\u0010\t\u001a\u00020\n2\b\b\u0001\u0010\u000b\u001a\u00020\f2\b\b\u0001\u0010\r\u001a\u00020\fH\u0002J$\u0010\t\u001a\u00020\n2\b\b\u0001\u0010\u000b\u001a\u00020\f2\b\b\u0001\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\fH\u0002J2\u0010\u000f\u001a\u00020\f2\u0016\u0010\u0010\u001a\u0012\u0012\u0004\u0012\u00020\f0\u0011j\b\u0012\u0004\u0012\u00020\f`\u00122\u0006\u0010\u0013\u001a\u00020\f2\b\b\u0001\u0010\u0014\u001a\u00020\fH\u0002J.\u0010\u0015\u001a\u001e\u0012\u0004\u0012\u00020\f\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\f0\u0011j\b\u0012\u0004\u0012\u00020\f`\u00120\u00162\b\b\u0001\u0010\u0014\u001a\u00020\fH\u0002J\u0018\u0010\u0017\u001a\u00020\f2\u0006\u0010\u0018\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\fH\u0007J\u0012\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0014R\u001a\u0010\u0003\u001a\u00020\u0004X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006 "}, d2 = {"Lorg/videolan/television/ui/ColorPickerActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lorg/videolan/television/databinding/ActivityColorPickerBinding;", "getBinding$television_release", "()Lorg/videolan/television/databinding/ActivityColorPickerBinding;", "setBinding$television_release", "(Lorg/videolan/television/databinding/ActivityColorPickerBinding;)V", "colorHsvDistance", "", "color1", "", "color2", "hsvIndex", "findClosestVariant", "colors", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "closestColorIndex", "previousColor", "generateColorsAndSelection", "Lkotlin/Pair;", "getVariantColor", "color", "position", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "ColorAdapter", "ColorPickerViewHolder", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ColorPickerActivity.kt */
public final class ColorPickerActivity extends AppCompatActivity {
    public ActivityColorPickerBinding binding;

    public final ActivityColorPickerBinding getBinding$television_release() {
        ActivityColorPickerBinding activityColorPickerBinding = this.binding;
        if (activityColorPickerBinding != null) {
            return activityColorPickerBinding;
        }
        Intrinsics.throwUninitializedPropertyAccessException("binding");
        return null;
    }

    public final void setBinding$television_release(ActivityColorPickerBinding activityColorPickerBinding) {
        Intrinsics.checkNotNullParameter(activityColorPickerBinding, "<set-?>");
        this.binding = activityColorPickerBinding;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        CharSequence charSequence;
        String string;
        super.onCreate(bundle);
        setContentView(R.layout.activity_color_picker);
        Activity activity = this;
        ViewDataBinding contentView = DataBindingUtil.setContentView(activity, R.layout.activity_color_picker);
        Intrinsics.checkNotNullExpressionValue(contentView, "setContentView(...)");
        setBinding$television_release((ActivityColorPickerBinding) contentView);
        HelpersKt.applyOverscanMargin(activity);
        TextView textView = getBinding$television_release().colorPickerTitle;
        Bundle extras = getIntent().getExtras();
        if (extras == null || (string = extras.getString(ColorPickerActivityKt.COLOR_PICKER_TITLE)) == null) {
            charSequence = getString(R.string.subtitles_color);
        } else {
            charSequence = string;
        }
        textView.setText(charSequence);
        Bundle extras2 = getIntent().getExtras();
        int i = extras2 != null ? extras2.getInt(ColorPickerActivityKt.COLOR_PICKER_SELECTED_COLOR) : ViewCompat.MEASURED_STATE_MASK;
        getBinding$television_release().oldColor.setColor(i);
        getBinding$television_release().newColor.setColor(i);
        Pair<Integer, ArrayList<Integer>> generateColorsAndSelection = generateColorsAndSelection(i);
        int intValue = generateColorsAndSelection.getFirst().intValue();
        ArrayList second = generateColorsAndSelection.getSecond();
        int findClosestVariant = findClosestVariant(second, intValue, i);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.color_grid);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 20));
        recyclerView.addItemDecoration(new ColorPickerActivity$onCreate$1(this, second));
        ColorAdapter colorAdapter = new ColorAdapter(this, second, intValue, findClosestVariant, new ColorPickerActivity$onCreate$colorAdapter$1(this));
        recyclerView.setAdapter(colorAdapter);
        getBinding$television_release().colorPickerButtonOk.setOnClickListener(new ColorPickerActivity$$ExternalSyntheticLambda0(this, colorAdapter));
        getBinding$television_release().colorPickerButtonCancel.setOnClickListener(new ColorPickerActivity$$ExternalSyntheticLambda1(this));
    }

    /* access modifiers changed from: private */
    public static final void onCreate$lambda$1(ColorPickerActivity colorPickerActivity, ColorAdapter colorAdapter, View view) {
        Intrinsics.checkNotNullParameter(colorPickerActivity, "this$0");
        Intrinsics.checkNotNullParameter(colorAdapter, "$colorAdapter");
        Intent intent = new Intent("android.intent.action.PICK");
        intent.putExtra(ColorPickerActivityKt.COLOR_PICKER_SELECTED_COLOR, colorAdapter.getSelectedColor());
        Unit unit = Unit.INSTANCE;
        colorPickerActivity.setResult(-1, intent);
        colorPickerActivity.finish();
    }

    /* access modifiers changed from: private */
    public static final void onCreate$lambda$2(ColorPickerActivity colorPickerActivity, View view) {
        Intrinsics.checkNotNullParameter(colorPickerActivity, "this$0");
        colorPickerActivity.finish();
    }

    private final int findClosestVariant(ArrayList<Integer> arrayList, int i, int i2) {
        HashMap hashMap = new HashMap();
        for (int i3 = 0; i3 < 20; i3++) {
            Integer num = arrayList.get(i);
            Intrinsics.checkNotNullExpressionValue(num, "get(...)");
            int variantColor = getVariantColor(num.intValue(), i3);
            Map map = hashMap;
            map.put(Integer.valueOf(i3), new Pair(Float.valueOf(colorHsvDistance(i2, variantColor, 1)), Float.valueOf(colorHsvDistance(i2, variantColor, 2))));
        }
        int i4 = 10;
        float f = 2.0f;
        for (Map.Entry entry : hashMap.entrySet()) {
            float floatValue = ((Number) ((Pair) entry.getValue()).getFirst()).floatValue() + ((Number) ((Pair) entry.getValue()).getSecond()).floatValue();
            if (floatValue < f) {
                i4 = ((Number) entry.getKey()).intValue();
                f = floatValue;
            }
        }
        return i4;
    }

    private final Pair<Integer, ArrayList<Integer>> generateColorsAndSelection(int i) {
        float colorHsvDistance = colorHsvDistance(i, -7829368);
        ArrayList arrayList = new ArrayList(100);
        arrayList.add(-7829368);
        int size = arrayList.size() - 1;
        for (int i2 = 1; i2 < 100; i2++) {
            int HSVToColor = Color.HSVToColor(new float[]{((float) i2) * 3.6f, 1.0f, 1.0f});
            arrayList.add(Integer.valueOf(HSVToColor));
            float colorHsvDistance2 = colorHsvDistance(i, HSVToColor);
            if (colorHsvDistance2 < colorHsvDistance) {
                size = arrayList.size() - 1;
                colorHsvDistance = colorHsvDistance2;
            }
        }
        return new Pair<>(Integer.valueOf(size), arrayList);
    }

    private final float colorHsvDistance(int i, int i2, int i3) {
        if (i3 < 0 || i3 >= 3) {
            throw new IllegalStateException("hsvIndex must be between 0 and 2");
        }
        float[] fArr = new float[3];
        float[] fArr2 = new float[3];
        Color.colorToHSV(i, fArr);
        Color.colorToHSV(i2, fArr2);
        return Math.abs(fArr[i3] - fArr2[i3]);
    }

    private final float colorHsvDistance(int i, int i2) {
        float[] fArr = new float[3];
        float[] fArr2 = new float[3];
        Color.colorToHSV(i, fArr);
        Color.colorToHSV(i2, fArr2);
        return Math.abs(fArr[0] - fArr2[0]) + Math.abs(fArr[1] - fArr2[1]) + Math.abs(fArr[2] - fArr2[2]);
    }

    public final int getVariantColor(int i, int i2) {
        if (i == -7829368) {
            return Color.HSVToColor(new float[]{0.0f, 0.0f, 1.0f - (((float) i2) * 0.05f)});
        }
        float[] fArr = new float[3];
        Color.colorToHSV(i, fArr);
        if (i2 <= 9) {
            fArr[1] = ((float) i2) * 0.1f;
        } else {
            fArr[2] = 1.0f - (((float) (i2 - 9)) * 0.1f);
        }
        return Color.HSVToColor(fArr);
    }

    @Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010!\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0004\u0018\u00002\u0012\u0012\u0004\u0012\u00020\u0002\u0012\b\u0012\u00060\u0003R\u00020\u00040\u0001B7\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\u0002\u0012\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\u0002\u0010\fJ\u000e\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00020\u0011H\u0014J\b\u0010\u0012\u001a\u00020\u0002H\u0016J\b\u0010\u0013\u001a\u00020\u0002H\u0007J\u001c\u0010\u0014\u001a\u00020\u000b2\n\u0010\u0015\u001a\u00060\u0003R\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u0002H\u0016J*\u0010\u0014\u001a\u00020\u000b2\n\u0010\u0015\u001a\u00060\u0003R\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u00022\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018H\u0016J\u001c\u0010\u001a\u001a\u00060\u0003R\u00020\u00042\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0002H\u0016R\u001a\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000b0\nX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0002X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0002X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0002X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u000e¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lorg/videolan/television/ui/ColorPickerActivity$ColorAdapter;", "Lorg/videolan/vlc/gui/DiffUtilAdapter;", "", "Lorg/videolan/television/ui/ColorPickerActivity$ColorPickerViewHolder;", "Lorg/videolan/television/ui/ColorPickerActivity;", "colors", "", "selectedIndex", "selectedVariantIndex", "colorSelectionListener", "Lkotlin/Function1;", "", "(Lorg/videolan/television/ui/ColorPickerActivity;Ljava/util/List;IILkotlin/jvm/functions/Function1;)V", "currentFocusPosition", "waitingForFocusRestore", "", "createCB", "Lorg/videolan/vlc/gui/DiffUtilAdapter$DiffCallback;", "getItemCount", "getSelectedColor", "onBindViewHolder", "holder", "position", "payloads", "", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: ColorPickerActivity.kt */
    public final class ColorAdapter extends DiffUtilAdapter<Integer, ColorPickerViewHolder> {
        /* access modifiers changed from: private */
        public final Function1<Integer, Unit> colorSelectionListener;
        /* access modifiers changed from: private */
        public final List<Integer> colors;
        private int currentFocusPosition = -1;
        /* access modifiers changed from: private */
        public int selectedIndex;
        /* access modifiers changed from: private */
        public int selectedVariantIndex;
        final /* synthetic */ ColorPickerActivity this$0;
        /* access modifiers changed from: private */
        public boolean waitingForFocusRestore;

        public ColorAdapter(ColorPickerActivity colorPickerActivity, List<Integer> list, int i, int i2, Function1<? super Integer, Unit> function1) {
            Intrinsics.checkNotNullParameter(list, "colors");
            Intrinsics.checkNotNullParameter(function1, "colorSelectionListener");
            this.this$0 = colorPickerActivity;
            this.colors = list;
            this.selectedIndex = i;
            this.selectedVariantIndex = i2;
            this.colorSelectionListener = function1;
        }

        public void onBindViewHolder(ColorPickerViewHolder colorPickerViewHolder, int i) {
            Intrinsics.checkNotNullParameter(colorPickerViewHolder, "holder");
            int absoluteAdapterPosition = colorPickerViewHolder.getAbsoluteAdapterPosition();
            colorPickerViewHolder.getBinding().colorPicker.setColor((absoluteAdapterPosition < 0 || absoluteAdapterPosition >= this.colors.size()) ? this.this$0.getVariantColor(this.colors.get(this.selectedIndex).intValue(), absoluteAdapterPosition - this.colors.size()) : this.colors.get(absoluteAdapterPosition).intValue());
            boolean z = true;
            if (absoluteAdapterPosition < 0 || absoluteAdapterPosition >= this.colors.size() ? absoluteAdapterPosition - this.colors.size() != this.selectedVariantIndex : this.selectedIndex != absoluteAdapterPosition) {
                z = false;
            }
            colorPickerViewHolder.getBinding().colorPicker.setCurrentlySelected(z);
            colorPickerViewHolder.getBinding().colorPicker.setOnFocusChangeListener(new ColorPickerActivity$ColorAdapter$$ExternalSyntheticLambda0(this, absoluteAdapterPosition));
            if (absoluteAdapterPosition == this.currentFocusPosition) {
                colorPickerViewHolder.getBinding().colorPicker.requestFocus();
                this.waitingForFocusRestore = false;
            }
        }

        /* access modifiers changed from: private */
        public static final void onBindViewHolder$lambda$0(ColorAdapter colorAdapter, int i, View view, boolean z) {
            Intrinsics.checkNotNullParameter(colorAdapter, "this$0");
            if (!colorAdapter.waitingForFocusRestore && z) {
                colorAdapter.currentFocusPosition = i;
            }
        }

        public final int getSelectedColor() {
            return this.this$0.getVariantColor(this.colors.get(this.selectedIndex).intValue(), this.selectedVariantIndex);
        }

        public void onBindViewHolder(ColorPickerViewHolder colorPickerViewHolder, int i, List<Object> list) {
            Intrinsics.checkNotNullParameter(colorPickerViewHolder, "holder");
            Intrinsics.checkNotNullParameter(list, "payloads");
            if (list.isEmpty()) {
                onBindViewHolder(colorPickerViewHolder, i);
                return;
            }
            for (Object areEqual : list) {
                if (Intrinsics.areEqual(areEqual, (Object) 5)) {
                    colorPickerViewHolder.getBinding().colorPicker.setCurrentlySelected(this.selectedIndex == i || i - this.colors.size() == this.selectedVariantIndex);
                    if (i == this.currentFocusPosition) {
                        colorPickerViewHolder.getBinding().colorPicker.requestFocus();
                    }
                }
            }
        }

        public ColorPickerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            Intrinsics.checkNotNullParameter(viewGroup, "parent");
            ColorPickerActivity colorPickerActivity = this.this$0;
            ViewDataBinding inflate = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.color_picker_item, viewGroup, false);
            Intrinsics.checkNotNull(inflate, "null cannot be cast to non-null type org.videolan.television.databinding.ColorPickerItemBinding");
            return new ColorPickerViewHolder(colorPickerActivity, (ColorPickerItemBinding) inflate, new ColorPickerActivity$ColorAdapter$onCreateViewHolder$1(this));
        }

        public int getItemCount() {
            return this.colors.size() + 20;
        }

        /* access modifiers changed from: protected */
        public DiffUtilAdapter.DiffCallback<Integer> createCB() {
            return new ColorPickerActivity$ColorAdapter$createCB$1();
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0004\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0018\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0005¢\u0006\u0002\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR \u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lorg/videolan/television/ui/ColorPickerActivity$ColorPickerViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lorg/videolan/television/databinding/ColorPickerItemBinding;", "listener", "Lkotlin/Function2;", "", "Landroid/view/View;", "", "(Lorg/videolan/television/ui/ColorPickerActivity;Lorg/videolan/television/databinding/ColorPickerItemBinding;Lkotlin/jvm/functions/Function2;)V", "getBinding", "()Lorg/videolan/television/databinding/ColorPickerItemBinding;", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: ColorPickerActivity.kt */
    public final class ColorPickerViewHolder extends RecyclerView.ViewHolder {
        private final ColorPickerItemBinding binding;
        private final Function2<Integer, View, Unit> listener;
        final /* synthetic */ ColorPickerActivity this$0;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public ColorPickerViewHolder(ColorPickerActivity colorPickerActivity, ColorPickerItemBinding colorPickerItemBinding, Function2<? super Integer, ? super View, Unit> function2) {
            super(colorPickerItemBinding.getRoot());
            Intrinsics.checkNotNullParameter(colorPickerItemBinding, "binding");
            Intrinsics.checkNotNullParameter(function2, "listener");
            this.this$0 = colorPickerActivity;
            this.binding = colorPickerItemBinding;
            this.listener = function2;
            colorPickerItemBinding.colorPicker.setOnClickListener(new ColorPickerActivity$ColorPickerViewHolder$$ExternalSyntheticLambda0(this));
        }

        public final ColorPickerItemBinding getBinding() {
            return this.binding;
        }

        /* access modifiers changed from: private */
        public static final void _init_$lambda$0(ColorPickerViewHolder colorPickerViewHolder, View view) {
            Intrinsics.checkNotNullParameter(colorPickerViewHolder, "this$0");
            Function2<Integer, View, Unit> function2 = colorPickerViewHolder.listener;
            Integer valueOf = Integer.valueOf(colorPickerViewHolder.getLayoutPosition());
            ColorPickerItem colorPickerItem = colorPickerViewHolder.binding.colorPicker;
            Intrinsics.checkNotNullExpressionValue(colorPickerItem, "colorPicker");
            function2.invoke(valueOf, colorPickerItem);
        }
    }
}
