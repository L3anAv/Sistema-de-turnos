package sistVot;

public class Turno {
	private Mesa mesa;
	private int horarioAsignado;
	private boolean asistioAVotar = false;
	
// Constructor  -----------------------------
	
	public Turno(Mesa mesa, int horario) {
		
		this.mesa = mesa;
		this.horarioAsignado = 0;
		
	}
	
// Metodos de la clase ----------------------
	
	public void registrarVoto(){
		asistioAVotar = true;
	};
	
//Metodos equals, toString, etc.
	
	
//	public String toString() {
//		//String horarioAsignado = horarioAsignado+"";
//		return horarioAsignado;
//		
//	}
	
	
// Getters and Setters ------------------------
	
	public boolean getAsistioAVotar() {
		return asistioAVotar;
	}
	
	public int getHorarioAsginado() {
		return horarioAsignado;
	}
	
	public Mesa getMesaAsginado() {
		return mesa;
	}
}
