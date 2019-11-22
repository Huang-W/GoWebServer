package go.view.screen.controller;

import go.view.observer.GoScreenSubject;

/**
 * An interface for access to this GoScreen by GoView.
 * GoScreenControllers are used to route User Input from GoScreens
 * To the GoViewController and return the GoScreen for observation
 */
public interface ScreenController {
	
	/**
	 * Set this screen visible
	 * Generally used in conjunction with another Screens hideScreen
	 */
	void showScreen();
	
	/**
	 * Set this screen not visible
	 * Generally used in conjunction with another Screen showScreen
	 */
	void hideScreen();
	
	/**
	 * Get this screen for observation by the GoView
	 * @return The screen to observe for user ActionEvents to pass to GoView
	 */
	GoScreenSubject getGoScreenSubject();
}
