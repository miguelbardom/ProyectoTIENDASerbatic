-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 10-05-2024 a las 08:58:14
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `tienda_miguel_barba`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categoria`
--

CREATE TABLE `categoria` (
  `id` int(11) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `activo` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `categoria`
--

INSERT INTO `categoria` (`id`, `nombre`, `descripcion`, `activo`) VALUES
(1, 'Clásicos', NULL, 1),
(2, 'Novela contemporánea', NULL, 1),
(3, 'Poesía', NULL, 1),
(4, 'Novela histórica', NULL, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categoria_old`
--

CREATE TABLE `categoria_old` (
  `id` int(11) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `activo` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `configuracion`
--

CREATE TABLE `configuracion` (
  `id` int(11) NOT NULL,
  `clave` varchar(255) DEFAULT NULL,
  `valor` varchar(255) DEFAULT NULL,
  `tipo` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `configuracion`
--

INSERT INTO `configuracion` (`id`, `clave`, `valor`, `tipo`) VALUES
(1, 'nombre_tienda', 'Librería Miguel S.L.', 'texto'),
(2, 'cif', 'A12345678X', 'texto'),
(3, 'direccion', 'Plaza de Sagasta, 4, 49002, Zamora', 'texto'),
(4, 'factura_id', '5', 'entero');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle`
--

CREATE TABLE `detalle` (
  `id` int(11) NOT NULL,
  `pedido_id` int(11) DEFAULT NULL,
  `libro_id` int(11) DEFAULT NULL,
  `unidades` int(11) DEFAULT NULL,
  `preciounidad` double DEFAULT NULL,
  `impuesto` double DEFAULT NULL,
  `total` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `detalle`
--

INSERT INTO `detalle` (`id`, `pedido_id`, `libro_id`, `unidades`, `preciounidad`, `impuesto`, `total`) VALUES
(90, 70, 7, 1, 23.65, 4, 23.65),
(91, 70, 5, 1, 9.45, 4, 9.45),
(92, 71, 10, 1, 12.99, 4, 12.99),
(93, 72, 8, 1, 11.35, 4, 11.35),
(94, 72, 9, 2, 13.25, 4, 26.5),
(95, 73, 4, 1, 30.4, 4, 30.4),
(96, 73, 7, 1, 23.65, 4, 23.65),
(97, 74, 10, 1, 12.99, 4, 12.99),
(98, 74, 9, 1, 13.25, 4, 13.25),
(99, 75, 8, 2, 11.35, 4, 22.7),
(100, 76, 8, 1, 11.35, 4, 11.35),
(101, 76, 9, 2, 13.25, 4, 26.5),
(102, 77, 9, 1, 13.25, 4, 13.25),
(103, 77, 7, 1, 23.65, 4, 23.65),
(104, 77, 5, 2, 9.45, 4, 18.9),
(105, 78, 10, 1, 12.99, 4, 12.99),
(106, 79, 5, 2, 9.45, 4, 18.9),
(107, 79, 9, 1, 13.25, 4, 13.25),
(108, 80, 9, 1, 13.25, 4, 13.25),
(109, 81, 9, 1, 13.25, 4, 13.25),
(111, 83, 5, 1, 9.45, 4, 9.45),
(112, 83, 9, 1, 13.25, 4, 13.25),
(113, 83, 8, 1, 11.35, 4, 11.35),
(114, 84, 9, 2, 13.25, 4, 26.5);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `libro`
--

CREATE TABLE `libro` (
  `id` int(11) NOT NULL,
  `titulo` varchar(255) NOT NULL,
  `autor` varchar(255) DEFAULT NULL,
  `editorial` varchar(100) DEFAULT NULL,
  `isbn` varchar(20) DEFAULT NULL,
  `categoria_id` int(11) DEFAULT NULL,
  `precio` double DEFAULT NULL,
  `impuesto` double DEFAULT NULL,
  `stock` int(11) DEFAULT 0,
  `url` varchar(255) DEFAULT NULL,
  `baja` tinyint(1) DEFAULT 0,
  `descripcion` text DEFAULT NULL,
  `formato` varchar(255) DEFAULT NULL,
  `paginas` int(11) DEFAULT NULL,
  `año_publicacion` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `libro`
--

INSERT INTO `libro` (`id`, `titulo`, `autor`, `editorial`, `isbn`, `categoria_id`, `precio`, `impuesto`, `stock`, `url`, `baja`, `descripcion`, `formato`, `paginas`, `año_publicacion`) VALUES
(4, 'Don Quijote de la Mancha', 'Miguel de Cervantes Saavedra', 'Editorial Verbum', '9788413379845', 1, 30.4, 4, 24, '/img/donquijote.jpeg', 0, 'Con el Quijote las cosas se complican: los datos de la realidad se tornan irreales ?unos molinos de viento se cambian en ejércitos, una venta es un castillo, unas monjas son princesas, frailes son cambiados en encantadores, y pellejos de vino en gigantes?\nEstamos en presencia de la doble aventura: por un lado se opera con lo conocido -el mundo tal cual lo conocemos?; por el otro se parte de lo conocido hacia lo desconocido -lo cotidiano sustituido por lo mágico\".', 'Tapa dura', 666, 2023),
(5, 'Cien años de soledad', 'Gabriel García Márquez', 'Editorial Debolsillo', '9788497592208', 2, 9.45, 4, 14, '/img/cienañosdesoledad.jpg', 0, 'Señalada como \"catedral gótica del lenguaje\", este clásico del siglo XX es el enorme y espléndido tapiz de la saga de la familia Buendía, en la mítica aldea de Macondo. UNO DE LOS 5 LIBROS MÁS IMPORTANTES DE LOS ÚLTIMOS 125 AÑOS SEGÚN THE NEW YORK TIMES. Un referente imprescindible de la vida y la narrativa latinoamericana.', 'Bolsillo', 496, 2003),
(6, 'El amor en los tiempos del cólera', 'Gabriel García Márquez', 'Editorial Random House', '9788439735427', 2, 23.65, 4, 20, '/img/amorcolera.jpg', 0, 'Una edición conmemorativa de El amor en los tiempos del cólera, un gran clásico de Gabriel García Márquez y una novela imprescindible de la literatura contemporánea.', 'Tapa dura', 400, 2019),
(7, 'Maldita Roma', 'Santiago Posteguillo', 'Editorial B', '9788466676564', 4, 23.65, 4, 17, '/img/maldita-roma.jpg', 0, 'VIVE LA SAGA MÁS APASIONANTE DE LA HISTORIA.\r\n\r\nCONTINÚA EL FENÓMENO DE ROMA SOY YO, LA NOVELA MÁS VENDIDA DE 2022.\r\n\r\n\r\nNingún escritor podría imaginar una vida como la de Julio César. Sólo Santiago Posteguillo puede escribirla.', 'Tapa dura', 896, 2023),
(8, 'Romancero gitano', 'Federico García Lorca', 'Editorial Austral', '9788467046878', 3, 11.35, 4, 19, '/img/romancero-gitano.jpg', 0, 'El Romancero Gitano es una de las creaciones líricas más significativas del siglo XX. Punto culminante de la primera etapa estética de Lorca, el propio poeta lo define como el poema de Andalucía, y lo llamo gitano porque el gitano es lo más elevado, lo más profundo, más aristocrático de mi país, lo más representativo de su modo y el que guarda el ascua, la sangre y el alfabeto de la verdad andaluza y universal...', 'Tapa dura', 144, 2016),
(9, 'Ilíada', 'Homero', 'Alianza Editorial', '9788413625164', 1, 13.25, 4, 9, '/img/iliada.jpg', 0, 'El origen de la fascinación que esta obra ha ejercido sobre los lectores de todas las épocas proviene de su inigualable aliento épico y de sus personajes, en los que vibra una cuerda que los identifica con todos los hombres.  La presente versión ofrece una versión límpida y rigurosa destinada a ser referencia para el lector actual.', 'Bolsillo', 768, 2021),
(10, 'Un animal salvaje', 'Jöel Dicker', 'Editorial Alfaguara', '9788420476858', 2, 12.99, 4, 17, '/img/un-animal-salvaje.jpg', 0, 'El 2 de julio de 2022, dos delincuentes se disponen a robar en una importante joyería de Ginebra. Un incidente que dista mucho de ser un vulgar atraco. Veinte días antes, en una lujosa urbanización a orillas del lago Lemán, Sophie Braun se prepara para celebrar su cuadragésimo cumpleaños. La vida le sonríe: vive con su familia en una mansión rodeada de bosques, pero su idílico mundo está a punto de tambalearse.', 'Electrónico', 448, 2021),
(17, '1984', 'George Orwell', 'Editorial Debolsillo', '9788499890944', 2, 9.95, 4, 15, '/img/1984.jpg', 0, 'En el año 1984 Londres es una ciudad lúgubre en la que la Policía del Pensamiento controla de forma asfixiante la vida de los ciudadanos. Winston Smith es un peón de este engranaje perverso y su cometido es reescribir la historia para adaptarla a lo que el Partido considera la versión oficial de los hechos. Hasta que decide replantearse la verdad del sistema que los gobierna y somete.', 'Bolsillo', 352, 2013),
(18, 'Poesía Completa', 'Francisco de Quevedo', 'Castalia Ediciones', '9788497408493', 3, 54, 4, 25, '/img/quevedo-poesia-completa.jpeg', 0, 'Francisco de Quevedo y Villegas es, sin lugar a dudas, uno de los grandes nombres de nuestras letras en toda la historia de la literatura, y no sólo española, sino mundial.    Reflejó en su obra literaria la profunda contradicción entre el hombre político que vocacionalmente quiso ser y el poeta que supo escribir los más excelsos poemas de nuestro Barroco. Son estos dos rasgos, además, propios de su carácter, y de ellos nace un pavoroso desengaño que lo llevará a sobrevivir en su refugio de creación y a escribir para vivir y padecer. Porque Quevedo no duda en ofrecer la fragmentación de su mundo, el suyo propio y el que lo rodea, disolviéndose al tiempo que su propia vida.', 'Tapa dura', 1552, 2021);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `opcion_menu`
--

CREATE TABLE `opcion_menu` (
  `id` int(11) NOT NULL,
  `rol_id` int(11) DEFAULT NULL,
  `nombre_opcion` varchar(255) DEFAULT NULL,
  `url_opcion` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedido`
--

CREATE TABLE `pedido` (
  `id` int(11) NOT NULL,
  `usuario_id` int(11) DEFAULT NULL,
  `fecha` datetime DEFAULT NULL,
  `metodopago` varchar(255) DEFAULT NULL,
  `numfactura` varchar(255) DEFAULT NULL,
  `total` double DEFAULT NULL,
  `estado` enum('PE','PC','E','C') NOT NULL DEFAULT 'PE'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `pedido`
--

INSERT INTO `pedido` (`id`, `usuario_id`, `fecha`, `metodopago`, `numfactura`, `total`, `estado`) VALUES
(70, 13, '2024-05-01 00:00:00', 'Tarjeta', NULL, 33.099999999999994, 'PC'),
(71, 13, '2024-05-01 00:00:00', 'Paypal', NULL, 12.99, 'PC'),
(72, 13, '2024-05-02 00:00:00', 'Paypal', 'FA-0001', 37.85, 'E'),
(73, 13, '2024-05-03 00:00:00', 'Tarjeta', 'FA-0002', 54.05, 'E'),
(74, 13, '2024-05-03 00:00:00', 'Paypal', NULL, 26.240000000000002, 'C'),
(75, 13, '2024-05-03 00:00:00', 'Tarjeta', 'FA-0005', 22.7, 'E'),
(76, 13, '2024-05-08 00:00:00', 'Tarjeta', NULL, 37.85, 'PC'),
(77, 24, '2024-05-09 00:00:00', 'Tarjeta', 'FA-0003', 55.8, 'E'),
(78, 13, '2024-05-09 00:00:00', 'Tarjeta', NULL, 12.99, 'C'),
(79, 24, '2024-05-09 00:00:00', 'Tarjeta', 'FA-0004', 32.15, 'E'),
(80, 13, '2024-05-09 00:00:00', 'Paypal', NULL, 13.25, 'PE'),
(81, 13, '2024-05-09 00:00:00', 'Tarjeta', NULL, 13.25, 'PE'),
(83, 25, '2024-05-09 00:00:00', 'Tarjeta', '', 34.05, 'E'),
(84, 25, '2024-05-09 00:00:00', 'Paypal', NULL, 26.5, 'C');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto_old`
--

CREATE TABLE `producto_old` (
  `id` int(11) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `precio` double DEFAULT NULL,
  `impuesto` double DEFAULT NULL,
  `stock` int(11) DEFAULT NULL,
  `baja` tinyint(1) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `categoria_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `producto_old`
--

INSERT INTO `producto_old` (`id`, `nombre`, `descripcion`, `precio`, `impuesto`, `stock`, `baja`, `url`, `categoria_id`) VALUES
(1, 'Coche', 'BMW', 15000, NULL, 17, NULL, '/img/bmw-serie5.jpg', NULL),
(2, 'Moto', 'Honda 150 cc', 10000, NULL, 22, NULL, '/img/honda-150cc.jpg', NULL),
(3, 'Ordenador portátil HP 15-fc0072ns', 'AMD Ryzen 7 7730U, 16GB RAM, 1TB SSD, AMD Radeon Graphics, Sin Sistema Operativo.\r\nPantalla de 15.6\" Full HD.\r\nColor Plata.', 599.99, 21, 10, 0, '/img/portatil_hp.jpg', NULL),
(4, 'Don Quijote de la Mancha', 'Miguel de Cervantes.\r\nTapa dura.', 45, 21, 9, 0, '/img/don-quijote.jpg', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rol`
--

CREATE TABLE `rol` (
  `id` int(11) NOT NULL,
  `rol` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `rol`
--

INSERT INTO `rol` (`id`, `rol`) VALUES
(1, 'Cliente'),
(2, 'Empleado'),
(3, 'Administrador');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id` int(11) NOT NULL,
  `rol_id` int(11) DEFAULT 1,
  `email` varchar(255) NOT NULL,
  `clave` varchar(255) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `apellidos` varchar(255) NOT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `provincia` varchar(255) DEFAULT NULL,
  `localidad` varchar(255) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  `dni` varchar(255) DEFAULT NULL,
  `baja` tinyint(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id`, `rol_id`, `email`, `clave`, `nombre`, `apellidos`, `direccion`, `provincia`, `localidad`, `telefono`, `dni`, `baja`) VALUES
(10, 1, 'bach@gmail.com', 'JuoR37I1uMsdVLIWuIXnIiT+MfUMhRZ2LJphQq9ytQc/7xwdnG9Gr5RxGSXFXoJZ', 'bach', 'bach', NULL, NULL, NULL, NULL, NULL, 0),
(12, 1, 'mozart@gmail.com', 'ei7l5LGVYgkIEhn0AZpwp84hn54CEOZBwrSONW8clPdFrEnVF9g0cYJi8i+1QetG', 'mozart', 'mozart', NULL, NULL, NULL, NULL, NULL, 0),
(13, 1, 'miguel@gmail.com', 'VtPZyS+FK0qiCv9gAFpdv61guoKUBdqC89EX9XeKBJE/yaICJXf9f1SuGCmU+wRl', 'Miguel', 'Barba', NULL, NULL, NULL, NULL, NULL, 0),
(14, 1, 'bee@gmail.com', 'RD0SxnpmYl0qffNgziQVjfp0Hryf4U5H7DyWEI1sbi980JPFzxctkhFdUXIriCpw', 'beethoven', 'bee', NULL, NULL, NULL, NULL, NULL, 0),
(15, 1, 'prueba@gmail.com', 'ruZmzN0go9pXIGZBJ0OKVUiXJotKEwB3XYxaula/DXrsgg72/QOaVXRe8S2/93bN', 'prueba', 'prueba', NULL, NULL, NULL, NULL, NULL, 0),
(22, 2, 'empleado@gmail.com', 'HZmd9fxuZgDg/X1eq1TN4xxHrJbtMAyh3rC4qo3DuYTZnnEyOHmLNDrard2bXU9Y', 'empleado', 'empleado', NULL, NULL, NULL, NULL, NULL, 0),
(23, 3, 'admin@gmail.com', '9Xc5V73F3pNF4qHi3LewR/NIJdOfpOsUdMVk7gp3LahNZY93xC444/SXVqoglOmr', 'admin', 'admin', NULL, NULL, NULL, NULL, NULL, 0),
(24, 1, 'juan@gmail.com', 'VTjJIBPDaq7fGYyCreTWu8VxI3KtVvZ4mKVXlY5RoGMH7J/nX6A8kXtr4j0JN5C/', 'Juan', 'Perezzz', NULL, NULL, NULL, NULL, NULL, 0),
(25, 1, 'miguelb@gmail.com', 'ub6mn/MMBm9rLhR9eNm8au64kdV+3YIZMiaLqmtWJGPlgbffZPrGtkSOmQpnWRzT', 'Miguel', 'Barba', NULL, NULL, NULL, NULL, NULL, 0);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `categoria`
--
ALTER TABLE `categoria`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `categoria_old`
--
ALTER TABLE `categoria_old`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `configuracion`
--
ALTER TABLE `configuracion`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`);

--
-- Indices de la tabla `detalle`
--
ALTER TABLE `detalle`
  ADD PRIMARY KEY (`id`),
  ADD KEY `pedido_id` (`pedido_id`),
  ADD KEY `detalle_ibfk_2` (`libro_id`);

--
-- Indices de la tabla `libro`
--
ALTER TABLE `libro`
  ADD PRIMARY KEY (`id`),
  ADD KEY `categoria_id` (`categoria_id`);

--
-- Indices de la tabla `opcion_menu`
--
ALTER TABLE `opcion_menu`
  ADD PRIMARY KEY (`id`),
  ADD KEY `rol_id` (`rol_id`);

--
-- Indices de la tabla `pedido`
--
ALTER TABLE `pedido`
  ADD PRIMARY KEY (`id`),
  ADD KEY `usuario_id` (`usuario_id`);

--
-- Indices de la tabla `producto_old`
--
ALTER TABLE `producto_old`
  ADD PRIMARY KEY (`id`),
  ADD KEY `categoria_id` (`categoria_id`);

--
-- Indices de la tabla `rol`
--
ALTER TABLE `rol`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`),
  ADD KEY `rol_id` (`rol_id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `categoria`
--
ALTER TABLE `categoria`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `categoria_old`
--
ALTER TABLE `categoria_old`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `configuracion`
--
ALTER TABLE `configuracion`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `detalle`
--
ALTER TABLE `detalle`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=115;

--
-- AUTO_INCREMENT de la tabla `libro`
--
ALTER TABLE `libro`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT de la tabla `opcion_menu`
--
ALTER TABLE `opcion_menu`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `pedido`
--
ALTER TABLE `pedido`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=85;

--
-- AUTO_INCREMENT de la tabla `producto_old`
--
ALTER TABLE `producto_old`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `rol`
--
ALTER TABLE `rol`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `detalle`
--
ALTER TABLE `detalle`
  ADD CONSTRAINT `detalle_ibfk_1` FOREIGN KEY (`pedido_id`) REFERENCES `pedido` (`id`),
  ADD CONSTRAINT `detalle_ibfk_2` FOREIGN KEY (`libro_id`) REFERENCES `libro` (`id`);

--
-- Filtros para la tabla `libro`
--
ALTER TABLE `libro`
  ADD CONSTRAINT `libro_ibfk_1` FOREIGN KEY (`categoria_id`) REFERENCES `categoria` (`id`),
  ADD CONSTRAINT `libro_ibfk_2` FOREIGN KEY (`categoria_id`) REFERENCES `categoria` (`id`);

--
-- Filtros para la tabla `opcion_menu`
--
ALTER TABLE `opcion_menu`
  ADD CONSTRAINT `opcion_menu_ibfk_1` FOREIGN KEY (`rol_id`) REFERENCES `rol` (`id`);

--
-- Filtros para la tabla `pedido`
--
ALTER TABLE `pedido`
  ADD CONSTRAINT `pedido_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`);

--
-- Filtros para la tabla `producto_old`
--
ALTER TABLE `producto_old`
  ADD CONSTRAINT `producto_old_ibfk_1` FOREIGN KEY (`categoria_id`) REFERENCES `categoria_old` (`id`);

--
-- Filtros para la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `usuario_ibfk_1` FOREIGN KEY (`rol_id`) REFERENCES `rol` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
