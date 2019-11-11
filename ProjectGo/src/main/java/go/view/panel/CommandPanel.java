package go.view.panel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JToolBar;

@SuppressWarnings("serial")
public class CommandPanel extends JToolBar implements ActionListener {
	
	private ActionListener listener;
	
	private JButton undoButton;
	private JButton passButton;
	
	public CommandPanel(ActionListener listener)
	{
		
		this.listener = listener;
		this.setFloatable(false);
        this.setRollover(true);
        this.setLayout(new BorderLayout());
        
        undoButton = new JButton("Undo");
		passButton = new JButton("Pass");
		
		undoButton.setActionCommand("Undo");
		passButton.setActionCommand("Pass");
		
		this.add(undoButton, BorderLayout.NORTH);
		this.add(passButton, BorderLayout.CENTER);
		
		undoButton.addActionListener(this);
		passButton.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		listener.actionPerformed(e);
	}
	
}
