package TP3;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import javax.swing.border.TitledBorder;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import java.util.Scanner;

public class AnalyseOccurrence {

	private JFrame frame;
	private JScrollPane scrollPane;
	private Button btAnalyse;
	private JPanel panelAnalyse;
	private JPanel panel;
	private JLabel lbl2;
	private JLabel lblNbmots;
	private JPanel panel_1;
	private JLabel lbl1;
	private DictionnaireStringInt compteur;
	private JTextArea textAnalyse;
	private JPanel panelTexte;
	private JLabel lblNewLabel;
	private JPanel panel_2;
	private JLabel lbl3;
	private JComboBox comboBox;
	private JLabel lbl4;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AnalyseOccurrence window = new AnalyseOccurrence();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AnalyseOccurrence() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(2, 1, 0, 0));
		
		panelTexte = new JPanel();
		panelTexte.setBorder(new TitledBorder(null, "Texte \u00E0 analyser", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frame.getContentPane().add(panelTexte);
		panelTexte.setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
		panelTexte.add(scrollPane);
		
		textAnalyse = new JTextArea();
		textAnalyse.setEditable(true);
		textAnalyse.setEnabled(true);
		//scrollPane.add(textAnalyse);

		scrollPane.setViewportView(textAnalyse);
		
		
		btAnalyse = new Button("Analyser");
		btAnalyse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				analyseTexte(textAnalyse.getText());
				lblNbmots.setText(String.valueOf(compteur.getNbElements()));
				lblNewLabel.setText(compteur.rechercherCleAvecValeurMax());    
				
				
			}
		});
		panelTexte.add(btAnalyse, BorderLayout.SOUTH);
		
		panelAnalyse = new JPanel();
		panelAnalyse.setBorder(new TitledBorder(null, "R\u00E9sultats de l'analyse", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frame.getContentPane().add(panelAnalyse);
		panelAnalyse.setLayout(new GridLayout(3, 1, 0, 0));
		
		panel = new JPanel();
		panelAnalyse.add(panel);
		
		lbl2 = new JLabel("Nombre de mots :");
		panel.add(lbl2);
		
		lblNbmots = new JLabel("xxx");
		panel.add(lblNbmots);
		
		panel_1 = new JPanel();
		panelAnalyse.add(panel_1);
		panel_1.setLayout(null);
		
		lbl1 = new JLabel("Mot le plus fréquent :");
		lbl1.setBounds(125, 0, 145, 16);
		panel_1.add(lbl1);
		
		lblNewLabel = new JLabel("xxx");
		lblNewLabel.setBounds(269, 0, 61, 16);
		panel_1.add(lblNewLabel);
		
		panel_2 = new JPanel();
		panelAnalyse.add(panel_2);
		
		lbl3 = new JLabel("Fréquence du mot");
		panel_2.add(lbl3);
		
		comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lbl4.setText("A");
			}
		});
		comboBox.removeAllItems();
		comboBox.addItem("the");
		comboBox.addItem("a");
		comboBox.addItem("of");
		comboBox.addItem("an");
		
		panel_2.add(comboBox);
		
		lbl4 = new JLabel("0");
		panel_2.add(lbl4);
		
	}
	
	public void analyseTexte(String txt) {
		Scanner sc = new Scanner(txt);
		compteur = new DictionnaireStringInt();
		/* expression régulière qui permet de définir
		 * les limites des mots lus via la méthode next() du scanner.
		 * (espaces, ponctuation, saut de ligne, etc.)
		 */
		sc.useDelimiter("([^\\p{L}\\p{Nd}]|\\s)+");
		while (sc.hasNext()) {
		String motLu = sc.next().toLowerCase();
		compteur.ajouterModifier(motLu,compteur.rechercherValeur(motLu)+1);
		}
		sc.close();
		}
	
	
	public JFrame getFrame() {
		return frame;
	}
	public JTextArea getTextAnalyse() {
		return textAnalyse;
	}
	public JScrollPane getScrollPane() {
		return scrollPane;
	}
	public JPanel getPanelTexte() {
		return panelTexte;
	}
	public Button getBtAnalyse() {
		return btAnalyse;
	}
	public JPanel getPanelAnalyse() {
		return panelAnalyse;
	}
	public JPanel getPanel() {
		return panel;
	}
	public JLabel getLbl2() {
		return lbl2;
	}
	public JLabel getLblNbmots() {
		return lblNbmots;
	}
	public JPanel getPanel_1() {
		return panel_1;
	}
	public JLabel getLbl1() {
		return lbl1;
	}
	public JLabel getLblNewLabel() {
		return lblNewLabel;
	}
	public JPanel getPanel_2() {
		return panel_2;
	}
	public JLabel getLbl3() {
		return lbl3;
	}
	public JComboBox getComboBox() {
		return comboBox;
	}
	public JLabel getLbl4() {
		return lbl4;
	}
}
