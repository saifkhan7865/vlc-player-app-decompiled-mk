package androidx.leanback.widget;

import android.view.View;
import android.view.ViewGroup;
import androidx.leanback.widget.ObjectAdapter;
import androidx.leanback.widget.Presenter;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class ItemBridgeAdapter extends RecyclerView.Adapter implements FacetProviderAdapter {
    static final boolean DEBUG = false;
    static final String TAG = "ItemBridgeAdapter";
    private ObjectAdapter mAdapter;
    private AdapterListener mAdapterListener;
    private ObjectAdapter.DataObserver mDataObserver;
    FocusHighlightHandler mFocusHighlight;
    private PresenterSelector mPresenterSelector;
    private ArrayList<Presenter> mPresenters;
    Wrapper mWrapper;

    public static abstract class Wrapper {
        public abstract View createWrapper(View view);

        public abstract void wrap(View view, View view2);
    }

    /* access modifiers changed from: protected */
    public void onAddPresenter(Presenter presenter, int i) {
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow(ViewHolder viewHolder) {
    }

    /* access modifiers changed from: protected */
    public void onBind(ViewHolder viewHolder) {
    }

    /* access modifiers changed from: protected */
    public void onCreate(ViewHolder viewHolder) {
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow(ViewHolder viewHolder) {
    }

    /* access modifiers changed from: protected */
    public void onUnbind(ViewHolder viewHolder) {
    }

    public static class AdapterListener {
        public void onAddPresenter(Presenter presenter, int i) {
        }

        public void onAttachedToWindow(ViewHolder viewHolder) {
        }

        public void onBind(ViewHolder viewHolder) {
        }

        public void onCreate(ViewHolder viewHolder) {
        }

        public void onDetachedFromWindow(ViewHolder viewHolder) {
        }

        public void onUnbind(ViewHolder viewHolder) {
        }

        public void onBind(ViewHolder viewHolder, List list) {
            onBind(viewHolder);
        }
    }

    final class OnFocusChangeListener implements View.OnFocusChangeListener {
        View.OnFocusChangeListener mChainedListener;

        OnFocusChangeListener() {
        }

        public void onFocusChange(View view, boolean z) {
            if (ItemBridgeAdapter.this.mWrapper != null) {
                view = (View) view.getParent();
            }
            if (ItemBridgeAdapter.this.mFocusHighlight != null) {
                ItemBridgeAdapter.this.mFocusHighlight.onItemFocused(view, z);
            }
            View.OnFocusChangeListener onFocusChangeListener = this.mChainedListener;
            if (onFocusChangeListener != null) {
                onFocusChangeListener.onFocusChange(view, z);
            }
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements FacetProvider {
        Object mExtraObject;
        final OnFocusChangeListener mFocusChangeListener;
        final Presenter.ViewHolder mHolder;
        Object mItem;
        final Presenter mPresenter;

        public final Presenter getPresenter() {
            return this.mPresenter;
        }

        public final Presenter.ViewHolder getViewHolder() {
            return this.mHolder;
        }

        public final Object getItem() {
            return this.mItem;
        }

        public final Object getExtraObject() {
            return this.mExtraObject;
        }

        public void setExtraObject(Object obj) {
            this.mExtraObject = obj;
        }

        public Object getFacet(Class<?> cls) {
            return this.mHolder.getFacet(cls);
        }

        ViewHolder(Presenter presenter, View view, Presenter.ViewHolder viewHolder) {
            super(view);
            this.mFocusChangeListener = new OnFocusChangeListener();
            this.mPresenter = presenter;
            this.mHolder = viewHolder;
        }
    }

    public ItemBridgeAdapter(ObjectAdapter objectAdapter, PresenterSelector presenterSelector) {
        this.mPresenters = new ArrayList<>();
        this.mDataObserver = new ObjectAdapter.DataObserver() {
            public void onChanged() {
                ItemBridgeAdapter.this.notifyDataSetChanged();
            }

            public void onItemRangeChanged(int i, int i2) {
                ItemBridgeAdapter.this.notifyItemRangeChanged(i, i2);
            }

            public void onItemRangeChanged(int i, int i2, Object obj) {
                ItemBridgeAdapter.this.notifyItemRangeChanged(i, i2, obj);
            }

            public void onItemRangeInserted(int i, int i2) {
                ItemBridgeAdapter.this.notifyItemRangeInserted(i, i2);
            }

            public void onItemRangeRemoved(int i, int i2) {
                ItemBridgeAdapter.this.notifyItemRangeRemoved(i, i2);
            }

            public void onItemMoved(int i, int i2) {
                ItemBridgeAdapter.this.notifyItemMoved(i, i2);
            }
        };
        setAdapter(objectAdapter);
        this.mPresenterSelector = presenterSelector;
    }

    public ItemBridgeAdapter(ObjectAdapter objectAdapter) {
        this(objectAdapter, (PresenterSelector) null);
    }

    public ItemBridgeAdapter() {
        this.mPresenters = new ArrayList<>();
        this.mDataObserver = new ObjectAdapter.DataObserver() {
            public void onChanged() {
                ItemBridgeAdapter.this.notifyDataSetChanged();
            }

            public void onItemRangeChanged(int i, int i2) {
                ItemBridgeAdapter.this.notifyItemRangeChanged(i, i2);
            }

            public void onItemRangeChanged(int i, int i2, Object obj) {
                ItemBridgeAdapter.this.notifyItemRangeChanged(i, i2, obj);
            }

            public void onItemRangeInserted(int i, int i2) {
                ItemBridgeAdapter.this.notifyItemRangeInserted(i, i2);
            }

            public void onItemRangeRemoved(int i, int i2) {
                ItemBridgeAdapter.this.notifyItemRangeRemoved(i, i2);
            }

            public void onItemMoved(int i, int i2) {
                ItemBridgeAdapter.this.notifyItemMoved(i, i2);
            }
        };
    }

    public void setAdapter(ObjectAdapter objectAdapter) {
        ObjectAdapter objectAdapter2 = this.mAdapter;
        if (objectAdapter != objectAdapter2) {
            if (objectAdapter2 != null) {
                objectAdapter2.unregisterObserver(this.mDataObserver);
            }
            this.mAdapter = objectAdapter;
            if (objectAdapter == null) {
                notifyDataSetChanged();
                return;
            }
            objectAdapter.registerObserver(this.mDataObserver);
            if (hasStableIds() != this.mAdapter.hasStableIds()) {
                setHasStableIds(this.mAdapter.hasStableIds());
            }
            notifyDataSetChanged();
        }
    }

    public void setPresenter(PresenterSelector presenterSelector) {
        this.mPresenterSelector = presenterSelector;
        notifyDataSetChanged();
    }

    public void setWrapper(Wrapper wrapper) {
        this.mWrapper = wrapper;
    }

    public Wrapper getWrapper() {
        return this.mWrapper;
    }

    /* access modifiers changed from: package-private */
    public void setFocusHighlight(FocusHighlightHandler focusHighlightHandler) {
        this.mFocusHighlight = focusHighlightHandler;
    }

    public void clear() {
        setAdapter((ObjectAdapter) null);
    }

    public void setPresenterMapper(ArrayList<Presenter> arrayList) {
        this.mPresenters = arrayList;
    }

    public ArrayList<Presenter> getPresenterMapper() {
        return this.mPresenters;
    }

    public int getItemCount() {
        ObjectAdapter objectAdapter = this.mAdapter;
        if (objectAdapter != null) {
            return objectAdapter.size();
        }
        return 0;
    }

    public int getItemViewType(int i) {
        PresenterSelector presenterSelector = this.mPresenterSelector;
        if (presenterSelector == null) {
            presenterSelector = this.mAdapter.getPresenterSelector();
        }
        Presenter presenter = presenterSelector.getPresenter(this.mAdapter.get(i));
        int indexOf = this.mPresenters.indexOf(presenter);
        if (indexOf < 0) {
            this.mPresenters.add(presenter);
            indexOf = this.mPresenters.indexOf(presenter);
            onAddPresenter(presenter, indexOf);
            AdapterListener adapterListener = this.mAdapterListener;
            if (adapterListener != null) {
                adapterListener.onAddPresenter(presenter, indexOf);
            }
        }
        return indexOf;
    }

    public final RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Presenter.ViewHolder viewHolder;
        View view;
        Presenter presenter = this.mPresenters.get(i);
        Wrapper wrapper = this.mWrapper;
        if (wrapper != null) {
            view = wrapper.createWrapper(viewGroup);
            viewHolder = presenter.onCreateViewHolder(viewGroup);
            this.mWrapper.wrap(view, viewHolder.view);
        } else {
            viewHolder = presenter.onCreateViewHolder(viewGroup);
            view = viewHolder.view;
        }
        ViewHolder viewHolder2 = new ViewHolder(presenter, view, viewHolder);
        onCreate(viewHolder2);
        AdapterListener adapterListener = this.mAdapterListener;
        if (adapterListener != null) {
            adapterListener.onCreate(viewHolder2);
        }
        View view2 = viewHolder2.mHolder.view;
        if (view2 != null) {
            viewHolder2.mFocusChangeListener.mChainedListener = view2.getOnFocusChangeListener();
            view2.setOnFocusChangeListener(viewHolder2.mFocusChangeListener);
        }
        FocusHighlightHandler focusHighlightHandler = this.mFocusHighlight;
        if (focusHighlightHandler != null) {
            focusHighlightHandler.onInitializeView(view);
        }
        return viewHolder2;
    }

    public void setAdapterListener(AdapterListener adapterListener) {
        this.mAdapterListener = adapterListener;
    }

    public final void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        ViewHolder viewHolder2 = (ViewHolder) viewHolder;
        viewHolder2.mItem = this.mAdapter.get(i);
        viewHolder2.mPresenter.onBindViewHolder(viewHolder2.mHolder, viewHolder2.mItem);
        onBind(viewHolder2);
        AdapterListener adapterListener = this.mAdapterListener;
        if (adapterListener != null) {
            adapterListener.onBind(viewHolder2);
        }
    }

    public final void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i, List list) {
        ViewHolder viewHolder2 = (ViewHolder) viewHolder;
        viewHolder2.mItem = this.mAdapter.get(i);
        viewHolder2.mPresenter.onBindViewHolder(viewHolder2.mHolder, viewHolder2.mItem, list);
        onBind(viewHolder2);
        AdapterListener adapterListener = this.mAdapterListener;
        if (adapterListener != null) {
            adapterListener.onBind(viewHolder2, list);
        }
    }

    public final void onViewRecycled(RecyclerView.ViewHolder viewHolder) {
        ViewHolder viewHolder2 = (ViewHolder) viewHolder;
        viewHolder2.mPresenter.onUnbindViewHolder(viewHolder2.mHolder);
        onUnbind(viewHolder2);
        AdapterListener adapterListener = this.mAdapterListener;
        if (adapterListener != null) {
            adapterListener.onUnbind(viewHolder2);
        }
        viewHolder2.mItem = null;
    }

    public final boolean onFailedToRecycleView(RecyclerView.ViewHolder viewHolder) {
        onViewRecycled(viewHolder);
        return false;
    }

    public final void onViewAttachedToWindow(RecyclerView.ViewHolder viewHolder) {
        ViewHolder viewHolder2 = (ViewHolder) viewHolder;
        onAttachedToWindow(viewHolder2);
        AdapterListener adapterListener = this.mAdapterListener;
        if (adapterListener != null) {
            adapterListener.onAttachedToWindow(viewHolder2);
        }
        viewHolder2.mPresenter.onViewAttachedToWindow(viewHolder2.mHolder);
    }

    public final void onViewDetachedFromWindow(RecyclerView.ViewHolder viewHolder) {
        ViewHolder viewHolder2 = (ViewHolder) viewHolder;
        viewHolder2.mPresenter.onViewDetachedFromWindow(viewHolder2.mHolder);
        onDetachedFromWindow(viewHolder2);
        AdapterListener adapterListener = this.mAdapterListener;
        if (adapterListener != null) {
            adapterListener.onDetachedFromWindow(viewHolder2);
        }
    }

    public long getItemId(int i) {
        return this.mAdapter.getId(i);
    }

    public FacetProvider getFacetProvider(int i) {
        return this.mPresenters.get(i);
    }
}
