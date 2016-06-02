package stackOverflow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class FiltroDeBusqueda {

	private Sistema sistema;

	public ArrayList<Pregunta> obtenerPreguntaDelSistema() {
		return this.getSistema().preguntas();
	}

	public Sistema getSistema() {
		return sistema;
	}

	public void setSistema(Sistema sistema) {
		this.sistema = sistema;
	}

	public List<Pregunta> buscar() {
		/*
		 * Devuelve una lista de preguntas que cumplen con el filtro aplicado en
		 * cada subclase
		 */
		return null;
	}

	public List<Pregunta> ordenarPorCriterioAscendente(List<Pregunta> list,
			ComparadorPorCriterio comparador) {
		if (list.size() <= 1)
			return list;
		Pregunta rotation = list.get(list.size() / 2);
		list.remove(list.size() / 2);
		ArrayList<Pregunta> lower = new ArrayList<Pregunta>();
		ArrayList<Pregunta> higher = new ArrayList<Pregunta>();
		for (Pregunta pregunta : list) {
			if (comparador.primerMenorQueElSegundo(pregunta, rotation))//
				lower.add(pregunta);
			else
				higher.add(pregunta);
		}
		ordenarPorCriterioAscendente(lower, comparador);
		ordenarPorCriterioAscendente(higher, comparador);

		list.clear();
		list.addAll(lower);
		list.add(rotation);
		list.addAll(higher);

		return list;
	}

	public List<Pregunta> ordenarPorCriterioDescendente(List<Pregunta> list,
			ComparadorPorCriterio comparador) {
		List<Pregunta> resultado = this.ordenarPorCriterioAscendente(list,
				comparador);
		Collections.reverse(resultado);
		return resultado;
	}

}
