package com.algaworks.projectlog.mappers;

import com.algaworks.projectlog.dto.EntregaDTO;
import com.algaworks.projectlog.model.Entrega;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class EntregaMapper {

    private ModelMapper modelMapper;

    /*Transforma um objeto do tipo Entrega em um Model*/
    public EntregaDTO toModel(Entrega entrega){
        return  modelMapper.map(entrega, EntregaDTO.class);
    }

    public List<EntregaDTO> toCollectionModel(List<Entrega> entregas){
        return  entregas.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

}
