package androidx.leanback.widget;

import android.util.Pair;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import androidx.leanback.widget.GuidedActionAdapter;
import androidx.leanback.widget.GuidedActionsStylist;
import java.util.ArrayList;

public class GuidedActionAdapterGroup {
    private static final boolean DEBUG_EDIT = false;
    private static final String TAG_EDIT = "EditableAction";
    ArrayList<Pair<GuidedActionAdapter, GuidedActionAdapter>> mAdapters = new ArrayList<>();
    private GuidedActionAdapter.EditListener mEditListener;
    private boolean mImeOpened;

    public void addAdpter(GuidedActionAdapter guidedActionAdapter, GuidedActionAdapter guidedActionAdapter2) {
        this.mAdapters.add(new Pair(guidedActionAdapter, guidedActionAdapter2));
        if (guidedActionAdapter != null) {
            guidedActionAdapter.mGroup = this;
        }
        if (guidedActionAdapter2 != null) {
            guidedActionAdapter2.mGroup = this;
        }
    }

    public GuidedActionAdapter getNextAdapter(GuidedActionAdapter guidedActionAdapter) {
        for (int i = 0; i < this.mAdapters.size(); i++) {
            Pair pair = this.mAdapters.get(i);
            if (pair.first == guidedActionAdapter) {
                return (GuidedActionAdapter) pair.second;
            }
        }
        return null;
    }

    public void setEditListener(GuidedActionAdapter.EditListener editListener) {
        this.mEditListener = editListener;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0029 A[LOOP:1: B:13:0x0029->B:16:0x0037, LOOP_START, PHI: r10 
      PHI: (r10v5 int) = (r10v1 int), (r10v6 int) binds: [B:8:0x0018, B:16:0x0037] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x004c  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0065 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0066  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x001a A[LOOP:0: B:9:0x001a->B:12:0x0026, LOOP_START, PHI: r10 
      PHI: (r10v7 int) = (r10v1 int), (r10v8 int) binds: [B:8:0x0018, B:12:0x0026] A[DONT_GENERATE, DONT_INLINE]] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean focusToNextAction(androidx.leanback.widget.GuidedActionAdapter r9, androidx.leanback.widget.GuidedAction r10, long r11) {
        /*
            r8 = this;
            r0 = 1
            r1 = 0
            r2 = -2
            int r4 = (r11 > r2 ? 1 : (r11 == r2 ? 0 : -1))
            if (r4 != 0) goto L_0x0011
            int r10 = r9.indexOf(r10)
            if (r10 >= 0) goto L_0x000f
            return r1
        L_0x000f:
            int r10 = r10 + r0
            goto L_0x0012
        L_0x0011:
            r10 = 0
        L_0x0012:
            int r4 = r9.getCount()
            int r5 = (r11 > r2 ? 1 : (r11 == r2 ? 0 : -1))
            if (r5 != 0) goto L_0x0029
        L_0x001a:
            if (r10 >= r4) goto L_0x003a
            androidx.leanback.widget.GuidedAction r5 = r9.getItem(r10)
            boolean r5 = r5.isFocusable()
            if (r5 != 0) goto L_0x003a
            int r10 = r10 + 1
            goto L_0x001a
        L_0x0029:
            if (r10 >= r4) goto L_0x003a
            androidx.leanback.widget.GuidedAction r5 = r9.getItem(r10)
            long r5 = r5.getId()
            int r7 = (r5 > r11 ? 1 : (r5 == r11 ? 0 : -1))
            if (r7 == 0) goto L_0x003a
            int r10 = r10 + 1
            goto L_0x0029
        L_0x003a:
            if (r10 >= r4) goto L_0x0066
            androidx.leanback.widget.GuidedActionsStylist r11 = r9.getGuidedActionsStylist()
            androidx.leanback.widget.VerticalGridView r11 = r11.getActionsGridView()
            androidx.recyclerview.widget.RecyclerView$ViewHolder r10 = r11.findViewHolderForPosition(r10)
            androidx.leanback.widget.GuidedActionsStylist$ViewHolder r10 = (androidx.leanback.widget.GuidedActionsStylist.ViewHolder) r10
            if (r10 == 0) goto L_0x0065
            androidx.leanback.widget.GuidedAction r11 = r10.getAction()
            boolean r11 = r11.hasTextEditable()
            if (r11 == 0) goto L_0x005a
            r8.openIme(r9, r10)
            goto L_0x0064
        L_0x005a:
            android.view.View r9 = r10.itemView
            r8.closeIme(r9)
            android.view.View r9 = r10.itemView
            r9.requestFocus()
        L_0x0064:
            return r0
        L_0x0065:
            return r1
        L_0x0066:
            androidx.leanback.widget.GuidedActionAdapter r9 = r8.getNextAdapter(r9)
            if (r9 != 0) goto L_0x0011
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.leanback.widget.GuidedActionAdapterGroup.focusToNextAction(androidx.leanback.widget.GuidedActionAdapter, androidx.leanback.widget.GuidedAction, long):boolean");
    }

    public void openIme(GuidedActionAdapter guidedActionAdapter, GuidedActionsStylist.ViewHolder viewHolder) {
        guidedActionAdapter.getGuidedActionsStylist().setEditingMode(viewHolder, true);
        View editingView = viewHolder.getEditingView();
        if (editingView != null && viewHolder.isInEditingText()) {
            editingView.setFocusable(true);
            editingView.requestFocus();
            ((InputMethodManager) editingView.getContext().getSystemService("input_method")).showSoftInput(editingView, 0);
            if (!this.mImeOpened) {
                this.mImeOpened = true;
                this.mEditListener.onImeOpen();
            }
        }
    }

    public void closeIme(View view) {
        if (this.mImeOpened) {
            this.mImeOpened = false;
            ((InputMethodManager) view.getContext().getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
            this.mEditListener.onImeClose();
        }
    }

    public void fillAndStay(GuidedActionAdapter guidedActionAdapter, TextView textView) {
        GuidedActionsStylist.ViewHolder findSubChildViewHolder = guidedActionAdapter.findSubChildViewHolder(textView);
        updateTextIntoAction(findSubChildViewHolder, textView);
        this.mEditListener.onGuidedActionEditCanceled(findSubChildViewHolder.getAction());
        guidedActionAdapter.getGuidedActionsStylist().setEditingMode(findSubChildViewHolder, false);
        closeIme(textView);
        findSubChildViewHolder.itemView.requestFocus();
    }

    public void fillAndGoNext(GuidedActionAdapter guidedActionAdapter, TextView textView) {
        GuidedActionsStylist.ViewHolder findSubChildViewHolder = guidedActionAdapter.findSubChildViewHolder(textView);
        updateTextIntoAction(findSubChildViewHolder, textView);
        guidedActionAdapter.performOnActionClick(findSubChildViewHolder);
        long onGuidedActionEditedAndProceed = this.mEditListener.onGuidedActionEditedAndProceed(findSubChildViewHolder.getAction());
        guidedActionAdapter.getGuidedActionsStylist().setEditingMode(findSubChildViewHolder, false);
        if (onGuidedActionEditedAndProceed == -3 || onGuidedActionEditedAndProceed == findSubChildViewHolder.getAction().getId() || !focusToNextAction(guidedActionAdapter, findSubChildViewHolder.getAction(), onGuidedActionEditedAndProceed)) {
            closeIme(textView);
            findSubChildViewHolder.itemView.requestFocus();
        }
    }

    private void updateTextIntoAction(GuidedActionsStylist.ViewHolder viewHolder, TextView textView) {
        GuidedAction action = viewHolder.getAction();
        if (textView == viewHolder.getDescriptionView()) {
            if (action.getEditDescription() != null) {
                action.setEditDescription(textView.getText());
            } else {
                action.setDescription(textView.getText());
            }
        } else if (textView != viewHolder.getTitleView()) {
        } else {
            if (action.getEditTitle() != null) {
                action.setEditTitle(textView.getText());
            } else {
                action.setTitle(textView.getText());
            }
        }
    }
}
