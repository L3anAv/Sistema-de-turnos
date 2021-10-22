package sistVot;

public class Turno {
	private Mesa mesa;
	private int horarioAsignado;
	private boolean asistioAVotar = false;
	
	public Turno(Mesa mesa, int horario) {
		
		this.mesa = mesa;
		this.horarioAsignado = horario;
		
	}
	
	public void registrarVoto(){
		asistioAVotar = true;
	};
}
