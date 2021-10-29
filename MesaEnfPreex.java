package sistVot;

public class MesaEnfPreex extends Mesa {

	
	public MesaEnfPreex(Presidente presidente, int numero) {
		super(presidente, numero, "Enfermedades Preexistentes", 20);
	}

	
	@Override
	public boolean aceptaVotante(Votante votante) {


//		System.out.println(votante.toString());

		return (
			!votante.esTrabajador()
			&& votante.tieneEnfermedad() 
		);
	}
	
}
