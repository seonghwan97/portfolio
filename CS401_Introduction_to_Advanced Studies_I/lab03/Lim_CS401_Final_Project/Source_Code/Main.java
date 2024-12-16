import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private JFrame frame;
    private JTabbedPane tabbedPane;
    private List<Employee> employeeList = new ArrayList<>();
    private UnsortedArray<Employee> unsortedArray;
    private SortedArray<Employee> sortedArray;
    private UnsortedLinkedList<Employee> unsortedLinkedList;
    private SortedLinkedList<Employee> sortedLinkedList;
    private BinarySearchTree<Employee> binarySearchTree;

    int maxSize = 1024;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }

    public Main() {
        frame = new JFrame("Employee Data Structure GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 800);

        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel(new GridLayout(2, 4, 10, 10));

        // Initialize buttons
        JButton loadCsvButton = new JButton("Load CSV");
        JButton createUnsortedArrayButton = new JButton("Create Unsorted Array");
        JButton createSortedArrayButton = new JButton("Create Sorted Array");
        JButton createUnsortedLinkedListButton = new JButton("Create Unsorted Linked List");
        JButton createSortedLinkedListButton = new JButton("Create Sorted Linked List");
        JButton performanceAnalysisButton = new JButton("Performance Analysis");

        // Add buttons to the panel
        buttonPanel.add(loadCsvButton);
        buttonPanel.add(createUnsortedArrayButton);
        buttonPanel.add(createSortedArrayButton);
        buttonPanel.add(createUnsortedLinkedListButton);
        buttonPanel.add(createSortedLinkedListButton);
        buttonPanel.add(performanceAnalysisButton);

        // Initialize tabbed pane
        tabbedPane = new JTabbedPane();

        // Set layout
        mainPanel.add(buttonPanel, BorderLayout.NORTH);
        mainPanel.add(tabbedPane, BorderLayout.CENTER);

        // Add action listeners to buttons
        loadCsvButton.addActionListener(this::loadCsvFile);
        createUnsortedArrayButton.addActionListener(e -> createUnsortedArray());
        createSortedArrayButton.addActionListener(e -> createSortedArray());
        createUnsortedLinkedListButton.addActionListener(e -> createUnsortedLinkedList());
        createSortedLinkedListButton.addActionListener(e -> createSortedLinkedList());
        performanceAnalysisButton.addActionListener(e -> showPerformanceAnalysis());

        // Finalize frame setup
        frame.add(mainPanel);
        frame.setVisible(true);
    }

    // Load CSV file and populate employee list
    private void loadCsvFile(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            employeeList.clear();
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                br.readLine(); // Skip header
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(",");
                    Employee emp = new Employee(Integer.parseInt(parts[0]), parts[1], parts[2], parts[3]);
                    employeeList.add(emp);
                }
                showMessage("CSV loaded successfully.");
            } catch (IOException ex) {
                showMessage("Error loading CSV file.");
            }
        }
    }

    // Create Unsorted Array and display in a new tab
    private void createUnsortedArray() {
        if (employeeList.isEmpty()) {
            showMessage("No data loaded. Please load a CSV file first.");
            return;
        }

        unsortedArray = new UnsortedArray<>(maxSize);
        for (Employee emp : employeeList) {
            unsortedArray.add(emp);
        }

        // Convert unsorted array to table model
        DefaultTableModel model = unsortedArrayToTableModel();
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);

        // Add buttons including Quick Sort
        JPanel buttonPanel = new JPanel(new FlowLayout());

        // Add common operation buttons
        buttonPanel.add(createCommonButtons(model, table, "Unsorted Array"));

        // Add Quick Sort button
        JButton quickSortButton = new JButton("Quick Sort");
        quickSortButton.addActionListener(e -> showQuickSortedArray());

        buttonPanel.add(quickSortButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        tabbedPane.addTab("Unsorted Array", panel);
        showMessage("Unsorted Array created.");
    }

    // Display Quick Sorted Array in a new tab
    private void showQuickSortedArray() {
        if (unsortedArray == null || unsortedArray.isEmpty()) {
            showMessage("Unsorted Array is empty. Cannot perform Quick Sort.");
            return;
        }

        // Perform quick sort on the unsorted array
        unsortedArray.quickSort();

        // Create table model for sorted array
        DefaultTableModel sortedModel = new DefaultTableModel(new Object[]{"EmpID", "First Name", "Last Name", "Title"}, 0);
        for (int i = 0; i < unsortedArray.size(); i++) {
            Employee emp = unsortedArray.getElementAt(i);
            sortedModel.addRow(new Object[]{emp.getEmpID(), emp.getFirstName(), emp.getLastName(), emp.getTitle()});
        }

        // Display sorted array in a new tab
        JTable sortedTable = new JTable(sortedModel);
        JScrollPane sortedScrollPane = new JScrollPane(sortedTable);

        JPanel sortedPanel = new JPanel(new BorderLayout());
        sortedPanel.add(sortedScrollPane, BorderLayout.CENTER);

        tabbedPane.addTab("Quick Sorted Array", sortedPanel);
        showMessage("Quick Sort completed. Check the new tab for results.");
    }

    // Create Sorted Array and display in a new tab
    private void createSortedArray() {
        if (employeeList.isEmpty()) {
            showMessage("No data loaded. Please load a CSV file first.");
            return;
        }

        sortedArray = new SortedArray<>(maxSize);
        for (Employee emp : employeeList) {
            sortedArray.add(emp);
        }

        // Convert sorted array to table model
        DefaultTableModel model = sortedArrayToTableModel();
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);

        // Add buttons for the Sorted Array tab
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(createCommonButtons(model, table, "Sorted Array"));

        // Add "Create Binary Search Tree" button
        JButton createBSTButton = new JButton("Create Binary Search Tree");
        createBSTButton.addActionListener(e -> createBinarySearchTree());

        buttonPanel.add(createBSTButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        tabbedPane.addTab("Sorted Array", panel);
        showMessage("Sorted Array created.");
    }
    
    // Populate BST table model recursively
    private void populateBSTTableModel(BSTNode<Employee> node, DefaultTableModel model, String position) {
        if (node != null) {
            model.addRow(new Object[]{position, node.getInfo().toString()});
            populateBSTTableModel(node.getLeft(), model, position + " -> Left");
            populateBSTTableModel(node.getRight(), model, position + " -> Right");
        }
    }

    // Create Binary Search Tree and display in a new tab
    private void createBinarySearchTree() {
        if (sortedArray == null || sortedArray.isEmpty()) {
            showMessage("Sorted Array is empty. Cannot create Binary Search Tree.");
            return;
        }

        // Initialize Binary Search Tree
        binarySearchTree = new BinarySearchTree<>();

        // Build a balanced tree from sorted array data
        Employee[] sortedArrayData = new Employee[sortedArray.size()];
        for (int i = 0; i < sortedArray.size(); i++) {
            sortedArrayData[i] = sortedArray.getElementAt(i);
        }
        binarySearchTree.buildBalancedTree(sortedArrayData);

        // Create table model for BST
        DefaultTableModel bstModel = new DefaultTableModel(new Object[]{"Node", "Employee Data"}, 0);
        populateBSTTableModel(binarySearchTree.getRoot(), bstModel, "Root");

        JTable bstTable = new JTable(bstModel);
        JScrollPane bstScrollPane = new JScrollPane(bstTable);

        JPanel bstPanel = new JPanel(new BorderLayout());
        bstPanel.add(bstScrollPane, BorderLayout.CENTER);

        // Add operation buttons for BST
        JPanel buttonPanel = new JPanel(new FlowLayout());

        JButton addButton = new JButton("Add");
        addButton.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(frame, "Enter Employee details (ID, FirstName, LastName, Title):");
            if (input != null && !input.trim().isEmpty()) {
                String[] parts = input.split(",");
                if (parts.length == 4) {
                    try {
                        Employee emp = new Employee(
                                Integer.parseInt(parts[0].trim()),
                                parts[1].trim(),
                                parts[2].trim(),
                                parts[3].trim()
                        );
                        binarySearchTree.add(emp);

                        // Update table model
                        bstModel.setRowCount(0);
                        populateBSTTableModel(binarySearchTree.getRoot(), bstModel, "Root");

                        showMessage("Employee added to Binary Search Tree.");
                    } catch (NumberFormatException ex) {
                        showMessage("Invalid input format.");
                    }
                } else {
                    showMessage("Invalid input format.");
                }
            }
        });

        JButton removeButton = new JButton("Remove");
        removeButton.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(frame, "Enter Employee ID to remove:");
            if (input != null && !input.trim().isEmpty()) {
                try {
                    int empID = Integer.parseInt(input.trim());
                    Employee target = new Employee(empID, "", "", "");
                    binarySearchTree.remove(target);

                    // Update table model
                    bstModel.setRowCount(0);
                    populateBSTTableModel(binarySearchTree.getRoot(), bstModel, "Root");

                    showMessage("Employee removed from Binary Search Tree.");
                } catch (NumberFormatException ex) {
                    showMessage("Invalid Employee ID.");
                }
            }
        });

        JButton containsButton = new JButton("Contains");
        containsButton.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(frame, "Enter Employee ID to search:");
            if (input != null && !input.trim().isEmpty()) {
                try {
                    int empID = Integer.parseInt(input.trim());
                    Employee target = new Employee(empID, "", "", "");
                    boolean found = binarySearchTree.contains(target);
                    showMessage(found ? "Employee exists in Binary Search Tree." : "Employee not found.");
                } catch (NumberFormatException ex) {
                    showMessage("Invalid Employee ID.");
                }
            }
        });

        // Add "Show Tree" button
        JButton showTreeButton = new JButton("Show Tree");
        showTreeButton.addActionListener(e -> showTreeWindow(binarySearchTree.getRoot()));

        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(containsButton);
        buttonPanel.add(showTreeButton);
        bstPanel.add(buttonPanel, BorderLayout.SOUTH);

        tabbedPane.addTab("Binary Search Tree", bstPanel);
        showMessage("Balanced Binary Search Tree created and displayed in a new tab.");
    }
    
    // Display the tree structure in a new window
    private void showTreeWindow(BSTNode<Employee> root) {
        if (root == null) {
            showMessage("Tree is empty.");
            return;
        }

        // Create a new frame for tree visualization
        JFrame treeFrame = new JFrame("Binary Search Tree Visualization");
        treeFrame.setSize(600, 400);
        treeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Display tree data in a text area
        JTextArea treeTextArea = new JTextArea();
        treeTextArea.setEditable(false);

        // Convert tree to string representation
        StringBuilder treeBuilder = new StringBuilder();
        buildTreeString(root, treeBuilder, "", true);

        treeTextArea.setText(treeBuilder.toString());
        treeTextArea.setFont(new Font("Monospaced", Font.PLAIN, 12));

        JScrollPane scrollPane = new JScrollPane(treeTextArea);

        treeFrame.add(scrollPane);
        treeFrame.setVisible(true);
    }

    // Recursively build string representation of the tree
    private void buildTreeString(BSTNode<Employee> node, StringBuilder builder, String prefix, boolean isTail) {
        if (node != null) {
            builder.append(prefix)
                   .append(isTail ? "└── " : "├── ")
                   .append(node.getInfo().getEmpID())
                   .append("\n");
            buildTreeString(node.getLeft(), builder, prefix + (isTail ? "    " : "│   "), false);
            buildTreeString(node.getRight(), builder, prefix + (isTail ? "    " : "│   "), true);
        }
    }

    // Create Unsorted Linked List and display in a new tab
    private void createUnsortedLinkedList() {
        if (employeeList.isEmpty()) {
            showMessage("No data loaded. Please load a CSV file first.");
            return;
        }

        unsortedLinkedList = new UnsortedLinkedList<>();
        for (Employee emp : employeeList) {
            unsortedLinkedList.add(emp);
        }

        createStructureTab("Unsorted Linked List", linkedListToTableModel(unsortedLinkedList));
        showMessage("Unsorted Linked List created.");
    }

    // Create Sorted Linked List and display in a new tab
    private void createSortedLinkedList() {
        if (employeeList.isEmpty()) {
            showMessage("No data loaded. Please load a CSV file first.");
            return;
        }

        sortedLinkedList = new SortedLinkedList<>();
        for (Employee emp : employeeList) {
            sortedLinkedList.add(emp);
        }

        createStructureTab("Sorted Linked List", linkedListToTableModel(sortedLinkedList));
        showMessage("Sorted Linked List created.");
    }

    // Create a new tab for a data structure with its table model
    private void createStructureTab(String title, DefaultTableModel model) {
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(createCommonButtons(model, table, title));
        panel.add(buttonPanel, BorderLayout.SOUTH);

        tabbedPane.addTab(title, panel);
    }

    // Convert unsorted array to table model
    private DefaultTableModel unsortedArrayToTableModel() {
        DefaultTableModel model = new DefaultTableModel(new Object[]{"EmpID", "First Name", "Last Name", "Title"}, 0);
        for (int i = 0; i < unsortedArray.size(); i++) {
            Employee emp = unsortedArray.getElementAt(i);
            model.addRow(new Object[]{emp.getEmpID(), emp.getFirstName(), emp.getLastName(), emp.getTitle()});
        }
        return model;
    }

    // Convert sorted array to table model
    private DefaultTableModel sortedArrayToTableModel() {
        DefaultTableModel model = new DefaultTableModel(new Object[]{"EmpID", "First Name", "Last Name", "Title"}, 0);
        for (int i = 0; i < sortedArray.size(); i++) {
            Employee emp = sortedArray.getElementAt(i);
            model.addRow(new Object[]{emp.getEmpID(), emp.getFirstName(), emp.getLastName(), emp.getTitle()});
        }
        return model;
    }

    // Convert unsorted linked list to table model
    private DefaultTableModel linkedListToTableModel(UnsortedLinkedList<Employee> list) {
        DefaultTableModel model = new DefaultTableModel(new Object[]{"EmpID", "First Name", "Last Name", "Title"}, 0);
        LLNode<Employee> currentNode = list.getHead();
        while (currentNode != null) {
            Employee emp = currentNode.getInfo();
            model.addRow(new Object[]{emp.getEmpID(), emp.getFirstName(), emp.getLastName(), emp.getTitle()});
            currentNode = currentNode.getLink();
        }
        return model;
    }

    // Convert sorted linked list to table model
    private DefaultTableModel linkedListToTableModel(SortedLinkedList<Employee> list) {
        DefaultTableModel model = new DefaultTableModel(new Object[]{"EmpID", "First Name", "Last Name", "Title"}, 0);
        LLNode<Employee> currentNode = list.getHead();
        while (currentNode != null) {
            Employee emp = currentNode.getInfo();
            model.addRow(new Object[]{emp.getEmpID(), emp.getFirstName(), emp.getLastName(), emp.getTitle()});
            currentNode = currentNode.getLink();
        }
        return model;
    }

    // Create common operation buttons for data structures
    private JPanel createCommonButtons(DefaultTableModel model, JTable table, String structureType) {
        JPanel panel = new JPanel(new FlowLayout());

        JButton addButton = new JButton("Add");
        addButton.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(frame, "Enter Employee details (ID, FirstName, LastName, Title):");
            if (input != null && !input.trim().isEmpty()) {
                String[] parts = input.split(",");
                if (parts.length == 4) {
                    try {
                        Employee emp = new Employee(
                                Integer.parseInt(parts[0].trim()),
                                parts[1].trim(),
                                parts[2].trim(),
                                parts[3].trim()
                        );

                        switch (structureType) {
                            case "Unsorted Array" -> {
                                unsortedArray.add(emp);
                                model.addRow(new Object[]{emp.getEmpID(), emp.getFirstName(), emp.getLastName(), emp.getTitle()});
                            }
                            case "Sorted Array" -> {
                                sortedArray.add(emp); // Add to the sorted array
                                
                                // Clear the table and re-populate it in sorted order
                                model.setRowCount(0); // Clear all rows
                                for (int i = 0; i < sortedArray.size(); i++) {
                                    Employee sortedEmp = sortedArray.getElementAt(i);
                                    model.addRow(new Object[]{sortedEmp.getEmpID(), sortedEmp.getFirstName(), sortedEmp.getLastName(), sortedEmp.getTitle()});
                                }
                            }
                            case "Unsorted Linked List" -> {
                                unsortedLinkedList.add(emp);
                                model.addRow(new Object[]{emp.getEmpID(), emp.getFirstName(), emp.getLastName(), emp.getTitle()});
                            }
                            case "Sorted Linked List" -> {
                                sortedLinkedList.add(emp); // Add to the sorted linked list
                                
                                // Clear the table and re-populate it in sorted order
                                model.setRowCount(0); // Clear all rows
                                LLNode<Employee> currentNode = sortedLinkedList.getHead();
                                while (currentNode != null) {
                                    Employee sortedEmp = currentNode.getInfo();
                                    model.addRow(new Object[]{sortedEmp.getEmpID(), sortedEmp.getFirstName(), sortedEmp.getLastName(), sortedEmp.getTitle()});
                                    currentNode = currentNode.getLink();
                                }
                            }
                            case "Binary Search Tree" -> {
                                binarySearchTree.add(emp);
                                model.addRow(new Object[]{emp.getEmpID(), emp.getFirstName(), emp.getLastName(), emp.getTitle()});
                            }
                        }

                        showMessage("Employee added successfully.");
                    } catch (NumberFormatException ex) {
                        showMessage("Invalid input format.");
                    }
                } else {
                    showMessage("Invalid input format.");
                }
            }
        });

        JButton removeButton = new JButton("Remove");
        removeButton.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(frame, "Enter Employee ID to remove:");
            if (input != null && !input.trim().isEmpty()) {
                try {
                    int empID = Integer.parseInt(input.trim());
                    Employee target = new Employee(empID, "", "", "");
                    boolean removed = switch (structureType) {
                        case "Unsorted Array" -> unsortedArray.remove(target);
                        case "Sorted Array" -> sortedArray.remove(target);
                        case "Unsorted Linked List" -> unsortedLinkedList.remove(target);
                        case "Sorted Linked List" -> sortedLinkedList.remove(target);
                        case "Binary Search Tree" -> binarySearchTree.remove(target);
                        default -> false;
                    };
                    if (removed) {
                        for (int i = 0; i < model.getRowCount(); i++) {
                            if (Integer.parseInt(model.getValueAt(i, 0).toString()) == empID) {
                                model.removeRow(i);
                                break;
                            }
                        }
                        showMessage("Employee removed successfully.");
                    } else {
                        showMessage("Employee not found.");
                    }
                } catch (NumberFormatException ex) {
                    showMessage("Invalid Employee ID.");
                }
            }
        });

        JButton containsButton = new JButton("Contains");
        containsButton.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(frame, "Enter Employee ID to search:");
            if (input != null && !input.trim().isEmpty()) {
                try {
                    int empID = Integer.parseInt(input.trim());
                    Employee target = new Employee(empID, "", "", "");
                    boolean found = switch (structureType) {
                        case "Unsorted Array" -> unsortedArray.contains(target);
                        case "Sorted Array" -> sortedArray.contains(target);
                        case "Unsorted Linked List" -> unsortedLinkedList.contains(target);
                        case "Sorted Linked List" -> sortedLinkedList.contains(target);
                        case "Binary Search Tree" -> binarySearchTree.contains(target);
                        default -> false;
                    };
                    showMessage(found ? "Employee found." : "Employee not found.");
                } catch (NumberFormatException ex) {
                    showMessage("Invalid Employee ID.");
                }
            }
        });

        panel.add(addButton);
        panel.add(removeButton);
        panel.add(containsButton);
        return panel;
    }

    // Display performance analysis of data structures
    private void showPerformanceAnalysis() {
        // Define Big-O complexities for each data structure
        String[][] data = {
                {"Unsorted Array", "O(1)", "O(N)", "O(N)"},
                {"Sorted Array", "O(N)", "O(N)", "O(log_2 N)"},
                {"Unsorted Linked List", "O(1)", "O(N)", "O(N)"},
                {"Sorted Linked List", "O(N)", "O(N)", "O(N)"},
                {"Binary Search Tree", "O(log_2 N)", "O(log_2 N)", "O(log_2 N)"}
        };
        String[] columnNames = {"Data Structure", "Add", "Remove", "Contains"};
        JTable table = new JTable(new DefaultTableModel(data, columnNames));
        JScrollPane scrollPane = new JScrollPane(table);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);

        // Add "Test Contains" button
        JButton containsButton = new JButton("Test Contains");

        containsButton.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(frame, "Enter Employee ID to search:");
            if (input != null && !input.trim().isEmpty()) {
                try {
                    int empID = Integer.parseInt(input.trim());
                    Employee target = new Employee(empID, "", "", "");
                    StringBuilder results = new StringBuilder("Contains Test Results:\n");

                    // Test Unsorted Array
                    if (unsortedArray != null) {
                        unsortedArray.resetComparisonCount();
                        boolean found = unsortedArray.contains(target);
                        results.append("Unsorted Array: ")
                               .append(found ? "Found" : "Not Found")
                               .append(" (Comparisons: ").append(unsortedArray.getComparisonCount()).append(")\n");
                    } else {
                        results.append("Unsorted Array: Not Initialized\n");
                    }

                    // Test Sorted Array
                    if (sortedArray != null) {
                        sortedArray.resetComparisonCount();
                        boolean found = sortedArray.contains(target);
                        results.append("Sorted Array: ")
                               .append(found ? "Found" : "Not Found")
                               .append(" (Comparisons: ").append(sortedArray.getComparisonCount()).append(")\n");
                    } else {
                        results.append("Sorted Array: Not Initialized\n");
                    }

                    // Test Unsorted Linked List
                    if (unsortedLinkedList != null) {
                        unsortedLinkedList.resetComparisonCount();
                        boolean found = unsortedLinkedList.contains(target);
                        results.append("Unsorted Linked List: ")
                               .append(found ? "Found" : "Not Found")
                               .append(" (Comparisons: ").append(unsortedLinkedList.getComparisonCount()).append(")\n");
                    } else {
                        results.append("Unsorted Linked List: Not Initialized\n");
                    }

                    // Test Sorted Linked List
                    if (sortedLinkedList != null) {
                        sortedLinkedList.resetComparisonCount();
                        boolean found = sortedLinkedList.contains(target);
                        results.append("Sorted Linked List: ")
                               .append(found ? "Found" : "Not Found")
                               .append(" (Comparisons: ").append(sortedLinkedList.getComparisonCount()).append(")\n");
                    } else {
                        results.append("Sorted Linked List: Not Initialized\n");
                    }

                    // Test Binary Search Tree
                    if (binarySearchTree != null) {
                        binarySearchTree.resetComparisonCount();
                        boolean found = binarySearchTree.contains(target);
                        results.append("Binary Search Tree: ")
                               .append(found ? "Found" : "Not Found")
                               .append(" (Comparisons: ").append(binarySearchTree.getComparisonCount()).append(")\n");
                    } else {
                        results.append("Binary Search Tree: Not Initialized\n");
                    }

                    // Display results
                    JOptionPane.showMessageDialog(frame, results.toString(), "Performance Results", JOptionPane.INFORMATION_MESSAGE);

                } catch (NumberFormatException ex) {
                    showMessage("Invalid Employee ID.");
                }
            }
        });

        panel.add(containsButton, BorderLayout.SOUTH);
        tabbedPane.addTab("Performance Analysis", panel);
    }

    // Display a message dialog
    private void showMessage(String message) {
        JOptionPane.showMessageDialog(frame, message);
    }
}