package presentation.frontcontroller;

import business.exception.CarloanException;

public class CarloanApplicationController implements ApplicationController{

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Target handleRequest(String request, Object entity) {
		try {
			Target res = TargetFactory.buildTarget(request, entity);
			return res; 
		} catch (CarloanException e) {
			e.showError();
		}
		return null; 
	}
}
