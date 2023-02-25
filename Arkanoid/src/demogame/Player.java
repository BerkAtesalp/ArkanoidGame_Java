package demogame;

// Player Classý Oynayan oyuncunun haklarýný belirtir
public class Player {
	private String player_name; 
	private String player_surname;
	private int player_score = 0; // oyuncunun scoru, topladýðý puanlar
	private int player_lives = 3; // Oyuncunun caný
	
	public String getPlayer_name() {
		return player_name;
	}
	public void setPlayer_name(String player_name) {
		this.player_name = player_name;
	}
	public String getPlayer_surname() {
		return player_surname;
	}
	public void setPlayer_surname(String player_surname) {
		this.player_surname = player_surname;
	}
	public int getPlayer_score() {
		return player_score;
	}
	public void setPlayer_score(int player_score) {
		this.player_score = player_score;
	}
	public int getPlayer_lives() {
		return player_lives;
	}
	public void setPlayer_lives(int player_lives) {
		this.player_lives = player_lives;
	}
}
