package org.videolan.vlc.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import org.videolan.vlc.R;

public class DialogDisplaySettingsBindingImpl extends DialogDisplaySettingsBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final NestedScrollView mboundView0;

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    public boolean setVariable(int i, Object obj) {
        return true;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.guideline16, 1);
        sparseIntArray.put(R.id.title, 2);
        sparseIntArray.put(R.id.display_mode_group, 3);
        sparseIntArray.put(R.id.display_in_list_image, 4);
        sparseIntArray.put(R.id.display_in_list_text, 5);
        sparseIntArray.put(R.id.show_all_artist_group, 6);
        sparseIntArray.put(R.id.all_artists_image, 7);
        sparseIntArray.put(R.id.show_all_artist_text, 8);
        sparseIntArray.put(R.id.show_all_artist_checkbox, 9);
        sparseIntArray.put(R.id.show_all_files_group, 10);
        sparseIntArray.put(R.id.all_files_image, 11);
        sparseIntArray.put(R.id.show_all_files_text, 12);
        sparseIntArray.put(R.id.show_all_files_checkbox, 13);
        sparseIntArray.put(R.id.show_hidden_files_group, 14);
        sparseIntArray.put(R.id.hidden_files_image, 15);
        sparseIntArray.put(R.id.show_hidden_files_text, 16);
        sparseIntArray.put(R.id.show_hidden_files_checkbox, 17);
        sparseIntArray.put(R.id.only_favs_group, 18);
        sparseIntArray.put(R.id.only_favs_image, 19);
        sparseIntArray.put(R.id.only_favs_text, 20);
        sparseIntArray.put(R.id.only_favs_checkbox, 21);
        sparseIntArray.put(R.id.video_groups_group, 22);
        sparseIntArray.put(R.id.video_group_image, 23);
        sparseIntArray.put(R.id.video_group_text, 24);
        sparseIntArray.put(R.id.video_group_spinner, 25);
        sparseIntArray.put(R.id.sorts_title, 26);
        sparseIntArray.put(R.id.sorts_container, 27);
    }

    public DialogDisplaySettingsBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 28, sIncludes, sViewsWithIds));
    }

    private DialogDisplaySettingsBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[7], objArr[11], objArr[4], objArr[5], objArr[3], objArr[1], objArr[15], objArr[21], objArr[18], objArr[19], objArr[20], objArr[9], objArr[6], objArr[8], objArr[13], objArr[10], objArr[12], objArr[17], objArr[14], objArr[16], objArr[27], objArr[26], objArr[2], objArr[23], objArr[25], objArr[24], objArr[22]);
        this.mDirtyFlags = -1;
        NestedScrollView nestedScrollView = objArr[0];
        this.mboundView0 = nestedScrollView;
        nestedScrollView.setTag((Object) null);
        setRootTag(view);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 1;
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

    /* access modifiers changed from: protected */
    public void executeBindings() {
        synchronized (this) {
            this.mDirtyFlags = 0;
        }
    }
}
