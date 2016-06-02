package stackOverflow;

import org.joda.time.DateTime;

public class UsuarioModerador extends Usuario {

	private String email;

	public UsuarioModerador(Sistema sistema, String name, String email) {
		super(sistema, name);
		this.email = email;
	}

	@Override
	public void realizarPregunta(String titulo, String cuerpo, String etiqueta) {
	}

	@Override
	public void responder(Pregunta pregunta, String tituloRespuesta,
			String respuesta) {
	}

	public void moderarPregunta(Pregunta pregunta, String cuerpo)
			throws Exception {
		Pregunta preguntaModificada=new Pregunta(pregunta.getTitle(), cuerpo, pregunta.getEtiqueta(), pregunta.autor());
		this.sistema.reemplazarPregunta(pregunta, preguntaModificada);
		this.fechaDeUltimaPublicacion = DateTime.now();
		this.cantPuntosPorActividad = this.cantPuntosPorActividad + 10;
		this.ranking.agregarAlRanking(this.puntosParaRanking());
	}

	public void moderarRespuesta(Pregunta pregunta, Respuesta respuesta,
			String cuerpoModerado) throws Exception {
		this.sistema.obtenerPregunta(pregunta).obtenerRespuesta(respuesta)
				.setBody(cuerpoModerado);

		this.fechaDeUltimaPublicacion = DateTime.now();
		this.cantPuntosPorActividad = this.cantPuntosPorActividad + 5;
		this.ranking.agregarAlRanking(this.puntosParaRanking());
	}

	public void eliminarPregunta(Pregunta pregunta) {
		this.sistema.borrarPregunta(pregunta);
	}

	public void eliminarRespuesta(Pregunta pregunta, Respuesta respuesta)
			throws Exception {
		Pregunta preguntaModificada=this.sistema.obtenerPregunta(pregunta);
		preguntaModificada.eliminarRespuesta(respuesta);
		this.sistema.reemplazarPregunta(pregunta, preguntaModificada);

	}

	public void crearInsignia(Insignia i) {
		this.sistema.agregarInsignia(i);
	}

	public String getEmail() {
		return email;
	}

	public String getName() {
		return super.getUsrName();
	}

	@Override
	public boolean sonIguales(Usuario usuario) {
		// TODO Auto-generated method stub
		UsuarioModerador moderador = (UsuarioModerador) usuario;
		return this.getUsrName() == moderador.getUsrName()
				&& this.getEmail() == moderador.getEmail();
	}
}
