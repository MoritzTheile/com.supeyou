package com.supeyou.crudie.impl.dtohelper.csv;

public abstract class Automat {

	public static final char PARA = '"';
	public final char SEPERATOR;
	public final char LINEBREAK = '\n';

	private enum STATE {
		OUTSIDE_CELL, IN_CELL, IN_COMMENT_CELL, IN_COMMENT_CELL_UNCLEAR
	}

	private STATE state = STATE.OUTSIDE_CELL;

	public Automat(char seperator) {

		this.SEPERATOR = seperator;

	}

	public void nextToken(char token) {
		if (state.equals(STATE.OUTSIDE_CELL)) {

			if (token == PARA) {
				state = STATE.IN_COMMENT_CELL;
			} else if (token == LINEBREAK) {
				newRow();
			} else {
				state = STATE.IN_CELL;
				nextChar(token);
			}

		} else if (state.equals(STATE.IN_CELL)) {

			if (token == SEPERATOR) {
				state = STATE.OUTSIDE_CELL;
				newColumn();
			} else if (token == LINEBREAK) {
				state = STATE.OUTSIDE_CELL;
				newColumn();
				newRow();
			} else {
				nextChar(token);
			}

		} else if (state.equals(STATE.IN_COMMENT_CELL)) {

			if (token == PARA) {
				state = STATE.IN_COMMENT_CELL_UNCLEAR;
				// System.out.print("(" + token + "->IN_COMMENT_CELL_UNCLEAR)");
			} else {
				nextChar(token);
			}

		} else if (state.equals(STATE.IN_COMMENT_CELL_UNCLEAR)) {

			if (token == SEPERATOR) {
				state = STATE.OUTSIDE_CELL;
				// System.out.print("(" + token + "->OUTSIDE_CELL)");
				newColumn();
			} else if (token == LINEBREAK) {
				state = STATE.OUTSIDE_CELL;
				// System.out.print("(" + token + "->OUTSIDE_CELL)");
				newColumn();
				newRow();
			} else {
				state = STATE.IN_COMMENT_CELL;
				nextChar(PARA); // was swallowed earlier
				nextChar(token);
			}

		}
	}

	public abstract void nextChar(char nextChar);

	public abstract void newColumn();

	public abstract void newRow();
}
