package business.applicationservice;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import business.entity.IncidenzaFascia;

/**
 * Apporta le modifiche al file contenente i parametri delle incidenze di fascia
 * @author Stefano
 */
public class ApplicationServiceIncidenza {
	
	/**
	 * Aggiorna il file che contiene le informazioni sull'incidenza delle fasce
	 * @param incidenza
	 * @throws IOException
	 */
	public void update(IncidenzaFascia incidenza) throws IOException {
		ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("./incidenza/incidenza.dat"));
		output.writeObject(incidenza);
		output.close();
	}
}
