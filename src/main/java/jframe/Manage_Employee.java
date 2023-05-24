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
public class Manage_Employee extends javax.swing.JFrame {

    /**
     * Creates new form Manage_Books
     */
    
    String emp_name,emp_email,emp_Phone,salary;
    int emp_id,dep_id;
    
    DefaultTableModel model;
    DefaultTableModel refModel;
     
    public Manage_Employee() {
        initComponents();
        refModel = (DefaultTableModel)emp_det.getModel();
        setEmployeeDetails();
    }
    
    
    public void setEmployeeDetails(){
         String url="jdbc:oracle:thin:@localhost:1523:orcl";
        try {
         
            Connection con=DriverManager.getConnection(url,"yosef","123456");
            Statement st=con.createStatement();
            ResultSet rs= st.executeQuery("select * from Employee");
            model = (DefaultTableModel)emp_det.getModel();
            model.getDataVector().removeAllElements();
            while(rs.next()){
                
                String empid=rs.getString("emp_id");
                String empName=rs.getString("emp_name");
                String empemail=rs.getString("emp_email");
                String empphone=rs.getString("emp_phone");
                String empsalary=rs.getString("salary");
                int DepartmentID =rs.getInt("dep_id");
                
                
                
                Object[] obj={empid,empName,empemail,empphone,empsalary,DepartmentID};
                model=(DefaultTableModel)emp_det.getModel();
                model.addRow(obj);
                
                 
            }
            
            
            
            
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public boolean DeleteEmployee(){
        
        boolean isDeleted=false;
        emp_id=Integer.parseInt(txt_empid.getText());
         String url="jdbc:oracle:thin:@localhost:1523:orcl";
         
         try {
            Connection con=DriverManager.getConnection(url,"yosef","123456");
            String sql=" delete from employee where emp_id=?";
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setInt(1, emp_id);
            
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
    
    
    public boolean Updateemployee(){
        
        boolean isUpdated=false;
        emp_id=Integer.parseInt(txt_empid.getText());
        emp_name=txt_empname.getText();
        emp_email=txt_empemail.getText();
        emp_Phone=txt_empphone.getText();
        salary=txt_empsalary.getText();
        dep_id=Integer.parseInt(txt_depiddep.getText());
       
        
               
        String url="jdbc:oracle:thin:@localhost:1523:orcl";
        
        try {
            
            Connection con=DriverManager.getConnection(url,"yosef","123456");
            String sql=" update employee set emp_name=?,emp_email=?,emp_phone=?,Dep_ID=?,salary=? where emp_ID=?";
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setString(1, emp_name);
            pst.setString(2, emp_email);
            pst.setString(3, emp_Phone);
            pst.setInt(4, Integer.parseInt(txt_depiddep.getText()));
            pst.setString(5, salary);
            pst.setInt(6, emp_id);
            
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
    
    public boolean searchEmployee(){
        String url="jdbc:oracle:thin:@localhost:1523:orcl";
        
        try {
            Connection con=DriverManager.getConnection(url,"yosef","123456");
            String sql="SELECT * FROM Employee WHERE emp_ID="+txt_empid.getText();
            con.setAutoCommit(false);
            Statement pst=con.createStatement();
            ResultSet rs= pst.executeQuery(sql);
            if(rs.next()){
                String empid=rs.getString("emp_id");
                String empName=rs.getString("emp_name");
                String empemail=rs.getString("emp_email");
                String empphone=rs.getString("emp_phone");
                int depid=rs.getInt("dep_id");
                String empsalary=rs.getString("salary");
                
                txt_empid.setText(empid);
                txt_empname.setText(empName);
                txt_empemail.setText(empemail);
                txt_empphone.setText(empphone);
                txt_empsalary.setText(empsalary);
                txt_depiddep.setText(Integer.toString(depid));
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
    
    
    public boolean addEmployee(){
        boolean isAdded=false;
        emp_id=Integer.parseInt(txt_empid.getText());
        emp_name=txt_empname.getText();
        emp_email=txt_empemail.getText();
        dep_id=Integer.parseInt(txt_depiddep.getText());
        emp_Phone=txt_empphone.getText();
        salary=txt_empsalary.getText();
        
               
        String url="jdbc:oracle:thin:@localhost:1523:orcl";
        
        try {
            Connection con=DriverManager.getConnection(url,"yosef","123456");
            String sql="insert into employee (emp_ID,emp_NAME,emp_email,emp_phone,DEp_ID,salary) values (?,?,?,?,?,?)";
             con.setAutoCommit(false);
            PreparedStatement pst=con.prepareStatement(sql);
           
            pst.setInt(1, emp_id);
            pst.setString(2, emp_name);
            pst.setString(3, emp_email);
            pst.setString(4, emp_Phone);
            pst.setInt(5, dep_id);
            pst.setString(6, salary);
            
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
        txt_empid = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txt_empname = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txt_empemail = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txt_empphone = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txt_empsalary = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txt_depiddep = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        emp_det = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(153, 0, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Emp ID");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, 290, 50));

        txt_empid.setBackground(new java.awt.Color(153, 0, 51));
        txt_empid.setForeground(new java.awt.Color(255, 255, 255));
        txt_empid.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        txt_empid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_empidActionPerformed(evt);
            }
        });
        jPanel1.add(txt_empid, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 110, 230, 30));

        jLabel13.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Emp Name");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 150, 290, 50));

        txt_empname.setBackground(new java.awt.Color(153, 0, 51));
        txt_empname.setForeground(new java.awt.Color(255, 255, 255));
        txt_empname.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        txt_empname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_empnameActionPerformed(evt);
            }
        });
        jPanel1.add(txt_empname, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 200, 230, 30));

        jLabel15.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Emp Email");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 240, 290, 50));

        txt_empemail.setBackground(new java.awt.Color(153, 0, 51));
        txt_empemail.setForeground(new java.awt.Color(255, 255, 255));
        txt_empemail.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        txt_empemail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_empemailActionPerformed(evt);
            }
        });
        jPanel1.add(txt_empemail, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 290, 230, 30));

        jLabel17.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Emp Phone");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 340, 290, 50));

        txt_empphone.setBackground(new java.awt.Color(153, 0, 51));
        txt_empphone.setForeground(new java.awt.Color(255, 255, 255));
        txt_empphone.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        txt_empphone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_empphoneActionPerformed(evt);
            }
        });
        jPanel1.add(txt_empphone, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 390, 230, 30));

        jLabel19.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Emp Salary");
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 430, 290, 50));

        txt_empsalary.setBackground(new java.awt.Color(153, 0, 51));
        txt_empsalary.setForeground(new java.awt.Color(255, 255, 255));
        txt_empsalary.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        txt_empsalary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_empsalaryActionPerformed(evt);
            }
        });
        jPanel1.add(txt_empsalary, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 480, 230, 30));

        jButton1.setBackground(new java.awt.Color(153, 0, 51));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Update");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 730, 130, 50));

        jButton2.setBackground(new java.awt.Color(153, 0, 51));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Add");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 630, 130, 50));

        jButton3.setBackground(new java.awt.Color(153, 0, 51));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Search");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 730, 130, 50));

        jButton4.setBackground(new java.awt.Color(153, 0, 51));
        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Delete");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 630, 130, 50));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("X");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 0, 60, 50));

        jLabel20.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Department ID");
        jPanel1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 510, 290, 50));

        txt_depiddep.setBackground(new java.awt.Color(153, 0, 51));
        txt_depiddep.setForeground(new java.awt.Color(255, 255, 255));
        txt_depiddep.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        txt_depiddep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_depiddepActionPerformed(evt);
            }
        });
        jPanel1.add(txt_depiddep, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 560, 230, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 0, 580, 830));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel21.setBackground(new java.awt.Color(153, 0, 51));
        jPanel21.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel3.add(jPanel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 110, 270, 5));

        jLabel2.setBackground(new java.awt.Color(153, 0, 51));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(153, 0, 51));
        jLabel2.setIcon(new javax.swing.ImageIcon("C:\\Users\\Msys\\OneDrive\\Documents\\NetBeansProjects\\Library_DBMS\\src\\main\\java\\stu.png")); // NOI18N
        jLabel2.setText("Manage Employee");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 60, 320, 60));

        jPanel22.setBackground(new java.awt.Color(153, 0, 51));
        jPanel22.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel3.add(jPanel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 260, 960, 10));

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

        emp_det.setAutoCreateRowSorter(true);
        emp_det.setBackground(new java.awt.Color(204, 204, 204));
        emp_det.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        emp_det.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Emp_ID", "Emp_Name", "Emp_Email", "Emp_Contact", "Emp_Salary", "Department_ID"
            }
        ));
        emp_det.setRowHeight(30);
        emp_det.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                emp_detMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(emp_det);

        jPanel3.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 270, 960, -1));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1140, 830));

        setSize(new java.awt.Dimension(1740, 832));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
          if(DeleteEmployee()==true){
           JOptionPane.showMessageDialog(this, "Employee Deleted");
           setEmployeeDetails();
       }
       else{
                      JOptionPane.showMessageDialog(this, "This Employee Deletion Failed");
//setBookDetails();
       }
    
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        if(!searchEmployee()){
            JOptionPane.showMessageDialog(rootPane, "Entry not found.");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       if(addEmployee()==true){
           JOptionPane.showMessageDialog(this, "Employee Added");
           setEmployeeDetails();
       }
       else{
                      JOptionPane.showMessageDialog(this, "This Employee Addition Failed");
//setBookDetails();
       }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
 if(Updateemployee()==true){
           JOptionPane.showMessageDialog(this, "Employee Information Updated");
           setEmployeeDetails();
       }
       else{
                      JOptionPane.showMessageDialog(this, "This Employee Update Failed");
//setBookDetails();
       }        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txt_empsalaryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_empsalaryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_empsalaryActionPerformed

    private void txt_empphoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_empphoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_empphoneActionPerformed

    private void txt_empemailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_empemailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_empemailActionPerformed

    private void txt_empnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_empnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_empnameActionPerformed

    private void txt_empidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_empidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_empidActionPerformed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
       Home_Page A=new Home_Page();
        A.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
System.exit(0);        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel3MouseClicked

    private void emp_detMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_emp_detMouseClicked
        int RowNumber=emp_det.getSelectedRow();
        TableModel model=emp_det.getModel();
        
        txt_empid.setText(model.getValueAt(RowNumber, 0).toString());
        txt_empname.setText(model.getValueAt(RowNumber, 1).toString());
         txt_empemail.setText(model.getValueAt(RowNumber, 2).toString());
          txt_empphone.setText(model.getValueAt(RowNumber, 3).toString());
          txt_empsalary.setText(model.getValueAt(RowNumber, 4).toString());
          txt_depiddep.setText(model.getValueAt(RowNumber, 5).toString());
    }//GEN-LAST:event_emp_detMouseClicked

    private void txt_depiddepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_depiddepActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_depiddepActionPerformed

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
            java.util.logging.Logger.getLogger(Manage_Employee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Manage_Employee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Manage_Employee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Manage_Employee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Manage_Employee().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable emp_det;
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
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField txt_depiddep;
    private javax.swing.JTextField txt_empemail;
    private javax.swing.JTextField txt_empid;
    private javax.swing.JTextField txt_empname;
    private javax.swing.JTextField txt_empphone;
    private javax.swing.JTextField txt_empsalary;
    // End of variables declaration//GEN-END:variables
}
