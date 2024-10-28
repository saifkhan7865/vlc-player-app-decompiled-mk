package org.videolan.television.ui;

import androidx.leanback.widget.DiffCallback;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.moviepedia.database.models.Person;

@Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0002H\u0016J\u0018\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0002H\u0016¨\u0006\b"}, d2 = {"org/videolan/television/ui/MediaItemDetailsFragment$personsDiffCallback$1", "Landroidx/leanback/widget/DiffCallback;", "Lorg/videolan/moviepedia/database/models/Person;", "areContentsTheSame", "", "oldItem", "newItem", "areItemsTheSame", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaItemDetailsFragment.kt */
public final class MediaItemDetailsFragment$personsDiffCallback$1 extends DiffCallback<Person> {
    MediaItemDetailsFragment$personsDiffCallback$1() {
    }

    public boolean areItemsTheSame(Person person, Person person2) {
        Intrinsics.checkNotNullParameter(person, "oldItem");
        Intrinsics.checkNotNullParameter(person2, "newItem");
        return Intrinsics.areEqual((Object) person.getMoviepediaId(), (Object) person2.getMoviepediaId());
    }

    public boolean areContentsTheSame(Person person, Person person2) {
        Intrinsics.checkNotNullParameter(person, "oldItem");
        Intrinsics.checkNotNullParameter(person2, "newItem");
        return Intrinsics.areEqual((Object) person.getMoviepediaId(), (Object) person2.getMoviepediaId()) && Intrinsics.areEqual((Object) person.getImage(), (Object) person2.getImage()) && Intrinsics.areEqual((Object) person.getName(), (Object) person2.getName());
    }
}
