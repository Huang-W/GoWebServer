package go.view.screen;

import javax.swing.JPanel;

import go.view.AppView;
import go.view.panel.BoardPanel;
import go.view.panel.CommandPanel;
import go.view.panel.OutputPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class GameScreen extends JPanel implements ActionListener {
	
	private Container boardPanel;
	private Container outputPanel;
	private Container commandPanel;
	//private Container statusPanel;
	
	public GameScreen() {
		
		boardPanel = new BoardPanel();
		outputPanel = new OutputPanel();
		commandPanel = new CommandPanel(this);
		
		JPanel eastPanel = new JPanel();
		//eastPanel.setPreferredSize(AppView.EAST_DIM);
		// temporarily make eastPanel larger for debug purposes
		eastPanel.setPreferredSize(AppView.CENTER_DIM);
		eastPanel.setLayout(new BorderLayout());
		eastPanel.add(commandPanel, BorderLayout.NORTH);
		eastPanel.add(outputPanel, BorderLayout.SOUTH);
		
		this.setBackground(Color.RED);
		this.setLayout(new BorderLayout());
		this.add(boardPanel, BorderLayout.CENTER);
		this.add(eastPanel, BorderLayout.EAST);
		//this.add(statusPanel, BorderLayout.NORTH);

		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				outputPanel.dispatchEvent(e);
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		((ActionListener) outputPanel).actionPerformed(e);
	}
	
}
