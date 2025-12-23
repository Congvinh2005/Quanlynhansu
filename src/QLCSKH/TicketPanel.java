/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QLCSKH;

/**
 *
 * @author AS
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TicketPanel extends JPanel {
    private User user;
    private JTable table;
    private DefaultTableModel tableModel;

    public TicketPanel(User user) {
        this.user = user;
        setLayout(new BorderLayout());
        
        // Thanh công cụ (Toolbar)
        JPanel toolBar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnAdd = new JButton("Tạo yêu cầu mới");
        JButton btnRefresh = new JButton("Làm mới");
        
        // Chỉ hiện nút Xóa nếu là Admin
        if(user.getRole().equals("ADMIN")) {
            JButton btnDelete = new JButton("Xóa yêu cầu");
            toolBar.add(btnDelete);
        }
        
        toolBar.add(btnAdd);
        toolBar.add(btnRefresh);
        add(toolBar, BorderLayout.NORTH);

        // Bảng dữ liệu (Table)
        String[] columnNames = {"ID", "Tiêu đề", "Khách hàng", "Trạng thái", "Người xử lý"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        
        // Load dữ liệu giả (Sau này thay bằng DB)
        loadFakeData();

        // Đặt bảng vào thanh cuộn (ScrollPane) - Bắt buộc phải có cái này mới hiện tiêu đề cột
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void loadFakeData() {
        // Giả lập dữ liệu từ Database
        tableModel.addRow(new Object[]{1, "Lỗi mạng", "Nguyễn Văn A", "OPEN", "Chưa gán"});
        tableModel.addRow(new Object[]{2, "Tư vấn gói cước", "Trần Thị B", "IN_PROGRESS", "staff1"});
        
        // Nếu là Staff, chỉ nên hiện ticket được gán cho họ (Logic xử lý ở tầng DAO)
        if(user.getRole().equals("STAFF")){
             tableModel.addRow(new Object[]{3, "Khiếu nại", "Lê Văn C", "OPEN", user.getUsername()});
        }
    }
}
