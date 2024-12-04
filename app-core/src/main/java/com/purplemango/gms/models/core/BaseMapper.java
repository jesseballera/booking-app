package com.purplemango.gms.models.core;


import java.util.Collection;

public interface BaseMapper<S, T>{

    S toEntity(T entity);
    T fromEntity(S entity);
    S fromId(final Long id);
    Collection<S> list(final Collection<T> ids);
}
