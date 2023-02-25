package demogame;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Iterator;

// Haritanýn, levellarýn oluþturulduðu Classtýr.
public class MapGenerator {
	public Block map[][];
	
	// Hangi Haritanýn oluþturulacaðýna karar verir.
	public MapGenerator(int row, int col, int level) {
		
		map = new Block[row][col];
		
		switch(level) {
		  case 1:
			  CreateMapSelectedMap(row, col, 1);
		    break;
		  case 2:
			  CreateMapSelectedMap(row, col, 2);
		    break;
		  case 3:
			  CreateMapSelectedMap(row, col, 3);
			break;
		  default:
			  CreateMapSelectedMap(row, col, 1);
		}
		
	}
	
	//Seçilen Level haritasýna göre block objelerini oluþturur.
	
	public void CreateMapSelectedMap(int row_, int col_, int selected_level) {
		for(int i=0; i<row_; i++) {
			
			for(int j=0; j<col_; j++) {
				if (selected_level == 3) {
					if (i == 0) {
						Block block_ = new Block(3); // 1 --> Level 1 , 2 --> Level 2, 3 --> level 3
						block_.setBlock_color(Color.black);
						map[i][j] = block_;	
					}else if( i == 1) {
						Block block_ = new Block(2); // 1 --> Level 1 , 2 --> Level 2, 3 --> level 3
						block_.setBlock_color(Color.orange);
						map[i][j] = block_;	
					}else {
						Block block_ = new Block(1); // 1 --> Level 1 , 2 --> Level 2, 3 --> level 3
						map[i][j] = block_;	
					}
				}else if(selected_level == 2) {
					if (i == 1) {
						Block block_ = new Block(2); // 1 --> Level 1 , 2 --> Level 2, 3 --> level 3
						block_.setBlock_color(Color.orange);
						map[i][j] = block_;	
					}else {
						Block block_ = new Block(1); // 1 --> Level 1 , 2 --> Level 2, 3 --> level 3
						map[i][j] = block_;	
					}
				}else {
					Block block_ = new Block(1); // 1 --> Level 1 , 2 --> Level 2, 3 --> level 3
					map[i][j] = block_;	
				}
				
			}
		}
	}
	
	// Bloðun canýný her bir çarpmadan sonra update eder.
	public int UpdateBlockResistance(int value, Block block_) {
		block_.setBlock_resistance(block_.getBlock_resistance() - value );
		return block_.getBlock_resistance();
		
	}
	
	// haritayý renderlar
	public void draw(Graphics2D g) {
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map[0].length; j++) {
				if(map[i][j].getBlock_resistance() > 0) {
					
					// Render Blocks
					g.setColor(map[i][j].getBlock_color());
					g.fillRect(j * map[i][j].getBlock_width() + 80, i * map[i][j].getBlock_heigth() + 50, map[i][j].getBlock_width(), map[i][j].getBlock_heigth()); 
					
					g.setColor(Color.blue);
					g.setStroke(new BasicStroke(3));
					g.drawRect(j * map[i][j].getBlock_width() + 80, i * map[i][j].getBlock_heigth() + 50, map[i][j].getBlock_width(), map[i][j].getBlock_heigth());
				}
			}
		}
	}
}
