package business.applicationservice;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import utils.Encrypt;
import integration.dao.DAOFactory;
import business.checker.CheckerFactory;
import business.entity.Operatore;
import business.exception.CarloanException;
import business.exception.IntegrityException;

public class ApplicationServiceOperatore extends ApplicationServiceEntity<Operatore> implements Gestione<Operatore>{

	/**
	 * Costruisce il DAO e il Checker dell'operatore 
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	@SuppressWarnings("unchecked")
	public ApplicationServiceOperatore() throws InstantiationException, IllegalAccessException {
		super(DAOFactory.buildDao(Operatore.class), CheckerFactory.buildChecker(Operatore.class));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void create(Operatore entity) throws IntegrityException {
		if (dao.read(entity.getUsername()) != null) throw new IntegrityException("Username già in uso");
		checker.check(entity);
		dao.create(entity);
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(Operatore entity) throws IntegrityException {
		checker.isModifiable(read(entity.getUsername()));
		dao.update(entity);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(Operatore entity) throws IntegrityException {
		checker.isModifiable(read(entity.getUsername()));
		dao.delete(entity.getUsername());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Operatore> readAll() {
		return dao.readAll();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Operatore read(String pk) {
		return dao.read(pk);
	}
	
	/**
	 * Si occupa di effettuare il login per un operatore specifico
	 * @param operatore
	 * @return boolean indicante il successo o  l'insuccesso dell'operazione
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 * @throws CarloanException
	 */
	public boolean login(Operatore operatore) throws NoSuchAlgorithmException, UnsupportedEncodingException, CarloanException {
		Operatore toLog = dao.read(operatore.getUsername());
		if (toLog != null) {
			if (toLog.getPassword().equals(Encrypt.getEncryptedString(operatore.getPassword())))
				return true;
		}
		throw new CarloanException("Impossibile effettuare il login, assicurarsi che username e password siano corretti");
	}

}
