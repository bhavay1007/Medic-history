package project.MedicHistoryGUI;
import javax.swing.*;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.*;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.Dimension;
import java.awt.BorderLayout;
//import java.awt.Box;
import javax.swing.Box;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;

import java.awt.GridLayout;
import java.awt.Color;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import project.database.DatabaseConnection;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.JScrollPane;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
//import javax.awt.Border.layout;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author HP
 */
public class time extends javax.swing.JFrame {
    static class Pair<K, V, M , N, O, P,Q,R,S> {
        private K key;
        private V aid;
        private M did;
        private N dgns;
        private O pdf;
        private P drn;
        private Q apn;
        private R cnf;
        private S cmp;

        public Pair(K key,V aid,M did,N dgns,O pdf,P drn,Q apn,R cnf,S cmp) {
            this.key = key;
            this.aid = aid;
            this.did = did;
            this.dgns =dgns;
            this.pdf = pdf;
            this.drn = drn;
            this.apn= apn;
            this.cnf=cnf;
            this.cmp=cmp;
        }

        public K getKey() {
            return key;
        }

        public V getaid() {
            return aid;
        }
        
        public M getdid() {
            return did;
        }
        
        public N getdgns() {
            return dgns;
        }
        
        public O getpdf() {
            return pdf;
        }
        
        public P getdrn() {
            return drn;
        }
        
        public Q getapn() {
            return apn;
        }
        
        public R getcnf() {
            return cnf;
        }
        
        public S getcmp(){
            return cmp;
        }
        
    }
static class TimestampDataComparator implements Comparator<Pair<String, String,String, String,String, String,String,Integer,Integer>> {
    @Override
    public int compare(Pair<String, String,String, String,String, String,String,Integer,Integer> pair1, Pair<String, String,String, String,String, String,String,Integer,Integer> pair2) {
        System.out.println("Comparing pair1: " + pair1 + " with pair2: " + pair2);
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime1 = LocalDateTime.parse(pair1.getKey(), formatter);
        LocalDateTime dateTime2 = LocalDateTime.parse(pair2.getKey(), formatter);
        
        System.out.println("Parsed dateTime1: " + dateTime1 + ", Parsed dateTime2: " + dateTime2);
        
        int comparisonResult = dateTime1.compareTo(dateTime2);
        System.out.println("Comparison result: " + comparisonResult);
        
        return comparisonResult*-1;
    }
}
    
    
    
    
    
      javax.swing.JDialog dialog;
      ArrayList<String> appointmentIds = new ArrayList<>();
      ArrayList<String> appointmentName = new ArrayList<>();
                ArrayList<String> doctorIds=new ArrayList<>();
                ArrayList<String> date=new ArrayList<>();
                ArrayList<String> diagnosis=new ArrayList<>();
                ArrayList<String> pdfurl=new ArrayList<>();
                ArrayList<String> drname=new ArrayList<>();
                ArrayList<Integer> confirm=new ArrayList<>();
                ArrayList<Integer> completed=new ArrayList<>();
//                 private static final Color[] CELL_COLORS = {Color.BLUE, Color.GREEN, Color.ORANGE, Color.YELLOW}; 
                ArrayList<Pair<String, String,String, String,String, String,String,Integer,Integer>> timestampDataPairs = new ArrayList<>();
//   Creates new form timelineGUI
                 String patientID;
    public time(String patientID)  {
        this.patientID=patientID;
       
         
    /**
     * Creates new form timelineGUI
     */
  
        
        try (Connection connection = DatabaseConnection.getConnection()) {
//            System.out.println("Getting Connection");
        Statement statement = connection.createStatement();
        
            // Execute the query
            String query = "SELECT appointmentID,doctorID FROM appointment WHERE patientID = '" + patientID+"'";
            
            ResultSet resultSet = statement.executeQuery(query);

            // Process the ResultSet and populate the ArrayList
            while (resultSet.next()) {
                String appointmentId = resultSet.getString("appointmentID");
                        String doctorId = resultSet.getString("doctorID");
                appointmentIds.add(appointmentId);
    
                doctorIds.add(doctorId);
            }
            
           
        
        }
            catch (SQLException ex) {
            System.out.println(ex);
        }
        
        setResizable(false); 
        initComponents();
        //print in j label
        try (Connection connection = DatabaseConnection.getConnection()) {
            
             Statement statement = connection.createStatement();

            // Execute the query to fetch data
            

//            initComponents();
            // Process the ResultSet and build a string representation of the data
            int i=0;
             for (String element : appointmentIds) {
//                 System.out.println(element);
       String query = "SELECT * FROM appointment_details where appointmentID= '"+element+"'"; // Modify query according to your table
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                // Assuming your table has columns named 'column1', 'column2', etc.
                String column1Value = resultSet.getString("appointment_date");
                String column2Value = resultSet.getString("diagnosis");
                String column3Value = resultSet.getString("pdf_url");
                String column4Value=resultSet.getString("appointmentName");
                int confirmation=Integer.parseInt(resultSet.getString("is_confirmed"));
                int complete=Integer.parseInt(resultSet.getString("is_completed"));
                date.add(column1Value);
                diagnosis.add(column2Value);
                pdfurl.add(column3Value);
                appointmentName.add(column4Value);
                confirm.add(confirmation);
                completed.add(complete);
            }++i;}
////             
            
           
            String nameq="Select name from patient where patientID= '"+patientID+"'";
            ResultSet resultSet = statement.executeQuery(nameq);
            while (resultSet.next()) {
               
            String patientName = resultSet.getString("name");
            PatientNameF.setText(patientName);
        
            }
         for(String element : doctorIds){
         String query = "SELECT name FROM doctor where doctorID= '"+element+"'"; // Modify query according to your table
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                
                drname.add(resultSet.getString("name"));
            }
    }
//   
for (int j=0;j<appointmentIds.size();j++) {
                 
                 timestampDataPairs.add(new Pair<>(date.get(j),appointmentIds.get(j),doctorIds.get(j),diagnosis.get(j),pdfurl.get(j),drname.get(j),appointmentName.get(j),confirm.get(j),completed.get(j)));
             }
             System.out.println("Size of timestampDataPairs: " + timestampDataPairs.size());
             Collections.sort(timestampDataPairs, new TimestampDataComparator());
             int j=0;
             for (Pair<String, String,String, String,String, String,String,Integer,Integer> pair : timestampDataPairs) {
            appointmentIds.set(j,pair.getaid());
            date.set(j,pair.getKey());
            doctorIds.set(j,pair.getdid());
            diagnosis.set(j,pair.getdgns());
            pdfurl.set(j,pair.getpdf());
            drname.set(j,pair.getdrn());
            appointmentName.set(j,pair.getapn());
            confirm.set(j,pair.getcnf() );
            completed.set(j,pair.getcmp());
            ++j;
        }
       generateDynamicLabels(appointmentIds.size());
    } catch (SQLException ex) {
            System.out.println(ex);
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

        jPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        PatientNameF = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));

        jLabel9.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
        jLabel9.setText("Medic History");

        PatientNameF.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        PatientNameF.setText("PatientName");

        jButton6.setBackground(new java.awt.Color(102, 102, 255));
        jButton6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("HOME");
        jButton6.setToolTipText("");
        jButton6.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton9.setBackground(new java.awt.Color(102, 102, 255));
        jButton9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.setText("DOCTORS CONSULTED");
        jButton9.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Timeline");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(8, 8, 8)
                                        .addComponent(PatientNameF, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 9, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(PatientNameF)
                .addGap(19, 19, 19))
        );

        jPanel3.setLayout(new java.awt.GridLayout(1, 0));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 749, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                .addGap(328, 328, 328))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
     private void showPatientPan(String patientID) throws SQLException{
        // Create an instance of the patient panel GUI
        System.out.println("Hello");
         try {
        PatientPanelUI patientPanel = new PatientPanelUI(patientID);
        patientPanel.setVisible(true);
        this.dispose(); // or this.setVisible(false);
    } catch (Exception e) {
        e.printStackTrace();
    }

//         Show the patient panel GUI
System.out.println("Hello");
        // Dispose or hide the login GUI
        this.dispose(); // or this.setVisible(false);
    }
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
try {
            showPatientPan(patientID);
        } catch (SQLException ex) {
            Logger.getLogger(time.class.getName()).log(Level.SEVERE, null, ex);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed
private void showdocList(String patientID) throws SQLException{
        // Create an instance of the patient panel GUI
        doctorlist docpanel = new doctorlist(patientID);

        // Show the patient panel GUI
//        System.out.println("hello3");
        docpanel.setVisible(true);
//System.out.println("hello4");
        // Dispose or hide the login GUI
        this.dispose(); // or this.setVisible(false);
    }
    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
try {
            showdocList(patientID);
        } catch (SQLException ex) {
            Logger.getLogger(PatientPanelUI.class.getName()).log(Level.SEVERE, null, ex);
        }         // TODO add your handling code here:
    }//GEN-LAST:event_jButton9ActionPerformed


  private void generateDynamicLabels(int numberOfLabels) {
        // Remove existing components
        jPanel3.removeAll();
        jPanel3.setLayout(new GridLayout(0, 3, 10, 10)); // 3 columns, 10px horizontal and vertical gaps

        for (int i = 0; i < numberOfLabels; i++) {
            // Create the main panel
            JPanel cell = new JPanel();
            cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));
       cell.setBackground(new Color(102, 102, 242));
//ign unique color to each cell
            cell.setLayout(new GridLayout(0, 1)); // Layout for vertical alignment

            // Labels for patient information
            JLabel PatientNameField = new JLabel();
            JLabel PatientAddressField = new JLabel();
            JLabel PatientPhoneNo = new JLabel();
            JLabel Time = new JLabel();
            // Set text and styles
            String docname = drname.get(i);
            String diag = diagnosis.get(i);
            String pdf = pdfurl.get(i);
            String appID=appointmentName.get(i);
            int cnfm=confirm.get(i);
            int comp=completed.get(i);
            String dates = date.get(i).substring(0, 10);
            String time=date.get(i).substring(10);
            PatientNameField.setFont(new Font("Segoe UI", Font.BOLD, 18));
            PatientNameField.setForeground(Color.WHITE);
            PatientNameField.setText(appID);
            
            PatientAddressField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            PatientAddressField.setForeground(Color.WHITE);
            PatientAddressField.setText("Date: " + dates);

            Time.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            Time.setForeground(Color.WHITE);
            Time.setText("Time: " + time);
            
            PatientPhoneNo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            PatientPhoneNo.setForeground(Color.WHITE);
            PatientPhoneNo.setText("Details");

            // Button for showing details
            JButton detailsButton = new JButton();
            detailsButton.setText("Open Details");
         detailsButton.setSize(20, 20);
            detailsButton.setFont(new Font("Segoe UI", Font.PLAIN, 12)); // Smaller font size
            detailsButton.addActionListener(evt -> {
                // Action to perform when button is clicked
                // Open detailed panel
                
                showDetailsPanel(docname,diag,pdf,cnfm,comp);
            });

            // Add components to the cell panel
            cell.add(PatientNameField);
            cell.add(PatientAddressField);
            cell.add(Time);
            cell.add(detailsButton);
            

            // Add space between cells
            cell.add(Box.createVerticalStrut(10)); // Adjust the spacing as needed

            // Add the cell panel to the main panel
            jPanel3.add(cell);
        }

        // Refresh the layout
        jPanel3.revalidate();
        jPanel3.repaint();
    }
    
    


   private void showDetailsPanel(String doctorName, String diagnosis, String pdf,int cnfm,int comp) {
    // Create a new frame for the details panel
    JFrame detailsFrame = new JFrame();
    detailsFrame.setTitle("Details for Treatment");
    detailsFrame.setSize(400, 300); // Adjust size as needed
    detailsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close only this frame on exit

    // Create a panel for details using BorderLayout
    JPanel detailsPanel = new JPanel(new BorderLayout());
    detailsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding
    detailsPanel.setBackground(Color.LIGHT_GRAY); // Unique color for details panel
    detailsFrame.add(detailsPanel);

    // Create a panel for labels using GridLayout
    JPanel labelsPanel = new JPanel(new GridLayout(3, 1, 0, 10)); // 3 rows, 1 column, 10px vertical gap
    labelsPanel.setBackground(Color.LIGHT_GRAY); // Match background color
    detailsPanel.add(labelsPanel, BorderLayout.NORTH); // Add labelsPanel to detailsPanel at the top
     
    JLabel cnfLabel1 = new JLabel("<html><div style='font-size: 14px;'><b>Status: </b>" + "Future Appointment"+ "</div></html>");
    JLabel cnfLabel2 = new JLabel("<html><div style='font-size: 14px;'><b>Status: </b>" + "Confirmation Awaited by Doctor"+ "</div></html>");
     JLabel cnfLabel3 = new JLabel("<html><div style='font-size: 14px;'><b>Status: </b>" + "Past Appointment"+ "</div></html>");
    // Add doctor name label
    
    JLabel doctorNameLabel = new JLabel("<html><div style='font-size: 14px;'><b>Doctor Name: </b>" + doctorName + "</div></html>");
    labelsPanel.add(doctorNameLabel);
if(cnfm==1){
    if(comp==1){
labelsPanel.setLayout(new BoxLayout(labelsPanel, BoxLayout.Y_AXIS));

// Add description label
JLabel descriptionLabel = new JLabel("<html><div style='font-size: 14px;'><b>Diagnosis: </b>" + diagnosis + "</div></html>");
labelsPanel.add(descriptionLabel);

// Add prescription label
//JLabel prescriptionLabel = new JLabel("<html><div style='font-size: 14px;'><b>Prescription: </b>" + prescription + "</div></html>");
//labelsPanel.add(prescriptionLabel);

// Add cnfLabel1 (assuming it's another JLabel)
labelsPanel.add(cnfLabel3);
      JButton prescriptionButton = new JButton("Prescription");
            prescriptionButton.setBackground(new java.awt.Color(102, 102, 255)); // Set background color
            prescriptionButton.setForeground(Color.WHITE);
            prescriptionButton.setHorizontalAlignment(SwingConstants.CENTER); // Center align the text
            prescriptionButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Open PDF file
                    try {
                        Desktop.getDesktop().open(new File(pdf));
                    } catch (Exception ex) {
                        ex.printStackTrace(); // Handle exception appropriately
                    }
                }
            });
            labelsPanel.add(prescriptionButton);
}
    else{
     labelsPanel.setLayout(new BoxLayout(labelsPanel, BoxLayout.Y_AXIS));

// Add description label
//JLabel descriptionLabel = new JLabel("<html><div style='font-size: 14px;'><b>Diagnosis: </b>" + diagnosis + "</div></html>");
//labelsPanel.add(descriptionLabel);

// Add prescription label
//JLabel prescriptionLabel = new JLabel("<html><div style='font-size: 14px;'><b>Prescription: </b>" + prescription + "</div></html>");
//labelsPanel.add(prescriptionLabel);

// Add cnfLabel1 (assuming it's another JLabel)
labelsPanel.add(cnfLabel1);   
    }

// Add the labelsPanel to your container panel (e.g., jPanel1)

}
else{
    labelsPanel.add(cnfLabel2);
}
    // Create an OK button
    JButton okButton = new JButton("OK");
    okButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            detailsFrame.dispose(); // Close the dialog when OK button is clicked
        }
    });

    // Add OK button to details panel
    detailsPanel.add(okButton, BorderLayout.SOUTH);

    detailsFrame.setLocationRelativeTo(null);
    detailsFrame.setVisible(true);
}
// Method to create and show the pop-out panel




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
            java.util.logging.Logger.getLogger(time.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(time.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(time.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(time.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
//generateDynamicButtons(3);
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                new time("124").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel PatientNameF;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables
}
