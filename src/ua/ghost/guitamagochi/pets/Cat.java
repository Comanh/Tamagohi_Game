package ua.ghost.guitamagochi.pets;

import ua.ghost.mylibrary.ImageLoader;

public class Cat extends Pet {

	public Cat(String name) {
		super(name);
		this.type=PetTypes.�����;
		img=ImageLoader.loadImage("/cat01.png");
		
		examp();
		
		
	}
	
	
	private Pet examp(){
		
		Pet res;
		res = new Rat("�����");
		
		return res;
	}
	
	
}
