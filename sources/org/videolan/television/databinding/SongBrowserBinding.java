package org.videolan.television.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Group;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import org.videolan.television.R;
import org.videolan.television.ui.FocusableRecyclerView;
import org.videolan.vlc.gui.view.EmptyLoadingStateView;

public final class SongBrowserBinding implements ViewBinding {
    public final RecyclerView ariane;
    public final ConstraintLayout content;
    public final AppCompatImageButton displayButton;
    public final TextView displayDescription;
    public final EmptyLoadingStateView emptyLoading;
    public final AppCompatImageButton favoriteButton;
    public final TextView favoriteDescription;
    public final AppCompatImageButton headerButton;
    public final TextView headerDescription;
    public final RecyclerView headerList;
    public final FrameLayout headerListContainer;
    public final AppCompatImageButton imageButtonDisplay;
    public final AppCompatImageButton imageButtonFavorite;
    public final AppCompatImageButton imageButtonHeader;
    public final AppCompatImageButton imageButtonSettings;
    public final AppCompatImageButton imageButtonSort;
    public final FocusableRecyclerView list;
    private final ConstraintLayout rootView;
    public final AppCompatImageButton sortButton;
    public final TextView sortDescription;
    public final TextView title;
    public final Group toolbar;

    private SongBrowserBinding(ConstraintLayout constraintLayout, RecyclerView recyclerView, ConstraintLayout constraintLayout2, AppCompatImageButton appCompatImageButton, TextView textView, EmptyLoadingStateView emptyLoadingStateView, AppCompatImageButton appCompatImageButton2, TextView textView2, AppCompatImageButton appCompatImageButton3, TextView textView3, RecyclerView recyclerView2, FrameLayout frameLayout, AppCompatImageButton appCompatImageButton4, AppCompatImageButton appCompatImageButton5, AppCompatImageButton appCompatImageButton6, AppCompatImageButton appCompatImageButton7, AppCompatImageButton appCompatImageButton8, FocusableRecyclerView focusableRecyclerView, AppCompatImageButton appCompatImageButton9, TextView textView4, TextView textView5, Group group) {
        this.rootView = constraintLayout;
        this.ariane = recyclerView;
        this.content = constraintLayout2;
        this.displayButton = appCompatImageButton;
        this.displayDescription = textView;
        this.emptyLoading = emptyLoadingStateView;
        this.favoriteButton = appCompatImageButton2;
        this.favoriteDescription = textView2;
        this.headerButton = appCompatImageButton3;
        this.headerDescription = textView3;
        this.headerList = recyclerView2;
        this.headerListContainer = frameLayout;
        this.imageButtonDisplay = appCompatImageButton4;
        this.imageButtonFavorite = appCompatImageButton5;
        this.imageButtonHeader = appCompatImageButton6;
        this.imageButtonSettings = appCompatImageButton7;
        this.imageButtonSort = appCompatImageButton8;
        this.list = focusableRecyclerView;
        this.sortButton = appCompatImageButton9;
        this.sortDescription = textView4;
        this.title = textView5;
        this.toolbar = group;
    }

    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static SongBrowserBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, (ViewGroup) null, false);
    }

    public static SongBrowserBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.song_browser, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static SongBrowserBinding bind(View view) {
        View view2 = view;
        int i = R.id.ariane;
        RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view2, i);
        if (recyclerView != null) {
            ConstraintLayout constraintLayout = (ConstraintLayout) view2;
            i = R.id.displayButton;
            AppCompatImageButton appCompatImageButton = (AppCompatImageButton) ViewBindings.findChildViewById(view2, i);
            if (appCompatImageButton != null) {
                i = R.id.displayDescription;
                TextView textView = (TextView) ViewBindings.findChildViewById(view2, i);
                if (textView != null) {
                    i = R.id.empty_loading;
                    EmptyLoadingStateView emptyLoadingStateView = (EmptyLoadingStateView) ViewBindings.findChildViewById(view2, i);
                    if (emptyLoadingStateView != null) {
                        i = R.id.favoriteButton;
                        AppCompatImageButton appCompatImageButton2 = (AppCompatImageButton) ViewBindings.findChildViewById(view2, i);
                        if (appCompatImageButton2 != null) {
                            i = R.id.favoriteDescription;
                            TextView textView2 = (TextView) ViewBindings.findChildViewById(view2, i);
                            if (textView2 != null) {
                                i = R.id.headerButton;
                                AppCompatImageButton appCompatImageButton3 = (AppCompatImageButton) ViewBindings.findChildViewById(view2, i);
                                if (appCompatImageButton3 != null) {
                                    i = R.id.headerDescription;
                                    TextView textView3 = (TextView) ViewBindings.findChildViewById(view2, i);
                                    if (textView3 != null) {
                                        i = R.id.headerList;
                                        RecyclerView recyclerView2 = (RecyclerView) ViewBindings.findChildViewById(view2, i);
                                        if (recyclerView2 != null) {
                                            i = R.id.headerListContainer;
                                            FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view2, i);
                                            if (frameLayout != null) {
                                                i = R.id.imageButtonDisplay;
                                                AppCompatImageButton appCompatImageButton4 = (AppCompatImageButton) ViewBindings.findChildViewById(view2, i);
                                                if (appCompatImageButton4 != null) {
                                                    i = R.id.imageButtonFavorite;
                                                    AppCompatImageButton appCompatImageButton5 = (AppCompatImageButton) ViewBindings.findChildViewById(view2, i);
                                                    if (appCompatImageButton5 != null) {
                                                        i = R.id.imageButtonHeader;
                                                        AppCompatImageButton appCompatImageButton6 = (AppCompatImageButton) ViewBindings.findChildViewById(view2, i);
                                                        if (appCompatImageButton6 != null) {
                                                            i = R.id.imageButtonSettings;
                                                            AppCompatImageButton appCompatImageButton7 = (AppCompatImageButton) ViewBindings.findChildViewById(view2, i);
                                                            if (appCompatImageButton7 != null) {
                                                                i = R.id.imageButtonSort;
                                                                AppCompatImageButton appCompatImageButton8 = (AppCompatImageButton) ViewBindings.findChildViewById(view2, i);
                                                                if (appCompatImageButton8 != null) {
                                                                    i = R.id.list;
                                                                    FocusableRecyclerView focusableRecyclerView = (FocusableRecyclerView) ViewBindings.findChildViewById(view2, i);
                                                                    if (focusableRecyclerView != null) {
                                                                        i = R.id.sortButton;
                                                                        AppCompatImageButton appCompatImageButton9 = (AppCompatImageButton) ViewBindings.findChildViewById(view2, i);
                                                                        if (appCompatImageButton9 != null) {
                                                                            i = R.id.sortDescription;
                                                                            TextView textView4 = (TextView) ViewBindings.findChildViewById(view2, i);
                                                                            if (textView4 != null) {
                                                                                i = R.id.title;
                                                                                TextView textView5 = (TextView) ViewBindings.findChildViewById(view2, i);
                                                                                if (textView5 != null) {
                                                                                    i = R.id.toolbar;
                                                                                    Group group = (Group) ViewBindings.findChildViewById(view2, i);
                                                                                    if (group != null) {
                                                                                        return new SongBrowserBinding(constraintLayout, recyclerView, constraintLayout, appCompatImageButton, textView, emptyLoadingStateView, appCompatImageButton2, textView2, appCompatImageButton3, textView3, recyclerView2, frameLayout, appCompatImageButton4, appCompatImageButton5, appCompatImageButton6, appCompatImageButton7, appCompatImageButton8, focusableRecyclerView, appCompatImageButton9, textView4, textView5, group);
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
