package androidx.room;

import java.util.List;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class QueryInterceptorDatabase$$ExternalSyntheticLambda8 implements Runnable {
    public final /* synthetic */ QueryInterceptorDatabase f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ List f$2;

    public /* synthetic */ QueryInterceptorDatabase$$ExternalSyntheticLambda8(QueryInterceptorDatabase queryInterceptorDatabase, String str, List list) {
        this.f$0 = queryInterceptorDatabase;
        this.f$1 = str;
        this.f$2 = list;
    }

    public final void run() {
        QueryInterceptorDatabase.execSQL$lambda$12(this.f$0, this.f$1, this.f$2);
    }
}
