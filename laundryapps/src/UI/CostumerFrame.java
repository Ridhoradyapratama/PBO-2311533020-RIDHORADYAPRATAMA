package UI;

import java.awt.EventQueue;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import DAO.CostumerRepo;
import model.Costumer;
import table.TableCostumer;

public class CostumerFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtNama;
    private JTextField txtAlamat;
    private JTextField txtNomorHp;
    private JTable table;
    private CostumerRepo custRepo;
    private String selectedId = null; 

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CostumerFrame frame = new CostumerFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void reset() {
        txtNama.setText("");
        txtAlamat.setText("");
        txtNomorHp.setText("");
        selectedId = null;
    }

    public void loadTable() {
        List<Costumer> ls = custRepo.show();
        TableCostumer model = new TableCostumer(ls);
        table.setModel(model);
    }

    public CostumerFrame() {
        custRepo = new CostumerRepo();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNama = new JLabel("Nama");
        lblNama.setBounds(10, 20, 80, 25);
        contentPane.add(lblNama);

        txtNama = new JTextField();
        txtNama.setBounds(100, 20, 200, 25);
        contentPane.add(txtNama);
        txtNama.setColumns(10);

        JLabel lblAlamat = new JLabel("Alamat");
        lblAlamat.setBounds(10, 60, 80, 25);
        contentPane.add(lblAlamat);

        txtAlamat = new JTextField();
        txtAlamat.setBounds(100, 60, 200, 25);
        contentPane.add(txtAlamat);
        txtAlamat.setColumns(10);

        JLabel lblNomorHp = new JLabel("Nomor HP");
        lblNomorHp.setBounds(10, 100, 80, 25);
        contentPane.add(lblNomorHp);

        txtNomorHp = new JTextField();
        txtNomorHp.setBounds(100, 100, 200, 25);
        contentPane.add(txtNomorHp);
        txtNomorHp.setColumns(10);

        JButton btnSave = new JButton("Save");
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (validateInput()) {
                    Costumer cust = new Costumer();
                    cust.setNama(txtNama.getText());
                    cust.setAlamat(txtAlamat.getText());
                    cust.setNomorHp(txtNomorHp.getText());
                    custRepo.save(cust);
                    reset();
                    loadTable();
                    JOptionPane.showMessageDialog(null, "Costumer saved successfully!");
                }
            }
        });
        btnSave.setBounds(50, 150, 80, 30);
        contentPane.add(btnSave);

        JButton btnUpdate = new JButton("Update");
        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (selectedId != null && validateInput()) {
                    Costumer cust = new Costumer();
                    cust.setId(selectedId);
                    cust.setNama(txtNama.getText());
                    cust.setAlamat(txtAlamat.getText());
                    cust.setNomorHp(txtNomorHp.getText());
                    custRepo.update(cust);
                    reset();
                    loadTable();
                    JOptionPane.showMessageDialog(null, "Costumer updated successfully!");
                } else {
                    JOptionPane.showMessageDialog(null, "Select a costumer first!");
                }
            }
        });
        btnUpdate.setBounds(140, 150, 80, 30);
        contentPane.add(btnUpdate);

        JButton btnDelete = new JButton("Delete");
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (selectedId != null) {
                    custRepo.delete(selectedId); 
                    reset();
                    loadTable();
                    JOptionPane.showMessageDialog(null, "Costumer deleted successfully!");
                } else {
                    JOptionPane.showMessageDialog(null, "Select a costumer first!");
                }
            }
        });
        btnDelete.setBounds(230, 150, 80, 30);
        contentPane.add(btnDelete);

        JButton btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                reset();
            }
        });
        btnCancel.setBounds(320, 150, 80, 30);
        contentPane.add(btnCancel);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 200, 460, 250);
        contentPane.add(scrollPane);

        table = new JTable();
        scrollPane.setViewportView(table);
        
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                selectedId = table.getModel().getValueAt(row, 0).toString();
                txtNama.setText(table.getModel().getValueAt(row, 1).toString());
                txtAlamat.setText(table.getModel().getValueAt(row, 2).toString());
                txtNomorHp.setText(table.getModel().getValueAt(row, 3).toString());
            }
        });

        loadTable();
    }

    private boolean validateInput() {
        if (txtNama.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nama cannot be empty!");
            return false;
        }
        if (txtAlamat.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Alamat cannot be empty!");
            return false;
        }
        if (txtNomorHp.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nomor HP cannot be empty!");
            return false;
        }
        return true;
    }
}
