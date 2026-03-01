# 🤖 AI-Powered Request Validator — Spring Boot + LangChain4j + Gemini

A production-ready REST API that uses **Google Gemini AI** via **LangChain4j** to intelligently validate incoming requests beyond traditional regex or annotation-based validation.

---

## 🚀 Tech Stack

| Technology | Purpose |
|-----------|---------|
| Spring Boot | Backend Framework |
| LangChain4j | AI Integration |
| Google Gemini 2.0 Flash | AI Validation Model |
| Lombok | Boilerplate Reduction |
| Jackson | JSON Serialization |

---

## ⚙️ Setup & Configuration

### 1. Clone the Repository
```bash
git clone https://github.com/your-username/ai-validator.git
cd ai-validator
```

### 2. Get Your Gemini API Key
1. Go to 👉 [aistudio.google.com](https://aistudio.google.com)
2. Sign in with your Google Account
3. Click **"Get API Key"** → **"Create API Key"**
4. Copy your API key

### 3. Configure application.properties

> ⚠️ **Never hardcode your API key!** Use environment variables instead.

```properties
# ✅ Safe to commit - no actual key here
gemini.api.key=${GEMINI_API_KEY}
gemini.model=gemini-2.0-flash

server.error.include-message=always
server.error.include-binding-errors=always
spring.mvc.problemdetails.enabled=false
```

### 4. Set Your API Key (IntelliJ)

**Step 1:** Click the run configuration dropdown (top right) → **Edit Configurations**

**Step 2:** Find the **Environment Variables** field → Click the 📋 icon

**Step 3:** Click ➕ and add:
```
Name  : GEMINI_API_KEY
Value : your_actual_api_key_here
```

**Step 4:** Click **OK** → **Apply** → **OK**

> ✅ Your key stays only inside IntelliJ — never touches any file!

### 5. Add Dependencies (pom.xml)
```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-validation</artifactId>
    </dependency>
    <dependency>
        <groupId>dev.langchain4j</groupId>
        <artifactId>langchain4j</artifactId>
        <version>0.36.2</version>
    </dependency>
    <dependency>
        <groupId>dev.langchain4j</groupId>
        <artifactId>langchain4j-google-ai-gemini</artifactId>
        <version>0.36.2</version>
    </dependency>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>
</dependencies>
```

### 6. Run the Application
```bash
mvn spring-boot:run
```

---

## 📁 Project Structure

```
src/
├── main/java/com/example/
│   ├── config/
│   │   └── GeminiConfig.java
│   ├── dto/
│   │   ├── UserRegistrationRequest.java
│   │   ├── ProductRequest.java
│   │   └── ValidationResult.java
│   ├── service/
│   │   ├── AiValidatorAssistant.java
│   │   └── AiValidationService.java
│   ├── validator/
│   │   └── AiValidator.java
│   ├── controller/
│   │   └── ValidationController.java
│   └── exception/
│       └── GlobalExceptionHandler.java
└── resources/
    └── application.properties
```

---

## 📡 API Endpoints & Sample Requests

### Base URL
```
http://localhost:8080
```

---

### 🔵 1. Register User

**Endpoint**
```
POST /api/register
```

**Headers**
```
Content-Type: application/json
```

**✅ Valid Request**
```json
{
  "firstName": "John",
  "lastName": "Smith",
  "email": "john.smith@gmail.com",
  "password": "SecurePass@123",
  "phone": "+1-555-234-5678",
  "address": "123 Main St, New York, NY",
  "age": 28,
  "bio": "Software developer with 5 years of experience"
}
```

**✅ Valid Response `200 OK`**
```json
{
  "message": "User registered successfully!",
  "confidenceScore": 95,
  "warnings": []
}
```

**❌ Invalid Request**
```json
{
  "firstName": "asdfjkl",
  "lastName": "qwerty",
  "email": "fake@@notreal..com",
  "password": "123",
  "phone": "000-000-0000",
  "address": "asdfgh",
  "age": 999,
  "bio": "Buy cheap meds at www.spam.com!!!"
}
```

**❌ Invalid Response `400 BAD REQUEST`**
```json
{
  "status": 400,
  "error": "Validation Failed",
  "message": "Validation Failed: Name appears to be random characters, Invalid email format, Password too weak, Age is unrealistic, Bio contains spam content"
}
```

**⚠️ Suspicious Request**
```json
{
  "firstName": "Test",
  "lastName": "User",
  "email": "test@test.com",
  "password": "Test@1234",
  "phone": "+1-555-000-0000",
  "address": "Test Address",
  "age": 25,
  "bio": "test bio"
}
```

**⚠️ Suspicious Response `400 BAD REQUEST`**
```json
{
  "status": 400,
  "error": "Validation Failed",
  "message": "Suspicious data detected. Confidence score too low: 35%"
}
```

---

### 🔵 2. Create Product

**Endpoint**
```
POST /api/product
```

**Headers**
```
Content-Type: application/json
```

**✅ Valid Request**
```json
{
  "name": "Apple iPhone 15 Pro",
  "description": "Latest Apple smartphone with A17 Pro chip and titanium design",
  "price": 999.99,
  "quantity": 50,
  "category": "Electronics"
}
```

**✅ Valid Response `200 OK`**
```json
{
  "message": "Product created successfully!",
  "confidenceScore": 97,
  "warnings": []
}
```

**❌ Invalid Request**
```json
{
  "name": "asdfghjkl",
  "description": "buy now click here www.spam.com",
  "price": -500,
  "quantity": -10,
  "category": "xyz123"
}
```

**❌ Invalid Response `400 BAD REQUEST`**
```json
{
  "status": 400,
  "error": "Validation Failed",
  "message": "Validation Failed: Product name looks invalid, Description contains spam, Price cannot be negative, Quantity cannot be negative"
}
```

---

### 🔵 3. Validate User Only (No Saving)

**Endpoint**
```
POST /api/validate/user
```

**Headers**
```
Content-Type: application/json
```

**✅ Valid Request**
```json
{
  "firstName": "Sarah",
  "lastName": "Johnson",
  "email": "sarah.johnson@outlook.com",
  "password": "MyPass@456",
  "phone": "+44-7911-123456",
  "address": "10 Downing St, London, UK",
  "age": 32,
  "bio": "Marketing manager with passion for travel"
}
```

**✅ Valid Response `200 OK`**
```json
{
  "valid": true,
  "errors": [],
  "warnings": [],
  "suggestion": "All data looks legitimate and properly formatted",
  "confidenceScore": 96
}
```

**❌ Invalid Request**
```json
{
  "firstName": "xyz",
  "lastName": "abc",
  "email": "notanemail",
  "password": "weak",
  "phone": "12345",
  "address": "",
  "age": 0,
  "bio": ""
}
```

**❌ Invalid Response `200 OK`**
```json
{
  "valid": false,
  "errors": [
    "First name appears invalid",
    "Last name appears invalid",
    "Email format is incorrect",
    "Password is too weak",
    "Phone number format is invalid",
    "Address cannot be empty",
    "Age must be greater than 0"
  ],
  "warnings": [
    "Bio is empty, consider adding one"
  ],
  "suggestion": "Please provide valid and realistic information",
  "confidenceScore": 10
}
```

---

## 📮 Postman Collection

A ready-to-use Postman Collection is included in the project under:

```
src/main/resources/AI-Validator.postman_collection.json
```

### How to Import

1. Open **Postman**
2. Click **Import** (top left)
3. Navigate to `src/main/resources/`
4. Select `AI-Validator.postman_collection.json`
5. Click **Import**

### What's Included

| Folder | Requests |
|--------|----------|
| 👤 User Registration | Valid User, Fake User, Suspicious User, International User, Weak Password, Invalid Age |
| 📦 Product Management | Valid Product, Spam Product, Clothing Product, Negative Price, Food Product |
| 🔍 Validate Only | Valid User, Invalid User, Borderline User |

> 💡 The `{{baseUrl}}` variable is pre-set to `http://localhost:8080` — change it in **Postman Environments** if needed.

---

## 🧪 Test With curl

**Register User**
```bash
curl -X POST http://localhost:8080/api/register \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "John",
    "lastName": "Smith",
    "email": "john.smith@gmail.com",
    "password": "SecurePass@123",
    "phone": "+1-555-234-5678",
    "address": "123 Main St, New York, NY",
    "age": 28,
    "bio": "Software developer with 5 years of experience"
  }'
```

**Create Product**
```bash
curl -X POST http://localhost:8080/api/product \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Apple iPhone 15 Pro",
    "description": "Latest Apple smartphone with A17 Pro chip",
    "price": 999.99,
    "quantity": 50,
    "category": "Electronics"
  }'
```

**Validate User Only**
```bash
curl -X POST http://localhost:8080/api/validate/user \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "Sarah",
    "lastName": "Johnson",
    "email": "sarah.johnson@outlook.com",
    "password": "MyPass@456",
    "phone": "+44-7911-123456",
    "address": "10 Downing St, London, UK",
    "age": 32,
    "bio": "Marketing manager with passion for travel"
  }'
```

---

## 🧪 Test With VS Code REST Client

> Create a file named `api-test.http` in your project root

```http
### ✅ Register Valid User
POST http://localhost:8080/api/register
Content-Type: application/json

{
  "firstName": "John",
  "lastName": "Smith",
  "email": "john.smith@gmail.com",
  "password": "SecurePass@123",
  "phone": "+1-555-234-5678",
  "address": "123 Main St, New York, NY",
  "age": 28,
  "bio": "Software developer with 5 years of experience"
}

### ❌ Register Fake User
POST http://localhost:8080/api/register
Content-Type: application/json

{
  "firstName": "asdfjkl",
  "lastName": "qwerty",
  "email": "fake@@notreal..com",
  "password": "123",
  "phone": "000-000-0000",
  "address": "asdfgh",
  "age": 999,
  "bio": "Buy cheap meds at www.spam.com!!!"
}

### ✅ Create Valid Product
POST http://localhost:8080/api/product
Content-Type: application/json

{
  "name": "Apple iPhone 15 Pro",
  "description": "Latest Apple smartphone with A17 Pro chip",
  "price": 999.99,
  "quantity": 50,
  "category": "Electronics"
}

### ❌ Create Invalid Product
POST http://localhost:8080/api/product
Content-Type: application/json

{
  "name": "asdfghjkl",
  "description": "buy now click here www.spam.com",
  "price": -500,
  "quantity": -10,
  "category": "xyz123"
}

### ✅ Validate User Only
POST http://localhost:8080/api/validate/user
Content-Type: application/json

{
  "firstName": "Sarah",
  "lastName": "Johnson",
  "email": "sarah.johnson@outlook.com",
  "password": "MyPass@456",
  "phone": "+44-7911-123456",
  "address": "10 Downing St, London, UK",
  "age": 32,
  "bio": "Marketing manager with passion for travel"
}
```

---

## 📊 Endpoints Summary

| Method | Endpoint | Description | Auth |
|--------|----------|-------------|------|
| `POST` | `/api/register` | Register user with AI validation | None |
| `POST` | `/api/product` | Create product with AI validation | None |
| `POST` | `/api/validate/user` | Validate user data only (no saving) | None |

---

## 📌 Response Codes

| Code | Meaning |
|------|---------|
| `200 OK` | Request is valid and processed successfully |
| `400 BAD REQUEST` | Validation failed or suspicious data detected |
| `500 INTERNAL SERVER ERROR` | Server or AI service error |

---

## 🎯 What AI Validates

| Field | AI Checks |
|-------|-----------|
| Name | Real human name vs random characters |
| Email | Format and legitimacy |
| Password | Strength and complexity |
| Phone | Valid format and real number pattern |
| Age | Realistic range (1–120) |
| Bio | Spam, offensive, or inappropriate content |
| Address | Looks like a real address |
| Price | Realistic product pricing |
| Description | Spam or misleading content |

---

## 🔒 Hiding Your API Key

### ✅ Option 1: IntelliJ Run Configuration (Recommended for POC)

No files needed — your key stays only inside IntelliJ:

1. Click dropdown (top right) → **Edit Configurations**
2. Find **Environment Variables** field → Click 📋 icon
3. Click ➕ → Add `GEMINI_API_KEY` = `your_actual_api_key_here`
4. Click **OK** → **Apply** → **OK**

> ⚠️ Make sure you put it in **Environment Variables** field — NOT in VM Options or Program Arguments!

```
❌ VM Options        → -DGEMINI_API_KEY=xxx   (Wrong!)
❌ Program Arguments → GEMINI_API_KEY=xxx      (Wrong!)
✅ Environment Variables → GEMINI_API_KEY=xxx  (Correct!)
```

---

### ✅ Option 2: VM Options (Alternative)

If you can't find the Environment Variables field, use VM Options with `-D`:

In **VM Options** field add:
```
-DGEMINI_API_KEY=your_actual_api_key_here
```

---

### ✅ Option 3: application-local.properties (Spring Native)

**Step 1:** Create `application-local.properties` in `src/main/resources/`
```properties
gemini.api.key=your_actual_api_key_here
```

**Step 2:** Add to `.gitignore`
```
application-local.properties
```

**Step 3:** Run with local profile
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=local
```

---

### 📊 Which Option to Use?

| Option | Best For | Commits Safe |
|--------|----------|-------------|
| IntelliJ Run Config | Local POC / Development | ✅ Yes |
| VM Options | Quick alternative in IntelliJ | ✅ Yes |
| application-local.properties | Spring-native approach | ✅ Yes (gitignored) |
| Environment Variables (OS) | Production / CI/CD | ✅ Yes |

---

### 🛡️ Always Add to .gitignore

```gitignore
# Secret files - never commit these
application-local.properties
.env
*.key
```

---

## 📈 Gemini Free Tier Limits

| Model | Requests/Day | Requests/Min |
|-------|-------------|-------------|
| gemini-2.0-flash | 1,500 | 15 |
| gemini-2.0-flash-lite | 1,500 | 30 |

---
