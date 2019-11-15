package go.view.screen.controller;

import go.view.observer.GoScreenSubject;

public interface ScreenController {
	
	void showScreen();
	
	void hideScreen();
	
	GoScreenSubject getGoScreenSubject();
}
