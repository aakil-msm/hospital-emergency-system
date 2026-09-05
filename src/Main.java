import java.util.Scanner;

/**
 * Mini Hospital Emergency Management System.
 *
 * Ties together four data structures:
 *   - PatientBST       : master patient records, keyed by Patient ID
 *   - EmergencyQueue    : FIFO queue of patients waiting for treatment
 *   - TreatmentStack    : LIFO history of completed treatments
 *   - VisitLinkedList   : per-patient singly linked list of past visits
 */
public class Main {

    private static PatientBST patientRecords = new PatientBST();
    private static EmergencyQueue emergencyQueue = new EmergencyQueue();
    private static TreatmentStack treatmentHistory = new TreatmentStack();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            printMenu();
            choice = readInt("Enter your choice: ");
            switch (choice) {
                case 1 -> registerPatient();
                case 2 -> searchPatient();
                case 3 -> deletePatient();
                case 4 -> patientRecords.inorderTraversal();
                case 5 -> addPatientToQueue();
                case 6 -> treatNextPatient();
                case 7 -> emergencyQueue.displayQueue();
                case 8 -> treatmentHistory.displayStack();
                case 9 -> undoLastTreatment();
                case 10 -> addVisit();
                case 11 -> removeVisit();
                case 12 -> searchVisit();
                case 13 -> displayVisitHistory();
                case 0 -> System.out.println("Exiting Hospital Emergency Management System. Goodbye!");
                default -> System.out.println("Invalid choice. Please try again.");
            }
            System.out.println();
        } while (choice != 0);

        scanner.close();
    }

    private static void printMenu() {
        System.out.println("=========================================");
        System.out.println(" MINI HOSPITAL EMERGENCY MANAGEMENT SYSTEM");
        System.out.println("=========================================");
        System.out.println(" --- Patient Records (BST) ---");
        System.out.println(" 1.  Register New Patient");
        System.out.println(" 2.  Search Patient by ID");
        System.out.println(" 3.  Delete Patient");
        System.out.println(" 4.  Display All Patients (In-order)");
        System.out.println(" --- Emergency Queue ---");
        System.out.println(" 5.  Add Patient to Emergency Queue");
        System.out.println(" 6.  Treat Next Patient (Dequeue)");
        System.out.println(" 7.  Display Emergency Queue");
        System.out.println(" --- Treatment History (Stack) ---");
        System.out.println(" 8.  Display Treatment History");
        System.out.println(" 9.  Undo Last Treatment Record (Pop)");
        System.out.println(" --- Patient Visit History (Linked List) ---");
        System.out.println(" 10. Add Visit for a Patient");
        System.out.println(" 11. Remove Visit for a Patient");
        System.out.println(" 12. Search Visit for a Patient");
        System.out.println(" 13. Display Visit History for a Patient");
        System.out.println(" 0.  Exit");
        System.out.println("-----------------------------------------");
    }

    // ---------------- Patient Records (BST) ----------------

    private static void registerPatient() {
        int id = readInt("Enter Patient ID: ");
        if (patientRecords.search(id) != null) {
            System.out.println("Error: Patient ID " + id + " already exists.");
            return;
        }
        String name = readString("Enter Patient Name: ");
        int age = readInt("Enter Age: ");
        String contact = readString("Enter Contact Number: ");
        String condition = readString("Enter Medical Condition: ");

        Patient patient = new Patient(id, name, age, contact, condition);
        patientRecords.insert(patient);
        System.out.println("Patient registered successfully.");
    }

    private static void searchPatient() {
        int id = readInt("Enter Patient ID to search: ");
        Patient patient = patientRecords.search(id);
        if (patient == null) {
            System.out.println("Patient with ID " + id + " not found.");
        } else {
            System.out.println("Patient found: " + patient);
        }
    }

    private static void deletePatient() {
        int id = readInt("Enter Patient ID to delete: ");
        boolean deleted = patientRecords.delete(id);
        System.out.println(deleted ? "Patient deleted successfully." : "Patient with ID " + id + " not found.");
    }

    // ---------------- Emergency Queue ----------------

    private static void addPatientToQueue() {
        int id = readInt("Enter Patient ID to add to emergency queue: ");
        Patient patient = patientRecords.search(id);
        if (patient == null) {
            System.out.println("Error: No patient record found with ID " + id + ". Register the patient first.");
            return;
        }
        emergencyQueue.enqueue(patient);
    }

    private static void treatNextPatient() {
        Patient patient = emergencyQueue.dequeue();
        if (patient == null) return;

        System.out.println("Now treating: " + patient);
        String details = readString("Enter treatment details: ");
        String date = readString("Enter completion date (e.g. 2026-09-05): ");

        TreatmentRecord record = new TreatmentRecord(patient.getPatientId(), patient.getName(), details, date);
        treatmentHistory.push(record);

        // Automatically log this treatment as a new visit for the patient
        Visit visit = new Visit(
                generateVisitId(patient),
                date,
                "Attending Doctor",
                patient.getMedicalCondition(),
                details
        );
        patient.getVisitHistory().addVisit(visit);
        System.out.println("Treatment completed and recorded in patient's visit history.");
    }

    private static void undoLastTreatment() {
        TreatmentRecord record = treatmentHistory.pop();
        if (record != null) {
            System.out.println("Removed most recent treatment record: " + record);
        }
    }

    // ---------------- Visit History (Linked List) ----------------

    private static void addVisit() {
        Patient patient = findPatientOrPrompt();
        if (patient == null) return;

        int visitId = readInt("Enter Visit ID: ");
        String date = readString("Enter Visit Date: ");
        String doctor = readString("Enter Doctor Name: ");
        String diagnosis = readString("Enter Diagnosis: ");
        String treatment = readString("Enter Treatment: ");

        patient.getVisitHistory().addVisit(new Visit(visitId, date, doctor, diagnosis, treatment));
        System.out.println("Visit added successfully.");
    }

    private static void removeVisit() {
        Patient patient = findPatientOrPrompt();
        if (patient == null) return;

        int visitId = readInt("Enter Visit ID to remove: ");
        boolean removed = patient.getVisitHistory().removeVisit(visitId);
        System.out.println(removed ? "Visit removed successfully." : "Visit ID " + visitId + " not found.");
    }

    private static void searchVisit() {
        Patient patient = findPatientOrPrompt();
        if (patient == null) return;

        int visitId = readInt("Enter Visit ID to search: ");
        Visit visit = patient.getVisitHistory().searchVisit(visitId);
        System.out.println(visit != null ? "Visit found: " + visit : "Visit ID " + visitId + " not found.");
    }

    private static void displayVisitHistory() {
        Patient patient = findPatientOrPrompt();
        if (patient == null) return;

        System.out.println("Visit history for " + patient.getName() + " (ID: " + patient.getPatientId() + "):");
        patient.getVisitHistory().displayVisits();
    }

    // ---------------- Helpers ----------------

    private static Patient findPatientOrPrompt() {
        int id = readInt("Enter Patient ID: ");
        Patient patient = patientRecords.search(id);
        if (patient == null) {
            System.out.println("Error: No patient record found with ID " + id + ".");
        }
        return patient;
    }

    private static int generateVisitId(Patient patient) {
        // Simple scheme: current time-based ID to avoid collisions across patients.
        return (int) (System.currentTimeMillis() % 100000);
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Please try again.");
            }
        }
    }

    private static String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
}