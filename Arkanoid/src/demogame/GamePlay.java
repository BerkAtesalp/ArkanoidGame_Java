package demogame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

// Oyun controllerinin, flowunun belirlendi�i yerdir.
public class GamePlay extends JPanel implements ActionListener, KeyListener {
	
	private int gameLevel = 1; // Oyunun initial level'�
	private boolean end_game_lock = true; // flag
	Player player = new Player();
	GameLevel gameLevelObj;
	private int prev_remain_block_num = 0; // kalan blo�un record�n� tutar
	
	public int getPrev_remain_block_num() {
		return prev_remain_block_num;
	}

	public void setPrev_remain_block_num(int prev_remain_block_num) {
		this.prev_remain_block_num = prev_remain_block_num;
	}

	private int block_life; // her bit gitten sonra blo�un ne kadar can� kald���n� tutar
	private int currentPt;
	private boolean play = false; // Oyunun devam edip etmeyece�ine karar veren flagt�r.
	private Timer timer;
	private int delay = 8;
	private MapGenerator map;
	
	// Oyunu istenilen Level ile y�kleyen constructor
	public GamePlay(int initial_game_level) {
		
		this.gameLevel = initial_game_level;
		this.gameLevelObj = new GameLevel(gameLevel);
		addKeyListener(this);
		
		setFocusable(true);
		setFocusTraversalKeysEnabled(true);
		
		DragListener dragListener = new DragListener();
		this.addMouseMotionListener(dragListener);
		
		timer = new Timer(delay, this);
		timer.start();
		
		map = new MapGenerator(3, 9, gameLevel); // initial map with level
	}
	
	// Haritay�, Objeleri renderlar
	public void paint(Graphics g) {
		
		// Black Canvas
		g.setColor(Color.blue);
		g.fillRect(1, 1, 692, 632);
		
		// Border
		g.setColor(Color.yellow);
		g.fillRect(0, 0, 692, 3);
		g.fillRect(0, 3, 3, 632);
		g.fillRect(681, 3, 3, 632);
		
		// Paddle
		g.setColor(Color.green);
		g.fillRect(gameLevelObj.getPaddle_position_X(), 550, 100, 8);
		
		
		// Block
		map.draw((Graphics2D)g);
		
		// Ball
		g.setColor(Color.white);
		g.fillOval(gameLevelObj.getBall_positionX(), gameLevelObj.getBall_positionY(), 20, 20);
		
		// Score
		g.setColor(Color.white);
		g.setFont(new Font("serif", Font.BOLD, 15));
		g.drawString("Score : " + player.getPlayer_score(), 50, 20);
		
		// Level
		g.setColor(Color.white);
		g.setFont(new Font("serif", Font.BOLD, 15));
		g.drawString("Level : " + gameLevel, 300, 20);
		
		// Lives
		g.setColor(Color.white);
		g.setFont(new Font("serif", Font.BOLD, 15));
		g.drawString("Live : " + player.getPlayer_lives(), 550, 20);
		
		
		
		// Top d��t���nde ki flowu i�erir.
		if (gameLevelObj.getBall_positionY() >= 620 ) { 

			int current_life = player.getPlayer_lives();
			
			// GameOver Case
			if (current_life == 0) {
				
				play = false;
				
				g.setColor(Color.green);
				g.setFont(new Font("serif", Font.BOLD, 30));
				g.drawString("GameOver!!, Your Score : " + player.getPlayer_score(), 200, 300);
				
				g.setFont(new Font("serif", Font.BOLD, 25));
				g.drawString("Press Ctrl+Q to Exit ", 250, 400);  
				
				if (end_game_lock) {
					EndPanel endPanel = new EndPanel(player);
					this.end_game_lock = false;
				}		
			}
			
			else {
				setPrev_remain_block_num(gameLevelObj.totalBrick);
				player.setPlayer_lives(current_life - 1);
				gameLevelObj = new GameLevel(gameLevel);
				gameLevelObj.totalBrick = getPrev_remain_block_num();
			} 
			
			
		}
		
		// Next Level & Wining Case

		if (gameLevel == 3 && gameLevelObj.totalBrick <= 0)  {
			play = false;
			gameLevelObj.setBall_X_direction(0); 
			gameLevelObj.setBall_Y_direction(0);  
			
			g.setFont(new Font("serif", Font.BOLD, 25));
			g.drawString("You Win!! Your Score is " + player.getPlayer_score() , 250, 350);  
			
			if (end_game_lock) {
				EndPanel endPanel = new EndPanel(player);
				this.end_game_lock = false;
			}
		}
		else if (gameLevelObj.totalBrick <= 0 ) { 
			play = false;
			gameLevelObj.setBall_X_direction(0); 
			gameLevelObj.setBall_Y_direction(0);  
			
			g.setFont(new Font("serif", Font.BOLD, 25));
			g.drawString("Press Enter to Start Next Level" , 250, 350);  
			
		}
	}
	
	
	
	
	public void moveNextLevel(int g_lvl) {
		
		setGameLevel(g_lvl + 1);
		gameLevelObj = new GameLevel(g_lvl + 1);
		
		map = new MapGenerator(3, 9, g_lvl + 1); 
		play = true;
	
	}
	
	
	
	@Override
	public void keyTyped(KeyEvent e) {}

	// Paddle Controllerini belirleyen methodtur.
	@Override
	public void keyPressed(KeyEvent e) {
		int keycode = e.getKeyCode();
		
		// paddle y�n verir
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			if (gameLevelObj.getPaddle_position_X() <= 0) { 
				gameLevelObj.setPaddle_position_X(0); 	
			}else {
				moveLeft();
			}
			
			
		}
		
		// paddle y�n verir
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if (gameLevelObj.getPaddle_position_X() >= 600) { 
				gameLevelObj.setPaddle_position_X(600); 	
			}else {
				moveRight();
			}
			
		}
		
		// trigger next level
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if(!play) {
				moveNextLevel(gameLevel);
				
			}
		}
		
		// Close Game when click CTRL + Q
		if ((e.getKeyCode() == KeyEvent.VK_Q) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
			System.exit(0);
        }
		
		repaint(); // her bir trigger aksiyonundan sonra objeleri rerender yap.
		
	}

	@Override
	public void keyReleased(KeyEvent e) {}

	// Oyunun gameEngine k�sm�. Her bir aksiyondan sonra objeleri d�zenler.( �rn: block a top �arpt�ysa blo�u yok et gibi )
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		
		if(play) {
			
			// Topu Panelin i�inde tutar. Duvarlardan sektirir.
			if (gameLevelObj.getBall_positionX() <= 0) {  
				int temp_ball_X_dir = gameLevelObj.getBall_X_direction();
				
				temp_ball_X_dir =- temp_ball_X_dir;  
				gameLevelObj.setBall_X_direction(temp_ball_X_dir);
			}
			// Topu Panelin i�inde tutar. Duvarlardan sektirir.
			if (gameLevelObj.getBall_positionY() <= 0) {  
				int temp_ball_Y_dir = gameLevelObj.getBall_Y_direction();
				temp_ball_Y_dir =- temp_ball_Y_dir;  
				gameLevelObj.setBall_Y_direction(temp_ball_Y_dir);
			}
			// Topu Panelin i�inde tutar. Duvarlardan sektirir.
			if (gameLevelObj.getBall_positionX() >= 661) {  
				int temp_ball_X_dir = gameLevelObj.getBall_X_direction();
				temp_ball_X_dir =- temp_ball_X_dir;  
				gameLevelObj.setBall_X_direction(temp_ball_X_dir);
			}
			
			Rectangle ballRect = new Rectangle(gameLevelObj.getBall_positionX(), gameLevelObj.getBall_positionY(), 20, 20);  
			Rectangle paddleRect = new Rectangle(gameLevelObj.getPaddle_position_X(), 550, 100, 8); 
			
			// Top paddle �arp�nca sektirir.
			if(ballRect.intersects(paddleRect)) {
				int temp_ball_Y_dir = gameLevelObj.getBall_Y_direction();
				temp_ball_Y_dir =- temp_ball_Y_dir;  
				gameLevelObj.setBall_Y_direction(temp_ball_Y_dir);
			}
			
			// Top block a �arpt�ktan sonra block'un durumunu ve topun y�n�n� belirler.
			A:for (int i=0; i<map.map.length; i++) {
				for(int j=0; j<map.map[0].length; j++) {
					if(map.map[i][j].getBlock_resistance() > 0) { // 0 = Hidden, 1 = Show
						int width = map.map[i][j].getBlock_width();
						int height = map.map[i][j].getBlock_heigth();
						int brickXpos = 80 + j * width;
						int brickYpos = 50 + i * height;
						
						Rectangle brickRect = new Rectangle(brickXpos, brickYpos, width, height);
						
						// Top Blocka �arpt���nda ki topun aksiyonu
						if(ballRect.intersects(brickRect)) {
							block_life = map.UpdateBlockResistance(1, map.map[i][j]);
							
							// Blo�u yok et.
							if (block_life == 0) {
								player.setPlayer_score(player.getPlayer_score() + 5);
								gameLevelObj.totalBrick -= 1;
							}
						
							
							if(gameLevelObj.getBall_positionX() + 19 <= brickXpos || gameLevelObj.getBall_positionX() + 1 >= brickXpos+width) { 
								int temp_ball_X_dir_v2 = gameLevelObj.getBall_X_direction();
								temp_ball_X_dir_v2 =- temp_ball_X_dir_v2; 
								gameLevelObj.setBall_X_direction(temp_ball_X_dir_v2);
							}
							else {
								int temp_ball_Y_dir_v2 = gameLevelObj.getBall_Y_direction();
								temp_ball_Y_dir_v2 =- temp_ball_Y_dir_v2; 
								gameLevelObj.setBall_Y_direction(temp_ball_Y_dir_v2);
							}
							
							break A;
						}
					}
				}
			}
			
			
			int temp_pos_x_dir = gameLevelObj.getBall_X_direction();
			int temp_pos_x_loc = gameLevelObj.getBall_positionX();
			temp_pos_x_loc += temp_pos_x_dir;	
			gameLevelObj.setBall_positionX(temp_pos_x_loc);
			
			int temp_pos_y_dir = gameLevelObj.getBall_Y_direction();
			int temp_pos_y_loc = gameLevelObj.getBall_positionY();
			temp_pos_y_loc += temp_pos_y_dir;	
			gameLevelObj.setBall_positionY(temp_pos_y_loc);
			
			
		}
		repaint();
	}
	
	// Paddle'� sola kayd�rmak i�in kordinatlar�n� d�zenler.
	private void moveLeft() {
		play = true;
		
		int temp_paddle = gameLevelObj.getPaddle_position_X();
		temp_paddle -= 20; 
		gameLevelObj.setPaddle_position_X(temp_paddle);
	}

	// Paddle'� sa�a kayd�rmak i�in kordinatlar�n� d�zenler.
	private void moveRight() {
		play = true;
		int temp_paddle = gameLevelObj.getPaddle_position_X();
		temp_paddle += 20; 
		gameLevelObj.setPaddle_position_X(temp_paddle);
	}
	
	
	// mouse drag i�lemi ile paddle'�n kontrol edilme i�lemi ger�ekle�ir.
	private class DragListener extends MouseMotionAdapter {
		public void mouseDragged(MouseEvent e) {

			if (gameLevelObj.getPaddle_position_X() >= 600) { 
				gameLevelObj.setPaddle_position_X(590); 
			}else if (gameLevelObj.getPaddle_position_X() <= 0) {
				gameLevelObj.setPaddle_position_X(5); 
			}else {
				currentPt = e.getX();
				gameLevelObj.setPaddle_position_X(currentPt);  
			}
			
			
			
			repaint();
		}
	}
	
	public int getGameLevel() {
		return gameLevel;
	}

	public void setGameLevel(int gameLevel) {
		this.gameLevel = gameLevel;
	}
	
}

