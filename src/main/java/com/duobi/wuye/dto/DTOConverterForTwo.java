package com.duobi.wuye.dto;

public interface DTOConverterForTwo <S,T,K>{

    T convertOne(S s);

    K convertTwo(S s);



}
