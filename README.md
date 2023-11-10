# QuizApp-Microservices

This Spring Boot Microservices project allows users to create quizzes, retrieve quizzes, and submit quizzes. It is composed of several microservices including Quiz-Service, Question-Service, Eureka Server, API Gateway, and Config Server.

## Microservices

### 1. Quiz-Service
   - Creating quizzes, retrieving quizzes by ID, and submitting quizzes.
   - **Endpoints:**
     - `POST /quiz/create` - Create a new quiz by specifying the category and number of questions.
     - `GET /quiz/{quizId}` - Retrieve a quiz by its ID.
     - `POST /quiz/submit/{quizId}` - Submit a quiz.
  
### 2. Question-Service
   - Responsible for Managing the creation, retrieval, updating, and deletion of questions.
   - Offers endpoints to get questions based on category and number.
   - **Endpoints:**
     - `POST /question` - Create a new question.
     - `GET /question/{questionId}` - Retrieve questions based on the specified questionId.
     - `GET /question/all` - Retrieve all the questions.
     - `GET /question/category/{category}` - Retrieve questions based on the specified category.
     - `GET /question/generateQuiz/{category}/{numOfQuestions}` - Retrieve question Id's based on the specified category and number.
     - `POST /question/getQuestions` - Retrieve question wrappers based on the specified question Id's.
     - `POST /question/scores` - Retrieve the count of correct questions based on question response.
     - `PUT /question/{questionId}` - Update a question.
     - `DELETE /question/{questionId}` - Delete a question.
    
### 3. Eureka Server
   - Functions as a service registry to enable service discovery among microservices.
   - **Endpoint:**
     -`http://localhost:8761`

### 4. API Gateway
   - Serves as an entry point for external clients to interact with the microservices.
   - Routes requests to the appropriate microservice based on the request path.
   - **Endpoint:**
     - `http://localhost:8095`

### 5. Config Server
   - Centralized configuration management to handle configurations for various microservices.
   - **Endpoint:**
     - `http://localhost:8765`

## Getting Started

1. **Clone the repository**
   ```shell
   git clone https://github.com/Pravesh-M/QuizApp-Microservices.git
   ```
   Alternatively, you can download the project as a ZIP file and extract it.
   
2. **Import Project**
   
   Open your favorite IDE (e.g Intellij IDEA, STS, VSCode) and import each project as a Maven project into your IDE.

3. **Run the Applications**
   - Start the Eureka Server.
   - Launch the Config Server.
   - Start the API Gateway.
   - Run the Quiz-Service and Question-Service microservices.
     
   Make requests to the API Gateway for quiz and question operations.


Feel free to contribute or report issues. Happy quizzing!
