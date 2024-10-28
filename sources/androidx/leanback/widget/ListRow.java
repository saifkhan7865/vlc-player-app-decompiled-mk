package androidx.leanback.widget;

public class ListRow extends Row {
    private final ObjectAdapter mAdapter;
    private CharSequence mContentDescription;

    public final ObjectAdapter getAdapter() {
        return this.mAdapter;
    }

    public ListRow(HeaderItem headerItem, ObjectAdapter objectAdapter) {
        super(headerItem);
        this.mAdapter = objectAdapter;
        verify();
    }

    public ListRow(long j, HeaderItem headerItem, ObjectAdapter objectAdapter) {
        super(j, headerItem);
        this.mAdapter = objectAdapter;
        verify();
    }

    public ListRow(ObjectAdapter objectAdapter) {
        this.mAdapter = objectAdapter;
        verify();
    }

    private void verify() {
        if (this.mAdapter == null) {
            throw new IllegalArgumentException("ObjectAdapter cannot be null");
        }
    }

    public CharSequence getContentDescription() {
        CharSequence charSequence = this.mContentDescription;
        if (charSequence != null) {
            return charSequence;
        }
        HeaderItem headerItem = getHeaderItem();
        if (headerItem == null) {
            return null;
        }
        CharSequence contentDescription = headerItem.getContentDescription();
        if (contentDescription != null) {
            return contentDescription;
        }
        return headerItem.getName();
    }

    public void setContentDescription(CharSequence charSequence) {
        this.mContentDescription = charSequence;
    }
}
