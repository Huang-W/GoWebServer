package go.view.screen.impl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class ConfigScreen extends GoScreenImpl {
	
	// General Game Values
	private static int BOARD_SIZE;
	private static int NUM_TILES;
	
	// Model-specific Values
	//private static double KOMI;
	
	// Board-Specific View Values
	private static int BORDER_SIZE;
	private static int TILE_SIZE;
	private static int HALF_TILE_SIZE;

	// Screen-Specific View Values
	private static final Dimension NORTH_DIM = new Dimension( 840, 200);
	private static final Dimension CENTER_DIM = new Dimension( 840, 840);
	private static final Dimension EAST_DIM = new Dimension( 200, 840);
	
	/**
	 * Constructor
	 * Defaults BOARD_SIZE to 9
	 */
	public ConfigScreen() {
		super();
		setBoardSize(9);
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(400, 400));
		
		JPanel frequentPanel = createSimpleDialogBox();
        Border padding = BorderFactory.createEmptyBorder(20,20,5,20);
        frequentPanel.setBorder(padding);
        this.add(frequentPanel, BorderLayout.CENTER);
	}
	
	public void setBoardSize(int size) {
		BOARD_SIZE = size;
		NUM_TILES = BOARD_SIZE - 1;
		BORDER_SIZE = CENTER_DIM.width / (NUM_TILES + 2);
		TILE_SIZE = BORDER_SIZE;
		HALF_TILE_SIZE = TILE_SIZE / 2;
	}
	
	public static int BOARD_SIZE() {
		return BOARD_SIZE;
	}
	public static int NUM_TILES() {
		return NUM_TILES;
	}
	public static int BORDER_SIZE() {
		return BORDER_SIZE;
	}
	public static int TILE_SIZE() {
		return TILE_SIZE;
	}
	public static int HALF_TILE_SIZE() {
		return HALF_TILE_SIZE;
	}
	public static Dimension CenterDim() {
		return CENTER_DIM;
	}
	public static Dimension EastDim() {
		return EAST_DIM;
	}
	public static Dimension NorthDim() {
		return NORTH_DIM;
	}
	
	// code from java.swing tutorials, modified for project use
    private JPanel createSimpleDialogBox() {
    	
        final int numButtons = 3;
        JRadioButton[] radioButtons = new JRadioButton[numButtons];
        final ButtonGroup group = new ButtonGroup();
 
        JButton confirmButton = null;
 
        final String sizeNineCommand = "SET_BOARD_SIZE_NINE";
        final String sizeThirteenCommand = "SET_BOARD_SIZE_THIRTEEN";
        final String sizeNineteenCommand = "SET_BOARD_SIZE_NINETEEN";
 
        radioButtons[0] = new JRadioButton("Board Size of 9");
        radioButtons[0].setActionCommand(sizeNineCommand);
 
        radioButtons[1] = new JRadioButton("Board Size of 13");
        radioButtons[1].setActionCommand(sizeThirteenCommand);
 
        radioButtons[2] = new JRadioButton("Board Size of 19");
        radioButtons[2].setActionCommand(sizeNineteenCommand);
 
        for (int i = 0; i < numButtons; i++) {
            group.add(radioButtons[i]);
        }
        
        radioButtons[0].setSelected(true);
        
        confirmButton = new JButton("Confirm Board Size");
        confirmButton.setActionCommand("SET_BOARD_SIZE");
        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	ButtonModel selected = group.getSelection();
            	ActionEvent event = new ActionEvent(selected, 
            			ActionEvent.ACTION_PERFORMED, 
            			selected.getActionCommand());
            	ConfigScreen.this.notifyObserversOfActionEvent(event);
            }
        });
 
        return createPane("Please Select a BoardSize" + ":",
                          radioButtons,
                          confirmButton);
    }
    
    /**
     * Used by createSimpleDialogBox
     * to create a pane containing a description, a single column
     * of radio buttons, and the Confirm button.
     */
    // code from java.swing tutorials
    private JPanel createPane(String description,
                              JRadioButton[] radioButtons,
                              JButton confirmButton) {
 
        int numChoices = radioButtons.length;
        JPanel box = new JPanel();
        JLabel label = new JLabel(description);
 
        box.setLayout(new BoxLayout(box, BoxLayout.PAGE_AXIS));
        box.add(label);
 
        for (int i = 0; i < numChoices; i++) {
            box.add(radioButtons[i]);
        }
 
        JPanel pane = new JPanel(new BorderLayout());
        pane.add(box, BorderLayout.PAGE_START);
        pane.add(confirmButton, BorderLayout.PAGE_END);
        return pane;
    }
    
	/**
	 * Not Used
	 */
	@Override
	public void paintOval(Point location, Color color) {}

	/**
	 * Not Used
	 */
	@Override
	public void paintGrid(Point location) {}
}
