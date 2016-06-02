package stackOverflow;

import java.util.ArrayList;

public class InsigniaPorRespuestas extends Insignia {

	private int cantidadDeVotos;

	public InsigniaPorRespuestas(String nombre, int cantidadDeRespuestas,
			int cantidadDeDias) {
		super(nombre, cantidadDeRespuestas, cantidadDeDias);
		this.setCantidadDeVotos(0);
	}

	public InsigniaPorRespuestas(String nombre, int cantidadDeRespuestas,
			int cantidadDeDias, int cantidadDeVotos) {
		super(nombre, cantidadDeRespuestas, cantidadDeDias);
		this.setCantidadDeVotos(cantidadDeVotos);
	}

	public InsigniaPorRespuestas(String nombre, int cantidadDeRespuestas) {
		super(nombre, cantidadDeRespuestas, 7);
		this.setCantidadDeVotos(0);
	}

	public InsigniaPorRespuestas(int cantidadDeVotos, String nombre) {
		super(nombre, 1, 100);
		this.setCantidadDeVotos(cantidadDeVotos);
	}

	@Override
	public boolean cumpleCondicion(Usuario usuario, Sistema sist) {
		// TODO Auto-generated method stub
		boolean cumple = false;
		ArrayList<Respuesta> respuestas = sist
				.obtenerRespuestasDelUsuario(usuario);
		for (Respuesta respuesta : respuestas) {
			cumple = cumple
					|| cumpleCondicionDeRespuesta(respuesta, respuestas);
		}
		return cumple;
	}

	public boolean cumpleCondicionDeRespuesta(Respuesta r,
			ArrayList<Respuesta> respuestas) {// respuesta pivote
		int count = 0;

		for (Respuesta resps : respuestas) {

			if (super.cumpleCondicionDeFecha(resps, respuestas)) {
				count = count + 1;
			}

		}
		return count == this.cantidadDePublicaciones;

	}

	public int getCantidadDeVotos() {
		return cantidadDeVotos;
	}

	public void setCantidadDeVotos(int cantidadDeVotos) {
		this.cantidadDeVotos = cantidadDeVotos;
	}
}
