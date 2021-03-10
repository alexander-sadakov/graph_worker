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
	private JButton remEdgeButton;
	private JButton remVertexButton;
	private JButton addVertexButton;
	private JButton kruskalButton;
	private JButton primButton;

	private JTextArea originalGraphText;
	private JTextArea kruskalGraphText;
	private JTextArea primGraphText;

	private Graph graph = new Graph();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.getContentPane().add(textPanel(), BorderLayout.CENTER);

		JPanel grid = new JPanel(new GridLayout());
		grid.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
		grid.add(edgePanel());
		grid.add(vertexPanel());

		JPanel border = new JPanel(new BorderLayout());
		border.add(grid, BorderLayout.NORTH);
		border.add(controlPanel(), BorderLayout.SOUTH);

		frame.getContentPane().add(border, BorderLayout.SOUTH);
	}

	private JPanel edgePanel() {
		JPanel gridEdge = new JPanel(new GridLayout(3, 3, 5, 5));
		gridEdge.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 3));

		gridEdge.add(new JLabel("������ �������"));
		firstVertexField = new JTextField();
		gridEdge.add(firstVertexField);

		gridEdge.add(Box.createHorizontalStrut(20));

		gridEdge.add(new JLabel("������ �������"));
		secondVertexField = new JTextField();
		gridEdge.add(secondVertexField);

		remEdgeButton = new JButton("������� �����");
		remEdgeButton.addActionListener(new OnRemEdgeButton());
		remEdgeButton.setToolTipText("������� �����");
		gridEdge.add(remEdgeButton);

		gridEdge.add(new JLabel("��� �����"));
		weightField = new JTextField();
		gridEdge.add(weightField);

		addEdgeButton = new JButton("�������� �����");
		addEdgeButton.addActionListener(new OnAddEdgeButton());
		addEdgeButton.setToolTipText("�������� �����");
		gridEdge.add(addEdgeButton);

		return gridEdge;
	}

	private JPanel vertexPanel() {
		JPanel gridVertex = new JPanel(new GridLayout(3, 3, 5, 5));
		gridVertex.setBorder(BorderFactory.createEmptyBorder(0, 3, 0, 0));

		gridVertex.add(Box.createHorizontalStrut(20));
		gridVertex.add(Box.createHorizontalStrut(20));
		gridVertex.add(Box.createHorizontalStrut(20));
		gridVertex.add(Box.createHorizontalStrut(20));
		gridVertex.add(Box.createHorizontalStrut(20));

		remVertexButton = new JButton("������� �������");
		remVertexButton.addActionListener(new OnRemVertexButton());
		remVertexButton.setToolTipText("������� �������");
		gridVertex.add(remVertexButton);

		gridVertex.add(new JLabel("����� �������"));
		numberVertexField = new JTextField();
		gridVertex.add(numberVertexField);

		addVertexButton = new JButton("�������� �������");
		addVertexButton.addActionListener(new OnAddVertexButton());
		addVertexButton.setToolTipText("�������� �������");
		gridVertex.add(addVertexButton);

		return gridVertex;
	}

	private JPanel controlPanel() {
		JPanel gridControl = new JPanel(new GridLayout(2, 1, 5, 0));
		gridControl.setBorder(BorderFactory.createEmptyBorder(3, 3, 6, 3));

		gridControl.add(Box.createHorizontalStrut(20));
		gridControl.add(Box.createHorizontalStrut(20));
		gridControl.add(Box.createHorizontalStrut(20));
		gridControl.add(Box.createHorizontalStrut(20));

		kruskalButton = new JButton("�������");
		kruskalButton.addActionListener(new OnKruskalButton());
		kruskalButton.setToolTipText("�������");
		gridControl.add(kruskalButton);

		primButton = new JButton("����");
		primButton.addActionListener(new OnPrimButton());
		primButton.setToolTipText("����");
		gridControl.add(primButton);

		return gridControl;
	}

	private JPanel textPanel() {
		JPanel textPanel = new JPanel(new GridLayout(1, 3, 5, 0));
		textPanel.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));

		JPanel originalGraphLayout = new JPanel(new BorderLayout());
		originalGraphLayout.add(new JLabel("�������� ����"), BorderLayout.NORTH);
		originalGraphText = new JTextArea();
		originalGraphText.setEditable(false);
		originalGraphLayout.add(originalGraphText, BorderLayout.CENTER);
		textPanel.add(originalGraphLayout);

		JPanel kruskalGraphLayout = new JPanel(new BorderLayout());
		kruskalGraphLayout.add(new JLabel("�������� ��������"), BorderLayout.NORTH);
		kruskalGraphText = new JTextArea();
		kruskalGraphText.setEditable(false);
		kruskalGraphLayout.add(kruskalGraphText, BorderLayout.CENTER);
		textPanel.add(kruskalGraphLayout);

		JPanel primGraphLayout = new JPanel(new BorderLayout());
		primGraphLayout.add(new JLabel("�������� �����"), BorderLayout.NORTH);
		primGraphText = new JTextArea();
		primGraphText.setEditable(false);
		primGraphLayout.add(primGraphText, BorderLayout.CENTER);
		textPanel.add(primGraphLayout);

		return textPanel;
	}

	private String outGraph(Graph g) {
		if (g == null) {
			return "���� �� �����";
		}
		
		if (g.vertexes().size() == 0) {
			return "���� ����";
		}
				
		Vector<Vertex> vertexList = g.vertexes();
		Vector<Vector<Edge>> vertexEdges = g.incidenceList();
		
		Collections.sort(vertexList);
		
		String str = new String("");
		
		for (int i = 0; i < vertexList.size(); i++) {
			Vertex vertex = vertexList.get(i);
			str += vertex.number() + ": ";
			
			for (Edge edge: vertexEdges.get(i)) {
				str += edge.adjacentVertex(vertex).number() + " (";
				str += edge.weight() + ")";
				str += " -> ";
			}
			
			str += "\n";
		}
		
		return str;
	}
	
	class OnRemEdgeButton implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			if (firstVertexField.getText().length() == 0 || 
					secondVertexField.getText().length() == 0 || 
					weightField.getText().length() == 0) {
				JOptionPane.showMessageDialog(remEdgeButton, "������������ ����� ��� ��������");
				return;
			}
			try {
				int first = Integer.parseInt(firstVertexField.getText().trim());
				int second = Integer.parseInt(secondVertexField.getText().trim());
				int weight = Integer.parseInt(weightField.getText().trim());
				graph.remEdge(new Vertex(first), new Vertex(second), weight);
				originalGraphText.setText(outGraph(graph));
				
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(remEdgeButton, "�������� ������ �����");
				return;
			}
		}
	}

	class OnAddEdgeButton implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			if (firstVertexField.getText().length() == 0 || 
					secondVertexField.getText().length() == 0 ||
					weightField.getText().length() == 0) {
				JOptionPane.showMessageDialog(addEdgeButton, "������������ ����� ��� ����������");
				return;
			}
			try {
				int first = Integer.parseInt(firstVertexField.getText().trim());
				int second = Integer.parseInt(secondVertexField.getText().trim());
				int weight = Integer.parseInt(weightField.getText().trim());
				graph.addEdge(new Vertex(first), new Vertex(second), weight);
				originalGraphText.setText(outGraph(graph));
				
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(addEdgeButton, "�������� ������ �����");
				return;
			}
		}
	}

	class OnRemVertexButton implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			if (numberVertexField.getText().length() == 0) {
				JOptionPane.showMessageDialog(remVertexButton, "������������ ������� ��� ��������");
				return;
			}
			try {
				int number = Integer.parseInt(numberVertexField.getText().trim());
				graph.remVertex(new Vertex(number));
				originalGraphText.setText(outGraph(graph));
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(remVertexButton, "�������� ������ �����");
				return;
			}
		}
	}

	class OnAddVertexButton implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			if (numberVertexField.getText().length() == 0) {
				JOptionPane.showMessageDialog(addVertexButton, "������������ ������� ��� ����������");
				return;
			}
			try {
				int number = Integer.parseInt(numberVertexField.getText().trim());
				graph.addVertex(new Vertex(number));
				originalGraphText.setText(outGraph(graph));
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(addVertexButton, "�������� ������ �����");
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
					kruskalGraphText.setText("���� �� �������");
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
					primGraphText.setText("���� �� �������");
				}
			}
		}
	}
}
