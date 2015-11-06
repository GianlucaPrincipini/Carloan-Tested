package business.applicationservice;

import java.util.List;

import integration.dao.DAOFactory;
import business.checker.CheckerFactory;
import business.entity.Fascia;
import business.entity.Modello;
import business.exception.CarloanException;
import business.exception.FasciaIndexException;
import business.exception.IntegrityException;

public class ApplicationServiceFascia extends ApplicationServiceEntity<Fascia> {

	/**
	 * Costruisce DAO e checker della Fascia
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	@SuppressWarnings("unchecked")
	public ApplicationServiceFascia() throws InstantiationException, IllegalAccessException {
		super(DAOFactory.buildDao(Fascia.class), CheckerFactory.buildChecker(Fascia.class));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void create(Fascia entity) throws IntegrityException {
		checker.check(entity);
		dao.create(entity);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(Fascia entity) throws CarloanException {
		checker.isModifiable(dao.read(Integer.toString(entity.getId())));
		checker.check(entity);
		dao.update(entity);
		updateFasceModelli();
		
	}

	/**
	 * Aggiorna gli indici di fascia relativi a tutti i modelli
	 * @throws CarloanException
	 */
	private void updateFasceModelli() throws CarloanException {
		ApplicationServiceModello asModello;
		try {
			asModello = new ApplicationServiceModello();
			List<Modello> modelli = asModello.readAll();
			for (Modello m:modelli) {
				try {
					asModello.calcolaFascia(m);
				} catch (FasciaIndexException e) {
					e.showError();
				}
				asModello.update(m);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(Fascia entity) throws CarloanException {
		checker.isModifiable(dao.read(Integer.toString(entity.getId())));
		List<Fascia> fasce = readAll();
		for (int i = 0; i < fasce.size(); i++) {
			if (fasce.get(i).getId() == 1) continue;
			if (fasce.get(i).compareTo(entity) == 0) {
				for (Fascia f:fasce) {
					if (f.getMax() == entity.getMin()) {
						f.setMax(entity.getMax());
						update(f);
						updateFasceModelli();
					}
				}
				dao.delete(Integer.toString(entity.getId()));
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Fascia> readAll() {
		return dao.readAll();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Fascia read(String pk) {
		return dao.read(pk);
	}
	
	
}
