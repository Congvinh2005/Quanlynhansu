package kiemtra2.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import kiemtra2.controller.DiemRenLuyenController;

public class FrmDiemRenLuyen extends JFrame {

    private JComboBox<String> cboNamHoc;
    private JComboBox<String> cboHocKy;
    private JComboBox<String> cboNganh;

    private JRadioButton radTBCNam;
    private JRadioButton radTBCHocKy;

    private JButton btnXem;
    private JTable table;
    private DefaultTableModel tableModel;

    private DiemRenLuyenController controller;

    public FrmDiemRenLuyen() {
        setTitle("Điểm rèn luyện sinh viên");
        setSize(950, 520);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        controller = new DiemRenLuyenController(this);

        initUI();
        event();
    }

    private void initUI() {
        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));

        cboNamHoc = new JComboBox<>(new String[]{"2022-2023", "2023-2024"});
        cboHocKy = new JComboBox<>(new String[]{"1", "2"});
        cboNganh = new JComboBox<>(new String[]{"Ngành chính", "Ngành phụ"});

        radTBCNam = new JRadioButton("Điểm cuối khóa là TBC các năm");
        radTBCHocKy = new JRadioButton("Điểm cuối khóa là TBC các học kỳ");

        // chọn mặc định
        radTBCHocKy.setSelected(true);

        ButtonGroup group = new ButtonGroup();
        group.add(radTBCNam);
        group.add(radTBCHocKy);

        btnXem = new JButton("Xem kết quả");

        top.add(new JLabel("Năm học"));
        top.add(cboNamHoc);
        top.add(new JLabel("Học kỳ"));
        top.add(cboHocKy);

        // radio button nằm ngay dưới
        top.add(radTBCNam);
        top.add(radTBCHocKy);

        top.add(btnXem);
        top.add(new JLabel("Ngành"));
        top.add(cboNganh);

        JLabel lblTitle = new JLabel("Điểm rèn luyện của sinh viên lớp 71DCTM21");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitle.setForeground(Color.RED);
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);

        tableModel = new DefaultTableModel(
                new Object[]{"STT", "Mã sinh viên", "Họ và tên", "Tổng điểm (100)"},
                0
        );
        table = new JTable(tableModel);

        controller.loadFullData();

        add(top, BorderLayout.NORTH);
        add(lblTitle, BorderLayout.CENTER);
        add(new JScrollPane(table), BorderLayout.SOUTH);
    }

    private void event() {
        btnXem.addActionListener(e -> controller.loadData());
    }

    // ===== GETTER cho Controller =====
    public String getCboNamHoc() {
        return cboNamHoc.getSelectedItem().toString();
    }

    public String getCboHocKy() {
        return cboHocKy.getSelectedItem().toString();
    }

    public boolean isTBCNam() {
        return radTBCNam.isSelected();
    }

    public boolean isTBCHocKy() {
        return radTBCHocKy.isSelected();
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public static void main(String[] args) {
        new FrmDiemRenLuyen().setVisible(true);
    }
}
