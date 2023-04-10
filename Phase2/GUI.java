// Code by Tjardo, merged with graph generation functionalities etc by Christopher

import javax.swing.*;
import java.awt.event.*;
import java.util.Random;
import java.awt.BorderLayout;
import java.awt.GridLayout;

class GUI extends JFrame implements ActionListener
{ 
		static JFrame baseFrame;
		JLayeredPane graphSelectionPane;
    JLayeredPane gameModePane;
    JButton randomGraph;
    JButton customGraph;
    JButton importGraph;
    JButton bitterEnd;
    JButton bestUpper;
    JButton randomOrder;
		
		JLabel graphLabel;
		JLabel gameModLabel;
		
		String txtFile = "";
		
		static HintButton hint;

    int gamemode = 0;
		
		static VisualGraph VG;
		static VisualTimer VT;
		static ColorButtons ColButtons;
    
    public GUI()
    {
        //Create startscreen
        CreateFrame();
        CreateGraphSelectionPane();
    }

    private void CreateFrame()
    {
        //Create frame
		baseFrame = new JFrame();
		
				baseFrame.setTitle("Group 19 - START");
				baseFrame.setSize(510, 300);
				baseFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        baseFrame.setResizable(false);
        baseFrame.setLocationRelativeTo(null);
        baseFrame.setVisible(true);		
    }

    private void CreateGraphSelectionPane()
    {
        graphSelectionPane = new JLayeredPane();
        graphSelectionPane.setBounds(0, 0, 500, 300);

        //Base label with text
        graphLabel = new JLabel();
        graphLabel.setBounds(100, 50, 300, 50);
        graphLabel.setText("Please choose how you want to create your graph: ");
        
        //Add 3 buttons for graph selection
        customGraph = new JButton();
        randomGraph = new JButton();
        importGraph = new JButton();

        customGraph.setBounds(20, 100, 150, 80);
        customGraph.addActionListener(this);
        customGraph.setText("Custom Graph");
        customGraph.setFocusable(false);

        //use random graph       
        randomGraph.setBounds(175, 100, 150, 80);         
        randomGraph.addActionListener(this);    
        randomGraph.setText("Random Graph");
        randomGraph.setFocusable(false);

        //Import graph   
        importGraph.setBounds(330, 100, 150, 80);  
        importGraph.addActionListener(this);    
        importGraph.setText("Import Graph");
        importGraph.setFocusable(false);

        //Add components
        graphSelectionPane.add(graphLabel);
        graphSelectionPane.add(customGraph);
        graphSelectionPane.add(randomGraph);
        graphSelectionPane.add(importGraph);

        //add pane to frame			
		baseFrame.add(graphSelectionPane);
    }

    private void CreateGameModeSelectionPane()
    {       		
				gameModePane = new JLayeredPane();
        gameModePane.setBounds(0, 0, 500, 300);
        
        //Base label with text
        gameModLabel = new JLabel();
        gameModLabel.setBounds(150, 50, 300, 50);
        gameModLabel.setText("Please select your gamemode: ");
        
        //Add 3 buttons for graph selection
        bitterEnd = new JButton();
        bestUpper = new JButton();
        randomOrder = new JButton();

        //gamemode 1
        bitterEnd.setBounds(20, 100, 150, 80);
        bitterEnd.setText("To The Bitter End");
        bitterEnd.addActionListener(this);
        bitterEnd.setFocusable(false);

        //gamemode 2      
        bestUpper.setBounds(175, 100, 150, 80);      
        bestUpper.setText("Best Upper bound");
				bestUpper.addActionListener(this);
        bestUpper.setFocusable(false);

        //gamemode 3  
        randomOrder.setBounds(330, 100, 150, 80);      
        randomOrder.setText("Random Order");
				randomOrder.addActionListener(this);
        randomOrder.setFocusable(false);

        //Add components
        gameModePane.add(gameModLabel);
        gameModePane.add(bitterEnd);
        gameModePane.add(bestUpper);
        gameModePane.add(randomOrder);

        //Add pane to frame
				baseFrame.add(gameModePane);
    }


    public void actionPerformed(ActionEvent e)
    {       
        //Determine gamemode based on which button is selected
        if(e.getSource() == bitterEnd) {        	  
            gamemode = 1;
						StartGameScreen();
        }
            
        if(e.getSource() == bestUpper) {                
            gamemode = 2;
						StartGameScreen();
        }
		
        if(e.getSource() == randomOrder) {
          gamemode = 3;
					StartGameScreen();
        }
            
        //Create graph  
        if(e.getSource() == customGraph) {
            //Ask user for graph input
						int ver = -1;
						int edges = -1;
						
						try{
							String input = JOptionPane.showInputDialog("Enter amount of Vertices (Highly recommended: Fewer than 40): ");
							ver = Integer.parseInt(input);
							
							int edges_bound = (ver*(ver - 1)/2);
							
							String input2 = JOptionPane.showInputDialog("Enter amount of Edges (Highly recommended: fewer than " + edges_bound + "):");
							edges = Integer.parseInt(input2);
						}catch(Exception exc){
							
						}
						
						boolean can_make_graph = false;
						if (ver > 1 && edges >= 0){
							// Formula for maximum amount of edges possible
							if (edges <= ver*(ver - 1)/2){
								can_make_graph = true;
							}
						}
						
            //Create custom graph with input variables
            //check if graph is valid
            //store graph 

						if (can_make_graph){
							graphLabel.setText("Generating graph & calculating chromatic number... may take a while...");
							baseFrame.repaint();
							
							Group19Phase2.e = GenerateGraph.generateRandomGraph(ver, edges);
							Group19Phase2.G = new Graph(ver, Group19Phase2.e);
							
							//continue to gamemode panel
							graphSelectionPane.setVisible(false);
							CreateGameModeSelectionPane();
						}else{
							JOptionPane.showMessageDialog(null, "Invalid input options.", "", 0); 
						}

        }

        if(e.getSource() == randomGraph)
        {	
				
						Random r = new Random();
						// At most, we will generate 30 nodes (otherwise it would probably get too messy).
            int ver = 2 + r.nextInt(28); 
						// Generate a certain amount of edges for this graph (should be under the bound though.)
						int bound_restriction = 1;	// By how much should we restrict the bound?
						
						// We try to go lower than the bound, otherwise we likely won't have enough colours to complete the graph.
						if (ver >= 13){
							bound_restriction = 5;
						}
						
						int edges = 1 + r.nextInt(Math.abs(ver*(ver - 1)/(2*bound_restriction)));
						
						// GraphCheck("Generating graph & Calculating chromatic number... may take a bit...");
						Group19Phase2.e = GenerateGraph.generateRandomGraph(ver, edges);
						Group19Phase2.G = new Graph(ver, Group19Phase2.e);
						
            //continue to gamemode panel
            graphSelectionPane.setVisible(false);
            CreateGameModeSelectionPane();
        }

        if(e.getSource() == importGraph)
        {
            JFileChooser fc = new JFileChooser();
						int r = fc.showOpenDialog(null); 
						txtFile = fc.getSelectedFile().getName();
						
						baseFrame.setTitle("Generating graph & calculating chromatic number...");
						
						Group19Phase2.e = GenerateGraph.graphFromTextFile(txtFile);
						
						// was the input file valid?
						if (Group19Phase2.e != null){
							Group19Phase2.G = new Graph(Group19Phase2.graphVertexCount, Group19Phase2.e);
							graphSelectionPane.setVisible(false);
							
							baseFrame.setTitle("Group 19 - START");
							
							// continue to gamemode panel 
							CreateGameModeSelectionPane();
						}else{
							JOptionPane.showMessageDialog(null, "Invalid file! Make sure that your graph file has proper formatting, and that it is located within the same folder as the game's class files.", "", 0);
						}
        }

	}
	
	// Method to initiate the game once the user is done with the start screen
	public void StartGameScreen(){
		gameModePane.setVisible(false);
		
		// Adjust the game window
		baseFrame.setTitle("Group 19 - GAME");
		baseFrame.setSize(800, 800);
		baseFrame.repaint();
		baseFrame.setLocationRelativeTo(null);
		
		// Initialize the colour buttons panel
		ColButtons = new ColorButtons();
		ColButtons.setOpaque(false);
		
		JPanel hintpanel = new JPanel();
		hint = new HintButton();
		hintpanel.setLayout(null);
		hintpanel.add(hint);
		hintpanel.setBounds(0, 0, 200, 100);
		baseFrame.add(hintpanel);
		
		// Initialize the visual graph
		VG = new VisualGraph();
		VG.setOpaque(false);
		baseFrame.add(VG);
		
		// Initialize the game manager object
		Group19Phase2.gameManager = new Game(gamemode);
		
		baseFrame.revalidate();
		baseFrame.repaint();
	}
}