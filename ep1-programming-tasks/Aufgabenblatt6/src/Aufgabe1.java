/*
    Aufgabe 1) Zweidimensionale Arrays und Gameplay - Sokoban
*/

import java.awt.*;
import java.awt.event.KeyEvent;

@SuppressWarnings({"CommentedOutCode", "unused"})
public final class Aufgabe1 {
	private static int LEVEL_ID = 0;
	private static final int SQUARE_SIZE = 40;

    public static void main(String[] args) {
        String[] allLevels = readLevels();
        int[][] goals = new int[numberOfGoals(allLevels[LEVEL_ID])][];
        char[][] level = newLevel(goals, allLevels[LEVEL_ID]);
        boolean gameRunning = true;
        int moveDirection = 0;
        int stepsLevel = 0;
        int stepsTotal = 0;

        setWindowSize(level.length, level[0].length);
        StdDraw.setPenRadius(0.01);
        StdDraw.enableDoubleBuffering();

        drawGame(level, goals);

        while (gameRunning) {
            // up -> right up
            // down -> left down
            // left -> left up
            // right -> right down
            // restart -> r
            // to next level -> t
            if (StdDraw.isKeyPressed(KeyEvent.VK_UP)) {
                moveDirection = 1;
            } else if (StdDraw.isKeyPressed(KeyEvent.VK_DOWN)) {
                moveDirection = 2;
            } else if (StdDraw.isKeyPressed(KeyEvent.VK_LEFT)) {
                moveDirection = 3;
            } else if (StdDraw.isKeyPressed(KeyEvent.VK_RIGHT)) {
                moveDirection = 4;
            } else if (StdDraw.isKeyPressed(KeyEvent.VK_R)) {
                stepsLevel = 0;
                goals = new int[numberOfGoals(allLevels[LEVEL_ID])][];
                level = newLevel(goals, allLevels[LEVEL_ID]);
                drawGame(level, goals);
                StdDraw.pause(200);
            } else if (StdDraw.isKeyPressed(KeyEvent.VK_T)) {
                if (LEVEL_ID < allLevels.length - 1) { // skip to next level
                    stepsLevel = 0;
	                LEVEL_ID++;
                    goals = new int[numberOfGoals(allLevels[LEVEL_ID])][];
                    level = newLevel(goals, allLevels[LEVEL_ID]);
                    setWindowSize(level.length, level[0].length);
                    drawGame(level, goals);
                    StdDraw.pause(200);
                } else { // end game
                    gameRunning = false;
                    showText(level[0].length * SQUARE_SIZE / 2.0, level.length * SQUARE_SIZE / 2.0, "YOU WON!!! Total steps: " + stepsTotal);
                }
            }

            if (moveDirection != 0) {
                if (move(level, moveDirection)) {
                    stepsLevel++;
                }
                moveDirection = 0;
                drawGame(level, goals);
                if (won(level, goals)) {
                    showText(level[0].length * SQUARE_SIZE / 2.0, level.length * SQUARE_SIZE / 2.0, "Steps: " + stepsLevel);
                    StdDraw.pause(2000);
                    stepsTotal += stepsLevel;
                    stepsLevel = 0;
                    if (LEVEL_ID < allLevels.length - 1) { // load next level
	                    LEVEL_ID++;
                        goals = new int[numberOfGoals(allLevels[LEVEL_ID])][];
                        level = newLevel(goals, allLevels[LEVEL_ID]);
                        setWindowSize(level.length, level[0].length);
                        drawGame(level, goals);
                    } else { // end game
                        gameRunning = false;
                        showText(level[0].length * SQUARE_SIZE / 2.0, level.length * SQUARE_SIZE / 2.0, "YOU WON!!! Total steps: " + stepsTotal);
                    }
                }
                StdDraw.pause(200);
            }
        }
    }

    // reads levels from file / first line is number of levels
    private static String[] readLevels() {
        In reader = new In("sokoban_level.csv");
        int numberOfLevels = reader.readInt();
        int counter = -1; // starts at -1 because first line is empty after reading int
        String[] levels = new String[numberOfLevels];
        while (!reader.isEmpty()) {
            String line = reader.readLine();
            if (line.isEmpty()) {
                counter++;
                levels[counter] = "";
            } else {
                levels[counter] += line + "\n";
            }
        }
        return levels;
    }

    // returns level as char array and fills goal positions into goals array
    private static char[][] newLevel(int[][] goals, String levelString) {
        // calculate array size
        int xSize = 0;
        int ySize = 0;
        int counter = 0;

        for (int i = 0; i < levelString.length(); i++) {
            if (levelString.charAt(i) == '\n') {
                ySize++;
                if (counter > xSize) {
                    xSize = counter;
                }
                counter = 0;
            } else {
                counter++;
            }
        }

        // fill array and goals
        char[][] levelArr = new char[ySize][xSize];
        int goalCounter = 0;
        int x = 0;
        int y = 0;

        for (int i = 0; i < levelString.length(); i++) {
            char item = levelString.charAt(i);
            switch (item) {
                case '.':
                    levelArr[y][x] = ' ';
                    goals[goalCounter] = new int[]{x, y};
                    goalCounter++;
                    x++;
                    break;
                case '\n':
                    y++;
                    x = 0;
                    break;
                case '#':
                case '$':
                case '@':
                case ' ':
                    levelArr[y][x] = item;
                    x++;
                    break;
                default:
                    break;
            }
        }

        return levelArr;
    }

    // calculates based on the current position and the direction the new position coordinates
    private static int[] adjacentPosition(int[] position, int direction) {
        switch(direction) {
            case 1:
	            return new int[]{position[0] - 1, position[1]};
            case 2:
	            return new int[]{position[0] + 1, position[1]};
            case 3:
	            return new int[]{position[0], position[1] - 1};
            case 4:
	            return new int[]{position[0], position[1] + 1};
        }
        return new int[]{-1, -1};
    }

	// helping method to set the StdDraw window size and the scaling of the axis
	private static void setWindowSize(int ySquares, int xSquares) {
		StdDraw.setCanvasSize(SQUARE_SIZE * xSquares, SQUARE_SIZE * ySquares);
		StdDraw.setXscale(0, SQUARE_SIZE * xSquares);
		StdDraw.setYscale(0, SQUARE_SIZE * ySquares);
	}

	// helping method for writing text in the StdDraw window
	private static void showText(double x, double y, String text) {
		StdDraw.clear(Color.white);
		StdDraw.setPenColor(Color.black);
		StdDraw.text(x, y, text);
		StdDraw.show();
	}

	/* ******************************** */
	/* *********** METHODS ************ */
	/* ******************************** */

	/* Vorbedingungen: levelString != null und levelString.length > 0 */
	private static int numberOfGoals(String levelString) {
		int sum = 0;
		for(char c: levelString.toCharArray()) {
			if(c == '.') sum++;
		}
		return sum;

		// mit Streams (Java 8+):
		// return (int) levelString.chars().filter(ch -> ch == '.').count();
	}

	/* Vorbedingung: level != null und level[i] != null fur alle gültigen Indizes i */
    private static int[] figurePosition(char[][] level) {
    	for(int x = 0; x < level.length; x++) {
    		for(int y = 0; y < level[x].length; y++) {
				if(level[x][y] == '@') {
				    return new int[]{x, y};
			    }
		    }
	    }

        return new int[]{-1, -1};
    }

	/*
		Vorbedingungen: level != null, level[i] != null fur alle gültigen Indizes i,
						direction > 0 und direction < 5
	*/

    private static boolean move(char[][] level, int direction) {
	    int[] oldPosition = figurePosition(level),
		      newPosition = adjacentPosition(oldPosition, direction);

	    boolean playerCanMove = false;

        switch(level[newPosition[0]][newPosition[1]]) {
        	case ' ': playerCanMove = true; break;
		    case '$': int[] boxPosition = adjacentPosition(newPosition, direction);
				      if(level[boxPosition[0]][boxPosition[1]] == ' ') {
					      playerCanMove = true;
					      level[boxPosition[0]][boxPosition[1]] = '$';
					  }
	    }

	    if(playerCanMove) {
		    level[oldPosition[0]][oldPosition[1]] = ' ';
		    level[newPosition[0]][newPosition[1]] = '@';
	    }
    	return playerCanMove;
    }

    /*
        Vorbedingungen: level != null, level[i] != null fur alle gültigen Indizes i und
						numberOfBoxes > 0
    */
    private static int[][] boxPositions(char[][] level, int numberOfBoxes) {
	    int[][] output = new int[numberOfBoxes][];
	    int firstEmptyIndex = 0;

	    for(int x = 0; x < level.length || firstEmptyIndex < numberOfBoxes - 1; x++) {
		    for(int y = 0; y < level[x].length; y++) {
			    if(level[x][y] == '$') {
				    output[firstEmptyIndex++] = new int[]{x, y};
			    }
		    }
	    }

        return output;
    }

	/*
		Vorbedingungen: level != null, level[i] != null fur alle gültigen Indizes i,
						goals != null und goals[i] != null fur alle gültigen Indizes i
	*/
	/*
		Annahme: goals[0] und goals[1] liegt in level
	*/
    private static boolean won(char[][] level, int[][] goals) {
	    for(int[] c: goals) {
		    if(level[c[1]][c[0]] != '$') {
			    return false;
		    }
	    }
	    return true;

	    /* oder */

	    /*

	    try {
		    for(int[] c: goals) {
			    if(level[c[1]][c[0]] != '$') {
				    return false;
			    }
		    }
	    } catch(ArrayIndexOutOfBoundsException ignored) {
		    return false;
	    }

	    */
    }

	/*
		Vorbedingungen: level != null, level[i] != null fur alle gültigen Indizes i,
		goals != null und goals[i] != null fur alle gültigen Indizes i
	*/
	/*
		Annahme: goals[0] und goals[1] liegt in level
	*/
    private static void drawGame(char[][] level, int[][] goals) {
    	StdDraw.setPenColor(Color.WHITE);
    	int halfSquare = SQUARE_SIZE / 2;

	    for(int i = 0; i < level.length; i++) {
		    for(int j = 0; j < level[i].length; j++) {
		    	int x = (j * SQUARE_SIZE) + halfSquare,
					y = ((level.length - 1 - i) * SQUARE_SIZE) + halfSquare;

		    	String object = null;

			    switch(level[i][j]) {
				    case ' ': StdDraw.filledRectangle(x, y, halfSquare, halfSquare);
						      break;
				    case '#': object = "wall";
				    	      break;
				    case '$': object = "box";
				    	      break;
				    case '@': object = "figure";
				    	      break;
			    }

			    if(object != null) {
				    StdDraw.picture(x, y, object + ".png", SQUARE_SIZE, SQUARE_SIZE);
			    }
		    }
	    }

	    for(int[] goal: goals) {
		    int x = (goal[0] * SQUARE_SIZE) + halfSquare,
			    y = ((level.length - goal[1]) * SQUARE_SIZE) - halfSquare;

		    if(level[goal[1]][goal[0]] != '$' &&
			       level[goal[1]][goal[0]] != '@') {

			    StdDraw.picture(x, y, "endpoint.png", SQUARE_SIZE, SQUARE_SIZE);
		    }
	    }

	    /* oder */

	    /*

	    try {
		    for(int[] goal: goals) {
			    int x = (goal[0] * SQUARE_SIZE) + halfSquare,
				    y = ((level.length - goal[1]) * SQUARE_SIZE) - halfSquare;

			    if(level[goal[1]][goal[0]] != '$' &&
				   level[goal[1]][goal[0]] != '@') {

				    StdDraw.picture(x, y, "endpoint.png", SQUARE_SIZE, SQUARE_SIZE);
			    }
		    }
	    } catch(ArrayIndexOutOfBoundsException ignored) {};

	    */

	    StdDraw.show();
    }
}