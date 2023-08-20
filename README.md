# Reimbursement Application

This guide provides step-by-step instructions for setting up the Reimbursement Application on a Windows system.

## Prerequisites

- Java JDK (at least version 8)
- Apache Maven
- Git

## Setup Environment

1. **Install Java JDK:**
   - Download and install the latest Java JDK from the official website: [Oracle JDK Downloads](https://www.oracle.com/java/technologies/javase-downloads.html)

2. **Install Apache Maven:**
   - Download and install Apache Maven from: [Apache Maven Downloads](https://maven.apache.org/download.cgi)
   - Set up the `MAVEN_HOME` and `PATH` environment variables. Make sure to add Maven's `bin` directory to your `PATH`.

3. **Install Git:**
   - Download and install Git from: [Git Downloads](https://git-scm.com/downloads)

4. **Clone the Repository:**
   - Open the Command Prompt or Git Bash.
   - Navigate to the directory where you want to clone the repository.
   - Run the following command:
     ```
     git clone <repository-url>
     ```

## Build the Application

1. **Navigate to the project root directory:**
cd ReimbursementApplication

2. **Build the project using Maven:**
mvn clean package

## Run Tests

1. **Navigate to the project root directory:**
cd ReimbursementApplication

2. **Run the tests using Maven:**
mvn test

## Run the Application

1. **Navigate to the project root directory:**
cd ReimbursementApplication

2. **Run the application using Maven:**
mvn tomcat7:run

3. **Access the application in your web browser:**
Open your web browser and navigate to [http://localhost:8080/ReimbursementApplication](http://localhost:8080/ReimbursementApplication)

