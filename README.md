# Mini Hospital Emergency Management System

A console-based Java application that simulates hospital emergency management
using four core data structures, built for the "Mini Hospital Emergency
Management System Using Data Structures" assignment.

## Data Structures Used

| Feature                  | Data Structure       | File(s)                                   |
|---------------------------|----------------------|--------------------------------------------|
| Patient records            | Binary Search Tree   | `PatientBST.java`, `Patient.java`          |
| Emergency waiting list     | Queue (FIFO)         | `EmergencyQueue.java`                      |
| Completed treatment history| Stack (LIFO)         | `TreatmentStack.java`, `TreatmentRecord.java` |
| Per-patient visit history  | Singly Linked List   | `VisitLinkedList.java`, `Visit.java`       |
| Console menu / driver      | —                    | `HospitalManagementSystem.java`            |

## Project Structure

```
HospitalEMS/
├── src/
│   ├── Patient.java
│   ├── PatientBST.java
│   ├── Visit.java
│   ├── VisitLinkedList.java
│   ├── EmergencyQueue.java
│   ├── TreatmentRecord.java
│   ├── TreatmentStack.java
│   └── HospitalManagementSystem.java
├── README.md
└── screenshots/
```

## How Each Data Structure Is Used

### 1. Patient Records — Binary Search Tree (`PatientBST.java`)
Patients are stored in a BST keyed by `Patient ID`.
- `insert(Patient)` — adds a new patient, rejecting duplicate IDs.
- `search(int id)` — recursive binary search by ID.
- `delete(int id)` — handles all three deletion cases (leaf, one child, two
  children via in-order successor).
- `inorderTraversal()` — prints patients in ascending Patient ID order.

### 2. Emergency Patient Queue — Queue (`EmergencyQueue.java`)
Implemented as a custom singly linked queue (not `java.util.Queue`) to
demonstrate the underlying structure directly.
- `enqueue(Patient)` — adds a patient to the back of the line.
- `dequeue()` — removes and returns the patient at the front (FIFO); handles
  the empty-queue case gracefully.
- `displayQueue()` — lists everyone currently waiting, in order.

### 3. Treatment History — Stack (`TreatmentStack.java`)
Implemented as a custom singly linked stack.
- `push(TreatmentRecord)` — records a just-completed treatment.
- `pop()` — removes the most recently completed record (LIFO); handles the
  empty-stack case gracefully.
- `displayStack()` — shows treatment history, most recent first.

### 4. Patient Visit History — Singly Linked List (`VisitLinkedList.java`)
Each `Patient` object owns one `VisitLinkedList` holding their past visits.
- `addVisit(Visit)` — appends a new visit to the end of the list.
- `removeVisit(int visitId)` — removes a visit by ID.
- `searchVisit(int visitId)` — linear search by Visit ID.
- `displayVisits()` — prints the full visit history in order.

## How to Compile and Run

```bash
cd HospitalEMS/src
javac *.java -d ../out
cd ../out
java HospitalManagementSystem
```

## Menu Overview

```
--- Patient Records (BST) ---
1. Register New Patient
2. Search Patient by ID
3. Delete Patient
4. Display All Patients (In-order)

--- Emergency Queue ---
5. Add Patient to Emergency Queue
6. Treat Next Patient (Dequeue)
7. Display Emergency Queue

--- Treatment History (Stack) ---
8. Display Treatment History
9. Undo Last Treatment Record (Pop)

--- Patient Visit History (Linked List) ---
10. Add Visit for a Patient
11. Remove Visit for a Patient
12. Search Visit for a Patient
13. Display Visit History for a Patient

0. Exit
```

Registering a patient (option 1) inserts them into the BST. Treating a
patient (option 6) dequeues them from the emergency queue, pushes a
completed treatment record onto the treatment stack, and automatically logs
that treatment as a new entry in the patient's visit history linked list —
so all four structures work together during a normal patient flow.

## Design Notes

- The Queue and Stack are implemented from scratch with linked nodes
  (rather than using `java.util.LinkedList`/`Deque`) to directly demonstrate
  FIFO and LIFO behavior as required by the assignment.
- The BST's delete method uses the in-order successor strategy for nodes
  with two children, which keeps the tree correctly ordered.
- Each `Patient` embeds its own `VisitLinkedList`, so visit history is
  naturally scoped per patient rather than stored in one global list.
- Input is read defensively (`readInt`/`readString` helpers) so invalid
  menu input doesn't crash the program.

## Author

[Your Name Here] — [Your Student ID Here]