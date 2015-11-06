package integration.dao;



import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import business.entity.Modello;
import business.entity.TipoCarburante;

public class DAOModello extends DAOCarloan<Modello> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void create(Modello entity) {
		connection.executeUpdateQuery("INSERT INTO modello(marca, nome, tipoCarburante, capacitabagagliaio, numeroposti, "
									+ "numeroporte, potenza, trasmissioneautomatica, emissionico2, peso, fascia)"
									+ " values(" 
									+ "'" + entity.getMarca() + "', "
									+ "'" + entity.getNome() + "', "
									+ entity.getTipoCarburante().getIndex() + ", "
									+ entity.getCapacit‡Bagagliaio() + ", "
									+ entity.getNumeroPosti() + ", "
									+ entity.getNumeroPorte() + ", "
									+ entity.getPotenza() + ", "
									+ entity.isTrasmissioneAutomatica() + ", "
									+ entity.getEmissioniCO2() + ", "
									+ entity.getPeso() + ", "
									+ entity.getFascia().getId()
									+ ");");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(Modello entity) {
		connection.executeUpdateQuery("UPDATE MODELLO SET id = " + entity.getId() + ", " +
									  "Marca = '" + entity.getMarca() + "', " +
									  "Nome = '" + entity.getNome() + "', " + 
									  "TipoCarburante = " + entity.getTipoCarburante().getIndex() + ", " +
									  "CapacitaBagagliaio = " + entity.getCapacit‡Bagagliaio() + ", " +
									  "NumeroPosti = " + entity.getNumeroPosti() + ", " +
									  "NumeroPorte = " + entity.getNumeroPorte() + ", " +
									  "Potenza = " + entity.getPotenza() + ", " +
									  "TrasmissioneAutomatica = " + entity.isTrasmissioneAutomatica() + ", " +
									  "Peso = " + entity.getPeso() + 
									  " WHERE id = " + entity.getId() + "; ");
		if (entity.getFascia() != null) {
			connection.executeUpdateQuery("update modello set fascia = " + entity.getFascia().getId() + " where id = " + entity.getId() +";");
		}
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Modello read(String pk) {
		Modello modello = null;
		try {
			modello = new Modello();
			ResultSet rs = connection.executeReadQuery(" SELECT * FROM MODELLO WHERE id = " + pk + ";");
			while(rs.next()) {
				modello.setId(rs.getInt(1));
				modello.setMarca(rs.getString(2));
				modello.setNome(rs.getString(3));
				modello.setTipoCarburante(TipoCarburante.getTipo(rs.getInt(4)));
				modello.setCapacit‡Bagagliaio(rs.getInt(5));
				modello.setNumeroPosti(rs.getInt(6));
				modello.setNumeroPorte(rs.getInt(7));
				modello.setPotenza(rs.getInt(8));
				modello.setTrasmissioneAutomatica(rs.getBoolean(9));
				modello.setEmissioniCO2(rs.getFloat(10));
				modello.setPeso(rs.getInt(11));
				modello.setFascia(new DAOFascia().read(Integer.toString(rs.getInt("fascia"))));
			}
		} catch (Exception e) {
			
		}
		return modello;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(String pk) {
		connection.executeUpdateQuery("DELETE FROM modello WHERE id = " + pk + ";");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Modello> readAll() {
		List<Modello> modelli = new ArrayList<Modello>();
		ResultSet rs = connection.executeReadQuery("select id from modello;");
		try {
			while(rs.next()) modelli.add(read(Integer.toString(rs.getInt("id"))));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return modelli;
	}
	
}
