package integration.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.DateHelper;
import business.entity.Cliente;

public class DAOCliente extends DAOCarloan<Cliente>{

	/**
	 * {@inheritDoc}
	 */
	@Override
	public  void create(Cliente entity){
		ResultSet rs = connection.executeReadQuery("SELECT AUTO_INCREMENT FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = "
				+ "'carloan' AND   TABLE_NAME = 'persona'");
		try {
			while(rs.next())
				entity.setId(rs.getInt(1));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		connection.executeUpdateQuery("insert into persona(nome, cognome, datanascita, numtelefono, email)"
									+ " values(" 
									+ "'" + entity.getNome() + "', "
									+ "'" + entity.getCognome() + "', "
									+ "'" + entity.getDataNascita() + "', "
									+ "'" + entity.getNumTelefono() + "', "
									+ "'" + entity.getEMail() + "'"
									+ ");");
		
		connection.executeUpdateQuery("INSERT INTO cliente(id, codicePatente) values("
									+ entity.getId() + ", "
									+ "'" + entity.getCodicePatente()
									+ "');");
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(Cliente entity){
		connection.executeUpdateQuery("update persona set " +
									  "nome = '" + entity.getNome() + "', " +
									  "cognome = '" + entity.getCognome() + "', " +
									  "dataNascita = '" + entity.getDataNascita() + "', " +
									  "numtelefono = '" + entity.getNumTelefono() + "', " +
									  "email = '" + entity.getEMail() + "' where id = "+ entity.getId() + ";");
		connection.executeUpdateQuery("update cliente set codicepatente = '" + entity.getCodicePatente() + "' where id = " + entity.getId());
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Cliente read(String pk){
		Cliente cliente = null;
		ResultSet rs = connection.executeReadQuery("select * from cliente where codicepatente = '" + pk + "';");
		try {
			cliente = new Cliente();
			while(rs.next()){
				cliente.setCodicePatente(pk);
				ResultSet anagrafica = connection.executeReadQuery("select * from persona where id = " + rs.getInt(1) + ";");
				while(anagrafica.next()) {
					cliente.setId(anagrafica.getInt("id"));
					cliente.setNome(anagrafica.getString("nome"));
					cliente.setCognome(anagrafica.getString("cognome"));
					cliente.setDataNascita(DateHelper.dateToLocalDate(anagrafica.getDate("datanascita")));
					cliente.setNumTelefono(anagrafica.getString("numtelefono"));
					cliente.setEMail(anagrafica.getString("email"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
 		return cliente;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(String pk){
		ResultSet rs = connection.executeReadQuery("select id from cliente where codicepatente = '" + pk + "';");
		try {
			while(rs.next()) {
				int id = rs.getInt("id");
				connection.executeUpdateQuery("delete from persona where id = " + id + ";");
			}
		} catch (SQLException e) {

		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Cliente> readAll() {
		List<Cliente> clienti = new ArrayList<Cliente>();
		ResultSet rs = connection.executeReadQuery("select codicepatente from cliente;");
		try {
			while(rs.next()) clienti.add(read(rs.getString("codicepatente")));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return clienti;
	}
	
}
