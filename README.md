# TPI – Programación 2

## 🏷 Dominio elegido: Productos y Códigos de Barras
El dominio modela la relación entre **productos** y sus **códigos de barras**.  
Un producto puede tener uno o varios códigos asociados, y el sistema permite:

```
- Crear productos  
- Registrar códigos de barras  
- Asociarlos a productos  
- Consultar productos por código  
- Listar productos con todos sus códigos  
- Modificar o eliminar registros
```

Este caso refleja un escenario típico de gestión comercial donde diferentes presentaciones o proveedores comparten o cambian códigos de barras.

## 📦 Estructura del repositorio
```
tpi-p2/
├── sql/
│ └── schema.sql
├── src/
│ └── main/
│ └── java/
│ ├── app/ # Clase Main y flujo de menú
│ ├── config/ # Configuración de base de datos
│ ├── dao/ # Acceso a datos (queries, DAO)
│ ├── entities/ # Clases del dominio (Producto, CodigoBarra, etc.)
│ └── service/ # Lógica de negocio
└── README.md
```

## 🛠 Requisitos

### Software
```
- **Java 17**  
- **MySQL**
- **Driver JDBC MySQL**
- (Opcional) IntelliJ IDEA / Eclipse / VSCode
```

🧭 Flujo de uso (menú)

Al iniciar el programa aparece un menú de consola:

```
Crear producto
Registrar código de barras
Asociar código a producto
Buscar producto por código de barras
Listar productos con sus códigos
Editar o eliminar registros
Salir
Toda la interacción es por teclado.
```

🎥 Enlace al video

🔗 Agregar el enlace al video aquí

### Base de datos
Crear la base de datos y configurar los datos en el archivo DatabaseConnection.java dentro de config
Se entrega un archivo .sql que contiene la creacion de las tablas, y del schema, se deja un nombre pro defecto definido (tpip2), en caso de tener un usuario/pass distinto de root/root colocar las correctas.

👥 Integrantes
```
Egea, Rodrigo
Jacinto, Gastón
Domínguez, Iván
Pérez Campos, Joaquín
```

📄 Licencia
Proyecto académico.
