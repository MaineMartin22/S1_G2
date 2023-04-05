# S1_G2

<h1 align="center"> AGENCIA DE TURISMO </h1>
<h1 align="center"> SPRINT 3 </h1>

**Índice**   
1. [Objetivo  Sprint 3 💡](#id1)
2. [Dependencias Sprint 3 🛠️](#id2)
3. [Contribuyentes Sprint 3 ✒️](#id3)
4. [Especificación de Requerimiento Nº 1 Sprint 3 📝](#id4)
5. [Especificación de Requerimiento Nº 2 Sprint 3 📝](#id5)
6. [Entregables Sprint 3 📝](#id6)




:bangbang:[**Link de Tablero de trabajo de daily en Trello**](https://trello.com/b/QEQDbLOv/dailybootcamp):bangbang:


## Objetivo  Sprint 3 💡<a name="id1"></a>

El objetivo de este desafío es aplicar los contenidos dados hasta el momento durante el BOOTCAMP (Git, Java, Spring Boot, Testing, JPA y Hibernate) en la implementación de una API REST a partir de un enunciado propuesto, una especificación de requisitos técnico-funcionales y documentación anexada.

## Dependencias Sprint 3 🛠️<a name="id2"></a>
 ```
 <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.modelmapper</groupId>
            <artifactId>modelmapper</artifactId>
            <version>3.1.1</version>
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
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>com.mysql</groupId>
            <artifactId>mysql-connector-j</artifactId>
            <scope>runtime</scope>
        </dependency>
        <!--  JPA  -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>org.modelmapper</groupId>
            <artifactId>modelmapper</artifactId>
            <version>2.4.4</version>
        </dependency>
        <!--  H2  -->
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <scope>runtime</scope>
        </dependency>
    </dependencies>
 ```
-Manejador de dependencias:Maven.

-Java:11.

## Contribuyentes Sprint 3 ✒️<a name="id3"></a>
  ```
  
  Ataides Nicolas
 
  ```
## Especificación de Requerimiento Nº 1 Sprint 3 📝<a name="id4"></a>

### Requerimiento Nº 2:

La agencia de turismo está dispuesta a escuchar sugerencias que provengan de los desarrolladores del proyecto para implementar nuevas funcionalidades que se adapten al desarrollo del sistema actual. Para ello, se sugiere tener en cuenta los siguientes criterios: Ser consistente con la lógica de negocios ya existente (se deberán tomar como base todos los requerimientos ya implementados, siendo la nueva implementación un “incremento” de las anteriores). Agregar valor a la implementación propuesta en los requerimientos anteriores (puede ser un agregado a un requerimiento ya existente que proporcione un PLUS). Plantear y resolver un problema que se corresponda con una situación posible del negocio propuesto (Puede ser un requerimiento totalmente nuevo pero debe de tener relación con los anteriores). Considerar la posibilidad de que la propuesta deba ser presentada (o “vendida”) al dueño de la agencia, por lo cual, la sugerencia debe estar bien presentada y poder llamar la atención o “interés” de esta persona para lograr convencer a éste.


Iconos indicadores :

:heavy_check_mark:Tarea cumplida.



####  User Story:

COMO desarrollador QUIERO que me traiga los vuelos PARA que el cliente tenga mas opciones que elegir y que su viaje sea mejor.


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


HTTP	Plantilla URI	Descripción	Dificultad
GET	/api/v1/hotel-reservation/PrecioTotal	

## Especificación de Requerimiento Nº 2 Sprint 3 📝<a name="id5"></a>

### Requerimiento Nº 2:

La agencia de turismo está dispuesta a escuchar sugerencias que provengan de los desarrolladores del proyecto para implementar nuevas funcionalidades que se adapten al desarrollo del sistema actual.
Para ello, se sugiere tener en cuenta los siguientes criterios:
Ser consistente con la lógica de negocios ya existente (se deberán tomar como base todos los requerimientos ya implementados, siendo la nueva implementación un “incremento” de las anteriores).
Agregar valor a la implementación propuesta en los requerimientos anteriores (puede ser un agregado a un requerimiento ya existente que proporcione un PLUS).
Plantear y resolver un problema que se corresponda con una situación posible del negocio propuesto (Puede ser un requerimiento totalmente nuevo pero debe de tener relación con los anteriores).
Considerar la posibilidad de que la propuesta deba ser presentada (o “vendida”) al dueño de la agencia, por lo cual, la sugerencia debe estar bien presentada y poder llamar la atención o “interés” de esta persona para lograr convencer a éste.




Especificaciones Técnicas necesarias: 


| HTTP   |     Plantilla URI   | Descripción | Dificultad |
|--------------|:-----------------:|-----------------:|-----------:|
| POST | /api/v1/touristicpackage/new/ | Alta de un nuevo paquete | -----------  |  
| PUT| /api/v1/touristicpackage/edit?packageNumber=number | Modificación de un paquete | -----------  |  
| GET | /api/v1/touristicpackages | Listado de todos los paquetes dados de alta | ----------- |  
| DELETE | /api/v1/touristicpackage/delete?packageNumber=number | Baja de un paquete | -----------  |  




| HTTP   |     Plantilla URI   | Descripción | Dificultad |
|--------------|:-----------------:|-----------------:|-----------:|
| GET | /api/v1/income?date=dd/mm/yyyy | Total de ingresos brutos para un día en particular  a partir de reservas | -----------  |  
| GET| /api/v1/income?month=1&year=2021 | Total de ingresos brutos para un mes y año en particular  a partir de reservas | -----------  |  


## Entregables:  Sprint 3 💡<a name="id6"></a>

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


