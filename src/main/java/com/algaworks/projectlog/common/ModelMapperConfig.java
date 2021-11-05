package com.algaworks.projectlog.common;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    /* Classe de Configuração do Model Mapper **/
    @Bean
    public ModelMapper modelMapper(){
        return  new ModelMapper();
    }

}
