package business.applicationservice;

import java.util.List;
import integration.dao.DAOFactory;
import business.checker.CheckerFactory;
import business.entity.Agenzia;
import business.exception.IntegrityException;


public class ApplicationServiceAgenzia extends ApplicationServiceEntity<Agenzia> {

	/**
	 * Costuttore che istanzia DAO e Checker relativi all'agenzia
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	@SuppressWarnings("unchecked")
	public ApplicationServiceAgenzia() throws InstantiationException, IllegalAccessException {
		super(DAOFactory.buildDao(Agenzia.class), CheckerFactory.buildChecker(Agenzia.class));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void create(Agenzia entity) throws IntegrityException{
		try {
			checker.check(entity);
			dao.create(entity);
		} catch (IntegrityException e) {
			e.printStackTrace();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(Agenzia entity) throws IntegrityException{
		checker.isModifiable(dao.read(Integer.toString(entity.getId())));
		checker.check(entity);
		dao.update(entity);
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(Agenzia entity) throws IntegrityException{
		checker.isModifiable(dao.read(Integer.toString(entity.getId())));
		if (entity.getId() != 1) 
			dao.delete(Integer.toString(entity.getId()));
		else {
			throw new IntegrityException("Impossibile eliminare");
		}

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Agenzia> readAll() {
		return dao.readAll();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Agenzia read(String pk){
		return dao.read(pk);
	}

}
