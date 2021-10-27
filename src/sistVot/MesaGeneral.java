package sistVot;

public class MesaGeneral extends Mesa {

	private int[] franjasHorarias;
	private int cupoPorFranja = 30;
	private int cuposTotales = franjasHorarias.length * cupoPorFranja;
	
	public MesaGeneral(Presidente presidente, int numero, int[] franjasHorarias) {
		super(presidente, numero);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean tieneCupo() {
		if(cuposTotales > 0)
			return true;
		else
			return false;
	}

	@Override
	public int darFranjaHorariaDisponible() {
		// TODO Esbozo de método generado automáticamente
		return 0;
	}

}
