package testInsignia;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import stackOverflow.InsigniaPorRespuestas;
import stackOverflow.Respuesta;
import stackOverflow.Sistema;
import stackOverflow.UsuarioSimple;
import static org.mockito.Mockito.*;

public class TestInsigniaPorRespuesta {
	private InsigniaPorRespuestas insigniaRespuestasYDias;
	private InsigniaPorRespuestas insigniaCantidadDeVotos;
	private InsigniaPorRespuestas insigniaCantidadDeRespuestas;
	private InsigniaPorRespuestas insigniaRespuestasDiasyVotos;
	private Respuesta respuestaMock;
	private ArrayList<Respuesta> respuestas;
	private DateTime dia;
	private Sistema sist;
	private UsuarioSimple simple;

	@Before
	public void setup() {
		insigniaRespuestasYDias = new InsigniaPorRespuestas("Salvador", 5, 7);
		insigniaCantidadDeVotos = new InsigniaPorRespuestas(1, "Altruista");
		insigniaCantidadDeRespuestas = new InsigniaPorRespuestas("Respondedor",
				20);
		insigniaRespuestasDiasyVotos = new InsigniaPorRespuestas(
				"ElGranRespondedor", 20, 7, 10);
		respuestaMock = mock(Respuesta.class);
		respuestas = new ArrayList<Respuesta>();
		sist = mock(Sistema.class);
		dia = new DateTime(20 - 11 - 2014);
		simple = mock(UsuarioSimple.class);

	}

	@Test
	public void testDadoUnUsuarioQuePublicoCincoRespuestasEnUnaSemanaVerificarSICumpleCondicionDeRespuesta() {
		when(respuestaMock.fechaDePublicacion()).thenReturn(dia);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		assertFalse(insigniaRespuestasYDias.cumpleCondicionDeRespuesta(
				respuestaMock, respuestas));
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);

		assertTrue(insigniaRespuestasYDias.cumpleCondicionDeRespuesta(
				respuestaMock, respuestas));
	}

	@Test
	public void testDadoUnUsuarioQuePublicoCincoRespuestasEnUnaSemanaVerificarSICumpleCondicion() {
		when(respuestaMock.fechaDePublicacion()).thenReturn(dia);
		when(respuestaMock.autor()).thenReturn(simple);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		assertFalse(insigniaRespuestasYDias.cumpleCondicion(simple, sist));
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		when(sist.obtenerRespuestasDelUsuario(simple)).thenReturn(respuestas);
		assertTrue(insigniaRespuestasYDias.cumpleCondicion(simple, sist));
	}

	@Test
	public void testDadoUnUsuarioQuePublicoCincoRespuestasEnUnaSemanaVerificarSIElSistemaLeEntregaLaInsignia() {
		when(respuestaMock.fechaDePublicacion()).thenReturn(dia);
		when(respuestaMock.autor()).thenReturn(simple);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		when(sist.obtenerRespuestasDelUsuario(simple)).thenReturn(respuestas);
		insigniaRespuestasYDias.update(sist, simple);

		verify(sist).entregarInsignia(insigniaRespuestasYDias, simple);

	}

	@Test
	public void testDadaUnRespuestaQueRecibeUnaVotacionVerificarSiELUsuarioQueLaPublicoCumpleCondicionDeRespuesta() {

		when(respuestaMock.fechaDePublicacion()).thenReturn(dia);
		when(respuestaMock.cantidadDeVotos()).thenReturn(1);
		respuestas.add(respuestaMock);
		assertTrue(insigniaCantidadDeVotos.cumpleCondicionDeRespuesta(
				respuestaMock, respuestas));
	}

	@Test
	public void testDadaUnRespuestaQueRecibeUnaVotacionVerificarSiELUsuarioQueLaPublicoCumpleCondicion() {

		when(respuestaMock.fechaDePublicacion()).thenReturn(dia);
		when(respuestaMock.cantidadDeVotos()).thenReturn(1);
		respuestas.add(respuestaMock);
		when(sist.obtenerRespuestasDelUsuario(simple)).thenReturn(respuestas);
		assertTrue(insigniaCantidadDeVotos.cumpleCondicion(simple, sist));
	}

	@Test
	public void testDadaUnRespuestaQueRecibeUnaVotacionVerificarSiELSistemaLeEntregaLaInsignia() {

		when(respuestaMock.fechaDePublicacion()).thenReturn(dia);
		when(respuestaMock.cantidadDeVotos()).thenReturn(1);
		respuestas.add(respuestaMock);
		when(sist.obtenerRespuestasDelUsuario(simple)).thenReturn(respuestas);
		insigniaCantidadDeVotos.update(sist, simple);
		verify(sist).entregarInsignia(insigniaCantidadDeVotos, simple);
	}

	@Test
	public void testDadaUnUsuarioQuePublicoVeinteRespuestasCumpleCondicionDeRespuesta() {

		when(respuestaMock.fechaDePublicacion()).thenReturn(dia);

		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		assertFalse(insigniaCantidadDeRespuestas.cumpleCondicionDeRespuesta(
				respuestaMock, respuestas));
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		assertTrue(insigniaCantidadDeRespuestas.cumpleCondicionDeRespuesta(
				respuestaMock, respuestas));
	}

	@Test
	public void testDadaUnUsuarioQuePublicoVeinteRespuestasCumpleCondicion() {

		when(respuestaMock.fechaDePublicacion()).thenReturn(dia);

		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		assertFalse(insigniaCantidadDeRespuestas.cumpleCondicion(simple, sist));
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		when(sist.obtenerRespuestasDelUsuario(simple)).thenReturn(respuestas);
		assertTrue(insigniaCantidadDeRespuestas.cumpleCondicion(simple, sist));
	}

	@Test
	public void testDadaUnUsuarioQuePublicoVeinteRespuestasVerificarQueElSistemaLeEntregaLaInsignia() {

		when(respuestaMock.fechaDePublicacion()).thenReturn(dia);

		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		when(sist.obtenerRespuestasDelUsuario(simple)).thenReturn(respuestas);
		insigniaCantidadDeRespuestas.update(sist, simple);
		verify(sist).entregarInsignia(insigniaCantidadDeRespuestas, simple);
	}

	@Test
	public void testDadoUnUsuarioQuePublicoVeinteRespuestasEnUnaSemanaLasCualesRecibieronDiezVotosVerificarQueCumplaCondicionDeRespuesta() {
		when(respuestaMock.fechaDePublicacion()).thenReturn(dia);
		when(respuestaMock.cantidadDeVotos()).thenReturn(10);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		assertFalse(insigniaRespuestasDiasyVotos.cumpleCondicionDeRespuesta(
				respuestaMock, respuestas));
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		assertTrue(insigniaRespuestasDiasyVotos.cumpleCondicionDeRespuesta(
				respuestaMock, respuestas));
	}

	@Test
	public void testDadoUnUsuarioQuePublicoVeinteRespuestasEnUnaSemanaLasCualesRecibieronDiezVotosVerificarQueCumplaCondicion() {
		when(respuestaMock.fechaDePublicacion()).thenReturn(dia);
		when(respuestaMock.cantidadDeVotos()).thenReturn(10);
		when(respuestaMock.autor()).thenReturn(simple);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		assertFalse(insigniaRespuestasDiasyVotos.cumpleCondicion(simple, sist));
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		when(sist.obtenerRespuestasDelUsuario(simple)).thenReturn(respuestas);

		assertTrue(insigniaRespuestasDiasyVotos.cumpleCondicion(simple, sist));
	}

	@Test
	public void testDadoUnUsuarioQuePublicoVeinteRespuestasEnUnaSemanaLasCualesRecibieronDiezVotosVerificarQueElSistemaLeEntregaLaInsignia() {
		when(respuestaMock.fechaDePublicacion()).thenReturn(dia);
		when(respuestaMock.cantidadDeVotos()).thenReturn(10);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		respuestas.add(respuestaMock);
		when(respuestaMock.autor()).thenReturn(simple);
		when(sist.obtenerRespuestasDelUsuario(simple)).thenReturn(respuestas);

		insigniaRespuestasDiasyVotos.update(sist, simple);

		verify(sist).entregarInsignia(insigniaRespuestasDiasyVotos, simple);
	}

}
