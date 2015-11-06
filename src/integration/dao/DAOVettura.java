package integration.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import business.entity.StatoVettura;
import business.entity.Vettura;

public class DAOVettura extends DAOCarloan<Vettura> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void create(Vettura entity) {
		connection.executeUpdateQuery("insert into vettura values("
									+ "'" + entity.getTarga() + "', "
									+ entity.getModello().getId() + ", "
									+ entity.getAgenziaLocalizzazione().getId() + ", "
									+ entity.getChilometraggio() + ", "
									+ entity.getStato().getIndex()
									+ ");");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(Vettura entity) {
		connection.executeUpdateQuery("update vettura set " + 
									  "agenzialocalizzazione = " + entity.getAgenziaLocalizzazione().getId() + ", " +
									  "chilometraggio = " + entity.getChilometraggio() + ", " +
									  "stato = " + entity.getStato().getIndex() + " where targa = '" + entity.getTarga() + "';");	
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Vettura read(String pk) {
		Vettura vettura = null;
		try {
			vettura = new Vettura();
			ResultSet rs = connection.executeReadQuery("select * from vettura where targa = '" + pk + "';"); 
			while(rs.next()) {
				vettura.setTarga(rs.getString("targa"));
				vettura.setAgenziaLocalizzazione(new DAOAgenzia().read(Integer.toString(rs.getInt("agenzialocalizzazione"))));
				vettura.setModello(new DAOModello().read(Integer.toString(rs.getInt("modello"))));
				vettura.setChilometraggio(rs.getInt("chilometraggio"));
				vettura.setStato(StatoVettura.getStato(rs.getInt("stato")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vettura;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(String pk) {
		connection.executeUpdateQuery("delete from vettura where targa = '" + pk + "';");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Vettura> readAll() {
		List<Vettura> vetture = new ArrayList<Vettura>();
		ResultSet rs = connection.executeReadQuery("select targa from vettura;");
		try {
			while(rs.next()) vetture.add(read(rs.getString("targa")));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vetture;
	}

}
