package com.algaworks.projectlog.controller;

import java.util.List;

import javax.validation.Valid;

import com.algaworks.projectlog.dto.DestinatarioDTO;
import com.algaworks.projectlog.dto.EntregaDTO;
import com.algaworks.projectlog.mappers.EntregaMapper;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.projectlog.model.Entrega;
import com.algaworks.projectlog.repository.EntregaRepository;
import com.algaworks.projectlog.service.SolicitacaoEntregaService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas")
public class EntregaController {
	
	/* Injeção de Dependencia sendo feita pelo construtor em vez de usar o @Autowired */
	private SolicitacaoEntregaService entregaService;
	private EntregaRepository repository;
	private EntregaMapper mapper;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EntregaDTO solicitar(@Valid @RequestBody Entrega entrega) {
		Entrega entregaSolicitada = entregaService.solicitar(entrega);
		return mapper.toModel(entregaSolicitada);
	}
	
	@GetMapping
	public List<EntregaDTO> listar(){
		return mapper.toCollectionModel(repository.findAll());
	}
	
	@GetMapping("/{entregaId}")
	public ResponseEntity<EntregaDTO> buscar(@PathVariable Long entregaId){
		return repository.findById(entregaId)
				.map(entrega -> ResponseEntity.ok(mapper.toModel(entrega)))
				.orElse(ResponseEntity.notFound().build());
	}
}
