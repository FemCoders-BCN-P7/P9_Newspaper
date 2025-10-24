# 🗞️ Yesterday’s Newspaper

## 📋 Project Overview

**Yesterday’s Newspaper** is a RESTful API developed with **Spring Boot** and **PostgreSQL**, designed to manage articles and users for a digital newspaper.  
It supports full **CRUD operations** (Create, Read, Update, Delete) for both **users** and **articles**, maintaining a **one-to-many** relationship:  
> One user can have multiple articles, while each article belongs to a single user.

The project was built collaboratively following **Agile methodology**, applying **MVC architecture**, **DAO pattern**, and **best practices in backend development**.

---
<img width="1616" height="413" alt="Captura_de_pantalla_2025-10-24_110047" src="https://github.com/user-attachments/assets/7a552ca5-4335-477b-9efe-5ea80ff4767f" />

<img width="1844" height="895" alt="Captura_de_pantalla_2025-10-24_110011" src="https://github.com/user-attachments/assets/9c8bddae-0ce1-4edf-aee0-73fae1575a77" />


---

## 🎯 Objectives

> The goal of the project was to design and implement a fully functional **backend system** that simulates the management of a real newspaper’s content.  
> It focuses on applying **Java + Spring Boot** for backend logic and **PostgreSQL** for data persistence, ensuring scalability, maintainability, and clean architecture.

---

## ⚙️ Technologies

- ☕ **Java 21**  
- 🌱 **Spring Boot**  
- 🐘 **PostgreSQL**  
- 🧩 **MapStruct** (for DTO mapping)  
- 🔧 **Maven**

---

## 🧰 Tools & Workflow

- 🧠 **Trello / Jira** → Agile task management and sprint tracking.  
- 💻 **Visual Studio Code** → Main development environment.  
- 🧪 **Postman** → API endpoint testing.  
- 🔄 **Git & GitHub** → Version control with feature branches and semantic commits (`feat`, `fix`, `refactor`).  

---

## 🧩 Architecture & Functionality

The application follows the **Model–View–Controller (MVC)** pattern:

1. **Entity Layer** → defines the `User` and `Article` tables and their one-to-many relationship.  
2. **Repository Layer** → handles database queries using **JpaRepository**.  
3. **Service Layer** → contains business logic and validations.  
4. **Controller Layer** → manages HTTP requests and responses (`GET`, `POST`, `PUT`, `DELETE`).  

**DTOs** and **MapStruct** were implemented to separate internal logic from data presentation, ensuring a clean and maintainable codebase.  
Database connection and persistence are managed through **JPA**.

---

## 🧪 Testing & Validation

- ✅ Integration and unit tests using **JUnit**.  
- ✅ CRUD endpoints validated with **Postman**.  
- ✅ Data consistency verified between entities (`User` ↔ `Article`).  

## 👥 Team Members

| Role | Name | GitHub | LinkedIn |
|------|------|--------|----------|
| 🧠 Product Owner | **[Erika P. Montoya](https://github.com/DevErika)** | [GitHub](https://github.com/DevErika) | [LinkedIn](https://www.linkedin.com/in/erikamontoya) |
| 🧩 Scrum Master | **[Mio Ogura](https://github.com/miaryl)** | [GitHub](https://github.com/miaryl) | [LinkedIn](https://www.linkedin.com/in/mio-ogura-a66880182/) |
| 💻 Developer | **[Suraya Mattar](https://github.com/surayac)** | [GitHub](https://github.com/surayac) | [LinkedIn](https://www.linkedin.com/in/suraya-mattar/) |
| 💻 Developer | **[Ángela Bello Medina](https://github.com/AngelaBello-creator)** | [GitHub](https://github.com/AngelaBello-creator) | [LinkedIn](https://www.linkedin.com/in/%C3%A1ngela-bello-759a72387/) |
