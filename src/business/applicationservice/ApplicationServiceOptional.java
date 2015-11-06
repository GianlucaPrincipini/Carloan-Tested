package business.applicationservice;

import integration.dao.DAOFactory;

import java.util.List;

import business.checker.CheckerFactory;
import business.entity.Optional;
import business.exception.IntegrityException;

public class ApplicationServiceOptional extends ApplicationServiceEntity<Optional> implements Gestione<Optional>{

	/**
	 * Costruisce il DAO e il checker di Optional
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	@SuppressWarnings("unchecked")
	public ApplicationServiceOptional() throws InstantiationException, IllegalAccessException {
		super(DAOFactory.buildDao(Optional.class), CheckerFactory.buildChecker(Optional.class));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void create(Optional entity) throws IntegrityException {
		checker.check(entity);
		dao.create(entity);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(Optional entity) throws IntegrityException {
		checker.isModifiable(dao.read(Integer.toString(entity.getId())));
		checker.check(entity);
		dao.update(entity);	
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(Optional entity) throws IntegrityException {
		checker.isModifiable(dao.read(Integer.toString(entity.getId())));
		dao.delete(Integer.toString(entity.getId()));
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Optional> readAll() {
		return dao.readAll();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional read(String pk) {
		return dao.read(pk);
	}

}
