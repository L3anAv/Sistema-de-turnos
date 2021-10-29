package sistVot;

public class MesaGeneral extends Mesa {


	public MesaGeneral(Presidente presidente, int numero) {
		super(presidente, numero, "General", 30);
	}

	@Override
	protected boolean aceptaVotante(Votante votante) {

		return (
			!votante.esTrabajador()
			&& !votante.esMayor()
			&& !votante.tieneEnfermedad()
		);
	}

}
