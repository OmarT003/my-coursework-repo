
/**
 * ImageViewer is the main class of the RecruitmentSystem application. It builds 
 * and displays the application GUI. [Taken template from ppt slides lecture 16]
 * @author Omar Tamimi
 * @version 25.2.2025
 */
//Importing GUI
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

/**
 * 
 * To start the application, create an object of this class and add fields.
 */

public class RecruitmentSystemGUI implements ActionListener
{
    private JFrame frame;
    private JPanel fieldPanel;
    private JTextField vacancyNumberField, designationField, jobTypeField, staffNameField,
    joiningDateField, qualificationField, appointedByField, salaryField,
    weeklyHoursField, workingHoursField, wagesPerHourField, shiftsField, displayNumberField;
    //Add buttons feature
    private JButton addFullTimeButton, addPartTimeButton, displayButton, clearButton, setSalaryButton, setShiftButton, terminateButton, displayNumberButton;
    private JTextArea displayArea;
    //List storing full/part time staff records
    private ArrayList<StaffHire> staffList;
    /**
     * Create an ImageViewer and show it on screen.
     */
    public RecruitmentSystemGUI()
    {
        staffList = new ArrayList<>();
        makeFrame();
    }

    @Override
    public void actionPerformed(ActionEvent event) 
    {
        String command = event.getActionCommand();
        if(command.equals("Add Full Time Staff")){
            addFullTimeStaff();
        }
        else if(command.equals("Add Part Time Staff")){
            addPartTimeStaff();
        }
        else if(command.equals("Display")){
            displayAllStaff();
        }
        else if (command.equals("Clear")){
            clearFields();
        }
        else if(command.equals("Set Salary")){
            setFullTimeSalary();
        }
        else if (command.equals("Set Working Shift")){
            setWorkingShift();
        }
        else if (command.equals("Terminate")) {
            terminatePartTimeStaff();
        }
        else if (command.equals("Display Number")){
            displayStaffNumber();
        }
    }

    //Method to add full time staff to the list, try method handles errors by turning crashes into messages
    private void addFullTimeStaff() {
        try {
            // Input validation: ensure no empty fields
            if (vacancyNumberField.getText().isEmpty() || designationField.getText().isEmpty() ||
            jobTypeField.getText().isEmpty() || staffNameField.getText().isEmpty() ||
            joiningDateField.getText().isEmpty() || qualificationField.getText().isEmpty() ||
            appointedByField.getText().isEmpty() || salaryField.getText().isEmpty() ||
            weeklyHoursField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please fill in all the fields before submitting.");
                return;
            }

            int vacancyNumber = Integer.parseInt(vacancyNumberField.getText());
            String designation = designationField.getText();
            String jobType = jobTypeField.getText();
            String staffName = staffNameField.getText();
            String joiningDate = joiningDateField.getText();
            String qualification = qualificationField.getText();
            String appointedBy = appointedByField.getText();
            double salary = Double.parseDouble(salaryField.getText());
            int weeklyHours = Integer.parseInt(weeklyHoursField.getText());

            // Creating and adding full-time staff to the list, true indicates staff has joined
            FullTimeStaffHire fullTimeStaff = new FullTimeStaffHire(vacancyNumber, designation, jobType, staffName,
                    joiningDate, qualification, appointedBy, true, salary, weeklyHours);
            staffList.add(fullTimeStaff);
            JOptionPane.showMessageDialog(frame, "Full time staff have been added successfully!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Please add whole numbers for Vacancy Number, Salary, and Weekly Hours.");
        }
    }

    // Method to add part time staff to the list
    private void addPartTimeStaff() {
        try {
            // Input validation: ensure no empty fields
            if (vacancyNumberField.getText().isEmpty() || designationField.getText().isEmpty() ||
            jobTypeField.getText().isEmpty() || staffNameField.getText().isEmpty() ||
            joiningDateField.getText().isEmpty() || qualificationField.getText().isEmpty() ||
            appointedByField.getText().isEmpty() || workingHoursField.getText().isEmpty() ||
            wagesPerHourField.getText().isEmpty() || shiftsField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please fill in all the fields before submitting.");
                return;
            }

            int vacancyNumber = Integer.parseInt(vacancyNumberField.getText());
            String designation = designationField.getText();
            String jobType = jobTypeField.getText();
            String staffName = staffNameField.getText();
            String joiningDate = joiningDateField.getText();
            String qualification = qualificationField.getText();
            String appointedBy = appointedByField.getText();
            int workingHours = Integer.parseInt(workingHoursField.getText());
            double wagesPerHour = Double.parseDouble(wagesPerHourField.getText());
            String shifts = shiftsField.getText();

            // Creating and adding part-time staff to the list
            PartTimeStaffHire partTimeStaff = new PartTimeStaffHire(vacancyNumber, designation, jobType, staffName,
                    joiningDate, qualification, appointedBy, true, workingHours, wagesPerHour, shifts);
            staffList.add(partTimeStaff);
            JOptionPane.showMessageDialog(frame, "Part time staff added successfully!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Please enter valid numbers for vacancy number, wages per hour, and working hours.");
        }
    }

    //Phase 2: Set Salary for full time staff based on vacancy number and staff name
    private void setFullTimeSalary() {
        try {
            int vacancyNumber = Integer.parseInt(vacancyNumberField.getText());
            String staffName = staffNameField.getText();
            double salary = Double.parseDouble(salaryField.getText());
            //searches through staffList array
            for (StaffHire staff : staffList) {
                if (staff instanceof FullTimeStaffHire) {
                    //matches StaffHire to FullTimeStaffHire
                    FullTimeStaffHire fullTime = (FullTimeStaffHire) staff;
                    //prevents salary updates for unappointed staff.
                    if (fullTime.isJoined()) {
                        fullTime.setSalary(salary);
                        JOptionPane.showMessageDialog(frame, "Salary has been updated for " + staffName);
                        return;
                    } else {
                        JOptionPane.showMessageDialog(frame, "Staff is not appointed, cannot set salary.", "Error!", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                }
            }
            JOptionPane.showMessageDialog(null, "Staff vacancy number and name not found.", "Not Found", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter valid numbers for Vacancy Number and Salary.", "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }

    //Phase 2: Building the working shift feature
    private void setWorkingShift() {
        try {
            int vacancyNumber = Integer.parseInt(vacancyNumberField.getText());
            String staffName = staffNameField.getText();
            String shift = shiftsField.getText();

            for (StaffHire staff : staffList) {
                if (staff instanceof PartTimeStaffHire) {
                    PartTimeStaffHire partTime = (PartTimeStaffHire) staff;
                    //trim method removes any spaces from input to avoid mismatch.
                    if (partTime.getVacancyNumber() == vacancyNumber && partTime.getStaffName().equalsIgnoreCase(staffName.trim())) {
                        if (partTime.isJoined()) {
                            partTime.setShifts(shift);
                            JOptionPane.showMessageDialog(frame, "Updated shift to '" + shift + "' for " + staffName);
                        } else {
                            JOptionPane.showMessageDialog(frame, "Unappointed staff member, cannot change shift.", "Error!", JOptionPane.WARNING_MESSAGE);
                        }
                        return;
                    }
                }
            }
            JOptionPane.showMessageDialog(frame, "Staff vacancy number and name not found.", "Not Found", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Please enter a valid vacancy number.", "Number Format Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    //Phase 2: Building terminate part time staff feature.
    private void terminatePartTimeStaff() {
        try {
            //finds matching part  time staff member via vacancy number to terminate if terminate is equal to false.
            int vacancyNumber = Integer.parseInt(vacancyNumberField.getText());
            for (StaffHire staff : staffList) {
                if (staff instanceof PartTimeStaffHire) {
                    PartTimeStaffHire partTime = (PartTimeStaffHire) staff;
                    if (partTime.getVacancyNumber() == vacancyNumber) {
                        if (!partTime.isTerminated()) {
                            partTime.terminate();
                            JOptionPane.showMessageDialog(frame, "Part time staff has been terminated.");
                        } else {
                            JOptionPane.showMessageDialog(frame, "Staff is already terminated.", "Note", JOptionPane.INFORMATION_MESSAGE);
                        }
                        return;
                    }
                }
            }
            JOptionPane.showMessageDialog(frame, "No match for part time staff found with vacancy number.", "Not Found", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Please enter a valid vacancy number.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Method to display all staff details in the text area
    // '/n' adds a line between the staff for ease of readability
    private void displayAllStaff() {
        displayArea.setText("");
        if (staffList.isEmpty()) {
            displayArea.append("No staff records are available.\n");
        } else {
            for (StaffHire staff : staffList) {
                displayArea.append(staff.toString() + "\n-----------------------\n");
            }
        }
    }

    //Method to display staff member based on entered number in index
    private void displayStaffNumber() {
        try {
            int index = Integer.parseInt(displayNumberField.getText().trim());

            if (index >= 0 && index < staffList.size()) {
                StaffHire staff = staffList.get(index);
                //Clears previous display
                displayArea.setText(""); 
                displayArea.append(staff.toString());
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid number. Please enter a number between 0 and " + (staffList.size() - 1) + ".", "Error!", JOptionPane.WARNING_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Please enter a valid integer.", "Input Error!", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Method to clear all fields
    private void clearFields() {
        vacancyNumberField.setText("");
        designationField.setText("");
        jobTypeField.setText("");
        staffNameField.setText("");
        joiningDateField.setText("");
        qualificationField.setText("");
        appointedByField.setText("");
        salaryField.setText("");
        weeklyHoursField.setText("");
        workingHoursField.setText("");
        wagesPerHourField.setText("");
        shiftsField.setText("");
    }

    // Method to create the main GUI frame using Border and Grid Layouts
    // Referenced from Lecture 17: 'Building GUIs with Swing', page 5
    private void makeFrame() {
        frame = new JFrame("Recruitment System");
        frame.setSize(950, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        fieldPanel = new JPanel(new GridLayout(7, 4, 10, 10));

        // Create input labels and fields
        vacancyNumberField = new JTextField(10);
        designationField = new JTextField(10);
        jobTypeField = new JTextField(10);
        staffNameField = new JTextField(10);
        joiningDateField = new JTextField(10);
        qualificationField = new JTextField(10);
        appointedByField = new JTextField(10);
        salaryField = new JTextField(10);
        weeklyHoursField = new JTextField(10);
        workingHoursField = new JTextField(10);
        wagesPerHourField = new JTextField(10);
        shiftsField = new JTextField(10);
        displayNumberField = new JTextField(10);

        // Add all label field pairs for rows
        fieldPanel.add(new JLabel("Vacancy Number:"));
        fieldPanel.add(vacancyNumberField);
        fieldPanel.add(new JLabel("Designation:"));
        fieldPanel.add(designationField);

        fieldPanel.add(new JLabel("Job Type:"));
        fieldPanel.add(jobTypeField);
        fieldPanel.add(new JLabel("Staff Name:"));
        fieldPanel.add(staffNameField);

        fieldPanel.add(new JLabel("Joining Date:"));
        fieldPanel.add(joiningDateField);
        fieldPanel.add(new JLabel("Qualification:"));
        fieldPanel.add(qualificationField);

        fieldPanel.add(new JLabel("Appointed By:"));
        fieldPanel.add(appointedByField);
        fieldPanel.add(new JLabel("Salary:"));
        fieldPanel.add(salaryField);

        fieldPanel.add(new JLabel("Weekly Hours:"));
        fieldPanel.add(weeklyHoursField);
        fieldPanel.add(new JLabel("Working Hours:"));
        fieldPanel.add(workingHoursField);

        fieldPanel.add(new JLabel("Wages Per Hour:"));
        fieldPanel.add(wagesPerHourField);
        fieldPanel.add(new JLabel("Shifts:"));
        fieldPanel.add(shiftsField);

        fieldPanel.add(new JLabel("Display Number:"));
        fieldPanel.add(displayNumberField);

        // Add buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());
        addFullTimeButton = new JButton("Add Full Time Staff");
        addPartTimeButton = new JButton("Add Part Time Staff");
        displayButton = new JButton("Display");
        clearButton = new JButton("Clear");
        setSalaryButton = new JButton("Set Salary");
        setShiftButton = new JButton("Set Working Shift");
        terminateButton = new JButton("Terminate");
        displayNumberButton = new JButton("Display Number");

        addFullTimeButton.addActionListener(this);
        addPartTimeButton.addActionListener(this);
        displayButton.addActionListener(this);
        clearButton.addActionListener(this);
        setSalaryButton.addActionListener(this);
        setShiftButton.addActionListener(this);
        terminateButton.addActionListener(this);
        displayNumberButton.addActionListener(this);

        buttonPanel.add(addFullTimeButton);
        buttonPanel.add(addPartTimeButton);
        buttonPanel.add(displayButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(setSalaryButton);
        buttonPanel.add(setShiftButton);
        buttonPanel.add(terminateButton);
        buttonPanel.add(displayNumberButton);

        // Area for displaying staff information
        displayArea = new JTextArea(14, 70);
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);

        // Add panels to frame
        frame.add(fieldPanel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.add(scrollPane, BorderLayout.SOUTH);
        frame.setVisible(true);

        // Finalise frame setup
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    //To run program outside of BlueJ
    public static void main(String[] args) {
        new RecruitmentSystemGUI();
    }
}

