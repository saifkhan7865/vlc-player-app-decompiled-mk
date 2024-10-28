package org.videolan.television.ui;

import kotlin.Metadata;
import kotlin.UInt$$ExternalSyntheticBackport0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0004\b\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\nJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0016\u001a\u00020\bHÆ\u0003J\t\u0010\u0017\u001a\u00020\bHÆ\u0003J;\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\bHÆ\u0001J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001c\u001a\u00020\bHÖ\u0001J\t\u0010\u001d\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\t\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000e¨\u0006\u001e"}, d2 = {"Lorg/videolan/television/ui/GenericCardItem;", "", "id", "", "title", "", "content", "icon", "", "color", "(JLjava/lang/String;Ljava/lang/String;II)V", "getColor", "()I", "getContent", "()Ljava/lang/String;", "getIcon", "getId", "()J", "getTitle", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: GenericCardPresenter.kt */
public final class GenericCardItem {
    private final int color;
    private final String content;
    private final int icon;
    private final long id;
    private final String title;

    public static /* synthetic */ GenericCardItem copy$default(GenericCardItem genericCardItem, long j, String str, String str2, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            j = genericCardItem.id;
        }
        long j2 = j;
        if ((i3 & 2) != 0) {
            str = genericCardItem.title;
        }
        String str3 = str;
        if ((i3 & 4) != 0) {
            str2 = genericCardItem.content;
        }
        String str4 = str2;
        if ((i3 & 8) != 0) {
            i = genericCardItem.icon;
        }
        int i4 = i;
        if ((i3 & 16) != 0) {
            i2 = genericCardItem.color;
        }
        return genericCardItem.copy(j2, str3, str4, i4, i2);
    }

    public final long component1() {
        return this.id;
    }

    public final String component2() {
        return this.title;
    }

    public final String component3() {
        return this.content;
    }

    public final int component4() {
        return this.icon;
    }

    public final int component5() {
        return this.color;
    }

    public final GenericCardItem copy(long j, String str, String str2, int i, int i2) {
        Intrinsics.checkNotNullParameter(str, "title");
        Intrinsics.checkNotNullParameter(str2, "content");
        return new GenericCardItem(j, str, str2, i, i2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof GenericCardItem)) {
            return false;
        }
        GenericCardItem genericCardItem = (GenericCardItem) obj;
        return this.id == genericCardItem.id && Intrinsics.areEqual((Object) this.title, (Object) genericCardItem.title) && Intrinsics.areEqual((Object) this.content, (Object) genericCardItem.content) && this.icon == genericCardItem.icon && this.color == genericCardItem.color;
    }

    public int hashCode() {
        return (((((((UInt$$ExternalSyntheticBackport0.m(this.id) * 31) + this.title.hashCode()) * 31) + this.content.hashCode()) * 31) + this.icon) * 31) + this.color;
    }

    public String toString() {
        return "GenericCardItem(id=" + this.id + ", title=" + this.title + ", content=" + this.content + ", icon=" + this.icon + ", color=" + this.color + ')';
    }

    public GenericCardItem(long j, String str, String str2, int i, int i2) {
        Intrinsics.checkNotNullParameter(str, "title");
        Intrinsics.checkNotNullParameter(str2, "content");
        this.id = j;
        this.title = str;
        this.content = str2;
        this.icon = i;
        this.color = i2;
    }

    public final long getId() {
        return this.id;
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getContent() {
        return this.content;
    }

    public final int getIcon() {
        return this.icon;
    }

    public final int getColor() {
        return this.color;
    }
}
