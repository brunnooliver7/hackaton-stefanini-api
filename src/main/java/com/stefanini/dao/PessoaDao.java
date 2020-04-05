package com.stefanini.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.stefanini.dao.abstracao.GenericDao;
import com.stefanini.model.Pessoa;

/**
 * O Unico objetivo da Dao
 * 
 * @author joaopedromilhome
 *
 */
public class PessoaDao extends GenericDao<Pessoa, Long> {

	public PessoaDao() {
		super(Pessoa.class);
	}

	/**
	 * Efetuando busca de Pessoa por email
	 * 
	 * @param email
	 * @return
	 */
	public Optional<Pessoa> buscarPessoaPorEmail(String email) {
		TypedQuery<Pessoa> q2 = entityManager.createQuery(" select p from Pessoa p where p.email=:email", Pessoa.class);
		q2.setParameter("email", email);
		return q2.getResultStream().findFirst();
	};
	
	public List<Pessoa> listarPessoasVinculadas() {
		
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT DISTINCT p FROM Pessoa p ");
		sb.append("JOIN FETCH p.enderecos ");
		sb.append("JOIN FETCH p.perfils");
		
		TypedQuery<Pessoa> query = entityManager.createQuery(sb.toString(), Pessoa.class);
		return query.getResultList();
	};
	
	public Optional<Pessoa> obterPessoaVinculada(Long id) {
		
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT p FROM Pessoa p ");
		sb.append("JOIN FETCH p.enderecos ");
		sb.append("JOIN FETCH p.perfils ");
		sb.append("where p.id=:id");
		
		TypedQuery<Pessoa> query = entityManager.createQuery(sb.toString(), Pessoa.class);
		query.setParameter("id", id);
		return query.getResultStream().findFirst();
	}

}
