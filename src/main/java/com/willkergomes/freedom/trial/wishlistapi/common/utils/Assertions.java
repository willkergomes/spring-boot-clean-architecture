package com.willkergomes.freedom.trial.wishlistapi.common.utils;

import java.util.List;

public final class Assertions {

	private Assertions() {
	}

	public static boolean assertNull(Object string) {
		return string == null;
	}

	public static boolean assertNotNull(Object string) {
		return string != null;
	}

	public static boolean assertNullOrEmpty(String string) {
		return assertNull(string) || string.trim().isEmpty();
	}

	public static boolean assertEqualsNullString(String string) {
		return assertNotNull(string) && string.equals("null");
	}

	public static boolean assertNullOrEmpty(List<?> list) {
		return list == null || list.isEmpty();
	}

}
