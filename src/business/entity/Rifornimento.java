package business.entity;

public enum Rifornimento {
	
	STANDARD(0), PIENO_ANTICIPO(1);
	
	private int index;
	
	/**
	 * Costruttore parametrico che imposta il tipo di rifornimento selezionato
	 * @param index
	 */
	private Rifornimento(int index) {
		this.index = index;
	}
	
	public int getIndex() {
		return index;
	}
	
	public static Rifornimento getRifornimento(int index) {
		for (Rifornimento t : Rifornimento.values()) {
			if (t.index == index) {
				return t;
			}
		} throw new IllegalArgumentException();
	}

	
}