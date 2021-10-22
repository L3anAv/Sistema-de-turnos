package sistVot;

public class Tupla<T1, T2> {
	
	private T1 valor1;
	private T2 valor2;
	
	public Tupla(T1 valor1, T2 valor2) {
		this.valor1 = valor1;
		this.valor2 = valor2;
	}
	
	public T1 getValor1() {
		return valor1;
	}
	
	public T2 getValor2() {
		return valor2;
	}
	
	public void setValor1(T1 valor) {
		this.valor1 = valor;
	}
	
	public void setValor2(T2 valor) {
		this.valor2 = valor;
	}
}
