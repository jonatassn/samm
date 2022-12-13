/*
 * Sistema de Auxílio ao Módulo de Monitoramento
 * Copyright (c) 2022 - Todos os direitos reservados.
 */
package br.com.jonatassn.samm.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


/**
 * @author Jonatas Silveira - <jonatassn.com.br>
 *
 */
public class Dao {
	private static EntityManager entityManager = null;
	private static EntityManagerFactory entityManagerFactory = null;
	
	public static EntityManager getEntityManager() {
		if(entityManagerFactory == null) {
			entityManagerFactory = Persistence.createEntityManagerFactory("DS-SAMM-DEV");
		}
		if(entityManager == null) {
			entityManager = entityManagerFactory.createEntityManager();
		}
		return entityManager;	
	}
}
