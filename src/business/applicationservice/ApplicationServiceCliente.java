package business.applicationservice;

import java.util.List;

import business.checker.Checker;
import business.checker.CheckerFactory;
import business.entity.Cliente;
import business.exception.IntegrityException;
import integration.dao.DAO;
import integration.dao.DAOFactory;

public class ApplicationServiceCliente extends ApplicationServiceEntity<Cliente> {
	
	/**
	 * Si occupa della costruzione del DAO e del checker del Cliente
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	@SuppressWarnings("unchecked")
	public ApplicationServiceCliente() throws InstantiationException, IllegalAccessException {
		super((DAO<Cliente>) DAOFactory.buildDao(Cliente.class), (Checker<Cliente>) CheckerFactory.buildChecker(Cliente.class));
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public void create(Cliente entity) throws IntegrityException {
		checker.check(entity);
		dao.create(entity);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(Cliente entity) throws IntegrityException {
		checker.isModifiable(dao.read(entity.getCodicePatente()));
		checker.check(entity);
		dao.update(entity);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(Cliente entity) throws IntegrityException {
		checker.isModifiable(entity);
		dao.delete(entity.getCodicePatente());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Cliente read(String pk) {
		return dao.read(pk);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Cliente> readAll() {
		return dao.readAll();
	}

}
