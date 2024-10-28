package org.videolan.vlc.webserver.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.gridlayout.widget.GridLayout;
import androidx.recyclerview.widget.RecyclerView;
import org.videolan.vlc.webserver.R;

public abstract class RemoteAccessShareActivityBinding extends ViewDataBinding {
    public final RecyclerView connectionList;
    public final TextView connectionTitle;
    public final CoordinatorLayout coordinator;
    public final NestedScrollView licenses;
    public final GridLayout linksGrid;
    public final TextView linksTitle;
    public final ImageView remoteAccessQrCode;
    public final TextView serverStatus;
    public final Button statusButton;
    public final TextView statusTitle;

    protected RemoteAccessShareActivityBinding(Object obj, View view, int i, RecyclerView recyclerView, TextView textView, CoordinatorLayout coordinatorLayout, NestedScrollView nestedScrollView, GridLayout gridLayout, TextView textView2, ImageView imageView, TextView textView3, Button button, TextView textView4) {
        super(obj, view, i);
        this.connectionList = recyclerView;
        this.connectionTitle = textView;
        this.coordinator = coordinatorLayout;
        this.licenses = nestedScrollView;
        this.linksGrid = gridLayout;
        this.linksTitle = textView2;
        this.remoteAccessQrCode = imageView;
        this.serverStatus = textView3;
        this.statusButton = button;
        this.statusTitle = textView4;
    }

    public static RemoteAccessShareActivityBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static RemoteAccessShareActivityBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (RemoteAccessShareActivityBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.remote_access_share_activity, viewGroup, z, obj);
    }

    public static RemoteAccessShareActivityBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static RemoteAccessShareActivityBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (RemoteAccessShareActivityBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.remote_access_share_activity, (ViewGroup) null, false, obj);
    }

    public static RemoteAccessShareActivityBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static RemoteAccessShareActivityBinding bind(View view, Object obj) {
        return (RemoteAccessShareActivityBinding) bind(obj, view, R.layout.remote_access_share_activity);
    }
}
