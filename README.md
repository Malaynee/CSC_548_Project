# CSC_548 Software Engineering Project
# 🍳 Recipe Manager

A full-stack web application for managing your pantry ingredients and discovering recipes. Built with Spring Boot, Thymeleaf, and modern web technologies.

[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen.svg)](https://spring.io/projects/spring-boot)

---

## 📖 Table of Contents

- [Features](#-features)
- [Technology Stack](#-technology-stack)
- [Getting Started](#-getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
  - [Running the Application](#running-the-application)
- [Usage Guide](#-usage-guide)
- [Project Structure](#-project-structure)
- [API Endpoints](#-api-endpoints)
- [Troubleshooting](#-troubleshooting)
- [Contributing](#-contributing)
- [Team](#-team)

---

## ✨ Features

### Core Functionality
- 🔐 **User Authentication** - Secure registration and login with SHA-256 password hashing
- 🥘 **Recipe Management** - Create, view, and organize your favorite recipes
- 🥬 **Ingredient Tracking** - Maintain a digital pantry inventory with quantities
- 🔍 **Advanced Filtering** - Find recipes by cuisine type and dietary restrictions (vegetarian, vegan)

### Technical Highlights
- RESTful API architecture
- MVC design pattern
- Session-based authentication
- JSON file persistence
- Dynamic form handling with AJAX
- Real-time client-side search

---

## 🛠️ Technology Stack

### Backend
- **Java 17** - Programming language
- **Spring Boot 3.x** - Application framework
- **Spring Web MVC** - Web layer
- **Maven** - Dependency management
- **Gson** - JSON serialization

### Frontend
- **HTML5 & CSS3** - Structure and styling
- **Thymeleaf** - Server-side template engine
- **JavaScript (ES6+)** - Client-side interactivity
- **Responsive Design** - Mobile-first approach

### Data Storage
- **JSON Files** - Lightweight data persistence
- File-based storage for recipes, ingredients, and users

### Development Tools
- **Git & GitHub** - Version control
- **Jira** - Agile project management
- **IntelliJ IDEA** - Primary IDE

---

## 🚀 Getting Started

### Prerequisites

Before you begin, ensure you have the following installed:

- **Java Development Kit (JDK) 17 or higher**
  - Download: [Oracle JDK](https://www.oracle.com/java/technologies/downloads/) or [OpenJDK](https://openjdk.org/)
  - Verify installation: `java -version`

- **Apache Maven 3.6+**
  - Download: [Maven](https://maven.apache.org/download.cgi)
  - Verify installation: `mvn -version`

- **Git** (for cloning the repository)
  - Download: [Git](https://git-scm.com/downloads)
  - Verify installation: `git --version`

- **Modern Web Browser**
  - Chrome, Firefox, Safari, or Edge (latest version)

---

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/recipe-manager.git
   cd recipe-manager
   ```

2. **Install dependencies**
   ```bash
   mvn clean install
   ```
   
   This command will:
   - Download all required dependencies
   - Compile the source code
   - Run tests (if any)
   - Package the application

---

### Running the Application

#### Option 1: Using Maven (Recommended)

```bash
mvn spring-boot:run
```

#### Option 2: Using the JAR file

```bash
# Build the JAR
mvn clean package

# Run the JAR
java -jar target/recipe-manager-0.0.1-SNAPSHOT.jar
```

#### Option 3: From your IDE

1. Open the project in IntelliJ IDEA or Eclipse
2. Locate the main application class: `SpringBootProjectApplication.java`
3. Right-click and select "Run"

---

### Accessing the Application

Once the application starts successfully, you'll see:
```
Started SpringBootProjectApplication in X.XXX seconds
```

Open your web browser and navigate to:
```
http://localhost:8080
```

**Default Port:** 8080  
**To change the port:** Edit `src/main/resources/application.properties` and add:
```properties
server.port=YOUR_PORT_NUMBER
```

---

## 📱 Usage Guide

### First Time Setup

1. **Register an Account**
   - Click "Login" in the navigation bar
   - Click "Sign up" link
   - Enter a username
   - Enter a password
   - Click "Create Account"

2. **Login**
   - Enter your credentials on the login page
   - You'll be redirected to the homepage with a personalized welcome

### Managing Ingredients

1. **Add an Ingredient**
   - Navigate to "Ingredients" page
   - Scroll to the "Add a New Ingredient" form
   - Enter ingredient name, quantity, and unit
   - Click "Add Ingredient"
   - The page will refresh showing your new ingredient

2. **View Your Pantry**
   - The ingredients page displays all your current ingredients in a table
   - Each row shows name, quantity, and unit

### Managing Recipes

1. **Browse Recipes**
   - Click "Recipes" in the navigation
   - View all available recipes with details

2. **Filter Recipes**
   - Use the **Cuisine Type** dropdown to filter by cuisine (Italian, Mexican, Asian, etc.)
   - Use the **Dietary Restriction** dropdown to filter by diet (Vegetarian, Vegan)
   - Both filters can be combined!

3. **Add a Recipe**
   - Scroll to the "Add a New Recipe" form
   - Enter recipe title
   - Add ingredients:
     - Click "Add Another Ingredient" to add more ingredient fields
     - Click "Remove" to delete an ingredient field
   - Enter instructions, source, time required, and cuisine type
   - Click "Save Recipe"

4. **Search Recipes**
   - Use the search bar at the top to filter recipes by name in real-time

### User Features

- **View Profile**: Your username is displayed in the navigation bar when logged in
- **Logout**: Click the "Logout" button in the navigation bar
- **Favorites**: (Feature in development)

---

## 📁 Project Structure

```
recipe-manager/
├── src/
│   ├── main/
│   │   ├── java/project/springbootproject/
│   │   │   ├── controller/              # Request handlers
│   │   │   │   ├── HomeController.java
│   │   │   │   ├── UserController.java
│   │   │   │   ├── RecipeController.java
│   │   │   │   ├── RecipeViewController.java
│   │   │   │   ├── IngredientController.java
│   │   │   │   └── IngredientViewController.java
│   │   │   ├── model/                   # Data models and business logic
│   │   │   │   ├── User.java
│   │   │   │   ├── UserStorage.java
│   │   │   │   ├── Recipe.java
│   │   │   │   ├── RecipeStorage.java
│   │   │   │   ├── Ingredient.java
│   │   │   │   └── IngredientStorage.java
│   │   │   └── SpringBootProjectApplication.java  # Main entry point
│   │   └── resources/
│   │       ├── static/                  # Static assets
│   │       │   ├── css/
│   │       │   │   └── style.css
│   │       │   └── js/
│   │       │       └── recipes.js
│   │       ├── templates/               # Thymeleaf HTML templates
│   │       │   ├── index.html
│   │       │   ├── login.html
│   │       │   ├── register.html
│   │       │   ├── ingredients.html
│   │       │   └── recipes.html
│   │       └── application.properties   # Configuration
├── users/                               # User data storage
│   └── [username]/
│       └── user.json
├── recipes.json                         # Recipe database
├── ingredients.json                     # Ingredient database
├── pom.xml                             # Maven configuration
└── README.md                           # This file
```

### Key Directories

- **`controller/`** - Handles HTTP requests and routes to appropriate services
- **`model/`** - Contains entity classes and data access logic
- **`templates/`** - HTML views rendered by Thymeleaf
- **`static/`** - CSS, JavaScript, and image files
- **`users/`** - Individual user data (created at runtime)

---

## 🔌 API Endpoints

### User Authentication

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/login` | Display login page |
| POST | `/login` | Authenticate user |
| GET | `/register` | Display registration page |
| POST | `/register` | Create new user account |
| GET | `/logout` | End user session |

### Recipes

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/recipes` | View recipes page (with optional filters) |
| GET | `/recipes?cuisine={type}` | Filter recipes by cuisine |
| GET | `/recipes?diet={type}` | Filter recipes by dietary restriction |
| GET | `/api/recipes` | Get all recipes (JSON) |
| POST | `/api/recipes` | Create new recipe (JSON) |

### Ingredients

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/ingredients` | View ingredients page |
| GET | `/api/ingredients` | Get all ingredients (JSON) |
| POST | `/api/ingredients` | Create new ingredient (JSON) |

---

## 🐛 Troubleshooting

### Common Issues and Solutions

#### 1. **Port 8080 Already in Use**

**Error:**
```
Web server failed to start. Port 8080 was already in use.
```

**Solutions:**
- **Option A:** Kill the process using port 8080
  ```bash
  # On macOS/Linux
  lsof -ti:8080 | xargs kill -9
  
  # On Windows (Command Prompt)
  netstat -ano | findstr :8080
  taskkill /PID <PID> /F
  ```

- **Option B:** Change the port in `application.properties`
  ```properties
  server.port=8081
  ```

---

#### 2. **Java Version Mismatch**

**Error:**
```
Java version: X.X.X, required: 17
```

**Solution:**
- Install JDK 17 or higher
- Update your `JAVA_HOME` environment variable
- Verify with: `java -version`

---

#### 3. **Maven Dependencies Not Downloading**

**Error:**
```
Could not resolve dependencies for project...
```

**Solutions:**
- Check your internet connection
- Clear Maven cache:
  ```bash
  mvn clean
  mvn dependency:purge-local-repository
  ```
- Try again:
  ```bash
  mvn clean install -U
  ```

---

#### 4. **Application Starts But Shows 404 Error**

**Symptoms:**
- Application starts successfully
- Browser shows "Whitelabel Error Page" or 404

**Solutions:**
- Verify you're accessing `http://localhost:8080` (not `http://localhost:8080/index`)
- Check console for errors
- Ensure templates are in `src/main/resources/templates/`
- Clear browser cache and try again

---

#### 5. **Login Doesn't Work / Session Issues**

**Symptoms:**
- Login seems successful but redirects to login again
- Username doesn't show after logging in

**Solutions:**
- Clear browser cookies and cache
- Try in an incognito/private window
- Check console logs for session-related errors
- Verify `UserController` has `HttpSession` parameter

---

#### 6. **JSON File Not Found Errors**

**Error:**
```
FileNotFoundException: recipes.json (No such file or directory)
```

**Solution:**
- The application creates these files automatically on first run
- Ensure you have write permissions in the project directory
- If files exist but are corrupt, delete them and restart the application
- Default files will be created:
  - `recipes.json` → `[]`
  - `ingredients.json` → `[]`
  - `users/` directory → created automatically

---

#### 7. **Ingredients or Recipes Not Appearing**

**Possible Causes:**
- JSON file is empty or has invalid syntax
- Data didn't save properly
- Browser cached old version

**Solutions:**
- Check JSON files are valid (use a JSON validator)
- Hard refresh browser: `Ctrl+Shift+R` (Windows) or `Cmd+Shift+R` (Mac)
- Check browser console for JavaScript errors
- Restart the application

---

#### 8. **Thymeleaf Template Errors**

**Error:**
```
Error resolving template "index"
```

**Solutions:**
- Verify file is named exactly `index.html` (case-sensitive)
- Ensure file is in `src/main/resources/templates/`
- Check controller returns correct template name (without `.html`)
- Rebuild the project: `mvn clean package`

---

#### 9. **CSS/JavaScript Not Loading**

**Symptoms:**
- Page loads but has no styling
- Forms don't submit properly

**Solutions:**
- Verify files are in `src/main/resources/static/`
- Check browser console for 404 errors
- Clear browser cache
- Verify Thymeleaf URL syntax: `th:href="@{/css/style.css}"`
- Try hard refresh: `Ctrl+F5`

---

#### 10. **Cannot Create User / Registration Fails**

**Symptoms:**
- "User already exists" error on first registration
- "Error creating user" message

**Solutions:**
- Check if `users/` directory has proper write permissions
- Verify username doesn't contain invalid characters (use only letters, numbers, underscore)
- Check console logs for specific error messages
- Try a different username
- Manually delete the `users/` directory and try again

---

### Debug Mode

To see more detailed logs, run with debug enabled:

```bash
mvn spring-boot:run -Dspring-boot.run.arguments=--logging.level.org.springframework=DEBUG
```

Or add to `application.properties`:
```properties
logging.level.org.springframework=DEBUG
logging.level.project.springbootproject=DEBUG
```

---

### Still Having Issues?

If you encounter problems not listed here:

1. **Check the console logs** - Error messages are usually descriptive
2. **Search our Issues** - Someone may have encountered the same problem
3. **Create a new Issue** - Include:
   - Operating system
   - Java version
   - Error message (full stack trace if possible)
   - Steps to reproduce

---

## 🤝 Contributing

We welcome contributions! Here's how you can help:

### Getting Started

1. **Fork the repository**
2. **Create a feature branch**
   ```bash
   git checkout -b feature/amazing-feature
   ```
3. **Make your changes**
4. **Commit with clear messages**
   ```bash
   git commit -m "Add amazing feature: description of what it does"
   ```
5. **Push to your branch**
   ```bash
   git push origin feature/amazing-feature
   ```
6. **Open a Pull Request**

### Code Standards

- Follow Java naming conventions
- Write clear, descriptive comments
- Test your changes thoroughly
- Update documentation if needed
- Keep commits atomic and focused

### Areas for Contribution

- 🐛 Bug fixes
- ✨ New features
- 📝 Documentation improvements
- 🎨 UI/UX enhancements
- ✅ Test coverage
- 🌐 Internationalization

---

## 👥 Team

This project was developed as part of CSC 548 - Software Engineering course.

| Name | GitHub |
|------|--------|
| [Catherine Larson] | [@catherinelarson-0](https://github.com/catherinelarson-0) |
| [Macy Callahan] | [@MacyCallahan](https://github.com/MacyCallahan) |
| [Mal Opocensky] | [@Malaynee](https://github.com/Malaynee) |

---

## 🙏 Acknowledgments

- **Spring Boot Team** - For the excellent framework
- **Thymeleaf** - For the powerful template engine
- **Course Instructor** - For guidance and support
- **All Contributors** - For making this project possible

---

<div align="center">

**Made with ❤️ by the Recipe Manager Team**

⭐ Star us on GitHub if you find this useful!

</div>
