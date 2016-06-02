package testFiltroDeBusqueda;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import stackOverflow.BusquedaCompuesta;
import stackOverflow.BusquedaPorAutor;
import stackOverflow.BusquedaPorEtiqueta;
import stackOverflow.BusquedaPorFecha;
import stackOverflow.FiltroDeBusqueda;
import stackOverflow.Pregunta;
import stackOverflow.Sistema;
import stackOverflow.Usuario;

public class testFiltroBusquedaCompuesta {

	private BusquedaPorFecha filtroPorFecha;
	private BusquedaPorAutor filtroPorAutor1;
	private BusquedaPorAutor filtroPorAutor2;
	private BusquedaPorEtiqueta filtroPorEtiqueta;

	private BusquedaCompuesta filtroCompuesto;
	private List<FiltroDeBusqueda> listaDeFiltros;

	private Sistema mockStack;
	private Pregunta mockPregunta1;
	private Pregunta mockPregunta2;
	private ArrayList<Pregunta> listaPreguntas;

	private Usuario mockUsuario1;
	private Usuario mockUsuario2;

	@Before
	public void setUp() {
		filtroPorFecha = new BusquedaPorFecha("2014-01-1", "2014-11-14");
		filtroPorEtiqueta = new BusquedaPorEtiqueta("Java");
		filtroPorAutor1 = new BusquedaPorAutor("Diego");
		filtroPorAutor2 = new BusquedaPorAutor("Ignacio");

		listaDeFiltros = new ArrayList<FiltroDeBusqueda>();
		listaDeFiltros.add(filtroPorFecha);
		listaDeFiltros.add(filtroPorEtiqueta);
		listaDeFiltros.add(filtroPorAutor1);
		listaDeFiltros.add(filtroPorAutor2);

		filtroCompuesto = new BusquedaCompuesta(listaDeFiltros);

		mockStack = mock(Sistema.class);
		mockPregunta1 = mock(Pregunta.class);
		mockPregunta2 = mock(Pregunta.class);
		listaPreguntas = new ArrayList<Pregunta>();

		mockUsuario1 = mock(Usuario.class);
		mockUsuario2 = mock(Usuario.class);

		filtroCompuesto.setSistema(mockStack);
	}

	@Test
	public void testDadoUnSistemaConSoloUnElementoRealizoUnaBusuqedaCompuestaQueLoContenga() {
		listaPreguntas.add(mockPregunta1);

		DateTime time1 = new DateTime("2014-11-8");

		when(mockStack.preguntas()).thenReturn(listaPreguntas);
		when(mockPregunta1.fechaDePublicacion()).thenReturn(time1);
		when(mockPregunta1.autor()).thenReturn(mockUsuario1);
		when(mockPregunta1.getEtiqueta()).thenReturn("");
		when(mockUsuario1.getUsrName()).thenReturn("Emiliano");

		assertEquals(filtroCompuesto.buscar().size(), 1);
		assertTrue(filtroCompuesto.buscar().contains(mockPregunta1));

	}

	@Test
	public void testDadoUnSistemaConSoloUnElementoRealizoUnaBusuqedaCompuestaQueNoLoContenga() {
		listaPreguntas.add(mockPregunta1);

		DateTime time1 = new DateTime("2000-11-8");

		when(mockStack.preguntas()).thenReturn(listaPreguntas);
		when(mockPregunta1.fechaDePublicacion()).thenReturn(time1);
		when(mockPregunta1.autor()).thenReturn(mockUsuario1);
		when(mockPregunta1.getEtiqueta()).thenReturn("");
		when(mockUsuario1.getUsrName()).thenReturn("Emiliano");

		assertEquals(filtroCompuesto.buscar().size(), 0);
		assertFalse(filtroCompuesto.buscar().contains(mockPregunta1));
	}

	@Test
	public void testDadoUnSistemaConDosElementosRealizoUnaBusuqedaCompuestaQueContengaSoloAUno() {
		listaPreguntas.add(mockPregunta1);
		listaPreguntas.add(mockPregunta2);

		DateTime time1 = new DateTime("2000-11-8");

		when(mockStack.preguntas()).thenReturn(listaPreguntas);
		when(mockPregunta1.fechaDePublicacion()).thenReturn(time1);
		when(mockPregunta1.autor()).thenReturn(mockUsuario1);
		when(mockPregunta2.autor()).thenReturn(mockUsuario2);
		when(mockPregunta1.getEtiqueta()).thenReturn("");
		when(mockPregunta2.getEtiqueta()).thenReturn("");

		when(mockUsuario1.getUsrName()).thenReturn("Emiliano");
		when(mockUsuario2.getUsrName()).thenReturn("Diego");

		assertEquals(filtroCompuesto.buscar().size(), 1);
		assertTrue(filtroCompuesto.buscar().contains(mockPregunta2));
		assertFalse(filtroCompuesto.buscar().contains(mockPregunta1));
	}

	@Test
	public void testDadoUnSistemaConDosElementosRealizoUnaBusuqedaCompuestaQueContengaNoContengaANinguno() {
		listaPreguntas.add(mockPregunta1);
		listaPreguntas.add(mockPregunta2);

		DateTime time1 = new DateTime("2000-11-8");

		when(mockStack.preguntas()).thenReturn(listaPreguntas);
		when(mockPregunta1.fechaDePublicacion()).thenReturn(time1);
		when(mockPregunta1.autor()).thenReturn(mockUsuario1);
		when(mockPregunta2.autor()).thenReturn(mockUsuario2);
		when(mockPregunta1.getEtiqueta()).thenReturn("");
		when(mockPregunta2.getEtiqueta()).thenReturn("");

		when(mockUsuario1.getUsrName()).thenReturn("Emiliano");
		when(mockUsuario2.getUsrName()).thenReturn("Juan Pablo");

		assertTrue(filtroCompuesto.buscar().size() == 0);
		assertFalse(filtroCompuesto.buscar().contains(mockPregunta1));
		assertFalse(filtroCompuesto.buscar().contains(mockPregunta2));

	}

	@Test
	public void testDadoUnSistemaConDosElementosRealizoUnaBusuqedaCompuestaQueContengaAAmbos() {
		listaPreguntas.add(mockPregunta1);
		listaPreguntas.add(mockPregunta2);

		DateTime time1 = new DateTime("2014-11-8");

		when(mockStack.preguntas()).thenReturn(listaPreguntas);
		when(mockPregunta1.fechaDePublicacion()).thenReturn(time1);
		when(mockPregunta1.autor()).thenReturn(mockUsuario1);
		when(mockPregunta2.autor()).thenReturn(mockUsuario1);
		when(mockPregunta1.getEtiqueta()).thenReturn("");
		when(mockPregunta2.getEtiqueta()).thenReturn("");

		when(mockUsuario1.getUsrName()).thenReturn("Diego");

		assertTrue(filtroCompuesto.buscar().size() == 2);
		assertTrue(filtroCompuesto.buscar().contains(mockPregunta2));
		assertTrue(filtroCompuesto.buscar().contains(mockPregunta1));
	}

	@Test
	public void testDadoUnSistemaConTresElementosRealizoUnaBusuqedaCompuestaQueContengaALosTRes() {
		Pregunta mockPregunta3 = mock(Pregunta.class);
		Usuario mockUsuario3 = mock(Usuario.class);
		listaPreguntas.add(mockPregunta3);
		listaPreguntas.add(mockPregunta1);
		listaPreguntas.add(mockPregunta2);

		String etiquetas = "Java SmallTalk";

		DateTime time1 = new DateTime("2014-11-8");

		when(mockStack.preguntas()).thenReturn(listaPreguntas);
		when(mockPregunta1.fechaDePublicacion()).thenReturn(time1);
		when(mockPregunta1.autor()).thenReturn(mockUsuario3);
		when(mockPregunta2.autor()).thenReturn(mockUsuario2);
		when(mockPregunta3.autor()).thenReturn(mockUsuario1);
		when(mockPregunta1.getEtiqueta()).thenReturn("");
		when(mockPregunta2.getEtiqueta()).thenReturn("");
		when(mockPregunta3.getEtiqueta()).thenReturn(etiquetas);

		when(mockUsuario1.getUsrName()).thenReturn("Emiliano");
		when(mockUsuario2.getUsrName()).thenReturn("Diego");
		when(mockUsuario3.getUsrName()).thenReturn("Juan Pablo");

		assertEquals(filtroCompuesto.buscar().size(), 3);
		assertTrue(filtroCompuesto.buscar().contains(mockPregunta3));
		assertTrue(filtroCompuesto.buscar().contains(mockPregunta2));
		assertTrue(filtroCompuesto.buscar().contains(mockPregunta1));
	}

}
