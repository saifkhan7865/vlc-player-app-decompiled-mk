package org.videolan.vlc.gui.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Marker;
import org.videolan.vlc.R;

@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u000f\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B\u0017\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tB\u001f\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\b\u0010%\u001a\u00020&H\u0002J\"\u0010'\u001a\u00020&2\b\u0010(\u001a\u0004\u0018\u00010)2\u0006\u0010*\u001a\u00020\u000b2\u0006\u0010+\u001a\u00020$H\u0016J\u0012\u0010,\u001a\u00020&2\b\u0010(\u001a\u0004\u0018\u00010)H\u0016J\u000e\u0010-\u001a\u00020&2\u0006\u0010.\u001a\u00020\u001dJ\u0014\u0010/\u001a\u00020&2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u000b0\u001fJ\b\u00100\u001a\u00020&H\u0002R\u0016\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX.¢\u0006\u0004\n\u0002\u0010\u0010R\"\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX.¢\u0006\u0010\n\u0002\u0010\u0010\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001b\u0010\u0016\u001a\u00020\u00178FX\u0002¢\u0006\f\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u0018\u0010\u0019R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u000b0\u001f8BX\u0004¢\u0006\u0006\u001a\u0004\b \u0010!R\u0014\u0010\"\u001a\b\u0012\u0004\u0012\u00020$0#X\u000e¢\u0006\u0002\n\u0000¨\u00061"}, d2 = {"Lorg/videolan/vlc/gui/view/LanguageSelector;", "Landroidx/constraintlayout/widget/ConstraintLayout;", "Landroid/content/DialogInterface$OnDismissListener;", "Landroid/content/DialogInterface$OnMultiChoiceClickListener;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyle", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "allEntriesOfLanguages", "", "", "[Ljava/lang/String;", "allValuesOfLanguages", "getAllValuesOfLanguages", "()[Ljava/lang/String;", "setAllValuesOfLanguages", "([Ljava/lang/String;)V", "badge", "Landroid/widget/TextView;", "getBadge", "()Landroid/widget/TextView;", "badge$delegate", "Lkotlin/Lazy;", "listener", "Lorg/videolan/vlc/gui/view/OnItemSelectListener;", "selectedIndices", "", "getSelectedIndices", "()Ljava/util/List;", "selection", "", "", "initViews", "", "onClick", "dialog", "Landroid/content/DialogInterface;", "index", "isChecked", "onDismiss", "setOnItemsSelectListener", "onItemSelectListener", "setSelection", "updateBadge", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: LanguageSelector.kt */
public final class LanguageSelector extends ConstraintLayout implements DialogInterface.OnDismissListener, DialogInterface.OnMultiChoiceClickListener {
    private String[] allEntriesOfLanguages;
    public String[] allValuesOfLanguages;
    private final Lazy badge$delegate = LazyKt.lazy(new LanguageSelector$badge$2(this));
    private OnItemSelectListener listener;
    private List<Boolean> selection = new ArrayList();

    public final String[] getAllValuesOfLanguages() {
        String[] strArr = this.allValuesOfLanguages;
        if (strArr != null) {
            return strArr;
        }
        Intrinsics.throwUninitializedPropertyAccessException("allValuesOfLanguages");
        return null;
    }

    public final void setAllValuesOfLanguages(String[] strArr) {
        Intrinsics.checkNotNullParameter(strArr, "<set-?>");
        this.allValuesOfLanguages = strArr;
    }

    private final List<Integer> getSelectedIndices() {
        Iterable iterable = this.selection;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        int i = 0;
        for (Object next : iterable) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            Boolean bool = (Boolean) next;
            bool.booleanValue();
            arrayList.add(new Pair(Integer.valueOf(i), bool));
            i = i2;
        }
        Collection arrayList2 = new ArrayList();
        for (Object next2 : (List) arrayList) {
            if (((Boolean) ((Pair) next2).getSecond()).booleanValue()) {
                arrayList2.add(next2);
            }
        }
        Iterable<Pair> iterable2 = (List) arrayList2;
        Collection arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable2, 10));
        for (Pair first : iterable2) {
            arrayList3.add(Integer.valueOf(((Number) first.getFirst()).intValue()));
        }
        return (List) arrayList3;
    }

    public final TextView getBadge() {
        Object value = this.badge$delegate.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
        return (TextView) value;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public LanguageSelector(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        initViews();
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public LanguageSelector(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attributeSet, "attrs");
        initViews();
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public LanguageSelector(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attributeSet, "attrs");
        initViews();
    }

    private final void initViews() {
        String[] stringArray = getResources().getStringArray(R.array.language_values);
        Intrinsics.checkNotNullExpressionValue(stringArray, "getStringArray(...)");
        setAllValuesOfLanguages(stringArray);
        String[] stringArray2 = getResources().getStringArray(R.array.language_entries);
        Intrinsics.checkNotNullExpressionValue(stringArray2, "getStringArray(...)");
        this.allEntriesOfLanguages = stringArray2;
        LayoutInflater.from(getContext()).inflate(R.layout.language_spinner, this, true);
        List<Boolean> list = this.selection;
        String[] strArr = this.allEntriesOfLanguages;
        if (strArr == null) {
            Intrinsics.throwUninitializedPropertyAccessException("allEntriesOfLanguages");
            strArr = null;
        }
        Collection arrayList = new ArrayList(strArr.length);
        for (String str : strArr) {
            arrayList.add(false);
        }
        list.addAll((List) arrayList);
        setOnClickListener(new LanguageSelector$$ExternalSyntheticLambda1(this));
    }

    /* access modifiers changed from: private */
    public static final void initViews$lambda$5(LanguageSelector languageSelector, View view) {
        Intrinsics.checkNotNullParameter(languageSelector, "this$0");
        AlertDialog.Builder builder = new AlertDialog.Builder(languageSelector.getContext());
        builder.setOnDismissListener(languageSelector);
        String[] strArr = languageSelector.allEntriesOfLanguages;
        if (strArr == null) {
            Intrinsics.throwUninitializedPropertyAccessException("allEntriesOfLanguages");
            strArr = null;
        }
        builder.setMultiChoiceItems((CharSequence[]) strArr, CollectionsKt.toBooleanArray(languageSelector.selection), languageSelector).setPositiveButton(R.string.done, new LanguageSelector$$ExternalSyntheticLambda0()).show();
    }

    /* access modifiers changed from: private */
    public static final void initViews$lambda$5$lambda$4(DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(dialogInterface, "dialogInterface");
        dialogInterface.dismiss();
    }

    public final void setSelection(List<Integer> list) {
        Intrinsics.checkNotNullParameter(list, "selectedIndices");
        Iterable<Boolean> iterable = this.selection;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (Boolean booleanValue : iterable) {
            booleanValue.booleanValue();
            arrayList.add(false);
        }
        this.selection = CollectionsKt.toMutableList((List) arrayList);
        for (Number intValue : list) {
            int intValue2 = intValue.intValue();
            if (intValue2 >= 0 && intValue2 < this.selection.size()) {
                this.selection.set(intValue2, true);
            }
        }
        Context context = getContext();
        int i = R.string.talkback_language_selection;
        Collection arrayList2 = new ArrayList();
        for (Object next : this.selection) {
            if (((Boolean) next).booleanValue()) {
                arrayList2.add(next);
            }
        }
        setContentDescription(context.getString(i, new Object[]{String.valueOf(((List) arrayList2).size())}));
        updateBadge();
        OnItemSelectListener onItemSelectListener = this.listener;
        if (onItemSelectListener != null) {
            onItemSelectListener.onItemSelect(list);
        }
    }

    public void onDismiss(DialogInterface dialogInterface) {
        OnItemSelectListener onItemSelectListener = this.listener;
        if (onItemSelectListener != null) {
            onItemSelectListener.onItemSelect(getSelectedIndices());
        }
    }

    public void onClick(DialogInterface dialogInterface, int i, boolean z) {
        if (i < this.selection.size()) {
            this.selection.set(i, Boolean.valueOf(z));
            updateBadge();
            return;
        }
        throw new IllegalArgumentException("Argument 'index' is out of bounds.");
    }

    private final void updateBadge() {
        getBadge().setText(getSelectedIndices().isEmpty() ^ true ? String.valueOf(getSelectedIndices().size()) : Marker.ANY_NON_NULL_MARKER);
    }

    public final void setOnItemsSelectListener(OnItemSelectListener onItemSelectListener) {
        Intrinsics.checkNotNullParameter(onItemSelectListener, "onItemSelectListener");
        this.listener = onItemSelectListener;
    }
}
