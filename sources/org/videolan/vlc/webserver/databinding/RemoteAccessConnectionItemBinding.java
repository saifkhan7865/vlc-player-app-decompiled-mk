package org.videolan.vlc.webserver.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import org.videolan.vlc.webserver.R;

public abstract class RemoteAccessConnectionItemBinding extends ViewDataBinding {
    public final ImageView connectionDelete;
    public final TextView connectionTitle;

    protected RemoteAccessConnectionItemBinding(Object obj, View view, int i, ImageView imageView, TextView textView) {
        super(obj, view, i);
        this.connectionDelete = imageView;
        this.connectionTitle = textView;
    }

    public static RemoteAccessConnectionItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static RemoteAccessConnectionItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (RemoteAccessConnectionItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.remote_access_connection_item, viewGroup, z, obj);
    }

    public static RemoteAccessConnectionItemBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static RemoteAccessConnectionItemBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (RemoteAccessConnectionItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.remote_access_connection_item, (ViewGroup) null, false, obj);
    }

    public static RemoteAccessConnectionItemBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static RemoteAccessConnectionItemBinding bind(View view, Object obj) {
        return (RemoteAccessConnectionItemBinding) bind(obj, view, R.layout.remote_access_connection_item);
    }
}
