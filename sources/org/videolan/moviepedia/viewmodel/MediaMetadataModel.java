package org.videolan.moviepedia.viewmodel;

import android.content.Context;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.channels.ActorKt;
import kotlinx.coroutines.channels.SendChannel;
import org.videolan.moviepedia.database.models.MediaImage;
import org.videolan.moviepedia.database.models.MediaMetadata;
import org.videolan.moviepedia.database.models.MediaMetadataWithImages;
import org.videolan.moviepedia.database.models.PersonType;
import org.videolan.moviepedia.provider.MediaScrapingTvshowProvider;
import org.videolan.moviepedia.repository.MediaMetadataRepository;
import org.videolan.moviepedia.repository.MediaPersonRepository;

@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002:\u0001$B%\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tJ\u000e\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\n\u001a\u00020\u000bX\u0005¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0013\u001a\u00020\u0014¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018X\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u001a\u0010\u001bR\u0017\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00190\u001d¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001f¨\u0006%"}, d2 = {"Lorg/videolan/moviepedia/viewmodel/MediaMetadataModel;", "Landroidx/lifecycle/ViewModel;", "Lkotlinx/coroutines/CoroutineScope;", "context", "Landroid/content/Context;", "mlId", "", "moviepediaId", "", "(Landroid/content/Context;Ljava/lang/Long;Ljava/lang/String;)V", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "nextEpisode", "Landroidx/lifecycle/MutableLiveData;", "Lorg/videolan/moviepedia/database/models/MediaMetadataWithImages;", "getNextEpisode", "()Landroidx/lifecycle/MutableLiveData;", "provider", "Lorg/videolan/moviepedia/provider/MediaScrapingTvshowProvider;", "getProvider", "()Lorg/videolan/moviepedia/provider/MediaScrapingTvshowProvider;", "updateActor", "Lkotlinx/coroutines/channels/SendChannel;", "Lorg/videolan/moviepedia/viewmodel/MediaMetadataFull;", "getUpdateActor$annotations", "()V", "updateLiveData", "Landroidx/lifecycle/MediatorLiveData;", "getUpdateLiveData", "()Landroidx/lifecycle/MediatorLiveData;", "updateMetadataImage", "", "item", "Lorg/videolan/moviepedia/database/models/MediaImage;", "Factory", "moviepedia_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaMetadataModel.kt */
public final class MediaMetadataModel extends ViewModel implements CoroutineScope {
    private final /* synthetic */ CoroutineScope $$delegate_0;
    /* access modifiers changed from: private */
    public final Context context;
    private final MutableLiveData<MediaMetadataWithImages> nextEpisode;
    private final MediaScrapingTvshowProvider provider;
    /* access modifiers changed from: private */
    public final SendChannel<MediaMetadataFull> updateActor;
    private final MediatorLiveData<MediaMetadataFull> updateLiveData;

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    /* compiled from: MediaMetadataModel.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        static {
            /*
                org.videolan.moviepedia.database.models.MediaImageType[] r0 = org.videolan.moviepedia.database.models.MediaImageType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                org.videolan.moviepedia.database.models.MediaImageType r1 = org.videolan.moviepedia.database.models.MediaImageType.POSTER     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                org.videolan.moviepedia.database.models.MediaImageType r1 = org.videolan.moviepedia.database.models.MediaImageType.BACKDROP     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.videolan.moviepedia.viewmodel.MediaMetadataModel.WhenMappings.<clinit>():void");
        }
    }

    private static /* synthetic */ void getUpdateActor$annotations() {
    }

    public CoroutineContext getCoroutineContext() {
        return this.$$delegate_0.getCoroutineContext();
    }

    public MediaMetadataModel(Context context2, Long l, String str) {
        Intrinsics.checkNotNullParameter(context2, "context");
        this.context = context2;
        this.$$delegate_0 = CoroutineScopeKt.MainScope();
        MediatorLiveData<MediaMetadataFull> mediatorLiveData = new MediatorLiveData<>();
        this.updateLiveData = mediatorLiveData;
        this.nextEpisode = new MutableLiveData<>();
        this.provider = new MediaScrapingTvshowProvider(context2);
        this.updateActor = ActorKt.actor$default(this, (CoroutineContext) null, -1, (CoroutineStart) null, (Function1) null, new MediaMetadataModel$updateActor$1(this, (Continuation<? super MediaMetadataModel$updateActor$1>) null), 13, (Object) null);
        MediaMetadataFull mediaMetadataFull = new MediaMetadataFull();
        if (l != null) {
            mediatorLiveData.addSource(((MediaMetadataRepository) MediaMetadataRepository.Companion.getInstance(context2)).getMetadataLiveByML(l.longValue()), new MediaMetadataModel$sam$androidx_lifecycle_Observer$0(new MediaMetadataModel$1$1(mediaMetadataFull, this)));
        }
        if (str != null) {
            mediatorLiveData.addSource(((MediaMetadataRepository) MediaMetadataRepository.Companion.getInstance(context2)).getMetadataLive(str), new MediaMetadataModel$sam$androidx_lifecycle_Observer$0(new MediaMetadataModel$2$1(mediaMetadataFull, this, str)));
            mediatorLiveData.addSource(((MediaPersonRepository) MediaPersonRepository.Companion.getInstance(context2)).getPersonsByType(str, PersonType.ACTOR), new MediaMetadataModel$sam$androidx_lifecycle_Observer$0(new MediaMetadataModel$2$2(mediaMetadataFull, this)));
            mediatorLiveData.addSource(((MediaPersonRepository) MediaPersonRepository.Companion.getInstance(context2)).getPersonsByType(str, PersonType.WRITER), new MediaMetadataModel$sam$androidx_lifecycle_Observer$0(new MediaMetadataModel$2$3(mediaMetadataFull, this)));
            mediatorLiveData.addSource(((MediaPersonRepository) MediaPersonRepository.Companion.getInstance(context2)).getPersonsByType(str, PersonType.PRODUCER), new MediaMetadataModel$sam$androidx_lifecycle_Observer$0(new MediaMetadataModel$2$4(mediaMetadataFull, this)));
            mediatorLiveData.addSource(((MediaPersonRepository) MediaPersonRepository.Companion.getInstance(context2)).getPersonsByType(str, PersonType.MUSICIAN), new MediaMetadataModel$sam$androidx_lifecycle_Observer$0(new MediaMetadataModel$2$5(mediaMetadataFull, this)));
            mediatorLiveData.addSource(((MediaPersonRepository) MediaPersonRepository.Companion.getInstance(context2)).getPersonsByType(str, PersonType.DIRECTOR), new MediaMetadataModel$sam$androidx_lifecycle_Observer$0(new MediaMetadataModel$2$6(mediaMetadataFull, this)));
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ MediaMetadataModel(Context context2, Long l, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context2, (i & 2) != 0 ? null : l, (i & 4) != 0 ? null : str);
    }

    public final MediatorLiveData<MediaMetadataFull> getUpdateLiveData() {
        return this.updateLiveData;
    }

    public final MutableLiveData<MediaMetadataWithImages> getNextEpisode() {
        return this.nextEpisode;
    }

    public final MediaScrapingTvshowProvider getProvider() {
        return this.provider;
    }

    public final void updateMetadataImage(MediaImage mediaImage) {
        MediaMetadataWithImages metadata;
        MediaMetadata metadata2;
        Intrinsics.checkNotNullParameter(mediaImage, "item");
        MediaMetadataFull value = this.updateLiveData.getValue();
        if (value != null && (metadata = value.getMetadata()) != null && (metadata2 = metadata.getMetadata()) != null) {
            int i = WhenMappings.$EnumSwitchMapping$0[mediaImage.getImageType().ordinal()];
            if (i == 1) {
                metadata2.setCurrentPoster(mediaImage.getUrl());
            } else if (i == 2) {
                metadata2.setCurrentBackdrop(mediaImage.getUrl());
            }
            Job unused = BuildersKt__Builders_commonKt.launch$default(this, (CoroutineContext) null, (CoroutineStart) null, new MediaMetadataModel$updateMetadataImage$1(this, metadata2, (Continuation<? super MediaMetadataModel$updateMetadataImage$1>) null), 3, (Object) null);
        }
    }

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ%\u0010\n\u001a\u0002H\u000b\"\b\b\u0000\u0010\u000b*\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\u000b0\u000eH\u0016¢\u0006\u0002\u0010\u000fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0004¢\u0006\u0004\n\u0002\u0010\tR\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lorg/videolan/moviepedia/viewmodel/MediaMetadataModel$Factory;", "Landroidx/lifecycle/ViewModelProvider$NewInstanceFactory;", "context", "Landroid/content/Context;", "mlId", "", "showId", "", "(Landroid/content/Context;Ljava/lang/Long;Ljava/lang/String;)V", "Ljava/lang/Long;", "create", "T", "Landroidx/lifecycle/ViewModel;", "modelClass", "Ljava/lang/Class;", "(Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;", "moviepedia_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: MediaMetadataModel.kt */
    public static final class Factory extends ViewModelProvider.NewInstanceFactory {
        private final Context context;
        private final Long mlId;
        private final String showId;

        public Factory(Context context2, Long l, String str) {
            Intrinsics.checkNotNullParameter(context2, "context");
            this.context = context2;
            this.mlId = l;
            this.showId = str;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ Factory(Context context2, Long l, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(context2, (i & 2) != 0 ? null : l, (i & 4) != 0 ? null : str);
        }

        public <T extends ViewModel> T create(Class<T> cls) {
            Intrinsics.checkNotNullParameter(cls, "modelClass");
            Context applicationContext = this.context.getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
            return (ViewModel) new MediaMetadataModel(applicationContext, this.mlId, this.showId);
        }
    }
}
