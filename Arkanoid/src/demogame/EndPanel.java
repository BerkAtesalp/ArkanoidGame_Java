package demogame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EndPanel extends JFrame {
	
	PrintWriter out = null;
	private boolean fileExists; // flag
	private final String fileName = "Arkanoid_Scores_List.txt";
	File f = new File(fileName);
	
	JPanel jp = new JPanel();
	JLabel jl = new JLabel();
	JTextField jt = new JTextField(30);
	JButton jb = new JButton("Enter");
	
	public boolean isDone = false;
	
	public boolean isDone() {
		return isDone;
	}

	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}
	
	public EndPanel() {
		
	}

	// GUI elementlerinin yaratýlmasý.
	
	public EndPanel(Player player) {
		  setTitle("Tutorial");
          setVisible(true);
          setSize(400, 200);
          setDefaultCloseOperation(EXIT_ON_CLOSE);
          
          jp.add(jt);


          jt.addActionListener(new ActionListener()
          {
                 public void actionPerformed(ActionEvent e)
                 {
                       String input = jt.getText();
                       jl.setText(input); 
                 }
          });

          jp.add(jb);
          jb.addActionListener(new ActionListener()
          {
        	  	  
                  public void actionPerformed(ActionEvent e)
                  {
                         String input = jt.getText();
                         jl.setText(input);
                         player.setPlayer_name(input);
                         System.out.println(player.getPlayer_name());
                         System.out.println(player.getPlayer_score());
                         writeScores(player);
                         dispose();
                    //     System.out.println(isDone);
                  }
          });
          System.out.println(isDone);
          
          jp.add(jl);
          add(jp);
          
	}
	
	// Player''ýn Scorelarýný txt file a kaydeder.
	
	public void writeScores(Player player) {
		
		LocalDateTime myDateObj = LocalDateTime.now();  
	    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	    String formattedDate = myDateObj.format(myFormatObj);
	    
		
		try {		
			if ( f.exists() && !f.isDirectory() ) {
			    out = new PrintWriter(new FileOutputStream(new File(fileName), true));
			}
			else {
			    out = new PrintWriter(fileName);
			}
			out.println(player.getPlayer_name() + "," + player.getPlayer_score() + "," + formattedDate);
			out.close();
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	// Player''ýn Scorelarýný txt file dan okur.
	
	public ArrayList<Integer> getScores() throws IOException, IOException {
		ArrayList<Integer> scores_list = new ArrayList<Integer>();
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {			
		    String line;
		    while ((line = br.readLine()) != null) {
		    	scores_list.add(Integer.parseInt(Arrays.asList(line.split(",")).get(1)));
		    }
		}finally {
	//		ArrayList<String> scores_list = Collections.sort(scores_list);
			Collections.sort(scores_list,Collections.<Integer>reverseOrder());
			return scores_list; 
		}
		
	}
}


