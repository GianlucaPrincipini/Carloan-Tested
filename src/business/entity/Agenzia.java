package business.entity;

public class Agenzia implements Entity {
	
	private int id;
	
	private String indirizzo;
	
	private String città;
	
	private String numTelefono;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getCittà() {
		return città;
	}

	public void setCittà(String città) {
		this.città = città;
	}

	public String getNumTelefono() {
		return numTelefono;
	}

	public void setNumTelefono(String numTelefono) {
		this.numTelefono =numTelefono;
	}
	
	public String toString() {
		return id + ", '" + indirizzo + "', '" + città + "', '" + numTelefono + "'";
	}

	/**
	 * Confronta tra loro due agenzie.
	 */
	@Override
	public int compareTo(Entity arg0) {
		Agenzia oAgenzia = (Agenzia) arg0;
		if (id == oAgenzia.id && indirizzo.equals(oAgenzia.indirizzo) && città.equals(oAgenzia.città) && numTelefono.equals(oAgenzia.numTelefono))
			return 0;
		else 
			return 1;
	}

}