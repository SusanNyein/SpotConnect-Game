import java.awt.*;
import java.sql.*;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
public class SelectionPage extends JFrame{
	
	JButton b1=new JButton(new ImageIcon(getClass().getResource("choosemusic.png")));
	JButton b2=new JButton(new ImageIcon(getClass().getResource("return.png")));
	JButton b3=new JButton(new ImageIcon(getClass().getResource("homeimg.png")));
	JLabel l1=new JLabel(new ImageIcon(getClass().getResource("nokia.png")));
	
Color c=new Color(255,0,0);
Color blue=new Color(0,0,255);
Statement statement;
Connection connection;
public SelectionPage(){
	JPanel p=new JPanel(new GridLayout(3,1,90,90));
	p.add(b1);
	p.add(b2);
	p.add(b3);
	p.setBackground(blue);
	
	b1.setBackground(c);
	b2.setBackground(c);
	b3.setBackground(c);
	l1.setBackground(c);
	
	JPanel main=new JPanel(new FlowLayout());
	main.add(p,BorderLayout.CENTER);
	main.add(l1,BorderLayout.EAST);
    main.setBackground(blue);
	add(main);
	setVisible(true);
	setSize(540,570);
	setTitle("Choose Menu");
	setLocationRelativeTo(null);
	setResizable(false);
	setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

	b1.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			try{
				 Class.forName("com.mysql.jdbc.Driver");
					System.out.println("Driver Loaded");
					
					 connection=DriverManager.getConnection("jdbc:mysql://localhost/twoplayer","root","root");
					System.out.println("Database Connected ");
					 statement=connection.createStatement();
					 statement.executeUpdate("delete from page");
					 statement.executeUpdate("insert into page values("+1+")");
					 LockPage p=new LockPage();
						//p.pno=1;
					setVisible(false);

			}
			catch(Exception err){
				
			}
			
		}
	});
	b2.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			setVisible(false);
			PlayPage pp=new PlayPage();
			pp.setTitle("SPOT CONNECT");
			pp.setSize(1320,660);
			pp.setVisible(true);
			pp.setLocationRelativeTo(null);
			Image icon=new ImageIcon("src/spot connect.png").getImage();
		    pp.setIconImage(icon);
		    pp.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		    pp.setResizable(false);

		
		}
	});
	b3.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			setVisible(false);
			try{
				Class.forName("com.mysql.jdbc.Driver");
				System.out.println("Driver Loaded");
				
				 connection=DriverManager.getConnection("jdbc:mysql://localhost/twoplayer","root","root");
				System.out.println("Database Connected ");
				 statement=connection.createStatement();
				 ResultSet rest=statement.executeQuery("select clcno from stopmusic ");
                  if(rest.next()){
                	  String no=rest.getString(1);
					  int num=Integer.parseInt(no); 
					  if(num%2!=0){
						  MainPage m=new MainPage();
                    	m.clip.stop();
                    	m.music.setIcon(new ImageIcon(getClass().getResource("nomusic.png")));

                  }
                      else{
                    	  MainPage m=new MainPage();
	                    	m.clip.stop();
                      }
                  }
                 
			}
			catch(Exception err){
				
			}
		}
	});

	

}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new SelectionPage();
		
	//	s.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}

}
