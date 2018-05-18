package org.aist.aide.apiservice.domain.strategies;

public interface ApiStrategy<T, K> {
    K run(T obj);
}
