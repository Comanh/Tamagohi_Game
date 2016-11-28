package ua.ghost.guitamagochi.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
//import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
//import javax.swing.ComboBoxModel;
//import javax.swing.DefaultComboBoxModel;
//import javax.swing.DefaultListModel;
//import javax.swing.GroupLayout.Group;
//import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
//import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import ua.ghost.guitamagochi.Game;
import ua.ghost.guitamagochi.components.MyProgressBar;
import ua.ghost.guitamagochi.pets.Invider;
import ua.ghost.guitamagochi.pets.Pet;
//import ua.ghost.mylibrary.ImageLoader;
import ua.ghost.mylibrary.Log;
import ua.ghost.mylibrary.components.ImageView;

public class FightGui {
	
	JFrame frame;
	ButtonGroup atakGroup = new ButtonGroup();
	ButtonGroup defensGroup = new ButtonGroup();
	
	private JCheckBox chb1, chb2;
//	private JComboBox combo;
	private Pet defender, invider;
	
	public JList<Pet> pet;
	private MasterGui parent;
	private ImageView petImage;
	
	private MyProgressBar invProgress, defProgress;
	
	
//	private final BufferedImage NULL_PET=ImageLoader.loadImage("/unknown.png");
	
	private JTextPane txt;
	
public FightGui(Pet defender, MasterGui masterGui) {
		
		parent = masterGui;
	
		this.defender=defender;
		this.invider=new Invider();
	
		
		
		frame=new JFrame();
		frame.setTitle("Нападение на стадо!");
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		
		frame.setBounds(300, 50, 800, 600);
		frame.setLayout(null);	
		atak();
		defens();
		ability();
		pb_pet();
		pb_invader();
		namepet ();
		nameinveder ();
		
		txt= new JTextPane();
		txt.setFont(Game.defFont);
				
		JScrollPane scroll = new JScrollPane(txt);
		scroll.setBounds(5, 300, 785, 270);
		writeToLog("Нападение на стадо,"+" стадо защищает, "+defender.getType()+ " "+defender.getName()+".");
		
		
		frame.add(scroll);
		
		
		
		JPanel panelpet = new JPanel();
		panelpet.setBackground(Color.WHITE);
		panelpet.setBounds(50, 55, 150, 150);
		panelpet.setBorder(BorderFactory.createLineBorder(Color.black));
		Border border1 = BorderFactory.createEtchedBorder(EtchedBorder.RAISED) ;
		panelpet.setBorder (border1);
		panelpet.setLayout(null);
				
		petImage=new ImageView("/unknown.png");
		petImage.setLocation(0, 0);
		panelpet.add(petImage);
		petImage.setImage(defender.getImage());
		frame.add(panelpet);
		
			
		JPanel panelenemy = new JPanel();
		panelenemy.setBackground(Color.WHITE);
		panelenemy.setBounds(590, 55, 150, 150);
		frame.add(panelenemy);
		Border border2 = BorderFactory.createEtchedBorder(EtchedBorder.RAISED) ;
		panelenemy.setBorder (border2);
		panelenemy.setLayout(null);
		
		petImage=new ImageView("/inveder.png");
		petImage.setLocation(0, 0);
		petImage.setImage(invider.getImage());
		
		panelenemy.add(petImage);
		
		frame.add(panelenemy);
		
		JPanel panelbtn = new JPanel();   
		panelbtn.setBackground(Color.WHITE);
		panelbtn.setBounds(260, 170, 310, 15);
		panelbtn.setLayout(null);
		frame.add(panelbtn);
		Border border3 = BorderFactory.createEtchedBorder(EtchedBorder.RAISED) ;
		panelenemy.setBorder (border3);
		
		JButton btn = new JButton();
		btn.setBounds(100, 0, 90, 15);
		btn.setText("Вперед!!!");
		btn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
			
			actionButton();
		}
			
		});
		panelbtn.add(btn);
		
	
		
		frame.setVisible(true);	
		frame.setResizable(false);
		
		frame.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				Log.d("Fight gui", "window Closing");
				//Аварийный выход
				defender.setAlive(false);
				parent.getModel().removeElement(defender);
				parent.getFrame().setVisible(true);
				Game.writeToLog("Питомец "+defender.getName()+" позорно слился и помер!");
				frame.dispose();
				
				
				
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				Log.d("Fight gui", "window Closed");
				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}
	
	private void actionButton(){
		
		int inviderAttac, inviderDefence;
		inviderAttac=Game.getRnd(1, 5);
		inviderDefence=Game.getRnd(1, 5);
		
		int defAttac=0, defDefence=0;
		
		if(atakGroup.getSelection()==null || defensGroup.getSelection()==null){
			return;
		}
		
		String str1 = atakGroup.getSelection().getActionCommand();
		String str2 = defensGroup.getSelection().getActionCommand();
		
				
		
	//--------------------------------------------------------------------------------------------	
			

					
			switch(str1){

			case "atak1":
				defAttac=1;
				break;
			case "atak2":
				defAttac=2;
				break;
			case "atak3":
				defAttac=3;
				break;
			case "atak4":
				defAttac=4;
				break;
			case "atak5":
				defAttac=5;
				break;
			}
			
			switch(str2){	
			case "defens1":
				defDefence=1;
				break;
			case "defens2":
				defDefence=2;
				break;
			case "defens3":
				defDefence=3;
				break;
			case "defens4":
				defDefence=4;
				break;
			case "defens5":
				defDefence=5;
				break;
				
			}
		
			int defAttacKey = defAttac;
			String x;
			switch (defAttacKey) {
			    case 1:  x = "голову";
			             break;
			    case 2:  x = "грудь";
			             break;
			    case 3:  x = "живот";
			             break;
			    case 4:  x = "пояс (пах)";
			             break;
			    case 5:  x = "";
			             break;
			    default: x = "Ошибка!!!";
			             break;
			}
			
			int defDefenceKey = defDefence;
			String y;
			switch (defDefenceKey) {
			    case 1:  y = "голову";
			             break;
			    case 2:  y = "грудь";
			             break;
			    case 3:  y = "живот";
			             break;
			    case 4:  y = "пояс (пах)";
			             break;
			    case 5:  y = "ноги и голову";
			             break;
			    default: y = "Ошибка!!!";
			             break;
			}
			
			int inviderAttacKey = inviderAttac;
			String xi;
			switch (inviderAttacKey) {
			    case 1:  xi = "голову";
			             break;
			    case 2:  xi = "грудь";
			             break;
			    case 3:  xi = "живот";
			             break;
			    case 4:  xi = "пояс(пах)";
			             break;
			    case 5:  xi = "";
			            break;
			    default: xi = "Ошибка!!!";
			             break;
			}
			
			
			int inviderDefenceKey = inviderDefence;
			String yi;
			switch (inviderDefenceKey) {
			    case 1:  yi = "голову";
			             break;
			    case 2:  yi = "грудь";
			             break;
			    case 3:  yi = "живот";
			             break;
			    case 4:  yi = "пояс(пах)";
			             break;
			    case 5:  yi = "ноги и голову";
			             break;
			    default: yi = "Ошибка!!!";
			             break;
			}
		
			String g = null;
			
			
			if (defAttac==5 ) 
				 g = " ударил по ногам";
			else g= " ударил в ";
			
			String j = null;
			
			
			if (inviderAttac==5) 
				 j = " ударил по ногам";
			else j= " ударил в ";
			
			
			writeToLog(defender.getName()+ g + x + " и защитил ,"+y+".");
			writeToLog(invider.getName() + j + xi + " и защитил ,"+yi+".");
			Log.d("Поединок", "invider: a="+inviderAttac+" d="+inviderDefence+"  defender: a="+defAttac+" d="+defDefence);
			
			//TODO: блок рассчета полученых повреждений
			
				
			int defDamage=invider.getAttack();
			if(inviderAttac == defDefence || inviderAttac == defDefence-1 || (inviderAttac==1 && defDefence==5) ) defDamage/=2;
			
			int invDamage=defender.getAttack();
			if(defAttac == inviderDefence || defAttac == inviderDefence-1 || (defAttac==1 && inviderDefence==5) ) invDamage/=2;
			

			if(chb1.isSelected()) {
writeToLog(defender.getName()+ " наорал на противника!!! Наносимый урон увеличелся.");
			invDamage +=5;
			}
			
			if(chb2.isSelected()){
writeToLog(defender.getName()+ " подтянул лапки???  Получаемый урон уменьшился.");
			defDamage-=5;
			}	
			
			writeToLog(defender.getName()+" получил по щам на "+defDamage+".");
			writeToLog(invider.getName()+" получил по щам на "+invDamage+".");
			
			defender.hit(defDamage);
			invider.hit(invDamage);
			
			if(!invider.getAlive()){
				writeToLog(invider.getName()+" помер! Победил "+defender.getName());
				Game.writeToLog(invider.getName()+" помер! Победил "+defender.getName());
				parent.getFrame().setVisible(true);
				frame.dispose();
				
			}
			
			if(!defender.getAlive()){
				writeToLog(defender.getName()+" помер! Победил "+invider.getName());
				Game.writeToLog(defender.getName()+" помер! Победил "+invider.getName());
				parent.getModel().removeElement(defender);
				parent.getFrame().setVisible(true);
				frame.dispose();
			}
			
			//
			
			
			
			defProgress.setValue(defender.getHits());
			invProgress.setValue(invider.getHits());
			
			
 
	}
	
	

	private void namepet (){
		// TODO XpeH
				JPanel panelpetname=new JPanel();
				panelpetname.setBackground(Color.WHITE);
				panelpetname.setBounds(51, 5, 200, 20);
			    panelpetname.setBorder(BorderFactory.createLineBorder(Color.black));
				frame.add(panelpetname);
				panelpetname.setLayout(null);
				
				JLabel l1 = new JLabel("Имя : ");
				l1.setBounds(1, 0, 60, 15);
				panelpetname.add(l1);
	
				JLabel name = new JLabel (defender.getName());
				name.setBounds(40, 0, 150, 15);
				panelpetname.add(name);
	
				name.setText(defender.getName());
	}
	
	private void nameinveder (){
		// TODO XpeH3
				JPanel panelpetname=new JPanel();
				panelpetname.setBackground(Color.WHITE);
				panelpetname.setBounds(590, 5, 200, 20);
			    panelpetname.setBorder(BorderFactory.createLineBorder(Color.black));
				frame.add(panelpetname);
				panelpetname.setLayout(null);
				
				//JLabel l1 = new JLabel("Космозахватчик");
				JLabel l1 = new JLabel(invider.getName());
				
				l1.setBounds(1, 0, 195, 15);
				panelpetname.add(l1);
	
			//	JLabel name = new JLabel (inveder.getName());
			//	name.setBounds(40, 0, 150, 15);
			//	panelpetname.add(name);
	
			//	name.setText(inveder.getName());
	}
	
	private void pb_pet(){
		
		defProgress = new MyProgressBar();
		defProgress.setBounds(51, 30, 200, 20);
		defProgress.setBorder(BorderFactory.createLineBorder(Color.black));
		
		defProgress.setValue(100);
		
		frame.add(defProgress);
		
	}

	
private void pb_invader(){
		
		invProgress = new MyProgressBar();
		invProgress.setBounds(590, 30, 200, 20);
		invProgress.setBorder(BorderFactory.createLineBorder(Color.black));
		
		invProgress.setValue(100);
		
		frame.add(invProgress);
		
	}
	
	private void atak (){
		JPanel panel1 = new JPanel();
		panel1.setBounds(250, 30, 150, 130);
		panel1.setLayout(null);
		
		JLabel label1 = new JLabel ("Атака");
		label1.setBounds(50, 15, 50, 20);
		label1.setOpaque(true);
		label1.setBackground(Color.WHITE);
		panel1.add(label1);
		
		JRadioButton rbtn1 = new JRadioButton();
		rbtn1.setText("удар в голову");
		rbtn1.setBounds(5, 35, 150, 15);
		rbtn1.setActionCommand("atak1");
		panel1.add(rbtn1);
		
		JRadioButton rbtn2 = new JRadioButton();
		rbtn2.setText("удар в грудь");
		rbtn2.setBounds(5, 55, 150, 15);
		rbtn2.setActionCommand("atak2");
		panel1.add(rbtn2);
		
		JRadioButton rbtn3 = new JRadioButton();
		rbtn3.setText("удар в живот,");
		rbtn3.setBounds(5, 75, 150, 15);
		rbtn3.setActionCommand("atak3");
		panel1.add(rbtn3);
		
		JRadioButton rbtn4 = new JRadioButton();
		rbtn4.setText("удар в пояс(пах)");
		rbtn4.setBounds(5, 95, 150, 15);
		rbtn4.setActionCommand("atak4");
		panel1.add(rbtn4);
		
		JRadioButton rbtn5 = new JRadioButton();
		rbtn5.setText("удар по ногам");
		rbtn5.setBounds(5, 115, 150, 15);
		rbtn5.setActionCommand("atak5");
		panel1.add(rbtn5);
		
		atakGroup.add(rbtn1);
		atakGroup.add(rbtn2);
		atakGroup.add(rbtn3);
		atakGroup.add(rbtn4);
		atakGroup.add(rbtn5);
		
		frame.add(panel1);
	}
	
	private void defens(){
		JPanel panel2 = new JPanel();
	//	panel2.setBackground(Color.WHITE);
		panel2.setBounds(410, 30, 150, 130);
		panel2.setLayout(null);
		
		JLabel label1 = new JLabel ("Защита");
		label1.setBounds(50, 15, 50, 20);
		label1.setOpaque(true);
		label1.setBackground(Color.WHITE);
		panel2.add(label1);
		
		JRadioButton rbtn1 = new JRadioButton();
		rbtn1.setText("блок головы и груди");
		rbtn1.setBounds(5, 35, 150, 15);
		rbtn1.setActionCommand("defens1");
		panel2.add(rbtn1);
		
		JRadioButton rbtn2 = new JRadioButton();
		rbtn2.setText("блок груди и живота");
		rbtn2.setBounds(5, 55, 150, 15);
		rbtn2.setActionCommand("defens2");
		panel2.add(rbtn2);
		
		JRadioButton rbtn3 = new JRadioButton();
		rbtn3.setText("блок живота и пояса");
		rbtn3.setBounds(5, 75, 150, 15);
		rbtn3.setActionCommand("defens3");
		panel2.add(rbtn3);
		
		JRadioButton rbtn4 = new JRadioButton();
		rbtn4.setText("блок пояса и ног");
		rbtn4.setBounds(5, 95, 150, 15);
		rbtn4.setActionCommand("defens4");
		panel2.add(rbtn4);
		
		JRadioButton rbtn5 = new JRadioButton();
		rbtn5.setText("блок ног и головы");
		rbtn5.setBounds(5, 115, 150, 15);
		rbtn5.setActionCommand("defens5");
		panel2.add(rbtn5);
		
		defensGroup.add(rbtn1);
		defensGroup.add(rbtn2);
		defensGroup.add(rbtn3);
		defensGroup.add(rbtn4);
		defensGroup.add(rbtn5);

		frame.add(panel2);
			
	}
		
		private void ability(){
			
			JPanel panel = new JPanel();
	//		panel.setBackground(new Color(238, 232, 170));
			panel.setBounds(260, 190, 310, 15);
			panel.setLayout(null);
			
			chb1 = new JCheckBox();
			chb1.setText("Подать голос");
			chb1.setBounds(0, 0, 150, 15);
			panel.add(chb1);
			
			
			chb2 = new JCheckBox();
			chb2.setText("Занять оборону");
			chb2.setBounds(185, 0, 150, 15);
			panel.add(chb2);
			
			frame.add(panel);
		}

		public void writeToLog(String message){
			message = Log.time()+" -> "+message+System.lineSeparator();
				txt.setText(txt.getText()+message);
			}
			
			
		}
		






