package go.view.screen.controller;

import java.awt.Component;

import go.view.observer.GoScreenSubject;

public interface ScreenController {
	
	void displaySelectedComponent();
	
	void hideSelectedComponent();
	
	GoScreenSubject getGoScreenSubject();
}
