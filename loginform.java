package LoginForm;

import Admin.AdminMainPanel;
import Librarian.LibrarianMain;
import bursar.BursarMain;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import student.StudentMain;
import teacher.TeacherMain;

public class LoginForm extends javax.swing.JFrame 
{
    Connection con = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    private Task task;
    
    
    public LoginForm() 
    {
        initComponents();
        progressBar.setVisible(false); 
    }
    
    
    /*
     *method that check for usertype 
     *and give the corresponding 
     *user tables/forms
    */
    public void selectUserType()
    {
       if(userType.getSelectedItem() == "Admin")
       {
            AdminMainPanel adminEntry = new AdminMainPanel();
            adminEntry.setVisible(true);
       }
       if(userType.getSelectedItem() == "Teacher")
       {
           TeacherMain teacherEntry = new TeacherMain();
           teacherEntry.setVisible(true);
       }
       if(userType.getSelectedItem() == "Bursar")
       {
           BursarMain bursarEntry = new BursarMain();
           bursarEntry.setVisible(true);
       }
       if(userType.getSelectedItem() == "Librarian")
       {
           LibrarianMain librarianEntry = new LibrarianMain();
           librarianEntry.setVisible(true);
       }
       if(userType.getSelectedItem() == "Student")
       {
           StudentMain studentEntry = new StudentMain();
           studentEntry.setVisible(true);
       }
    }
    
    /*
      *Method that connects to the database 
      *and retrieves required data
    */
    public void connectDatabase()
    {
        try 
        {
          /**
            *checks if userNameText(textbox) 
            *and passwordText(textbox) 
            *are not empty 
          */
          if(!"".equals(userNameText.getText()) && !"".equals(passwordText.getText())){
         

   String url = "jdbc:derby://localhost:1527/Adam";
    

      String username = "uzdel";
          String password = "uzdel";

          
          Connection con = DriverManager.getConnection(url, username, password);


          String Query =  "select*from LOGINTBL where USERTYPE=? and USERNAME=? and PASSWORD =?";
          pst = con.prepareStatement(Query);
          pst.setString(1, (String) userType.getSelectedItem());
          pst.setString(2, userNameText.getText());
          pst.setString(3, passwordText.getText());
          rs = pst.executeQuery();
            
          if(rs.next())
            {
             progressBar.setIndeterminate(false);
             progressBar.setString("Login Successful");
             JOptionPane.showMessageDialog(this, "You have logged in as \n" + userType.getSelectedItem() + "\n Press ok to continue");
             userNameText.setText(null);
             passwordText.setText(null);
             selectUserType(); //method to check for usertype and give the corresponding user tables/forms
            }
           else
            {
             progressBar.setIndeterminate(false);
             progressBar.setString("Login Failed");
             JOptionPane.showMessageDialog(this, "Cannot Login, \nInvalid user data! \n Please try again...");
            } 
          }
          else
          {
            progressBar.setIndeterminate(false);
            progressBar.setString("Login Failed");
            JOptionPane.showMessageDialog(this, "username or password should not be empty \n Please fill the fields and try again");
          }
       }
       catch(SQLException ex)
       {
          JOptionPane.showMessageDialog(null, ex.toString());
       }
       finally
       {
           try
           {
              rs.close();
              pst.close();
           }
           catch(Exception e){}
       }//hereh
    }
    
    /*
     *Method that calls the class Task 
     *which calls the method connectDatabase() 
     *and sets up the progress bar
    */
    public void execute()
    { 
      task = new Task();       
      task.addPropertyChangeListener(propertyChangeListener);       
      task.execute();
      progressBar.setVisible(true);       
      progressBar.setIndeterminate(true);     
      goBtn.setEnabled(false); 
    }
    
    class Task extends SwingWorker<Void, Void> { 
        /*        
         * Main task. Executed in background thread.        
        */       
        @Override       
        public Void doInBackground()
        {
           connectDatabase();
           return null;
        }
        /*        
         * Executed in event dispatch thread        
        */       
        public void done() 
        { 
            // Beep             
            Toolkit.getDefaultToolkit().beep();
            progressBar.setString("");
            progressBar.setVisible(false);
            progressBar.setStringPainted(true);
            // enable the go button             
            goBtn.setEnabled(true);        
        } 
    }
    
    /**        
      * Invoked when task's progress property changes.
     * @param evt
      */ 
    public void propertyChange(PropertyChangeEvent evt) {        
          if ("progress" == evt.getPropertyName()) 
           {              
              Task task = new Task();
              int progress = task.getProgress();
              progressBar.setValue(progress);
              progressBar.setVisible(false); 
           }          
    }       
 
    PropertyChangeListener propertyChangeListener = new PropertyChangeListener() 
    {        
      @Override
      public void propertyChange(PropertyChangeEvent propertyChangeEvent) 
      {        
        String property = propertyChangeEvent.getPropertyName();
      } 
    };
   
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        goBtn = new javax.swing.JButton();
        cancelBtn = new javax.swing.JButton();
        progressBar = new javax.swing.JProgressBar();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        userNameText = new javax.swing.JTextField();
        passwordText = new javax.swing.JPasswordField();
        userType = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login Form");
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        setLocation(new java.awt.Point(200, 200));
        setMinimumSize(new java.awt.Dimension(500, 300));
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(500, 350));
        setResizable(false);
        setSize(new java.awt.Dimension(0, 0));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(null);

        jLabel1.setBackground(new java.awt.Color(153, 153, 153));
        jLabel1.setFont(new java.awt.Font("Vijaya", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(219, 239, 239));
        jLabel1.setText("Password:");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(90, 180, 110, 30);

        goBtn.setText("Go");
        goBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goBtnActionPerformed(evt);
            }
        });
        getContentPane().add(goBtn);
        goBtn.setBounds(200, 223, 80, 30);

        cancelBtn.setForeground(new java.awt.Color(102, 0, 0));
        cancelBtn.setText("Exit");
        cancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBtnActionPerformed(evt);
            }
        });
        getContentPane().add(cancelBtn);
        cancelBtn.setBounds(310, 223, 80, 30);

        progressBar.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        progressBar.setStringPainted(true);
        progressBar.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                progressBarPropertyChange(evt);
            }
        });
        getContentPane().add(progressBar);
        progressBar.setBounds(70, 270, 360, 20);
        progressBar.getAccessibleContext().setAccessibleParent(goBtn);

        jLabel3.setBackground(new java.awt.Color(153, 153, 153));
        jLabel3.setFont(new java.awt.Font("Vijaya", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(223, 237, 235));
        jLabel3.setText("UserName:");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(90, 130, 110, 30);

        jLabel4.setBackground(new java.awt.Color(153, 153, 153));
        jLabel4.setFont(new java.awt.Font("Vijaya", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(223, 237, 235));
        jLabel4.setText("Login Type");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(90, 80, 110, 30);

        userNameText.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        userNameText.setForeground(new java.awt.Color(255, 255, 255));
        userNameText.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        userNameText.setMinimumSize(new java.awt.Dimension(150, 30));
        userNameText.setOpaque(false);
        userNameText.setPreferredSize(new java.awt.Dimension(150, 30));
        userNameText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userNameTextActionPerformed(evt);
            }
        });
        getContentPane().add(userNameText);
        userNameText.setBounds(200, 130, 190, 30);

        passwordText.setForeground(new java.awt.Color(255, 255, 255));
        passwordText.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        passwordText.setOpaque(false);
        getContentPane().add(passwordText);
        passwordText.setBounds(200, 180, 190, 30);

        userType.setBackground(new java.awt.Color(0, 153, 153));
        userType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "Teacher", "Librarian", "Bursar", "Student" }));
        userType.setMinimumSize(new java.awt.Dimension(190, 30));
        userType.setOpaque(false);
        userType.setPreferredSize(new java.awt.Dimension(190, 30));
        userType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userTypeActionPerformed(evt);
            }
        });
        getContentPane().add(userType);
        userType.setBounds(200, 80, 190, 30);

        jPanel3.setBackground(new java.awt.Color(0, 51, 51));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(223, 239, 239), 2, true), "Login ", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 0, 18), new java.awt.Color(204, 255, 255))); // NOI18N
        getContentPane().add(jPanel3);
        jPanel3.setBounds(60, 30, 380, 270);

        jLabel2.setIcon(new javax.swing.ImageIcon("D:\\axam\\photoshop\\bg_01.jpg")); // NOI18N
        jLabel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 255, 204), new java.awt.Color(0, 255, 204), new java.awt.Color(0, 153, 153), new java.awt.Color(0, 204, 204)));
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel2.setMaximumSize(new java.awt.Dimension(500, 500));
        jLabel2.setMinimumSize(new java.awt.Dimension(500, 300));
        jLabel2.setPreferredSize(new java.awt.Dimension(500, 350));
        getContentPane().add(jLabel2);
        jLabel2.setBounds(0, 0, 500, 330);

        pack();
    }// </editor-fold>                        
    private void userTypeActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
    }                                        
    private void goBtnActionPerformed(java.awt.event.ActionEvent evt) {                                      
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        execute();
	selectUserType();
        setCursor(null); //turn off the wait cursor
    }                                     

    private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {                                          
       System.exit(0); //Close window-another method
    }                                         

    private void userNameTextActionPerformed(java.awt.event.ActionEvent evt) {                                             
    }                                            
    private void progressBarPropertyChange(java.beans.PropertyChangeEvent evt) {                                           
        
    }                                          

    private void formWindowOpened(java.awt.event.WindowEvent evt) {                                  
        this.setLocationRelativeTo(null);
    }                                 
    
    public static void main(String args[]) 
    {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            public void run() 
            {
                try
               {
                 Thread.sleep(1000);
               }
                catch(Exception ex)
               {
            
               }
                
               new LoginForm().setVisible(true);
            }
            
        });
        
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton cancelBtn;
    private javax.swing.JButton goBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPasswordField passwordText;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JTextField userNameText;
    private javax.swing.JComboBox<String> userType;
    // End of variables declaration                   
}