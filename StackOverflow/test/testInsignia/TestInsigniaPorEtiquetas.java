package testInsignia;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import stackOverflow.InsigniaPorEtiqueta;
import stackOverflow.Pregunta;
import stackOverflow.Sistema;
import stackOverflow.UsuarioSimple;
import static org.mockito.Mockito.*;

public class TestInsigniaPorEtiquetas {

	private InsigniaPorEtiqueta insignia;
	private InsigniaPorEtiqueta insigniaPreguntasDias;
	private InsigniaPorEtiqueta insigniaPreguntasDiasVotos;
	private Pregunta preguntaMock;
	private Pregunta preguntaMock2;
	private ArrayList<Pregunta> preguntas;
	private DateTime dia;
	private Sistema sist;
	private UsuarioSimple simple;

	@Before
	public void setup() {
		insigniaPreguntasDias = new InsigniaPorEtiqueta("Curioso", 10, 7);
		insigniaPreguntasDiasVotos = new InsigniaPorEtiqueta("Curioso", 10, 7,
				1);
		preguntaMock = mock(Pregunta.class);
		preguntaMock2 = mock(Pregunta.class);
		preguntas = new ArrayList<Pregunta>();
		dia = new DateTime(20 - 11 - 2014);
		sist = mock(Sistema.class);
		simple = mock(UsuarioSimple.class);
	}

	@Test
	public void testcantidadDePreguntasQueTienenLaMismaEtiquetaTieneQueSerIagualAVeinte() {
		insignia = new InsigniaPorEtiqueta("Investigador", 20);
		assertTrue(insignia.nombre() == "Investigador");
		assertTrue(insignia.cantidadDePublicaciones() == 20);

	}

	@Test
	public void testDadaUnaInsigniaQueSoloLeinteresaLaCantidadDePreguntasConLaMismaEtiquetaElPeriodoEntrePublicacionesEsIgualAlPeridoDeRegularidadDeUnUsuario() {
		insignia = new InsigniaPorEtiqueta("Investigador", 20);

		assertTrue(insignia.cantidadDeDias() == 180);
	}

	@Test
	public void testCumpleCondicionDeEtiquetaCuandoLaCantidadDeInsigniasConLaMismaEtiquetaEsIgualOMayorAVeinte() {
		insignia = new InsigniaPorEtiqueta("Investigador", 20);
		when(preguntaMock.getEtiqueta()).thenReturn("Pharo");
		when(preguntaMock.fechaDePublicacion()).thenReturn(dia);
		when(preguntaMock2.getEtiqueta()).thenReturn("Pharo");
		when(preguntaMock2.fechaDePublicacion()).thenReturn(dia.minusMonths(2));
		when(preguntaMock.tieneLaMismaEtiqueta(preguntaMock2)).thenReturn(true);
		when(preguntaMock.tieneLaMismaEtiqueta(preguntaMock)).thenReturn(true);

		when(preguntaMock2.tieneLaMismaEtiqueta(preguntaMock)).thenReturn(true);

		preguntas.add(preguntaMock);
		preguntas.add(preguntaMock);
		preguntas.add(preguntaMock);
		preguntas.add(preguntaMock);
		preguntas.add(preguntaMock);
		preguntas.add(preguntaMock);
		preguntas.add(preguntaMock);
		preguntas.add(preguntaMock);
		preguntas.add(preguntaMock2);
		preguntas.add(preguntaMock2);
		preguntas.add(preguntaMock2);
		preguntas.add(preguntaMock2);
		preguntas.add(preguntaMock2);
		preguntas.add(preguntaMock2);
		preguntas.add(preguntaMock2);
		preguntas.add(preguntaMock2);
		preguntas.add(preguntaMock2);
		assertFalse(insignia.cumpleCondicionDeEtiquetas(preguntaMock, preguntas));
		preguntas.add(preguntaMock2);
		preguntas.add(preguntaMock2);
		preguntas.add(preguntaMock2);

		assertTrue(insignia.cumpleCondicionDeEtiquetas(preguntaMock, preguntas));
	}

	@Test
	public void testCumpleCondicionDeFecha() {
		insignia = new InsigniaPorEtiqueta("PocoInteresado", 5);

		when(preguntaMock.fechaDePublicacion()).thenReturn(dia);
		preguntas.add(preguntaMock);
		preguntas.add(preguntaMock);
		preguntas.add(preguntaMock);
		preguntas.add(preguntaMock);
		preguntas.add(preguntaMock);
		assertTrue(insignia.cumpleCondicionDeFecha(preguntaMock, preguntas));

	}

	@Test
	public void testObtenerPreguntasDelAutor() {
		insignia = new InsigniaPorEtiqueta("PocoInteresado", 5);
		when(simple.sonIguales(simple)).thenReturn(true);
		when(preguntaMock.autor()).thenReturn(simple);
		preguntas.add(preguntaMock);
		preguntas.add(preguntaMock);
		preguntas.add(preguntaMock);
		preguntas.add(preguntaMock);
		preguntas.add(preguntaMock);
		when(sist.preguntas()).thenReturn(preguntas);
		assertEquals(insignia.obtenerPreguntasDelAutor(sist, simple), preguntas);
	}

	@Test
	public void testCumpleCondicion() {
		insignia = new InsigniaPorEtiqueta("PocoInteresado", 5);
		when(preguntaMock.getEtiqueta()).thenReturn("Pharo");
		when(preguntaMock.autor()).thenReturn(simple);
		when(preguntaMock.fechaDePublicacion()).thenReturn(dia);
		when(preguntaMock.tieneLaMismaEtiqueta(preguntaMock)).thenReturn(true);
		when(simple.sonIguales(simple)).thenReturn(true);

		preguntas.add(preguntaMock);
		preguntas.add(preguntaMock);
		assertFalse(insignia.cumpleCondicion(simple, sist));
		preguntas.add(preguntaMock);
		preguntas.add(preguntaMock);
		preguntas.add(preguntaMock);
		when(sist.preguntas()).thenReturn(preguntas);

		assertTrue(insignia.cumpleCondicion(simple, sist));

	}

	@Test
	public void testUpdate() {
		insignia = new InsigniaPorEtiqueta("PocoInteresado", 5);
		when(preguntaMock.getEtiqueta()).thenReturn("Pharo");
		when(preguntaMock.autor()).thenReturn(simple);
		when(preguntaMock.fechaDePublicacion()).thenReturn(dia);
		when(preguntaMock.tieneLaMismaEtiqueta(preguntaMock)).thenReturn(true);
		when(simple.sonIguales(simple)).thenReturn(true);

		preguntas.add(preguntaMock);
		preguntas.add(preguntaMock);
		preguntas.add(preguntaMock);
		preguntas.add(preguntaMock);
		preguntas.add(preguntaMock);
		when(sist.preguntas()).thenReturn(preguntas);
		insignia.update(sist, simple);
		verify(sist).entregarInsignia(insignia, simple);
	}

	@Test
	public void testDadoUnUsuarioQuePublicoDiezPreguntasEnUnaSemanaLasCualesRecibieronUnVotoVerificarCondicionDePregunta() {
		when(preguntaMock.getEtiqueta()).thenReturn("Pharo");
		when(preguntaMock.fechaDePublicacion()).thenReturn(dia);
		when(preguntaMock.cantidadDeVotos()).thenReturn(1);
		when(preguntaMock2.getEtiqueta()).thenReturn("Pharo");
		when(preguntaMock2.fechaDePublicacion()).thenReturn(dia.minusDays(2));
		when(preguntaMock2.cantidadDeVotos()).thenReturn(1);
		when(preguntaMock.tieneLaMismaEtiqueta(preguntaMock)).thenReturn(true);
		when(preguntaMock.tieneLaMismaEtiqueta(preguntaMock2)).thenReturn(true);
		when(preguntaMock2.tieneLaMismaEtiqueta(preguntaMock)).thenReturn(true);
		when(preguntaMock2.tieneLaMismaEtiqueta(preguntaMock2))
				.thenReturn(true);

		when(simple.sonIguales(simple)).thenReturn(true);

		preguntas.add(preguntaMock);
		preguntas.add(preguntaMock);
		preguntas.add(preguntaMock);
		preguntas.add(preguntaMock);
		preguntas.add(preguntaMock2);
		assertFalse(insigniaPreguntasDiasVotos.cumpleCondicionDeEtiquetas(
				preguntaMock, preguntas));
		preguntas.add(preguntaMock2);
		preguntas.add(preguntaMock2);
		preguntas.add(preguntaMock2);
		preguntas.add(preguntaMock2);
		preguntas.add(preguntaMock2);

		assertTrue(insigniaPreguntasDiasVotos.cumpleCondicionDeEtiquetas(
				preguntaMock, preguntas));
	}

	@Test
	public void testDadoUnUsuarioQuePublicoDiezPreguntasEnUnaSemanaVerificarCondicion() {
		when(preguntaMock.getEtiqueta()).thenReturn("Pharo");
		when(preguntaMock.fechaDePublicacion()).thenReturn(dia);

		when(preguntaMock2.getEtiqueta()).thenReturn("Pharo");
		when(preguntaMock2.fechaDePublicacion()).thenReturn(dia.minusDays(2));
		when(preguntaMock.autor()).thenReturn(simple);
		when(preguntaMock2.autor()).thenReturn(simple);
		when(simple.sonIguales(simple)).thenReturn(true);
		when(preguntaMock.tieneLaMismaEtiqueta(preguntaMock)).thenReturn(true);
		when(preguntaMock.tieneLaMismaEtiqueta(preguntaMock2)).thenReturn(true);
		when(preguntaMock2.tieneLaMismaEtiqueta(preguntaMock)).thenReturn(true);
		when(preguntaMock2.tieneLaMismaEtiqueta(preguntaMock2))
				.thenReturn(true);

		preguntas.add(preguntaMock);
		preguntas.add(preguntaMock);
		preguntas.add(preguntaMock);
		preguntas.add(preguntaMock);
		preguntas.add(preguntaMock2);
		assertFalse(insigniaPreguntasDias.cumpleCondicion(simple, sist));
		preguntas.add(preguntaMock2);
		preguntas.add(preguntaMock2);
		preguntas.add(preguntaMock2);
		preguntas.add(preguntaMock2);
		preguntas.add(preguntaMock2);
		when(sist.preguntas()).thenReturn(preguntas);

		assertTrue(insigniaPreguntasDias.cumpleCondicion(simple, sist));
	}

	@Test
	public void testDadoUnUsuarioQuePublicoDiezPreguntasEnUnaSemanaVerificarSIElSistemaLeENtregaLaInsignia() {
		when(preguntaMock.getEtiqueta()).thenReturn("Pharo");
		when(preguntaMock.fechaDePublicacion()).thenReturn(dia);
		when(preguntaMock2.getEtiqueta()).thenReturn("Pharo");
		when(preguntaMock2.fechaDePublicacion()).thenReturn(dia.minusDays(2));
		when(preguntaMock.autor()).thenReturn(simple);
		when(preguntaMock2.autor()).thenReturn(simple);
		when(simple.sonIguales(simple)).thenReturn(true);
		when(preguntaMock.tieneLaMismaEtiqueta(preguntaMock)).thenReturn(true);
		when(preguntaMock.tieneLaMismaEtiqueta(preguntaMock2)).thenReturn(true);
		when(preguntaMock2.tieneLaMismaEtiqueta(preguntaMock)).thenReturn(true);
		when(preguntaMock2.tieneLaMismaEtiqueta(preguntaMock2))
				.thenReturn(true);
		preguntas.add(preguntaMock);
		preguntas.add(preguntaMock);
		preguntas.add(preguntaMock);
		preguntas.add(preguntaMock);
		preguntas.add(preguntaMock2);
		preguntas.add(preguntaMock2);
		preguntas.add(preguntaMock2);
		preguntas.add(preguntaMock2);
		preguntas.add(preguntaMock2);
		preguntas.add(preguntaMock2);
		when(sist.preguntas()).thenReturn(preguntas);

		insigniaPreguntasDias.update(sist, simple);
		verify(sist).entregarInsignia(insigniaPreguntasDias, simple);
	}

	@Test
	public void testDadoUnUsuarioQuePublicoDiezPreguntasEnUnaSemanaLasCualesRecibieronUnVotoVerificarCondicionDeRespuesta() {
		when(preguntaMock.getEtiqueta()).thenReturn("Pharo");
		when(preguntaMock.fechaDePublicacion()).thenReturn(dia);
		when(preguntaMock.cantidadDeVotos()).thenReturn(1);

		when(preguntaMock2.getEtiqueta()).thenReturn("Pharo");
		when(preguntaMock2.fechaDePublicacion()).thenReturn(dia.minusDays(2));
		when(preguntaMock2.cantidadDeVotos()).thenReturn(1);
		when(simple.sonIguales(simple)).thenReturn(true);
		when(preguntaMock.tieneLaMismaEtiqueta(preguntaMock)).thenReturn(true);
		when(preguntaMock.tieneLaMismaEtiqueta(preguntaMock2)).thenReturn(true);
		when(preguntaMock2.tieneLaMismaEtiqueta(preguntaMock)).thenReturn(true);
		when(preguntaMock2.tieneLaMismaEtiqueta(preguntaMock2))
				.thenReturn(true);
		preguntas.add(preguntaMock);
		preguntas.add(preguntaMock);
		preguntas.add(preguntaMock);
		preguntas.add(preguntaMock);
		preguntas.add(preguntaMock2);
		preguntas.add(preguntaMock2);
		assertFalse(insigniaPreguntasDiasVotos.cumpleCondicionDeEtiquetas(
				preguntaMock, preguntas));
		preguntas.add(preguntaMock2);
		preguntas.add(preguntaMock2);
		preguntas.add(preguntaMock2);
		preguntas.add(preguntaMock2);

		assertTrue(insigniaPreguntasDiasVotos.cumpleCondicionDeEtiquetas(
				preguntaMock, preguntas));
	}

	@Test
	public void testDadoUnUsuarioQuePublicoDiezPreguntasEnUnaSemanaLasCualesRecibieronUnVotoVerificarCondicion() {
		when(preguntaMock.getEtiqueta()).thenReturn("Pharo");
		when(preguntaMock.cantidadDeVotos()).thenReturn(1);

		when(preguntaMock.fechaDePublicacion()).thenReturn(dia);
		when(preguntaMock2.getEtiqueta()).thenReturn("Pharo");
		when(preguntaMock2.cantidadDeVotos()).thenReturn(1);
		when(preguntaMock2.fechaDePublicacion()).thenReturn(dia.minusDays(2));
		when(preguntaMock.autor()).thenReturn(simple);
		when(preguntaMock2.autor()).thenReturn(simple);
		when(simple.sonIguales(simple)).thenReturn(true);
		when(preguntaMock.tieneLaMismaEtiqueta(preguntaMock)).thenReturn(true);
		when(preguntaMock.tieneLaMismaEtiqueta(preguntaMock2)).thenReturn(true);
		when(preguntaMock2.tieneLaMismaEtiqueta(preguntaMock)).thenReturn(true);
		when(preguntaMock2.tieneLaMismaEtiqueta(preguntaMock2))
				.thenReturn(true);
		preguntas.add(preguntaMock);
		preguntas.add(preguntaMock);
		preguntas.add(preguntaMock);
		preguntas.add(preguntaMock);
		preguntas.add(preguntaMock2);
		preguntas.add(preguntaMock2);
		assertFalse(insigniaPreguntasDiasVotos.cumpleCondicion(simple, sist));
		preguntas.add(preguntaMock2);
		preguntas.add(preguntaMock2);
		preguntas.add(preguntaMock2);
		preguntas.add(preguntaMock2);
		when(sist.preguntas()).thenReturn(preguntas);

		assertTrue(insigniaPreguntasDiasVotos.cumpleCondicion(simple, sist));
	}

	@Test
	public void testDadoUnUsuarioQuePublicoDiezPreguntasEnUnaSemanaLasCualesRecibieronUnVotoVerificarQueElSistemaLeEntregaLaInsignia() {
		when(preguntaMock.getEtiqueta()).thenReturn("Pharo");
		when(preguntaMock.fechaDePublicacion()).thenReturn(dia);
		when(preguntaMock2.getEtiqueta()).thenReturn("Pharo");
		when(preguntaMock2.fechaDePublicacion()).thenReturn(dia.minusDays(2));
		when(preguntaMock.autor()).thenReturn(simple);
		when(preguntaMock2.autor()).thenReturn(simple);
		when(simple.sonIguales(simple)).thenReturn(true);
		when(preguntaMock.tieneLaMismaEtiqueta(preguntaMock)).thenReturn(true);
		when(preguntaMock.tieneLaMismaEtiqueta(preguntaMock2)).thenReturn(true);
		when(preguntaMock2.tieneLaMismaEtiqueta(preguntaMock)).thenReturn(true);
		when(preguntaMock2.tieneLaMismaEtiqueta(preguntaMock2))
				.thenReturn(true);
		preguntas.add(preguntaMock);
		preguntas.add(preguntaMock);
		preguntas.add(preguntaMock);
		preguntas.add(preguntaMock);
		preguntas.add(preguntaMock2);
		preguntas.add(preguntaMock2);
		preguntas.add(preguntaMock2);
		preguntas.add(preguntaMock2);
		preguntas.add(preguntaMock2);
		preguntas.add(preguntaMock2);
		when(sist.preguntas()).thenReturn(preguntas);

		insigniaPreguntasDiasVotos.update(sist, simple);
		verify(sist).entregarInsignia(insigniaPreguntasDiasVotos, simple);
	}

}
