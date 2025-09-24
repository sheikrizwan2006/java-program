// File: HospitalSystem.java
class MedicalStaff {
    protected String name;

    public MedicalStaff(String name) {
        this.name = name;
    }

    public void commonDuties() {
        System.out.println(name + " has shift scheduling, ID access, and payroll processing.");
    }
}

class Doctor extends MedicalStaff {
    public Doctor(String name) { super(name); }
    public void diagnose() { System.out.println(name + " is diagnosing a patient."); }
    public void prescribe() { System.out.println(name + " is prescribing medicine."); }
}

class Nurse extends MedicalStaff {
    public Nurse(String name) { super(name); }
    public void assist() { System.out.println(name + " is assisting in procedures."); }
}

class Technician extends MedicalStaff {
    public Technician(String name) { super(name); }
    public void runTests() { System.out.println(name + " is running lab tests."); }
}

class Administrator extends MedicalStaff {
    public Administrator(String name) { super(name); }
    public void schedule() { System.out.println(name + " is scheduling appointments."); }
}

public class HospitalSystem {
    public static void main(String[] args) {
        MedicalStaff staff = new Doctor("Dr. Smith"); // Upcasting
        staff.commonDuties();

        // ❌ staff.diagnose(); // Not allowed (only Doctor knows this)
        // Need downcast to access
    }
}
