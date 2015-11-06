package business.applicationservice;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import business.entity.Tariffario;

/**
 * Classe che aggiorna il file tariffario.dat, non il database
 * @author Stefano
 *
 */
public class ApplicationServiceTariffario implements ApplicationService {
	
	/**
	 * Aggiorna il file contenente le informazioni sulla tariffa
	 * @param tariffario
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void update(Tariffario tariffario) throws FileNotFoundException, IOException {
		ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("./tariffario/tariffario.dat"));
		output.writeObject(tariffario);
		output.close();
	}
	
}
