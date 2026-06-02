/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package project.MedicHistoryGUI;

import com.formdev.flatlaf.FlatIntelliJLaf;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.security.Timestamp;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import project.database.DatabaseConnection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.UUID;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author vaibh
 */
public class MedRequest extends javax.swing.JFrame {

    /**
     * Creates new form MedRequest
     */
    String docID;
    String docName;
    String email;
    
    public MedRequest(String doctorID,String docName,String email) throws SQLException {
        this.docID=doctorID;
        this.docName=docName;
        this.email=email;
        initComponents();
        userid.setText("UniqueID:-"+email);
        docname.setText(this.docName);
        int num = 0;
      try (Connection connection = DatabaseConnection.getConnection()){
    Statement stmt = connection.createStatement();
    ResultSet rs;
    rs = stmt.executeQuery("select ca.doctorID, COUNT(ca.patientID) as num_patients FROM appointment ca join appointment_details a ON ca.appointmentID = a.appointmentID where ca.doctorID = '" + doctorID + "' AND a.is_confirmed = '0'AND a.is_completed='0'");
    
    // Check if the result set has any rows
    if (rs.next()) {
        // Retrieve the value of "num_patients" column
        num = rs.getInt("num_patients");
    } else {
        // Handle the case where no rows were returned
        // For example, set num to a default value or log a message
        num = 0; // Default value
        System.out.println("No rows returned from the query");
    }
} catch (SQLException ex) {
    Logger.getLogger(MedRequest.class.getName()).log(Level.SEVERE, null, ex);
}
       System.out.println(num);
            String name=null;
            String phoneno=null;
            String address=null;
//            Timestamp date=null;
             java.sql.Timestamp date;
            String appointmentID=null;
             try (Connection connection1 = DatabaseConnection.getConnection()){
             Statement stmt1 = connection1.createStatement();
             ResultSet rs1;
             rs1 = stmt1.executeQuery("select distinct(appointment.appointmentID),(patient.name),(patient.address),(patient.phonenumber),(appointment_details.appointment_date) from appointment join patient join doctor join appointment_details where appointment.doctorID= '"+ doctorID +"' and appointment.patientID=patient.patientID and appointment.appointmentID=appointment_details.appointmentID and appointment_details.is_confirmed= '0' and appointment_details.is_completed='0' order by appointment_details.appointment_date desc");
          
             //System.out.println(rs1.getString("name"));
             for(int i=0;i<num;i++){
                 System.out.println("vah");
                 if(rs1.next()){
                      name = rs1.getString("name");
        System.out.println("called");
        phoneno=rs1.getString("phonenumber");
        address=rs1.getString("address");
        appointmentID=rs1.getString("appointmentID");
        date = rs1.getTimestamp("appointment_date");
        System.out.println(appointmentID);
        dynamiccell(name,phoneno,address,appointmentID,date);
        //System.out.println("called");
                 }
                 else{
                     System.out.println("calledelse");
                 }
             }
            
            
}
             catch (SQLException ex) {
    Logger.getLogger(MedRequest.class.getName()).log(Level.SEVERE, null, ex);
}
        
    }
    
//    welcomeDoctorName.setText(this.name+"👋");
private void dynamiccell(String name,String phoneno,String address,String appointmentID,java.sql.Timestamp date)//for creating the dynamic cells having info about patients 
{   
    
    javax.swing.JPanel cell;
    cell = new javax.swing.JPanel();
//        javax.swing.JLabel celltxt;
//        celltxt=new javax.swing.JLabel();
//        celltxt.setText("HELLLO");
//        cell.setSize(new Dimension(30,100));
    javax.swing.JLabel j1 = new javax.swing.JLabel();
    javax.swing.JLabel j2 = new javax.swing.JLabel();
    javax.swing.JLabel j3 = new javax.swing.JLabel();
    javax.swing.JLabel j4 = new javax.swing.JLabel();
    javax.swing.JLabel j5 = new javax.swing.JLabel();
    javax.swing.JButton accept = new javax.swing.JButton();
    javax.swing.JButton decline = new javax.swing.JButton();
    javax.swing.JLabel name1 = new javax.swing.JLabel();
    javax.swing.JLabel address1 = new javax.swing.JLabel();
    javax.swing.JLabel phone = new javax.swing.JLabel();
    javax.swing.JLabel date1 = new javax.swing.JLabel();
    javax.swing.JLabel time1 = new javax.swing.JLabel();
    
    cell.setBackground(new java.awt.Color(102, 102, 242));
    cell.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

    j1.setFont(new java.awt.Font("Microsoft Tai Le", 1, 14)); // NOI18N
    j1.setForeground(new java.awt.Color(242, 242, 242));
    j1.setText("Name:");

    j2.setFont(new java.awt.Font("Microsoft Tai Le", 1, 12)); // NOI18N
    j2.setForeground(new java.awt.Color(242, 242, 242));
    j2.setText("Address:");

    j3.setFont(new java.awt.Font("Microsoft Tai Le", 1, 12)); // NOI18N
    j3.setForeground(new java.awt.Color(242, 242, 242));
    j3.setText("Phone No.:");
    j4.setFont(new java.awt.Font("Microsoft Tai Le", 1, 12)); // NOI18N
    j4.setForeground(new java.awt.Color(242, 242, 242));
    j4.setText("Date.:");
        j5.setFont(new java.awt.Font("Microsoft Tai Le", 1, 12)); // NOI18N
    j5.setForeground(new java.awt.Color(242, 242, 242));
    j5.setText("Time.:");
    accept.setFont(new java.awt.Font("Microsoft Tai Le", 1, 12)); // NOI18N
    accept.setForeground(new java.awt.Color(0, 204, 0));
    accept.setText("Accept");
   accept.addActionListener(new java.awt.event.ActionListener() {
       public void actionPerformed(java.awt.event.ActionEvent evt) {
           acceptActionPerformed(evt,appointmentID);
       }
   });

    decline.setFont(new java.awt.Font("Microsoft Tai Le", 1, 12)); // NOI18N
    decline.setForeground(new java.awt.Color(255, 0, 0));
    decline.setText("Decline");
    decline.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            declineActionPerformed(evt,appointmentID);
        }
    });

    name1.setFont(new java.awt.Font("Microsoft Tai Le", 1, 14)); // NOI18N
    name1.setForeground(new java.awt.Color(242, 242, 242));
    name1.setText(name);
//    java.awt.Dimension size = name1.getPreferredSize();
//size.width = 200; // Set the desired width here
//name1.setPreferredSize(size);

    address1.setFont(new java.awt.Font("Microsoft Tai Le", 1, 12)); // NOI18N
    address1.setForeground(new java.awt.Color(242, 242, 242));
    address1.setText(address);

    phone.setFont(new java.awt.Font("Microsoft Tai Le", 1, 12)); // NOI18N
    phone.setForeground(new java.awt.Color(242, 242, 242));
    phone.setText(phoneno);
   
    date1.setFont(new java.awt.Font("Microsoft Tai Le", 1, 12)); // NOI18N
    date1.setForeground(new java.awt.Color(242, 242, 242));
    date1.setText((date.toString().substring(0,10)));
     time1.setFont(new java.awt.Font("Microsoft Tai Le", 1, 12)); // NOI18N
    time1.setForeground(new java.awt.Color(242, 242, 242));
    time1.setText((date.toString().substring(11)));
    
    

    
    
//        AppointmentID.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
//        AppointmentID.setText(appointmentID);
    System.out.println(name);

    

   
    

    javax.swing.GroupLayout cellLayout = new javax.swing.GroupLayout(cell);
    cell.setLayout(cellLayout);

//    cellLayout.setHorizontalGroup(
//        cellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//        .addGroup(cellLayout.createSequentialGroup()
//            .addContainerGap()
//            .addGroup(cellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                .addGroup(cellLayout.createSequentialGroup()
//                    .addGroup(cellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                        .addGroup(cellLayout.createSequentialGroup()
//                            .addComponent(j2)
//                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
//                            .addComponent(address1, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
//                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//                            .addComponent(accept)
//                            .addGap(47, 47, 47))
//                        .addGroup(cellLayout.createSequentialGroup()
//                            .addComponent(j3)
//                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
//                            .addComponent(phone, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
//                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//                            .addComponent(decline)))
//                    .addGap(47, 47, 47))
//                .addGroup(cellLayout.createSequentialGroup()
//                    .addComponent(j1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
//                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
//                    .addComponent(name1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
//                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
//    );

//cellLayout.setHorizontalGroup(
//    cellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//        .addGroup(cellLayout.createSequentialGroup()
//            .addContainerGap()
//            .addGroup(cellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                .addGroup(cellLayout.createSequentialGroup()
//                    .addGroup(cellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                        .addGroup(cellLayout.createSequentialGroup()
//                            .addComponent(j2)
//                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
//                            .addComponent(address1, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE))
//                        .addGroup(cellLayout.createSequentialGroup()
//                            .addComponent(j3)
//                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
//                            .addComponent(phone, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
//                    .addGap(47, 47, 47))
//                .addGroup(cellLayout.createSequentialGroup()
//                    .addComponent(j1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
//                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
//                    .addComponent(name1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
//                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
//                .addGroup(cellLayout.createSequentialGroup()
//                    .addComponent(j4) // Add this line to include the j4 label
//                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
//                    .addComponent(date1) // Position date1 label after j4 label
//                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
//                .addGroup(cellLayout.createSequentialGroup()
//                    .addComponent(accept)
//                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
//                    .addComponent(decline)
//                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
//));
//cellLayout.setHorizontalGroup(
//    cellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//    .addGroup(cellLayout.createSequentialGroup()
//        .addContainerGap()
//        .addGroup(cellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//            .addGroup(cellLayout.createSequentialGroup()
//                .addGroup(cellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                    .addGroup(cellLayout.createSequentialGroup()
//                        .addComponent(j2)
//                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
//                        .addComponent(address1, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE))
//                    .addGroup(cellLayout.createSequentialGroup()
//                        .addComponent(j3)
//                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
//                        .addComponent(phone, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
//                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//                .addGroup(cellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
//                    .addComponent(accept)
//                    .addComponent(decline))
//                .addGap(47, 47, 47))
//            .addGroup(cellLayout.createSequentialGroup()
//                .addComponent(j1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
//                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
//                .addComponent(name1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
//                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
//);
//    cellLayout.setVerticalGroup(
//        cellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//        .addGroup(cellLayout.createSequentialGroup()
//            .addContainerGap()
//            .addGroup(cellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
//                .addGroup(cellLayout.createSequentialGroup()
//                    .addGroup(cellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
//                        .addComponent(j1)
//                        .addComponent(name1))
//                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
//                    .addGroup(cellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
//                        .addComponent(j2)
//                        .addComponent(address1)))
//                )
//            .addGroup(cellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                .addGroup(cellLayout.createSequentialGroup()
//                    .addGap(17, 17, 17)
//                    
//                .addGroup(cellLayout.createSequentialGroup()
//                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
//                    .addGroup(cellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
//                        .addComponent(j3)
//                        .addComponent(phone))
//                        .addComponent(accept)
//                .addComponent(decline))))
//            .addContainerGap(16, Short.MAX_VALUE))
//    );

cellLayout.setHorizontalGroup(
    cellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(cellLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(cellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(cellLayout.createSequentialGroup()
                    .addGroup(cellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(cellLayout.createSequentialGroup()
                            .addComponent(j2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(address1, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(cellLayout.createSequentialGroup()
                            .addComponent(j3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(phone, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(47, 47, 47))
                .addGroup(cellLayout.createSequentialGroup()
                    .addComponent(j1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(name1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(cellLayout.createSequentialGroup()
                    .addComponent(j4) // Add this line to include the j4 label
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(date1) // Position date1 label after j4 label
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(cellLayout.createSequentialGroup()
                    .addComponent(j5) // Add this line to include the j4 label
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(time1) // Position date1 label after j4 label
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(cellLayout.createSequentialGroup()
                    .addComponent(accept)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(decline)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
));
cellLayout.setVerticalGroup(
    cellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(cellLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(cellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(j1)
                .addComponent(name1))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(cellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(j2)
                .addComponent(address1))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(cellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(j3)
                .addComponent(phone))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(cellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(j4) // Add j4 label after phone label
                .addComponent(date1)) // Add date1 label after j4 label
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(cellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(j5) // Add j4 label after phone label
                .addComponent(time1)) // Add date1 label after j4 label
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(cellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(accept)
                .addComponent(decline))
            .addContainerGap(16, Short.MAX_VALUE))
);

//cellLayout.setVerticalGroup(
//    cellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//    .addGroup(cellLayout.createSequentialGroup()
//        .addContainerGap()
//        .addGroup(cellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
//            .addComponent(j1)
//            .addComponent(name1))
//        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
//        .addGroup(cellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
//            .addComponent(j2)
//            .addComponent(address1))
//        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
//        .addGroup(cellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
//            .addComponent(j3)
//            .addComponent(phone))
//        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
//        .addGroup(cellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
//            .addComponent(accept)
//            .addComponent(decline))
//        .addContainerGap(16, Short.MAX_VALUE))
//);
    jPanel1.add(cell);
    jPanel1.revalidate();
    jPanel1.repaint();  
}

private void declineActionPerformed(ActionEvent evt,String appointmentID) {
JButton deleteButton = (JButton) evt.getSource();
JPanel cellPanel = (JPanel) deleteButton.getParent();
jPanel1.remove(cellPanel);
 // Call method to delete patient from database passing appointmentID
deletePatientFromDatabase(appointmentID);
deletePatientdetails(appointmentID);
jPanel1.revalidate();
jPanel1.repaint();
}
private void acceptActionPerformed(ActionEvent evt,String appointmentID) {
    JButton deleteButton = (JButton) evt.getSource();
    JPanel cellPanel = (JPanel) deleteButton.getParent();
    jPanel1.remove(cellPanel);
     // Call method to delete patient from database passing appointmentID
    //deletePatientFromDatabase(appointmentID);
    setIsConfirmedTrue(appointmentID);
    
    jPanel1.revalidate();
    jPanel1.repaint();
}
private void deletePatientFromDatabase(String appointmentID) {
System.out.println(appointmentID);

try (Connection connection = DatabaseConnection.getConnection()) {
    String sql = "DELETE FROM appointment WHERE appointmentID = ?";
    try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
        preparedStatement.setString(1, appointmentID);
        int rowsAffected = preparedStatement.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Patient removed from current appointment.");
        } else {
            System.out.println("Patient not found in current appointment.");
        }
    } catch (SQLException ex) {
        Logger.getLogger(MedRequest.class.getName()).log(Level.SEVERE, null, ex);
    }
} catch (SQLException ex) {
    Logger.getLogger(MedRequest.class.getName()).log(Level.SEVERE, null, ex);
}
}
private void deletePatientdetails(String appointmentID) {
System.out.println(appointmentID);

try (Connection connection = DatabaseConnection.getConnection()) {
    String sql = "DELETE FROM appointment WHERE appointmentID = ?";
    try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
        preparedStatement.setString(1, appointmentID);
        int rowsAffected = preparedStatement.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Patient removed from appointment.");
        } else {
            System.out.println("Patient not found in appointment.");
        }
    } catch (SQLException ex) {
        Logger.getLogger(MedRequest.class.getName()).log(Level.SEVERE, null, ex);
    }
} catch (SQLException ex) {
    Logger.getLogger(MedRequest.class.getName()).log(Level.SEVERE, null, ex);
}
}

 public void setIsConfirmedTrue(String appointmentID) {
        String sql = "UPDATE appointment_details SET is_confirmed = 1 WHERE appointmentID = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, appointmentID);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Appointment confirmed.");
            } else {
                System.out.println("Appointment not found.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MedRequest.class.getName()).log(Level.SEVERE, null, ex);
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

        jPanel5 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        docname = new javax.swing.JLabel();
        home = new javax.swing.JButton();
        patientlist = new javax.swing.JButton();
        medreq = new javax.swing.JButton();
        newpres = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        userid = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));

        jLabel9.setFont(new java.awt.Font("Helvetica Neue", 1, 36)); // NOI18N
        jLabel9.setText("Medic History");

        docname.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        docname.setText("DoctorName");

        home.setBackground(new java.awt.Color(102, 102, 255));
        home.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        home.setForeground(new java.awt.Color(255, 255, 255));
        home.setText("Home");
        home.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeActionPerformed(evt);
            }
        });

        patientlist.setBackground(new java.awt.Color(102, 102, 255));
        patientlist.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        patientlist.setForeground(new java.awt.Color(255, 255, 255));
        patientlist.setText("Appointment List");
        patientlist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                patientlistActionPerformed(evt);
            }
        });

        medreq.setBackground(new java.awt.Color(102, 102, 255));
        medreq.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        medreq.setForeground(new java.awt.Color(255, 255, 255));
        medreq.setText("Med Request");
        medreq.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                medreqActionPerformed(evt);
            }
        });

        newpres.setBackground(new java.awt.Color(102, 102, 255));
        newpres.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        newpres.setForeground(new java.awt.Color(255, 255, 255));
        newpres.setText("Create New Prescription");
        newpres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newpresActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(98, 98, 98)
                .addComponent(docname)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(medreq, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(newpres, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(patientlist, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(home, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(35, 35, 35))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addGap(92, 92, 92)
                .addComponent(home, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(medreq, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(patientlist, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(newpres, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 241, Short.MAX_VALUE)
                .addComponent(docname)
                .addGap(28, 28, 28))
        );

        jPanel1.setLayout(new java.awt.GridLayout(3, 3, 10, 10));

        jLabel1.setFont(new java.awt.Font("Microsoft Tai Le", 1, 36)); // NOI18N
        jLabel1.setText("Appointment Requests");

        userid.setFont(new java.awt.Font("Microsoft Tai Le", 1, 14)); // NOI18N
        userid.setText("User id:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 858, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 507, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(userid, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(userid))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void homeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeActionPerformed
        // TODO add your handling code here:
                DoctorPanelUI homePanel = new DoctorPanelUI(docID);

        // Show the patient panel GUI
//        System.out.println(doctorID);
        homePanel.setVisible(true);
        System.out.println("hello3");
        // Dispose or hide the login GUI
        this.dispose();
    }//GEN-LAST:event_homeActionPerformed

    private void patientlistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_patientlistActionPerformed
        // TODO add your handling code here:
//        System.out.println("hello");
//
        try {
            doctorpatientlistui(docID);
        } catch (SQLException ex) {
            Logger.getLogger(DoctorPanelUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_patientlistActionPerformed
    private void doctorpatientlistui(String doctorID) throws SQLException {
        // Create an instance of the patient panel GUI
        Docpatientlist patientlistPanel = new Docpatientlist(docID,docName,email);

        // Show the patient panel GUI
        System.out.println(doctorID);
        patientlistPanel.setVisible(true);
System.out.println("hello3");
        // Dispose or hide the login GUI
        this.dispose();
    }
    private void medreqActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_medreqActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_medreqActionPerformed

    private void newpresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newpresActionPerformed
        // TODO add your handling code here:
                                DoctorRecieptUI docrec=new DoctorRecieptUI(docID,docName,email);
        docrec.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_newpresActionPerformed

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
            java.util.logging.Logger.getLogger(MedRequest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MedRequest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MedRequest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MedRequest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
//        MedRequest obj=new MedRequest()
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                new MedRequest(docID,docName).setVisible(true);
            }
        });}
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel docname;
    private javax.swing.JButton home;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JButton medreq;
    private javax.swing.JButton newpres;
    private javax.swing.JButton patientlist;
    private javax.swing.JLabel userid;
    // End of variables declaration//GEN-END:variables
}
