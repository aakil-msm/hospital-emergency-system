/**
 * Singly Linked List that stores a single patient's visit history.
 * Supports add, remove, search, and display operations.
 */
public class VisitLinkedList {
    private Visit head;

    /** Adds a new visit to the end of the list. */
    public void addVisit(Visit newVisit) {
        if (head == null) {
            head = newVisit;
        } else {
            Visit current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newVisit);
        }
    }

    /** Removes a visit by Visit ID. Returns true if found and removed. */
    public boolean removeVisit(int visitId) {
        if (head == null) return false;

        if (head.getVisitId() == visitId) {
            head = head.getNext();
            return true;
        }

        Visit current = head;
        while (current.getNext() != null) {
            if (current.getNext().getVisitId() == visitId) {
                current.setNext(current.getNext().getNext());
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    /** Searches for a visit by Visit ID. Returns null if not found. */
    public Visit searchVisit(int visitId) {
        Visit current = head;
        while (current != null) {
            if (current.getVisitId() == visitId) {
                return current;
            }
            current = current.getNext();
        }
        return null;
    }

    /** Displays the entire visit history in order added. */
    public void displayVisits() {
        if (head == null) {
            System.out.println("No visit history available for this patient.");
            return;
        }
        Visit current = head;
        while (current != null) {
            System.out.println("  " + current);
            current = current.getNext();
        }
    }

    public boolean isEmpty() {
        return head == null;
    }
}