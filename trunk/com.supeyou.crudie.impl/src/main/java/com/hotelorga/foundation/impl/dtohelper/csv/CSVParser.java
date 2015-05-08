package com.hotelorga.foundation.impl.dtohelper.csv;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CSVParser {

	public static List<List<String>> parseCSV(char seperator, InputStream csvInputStream, Charset charset) throws Exception {
		final List<List<String>> table = new ArrayList<>();
		Automat automat = new Automat(seperator) {

			List<String> currentRow = new ArrayList<>();
			String currentCell = "";

			@Override
			public void nextChar(char nextChar) {
				currentCell += nextChar;
			}

			@Override
			public void newColumn() {
				currentRow.add(currentCell);
				currentCell = "";

			}

			@Override
			public void newRow() {
				table.add(currentRow);
				currentRow = new ArrayList<>();

			}
		};
		InputStreamReader reader = new InputStreamReader(csvInputStream, charset);

		boolean previousWasR = false;
		int intChar;
		try {
			while ((intChar = reader.read()) != -1) {

				char ch = (char) intChar;

				if (previousWasR) {
					if (!isN(ch)) {
						automat.nextToken('r');
						automat.nextToken(ch);
					} else {
						automat.nextToken('\n');
					}
					previousWasR = false;

				} else {
					if (isR(ch)) {
						previousWasR = true;
					} else {
						automat.nextToken(ch);
					}
				}
			}
			// forcing last line:
			automat.nextToken('\r');
		} finally {
			reader.close();
		}
		return table;
	}

	private static boolean isR(char ch) {
		if (ch == '\r') {
			return true;
		}

		return false;
	}

	private static boolean isN(char ch) {
		if (ch == '\n') {
			return true;
		}

		return false;
	}

	public static void main(String[] args) throws Exception {

		InputStream inputStream = ClassLoader.getSystemResourceAsStream("michaeltest_auditions.csv");

		Set<Integer> lengths = new HashSet<>();
		try {
			for (List<String> row : parseCSV(';', inputStream, Charset.forName("UTF-8"))) {
				System.out.println(row);
				lengths.add(new Integer(row.size()));
			}
		} finally {
			inputStream.close();
		}
		System.out.println(lengths);

	}
}
