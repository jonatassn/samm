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
import br.com.jonatassn.samm.dao.EspecimeDao;
import br.com.jonatassn.samm.model.Especime;
import br.com.jonatassn.samm.model.Tag;
import br.com.jonatassn.samm.model.Usuario;
import br.com.jonatassn.samm.service.AuthenticationService;
import br.com.jonatassn.samm.service.AuthenticationServiceImpl;


/**
 * @author Jonatas Silveira - <jonatassn.com.br>
 *
 */
public class EspecimeDaoImpl extends Dao implements EspecimeDao {

	@Override
	public void salvar(Especime entidade) {
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
	public Especime localizar(Long id) {
		EntityManager entityManager = getEntityManager();
		Especime especime = entityManager.find(Especime.class, id);
		return especime;
	}
	
	@Override
	public Especime localizar(Tag tag) {
		EntityManager entityManager = getEntityManager();
		TypedQuery<Especime> consulta = entityManager.createQuery(
				"SELECT e FROM Especime e WHERE e.tag = :tag", Especime.class);
		consulta.setParameter("tag", tag);
		try {
			Especime especime = consulta.getSingleResult();
			return especime;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Especime> listarTodos() {
		EntityManager entityManager = getEntityManager();
		TypedQuery<Especime> consulta = entityManager.createQuery(
				"SELECT e FROM Especime e", Especime.class);
		List<Especime> lista = new ArrayList<Especime>();
		try {
			lista = consulta.getResultList();
			return lista;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	@Override
	public List<Especime> listarTodos(String busca) {
		EntityManager entityManager = getEntityManager();
		String sql = "SELECT e FROM Especime e"+
					" WHERE e.nome LIKE :nome"+
					" OR e.informacoesBiometricas LIKE :info"+
					" OR e.pesquisador.nome LIKE :nomePesquisador"+
					" OR e.tag.codigoHexadecimal LIKE :codigoTag";
		TypedQuery<Especime> consulta = entityManager.createQuery(sql, Especime.class);
		consulta.setParameter("nome", "%"+busca+"%" );
		consulta.setParameter("info", "%"+busca+"%" );
		consulta.setParameter("nomePesquisador", "%"+busca+"%" );
		consulta.setParameter("codigoTag", "%"+busca+"%" );
		List<Especime> lista = new ArrayList<Especime>();
		try {
			lista = consulta.getResultList();
			return lista;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

}
