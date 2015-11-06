package business.checker;

import java.util.List;

import integration.dao.DAOFascia;
import business.entity.Fascia;
import business.exception.IntegrityException;

public class CheckerFascia implements Checker<Fascia>{
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void check(Fascia entity) throws IntegrityException {
		if (entity.getTariffaBase() < 0) 
			throw new IntegrityException("La tariffa base della fascia non può essere negativa");
		if (entity.getMax() <= entity.getMin()) 
			throw new IntegrityException("Il valore massimo non può essere inferiore dell'indice minimo");
		if (entity.getMax() == 0) {
			throw new IntegrityException("L'indice massimo non può essere pari a 0");
		}
		List<Fascia> fasce = new DAOFascia().readAll();
		for (Fascia f:fasce) {
			if (entity.getId() != f.getId()) {
				if (entity.getMin() >= f.getMin() && entity.getMin() <= f.getMax())
					throw new IntegrityException("Indici in conflitto con altre fasce");
				if (entity.getMax() <= f.getMax() && entity.getMax() >= f.getMin() && entity.getMin() <= f.getMin())
					throw new IntegrityException("Indici in conflitto con altre fasce");
				if (entity.getMin() >= f.getMin() && entity.getMax() <= f.getMax())
					throw new IntegrityException("Indici in conflitto con altre fasce");
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void isModifiable(Fascia entity) {
		
	}
}
