package demogame;

// Oyun levellarýnýn ve kurallarýnýn belirlendiði Classtýr.
public class GameLevel {
	
	public int totalBrick; // Oyun içerisinde ki block sayýsý
	

	private int ball_positionX; // Topun pozisyonunun X kordinatý
	private int ball_positionY; // Topun pozisyonunun Y kordinatý
	private int ball_X_direction; // topun X direction'ýn daki hýzý
	private int ball_Y_direction; // topun Y direction'ýn daki hýzý
	private int paddle_position_X; // paddle position
	
	
	// Level Selection aþamasý
	public GameLevel(int map_lvl) {
		if (map_lvl == 1) {
			this.ball_positionX = 120;
			this.ball_positionY = 350;
			this.ball_X_direction = 1; 
			this.ball_Y_direction = 2; 
			this.paddle_position_X = 350;
			this.totalBrick = 27;
		} else if (map_lvl == 2) {
			this.ball_positionX = 120;
			this.ball_positionY = 350;
			this.ball_X_direction = 2; 
			this.ball_Y_direction = 3; 
			this.paddle_position_X = 350;
			this.totalBrick = 27;
		}else if (map_lvl == 3) {
			this.ball_positionX = 120;
			this.ball_positionY = 350;
			this.ball_X_direction = 2; 
			this.ball_Y_direction = 5; 
			this.paddle_position_X = 350;
			this.totalBrick = 27;
		}
	}
	
	public int getBall_positionX() {
		return ball_positionX;
	}

	public void setBall_positionX(int ball_positionX) {
		this.ball_positionX = ball_positionX;
	}

	public int getBall_positionY() {
		return ball_positionY;
	}

	public void setBall_positionY(int ball_positionY) {
		this.ball_positionY = ball_positionY;
	}

	public int getBall_X_direction() {
		return ball_X_direction;
	}

	public void setBall_X_direction(int ball_X_direction) {
		this.ball_X_direction = ball_X_direction;
	}

	public int getBall_Y_direction() {
		return ball_Y_direction;
	}

	public void setBall_Y_direction(int ball_Y_direction) {
		this.ball_Y_direction = ball_Y_direction;
	}

	public int getPaddle_position_X() {
		return paddle_position_X;
	}

	public void setPaddle_position_X(int paddle_position_X) {
		this.paddle_position_X = paddle_position_X;
	}
	
}
