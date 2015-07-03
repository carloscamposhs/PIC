package classes;

public class Teste {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MemoriaPrograma a = new MemoriaPrograma();
		MemoriaDados b = new MemoriaDados();
		a.escrever(0,0b0000111000000001 );//MOVLW 1
		a.escrever(2, 60426); //CALL 43530;
		a.escrever(4, 0b1111000010101010);//Resto do endereço
		a.escrever(6,0b0000111000000011);//MOVLW 3
		a.escrever(43530,0b0000111000000010);//MOVLW 
		a.escrever(43532,18);//RETURN
		Processador p = new Processador(a,b);
		for (int i = 0; i < 10; i++) {
			p.executaCiclo();
			p.imprimir();
		}
	
	}

}
//368