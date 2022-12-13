/*
 * Sistema de Auxílio ao Módulo de Monitoramento
 * Copyright (c) 2022 - Todos os direitos reservados.
 */
package br.com.jonatassn.samm.dao.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.jonatassn.samm.dao.Dao;
import br.com.jonatassn.samm.dao.UsuarioDao;
import br.com.jonatassn.samm.model.Usuario;


/**
 * @author Jonatas Silveira - <jonatassn.com.br>
 *
 */
public class UsuarioDaoImpl extends Dao implements UsuarioDao {

	@Override
	public void salvar(Usuario entidade) {
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
	public Usuario localizar(Long id) {
		EntityManager entityManager = getEntityManager();
		Usuario usuario = entityManager.find(Usuario.class, id);
		return usuario;
	}
	
	@Override
	public Usuario localizar(String email, String senha) throws Exception {
		EntityManager entityManager = getEntityManager();
		TypedQuery<Usuario> consulta = entityManager.createQuery(
				"SELECT u FROM Usuario u WHERE u.email = :email AND u.password = :senha", Usuario.class);
		consulta.setParameter("email", email);
		consulta.setParameter("senha", senha);
			return consulta.getSingleResult();
	}

	@Override
	public List<Usuario> listarTodos() {
		EntityManager entityManager = getEntityManager();
		TypedQuery<Usuario> consulta = entityManager.createQuery(
				"SELECT u FROM Usuario u", Usuario.class);
		List<Usuario> lista = new ArrayList<Usuario>();
		try {
			lista = consulta.getResultList();
			return lista;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	@Override
	public List<Usuario> listarTodos(String busca) {
		EntityManager entityManager = getEntityManager();
		String sql = "SELECT u FROM Usuario u" +
					" WHERE u.email LIKE :email"+
					(busca.matches(".*admin*.") ? " OR u.admin = :admin" :"");
		if(busca.toUpperCase().equals("INATIVO") || busca.toUpperCase().equals("ATIVO"))			
					 sql += " OR u.status = :status";
		TypedQuery<Usuario> consulta = entityManager.createQuery(
				sql, Usuario.class);
		consulta.setParameter("email", "%"+busca+"%");
		if(busca.matches(".*admin*.") )
			consulta.setParameter("admin", busca.matches(".*admin*.")? true: "");
		if(busca.toUpperCase().equals("INATIVO") || busca.toUpperCase().equals("ATIVO"))
			consulta.setParameter("status", busca.toUpperCase().equals("INATIVO")? false : true);
		List<Usuario> lista = new ArrayList<Usuario>();
		try {
			lista = consulta.getResultList();
			return lista;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

}
