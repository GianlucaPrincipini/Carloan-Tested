package business.checker;

import business.entity.Optional;
import business.exception.IntegrityException;

public class CheckerOptional implements Checker<Optional>{

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void check(Optional entity) throws IntegrityException {
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void isModifiable(Optional entity) throws IntegrityException {

	}

}
