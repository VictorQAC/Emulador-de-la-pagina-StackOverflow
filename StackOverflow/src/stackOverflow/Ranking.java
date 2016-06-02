package stackOverflow;

import java.util.ArrayList;
import java.util.Collections;

public class Ranking {
	private ArrayList<Double> ranking;

	public Ranking() {
		this.ranking = new ArrayList<Double>();
	}

	public void agregarAlRanking(double puntajeParaElRanking) {
		// TODO Auto-generated method stub
		double rank = puntajeParaElRanking;

		if (this.ranking.contains(rank) == false) {
			this.ranking.add(rank);
			Collections.sort(this.ranking);
		}

		/*
		 * si tengo una lista de numeros; cada usuario tiene un numero; se puede
		 * repetir; el numero da una posicion en el ranking; si quiero saber
		 * cual es la posicion del usuario; le pregunto su puntaje; miro que
		 * posicion tiene el ranking para ese puntaje; le digo al usuario su
		 * puntaje; tengo que fijarme el puntaje de todos los usuarios; agregar
		 * esos punts a una lista ordenada de mayor a menor; cada vez que un
		 * usuario publique se agrega su puntaje a la lista de puntos; con
		 * idexof obtengo la posicion del elemento
		 */
	}

	public ArrayList<Double> ranking() {
		return this.ranking;
	}

	public int tamanhoDelRanking() {
		return this.ranking.size();
	}

}
