package go.view.screen;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import go.view.datamodel.impl.GoViewImpl;
import go.view.observer.*;
import go.view.panel.BoardPanel;
import go.view.panel.OutputPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

@SuppressWarnings("serial")
public class GameScreen extends JComponent implements GoScreenSubject {
	
	private List<GoScreenObserver> observers;
	
	private Container boardPanel;
	private OutputPanel outputPanel;
	
	private JToolBar commandPanel;
	private JButton undoButton;
	private JButton passButton;
	
	public GameScreen() {
		observers = new LinkedList<GoScreenObserver>();
		boardPanel = new BoardPanel();
		outputPanel = new OutputPanel();
		
		commandPanel = new JToolBar();
		commandPanel.setFloatable(false);
		commandPanel.setRollover(true);
		commandPanel.setLayout(new BorderLayout());
        undoButton = new JButton("Undo");
		passButton = new JButton("Pass");
		undoButton.setActionCommand("Undo");
		passButton.setActionCommand("Pass");
		undoButton.addActionListener(outputPanel);
		passButton.addActionListener(outputPanel);
		commandPanel.add(undoButton, BorderLayout.NORTH);
		commandPanel.add(passButton, BorderLayout.CENTER);

		JPanel eastPanel = new JPanel();
		// temporarily make eastPanel larger for debug purposes
		eastPanel.setPreferredSize(GoViewImpl.CENTER_DIM);
		eastPanel.setLayout(new BorderLayout());
		eastPanel.add(commandPanel, BorderLayout.NORTH);
		eastPanel.add(outputPanel, BorderLayout.SOUTH);
		
		this.setBackground(Color.RED);
		this.setLayout(new BorderLayout());
		this.add(boardPanel, BorderLayout.CENTER);
		this.add(eastPanel, BorderLayout.EAST);
		
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				notifyObserversOfMouseEvent(e);
			}
		});
	}

	@Override
	public void notifyObserversOfActionEvent(ActionEvent event) {
		observers.forEach(observer -> observer.handleActionEvent(event));
	}

	@Override
	public void notifyObserversOfMouseEvent(MouseEvent event) {
		observers.forEach(observer -> observer.handleMouseEvent(event));
	}

	@Override
	public void registerGoScreenObserver(GoScreenObserver observer) {
		this.observers.add(observer);
	}
	
}
