package com.duobi.wuye.dto;

public interface DTOConverterForOne<S,T> {
    T convert(S s);
}
