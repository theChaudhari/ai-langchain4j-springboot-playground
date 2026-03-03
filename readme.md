# 🤖 ai-langchain4j-springboot-playground

A collection of AI-powered Spring Boot projects built with **LangChain4j** and **Google Gemini**.
Each lab lives in its own branch and demonstrates a different real-world AI use case.

---

## 👨‍💻 Author

**Abhishek Chaudhari**  
[github.com/theChaudhari](https://github.com/theChaudhari)

---

## 🧰 Common Tech Stack

| Technology | Version | Purpose |
|---|---|---|
| Java | 17 | Language |
| Spring Boot | 3.3.5 | Backend Framework |
| LangChain4j | 0.36.2 | AI Integration Layer |
| Google Gemini | gemini-1.5-flash | Language Model |
| Lombok | Latest | Boilerplate Reduction |
| Maven | Latest | Build Tool |

---

## 🧪 Labs

| # | Branch | Description | Key Concepts |
|---|---|---|---|
| 01 | [labs/01-ai-request-validator](../../tree/labs/01-ai-request-validator) | AI-powered REST API request validation using Gemini | `@AiService`, Prompt Engineering |
| 02 | [labs/02-ai-task-agent](../../tree/labs/02-ai-task-agent) | Natural language task manager with AI tool invocation | `@AiService`, `@Tool`, H2, JPA |

> More labs coming soon 🚀

---

## 🚀 Getting Started

### Prerequisites
- Java 17+
- Maven 3.8+
- Google Gemini API Key → [aistudio.google.com](https://aistudio.google.com)

### Clone & Switch to a Lab

```bash
# Clone the repo
git clone https://github.com/theChaudhari/ai-langchain4j-springboot-playground.git

# Switch to a lab branch
git checkout labs/02-ai-task-agent

# Run
mvn spring-boot:run
```

### Set Your API Key in IntelliJ

1. Run Configuration → **Edit Configurations**
2. **Environment Variables** → Add:
```
GEMINI_API_KEY = your_actual_key_here
```

---

## 🗺️ Roadmap

Upcoming labs planned:

- [ ] `labs/03-ai-chat-memory` — Conversation memory with session management
- [ ] `labs/04-rag-document-search` — RAG with PDF document ingestion
- [ ] `labs/05-ai-image-analyzer` — Image analysis using Gemini Vision
- [ ] `labs/06-ai-code-reviewer` — AI powered code review agent

---

## 📈 Gemini Free Tier

| Model | Requests/Day | Tokens/Min |
|---|---|---|
| gemini-1.5-flash | 1,500 | 1,000,000 |

---

## 📄 License

This project is open source and available under the [MIT License](LICENSE).