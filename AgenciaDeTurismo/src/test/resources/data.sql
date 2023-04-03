INSERT INTO vuelos(codigo_vuelo, origen, destino, tipo_asiento, precio_persona, fecha_salida, fecha_vuelta)
VALUES
("BAPI-1235", "Buenos Aires", "Puerto Iguazú", "Economy", 6500.0, "2022-02-10", "2022-02-15"),
("PIBA-1420", "Puerto Iguazú", "Bogotá", "Business", 43200.0, "2022-02-10", "2022-02-20");


INSERT INTO hoteles(codigo_hotel, nombre, ciudad, tipo_habitacion, precio_noche, disponible_desde, disponible_hasta, reservado)
VALUES
("CH-0002", "Cataratas Hotel", "Puerto Iguazú", "Doble", 6300, "2022-02-10", "2022-03-20", false),
("HB-0001", "Hotel Bristol", "Buenos Aires", "Single", 5435, "2022-02-10", "2022-03-23", false);

INSERT INTO booking_hotel (`total_final`,`total_intereses`,`total_neto`,`nombre_usuario`,`booking_id`)
VALUES (226800,22680,249480,'arjonamiguel@gmail.com',1);

INSERT INTO booking_hotel_detalle (`fecha_ingreso`,`fecha_salida`,`destino`,`codigo_hotel`,`cantidad_persona`,`tipo_habitacion`)
VALUES ('2022-02-11','2022-03-19','Puerto Iguazú','CH-0002',1,'doble');

INSERT INTO persona (`fecha_nacimiento`,`dni`,`apellido`,`email`,`nombre`)
VALUES ('2022-02-02','12421','sdmaine','mmasdasd@gmial.com','pepsadito');
