package com.algaworks.projectlog.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.projectlog.model.Cliente;
import com.algaworks.projectlog.repository.ClienteRepository;
import com.algaworks.projectlog.service.CatalagoClienteService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	
	private ClienteRepository repository;
	private CatalagoClienteService service;
	
	/* Metodo GET - Listar todos os Registros */
	@GetMapping
	public List<Cliente> listar() {
		return repository.findAll();
	}
	
	/* Metodo GET BY ID - Listar um registro pelo ID */
	@GetMapping("/{clienteId}")
	public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId) {
		Optional<Cliente> cliente = repository.findById(clienteId);
		if(cliente.isPresent()) {
			return ResponseEntity.ok(cliente.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	/* Metodo POST - Cadastra um novo Cliente */
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Cliente adicionar(@Valid @RequestBody Cliente cliente) {
		return service.salvar(cliente);
	}
	
	/* Metodo PUT - Altera um Registro pelo ID especificado */
	@PutMapping("/{clienteId}")
	public ResponseEntity<Cliente> atualizar(@PathVariable Long clienteId, 
			@Valid @RequestBody Cliente cliente) {
		if(!repository.existsById(clienteId)) {
			return ResponseEntity.notFound().build(); 
		}
		cliente.setId(clienteId);
		cliente = service.salvar(cliente);
		return ResponseEntity.ok(cliente);
	}
	
	/* Metodo DELETE - Deleta um Registro pelo seu ID */
	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Void> deletar(@PathVariable Long clienteId){
		if(!repository.existsById(clienteId)) {
			return ResponseEntity.notFound().build(); 
		}
		service.excluir(clienteId);
		return ResponseEntity.noContent().build();
	}

}
