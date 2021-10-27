package sistVot;

public class MesaTrabajadores extends Mesa {

	
	public MesaTrabajadores(Presidente presidente, int numero) {
		super(presidente, numero);
	}

	@Override
	public boolean tieneCupo() {
		return true;
	}

	@Override
	public int darFranjaHorariaDisponible() {
		return 8;
	}

}
