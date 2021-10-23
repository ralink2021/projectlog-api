package com.algaworks.projectlog.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.projectlog.exception.NegocioException;
import com.algaworks.projectlog.model.Cliente;
import com.algaworks.projectlog.model.Entrega;
import com.algaworks.projectlog.model.StatusEntrega;
import com.algaworks.projectlog.repository.EntregaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class SolicitacaoEntregaService {
	
	private EntregaRepository repository;
	private CatalagoClienteService service;
	
	@Transactional
	public Entrega solicitar(Entrega entrega) {
		Cliente cliente = service.buscar(entrega.getCliente().getId());
		
		entrega.setCliente(cliente);
		entrega.setStatus(StatusEntrega.PENDENTE);
		entrega.setDataPedido(LocalDateTime.now());
		
		return repository.save(entrega);
		
	}

}
