package classes;

import java.util.Stack;

public class Processador {
	private byte W, STATUS;
	private int pc=0, insatual,args;
	private String function;
	private MemoriaPrograma memProg;
	private MemoriaDados memDad;
	private Stack<EstadoProcessador> pilha = new Stack<EstadoProcessador>();
	
	
	public Processador(MemoriaPrograma memProg, MemoriaDados memDad) {
		this.memProg = memProg;
		this.memDad = memDad;
	}

	public void executaCiclo(){
		buscaInstrucao();
		decodifica();
		atualiza();
	}
	
	private void buscaInstrucao(){
		setInsatual(memProg.lerInstrucao(getPc()));
	}
	
	private void decodifica(){
		int ins=getInsatual() & 0b1111111111111110;
		switch (ins) {
		case 18:
			int x = 0b0000000000000001;
			setArgs(getInsatual()&x);
			executaReturn((byte)getArgs());
			System.out.println(getArgs());
			return;			
		}
		ins=getInsatual() & 0b1111111100000000;
		switch (ins){
			case 3584:
				int x = 0b0000000011111111;
				setArgs(getInsatual()&x);
				executaMovlw((byte)getArgs());
				System.out.println(getArgs());
				return;
			case 61184:
				int x1 = 0b0000000011111111;
				setArgs(getInsatual()&x1);
				executaGoto((byte)getArgs());
				System.out.println(getArgs());
				return;				
		}
		ins=getInsatual() & 0b1111111000000000;


		switch (ins){
			case 28160:
				int x= 0b0000000111111111;
				setArgs(getInsatual()&x);
				executaMovwf((byte)getArgs());
				return;
			case 60416:
				x= 0b0000000111111111;
				setArgs(getInsatual()&x);
				executaCall((byte)getArgs());
				return;				
		}
	}
	
	//MOVLW se 0: se arg <60h os 4 bits são 0, ser arg >60h os 4 bits são 1
	//      se 1: os 4 bits são o BSR
	private void executaMovlw(byte arg){
		setW((byte) getArgs());

	}
	private void executaMovwf(byte arg){
		int y= 0b0000000100000000 & getInsatual();
		if(y==0){
			y= 0b0000000011111111 & getInsatual();
			System.out.println(getInsatual());
			if(y<=0x60){
				memDad.escreveNaMemoria(y, getW());
			}else{
				memDad.escreveNaMemoria(0b111100000000+y, getW());
			}
		}else{
			y=0b0000000011111111 & getInsatual();
			y=((byte)memDad.getDadosnaMemoria(0xFE0)<<8)+y;
			memDad.escreveNaMemoria(y,getW());
		}
	}
	private void executaCall(byte arg){
		int z = memProg.lerInstrucao(getPc()+2);
		z = z & 0b0000111111111111;
		z = z << 8;
		System.out.println("Arg ->" + arg);
		z = ((byte)arg & 011111111) + z;
		System.out.println("O Z-> "+ z);
		if((arg & 0b100000000) == 0){
			pilha.push(new EstadoProcessador(pc+4));
		}else{
			pilha.push(new EstadoProcessador(pc+4, STATUS, W, (byte)memDad.getDadosnaMemoria(0xFE0)));
		}	
		setPc(z);
		
	}
	
	private void executaGoto(byte arg){
		int z = memProg.lerInstrucao(getPc()+2);
		z = z & 0b0000111111111111;
		z = z << 8;
		z = (arg & 011111111) + z;
		setPc(z);
	}
	
	private void executaReturn(byte arg){
		EstadoProcessador temp = pilha.pop();
		if(arg == 1){
			memDad.escreveNaMemoria(0xFE0, temp.getBsr());
			setPc(temp.getPc()-2);
			setW(temp.getW());
			STATUS=temp.getStatus();
		}else{
			setPc(temp.getPc()-2);
		}
	}
	
	private void atualiza(){
		this.setPc(this.getPc()+2);
	}
	
	public int getArgs() {
		return args;
	}

	public void setArgs(int args) {
		this.args = args;
	}


	public byte getW() {
		return W;
	}

	public void setW(byte w) {
		W = w;
	}

	public int getPc() {
		return pc;
	}

	public void setPc(int pc) {
		this.pc = pc;
	}
	public void setFunction(String function) {
		this.function = function;
	}
	
	public String getFunction() {
		return this.function;
	}	
	public int getInsatual() {
		return insatual;
	}

	public void setInsatual(int instual) {
		this.insatual = instual;
	}
	
	public void imprimir(){
		System.out.println("PC -> " + getPc() + " W -> "+getW() + " IA -> " + getInsatual());
	}
}
