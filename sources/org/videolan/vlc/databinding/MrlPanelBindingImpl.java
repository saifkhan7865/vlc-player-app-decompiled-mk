package org.videolan.vlc.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.EditText;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.InverseBindingListener;
import androidx.databinding.ObservableField;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import org.videolan.vlc.BR;
import org.videolan.vlc.R;
import org.videolan.vlc.viewmodels.StreamsModel;

public class MrlPanelBindingImpl extends MrlPanelBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final NestedScrollView mboundView0;
    /* access modifiers changed from: private */
    public final EditText mboundView1;
    private InverseBindingListener mboundView1androidTextAttrChanged;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.mrl_root, 2);
        sparseIntArray.put(R.id.mrlbar, 3);
        sparseIntArray.put(R.id.mrl_edit, 4);
        sparseIntArray.put(R.id.clipboard_indicator, 5);
        sparseIntArray.put(R.id.play, 6);
        sparseIntArray.put(R.id.mrl_list, 7);
    }

    public MrlPanelBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 8, sIncludes, sViewsWithIds));
    }

    private MrlPanelBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 1, objArr[5], objArr[4], objArr[7], objArr[2], objArr[3], objArr[6]);
        this.mboundView1androidTextAttrChanged = new InverseBindingListener() {
            public void onChange() {
                ObservableField<String> observableSearchText;
                String textString = TextViewBindingAdapter.getTextString(MrlPanelBindingImpl.this.mboundView1);
                StreamsModel streamsModel = MrlPanelBindingImpl.this.mViewmodel;
                if (streamsModel != null && (observableSearchText = streamsModel.getObservableSearchText()) != null) {
                    observableSearchText.set(textString);
                }
            }
        };
        this.mDirtyFlags = -1;
        NestedScrollView nestedScrollView = objArr[0];
        this.mboundView0 = nestedScrollView;
        nestedScrollView.setTag((Object) null);
        EditText editText = objArr[1];
        this.mboundView1 = editText;
        editText.setTag((Object) null);
        setRootTag(view);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 4;
        }
        requestRebind();
    }

    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
            return false;
        }
    }

    public boolean setVariable(int i, Object obj) {
        if (BR.viewmodel != i) {
            return false;
        }
        setViewmodel((StreamsModel) obj);
        return true;
    }

    public void setViewmodel(StreamsModel streamsModel) {
        this.mViewmodel = streamsModel;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(BR.viewmodel);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            return false;
        }
        return onChangeViewmodelObservableSearchText((ObservableField) obj, i2);
    }

    private boolean onChangeViewmodelObservableSearchText(ObservableField<String> observableField, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x002c  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0038  */
    /* JADX WARNING: Removed duplicated region for block: B:27:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
            r9 = this;
            monitor-enter(r9)
            long r0 = r9.mDirtyFlags     // Catch:{ all -> 0x0049 }
            r2 = 0
            r9.mDirtyFlags = r2     // Catch:{ all -> 0x0049 }
            monitor-exit(r9)     // Catch:{ all -> 0x0049 }
            org.videolan.vlc.viewmodels.StreamsModel r4 = r9.mViewmodel
            r5 = 7
            long r5 = r5 & r0
            r7 = 0
            int r8 = (r5 > r2 ? 1 : (r5 == r2 ? 0 : -1))
            if (r8 == 0) goto L_0x0027
            if (r4 == 0) goto L_0x0019
            androidx.databinding.ObservableField r4 = r4.getObservableSearchText()
            goto L_0x001a
        L_0x0019:
            r4 = r7
        L_0x001a:
            r8 = 0
            r9.updateRegistration((int) r8, (androidx.databinding.Observable) r4)
            if (r4 == 0) goto L_0x0027
            java.lang.Object r4 = r4.get()
            java.lang.String r4 = (java.lang.String) r4
            goto L_0x0028
        L_0x0027:
            r4 = r7
        L_0x0028:
            int r8 = (r5 > r2 ? 1 : (r5 == r2 ? 0 : -1))
            if (r8 == 0) goto L_0x0031
            android.widget.EditText r5 = r9.mboundView1
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r5, r4)
        L_0x0031:
            r4 = 4
            long r0 = r0 & r4
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 == 0) goto L_0x0048
            android.widget.EditText r0 = r9.mboundView1
            r1 = r7
            androidx.databinding.adapters.TextViewBindingAdapter$BeforeTextChanged r1 = (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged) r1
            r1 = r7
            androidx.databinding.adapters.TextViewBindingAdapter$OnTextChanged r1 = (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged) r1
            r1 = r7
            androidx.databinding.adapters.TextViewBindingAdapter$AfterTextChanged r1 = (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged) r1
            androidx.databinding.InverseBindingListener r1 = r9.mboundView1androidTextAttrChanged
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(r0, r7, r7, r7, r1)
        L_0x0048:
            return
        L_0x0049:
            r0 = move-exception
            monitor-exit(r9)     // Catch:{ all -> 0x0049 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.databinding.MrlPanelBindingImpl.executeBindings():void");
    }
}
