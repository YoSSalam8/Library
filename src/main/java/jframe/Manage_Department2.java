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
public class Manage_Department2 extends javax.swing.JFrame {

    String dep_name;
    int dep_id;
   
    DefaultTableModel model;
    DefaultTableModel refModel;
     
    public Manage_Department2() {
        initComponents();
         refModel = (DefaultTableModel)dep_det.getModel();
        setdepartmentDetails();
    }
    
    public void setdepartmentDetails(){
         String url="jdbc:oracle:thin:@localhost:1523:orcl";
        try {
         
            Connection con=DriverManager.getConnection(url,"yosef","123456");
            Statement st=con.createStatement();
            ResultSet rs= st.executeQuery("select * from department");
            model = (DefaultTableModel)dep_det.getModel();
            model.getDataVector().removeAllElements();
            while(rs.next()){
                
                String DepID=rs.getString("dep_ID");
                String DepName=rs.getString("dep_name");
               
                
                Object[] obj={DepID,DepName};
                model=(DefaultTableModel)dep_det.getModel();
                model.addRow(obj);
                
                 
            }
            
            
            
            
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public boolean DeleteDepartment(){
        
        boolean isDeleted=false;
        dep_id=Integer.parseInt(txt_depid.getText());
         String url="jdbc:oracle:thin:@localhost:1523:orcl";
         
         try {
            Connection con=DriverManager.getConnection(url,"yosef","123456");
            String sql=" delete from department where dep_ID=?";
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setInt(1, dep_id);
            
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
    
    
    public boolean UpdateDepartment(){
        
        boolean isUpdated=false;
        dep_id=Integer.parseInt(txt_depid.getText());
        dep_name=txt_depname.getText();
       
       
        
               
        String url="jdbc:oracle:thin:@localhost:1523:orcl";
        
        try {
            
            Connection con=DriverManager.getConnection(url,"yosef","123456");
            String sql=" update department set dep_name=? where dep_id=?";
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setString(1, dep_name);
          
            pst.setInt(2, dep_id);
            
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
    
public boolean searchDepartment(){
        String url="jdbc:oracle:thin:@localhost:1523:orcl";
        
        try {
            Connection con=DriverManager.getConnection(url,"yosef","123456");
            String sql="SELECT * FROM department WHERE dep_ID="+txt_depid.getText();
            con.setAutoCommit(false);
            Statement pst=con.createStatement();
            ResultSet rs= pst.executeQuery(sql);
            if(rs.next()){
                String DepID=rs.getString("dep_id");
                String DepName=rs.getString("dep_name");
                
                
                txt_depid.setText(DepID);
                txt_depname.setText(DepName);
               
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

 public boolean addDepartemnt(){
        boolean isAdded=false;
        dep_id=Integer.parseInt(txt_depid.getText());
        dep_name=txt_depname.getText();
       
               
        String url="jdbc:oracle:thin:@localhost:1523:orcl";
        
        try {
            Connection con=DriverManager.getConnection(url,"yosef","123456");
            String sql="insert into department (dep_ID,dep_NAME) values (?,?)";
             con.setAutoCommit(false);
            PreparedStatement pst=con.prepareStatement(sql);
           
            pst.setInt(1, dep_id);
            pst.setString(2, dep_name);
           
            
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
        txt_depid = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txt_depname = new javax.swing.JTextField();
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
        dep_det = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(153, 0, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Department ID:");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 250, 290, 50));

        txt_depid.setBackground(new java.awt.Color(153, 0, 51));
        txt_depid.setForeground(new java.awt.Color(255, 255, 255));
        txt_depid.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        txt_depid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_depidActionPerformed(evt);
            }
        });
        jPanel1.add(txt_depid, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 290, 230, 30));

        jLabel13.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Department Name:");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 330, 290, 50));

        txt_depname.setBackground(new java.awt.Color(153, 0, 51));
        txt_depname.setForeground(new java.awt.Color(255, 255, 255));
        txt_depname.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        txt_depname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_depnameActionPerformed(evt);
            }
        });
        jPanel1.add(txt_depname, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 370, 230, 30));

        jButton1.setBackground(new java.awt.Color(153, 0, 51));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Update");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 640, 130, 50));

        jButton2.setBackground(new java.awt.Color(153, 0, 51));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Add");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 540, 130, 50));

        jButton3.setBackground(new java.awt.Color(153, 0, 51));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Search");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 640, 130, 50));

        jButton4.setBackground(new java.awt.Color(153, 0, 51));
        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Delete");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 540, 130, 50));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("X");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, -10, 60, 60));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 0, 580, 830));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel21.setBackground(new java.awt.Color(153, 0, 51));
        jPanel21.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel3.add(jPanel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 110, 320, 5));

        jLabel2.setBackground(new java.awt.Color(153, 0, 51));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(153, 0, 51));
        jLabel2.setIcon(new javax.swing.ImageIcon("C:\\Users\\Msys\\OneDrive\\Documents\\NetBeansProjects\\Library_DBMS\\src\\main\\java\\Student.png")); // NOI18N
        jLabel2.setText("Manage Department");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 50, 330, 60));

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

        dep_det.setBackground(new java.awt.Color(204, 204, 204));
        dep_det.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        dep_det.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"01", "Animation Department"},
                {"02", "Science Department"},
                {null, null},
                {null, null}
            },
            new String [] {
                "Department ID", "Department Name"
            }
        ));
        dep_det.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dep_detMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(dep_det);

        jPanel3.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 280, 870, -1));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1140, 830));

        setSize(new java.awt.Dimension(1740, 832));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
  if(DeleteDepartment()==true){
           JOptionPane.showMessageDialog(this, "Department Deleted");
           setdepartmentDetails();
       }
       else{
                      JOptionPane.showMessageDialog(this, "This Department Deletion Failed");
//setBookDetails();
       }        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
if(!searchDepartment()){
            JOptionPane.showMessageDialog(rootPane, "Entry Not Found.");
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
if(addDepartemnt()==true){
           JOptionPane.showMessageDialog(this, "Department Added");
           setdepartmentDetails();
       }
       else{
                      JOptionPane.showMessageDialog(this, "This Department Addition Failed");
//setBookDetails();
       }        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
 if(UpdateDepartment()==true){
           JOptionPane.showMessageDialog(this, "Department Updated");
           setdepartmentDetails();
       }
       else{
                      JOptionPane.showMessageDialog(this, "This Department Update Failed");
//setBookDetails();
       }              // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txt_depnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_depnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_depnameActionPerformed

    private void txt_depidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_depidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_depidActionPerformed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
 Home_Page_Emp A=new Home_Page_Emp();
        A.setVisible(true);
        this.dispose();        
// TODO add your handling code here:
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
System.exit(0);        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel3MouseClicked

    private void dep_detMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dep_detMouseClicked
    int RowNumber=dep_det.getSelectedRow();
        TableModel model=dep_det.getModel();
        
        txt_depid.setText(model.getValueAt(RowNumber, 0).toString());
        txt_depname.setText(model.getValueAt(RowNumber, 1).toString());
                 // TODO add your handling code here:
    }//GEN-LAST:event_dep_detMouseClicked

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
            java.util.logging.Logger.getLogger(Manage_Department2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Manage_Department2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Manage_Department2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Manage_Department2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Manage_Department2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable dep_det;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField txt_depid;
    private javax.swing.JTextField txt_depname;
    // End of variables declaration//GEN-END:variables
}
