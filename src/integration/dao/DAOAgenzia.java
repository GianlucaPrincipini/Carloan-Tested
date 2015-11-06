package integration.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import business.entity.Agenzia;

public class DAOAgenzia extends DAOCarloan<Agenzia>{

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void create(Agenzia entity) {
		connection.executeUpdateQuery("INSERT INTO agenzia(indirizzo, citta, numTelefono)"
									+ " values(" 
									+ "'" + entity.getIndirizzo() + "', " 
									+ "'" + entity.getCittà() + "', "
									+ "'" + entity.getNumTelefono() + "'"
									+ ");");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(Agenzia entity) {
		connection.executeUpdateQuery("UPDATE agenzia SET " +
									  "indirizzo = '" + entity.getIndirizzo() + "', " +
									  "citta = '" + entity.getCittà() + "', " + 
									  "numTelefono = '" + entity.getNumTelefono() + "' " 
									  + "WHERE id = '" + entity.getId() + "'; ");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Agenzia read(String pk) {
		Agenzia agenzia = null;
		if (pk.isEmpty()) return null;
		ResultSet rs = connection.executeReadQuery("SELECT * FROM agenzia WHERE id = " + pk + ";");
		try {
			agenzia = new Agenzia();
			while (rs.next()) {
				agenzia.setId(rs.getInt("id"));
				agenzia.setIndirizzo(rs.getString("indirizzo"));
				agenzia.setCittà(rs.getString("citta"));
				agenzia.setNumTelefono(rs.getString("numtelefono"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return agenzia;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(String pk) {
		if (!pk.equals("1"))
			connection.executeUpdateQuery("DELETE FROM agenzia WHERE id"
				+ " = " + pk + ";");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Agenzia> readAll() {
		List<Agenzia> agenzie = new ArrayList<Agenzia>();
		ResultSet rs = connection.executeReadQuery("select id from agenzia;");
		try {
			while(rs.next()) 
				agenzie.add(read(Integer.toString(rs.getInt("id"))));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return agenzie;
	}
}
