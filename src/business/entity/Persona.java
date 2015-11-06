package business.entity;

import org.joda.time.LocalDate;

public abstract class Persona implements Entity {
	protected int id;
	protected String nome;
	protected String cognome;
	protected LocalDate dataNascita;
	protected String numTelefono;
	protected String eMail;

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

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public LocalDate getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(LocalDate dataNascita) {
		this.dataNascita = dataNascita;
	}

	public String getNumTelefono() {
		return numTelefono;
	}

	public void setNumTelefono(String numTelefono) {
		this.numTelefono = numTelefono;
	}

	public String getEMail() {
		return eMail;
	}

	public void setEMail(String eMail) {
		this.eMail = eMail;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public String toString() {
		return id + ", '" + nome + "', '" + cognome + "', '" + dataNascita + "', '" + numTelefono + "', '" + eMail + "'";
	}

	/**
	 * Confronta due Persone
	 */
	@Override
	public int compareTo(Entity arg0) {
		Persona p = (Persona) arg0;
		if (id == p.id && dataNascita.equals(p.dataNascita) && nome.equals(p.nome) && cognome.equals(p.cognome) &&
			numTelefono.equals(p.numTelefono) && eMail.equals(p.eMail))
			return 0;
		else 
			return 1;
	}
	
}