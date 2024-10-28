package org.videolan.television.ui;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH&J\b\u0010\f\u001a\u00020\u000bH&J\u0012\u0010\r\u001a\u00020\t2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH&R\u0018\u0010\u0002\u001a\u00020\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007¨\u0006\u0010"}, d2 = {"Lorg/videolan/television/ui/TvItemAdapter;", "Lorg/videolan/television/ui/TvFocusableAdapter;", "focusNext", "", "getFocusNext", "()I", "setFocusNext", "(I)V", "displaySwitch", "", "inGrid", "", "isEmpty", "submitList", "pagedList", "", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: TvItemAdapter.kt */
public interface TvItemAdapter extends TvFocusableAdapter {
    void displaySwitch(boolean z);

    int getFocusNext();

    boolean isEmpty();

    void setFocusNext(int i);

    void submitList(Object obj);
}
