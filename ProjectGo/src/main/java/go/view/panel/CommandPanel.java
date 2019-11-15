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
	
	public CommandPanel()
	{
		
		this.setFloatable(false);
        this.setRollover(true);
        this.setLayout(new BorderLayout());
		
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
