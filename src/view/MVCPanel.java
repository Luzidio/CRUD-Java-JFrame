package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Model.Estudante;
import controller.ControllerEstudante;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class MVCPanel extends JFrame {

	private JPanel contentPane;
	private JTextField txtCodigo;
	private JTextField txtNome;
	private JTextField txtTeste1;
	private JTextField txtTeste2;
	private JTable table;

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MVCPanel frame = new MVCPanel();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MVCPanel() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 590, 465);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 560, 46);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblCodigo = new JLabel("Codigo");
		lblCodigo.setBounds(10, 11, 46, 14);
		panel.add(lblCodigo);

		txtCodigo = new JTextField();
		txtCodigo.setBounds(55, 8, 32, 20);
		panel.add(txtCodigo);
		txtCodigo.setColumns(10);

		txtNome = new JTextField();
		txtNome.setColumns(10);
		txtNome.setBounds(139, 8, 178, 20);
		panel.add(txtNome);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(97, 11, 32, 14);
		panel.add(lblNome);

		txtTeste1 = new JTextField();
		txtTeste1.setColumns(10);
		txtTeste1.setBounds(367, 8, 67, 20);
		panel.add(txtTeste1);

		JLabel lblTeste1 = new JLabel("Teste 1");
		lblTeste1.setBounds(327, 11, 46, 14);
		panel.add(lblTeste1);

		JLabel lblTeste2 = new JLabel("Teste 2");
		lblTeste2.setBounds(444, 11, 46, 14);
		panel.add(lblTeste2);

		txtTeste2 = new JTextField();
		txtTeste2.setColumns(10);
		txtTeste2.setBounds(489, 8, 60, 20);
		panel.add(txtTeste2);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 62, 560, 46);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JButton btnAdiconar = new JButton("Adicionar");
		btnAdiconar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		btnAdiconar.setBounds(10, 11, 89, 23);
		panel_1.add(btnAdiconar);

		JButton btnListar = new JButton("Listar");
		btnListar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		btnListar.setBounds(157, 11, 89, 23);
		panel_1.add(btnListar);

		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		btnAtualizar.setBounds(304, 11, 89, 23);
		panel_1.add(btnAtualizar);

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		btnEliminar.setBounds(433, 11, 89, 23);
		panel_1.add(btnEliminar);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 119, 560, 296);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 540, 274);
		panel_2.add(scrollPane);

		btnAdiconar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int codigo = Integer.parseInt(txtCodigo.getText().toString());
				String nome = txtNome.getText().toString();
				double nota1 = Double.parseDouble(txtTeste1.getText().toString());
				double nota2 = Double.parseDouble(txtTeste2.getText().toString());
				ControllerEstudante.adicionar(codigo, nome, nota1, nota2);
				limparCampos();
			}
		});

		btnListar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				ArrayList<Estudante> listaDeEstudantes = ControllerEstudante.listar();
				model.setRowCount(0);

				for (Estudante estudante : listaDeEstudantes) {
					int codigo = estudante.getCodigo();
					String nome = estudante.getNome();
					double nota1 = estudante.getNota1();
					double nota2 = estudante.getNota2();
					double media = estudante.calcularMedia();
					model.addRow(new Object[] { codigo, nome, nota1, nota2, media });
				}
			}
		});

		btnEliminar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ControllerEstudante.remover(table.getSelectedRow());
			}
		});

		btnAtualizar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Estudante estudante = ControllerEstudante.listar().get(table.getSelectedRow());
				estudante.setNome(txtNome.getText().toString());
				estudante.setNota1(Double.parseDouble(txtTeste1.getText().toString()));
				estudante.setNota2(Double.parseDouble(txtTeste2.getText().toString()));
				limparCampos();
			}
		});

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				Estudante estudante = ControllerEstudante.listar().get(table.getSelectedRow());

				txtCodigo.setEnabled(false);
				txtCodigo.setText(String.valueOf(estudante.getCodigo()));
				txtNome.setText(estudante.getNome().toString());
				txtTeste1.setText(String.valueOf(estudante.getNota1()));
				txtTeste2.setText(String.valueOf(estudante.getNota2()));

			}
		});

		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Codigo", "Nome", "Nota 1", "Nota 2", "Media" }));
		scrollPane.setViewportView(table);
	}

	private void limparCampos() {
		txtCodigo.setText("");
		txtNome.setText("");
		txtTeste1.setText("");
		txtTeste2.setText("");
	}
}
