package testUsuario;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import stackOverflow.Insignia;
import stackOverflow.InsigniaPorEtiqueta;
import stackOverflow.InsigniaPorRespuestas;
import stackOverflow.Pregunta;
import stackOverflow.Respuesta;
import stackOverflow.Sistema;
import stackOverflow.SistemaAdministradorDePreguntas;
import stackOverflow.SistemaAdministradorDeUsuarios;
import stackOverflow.UsuarioModerador;
import stackOverflow.UsuarioSimple;

public class TestUsuarioModerador {

	private UsuarioModerador usuarioModerador1;
	private UsuarioModerador usuarioModerador3;
	private Sistema sistema;
	private DateTime fechaDeRegistracion;
	private DateTime fechaUltimaDeModeracion;
	private InsigniaPorEtiqueta insignia1;
	private InsigniaPorEtiqueta insignia2;
	private InsigniaPorRespuestas insigniaPorRespuesta;
	private UsuarioSimple usuarioSimple1;
	private UsuarioSimple usuarioSimple2;
	private SistemaAdministradorDeUsuarios sistU;
	private SistemaAdministradorDePreguntas sistP;
	private Pregunta pregunta;
	private Respuesta respuesta;

	@Before
	public void setUp() {
		sistU = new SistemaAdministradorDeUsuarios();
		sistP = new SistemaAdministradorDePreguntas();
		sistema = new Sistema(sistP, sistU);
		fechaDeRegistracion = new DateTime(DateTime.now().getDayOfYear()
				- DateTime.now().getMonthOfYear() - DateTime.now().getYear());
		usuarioModerador1 = new UsuarioModerador(sistema, "Juancho Admin",
				"jadmin@gmail.com");
		insignia1 = new InsigniaPorEtiqueta("InsigniaDePrueba", 1);
		insignia2 = new InsigniaPorEtiqueta("InsigniaDePrueba2", 2);
		usuarioSimple1 = new UsuarioSimple(sistema, "JuanPerez10",
				"jperez@gmail.com", "Juan Perez");
		usuarioSimple2 = new UsuarioSimple(sistema, "diegoz12",
				"diegoz12@gmail.com", "Diego goz");

	}

	@Test
	public void DadoUnUsuarioModeradorQueRealizaUnPreguntaComprobarQueNoSeGuardoEnElSistemaDichaPregunta() {
		assertSame(sistema.preguntas().size(), 0);
		usuarioModerador1.realizarPregunta("Patron Composite",
				"Es un patron de diseño recursivo?",
				"PatronDeDiseño-Programacion-Composite");
		assertSame(sistema.preguntas().size(), 0);
	}

	@Test
	public void DadoUnUsuarioModeradorSeEncuentraInregularQueDeberiaDevolverElValorCeroComaSetentaYCinco() {
		fechaUltimaDeModeracion = new DateTime("2014-05-4");
		usuarioModerador3 = new UsuarioModerador(sistema, "PepGomez",
				"pgomez@gmail.com");
		usuarioModerador3.setFechaDeUltimaPublicacion(fechaUltimaDeModeracion);
		assertTrue(usuarioModerador3.esRegular() == 0.75);
	}

	@Test
	public void DadoUnUsuarioModeradorSeEncuentraRegularQueDeberiaDevolverElValorUno() {
		usuarioModerador1.realizarPregunta("Patron Composite",
				"Es un patron de diseño",
				"PatronDeDiseño-Programacion-Composite");
		assertTrue(usuarioModerador1.esRegular() == 1);
	}

	@Test
	public void DadoUnUsuarioModeradorQueRealizaUnaModificacionEnUnaPreguntaComprobarQueSeRealizo()
			throws Exception {
		
		assertSame(sistema.preguntas().size(), 0);
		usuarioSimple1.realizarPregunta("tituloPrueba",
				"preguntaPruebaAReemplazar", "Prueba");
		assertSame(sistema.preguntas().size(), 1);
		assertSame(sistema.preguntas().get(0).getBody(),
				"preguntaPruebaAReemplazar");
		usuarioModerador1.moderarPregunta(sistema.preguntas().get(0), "preguntaReemplazada");
		assertSame(sistema.preguntas().size(), 1);
		assertSame(sistema.preguntas().get(0).getBody(), "preguntaReemplazada");
	}

	@Test
	public void DadoUnUsuarioModeradorQueRealizaUnaModificacionEnUnaRespuestaComprobarQueSeRealizo()
			throws Exception {
		pregunta = new Pregunta("tituloPrueba", "preguntaPruebaAReemplar",
				"Prueba", usuarioSimple1);
		respuesta = new Respuesta("respuesta a tituloPrueba",
				"respuestaRealizadaDePrueba", usuarioSimple2);
		assertSame(sistema.preguntas().size(), 0);
		usuarioSimple1.realizarPregunta("tituloPrueba",
				"preguntaPruebaAReemplar", "Prueba");
		assertSame(sistema.preguntas().size(), 1);
		assertSame(sistema.preguntas().get(0).cantidadDeRespuestas(), 0);
		usuarioSimple2.responder(pregunta, "respuesta a tituloPrueba",
				"respuestaRealizadaDePrueba");
		assertSame(sistema.preguntas().get(0).cantidadDeRespuestas(), 1);
		usuarioModerador1.moderarRespuesta(pregunta, respuesta,
				"respuestaReemplazada");
		assertSame(sistema.preguntas().size(), 1);
		assertSame(sistema.preguntas().get(0).cantidadDeRespuestas(), 1);
		assertSame(sistema.preguntas().get(0).getRespuestas().get(0).getBody(),
				"respuestaReemplazada");
	}

	@Test
	public void DadoUnUsuarioModeradorQueEliminoUnaPreguntaComprobarQueSeEliminoDelSistema()
			throws Exception {
		pregunta = new Pregunta("tituloPrueba", "preguntaPruebaAReemplar",
				"Prueba", usuarioSimple1);

		assertSame(sistema.preguntas().size(), 0);
		usuarioSimple1.realizarPregunta("tituloPrueba",
				"preguntaPruebaAReemplar", "Prueba");
		assertSame(sistema.preguntas().size(), 1);
		usuarioModerador1.eliminarPregunta(pregunta);
		assertSame(sistema.preguntas().size(), 0);
	}

	@Test
	public void DadoUnUsuarioModeradorQueEliminoUnaRespuestaComprobarQueSeEliminoDelSistema()
			throws Exception {
		pregunta = new Pregunta("tituloPrueba", "preguntaPruebaAReemplar",
				"Prueba", usuarioSimple1);
		respuesta = new Respuesta("respuesta a tituloPrueba",
				"respuestaRealizadaDePrueba", usuarioSimple2);
		assertSame(sistema.preguntas().size(), 0);
		usuarioSimple1.realizarPregunta("tituloPrueba",
				"preguntaPruebaAReemplar", "Prueba");
		assertSame(sistema.preguntas().size(), 1);
		assertSame(sistema.preguntas().get(0).cantidadDeRespuestas(), 0);
		usuarioSimple2.responder(pregunta, "respuesta a tituloPrueba",
				"respuestaRealizadaDePrueba");
		assertSame(sistema.preguntas().get(0).cantidadDeRespuestas(), 1);
		usuarioModerador1.eliminarRespuesta(pregunta, respuesta);
		assertSame(sistema.preguntas().get(0).cantidadDeRespuestas(), 0);
	}

	@Test
	public void DadoUnUsuarioModeradorQueModeroUnaPreguntaObtenerSuPuntajePorActividadQueEsDiez()
			throws Exception {
		pregunta = new Pregunta("Patron Composite", "Es un patron de diseño",
				"PatronDeDiseño-Programacion . Composite", usuarioSimple1);

		usuarioSimple1.realizarPregunta("Patron Composite",
				"Es un patron de diseño",
				"PatronDeDiseño-Programacion . Composite");
		usuarioModerador1.moderarPregunta(pregunta, "Patron Composite");
		assertSame(usuarioModerador1.puntajePorActividad(), 10);
	}

	@Test
	public void DadoUnUsuarioModeradorQueModeroUnaPreguntaPeroSeEncuentraInregularObtenerSusPuntosParaRankingQueSieteComaCinco()
			throws Exception {
		pregunta = new Pregunta("Patron Composite", "Es un patron de diseño",
				"PatronDeDiseño . Programacion . Composite", usuarioSimple1);

		fechaUltimaDeModeracion = new DateTime("2014-04-4");
		usuarioSimple1.realizarPregunta("Patron Composite",
				"Es un patron de diseño",
				"PatronDeDiseño . Programacion . Composite");
		usuarioModerador1.moderarPregunta(pregunta, "Es un patron de diseño");
		usuarioModerador1.setFechaDeUltimaPublicacion(fechaUltimaDeModeracion);
		assertTrue(usuarioModerador1.puntosParaRanking() == 7.5);
		// Le seteo una fecha mas antigua de moderacion asi se encuentra
		// irregular.
	}

	@Test
	public void DadoUnUsuarioModeradorQueVotoPositivoUnaPreguntaComprobarQueSeComputoElVoto()
			throws Exception {
		pregunta = new Pregunta("Titulo Pregunta 1", "pregunta 1",
				"etiqueta 1", usuarioSimple1);

		assertSame(sistema.preguntas().size(), 0);
		usuarioSimple1.realizarPregunta("Titulo Pregunta 1", "pregunta 1",
				"etiqueta 1");
		assertSame(sistema.preguntas().size(), 1);
		assertSame(sistema.preguntas().get(0).cantidadDeVotos(), 0);
		usuarioModerador1.votarPregunta(pregunta, "positiva");
		assertSame(sistema.preguntas().get(0).cantidadDeVotos(), 1);
	}

	@Test
	public void DadoUnUsuarioQueVotoPositivoUnaRespuestaComprobarQueSeComputoElVoto()
			throws Exception {
		pregunta = new Pregunta("Titulo Pregunta 2", "pregunta 2",
				"etiqueta 2", usuarioSimple1);
		respuesta = new Respuesta("respuesta a publicacion", "respuesta",
				usuarioSimple2);
		assertSame(sistema.preguntas().size(), 0);
		usuarioSimple1.realizarPregunta("Titulo Pregunta 2", "pregunta 2",
				"etiqueta 2");
		assertSame(sistema.preguntas().get(0).cantidadDeRespuestas(), 0);
		usuarioSimple2.responder(pregunta, "respuesta a publicacion",
				"respuesta");
		usuarioModerador1.votarRespuesta(pregunta, respuesta, "positiva");
		assertSame(sistema.preguntas().get(0).obtenerRespuesta(respuesta)
				.cantidadDeVotos(), 1);
	}

	@Test
	public void DadoUnUsuarioModeradorComprobarQueResivioUnaInsignia() {
		assertSame(usuarioModerador1.cantidadDeInsignias(), 0);
		usuarioModerador1.aceptarInsignia(insignia1);
		assertSame(usuarioModerador1.cantidadDeInsignias(), 1);
	}

	@Test
	public void DadoUnUsuarioComprobarQueResivioUnaInsigniaQueYaTeniaYNoLaAgrego() {
		usuarioModerador1.aceptarInsignia(insignia1);
		assertSame(usuarioModerador1.cantidadDeInsignias(), 1);
		usuarioModerador1.aceptarInsignia(insignia2);
		assertSame(usuarioModerador1.cantidadDeInsignias(), 2);
		usuarioModerador1.aceptarInsignia(insignia1);
		assertSame(usuarioModerador1.cantidadDeInsignias(), 2);
	}

	@Test
	public void DadoUnUsuarioModeradorComprobarSuNombre() {
		assertSame(usuarioModerador1.getName(), "Juancho Admin");
	}

	@Test
	public void DadoUnUsuarioModeradorComprobarSuEmail() {
		assertSame(usuarioModerador1.getEmail(), "jadmin@gmail.com");
	}

	@Test
	public void DadoUnUsuarioModeradorComprobarSuFechaDeRegistracion() {
		assertEquals(usuarioModerador1.getFechaDeRegistracion(),
				fechaDeRegistracion);
	}

	@Test
	public void DadoUnUsuarioModeradorComprobarSuFechaDeUltimaModeracion() {
		assertEquals(usuarioModerador1.getFechaDeUltimaPublicacion(), null);
	}

	@Test
	public void DadoUnUsuarioModeradorComprobrarLasInsigniaQueLeFalta() {
		assertSame(sistema.insignias().size(), 0);
		sistema.agregarInsignia(insignia1);
		assertSame(sistema.insignias().size(), 1);
		sistema.agregarInsignia(insignia2);
		assertSame(sistema.insignias().size(), 2);
		assertSame(usuarioModerador1.cantidadDeInsignias(), 0);
		usuarioModerador1.aceptarInsignia(insignia1);
		assertSame(usuarioModerador1.cantidadDeInsignias(), 1);
		ArrayList<Insignia> faltantes = new ArrayList<Insignia>();
		faltantes.add(insignia2);
		assertEquals(usuarioModerador1.insigniasFaltantes(), faltantes);
	}

	@Test
	public void DadoUnUsuarioModeradorComprobarQueNoLeFaltaInsigniasDelSistema() {
		assertSame(sistema.insignias().size(), 0);
		sistema.agregarInsignia(insignia1);
		assertSame(sistema.insignias().size(), 1);
		sistema.agregarInsignia(insignia2);
		assertSame(sistema.insignias().size(), 2);
		assertSame(usuarioModerador1.cantidadDeInsignias(), 0);
		usuarioModerador1.aceptarInsignia(insignia1);
		usuarioModerador1.aceptarInsignia(insignia2);
		assertSame(usuarioModerador1.cantidadDeInsignias(), 2);
		ArrayList<Insignia> faltantes = new ArrayList<Insignia>();
		assertEquals(usuarioModerador1.insigniasFaltantes(), faltantes);
	}

	@Test
	public void DadoUnUsuarioModeradorQueCreaUnaInsigniaComprobarQueSeGuardaEnElSistema() {
		assertSame(sistema.insignias().size(), 0);
		insigniaPorRespuesta = new InsigniaPorRespuestas("TituloRespuesta", 5,
				5);
		usuarioModerador1.crearInsignia(insigniaPorRespuesta);
		assertSame(sistema.insignias().size(), 1);
	}

}
