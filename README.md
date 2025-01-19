# 📘 Project Setup Guide

## 🚀 Quick Start with Docker Compose

Manage services using `docker-compose` commands:

- **Start services**:
  ```bash
  docker-compose up -d
  ```
- **Stop and remove services**:
  ```bash
  docker-compose down
  ```
- **Restart services**:
  ```bash
  docker-compose restart
  ```

---

## 📤 Sending Messages to ElasticMQ with Postman

### Prerequisites
- ElasticMQ running at `http://localhost:9324`.

### Steps to Send a Message
1. **Open Postman** and create a new **POST** request.
2. **Set URL** to:
   ```
   http://localhost:9324/queue/YourQueueName
   ```
3. **Add Header**:
  - `Content-Type: application/x-www-form-urlencoded`
4. **Set Body Parameters** (as `x-www-form-urlencoded`):
  - `Action=SendMessage`
  - `MessageBody=Your Message`

5. **Click Send** to send your message.

---

## 📋 Example `curl` Command
```bash
curl -X POST \
  -H "Content-Type: application/x-www-form-urlencoded" \
  -d "Action=SendMessage&MessageBody=Hello ElasticMQ!" \
  http://localhost:9324/queue/YourQueueName
```

---

# 🧰 Skillzy Toolbox (HTML Interface)

A simple web-based tool for interacting with a local SQS-compatible queue.

### How to Use:
1. Open the **HTML file** in a browser.(http://localhost:8080/queue_sender.html)
2. Enter **Queue Name** (default: `participantQueue`).
3. Enter **Message Body** (e.g., `{"id":123,"text":"Hello"}`).
4. Select **Message Type** (`DELETE`, `UPDATE`, `CREATE`).
5. Click **Send Message** or **Purge Queue**.

---

## ✅ Requirements
- **SQS-compatible server** at `http://localhost:9324` (e.g., **ElasticMQ**).
- A modern **web browser**.

---

## 📝 Example JSON Message
```json
{
  "id": 123,
  "text": "Hello World!"
}
```

