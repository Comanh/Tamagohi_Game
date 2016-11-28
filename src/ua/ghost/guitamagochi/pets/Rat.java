package ua.ghost.guitamagochi.pets;

import ua.ghost.mylibrary.ImageLoader;

public class Rat extends Pet {

	public Rat(String name) {
		super(name);
		this.img=ImageLoader.loadImage("/rat01.png");
		this.type=PetTypes.крыса;
	}

}
