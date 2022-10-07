package com.mcoder.random;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.TextArea;

public class RandGenerator extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RandGenerator frame = new RandGenerator();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame
	 */
	public RandGenerator() {
		setTitle("Random");
		setPreferredSize(new Dimension(303, 403));
		setSize(303, 403);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMin = new JLabel("Min:");
		lblMin.setBounds(10, 45, 46, 14);
		contentPane.add(lblMin);
		
		JCheckBox chckbxCrescente = new JCheckBox("Crescente");
		chckbxCrescente.setEnabled(false);
		chckbxCrescente.setBounds(10, 105, 97, 23);
		contentPane.add(chckbxCrescente);
		
		JCheckBox chckbxDecrescente = new JCheckBox("Decrescente");
		chckbxDecrescente.setEnabled(false);
		chckbxDecrescente.setBounds(179, 105, 98, 23);
		contentPane.add(chckbxDecrescente);
		
		chckbxCrescente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chckbxCrescente.setSelected(true);
				chckbxDecrescente.setSelected(false);
			}
		});
		
		chckbxDecrescente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chckbxCrescente.setSelected(false);
				chckbxDecrescente.setSelected(true);
			}
		});
		
		JLabel lblMax = new JLabel("Max:");
		lblMax.setBounds(167, 45, 46, 14);
		contentPane.add(lblMax);
		
		JRadioButton rdbtnOrdinato = new JRadioButton("Ordinato");
		rdbtnOrdinato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (rdbtnOrdinato.isSelected()) {
					chckbxCrescente.setEnabled(true);
					chckbxCrescente.setSelected(true);
					chckbxDecrescente.setEnabled(true);
				} else {
					chckbxCrescente.setEnabled(false);
					chckbxCrescente.setSelected(false);
					chckbxDecrescente.setEnabled(false);
					chckbxDecrescente.setSelected(false);
				}
			}
		});
		rdbtnOrdinato.setBounds(10, 79, 109, 23);
		contentPane.add(rdbtnOrdinato);
		
		JLabel lblValoriDaGenerare = new JLabel("Valori da generare:");
		lblValoriDaGenerare.setBounds(10, 12, 120, 14);
		contentPane.add(lblValoriDaGenerare);
		
		JSpinner spin = new JSpinner();
		spin.setBounds(130, 8, 70, 23);
		contentPane.add(spin);
		
		JSpinner spin1 = new JSpinner();
		spin1.setBounds(40, 42, 75, 23);
		contentPane.add(spin1);
		
		JSpinner spin2 = new JSpinner();
		spin2.setBounds(202, 42, 75, 23);
		contentPane.add(spin2);
		
		TextArea generated = new TextArea();
		generated.setBounds(10, 179, 267, 176);
		generated.setEditable(false);
		contentPane.add(generated);
		
		JButton btnGenera = new JButton("Genera");
		btnGenera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				generated.setText("");
				
				Random rand = new Random();
				int n = (int) spin.getValue(), min = (int) spin1.getValue(), max = (int) spin2.getValue();
				if (min < max) {
					generated.setForeground(Color.BLACK);
					
					if (!rdbtnOrdinato.isSelected()) {
						for (int i = 0; i < n; i++)
							generated.append(rand.nextInt(max-min)+min + "\n");
					} else {
						int a[] = new int[n], k, temp;
						
						for (int i = 0; i < n; i++)
							a[i] = rand.nextInt(max-min)+min;
						
						do {
							k = 0;
							for (int i = 0; i < n-1; i++)
								if (chckbxCrescente.isSelected() && a[i] > a[i+1]) {
									temp = a[i+1];
									a[i+1] = a[i];
									a[i] = temp;
									k = 1;
								} else if (chckbxDecrescente.isSelected() && a[i] < a[i+1]) {
									temp = a[i+1];
									a[i+1] = a[i];
									a[i] = temp;
									k = 1;
								}
						}while(k == 1);
						
						for (int i = 0; i < n; i++)
							generated.append(a[i] + "\n");
					}
				} else {
					generated.setForeground(Color.RED);
					generated.setText("Attenzione! Minimo e massimo non validi." );
				}
			}
		});
		btnGenera.setFont(new Font("Arial", Font.BOLD, 16));
		btnGenera.setBounds(10, 140, 267, 33);
		contentPane.add(btnGenera);
	}
}