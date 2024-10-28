package androidx.databinding;

public class DataBinderMapperImpl extends MergedDataBinderMapper {
    DataBinderMapperImpl() {
        addMapper((DataBinderMapper) new org.videolan.mobile.app.DataBinderMapperImpl());
    }
}
