package stackOverflow;

import java.util.ArrayList;
import java.util.List;

public class Pregunta extends Publicacion {

	private List<Respuesta> respuestas;
	private String tags;

	public Pregunta(String titulo, String cuerpo, String tag, Usuario autor) {
		super(titulo, cuerpo, autor);
		this.tags = tag;
		this.respuestas = new ArrayList<Respuesta>();
	}

	public void agregarRespuesta(Respuesta respuesta) {
		this.respuestas.add(respuesta);

	}

	public int cantidadDeRespuestas() {
		return this.respuestas.size();
	}

	public Respuesta obtenerRespuesta(Respuesta respuesta) {

		// PRECONDICION: la respuesta existe
		int indice = 0;
		while (indice != this.respuestas.size()
				&& this.respuestas.get(indice).sonIguales(respuesta) == false) {
			indice = indice + 1;
		}
		return this.respuestas.get(indice);
	}

	public String getEtiqueta() {
		return this.tags;
	}

	public List<Respuesta> getRespuestas() {
		return this.respuestas;
	}

	public ArrayList<Respuesta> obtenerRespuestasDelUsuario(Usuario urs) {
		ArrayList<Respuesta> respuestaFiltradas = new ArrayList<Respuesta>();
		for (Respuesta respuesta : this.respuestas) {
			if (respuesta.autor().sonIguales(urs)) {
				respuestaFiltradas.add(respuesta);
			}
		}
		return respuestaFiltradas;
	}

	@Override
	public boolean sonIguales(Publicacion publicacion) {
		// TODO Auto-generated method stub
		Pregunta pregunta = (Pregunta) publicacion;
		return this.getTitle() == publicacion.getTitle()
				&& this.autor() == pregunta.autor()
				&& this.getEtiqueta() == pregunta.getEtiqueta();
	}

	public void eliminarRespuesta(Respuesta respuesta) {
		this.respuestas.remove(this.obtenerRespuesta(respuesta));
	}

	public boolean tieneLaMismaEtiqueta(Pregunta pregunta) {
		String etiquetaMia = this.tags;
		String etiquetaDeLaOtraPregunta = pregunta.tags;
		return etiquetaMia.split(".").equals(
				etiquetaDeLaOtraPregunta.split("."));
	}
}
