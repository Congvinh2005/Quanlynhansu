-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Máy chủ: localhost
-- Thời gian đã tạo: Th1 06, 2026 lúc 05:53 PM
-- Phiên bản máy phục vụ: 10.4.27-MariaDB
-- Phiên bản PHP: 7.4.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `nhansu`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chucvu`
--

CREATE TABLE `chucvu` (
  `MaChucVu` varchar(10) NOT NULL,
  `TenChucVu` varchar(100) DEFAULT NULL,
  `MoTa` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `chucvu`
--

INSERT INTO `chucvu` (`MaChucVu`, `TenChucVu`, `MoTa`) VALUES
('1', 'Bar trưởng', 'Trưởng phòng pha chế'),
('2', 'Order', 'order viên'),
('3', 'Nhân viên markerting', '6 tháng'),
('4', 'CEO', 'người sáng lập'),
('5', 'An ninh', 'nhân viên bảo vệ');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `loaitaikhoan`
--

CREATE TABLE `loaitaikhoan` (
  `LoaiTaiKhoan` varchar(50) NOT NULL,
  `TenLoai` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `loaitaikhoan`
--

INSERT INTO `loaitaikhoan` (`LoaiTaiKhoan`, `TenLoai`) VALUES
('Nhân viên', 'nv'),
('Quản trị viên', 'qt');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `luong`
--

CREATE TABLE `luong` (
  `MaLuong` varchar(10) NOT NULL,
  `LuongCoBan` decimal(20,2) DEFAULT NULL,
  `GhiChu` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `luong`
--

INSERT INTO `luong` (`MaLuong`, `LuongCoBan`, `GhiChu`) VALUES
('1', '3000000.00', '27 công'),
('2', '1000000.00', '25 công'),
('3', '5000000.00', '30 công');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nhanvien`
--

CREATE TABLE `nhanvien` (
  `MaNhanVien` varchar(10) NOT NULL,
  `HoTen` varchar(100) DEFAULT NULL,
  `NgaySinh` date DEFAULT NULL,
  `DiaChi` varchar(255) DEFAULT NULL,
  `GioiTinh` varchar(10) DEFAULT NULL,
  `SDT` varchar(20) DEFAULT NULL,
  `CCCD` varchar(20) DEFAULT NULL,
  `MaChucVu` varchar(10) DEFAULT NULL,
  `MaPhongBan` varchar(10) DEFAULT NULL,
  `MaLuong` varchar(10) DEFAULT NULL,
  `MaPhuCap` varchar(10) DEFAULT NULL,
  `MaThuong` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `nhanvien`
--

INSERT INTO `nhanvien` (`MaNhanVien`, `HoTen`, `NgaySinh`, `DiaChi`, `GioiTinh`, `SDT`, `CCCD`, `MaChucVu`, `MaPhongBan`, `MaLuong`, `MaPhuCap`, `MaThuong`) VALUES
('NV01', 'vinh', '1999-01-03', 'HN', 'Nữ', '0123456789', '123456789', '1', '2', '1', NULL, NULL),
('NV02', 'Đào Văn Vinh', '2005-06-24', 'Hải Phòng', 'Nam', '0389737619', NULL, '2', '1', '1', '1', '2'),
('NV03', 'Hoàng Văn Thành', '2005-01-01', 'Hà Đông', 'Nam', '0241248852', NULL, '3', '4', '2', '1', '1'),
('NV04', 'Đỗ Quang Anh', '2005-10-30', 'Thái Bình', 'Nam', '0131278349', NULL, NULL, NULL, '1', '3', '3'),
('NV05', 'thanh', '2005-01-05', 'Hà Nội', 'Nam', '0123214215', NULL, NULL, '3', '2', '2', '1'),
('NV06', 'quang linh', '2010-02-11', 'Kiến Thuỵ', 'Nữ', '0327839598', NULL, '5', NULL, '3', NULL, NULL);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `phongban`
--

CREATE TABLE `phongban` (
  `MaPhongBan` varchar(10) NOT NULL,
  `TenPhongBan` varchar(100) DEFAULT NULL,
  `NgayThanhLap` date DEFAULT NULL,
  `GhiChu` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `phongban`
--

INSERT INTO `phongban` (`MaPhongBan`, `TenPhongBan`, `NgayThanhLap`, `GhiChu`) VALUES
('1', 'Kinh Doanh', '2022-02-02', 'nội bộ'),
('2', 'Kế Toán', '2022-02-02', '2'),
('3', 'Pha Chế', '2024-02-02', '1'),
('4', 'Markerting', '2022-12-01', 'không có'),
('5', 'Nhập Liệu', '2025-11-12', 'nội bộ');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `phucap`
--

CREATE TABLE `phucap` (
  `MaPhuCap` varchar(10) NOT NULL,
  `TenPhuCap` varchar(100) DEFAULT NULL,
  `TienPhuCap` decimal(15,2) DEFAULT NULL,
  `NgayHieuLuc` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `phucap`
--

INSERT INTO `phucap` (`MaPhuCap`, `TenPhuCap`, `TienPhuCap`, `NgayHieuLuc`) VALUES
('1', 'Ăn trưa', '500000.00', '2025-02-02'),
('2', 'Xăng xe', '100000.00', '2025-12-02'),
('3', 'Phí gửi xe', '30000.00', '2026-01-04');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `taikhoan`
--

CREATE TABLE `taikhoan` (
  `ID` int(11) NOT NULL,
  `TenTaiKhoan` varchar(50) DEFAULT NULL,
  `MatKhau` varchar(100) DEFAULT NULL,
  `LoaiTaiKhoan` varchar(50) DEFAULT NULL,
  `MaNhanVien` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `taikhoan`
--

INSERT INTO `taikhoan` (`ID`, `TenTaiKhoan`, `MatKhau`, `LoaiTaiKhoan`, `MaNhanVien`) VALUES
(1, 'admin', '123', 'Quản trị viên', 'NV01'),
(21, 'qa', '123', 'Nhân viên', 'NV04'),
(27, 'Đào Văn Vinh', '1234', 'Nhân viên', 'NV02'),
(28, 'Đỗ Quang Anh', '123', 'Nhân viên', 'NV01'),
(29, 'vinh', '123', 'Nhân viên', 'NV02'),
(30, 'Hoàng Văn Thành', '123456', 'Nhân viên', 'NV05');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `thuong`
--

CREATE TABLE `thuong` (
  `MaThuong` varchar(10) NOT NULL,
  `SoTien` decimal(15,2) DEFAULT NULL,
  `LyDo` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `thuong`
--

INSERT INTO `thuong` (`MaThuong`, `SoTien`, `LyDo`) VALUES
('1', '200000.00', 'Đủ kpi'),
('2', '300000.00', 'vượt kpi'),
('3', '500000.00', 'thưởng lễ tết');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `chucvu`
--
ALTER TABLE `chucvu`
  ADD PRIMARY KEY (`MaChucVu`);

--
-- Chỉ mục cho bảng `loaitaikhoan`
--
ALTER TABLE `loaitaikhoan`
  ADD PRIMARY KEY (`LoaiTaiKhoan`);

--
-- Chỉ mục cho bảng `luong`
--
ALTER TABLE `luong`
  ADD PRIMARY KEY (`MaLuong`);

--
-- Chỉ mục cho bảng `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD PRIMARY KEY (`MaNhanVien`);

--
-- Chỉ mục cho bảng `phongban`
--
ALTER TABLE `phongban`
  ADD PRIMARY KEY (`MaPhongBan`);

--
-- Chỉ mục cho bảng `phucap`
--
ALTER TABLE `phucap`
  ADD PRIMARY KEY (`MaPhuCap`);

--
-- Chỉ mục cho bảng `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD PRIMARY KEY (`ID`);

--
-- Chỉ mục cho bảng `thuong`
--
ALTER TABLE `thuong`
  ADD PRIMARY KEY (`MaThuong`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `taikhoan`
--
ALTER TABLE `taikhoan`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
