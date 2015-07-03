package classes;

public class EstadoProcessador {
	private int pc;
	private byte status, w, bsr;
	
	
	
	public EstadoProcessador(int pc, byte status, byte w, byte bsr) {
		this.pc = pc;
		this.status = status;
		this.w = w;
		this.bsr = bsr;
	}

	public EstadoProcessador(int pc) {
		this.pc = pc;
	}
	
	
	public int getPc() {
		return pc;
	}
	public void setPc(int pc) {
		this.pc = pc;
	}
	public byte getStatus() {
		return status;
	}
	public void setStatus(byte status) {
		this.status = status;
	}
	public byte getW() {
		return w;
	}
	public void setW(byte w) {
		this.w = w;
	}
	public byte getBsr() {
		return bsr;
	}
	public void setBsr(byte bsr) {
		this.bsr = bsr;
	}
	
	
}
