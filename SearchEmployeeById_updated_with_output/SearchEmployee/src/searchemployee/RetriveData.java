
package searchemployee;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

public  class RetriveData 
{

    JFrame f;
    JLabel text;
    JTextField searchFild;
    JButton search;
    public RetriveData() throws  UnsupportedLookAndFeelException
    {
        f = new JFrame("Search an Employee");
        UIManager.setLookAndFeel(new NimbusLookAndFeel());
        f.setLayout(null);
        f.setBounds(400, 100, 500, 600);
        Font ft = new Font("Times New Roman", Font.PLAIN ,25);
        text = new JLabel("Enter ID : ");
        text.setFont(ft);
        text.setBounds(20,10,120, 25);
        f.add(text);
        
        searchFild = new JTextField();
        searchFild.setBounds(140, 10, 100, 30);
        f.add(searchFild);
        
        search = new JButton("Search");
        search.setBounds(280, 10, 100, 30);
        f.add(search);
        
        JSeparator s = new JSeparator();
        s.setBounds(0, 50, 500, 15);
        f.add(s);
        
        getData();
        
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                try{
                getselectedData();
                }catch(SQLException sqlex){sqlex.printStackTrace();};
            }
        });
        
        
        //f.setResizable(false);
        f.setDefaultCloseOperation(EXIT_ON_CLOSE);
        f.setVisible(true);
    }
    
    public void getData() 
    {
        
        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBounds(0, 60, 500, 550);
        f.add(p);
        String[] clm = {"id","fname","lname","dept","pincode"};
        JLabel str;
        JTable table;
        try
        {

            String url = "jdbc:mysql://localhost/javadb";
            String user = "root";
            String pass = "Akshay";
            Connection c = DriverManager.getConnection(url,user,pass);
            Statement s = c.createStatement();
            ResultSet set = s.executeQuery("select * from employee");
            int i = 0;
            while(set.next())
            {
                p.repaint();
                f.repaint();
                String name = set.getString("id")+"     "+set.getString("fname")+"      "+set.getString("lname")+"      "+set.getString("dept")+"       "+set.getString("pincode");
                str = new JLabel(name);
                str.setBounds(30, 60+i, 300, 50);
                p.add(str);
                i+=50;
                
            }
        }catch(SQLException e){e.printStackTrace();} 
    }
    
    
    public void getselectedData() throws SQLException{
        
        String url = "jdbc:mysql://localhost/javadb";
        String user = "root";
        String pass = "Akshay";
        JLabel strg;
        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBounds(0, 60, 500, 550);
        f.add(p);
       
        Connection c = DriverManager.getConnection(url,user,pass);
        Statement s = c.createStatement();
        
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                p.repaint();
                String txt = searchFild.getText();
                
                String slct = "select * from employee where id = "+txt;
                try{
                    p.repaint();
                    ResultSet set = s.executeQuery(slct);
                    p.repaint();
                    while(set.next())
                    {
                        p.repaint();
                        String name = set.getString("id")+"     "+set.getString("fname")+"      "+set.getString("lname")+"      "+set.getString("dept")+"       "+set.getString("pincode");
                        JLabel str = new JLabel(name);
                        str.setBounds(30, 100, 300, 50);
                        p.add(str);
                    }
                }catch(SQLException e1){e1.getMessage();}   
            }
        });
       
    
        
    }

}
