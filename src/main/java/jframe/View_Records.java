/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package jframe;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.swing.JRViewer;

/**
 *
 * @author Msys
 */
public class View_Records extends javax.swing.JFrame {

    /**
     * Creates new form View_Records
     */
    DefaultTableModel model;
    public View_Records() {
        initComponents();
        setRecordDetails();
    }
    
    public void setRecordDetails(){
         String url="jdbc:oracle:thin:@localhost:1523:orcl";
        try {
         
            Connection con=DriverManager.getConnection(url,"yosef","123456");
            con.setAutoCommit(false);
            Statement st=con.createStatement();
            ResultSet rs= st.executeQuery("select * from student_book");
            model = (DefaultTableModel)records_det.getModel();
            model.getDataVector().removeAllElements();
            while(rs.next()){
                String IssueID=rs.getString("Issue_id");
                String StuID=rs.getString("Stu_id");
                String BookId=rs.getString("book_id");
                
                String StuName=rs.getString("Stu_name");
                String BookName=rs.getString("book_name");

                String Borroweddate=rs.getString("Borrowed_date");
                String Returneddate=rs.getString("Returned_date");
                String Status=rs.getString("Status");
                
                
                Object[] obj={IssueID,StuID,BookId,StuName,BookName,Borroweddate,Returneddate,Status};
                model=(DefaultTableModel)records_det.getModel();
                model.addRow(obj);
                
                 
            }
            
            con.commit();
            con.close();
            
            
            
            
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public void SerachRecords(){
        
        java.util.Date BorrowedDate= date_borroweddate.getDatoFecha();
         java.util.Date ReturnedDate= date_returneddate.getDatoFecha();
         
         
         long L1=BorrowedDate.getTime();
         long L2=ReturnedDate.getTime();
         
         java.sql.Date BorDate=new java.sql.Date(L1);
         java.sql.Date RetDate =new java.sql.Date(L2);
       
        
        String url="jdbc:oracle:thin:@localhost:1523:orcl";
        
        try {
            
            Connection con=DriverManager.getConnection(url,"yosef","123456");
            String sql="SELECT * FROM student_book WHERE Borrowed_date=? and returned_date=?";
            con.setAutoCommit(false);
            PreparedStatement pst=con.prepareStatement(sql);
            
            pst.setDate(1, BorDate);
            pst.setDate(2, RetDate);
            
            
            ResultSet rs= pst.executeQuery();
            
            if(rs.next()==false){
                
                JOptionPane.showMessageDialog(this, "No Records Found");
                
            }
            
            else{
                
                 String IssueID=rs.getString("Issue_id");
                String StuID=rs.getString("Stu_id");
                String BookId=rs.getString("book_id");
                
                String StuName=rs.getString("Stu_name");
                String BookName=rs.getString("book_name");

                String Borroweddate=rs.getString("Borrowed_date");
                String Returneddate=rs.getString("Returned_date");
                String Status=rs.getString("Status");
                
                
                Object[] obj={IssueID,StuID,BookId,StuName,BookName,Borroweddate,Returneddate,Status};
                model=(DefaultTableModel)records_det.getModel();
                model.addRow(obj);
                
                
            }
            
            con.commit();
            con.close();
            
           
            
            
            
            
            
        } catch (Exception e) {
        e.printStackTrace();
        }
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
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
        jLabel9 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        date_returneddate = new rojeru_san.componentes.RSDateChooser();
        date_borroweddate = new rojeru_san.componentes.RSDateChooser();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        records_det = new javax.swing.JTable();
        jPanel22 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(153, 0, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 25)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setIcon(new javax.swing.ImageIcon("C:\\Users\\Msys\\OneDrive\\Documents\\NetBeansProjects\\Library_DBMS\\src\\main\\java\\Bookbook.png")); // NOI18N
        jLabel9.setText("View All Records");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 30, 310, 120));

        jLabel25.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Due Date:");
        jPanel1.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 180, 140, 50));

        jLabel26.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("Issue Date:");
        jPanel1.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 180, 140, 50));

        jButton1.setBackground(new java.awt.Color(153, 0, 51));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Search");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1290, 190, 130, 50));

        jPanel6.setBackground(new java.awt.Color(153, 0, 51));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setIcon(new javax.swing.ImageIcon("C:\\Users\\Msys\\OneDrive\\Documents\\NetBeansProjects\\Library_DBMS\\src\\main\\java\\Back.png")); // NOI18N
        jLabel8.setText("Back");
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });
        jPanel6.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 130, 40));

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 160, 60));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("X");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1440, 0, 60, -1));

        date_returneddate.setColorBackground(new java.awt.Color(153, 0, 51));
        date_returneddate.setColorForeground(new java.awt.Color(153, 0, 51));
        jPanel1.add(date_returneddate, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 190, 260, -1));

        date_borroweddate.setColorBackground(new java.awt.Color(153, 0, 51));
        date_borroweddate.setColorForeground(new java.awt.Color(153, 0, 51));
        jPanel1.add(date_borroweddate, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 190, 260, -1));

        jButton2.setText("JasperReport");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 50, 140, 100));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1480, 280));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        records_det.setAutoCreateRowSorter(true);
        records_det.setBackground(new java.awt.Color(204, 204, 204));
        records_det.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        records_det.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "IssueID", "Student Id", "Book ID", "Student Name", "Book Name", "Issue Date", "Due DAte", "Status"
            }
        ));
        records_det.setRowHeight(30);
        jScrollPane1.setViewportView(records_det);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 80, 1350, 480));

        jPanel22.setBackground(new java.awt.Color(153, 0, 51));
        jPanel22.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel2.add(jPanel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 70, 1350, 10));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 280, 1480, 580));

        setSize(new java.awt.Dimension(1497, 865));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        Home_Page A=new Home_Page();
        A.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel8MouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
System.exit(0);        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       model.getDataVector().removeAllElements();
        SerachRecords();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        
        
        Connection con;
        try {
            String url="jdbc:oracle:thin:@localhost:1523:orcl";
            con = DriverManager.getConnection(url,"yosef","123456");
            PreparedStatement pst=con.prepareStatement("select * from Secretary where sec_username = ? and sec_password =?");
            InputStream input = new FileInputStream(new File("src/main/java/BookRecordssumm.jrxml"));
            JasperDesign jd = JRXmlLoader.load(input);
            JasperReport jr = JasperCompileManager.compileReport(jd);
            JasperPrint jp = JasperFillManager.fillReport(jr,null,con);
            
            JFrame frame = new JFrame("report");
            frame.getContentPane().add(new JRViewer(jp));
            frame.pack();
            frame.setVisible(true);
            
        } catch (SQLException ex) {
            Logger.getLogger(View_Records.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(View_Records.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JRException ex) {
            Logger.getLogger(View_Records.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(View_Records.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(View_Records.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(View_Records.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(View_Records.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new View_Records().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.componentes.RSDateChooser date_borroweddate;
    private rojeru_san.componentes.RSDateChooser date_returneddate;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable records_det;
    // End of variables declaration//GEN-END:variables
}
