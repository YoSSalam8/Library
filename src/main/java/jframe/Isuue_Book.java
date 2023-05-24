/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package jframe;

import java.awt.print.Book;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author Msys
 */
public class Isuue_Book extends javax.swing.JFrame {

    /**
     * Creates new form Isuue_Book
     */
    public Isuue_Book() {
        initComponents();
    }
    
    
    public void getBookDetails(){
        
        int BookID=Integer.parseInt(txt_bookid.getText());
        String url="jdbc:oracle:thin:@localhost:1523:orcl";
        
        try {
            
           Connection con=DriverManager.getConnection(url,"yosef","123456");
            String sql="select * from book where book_id=?";
             con.setAutoCommit(false);
            PreparedStatement pst=con.prepareStatement(sql); 
            
           pst.setInt(1, BookID);
            ResultSet rs=pst.executeQuery();
                
            if(rs.next()){
                
                lbl_bookid.setText(rs.getString("Book_id"));
                lbl_bookname.setText(rs.getString("book_name"));
                lbl_bookauthor.setText(rs.getString("Book_author"));
                lbl_bookquantity.setText(rs.getString("book_quantity"));
                lbl_depid.setText(rs.getString("department_id"));
                
                
            }
            else{
                JOptionPane.showMessageDialog(this, "Invalid Book ID");
            }
            
            
        } catch (Exception e) {
        }
        
        
        
    }
    
    
    public void getStudentDetails(){
        
        int Stuid=Integer.parseInt(txt_stuid.getText());
        String url="jdbc:oracle:thin:@localhost:1523:orcl";
        
        try {
            
           Connection con=DriverManager.getConnection(url,"yosef","123456");
            String sql="select * from student where id=?";
             con.setAutoCommit(false);
            PreparedStatement pst=con.prepareStatement(sql); 
            
           pst.setInt(1, Stuid);
            ResultSet rs=pst.executeQuery();
                
            if(rs.next()){
                
                lbl_stuid.setText(rs.getString("id"));
                lbl_stuname.setText(rs.getString("name"));
                lbl_stueducation.setText(rs.getString("Education"));
                lbl_phone.setText(rs.getString("Contact"));
                
                
                
            }
            else{
                JOptionPane.showMessageDialog(this, "Invalid Student ID");
            }
            
            
        } catch (Exception e) {
        }
        
        
        
    }
    
    
    
    public boolean issueBook(){
         boolean isissued=false;
         int IssueId=Integer.parseInt(txt_issueid.getText());
         int Bookid=Integer.parseInt(txt_bookid.getText());
         int stuID=Integer.parseInt(txt_stuid.getText());
         String bookname=lbl_bookname.getText();
         String stuname=lbl_stuname.getText();  
         
         
         java.util.Date BorrowedDate= date_Borroweddate.getDatoFecha();
         java.util.Date ReturnedDate= date_returneddate.getDatoFecha();
         
         
         long L1=BorrowedDate.getTime();
         long L2=ReturnedDate.getTime();
         
         java.sql.Date BorDate=new java.sql.Date(L1);
         java.sql.Date RetDate =new java.sql.Date(L2);
         
         
         
        
         String url="jdbc:oracle:thin:@localhost:1523:orcl";
        
        try {
            
             Connection con=DriverManager.getConnection(url,"yosef","123456");
            String sql="insert into student_book(issue_id,stu_id,book_id,stu_name,book_name,borrowed_date,returned_date,status) values(?,?,?,?,?,?,?,?)";
             con.setAutoCommit(false);
            PreparedStatement pst=con.prepareStatement(sql); 
            
            pst.setInt(1, IssueId);
            pst.setInt(2, stuID);
            pst.setInt(3, Bookid);
            
            pst.setString(4, stuname);
            pst.setString(5, bookname);
            
            pst.setDate(6, BorDate);
            pst.setDate(7, RetDate);
            
            pst.setString(8, "Pending");
            
            int RowCount=pst.executeUpdate();
            if(RowCount>0){
                isissued=true;
            }
            else{
                isissued=false;
            }
             con.commit();
        con.close();
            
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return isissued;
        
    }
    
    
    public void UpdateBookCount(){
        
        int BookID=Integer.parseInt(txt_bookid.getText());
        String url="jdbc:oracle:thin:@localhost:1523:orcl";
        try {
            
            Connection con=DriverManager.getConnection(url,"yosef","123456");
            String sql="update book set book_quantity=book_quantity-1 where Book_id=?";
             con.setAutoCommit(false);
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setInt(1, BookID);
            
            int RowCount=pst.executeUpdate();
            
            if(RowCount>0){
                
                JOptionPane.showMessageDialog(this, "Book Quantity Updated");
                int initialCount=Integer.parseInt(lbl_bookquantity.getText());
                lbl_bookquantity.setText(Integer.toString(initialCount-1));
            }
            else{
                JOptionPane.showMessageDialog(this, "Cant Update Book Quantity ");
                
            }
            
            con.commit();
            con.close();
            
            
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        
        
        
    }
    
    
    public boolean IsAlreadyIssued(){
        boolean isAlreadyIssued=false;
        int BookID=Integer.parseInt(txt_bookid.getText());
        int StuID=Integer.parseInt(txt_stuid.getText());
         String url="jdbc:oracle:thin:@localhost:1523:orcl";
         
         try {
             Connection con=DriverManager.getConnection(url,"yosef","123456");
            String sql="select * from Student_book where book_id=? and stu_id=? and status=?";
             con.setAutoCommit(false);
            PreparedStatement pst=con.prepareStatement(sql); 
            
            pst.setInt(1, BookID);
            pst.setInt(2, StuID);
            pst.setString(3, "pending");
            
            
            ResultSet rs =pst.executeQuery();
            
            if(rs.next()){
                isAlreadyIssued=true;
            }
            else{
                isAlreadyIssued=false;
            }
            
            con.commit();
            con.close();
            
            
            
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        
        return isAlreadyIssued;
    }
 public boolean CheckDuplicateIssueID(){
         int IssueId=Integer.parseInt(txt_issueid.getText());
         boolean exist=false;
         String url="jdbc:oracle:thin:@localhost:1523:orcl";
         
         try {
             
             Connection con=DriverManager.getConnection(url,"yosef","123456");
             
             PreparedStatement pst=con.prepareStatement("select * from student_book where issue_id = ?");
             pst.setInt(1, IssueId);
             ResultSet rs=pst.executeQuery();
             if(rs.next()){
                 exist=true;
                
             }
             
             else{
                 exist=false;
             }
             
             
             
         } catch (Exception e) {
             e.printStackTrace();
         }
         return exist;
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
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        lbl_stuid = new javax.swing.JLabel();
        lbl_stuname = new javax.swing.JLabel();
        lbl_stueducation = new javax.swing.JLabel();
        lbl_phone = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        lbl_depid = new javax.swing.JLabel();
        lbl_bookid = new javax.swing.JLabel();
        lbl_bookname = new javax.swing.JLabel();
        lbl_bookauthor = new javax.swing.JLabel();
        lbl_bookquantity = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txt_stuid = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        txt_bookid = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        date_Borroweddate = new rojeru_san.componentes.RSDateChooser();
        date_returneddate = new rojeru_san.componentes.RSDateChooser();
        jLabel21 = new javax.swing.JLabel();
        txt_issueid = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(153, 0, 51));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(153, 0, 51));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 160, 60));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 25)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setIcon(new javax.swing.ImageIcon("C:\\Users\\Msys\\OneDrive\\Documents\\NetBeansProjects\\Library_DBMS\\src\\main\\java\\mang_stu.png")); // NOI18N
        jLabel2.setText("Student Details");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, 310, 120));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 190, 190, 3));

        jLabel11.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Student ID:");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, 110, 50));

        jLabel12.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Student Name:");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, 140, 50));

        jLabel13.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Education:");
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 420, 120, 50));

        jLabel14.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Phone:");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 490, 90, 50));

        lbl_stuid.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbl_stuid.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(lbl_stuid, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 290, 230, 40));

        lbl_stuname.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbl_stuname.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(lbl_stuname, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 360, 230, 40));

        lbl_stueducation.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbl_stueducation.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(lbl_stueducation, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 430, 230, 40));

        lbl_phone.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbl_phone.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(lbl_phone, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 500, 230, 40));

        jLabel28.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("X");
        jLabel28.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel28MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 0, 50, 50));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 0, 420, 810));

        jPanel5.setBackground(new java.awt.Color(153, 0, 51));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        jPanel5.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 160, 60));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 25)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setIcon(new javax.swing.ImageIcon("C:\\Users\\Msys\\OneDrive\\Documents\\NetBeansProjects\\Library_DBMS\\src\\main\\java\\Bookbook.png")); // NOI18N
        jLabel9.setText("Book Details");
        jPanel5.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, 310, 120));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel5.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 190, 150, 3));

        jLabel16.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Book ID:");
        jPanel5.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, 90, 50));

        jLabel17.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Book Name:");
        jPanel5.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, 110, 50));

        jLabel18.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Book Author:");
        jPanel5.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 420, 120, 50));

        jLabel19.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Quantity:");
        jPanel5.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 490, 90, 50));

        jLabel20.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Department ID:");
        jPanel5.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 560, 140, 50));

        lbl_depid.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbl_depid.setForeground(new java.awt.Color(255, 255, 255));
        jPanel5.add(lbl_depid, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 570, 230, 40));

        lbl_bookid.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbl_bookid.setForeground(new java.awt.Color(255, 255, 255));
        jPanel5.add(lbl_bookid, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 290, 230, 40));

        lbl_bookname.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbl_bookname.setForeground(new java.awt.Color(255, 255, 255));
        jPanel5.add(lbl_bookname, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 360, 230, 40));

        lbl_bookauthor.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbl_bookauthor.setForeground(new java.awt.Color(255, 255, 255));
        jPanel5.add(lbl_bookauthor, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 430, 230, 40));

        lbl_bookquantity.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbl_bookquantity.setForeground(new java.awt.Color(255, 255, 255));
        jPanel5.add(lbl_bookquantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 500, 230, 40));

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 420, 810));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 25)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon("C:\\Users\\Msys\\OneDrive\\Documents\\NetBeansProjects\\Library_DBMS\\src\\main\\java\\Issuebook.png")); // NOI18N
        jLabel3.setText("Issue Book");

        jLabel15.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        jLabel15.setText("Book ID:");

        txt_stuid.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txt_stuid.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        txt_stuid.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_stuidFocusLost(evt);
            }
        });
        txt_stuid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_stuidActionPerformed(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        jLabel25.setText("Issue Date:");

        txt_bookid.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txt_bookid.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        txt_bookid.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_bookidFocusLost(evt);
            }
        });
        txt_bookid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_bookidActionPerformed(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        jLabel26.setText("Student ID:");

        jLabel27.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        jLabel27.setText("Due Date:");

        jButton1.setBackground(new java.awt.Color(153, 0, 51));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Issue Book");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel9.setBackground(new java.awt.Color(153, 0, 51));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        date_Borroweddate.setBackground(new java.awt.Color(153, 0, 51));
        date_Borroweddate.setColorBackground(new java.awt.Color(153, 0, 51));
        date_Borroweddate.setColorForeground(new java.awt.Color(153, 0, 0));

        date_returneddate.setBackground(new java.awt.Color(153, 0, 51));
        date_returneddate.setColorBackground(new java.awt.Color(153, 0, 51));
        date_returneddate.setColorForeground(new java.awt.Color(153, 0, 51));

        jLabel21.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        jLabel21.setText("Issue ID:");

        txt_issueid.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txt_issueid.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        txt_issueid.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_issueidFocusLost(evt);
            }
        });
        txt_issueid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_issueidActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(204, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(137, 137, 137))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(204, 204, 204))))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(date_returneddate, javax.swing.GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
                                    .addComponent(date_Borroweddate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txt_stuid)))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(210, 210, 210)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txt_issueid, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_bookid, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(157, 157, 157)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(85, 85, 85)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_issueid, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_bookid, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_stuid, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(date_Borroweddate, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addGap(205, 205, 205)
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .addComponent(date_returneddate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );

        jPanel1.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 0, 570, 800));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1410, 800));

        setSize(new java.awt.Dimension(1427, 811));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_stuidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_stuidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_stuidActionPerformed

    private void txt_bookidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_bookidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_bookidActionPerformed

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        Home_Page A=new Home_Page();
        A.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel8MouseClicked

    private void jLabel28MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel28MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel28MouseClicked

    private void txt_bookidFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_bookidFocusLost
        
        if(!txt_bookid.getText().equals("")){
            getBookDetails();
        }
        
    }//GEN-LAST:event_txt_bookidFocusLost

    private void txt_stuidFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_stuidFocusLost
if(!txt_stuid.getText().equals("")){
            getStudentDetails();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_txt_stuidFocusLost

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
            if(lbl_bookquantity.getText().equals("0")){
                JOptionPane.showMessageDialog(this, "Book Is Not Available");
            }
            else{
                
                    
                    
                if(IsAlreadyIssued()==false){
                     if(issueBook()==true){
               
               JOptionPane.showMessageDialog(this, "This Book Has Been Borrowed Successfully");
               UpdateBookCount();
               
           }
           else{
               JOptionPane.showMessageDialog(this, "Cant Borrow This Book");
           }
                    
                }else{
                    
                    JOptionPane.showMessageDialog(this, "This Student Already Has This Book");
                }
                    
                    
                    
                
                
              
                
                
                
            }
      
          
           
           
     
      
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txt_issueidFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_issueidFocusLost
   if (CheckDuplicateIssueID()==true){
            JOptionPane.showMessageDialog(this, "This ID already Exist");
        } 
           // TODO add your handling code here:
    }//GEN-LAST:event_txt_issueidFocusLost

    private void txt_issueidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_issueidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_issueidActionPerformed

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
            java.util.logging.Logger.getLogger(Isuue_Book.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Isuue_Book.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Isuue_Book.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Isuue_Book.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Isuue_Book().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.componentes.RSDateChooser date_Borroweddate;
    private rojeru_san.componentes.RSDateChooser date_returneddate;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JLabel lbl_bookauthor;
    private javax.swing.JLabel lbl_bookid;
    private javax.swing.JLabel lbl_bookname;
    private javax.swing.JLabel lbl_bookquantity;
    private javax.swing.JLabel lbl_depid;
    private javax.swing.JLabel lbl_phone;
    private javax.swing.JLabel lbl_stueducation;
    private javax.swing.JLabel lbl_stuid;
    private javax.swing.JLabel lbl_stuname;
    private javax.swing.JTextField txt_bookid;
    private javax.swing.JTextField txt_issueid;
    private javax.swing.JTextField txt_stuid;
    // End of variables declaration//GEN-END:variables
}
