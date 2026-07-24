# Sistema de Gestión de Pedidos - Food Store

Trabajo Integrador Programacion 2. Aplicación de consola en Java para administrar categorias, productos, usuarios y pedidos.

## Integrantes
- Alumno: Adrian Javier Gonzalez
- Materia: Programación 2
- Año: 2026
-Comision:6

## Tecnologías Utilizadas
- Java 17
- Programación Orientada a Objetos
- Manejo de Excepciones Personalizadas
- Enums para Estado, FormaPago y Rol
- Interfaces: Calculable

## Estructura del Proyecto

integrado.prog2
├── entities     -> Clases: Usuario, Producto, Categoria, Pedido, DetallePedido
├── service      -> Lógica de negocio: CRUD y validaciones
├── exception    -> ValidacionException, DatoDuplicadoException, RecursoNoEncontradoException
├── enums        -> Estado, FormaPago, Rol
├── menu         -> Clase Menu para la interfaz de consola
└── config       -> Clase Utilitarios

## Cómo ejecutar el proyecto

### Opción 1: Desde NetBeans
1. Abrir NetBeans
2. Archivo > Abrir Proyecto > Seleccionar carpeta `TP_Integrador_Sistema_Foodstore`
3. Clic derecho en `Main.java` > `Ejecutar`

### Opción 2: Desde CMD / Terminal
1. Pararse en la carpeta raíz del proyecto `TP_Integrador_Sistema_Foodstore`
2. Compilar:
   ```cmd
   javac -d out src\integrado\prog2\Main.java

   java -cp out integrado.prog2.Main

LINK VIDEO DEMOSTRACION 
https://www.youtube.com/watch?v=8k2hHNHaEEo
