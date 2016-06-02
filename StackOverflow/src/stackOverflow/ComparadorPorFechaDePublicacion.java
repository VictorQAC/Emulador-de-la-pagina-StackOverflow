package stackOverflow;

public class ComparadorPorFechaDePublicacion implements ComparadorPorCriterio {

	@Override
	public boolean primerMenorQueElSegundo(Pregunta pregunta, Pregunta rotation) {
		return pregunta.fechaDePublicacion().isBefore(
				rotation.fechaDePublicacion());
	}

}
