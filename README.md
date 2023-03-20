<h1 align="center"> AGENCIA DE TURISMO </h1>
<h1 align="center"> SPRINT 2 </h1>

**Índice**   
1. [Objetivo  Sprint 2 💡](#id1)
2. [Dependencias Sprint 2 🛠️](#id2)
3. [Contribuyentes Sprint 2 ✒️](#id3)
4. [Especificación de Requerimientos Funcionales Sprint 2 📝](#id4)
5. [Tests Unitarios Sprint 2 ▶️](#id5)
6. [Bonus Sprint 2 ▶️](#id6)


:bangbang:[**Link de Tablero de trabajo de daily en Trello**](https://trello.com/b/QEQDbLOv/dailybootcamp):bangbang:
:bangbang:[**Link de Tablero de trabajo en Trello**](https://trello.com/b/272x0iIb/bootcamp-java):bangbang:

## Objetivo  Sprint 2 💡<a name="id1"></a>

El objetivo de este desafío es aplicar los contenidos dados hasta el momento durante el BOOTCAMP (Git, Java, Spring y Testing) en la implementación de una API REST a partir de un enunciado propuesto, una especificación de requisitos técnico-funcionales y documentación anexada.

## Dependencias Sprint 2 🛠️<a name="id2"></a>
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

## Contribuyentes Sprint 2 ✒️<a name="id3"></a>
  ```
  Alvarez Lucas
  Ataides Nicolas
  Flax Marco Maria Laura de las Victorias
  Maine Martin
  Vallejos Podio Valentina
  ```
## Especificación de Requerimientos Funcionales Sprint 2 📝<a name="id4"></a>

### VALIDACIONES:
Iconos indicadores :

:heavy_check_mark:Tarea cumplida.

:heavy_check_mark:No se realizo debido a indicaciones Scrum Master. Ya que contienen anotaciones personalizadas o por otro motivo. Lo que si se realizo fueron las excepciones.


#### 1.1 Hoteles


US 0002: Obtener un listado de todos los hoteles disponibles en un determinado rango de fechas y según el destino seleccionado. 


| Parámetros   |     Validación    | Mensaje de error | Dificultad |
|--------------|:-----------------:|-----------------:|-----------:|
| :heavy_check_mark:Fecha Entrada | Formato correcto | Formato de fecha debe ser yyyy/mm/dd | Se realizo con anotaciones personalizadas  |  
| :heavy_check_mark:Fecha Entrada | Fecha de entrada < a fecha de salida | La fecha de entrada debe ser menor a la de salida | Se realizo con anotaciones personalizadas  |  
||||
| :heavy_check_mark:Fecha Salida  | Formato correcto | Formato de fecha debe ser yyyy/mm/dd | Se realizo con anotaciones personalizadas  |  
| :heavy_check_mark:Fecha Salida  | Fecha de salida > a fecha de entrada | La fecha de entrada debe ser mayor a la de entrada | Se realizo con anotaciones personalizadas  |  
||||
| Destino  | Que exista | El destino elegido no existe |


US 0003: Realizar una reserva de un hotel, indicando cantidad de personas, fecha de entrada, fecha de salida y tipo de habitación. Obtener como respuesta el monto total de la reserva realizada


| Parámetros   |     Validación    | Mensaje de error | Dificultad |
|--------------|:-----------------:|-----------------:|-----------------:|
| :heavy_check_mark:Fecha Entrada | Formato correcto | Formato de fecha debe ser yyyy/mm/dd | Se realizo con anotaciones personalizadas  |  
| :heavy_check_mark:Fecha Entrada | Fecha de entrada < a fecha de salida | La fecha de entrada debe ser menor a la de salida | Se realizo con anotaciones personalizadas  |  
||||
| :heavy_check_mark:Fecha Salida  | Formato correcto | Formato de fecha debe ser yyyy/mm/dd | Se realizo con anotaciones personalizadas  |  
| :heavy_check_mark:Fecha Salida  | Fecha de salida > a fecha de entrada | La fecha de entrada debe ser mayor a la de entrada | Se realizo con anotaciones personalizadas  |  
||||
| :heavy_check_mark:Destino  | Que exista | El destino elegido no existe |
||||
| :heavy_check_mark:Cantidad de Personas  | Que sea un valor numérico | La cantidad de personas debe ser un valor numérico | Al ser declarado como atributo integer, no se le puede hacer una anotacion para validarlo  | 
||||
| :heavy_check_mark:Tipo de Habitación  | Que coincida con la cantidad de personas | El tipo de habitación seleccionada no coincide con la cantidad de personas que se alojarán en ella | Es con anotaciones personalizadas  |
||||
| :heavy_check_mark:E-mail  |  El mail cumpla con el formato de correo electrónico nombre@dominio.com (también es válido .com.ar, .com.co, etc) | Por favor ingrese un e-mail válido |
||||
| :heavy_check_mark:Intereses  | En caso que la tarjeta sea de crédito verificar recargo de intereses. Ej: hasta 3 cuotas 5%, de 3 a 6 10%, etc.En caso que sea tarjeta de débito verificar que no se incorporen intereses y que permita el pago en una sola cuota | Tarjeta de crédito: Devolver porcentaje y monto de interés (recargo).Tarjeta de débito: Informar que se ha ingresado una cantidad de cuotas diferente a 1 | Sprint1 no pedia intereses por eso no se realizo,  |


#### 1.2 Vuelos


US 0005: Obtener un listado de todos los vuelos disponibles en un determinado rango de fechas y según el destino y el origen seleccionados. 


| Parámetros   |     Validación    | Mensaje de error | Dificultad |
|--------------|:-----------------:|-----------------:|-----------------:|
| :heavy_check_mark:Fecha Entrada | Formato correcto | Formato de fecha debe ser yyyy/mm/dd | Se realizo con anotaciones personalizadas  |  
| :heavy_check_mark:Fecha Entrada | Fecha de entrada < a fecha de salida | La fecha de entrada debe ser menor a la de salida | Se realizo con anotaciones personalizadas  |  
||||
| :heavy_check_mark:Fecha Salida  | Formato correcto | Formato de fecha debe ser yyyy/mm/dd | Se realizo con anotaciones personalizadas  |  
| :heavy_check_mark:Fecha Salida  | Fecha de salida > a fecha de entrada | La fecha de entrada debe ser mayor a la de entrada | Se realizo con anotaciones personalizadas  |  
||||
| Origen  | Que exista | El Origen elegido no existe |
||||
| Destino  | Que exista | El destino elegido no existe |


US 0006: Realizar una reserva de un vuelo, indicando cantidad de personas, origen, destino, fecha de ida y fecha de vuelta. Obtener como respuesta el monto total de la reserva realizada.


| Parámetros   |     Validación    | Mensaje de error | Dificultad |
|--------------|:-----------------:|-----------------:|-----------------:|
| :heavy_check_mark:Fecha Entrada | Formato correcto | Formato de fecha debe ser yyyy/mm/dd | Se realizo con anotaciones personalizadas  |  
| :heavy_check_mark:Fecha Entrada | Fecha de entrada < a fecha de salida | La fecha de entrada debe ser menor a la de salida | Se realizo con anotaciones personalizadas  |  
||||
| :heavy_check_mark:Fecha Salida  | Formato correcto | Formato de fecha debe ser yyyy/mm/dd | Se realizo con anotaciones personalizadas  |  
| :heavy_check_mark:Fecha Salida  | Fecha de salida > a fecha de entrada | La fecha de entrada debe ser mayor a la de entrada | Se realizo con anotaciones personalizadas  |  
||||
| :heavy_check_mark:Destino  | Que exista | El destino elegido no existe |
||||
| :heavy_check_mark:Cantidad de Personas:warning:  | Que sea un valor numérico | La cantidad de personas debe ser un valor numérico | Al ser declarado como atributo integer, no se le puede hacer una anotacion para validarlo  | 
||||
| :heavy_check_mark:E-mail  |  El mail cumpla con el formato de correo electrónico nombre@dominio.com (también es válido .com.ar, .com.co, etc) | Por favor ingrese un e-mail válido |
||||
| :heavy_check_mark:Intereses (Sprint1 no pedia intereses por eso no se realizo)  | En caso que la tarjeta sea de crédito verificar recargo de intereses. Ej: hasta 3 cuotas 5%, de 3 a 6 10%, etc.En caso que sea tarjeta de débito verificar que no se incorporen intereses y que permita el pago en una sola cuota | Tarjeta de crédito: Devolver porcentaje y monto de interés (recargo).Tarjeta de débito: Informar que se ha ingresado una cantidad de cuotas diferente a 1 | Sprint1 no pedia intereses por eso no se realizo  |


## Tests Unitarios Sprint 2 ▶️<a name="id5"></a>


#### User Stories

La lista de requerimientos que habían sido solicitados por la Agencia de turismo eran los siguientes:

US  0001: Obtener un listado de todos los hoteles registrados

US 0002: Obtener un listado de todos los hoteles disponibles en un determinado rango de fechas y según el destino seleccionado.

US 0003: Realizar una reserva de un hotel, indicando cantidad de personas, fecha de entrada, fecha de salida y tipo de habitación. Obtener como respuesta el monto total de la reserva realizada

US 0004: Obtener un listado de todos los vuelos registrados.

US 0005: Obtener un listado de todos los vuelos disponibles en un determinado rango de fechas y según el destino y el origen seleccionados. 

US 0006: Realizar una reserva de un vuelo, indicando cantidad de personas, origen, destino y fecha de ida. Obtener como respuesta el monto total de la reserva realizada.


| User Story(* Requerido) |    Situaciones/Datos de entrada   | Comportamiento Esperado | Dificultad |
|-------------------------|:---------------------------------:|------------------------:|-----------:|
| US-0001 | Se envía solicitud de listado de todos los hoteles registrados | Si hay hoteles registrados: Permite continuar con normalidad y muestra listado completo. Si no hay hoteles: Notifica la no existencia mediante una excepción |
||||
| US 0002 | Se envía solicitud de listado de todos los hoteles disponibles en determinado rango de fechas y destinos. Datos de entrada:Fecha Desde,Fecha Hasta,Destinos |Si hay registros que cumplan el criterio: Se debe obtener un listado de los hoteles disponibles en ese rango de fechas en esos destinos.No se cumple:Notifica la situación mediante una excepción  |inconvenientes al momento de realizar varios when, en la implementacion de test con mocks para el segundo requisito|
||||
| US-0003 | Se envía solicitud de reserva de un hotel.Datos de entrada:Id hotel,Cantidad de personas,Fecha Entrada,Fecha Salida,Tipo de habitación  |Se cumplen todos los criterios:Responde un Status code 200 con el monto total de la reserva.Da de alta una nueva reserva.No se cumple:Notifica error/imposibilidad de finalizar la transacción  |inconvenientes al momento de realizar varios when, en la implementacion de test con mocks para el segundo requisito|
||||
| US 0004  | Se envía solicitud de listado de todos los vuelos registrados |Si hay vuelos registrados:Permite continuar con normalidad y muestra listado completo.Si no hay vuelos registrados:Notifica la no existencia mediante una excepción |
||||
| US 0005 | Se envía solicitud de listado de todos los vuelos disponibles en determinado rango de fechas y según un origen y destino.Datos de entrada:Fecha Desde,Fecha Hasta,Origen,Destino  |Si hay registros que cumplan el criterio:Se debe obtener un listado de los vuelos disponibles en ese rango de fechas en esos destinos.No se cumple:Notifica la situación mediante una excepción  |
||||
| US 0006 | Se envía solicitud de reserva de un vuelo.Datos de entrada:Id vuelo,Cantidad de personas,Origen,Destino,Fecha de ida  |Se cumplen todos los criterios:Responde un Status code 200 con el monto total de la reserva.Da de alta una nueva reserva.No se cumple:Notifica error/imposibilidad de finalizar la transacción  |El primer requisito se nos dificulto ya que habia un inconeniente en la factory.Inconvenientes al momento de realizar varios when, en la implementacion de test con mocks para el segundo requisito|

## Bonus Sprint 2 ▶️<a name="id6"></a>

Siguiendo con el principio de que la agencia posee unos estándares de calidad muy altos, un especialista sugirió la posibilidad de contar también con diferentes tests de integración además de los tests unitarios mencionados anteriormente; sin embargo, el especialista conoce que los tiempos son acotados, por lo que sugiere llevar a cabo esta implementación solo en caso de que alcancen los tiempos y se pueda cumplir con la fecha de entrega estimada.

Dificultad: En lo DTO de Hotel y FLights no teniamos la anotación @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING). Que tuvimos que agregarla.
