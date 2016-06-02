package testFiltroDeBusqueda;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import stackOverflow.BusquedaPorAutor;
import stackOverflow.Pregunta;
import stackOverflow.Sistema;
import stackOverflow.Usuario;

public class testFiltroBusquedaPorAutor {

	private BusquedaPorAutor filtroPorAutor;
	private Sistema mockStack;
	private Pregunta mockPregunta1;
	private Pregunta mockPregunta2;
	private ArrayList<Pregunta> listaPreguntas;

	private Usuario mockUsuario1;
	private Usuario mockUsuario2;

	@Before
	public void setUp() {
		filtroPorAutor = new BusquedaPorAutor("Jorge");
		mockStack = mock(Sistema.class);
		mockPregunta1 = mock(Pregunta.class);
		mockPregunta2 = mock(Pregunta.class);
		listaPreguntas = new ArrayList<Pregunta>();

		mockUsuario1 = mock(Usuario.class);
		mockUsuario2 = mock(Usuario.class);

		filtroPorAutor.setSistema(mockStack);
	}

	@Test
	public void testDadoUnSistemaConUnaPreguntaFiltroPorSuAutor() {

		listaPreguntas.add(mockPregunta1);
		when(mockStack.preguntas()).thenReturn(listaPreguntas);
		when(mockPregunta1.autor()).thenReturn(mockUsuario1);

		when(mockUsuario1.getUsrName()).thenReturn("Jorge");

		assertEquals(this.filtroPorAutor.buscar().size(), 1);
		assertTrue(this.filtroPorAutor.buscar().contains(mockPregunta1));
	}

	@Test
	public void testDadoUnSistemaConDosPreguntasFiltroElAutorDeSoloUnaDeEllas() {

		listaPreguntas.add(mockPregunta1);
		listaPreguntas.add(mockPregunta2);
		when(mockStack.preguntas()).thenReturn(listaPreguntas);
		when(mockPregunta1.autor()).thenReturn(mockUsuario1);
		when(mockPregunta2.autor()).thenReturn(mockUsuario2);

		when(mockUsuario1.getUsrName()).thenReturn("Jorge");
		when(mockUsuario2.getUsrName()).thenReturn("David");

		assertTrue(this.filtroPorAutor.buscar().size() == 1);
		assertTrue(this.filtroPorAutor.buscar().contains(mockPregunta1));
		assertFalse(filtroPorAutor.buscar().contains(mockPregunta2));
	}

	@Test
	public void testDadoUnSistemaConDosPreguntasFiltroElAutorDondeNingunaEsCorrecta() {

		listaPreguntas.add(mockPregunta1);
		listaPreguntas.add(mockPregunta2);
		when(mockStack.preguntas()).thenReturn(listaPreguntas);
		when(mockPregunta1.autor()).thenReturn(mockUsuario1);
		when(mockPregunta2.autor()).thenReturn(mockUsuario2);

		when(mockUsuario1.getUsrName()).thenReturn("Matias");
		when(mockUsuario2.getUsrName()).thenReturn("David");

		assertEquals(this.filtroPorAutor.buscar().size(), 0);
		assertFalse(filtroPorAutor.buscar().contains(mockPregunta2));
		assertFalse(filtroPorAutor.buscar().contains(mockPregunta1));

	}

	@Test
	public void testDadoUnSistemaConDosPreguntasFiltroElAutorDondeLasDosTienenElMismoAutor() {

		listaPreguntas.add(mockPregunta1);
		listaPreguntas.add(mockPregunta2);
		when(mockStack.preguntas()).thenReturn(listaPreguntas);
		when(mockPregunta1.autor()).thenReturn(mockUsuario1);
		when(mockPregunta2.autor()).thenReturn(mockUsuario1);

		when(mockUsuario1.getUsrName()).thenReturn("Jorge");

		assertEquals(this.filtroPorAutor.buscar().size(), 2);
		assertTrue(this.filtroPorAutor.buscar().contains(mockPregunta1));
		assertTrue(this.filtroPorAutor.buscar().contains(mockPregunta2));
	}

}
