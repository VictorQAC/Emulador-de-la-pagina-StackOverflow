package testComparadorPorCriterio;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import stackOverflow.ComparadorPorCriterio;
import stackOverflow.ComparadorPorNombreDeUsuario;
import stackOverflow.Pregunta;
import stackOverflow.Usuario;

public class testComparadorPorNombreDeUsuario {

	private ComparadorPorCriterio comparadorPorNombreAutorAlfabeticamente;

	private Pregunta mockPregunta1;
	private Pregunta mockPregunta2;

	private Usuario mockUsuario1;
	private Usuario mockUsuario2;

	@Before
	public void setUp() {
		mockPregunta1 = mock(Pregunta.class);
		mockPregunta2 = mock(Pregunta.class);

		mockUsuario1 = mock(Usuario.class);
		mockUsuario2 = mock(Usuario.class);

		comparadorPorNombreAutorAlfabeticamente = new ComparadorPorNombreDeUsuario();
	}

	@Test
	public void testComparoConUnComparadorDeCriterioDosPreguntasDelDiferentesAutores() {

		when(mockPregunta1.autor()).thenReturn(mockUsuario1);
		when(mockPregunta2.autor()).thenReturn(mockUsuario2);

		when(mockUsuario1.getUsrName()).thenReturn("Andres");
		when(mockUsuario2.getUsrName()).thenReturn("Abel");

		assertFalse(this.comparadorPorNombreAutorAlfabeticamente
				.primerMenorQueElSegundo(mockPregunta1, mockPregunta2));

		assertTrue(this.comparadorPorNombreAutorAlfabeticamente
				.primerMenorQueElSegundo(mockPregunta2, mockPregunta1));

	}

	@Test
	public void testComparoConUnComparadorDeCriterioDosPreguntasDelMismoAutor() {

		when(mockPregunta1.autor()).thenReturn(mockUsuario1);
		when(mockPregunta2.autor()).thenReturn(mockUsuario2);

		when(mockUsuario1.getUsrName()).thenReturn("Andres");
		when(mockUsuario2.getUsrName()).thenReturn("Andres");

		assertFalse(this.comparadorPorNombreAutorAlfabeticamente
				.primerMenorQueElSegundo(mockPregunta1, mockPregunta2));
		assertFalse(this.comparadorPorNombreAutorAlfabeticamente
				.primerMenorQueElSegundo(mockPregunta2, mockPregunta1));

	}

}
