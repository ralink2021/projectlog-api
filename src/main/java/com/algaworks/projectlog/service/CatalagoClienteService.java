package com.algaworks.projectlog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.projectlog.exception.NegocioException;
import com.algaworks.projectlog.model.Cliente;
import com.algaworks.projectlog.repository.ClienteRepository;

@Service
public class CatalagoClienteService {
	
	@Autowired
	private ClienteRepository repository;
	
	public Cliente buscar(Long clienteId) {
		return repository.findById(clienteId)
				.orElseThrow(() -> new NegocioException("Cliente não encontrado"));
	}
	
	@Transactional
	public Cliente salvar(Cliente cliente) {
		boolean emailEmUso = repository.findByEmail(cliente.getEmail())
				.stream()
				.anyMatch(clienteExistente -> !clienteExistente.equals(cliente));
		if(emailEmUso) {
			throw new NegocioException("Ja existe um cliente cadastrado com esse email!");
		}
		return repository.save(cliente);
	}
	
	@Transactional
	public void excluir(Long clienteId) {
		repository.deleteById(clienteId);
	}
	
	
}
