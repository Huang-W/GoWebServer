package go.view.screen;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import go.view.datamodel.impl.GoAppViewImpl;
import go.view.panel.BoardPanel;
import go.view.panel.CommandPanel;
import go.view.panel.OutputPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
		eastPanel.setPreferredSize(GoAppViewImpl.CENTER_DIM);
		eastPanel.setLayout(new BorderLayout());
		eastPanel.add(commandPanel, BorderLayout.NORTH);
		eastPanel.add(outputPanel, BorderLayout.SOUTH);
		
		this.setBackground(Color.RED);
		this.setLayout(new BorderLayout());
		this.add(boardPanel, BorderLayout.CENTER);
		this.add(eastPanel, BorderLayout.EAST);
		//this.add(statusPanel, BorderLayout.NORTH);
		
		/*this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//boardPanel.dispatchEvent(e);
				outputPanel.dispatchEvent(e);
			}
		});*/
		this.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent e) {
			    // Converts to float for float division and then rounds to
			    // provide nearest intersection.
			    int row = Math.round((float) (e.getY() - BORDER_SIZE)
				    / TILE_SIZE);
			    int col = Math.round((float) (e.getX() - BORDER_SIZE)
				    / TILE_SIZE);

			    // DEBUG INFO
			    // System.out.println(String.format("y: %d, x: %d", row, col));

			    // Check wherever it's valid
			    if (row >= SIZE || col >= SIZE || row < 0 || col < 0) {
				return;
			    }

			    if (grid.isOccupied(row, col)) {
				return;
			    }

			    grid.addStone(row, col, current_player);
			    lastMove = new Point(col, row);

			    // Switch current player
			    if (current_player == State.BLACK) {
				current_player = State.WHITE;
			    } else {
				current_player = State.BLACK;
			    }
			    repaint();
			}
		    });
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		((ActionListener) outputPanel).actionPerformed(e);
	}
	
}
