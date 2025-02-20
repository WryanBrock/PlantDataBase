# Plant Database Management System

## Overview
This Java project is a database-driven application that manages plant records using CRUD operations. It follows the DAO (Data Access Object) pattern to maintain a clean separation between business logic and database interactions.

## Features
- Create, Read, Update, and Delete (CRUD) operations for plant records.
- Uses JDBC to connect and interact with a relational database.
- Implements DAO pattern for better modularity and maintainability.
- Encapsulates database logic in separate classes.

## Technologies Used
- **Java** (OOP, Interfaces, Exception Handling)
- **JDBC** for database connection
- **MySQL** or any relational database
- **DAO Design Pattern**

## Project Structure
/src
│── DBConnect.java      # Handles database connection
│── DAO.java            # Interface defining CRUD methods
│── Plant.java          # Model class for Plant entity
│── PlantImpl.java      # Implements DAO methods for Plant operations
│── Operation.java      # Provides CRUD functionality
│── Main.java           # Entry point to run the application

## Setup & Installation
1. Clone the repository: git clone https://github.com/your-username/plant-database.git
2. Open the project in an IDE (e.g., IntelliJ IDEA, Eclipse, VS Code).
3. Ensure you have Java and MySQL installed.
4. Configure database credentials in `DBConnect.java`.
5. Compile and run the project:
   javac Main.java
   java Main
   
## Future Enhancements
- Implement a GUI for better user interaction.
- Introduce Spring Boot for a web-based approach.
- Add logging and unit testing.

## License
This project is open-source and available under the MIT License.
