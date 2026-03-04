Backend-сервис на Spring Boot, реализующий полноценный CRUD REST API для управления сотрудниками. Приложение упаковано в Docker и использует PostgreSQL в качестве базы данных.

---

## Стек технологий

| Категория       | Технология                        |
|-----------------|-----------------------------------|
| Язык            | Java 17                           |
| Фреймворк       | Spring Boot 4.0.0                 |
| Веб-слой        | Spring Web MVC                    |
| Слой данных     | Spring Data JPA / Hibernate       |
| База данных     | PostgreSQL 13                     |
| Сборка          | Maven                             |
| Утилиты         | Lombok                            |
| Тестирование    | JUnit Jupiter + Testcontainers    |
| Контейнеризация | Docker / Docker Compose           |

---

## Структура проекта

```
src/main/java/com/company/thirdspringbootproject/
├── ThirdSpringBootProjectApplication.java   # Точка входа
├── controller/
│   └── EmployeeController.java             # REST-контроллер
├── service/
│   └── EmployeeService.java                # Бизнес-логика и маппинг
├── repository/
│   └── EmployeeRepository.java             # JPA-репозиторий
├── model/
│   └── Employee.java                       # JPA-сущность
└── dto/
    ├── EmployeeReqDTO.java                 # DTO для запросов (create/update/get)
    └── EmployeeResDTO.java                 # DTO для ответа (get all)
```

---
