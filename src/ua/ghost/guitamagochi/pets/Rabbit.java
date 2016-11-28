package ua.ghost.guitamagochi.pets;

import ua.ghost.mylibrary.ImageLoader;

public class Rabbit extends Pet {

	public Rabbit(String name) {
		super(name);
		this.img=ImageLoader.loadImage("/rabbit01.png");
		this.type=PetTypes.кролик;
	}

}
