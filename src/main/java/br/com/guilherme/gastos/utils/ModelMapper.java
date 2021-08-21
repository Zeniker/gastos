package br.com.guilherme.gastos.utils;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

public final class ModelMapper {

    private static Mapper SINGLETON_MAPPER;

    public static Mapper getMapper(){

        if(SINGLETON_MAPPER == null){
            SINGLETON_MAPPER = DozerBeanMapperBuilder.buildDefault();
        }

        return SINGLETON_MAPPER;
    }
}
