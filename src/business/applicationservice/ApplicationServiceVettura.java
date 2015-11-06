package business.applicationservice;

import java.util.Iterator;
import java.util.List;

import integration.dao.DAOFactory;
import business.checker.CheckerFactory;
import business.entity.Agenzia;
import business.entity.Vettura;
import business.exception.IntegrityException;

public class ApplicationServiceVettura extends ApplicationServiceEntity<Vettura> implements Gestione<Vettura>{

	/**
	 * Costruisce il DAO e il checker della vettura
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	@SuppressWarnings("unchecked")
	public ApplicationServiceVettura() throws InstantiationException, IllegalAccessException {
		super(DAOFactory.buildDao(Vettura.class), CheckerFactory.buildChecker(Vettura.class));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void create(Vettura entity) throws IntegrityException {
		checker.check(entity);
		dao.create(entity);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(Vettura entity) throws IntegrityException {
		checker.isModifiable(read(entity.getTarga()));
		checker.check(entity);
		dao.update(entity);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(Vettura entity) throws IntegrityException {
		checker.isModifiable(read(entity.getTarga()));
		checker.check(entity);
		dao.delete(entity.getTarga());
	}

	
	public List<Vettura> filtra(Agenzia agenzia) {
		List<Vettura> vetture = readAll();
		for (Iterator<Vettura> iv = vetture.iterator(); iv.hasNext();) {
			Vettura v = iv.next();
			if (v.getAgenziaLocalizzazione().getId() != agenzia.getId()) {
				iv.remove();
			}
		}
		return vetture;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Vettura> readAll() {
		return dao.readAll();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Vettura read(String pk) {
		return dao.read(pk);
	}

}
