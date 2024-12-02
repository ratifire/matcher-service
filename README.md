# Getting Started

### Reference Documentation

For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/3.3.5/gradle-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/3.3.5/gradle-plugin/packaging-oci-image.html)
* [Spring Web](https://docs.spring.io/spring-boot/3.3.5/reference/web/servlet.html)

### Guides

The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)

### Additional Links

These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)

# How to Use Docker Compose

To manage Docker Compose services, use the following commands in the directory containing the `docker-compose.yml` file:

- To **start the services**, run:
  ```bash
  docker-compose up -d
  ```
  This starts all services in detached mode.

- To **stop and remove the services**, run:
  ```bash
  docker-compose down
  ```
  This stops the running containers and removes them.

- To **restart the services**, run:
  ```bash
  docker-compose restart
  ```
  This stops and immediately restarts the services without removing the containers.
# How to Send a Message to ElasticMQ using Postman

ElasticMQ is a lightweight message queue system that supports the Amazon SQS API. Here's how you can send a message to an ElasticMQ queue using Postman:

---

## Prerequisites
1. ElasticMQ must be running locally or on a specified endpoint.

---

## Steps to Send a Message

### 1. Open Postman
Ensure Postman is installed on your machine. If not, download it from [Postman](https://www.postman.com/).

### 2. Create a New Request
1. Click on **New** > **Request**.
2. Choose **POST** as the HTTP method.

### 3. Set the Request URL
Enter the following URL:
```
http://localhost:9324/queue/YourQueueName
```
Replace `YourQueueName` with the name of your queue.

### 4. Configure Headers
Add the following header to your request:
- **Key**: `Content-Type`
- **Value**: `application/x-www-form-urlencoded`

### 5. Add Body Parameters
In the **Body** tab, select **x-www-form-urlencoded** and add the following parameters:
- **Action**: `SendMessage`
- **MessageBody**: The content of your message (e.g., `Hello ElasticMQ!`).

Example parameters:
```
Action=SendMessage
MessageBody=Hello ElasticMQ!
```

### 6. Send the Request
Click **Send** to send the message to ElasticMQ.

---

## Verifying the Message
1. Open the ElasticMQ UI (if available) or connect via a CLI tool to view the messages in the queue.
2. Confirm that your message (`Hello ElasticMQ!`) has been successfully sent.

---

## Example Curl Command (Optional)
If you prefer, you can also send a message via `curl`:
```bash
curl -X POST   -H "Content-Type: application/x-www-form-urlencoded"   -d "Action=SendMessage&MessageBody=Hello ElasticMQ!"   http://localhost:9324/queue/YourQueueName
```
Replace `YourQueueName` with the name of your queue.
