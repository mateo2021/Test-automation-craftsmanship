package Projekt1;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class GameTest {
	
	private Game game;

	@Before
	public void initGame() {
		game = new Game();
	}

	@Test
	public void firstPlayerNotNull() {
		char result = game.Mark;
		assertThat(result).isNotNull();
	}

	@Test
	public void firstPlayersMarkIsX() {
		char result = game.Mark;
		assertEquals(result, 'X');
	}

	@Test
	public void changeFirstPlayerMarkTest() {
		game.changePlayer();
		char result = game.Mark;
		assertEquals(result, 'O');
	}

	@Test
	public void changePlayerMarkFromOtoX() {
		game.Mark = 'O';
		game.changePlayer();
		char result = game.Mark;
		assertThat(result, is('X'));
	}

	@Test
	@Parameters({ "X,X,X, true", "O,O,O, true" })
	public void are3marksSameTestTrue(char mark1, char mark2, char mark3, boolean valid) throws Exception {
		assertThat(game.checkMarks(mark1, mark2, mark3), is(valid));
	}

	@Test
	@FileParameters("test.csv")
	public void are3marksSameTestFalse(char mark1, char mark2, char mark3, boolean valid) throws Exception {
		assertThat(game.checkMarks(mark1, mark2, mark3), is(valid));
	}

	@Test
	public void wrongRowPosition1() {
		try {
			game.addMark(4, 5);
		} catch (Exception expected) {
			assertEquals("Wiersz poza tablica", expected.getMessage());
		}
	}

	@Test
	public void wrongColumnPosition1() {
		try {
			game.addMark(1, 5);
		} catch (Exception expected) {
			assertEquals("Kolumna poza tablica", expected.getMessage());
		}
	}

	@Test
	public void wrongRowPosition2() {
		try {
			game.addMark(-1, 4);
		} catch (Exception expected) {
			assertEquals("Wiersz poza tablica", expected.getMessage());
		}
	}

	@Test
	public void wrongColumnPosition2() {
		try {
			game.addMark(0, 4);
		} catch (Exception expected) {
			assertEquals("Kolumna poza tablica", expected.getMessage());
		}
	}

	@Test
	public void wrongColumnPosition3() {
		try {
			game.addMark(0, -4);
		} catch (Exception expected) {
			assertEquals("Kolumna poza tablica", expected.getMessage());
		}
	}

	@Test
	public void positionOccupiedTest() {
		try {
			game.addMark(0, 0);
			game.addMark(0, 0);
		} catch (Exception expected) {
			assertEquals("Pozycja zajeta lub zly znak", expected.getMessage());
		}
	}

	@Test
	public void firstDiagonalTest() throws Exception {
		game.addMark(0, 0);
		game.addMark(1, 1);
		game.addMark(2, 2);
		boolean result = game.checkDiagonals();
		assertTrue(result);
	}

	@Test
	public void secondDiagonalTest() throws Exception {
		game.addMark(0, 2);
		game.addMark(1, 1);
		game.addMark(2, 0);
		boolean result = game.checkDiagonals();
		assertThat(result).isEqualTo(true);
	}

	@Test
	public void diagonalFalseTest() {
		try {
			game.addMark(0, 0);
			game.addMark(1, 1);
			game.addMark('-', 2);
			game.checkDiagonals();
		} catch (Exception expected) {
			assertEquals("Wiersz poza tablica", expected.getMessage());
		}
	}

	@Test
	public void diagonalFalseTest2() throws Exception {
		game.addMark(1, 2);
		game.addMark(0, 1);
		game.addMark(2, 0);
		boolean result = game.checkDiagonals();
		assertFalse(result);
	}

	@Test
	public void firstRowTest() throws Exception {
		game.addMark(0, 0);
		game.addMark(0, 1);
		game.addMark(0, 2);
		boolean result = game.checkRows();
		assertTrue(result);
	}

	@Test
	public void secondRowTest() throws Exception {
		game.addMark(1, 0);
		game.addMark(1, 1);
		game.addMark(1, 2);
		boolean result = game.checkRows();
		assertThat(result).isEqualTo(true);
	}

	@Test
	public void thirdRowTest() throws Exception {
		game.addMark(2, 0);
		game.addMark(2, 1);
		game.addMark(2, 2);
		boolean result = game.checkRows();
		assertTrue(result);
	}

	@Test
	public void rowTestFail() throws Exception {
		game.addMark(1, 0);
		game.addMark(2, 1);
		game.addMark(2, 2);
		boolean result = game.checkRows();
		assertFalse(result);
	}

	@Test
	public void firstColumnTest() throws Exception {
		game.addMark(0, 0);
		game.addMark(1, 0);
		game.addMark(2, 0);
		boolean result = game.checkColumns();
		assertThat(result).isEqualTo(true);
	}

	@Test
	public void secondColumnTest() throws Exception {
		game.addMark(0, 1);
		game.addMark(1, 1);
		game.addMark(2, 1);
		boolean result = game.checkColumns();
		assertTrue(result);
	}

	@Test
	public void thirdColumnTest() throws Exception {
		game.addMark(0, 2);
		game.addMark(1, 2);
		game.addMark(2, 2);
		boolean result = game.checkColumns();
		assertTrue(result);
	}

	@Test
	public void columnTestFail() throws Exception {
		game.addMark(0, 1);
		game.addMark(1, 2);
		game.addMark(2, 2);
		boolean result = game.checkColumns();
		assertFalse(result);
	}

	@Test
	public void drawTest() throws Exception {
		game.addMark(0, 0);
		game.addMark(0, 1);
		game.addMark(0, 2);
		game.addMark(1, 0);
		game.addMark(1, 1);
		game.addMark(1, 2);
		game.addMark(2, 0);
		game.addMark(2, 1);
		game.addMark(2, 2);
		boolean result = game.isDraw();
		assertTrue(result);
	}

	@Test
	public void drawTestFail() throws Exception {
		game.addMark(0, 0);
		game.addMark(0, 1);
		game.addMark(0, 2);
		game.addMark(1, 0);
		game.addMark(1, 1);
		game.addMark(1, 2);
		game.addMark(2, 0);
		game.addMark(2, 1);
		boolean result = game.isDraw();
		assertFalse(result);
	}

	@Test
	public void testWinnerRow() throws Exception {
		game.addMark(0, 0);
		game.addMark(0, 1);
		game.addMark(0, 2);
		boolean result = game.checkWinner();
		assertThat(result, is(true));
	}

	@Test
	public void testWinnerColumn() throws Exception {
		game.addMark(0, 0);
		game.addMark(1, 0);
		game.addMark(2, 0);
		boolean result = game.checkWinner();
		assertThat(result, is(true));
	}

	@Test
	public void testWinnerDiagonals() throws Exception {
		game.addMark(0, 0);
		game.addMark(1, 1);
		game.addMark(2, 2);
		boolean result = game.checkWinner();
		assertThat(result, is(true));
	}

	@Test
	public void testWinnerDiagonalsFail() throws Exception {
		game.addMark(0, 0);
		game.addMark(1, 1);
		game.addMark(2, 1);
		boolean result = game.checkWinner();
		assertThat(result, is(false));
	}

	@After
	public void teardown() {
		game = null;
	}

}
