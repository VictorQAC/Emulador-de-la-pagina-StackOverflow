package stackOverflow;

import org.joda.time.DateTime;

public class UsuarioSimple extends Usuario {

	private String realName;
	private String email;

	public UsuarioSimple(Sistema sistema, String usrName, String email,
			String realName) {
		super(sistema, usrName);
		this.realName = realName;
		this.email = email;
	}

	@Override
	public void realizarPregunta(String titulo, String cuerpo, String etiqueta)
			throws Exception {
		Pregunta pregunta = new Pregunta(titulo, cuerpo, etiqueta, this);

		this.sistema.registrarPublicacion(this, pregunta);
		this.cantPuntosPorActividad = this.cantPuntosPorActividad + 10;
		this.fechaDeUltimaPublicacion = DateTime.now();
		this.ranking.agregarAlRanking(this.puntosParaRanking());
	}

	@Override
	public void responder(Pregunta pregunta, String tituloRespuesta,
			String respuesta) throws Exception {

		this.sistema.obtenerPregunta(pregunta).agregarRespuesta(
				new Respuesta(tituloRespuesta, respuesta, this));
		this.cantPuntosPorActividad = this.cantPuntosPorActividad + 5;
		this.fechaDeUltimaPublicacion = DateTime.now();
		this.ranking.agregarAlRanking(this.puntosParaRanking());
		this.sistema.notifyObservers(this);
	}

	@Override
	public int puntajePorActividad() {
		return cantPuntosPorActividad;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public boolean sonIguales(Usuario usuario) {
		// retorna true si dos usuarios tienen las mismas propiedades
		UsuarioSimple simple = (UsuarioSimple) usuario;
		return this.usrName == simple.usrName && this.email == simple.email
				&& this.realName == simple.realName;
	}
}
