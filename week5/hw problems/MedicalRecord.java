import java.time.LocalDate;
import java.util.*;

// a. Immutable MedicalRecord class
public final class MedicalRecord {
    private final String recordId;
    private final String patientDNA;
    private final String[] allergies;
    private final String[] medicalHistory;
    private final LocalDate birthDate;
    private final String bloodType;

    // HIPAA compliance validation example (simple check)
    public MedicalRecord(String recordId, String patientDNA, String[] allergies, String[] medicalHistory,
                         LocalDate birthDate, String bloodType) {
        if (recordId == null || recordId.isBlank())
            throw new IllegalArgumentException("Record ID cannot be null or blank");
        if (patientDNA == null || patientDNA.isBlank())
            throw new IllegalArgumentException("Patient DNA cannot be null or blank");
        if (birthDate == null || birthDate.isAfter(LocalDate.now()))
            throw new IllegalArgumentException("Invalid birth date");
        if (bloodType == null || bloodType.isBlank())
            throw new IllegalArgumentException("Blood type cannot be null or blank");

        this.recordId = recordId;
        this.patientDNA = patientDNA;
        this.birthDate = birthDate;
        this.bloodType = bloodType;

        // Defensive copying arrays, null becomes empty arrays
        this.allergies = allergies == null ? new String[0] : Arrays.copyOf(allergies, allergies.length);
        this.medicalHistory = medicalHistory == null ? new String[0] : Arrays.copyOf(medicalHistory, medicalHistory.length);
    }

    // Getters return defensive copies for arrays
    public String getRecordId() { return recordId; }
    public String getPatientDNA() { return patientDNA; }
    public LocalDate getBirthDate() { return birthDate; }
    public String getBloodType() { return bloodType; }

    public String[] getAllergies() { return Arrays.copyOf(allergies, allergies.length); }
    public String[] getMedicalHistory() { return Arrays.copyOf(medicalHistory, medicalHistory.length); }

    // Cannot be overridden for safety
    public final boolean isAllergicTo(String substance) {
        if (substance == null || substance.isBlank()) return false;
        for (String allergy : allergies) {
            if (substance.equalsIgnoreCase(allergy)) return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "MedicalRecord{" +
                "recordId='" + recordId + '\'' +
                ", birthDate=" + birthDate +
                ", bloodType='" + bloodType + '\'' +
                ", allergies=" + Arrays.toString(allergies) +
                ", medicalHistory=" + Arrays.toString(medicalHistory) +
                '}';
    }
}


// b. Patient class with privacy levels
public class Patient {
    private final String patientId;
    private final MedicalRecord medicalRecord;

    private String currentName;
    private String emergencyContact;
    private String insuranceInfo;

    private int roomNumber;
    private String attendingPhysician;

    // Package-private method for staff access to basic info
    String getBasicInfo() {
        return "PatientID: " + patientId + ", Name: " + currentName + ", EmergencyContact: " + emergencyContact;
    }

    // Public method returns non-sensitive public info only
    public String getPublicInfo() {
        return "Name: " + currentName + ", Room: " + roomNumber;
    }

    // Constructor chaining with privacy validation
    // Emergency admission (minimal data, temporary ID)
    public Patient(String tempName, String emergencyContact) {
        this(UUID.randomUUID().toString(), 
            new MedicalRecord(UUID.randomUUID().toString(), "UNKNOWN_DNA", null, null, LocalDate.now().minusYears(30), "Unknown"),
            tempName, emergencyContact, "None", -1, "Unknown");
    }

    // Standard admission (full info)
    public Patient(String patientId, MedicalRecord medicalRecord, String currentName, String emergencyContact,
                   String insuranceInfo, int roomNumber, String attendingPhysician) {
        if (patientId == null || patientId.isBlank()) throw new IllegalArgumentException("patientId required");
        if (medicalRecord == null) throw new IllegalArgumentException("medicalRecord required");
        if (currentName == null || currentName.isBlank()) throw new IllegalArgumentException("currentName required");
        if (roomNumber < 0) roomNumber = -1; // unassigned room

        this.patientId = patientId;
        this.medicalRecord = medicalRecord;
        this.currentName = currentName;
        this.emergencyContact = emergencyContact;
        this.insuranceInfo = insuranceInfo;
        this.roomNumber = roomNumber;
        this.attendingPhysician = attendingPhysician;
    }

    // Transfer admission (imports existing medical record)
    public Patient(String patientId, MedicalRecord medicalRecord, String currentName, int roomNumber) {
        this(patientId, medicalRecord, currentName, null, null, roomNumber, "Unknown");
    }

    // Getters and validated setters for modifiable info

    public String getPatientId() { return patientId; }
    public MedicalRecord getMedicalRecord() { return medicalRecord; }

    public String getCurrentName() { return currentName; }
    public void setCurrentName(String currentName) {
        if (currentName == null || currentName.isBlank()) throw new IllegalArgumentException("Name cannot be empty");
        this.currentName = currentName;
    }

    public String getEmergencyContact() { return emergencyContact; }
    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public String getInsuranceInfo() { return insuranceInfo; }
    public void setInsuranceInfo(String insuranceInfo) {
        this.insuranceInfo = insuranceInfo;
    }

    public int getRoomNumber() { return roomNumber; }
    public void setRoomNumber(int roomNumber) {
        if (roomNumber < 0) throw new IllegalArgumentException("Invalid room number");
        this.roomNumber = roomNumber;
    }

    public String getAttendingPhysician() { return attendingPhysician; }
    public void setAttendingPhysician(String attendingPhysician) {
        this.attendingPhysician = attendingPhysician;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "patientId='" + patientId + '\'' +
                ", currentName='" + currentName + '\'' +
                ", emergencyContact='" + emergencyContact + '\'' +
                ", insuranceInfo='" + insuranceInfo + '\'' +
                ", roomNumber=" + roomNumber +
                ", attendingPhysician='" + attendingPhysician + '\'' +
                ", medicalRecord=" + medicalRecord +
                '}';
    }
}


// d. Medical staff classes with access levels
class Doctor {
    private final String licenseNumber;
    private final String specialty;
    private final Set<String> certifications;

    public Doctor(String licenseNumber, String specialty, Set<String> certifications) {
        if (licenseNumber == null || licenseNumber.isBlank())
            throw new IllegalArgumentException("License number required");
        if (specialty == null || specialty.isBlank())
            throw new IllegalArgumentException("Specialty required");
        this.licenseNumber = licenseNumber;
        this.specialty = specialty;
        this.certifications = certifications == null ? new HashSet<>() : new HashSet<>(certifications);
    }

    public String getLicenseNumber() { return licenseNumber; }
    public String getSpecialty() { return specialty; }
    public Set<String> getCertifications() { return Collections.unmodifiableSet(certifications); }

    @Override
    public String toString() {
        return "Doctor{" +
                "licenseNumber='" + licenseNumber + '\'' +
                ", specialty='" + specialty + '\'' +
                ", certifications=" + certifications +
                '}';
    }
}

class Nurse {
    private final String nurseId;
    private final String shift;
    private final List<String> qualifications;

    public Nurse(String nurseId, String shift, List<String> qualifications) {
        if (nurseId == null || nurseId.isBlank())
            throw new IllegalArgumentException("Nurse ID required");
        if (shift == null || shift.isBlank())
            throw new IllegalArgumentException("Shift required");
        this.nurseId = nurseId;
        this.shift = shift;
        this.qualifications = qualifications == null ? new ArrayList<>() : new ArrayList<>(qualifications);
    }

    public String getNurseId() { return nurseId; }
    public String getShift() { return shift; }
    public List<String> getQualifications() { return Collections.unmodifiableList(qualifications); }

    @Override
    public String toString() {
        return "Nurse{" +
                "nurseId='" + nurseId + '\'' +
                ", shift='" + shift + '\'' +
                ", qualifications=" + qualifications +
                '}';
    }
}

class Administrator {
    private final String adminId;
    private final List<String> accessPermissions;

    public Administrator(String adminId, List<String> accessPermissions) {
        if (adminId == null || adminId.isBlank())
            throw new IllegalArgumentException("Admin ID required");
        this.adminId = adminId;
        this.accessPermissions = accessPermissions == null ? new ArrayList<>() : new ArrayList<>(accessPermissions);
    }

    public String getAdminId() { return adminId; }
    public List<String> getAccessPermissions() { return Collections.unmodifiableList(accessPermissions); }

    @Override
    public String toString() {
        return "Administrator{" +
                "adminId='" + adminId + '\'' +
                ", accessPermissions=" + accessPermissions +
                '}';
    }
}


// e. HospitalSystem class with access control
class HospitalSystem {

    // Static final constants for hospital policies and privacy rules
    static final int MAX_ROOM_NUMBER = 500;
    static final Set<String> ALLOWED_ACCESS_ROLES = Set.of("Doctor", "Nurse", "Administrator");

    private final Map<String, Patient> patientRegistry = new HashMap<>();

    // Admit patient method with staff validation
    public boolean admitPatient(Object patient, Object staff) {
        if (patient == null || staff == null) return false;
        if (!(patient instanceof Patient)) return false;

        if (!validateStaffAccess(staff, patient)) return false;

        Patient p = (Patient) patient;
        if (patientRegistry.containsKey(p.getPatientId())) return false; // Already admitted

        patientRegistry.put(p.getPatientId(), p);
        // For audit trail, maybe log admission here
        System.out.println("Patient admitted: " + p.getPatientId() + " by staff: " + staff);
        return true;
    }

    // Validate staff access to patient data based on role
    private boolean validateStaffAccess(Object staff, Object patient) {
        if (!(patient instanceof Patient)) return false;

        if (staff instanceof Doctor) {
            // Doctor has full access to patient medical record and info
            return true;
        }
        if (staff instanceof Nurse) {
            // Nurse has limited access: can see basic info only
            return true;
        }
        if (staff instanceof Administrator) {
            // Admin access only if permission contains 'patient_access'
            Administrator admin = (Administrator) staff;
            return admin.getAccessPermissions().contains("patient_access");
        }
        return false; // unknown staff type
    }

    // Package-private method to get patient by ID (internal use)
    Patient getPatientById(String patientId) {
        return patientRegistry.get(patientId);
    }

    // Package-private method for hospital internal audit
    void performInternalAudit() {
        System.out.println("Performing hospital internal audit on " + patientRegistry.size() + " patients");
    }
}