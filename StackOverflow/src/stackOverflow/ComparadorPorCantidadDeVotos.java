package stackOverflow;

public class ComparadorPorCantidadDeVotos implements ComparadorPorCriterio {

	@Override
	public boolean primerMenorQueElSegundo(Pregunta pregunta, Pregunta rotation) {
		return pregunta.cantidadDeVotos() < rotation.cantidadDeVotos();
	}

}
