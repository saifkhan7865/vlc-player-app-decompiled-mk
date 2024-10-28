package org.videolan.vlc.util;

import android.content.Intent;
import android.net.Uri;
import androidx.core.content.FileProvider;
import androidx.fragment.app.FragmentActivity;
import com.google.android.material.snackbar.Snackbar;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.tools.KotlinExtensionsKt;
import org.videolan.vlc.R;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.util.KextensionsKt$share$2", f = "Kextensions.kt", i = {0, 0, 0, 0, 0}, l = {140}, m = "invokeSuspend", n = {"intentShareFile", "uris", "title", "hasVideo", "hasAudio"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4"})
/* compiled from: Kextensions.kt */
final class KextensionsKt$share$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ List<MediaWrapper> $medias;
    final /* synthetic */ FragmentActivity $this_share;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    KextensionsKt$share$2(List<? extends MediaWrapper> list, FragmentActivity fragmentActivity, Continuation<? super KextensionsKt$share$2> continuation) {
        super(2, continuation);
        this.$medias = list;
        this.$this_share = fragmentActivity;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new KextensionsKt$share$2(this.$medias, this.$this_share, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((KextensionsKt$share$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Intent intent;
        ArrayList arrayList;
        String str;
        Ref.BooleanRef booleanRef;
        Ref.BooleanRef booleanRef2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            intent = new Intent("android.intent.action.SEND_MULTIPLE");
            arrayList = new ArrayList();
            str = this.$medias.size() == 1 ? this.$medias.get(0).getTitle() : this.$this_share.getResources().getQuantityString(R.plurals.media_quantity, this.$medias.size(), new Object[]{Boxing.boxInt(this.$medias.size())});
            booleanRef = new Ref.BooleanRef();
            Ref.BooleanRef booleanRef3 = new Ref.BooleanRef();
            final List<MediaWrapper> list = this.$medias;
            final FragmentActivity fragmentActivity = this.$this_share;
            final Ref.BooleanRef booleanRef4 = booleanRef;
            final Ref.BooleanRef booleanRef5 = booleanRef3;
            final ArrayList arrayList2 = arrayList;
            this.L$0 = intent;
            this.L$1 = arrayList;
            this.L$2 = str;
            this.L$3 = booleanRef;
            this.L$4 = booleanRef3;
            this.label = 1;
            if (BuildersKt.withContext(Dispatchers.getIO(), new AnonymousClass1((Continuation<? super AnonymousClass1>) null), this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            booleanRef2 = booleanRef3;
        } else if (i == 1) {
            booleanRef2 = (Ref.BooleanRef) this.L$4;
            booleanRef = (Ref.BooleanRef) this.L$3;
            str = (String) this.L$2;
            arrayList = (ArrayList) this.L$1;
            intent = (Intent) this.L$0;
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        if (KotlinExtensionsKt.isStarted(this.$this_share)) {
            if (!arrayList.isEmpty()) {
                intent.setType((!booleanRef2.element || booleanRef.element) ? (!booleanRef.element || booleanRef2.element) ? "*/*" : "video/*" : "audio/*");
                intent.putParcelableArrayListExtra("android.intent.extra.STREAM", arrayList);
                intent.putExtra("android.intent.extra.SUBJECT", str);
                intent.putExtra("android.intent.extra.TEXT", this.$this_share.getString(R.string.share_message, new Object[]{str}));
                FragmentActivity fragmentActivity2 = this.$this_share;
                fragmentActivity2.startActivity(Intent.createChooser(intent, fragmentActivity2.getString(R.string.share_file, new Object[]{str})));
            } else {
                Snackbar.make(this.$this_share.findViewById(16908290), R.string.invalid_file, 0).show();
            }
        }
        return Unit.INSTANCE;
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "org.videolan.vlc.util.KextensionsKt$share$2$1", f = "Kextensions.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: org.videolan.vlc.util.KextensionsKt$share$2$1  reason: invalid class name */
    /* compiled from: Kextensions.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(list, booleanRef4, booleanRef5, arrayList2, fragmentActivity, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                Collection arrayList = new ArrayList();
                for (Object next : list) {
                    MediaWrapper mediaWrapper = (MediaWrapper) next;
                    if (mediaWrapper.getUri().getPath() != null) {
                        String path = mediaWrapper.getUri().getPath();
                        Intrinsics.checkNotNull(path);
                        if (new File(path).exists()) {
                            arrayList.add(next);
                        }
                    }
                }
                Ref.BooleanRef booleanRef = booleanRef4;
                Ref.BooleanRef booleanRef2 = booleanRef5;
                ArrayList<Uri> arrayList2 = arrayList2;
                FragmentActivity fragmentActivity = fragmentActivity;
                for (MediaWrapper mediaWrapper2 : (List) arrayList) {
                    String path2 = mediaWrapper2.getUri().getPath();
                    Intrinsics.checkNotNull(path2);
                    File file = new File(path2);
                    if (mediaWrapper2.getType() == 0) {
                        booleanRef.element = true;
                    } else {
                        booleanRef2.element = true;
                    }
                    arrayList2.add(FileProvider.getUriForFile(fragmentActivity, fragmentActivity.getPackageName() + ".provider", file));
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
