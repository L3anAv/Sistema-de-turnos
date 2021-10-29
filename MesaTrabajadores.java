package sistVot;

public class MesaTrabajadores extends Mesa {

	public MesaTrabajadores(Presidente presidente, int numero) {
		super(presidente, numero, "Trabajadores", 0);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected boolean aceptaVotante(Votante votante) {
		return votante.esTrabajador();
	}
	
	@Override
	public int obtenerHorarioDisponible() {
		return getFranjasHorarias()[0];
	}
	

}
