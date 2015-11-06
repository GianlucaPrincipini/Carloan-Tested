package integration.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.DateHelper;
import business.entity.Amministratore;
import business.entity.Operatore;

public class DAOOperatore extends DAOCarloan<Operatore> {
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public  void create(Operatore entity){
		ResultSet rs = connection.executeReadQuery("SELECT AUTO_INCREMENT FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = "
				+ "'carloan' AND   TABLE_NAME = 'persona'");
		try {
			while(rs.next())
				entity.setId(rs.getInt(1));
		} catch (SQLException e) {
			//e.printStackTrace();
		}
		connection.executeUpdateQuery("insert into persona(nome, cognome, datanascita, numtelefono, email)"
									+ " values(" 
									+ "'" + entity.getNome() + "', "
									+ "'" + entity.getCognome() + "', "
									+ "'" + entity.getDataNascita() + "', "
									+ "'" + entity.getNumTelefono() + "', "
									+ "'" + entity.getEMail() + "'"
									+ ");");
		connection.executeUpdateQuery("insert into profilo(id, username, password) "
									+ "values( " 
									+ entity.getId() + ", " 
									+ "'" + entity.getUsername() + "', " 
									+ "'" + entity.getPassword() 
									+ "');");
		if (entity instanceof Amministratore)
			connection.executeUpdateQuery("INSERT INTO Operatore values('" + entity.getUsername() + "', " + entity.getAgenzia().getId() + ", true);");
		else 
			connection.executeUpdateQuery("INSERT INTO Operatore values('" + entity.getUsername() + "', " + entity.getAgenzia().getId() + ", false);");
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(Operatore entity){
		try {
			connection.executeUpdateQuery("update persona set " +
										  "nome = '" + entity.getNome() + "', " +
										  "cognome = '" + entity.getCognome() + "', " +
										  "dataNascita = '" + entity.getDataNascita() + "', " +
										  "numtelefono = '" + entity.getNumTelefono() + "', " +
										  "email = '" + entity.getEMail() + "' where id = "+ entity.getId() + ";");

			connection.executeUpdateQuery("update profilo set " +
										  "id = " + entity.getId()+ ", " +
										  "password = '" + entity.getPassword() + "' where username = '"+ entity.getUsername() + "';");
			if (entity instanceof Amministratore)
				connection.executeUpdateQuery("update operatore set agenzia = " + entity.getAgenzia().getId() + ", amministratore = 1 where username = '" + entity.getUsername() + "';");
			else 
				connection.executeUpdateQuery("update operatore set agenzia = " + entity.getAgenzia().getId() + ", amministratore = 0 where username = '" + entity.getUsername() + "';");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Operatore read(String pk){
		Operatore operatore= null;
		ResultSet rs = connection.executeReadQuery("select * from operatore inner join profilo on operatore.username = profilo.username where operatore.username = '" + pk + "';");
		System.out.println(rs);
		try {
			while(rs.next()){
				if (rs.getInt("amministratore") == 1)
					operatore = new Amministratore();
				else 
					operatore = new Operatore();
				
				operatore.setUsername(rs.getString("username"));
				operatore.setPassword(rs.getString("password"));
				operatore.setAgenzia(new DAOAgenzia().read(rs.getString("agenzia")));
				ResultSet anagrafica = connection.executeReadQuery("select * from persona where id = " + rs.getInt("id") + ";");
				while(anagrafica.next()) {
					operatore.setNome(anagrafica.getString("nome"));
					operatore.setCognome(anagrafica.getString("cognome"));
					operatore.setDataNascita(DateHelper.dateToLocalDate(anagrafica.getDate("datanascita")));
					operatore.setNumTelefono(anagrafica.getString("numtelefono"));
					operatore.setEMail(anagrafica.getString("email"));
					operatore.setId(anagrafica.getInt("id"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
 		return operatore;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(String pk){
		
		//connection.executeUpdateQuery("DELETE FROM operatore WHERE username = '" + pk + "';");
		ResultSet tmp = connection.executeReadQuery("select id from profilo where username = '" + pk + "';");
		try {
			while(tmp.next()) {
				connection.executeUpdateQuery("delete from persona where id = " + tmp.getInt("id") + ";");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Operatore> readAll() {
		List<Operatore> operatori = new ArrayList<Operatore>();
		ResultSet rs = connection.executeReadQuery("select username from operatore;");
		try {
			while(rs.next()) operatori.add(read(rs.getString("username")));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return operatori;
	}
	
}
