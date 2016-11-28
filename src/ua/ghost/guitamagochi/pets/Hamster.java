package ua.ghost.guitamagochi.pets;

import ua.ghost.mylibrary.ImageLoader;

public class Hamster extends Pet {

	public Hamster(String name) {
		super(name);
		this.img=ImageLoader.loadImage("/hamster01.png");
		this.type=PetTypes.хомяк;
	}

}
