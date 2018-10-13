package com.test.mapper;


import com.test.facade.authentication.model.AuthenticationRequest;
import com.test.security.dto.AuthRequestDto;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

@Component
public class ModelMapper extends ConfigurableMapper {
    private MapperFactory factory;

    @Override
    protected void configure(MapperFactory factory) {
        this.factory = factory;

        factory.classMap(AuthRequestDto.class, AuthenticationRequest.class)
                .field("password", "plainPassword")
                .byDefault()
                .register();
    }

}
