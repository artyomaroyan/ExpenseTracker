//package main.java.persistence;
//
//**
// * Author: Artyom Aroyan
// * Date: 22.06.26
// * Time: 18:58:34
// */
//public class DelegatingModelInformation<T, ID> implements ModelInformation<T, ID> {
//    private final ModelInformation<T, ID> delegate;
//
//    public DelegatingModelInformation(ModelInformation<T, ID> delegate) {
//        this.delegate = delegate;
//    }
//
//    @Override
//    public boolean isNew(T model) {
//        return delegate.isNew(model);
//    }
//
//    @Override
//    public ID getId(T model) {
//        return delegate.getId(model);
//    }
//
//    @Override
//    public Class<ID> getIdType() {
//        return delegate.getIdType();
//    }
//
//    @Override
//    public Class<T> getJavaType() {
//        return delegate.getJavaType();
//    }
//}