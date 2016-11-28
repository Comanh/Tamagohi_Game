package ua.ghost.guitamagochi.pets;

import ua.ghost.mylibrary.ImageLoader;

public class Hedgehog extends Pet {

	public Hedgehog(String name) {
		super(name);
		this.img=ImageLoader.loadImage("/hedgehog02.png");
		this.type=PetTypes.йобжик;
	}

}
