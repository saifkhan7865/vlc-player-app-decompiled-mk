package org.videolan.vlc.webserver;

import android.content.Context;
import android.text.format.Formatter;
import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import java.io.File;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.TimeoutKt;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.media.Storage;
import org.videolan.tools.livedata.LiveDataset;
import org.videolan.vlc.R;
import org.videolan.vlc.util.BrowserutilsKt;
import org.videolan.vlc.util.KextensionsKt;

@Metadata(d1 = {"\u0000\n\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u0004\u0018\u0001H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u0002H@¨\u0006\u0003"}, d2 = {"<anonymous>", "T", "Lkotlinx/coroutines/CoroutineScope;", "org/videolan/resources/util/ExtensionsKt$observeLiveDataUntil$2"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.webserver.RemoteAccessRoutingKt$getProviderDescriptions$1$invokeSuspend$$inlined$observeLiveDataUntil$1", f = "RemoteAccessRouting.kt", i = {0}, l = {338}, m = "invokeSuspend", n = {"finalValue$iv"}, s = {"L$0"})
/* compiled from: Extensions.kt */
public final class RemoteAccessRoutingKt$getProviderDescriptions$1$invokeSuspend$$inlined$observeLiveDataUntil$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Pair<? extends Integer, ? extends String>>, Object> {
    final /* synthetic */ Context $context$inlined;
    final /* synthetic */ LiveData $data;
    final /* synthetic */ LiveDataset $dataset$inlined;
    final /* synthetic */ ArrayList $descriptions$inlined;
    final /* synthetic */ CoroutineScope $scope$inlined;
    final /* synthetic */ long $timeout;
    Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public RemoteAccessRoutingKt$getProviderDescriptions$1$invokeSuspend$$inlined$observeLiveDataUntil$1(long j, LiveData liveData, Continuation continuation, LiveDataset liveDataset, ArrayList arrayList, Context context, CoroutineScope coroutineScope) {
        super(2, continuation);
        this.$timeout = j;
        this.$data = liveData;
        this.$dataset$inlined = liveDataset;
        this.$descriptions$inlined = arrayList;
        this.$context$inlined = context;
        this.$scope$inlined = coroutineScope;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new RemoteAccessRoutingKt$getProviderDescriptions$1$invokeSuspend$$inlined$observeLiveDataUntil$1(this.$timeout, this.$data, continuation, this.$dataset$inlined, this.$descriptions$inlined, this.$context$inlined, this.$scope$inlined);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Pair<? extends Integer, ? extends String>> continuation) {
        return ((RemoteAccessRoutingKt$getProviderDescriptions$1$invokeSuspend$$inlined$observeLiveDataUntil$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u0003H@¨\u0006\u0005"}, d2 = {"<anonymous>", "", "T", "Lkotlinx/coroutines/CoroutineScope;", "org/videolan/resources/util/ExtensionsKt$suspendCoroutineWithTimeout$2", "org/videolan/resources/util/ExtensionsKt$observeLiveDataUntil$2$invokeSuspend$$inlined$suspendCoroutineWithTimeout$1"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "org.videolan.vlc.webserver.RemoteAccessRoutingKt$getProviderDescriptions$1$invokeSuspend$$inlined$observeLiveDataUntil$1$1", f = "RemoteAccessRouting.kt", i = {}, l = {337}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: org.videolan.vlc.webserver.RemoteAccessRoutingKt$getProviderDescriptions$1$invokeSuspend$$inlined$observeLiveDataUntil$1$1  reason: invalid class name */
    /* compiled from: Extensions.kt */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$0;
        Object L$1;
        int label;

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(objectRef3, continuation, liveData, booleanRef, liveDataset, arrayList, context, coroutineScope);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object obj2;
            Ref.ObjectRef objectRef;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Ref.ObjectRef objectRef2 = objectRef3;
                this.L$0 = objectRef2;
                this.label = 1;
                Continuation continuation = this;
                CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
                cancellableContinuationImpl.initCancellability();
                CancellableContinuation cancellableContinuation = cancellableContinuationImpl;
                final Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
                final Object value = liveData.getValue();
                final Ref.BooleanRef booleanRef = booleanRef;
                final LiveData liveData = liveData;
                final LiveDataset liveDataset = liveDataset;
                final ArrayList arrayList = arrayList;
                Context context = context;
                final CoroutineScope coroutineScope = coroutineScope;
                final CancellableContinuation cancellableContinuation2 = cancellableContinuation;
                Context context2 = context;
                final Ref.ObjectRef objectRef4 = objectRef3;
                Ref.ObjectRef objectRef5 = objectRef2;
                T t = r7;
                final Context context3 = context2;
                T r7 = new Observer() {
                    public final void onChanged(T t) {
                        String str;
                        if (!Intrinsics.areEqual(value, (Object) t) || !booleanRef.element) {
                            booleanRef.element = true;
                            Pair pair = (Pair) t;
                            try {
                                Object obj = liveDataset.getList().get(((Number) pair.getFirst()).intValue());
                                MediaWrapper mediaWrapper = obj instanceof MediaWrapper ? (MediaWrapper) obj : null;
                                if (mediaWrapper != null) {
                                    if (mediaWrapper.getType() != 3) {
                                        str = "";
                                        if (BrowserutilsKt.isSchemeFile(mediaWrapper.getUri().getScheme())) {
                                            String path = mediaWrapper.getUri().getPath();
                                            String formatFileSize = path != null ? Formatter.formatFileSize(context3, new File(path).length()) : null;
                                            if (formatFileSize != null) {
                                                str = formatFileSize;
                                            }
                                        }
                                    } else {
                                        String str2 = (String) pair.getSecond();
                                        int folderNumber = KextensionsKt.getFolderNumber(str2);
                                        int filesNumber = KextensionsKt.getFilesNumber(str2);
                                        str = context3.getResources().getQuantityString(R.plurals.subfolders_quantity, folderNumber, new Object[]{Integer.valueOf(folderNumber)}) + " · " + context3.getResources().getQuantityString(R.plurals.mediafiles_quantity, filesNumber, new Object[]{Integer.valueOf(filesNumber)});
                                    }
                                    if (str.length() > 0) {
                                        Job unused = BuildersKt__Builders_commonKt.launch$default(coroutineScope, Dispatchers.getIO(), (CoroutineStart) null, new RemoteAccessRoutingKt$getProviderDescriptions$1$1$1$1(mediaWrapper, str, (Continuation<? super RemoteAccessRoutingKt$getProviderDescriptions$1$1$1$1>) null), 2, (Object) null);
                                    }
                                }
                                Object obj2 = liveDataset.getList().get(((Number) pair.getFirst()).intValue());
                                Storage storage = obj2 instanceof Storage ? (Storage) obj2 : null;
                                if (storage != null) {
                                    String str3 = (String) pair.getSecond();
                                    int folderNumber2 = KextensionsKt.getFolderNumber(str3);
                                    int filesNumber2 = KextensionsKt.getFilesNumber(str3);
                                    String str4 = context3.getResources().getQuantityString(R.plurals.subfolders_quantity, folderNumber2, new Object[]{Integer.valueOf(folderNumber2)}) + " · " + context3.getResources().getQuantityString(R.plurals.mediafiles_quantity, filesNumber2, new Object[]{Integer.valueOf(filesNumber2)});
                                    if (str4.length() > 0) {
                                        Job unused2 = BuildersKt__Builders_commonKt.launch$default(coroutineScope, Dispatchers.getIO(), (CoroutineStart) null, new RemoteAccessRoutingKt$getProviderDescriptions$1$1$2$1(storage, str4, (Continuation<? super RemoteAccessRoutingKt$getProviderDescriptions$1$1$2$1>) null), 2, (Object) null);
                                    }
                                }
                            } catch (Exception e) {
                                Log.e("RemoteAccess", e.getMessage(), e);
                            }
                            arrayList.add(pair);
                            if (arrayList.size() >= liveDataset.getList().size() && !cancellableContinuation2.isCancelled()) {
                                Result.Companion companion = Result.Companion;
                                cancellableContinuation2.resumeWith(Result.m1774constructorimpl(t));
                                Observer observer = (Observer) objectRef4.element;
                                if (observer != null) {
                                    liveData.removeObserver(observer);
                                }
                            }
                        }
                    }
                };
                objectRef3.element = t;
                liveData.observeForever((Observer) objectRef3.element);
                final LiveData liveData2 = liveData;
                cancellableContinuation.invokeOnCancellation(new Function1<Throwable, Unit>() {
                    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                        invoke((Throwable) obj);
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Throwable th) {
                        liveData2.removeObserver((Observer) objectRef3.element);
                    }
                });
                obj2 = cancellableContinuationImpl.getResult();
                if (obj2 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                    DebugProbesKt.probeCoroutineSuspended(continuation);
                }
                if (obj2 == coroutine_suspended) {
                    return coroutine_suspended;
                }
                objectRef = objectRef5;
            } else if (i == 1) {
                objectRef = (Ref.ObjectRef) this.L$0;
                ResultKt.throwOnFailure(obj);
                obj2 = obj;
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            objectRef.element = obj2;
            return Unit.INSTANCE;
        }
    }

    public final Object invokeSuspend(Object obj) {
        Ref.ObjectRef objectRef;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
            long j = this.$timeout;
            final LiveData liveData = this.$data;
            Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
            final LiveDataset liveDataset = this.$dataset$inlined;
            final ArrayList arrayList = this.$descriptions$inlined;
            final Context context = this.$context$inlined;
            final CoroutineScope coroutineScope = this.$scope$inlined;
            final Ref.ObjectRef objectRef3 = objectRef2;
            this.L$0 = objectRef2;
            this.label = 1;
            if (TimeoutKt.withTimeoutOrNull(j, new AnonymousClass1((Continuation) null), this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            objectRef = objectRef2;
        } else if (i == 1) {
            objectRef = (Ref.ObjectRef) this.L$0;
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return objectRef.element;
    }
}
