/*
    Aufgabe 5) Eindimensionale Arrays und File I/O
*/

import java.awt.*;

public class Aufgabe5 {
	private static String[] readFileData(String fileName, int lineStart, int lineEnd) {
		In fileReader = new In(fileName);
		String[] fileContent = new String[lineEnd - lineStart + 1];
		for (int i = 1; i < lineStart; i++) {
			fileReader.readLine(); // remove lines before lineStart
		}
		for (int i = 0; i < (lineEnd - lineStart + 1); i++) {
			fileContent[i] = fileReader.readLine(); // add lines to fileContent
		}
		return fileContent;
	}

	private static void extractData(String[] dataArray, int[] resultArray, int numColumn, int entriesPerYear) {
		for (int i = 0; i < resultArray.length; i++) {
			int sumPerYear = 0;
			for (int j = 0; j < entriesPerYear; j++) {
				String[] columns = dataArray[(i * entriesPerYear) + j].split(";");
				sumPerYear += Integer.parseInt(columns[numColumn]);
			}
			resultArray[i] = sumPerYear;
		}
	}

	private static void drawChart(int[] sunHours) {
		int windowWidth = 1280;
		int windowHeight = 620;
		StdDraw.setCanvasSize(windowWidth, windowHeight);
		StdDraw.setXscale(0, windowWidth);
		StdDraw.setYscale(0, windowHeight);
		StdDraw.enableDoubleBuffering();

		StdDraw.setFont(new Font("Times", Font.PLAIN, 10));

		/* **************************************************************************************** */

		// orange rectangles (bars)
		StdDraw.setPenColor(StdDraw.ORANGE);
		for (int i = 0; i < sunHours.length; i++) {
			StdDraw.filledRectangle(
				(30 + i * 20 + 15 / 2.0),
				(sunHours[i] / 8.0 + 5),
				(15 / 2.0),
				(sunHours[i] / 8.0)
			);
		}

		/* **************************************************************************************** */

		// black guiding line
		StdDraw.setPenRadius(0.005);
		StdDraw.setPenColor(StdDraw.BLACK);
		for (int i = 0; i < sunHours.length - 1; i++) {
			StdDraw.line(
				30 + i * 20 + 15 / 2.0, (sunHours[i] / 4.0) + 5,
				30 + (i + 1) * 20 + 15 / 2.0, (sunHours[i + 1] / 4.0) + 5
			);
		}

		/* **************************************************************************************** */

		// min / max line
		StdDraw.setPenRadius(0.002);
		int[] minMax = {getMinValue(sunHours), getMaxValue(sunHours)};

		for(int i = 0; i < minMax.length; i++) {
			int value = minMax[i];
			StdDraw.line(
				30, (value / 4.0) + 5,
				windowWidth - 30, (value / 4.0) + 5
			);
			StdDraw.text(15, (value / 4.0) + 5, Integer.toString(value));
			StdDraw.text(windowWidth - 15, (value / 4.0) + 5, Integer.toString(value));
		}

		/* **************************************************************************************** */

		// column with year
		for (int i = 0; i < sunHours.length; i++) {
			StdDraw.text(30 + (i * 20) + (15 / 2.0), 15, String.format("%02d", (i + 55) % 100));
		}

		/* **************************************************************************************** */

		StdDraw.show();
	}

	private static int getMinValue(int[] values) {
		int min = values[0];
		for (int value : values) {
			if (value < min) min = value;
		}
		return min;
	}

	private static int getMaxValue(int[] values) {
		int max = values[0];
		for (int value : values) {
			if (value > max) max = value;
		}
		return max;
	}

	public static void main(String[] args) {
		String[] data = readFileData("weather_data.csv", 2, 733);
		int[] resultArray = new int[61];

		extractData(data, resultArray, 16, 12);
		drawChart(resultArray);
	}
}
