package AppDesi;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class OpeningPanel extends JPanel{
	JButton startButton = new JButton("START");
	boolean i = false;
	//コンストラクタ
	public OpeningPanel(String name) {
		//mainFrame = frame;
		 this.setName(name);
	}
	public boolean opening(JFrame frame) {

		 this.setLayout(null);
		 
		//文字表示
		 JLabel label1 = new JLabel("Let's make Mr.Sugimura!!");
		 label1.setHorizontalAlignment(JLabel.CENTER);
		 label1.setVerticalAlignment(JLabel.CENTER);
		 label1.setFont(new Font("Century", Font.ITALIC, 70));
		 label1.setForeground(Color.blue);
		 label1.setBounds(0, 100, 1500, 300);
	     this.add(label1);




		//文章表示
		JLabel label2 = new JLabel("Please double-click start button!!");
		label2.setHorizontalAlignment(JLabel.CENTER);
		label2.setVerticalAlignment(JLabel.CENTER);
		label2.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 20));
		label2.setForeground(Color.red);
		label2.setBounds(0, 600, 1500, 70);
	    this.add(label2);

	    //先生の画像をを表示
	    ImageIcon icon1 = new ImageIcon("/Users/yoshiokarina/Documents/AppDes/teacher_open.png");
	    JLabel label3 = new JLabel(icon1);
	    label3.setBounds(780, 200, 680, 680);
	    this.add(label3);
	    
	    //カナダの国旗を表示
	    ImageIcon icon2 = new ImageIcon("/Users/yoshiokarina/Documents/AppDes/canada.png");
	    JLabel label4 = new JLabel(icon2);
	    label4.setBounds(10, 0, 320, 320);
	    this.add(label4);
		// STARTボタン
		startButton.setHorizontalAlignment(JLabel.CENTER);
		startButton.setVerticalAlignment(JLabel.CENTER);
		startButton.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 40));
		startButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
			  changePanel("exit",frame);
			  i=true;
			}
		    });
		startButton.setBounds(500, 500, 500, 100);
		this.add(startButton);

	    
		return i;
	}

	GridBagConstraints getConstraints(int x, int y, int w, int h, double weightx, double weighty, int xmargin,int ymargin) {
          GridBagConstraints gbc = new GridBagConstraints();
          gbc.gridx = x;
          gbc.gridy = y;
          gbc.gridwidth = w;
          gbc.gridheight = h;
          gbc.weightx = weightx;
          gbc.weighty = weighty;
          gbc.fill = GridBagConstraints.BOTH;
          gbc.anchor = GridBagConstraints.CENTER;
          if(xmargin!=0 && ymargin!=0) {
        	  gbc.insets = new Insets(ymargin, xmargin, ymargin, xmargin);
          }
          return gbc;
	}


	 //スタートボタンを閉じる
	public void changePanel(String name,JFrame frame){
		if(this.showPanel(name)) {
			startButton.addActionListener(event -> frame.dispose());
		}
	}

	
	//
    public boolean showPanel(String name) {
    	boolean i = false;
    	if (name == "exit")  i = true;
    	return i;
        }

    
    
}
