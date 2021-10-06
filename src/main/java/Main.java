
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

	static LinkedList<String> indicesTablasOld = new LinkedList<String>();

	static LinkedList<String> tiposTablasOld = new LinkedList<String>();

	static LinkedList<Integer> finTablasOld = new LinkedList<Integer>();

	static LinkedList<String> indicesTablasNew = new LinkedList<String>();

	static LinkedList<String> tiposTablasNew = new LinkedList<String>();

	static LinkedList<Integer> finTablasNew = new LinkedList<Integer>();

	static LinkedList<String> inserts = new LinkedList<String>();

	static LinkedList<String> cabecerasTablas = new LinkedList<String>();

	static LinkedList<Integer> numeroCamposTablasOld = new LinkedList<Integer>();

	static LinkedList<Integer> numeroCamposTablasNew = new LinkedList<Integer>();

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

				if (!finTablasNew.isEmpty()) {

					try {

						String[] entradaInserts;

						entradaInserts = entrada.getText().split(" ");

						int indice = 0;

						String reconstruirInserts = "";

						for (int i = 1; i < entradaInserts.length; i++) {

							reconstruirInserts += entradaInserts[indice];

							indice++;

						}

						entradaInserts = reconstruirInserts.split(",");

						for (int i = 0; i < entradaInserts.length; i++) {

							inserts.add(entradaInserts[i]);

						}

						int empezarInsers = 0;

						// inserts.add(entradaInserts);

						indice = 0;

						System.out.println("hay " + finTablasNew.size() + " tablas nuevas");

						String sql = "";

						for (int z = 0; z < finTablasNew.size(); z++) {

							sql = "INSERT INTO " + cabecerasTablas.get(z) + " (";

							empezarInsers = numeroCamposTablasNew.get(z);
							System.out.println("NUMERO DE COLUMNAS POR TABLA: " + empezarInsers);
							for (int i = 0; i < finTablasNew.get(z); i++) {

								i++;

								sql += indicesTablasNew.get(indice);

								if (i < finTablasNew.get(z)) {
									sql += " , ";
								}

								i--;

								indice++;

							}

							sql += ") VALUES";

							// System.out.println(numeroCamposTablasNew.size() + " ||-|| " +
							// numeroCamposTablasOld.size());

							for (int xy = empezarInsers; xy < entradaInserts.length; xy++) {

								sql += entradaInserts[xy];

								xy++;

								if (xy < finTablasNew.get(z)) {

									sql += " , ";

								}

								xy--;

							}

							sql += ";";

							System.out.println("SQL: " + sql);

							String insert;

							System.out.println("\n");

						}

					}

					catch (Exception e1) {
					}

				}
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

		LinkedList<String> tablaOld = new LinkedList<String>();

		LinkedList<String> tablaNew = new LinkedList<String>();

		List<String> tablas;

		tablaOld.add("CREATE TABLE test");

		tablaOld.add("11id varchar");

		tablaOld.add("11id_cliente int");
		tablaOld.add("avatar VARCHAR");
		tablaOld.add("pedido VARCHAR");
		tablaOld.add("avatar VARCHAR");
		tablaOld.add("avatar VARCHAR");

		tablaOld.add("FIN_TABLA");
		tablaOld.add("CREATE TABLE test");

		tablaOld.add("11id varchar");

		tablaOld.add("11id_cliente int");

		tablaOld.add("pedido VARCHAR");

		tablaOld.add("avatar VARCHAR");
		tablaOld.add("avatar VARCHAR");
		tablaOld.add("FIN_TABLA");

		tablaNew.add("CREATE TABLE test2");

		tablaNew.add("id2 WWvarchar");

		tablaNew.add("id_cliente2 WWint");

		tablaNew.add("pedido2 WWVARCHAR");

		tablaNew.add("avatar2 WWVARCHAR");

		tablaNew.add("FIN_TABLA");

		tablaNew.add("CREATE TABLE test3");

		tablaNew.add("222id2 WWvarchar");

		tablaNew.add("22333id_cliente2 WWint");

		tablaNew.add("22244pedido2 WWVARCHAR");

		tablaNew.add("25555avatar2 WWVARCHAR");

		tablaNew.add("FIN_TABLA");

		tablaNew.add("CREATE TABLE test3");

		tablaNew.add("222id2 WWvarchar");

		tablaNew.add("22333id_cliente2 WWint");

		tablaNew.add("FIN_TABLA");

		tablaNew.add("CREATE TABLE test3");

		tablaNew.add("222id2 WWvarchar");

		tablaNew.add("22333id_cliente2 WWint");

		tablaNew.add("22244pedido2 WWVARCHAR");

		tablaNew.add("FIN_TABLA");

		verDato(tablaOld, true);

		verDato(tablaNew, false);

		new Main().setVisible(true);

	}

	private static void verDato(LinkedList<String> tabla, boolean old) {

		List<String> result = tabla.stream().filter(x -> x.contains("CREATE TABLE")).collect(Collectors.toList());

		int finTabla = 0;

		int indice = 0;

		int vueltas = 0;

		int busqueda = 0;

		String datoIndice;

		String datoTipo;

		int contador = 0;

		LinkedList<Integer> busquedas = new LinkedList<Integer>();

		for (int i = 0; i < result.size(); i++) {

			vueltas = 0;

			busqueda = tabla.indexOf("FIN_TABLA");

			if (i == 0) {

				if (old) {

					numeroCamposTablasOld.add(busqueda);

				}

				else {

					numeroCamposTablasNew.add(busqueda);

				}

			}

			else {

				System.out.println(i);

				i--;

				if (old) {

					numeroCamposTablasOld.add((busqueda - numeroCamposTablasOld.get(i)) - 1);

				}

				else {

					busquedas.add(busqueda);

					if (i == 0) {

						numeroCamposTablasNew.add((busquedas.getLast() - numeroCamposTablasNew.get(i)) - 1);

					}

					else {

						--i;

						numeroCamposTablasNew.add((busquedas.getLast() - busquedas.get(i)) - 1);

						++i;

					}

				}

				i++;

			}

			finTabla = tabla.indexOf("FIN_TABLA");

			for (int y = indice; y < finTabla; y++) {

				try {

					vueltas++;

					if (vueltas == 1) {

						datoIndice = tabla.get(y).substring(tabla.get(y).indexOf("TABLE") + 5, tabla.get(y).length());

						if (datoIndice.equals("RTED")) {

							++y;

							datoIndice = tabla.get(y).substring(tabla.get(y).indexOf("TABLE") + 5,
									tabla.get(y).length());

						}

						datoIndice = datoIndice.trim();

						if (!old && !datoIndice.isEmpty()) {

							cabecerasTablas.add(datoIndice);

						}

						y++;

					}
					datoIndice = tabla.get(y).substring(0, tabla.get(y).indexOf(" "));

					datoTipo = tabla.get(y).substring(tabla.get(y).indexOf(" ") + 1, tabla.get(y).length());

					if (old) {

						indicesTablasOld.add(datoIndice);

						tiposTablasOld.add(datoTipo);

					}

					else {

						indicesTablasNew.add(datoIndice);

						tiposTablasNew.add(datoTipo);
					}

				}

				catch (Exception e) {

					vueltas = 0;

				}

			}

			if (old) {

				finTablasOld.add(vueltas);

			}

			else {

				finTablasNew.add(vueltas);

			}

			indice = finTabla;

			tabla.set(finTabla, "INSERTED");

		}

	}

	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub

	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
