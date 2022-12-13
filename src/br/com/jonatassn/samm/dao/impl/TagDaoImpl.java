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
import br.com.jonatassn.samm.dao.TagDao;
import br.com.jonatassn.samm.model.Tag;
import br.com.jonatassn.samm.model.Usuario;
import br.com.jonatassn.samm.service.AuthenticationService;
import br.com.jonatassn.samm.service.AuthenticationServiceImpl;


/**
 * @author Jonatas Silveira - <jonatassn.com.br>
 *
 */
public class TagDaoImpl extends Dao implements TagDao {

	@Override
	public void salvar(Tag entidade) {
		EntityManager entityManager = getEntityManager();
		try {
			entityManager.getTransaction().begin();
			Calendar agora = Calendar.getInstance();
			AuthenticationService servico = new AuthenticationServiceImpl();
			Usuario usuario = servico.getUserCredential();
			String identificacao = "id: "+ usuario.getId() + " email: " +usuario.getEmail();
			if(entidade.getId() == null) {
				entidade.setCriadoPor(identificacao);
				entidade.setCriadoEm(agora.getTime());
				entityManager.persist(entidade);
			}
			else {
				entidade.setAlteradoPor(identificacao);
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
	public Tag localizar(Long id) {
		EntityManager entityManager = getEntityManager();
		Tag tag = entityManager.find(Tag.class, id);
		return tag;
	}
	
	@Override
	public Tag localizar(String codigo) {
		EntityManager entityManager = getEntityManager();
		TypedQuery<Tag> consulta = entityManager.createQuery(
				"SELECT t FROM Tag t WHERE t.codigoHexadecimal = :codigo", Tag.class);
		consulta.setParameter("codigo", codigo);
		try {
			Tag tag = consulta.getSingleResult();
			return tag;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Tag> listarTodos() {
		EntityManager entityManager = getEntityManager();
		TypedQuery<Tag> consulta = entityManager.createQuery(
				"SELECT t FROM Tag t", Tag.class);
		List<Tag> lista = new ArrayList<Tag>();
		try {
			lista = consulta.getResultList();
			return lista;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	@Override
	public List<Tag> listarTodos(String busca) {
		EntityManager entityManager = getEntityManager();
		String sql = "SELECT t FROM Tag t" +
					" WHERE t.codigoHexadecimal LIKE :codigo"+
					" OR t.frequencia = :frequencia";
		TypedQuery<Tag> consulta = entityManager.createQuery(sql, Tag.class);
		
		if(busca.toUpperCase().equals("INATIVO") || busca.toUpperCase().equals("ATIVO"))
			consulta.setParameter("status", busca.toUpperCase().equals("INATIVO")? false : true);
		List<Tag> lista = new ArrayList<Tag>();
		try {
			lista = consulta.getResultList();
			return lista;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

}
