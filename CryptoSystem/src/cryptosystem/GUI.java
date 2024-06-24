package cryptosystem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;

public class GUI extends JFrame implements ActionListener
{
    JLabel l1;
    JLabel l2;
    JLabel l3;
    JLabel l4;
    JTextField t1;
    JTextField t2;
    JTextArea ta1;
    JTextArea ta2;
    JButton b1;
    JButton b2;
    JButton b3;
    JButton b4;
    JComboBox combo;

    public GUI() 
    {
        setTitle("Cryptology");
        setLayout(new BorderLayout(10,10));
        
        l1=new JLabel("Text:");
        l2=new JLabel("Key:");
        l3=new JLabel("The one cipher output:");
        l4=new JLabel("The final output:");
        l1.setFont(new Font("Calibari",Font.BOLD,20));
        l2.setFont(new Font("Calibari",Font.BOLD,20));
        l3.setFont(new Font("Calibari",Font.BOLD,20));
        l4.setFont(new Font("Calibari",Font.BOLD,20));
        
        t1=new JTextField();
        t2=new JTextField();
        t1.setFont(new Font("Calibari",Font.PLAIN,25));
        t2.setFont(new Font("Calibari",Font.PLAIN,25));
        
        ta1=new JTextArea();
        ta2=new JTextArea();
        ta1.setFont(new Font("Calibari",Font.PLAIN,25));
        ta2.setFont(new Font("Calibari",Font.PLAIN,25));
        ta1.setBorder(new TitledBorder(new LineBorder(Color.gray,5)));
        ta2.setBorder(new TitledBorder(new LineBorder(Color.gray,5)));
        
        b1=new JButton("Encrypt");
        b2=new JButton("Decrypt");
        b3=new JButton("The final encrypted text");
        b4=new JButton("The final decrypted text");
        b1.setFont(new Font("Calibari",Font.BOLD,20));
        b2.setFont(new Font("Calibari",Font.BOLD,20));
        b3.setFont(new Font("Calibari",Font.BOLD,20));
        b4.setFont(new Font("Calibari",Font.BOLD,20));
        
        combo=new JComboBox(new String[]{"Caesar","Playfair","Vigenere","Transposition"});
        combo.setFont(new Font("Calibari",Font.BOLD,20));
        combo.setBackground(Color.lightGray);
        combo.setSelectedIndex(-1);
        
        JPanel p1=new JPanel(new GridLayout(2,1,15,15));
        JPanel p2=new JPanel(new BorderLayout());
        JPanel p3=new JPanel(new GridLayout(2,1,15,15));
        JPanel p4=new JPanel(new GridLayout(2,1,15,15));
        JPanel p5=new JPanel(new FlowLayout());
        JPanel p6=new JPanel(new FlowLayout());
        JPanel p7=new JPanel(new BorderLayout());
        JPanel p8=new JPanel(new BorderLayout());
        
        p3.add(l1);
        p3.add(l2);
        
        p4.add(t1);
        p4.add(t2);
        
        p5.add(b1);
        p5.add(combo);
        p5.add(b2);
        
        p6.add(b3);
        p6.add(b4);
        
        p2.add(p3,BorderLayout.WEST);
        p2.add(p4,BorderLayout.CENTER);
        p2.add(p5,BorderLayout.SOUTH);
        
        p7.add(l3,BorderLayout.NORTH);
        p7.add(ta1);
        
        p8.add(l4,BorderLayout.NORTH);
        p8.add(ta2);
        
        p1.add(p7);
        p1.add(p8);
        
        add(p2,BorderLayout.NORTH);     
        add(p1,BorderLayout.CENTER);     
        add(p6,BorderLayout.SOUTH);     
        
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae) 
            {
                String in1=t1.getText();
                String k1=t2.getText();
                String k2,k3,k4;
                String out1,out2,out3,out4;
                if(k1.isEmpty())
                    k1=JOptionPane.showInputDialog(null,"Enter the Caesar key:","Key 1",JOptionPane.QUESTION_MESSAGE);
                Caesar caesar=new Caesar();
                int key=Integer.parseInt(k1);
                out1=caesar.encrypt(in1,key);
                JOptionPane.showMessageDialog(null,out1,"output:",JOptionPane.INFORMATION_MESSAGE);
                k2=JOptionPane.showInputDialog(null,"Enter the Playfair key:","Key 2",JOptionPane.QUESTION_MESSAGE);
                Playfair playfair=new Playfair(k2);
                out2=playfair.encrypt(out1);
                JOptionPane.showMessageDialog(null,out2,"output:",JOptionPane.INFORMATION_MESSAGE);
                k3=JOptionPane.showInputDialog(null,"Enter the Vigenere key:","Key 3",JOptionPane.QUESTION_MESSAGE);
                Vigenere vigenere=new Vigenere();
                out3=vigenere.encrypt(out2,k3);
                JOptionPane.showMessageDialog(null,out3,"output:",JOptionPane.INFORMATION_MESSAGE);
                k4=JOptionPane.showInputDialog(null,"Enter the Transposition key:","Key 4",JOptionPane.QUESTION_MESSAGE);
                Transposition transposition=new Transposition();
                out4=transposition.encrypt(out3,k4);
                ta2.setText(out4);
            }
        });
        b4.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                String in1=t1.getText();
                String k1=t2.getText();
                String k2,k3,k4;
                String out1,out2,out3,out4;
                if(k1.isEmpty())
                    k1=JOptionPane.showInputDialog(null,"Enter the Transposition key:","Key 1",JOptionPane.QUESTION_MESSAGE);
                Transposition transposition=new Transposition();
                out1=transposition.decrypt(in1,k1);
                JOptionPane.showMessageDialog(null,out1,"output:",JOptionPane.INFORMATION_MESSAGE);
                k2=JOptionPane.showInputDialog(null,"Enter the Vigenere key:","Key 2",JOptionPane.QUESTION_MESSAGE);
                Vigenere vigenere=new Vigenere();
                out2=vigenere.decrypt(out1,k2);
                JOptionPane.showMessageDialog(null,out2,"output:",JOptionPane.INFORMATION_MESSAGE);
                k3=JOptionPane.showInputDialog(null,"Enter the Playfair key:","Key 3",JOptionPane.QUESTION_MESSAGE);
                Playfair playfair=new Playfair(k3);
                out3=playfair.decrypt(out2);
                JOptionPane.showMessageDialog(null,out3,"output:",JOptionPane.INFORMATION_MESSAGE);
                k4=JOptionPane.showInputDialog(null,"Enter the Caesar key:","Key 4",JOptionPane.QUESTION_MESSAGE);
                Caesar caesar=new Caesar();
                int key=Integer.parseInt(k4);
                out4=caesar.decrypt(out3,key);
                ta2.setText(out4);
            }
        });
        
        setVisible(true);
        setSize(800,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent ae) 
    {
        String text=t1.getText();
        String key=t2.getText();
        if(combo.getSelectedIndex()==0)
        {
            Caesar caesar=new Caesar();
            int key2=Integer.parseInt(key);
            if(ae.getSource()==b1)
                ta1.setText(caesar.encrypt(text,key2));
            else if(ae.getSource()==b2)
                ta1.setText(caesar.decrypt(text,key2));
        }
        else if(combo.getSelectedIndex()==1)
        {
            Playfair playfair=new Playfair(key);
            if(ae.getSource()==b1)
                ta1.setText(playfair.encrypt(text));
            else if(ae.getSource()==b2)
                ta1.setText(playfair.decrypt(text));
        }
        else if(combo.getSelectedIndex()==2)
        {
            Vigenere vigenere=new Vigenere();
            if(ae.getSource()==b1)
                ta1.setText(vigenere.encrypt(text,key));
            else if(ae.getSource()==b2)
                ta1.setText(vigenere.decrypt(text,key));
        }
        else if(combo.getSelectedIndex()==3)
        {
            Transposition transposition=new Transposition();
            if(ae.getSource()==b1)
                ta1.setText(transposition.encrypt(text,key));
            else if(ae.getSource()==b2)
                ta1.setText(transposition.decrypt(text,key));
        }
    }
}
