package go.view.screen.controller.impl;

import java.awt.Component;

import go.view.observer.GoScreenSubject;
import go.view.screen.controller.ScreenController;

public class ScreenControllerImpl implements ScreenController {
	
	private Component appScreen;
	private GoScreenSubject subject;
	
	public ScreenControllerImpl(Component component) {
		appScreen = component;
		subject = (GoScreenSubject) component;
	}
	
	@Override
	public void displaySelectedComponent() {
		appScreen.setVisible(true);
	}
	
	@Override
	public void hideSelectedComponent() {
		appScreen.setVisible(false);
	}
	
	@Override
	public GoScreenSubject getGoScreenSubject() {
		return subject;
	}
	
}
