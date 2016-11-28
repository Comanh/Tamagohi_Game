package ua.ghost.guitamagochi.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import ua.ghost.guitamagochi.pets.*;

import ua.ghost.mylibrary.Log;

public class AddPetDialog {

	private JFrame frame;
	private JComboBox<PetTypes> type;
	private JTextField name;
	
	private MasterGui parent;
	
	
	public AddPetDialog(MasterGui parent){
		
		this.parent=parent;
		
		
		
		frame = new JFrame("Добавить питомца");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(300, 180);
		frame.setLocationRelativeTo(null);
		frame.setLayout(null);
		
		initInterface();

		//------------------
		Locale loc = new Locale("ru","RU");
	    frame.setLocale(loc);
	    frame.getInputContext().selectInputMethod(loc);
	    //------------------
		
		frame.setResizable(false);
		frame.setVisible(true);
		
	}

	private void initInterface() {
		// TODO Auto-generated method stub
		
		Font font = new Font("Verdana", Font.PLAIN, 12);
		
		JLabel l1 = new JLabel("Имя:");
		name=new JTextField();
		name.setFont(font);
		
		type=new JComboBox<PetTypes>(PetTypes.values());
		type.removeItem(PetTypes.Неведомое);
		
		JButton btn_ok=new JButton("Ok");
		JButton btn_cansel=new JButton("Cansel");
		
		l1.setBounds(50, 10, 50, 10);
		name.setBounds(50, 25, 200, 25);
		type.setBounds(50, 55, 200, 25);
		btn_ok.setBounds(50, 95, 98, 25);
		btn_ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addPet();
				
			}
		});
		
		
		
		btn_cansel.setBounds(152, 95, 98, 25);
		btn_cansel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				
			}
		});
		
		
		frame.add(l1);
		frame.add(name);
		frame.add(type);
		frame.add(btn_ok);
		frame.add(btn_cansel);
		
	}
	
	
	private void addPet(){
		
		String newName=name.getText();
		PetTypes newType= (PetTypes)type.getSelectedItem();
		
		if(newName.length()<3){
			Log.e("Добавление питомца", "Слишком короткое имя");
			return;
		}
		
		Pet newPet=null;
		
		switch(newType){
		case котик:
			newPet=new Cat(newName);
			break;
		case псина:
			newPet=new Dog(newName);
			break;
		case хомяк:
			newPet=new Hamster(newName);
			break;
		case йобжик:
			newPet=new Hedgehog(newName);
			break;
		case крыса:
			newPet=new Rat(newName);
			break;
		case кролик:
			newPet=new Rabbit(newName);
			break;
		}
		
		Log.d("", "Новый питомец: имя="+newPet.getName()+" тип="+newPet.getType());

		parent.addPetToList(newPet);
		
		frame.dispose();
	}
	
}
