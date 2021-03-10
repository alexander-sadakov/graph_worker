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
        originalGraphLayout.add(new JLabel("???????? ????"), BorderLayout.NORTH);
        originalGraphText = new JTextArea();
        originalGraphText.setEditable(false);
        originalGraphLayout.add(originalGraphText, BorderLayout.CENTER);
        textPanel.add(originalGraphLayout);

        JPanel kruskalGraphLayout = new JPanel(new BorderLayout());
        kruskalGraphLayout.add(new JLabel("???????? ????????"), BorderLayout.NORTH);
        kruskalGraphText = new JTextArea();
        kruskalGraphText.setEditable(false);
        kruskalGraphLayout.add(kruskalGraphText, BorderLayout.CENTER);
        textPanel.add(kruskalGraphLayout);

        JPanel primGraphLayout = new JPanel(new BorderLayout());
        primGraphLayout.add(new JLabel("???????? ?????"), BorderLayout.NORTH);
        primGraphText = new JTextArea();
        primGraphText.setEditable(false);
        primGraphLayout.add(primGraphText, BorderLayout.CENTER);
        textPanel.add(primGraphLayout);

        return textPanel;
    }

    private JPanel edgePanels() {
        JPanel gridEdge = new JPanel(new GridLayout(3, 3, 5, 5));
        gridEdge.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 3));

        gridEdge.add(new JLabel("?????? ???????"));
        firstVertexField = new JTextField();
        gridEdge.add(firstVertexField);

        gridEdge.add(Box.createHorizontalStrut(20));

        gridEdge.add(new JLabel("?????? ???????"));
        secondVertexField = new JTextField();
        gridEdge.add(secondVertexField);

        removeEdgeButton = new JButton("??????? ?????");
        removeEdgeButton.addActionListener(new MainWindow.OnRemEdgeButton());
        removeEdgeButton.setToolTipText("??????? ?????");
        gridEdge.add(removeEdgeButton);

        gridEdge.add(new JLabel("??? ?????"));
        weightField = new JTextField();
        gridEdge.add(weightField);

        addEdgeButton = new JButton("???????? ?????");
        addEdgeButton.addActionListener(new MainWindow.OnAddEdgeButton());
        addEdgeButton.setToolTipText("???????? ?????");
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

        removeVertexButton = new JButton("??????? ???????");
        removeVertexButton.addActionListener(new MainWindow.OnRemVertexButton());
        removeVertexButton.setToolTipText("??????? ???????");
        gridVertex.add(removeVertexButton);

        gridVertex.add(new JLabel("????? ???????"));
        numberVertexField = new JTextField();
        gridVertex.add(numberVertexField);

        addVertexButton = new JButton("???????? ???????");
        addVertexButton.addActionListener(new MainWindow.OnAddVertexButton());
        addVertexButton.setToolTipText("???????? ???????");
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

        kruskalButton = new JButton("???????");
        kruskalButton.addActionListener(new MainWindow.OnKruskalButton());
        kruskalButton.setToolTipText("???????");
        gridControl.add(kruskalButton);

        primButton = new JButton("????");
        primButton.addActionListener(new MainWindow.OnPrimButton());
        primButton.setToolTipText("????");
        gridControl.add(primButton);

        return gridControl;
    }
}
