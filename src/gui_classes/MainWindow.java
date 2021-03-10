package gui_classes;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Vector;
import java.util.Collections;

import graph_classes.*;

public class MainWindow {
    private JFrame frame;

    private JTextField numberVertexField;
    private JTextField firstVertexField;
    private JTextField secondVertexField;
    private JTextField weightField;

    private JButton addEdgeButton;
    private JButton removeEdgeButton;
    private JButton removeVertexButton;
    private JButton addVertexButton;
    private JButton kruskalButton;
    private JButton primButton;

    private JTextArea originalGraphText;
    private JTextArea kruskalGraphText;
    private JTextArea primGraphText;

    private Graph graph;

    public MainWindow() {

        frame = new JFrame();
        frame.setBounds(100, 100, 700, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.getContentPane().add(textPanels(), BorderLayout.CENTER);

        JPanel grid = new JPanel(new GridLayout());
        grid.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
        grid.add(edgePanels());
        grid.add(vertexPanels());

        JPanel border = new JPanel(new BorderLayout());
        border.add(grid, BorderLayout.NORTH);
        border.add(controlPanels(), BorderLayout.SOUTH);

        frame.getContentPane().add(border, BorderLayout.SOUTH);
    }

    private JPanel textPanels() {
        JPanel textPanel = new JPanel(new GridLayout(1, 3, 5, 0));
        textPanel.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));

        JPanel originalGraphLayout = new JPanel(new BorderLayout());
        originalGraphLayout.add(new JLabel("Origin graph"), BorderLayout.NORTH);
        originalGraphText = new JTextArea();
        originalGraphText.setEditable(false);
        originalGraphLayout.add(originalGraphText, BorderLayout.CENTER);
        textPanel.add(originalGraphLayout);

        JPanel kruskalGraphLayout = new JPanel(new BorderLayout());
        kruskalGraphLayout.add(new JLabel("Kruskal graph"), BorderLayout.NORTH);
        kruskalGraphText = new JTextArea();
        kruskalGraphText.setEditable(false);
        kruskalGraphLayout.add(kruskalGraphText, BorderLayout.CENTER);
        textPanel.add(kruskalGraphLayout);

        JPanel primGraphLayout = new JPanel(new BorderLayout());
        primGraphLayout.add(new JLabel("Prim graph"), BorderLayout.NORTH);
        primGraphText = new JTextArea();
        primGraphText.setEditable(false);
        primGraphLayout.add(primGraphText, BorderLayout.CENTER);
        textPanel.add(primGraphLayout);

        return textPanel;
    }

    private JPanel edgePanels() {
        JPanel gridEdge = new JPanel(new GridLayout(3, 3, 5, 5));
        gridEdge.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 3));

        gridEdge.add(new JLabel("First Vertex"));
        firstVertexField = new JTextField();
        gridEdge.add(firstVertexField);

        gridEdge.add(Box.createHorizontalStrut(20));

        gridEdge.add(new JLabel("Second Vertex"));
        secondVertexField = new JTextField();
        gridEdge.add(secondVertexField);

        removeEdgeButton = new JButton("Remove Edge");
        removeEdgeButton.addActionListener(new MainWindow.OnRemEdgeButton());
        removeEdgeButton.setToolTipText("Remove Edge");
        gridEdge.add(removeEdgeButton);

        gridEdge.add(new JLabel("Edge Weight"));
        weightField = new JTextField();
        gridEdge.add(weightField);

        addEdgeButton = new JButton("Add Edge");
        addEdgeButton.addActionListener(new MainWindow.OnAddEdgeButton());
        addEdgeButton.setToolTipText("Add Edge");
        gridEdge.add(addEdgeButton);

        return gridEdge;
    }

    private JPanel vertexPanels() {
        JPanel gridVertex = new JPanel(new GridLayout(3, 3, 5, 5));
        gridVertex.setBorder(BorderFactory.createEmptyBorder(0, 3, 0, 0));

        gridVertex.add(Box.createHorizontalStrut(20));
        gridVertex.add(Box.createHorizontalStrut(20));
        gridVertex.add(Box.createHorizontalStrut(20));
        gridVertex.add(Box.createHorizontalStrut(20));
        gridVertex.add(Box.createHorizontalStrut(20));

        removeVertexButton = new JButton("Remove Vertex");
        removeVertexButton.addActionListener(new MainWindow.OnRemVertexButton());
        removeVertexButton.setToolTipText("Remove Vertex");
        gridVertex.add(removeVertexButton);

        gridVertex.add(new JLabel("Vertex Number"));
        numberVertexField = new JTextField();
        gridVertex.add(numberVertexField);

        addVertexButton = new JButton("Add Vertex");
        addVertexButton.addActionListener(new MainWindow.OnAddVertexButton());
        addVertexButton.setToolTipText("Add Vertex");
        gridVertex.add(addVertexButton);

        return gridVertex;
    }

    private JPanel controlPanels() {
        JPanel gridControl = new JPanel(new GridLayout(2, 1, 5, 0));
        gridControl.setBorder(BorderFactory.createEmptyBorder(3, 3, 6, 3));

        gridControl.add(Box.createHorizontalStrut(20));
        gridControl.add(Box.createHorizontalStrut(20));
        gridControl.add(Box.createHorizontalStrut(20));
        gridControl.add(Box.createHorizontalStrut(20));

        kruskalButton = new JButton("Start Kruskal");
        kruskalButton.addActionListener(new MainWindow.OnKruskalButton());
        kruskalButton.setToolTipText("Start Kruskal");
        gridControl.add(kruskalButton);

        primButton = new JButton("Start Prim");
        primButton.addActionListener(new MainWindow.OnPrimButton());
        primButton.setToolTipText("Start Prim");
        gridControl.add(primButton);

        return gridControl;
    }

    class OnRemEdgeButton implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (firstVertexField.getText().length() == 0 ||
                    secondVertexField.getText().length() == 0 ||
                    weightField.getText().length() == 0) {
                JOptionPane.showMessageDialog(removeEdgeButton,
                        "Edge not specified");
                return;
            }
            try {
                int first = Integer.parseInt(firstVertexField.getText().trim());
                int second = Integer.parseInt(secondVertexField.getText().trim());
                int weight = Integer.parseInt(weightField.getText().trim());
                graph.removeEdge(new Vertex(first), new Vertex(second), weight);
                originalGraphText.setText(outGraph(graph));

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(removeEdgeButton,
                        "Fields incorrect");
                return;
            }
        }
    }

    class OnAddEdgeButton implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (firstVertexField.getText().length() == 0 ||
                    secondVertexField.getText().length() == 0 ||
                    weightField.getText().length() == 0) {
                JOptionPane.showMessageDialog(addEdgeButton,
                        "Edge not specified");
                return;
            }
            try {
                int first = Integer.parseInt(firstVertexField.getText().trim());
                int second = Integer.parseInt(secondVertexField.getText().trim());
                int weight = Integer.parseInt(weightField.getText().trim());
                graph.addEdge(new Vertex(first), new Vertex(second), weight);
                originalGraphText.setText(outGraph(graph));

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(addEdgeButton,
                        "Fields incorrect");
                return;
            }
        }
    }

    class OnRemVertexButton implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (numberVertexField.getText().length() == 0) {
                JOptionPane.showMessageDialog(removeVertexButton,
                        "Vertex not specified");
                return;
            }
            try {
                int number = Integer.parseInt(numberVertexField.getText().trim());
                graph.removeVertex(new Vertex(number));
                originalGraphText.setText(outGraph(graph));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(removeVertexButton,
                        "Field incorrect");
                return;
            }
        }
    }

    class OnAddVertexButton implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (numberVertexField.getText().length() == 0) {
                JOptionPane.showMessageDialog(addVertexButton,
                        "Vertex not specified");
                return;
            }
            try {
                int number = Integer.parseInt(numberVertexField.getText().trim());
                graph.addVertex(new Vertex(number));
                originalGraphText.setText(outGraph(graph));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(addVertexButton,
                        "Field incorrect");
                return;
            }
        }
    }

    class OnKruskalButton implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (graph.edges().size() != 0) {
                if (graph.isConnected()) {
                    Kruskal kruskal = new Kruskal();
                    Graph g = kruskal.getGraph(graph);
                    kruskalGraphText.setText(outGraph(g));
                }
                else {
                    kruskalGraphText.setText("Graph is not connected");
                }
            }
        }
    }

    class OnPrimButton implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (graph.edges().size() != 0) {
                if (graph.isConnected()) {
                    Prim prim = new Prim();
                    Graph g = prim.getGraph(graph);
                    primGraphText.setText(outGraph(g));
                }
                else {
                    primGraphText.setText("Graph is not connected");
                }
            }
        }
    }

    private String outGraph(Graph graph) {
        if (graph == null) {
            return "No graph";
        }

        String graphString = graph.toString();
        if (graphString == "") {
            return "Graph is empty";
        }

        return graphString;
    }
}
