<h1 align="center"> AGENCIA DE TURISMO </h1>
<h1 align="center"> SPRINT 3 </h1>

**Índice**   
1. [Objetivo  Sprint 3 💡](#id1)
2. [Dependencias Sprint 3 🛠️](#id2)
3. [Contribuyentes Sprint 3 ✒️](#id3)
4. [Especificación de Requerimiento Nº 1 Sprint 3 📝](#id4)
5. [Especificación de Requerimiento Nº 2 Sprint 3 📝](#id5)
6. [Especificación de Requerimiento Nº 3 Sprint 3 📝](#id6)


:bangbang:[**Link de Tablero de trabajo de daily en Trello**](https://trello.com/b/QEQDbLOv/dailybootcamp):bangbang:


## Objetivo  Sprint 3 💡<a name="id1"></a>

El objetivo de este desafío es aplicar los contenidos dados hasta el momento durante el BOOTCAMP (Git, Java, Spring Boot, Testing, JPA y Hibernate) en la implementación de una API REST a partir de un enunciado propuesto, una especificación de requisitos técnico-funcionales y documentación anexada.

## Dependencias Sprint 3 🛠️<a name="id2"></a>
 ```
   <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
      <dependency>
            <groupId>javax.validation</groupId>
            <artifactId>validation-api</artifactId>
            <version>2.0.1.Final</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>
        <dependency>
            <groupId>org.hibernate.validator</groupId>
            <artifactId>hibernate-validator</artifactId>
        </dependency>
        <dependency>
            <groupId>com.fasterxml.jackson.datatype</groupId>
            <artifactId>jackson-datatype-jsr310</artifactId>
        </dependency>
    </dependencies>
 ```
-Manejador de dependencias:Maven.

-Java:11.

## Contribuyentes Sprint 3 ✒️<a name="id3"></a>
  ```
  Alvarez Lucas
  Ataides Nicolas
  Flax Marco Maria Laura de las Victorias
  Maine Martin
  Vallejos Podio Valentina
  ```
## Especificación de Requerimiento Nº 1 Sprint 3 📝<a name="id4"></a>

### Requerimiento Nº 1:

En las versiones anteriores de la aplicación, el manejo de datos se llevó a cabo con una “base de datos” lógica, implementada mediante collections o maps y en algunos casos JSON. Llegó el momento de implementar una base de datos relacional que permita realizar operaciones CRUD/ABM.

Iconos indicadores :

:heavy_check_mark:Tarea cumplida.



####  User Story:

COMO encargado de la empresa de turismo QUIERO poder realizar operaciones de alta, baja, modificación y lectura sobre una base de datos PARA poder almacenar cada uno de los datos necesarios para la administración de las reservas de mi empresa.


OPERACIONES/ACCIONES TÉCNICAS Y FUNCIONALES  NECESARIAS:

Configuración de conexión de base de datos y dependencias

Mapeo de entidades JPA + Hibernate

Se debe permitir el alta, baja, modificación y consulta de vuelos.

Se debe permitir el alta, baja, modificación y consulta de hoteles

Se debe permitir el alta, baja, modificación y consulta de reservas.


ROLES Y PERMISOS:

Para esta US aún no se aplicarán roles o permisos de ningún tipo. Se deberán generar únicamente los endpoints correspondientes.


VALIDACIONES NECESARIAS:

Para bajas y modificaciones debe existir el hotel, reserva o vuelo correspondiente. Caso contrario, se debe retornar el correspondiente status code y msje.

Para las consultas, en caso de no encontrar resultados se debe informar dicha situación mediante un mensaje.

Para altas, validar que no exista anteriormente una reserva con idénticas características.

Antes de dar de baja un vuelo o un hotel, validar que no se encuentre actualmente en una reserva. En caso de que sea así, no se podrá eliminar el registro, sin antes haber cancelado/eliminado la reserva.

Especificaciones Técnicas necesarias: 


| HTTP   |     Plantilla URI   | Descripción | Dificultad |
|--------------|:-----------------:|-----------------:|-----------:|
||ALTAS|
| POST | /api/v1/hotels/new/ | Alta de un nuevo hotel | -----------  |  
| POST| /api/v1/flights/new | Alta de un nuevo vuelo | -----------  |  
| POST | /api/v1/hotel-booking/new | Alta de una reserva de hotel | ----------- |  
| POST | /api/v1/flight-reservation/new | Alta de una reserva de vuelo | -----------  |  
||||
||MODIFICACIONES|
| PUT | /api/v1/flights/edit?flightNumber=number | Modificación de un vuelo | -----------  |  
| PUT| /api/v1/hotels/edit?hotelCode=code | Modificación de un hotel | -----------  |  
| PUT | /api/v1/hotel-booking/edit?id=num_id | Modificación de una reserva de hotel | ----------- |  
| PUT | /api/v1/flight-reservation/edit?id=num_id | Modificación de una reserva de vuelo | -----------  |  
||||
||CONSULTAS/LECTURAS|
| GET |/api/v1/hotels | Listado de todos los hoteles  | -----------  |  
| GET| /api/v1/hotels?dateFrom=dd/mm/aaaa&dateTo=dd/mm/aaaa&destination=destination_name | Listado de hoteles según filtros | -----------  |  
| GET | /api/v1/flights | Listado de todos los vuelos | ----------- |  
| GET | /api/v1/flights?dateFrom=dd/mm/aaaa&dateTo=dd/mm/aaaa&origin=origin_name&destination=destination_name  | Listado de vuelos según filtros | -----------  |  
| GET | /api/v1/hotel-bookings/ | Listado de todas las reservas de hotel | ----------- |  
| GET | /api/v1/flight-reservations/ | Listado de todas las reservas de vuelos | ----------- |
||||
||BAJAS|
| DELETE | /api/v1/hotels/delete?hotelCode=code | Baja de un hotel | -----------  |  
| DELETE| /api/v1/flights/delete?flightNumber=number | Baja de un vuelo | -----------  |  
| DELETE | /api/v1/hotel-booking/delete?id=num_id | Baja de una reserva de hotel | ----------- |  
| DELETE | /api/v1/flight-reservation/delete?id=num_id | Baja de una reserva de vuelo | -----------  |  


## Especificación de Requerimiento Nº 2 Sprint 3 📝<a name="id5"></a>

### Requerimiento Nº 2:

La agencia de turismo está dispuesta a escuchar sugerencias que provengan de los desarrolladores del proyecto para implementar nuevas funcionalidades que se adapten al desarrollo del sistema actual.
Para ello, se sugiere tener en cuenta los siguientes criterios:
Ser consistente con la lógica de negocios ya existente (se deberán tomar como base todos los requerimientos ya implementados, siendo la nueva implementación un “incremento” de las anteriores).
Agregar valor a la implementación propuesta en los requerimientos anteriores (puede ser un agregado a un requerimiento ya existente que proporcione un PLUS).
Plantear y resolver un problema que se corresponda con una situación posible del negocio propuesto (Puede ser un requerimiento totalmente nuevo pero debe de tener relación con los anteriores).
Considerar la posibilidad de que la propuesta deba ser presentada (o “vendida”) al dueño de la agencia, por lo cual, la sugerencia debe estar bien presentada y poder llamar la atención o “interés” de esta persona para lograr convencer a éste.

Entregables:

Este requerimiento libre contará con dos posibles formas de entrega, la primera de ellas de caracter OBLIGATORIO y la segunda en caracter de BONUS (opcional). Las mismas se citan a continuación:

Opción 1 - Entregable Obligatorio:

Se deberá entregar el proyecto completo (incluido el nuevo requerimiento planteado) mediante Github. Entiéndase por proyecto completo TODOS LOS ARCHIVOS relacionados al mismo.
Se deberá incorporar un archivo Readme.txt descriptivo incluido en el repositorio de Github.
Documentación en PDF en formato de User Stories y especificaciones técnicas  funcionales (teniendo en cuenta el template que será otorgado por los facilitadores).
El nuevo requerimiento deberá contar con al menos 3 nuevos endpoints o en su defecto 3 modificaciones sobre endpoints ya existentes que permitan nuevas funcionalidades.
Colección de Postman con casos de prueba para cada end-point.
Al menos 4 tests unitarios implementados en total.
Como información adicional se puede proporcionar nombre de la bd creada, algún archivo SQL con datos de ejemplo o toda información de respaldo que pueda facilitar la conexión con la base de datos.

Opción 2 - Entregable Bonus (Opcional):

Todos los entregables mencionados en la Opción 1
Diagrama de clases (UML) completo del Proyecto.
Diagrama Entidad-Relación (DER) completo de la base de datos implementada.
Requerimiento Nº 5 con 4 end-points nuevos o más.
Cobertura total de testeo superior a un 80%.
Documentación completa a través de Swagger


Especificaciones Técnicas necesarias: 


| HTTP   |     Plantilla URI   | Descripción | Dificultad |
|--------------|:-----------------:|-----------------:|-----------:|
| POST | /api/v1/touristicpackage/new/ | Alta de un nuevo paquete | -----------  |  
| PUT| /api/v1/touristicpackage/edit?packageNumber=number | Modificación de un paquete | -----------  |  
| GET | /api/v1/touristicpackages | Listado de todos los paquetes dados de alta | ----------- |  
| DELETE | /api/v1/touristicpackage/delete?packageNumber=number | Baja de un paquete | -----------  |  


## Especificación de Requerimiento Nº 3 Sprint 3 📝<a name="id6"></a>


### Requerimiento Nº 3:

El dueño de la agencia de turismo manifestó la necesidad de conocer los montos brutos totales que ingresan en un determinado día o mes a partir de cada una de las reservas realizadas. Para ello solicita que sus empleados sean capaces de realizar consultas por día o mes y brindarle dicha información.


Iconos indicadores :

:heavy_check_mark:Tarea cumplida.



####  User Story:

COMO empleado de la agencia de turismo QUIERO poder obtener la sumatoria de los montos totales por día a partir de las reservas PARA informar los ingresos de un determinado día al dueño de la agencia.


VALIDACIONES NECESARIAS

Corroborar que la fecha ingresada (para la consulta por determinado día) cumpla con el formato dd/mm/yyyy

Corroborar que el mes ingresado sea un número del 1 al 12 (siendo enero=1, febrero=2, etc). Recordar que será necesario también indicar el año del mes que se requiere conocer los ingresos.


Especificaciones Técnicas necesarias: 


| HTTP   |     Plantilla URI   | Descripción | Dificultad |
|--------------|:-----------------:|-----------------:|-----------:|
| GET | /api/v1/income?date=dd/mm/yyyy | Total de ingresos brutos para un día en particular  a partir de reservas | -----------  |  
| GET| /api/v1/income?month=1&year=2021 | Total de ingresos brutos para un mes y año en particular  a partir de reservas | -----------  |  





