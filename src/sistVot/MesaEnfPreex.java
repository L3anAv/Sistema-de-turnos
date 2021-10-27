package sistVot;

public class MesaEnfPreex extends Mesa {

	private int[] franjasHorarias;
	private int cupoPorFranja = 1;
	private int cuposTotales;
	private int i = 0;
	private int cupoPorHorario = cupoPorFranja;
	
	public MesaEnfPreex(Presidente presidente, int numero, int[] franjasHorarias) {
		super(presidente, numero);
		this.franjasHorarias = franjasHorarias;
		cuposTotales = this.franjasHorarias.length * cupoPorFranja;
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
		
		int franjaHorariaElegida = 0;
		
		if(cupoPorHorario != 0) {
			cupoPorHorario = cupoPorHorario - 1;
			cuposTotales = cuposTotales - 1;
			franjaHorariaElegida = franjasHorarias[i];
		} else {
			cupoPorHorario = cupoPorFranja;
			i++;
			franjaHorariaElegida = franjasHorarias[i];
		}
		
		return franjaHorariaElegida;
		
	}
	
	
}
