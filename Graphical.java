package Sudoku;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


@SuppressWarnings("serial")
class Graphical extends JFrame implements ActionListener 
{
	Container con;
	JButton b[][] = new JButton[9][9];
	JMenuBar mbar;
	JMenu file;
	JMenuItem submit, exit, about, Reset,help;

	int[][] cp = new int[9][9];
	int[][] ip = new int[9][9];


	@SuppressWarnings("deprecation")
	Graphical() 
	{
		super("Sudoku");
		setSize(700, 700);
		Dimension dim =Toolkit.getDefaultToolkit().getScreenSize();
		super.setLocation(((dim.width/2)-(super.getSize().width/2)),((dim.height/2)-(super.getSize().height/2)));
		setResizable(false);

		con = getContentPane();
		con.setLayout(new GridLayout(9, 9));

		Logic ob1 = new Logic();
		cp=ob1.save();
		ip=ob1.hide();

		for (int i = 0; i < 9; i++)
		{
			for (int j = 0; j < 9; j++) 
			{
				b[i][j] = new JButton("" + ip[i][j]);
				b[i][j].setFont(new Font("BAHNSCHRIFT", Font.PLAIN, 25));
				b[i][j].setForeground(Color.BLACK);
				if (ip[i][j] == 0) 
				{
					b[i][j].setText("");
					b[i][j].addActionListener(this);

				}

				con.add(b[i][j]);

				if (i == 3 || i == 4 || i == 5 || j == 3 || j == 4 || j == 5) 
				{
					if (2 < i && i < 6 && 2 < j && j < 6) {
						b[i][j].setBackground(Color.WHITE);
						continue;
					}
					b[i][j].setBackground(Color.LIGHT_GRAY);

				}

				else
					b[i][j].setBackground(Color.WHITE);
			}
		}

		
		UIManager.put("OptionPane.messageFont", new Font("BAHNSCHRIFT", Font.PLAIN, 15));
		mbar = new JMenuBar();
		mbar.setPreferredSize(new Dimension(700,30));

		file = new JMenu("File                                                 ");
		about = new JMenuItem("About");
		help = new JMenuItem("Help");
		
		Reset = new JMenuItem("Reset                                           ");
		submit = new JMenuItem("Submit");
		exit = new JMenuItem("Exit");
		
		file.setFont(new Font("ARIAL", Font.PLAIN, 15));
		about.setFont(new Font("ARIAL", Font.PLAIN, 15));
		Reset.setFont(new Font("ARIAL", Font.PLAIN, 15));
		submit.setFont(new Font("ARIAL", Font.PLAIN, 15));
		exit.setFont(new Font("ARIAL", Font.PLAIN, 15));
		help.setFont(new Font("ARIAL", Font.PLAIN, 15));
		
		setJMenuBar(mbar);
		
		submit.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				int r = 0;

				for (int i = 0; i < 9; i++)
				{
					for (int j = 0; j < 9; j++)
					{
						if(b[i][j].getText() != "")
						{
						if (cp[i][j] != Integer.parseInt(b[i][j].getText())) 
							{
								r = 1;
								break;
							}
						}
						else
						{
							r=2;
							break;
						}
					}
				}
				if (r == 0)
				JOptionPane.showMessageDialog(Graphical.this, "You Won the Game");
				else if(r == 1)
					JOptionPane.showMessageDialog(Graphical.this, "You Lose the Game");
				else
					JOptionPane.showMessageDialog(Graphical.this, "Some Unfilled cells are left.");
			}

		});
		
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}

		});

		about.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(Graphical.this,
					"Project By:\nDevesh Bhartiya\nBrijesh Anand\nGunashekhar Reddy\nAsad Tayyab",
					"About", JOptionPane.PLAIN_MESSAGE);
			}
		});
		help.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(Graphical.this,
					"Sudoku is a game of logic and reasoning.\n"+
					"Use Numbers 1-9.\n" + 
					"Don't Repeat Any Numbers in a Row,Column or 3X3 Grid.\n" +  
					"Use Process of Elimination.",
					"How To Play", JOptionPane.PLAIN_MESSAGE);
			}
		});
		
		Reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				recall();
			}
		});

		file.add(Reset);
		file.add(submit);
		file.addSeparator();
		file.add(exit);
		
		mbar.add(file);
		mbar.add(help);
		mbar.add(about);
		
		
		show();
		
		MyWindowAdapter mwa = new MyWindowAdapter();
		addWindowListener(mwa);

	}


	class MyWindowAdapter extends WindowAdapter 
	{
		public void windowClosing(WindowEvent e) 
		{
			System.exit(0);
		}
	}


	public void actionPerformed(ActionEvent e) 
	{
		for (int i = 0; i < 9; i++)
		{
			for (int j = 0; j < 9; j++) 
			{
				if (e.getSource() == b[i][j]) 
				{
					try {
						
					
					String s = JOptionPane.showInputDialog("Enter Number");
					if(s.matches("\\d"))
					{
						int c = Integer.parseInt(s);
						if (0 < c && c < 10) 
						{
							b[i][j].setText(s);
							b[i][j].setFont(new Font("BAHNSCHRIFT", Font.PLAIN, 25));
							if(cp[i][j]==c)
								b[i][j].setForeground(Color.BLUE);
							else
								b[i][j].setForeground(Color.RED);
						}
						
						break;
					}
					}
					catch(Exception E)
					{
						b[i][j].setText("");
					}
				}
			}
		}
	}


	void recall() 
	{
		Logic ob1 = new Logic();
		cp=ob1.save();
		ip=ob1.hide();
		for (int i = 0; i < 9; i++)
			for (int j = 0; j < 9; j++) {
				b[i][j].setText("" + ip[i][j]);
				b[i][j].setFont(new Font("BAHNSCHRIFT", Font.PLAIN, 25));
				b[i][j].setForeground(Color.BLACK);
				b[i][j].removeActionListener(this);
				if (ip[i][j] == 0) 
				{
					b[i][j].setText("");
					b[i][j].addActionListener(this);

				}

				con.add(b[i][j]);

				if (i == 3 || i == 4 || i == 5 || j == 3 || j == 4 || j == 5) 
				{
					if (2 < i && i < 6 && 2 < j && j < 6) 
					{
						b[i][j].setBackground(Color.WHITE);
						continue;
					}
					b[i][j].setBackground(Color.LIGHT_GRAY);

				}

				else
					b[i][j].setBackground(Color.WHITE);
			}
	}
}