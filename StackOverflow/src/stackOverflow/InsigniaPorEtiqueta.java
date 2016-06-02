package stackOverflow;

import java.util.ArrayList;

public class InsigniaPorEtiqueta extends Insignia {

	private int cantidadDeVotos;

	public InsigniaPorEtiqueta(String nombre, int cantidadDePreguntas) {
		super(nombre, cantidadDePreguntas, 180);
		this.setCantidadDeVotos(0);
	}

	public InsigniaPorEtiqueta(String nombre, int cantidadDePreguntas,
			int cantidadDeDias) {
		super(nombre, cantidadDePreguntas, cantidadDeDias);
		this.setCantidadDeVotos(0);
	}

	public InsigniaPorEtiqueta(String nombre, int cantidadDePreguntas,
			int cantidadDeDias, int cantidadDeVotos) {
		super(nombre, cantidadDePreguntas, cantidadDeDias);

		this.setCantidadDeVotos(cantidadDeVotos);
	}

	@Override
	public boolean cumpleCondicion(Usuario usuario, Sistema sist) {
		boolean cumple = false;
		ArrayList<Pregunta> preguntas = this.obtenerPreguntasDelAutor(sist,
				usuario);
		for (Pregunta preg : preguntas) {
			cumple = cumple || cumpleCondicionDeEtiquetas(preg, preguntas);
		}
		return cumple;
	}

	public ArrayList<Pregunta> obtenerPreguntasDelAutor(Sistema sist,
			Usuario usr) {
		ArrayList<Pregunta> preguntasResult;
		preguntasResult = new ArrayList<Pregunta>();
		for (Pregunta pregunta : sist.preguntas()) {
			if (pregunta.autor().sonIguales(usr)) {
				preguntasResult.add(pregunta);
			}
		}
		return preguntasResult;
	}

	public boolean cumpleCondicionDeEtiquetas(Pregunta pregunta,
			ArrayList<Pregunta> preguntas) {
		int count = 0;
		for (Pregunta preg : preguntas) {
			if (pregunta.tieneLaMismaEtiqueta(preg)
					&& super.cumpleCondicionDeFecha(pregunta, preguntas)) {
				count = count + 1;
			}
		}
		return count >= this.cantidadDePublicaciones;
	}

	public int getCantidadDeVotos() {
		return cantidadDeVotos;
	}

	public void setCantidadDeVotos(int cantidadDeVotos) {
		this.cantidadDeVotos = cantidadDeVotos;
	}

}
