package business.entity;

public class Vettura implements Entity {
	private String targa;
	private Modello modello;
	private Agenzia agenziaLocalizzazione;
	private int chilometraggio;
	private StatoVettura stato;

	public String getTarga() {
		return targa;
	}

	public void setTarga(String targa) {
		this.targa = targa;
	}

	public Modello getModello() {
		return modello;
	}

	public void setModello(Modello modello) {
		this.modello = modello;
	}

	public Agenzia getAgenziaLocalizzazione() {
		return agenziaLocalizzazione;
	}

	public void setAgenziaLocalizzazione(Agenzia agenziaLocalizzazione) {
		this.agenziaLocalizzazione = agenziaLocalizzazione;
	}

	public int getChilometraggio() {
		return chilometraggio;
	}

	public void setChilometraggio(int chilometraggio) {
		this.chilometraggio = chilometraggio;
	}

	public StatoVettura getStato() {
		return stato;
	}

	public void setStato(StatoVettura stato) {
		this.stato = stato;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "'" + targa + "', " + modello.getId() + ", " + agenziaLocalizzazione.getId() + ", " + chilometraggio + ", " + stato.getIndex();
	}

	/**
	 * Confronta due vetture
	 */
	@Override
	public int compareTo(Entity o) {
		if (targa.equals(((Vettura)o).targa))
			return 0;
		return 1;
	}
}