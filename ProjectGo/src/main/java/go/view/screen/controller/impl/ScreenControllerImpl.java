package go.view.screen.controller.impl;

import go.view.observer.GoScreenSubject;
import go.view.screen.GoScreen;
import go.view.screen.controller.ScreenController;
import go.view.screen.impl.GoScreenImpl;

public class ScreenControllerImpl implements ScreenController {
	
	private GoScreen goScreen;
	private GoScreenSubject subject;
    
	public ScreenControllerImpl(GoScreenImpl goScreen) {
		this.goScreen = goScreen;
		this.subject = (GoScreenSubject) goScreen;
	}
	
	@Override
	public void showScreen() {
		goScreen.showScreen();
	}
	
	@Override
	public void hideScreen() {
		goScreen.hideScreen();
	}
	
	@Override
	public GoScreenSubject getGoScreenSubject() {
		return subject;
	}
	
}
