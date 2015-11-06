package business.entity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

@SuppressWarnings({ "serial", "unused" })
public class Tariffario implements Serializable {
	private double costoGiornaliero;
	private double costoSettimanale;
	private double moraChilometraggio;
	private double moraDurata;
	private double costoChilometrico;
	private double costoChilometraggioIllimitato;
	private double assicurazioneBase;
	private double assicurazioneAvanzata;
	private Map<TipoCarburante, Double> costoLitro = new HashMap<TipoCarburante, Double>();
	private Map<Rifornimento, Double> rifornimento = new HashMap<Rifornimento, Double>();
	private static Tariffario tariffario;

	/**
	 * Costruttore privato della classe singleton
	 */
	private Tariffario() {
		for (TipoCarburante t:TipoCarburante.values()) {
			costoLitro.put(t, 0.0);
		}
		for (Rifornimento r:Rifornimento.values()) {
			rifornimento.put(r, 0.0);
		}
	}
	
	/**
	 * Carica le informazioni del tariffario da file se ci sono, altrimenti crea un nuovo file con valori settati in default
	 */
	private static void load() {
		File file = new File("./tariffario/");
		if (!file.canRead()) {
			file.mkdirs();
		}
		try {
			ObjectInputStream input = new ObjectInputStream(new FileInputStream("./tariffario/tariffario.dat"));
			tariffario = (Tariffario) input.readObject();
			input.close();
		} catch (IOException e) {
			try {
				ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("./tariffario/tariffario.dat"));
				output.writeObject(tariffario);
				output.close();
			} catch (IOException e1) {
				e.printStackTrace();
			} 
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void setCostoLitro(TipoCarburante carburante, Double costo) {
		this.costoLitro.put(carburante, costo);
	}
	
	public double getCostoLitro(TipoCarburante carburante) {
		return this.costoLitro.get(carburante);
	}
	
	public void setRifornimento(Rifornimento rifornimento, Double costo) {
		this.rifornimento.put(rifornimento, costo);
	}
	
	public double getRifornimento(Rifornimento rifornimento) {
		return this.rifornimento.get(rifornimento);
	}
	
	public void setCostoChilometraggioIllimitato(double costoChilometraggioIllimitato) {
		this.costoChilometraggioIllimitato = costoChilometraggioIllimitato;
	}
	
	public double getCostoChilometraggioIllimitato() {
		return costoChilometraggioIllimitato;
	}
	
	/**
	 * @return l'istanza di {@link Tariffario}
	 */
	public static Tariffario getInstance() {
		if (tariffario==null) 
			tariffario = new Tariffario();
		load();
		return tariffario;
		
	}

	public double getCostoGiornaliero() {
		return costoGiornaliero;
	}

	public void setCostoGiornaliero(double costoGiornaliero) {
		this.costoGiornaliero = costoGiornaliero;
	}

	public double getCostoSettimanale() {
		return costoSettimanale;
	}

	public void setCostoSettimanale(double costoSettimanale) {
		this.costoSettimanale = costoSettimanale;
	}

	public double getMoraChilometraggio() {
		return moraChilometraggio;
	}

	public void setMoraChilometraggio(double moraChilometraggio) {
		this.moraChilometraggio = moraChilometraggio;
	}

	public double getMoraDurata() {
		return moraDurata;
	}

	public void setMoraDurata(double moraDurata) {
		this.moraDurata = moraDurata;
	}


	public double getCostoChilometrico() {
		return costoChilometrico;
	}

	public void setCostoChilometrico(double costoChilometrico) {
		this.costoChilometrico = costoChilometrico;
	}

	public double getAssicurazioneBase() {
		return assicurazioneBase;
	}
	
	public void setAssicurazioneBase(double assicurazioneBase) {
		this.assicurazioneBase = assicurazioneBase;
	}

	public double getAssicurazioneAvanzata() {
		return assicurazioneAvanzata;
	}

	public void setAssicurazioneAvanzata(double assicurazioneAvanzata) {
		this.assicurazioneAvanzata = assicurazioneAvanzata;
	}
	
}