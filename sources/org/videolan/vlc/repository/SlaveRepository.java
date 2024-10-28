package org.videolan.vlc.repository;

import android.content.Context;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import org.videolan.libvlc.interfaces.IMedia;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.resources.Constants;
import org.videolan.tools.IOScopedObject;
import org.videolan.tools.SingletonHolder;
import org.videolan.vlc.database.SlaveDao;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001c\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tH@¢\u0006\u0002\u0010\nJ&\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\tJ\u0016\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u00062\u0006\u0010\u0013\u001a\u00020\u0014R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lorg/videolan/vlc/repository/SlaveRepository;", "Lorg/videolan/tools/IOScopedObject;", "slaveDao", "Lorg/videolan/vlc/database/SlaveDao;", "(Lorg/videolan/vlc/database/SlaveDao;)V", "getSlaves", "", "Lorg/videolan/libvlc/interfaces/IMedia$Slave;", "mrl", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveSlave", "Lkotlinx/coroutines/Job;", "mediaPath", "type", "", "priority", "uriString", "saveSlaves", "mw", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "Companion", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: SlaveRepository.kt */
public final class SlaveRepository extends IOScopedObject {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public final SlaveDao slaveDao;

    public SlaveRepository(SlaveDao slaveDao2) {
        Intrinsics.checkNotNullParameter(slaveDao2, "slaveDao");
        this.slaveDao = slaveDao2;
    }

    public final Job saveSlave(String str, int i, int i2, String str2) {
        Intrinsics.checkNotNullParameter(str, "mediaPath");
        Intrinsics.checkNotNullParameter(str2, "uriString");
        return BuildersKt__Builders_commonKt.launch$default(this, (CoroutineContext) null, (CoroutineStart) null, new SlaveRepository$saveSlave$1(this, str, i, i2, str2, (Continuation<? super SlaveRepository$saveSlave$1>) null), 3, (Object) null);
    }

    public final List<Job> saveSlaves(MediaWrapper mediaWrapper) {
        Intrinsics.checkNotNullParameter(mediaWrapper, "mw");
        IMedia.Slave[] slaves = mediaWrapper.getSlaves();
        if (slaves == null) {
            return null;
        }
        Collection arrayList = new ArrayList(slaves.length);
        for (IMedia.Slave slave : slaves) {
            String location = mediaWrapper.getLocation();
            Intrinsics.checkNotNullExpressionValue(location, "getLocation(...)");
            int i = slave.type;
            int i2 = slave.priority;
            String str = slave.uri;
            Intrinsics.checkNotNullExpressionValue(str, Constants.KEY_URI);
            arrayList.add(saveSlave(location, i, i2, str));
        }
        return (List) arrayList;
    }

    public final Object getSlaves(String str, Continuation<? super List<? extends IMedia.Slave>> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new SlaveRepository$getSlaves$2(this, str, (Continuation<? super SlaveRepository$getSlaves$2>) null), continuation);
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lorg/videolan/vlc/repository/SlaveRepository$Companion;", "Lorg/videolan/tools/SingletonHolder;", "Lorg/videolan/vlc/repository/SlaveRepository;", "Landroid/content/Context;", "()V", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: SlaveRepository.kt */
    public static final class Companion extends SingletonHolder<SlaveRepository, Context> {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
            super(AnonymousClass1.INSTANCE);
        }
    }
}
