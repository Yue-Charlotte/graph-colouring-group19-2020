import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


	public class Start extends JFrame implements ActionListener{

		JButton StartButton;
		JButton graphButton;
		JButton graphExit;
		JButton modeButton;
		JButton modeExit;
		JRadioButton bitterEnd;
		JRadioButton bestBound;
		JRadioButton randomOrder;
		JRadioButton randomGraph;
		JRadioButton insertGraph;
		JTextField minvector;
		JTextField maxvector;
		JTextField minedge;
		JTextField maxedge;
		JTextField graphText;
		int GameMode = 0;
		Boolean txtFile = false;
		JFrame baseFrame;
		JLayeredPane basePane;
		JPanel basePanel;
		JPanel graphPanel;
		JPanel randomGraphPanel;
		JPanel textGraphPanel;
		JPanel modePanel;
		int baseInt;
		int graphInt;
		int modeInt;
		int rGraphInt;
		int txtGraphInt;


		Start(){
		baseInt = 2;
		graphInt = 1;
		modeInt = 1;
		rGraphInt = 1;
		txtGraphInt = 1;

			modeButton = new JButton();
    		modeButton.setBounds(250, 100, 100, 50);
    		modeButton.addActionListener(this);
    		modeButton.setText("GameMode");
    		modeButton.setFocusable(false);

    		modeExit = new JButton();
    		modeExit.setBounds(480, 20, 20, 20);
    		modeExit.addActionListener(this);
    		modeExit.setText("X");
    		modeExit.setFocusable(false);

    		graphButton = new JButton();
    		graphButton.setBounds(250, 200, 100, 50);
    		graphButton.addActionListener(this);
    		graphButton.setText("Set Graph");
    		graphButton.setFocusable(false);

    		StartButton = new JButton();
    		StartButton.setBounds(250, 300, 100, 50);
    		StartButton.addActionListener(this);
    		StartButton.setText("Start");
    		StartButton.setFocusable(false);

    		minvector = new JTextField();
			minvector.setText("1");
			minvector.setBounds(0, 0, 50, 50);
			minvector.setFont(new Font("null",Font.PLAIN,25));

			maxvector = new JTextField();
			maxvector.setText("1");
			maxvector.setBounds(200, 150, 50, 50);
			maxvector.setFont(new Font("null",Font.PLAIN,25));

			minedge = new JTextField();
			minedge.setText("1");
			minedge.setBounds(200, 200, 50, 50);
			minedge.setFont(new Font("null",Font.PLAIN,25));

			maxedge = new JTextField();
			maxedge.setText("1");
			maxedge.setBounds(200, 250, 50, 50);
			maxedge.setFont(new Font("null",Font.PLAIN,25));

			graphText = new JTextField();
			graphText.setText("paste graph here");
			graphText.setBounds(300, 100, 250, 250);
			graphText.setFont(new Font("null",Font.PLAIN,25));


			bitterEnd = new JRadioButton("To the bitter end");
			bitterEnd.setBounds(100, 100, 100, 50);
    		bitterEnd.addActionListener(this);
    		//bitterEnd.setText();
    		bitterEnd.setFocusable(false);

    		bestBound = new JRadioButton("Best upper bound in fixed time");
    		bestBound.setBounds(100, 200, 100, 50);
    		bestBound.addActionListener(this);
    		//bestBound.setText();
    		bestBound.setFocusable(false);

    		randomOrder = new JRadioButton("Random Order");
    		randomOrder.setBounds(100, 300, 100, 50);
    		randomOrder.addActionListener(this);
    		//randomOrder.setText();
    		randomOrder.setFocusable(false);

    		ButtonGroup gamemode = new ButtonGroup();
    		gamemode.add(bitterEnd);
    		gamemode.add(bestBound);
    		gamemode.add(randomOrder);
    		bitterEnd.setSelected(true);


    		randomGraph = new JRadioButton("Randomize graph");
    		randomGraph.setBounds(100, 100, 100, 50);
    		randomGraph.addActionListener(this);
    		//randomOrder.setText();
    		randomGraph.setFocusable(false);

    		insertGraph = new JRadioButton("Insert graph from text file");
    		insertGraph.setBounds(100, 200, 100, 50);
    		insertGraph.addActionListener(this);
    		//randomOrder.setText();
    		insertGraph.setFocusable(false);

    		ButtonGroup graphType = new ButtonGroup();
    		graphType.add(randomGraph);
    		graphType.add(insertGraph);
    		randomGraph.setSelected(true);

    		basePane = new JLayeredPane();
    		basePane.setBounds(0,0,600,600);

    		basePanel = new JPanel();
    		basePanel.setBounds(0,0,600,600);
    		basePanel.setBackground(new Color(70,170,170));
    		basePanel.setLayout(null);


    		graphPanel = new JPanel();
    		graphPanel.setBounds(50,50,250,500);
    		graphPanel.setBackground(new Color(170,70,170));
    		graphPanel.setLayout(null);

	 
    		randomGraphPanel = new JPanel();
    		randomGraphPanel.setBounds(300,50,250,500);
    		randomGraphPanel.setBackground(new Color(170,170,70));
    		randomGraphPanel.setLayout(null);

    		
    		textGraphPanel = new JPanel();
    		textGraphPanel.setBounds(300,50,250,500);
    		textGraphPanel.setBackground(new Color(70,170,70));
    		

    		modePanel = new JPanel();
    		modePanel.setBounds(50,50,500,500);
    		modePanel.setBackground(new Color(170,70,70));	
    		modePanel.setLayout(null);


    		baseFrame = new JFrame();
    		baseFrame.add(basePane);
			baseFrame.setTitle("GAME");
			baseFrame.setSize(600,600);
			baseFrame.setLayout(null);
			baseFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			baseFrame.setResizable(false);
			baseFrame.setVisible(true);
    		basePane.add(basePanel,Integer.valueOf(baseInt));
    		basePanel.add(modeButton);
    		basePanel.add(graphButton);
    		basePanel.add(StartButton);
			modePanel.add(bitterEnd);
			modePanel.add(bestBound);
			modePanel.add(randomOrder);
			modePanel.add(modeExit);
			graphPanel.add(randomGraph);
			graphPanel.add(insertGraph);
			graphPanel.add(graphExit);
			randomGraphPanel.add(minvector);
			randomGraphPanel.add(maxvector);
			randomGraphPanel.add(minedge);
			randomGraphPanel.add(maxedge);
			textGraphPanel.add(graphText);


		}

		public void actionPerformed(ActionEvent e){
			
			if(e.getSource()==bitterEnd){
				GameMode = 0;
			}
			if(e.getSource()==bestBound){
				GameMode = 1;
			}
			if(e.getSource()==randomOrder){
			GameMode = 2;
			}
			if(e.getSource()==randomGraph){
			txtFile = false;
			}
			if(e.getSource()==insertGraph){
			txtFile = true;
			}
			if(e.getSource()==modeButton){
				modeInt = 3;
    		basePane.add(modePanel,Integer.valueOf(modeInt));

			}
			if(e.getSource()==modeExit){
				modeInt = 1;
    		basePane.add(modePanel,Integer.valueOf(modeInt));

			}

			if(e.getSource()==graphButton){
				graphInt = 3;
    		basePane.add(graphPanel,Integer.valueOf(graphInt));

			}
			if(e.getSource()==graphExit){
				graphInt = 1;
    		basePane.add(graphPanel,Integer.valueOf(graphInt));

			}

			if(e.getSource()==randomGraph){
				rGraphInt = 3;
				txtGraphInt = 1;
    		basePane.add(randomGraphPanel,Integer.valueOf(rGraphInt));
    		basePane.add(textGraphPanel,Integer.valueOf(txtGraphInt));

			}
			if(e.getSource()==insertGraph){
				txtGraphInt = 3;
				rGraphInt = 1;
    		basePane.add(randomGraphPanel,Integer.valueOf(rGraphInt));
    		basePane.add(textGraphPanel,Integer.valueOf(txtGraphInt));

			}

			if(e.getSource()==StartButton){
				if (txtFile = false){
				int minv = Integer.parseInt(minvector.getText());
				int maxv = Integer.parseInt(maxvector.getText());
				int mine = Integer.parseInt(minedge.getText());
				int maxe = Integer.parseInt(maxedge.getText());
				//insert random graph generator
			}
			if (txtFile = true){
				String graphCode = graphText.getText();
			}
				if (GameMode == 0){
					System.out.println("TheBitterEnd");
				}
				if (GameMode == 1){
					System.out.println("BestUpper");
				}
				if (GameMode == 2){
					System.out.println("Random");
				}
			}
		}

	}