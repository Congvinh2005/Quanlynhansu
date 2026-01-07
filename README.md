# Ứng Dụng Quản Lý Nhân Sự (QLNS)

**Tác giả:** Vinh  
**Ngày tạo:** 06/01/2026

## Mô Tả Dự Án

Đây là một ứng dụng quản lý nhân sự được xây dựng bằng Java Swing theo mô hình MVC (Model-View-Controller). Ứng dụng giúp quản lý thông tin nhân viên, phòng ban, chức vụ, lương, phụ cấp, thưởng và tài khoản người dùng trong một tổ chức.

## Kiến Trúc MVC

Dự án được tổ chức theo mô hình MVC với các thành phần chính:

### Model (Mô hình)
- Chứa các lớp đại diện cho dữ liệu và logic nghiệp vụ
- Các lớp model bao gồm:
    - `NhanVien.java` - Thông tin nhân viên
    - `PhongBan.java` - Thông tin phòng ban
    - `ChucVu.java` - Thông tin chức vụ
    - `Luong.java` - Thông tin lương
    - `PhuCap.java` - Thông tin phụ cấp
    - `Thuong.java` - Thông tin thưởng
    - `TaiKhoan.java` - Thông tin tài khoản người dùng

### View (Giao diện)
- Chứa các lớp giao diện người dùng sử dụng Java Swing
- Các form giao diện bao gồm:
    - `FrmLogin.java` - Giao diện đăng nhập
    - `FrmMain.java` - Giao diện chính
    - `FrmNhanVien.java` - Quản lý nhân viên
    - `FrmPhongBan.java` - Quản lý phòng ban
    - `FrmChucVu.java` - Quản lý chức vụ
    - `FrmLuong.java` - Quản lý lương
    - `FrmPhuCap.java` - Quản lý phụ cấp
    - `FrmThuong.java` - Quản lý thưởng
    - `FrmQLTK.java` - Quản lý tài khoản
    - Và các giao diện khác...

### Controller (Điều khiển)
- Chứa các lớp điều khiển xử lý logic nghiệp vụ và tương tác giữa Model và View
- Các controller bao gồm:
    - `LoginController.java` - Xử lý đăng nhập
    - `NhanVienController.java` - Xử lý nghiệp vụ nhân viên
    - `PhongBanController.java` - Xử lý nghiệp vụ phòng ban
    - `ChucVuController.java` - Xử lý nghiệp vụ chức vụ
    - `LuongController.java` - Xử lý nghiệp vụ lương
    - `PhuCapController.java` - Xử lý nghiệp vụ phụ cấp
    - `ThuongController.java` - Xử lý nghiệp vụ thưởng
    - `TaiKhoanController.java` - Xử lý nghiệp vụ tài khoản
    - Và các controller khác...

### DAO (Data Access Object)
- Chứa các lớp truy cập dữ liệu để tương tác với cơ sở dữ liệu
- Các lớp DAO bao gồm:
    - `NhanVienDAO.java` - Truy cập dữ liệu nhân viên
    - `PhongBanDAO.java` - Truy cập dữ liệu phòng ban
    - `ChucVuDAO.java` - Truy cập dữ liệu chức vụ
    - `LuongDAO.java` - Truy cập dữ liệu lương
    - `PhuCapDAO.java` - Truy cập dữ liệu phụ cấp
    - `ThuongDAO.java` - Truy cập dữ liệu thưởng
    - `TaiKhoanDAO.java` - Truy cập dữ liệu tài khoản
    - Và các DAO khác...

### Utility
- `DBConnection.java` - Quản lý kết nối cơ sở dữ liệu
- `Session.java` - Quản lý phiên đăng nhập người dùng

## Cơ Sở Dữ Liệu

Ứng dụng sử dụng cơ sở dữ liệu MySQL với tên `nhansu`. Các bảng chính bao gồm:

- `nhanvien` - Thông tin nhân viên
- `phongban` - Thông tin phòng ban
- `chucvu` - Thông tin chức vụ
- `luong` - Thông tin lương
- `phucap` - Thông tin phụ cấp
- `thuong` - Thông tin thưởng
- `taikhoan` - Thông tin tài khoản người dùng
- `loaitaikhoan` - Thông tin loại tài khoản

## Tính Năng Chính

1. **Đăng nhập hệ thống**
    - Hỗ trợ phân quyền quản trị viên và nhân viên
    - Xác thực tài khoản người dùng

2. **Quản lý nhân viên**
    - Thêm, sửa, xóa thông tin nhân viên
    - Phân công nhân viên vào phòng ban và chức vụ

3. **Quản lý phòng ban**
    - Thêm, sửa, xóa thông tin phòng ban

4. **Quản lý chức vụ**
    - Thêm, sửa, xóa thông tin chức vụ

5. **Quản lý lương**
    - Thêm, sửa, xóa thông tin lương cơ bản

6. **Quản lý phụ cấp**
    - Thêm, sửa, xóa thông tin phụ cấp

7. **Quản lý thưởng**
    - Thêm, sửa, xóa thông tin thưởng

8. **Quản lý tài khoản**
    - Tạo, chỉnh sửa tài khoản người dùng
    - Phân quyền tài khoản

9. **Tra cứu thông tin**
    - Tìm kiếm và hiển thị thông tin nhân viên

10. **Báo cáo**
    - Xuất báo cáo lương, thống kê nhân sự
    -
## Tất cả đều có thể xuất excel

## Yêu Cầu Hệ Thống

- Java 17 trở lên
- MySQL 5.7 trở lên
- Maven 3.6.0 trở lên (đối với build)

## Cách Chạy Ứng Dụng

### 1. Cài đặt môi trường

- Cài đặt Java 17 hoặc mới hơn
- Cài đặt MySQL Server ( PHPadmin)
- Cài đặt Maven (nếu chưa có)

### 2. Cài đặt cơ sở dữ liệu

- Khởi động MySQL Server
- Tạo cơ sở dữ liệu `nhansu` bằng cách chạy file `quanlynhansu.sql`

### 3. Cấu hình kết nối cơ sở dữ liệu

- Mở file `src/main/java/QLNS/util/DBConnection.java`
- Cập nhật thông tin kết nối phù hợp với hệ thống của bạn:

```java
return DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/nhansu", "root", ""
);
```

### 4. Biên dịch và chạy ứng dụng

#### Cách 1: Sử dụng IDE (IntelliJ IDEA, Eclipse, NetBeans)
- Mở dự án trong IDE
- Chạy file `src/main/java/QLNS/app/Main.java`

#### Cách 2: Sử dụng Maven
- Mở terminal/command prompt trong thư mục dự án
- Chạy lệnh:

```bash
mvn clean compile
mvn exec:java -Dexec.mainClass="QLNS.app.Main"
```

#### Cách 3: Build JAR và chạy
- Build JAR:

```bash
mvn clean package
```

- Chạy JAR:

```bash
java -jar target/QLNS_Project-1.0-SNAPSHOT.jar
```

## Tài Khoản Mẫu

- **Tài khoản quản trị viên:**
    - Tên đăng nhập: `admin`
    - Mật khẩu: `123`

- **Tài khoản nhân viên:**
    - Tên đăng nhập: `vinh`
    - Mật khẩu: `123`

## Cấu Trúc Thư Mục

```
QLNS_Project/
├── src/
│   └── main/
│       └── java/
│           └── QLNS/
│               ├── app/          # Lớp chính chạy ứng dụng
│               ├── controller/   # Lớp điều khiển (Controller)
│               ├── dao/          # Lớp truy cập dữ liệu (DAO)
│               ├── model/        # Lớp mô hình (Model)
│               ├── util/         # Lớp tiện ích
│               └── view/         # Lớp giao diện (View)
├── pom.xml                      # File cấu hình Maven
└── README.md                    # Tài liệu hướng dẫn
```

## Công Nghệ Sử Dụng

- **Java 17** - Ngôn ngữ lập trình chính
- **Swing** - Thư viện GUI
- **MySQL** - Cơ sở dữ liệu
- **Maven** - Công cụ quản lý dự án
- **Apache POI** - Thư viện xử lý file Excel
- **JDBC** - Kết nối cơ sở dữ liệu

## Ghi Chú

- Ứng dụng sử dụng kết nối MySQL mặc định với tài khoản root không mật khẩu
- Có thể cần thay đổi thông tin kết nối trong `DBConnection.java` phù hợp với môi trường thực tế
- Dữ liệu mẫu đã được cung cấp trong file SQL để dễ dàng kiểm thử

## Tác Giả

** Vinh **  
Email: [daovinhgm2005@gmail.com]  
GitHub: [https://github.com/Congvinh2005]
