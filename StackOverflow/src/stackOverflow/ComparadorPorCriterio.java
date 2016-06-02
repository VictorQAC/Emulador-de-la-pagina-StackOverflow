package stackOverflow;

public interface ComparadorPorCriterio {

	boolean primerMenorQueElSegundo(Pregunta pregunta, Pregunta rotation);
	/*
	 * Este metodo compara, segun el critero de la clase que lo implemente,
	 * entre dos preguntas pasadas como parametro y devuelve TRUE si la primera
	 * es "menor" que la segunda en caso igual u opuesto devuelve FALSE.
	 * Entiendase "menor" segun el criterio de cada clase que implemente dicha
	 * interface
	 */
}
