package org.videolan.vlc.databinding;

import android.text.Spanned;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.InverseBindingListener;
import androidx.databinding.ObservableField;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import org.videolan.vlc.BR;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.dialogs.SubDownloadDialogState;
import org.videolan.vlc.viewmodels.SubtitlesModel;

public class SubtitleDownloaderDialogBindingImpl extends SubtitleDownloaderDialogBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private InverseBindingListener episodeandroidTextAttrChanged;
    private long mDirtyFlags;
    private InverseBindingListener nameandroidTextAttrChanged;
    private InverseBindingListener seasonandroidTextAttrChanged;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.constraintLayout, 15);
        sparseIntArray.put(R.id.movieName, 16);
        sparseIntArray.put(R.id.language_list_spinner, 17);
        sparseIntArray.put(R.id.sub_download_search, 18);
        sparseIntArray.put(R.id.sub_download_next, 19);
        sparseIntArray.put(R.id.sub_download_loading, 20);
    }

    public SubtitleDownloaderDialogBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 21, sIncludes, sViewsWithIds));
    }

    private SubtitleDownloaderDialogBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 6, objArr[13], objArr[15], objArr[12], objArr[11], objArr[17], objArr[4], objArr[16], objArr[8], objArr[7], objArr[2], objArr[5], objArr[0], objArr[14], objArr[10], objArr[9], objArr[1], objArr[20], objArr[19], objArr[18], objArr[3], objArr[6]);
        this.episodeandroidTextAttrChanged = new InverseBindingListener() {
            public void onChange() {
                ObservableField<String> observableSearchEpisode;
                String textString = TextViewBindingAdapter.getTextString(SubtitleDownloaderDialogBindingImpl.this.episode);
                SubtitlesModel subtitlesModel = SubtitleDownloaderDialogBindingImpl.this.mViewmodel;
                if (subtitlesModel != null && (observableSearchEpisode = subtitlesModel.getObservableSearchEpisode()) != null) {
                    observableSearchEpisode.set(textString);
                }
            }
        };
        this.nameandroidTextAttrChanged = new InverseBindingListener() {
            public void onChange() {
                ObservableField<String> observableSearchName;
                String textString = TextViewBindingAdapter.getTextString(SubtitleDownloaderDialogBindingImpl.this.name);
                SubtitlesModel subtitlesModel = SubtitleDownloaderDialogBindingImpl.this.mViewmodel;
                if (subtitlesModel != null && (observableSearchName = subtitlesModel.getObservableSearchName()) != null) {
                    observableSearchName.set(textString);
                }
            }
        };
        this.seasonandroidTextAttrChanged = new InverseBindingListener() {
            public void onChange() {
                ObservableField<String> observableSearchSeason;
                String textString = TextViewBindingAdapter.getTextString(SubtitleDownloaderDialogBindingImpl.this.season);
                SubtitlesModel subtitlesModel = SubtitleDownloaderDialogBindingImpl.this.mViewmodel;
                if (subtitlesModel != null && (observableSearchSeason = subtitlesModel.getObservableSearchSeason()) != null) {
                    observableSearchSeason.set(textString);
                }
            }
        };
        this.mDirtyFlags = -1;
        this.cancelButton.setTag((Object) null);
        this.episode.setTag((Object) null);
        this.episodeContainer.setTag((Object) null);
        this.message.setTag((Object) null);
        this.name.setTag((Object) null);
        this.nameContainer.setTag((Object) null);
        this.resultDescription.setTag((Object) null);
        this.retryButton.setTag((Object) null);
        this.scrollView.setTag("nested");
        this.searchButton.setTag((Object) null);
        this.season.setTag((Object) null);
        this.seasonContainer.setTag((Object) null);
        this.subDownloadHistory.setTag((Object) null);
        this.subsDownloadList.setTag((Object) null);
        this.subsHistoryList.setTag((Object) null);
        setRootTag(view);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 512;
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
        if (BR.state == i) {
            setState((SubDownloadDialogState) obj);
        } else if (BR.inError == i) {
            setInError((Boolean) obj);
        } else if (BR.viewmodel != i) {
            return false;
        } else {
            setViewmodel((SubtitlesModel) obj);
        }
        return true;
    }

    public void setState(SubDownloadDialogState subDownloadDialogState) {
        this.mState = subDownloadDialogState;
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        notifyPropertyChanged(BR.state);
        super.requestRebind();
    }

    public void setInError(Boolean bool) {
        this.mInError = bool;
    }

    public void setViewmodel(SubtitlesModel subtitlesModel) {
        this.mViewmodel = subtitlesModel;
        synchronized (this) {
            this.mDirtyFlags |= 256;
        }
        notifyPropertyChanged(BR.viewmodel);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i == 0) {
            return onChangeViewmodelObservableSearchEpisode((ObservableField) obj, i2);
        }
        if (i == 1) {
            return onChangeViewmodelObservableSearchSeason((ObservableField) obj, i2);
        }
        if (i == 2) {
            return onChangeViewmodelObservableMessage((ObservableField) obj, i2);
        }
        if (i == 3) {
            return onChangeViewmodelObservableResultDescription((ObservableField) obj, i2);
        }
        if (i == 4) {
            return onChangeViewmodelObservableSearchName((ObservableField) obj, i2);
        }
        if (i != 5) {
            return false;
        }
        return onChangeViewmodelObservableError((ObservableField) obj, i2);
    }

    private boolean onChangeViewmodelObservableSearchEpisode(ObservableField<String> observableField, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeViewmodelObservableSearchSeason(ObservableField<String> observableField, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeViewmodelObservableMessage(ObservableField<String> observableField, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeViewmodelObservableResultDescription(ObservableField<Spanned> observableField, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    private boolean onChangeViewmodelObservableSearchName(ObservableField<String> observableField, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        return true;
    }

    private boolean onChangeViewmodelObservableError(ObservableField<Boolean> observableField, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:100:0x0124  */
    /* JADX WARNING: Removed duplicated region for block: B:111:0x014b  */
    /* JADX WARNING: Removed duplicated region for block: B:114:0x0155  */
    /* JADX WARNING: Removed duplicated region for block: B:131:0x0196  */
    /* JADX WARNING: Removed duplicated region for block: B:133:0x01aa  */
    /* JADX WARNING: Removed duplicated region for block: B:136:0x01be  */
    /* JADX WARNING: Removed duplicated region for block: B:144:0x01e2  */
    /* JADX WARNING: Removed duplicated region for block: B:147:0x01ef  */
    /* JADX WARNING: Removed duplicated region for block: B:159:0x020b  */
    /* JADX WARNING: Removed duplicated region for block: B:162:0x0216  */
    /* JADX WARNING: Removed duplicated region for block: B:165:0x0255  */
    /* JADX WARNING: Removed duplicated region for block: B:168:0x0261  */
    /* JADX WARNING: Removed duplicated region for block: B:171:0x0288  */
    /* JADX WARNING: Removed duplicated region for block: B:174:0x0294  */
    /* JADX WARNING: Removed duplicated region for block: B:177:0x029f  */
    /* JADX WARNING: Removed duplicated region for block: B:180:0x02af  */
    /* JADX WARNING: Removed duplicated region for block: B:183:0x02ba  */
    /* JADX WARNING: Removed duplicated region for block: B:186:0x02c5  */
    /* JADX WARNING: Removed duplicated region for block: B:195:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x00a5  */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x00c9  */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x00e8  */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x00fc  */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x0104  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
            r43 = this;
            r1 = r43
            monitor-enter(r43)
            long r2 = r1.mDirtyFlags     // Catch:{ all -> 0x02cb }
            r4 = 0
            r1.mDirtyFlags = r4     // Catch:{ all -> 0x02cb }
            monitor-exit(r43)     // Catch:{ all -> 0x02cb }
            org.videolan.vlc.gui.dialogs.SubDownloadDialogState r0 = r1.mState
            org.videolan.vlc.viewmodels.SubtitlesModel r6 = r1.mViewmodel
            r7 = 836(0x344, double:4.13E-321)
            long r9 = r2 & r7
            r14 = 576(0x240, double:2.846E-321)
            int r18 = (r9 > r4 ? 1 : (r9 == r4 ? 0 : -1))
            if (r18 == 0) goto L_0x008b
            long r9 = r2 & r14
            int r18 = (r9 > r4 ? 1 : (r9 == r4 ? 0 : -1))
            if (r18 == 0) goto L_0x0055
            org.videolan.vlc.gui.dialogs.SubDownloadDialogState r13 = org.videolan.vlc.gui.dialogs.SubDownloadDialogState.Search
            if (r0 != r13) goto L_0x0024
            r13 = 1
            goto L_0x0025
        L_0x0024:
            r13 = 0
        L_0x0025:
            org.videolan.vlc.gui.dialogs.SubDownloadDialogState r11 = org.videolan.vlc.gui.dialogs.SubDownloadDialogState.History
            if (r0 != r11) goto L_0x002b
            r11 = 1
            goto L_0x002c
        L_0x002b:
            r11 = 0
        L_0x002c:
            int r12 = (r9 > r4 ? 1 : (r9 == r4 ? 0 : -1))
            if (r12 == 0) goto L_0x0038
            if (r13 == 0) goto L_0x0035
            r9 = 2048(0x800, double:1.0118E-320)
            goto L_0x0037
        L_0x0035:
            r9 = 1024(0x400, double:5.06E-321)
        L_0x0037:
            long r2 = r2 | r9
        L_0x0038:
            long r9 = r2 & r14
            int r12 = (r9 > r4 ? 1 : (r9 == r4 ? 0 : -1))
            if (r12 == 0) goto L_0x0048
            if (r11 == 0) goto L_0x0044
            r9 = 131072(0x20000, double:6.47582E-319)
            goto L_0x0047
        L_0x0044:
            r9 = 65536(0x10000, double:3.2379E-319)
        L_0x0047:
            long r2 = r2 | r9
        L_0x0048:
            if (r13 == 0) goto L_0x004c
            r9 = 0
            goto L_0x004e
        L_0x004c:
            r9 = 8
        L_0x004e:
            if (r11 == 0) goto L_0x0052
            r10 = 0
            goto L_0x0058
        L_0x0052:
            r10 = 8
            goto L_0x0058
        L_0x0055:
            r9 = 0
            r10 = 0
            r11 = 0
        L_0x0058:
            org.videolan.vlc.gui.dialogs.SubDownloadDialogState r12 = org.videolan.vlc.gui.dialogs.SubDownloadDialogState.Download
            if (r0 != r12) goto L_0x005e
            r0 = 1
            goto L_0x005f
        L_0x005e:
            r0 = 0
        L_0x005f:
            long r12 = r2 & r14
            int r21 = (r12 > r4 ? 1 : (r12 == r4 ? 0 : -1))
            if (r21 == 0) goto L_0x006f
            if (r0 == 0) goto L_0x006b
            r12 = 524288(0x80000, double:2.590327E-318)
            goto L_0x006e
        L_0x006b:
            r12 = 262144(0x40000, double:1.295163E-318)
        L_0x006e:
            long r2 = r2 | r12
        L_0x006f:
            long r12 = r2 & r7
            int r21 = (r12 > r4 ? 1 : (r12 == r4 ? 0 : -1))
            if (r21 == 0) goto L_0x007f
            if (r0 == 0) goto L_0x007b
            r12 = 2097152(0x200000, double:1.0361308E-317)
            goto L_0x007e
        L_0x007b:
            r12 = 1048576(0x100000, double:5.180654E-318)
        L_0x007e:
            long r2 = r2 | r12
        L_0x007f:
            long r12 = r2 & r14
            int r21 = (r12 > r4 ? 1 : (r12 == r4 ? 0 : -1))
            if (r21 == 0) goto L_0x008f
            if (r0 == 0) goto L_0x0088
            goto L_0x008f
        L_0x0088:
            r12 = 8
            goto L_0x0090
        L_0x008b:
            r0 = 0
            r9 = 0
            r10 = 0
            r11 = 0
        L_0x008f:
            r12 = 0
        L_0x0090:
            r21 = 831(0x33f, double:4.106E-321)
            long r21 = r2 & r21
            r23 = 800(0x320, double:3.953E-321)
            r25 = 784(0x310, double:3.873E-321)
            r27 = 776(0x308, double:3.834E-321)
            r29 = 772(0x304, double:3.814E-321)
            r13 = 2
            r31 = 770(0x302, double:3.804E-321)
            r33 = 769(0x301, double:3.8E-321)
            int r15 = (r21 > r4 ? 1 : (r21 == r4 ? 0 : -1))
            if (r15 == 0) goto L_0x01aa
            long r21 = r2 & r33
            int r15 = (r21 > r4 ? 1 : (r21 == r4 ? 0 : -1))
            if (r15 == 0) goto L_0x00c1
            if (r6 == 0) goto L_0x00b3
            androidx.databinding.ObservableField r15 = r6.getObservableSearchEpisode()
            r14 = 0
            goto L_0x00b5
        L_0x00b3:
            r14 = 0
            r15 = 0
        L_0x00b5:
            r1.updateRegistration((int) r14, (androidx.databinding.Observable) r15)
            if (r15 == 0) goto L_0x00c2
            java.lang.Object r15 = r15.get()
            java.lang.String r15 = (java.lang.String) r15
            goto L_0x00c3
        L_0x00c1:
            r14 = 0
        L_0x00c2:
            r15 = 0
        L_0x00c3:
            long r21 = r2 & r31
            int r35 = (r21 > r4 ? 1 : (r21 == r4 ? 0 : -1))
            if (r35 == 0) goto L_0x00e1
            if (r6 == 0) goto L_0x00d3
            androidx.databinding.ObservableField r21 = r6.getObservableSearchSeason()
            r14 = r21
            r7 = 1
            goto L_0x00d5
        L_0x00d3:
            r7 = 1
            r14 = 0
        L_0x00d5:
            r1.updateRegistration((int) r7, (androidx.databinding.Observable) r14)
            if (r14 == 0) goto L_0x00e1
            java.lang.Object r7 = r14.get()
            java.lang.String r7 = (java.lang.String) r7
            goto L_0x00e2
        L_0x00e1:
            r7 = 0
        L_0x00e2:
            long r36 = r2 & r29
            int r8 = (r36 > r4 ? 1 : (r36 == r4 ? 0 : -1))
            if (r8 == 0) goto L_0x00fc
            if (r6 == 0) goto L_0x00ef
            androidx.databinding.ObservableField r8 = r6.getObservableMessage()
            goto L_0x00f0
        L_0x00ef:
            r8 = 0
        L_0x00f0:
            r1.updateRegistration((int) r13, (androidx.databinding.Observable) r8)
            if (r8 == 0) goto L_0x00fd
            java.lang.Object r14 = r8.get()
            java.lang.String r14 = (java.lang.String) r14
            goto L_0x00fe
        L_0x00fc:
            r8 = 0
        L_0x00fd:
            r14 = 0
        L_0x00fe:
            long r36 = r2 & r27
            int r22 = (r36 > r4 ? 1 : (r36 == r4 ? 0 : -1))
            if (r22 == 0) goto L_0x011b
            if (r6 == 0) goto L_0x010d
            androidx.databinding.ObservableField r22 = r6.getObservableResultDescription()
            r13 = r22
            goto L_0x010e
        L_0x010d:
            r13 = 0
        L_0x010e:
            r4 = 3
            r1.updateRegistration((int) r4, (androidx.databinding.Observable) r13)
            if (r13 == 0) goto L_0x011b
            java.lang.Object r4 = r13.get()
            android.text.Spanned r4 = (android.text.Spanned) r4
            goto L_0x011c
        L_0x011b:
            r4 = 0
        L_0x011c:
            long r38 = r2 & r25
            r36 = 0
            int r5 = (r38 > r36 ? 1 : (r38 == r36 ? 0 : -1))
            if (r5 == 0) goto L_0x014b
            if (r6 == 0) goto L_0x012b
            androidx.databinding.ObservableField r5 = r6.getObservableSearchName()
            goto L_0x012c
        L_0x012b:
            r5 = 0
        L_0x012c:
            r13 = 4
            r1.updateRegistration((int) r13, (androidx.databinding.Observable) r5)
            if (r5 == 0) goto L_0x0139
            java.lang.Object r5 = r5.get()
            java.lang.String r5 = (java.lang.String) r5
            goto L_0x013a
        L_0x0139:
            r5 = 0
        L_0x013a:
            if (r5 == 0) goto L_0x0141
            java.lang.String r13 = r5.trim()
            goto L_0x0142
        L_0x0141:
            r13 = 0
        L_0x0142:
            boolean r13 = android.text.TextUtils.isEmpty(r13)
            r17 = 1
            r13 = r13 ^ 1
            goto L_0x014d
        L_0x014b:
            r5 = 0
            r13 = 0
        L_0x014d:
            long r38 = r2 & r23
            r36 = 0
            int r40 = (r38 > r36 ? 1 : (r38 == r36 ? 0 : -1))
            if (r40 == 0) goto L_0x0196
            if (r6 == 0) goto L_0x0164
            androidx.databinding.ObservableField r40 = r6.getObservableError()
            r41 = r5
            r42 = r40
            r40 = r4
            r4 = r42
            goto L_0x0169
        L_0x0164:
            r40 = r4
            r41 = r5
            r4 = 0
        L_0x0169:
            r5 = 5
            r1.updateRegistration((int) r5, (androidx.databinding.Observable) r4)
            if (r4 == 0) goto L_0x0176
            java.lang.Object r4 = r4.get()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            goto L_0x0177
        L_0x0176:
            r4 = 0
        L_0x0177:
            boolean r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r4)
            r36 = 0
            int r5 = (r38 > r36 ? 1 : (r38 == r36 ? 0 : -1))
            if (r5 == 0) goto L_0x018a
            if (r4 == 0) goto L_0x0186
            r38 = 8192(0x2000, double:4.0474E-320)
            goto L_0x0188
        L_0x0186:
            r38 = 4096(0x1000, double:2.0237E-320)
        L_0x0188:
            long r2 = r2 | r38
        L_0x018a:
            if (r4 == 0) goto L_0x018e
            r4 = 0
            goto L_0x0190
        L_0x018e:
            r4 = 8
        L_0x0190:
            r5 = r4
            r19 = r8
            r4 = r40
            goto L_0x019d
        L_0x0196:
            r40 = r4
            r41 = r5
            r19 = r8
            r5 = 0
        L_0x019d:
            r38 = 2097152(0x200000, double:1.0361308E-317)
            r8 = r7
            r7 = r41
            r42 = r14
            r14 = r13
            r13 = r15
            r15 = r42
            goto L_0x01b6
        L_0x01aa:
            r4 = 0
            r5 = 0
            r7 = 0
            r8 = 0
            r13 = 0
            r14 = 0
            r15 = 0
            r19 = 0
            r38 = 2097152(0x200000, double:1.0361308E-317)
        L_0x01b6:
            long r38 = r2 & r38
            r36 = 0
            int r20 = (r38 > r36 ? 1 : (r38 == r36 ? 0 : -1))
            if (r20 == 0) goto L_0x01e2
            if (r6 == 0) goto L_0x01c5
            androidx.databinding.ObservableField r6 = r6.getObservableMessage()
            goto L_0x01c7
        L_0x01c5:
            r6 = r19
        L_0x01c7:
            r19 = r15
            r15 = 2
            r1.updateRegistration((int) r15, (androidx.databinding.Observable) r6)
            if (r6 == 0) goto L_0x01d7
            java.lang.Object r6 = r6.get()
            r15 = r6
            java.lang.String r15 = (java.lang.String) r15
            goto L_0x01d9
        L_0x01d7:
            r15 = r19
        L_0x01d9:
            boolean r6 = android.text.TextUtils.isEmpty(r15)
            r17 = 1
            r6 = r6 ^ 1
            goto L_0x01e5
        L_0x01e2:
            r19 = r15
            r6 = 0
        L_0x01e5:
            r19 = 836(0x344, double:4.13E-321)
            long r38 = r2 & r19
            r19 = 0
            int r17 = (r38 > r19 ? 1 : (r38 == r19 ? 0 : -1))
            if (r17 == 0) goto L_0x020b
            if (r0 == 0) goto L_0x01f2
            goto L_0x01f3
        L_0x01f2:
            r6 = 0
        L_0x01f3:
            int r0 = (r38 > r19 ? 1 : (r38 == r19 ? 0 : -1))
            if (r0 == 0) goto L_0x0201
            if (r6 == 0) goto L_0x01fd
            r19 = 32768(0x8000, double:1.61895E-319)
            goto L_0x01ff
        L_0x01fd:
            r19 = 16384(0x4000, double:8.0948E-320)
        L_0x01ff:
            long r2 = r2 | r19
        L_0x0201:
            if (r6 == 0) goto L_0x0206
            r16 = 0
            goto L_0x0208
        L_0x0206:
            r16 = 8
        L_0x0208:
            r0 = r16
            goto L_0x020c
        L_0x020b:
            r0 = 0
        L_0x020c:
            r16 = 576(0x240, double:2.846E-321)
            long r16 = r2 & r16
            r19 = 0
            int r6 = (r16 > r19 ? 1 : (r16 == r19 ? 0 : -1))
            if (r6 == 0) goto L_0x024d
            android.widget.Button r6 = r1.cancelButton
            r6.setVisibility(r9)
            com.google.android.material.textfield.TextInputEditText r6 = r1.episode
            r6.setVisibility(r9)
            com.google.android.material.textfield.TextInputLayout r6 = r1.episodeContainer
            r6.setVisibility(r9)
            com.google.android.material.textfield.TextInputLayout r6 = r1.nameContainer
            r6.setVisibility(r9)
            android.widget.TextView r6 = r1.resultDescription
            r6.setVisibility(r12)
            android.widget.Button r6 = r1.searchButton
            r6.setVisibility(r9)
            com.google.android.material.textfield.TextInputEditText r6 = r1.season
            r6.setVisibility(r9)
            com.google.android.material.textfield.TextInputLayout r6 = r1.seasonContainer
            r6.setVisibility(r9)
            android.widget.ImageView r6 = r1.subDownloadHistory
            r6.setSelected(r11)
            androidx.recyclerview.widget.RecyclerView r6 = r1.subsDownloadList
            r6.setVisibility(r12)
            androidx.recyclerview.widget.RecyclerView r6 = r1.subsHistoryList
            r6.setVisibility(r10)
        L_0x024d:
            long r9 = r2 & r33
            r11 = 0
            int r6 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r6 == 0) goto L_0x025a
            com.google.android.material.textfield.TextInputEditText r6 = r1.episode
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r6, r13)
        L_0x025a:
            r9 = 512(0x200, double:2.53E-321)
            long r9 = r9 & r2
            int r6 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r6 == 0) goto L_0x0280
            com.google.android.material.textfield.TextInputEditText r6 = r1.episode
            r9 = 0
            r10 = r9
            androidx.databinding.adapters.TextViewBindingAdapter$BeforeTextChanged r10 = (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged) r10
            r10 = r9
            androidx.databinding.adapters.TextViewBindingAdapter$OnTextChanged r10 = (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged) r10
            r10 = r9
            androidx.databinding.adapters.TextViewBindingAdapter$AfterTextChanged r10 = (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged) r10
            androidx.databinding.InverseBindingListener r10 = r1.episodeandroidTextAttrChanged
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(r6, r9, r9, r9, r10)
            com.google.android.material.textfield.TextInputEditText r6 = r1.name
            androidx.databinding.InverseBindingListener r10 = r1.nameandroidTextAttrChanged
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(r6, r9, r9, r9, r10)
            com.google.android.material.textfield.TextInputEditText r6 = r1.season
            androidx.databinding.InverseBindingListener r10 = r1.seasonandroidTextAttrChanged
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(r6, r9, r9, r9, r10)
        L_0x0280:
            long r9 = r2 & r29
            r11 = 0
            int r6 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r6 == 0) goto L_0x028d
            android.widget.TextView r6 = r1.message
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r6, r15)
        L_0x028d:
            r9 = 836(0x344, double:4.13E-321)
            long r9 = r9 & r2
            int r6 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r6 == 0) goto L_0x0299
            android.widget.TextView r6 = r1.message
            r6.setVisibility(r0)
        L_0x0299:
            long r9 = r2 & r25
            int r0 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r0 == 0) goto L_0x02a9
            com.google.android.material.textfield.TextInputEditText r0 = r1.name
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r7)
            android.widget.Button r0 = r1.searchButton
            r0.setEnabled(r14)
        L_0x02a9:
            long r6 = r2 & r27
            int r0 = (r6 > r11 ? 1 : (r6 == r11 ? 0 : -1))
            if (r0 == 0) goto L_0x02b4
            android.widget.TextView r0 = r1.resultDescription
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r4)
        L_0x02b4:
            long r6 = r2 & r23
            int r0 = (r6 > r11 ? 1 : (r6 == r11 ? 0 : -1))
            if (r0 == 0) goto L_0x02bf
            android.widget.Button r0 = r1.retryButton
            r0.setVisibility(r5)
        L_0x02bf:
            long r2 = r2 & r31
            int r0 = (r2 > r11 ? 1 : (r2 == r11 ? 0 : -1))
            if (r0 == 0) goto L_0x02ca
            com.google.android.material.textfield.TextInputEditText r0 = r1.season
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r8)
        L_0x02ca:
            return
        L_0x02cb:
            r0 = move-exception
            monitor-exit(r43)     // Catch:{ all -> 0x02cb }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.databinding.SubtitleDownloaderDialogBindingImpl.executeBindings():void");
    }
}
