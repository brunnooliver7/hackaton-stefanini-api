package com.stefanini.servico;

import com.github.gilbertotorrezan.viacep.se.ViaCEPClient;
import com.github.gilbertotorrezan.viacep.shared.ViaCEPEndereco;
import com.stefanini.dao.EnderecoDao;
import com.stefanini.model.Endereco;

import javax.ejb.*;
import javax.inject.Inject;
import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * 
 * Classe de servico, as regras de negocio devem estar nessa classe
 * @author joaopedromilhome
 *
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class EnderecoServico implements Serializable {
	
	@Inject
	private EnderecoDao dao;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Endereco salvar(@Valid Endereco entity) {
		return dao.salvar(entity);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Endereco atualizar(@Valid Endereco entity) {
		return dao.atualizar(entity);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void remover(Long id) {
	dao.remover(id);
	}

	public Optional<List<Endereco>> getList() {
		return dao.getList();
	}

	public Optional<Endereco> encontrar(Long id) {
		return dao.encontrar(id);
	}
	
	public Endereco converterCep(String cep) {
		System.out.println(cep);
		ViaCEPClient client = new ViaCEPClient();
		Endereco endereco = new Endereco();
		try {
			ViaCEPEndereco enderecoCep = client.getEndereco(cep);
			endereco.setCep(enderecoCep.getCep());
			endereco.setBairro(enderecoCep.getBairro());
			endereco.setComplemento(enderecoCep.getComplemento());
			endereco.setLocalidade(enderecoCep.getLocalidade());
			endereco.setUf(enderecoCep.getUf());
			endereco.setLogradouro(enderecoCep.getLogradouro());			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return endereco;
	}
}
