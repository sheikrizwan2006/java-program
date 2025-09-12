import java.io.Serializable;
import java.lang.reflect.Method;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;
import java.util.stream.Collectors;

public class EmployeeBean implements Serializable {
    private static final long serialVersionUID = 1L;

    // Private fields following JavaBean conventions
    private String employeeId;
    private String firstName;
    private String lastName;
    private double salary;
    private String department;
    private Date hireDate;
    private boolean isActive;

    // Default no-argument constructor
    public EmployeeBean() {}

    // Parameterized constructor
    public EmployeeBean(String employeeId, String firstName, String lastName, double salary,
                        String department, Date hireDate, boolean isActive) {
        setEmployeeId(employeeId);
        setFirstName(firstName);
        setLastName(lastName);
        setSalary(salary);
        this.department = department;
        this.hireDate = hireDate;
        this.isActive = isActive;
    }

    // Getters
    public String getEmployeeId() { return employeeId; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public double getSalary() { return salary; }
    public String getDepartment() { return department; }
    public Date getHireDate() { return hireDate; }
    public boolean isActive() { return isActive; }

    // Setters with validation
    public void setEmployeeId(String employeeId) {
        if (employeeId == null || employeeId.trim().isEmpty()) 
            throw new IllegalArgumentException("Employee ID cannot be null or empty.");
        this.employeeId = employeeId;
    }

    public void setFirstName(String firstName) {
        if (firstName == null || firstName.trim().isEmpty()) 
            throw new IllegalArgumentException("First name cannot be null or empty.");
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        if (lastName == null || lastName.trim().isEmpty()) 
            throw new IllegalArgumentException("Last name cannot be null or empty.");
        this.lastName = lastName;
    }

    public void setSalary(double salary) {
        if (salary < 0) 
            throw new IllegalArgumentException("Salary must be positive.");
        this.salary = salary;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public void setActive(boolean active) {
        this.isActive = active;
    }

    // Computed properties

    public String getFullName() {
        return (firstName != null ? firstName : "") + " " + (lastName != null ? lastName : "");
    }

    public int getYearsOfService() {
        if (hireDate == null) return 0;
        LocalDate hire = hireDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate now = LocalDate.now();
        if (hire.isAfter(now)) return 0;
        return (int) ChronoUnit.YEARS.between(hire, now);
    }

    public String getFormattedSalary() {
        NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);
        return formatter.format(salary);
    }

    // Derived property setter
    public void setFullName(String fullName) {
        if (fullName == null || fullName.trim().isEmpty()) {
            this.firstName = "";
            this.lastName = "";
            return;
        }
        String[] parts = fullName.trim().split("\\s+", 2);
        this.firstName = parts[0];
        this.lastName = (parts.length > 1) ? parts[1] : "";
    }

    @Override
    public String toString() {
        return "EmployeeBean{" +
                "employeeId='" + employeeId + '\'' +
                ", fullName='" + getFullName() + '\'' +
                ", salary=" + getFormattedSalary() +
                ", department='" + department + '\'' +
                ", hireDate=" + hireDate +
                ", yearsOfService=" + getYearsOfService() +
                ", isActive=" + isActive +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof EmployeeBean)) return false;
        EmployeeBean other = (EmployeeBean) obj;
        return employeeId != null && employeeId.equals(other.employeeId);
    }

    @Override
    public int hashCode() {
        return employeeId == null ? 0 : employeeId.hashCode();
    }

    public static void main(String[] args) throws Exception {
        // Create EmployeeBean using default constructor + setters
        EmployeeBean emp1 = new EmployeeBean();
        emp1.setEmployeeId("E001");
        emp1.setFullName("John Doe");
        emp1.setSalary(75000);
        emp1.setDepartment("Finance");
        emp1.setHireDate(new Date(118, 0, 15)); // Jan 15, 2018 (year - 1900)
        emp1.setActive(true);

        // Create EmployeeBean using parameterized constructor
        EmployeeBean emp2 = new EmployeeBean("E002", "Jane", "Smith", 82000, "Marketing",
                new Date(115, 5, 1), true);

        // Demonstrate getters and computed properties
        System.out.println(emp1);
        System.out.println(emp2);
        System.out.println("emp2 fullName: " + emp2.getFullName());
        System.out.println("emp2 yearsOfService: " + emp2.getYearsOfService());
        System.out.println("emp2 formattedSalary: " + emp2.getFormattedSalary());

        // Test validation in setter methods
        try {
            emp1.setSalary(-5000);
        } catch (IllegalArgumentException e) {
            System.out.println("Validation error caught: " + e.getMessage());
        }

        try {
            emp1.setEmployeeId("");
        } catch (IllegalArgumentException e) {
            System.out.println("Validation error caught: " + e.getMessage());
        }

        // Create an array of EmployeeBeans and demonstrate sorting/filtering
        EmployeeBean[] employees = {
                emp1,
                emp2,
                new EmployeeBean("E003", "Alice", "Brown", 90000, "IT", new Date(120, 2, 1), false),
                new EmployeeBean("E004", "Bob", "White", 65000, "Finance", new Date(110, 10, 15), true)
        };

        System.out.println("\nOriginal array:");
        for (EmployeeBean e : employees) {
            System.out.println(e);
        }

        // Sort by salary descending
        Arrays.sort(employees, Comparator.comparingDouble(EmployeeBean::getSalary).reversed());
        System.out.println("\nSorted by salary descending:");
        for (EmployeeBean e : employees) {
            System.out.println(e.getEmployeeId() + ": " + e.getFormattedSalary());
        }

        // Filter active employees
        EmployeeBean[] activeEmployees = Arrays.stream(employees)
                .filter(EmployeeBean::isActive)
                .toArray(EmployeeBean[]::new);
        System.out.println("\nActive employees:");
        for (EmployeeBean e : activeEmployees) {
            System.out.println(e.getEmployeeId() + ": " + e.getFullName());
        }

        // Use JavaBeanProcessor utility class
        System.out.println("\nProperties of emp2:");
        JavaBeanProcessor.printAllProperties(emp2);

        // Copy properties
        EmployeeBean empCopy = new EmployeeBean();
        JavaBeanProcessor.copyProperties(emp1, empCopy);
        System.out.println("\nCopied employee:");
        System.out.println(empCopy);
    }
}

// Utility class for JavaBean introspection and copying
class JavaBeanProcessor {

    // Print all JavaBean properties using reflection
    public static void printAllProperties(EmployeeBean emp) {
        if (emp == null) {
            System.out.println("EmployeeBean is null");
            return;
        }
        Method[] methods = EmployeeBean.class.getMethods();

        for (Method method : methods) {
            String name = method.getName();
            if (((name.startsWith("get") && method.getParameterCount() == 0 && !name.equals("getClass"))) ||
                (name.startsWith("is") && method.getParameterCount() == 0 && method.getReturnType() == boolean.class)) {
                try {
                    Object value = method.invoke(emp);
                    String propName = name.startsWith("get") ? name.substring(3) : name.substring(2);
                    propName = propName.substring(0,1).toLowerCase() + propName.substring(1);
                    System.out.printf("%s: %s%n", propName, value);
                } catch (Exception e) {
                    System.out.println("Error invoking " + name);
                }
            }
        }
    }

    // Copy all JavaBean properties from source to target using reflection
    public static void copyProperties(EmployeeBean source, EmployeeBean target) {
        if (source == null || target == null) return;

        Method[] methods = EmployeeBean.class.getMethods();

        for (Method getter : methods) {
            String name = getter.getName();
            if ((name.startsWith("get") || name.startsWith("is")) &&
                getter.getParameterCount() == 0 &&
                !name.equals("getClass")) {

                String baseName = name.startsWith("get") ? name.substring(3) : name.substring(2);
                try {
                    Method setter = EmployeeBean.class.getMethod("set" + baseName, getter.getReturnType());
                    Object value = getter.invoke(source);
                    setter.invoke(target, value);
                } catch (NoSuchMethodException e) {
                    // Setter not found, skip
                } catch (Exception e) {
                    System.out.println("Error copying property " + baseName + ": " + e.getMessage());
                }
            }
        }
    }
}