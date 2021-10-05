
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("all")

public class Main extends javax.swing.JFrame implements ActionListener, ChangeListener {

	JTextArea salida = new JTextArea("", 0, 50);

	JTextArea entrada = new JTextArea("", 0, 50);

	public Main() {

		setTitle("Query Generator");

		JMenuBar menuBar = new JMenuBar();

		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("Open");

		menuBar.add(mnNewMenu);

		JMenuItem mntmNewMenuItem = new JMenuItem("Config");

		menuBar.add(mntmNewMenuItem);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Open Folder");

		menuBar.add(mntmNewMenuItem_1);

		initComponents();

		this.setVisible(true);
	}

	private void initComponents() {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

		setResizable(false);

		JButton btnNewButton = new JButton("Generate SQL");

		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));

		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
//				CREATE TABLE `albaranes` (
//						  `id` varchar(40) COLLATE latin1_spanish_ci NOT NULL,
//						  `id_cliente` int(11) NOT NULL,
//						  `id_pedido` int(11) NOT NULL
//						) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

//				CREATE TABLE `albaranes` (
//						  `id` varchar(40) COLLATE latin1_spanish_ci NOT NULL,
//						  `cliente` int(11) NOT NULL,
//						  `id_pedido` int(11) NOT NULL,
//				  		  `avatar` varchar(30) NOT NULL,
//				          `id_pedido` int(11) NOT NULL,
//						) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

				LinkedList<String> tablaOld = new LinkedList<String>();

				tablaOld.add("CREATE TABLE albaranes");

				tablaOld.add("id varchar");

				tablaOld.add("id_cliente int");

				tablaOld.add("pedido VARCHAR");

				tablaOld.add("FIN_TABLA");

				tablaOld.add("CREATE TABLE albaranes2");

				tablaOld.add("id2 varchar");

				tablaOld.add("id_cliente2 int");

				tablaOld.add("pedido2 VARCHAR");

				tablaOld.add("FIN_TABLA");

//				INSERT INTO `albaranes` (`id`, `id_cliente`, `id_pedido`) VALUES
//				('1806690.pdf', 20, 24);

				String sql;

				// for() {}

				try {

					sql = "INSERT INTO ";

				}

				catch (Exception e1) {
					e1.printStackTrace();
				}

				System.out.println(entrada.getText());

			}

		});

		JScrollPane scrollPane_1 = new JScrollPane((Component) null);

		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		JScrollPane scrollPane_2 = new JScrollPane((Component) null);
		scrollPane_2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.TRAILING).addGroup(layout.createSequentialGroup()
				.addContainerGap(61, Short.MAX_VALUE)
				.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 365, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 365, GroupLayout.PREFERRED_SIZE))
				.addGap(50))
				.addGroup(Alignment.LEADING, layout.createSequentialGroup().addGap(184).addComponent(btnNewButton)
						.addContainerGap(203, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(36)
						.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 227, GroupLayout.PREFERRED_SIZE)
						.addGap(33).addComponent(btnNewButton).addGap(32)
						.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 210, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(37, Short.MAX_VALUE)));

		salida = new JTextArea("", 0, 50);

		salida.setWrapStyleWord(true);

		salida.setLineWrap(true);

		salida.setFont(new Font("Monospaced", Font.PLAIN, 20));

		salida.setBackground(Color.WHITE);

		scrollPane_2.setViewportView(salida);

		entrada = new JTextArea("", 0, 50);

		entrada.setWrapStyleWord(true);

		entrada.setLineWrap(true);

		entrada.setFont(new Font("Monospaced", Font.PLAIN, 20));

		entrada.setBackground(Color.WHITE);

		scrollPane_1.setViewportView(entrada);

		getContentPane().setLayout(layout);

		setSize(new Dimension(482, 670));

		setLocationRelativeTo(null);

	}

	public static void main(String[] args) {

		LinkedList<String> tablaNew = new LinkedList<String>();

		List<String> tablas;

		tablaNew.add("CREATE TABLE test");

		tablaNew.add("id varchar");

		tablaNew.add("id_cliente int");

		tablaNew.add("pedido VARCHAR");

		tablaNew.add("avatar VARCHAR");

		tablaNew.add("FIN_TABLA");

		tablaNew.add("CREATE TABLE test2");

		tablaNew.add("id varchar");

		tablaNew.add("id_cliente int");

		tablaNew.add("pedido VARCHAR");

		tablaNew.add("avatar VARCHAR");

		tablaNew.add("FIN_TABLA");

		tablas = verDato(tablaNew);

	}

	private static List<String> verDato(LinkedList<String> tablaNew) {

		List<String> result = tablaNew.stream().filter(x -> x.contains("CREATE TABLE")).collect(Collectors.toList());

		int finTabla = tablaNew.indexOf("FIN_TABLA");

		for (int i = 0; i < result.size(); i++) {

			for (int y = 0; y < finTabla; y++) {

			}

			// result.set(i, result.get(i).substring(result.get(i).indexOf("CREATE TABLE") +
			// 1, result.get(i).length()));

		}

		System.out.println(result.get(0).substring(result.get(0).indexOf("TABLE") + 5, result.get(0).length()));

		return result;

	}

	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub

	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
