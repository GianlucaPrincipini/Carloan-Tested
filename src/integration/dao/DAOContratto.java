package integration.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import utils.DateHelper;
import business.entity.Contratto;
import business.entity.Optional;
import business.entity.Rifornimento;

public class DAOContratto extends DAOCarloan<Contratto>{
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void create(Contratto entity){
		ResultSet rs = connection.executeReadQuery("SELECT AUTO_INCREMENT FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = "
				+ "'carloan' AND   TABLE_NAME = 'contratto'");
		try {
			while(rs.next())
				entity.setId(rs.getInt(1));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		connection.executeUpdateQuery("INSERT INTO contratto(operatore, cliente, vettura, agenzianoleggio, agenziaconsegna, datastipula, "
									+ "datainizionoleggio, datafinenoleggio, chilometraggiolimitato, chilometriprevisti, rifornimento, acconto, "
									+ "costo, assicurazioneavanzata) values(" 
									+ "'" + entity.getOperatore().getUsername() + "', "
									+ "'" + entity.getCliente().getCodicePatente() + "', "
									+ "'" + entity.getVettura().getTarga() + "', "
									+ entity.getAgenziaNoleggio().getId() + ", "
									+ entity.getAgenziaConsegna().getId() + ", "
									+ "'" + entity.getDataStipula() + "', "
									+ "'" + entity.getDataInizioNoleggio() + "', "
									+ "'" + entity.getDataFineNoleggio() + "', "
									+ entity.isChilometraggioLimitato() + ", "
									+ entity.getChilometriPrevisti() + ", " 
									+ entity.getRifornimento().getIndex() +", "
									+ entity.getAcconto() + ", "
									+ entity.getCosto() + ", "
									+ entity.isAssicurazioneAvanzata()
									+ ");");
		for (Optional o : entity.getOptionals()) {
			connection.executeUpdateQuery("insert into optional_contratto values (" + entity.getId() + ", " + o.getId() + ");");
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(Contratto entity){
		connection.executeUpdateQuery("UPDATE contratto SET " +
				  "Operatore = '" + entity.getOperatore().getUsername() + "', " +
				  "Cliente = '" + entity.getCliente().getCodicePatente() + "', " + 
				  "Vettura = '" + entity.getVettura().getTarga() + "', " +
				  "AgenziaNoleggio = " + entity.getAgenziaNoleggio().getId() + ", " +
				  "AgenziaConsegna = " + entity.getAgenziaConsegna().getId() + ", " +
				  "DataStipula = '" + entity.getDataStipula() + "', " +
				  "DataInizioNoleggio = '" + entity.getDataInizioNoleggio() + "', " +
				  "ChilometraggioLimitato = " + entity.isChilometraggioLimitato() + ", " +
				  "ChilometriPrevisti = " + entity.getChilometriPrevisti() + ", " +
				  "Rifornimento = " + entity.getRifornimento().getIndex() + ", " +
				  "Acconto = " + entity.getAcconto() + ", " +
				  "Chiuso = " + entity.isChiuso() + ", " +
				  "Costo = " + entity.getCosto() + ", " +
				  "AssicurazioneAvanzata = " + entity.isAssicurazioneAvanzata() + " " +
				  "WHERE id = " + entity.getId() + "; ");
		connection.executeUpdateQuery("delete from optional_contratto where idcontratto = " + entity.getId() + ";");
		for (Optional o : entity.getOptionals()) {
			connection.executeUpdateQuery("insert into optional_contratto values (" + entity.getId() + ", " + o.getId() + ");");
		}
		if (entity.getDataChiusura() != null) {
			connection.executeUpdateQuery("update contratto set datachiusura = '" + entity.getDataChiusura() + "', chilometriPercorsi = "
					+ entity.getChilometriPercorsi() + " where id = " + entity.getId() + ";");
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Contratto read(String pk){
		Contratto contratto = null;
		DAOOperatore operatore = null;
		DAOCliente cliente = null;
		DAOAgenzia agenziaNoleggio = null;
		DAOAgenzia agenziaConsegna = null;
		
		try {
			contratto = new Contratto();
			operatore = new DAOOperatore();
			cliente = new DAOCliente();
			agenziaNoleggio = new DAOAgenzia();
			agenziaConsegna = new DAOAgenzia();
			ResultSet rs = connection.executeReadQuery("select * from contratto where id = "+ pk + ";");
			while(rs.next()) {
				contratto.setId(rs.getInt("id"));
				contratto.setOperatore(operatore.read(rs.getString("operatore")));
				contratto.setCliente(cliente.read(rs.getString("cliente")));
				contratto.setAgenziaNoleggio(agenziaNoleggio.read(Integer.toString(rs.getInt("agenziaNoleggio"))));
				contratto.setAgenziaConsegna(agenziaConsegna.read(Integer.toString(rs.getInt("agenziaconsegna"))));
				contratto.setDataStipula(DateHelper.dateToLocalDate(rs.getDate("datastipula")));
				contratto.setDataChiusura(DateHelper.dateToLocalDate(rs.getDate("datachiusura")));
				contratto.setDataInizioNoleggio(DateHelper.dateToLocalDate(rs.getDate("datainizionoleggio")));
				contratto.setDataFineNoleggio(DateHelper.dateToLocalDate(rs.getDate("dataFineNoleggio")));
				contratto.setChilometraggioLimitato(rs.getBoolean("chilometraggiolimitato"));
				contratto.setChilometriPrevisti(rs.getInt("chilometriprevisti"));
				contratto.setChilometriPercorsi(rs.getInt("chilometripercorsi"));
				contratto.setRifornimento(Rifornimento.getRifornimento(rs.getInt("rifornimento")));
				contratto.setAcconto(rs.getFloat("acconto"));
				contratto.setChiuso(rs.getBoolean("chiuso"));
				contratto.setCosto(rs.getFloat("costo"));
				contratto.setVettura(new DAOVettura().read(rs.getString("vettura")));
				contratto.setAssicurazioneAvanzata(rs.getBoolean("assicurazioneAvanzata"));
				ResultSet rs_optional = connection.executeReadQuery("SELECT idOptional from optional_contratto where idContratto = " + rs.getInt(1) + ";");
				List<Optional> optionals = new ArrayList<Optional>(); 
				while(rs_optional.next()) {
					optionals.add(new DAOOptional().read(rs_optional.getString(1)));
				}
				contratto.setOptionals(optionals);
			}
		} catch (Exception e) {
			
		}
		return contratto;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(String pk){
		connection.executeUpdateQuery("DELETE FROM contratto WHERE id = " + pk + ";");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Contratto> readAll() {
		List<Contratto> contratti = new ArrayList<Contratto>();
		ResultSet rs = connection.executeReadQuery("select id from contratto;");
		try {
			while(rs.next()) contratti.add(read(Integer.toString(rs.getInt("id"))));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return contratti;
	}
}
