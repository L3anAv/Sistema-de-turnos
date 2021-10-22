package sistVot;

import java.util.ArrayList;

public abstract class Mesa {
	private int numDeMesa;
	private Presidente presidenteDeMesa;
	private int[] franjasHorarias;
	
	public Mesa(int numDeMesa, Presidente presidenteDeMesa){
		this.numDeMesa = numDeMesa;
		this.presidenteDeMesa = presidenteDeMesa;
	}
	
	public abstract void tieneCupo();
}
