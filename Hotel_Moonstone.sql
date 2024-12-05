-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 05 Des 2024 pada 06.26
-- Versi server: 10.4.32-MariaDB
-- Versi PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hotel_reservation`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `admins`
--

CREATE TABLE `admins` (
  `id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struktur dari tabel `booking`
--

CREATE TABLE `booking` (
  `id` bigint(20) NOT NULL,
  `customer_id` bigint(20) NOT NULL,
  `room_id` bigint(20) NOT NULL,
  `check_in` date NOT NULL,
  `check_out` date NOT NULL,
  `status` enum('PENDING','CONFIRMED','CANCELLED','PAID') DEFAULT 'PENDING',
  `payment_status` enum('UNPAID','PAID') DEFAULT 'UNPAID',
  `total_amount` decimal(38,2) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `booking`
--

INSERT INTO `booking` (`id`, `customer_id`, `room_id`, `check_in`, `check_out`, `status`, `payment_status`, `total_amount`, `created_at`, `updated_at`) VALUES
(8, 9, 1, '2024-12-16', '2024-12-19', 'PAID', 'PAID', 1500000.00, '2024-12-05 04:35:41', '2024-12-05 04:35:52'),
(9, 11, 6, '2024-12-05', '2024-12-07', 'PAID', 'PAID', 2400000.00, '2024-12-05 05:00:45', '2024-12-05 05:02:38');

-- --------------------------------------------------------

--
-- Struktur dari tabel `customers`
--

CREATE TABLE `customers` (
  `id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `customers`
--

INSERT INTO `customers` (`id`) VALUES
(9),
(10),
(11);

-- --------------------------------------------------------

--
-- Struktur dari tabel `login`
--

CREATE TABLE `login` (
  `id` bigint(11) NOT NULL,
  `nik` varchar(255) NOT NULL,
  `fname` varchar(255) NOT NULL,
  `lname` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` enum('ADMIN','CUSTOMER') NOT NULL DEFAULT 'CUSTOMER',
  `id_admin` int(11) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `username` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `login`
--

INSERT INTO `login` (`id`, `nik`, `fname`, `lname`, `email`, `password`, `role`, `id_admin`, `created_at`, `updated_at`, `username`) VALUES
(9, '8252300529', 'fio', 'lia', 'fionna@moonstone.com', '$2a$10$YdhrEOc9o1zHMAPVbSIao.UXR19Vd2LWjhPARl9Sax4pQOHB8XoB.', 'CUSTOMER', NULL, '2024-12-04 14:56:54', '2024-12-04 14:56:54', NULL),
(10, '123', 'mike', 'james', 'admin@moonstone.com', '$2a$10$hn/J7SAnCfJqiAkanLupFuLtMV53TxNPmjbT8MqxWqPfBPVQrf61y', 'ADMIN', NULL, '2024-12-04 15:02:34', '2024-12-04 15:02:34', NULL),
(11, '123221', 'Angel', 'Wijaya', 'angel@moonstone.com', '$2a$10$4y52fRAQqGtLljrRv4PRxOlqJYZJLX8YYx4L2vNojc61/o3vXo4C.', 'CUSTOMER', NULL, '2024-12-05 04:50:56', '2024-12-05 04:50:56', NULL);

-- --------------------------------------------------------

--
-- Struktur dari tabel `payment`
--

CREATE TABLE `payment` (
  `id` bigint(20) NOT NULL,
  `booking_id` bigint(20) NOT NULL,
  `amount` double NOT NULL,
  `payment_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `payment_method` enum('DEBIT_CARD') NOT NULL,
  `card_number` varchar(255) NOT NULL,
  `status` enum('PENDING','COMPLETED') DEFAULT 'PENDING'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `payment`
--

INSERT INTO `payment` (`id`, `booking_id`, `amount`, `payment_date`, `payment_method`, `card_number`, `status`) VALUES
(8, 8, 1500000, '2024-12-05 04:35:52', 'DEBIT_CARD', '1234', 'COMPLETED'),
(9, 9, 2400000, '2024-12-05 05:02:38', 'DEBIT_CARD', '123456', 'COMPLETED');

-- --------------------------------------------------------

--
-- Struktur dari tabel `room`
--

CREATE TABLE `room` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `img_url` varchar(255) NOT NULL,
  `room_number` varchar(255) NOT NULL,
  `room_type` enum('SINGLE','DOUBLE','SUITE') NOT NULL,
  `description` varchar(255) NOT NULL,
  `person_count` int(11) NOT NULL,
  `bed_count` int(11) NOT NULL,
  `price` double NOT NULL,
  `availability` tinyint(1) DEFAULT 1,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `room`
--

INSERT INTO `room` (`id`, `name`, `img_url`, `room_number`, `room_type`, `description`, `person_count`, `bed_count`, `price`, `availability`, `created_at`, `updated_at`) VALUES
(1, 'Single Economy Room', '/img/hotel-1.webp', '101', 'SINGLE', 'Cozy single room with modern amenities edit', 1, 1, 500000, 1, '2024-11-23 20:28:56', '2024-12-05 04:33:47'),
(2, 'Double Starry Room', '/img/hotel-2.webp', '102', 'DOUBLE', 'Spacious double room with a beautiful view', 2, 2, 700000, 1, '2024-11-23 20:28:56', '2024-11-26 07:38:21'),
(3, 'Luxurious Family Room', '/img/hotel-3.webp', '103', 'SUITE', 'Luxurious suite with a large living area', 4, 2, 1000000, 1, '2024-11-23 20:28:56', '2024-11-26 07:39:02'),
(4, 'Traveler Adventure Room', '/img/hotel-4-large.webp', '104', 'SINGLE', 'Compact single room for solo travelers', 1, 1, 450000, 1, '2024-11-23 20:28:56', '2024-11-26 07:39:33'),
(5, 'Couple Room', '/img/hotel-5.webp', '105', 'DOUBLE', 'Comfortable double room with a king-size bed', 2, 1, 800000, 1, '2024-11-23 20:28:56', '2024-11-26 07:51:49'),
(6, 'Skyline Room', '/img/hotel-6.jpg', '106', 'DOUBLE', 'Offers an unparalleled view of the horizon', 4, 1, 1200000, 1, '2024-11-26 07:56:55', '2024-11-26 08:07:26');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `admins`
--
ALTER TABLE `admins`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `booking`
--
ALTER TABLE `booking`
  ADD PRIMARY KEY (`id`),
  ADD KEY `customer_id` (`customer_id`),
  ADD KEY `room_id` (`room_id`);

--
-- Indeks untuk tabel `customers`
--
ALTER TABLE `customers`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indeks untuk tabel `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`id`),
  ADD KEY `booking_id` (`booking_id`);

--
-- Indeks untuk tabel `room`
--
ALTER TABLE `room`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `room_number` (`room_number`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `booking`
--
ALTER TABLE `booking`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT untuk tabel `login`
--
ALTER TABLE `login`
  MODIFY `id` bigint(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT untuk tabel `payment`
--
ALTER TABLE `payment`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT untuk tabel `room`
--
ALTER TABLE `room`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `admins`
--
ALTER TABLE `admins`
  ADD CONSTRAINT `FK589p5q7pmplltnjhbjvf3j7wt` FOREIGN KEY (`id`) REFERENCES `login` (`id`);

--
-- Ketidakleluasaan untuk tabel `booking`
--
ALTER TABLE `booking`
  ADD CONSTRAINT `booking_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `login` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `booking_ibfk_2` FOREIGN KEY (`room_id`) REFERENCES `room` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `customers`
--
ALTER TABLE `customers`
  ADD CONSTRAINT `FK4xs17c3xu01fpp1m6n968mslf` FOREIGN KEY (`id`) REFERENCES `login` (`id`);

--
-- Ketidakleluasaan untuk tabel `payment`
--
ALTER TABLE `payment`
  ADD CONSTRAINT `payment_ibfk_1` FOREIGN KEY (`booking_id`) REFERENCES `booking` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
