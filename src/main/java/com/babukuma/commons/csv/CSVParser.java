/*
 * http://babukuma.com
 * author : babukuma
 * Date : 2009/11/10
 */
package com.babukuma.commons.csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * CSV Parser
 * 
 * @author babukuma
 */
public class CSVParser {
	private static final String PATTERN_COMMA_DOUBLE_QUATATION = ",?(\"([^\"]*(?:\"\"[^\"]*)*)\"|[^,]*)";
	private static final String PATTERN_TAB_DOUBLE_QUATATION = "\\t?(\"([^\"]*(?:\"\"[^\"]*)*)\"|[^\\t]*)";

	/**
	 * CSV -&gt; List&lt;String[]&gt;
	 * 
	 * @param file
	 *            CSV File
	 * @return
	 * @throws IOException
	 */
	public static List<String[]> csv2array(final File file) throws IOException {
		return parse2Array(new FileReader(file), false);
	}

	/**
	 * Reader -&gt; List&lt;String[]&gt;
	 * 
	 * @param reader
	 * @return
	 * @throws IOException
	 */
	public static List<String[]> csv2array(final Reader reader)
			throws IOException {
		return parse2Array(reader, false);
	}

	/**
	 * InputStream -&gt; List&lt;String[]&gt;
	 * 
	 * @param in
	 * @return
	 * @throws IOException
	 */
	public static List<String[]> csv2array(final InputStream in)
			throws IOException {
		return parse2Array(new InputStreamReader(in), false);
	}

	/**
	 * CSV -&gt; List&lt;String[]&gt;
	 * 
	 * @param csv
	 *            CSV Format text
	 * @return
	 * @throws IOException
	 */
	public static List<String[]> csv2array(final String csv) throws IOException {
		return parse2Array(new StringReader(csv), false);
	}

	/**
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static List<String[]> tsv2array(final File file) throws IOException {
		return parse2Array(new FileReader(file), true);
	}

	public static List<String[]> tsv2array(final Reader reader)
			throws IOException {
		return parse2Array(reader, true);
	}

	public static List<String[]> tsv2array(final InputStream in)
			throws IOException {
		return parse2Array(new InputStreamReader(in), true);
	}

	public static List<String[]> tsv2array(final String csv) throws IOException {
		return parse2Array(new StringReader(csv), true);
	}

	private static List<String[]> parse2Array(final Reader reader,
			final boolean isTsv) throws IOException {
		BufferedReader bufferedFreader = null;

		Pattern p = Pattern.compile((isTsv ? PATTERN_TAB_DOUBLE_QUATATION
				: PATTERN_COMMA_DOUBLE_QUATATION));
		List<String[]> array = new ArrayList<String[]>();
		List<String> list = new ArrayList<String>();
		try {
			bufferedFreader = new BufferedReader(reader);
			String row = null;

			while ((row = bufferedFreader.readLine()) != null) {
				Matcher m = p.matcher(row);
				list.clear();

				while (m.find()) {
					if (m.group(2) != null) {
						list.add(m.group(2));
					} else {
						list.add(m.group(1));
					}
				}

				list.remove(list.size() - 1);
				String[] rowArray = new String[list.size()];
				array.add(list.toArray(rowArray));
			}
		} finally {
			if (bufferedFreader != null) {
				bufferedFreader.close();
			}
		}

		return array;
	}
}
