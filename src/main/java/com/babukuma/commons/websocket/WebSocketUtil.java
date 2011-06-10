/*
 * http://babukuma.com
 * author : babukuma
 * Date : 2009/12/24
 */
package com.babukuma.commons.websocket;

/**
 * @author babukuma
 */
class WebSocketUtil {
	private static final byte[] HEADER_PROTOCOL = { 'G', 'E', 'T', ' ' };
	private static final byte[] HEADER_CONNECTION = { 'C', 'o', 'n', 'n', 'e',
			'c', 't', 'i', 'o', 'n' };
	private static final byte[] HEADER_UPGRADE = { 'U', 'p', 'g', 'r', 'a',
			'd', 'e' };
	private static final byte[] HEADER_ORIGIN = { 'O', 'r', 'i', 'g', 'i', 'n' };
	private static final byte[] HEADER_HOST = { 'H', 'o', 's', 't' };

	private static boolean isHeader(final byte[] HEADER, final byte[] header) {
		if (header == null)
			return false;

		if (HEADER.length > header.length)
			return false;

		for (int index = 0; index < HEADER.length; index++) {
			if (HEADER[index] != header[index]) {
				return false;
			}
		}

		return true;
	}

	public static boolean isHeaderProtocol(final byte[] header) {
		return isHeader(HEADER_PROTOCOL, header);
	}

	public static boolean isHeaderConnection(final byte[] header) {
		return isHeader(HEADER_CONNECTION, header);
	}

	public static boolean isHeaderUpgrade(final byte[] header) {
		return isHeader(HEADER_UPGRADE, header);
	}

	public static boolean isHeaderOrigin(final byte[] header) {
		return isHeader(HEADER_ORIGIN, header);
	}

	public static boolean isHeaderHost(final byte[] header) {
		return isHeader(HEADER_HOST, header);
	}

	public static String getUrl(final byte[] header) {
		int offset = 0;
		int length = 0;

		for (int index = 0; index < header.length; index++) {
			if (offset == 0 && header[index] == 0x20) {
				index++;
				offset = index;
			}

			if (offset > 0 && header[index] != 0x20) {
				length++;
			} else if (offset > 0 && header[index] == 0x20) {
				break;
			}
		}

		return new String(header, offset, length);
	}

	public static String getHeader(final byte[] header) {
		int offset = 0;
		int length = 0;

		for (int index = 0; index < header.length; index++) {
			if (offset == 0 && header[index] == 0x3A) {
				index += 2;
				offset = index;
			}

			if (offset > 0 && header[index] != 0x0D) {
				length++;
			} else if (offset > 0 && header[index] == 0x0D) {
				break;
			}
		}

		return new String(header, offset, length);
	}

	public static void main(String[] args) {
		System.out.println(0x3A);
		System.out.println((char) 0x3A);
		byte[] h = { (byte) 'U', (byte) 'p', (byte) 'g', (byte) 'r',
				(byte) 'a', (byte) 'd', (byte) 'e', (byte) ':', (byte) ' ',
				(byte) 'a', (byte) 'b', (byte) 'c', (byte) 'd', 0x0d, 0x0a };

		System.out.println("[" + getHeader(h) + "]");
	}
}
