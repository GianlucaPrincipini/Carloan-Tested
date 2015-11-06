package business.entity;

public class Modello implements Entity {
	private int id;
	private String marca;
	private String nome;
	private TipoCarburante tipoCarburante;
	private int capacit‡Bagagliaio;
	private int numeroPosti;
	private int numeroPorte;
	private int potenza;
	private boolean trasmissioneAutomatica;
	private double emissioniCO2;
	private int peso;
	private Fascia fascia;
	
	public void setFascia(Fascia fascia) {
		this.fascia = fascia;
	}
	
	public Fascia getFascia() {
		return fascia;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getCapacit‡Bagagliaio() {
		return capacit‡Bagagliaio;
	}

	public void setCapacit‡Bagagliaio(int capacit‡Bagagliaio) {
		this.capacit‡Bagagliaio = capacit‡Bagagliaio;
	}

	public int getNumeroPosti() {
		return numeroPosti;
	}

	public void setNumeroPosti(int numeroPosti) {
		this.numeroPosti = numeroPosti;
	}

	public int getNumeroPorte() {
		return numeroPorte;
	}

	public void setNumeroPorte(int numeroPorte) {
		this.numeroPorte = numeroPorte;
	}

	public int getPotenza() {
		return potenza;
	}

	public void setPotenza(int potenza) {
		this.potenza = potenza;
	}

	public boolean isTrasmissioneAutomatica() {
		return trasmissioneAutomatica;
	}

	public void setTrasmissioneAutomatica(boolean trasmissioneAutomatica) {
		this.trasmissioneAutomatica = trasmissioneAutomatica;
	}

	public double getEmissioniCO2() {
		return emissioniCO2;
	}

	public void setEmissioniCO2(double emissioniCO2) {
		this.emissioniCO2 = emissioniCO2;
	}

	public TipoCarburante getTipoCarburante() {
		return tipoCarburante;
	}

	public void setTipoCarburante(TipoCarburante tipoCarburante) {
		this.tipoCarburante = tipoCarburante;
	}

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public String toString() {
		return id + ", '" + marca + "', '" + nome + "', " + tipoCarburante.getIndex() + ", " + capacit‡Bagagliaio + ", " 
				+ numeroPosti + ", " + numeroPorte + ", " + potenza + 
				", " + trasmissioneAutomatica + ", " + emissioniCO2 + ", " + peso;
	}

	/**
	 * Confronta due contratti
	 */
	@Override
	public int compareTo(Entity o) {
		Modello m = (Modello) o;
		if (id == m.id)
			return 0;
		return 1;
	}
	
}