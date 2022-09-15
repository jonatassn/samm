/*
 * Universidade Estadual do Paraná - Unespar
 * Núcleo de Tecnologia da Informação - NTI
 * Copyright (c) 2022 - Todos os direitos reservados.
 */
package br.com.jonatassn.samm.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.jonatassn.samm.dao.Dao;
import br.com.jonatassn.samm.dao.UsuarioDao;
import br.com.jonatassn.samm.model.Usuario;

/**
 * @author Jonatas.Silveira - Unespar <jonatas.silveira@unespar.edu.br>
 *
 */
public class UsuarioDaoImpl extends Dao implements UsuarioDao {

	@Override
	public void salvar(Usuario entidade) {
		EntityManager entityManager = getEntityManager();
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(entidade);
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
	public List<Usuario> listarTodos() {
		EntityManager entityManager = getEntityManager();
		TypedQuery<Usuario> consulta = entityManager.createQuery(""
				+ "SELECT u FROM Usuario u", Usuario.class);
		List<Usuario> lista = consulta.getResultList();
		return lista;
	}

}
