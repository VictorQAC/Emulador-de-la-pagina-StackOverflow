package stackOverflow;

import java.util.ArrayList;
import java.util.List;

public class BusquedaPorAutor extends FiltroDeBusqueda {
	/*
	 * Filtro que aplica una busqueda de preguntas segun el autor que las
	 * publica
	 */

	private String filtro;

	public BusquedaPorAutor(String autor) {
		this.setFiltro(autor);
	}

	@Override
	public List<Pregunta> buscar()

	{
		List<Pregunta> resultado;
		resultado = new ArrayList<Pregunta>();
		for (Pregunta preg : this.obtenerPreguntaDelSistema()) {
			if (preg.autor().getUsrName().equals(this.getFiltro())) {
				resultado.add(preg);
			}
		}

		return resultado;
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

}
