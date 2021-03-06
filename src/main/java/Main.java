
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

				indicesTablasOld.clear();

				tiposTablasOld.clear();

				finTablasOld.clear();

				indicesTablasNew.clear();

				tiposTablasNew.clear();

				finTablasNew.clear();

				inserts.clear();

				cabecerasTablas.clear();

				numeroCamposTablasOld.clear();

				numeroCamposTablasNew.clear();

				LinkedList<String> tablaOld = new LinkedList<String>();

				LinkedList<String> tablaNew = new LinkedList<String>();

				List<String> tablas;

				tablaOld.add("CREATE TABLE familias");

				tablaOld.add("id int");

				tablaOld.add("nombre varchar");

				tablaOld.add("FIN_TABLA");

				tablaNew.add("CREATE TABLE provincias");

				tablaNew.add("NOMBRE varchar");

				tablaNew.add("FIN_TABLA");

				verDato(tablaOld, true);

				verDato(tablaNew, false);

				if (!finTablasNew.isEmpty()) {

					try {

						String[] inputInserts;

						LinkedList<String> entradaInserts = new LinkedList<String>();

						String texto = entrada.getText().replace("`", "");

						inputInserts = texto.split(" ");

						int indice = 0;

						String reconstruirInserts = "";

						for (int i = 0; i < inputInserts.length; i++) {

							reconstruirInserts += inputInserts[indice];

							indice++;

						}

						inputInserts = reconstruirInserts.split(",");

						for (int i = 0; i < inputInserts.length; i++) {

							entradaInserts.add(inputInserts[i]);

						}

						LinkedList<Integer> descontar = new LinkedList<Integer>();

						for (int x = 0; x < numeroCamposTablasNew.size(); x++) {

							descontar.add(numeroCamposTablasNew.get(x) - 3);

						}

						int contador = 0;

						for (int i = 0; i < entradaInserts.size(); i++) {

							if (contador < descontar.size() && descontar.get(contador) >= 0) {

								i += descontar.get(contador);

								contador++;

							}

							try {

								entradaInserts.set(i, limpiarCadena(entradaInserts.get(i)));

							}

							catch (Exception e1) {

							}

							if (!entradaInserts.get(i).isEmpty()) {

								if (entradaInserts.get(i).contains("VALUES")) {

									entradaInserts.set(i,
											entradaInserts.get(i).substring(entradaInserts.get(i).indexOf("VALUES") + 6,
													entradaInserts.get(i).length()));

								}

								inserts.add(entradaInserts.get(i));

							}

						}

						int empezarInsers = 0;

						indice = 0;

						String sql = "";

						for (int x = 0; x < cabecerasTablas.size(); x++) {

							sql = "INSERT INTO " + cabecerasTablas.get(x) + " (";

							for (int i = 0; i < indicesTablasNew.size(); i++) {

								sql += indicesTablasNew.get(i);

								i++;

								if (i < indicesTablasNew.size()) {

									sql += " , ";

								}

								i--;

							}
						}

						sql += ") VALUES ";

						indice = 0;

						contador = 0;

						int numeroInserts = 0;

						int valoresPorDefecto = 0;

						for (int i = 0; i < numeroCamposTablasNew.size(); i++) {

							contador = numeroCamposTablasNew.get(indice);

							contador--;

							valoresPorDefecto = numeroCamposTablasOld.get(indice);

							valoresPorDefecto--;

							indice = 1;

							System.out.println("NUMERO INSERTS: " + inserts.size());

							for (int xy = 0; xy < inserts.size(); xy++) {

								sql += "('" + inserts.get(xy);

								if (indice > 0 && indice % contador == 0) {

									sql += "'), ";

									indice = 0;

								}

								numeroInserts++;

								// if (indice < numeroCamposTablasNew.size()) {

								indice++;

								// }

								if (numeroInserts > contador) {
									numeroInserts = 0;
								}

							}

						}

						numeroInserts++;

						System.out.println(numeroInserts + " - " + contador);

						if (numeroInserts < contador) {

							for (int i = 0; i < contador - numeroInserts; i++) {

								sql += "DEFAULT,";
							}

							sql += " --),-- ";

						}

						sql += ";";

						salida.setText(sql);

					}

					catch (Exception e1) {
						e1.printStackTrace();
					}

				}
			}

			private String limpiarCadena(String cadena) {

				cadena = cadena.replace("\n", "");

				cadena = cadena.replace("\r", "");

				cadena = cadena.replace("\t", "");

				cadena = cadena.replace("  ", " ");

				cadena = cadena.replace("(", "");

				cadena = cadena.replace(")", "");

				cadena = cadena.trim();

				return cadena;

			}
		});

		JScrollPane scrollPane_1 = new JScrollPane((Component) null);

		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		JScrollPane scrollPane_2 = new JScrollPane((Component) null);
		scrollPane_2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING, layout
				.createSequentialGroup().addContainerGap(51, Short.MAX_VALUE)
				.addGroup(layout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(btnNewButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
								GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(scrollPane_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE)
						.addComponent(scrollPane_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE))
				.addGap(50)));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(36)
						.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 227, GroupLayout.PREFERRED_SIZE)
						.addGap(33).addComponent(btnNewButton).addGap(32)
						.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 210, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(36, Short.MAX_VALUE)));

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

					busqueda -= contarIteraciones(tabla);

					numeroCamposTablasNew.add(busqueda);

				}

			}

			else {

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

					if (!datoIndice.contains("FOREIGN")) {

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

	private static int contarIteraciones(LinkedList<String> lista) {

		String cadena = "";

		int contador = 0;

		for (int i = 0; i < lista.size(); i++) {

			cadena += lista.get(i);

		}

		while (cadena.indexOf("FOREIGN") > -1) {

			cadena = cadena.substring(cadena.indexOf("FOREIGN") + "FOREIGN".length(), cadena.length());

			contador++;
		}

		return contador;
	}

	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub

	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
