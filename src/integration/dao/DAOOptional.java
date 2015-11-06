package integration.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import business.entity.Optional;

public class DAOOptional extends DAOCarloan<Optional>{
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional read(String pk){
		Optional optional = null;
		ResultSet rs = connection.executeReadQuery("SELECT * FROM optional WHERE id = " + pk + ";");
		try {
			optional = new Optional();
			while(rs.next()) {
				optional.setId(rs.getInt(1));
				optional.setTipo(rs.getString(2));
				optional.setCosto(rs.getDouble(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return optional;
	}
	
	public void delete(String pk){
		connection.executeUpdateQuery("delete from optional where id = " + pk + ";");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void create(Optional entity) {
		connection.executeUpdateQuery("INSERT INTO optional(tipo, costo) values(" 
									+ "'" + entity.getTipo() + "', "
									+ entity.getCosto()
									+ ");");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(Optional entity) {
		connection.executeUpdateQuery("update optional set " +
									  "tipo = '" + entity.getTipo() + "', " +
									  "costo = " + entity.getCosto() + "where id = '" + entity.getId() + "';");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Optional> readAll() {
		List<Optional> optional = new ArrayList<Optional>();
		ResultSet rs = connection.executeReadQuery("select id from optional;");
		try {
			while(rs.next()) optional.add(read(Integer.toString(rs.getInt("id"))));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return optional;
	}

}
