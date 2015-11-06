package business.applicationservice;

import business.checker.Checker;
import integration.dao.DAO;

public abstract class ApplicationServiceEntity<Data> implements ApplicationService, Gestione<Data> {
	
	/**
	 * {@link DAO}
	 */
	protected DAO<Data> dao;
	
	/**
	 * {@link Checker}
	 */
	protected Checker<Data> checker;
	
	/**
	 * Costruisce DAO e Checker dell'entità generica
	 * @param dao
	 * @param checker
	 */
	protected ApplicationServiceEntity(DAO<Data> dao, Checker<Data> checker) {
		this.dao = dao;
		this.checker = checker;
	}
}
