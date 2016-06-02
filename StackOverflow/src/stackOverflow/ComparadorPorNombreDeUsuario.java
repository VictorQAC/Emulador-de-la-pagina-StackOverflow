package stackOverflow;

public class ComparadorPorNombreDeUsuario implements ComparadorPorCriterio {

	@Override
	public boolean primerMenorQueElSegundo(Pregunta pregunta, Pregunta rotation) {
		return (pregunta.autor().getUsrName()
				.compareTo(rotation.autor().getUsrName()) < 0);
	}

}
