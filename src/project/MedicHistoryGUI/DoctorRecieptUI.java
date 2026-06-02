/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package project.MedicHistoryGUI;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.io.File;
import project.database.DatabaseConnection;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;

/**
 *
 * @author harshit_nagpal
 */
public class DoctorRecieptUI extends javax.swing.JFrame {

    /**
     * Creates new form DoctorRecieptUI
     */
    private String docID;
    private String docName;
    private String email;
    private String AppID;
    private List<List<String>> prescriptionList = new ArrayList<>();

    public DoctorRecieptUI(String docID, String docName, String email) {

        this.docID = docID;
        this.docName = docName;
        this.email = email;

        initComponents();
        docname.setText(this.docName);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String formattedDateTime = now.format(formatter);
        datepresField.setText(formattedDateTime);
        followupField.setText(formattedDateTime);
    }

    private void createNewMedicinePanel(String drug_id, String drug_name, String manufacturer, String dosage_form, String strength, String indication, String contraindications, String side_effects, String dosage_instructions, String storage_instructions, String precautions, String warnings) {

        javax.swing.JPanel jPanel5;
        javax.swing.JLabel jLabel14 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel15 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel16 = new javax.swing.JLabel();
        javax.swing.JButton jButton5 = new javax.swing.JButton();

        jPanel5 = new javax.swing.JPanel();
        jPanel5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 255), 1, true));

        jLabel14.setBackground(new java.awt.Color(105, 105, 255));
        jLabel14.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(105, 105, 255));
        jLabel14.setText(drug_name);

        jButton5.setBackground(new java.awt.Color(105, 105, 255));
        jButton5.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("+");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }

            private void jButton5ActionPerformed(ActionEvent evt) {
                createPrescriptionPanel(drug_id, drug_name, manufacturer, dosage_form, strength, indication, contraindications, side_effects, dosage_instructions, storage_instructions, precautions, warnings);
                List<String> prescription = new ArrayList<>();
                prescription.add(drug_id);
                prescription.add(drug_name);
                prescription.add(manufacturer);
                prescription.add(dosage_form);
                prescription.add(strength);
                prescription.add(indication);
                prescription.add(contraindications);
                prescription.add(side_effects);
                prescription.add(dosage_instructions);
                prescription.add(storage_instructions);
                prescription.add(precautions);
                prescription.add(warnings);
                prescriptionList.add(prescription);
            }
        });

        jLabel15.setBackground(new java.awt.Color(105, 105, 255));
        jLabel15.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(105, 105, 255));
        jLabel15.setText(dosage_form);

        jLabel16.setBackground(new java.awt.Color(105, 105, 255));
        jLabel16.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(105, 105, 255));
        jLabel16.setText(indication);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
                jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                                .addGap(0, 0, 0)
                                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel5Layout.setVerticalGroup(
                jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(0, 0, 0)
                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                                .addContainerGap())
        );

        searchResultsPanel.add(jPanel5);
        searchResultsPanel.revalidate();
        searchResultsPanel.repaint();

    }

    private void createPrescriptionPanel(String drug_id, String drug_name, String manufacturer, String dosage_form, String strength, String indication, String contraindications, String side_effects, String dosage_instructions, String storage_instructions, String precautions, String warnings) {

        javax.swing.JPanel PrescriptionCell = new javax.swing.JPanel();
        javax.swing.JPanel jPanel8 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel17 = new javax.swing.JLabel();
        javax.swing.JButton jButton5 = new javax.swing.JButton();
        javax.swing.JLabel jLabel_1 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel_2 = new javax.swing.JLabel();

        PrescriptionCell.setLayout(new java.awt.GridLayout(1, 3, 10, 0));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(104, 104, 255)));

        jLabel17.setBackground(new java.awt.Color(104, 104, 255));
        jLabel17.setFont(new java.awt.Font("Helvetica Neue", 1, 10)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(104, 104, 255));
        jLabel17.setText(drug_name);

        jButton5.setBackground(new java.awt.Color(104, 104, 255));
        jButton5.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("i");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
                jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel8Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 141, Short.MAX_VALUE)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
                jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                                .addComponent(jButton5))
        );

        PrescriptionCell.add(jPanel8);

        jLabel_2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jLabel_2.setText(dosage_form);
        PrescriptionCell.add(jLabel_2);

        jLabel_1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jLabel_1.setText(dosage_instructions);
        PrescriptionCell.add(jLabel_1);

        PrescriptionTable.add(PrescriptionCell);
        PrescriptionTable.revalidate();
        PrescriptionTable.repaint();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        docname = new javax.swing.JLabel();
        home = new javax.swing.JButton();
        medreq = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        appointmentlist = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        AppointmentField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        nameField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        datepresField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        ageField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        addField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        heightField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        weightField = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        genderField = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        followupField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        diagnosisField = new javax.swing.JTextArea();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        PrescriptionTable = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        searchPresField = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        searchResultsPanel = new javax.swing.JPanel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));

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

        medreq.setBackground(new java.awt.Color(102, 102, 255));
        medreq.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        medreq.setForeground(new java.awt.Color(255, 255, 255));
        medreq.setText("MedRequest");
        medreq.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                medreqActionPerformed(evt);
            }
        });

        jButton8.setBackground(new java.awt.Color(102, 102, 255));
        jButton8.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jButton8.setForeground(new java.awt.Color(255, 255, 255));
        jButton8.setText("Create New Pres. Reciept");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        appointmentlist.setBackground(new java.awt.Color(102, 102, 255));
        appointmentlist.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        appointmentlist.setForeground(new java.awt.Color(255, 255, 255));
        appointmentlist.setText("Appointment List");
        appointmentlist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                appointmentlistActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(98, 98, 98)
                .addComponent(docname)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(medreq, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(home, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(appointmentlist, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(35, 35, 35))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel9)
                .addGap(68, 68, 68)
                .addComponent(home, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(medreq, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(appointmentlist, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(docname)
                .addGap(28, 28, 28))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel1.setText("Appointment ID");

        AppointmentField.setText("326aab41-bc65-4ae6-8656-2bc170928695");
        AppointmentField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AppointmentFieldActionPerformed(evt);
            }
        });

        jLabel2.setText("Name");

        nameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameFieldActionPerformed(evt);
            }
        });

        jLabel3.setText("Date of prescription");

        datepresField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                datepresFieldActionPerformed(evt);
            }
        });

        jLabel4.setText("Age");

        ageField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ageFieldActionPerformed(evt);
            }
        });

        jLabel5.setText("Address");

        addField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addFieldActionPerformed(evt);
            }
        });

        jLabel6.setText("Height (in cm)");

        heightField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                heightFieldActionPerformed(evt);
            }
        });

        jLabel7.setText("Weight (in kg)");

        weightField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                weightFieldActionPerformed(evt);
            }
        });

        jLabel8.setText("Gender");

        genderField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                genderFieldActionPerformed(evt);
            }
        });

        jLabel10.setText("Follow Up");

        followupField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                followupFieldActionPerformed(evt);
            }
        });

        diagnosisField.setColumns(20);
        diagnosisField.setRows(5);
        diagnosisField.setText("Start your Diagnosis reciept here...");
        jScrollPane1.setViewportView(diagnosisField);

        jLabel11.setText("Prescription");

        jLabel13.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
        jLabel13.setText("Create New Prescription Recipt");

        jButton2.setBackground(new java.awt.Color(104, 104, 255));
        jButton2.setFont(new java.awt.Font("Helvetica Neue", 1, 15)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Enter");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(102, 102, 255));
        jButton3.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Save");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jButton4.setForeground(new java.awt.Color(102, 102, 255));
        jButton4.setText("Print");
        jButton4.setToolTipText("");
        jButton4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 255), 1, true));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        PrescriptionTable.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        PrescriptionTable.setLayout(new java.awt.GridLayout(8, 1, 0, 5));

        jPanel5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanel5.setLayout(new java.awt.GridLayout(1, 3));

        jLabel15.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Medicine Name");
        jPanel5.add(jLabel15);

        jLabel14.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Dosage");
        jPanel5.add(jLabel14);

        jLabel16.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Duration");
        jPanel5.add(jLabel16);

        PrescriptionTable.add(jPanel5);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel8)
                                            .addComponent(genderField, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel7)
                                            .addComponent(weightField, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel6)
                                            .addComponent(heightField, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3)
                                            .addComponent(datepresField, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel10)
                                            .addComponent(followupField, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2)
                                            .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel4)
                                            .addComponent(ageField, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5)
                                            .addComponent(addField, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(20, 20, 20))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 661, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(AppointmentField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 644, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(PrescriptionTable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jScrollPane1)))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel13)
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ageField, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(AppointmentField, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addField, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(genderField, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(weightField, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(heightField, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(datepresField, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(followupField, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PrescriptionTable, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel12.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Search Prescription");

        searchPresField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchPresFieldActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(104, 104, 255));
        jButton1.setFont(new java.awt.Font("Helvetica Neue", 1, 15)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("OK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        searchResultsPanel.setLayout(new java.awt.GridLayout(10, 1, 2000, 10));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(searchPresField, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(searchResultsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(searchPresField))
                .addGap(27, 27, 27)
                .addComponent(searchResultsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 538, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(105, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(55, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

    private void medreqActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_medreqActionPerformed
        // TODO add your handling code here:
        try {
            MedRequest medreq = new MedRequest(docID, docName, email);
            medreq.setVisible(true);
            this.dispose();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_medreqActionPerformed

    private void searchPresFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchPresFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchPresFieldActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String searchText = searchPresField.getText().trim();

        searchResultsPanel.removeAll();
        searchResultsPanel.revalidate();
        searchResultsPanel.repaint();

        if (!searchText.isEmpty()) {
            String query = "SELECT `medical_drugs`.`drug_id`, "
                    + "`medical_drugs`.`drug_name`, "
                    + "`medical_drugs`.`manufacturer`, "
                    + "`medical_drugs`.`dosage_form`, "
                    + "`medical_drugs`.`strength`, "
                    + "`medical_drugs`.`indication`, "
                    + "`medical_drugs`.`contraindications`, "
                    + "`medical_drugs`.`side_effects`, "
                    + "`medical_drugs`.`dosage_instructions`, "
                    + "`medical_drugs`.`storage_instructions`, "
                    + "`medical_drugs`.`precautions`, "
                    + "`medical_drugs`.`warnings` "
                    + "FROM `medic_history`.`medical_drugs` "
                    + "WHERE `drug_name` LIKE ? OR `indication` LIKE ?";

            try (Connection connection = DatabaseConnection.getConnection()) {
                PreparedStatement stmt = connection.prepareStatement(query);
                stmt.setString(1, "%" + searchText + "%");
                stmt.setString(2, "%" + searchText + "%");
                ResultSet rs = stmt.executeQuery();

                List<String> resultList = new ArrayList<>();
                while (rs.next()) {

                    createNewMedicinePanel(
                            rs.getString("drug_id"),
                            rs.getString("drug_name"),
                            rs.getString("manufacturer"),
                            rs.getString("dosage_form"),
                            rs.getString("strength"),
                            rs.getString("indication"),
                            rs.getString("contraindications"),
                            rs.getString("side_effects"),
                            rs.getString("dosage_instructions"),
                            rs.getString("storage_instructions"),
                            rs.getString("precautions"),
                            rs.getString("warnings")
                    );

                    StringBuilder sb = new StringBuilder();
                    sb.append("drug_id: ").append(rs.getString("drug_id")).append(", ");
                    sb.append("drug_name: ").append(rs.getString("drug_name")).append(", ");
                    sb.append("manufacturer: ").append(rs.getString("manufacturer")).append(", ");
                    sb.append("dosage_form: ").append(rs.getString("dosage_form")).append(", ");
                    sb.append("strength: ").append(rs.getString("strength")).append(", ");
                    sb.append("indication: ").append(rs.getString("indication")).append(", ");
                    sb.append("contraindications: ").append(rs.getString("contraindications")).append(", ");
                    sb.append("side_effects: ").append(rs.getString("side_effects")).append(", ");
                    sb.append("dosage_instructions: ").append(rs.getString("dosage_instructions")).append(", ");
                    sb.append("storage_instructions: ").append(rs.getString("storage_instructions")).append(", ");
                    sb.append("precautions: ").append(rs.getString("precautions")).append(", ");
                    sb.append("warnings: ").append(rs.getString("warnings"));
                    resultList.add(sb.toString());
                }

                // Print the result
                for (String result : resultList) {
                    System.out.println(result);
                }
                // Process the result set
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private String createNewPDF() {
        String fname = null;
        try {

            PDDocument document = new PDDocument();

            // Create a new page in the document
            PDPage page = new PDPage();
            document.addPage(page);

            // Create a content stream for the new page
            PDPageContentStream contentStream = null;
            try {
                contentStream = new PDPageContentStream(document, page);
            } catch (IOException ex) {
                Logger.getLogger(DoctorRecieptUI.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                // Define the font and font size
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 12);
            } catch (IOException ex) {
                Logger.getLogger(DoctorRecieptUI.class.getName()).log(Level.SEVERE, null, ex);
            }

            // Set the starting position for the text
            float y = 750;
            float leading = 15;

            String appID = AppID;
            String nameField_ = nameField.getText();
            String ageField_ = ageField.getText();
            String addField_ = addField.getText();
            String genderField_ = genderField.getText();
            String heightField_ = heightField.getText();
            String weightField_ = weightField.getText();
            String datepresField_ = datepresField.getText();
            String diagnosisField_ = diagnosisField.getText();

            contentStream.beginText();
            contentStream.newLineAtOffset(50, 700);
            contentStream.showText("Appointment ID: " + appID);

            contentStream.newLineAtOffset(0, -15);
            contentStream.showText("Name: " + nameField_);

            contentStream.newLineAtOffset(0, -15);
            contentStream.showText("Age: " + ageField_);

            contentStream.newLineAtOffset(0, -15);
            contentStream.showText("Address: " + addField_);

            contentStream.newLineAtOffset(0, -15);
            contentStream.showText("Gender: " + genderField_);

            contentStream.newLineAtOffset(0, -15);
            contentStream.showText("Height: " + heightField_);

            contentStream.newLineAtOffset(0, -15);
            contentStream.showText("Weight: " + weightField_);

            contentStream.newLineAtOffset(0, -15);
            contentStream.showText("Date of Prescription: " + datepresField_);

            contentStream.newLineAtOffset(0, -30);
            contentStream.showText("Diagnosis: ");
            contentStream.newLineAtOffset(0, -15);
            contentStream.showText(diagnosisField_.replace("\n", "").replace("\r", ""));

            contentStream.endText();

            y -= leading * 15;

            // Add a table for prescriptions
            contentStream.beginText();
            contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 12);
            contentStream.newLineAtOffset(50, y);
            contentStream.showText("Prescriptions:");
            contentStream.endText();

            y -= leading;

// Create table header
            float[] columnWidths = {200, 100, 200};
            float startX = 50;
            float startY = y;

// Draw table header
            contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 10);
            contentStream.beginText();
            contentStream.newLineAtOffset(startX, startY);
            contentStream.showText("Medicine Name");
            contentStream.newLineAtOffset(columnWidths[0], 0);
            contentStream.showText("Dosage");
            contentStream.newLineAtOffset(columnWidths[1], 0);
            contentStream.showText("Dosage Instructions");
            contentStream.endText();

            y -= leading;

// Draw table rows
            contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 10);
            for (List<String> prescription : prescriptionList) {
                contentStream.beginText();
                contentStream.newLineAtOffset(startX, y);
                contentStream.showText(prescription.get(1)); // Medicine Name
                contentStream.newLineAtOffset(columnWidths[0], 0);
                contentStream.showText(prescription.get(3)); // Dosage
                contentStream.newLineAtOffset(columnWidths[1], 0);
                contentStream.showText(prescription.get(8)); // Dosage Instructions
                contentStream.endText();
                y -= leading;
            }

            y -= leading;
            contentStream.close();
            fname = "MedicalRecipts/" + appID + ".pdf";
            document.save(fname);

            JOptionPane.showMessageDialog(null, "Medical Reciept PDF generated and stored successfuly");

        } catch (IOException ex) {
            Logger.getLogger(DoctorRecieptUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fname;

    }

    private void openPDFFile(String filePath) {
        try {
            File file = new File(filePath);
            if (!Desktop.isDesktopSupported()) {
                System.out.println("Desktop is not supported");
                return;
            }

            Desktop desktop = Desktop.getDesktop();
            if (file.exists()) {
                desktop.open(file);
            } else {
                System.out.println("File not found: " + filePath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        openPDFFile("./" + createNewPDF());
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // left the case where no App ID is given
        String appID = AppID;
        String nameField_ = nameField.getText();
        String ageField_ = ageField.getText();
        String addField_ = addField.getText();
        String genderField_ = genderField.getText();
        String heightField_ = heightField.getText();
        String weightField_ = weightField.getText();
        String datepresField_ = datepresField.getText();
        String diagnosisField_ = diagnosisField.getText();

        try (Connection connection = DatabaseConnection.getConnection()) {
            String drugs_sql = "INSERT INTO medicaldrug_appointmet (appointmentID, drug_id) VALUES (?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(drugs_sql)) {
                for (List<String> prescription : prescriptionList) {
                    String drugID = prescription.get(0); // Assuming drug_id is at index 0 in each prescription list
                    statement.setString(1, appID);
                    statement.setString(2, drugID);
                    statement.addBatch(); // Add the statement to the batch
                }
                statement.executeBatch(); // Execute the batch of insert statements
            } catch (SQLException e) {
                e.printStackTrace();
            }

            // SQL queries for updating the database tables
            String sql = "SELECT patientID FROM appointment WHERE appointmentID = ?";
            try (PreparedStatement statement2 = connection.prepareStatement(sql)) {
                // Set the parameter
                statement2.setString(1, appID);
                // Execute the query
                try (ResultSet resultSet = statement2.executeQuery()) {
                    // Check if a result is returned
                    if (resultSet.next()) {
                        // Retrieve the patientID
                        String patientID = resultSet.getString("patientID");
                        String sql2 = "{CALL create_medical_receipt(?, ? ,?, ?, ?, ?, ?, ?, ?, ?,?)}";
                        try (CallableStatement statement = connection.prepareCall(sql2)) {
                            // Set the parameters
                            statement.setString(1, appID);
                            statement.setString(2, patientID);
                            statement.setString(3, nameField_);
                            statement.setInt(4, Integer.parseInt(ageField_));
                            statement.setString(5, addField_);
                            statement.setString(6, genderField_);
                            statement.setDouble(7, Double.parseDouble(heightField_));
                            statement.setDouble(8, Double.parseDouble(weightField_));
                            statement.setString(9, datepresField_);
                            statement.setString(10, diagnosisField_);
                            statement.setString(11, createNewPDF());

                            // Execute the stored procedure
                            statement.execute();

                            JOptionPane.showMessageDialog(this, "Medical receipt saved successfully to the database!");
                        }

                    }
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(DoctorRecieptUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton3ActionPerformed
    public void copyaction(String appointmentID)
    {
        AppointmentField.setText(appointmentID);
        AppID=appointmentID;
                try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT p.`name`, p.`age`, p.`address`, p.`gender`, p.`height`, p.`weight` "
                    + "FROM `medic_history`.`Patient` p "
                    + "JOIN `medic_history`.`appointment` ca ON p.`patientID` = ca.`patientID` "
                    + "WHERE ca.`appointmentID` = ?";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, AppID);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                nameField.setText(resultSet.getString("name"));
                ageField.setText(String.valueOf(resultSet.getInt("age")));
                addField.setText(resultSet.getString("address"));
                genderField.setText(resultSet.getString("gender"));
                heightField.setText(String.valueOf(resultSet.getDouble("height")));
                weightField.setText(String.valueOf(resultSet.getDouble("weight")));
            } else {
                // lite
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        AppID = AppointmentField.getText();
        
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT p.`name`, p.`age`, p.`address`, p.`gender`, p.`height`, p.`weight` "
                    + "FROM `medic_history`.`Patient` p "
                    + "JOIN `medic_history`.`appointment` ca ON p.`patientID` = ca.`patientID` "
                    + "WHERE ca.`appointmentID` = ?";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, AppID);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                nameField.setText(resultSet.getString("name"));
                ageField.setText(String.valueOf(resultSet.getInt("age")));
                addField.setText(resultSet.getString("address"));
                genderField.setText(resultSet.getString("gender"));
                heightField.setText(String.valueOf(resultSet.getDouble("height")));
                weightField.setText(String.valueOf(resultSet.getDouble("weight")));
            } else {
                // lite
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void followupFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_followupFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_followupFieldActionPerformed

    private void genderFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genderFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_genderFieldActionPerformed

    private void weightFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_weightFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_weightFieldActionPerformed

    private void heightFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_heightFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_heightFieldActionPerformed

    private void addFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addFieldActionPerformed

    private void ageFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ageFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ageFieldActionPerformed

    private void datepresFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_datepresFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_datepresFieldActionPerformed

    private void nameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameFieldActionPerformed

    private void AppointmentFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AppointmentFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AppointmentFieldActionPerformed

    private void appointmentlistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_appointmentlistActionPerformed
        Docpatientlist patientlistPanel;
        try {
            patientlistPanel = new Docpatientlist(docID, docName, email);
            patientlistPanel.setVisible(true);
            this.dispose();
        } catch (SQLException ex) {
            Logger.getLogger(DoctorRecieptUI.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Show the patient panel GUI
        // Dispose or hide the login GUI

    }//GEN-LAST:event_appointmentlistActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField AppointmentField;
    private javax.swing.JPanel PrescriptionTable;
    private javax.swing.JTextField addField;
    private javax.swing.JTextField ageField;
    private javax.swing.JButton appointmentlist;
    private javax.swing.JTextField datepresField;
    private javax.swing.JTextArea diagnosisField;
    private javax.swing.JLabel docname;
    private javax.swing.JTextField followupField;
    private javax.swing.JTextField genderField;
    private javax.swing.JTextField heightField;
    private javax.swing.JButton home;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton medreq;
    private javax.swing.JTextField nameField;
    private javax.swing.JTextField searchPresField;
    private javax.swing.JPanel searchResultsPanel;
    private javax.swing.JTextField weightField;
    // End of variables declaration//GEN-END:variables
}
