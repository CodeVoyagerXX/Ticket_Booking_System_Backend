# Ticket Booking System – Backend

A Java‑based backend service (likely using Spring Boot and Maven) powering a ticket booking platform—handling user authentication, event/ticket management, and booking operations.

---

## 🚀 Features

* **User authentication** via secure login/signup (JWT or session-based)
* **Event listing** with available tickets and schedules
* **Ticket booking** with real-time availability checks and prevention of double-booking
* **User booking history** retrieval
* **Admin operations**: create/update/delete events
* **Modular design**: clear separation of controllers, services, repositories, and models

---

## 🗂 Project Structure

```
Ticket_Booking_System_Backend/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── controllers/
│   │   │   ├── services/
│   │   │   ├── repositories/
│   │   │   ├── models/
│   │   │   └── Application.java
│   └── test/
├── .gitignore
├── pom.xml
└── mvnw, mvnw.cmd, .mvn/ wrapper
```

---

## 🧱 Prerequisites

* Java 11+ installed
* Maven
* A database: PostgreSQL, MySQL, or embedded H2
* (Optional) Docker, if running via container

---

## ⬇️ Installation & Setup

1. **Clone** the repository:

   ```bash
   git clone https://github.com/CodeVoyagerXX/Ticket_Booking_System_Backend.git
   cd Ticket_Booking_System_Backend
   ```

2. **Configure** `application.properties` (or `.yaml`):

   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/ticketdb
   spring.datasource.username=...
   spring.datasource.password=...
   jwt.secret=your_jwt_secret_here
   ```

3. **Build & Run** the project:

   ```bash
   ./mvnw clean install
   ./mvnw spring-boot:run
   ```

4. **Access API** on `http://localhost:8080/`

---

## 🧭 API Endpoints

* `POST /api/auth/signup` — register a new user
* `POST /api/auth/login` — obtain JWT
* `GET /api/events` — list all events or shows
* `GET /api/events/{id}` — event details
* `POST /api/events` — create new event (admin only)
* `PUT /api/events/{id}` — update event (admin only)
* `DELETE /api/events/{id}` — remove event (admin only)
* `POST /api/bookings` — create a ticket booking
* `GET /api/bookings` — list user's bookings

*(Adjust paths per actual implementation.)*

---

## 🧪 Development

* `./mvnw spring-boot:run` — run in development
* `./mvnw test` — execute tests
* Rebuild via `./mvnw clean package`

---

## 📦 Tech Stack

* Java + Spring Boot
* Spring Data JPA (ORM)
* PostgreSQL / MySQL / H2
* JWT authentication
* Maven build tool
* (Optional) Docker for containerized deployments

---

## 🤝 Contribution

Contributions welcome via PRs:

1. Fork the repo
2. Create a feature branch (`git checkout -b feature/my-feature`)
3. Commit changes (`git commit -m "Add feature"`)
4. Push branch (`git push origin feature/my-feature`)
5. Open a Pull Request

---

## 📄 License

Distributed under the **MIT License**. See [LICENSE](LICENSE) for details.

---


