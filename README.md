# Social Media Application

A robust, backend social media platform built with **Java** and **Spring Boot**. This application features a secure backend architecture using **Spring Security** and **JWT**.

## 🚀 Features

* **User Authentication:** Secure registration and login system using **JWT (JSON Web Tokens)**.
* **Profile Management:** Custom user profiles with the ability to update personal information.
* **Social Networking:** Users can create, edit, and delete posts.
* **Interactions:** Commenting system to foster engagement between users.
* **Security:** Role-based access control and protected REST endpoints.
* **Responsive UI:** Server-side rendered views using Thymeleaf and Bootstrap.

---

## 🛠️ Tech Stack

### Backend

* **Java 17+**
* **Spring Boot:** Core framework.
* **Spring Security:** For authentication and authorization.
* **Spring Data JPA:** For ORM and database communication.
* **Hibernate:** Database engine.
* **MySQL:** Relational database for persistent storage.
---

## 🏗️ Architecture

The project follows the standard **Layered Architecture** pattern to ensure clean separation of concerns:

1. **Controller Layer:** Handles HTTP requests and maps them to service methods.
2. **Service Layer:** Contains the core business logic.
3. **Repository Layer:** Interacts with the MySQL database using JPA.
4. **Security Layer:** Intercepts requests for JWT validation.

---

## 🚦 Getting Started

### Prerequisites

* **JDK 17** or higher.
* **Maven** 3.6+.
* **MySQL Server** running locally or via Docker.

### Installation

1. **Clone the repository:**
```bash
git clone https://github.com/MustafaGomaa1/Social-App.git
cd Social-App

```


2. **Configure the Database:**
Open `src/main/resources/application.properties` and update your MySQL credentials:

```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/social_app_db
    spring.datasource.username=your_username
    spring.datasource.password=your_password
    spring.jpa.hibernate.ddl-auto=update
    ```

3.  **Build and Run:**
    ```bash
    mvn clean install
    mvn spring-boot:run
    ```

4.  **Access the App:**
    Open your browser and navigate to `http://localhost:8080`.

---

## 🔑 API Endpoints (Brief)

| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `POST` | `/api/auth/register` | Register a new user |
| `POST` | `/api/auth/login` | Authenticate and receive JWT |
| `GET` | `/api/posts` | Fetch all social posts |
| `POST` | `/api/posts` | Create a new post (Auth required) |
| `GET`| `/swagger-ui/index.html#/`
---



## 📝 License
Distributed under the MIT License. See `LICENSE` for more information.

```
