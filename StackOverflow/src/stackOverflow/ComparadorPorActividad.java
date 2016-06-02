package stackOverflow;

public class ComparadorPorActividad implements ComparadorPorCriterio {

	@Override
	public boolean primerMenorQueElSegundo(Pregunta pregunta, Pregunta rotation) {
		return pregunta.autor().posicionEnELRanking() < rotation.autor()
				.posicionEnELRanking();
	}

}
