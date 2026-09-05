/**
 * Binary Search Tree that stores Patient records keyed by Patient ID.
 * Supports insert, search, delete, and in-order traversal (ascending Patient ID).
 */
public class PatientBST {

    private class Node {
        Patient patient;
        Node left, right;
        Node(Patient patient) { this.patient = patient; }
    }

    private Node root;

    /** Inserts a new patient. Duplicate Patient IDs are rejected. */
    public void insert(Patient patient) {
        root = insertRec(root, patient);
    }

    private Node insertRec(Node node, Patient patient) {
        if (node == null) return new Node(patient);

        if (patient.getPatientId() < node.patient.getPatientId()) {
            node.left = insertRec(node.left, patient);
        } else if (patient.getPatientId() > node.patient.getPatientId()) {
            node.right = insertRec(node.right, patient);
        } else {
            System.out.println("Error: Patient ID " + patient.getPatientId() + " already exists. Insert ignored.");
        }
        return node;
    }

    /** Searches for a patient by ID. Returns null if not found. */
    public Patient search(int patientId) {
        Node result = searchRec(root, patientId);
        return (result == null) ? null : result.patient;
    }

    private Node searchRec(Node node, int patientId) {
        if (node == null || node.patient.getPatientId() == patientId) {
            return node;
        }
        if (patientId < node.patient.getPatientId()) {
            return searchRec(node.left, patientId);
        }
        return searchRec(node.right, patientId);
    }

    /** Deletes a patient by ID. Returns true if a patient was found and removed. */
    public boolean delete(int patientId) {
        if (search(patientId) == null) {
            return false;
        }
        root = deleteRec(root, patientId);
        return true;
    }

    private Node deleteRec(Node node, int patientId) {
        if (node == null) return null;

        if (patientId < node.patient.getPatientId()) {
            node.left = deleteRec(node.left, patientId);
        } else if (patientId > node.patient.getPatientId()) {
            node.right = deleteRec(node.right, patientId);
        } else {
            // Node found
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;

            // Two children: replace with in-order successor (smallest in right subtree)
            Node successor = findMin(node.right);
            node.patient = successor.patient;
            node.right = deleteRec(node.right, successor.patient.getPatientId());
        }
        return node;
    }

    private Node findMin(Node node) {
        while (node.left != null) node = node.left;
        return node;
    }

    /** Displays all patients in ascending order of Patient ID. */
    public void inorderTraversal() {
        if (root == null) {
            System.out.println("No patient records found.");
            return;
        }
        System.out.println("----- Patient Records (Ascending Patient ID) -----");
        inorderRec(root);
    }

    private void inorderRec(Node node) {
        if (node == null) return;
        inorderRec(node.left);
        System.out.println(node.patient);
        inorderRec(node.right);
    }

    public boolean isEmpty() {
        return root == null;
    }
}