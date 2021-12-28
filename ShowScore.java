import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.*;
import java.sql.*;
public class ShowScore extends JFrame{
	//static int score=0;
	JLabel l1=new JLabel();
	JLabel l2=new JLabel(new ImageIcon(getClass().getResource("twobest.png")));
	JButton l3=new JButton(new ImageIcon(getClass().getResource("doll.png")));

	JButton b=new JButton(new ImageIcon(getClass().getResource("back3.png")));
	Color c=new Color(0,255,0);
	Color yellow=new Color(255,255,0);
	Color pink=new Color(255,0,128);
String name;
int playerno;
Statement statement;
Connection connection;

	public ShowScore(){
		 Font f1=new Font("Times New Roman",Font.BOLD,200);
		// Font f2=new Font("Times New Roman",Font.BOLD,22);

        TitledBorder tb=new TitledBorder("Your Total Score");
       // tb.setTitleFont(f2);
        tb.setTitleColor(yellow);
        
		JPanel p1=new JPanel(new BorderLayout());
	    p1.add(l3,BorderLayout.WEST);
		p1.add(l1,BorderLayout.CENTER);
		//p1.add(l2,BorderLayout.EAST);
		l1.setBackground(c);
		l3.setBackground(c);
		l1.setBorder(tb);
		l1.setFont(f1);
		l1.setForeground(pink);
		//l1.setHorizontalTextPosition();

		p1.setBackground(c);
		
		JPanel p2=new JPanel(new FlowLayout());
		p2.add(b);
		p2.setBackground(c);
        b.setBackground(yellow);
        
        
        
		JPanel p=new JPanel(new BorderLayout());
		p.add(p1,BorderLayout.CENTER);
		p.add(p2,BorderLayout.SOUTH);
		p.setBackground(c);

		add(p);
		
		l3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
			 name=JOptionPane.showInputDialog(null,"Enter Your Name", "Info",JOptionPane.INFORMATION_MESSAGE);
				
		try{
				Class.forName("com.mysql.jdbc.Driver");
				System.out.println("Driver Loaded");
				
				 connection=DriverManager.getConnection("jdbc:mysql://localhost/twoplayer","root","root");
				System.out.println("Database Connected ");
				 statement=connection.createStatement();
				 ResultSet rest=statement.executeQuery("select playerscore.score from playerscore where playerscore.name='"+name+"'");
				 if(rest.next()){
					 String s=rest.getString(1);
					//int score=Integer.parseInt(s);
					l1.setText(s);
					
					/*switch(score){
					case 0:l1.setIcon(new ImageIcon(getClass().getResource("best0.png")));break;
					case 1:l1.setIcon(new ImageIcon(getClass().getResource("best1.png")));break;
					case 2:l1.setIcon(new ImageIcon(getClass().getResource("best2.png")));break;
					case 3:l1.setIcon(new ImageIcon(getClass().getResource("best3.png")));break;
					case 4:l1.setIcon(new ImageIcon(getClass().getResource("best4.png")));break;
					case 5:l1.setIcon(new ImageIcon(getClass().getResource("best5.png")));break;
					case 6:l1.setIcon(new ImageIcon(getClass().getResource("best6.png")));break;
					case 7:l1.setIcon(new ImageIcon(getClass().getResource("best7.png")));break;

					}*/
				 }
				 
				 else{
					 NotFound n=new NotFound();
					 n.setSize(443,193);
					 n.setVisible(true);
					 n.setTitle("Name Not Found Info");
					 n.setResizable(false);
					 n.setLocationRelativeTo(null);
					 setVisible(false);
				 }

			}
			catch(Exception err){
				System.out.println(err);
			}
			}
		});
		
		
		b.addActionListener(new ActionListener(){
			public  void actionPerformed(ActionEvent e){
				MainPage m=new MainPage();
				m.setTitle("Home page");
				m.setVisible(true);
				m.setSize(1000,681);
				m.setLocationRelativeTo(null);
				m.setResizable(false);
				setVisible(false);
			}
		});
		}
public static void main(String[] args){
ShowScore s=new ShowScore();
s.setSize(600,488);
//s.setResizable(false);
s.setVisible(true);
s.setLocationRelativeTo(null);
s.setTitle("Best Score For Two Player");
}

}
