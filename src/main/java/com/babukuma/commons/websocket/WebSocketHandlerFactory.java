/*
 * http://babukuma.com
 * author : babukuma
 * Date : 2009/12/24
 */
package com.babukuma.commons.websocket;

/**
 * @author babukuma
 * 
 */
public interface WebSocketHandlerFactory {
	/**
	 * @param urlPath
	 * @return WebSocketHandlerの実装クラス
	 */
	WebSocketHandler createHandler(String urlPath);

	/**
	 * @param urlPath
	 * @param handler
	 */
	void removeHandler(String urlPath, WebSocketHandler handler);
}
