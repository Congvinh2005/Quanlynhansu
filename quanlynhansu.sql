-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
--
-- Máy chủ: localhost
-- Thời gian đã tạo: Th1 06, 2026 lúc 04:35 AM
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

CREATE DATABASE IF NOT EXISTS `nhansu` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE `nhansu`;

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
('1', '1', '1'),
('2', '1', '1');

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
  `LuongCoBan` decimal(15,2) DEFAULT NULL,
  `GhiChu` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `luong`
--

INSERT INTO `luong` (`MaLuong`, `LuongCoBan`, `GhiChu`) VALUES
('1', '1.90', '1');

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
('NV01', 'vinh', '1999-01-03', 'HN', 'Nam', '0123456789', '123456789', '1', '2', '1', NULL, NULL),
('NV02', 'Đào Văn Vinh', '2005-06-24', 'Hải Phòng', 'Nam', '0389737619', NULL, NULL, NULL, NULL, NULL, NULL);

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
('1', '1', '2022-02-02', '1'),
('2', '2', '2022-02-02', '2'),
('3', '3', '2024-02-02', '1'),
('4', 'IT', '2022-12-01', 'không có');

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
('1', '1', '1.00', '2222-02-02');

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
(9, 'songa', '123', 'Nhân viên', 'NV01'),
(19, 'vinh', '123', 'Nhân viên', 'NV01'),
(21, 'qa', '123', 'Nhân viên', 'NV01'),
(24, '1', '1', 'Nhân viên', 'NV01'),
(25, '2', '2', 'Quản trị viên', 'NV01'),
(26, 'Văn Vinh', '123', 'Quản trị viên', 'NV01'),
(27, 'Đào Văn Vinh', '1234', 'Nhân viên', 'NV02');

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
('1', '2.00', '1'),
('2', '1.00', '1');

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
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT= @OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS= @OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION= @OLD_COLLATION_CONNECTION */;