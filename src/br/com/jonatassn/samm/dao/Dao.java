/*
 * Universidade Estadual do Paraná - Unespar
 * Núcleo de Tecnologia da Informação - NTI
 * Copyright (c) 2022 - Todos os direitos reservados.
 */
package br.com.jonatassn.samm.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author Jonatas.Silveira - Unespar <jonatas.silveira@unespar.edu.br>
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
