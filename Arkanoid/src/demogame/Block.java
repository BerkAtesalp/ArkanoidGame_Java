package demogame;

import java.awt.Color;

// Block Classý Blocklarýn özelliklerini belirtir
public class Block {
	private int block_width = 60; // Block geniþliði
	private int block_height = 50; // Block uzunluðu
	public int block_resistance;	// Bloðun direnci
	private Color block_color; // Bloðun rengi
	
	public Block(int block_resistance_) {
		this.block_resistance = block_resistance_; 
		this.block_color = Color.red;
	}
	
	public int getBlock_width() {
		return block_width;
	}
	
	public Color getBlock_color() {
		return block_color;
	}

	public void setBlock_color(Color block_color) {
		this.block_color = block_color;
	}

	public int getBlock_heigth() {
		return block_height;
	}
	
	public int getBlock_resistance() {
		return this.block_resistance;
	}
	
	public void setBlock_resistance(int new_block_resistance) {
		this.block_resistance = new_block_resistance;
	}
}
