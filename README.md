# Major Project

Welcome to the Major Project repository! This project is an AI-based interactive chatbot and virtual assistant designed for the Department of Justice's website. It leverages modern web and mobile technologies to provide accurate information and facilitate user interactions with the DoJ's services.

![Ongoing Project](https://img.shields.io/badge/Status-Ongoing-blue?style=flat-square)

## Table of Contents

[![Technologies Used](https://img.shields.io/badge/Technologies%20Used-4CAF50?style=flat-square)](#technologies-used)  
[![Project Overview](https://img.shields.io/badge/Project%20Overview-2196F3?style=flat-square)](#project-overview)  
[![Features](https://img.shields.io/badge/Features-9C27B0?style=flat-square)](#features)  
[![Architecture](https://img.shields.io/badge/Architecture-FF9800?style=flat-square)](#architecture)  
[![Installation & Usage](https://img.shields.io/badge/Installation-673AB7?style=flat-square)](#installation)   
[![Contributing](https://img.shields.io/badge/Contributing-FFC107?style=flat-square)](#contributing)  
[![License](https://img.shields.io/badge/License-795548?style=flat-square)](#license)  
[![Contact](https://img.shields.io/badge/Contact-8BC34A?style=flat-square)](#contact)

## Technologies Used

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=flat-square&logo=spring-boot&logoColor=white)](https://spring.io/projects/spring-boot)  
[![React](https://img.shields.io/badge/React-61DAFB?style=flat-square&logo=react&logoColor=black)](https://reactjs.org/)  
[![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-4285F4?style=flat-square&logo=jetpack-compose&logoColor=white)](https://developer.android.com/jetpack/compose)  
[![Flutter](https://img.shields.io/badge/Flutter-02569B?style=flat-square&logo=flutter&logoColor=white)](https://flutter.dev/)  
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-5586A4?style=flat-square&logo=postgresql&logoColor=white)](https://www.postgresql.org/)  
[![Docker](https://img.shields.io/badge/Docker-3C3C3D?style=flat-square&logo=docker&logoColor=white)](https://www.docker.com/)  
[![Kubernetes](https://img.shields.io/badge/Kubernetes-326CE5?style=flat-square&logo=kubernetes&logoColor=white)](https://kubernetes.io/)  
[![Nginx](https://img.shields.io/badge/Nginx-009639?style=flat-square&logo=nginx&logoColor=white)](https://www.nginx.com/)  

### AI and Machine Learning Stack
[![Python](https://img.shields.io/badge/Python-3776AB?style=flat-square&logo=python&logoColor=white)](https://www.python.org/)  
[![TensorFlow](https://img.shields.io/badge/TensorFlow-FF6F00?style=flat-square&logo=tensorflow&logoColor=white)](https://www.tensorflow.org/)  
[![PyTorch](https://img.shields.io/badge/PyTorch-EE4C2C?style=flat-square&logo=pytorch&logoColor=white)](https://pytorch.org/)  
[![Scikit-Learn](https://img.shields.io/badge/Scikit--Learn-F7931E?style=flat-square&logo=scikit-learn&logoColor=white)](https://scikit-learn.org/)  
[![NLTK](https://img.shields.io/badge/NLTK-4B8BBE?style=flat-square&logo=python&logoColor=white)](https://www.nltk.org/)  

## Project Overview

The Major Project is an AI-based chatbot and virtual assistant intended to improve access to legal information for users of the Department of Justice website. The chatbot can provide insights on various judiciary topics, including case status, judicial appointments, legal procedures, and much more. The goal is to enhance the user experience and make legal information more accessible to the public.

The project was started in October 2024 and is currently ongoing. We are focusing on providing a scalable and intelligent solution for legal assistance.

## Features

- **Interactive Chatbot:** The AI-powered chatbot can understand user queries and respond with relevant information regarding judiciary services.
- **Judicial Data Access:** Allows users to access information about the judiciary, such as judicial appointments, case status, and court procedures.
- **Authentication:** Secure user authentication to personalize user interactions and allow access to private case information.
- **Multiplatform Support:** The solution supports web, Android, iOS, and desktop applications using React, Jetpack Compose, Flutter, and KMM.
- **Real-time Case Tracking:** Enables users to track the status of their legal cases.
- **Learning Capabilities:** The chatbot learns over time to improve its responses and deliver better information.

## Architecture

The Major Project follows a modular architecture to ensure scalability and maintainability. The system's main components include:

- **Backend Services:** Developed using Spring Boot to handle RESTful API requests and manage data persistence.
- **Frontend:** Built with React for web applications, Jetpack Compose for native Android, and Flutter for iOS and desktop.
- **AI & ML Integration:** Utilizes Python-based frameworks such as TensorFlow, PyTorch, and NLTK for natural language processing and model training.
- **Data Storage:** PostgreSQL is used for structured data storage, while other NoSQL solutions may be added for specific use cases.
- **Containerization and Orchestration:** Docker and Kubernetes are used for deployment and orchestration across environments.
- **Reverse Proxy and Load Balancer:** Nginx is used for traffic management and load balancing.

## Installation & Usage

To set up this project locally, follow these steps:

1. **Clone the repository:**
   ```bash
   git clone https://github.com/singhtwenty2/Major-Project.git
   cd Major-Project

2. **Backend Setup:**
   Make sure you have Java 17+ and Maven installed.
   Navigate to the backend directory
     ```bash
     cd backend
     mvn clean install
     mvn spring-boot:run
  Configure the database by setting up application.properties or application.yml in the backend project with the appropriate PostgreSQL configurations (e.g., database URL, username, 
  password).

3. **Frontend Setup:**
   ```bash
   cd ../frontend/react
   npm install
   npm run dev

4. **Mobile Setup:**

   For Android (Jetpack Compose):
   Open the Android project in Android Studio, build, and run it on an emulator or device.

   For iOS (Flutter):
   Ensure you have Xcode installed, navigate to the Flutter project, and run:
   ```bash
   flutter run

6. **Docker Setup (Optional):**
   If using Docker, make sure Docker is installed.
   Build and run the Docker containers:
   ```bash
   docker-compose up --build


## Contributing

We welcome contributions! Please read our [Contributing Guidelines](CONTRIBUTING.md) for information on how to contribute.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Contact

For any inquiries or support, please reach out to us at trm97713@gmail.com
