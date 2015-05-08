public class JustAMain {

	public static void main(String[] args) {

		// positive tests:
		{
			String string = "GIRL ON FIRE";
			assertTrue(string.matches(inputString2regEx("^GIRL*")));
			// Title begins with GIRL. Filter should accept it.
		}

		// "GIRLS PLAY WITH FIRE"
		// // "Title starts with GIRLS and ends with FIRE. GIRL has a wildcard at the end but the title has two words in the middle."
		//
		// "GIRLS PLAY WITH FIRE"
		// "GIRL* * FIRE"
		//
		// // assertTrue(
		// // "Title starts with GIRLS and ends with FIRE. Wildcard in the middle should accept all other words."
		//
		//
		// "GIRLS PLAY WITH FIRE").build();
		// //
		// WorkFilter workFilter = new FilterBuilder()
		// .withWildcard("*IRLS * FIRE").buildTitleWildcard();
		//
		// assertTrue(
		// "Title starts with GIRLS and ends with FIRE. Wildcard on the start of IRLS and in the middle should accept all other words."
		// + "Filter should accept it.", workFilter.accept(work));
		// }
		//
		// @Test
		// public void testWorkAndFilterRangeWithWildcard4() throws Exception {
		// Work work = new WorkBuilder().withTitle("GIRLS PLAY WITH FIRE").build();
		//
		// WorkFilter workFilter = new FilterBuilder().withWildcard("*IRLS* FIRE")
		// .buildTitleWildcard();
		//
		// assertFalse(
		// "Title starts with GIRLS and ends with FIRE. Wildcard on the start of IRLS "
		// + "but not in the middle should not accept all other words."
		// + "Filter should not accept it.",
		// workFilter.accept(work));
		// }
		//
		// @Test
		// public void testWorkAndFilterRangeWithWildcard5() throws Exception {
		// Work work = new WorkBuilder().withTitle("GIRL ON FIRE").build();
		//
		// WorkFilter workFilter = new FilterBuilder().withWildcard(
		// "GIRL* ON FIRE").buildTitleWildcard();
		//
		// assertTrue(
		// "Title starts with GIRL and ends with FIRE. Wildcard on the end of GIRL."
		// + "Filter should accept it.", workFilter.accept(work));
		// }
		//
		// @Test
		// public void testWorkAndFilterRangeWithWildcard6() throws Exception {
		// Work work = new WorkBuilder().withTitle("THE POWER OF LOVE").build();
		//
		// WorkFilter workFilter = new FilterBuilder().withWildcard(
		// "^THE POW* OF LOVE").buildTitleWildcard();
		//
		// assertTrue(
		// "Title starts with THE and contains POWER OF LOVE."
		// + "Filter should accept it.", workFilter.accept(work));
		// }

	}

	private static String inputString2regEx(String string) {

		String debugString = string + " -> ";

		String regEx = string.replaceAll("\\*", ".*");

		debugString += regEx;

		System.out.println(debugString);

		return regEx;
	}

	private static void assertTrue(boolean b) {
		String message = "";
		if (b) {
			message += "OK   ";
		} else {
			message += "ERROR";
		}

		message += " (line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + ")";

		System.out.println(message);
	}

}
