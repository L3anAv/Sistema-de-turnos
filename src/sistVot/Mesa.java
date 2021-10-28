package sistVot;

public abstract class Mesa {
	
	protected int numero;
	private Presidente presidente;
	private int[] franjasHorarias;
	private int maximoVotantesPorFranjaHoraria;
	private int votantesRegistrados;
	private String tipoDeMesa;

	// Constructor  ----------------------------
		
	public Mesa(Presidente presidente, int numero, String tipoDeMesa, int maximoVotantesPorFranjaHoraria) {
		// TODO Auto-generated constructor stub
		this.presidente = presidente;
		this.numero = numero;
		this.franjasHorarias = new int[]{8, 9, 10, 11, 12, 13, 14, 15, 16, 17};
		this.votantesRegistrados = 0;
		this.maximoVotantesPorFranjaHoraria = maximoVotantesPorFranjaHoraria;
		this.tipoDeMesa = tipoDeMesa;
	}

	@Override
	public String toString() {
		return "Mesa para " + tipoDeMesa + " n° " + getNumero();
	}
	
// Metodos Abstrac -----------
	protected abstract boolean aceptaVotante(Votante votante);
	
// Metodos de Mesa  -----------
	public int obtenerHorarioDisponible() {

		// Como la division de enteros trunca el resultado, sirve para obtener la posicion 
		// de franjas horarias libre.
		int arrayPosition = votantesRegistrados / maximoVotantesPorFranjaHoraria;
		if(franjasHorarias.length == arrayPosition) {
			return -1;
		} else 
			return (
				franjasHorarias[
	                votantesRegistrados / maximoVotantesPorFranjaHoraria
	            ]
			);
}

	public void registrarVotante() {
		votantesRegistrados++;
}
	
// Getters and Setters ---------------------
	
	public Presidente getPresidente() {
			return presidente;
}

	public int[] getFranjasHorarias() {
			return franjasHorarias;
}

	public int getNumero(){
			return numero;
}
	
}
