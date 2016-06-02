package stackOverflow;

public class Respuesta extends Publicacion {

	public Respuesta(String tituloRespuesta, String cuerpo, Usuario autor) {
		super(tituloRespuesta, cuerpo, autor);
	}

	@Override
	public boolean sonIguales(Publicacion publicacion) {
		Respuesta respuesta = (Respuesta) publicacion;
		return this.getTitle() == respuesta.getTitle()
				&& this.getBody() == respuesta.getBody()
				&& this.autor() == respuesta.autor();
	}

}
