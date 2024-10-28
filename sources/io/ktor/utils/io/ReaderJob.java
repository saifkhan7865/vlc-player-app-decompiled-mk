package io.ktor.utils.io;

import androidx.leanback.preference.LeanbackPreferenceDialogFragment;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Job;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Lio/ktor/utils/io/ReaderJob;", "Lkotlinx/coroutines/Job;", "channel", "Lio/ktor/utils/io/ByteWriteChannel;", "getChannel", "()Lio/ktor/utils/io/ByteWriteChannel;", "ktor-io"}, k = 1, mv = {1, 8, 0}, xi = 48)
@Deprecated(message = "\n    We're migrating to the new kotlinx-io library.\n    This declaration is deprecated and will be removed in Ktor 4.0.0\n    If you have any problems with migration, please contact us in \n    https://youtrack.jetbrains.com/issue/KTOR-6030/Migrate-to-new-kotlinx.io-library\n    ")
/* compiled from: Coroutines.kt */
public interface ReaderJob extends Job {
    ByteWriteChannel getChannel();

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* compiled from: Coroutines.kt */
    public static final class DefaultImpls {
        public static <R> R fold(ReaderJob readerJob, R r, Function2<? super R, ? super CoroutineContext.Element, ? extends R> function2) {
            Intrinsics.checkNotNullParameter(function2, "operation");
            return Job.DefaultImpls.fold(readerJob, r, function2);
        }

        public static <E extends CoroutineContext.Element> E get(ReaderJob readerJob, CoroutineContext.Key<E> key) {
            Intrinsics.checkNotNullParameter(key, LeanbackPreferenceDialogFragment.ARG_KEY);
            return Job.DefaultImpls.get(readerJob, key);
        }

        public static CoroutineContext minusKey(ReaderJob readerJob, CoroutineContext.Key<?> key) {
            Intrinsics.checkNotNullParameter(key, LeanbackPreferenceDialogFragment.ARG_KEY);
            return Job.DefaultImpls.minusKey(readerJob, key);
        }

        public static CoroutineContext plus(ReaderJob readerJob, CoroutineContext coroutineContext) {
            Intrinsics.checkNotNullParameter(coroutineContext, "context");
            return Job.DefaultImpls.plus((Job) readerJob, coroutineContext);
        }

        @Deprecated(level = DeprecationLevel.ERROR, message = "Operator '+' on two Job objects is meaningless. Job is a coroutine context element and `+` is a set-sum operator for coroutine contexts. The job to the right of `+` just replaces the job the left of `+`.")
        public static Job plus(ReaderJob readerJob, Job job) {
            Intrinsics.checkNotNullParameter(job, "other");
            return Job.DefaultImpls.plus((Job) readerJob, job);
        }
    }
}
