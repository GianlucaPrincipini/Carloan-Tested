package business.entity;

public class Agenzia implements Entity {
	
	private int id;
	
	private String indirizzo;
	
	private String citt�;
	
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

	public String getCitt�() {
		return citt�;
	}

	public void setCitt�(String citt�) {
		this.citt� = citt�;
	}

	public String getNumTelefono() {
		return numTelefono;
	}

	public void setNumTelefono(String numTelefono) {
		this.numTelefono =numTelefono;
	}
	
	public String toString() {
		return id + ", '" + indirizzo + "', '" + citt� + "', '" + numTelefono + "'";
	}

	/**
	 * Confronta tra loro due agenzie.
	 */
	@Override
	public int compareTo(Entity arg0) {
		Agenzia oAgenzia = (Agenzia) arg0;
		if (id == oAgenzia.id && indirizzo.equals(oAgenzia.indirizzo) && citt�.equals(oAgenzia.citt�) && numTelefono.equals(oAgenzia.numTelefono))
			return 0;
		else 
			return 1;
	}

}