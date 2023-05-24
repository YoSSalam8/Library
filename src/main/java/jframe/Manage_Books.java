/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package jframe;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.naming.spi.DirStateFactory;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Msys
 */
public class Manage_Books extends javax.swing.JFrame {

    /**
     * Creates new form Manage_Books
     */
    
    String book_name,book_author;
    int book_id,book_quantity;
    int department_id;
    DefaultTableModel model;
    DefaultTableModel refModel;
     
    public Manage_Books() {
        initComponents();
        refModel = (DefaultTableModel)Book_det.getModel();
        setBookDetails();
    }
    
    
    public void setBookDetails(){
         String url="jdbc:oracle:thin:@localhost:1523:orcl";
        try {
         
            Connection con=DriverManager.getConnection(url,"yosef","123456");
            Statement st=con.createStatement();
            ResultSet rs= st.executeQuery("select * from book");
            model = (DefaultTableModel)Book_det.getModel();
            model.getDataVector().removeAllElements();
            while(rs.next()){
                
                String BookID=rs.getString("book_id");
                String BookName=rs.getString("book_name");
                String BookAuthor=rs.getString("book_author");
                int BookQuantity=rs.getInt("book_quantity");
                int DepartmentID =rs.getInt("department_id");
                
                Object[] obj={BookID,BookName,BookAuthor,BookQuantity,DepartmentID};
                model=(DefaultTableModel)Book_det.getModel();
                model.addRow(obj);
                
                 
            }
            
            
            
            
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public boolean DeleteBook(){
        
        boolean isDeleted=false;
        book_id=Integer.parseInt(txt_bookid.getText());
         String url="jdbc:oracle:thin:@localhost:1523:orcl";
         
         try {
            Connection con=DriverManager.getConnection(url,"yosef","123456");
            String sql=" delete from book where Book_ID=?";
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setInt(1, book_id);
            
            int RowCount=pst.executeUpdate();
            if(RowCount>0){
                
                isDeleted=true;
            }
            else{
                isDeleted=false;
            }
            
             
             
        } catch (Exception e) {
            e.printStackTrace();
        }
         return isDeleted;
        
        
        
        
        
    }
    
    
    public boolean Updatebook(){
        
        boolean isUpdated=false;
        book_id=Integer.parseInt(txt_bookid.getText());
        book_name=txt_bookname.getText();
        book_author=txt_bookauthor.getText();
        book_quantity=Integer.parseInt(txt_quantity.getText());
       
        
               
        String url="jdbc:oracle:thin:@localhost:1523:orcl";
        
        try {
            
            Connection con=DriverManager.getConnection(url,"yosef","123456");
            String sql=" update book set Book_name=?,Book_author=?,Book_quantity=?,Department_ID=? where Book_ID=?";
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setString(1, book_name);
            pst.setString(2, book_author);
            pst.setInt(3, book_quantity);
            pst.setInt(4, Integer.parseInt(txt_depid.getText()));
            pst.setInt(5, book_id);
            
            int RowCount=pst.executeUpdate();
            if(RowCount>0){
                
                isUpdated=true;
            }
            else{
                isUpdated=false;
                
                
            }
            
            
            
            
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isUpdated;
        
        
    } 
    
    public boolean searchBook(){
        String url="jdbc:oracle:thin:@localhost:1523:orcl";
        
        try {
            Connection con=DriverManager.getConnection(url,"yosef","123456");
            String sql="SELECT * FROM BOOK WHERE BOOK_ID="+txt_bookid.getText();
            con.setAutoCommit(false);
            Statement pst=con.createStatement();
            ResultSet rs= pst.executeQuery(sql);
            if(rs.next()){
                String BookID=rs.getString("book_id");
                String BookName=rs.getString("book_name");
                String BookAuthor=rs.getString("book_author");
                int BookQuantity=rs.getInt("book_quantity");
                
                txt_bookid.setText(BookID);
                txt_bookname.setText(BookName);
                txt_bookauthor.setText(BookAuthor);
                txt_quantity.setText(Integer.toString(BookQuantity));
                txt_depid.setText(Integer.toString(department_id));
                con.close();
                return true;
            }
            else{
                con.close();
                return false;
            }
        
        
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    
    public boolean addBook(){
        boolean isAdded=false;
        book_id=Integer.parseInt(txt_bookid.getText());
        book_name=txt_bookname.getText();
        book_author=txt_bookauthor.getText();
        book_quantity=Integer.parseInt(txt_quantity.getText());
        
               
        String url="jdbc:oracle:thin:@localhost:1523:orcl";
        
        try {
            Connection con=DriverManager.getConnection(url,"yosef","123456");
            String sql="insert into book (BOOK_ID,BOOK_NAME,BOOK_AUTHOR,BOOK_QUANTITY,DEPARTMENT_ID) values (?,?,?,?,?)";
             con.setAutoCommit(false);
            PreparedStatement pst=con.prepareStatement(sql);
           
            pst.setInt(1, book_id);
            pst.setString(2, book_name);
            pst.setString(3, book_author);
            pst.setInt(4, book_quantity);
            pst.setInt(5, Integer.parseInt(txt_depid.getText()));
            
            int rowCount=pst.executeUpdate();
            if(rowCount>0){
                
                isAdded=true;
            }
            else{
                isAdded=false;
            }
            
             con.commit();
        con.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isAdded;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        txt_bookid = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txt_bookname = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txt_bookauthor = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txt_quantity = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txt_depid = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Book_det = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(153, 0, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Book ID");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 80, 290, 50));

        txt_bookid.setBackground(new java.awt.Color(153, 0, 51));
        txt_bookid.setForeground(new java.awt.Color(255, 255, 255));
        txt_bookid.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        txt_bookid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_bookidActionPerformed(evt);
            }
        });
        jPanel1.add(txt_bookid, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 130, 230, 30));

        jLabel13.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Book Name");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 170, 290, 50));

        txt_bookname.setBackground(new java.awt.Color(153, 0, 51));
        txt_bookname.setForeground(new java.awt.Color(255, 255, 255));
        txt_bookname.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        txt_bookname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_booknameActionPerformed(evt);
            }
        });
        jPanel1.add(txt_bookname, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 220, 230, 30));

        jLabel15.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Author's Name");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 260, 290, 50));

        txt_bookauthor.setBackground(new java.awt.Color(153, 0, 51));
        txt_bookauthor.setForeground(new java.awt.Color(255, 255, 255));
        txt_bookauthor.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        txt_bookauthor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_bookauthorActionPerformed(evt);
            }
        });
        jPanel1.add(txt_bookauthor, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 310, 230, 30));

        jLabel17.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Quantity");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 350, 290, 50));

        txt_quantity.setBackground(new java.awt.Color(153, 0, 51));
        txt_quantity.setForeground(new java.awt.Color(255, 255, 255));
        txt_quantity.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        txt_quantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_quantityActionPerformed(evt);
            }
        });
        jPanel1.add(txt_quantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 400, 230, 30));

        jLabel19.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Department ID");
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 440, 290, 50));

        txt_depid.setBackground(new java.awt.Color(153, 0, 51));
        txt_depid.setForeground(new java.awt.Color(255, 255, 255));
        txt_depid.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        txt_depid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_depidActionPerformed(evt);
            }
        });
        jPanel1.add(txt_depid, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 490, 230, 30));

        jButton1.setBackground(new java.awt.Color(153, 0, 51));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Update");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 700, 130, 50));

        jButton2.setBackground(new java.awt.Color(153, 0, 51));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Add");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 610, 130, 50));

        jButton3.setBackground(new java.awt.Color(153, 0, 51));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Search");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 700, 130, 50));

        jButton4.setBackground(new java.awt.Color(153, 0, 51));
        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Delete");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 610, 130, 50));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("X");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 0, 60, 50));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 0, 580, 830));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel21.setBackground(new java.awt.Color(153, 0, 51));
        jPanel21.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel3.add(jPanel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 110, 270, 5));

        jLabel2.setBackground(new java.awt.Color(153, 0, 51));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(153, 0, 51));
        jLabel2.setIcon(new javax.swing.ImageIcon("C:\\Users\\Msys\\OneDrive\\Documents\\NetBeansProjects\\Library_DBMS\\src\\main\\java\\books.png")); // NOI18N
        jLabel2.setText("Manage Books");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 60, 320, 60));

        jPanel22.setBackground(new java.awt.Color(153, 0, 51));
        jPanel22.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel3.add(jPanel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 260, 870, 10));

        jPanel2.setBackground(new java.awt.Color(153, 0, 51));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\Msys\\OneDrive\\Documents\\NetBeansProjects\\Library_DBMS\\src\\main\\java\\Back.png")); // NOI18N
        jLabel1.setText("Back");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 130, 40));

        jPanel3.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 160, 60));

        Book_det.setAutoCreateRowSorter(true);
        Book_det.setBackground(new java.awt.Color(204, 204, 204));
        Book_det.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        Book_det.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Book_ID", "Book_name", "Author", "Quantity", "Department_ID"
            }
        ));
        Book_det.setRowHeight(30);
        Book_det.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Book_detMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(Book_det);

        jPanel3.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 270, 870, -1));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1140, 830));

        setSize(new java.awt.Dimension(1740, 832));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
          if(DeleteBook()==true){
           JOptionPane.showMessageDialog(this, "Book Deleted");
           setBookDetails();
       }
       else{
                      JOptionPane.showMessageDialog(this, "This Book Deletion Failed");
//setBookDetails();
       }
    
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        if(!searchBook()){
            JOptionPane.showMessageDialog(rootPane, "Entry not found.");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       if(addBook()==true){
           JOptionPane.showMessageDialog(this, "Book Added");
           setBookDetails();
       }
       else{
                      JOptionPane.showMessageDialog(this, "This Book Addition Failed");
//setBookDetails();
       }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
 if(Updatebook()==true){
           JOptionPane.showMessageDialog(this, "Book Updated");
           setBookDetails();
       }
       else{
                      JOptionPane.showMessageDialog(this, "This Book Update Failed");
//setBookDetails();
       }        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txt_depidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_depidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_depidActionPerformed

    private void txt_quantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_quantityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_quantityActionPerformed

    private void txt_bookauthorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_bookauthorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_bookauthorActionPerformed

    private void txt_booknameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_booknameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_booknameActionPerformed

    private void txt_bookidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_bookidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_bookidActionPerformed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
       Home_Page A=new Home_Page();
        A.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
System.exit(0);        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel3MouseClicked

    private void Book_detMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Book_detMouseClicked
        int RowNumber=Book_det.getSelectedRow();
        TableModel model=Book_det.getModel();
        
        txt_bookid.setText(model.getValueAt(RowNumber, 0).toString());
        txt_bookname.setText(model.getValueAt(RowNumber, 1).toString());
         txt_bookauthor.setText(model.getValueAt(RowNumber, 2).toString());
          txt_quantity.setText(model.getValueAt(RowNumber, 3).toString());
    }//GEN-LAST:event_Book_detMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Manage_Books.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Manage_Books.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Manage_Books.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Manage_Books.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Manage_Books().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Book_det;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField txt_bookauthor;
    private javax.swing.JTextField txt_bookid;
    private javax.swing.JTextField txt_bookname;
    private javax.swing.JTextField txt_depid;
    private javax.swing.JTextField txt_quantity;
    // End of variables declaration//GEN-END:variables
}
