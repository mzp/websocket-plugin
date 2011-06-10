/*
 * http://babukuma.com
 * author : babukuma
 * Date : 2009/10/30
 */
package com.babukuma.commons.validate;

/**
 * 日本語チェック
 * 
 * @author babukuma
 */
public class JapaneseValidate {
	private JapaneseValidate() {
	}

	/**
	 * 全角チェック
	 * 
	 * @param str
	 *            チェック元文字列
	 * @return boolean true:全角
	 */
	public static boolean checkZenkaku(String str) {
		if (str == null) {
			return false;
		}

		return str.matches("^[^ -~｡-ﾟ]+$");
	}

	/**
	 * 全角と空白文字チェック
	 * 
	 * @param str
	 *            チェック元文字列
	 * @return boolean true:全角と空白文字
	 */
	public static boolean checkZenkakuWithSpace(String str) {
		if (str == null) {
			return false;
		}

		return str.matches("^([^ -~｡-ﾟ]|\\s)+$");
	}

	/**
	 * 半角チェック
	 * 
	 * @param str
	 *            チェック元文字列
	 * @return boolean true:半角
	 */
	public static boolean checkHankaku(String str) {
		if (str == null) {
			return false;
		}

		return str.matches("^[ -~｡-ﾟ]+$");
	}

	/**
	 * 半角と空白文字チェック
	 * 
	 * @param str
	 *            チェック元文字列
	 * @return boolean true:半角と空白文字
	 */
	public static boolean checkHankakuWithSpace(String str) {
		if (str == null) {
			return false;
		}

		return str.matches("^([ -~｡-ﾟ]|\\s)+$");
	}

	/**
	 * 半角カナチェック
	 * 
	 * @param str
	 *            チェック元文字列
	 * @return boolean true:半角カナのみ
	 */
	public static boolean checkHankakuKana(String str) {
		if (str == null) {
			return false;
		}

		return str.matches("^[ｱ-ﾝﾞﾟ]+$");
	}

	/**
	 * 半角カナと空白文字チェック
	 * 
	 * @param str
	 *            チェック元文字列
	 * @return boolean true:半角カナと空白文字
	 */
	public static boolean checkHankakuKanaWithSpace(String str) {
		if (str == null) {
			return false;
		}

		return str.matches("^([ｱ-ﾝﾞﾟ]|\\s)+$");
	}
}
