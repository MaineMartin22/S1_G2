INSERT INTO vuelos(codigo_vuelo, origen, destino, tipo_asiento, precio_persona, fecha_salida, fecha_vuelta, total_asientos)
VALUES
("BAPI-1235", "Buenos Aires", "Puerto Iguazú", "Economy", 6500.0, "2022-02-10", "2022-02-15", 10),
("PIBA-1420", "Puerto Iguazú", "Bogotá", "Business", 43200.0, "2022-02-10", "2022-02-20", 10),
("PIBA-1421", "Puerto Iguazú", "Bogotá", "Economy", 25735.0, "2022-02-10", "2022-02-21", 10),
("BATU-5536", "Buenos Aires", "Tucumán", "Economy", 7320.0, "2022-02-10", "2022-02-17", 10),
("TUPI-3369", "Tucumán", "Puerto Iguazú", "Business", 12530.0, "2022-02-12", "2022-02-23", 10),
("TUPI-3370", "Tucumán", "Puerto Iguazú", "Economy", 5400.0, "2022-01-02", "2022-01-16", 10),
("BOCA-4213", "Bogotá", "Cartagena", "Economy", 8000.0, "2022-01-23", "2022-02-05", 10),
("CAME-0321", "Cartagena", "Medellín", "Economy", 7800.0, "2022-01-23", "2022-01-31", 10),
("BOBA-6567", "Bogotá", "Buenos Aires", "Business", 57000.0, "2022-02-15", "2022-02-28", 10),
("BOBA-6568", "Bogotá", "Buenos Aires", "Economy", 39860.0, "2022-03-01", "2022-03-14", 10),
("BOME-4442", "Bogotá", "Medellín", "Economy", 11000.0, "2022-02-10", "2022-02-24", 10),
("MEPI-9986", "Medellín", "Puerto Iguazú", "Business", 41640.0, "2022-04-17", "2022-05-02", 10);


INSERT INTO hoteles(codigo_hotel, nombre, ciudad, tipo_habitacion, precio_noche, disponible_desde, disponible_hasta, reservado, habitaciones_totales)
VALUES
("CH-0002", "Cataratas Hotel", "Puerto Iguazú", "Doble", 6300, "2022-02-10", "2022-03-20", false, 5),
("CH-0003", "Cataratas Hotel 2", "Puerto Iguazú", "Triple", 8200, "2022-02-10", "2022-03-20", false, 3),
("HB-0001", "Hotel Bristol", "Buenos Aires", "Single", 5435, "2022-02-10", "2022-03-23", false, 4),
("BH-0002", "Hotel Bristol 2", "Buenos Aires", "Doble", 7200, "2022-02-12", "2022-04-17", false, 2),
("SH-0002", "Sheraton", "Tucumán", "Doble", 5790, "2022-04-17", "2022-05-23", false, 1),
("SH-0001", "Sheraton 2", "Tucumán", "Single", 4150, "2022-01-02", "2022-02-19", false, 2),
("SE-0001", "Selina", "Bogotá", "Single", 3900, "2022-01-23", "2022-11-23", false, 9),
("SE-0002", "Selina 2", "Bogotá", "Doble", 5840, "2022-01-23", "2022-10-15", false, 10),
("EC-0003", "El Campín", "Bogotá", "Triple", 7020, "2022-02-15", "2022-03-27", false, 4),
("CP-0004", "Central Plaza", "Medellín", "Múltiple", 8600, "2022-03-01", "2022-04-17", false, 3),
("CP-0002", "Central Plaza 2", "Medellín", "Doble", 6400, "2022-02-10", "2022-03-20", false, 4),
("BG-0004", "Bocagrande", "Cartagena", "Múltiple", 9370, "2022-04-17", "2022-06-12", false, 4);

INSERT INTO persona(id, dni, nombre, apellido, fecha_nacimiento, email)
VALUES
(1,'2022-02-02','125421','maine','mm@gmail.com','martin'),
(2,'09-11-1982','123456','Goemez','miguel2@gmail.com','pepeito');

INSERT INTO booking_hotel_detalle(id, fecha_ingreso, fecha_salida, destino, codigo_hotel, cantidad_persona, tipo_habitacion)
VALUES (1,'2022-02-12','2022-03-19','Puerto Iguazú','CH-0002',1,'doble');

INSERT INTO booking_hotel(id, nombre_usuario, total_neto, total_intereses, total_final, booking_id)
VALUES (1,'mm2@gmail.com', 242550,22050,220500,1);

INSERT INTO booking_hotel_detalle_people(booking_hotel_details_id, people_id)
VALUES (1,1);

INSERT INTO `reserva_vuelo_detalle` VALUES (1,'2022-02-10','2022-02-15','Puerto Iguazú','BAPI-1235','Buenos Aires','economy',2);

INSERT INTO `reserva_vuelo` VALUES (1,14300,1300,13000,'miguel@gmail.com',1);

INSERT INTO `reserva_vuelo_detalle_people` VALUES (1,2);

