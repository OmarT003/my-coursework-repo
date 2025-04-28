
/**
 * @author Omar Tamimi
 * @version 24/2/2025
 */
public class FullTimeStaffHire extends StaffHire
{
    // attributes for the subclass FullTimeStaffHire
    private double salary;
    private int weeklyFractionalHours;

    /**
     * Constructor for objects of class FullTimeStaffHire, initialising full time staff
     * details including salary and weekly fractional hours.
     */
    public FullTimeStaffHire(int vacancyNumber, String designation, String jobType, String staffName,
    String joiningDate, String qualification, String appointedBy, boolean joined,
    double salary, int weeklyFractionalHours)
    {
        // call the superclass constructor 
        super(vacancyNumber, designation, jobType, staffName, joiningDate, qualification, appointedBy, joined);
        this.salary = salary;
        this.weeklyFractionalHours = weeklyFractionalHours;
    }

    /**
     * New attributes have a corresponding accessor method
     *
     */
    //Attribute 1- returns staff salary
    //Read only access setter method 1
    public double getSalary()
    {
        return salary;
    }

    //If statement to return appointed staff salary if appointed, if not then a suitable message will appear to the user.
    public void setSalary(double salary){
        if (isJoined()) {
            this.salary = salary;
        }
        else {
            System.out.println("There is no staff appointed to set the salary");
        }
    }

    //Attribute 2- returns number of weekly fractional hours
    //Read only access setter method 2
    public int getWeeklyFractionalHours(){
        return weeklyFractionalHours;
    }
    //Updates weekly fractional hours.
    public void setWeeklyFractionalHours(int weeklyFractionalHours){
        weeklyFractionalHours = weeklyFractionalHours;
    }
    //Override method to display FullTimeStaff details.
    @Override
    public void display() {
        //super method calls the display method from StaffHire
        super.display(); 
        System.out.println("Salary is " + salary);
        System.out.println("Weekly Fractional Hours: " + weeklyFractionalHours);
    }
    //Returns a string containing full time hired staff details for GUI display.
    @Override
    public String toString() {
        return super.toString() +
        "\nSalary: " + salary +
        "\nWeekly Hours: " + weeklyFractionalHours +
        //Added to show whether staff is appointed or not.
        "\nStatus: " + (isJoined() ? "Appointed" : "Not Appointed");
    }
}
