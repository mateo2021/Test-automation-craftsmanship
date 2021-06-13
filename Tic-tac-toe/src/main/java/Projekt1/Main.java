package Projekt1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.Clock;

public class Main {

	public static void main(String[] args) throws Exception {
		Game game = new Game();
		System.out.println("Rozpoczynamy nowa gre");
		Clock clock = Clock.systemDefaultZone();
		long start = clock.millis();

		for (int i = 0; i < 10; i++) {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Wybierz pozycje dla " + game.Mark + ":");

			int a = Integer.parseInt(br.readLine());
			int b = Integer.parseInt(br.readLine());

			game.addMark(a, b);

			if (game.checkWinner()) {
				System.out.println("Wygrales!");
				long elapsedTime = (clock.millis() - start);
				System.out.println("Calkowity czas gry: " + elapsedTime * 0.001 + " sekund.");
				System.exit(0);
			} else if (game.isDraw()) {
				System.out.println("Porazka ... !");
				long elapsedTime = (clock.millis() - start);
				System.out.println("Calkowity czas gry: " + elapsedTime * 0.001 + " sekund.");
				System.exit(0);
			}
			game.changePlayer();
		}
	}
}
