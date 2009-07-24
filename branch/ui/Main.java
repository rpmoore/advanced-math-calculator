package ui;

import javax.swing.JPanel;
import javax.swing.JApplet;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.Rectangle;

public class Main extends JApplet {

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
			expression.setBounds(new Rectangle(105, 45, 150, 20));
			expression.setPreferredSize(new Dimension(150, 20));
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

}
