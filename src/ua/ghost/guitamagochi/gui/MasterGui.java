package ua.ghost.guitamagochi.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ListModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import ua.ghost.guitamagochi.Game;
import ua.ghost.guitamagochi.pets.*;
import ua.ghost.mylibrary.ImageLoader;
import ua.ghost.mylibrary.Log;
import ua.ghost.mylibrary.components.ImageView;

public class MasterGui {
	
	private JFrame frame;
	private JList<Pet> list;
	private ImageView petImage;
	
	public JLabel name, type, age, hungry;
	private JButton food, play;
	
	private DefaultListModel<Pet> model;
	private final String LOG_TAG="Master gui";
	
	
	private final BufferedImage NULL_PET=ImageLoader.loadImage("/unknown.png");
	
	private JTextPane txt;
	
	
	public MasterGui(){

		
		frame=new JFrame("Тамагори-экстрим");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 600);
		frame.setLocation(100, 100);
		frame.setLayout(null);
		
		initList();
		initInfoPane();
		intiTextPane();
		initBtnPane();
		
		frame.setResizable(false);
		frame.setVisible(true);

		
	}
	
	
	private void initList(){
		
		model=new DefaultListModel<Pet>();

		//TODO dont forgwt remuve this nahren
		model.addElement(new Cat("Мурззик"));
		model.addElement(new Dog("Мухтар"));
		model.addElement(new Hamster("Халк"));
		model.addElement(new Hedgehog("Упырок"));
		model.addElement(new Rat("Лариса"));
		model.addElement(new Rabbit("Иннокентий"));
			
		list = new JList(model);
		
		list.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				if(!e.getValueIsAdjusting()){
					Log.d("список", "Изменилось выделение");
					Pet pet = list.getSelectedValue();
					fillInfoPanel(pet);
				}
				
			}
		});
		
		
		JScrollPane scroll = new JScrollPane(list);
		scroll.setBounds(10, 5, 200, 550);
		frame.add(scroll);
		
	}
	
	
	private void initInfoPane(){
		JPanel pane=new JPanel();
		pane.setBounds(215, 5, 415, 160);
		pane.setBorder(BorderFactory.createLineBorder(Color.black));
		pane.setLayout(null);
		
		petImage=new ImageView("/unknown.png");
		petImage.setLocation(5, 5);
		pane.add(petImage);
		
		JLabel l1=new JLabel("Информация о питомце", SwingConstants.CENTER);
		l1.setBounds(160, 5, 250, 15);
		pane.add(l1);
		
		l1=new JLabel("Имя");
		l1.setBounds(160, 25, 60, 15);
		pane.add(l1);
		
		l1=new JLabel("Порода");
		l1.setBounds(160, 45, 60, 15);
		pane.add(l1);
		
		l1=new JLabel("Возраст");
		l1.setBounds(160, 65, 60, 15);
		//l1.setBorder(BorderFactory.createLineBorder(Color.black));
		pane.add(l1);
		
		name=new JLabel("none");
		name.setBounds(225, 25, 100, 15);
		pane.add(name);
		
		type=new JLabel("none");
		type.setBounds(225, 45, 100, 15);
		pane.add(type);
		
		age=new JLabel("none");
		age.setBounds(225, 65, 100, 15);
		pane.add(age);
		
		hungry=new JLabel("Не голоден", SwingConstants.CENTER);
		hungry.setBounds(160, 85, 250, 15);
		hungry.setOpaque(true);
		hungry.setBackground(Color.green);
		pane.add(hungry);
		
		food=new JButton("Кормить");
		food.setBounds(160, 135, 120, 20);
		food.setEnabled(false);
		food.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				list.getSelectedValue().food();
				fillInfoPanel(list.getSelectedValue());
				
			}
		});
		
		pane.add(food);
		
		play=new JButton("Играть");
		play.setBounds(290, 135, 120, 20);
		play.setEnabled(false);
		play.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				list.getSelectedValue().gaming();
				fillInfoPanel(list.getSelectedValue());
				
			}
		});
		pane.add(play);
		
		frame.add(pane);
	}

	private void fillInfoPanel(Pet pet){
		
		if(pet==null){
			setDefaultPetInfo();
			return;
		} 
		
		name.setText(pet.getName());
		type.setText(pet.getType());
		age.setText(" "+pet.getAge());
		
		if(pet.getHungry()){
			hungry.setText("Голоден");
			hungry.setBackground(Color.red);
			play.setEnabled(false);
			
		}else{
			hungry.setText("не голоден");
			hungry.setBackground(Color.green);
			play.setEnabled(true);
		}
		
		food.setEnabled(true);
		
		petImage.setImage(pet.getImage());
		
		writeToLog("Выбран питомец "+pet.getName()+" ("+pet.getType()+")");
		
	}
	
	private void setDefaultPetInfo(){
		
		name.setText("none");
		type.setText("none");
		age.setText(" 0");
		
		hungry.setText("---------");
		hungry.setBackground(Color.red);
		
		food.setEnabled(false);
		play.setEnabled(false);
		
		petImage.setImage(NULL_PET);
		
	}
	
	
	private void intiTextPane(){
		txt = new JTextPane();
		Font font = new Font("Verdana", Font.PLAIN, 12);
		txt.setFont(font);
		
		Game.setOutput(txt);
		
		JScrollPane scroll=new JScrollPane(txt);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setBounds(215, 170, 415, 385);
		
		frame.add(scroll);
	}
	
	
	private void initBtnPane(){
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createLineBorder(Color.black));
		panel.setBounds(635, 5, 150, 550);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));  
		
		panel.add(Box.createVerticalStrut(5));
		
		JButton btn=new JButton("Новый питомец");
		btn.setAlignmentX(Component.CENTER_ALIGNMENT);
		btn.setPreferredSize(new Dimension(145, 20));
		btn.setMinimumSize(new Dimension(145, 20));
		btn.setMaximumSize(new Dimension(145, 20));
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				AddPetDialog dialog = new AddPetDialog(MasterGui.this);
				
			}
		});
		
		panel.add(btn);
		
		panel.add(Box.createVerticalStrut(5));
		
		btn=new JButton("Завершить день");
		btn.setAlignmentX(Component.CENTER_ALIGNMENT);
		btn.setPreferredSize(new Dimension(145, 20));
		btn.setMinimumSize(new Dimension(145, 20));
		btn.setMaximumSize(new Dimension(145, 20));
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				makeNight();
				
			}
		});
		
		
		panel.add(btn);
		
		panel.add(Box.createVerticalStrut(15));
		
		btn=new JButton("Очистить лог");
		btn.setAlignmentX(Component.CENTER_ALIGNMENT);
		btn.setPreferredSize(new Dimension(145, 20));
		btn.setMinimumSize(new Dimension(145, 20));
		btn.setMaximumSize(new Dimension(145, 20));
		
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				txt.setText("");
				
			}
		});
		
		panel.add(btn);
		
		panel.add(Box.createVerticalStrut(30));
		
		btn=new JButton("Окно поединка");
		btn.setAlignmentX(Component.CENTER_ALIGNMENT);
		btn.setPreferredSize(new Dimension(145, 20));
		btn.setMinimumSize(new Dimension(145, 20));
		btn.setMaximumSize(new Dimension(145, 20));
		
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				FightDialog dialog2 = new FightDialog(MasterGui.this);
				//new FightGui(model);
			}
		});
		
		panel.add(btn);
		
		frame.add(panel);
	}
	
	
	public void writeToLog(String message){
		message = Log.time()+" -> "+message+System.lineSeparator();
		txt.setText(txt.getText()+message);
	}
	
	public void addPetToList(Pet pet){
		model.addElement(pet);
		writeToLog("Добавлен питомец "+pet.getName()+" и он "+pet.getType());
	}
	
	private void makeNight(){
		
		
		
		
		for(int i=0; i<list.getModel().getSize(); i++){
			list.getModel().getElementAt(i).sleep();
		}
		
		for(int i=list.getModel().getSize()-1; i>=0; i--){
			Pet pet=list.getModel().getElementAt(i);
			if(!pet.getAlive()){
				model.remove(i);
			}
			fillInfoPanel(list.getSelectedValue());
		}
		
		if(list.getModel().getSize()==0){
			Game.writeToLog("Батенька, два у вас все сдохли!");
			return;
		}
		
		//Шансы на драку
		int rnd = Game.getRnd(1, 100);
		if(rnd<90){
			//что-то делаем
			frame.setVisible(false);
			//frame.setEnabled(false);
			FightDialog dialog2 = new FightDialog(this);
			
			//new FightGui(model);
		}
		
	
		
	}
	
	
	public DefaultListModel<Pet> getModel(){
		return model;
	}
	
	public JFrame getFrame(){
		return frame;
	}
	
	
}
