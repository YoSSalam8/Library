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
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Msys
 */

public class Manage_Students2 extends javax.swing.JFrame {
    
String name,Education,Contact;
int ID;
DefaultTableModel model;
DefaultTableModel refModel;
    /**
     * Creates new form Manage_Books
     */
    public Manage_Students2() {
        initComponents();
        refModel = (DefaultTableModel)stu_det.getModel();
        setStudentDetails();
        
    }
    
    public void setStudentDetails(){
         String url="jdbc:oracle:thin:@localhost:1523:orcl";
        try {
         
            Connection con=DriverManager.getConnection(url,"yosef","123456");
            Statement st=con.createStatement();
            ResultSet rs= st.executeQuery("select * from student");
            model = (DefaultTableModel)stu_det.getModel();
            model.getDataVector().removeAllElements();
            while(rs.next()){
                
                String StuID=rs.getString("ID");
                String StuName=rs.getString("name");
                String StuEducation=rs.getString("Education");
                String StuContact=rs.getString("Contact");
                
                
                Object[] obj={StuID,StuName,StuEducation,StuContact};
                model=(DefaultTableModel)stu_det.getModel();
                model.addRow(obj);
                
                 
            }
            
            
            
            
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public boolean addStudent(){
        boolean isAdded=false;
        ID=Integer.parseInt(txt_stuid.getText());
        name=txt_stuname1.getText();
        Education=txt_stueducation.getText();
        Contact=txt_stucontact.getText();
        
               
        String url="jdbc:oracle:thin:@localhost:1523:orcl";
        
        try {
            Connection con=DriverManager.getConnection(url,"yosef","123456");
            String sql="insert into Student (ID,NAME,Education,Contact) values (?,?,?,?)";
             con.setAutoCommit(false);
            PreparedStatement pst=con.prepareStatement(sql);
           
            pst.setInt(1, ID);
            pst.setString(2, name);
            pst.setString(3, Education);
            pst.setString(4, Contact);
            
            
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
    
    
    public boolean DeleteStudent(){
        
        boolean isDeleted=false;
        ID=Integer.parseInt(txt_stuid.getText());
         String url="jdbc:oracle:thin:@localhost:1523:orcl";
         
         try {
            Connection con=DriverManager.getConnection(url,"yosef","123456");
            String sql=" delete from student where ID=?";
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setInt(1, ID);
            
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
    
    
    public boolean UpdateStudent(){
        
        boolean isUpdated=false;
        ID=Integer.parseInt(txt_stuid.getText());
        name=txt_stuname1.getText();
        Education=txt_stueducation.getText();
        Contact=txt_stucontact.getText();
       
        
               
        String url="jdbc:oracle:thin:@localhost:1523:orcl";
        
        try {
            
            Connection con=DriverManager.getConnection(url,"yosef","123456");
            String sql=" update student set name=?,education=?,contact=? where ID=?";
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setString(1, name);
            pst.setString(2, Education);
            pst.setString(3, Contact);
            pst.setInt(4, ID);
            
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
    
    
    public boolean searchStudent(){
        String url="jdbc:oracle:thin:@localhost:1523:orcl";
        
        try {
            Connection con=DriverManager.getConnection(url,"yosef","123456");
            String sql="SELECT * FROM student WHERE ID="+txt_stuid.getText();
            con.setAutoCommit(false);
            Statement pst=con.createStatement();
            ResultSet rs= pst.executeQuery(sql);
            if(rs.next()){
                String StuID=rs.getString("ID");
                String StuName=rs.getString("name");
                String StuEducation=rs.getString("Education");
                String StuConatact=rs.getString("Contact");
                
                txt_stuid.setText(StuID);
                txt_stuname1.setText(StuName);
                txt_stueducation.setText(StuEducation);
                txt_stucontact.setText(StuConatact);
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
        txt_stuid = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txt_stueducation = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txt_stucontact = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txt_stuname1 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        stu_det = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(153, 0, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Student ID:");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 80, 290, 50));

        txt_stuid.setBackground(new java.awt.Color(153, 0, 51));
        txt_stuid.setForeground(new java.awt.Color(255, 255, 255));
        txt_stuid.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        txt_stuid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_stuidActionPerformed(evt);
            }
        });
        jPanel1.add(txt_stuid, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 130, 230, 30));

        jLabel13.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Student Name:");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 170, 290, 50));

        txt_stueducation.setBackground(new java.awt.Color(153, 0, 51));
        txt_stueducation.setForeground(new java.awt.Color(255, 255, 255));
        txt_stueducation.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        txt_stueducation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_stueducationActionPerformed(evt);
            }
        });
        jPanel1.add(txt_stueducation, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 310, 230, 30));

        jLabel15.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Education:");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 260, 290, 50));

        jLabel17.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Phone:");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 360, 290, 50));

        txt_stucontact.setBackground(new java.awt.Color(153, 0, 51));
        txt_stucontact.setForeground(new java.awt.Color(255, 255, 255));
        txt_stucontact.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        txt_stucontact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_stucontactActionPerformed(evt);
            }
        });
        jPanel1.add(txt_stucontact, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 400, 230, 30));

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
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, -10, 60, 70));

        txt_stuname1.setBackground(new java.awt.Color(153, 0, 51));
        txt_stuname1.setForeground(new java.awt.Color(255, 255, 255));
        txt_stuname1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        txt_stuname1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_stuname1ActionPerformed(evt);
            }
        });
        jPanel1.add(txt_stuname1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 220, 230, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 0, 580, 830));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel21.setBackground(new java.awt.Color(153, 0, 51));
        jPanel21.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel3.add(jPanel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 110, 270, 5));

        jLabel2.setBackground(new java.awt.Color(153, 0, 51));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(153, 0, 51));
        jLabel2.setIcon(new javax.swing.ImageIcon("C:\\Users\\Msys\\OneDrive\\Documents\\NetBeansProjects\\Library_DBMS\\src\\main\\java\\Student.png")); // NOI18N
        jLabel2.setText("Manage Students");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 60, 320, 60));

        jPanel22.setBackground(new java.awt.Color(153, 0, 51));
        jPanel22.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel3.add(jPanel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 270, 870, 10));

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

        stu_det.setAutoCreateRowSorter(true);
        stu_det.setBackground(new java.awt.Color(204, 204, 204));
        stu_det.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        stu_det.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Stu_ID", "Stu_Name", "Education", "Phone"
            }
        ));
        stu_det.setRowHeight(30);
        stu_det.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                stu_detMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(stu_det);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 280, 870, -1));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1140, 830));

        setSize(new java.awt.Dimension(1740, 832));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
   if(DeleteStudent()==true){
           JOptionPane.showMessageDialog(this, "Student Deleted");
           setStudentDetails();
       }
       else{
                      JOptionPane.showMessageDialog(this, "This Student Deletion Failed");
//setBookDetails();
       }        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
if(!searchStudent()){
            JOptionPane.showMessageDialog(rootPane, "Entry not found.");
        }
             // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
 if(addStudent()==true){
           JOptionPane.showMessageDialog(this, "Student Added");
           setStudentDetails();
       }
       else{
                      JOptionPane.showMessageDialog(this, "This Student Addition Failed");
//setBookDetails();
       }        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
if(UpdateStudent()==true){
           JOptionPane.showMessageDialog(this, "Student Updated");
           setStudentDetails();
       }
       else{
                      JOptionPane.showMessageDialog(this, "This Student Update Failed");
//setBookDetails();
       }         // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txt_stucontactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_stucontactActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_stucontactActionPerformed

    private void txt_stueducationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_stueducationActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_stueducationActionPerformed

    private void txt_stuidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_stuidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_stuidActionPerformed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
 Home_Page_Emp A=new Home_Page_Emp();
        A.setVisible(true);
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
System.exit(0);        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel3MouseClicked

    private void txt_stuname1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_stuname1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_stuname1ActionPerformed

    private void stu_detMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_stu_detMouseClicked
int RowNumber=stu_det.getSelectedRow();
        TableModel model=stu_det.getModel();
        
        txt_stuid.setText(model.getValueAt(RowNumber, 0).toString());
        txt_stuname1.setText(model.getValueAt(RowNumber, 1).toString());
         txt_stueducation.setText(model.getValueAt(RowNumber, 2).toString());
          txt_stucontact.setText(model.getValueAt(RowNumber, 3).toString());        // TODO add your handling code here:
    }//GEN-LAST:event_stu_detMouseClicked

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
            java.util.logging.Logger.getLogger(Manage_Students2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Manage_Students2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Manage_Students2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Manage_Students2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Manage_Students2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable stu_det;
    private javax.swing.JTextField txt_stucontact;
    private javax.swing.JTextField txt_stueducation;
    private javax.swing.JTextField txt_stuid;
    private javax.swing.JTextField txt_stuname1;
    // End of variables declaration//GEN-END:variables
}
