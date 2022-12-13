/*
 * Sistema de Auxílio ao Módulo de Monitoramento
 * Copyright (c) 2022 - Todos os direitos reservados.
 */
package br.com.jonatassn.samm.dao.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.jonatassn.samm.dao.Dao;
import br.com.jonatassn.samm.dao.PesquisadorDao;
import br.com.jonatassn.samm.model.Pesquisador;
import br.com.jonatassn.samm.model.Usuario;


/**
 * @author Jonatas Silveira - <jonatassn.com.br>
 *
 */
public class PesquisadorDaoImpl extends Dao implements PesquisadorDao {

	@Override
	public void salvar(Pesquisador entidade) {
		EntityManager entityManager = getEntityManager();
		try {
			entityManager.getTransaction().begin();
			Calendar agora = Calendar.getInstance();
			if(entidade.getId() == null) {
				entidade.setCriadoEm(agora.getTime());
				entityManager.persist(entidade);
			}
			else {
				entidade.setAlteradoEm(agora.getTime());
				entityManager.persist(entidade);
			}
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	@Override
	public Pesquisador localizar(Long id) {
		EntityManager entityManager = getEntityManager();
		Pesquisador pesquisador = entityManager.find(Pesquisador.class, id);
		return pesquisador;
	}
	
	@Override
	public Pesquisador localizar(Usuario usuario) {
		EntityManager entityManager = getEntityManager();
		TypedQuery<Pesquisador> consulta = entityManager.createQuery(
				"FROM Pesquisador p WHERE p.usuario = :usuario", Pesquisador.class);
		consulta.setParameter("usuario", usuario);
		Pesquisador result;
		try {
			result = consulta.getSingleResult();
			return result;
		}
		catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Pesquisador> listarTodos() {
		EntityManager entityManager = getEntityManager();
		TypedQuery<Pesquisador> consulta = entityManager.createQuery(
				"SELECT p FROM Pesquisador p", Pesquisador.class);
		return consulta.getResultList();
	}

	@Override
	public List<Pesquisador> listarTodos(String busca) {
		EntityManager entityManager = getEntityManager();
		String sql = "SELECT p FROM Pesquisador p" +
					" WHERE p.usuario.email LIKE :email"+
					" OR p.nome LIKE :nome"+
					" OR p.sobrenome LIKE :sobreNome"+
					" OR p.cpf = :cpf"+
					(busca.matches(".*admin*.") ? " OR p.usuario.admin = :admin" :"");
		if(busca.toUpperCase().equals("INATIVO") || busca.toUpperCase().equals("ATIVO"))			
					 sql += " OR p.status = :status";
		
		TypedQuery<Pesquisador> consulta = entityManager.createQuery(
				sql, Pesquisador.class);
		
		consulta.setParameter("nome", "%"+busca+"%");
		consulta.setParameter("sobreNome", "%"+busca+"%");
		consulta.setParameter("cpf", busca);
		consulta.setParameter("email", "%"+busca+"%");
		if(busca.matches(".*admin*.") )
			consulta.setParameter("admin", busca.matches(".*admin*.")? true: "");
		if(busca.toUpperCase().equals("INATIVO") || busca.toUpperCase().equals("ATIVO"))
			consulta.setParameter("status", busca.toUpperCase().equals("INATIVO")? false : true);
		List<Pesquisador> lista = new ArrayList<Pesquisador>();
		try {
			lista = consulta.getResultList();
			return lista;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
}
