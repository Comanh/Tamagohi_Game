package ua.ghost.guitamagochi.pets;

import ua.ghost.mylibrary.ImageLoader;

public class Dog extends Pet {

	public Dog(String name) {
		super(name);
		this.img=ImageLoader.loadImage("/dog01.png");
		this.type=PetTypes.псина;
	}

}
