package ua.ghost.guitamagochi.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import ua.ghost.guitamagochi.pets.Pet;
import ua.ghost.guitamagochi.pets.PetTypes;
import ua.ghost.mylibrary.Log;
import ua.ghost.mylibrary.components.ImageView;

public class FightDialog {

	private JFrame frame;
	public JList<Pet> pet;
	private MasterGui parent;
	
	public FightDialog(MasterGui parent){
		
		this.parent=parent;
		
		
		
		frame = new JFrame("Выбор питомца для боя");
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setSize(300, 180);
		frame.setLocationRelativeTo(null);
		frame.setLayout(null);
		//frame.setUndecorated(true);
	    
		
		initInterface();
		
		frame.setResizable(false);
		frame.setVisible(true);
		
	}

	private void initInterface() {
		Font font = new Font("Verdana", Font.PLAIN, 12);
		JLabel l1 = new JLabel("Питомец:");
		
		pet = new JList(parent.getModel());
		
		pet.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JButton btn_Selection=new JButton("Selection");
		l1.setBounds(0, 0, 200, 25);
		pet.setBounds(0, 25, 295, 100);
		pet.setBorder(BorderFactory.createEtchedBorder()); 
		
		
		btn_Selection.setBounds(0, 125, 98, 25);
		btn_Selection.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SelectionPet();
				
			}
		});
		
				
		frame.add(l1);
		frame.add(pet);
		frame.add(btn_Selection);

		JScrollPane scroll = new JScrollPane(pet);
		scroll.setBounds(0, 25, 290, 100);
		frame.add(scroll);
		
	}
	
	
	private void SelectionPet(){
		
		if(pet==null) Log.e("Fight dialog", "Pet is null");
		new FightGui(pet.getSelectedValue(), parent);
		frame.dispose();
		
	}
}

