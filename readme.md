# 🤖 AI Agent Task Assistance — Spring Boot + LangChain4j + Gemini

A Spring Boot POC that uses **Google Gemini AI** via **LangChain4j** to manage tasks through natural language. Just tell the AI what you want in plain English — it figures out which tool to call.

---

## 🧰 Tech Stack

| Technology | Version | Purpose |
|---|---|---|
| Spring Boot | 3.3.5 | Backend Framework |
| LangChain4j | 0.36.2 | AI Integration |
| Google Gemini | gemini-1.5-flash | Language Model |
| H2 Database | Runtime | In-Memory Database |
| Spring Data JPA | — | ORM |
| Lombok | — | Boilerplate Reduction |

---

## 📁 Project Structure

```
src/main/java/com/langchain4j_poc/
├── config/
│   └── LangChain4jConfig.java        # Gemini ChatModel bean
├── controller/
│   └── AgentController.java          # POST /agent/ask
├── entity/
│   └── Task.java                     # Task JPA entity
├── repository/
│   └── TaskRepository.java           # JPA Repository
├── service/
│   └── TaskAgent.java                # @AiService interface
└── tool/
    └── TaskTools.java                # @Tool methods (AI callable)
```

---

## ⚙️ Setup & Configuration

### 1. Clone the Repository

```bash
git clone https://github.com/theChaudhari/langchain4j-springBoot-poc.git
cd langchain4j-springBoot-poc
git checkout project/ai-agent-task-assistance-gemini
```

### 2. Get Your Gemini API Key

1. Go to [aistudio.google.com](https://aistudio.google.com)
2. Sign in with your Google Account
3. Click **Get API Key** → **Create API Key**
4. Copy your key

### 3. Set API Key in IntelliJ

1. Click run configuration dropdown → **Edit Configurations**
2. Find **Environment Variables** → Click the icon
3. Add: `GEMINI_API_KEY` = `your_actual_key_here`
4. Click **OK → Apply → OK**

### 4. application.properties

```properties
# Gemini AI
gemini.api.key=${GEMINI_API_KEY}
gemini.model=gemini-1.5-flash

# Server
server.port=8080
server.error.include-message=always

# H2 Console
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

# DataSource
spring.datasource.url=jdbc:h2:mem:testdb;DB_CLOSE_ON_EXIT=FALSE;DB_CLOSE_DELAY=-1
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

# JPA
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true
spring.jpa.open-in-view=false

# Logging
spring.output.ansi.enabled=always
```

### 5. Run the Application

```bash
mvn spring-boot:run
```

---

## 🗄️ H2 Database Console

Access the in-memory database at:

```
http://localhost:8080/h2-console
```

| Field | Value |
|---|---|
| JDBC URL | `jdbc:h2:mem:testdb` |
| Username | `sa` |
| Password | *(leave blank)* |

> Data resets on every app restart since it's in-memory.

---

## 📡 API Endpoint

### `POST /agent/ask`

Send a plain English message. The AI decides which tool to invoke.

**Headers:**
```
Content-Type: text/plain
```

**Body:** Plain text (no JSON)

---

## 🧪 Sample Requests

### ✅ Create a Task
```
Create a task titled "Fix login bug"
```

### ✅ Create Multiple Tasks
```
Create three tasks: "Design UI", "Write unit tests", "Deploy to staging"
```

### ✅ Get All Tasks
```
Show me all tasks
```

### ✅ Filter Tasks by Status
```
Show me all CREATED tasks
```

### ✅ Get Tasks NOT Completed
```
Show me tasks that are not completed
```

### ✅ Update Task Status
```
Update task with id 1 to status COMPLETED
```

### ✅ Combined Operation
```
Create a task called "Write documentation" and mark it as IN_PROGRESS
```

---

## 🛠️ Available AI Tools

| Tool | Description |
|---|---|
| `createTask(title)` | Creates a new task with status CREATED |
| `getAllTasks()` | Returns all tasks |
| `getFilteredTasks(status)` | Filters tasks by status |
| `getTasksExcludingStatus(status)` | Returns tasks excluding a given status |
| `updateTask(id, status)` | Updates task status by ID |

**Valid Status Values:** `CREATED`, `IN_PROGRESS`, `COMPLETED`, `CANCELLED`

---

## 📮 Postman Collection

A ready-to-use Postman collection is included in the project:

```
src/main/resources/TaskAgent.postman_collection.json
```

### How to Import
1. Open **Postman**
2. Click **Import** (top left)
3. Select `TaskAgent.postman_collection.json`
4. Click **Import**

> The `{{baseUrl}}` variable is pre-set to `http://localhost:8080`

---

## 📈 Gemini Free Tier Limits

| Model | Requests/Day | Requests/Min |
|---|---|---|
| gemini-1.5-flash | 1,500 | 15 |
| gemini-2.0-flash | 1,500 | 15 |

Sufficient for development and POC usage.

---

## 👤 Author

**Abhishek Chaudhari**
[github.com/theChaudhari](https://github.com/theChaudhari)