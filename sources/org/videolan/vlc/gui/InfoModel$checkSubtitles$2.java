package org.videolan.vlc.gui;

import android.net.Uri;
import java.io.File;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import org.videolan.libvlc.util.Extensions;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.InfoModel$checkSubtitles$2", f = "InfoActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: InfoActivity.kt */
final class InfoModel$checkSubtitles$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ File $itemFile;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ InfoModel this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    InfoModel$checkSubtitles$2(File file, InfoModel infoModel, Continuation<? super InfoModel$checkSubtitles$2> continuation) {
        super(2, continuation);
        this.$itemFile = file;
        this.this$0 = infoModel;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        InfoModel$checkSubtitles$2 infoModel$checkSubtitles$2 = new InfoModel$checkSubtitles$2(this.$itemFile, this.this$0, continuation);
        infoModel$checkSubtitles$2.L$0 = obj;
        return infoModel$checkSubtitles$2;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((InfoModel$checkSubtitles$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        int i;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            String decode = Uri.decode(this.$itemFile.getName());
            String decode2 = Uri.decode(this.$itemFile.getParent());
            Intrinsics.checkNotNull(decode);
            Intrinsics.checkNotNull(decode);
            String substring = decode.substring(0, StringsKt.lastIndexOf$default((CharSequence) decode, '.', 0, false, 6, (Object) null));
            Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
            String[] strArr = {"/Subtitles", "/subtitles", "/Subs", "/subs"};
            File parentFile = this.$itemFile.getParentFile();
            String[] list = parentFile != null ? parentFile.list() : null;
            Ref.IntRef intRef = new Ref.IntRef();
            intRef.element = list != null ? list.length : 0;
            int i2 = 0;
            for (int i3 = 4; i2 < i3; i3 = 4) {
                String str = strArr[i2];
                File file = new File(decode2 + str);
                if (file.exists()) {
                    String[] list2 = file.list();
                    Ref.ObjectRef objectRef = new Ref.ObjectRef();
                    objectRef.element = new String[0];
                    if (list2 != null) {
                        i = list2.length;
                        objectRef.element = new String[(intRef.element + i)];
                        System.arraycopy(list2, 0, objectRef.element, 0, i);
                    } else {
                        i = 0;
                    }
                    if (list != null) {
                        System.arraycopy(list, 0, objectRef.element, i, intRef.element);
                    }
                    String[] strArr2 = (String[]) ArraysKt.filterNotNull((Object[]) objectRef.element).toArray(new String[0]);
                    intRef.element = strArr2.length;
                    list = strArr2;
                }
                i2++;
            }
            if (list != null) {
                int i4 = intRef.element;
                for (int i5 = 0; i5 < i4; i5++) {
                    String decode3 = Uri.decode(list[i5]);
                    Intrinsics.checkNotNullExpressionValue(decode3, "decode(...)");
                    int lastIndexOf$default = StringsKt.lastIndexOf$default((CharSequence) decode3, '.', 0, false, 6, (Object) null);
                    if (lastIndexOf$default > 0) {
                        String substring2 = decode3.substring(lastIndexOf$default);
                        Intrinsics.checkNotNullExpressionValue(substring2, "substring(...)");
                        if (!Extensions.SUBTITLES.contains(substring2)) {
                            continue;
                        } else if (!CoroutineScopeKt.isActive(coroutineScope)) {
                            return Unit.INSTANCE;
                        } else {
                            Intrinsics.checkNotNull(substring);
                            if (StringsKt.startsWith$default(decode3, substring, false, 2, (Object) null)) {
                                this.this$0.getHasSubs().postValue(Boxing.boxBoolean(true));
                                return Unit.INSTANCE;
                            }
                        }
                    }
                }
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
