package classes;
//Ailton
public class MemoriaPrograma {
	int cp;
	private byte[] dadosPrograma = new byte[1048575];
	/*int x = 0b00001111;
	int y = 0b01010101;
	int z = x & y;
	int z1 = x|y;
	
	int x1 = 0b00001010;
	int y1 = x<<4;*/
	
	
	public int lerInstrucao(int pos) {
		if(pos>=0 && pos<1048575){
			int p1 = 0xFF & dadosPrograma[pos];
			int p2 = 0xFF & dadosPrograma[pos+1];
			p1 = p1 << 8;
			p2= p1 + p2;
			return p2;
		}else{
			return 0;
		}
	}
	
	public void escrever(int pos, int valor){
		int x = valor & 0xFF00;
		int z=x>>8;
		int y = valor & 0x00FF;
		
		dadosPrograma[pos]=(byte) z;
		dadosPrograma[pos+1]=(byte) y;
	}
	

}