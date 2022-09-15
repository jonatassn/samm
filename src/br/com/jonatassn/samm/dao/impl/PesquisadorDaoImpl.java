/*
 * Universidade Estadual do Paraná - Unespar
 * Núcleo de Tecnologia da Informação - NTI
 * Copyright (c) 2022 - Todos os direitos reservados.
 */
package br.com.jonatassn.samm.dao.impl;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.jonatassn.samm.dao.Dao;
import br.com.jonatassn.samm.dao.PesquisadorDao;
import br.com.jonatassn.samm.model.Pesquisador;
import br.com.jonatassn.samm.model.Usuario;

/**
 * @author Jonatas.Silveira - Unespar <jonatas.silveira@unespar.edu.br>
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
	public List<Pesquisador> listarTodos() {
		EntityManager entityManager = getEntityManager();
		TypedQuery<Pesquisador> consulta = entityManager.createQuery(
				"SELECT p FROM Pesquisador p", Pesquisador.class);
		return consulta.getResultList();
	}

}
