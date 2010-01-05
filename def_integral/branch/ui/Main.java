package ui;

import javax.swing.JPanel;
import javax.swing.JApplet;
import javax.swing.PopupFactory;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.text.ParseException;

import javax.swing.ImageIcon;

import bptree.ParseTree;

public class Main extends JApplet implements Runnable {
	private ParseTree bpTree = null;
	private JPanel jContentPane = null;
	private JLabel appName = null;
	private JTextField expression = null;
	private JTextField upperbound = null;
	private JLabel UpperBound = null;
	private JTextField lowerbound = null;
	private JLabel LowerBound = null;
	private JPanel controlPanel = null;
	private JButton eval = null;
	private JButton clear = null;
	private JLabel integralSign = null;
	private JLabel expressionEnd = null;

	/**
	 * This is the xxx default constructor
	 */
	public Main() {
		super();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	public void init() {
		this.setSize(300, 200);
		this.setContentPane(getJContentPane());
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			expressionEnd = new JLabel();
			expressionEnd.setBounds(new Rectangle(272, 48, 14, 16));
			expressionEnd.setText("dx");
			integralSign = new JLabel();
			integralSign.setBounds(new Rectangle(90, 30, 31, 46));
			integralSign.setIcon(new ImageIcon(getClass().getResource("/ui/46px-WPint.png")));
			integralSign.setText("Integral");
			LowerBound = new JLabel();
			LowerBound.setText("Lower Bound");
			LowerBound.setBounds(new Rectangle(30, 75, 75, 16));
			UpperBound = new JLabel();
			UpperBound.setText("Upper Bound");
			UpperBound.setBounds(new Rectangle(30, 15, 73, 16));
			appName = new JLabel();
			appName.setText("Expression");
			appName.setBounds(new Rectangle(15, 45, 64, 16));
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(appName, null);
			jContentPane.add(getExpression(), null);
			jContentPane.add(getUpperbound(), null);
			jContentPane.add(UpperBound, null);
			jContentPane.add(getLowerbound(), null);
			jContentPane.add(LowerBound, null);
			jContentPane.add(getControlPanel(), null);
			jContentPane.add(getEval(), null);
			jContentPane.add(getClear(), null);
			jContentPane.add(integralSign, null);
			jContentPane.add(expressionEnd, null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes expression	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getExpression() {
		if (expression == null) {
			expression = new JTextField();
			expression.setText("");
			expression.setBounds(new Rectangle(120, 45, 150, 20));
			expression.setPreferredSize(new Dimension(150, 20));
			expression.addKeyListener(new java.awt.event.KeyListener() {
				public void keyPressed(java.awt.event.KeyEvent e) {
					if(e.getKeyCode() == KeyEvent.VK_ENTER)
					{
							try {
								bpTree = ParseTree.makeTree(expression.getText(),true);
							} catch (ParseException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
					}
				}

				@Override
				public void keyReleased(KeyEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void keyTyped(KeyEvent arg0) {
					// TODO Auto-generated method stub
					
				}
			});
		}
		return expression;
	}

	/**
	 * This method initializes upperbound	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getUpperbound() {
		if (upperbound == null) {
			upperbound = new JTextField();
			upperbound.setPreferredSize(new Dimension(25, 20));
			upperbound.setText("b");
			upperbound.setBounds(new Rectangle(120, 15, 25, 20));
			upperbound.addFocusListener(new java.awt.event.FocusAdapter() {
				public void focusGained(java.awt.event.FocusEvent e) {
					upperbound.setText(""); // TODO Auto-generated Event stub focusGained()
				}
			});
		}
		return upperbound;
	}

	/**
	 * This method initializes lowerbound	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getLowerbound() {
		if (lowerbound == null) {
			lowerbound = new JTextField();
			lowerbound.setPreferredSize(new Dimension(25, 20));
			lowerbound.setText("a");
			lowerbound.setBounds(new Rectangle(120, 75, 25, 20));
			lowerbound.addFocusListener(new java.awt.event.FocusAdapter() {
				public void focusGained(java.awt.event.FocusEvent e) {
					lowerbound.setText(""); // TODO Auto-generated Event stub focusGained()
				}
			});
		}
		return lowerbound;
	}

	/**
	 * This method initializes controlPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getControlPanel() {
		if (controlPanel == null) {
			controlPanel = new JPanel();
			controlPanel.setLayout(new GridBagLayout());
			controlPanel.setBounds(new Rectangle(0, 0, 0, 0));
		}
		return controlPanel;
	}

	/**
	 * This method initializes eval	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getEval() {
		if (eval == null) {
			eval = new JButton();
			eval.setBounds(new Rectangle(45, 105, 61, 22));
			eval.setText("Eval");
			eval.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
						try {
							bpTree = ParseTree.makeTree(expression.getText(),true);
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				}
			});
		}
		return eval;
	}

	/**
	 * This method initializes clear	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getClear() {
		if (clear == null) {
			clear = new JButton();
			clear.setBounds(new Rectangle(150, 105, 75, 22));
			clear.setText("Clear");
			clear.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					expression.setText(""); // TODO Auto-generated Event stub mouseClicked()
					lowerbound.setText("a");
					upperbound.setText("b");
				}
			});
		}
		return clear;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

}
