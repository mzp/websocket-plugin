/*
 * http://babukuma.com
 * author : babukuma
 * Date : 2009/11/16
 */
package com.babukuma.commons.utils;

/**
 * @author babukuma
 */
public class JapaneseUtils {
	private static final char[] ZENKAKU_KATAKANA_1 = { 'ァ', 'ア', 'ィ', 'イ', 'ゥ',
			'ウ', 'ェ', 'エ', 'ォ', 'オ', 'カ', 'ガ', 'キ', 'ギ', 'ク', 'グ', 'ケ', 'ゲ',
			'コ', 'ゴ', 'サ', 'ザ', 'シ', 'ジ', 'ス', 'ズ', 'セ', 'ゼ', 'ソ', 'ゾ', 'タ',
			'ダ', 'チ', 'ヂ', 'ッ', 'ツ', 'ヅ', 'テ', 'デ', 'ト', 'ド', 'ナ', 'ニ', 'ヌ',
			'ネ', 'ノ', 'ハ', 'バ', 'パ', 'ヒ', 'ビ', 'ピ', 'フ', 'ブ', 'プ', 'ヘ', 'ベ',
			'ペ', 'ホ', 'ボ', 'ポ', 'マ', 'ミ', 'ム', 'メ', 'モ', 'ャ', 'ヤ', 'ュ', 'ユ',
			'ョ', 'ヨ', 'ラ', 'リ', 'ル', 'レ', 'ロ', 'ヮ', 'ワ', 'ヰ', 'ヱ', 'ヲ', 'ン',
			'ヴ', 'ヵ', 'ヶ' };

	private static final String[] HANKAKU_KATAKANA_1 = { "ｧ", "ｱ", "ｨ", "ｲ",
			"ｩ", "ｳ", "ｪ", "ｴ", "ｫ", "ｵ", "ｶ", "ｶﾞ", "ｷ", "ｷﾞ", "ｸ", "ｸﾞ", "ｹ",
			"ｹﾞ", "ｺ", "ｺﾞ", "ｻ", "ｻﾞ", "ｼ", "ｼﾞ", "ｽ", "ｽﾞ", "ｾ", "ｾﾞ", "ｿ",
			"ｿﾞ", "ﾀ", "ﾀﾞ", "ﾁ", "ﾁﾞ", "ｯ", "ﾂ", "ﾂﾞ", "ﾃ", "ﾃﾞ", "ﾄ", "ﾄﾞ",
			"ﾅ", "ﾆ", "ﾇ", "ﾈ", "ﾉ", "ﾊ", "ﾊﾞ", "ﾊﾟ", "ﾋ", "ﾋﾞ", "ﾋﾟ", "ﾌ",
			"ﾌﾞ", "ﾌﾟ", "ﾍ", "ﾍﾞ", "ﾍﾟ", "ﾎ", "ﾎﾞ", "ﾎﾟ", "ﾏ", "ﾐ", "ﾑ", "ﾒ",
			"ﾓ", "ｬ", "ﾔ", "ｭ", "ﾕ", "ｮ", "ﾖ", "ﾗ", "ﾘ", "ﾙ", "ﾚ", "ﾛ", "ﾜ",
			"ﾜ", "ｲ", "ｴ", "ｦ", "ﾝ", "ｳﾞ", "ｶ", "ｹ" };

	private static final char ZENKAKU_START = ZENKAKU_KATAKANA_1[0];
	private static final char ZENKAKU_END = ZENKAKU_KATAKANA_1[ZENKAKU_KATAKANA_1.length - 1];

	private static final char[] HANKAKU_KATAKANA_2 = { '｡', '｢', '｣', '､', '･',
			'ｦ', 'ｧ', 'ｨ', 'ｩ', 'ｪ', 'ｫ', 'ｬ', 'ｭ', 'ｮ', 'ｯ', 'ｰ', 'ｱ', 'ｲ',
			'ｳ', 'ｴ', 'ｵ', 'ｶ', 'ｷ', 'ｸ', 'ｹ', 'ｺ', 'ｻ', 'ｼ', 'ｽ', 'ｾ', 'ｿ',
			'ﾀ', 'ﾁ', 'ﾂ', 'ﾃ', 'ﾄ', 'ﾅ', 'ﾆ', 'ﾇ', 'ﾈ', 'ﾉ', 'ﾊ', 'ﾋ', 'ﾌ',
			'ﾍ', 'ﾎ', 'ﾏ', 'ﾐ', 'ﾑ', 'ﾒ', 'ﾓ', 'ﾔ', 'ﾕ', 'ﾖ', 'ﾗ', 'ﾘ', 'ﾙ',
			'ﾚ', 'ﾛ', 'ﾜ', 'ﾝ', 'ﾞ', 'ﾟ' };

	private static final char[] ZENKAKU_KATAKANA_2 = { '。', '「', '」', '、', '・',
			'ヲ', 'ァ', 'ィ', 'ゥ', 'ェ', 'ォ', 'ャ', 'ュ', 'ョ', 'ッ', 'ー', 'ア', 'イ',
			'ウ', 'エ', 'オ', 'カ', 'キ', 'ク', 'ケ', 'コ', 'サ', 'シ', 'ス', 'セ', 'ソ',
			'タ', 'チ', 'ツ', 'テ', 'ト', 'ナ', 'ニ', 'ヌ', 'ネ', 'ノ', 'ハ', 'ヒ', 'フ',
			'ヘ', 'ホ', 'マ', 'ミ', 'ム', 'メ', 'モ', 'ヤ', 'ユ', 'ヨ', 'ラ', 'リ', 'ル',
			'レ', 'ロ', 'ワ', 'ン', '゛', '゜' };

	private static final char HANKAKU_START = HANKAKU_KATAKANA_2[0];
	private static final char HANKAKU_END = HANKAKU_KATAKANA_2[HANKAKU_KATAKANA_2.length - 1];

	public static String convertHanKana2zenKana(final String text) {
		if (text == null)
			return null;

		StringBuilder zenkaku = new StringBuilder();

		for (int index = 0; index < text.length(); index++) {
			char c = text.charAt(index);

			if (c >= HANKAKU_START && c <= HANKAKU_END) {
				if (zenkaku.length() > 0 && c == 'ﾞ') {
					char prevChar = text.charAt(index - 1);
					if ("ｶｷｸｹｺｻｼｽｾｿﾀﾁﾂﾃﾄﾊﾋﾌﾍﾎｳ".indexOf(prevChar) >= 0) {
						switch (prevChar) {
						case 'ｶ':
							zenkaku.deleteCharAt(zenkaku.length() - 1);
							zenkaku.append('ガ');
							break;
						case 'ｷ':
							zenkaku.deleteCharAt(zenkaku.length() - 1);
							zenkaku.append('ギ');
							break;
						case 'ｸ':
							zenkaku.deleteCharAt(zenkaku.length() - 1);
							zenkaku.append('グ');
							break;
						case 'ｹ':
							zenkaku.deleteCharAt(zenkaku.length() - 1);
							zenkaku.append('ゲ');
							break;
						case 'ｺ':
							zenkaku.deleteCharAt(zenkaku.length() - 1);
							zenkaku.append('ゴ');
							break;
						case 'ｻ':
							zenkaku.deleteCharAt(zenkaku.length() - 1);
							zenkaku.append('ザ');
							break;
						case 'ｼ':
							zenkaku.deleteCharAt(zenkaku.length() - 1);
							zenkaku.append('ジ');
							break;
						case 'ｽ':
							zenkaku.deleteCharAt(zenkaku.length() - 1);
							zenkaku.append('ズ');
							break;
						case 'ｾ':
							zenkaku.deleteCharAt(zenkaku.length() - 1);
							zenkaku.append('ゼ');
							break;
						case 'ｿ':
							zenkaku.deleteCharAt(zenkaku.length() - 1);
							zenkaku.append('ゾ');
							break;
						case 'ﾀ':
							zenkaku.deleteCharAt(zenkaku.length() - 1);
							zenkaku.append('ダ');
							break;
						case 'ﾁ':
							zenkaku.deleteCharAt(zenkaku.length() - 1);
							zenkaku.append('ヂ');
							break;
						case 'ﾂ':
							zenkaku.deleteCharAt(zenkaku.length() - 1);
							zenkaku.append('ヅ');
							break;
						case 'ﾃ':
							zenkaku.deleteCharAt(zenkaku.length() - 1);
							zenkaku.append('デ');
							break;
						case 'ﾄ':
							zenkaku.deleteCharAt(zenkaku.length() - 1);
							zenkaku.append('ド');
							break;
						case 'ﾊ':
							zenkaku.deleteCharAt(zenkaku.length() - 1);
							zenkaku.append('バ');
							break;
						case 'ﾋ':
							zenkaku.deleteCharAt(zenkaku.length() - 1);
							zenkaku.append('ビ');
							break;
						case 'ﾌ':
							zenkaku.deleteCharAt(zenkaku.length() - 1);
							zenkaku.append('ブ');
							break;
						case 'ﾍ':
							zenkaku.deleteCharAt(zenkaku.length() - 1);
							zenkaku.append('ベ');
							break;
						case 'ﾎ':
							zenkaku.deleteCharAt(zenkaku.length() - 1);
							zenkaku.append('ボ');
							break;
						case 'ｳ':
							zenkaku.deleteCharAt(zenkaku.length() - 1);
							zenkaku.append('ヴ');
							break;
						}
					}
				} else if (zenkaku.length() > 0 && c == 'ﾟ') {
					char prevChar = text.charAt(index - 1);

					if ("ﾊﾋﾌﾍﾎ".indexOf(prevChar) >= 0) {
						switch (prevChar) {
						case 'ﾊ':
							zenkaku.deleteCharAt(zenkaku.length() - 1);
							zenkaku.append('パ');
							break;
						case 'ﾋ':
							zenkaku.deleteCharAt(zenkaku.length() - 1);
							zenkaku.append('ピ');
							break;
						case 'ﾌ':
							zenkaku.deleteCharAt(zenkaku.length() - 1);
							zenkaku.append('プ');
							break;
						case 'ﾍ':
							zenkaku.deleteCharAt(zenkaku.length() - 1);
							zenkaku.append('ペ');
							break;
						case 'ﾎ':
							zenkaku.deleteCharAt(zenkaku.length() - 1);
							zenkaku.append('ポ');
							break;
						}
					}
				} else {
					zenkaku.append(ZENKAKU_KATAKANA_2[c - HANKAKU_START]);
				}
			} else {
				zenkaku.append(c);
			}
		}

		return zenkaku.toString();
	}

	/**
	 * 全角カナを半角ｶﾅに変換する
	 * 
	 * @param text
	 * @return
	 */
	public static String convertZenKana2hanKana(final String text) {
		if (text == null)
			return null;

		StringBuilder hankaku = new StringBuilder();

		for (int index = 0; index < text.length(); index++) {
			char c = text.charAt(index);

			if (c >= ZENKAKU_START && c <= ZENKAKU_END) {
				hankaku.append(HANKAKU_KATAKANA_1[c - ZENKAKU_START]);
			} else {
				hankaku.append(c);
			}
		}

		return hankaku.toString();
	}
}
