package sistVot;

public class MesaMayores extends Mesa {
	
	
	public MesaMayores(Presidente presidente, int numero) {
		super(presidente, numero, "Personas Mayores", 10);
	}

	@Override
	protected boolean aceptaVotante(Votante votante) {
		
		return (
			!votante.esTrabajador()
			&& !votante.tieneEnfermedad()
		);
	}
	
	
}
