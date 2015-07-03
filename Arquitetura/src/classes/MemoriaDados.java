package classes;

public class MemoriaDados {
	private byte[] dadosNaMemoria = new byte[4096];
	

	public void escreveNaMemoria(int pos, byte dado) {
		if(pos<0 || pos>4095){
			System.out.println(pos);
			System.out.println("Overflow");
		}
		if(pos>=2048 && pos<3935){
			System.out.println("Memoria inacessivel");
		}else{	
		dadosNaMemoria[pos]=dado;
		}
	}

	public byte getDadosnaMemoria(int pos) {
		if(pos>=2048 && pos<3935){
			System.out.println("Memória inacessivel");
			return 0;
			
		}else{
			return this.dadosNaMemoria[pos];
		}
	}


}
