-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 04, 2020 at 05:35 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dn-mart`
--

-- --------------------------------------------------------

--
-- Table structure for table `adminuser`
--

CREATE TABLE `adminuser` (
  `id` int(11) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `adminuser`
--

INSERT INTO `adminuser` (`id`, `email`, `password`) VALUES
(1, 'ahmar@gmail.com', '123');

-- --------------------------------------------------------

--
-- Table structure for table `barcode_products`
--

CREATE TABLE `barcode_products` (
  `id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `barcode_id` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `barcode_products`
--

INSERT INTO `barcode_products` (`id`, `product_id`, `barcode_id`) VALUES
(1, 8, '036000291452');

-- --------------------------------------------------------

--
-- Table structure for table `cart`
--

CREATE TABLE `cart` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `cart`
--

INSERT INTO `cart` (`id`, `user_id`, `product_id`, `quantity`) VALUES
(95, 40, 9, 1);

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `image` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`id`, `name`, `image`) VALUES
(1, 'Lays', '91eYwmmel5L._SX425_PIbundle-64,TopRight,0,0_AA425SH20_.jpg'),
(2, 'Wavy', '91eYwmmel5L._SX425_PIbundle-64,TopRight,0,0_AA425SH20_.jpg'),
(3, 'nimko', '91eYwmmel5L._SX425_PIbundle-64,TopRight,0,0_AA425SH20_.jpg'),
(4, 'biscuites', '91eYwmmel5L._SX425_PIbundle-64,TopRight,0,0_AA425SH20_.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `orderlist`
--

CREATE TABLE `orderlist` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `number` varchar(255) NOT NULL,
  `city` varchar(255) NOT NULL,
  `cnic` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL DEFAULT 'Pending',
  `user_id` varchar(255) NOT NULL,
  `order_id` varchar(255) NOT NULL,
  `date` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `orderlist`
--

INSERT INTO `orderlist` (`id`, `name`, `address`, `number`, `city`, `cnic`, `status`, `user_id`, `order_id`, `date`) VALUES
(163, 'Ahmar', '1600 Amphitheatre Pkwy, Mountain View, CA 94043, USA', '03227134554', 'Sargodha', '123456789', 'booked', '40', '200223084810', '2019-02-23'),
(164, 'usama', 'park', '1234566', 'Sargodha', '123', 'pending', '40', '200225024030', '2019-02-23'),
(165, 'ahmar', 'yhi wala', '032555555', 'Sargodha', '23544666', 'pending', '40', '200225035546', '2020-02-26'),
(166, 'hassan', 'Sargodha Fatima road', '123456789', 'sargodga', '456789', 'Pending', '40', '200228010045', '2020-03-28'),
(174, 'ahmar', '1600 Amphitheatre Pkwy, Mountain View, CA 94043, USA', '123456789', 'Sargodha', '1345689', 'Pending', '40', '200301111216', '2020-03-01');

-- --------------------------------------------------------

--
-- Table structure for table `order_products`
--

CREATE TABLE `order_products` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `order_id` varchar(255) NOT NULL,
  `product_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `shipping` int(11) NOT NULL,
  `total` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `order_products`
--

INSERT INTO `order_products` (`id`, `user_id`, `order_id`, `product_id`, `quantity`, `shipping`, `total`) VALUES
(29, 40, '200223084810', 8, 1, 101, '443'),
(30, 40, '200223084810', 9, 1, 101, '443'),
(31, 40, '200223084810', 10, 1, 101, '443'),
(33, 40, '200225024030', 8, 1, 200, '280'),
(34, 40, '200225024030', 9, 1, 200, '280'),
(35, 40, '200225035546', 8, 1, 200, '280'),
(36, 40, '200225035546', 9, 1, 200, '280'),
(37, 40, '200228010045', 8, 2, 200, '300'),
(38, 40, '200301111216', 8, 2, 200, '300');

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `image` varchar(255) NOT NULL,
  `des` varchar(255) NOT NULL,
  `price` int(255) NOT NULL,
  `discount` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `category` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`id`, `name`, `image`, `des`, `price`, `discount`, `quantity`, `category`) VALUES
(8, 'lays', '91eYwmmel5L._SX425_PIbundle-64,TopRight,0,0_AA425SH20_.jpg', 'salted lays', 50, 10, 2, '3'),
(9, 'lays', '91eYwmmel5L._SX425_PIbundle-64,TopRight,0,0_AA425SH20_.jpg', 'masla lays', 30, 5, 5, '3'),
(10, 'lays', '91eYwmmel5L._SX425_PIbundle-64,TopRight,0,0_AA425SH20_.jpg', 'asasas', 40, 10, 1, '3');

-- --------------------------------------------------------

--
-- Table structure for table `shipping`
--

CREATE TABLE `shipping` (
  `shipping` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `shipping`
--

INSERT INTO `shipping` (`shipping`) VALUES
('100');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `number` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `image` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `name`, `email`, `number`, `password`, `image`) VALUES
(39, 'Eysa Azhar', 'eysaazhar@yahoo.com', '03069603634', 'Eysa1234', '11111.jpg'),
(40, 'Muhamamd Ahmar', 'ahmar@gmail.com', '03227134554', '123', 'DSC_0426.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `wishproducts`
--

CREATE TABLE `wishproducts` (
  `id` int(11) NOT NULL,
  `u_id` varchar(255) NOT NULL,
  `p_id` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `wishproducts`
--

INSERT INTO `wishproducts` (`id`, `u_id`, `p_id`) VALUES
(3, '40', '8'),
(4, '40', '10'),
(5, '40', '9');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `adminuser`
--
ALTER TABLE `adminuser`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `barcode_products`
--
ALTER TABLE `barcode_products`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `cart`
--
ALTER TABLE `cart`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `orderlist`
--
ALTER TABLE `orderlist`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `order_products`
--
ALTER TABLE `order_products`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `wishproducts`
--
ALTER TABLE `wishproducts`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `adminuser`
--
ALTER TABLE `adminuser`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `barcode_products`
--
ALTER TABLE `barcode_products`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `cart`
--
ALTER TABLE `cart`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=96;

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `orderlist`
--
ALTER TABLE `orderlist`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=176;

--
-- AUTO_INCREMENT for table `order_products`
--
ALTER TABLE `order_products`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=39;

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=42;

--
-- AUTO_INCREMENT for table `wishproducts`
--
ALTER TABLE `wishproducts`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
