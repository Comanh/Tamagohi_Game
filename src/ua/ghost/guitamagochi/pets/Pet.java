package ua.ghost.guitamagochi.pets;

import java.awt.image.BufferedImage;

import ua.ghost.guitamagochi.Game;
import ua.ghost.mylibrary.Log;

public class Pet {
	
	private String name;
	
	protected PetTypes type=PetTypes.���������;
	
	public int age=0, maxFood=5;
	protected boolean hungry=false;
	private boolean fatigue=false;
	private boolean showMenu=false;
	public  boolean alive=true;
	public int innerFood=5;
	
	protected BufferedImage img = null;
	
	protected int hits=100, attack=20;
	
	
	public Pet(String name){
		this.name=name;
		
	}

	public void gaming(){
		if (innerFood>=3) 
			Game.writeToLog("������ � �������� ");
		innerFood--;
		
		if(innerFood<3){
			Game.writeToLog("������� ����� � �������. ��� �� �������� ������.");
			hungry=true;
			fatigue=true;
		} 
		 
		return;
	}
	
	public void food(){
		innerFood++;
		if(innerFood>maxFood) innerFood=maxFood;
		if(innerFood>=3){
			hungry=false; 
			fatigue=false;
		}
			
	}
	
	public void sleep(){
		
		Log.d("", "Pet "+name+" go to sleep");
		Game.writeToLog("������� "+name+"("+type+") �����"); 
		age++;
		innerFood--;
		if(innerFood<3){
			hungry=true;
			
			Game.writeToLog("��������! "+type+" "+name+" ������������");
			
		}
		
		if(innerFood<=0){
			alive=false;
			Game.writeToLog("��������! "+type+" "+name+" �����.");
		} 
 		
	}
	
	public String getName(){
		return name;
	}
	
	public String getType(){
		return type.toString();
	}
	
	public boolean getAlive(){
		return alive;
	}
	
	public void setAlive(boolean alive){
		this.alive=alive;
	}
	
	@Override
	public String toString(){
		return this.name+" ("+this.type+")";
	}
	
	public int getAge(){
		return age;
	}
	
	public boolean getHungry()
	{
		return hungry;
	}	
	
	public BufferedImage getImage(){
		return img;
	}
	
	public int getHits(){
		return hits;
	}
	
	public int getAttack(){
		return attack;
	}
	
	public void hit(int damage){
		hits-=damage;
		if(hits<0) alive=false;
		
	}

}
