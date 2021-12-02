package sistVot;

public class MesaMayores extends Mesa {
	
	
//--- Constructor.
	
	public MesaMayores(Presidente presidente, int numero) {
		super(presidente, numero, "Personas Mayores", 10);
	}

//--- Metodos (Sobre-escritura)
	
	@Override
	protected boolean aceptaVotante(Votante votante) {
		
		return (
			!votante.esTrabajador()
			&& votante.esMayor()
		);
	}

	@Override
	protected boolean esDeTipo(String tipoMesa) {
		return tipoMesa.equals("Mayor65");
	}
	
	
}
