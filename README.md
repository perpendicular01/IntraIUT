# IntraIUT 🎓

A unified event management platform for Islamic University of Technology (IUT) clubs and societies.

## 🎯 Problem Statement

IUT has multiple clubs and societies that organize events frequently, but:
- Event announcements are scattered across various Facebook pages
- Information flow depends on Class Representatives (CRs) forwarding posts
- Many students miss events due to inconsistent communication
- No centralized platform to view all ongoing/upcoming events
- Difficulty in tracking event overlaps or planning ahead

## 💡 Solution

IntraIUT provides a unified platform that:
- Lists all IUT club and society events in one centralized location
- Reduces dependency on CRs and Facebook algorithms
- Ensures every student has equal access to event information
- Allows clubs to schedule and promote events with better visibility
- Promotes higher participation and better time management
- Implements secure JWT authentication with role-based access control

## ✨ Features

- **Centralized Event Dashboard**: View all upcoming and ongoing events in one place
- **Advanced Filtering**: Filter events by club, date, category, or type
- **JWT Authentication**: Secure login system with token-based authentication
- **Role-Based Access Control**: Different permissions for students, club admins, and system administrators
- **Event Management**: Clubs can create, update, and manage their events
- **Responsive Design**: Accessible on desktop and mobile devices
- **Real-time Updates**: Stay informed about the latest events

## 🛠️ Technology Stack

### Backend
- **Java**: Core application logic
- **Spring Boot**: RESTful API framework
- **Spring Security**: Authentication and authorization
- **JWT (JSON Web Tokens)**: Secure token-based authentication
- **MySQL**: Relational database for data persistence
- **JPA/Hibernate**: Object-relational mapping



## 📋 Prerequisites

- Java 17 or higher
- MySQL 8.0 or higher
- Maven 3.6+
- IDE (IntelliJ IDEA, Eclipse, or VS Code)

## ⚙️ Installation & Setup

### 1. Clone the Repository
```bash
git clone https://github.com/yourusername/IntraIUT.git
cd IntraIUT
```

### 2. Database Configuration

Create a MySQL database:
```sql
CREATE DATABASE intraiut;
```

Update `application.properties` with your database credentials:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/intraiut
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

### 3. Configure JWT Secret

Add JWT configuration to `application.properties`:
```properties
jwt.secret=your_secret_key_here
jwt.expiration=86400000
```

### 4. Build the Project
```bash
mvn clean install
```

### 5. Run the Application
```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`



## API Authentication
All protected endpoints require a JWT token in the Authorization header:
```
Authorization: Bearer <your_jwt_token>
```

## 📡 API Endpoints

### Authentication
- `POST /api/v1/auth/login` - User login with JWT token
- `POST /api/users` - Register new user

### Users
- `GET /api/users` - Get all users profiles
- `GET /api/users/{userId}` - Get user profile by ID
- `PUT /api/users/{userId}` - Update user profile
- `DELETE /api/users/{userId}` - Delete user profile

### Categories
- `GET /api/v1/categories` - Get all categories (Public)
- `GET /api/v1/categories/{catId}` - Get category by ID (Public)
- `POST /api/v1/categories` - Create new category (ADMIN only)
- `PUT /api/v1/categories/{catId}` - Update category (ADMIN only)
- `DELETE /api/v1/categories/{catId}` - Delete category (ADMIN only)

### Posts
- `GET /api/v1/posts` - Get all posts (Paginated, Public)
- `GET /api/v1/posts/{postId}` - Get post by ID (Public)
- `GET /api/v1/posts/search/{keywords}` - Search posts by keywords (Public)
- `POST /api/v1/category/{categoryId}/posts` - Create new post (Authenticated)
- `PUT /api/v1/posts/{postId}` - Update post (Owner only)
- `DELETE /api/v1/posts/{postId}` - Delete post (Owner only)

### User Posts
- `GET /api/v1/user/posts` - Get current user's posts (Authenticated)
- `GET /api/v1/user/{userId}/posts` - Get posts by user ID (Public)

### Category Posts
- `GET /api/v1/category/{categoryId}/posts` - Get posts by category (Public)

### Comments
- `POST /api/v1/post/{postId}/comments` - Create comment on post (Authenticated)
- `DELETE /api/v1/comments/{commentId}` - Delete comment (Owner only)

### Admin Operations
- `GET /api/v1/admin/posts/pending` - Get pending posts for approval (ADMIN only)
- `PUT /api/v1/admin/posts/{postId}/approve` - Approve post (ADMIN only)
- `PUT /api/v1/admin/posts/{postId}/reject` - Reject post (ADMIN only)

