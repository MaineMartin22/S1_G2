INSERT INTO vuelos(codigo_vuelo, origen, destino, tipo_asiento, precio_persona, fecha_salida, fecha_vuelta, total_asientos)
VALUES
("BAPI-1235", "Buenos Aires", "Puerto Iguazú", "Economy", 6500.0, "2022-02-10", "2022-02-15", 4),
("PIBA-1420", "Puerto Iguazú", "Bogotá", "Business", 43200.0, "2022-02-10", "2022-02-20", 4);



INSERT INTO hoteles(codigo_hotel, nombre, ciudad, tipo_habitacion, precio_noche, disponible_desde, disponible_hasta, reservado, habitaciones_totales)
VALUES
("CH-0002", "Cataratas Hotel", "Puerto Iguazú", "Doble", 6300, "2022-02-10", "2022-03-20", false, 2),
("HB-0001", "Hotel Bristol", "Buenos Aires", "Single", 5435, "2022-02-10", "2022-03-23", false, 2);

INSERT INTO persona(id, dni, nombre, apellido, fecha_nacimiento, email)
VALUES
(1,'125421','martin','maine','2022-02-02','mm@gmail.com'),
(2,'123456','pepeito','Goemez','09-11-1982','miguel2@gmail.com');

INSERT INTO booking_hotel_detalle(id, fecha_ingreso, fecha_salida, destino, codigo_hotel, cantidad_persona, tipo_habitacion)
VALUES (1,'2022-02-12','2022-03-19','Puerto Iguazú','CH-0002',1,'doble');

INSERT INTO booking_hotel(id, nombre_usuario, total_neto, total_intereses, total_final, booking_id)
VALUES (1,'mm@gmail.com', 242550,22050,220500,1);

INSERT INTO booking_hotel_detalle_people(booking_hotel_details_id, people_id)
VALUES (1,1);

INSERT INTO `reserva_vuelo_detalle` VALUES (1,'2022-02-10','2022-02-15','Puerto Iguazú','BAPI-1235','Buenos Aires','economy',2);

INSERT INTO `reserva_vuelo` VALUES (1,14300,1300,13000,'miguel@gmail.com',1);

INSERT INTO `reserva_vuelo_detalle_people` VALUES (1,2);

