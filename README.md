# Digital Medical Receipt Management System

The Digital Medical Receipt Management System is a Java application built using Java Swing and Ant in NetBeans. It provides a secure and efficient way to store and manage medical receipts for both doctors and patients.

## Features

- **Secure Storage**: Medical receipts are stored securely, ensuring patient privacy and data protection.
- **Efficient Documentation**: Doctors can easily track and access patient histories, streamlining healthcare documentation.
- **Patient Access**: Patients can view their visit records across different physicians, empowering them with control over their medical information.
- **User-friendly Interface**: The application features an intuitive Java Swing-based graphical user interface (GUI) for seamless navigation and interaction.

## Prerequisites

- Java Development Kit (JDK) installed on your system
- Apache Ant (for building the project)
- NetBeans IDE (optional, but recommended for easy project setup and development)

## Setting up the Project

1. Clone the project repository or download the source code.
2. Open NetBeans IDE and select "Open Project" from the File menu.
3. Navigate to the project directory and select the project folder.
4. NetBeans will automatically detect and configure the project settings.
5. Run the SQL file `create_schema.sql` to create the schema `medic_history`:
6. To change the MySQL password in DatabaseConnection.java, locate the file, update the password parameter, and restart the application.

## Building the Project

1. In NetBeans, open the project and locate the "Build" option in the toolbar or the "Build" menu.
2. Select "Clean and Build Project" to compile the source code and generate the executable JAR file.

## Running the Application

1. After the successful build, locate the generated JAR file in the `dist` folder within the project directory.
2. Open a terminal or command prompt and navigate to the `dist` folder.
3. Run the following command to execute the JAR file:

```
java -jar "MedicHistory.jar" 

```
To distribute this project, zip up the dist folder (including the lib folder)
and distribute the ZIP file.

## Usage

Upon running the application, you will be presented with the main window of the Digital Medical Receipt Management System. Follow the on-screen instructions to navigate through the various functionalities, such as adding new medical receipts, viewing existing records, and managing user accounts.

## Contributing

Contributions to this project are done by
2021B5A72792P   Harshit Nagpal
2021B3A70870P	Aditya Agarwal
2021B3A72786P	Vaibhav Gupta
2021B4A71306P	Saransh Agarwal
2021B4A72759P	Mayur Agarwal

## Acknowledgments

We would like to express our gratitude to Dr Yashvardhan Sharma and Saurav SS for their continued support and contribution.

