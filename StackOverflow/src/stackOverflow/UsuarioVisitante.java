package stackOverflow;

import org.joda.time.DateTime;

public class UsuarioVisitante extends Usuario {

	private int cantPuntosPorActividad;

	public UsuarioVisitante(Sistema sistema, String IP) {
		super(sistema, IP);
	}

	@Override
	public void realizarPregunta(String titulo, String cuerpo, String etiqueta) {
	}

	public String getIP() {
		return super.getUsrName();
	}

	@Override
	public boolean sonIguales(Usuario usuario) {
		UsuarioVisitante visitante = (UsuarioVisitante) usuario;
		return this.usrName == visitante.usrName;
	}

	@Override
	public void responder(Pregunta pregunta, String tituloRespuesta,
			String cuerpoDeRespuesta) throws Exception {
		this.sistema.registrarUsuarioVisitante(this);
		this.sistema.obtenerPregunta(pregunta).agregarRespuesta(
				new Respuesta(tituloRespuesta, cuerpoDeRespuesta, this));
		this.setCantPuntosPorActividad(this.cantPuntosPorActividad + 2);
		this.fechaDeUltimaPublicacion = DateTime.now();
		this.ranking.agregarAlRanking(this.puntosParaRanking());
		this.sistema.notifyObservers(this);

	}
	
	@Override
	public void votarPregunta(Pregunta pregunta, String voto) throws Exception {
		this.sistema.registrarUsuarioVisitante(this);
		sistema.obtenerPregunta(pregunta).agregarVoto(this.getUsrName(), voto);

	}

	@Override
	public void votarRespuesta(Pregunta pregunta, Respuesta respuesta,
			String voto) throws Exception {
		this.sistema.registrarUsuarioVisitante(this);
		this.sistema.obtenerPregunta(pregunta).obtenerRespuesta(respuesta)
				.agregarVoto(voto, voto);

	}

}
