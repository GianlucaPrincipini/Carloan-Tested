package business.entity;

public enum StatoVettura {
	DISPONIBILE(0), MANUTENZIONE(1), OCCUPATA(2);
	private int index;
	
	/**
	 * Costruttore parametrico usato per scegliere lo stato della vettura
	 * @param index
	 */
	private StatoVettura(int index) {
		this.index = index;
	}
	
	public int getIndex() {
		return index;
	}
	
	public static StatoVettura getStato(int index) {
		for (StatoVettura t : StatoVettura.values()) {
			if (t.index == index) {
				return t;
			}
		} throw new IllegalArgumentException();
	}

}