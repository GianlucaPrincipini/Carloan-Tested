package business.entity;

public class Fascia implements Entity {
	private int id;
	private String nome;
	private double tariffaBase;
	private double min;
	private double max;

	public double getTariffaBase() {
		return tariffaBase;
	}
	
	public void setTariffaBase(double tariffaBase) {
		this.tariffaBase = tariffaBase;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getMin() {
		return min;
	}

	public void setMin(double min) {
		this.min = min;
	}

	public double getMax() {
		return max;
	}

	public void setMax(double max) {
		this.max = max;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public String toString() {
		return id + ", '" + nome + "', " + tariffaBase + ", " + min + ", " + max;
	}

	/**
	 * Confronta due fasce
	 */
	@Override
	public int compareTo(Entity o) {
		Fascia f = (Fascia) o;
		if (id == f.getId() && nome.equals(f.nome))
			return 0;
		else return 1;
	}
}