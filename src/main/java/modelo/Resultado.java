package modelo;

public class Resultado {
	private String local,visitante,fecha;
	private int puntajeLocal, puntajeVisitante;
	
	
	public Resultado(String local, String visitante, String fecha, int puntajeLocal, int puntajeVisitante) {
		super();
		this.local = local;
		this.visitante = visitante;
		this.fecha = fecha;
		this.puntajeLocal = puntajeLocal;
		this.puntajeVisitante = puntajeVisitante;
	}
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	public String getVisitante() {
		return visitante;
	}
	public void setVisitante(String visitante) {
		this.visitante = visitante;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public int getPuntajeLocal() {
		return puntajeLocal;
	}
	public void setPuntajeLocal(int puntajeLocal) {
		this.puntajeLocal = puntajeLocal;
	}
	public int getPuntajeVisitante() {
		return puntajeVisitante;
	}
	public void setPuntajeVisitante(int puntajeVisitante) {
		this.puntajeVisitante = puntajeVisitante;
	}
	
	
	
	
}
