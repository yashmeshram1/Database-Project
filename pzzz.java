import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
public class pzzz
{
    public static void main(String[] args)
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url="jdbc:mysql://localhost:3306/connect";
            String user="root";
            String pass="mcstan833";
            Connection con=DriverManager.getConnection(url,user,pass);

            JFrame frame=new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500,300);

            JPanel panel=new JPanel();
            panel.setLayout(new GridLayout(10,1,10,10));
            panel.setBackground(Color.WHITE);

            JLabel idlabel=new JLabel("ID");
            panel.add(idlabel);
            JTextField idfielde=new JTextField();

            panel.add(idfielde);


            JLabel namelabel=new JLabel("NAME");
            panel.add(namelabel);
            JTextField namefield=new JTextField();
            panel.add(namefield);

            JLabel symbollabel=new JLabel("SYMBOL");
            panel.add(symbollabel);
            JTextField symbolfield=new JTextField();
            panel.add(symbolfield);

            JLabel pricellabel=new JLabel("PRICE(IN $)");
            panel.add(pricellabel);
            JTextField pricefield=new JTextField();
            panel.add(pricefield);

            JLabel exchllabel=new JLabel("EXCHANGE WHICH ON LISTED");
            panel.add(exchllabel);
            JTextField exchfield=new JTextField();
            panel.add(exchfield);

            JButton crate=new JButton("CREATE");
            crate.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    try {
                        String c="create table practice(id int primary key,name varchar(255),symbol varchar(255),price int,exchange varchar(255))";

                        Statement stmt=con.createStatement();
                        stmt.executeUpdate(c);

                    }
                    catch (Exception ex)
                    {
                        ex.printStackTrace();
                    }


                }
            });
            panel.add(crate);
            JButton insert=new JButton("INSERT");
            insert.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    try
                    {
                        int a=Integer.parseInt(idfielde.getText());
                        int b=Integer.parseInt(pricefield.getText());
                        String n="insert into practice values(?,?,?,?,?)";
                        PreparedStatement pstmt=con.prepareStatement(n);
                        pstmt.setInt(1,a);
                        pstmt.setString(2,namefield.getText());
                        pstmt.setString(3,symbolfield.getText());
                        pstmt.setInt(4,b);
                        pstmt.setString(5,exchfield.getText());
                        pstmt.executeUpdate();
                    }
                    catch (Exception er)
                    {
                        er.printStackTrace();
                    }
                }
            });
            panel.add(insert);

            JButton diplay=new JButton("DISPLAY");
            diplay.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    try
                    {
                        String w="select * from practice";
                        Statement na=con.createStatement();
                        ResultSet resultSet=na.executeQuery(w);
                        DefaultTableModel model=new DefaultTableModel();
                        model.addColumn("ID");
                        model.addColumn("NAME");
                        model.addColumn("SYMBOL");
                        model.addColumn("PRICE");
                        model.addColumn("EXCHANGE WHICH ON LISTED");
                        while (resultSet.next())
                        {
                            Object[] print=new Object[5];
                            print[0]=resultSet.getInt("id");
                            print[1]=resultSet.getString("name");
                            print[2]=resultSet.getString("symbol");
                            print[3]=resultSet.getInt("price");
                            print[4]=resultSet.getString("exchange");
                            model.addRow(print);
                        }
                        JTable table=new JTable();
                        table.setModel(model);
                        JOptionPane.showMessageDialog(frame,new JScrollPane(table));

                    }
                    catch (Exception naa)
                    {
                        naa.printStackTrace();
                    }

                }
            });
            panel.add(diplay);
            JButton nameupdate=new JButton("UPDATE NAME ");
            nameupdate.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {

                    try
                    {
                        int ce=Integer.parseInt(idfielde.getText());
                        String huu="update practice set name=? where id=?";
                        PreparedStatement pss1=con.prepareStatement(huu);
                        pss1.setString(1,namefield.getText());
                        pss1.setInt(2,ce);
                        pss1.executeUpdate();

                    }
                    catch (Exception rw)
                    {
                        rw.printStackTrace();
                    }

                }
            });
            panel.add(nameupdate);
            JButton syupdate=new JButton("UPDATE SYMBOL ");
            syupdate.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {

                    try
                    {
                        int de=Integer.parseInt(idfielde.getText());
                        String kl="update practice set symbol=? where id=?";
                        PreparedStatement pss2=con.prepareStatement(kl);
                        pss2.setString(1,symbolfield.getText());
                        pss2.setInt(2,de);
                        pss2.executeUpdate();

                    }
                    catch (Exception rn)
                    {
                        rn.printStackTrace();
                    }

                }
            });
            panel.add(syupdate);
            JButton prupdate=new JButton("UPDATE PRICE ");
            prupdate.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {

                    try
                    {
                        int fe=Integer.parseInt(idfielde.getText());
                        String jhd="update practice set price=? where id=?";
                        PreparedStatement pss=con.prepareStatement(jhd);
                        pss.setString(1,pricefield.getText());
                        pss.setInt(2,fe);
                        pss.executeUpdate();

                    }
                    catch (Exception rt)
                    {
                        rt.printStackTrace();
                    }

                }
            });
            panel.add(prupdate);

            JButton exchupdate=new JButton("UPDATE EXCHANGE ");
            exchupdate.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {

                    try
                    {
                        int mui=Integer.parseInt(idfielde.getText());
                        String yor="update practice set exchange=? where id=?";
                        PreparedStatement pss=con.prepareStatement(yor);
                        pss.setString(1,exchfield.getText());
                        pss.setInt(2,mui);
                        pss.executeUpdate();

                    }
                    catch (Exception rt)
                    {
                        rt.printStackTrace();
                    }

                }
            });
            panel.add(exchupdate);

            JButton row=new JButton("DELETE RECORD ");
            row.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    try
                    {
                        int rkm=Integer.parseInt(idfielde.getText());
                        String de="delete from practice where id=?";
                        PreparedStatement oua= con.prepareStatement(de);
                        oua.setInt(1,rkm);
                        oua.executeUpdate();
                    }
                    catch (Exception nji)
                    {
                        nji.printStackTrace();
                    }
                }
            });
            panel.add(row);

            JButton tab=new JButton("DELETE TABLE ");
            tab.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    try
                    {

                        String de="drop table practice";
                        Statement jah= con.createStatement();
                        jah.executeUpdate(de);


                    }
                    catch (Exception ncji)
                    {
                        ncji.printStackTrace();
                    }
                }
            });
            panel.add(tab);


            frame.add(panel,BorderLayout.NORTH);
            frame.setVisible(true);




        }

        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}