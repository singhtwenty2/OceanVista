# OceanVista

Welcome to the OceanVista repository! This project is a real-time **Beach Suitability and Safety App** designed for tourists and beachgoers in India. Using advanced data processing and AI-powered analytics, OceanVista delivers up-to-date beach safety bulletins, integrates with nearby services, and ensures a safe and convenient beach experience.

![Ongoing Project](https://img.shields.io/badge/Status-Ongoing-blue?style=flat-square)
[![Beta Release](https://img.shields.io/badge/Beta-Live-green?style=flat-square)](https://app-oceanvista.pages.dev/)

---

## Table of Contents

[![Technologies Used](https://img.shields.io/badge/Technologies%20Used-4CAF50?style=flat-square)](#technologies-used)  
[![Project Overview](https://img.shields.io/badge/Project%20Overview-2196F3?style=flat-square)](#project-overview)  
[![Features](https://img.shields.io/badge/Features-9C27B0?style=flat-square)](#features)  
[![Architecture](https://img.shields.io/badge/Architecture-FF9800?style=flat-square)](#architecture)  
[![Installation & Usage](https://img.shields.io/badge/Installation-673AB7?style=flat-square)](#installation--usage)  
[![Contributing](https://img.shields.io/badge/Contributing-FFC107?style=flat-square)](#contributing)  
[![License](https://img.shields.io/badge/License-795548?style=flat-square)](#license)  
[![Contact](https://img.shields.io/badge/Contact-8BC34A?style=flat-square)](#contact)  

---

## Technologies Used

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=flat-square&logo=spring-boot&logoColor=white)](https://spring.io/projects/spring-boot)  
[![React](https://img.shields.io/badge/React-61DAFB?style=flat-square&logo=react&logoColor=black)](https://reactjs.org/)  
[![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-4285F4?style=flat-square&logo=jetpack-compose&logoColor=white)](https://developer.android.com/jetpack/compose)  
[![Redis](https://img.shields.io/badge/Redis-DC382D?style=flat-square&logo=redis&logoColor=white)](https://redis.io/)  
[![Kafka](https://img.shields.io/badge/Kafka-231F20?style=flat-square&logo=apache-kafka&logoColor=white)](https://kafka.apache.org/)  
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-5586A4?style=flat-square&logo=postgresql&logoColor=white)](https://www.postgresql.org/)  
[![Docker](https://img.shields.io/badge/Docker-3C3C3D?style=flat-square&logo=docker&logoColor=white)](https://www.docker.com/)  
[![Kubernetes](https://img.shields.io/badge/Kubernetes-326CE5?style=flat-square&logo=kubernetes&logoColor=white)](https://kubernetes.io/)  
[![Nginx](https://img.shields.io/badge/Nginx-009639?style=flat-square&logo=nginx&logoColor=white)](https://www.nginx.com/) 
[![Python](https://img.shields.io/badge/Python-3776AB?style=flat-square&logo=python&logoColor=white)](https://www.python.org/)  
[![TensorFlow](https://img.shields.io/badge/TensorFlow-FF6F00?style=flat-square&logo=tensorflow&logoColor=white)](https://www.tensorflow.org/)  
[![PyTorch](https://img.shields.io/badge/PyTorch-EE4C2C?style=flat-square&logo=pytorch&logoColor=white)](https://pytorch.org/)  
[![Scikit-Learn](https://img.shields.io/badge/Scikit--Learn-F7931E?style=flat-square&logo=scikit-learn&logoColor=white)](https://scikit-learn.org/)  
[![NLTK](https://img.shields.io/badge/NLTK-4B8BBE?style=flat-square&logo=python&logoColor=white)](https://www.nltk.org/)

---

## Project Overview

**OceanVista** is designed to revolutionize beach tourism in India by providing tourists and beachgoers with real-time beach safety and suitability information. Using data from **INCOIS** and in-house AI models, the app evaluates multiple parameters to generate comprehensive beach safety bulletins. It also integrates nearby facilities and booking options, ensuring users' comfort and convenience.

The project started in **October 2024** and includes mobile apps for users and partners, a web portal for partner businesses, and backend services supporting all platforms.

---

## Features

- **Beach Safety Bulletins:** Real-time safety scores and alerts using INCOIS data and AI-based models.  
- **Color-Coded Beach Map:** Visual safety indicators for all beaches in India.  
- **Integrated Booking Services:** Book nearby resorts, medical services, shops, and vendor services.  
- **SOS Mechanism:** Emergency support with instant location sharing and connections to medical services.  
- **Partner Portal:** A web platform for business partners to manage services, bookings, and payments.  
- **Advanced Analytics:** Weekly reports with actionable insights for partner businesses to optimize performance.  
- **Authentication:** Secure user and partner authentication using JWT and OAuth2 for Google and GitHub.  
- **Multiplatform Support:** Native Android (Jetpack Compose), iOS (Swift), and web (React).  

---

## Architecture

OceanVista adopts a microservices architecture for scalability and maintainability.

- **APP_ENGINE:**  
  Handles user interactions and integrates with Kafka, Flask-based ML models, and a live web crawler for INCOIS data.  
  Entities: Folks (users), UserLocation, Alerts, Beach, BeachConditions, SOS.  

- **PARTNER_ENGINE:**  
  Manages partner functionalities, including subscriptions and payments.  
  Entities: Partner, SubscriptionPlan, Payment, Resort, MedicalService.  

- **ANALYTICS_ENGINE:**  
  Provides advanced analytics and generates business reports.  
  Communicates with other engines via Kafka or Redis.

### Technology Highlights

- **Backend:** Spring Boot-based RESTful APIs with PostgreSQL for relational data.  
- **Frontend:** React for web, Jetpack Compose for Android, and Swift for iOS.  
- **AI Models:** Flask-based services powered by TensorFlow, Scikit-Learn, and PyTorch.  
- **Deployment:** Dockerized microservices orchestrated with Kubernetes on AWS EKS.  
- **Inter-Service Communication:** Apache Kafka and Redis.  

---

## Installation & Usage

To set up OceanVista locally, follow these steps:

1. **Clone the repository:**
   ```bash
   git clone https://github.com/singhtwenty2/OceanVista.git
   cd OceanVista
   ```

2. **Backend Setup:**
   Make sure you have Java 17+ and Maven installed.
   Navigate to the backend directory
   ```bash
   cd APP_ENGINE
   mvn clean install
   mvn spring-boot:run

   cd PARTNER_ENGINE
   mvn clean install
   mvn spring-boot:run
   ```
   Configure the database by setting up application.yml in the backend project with the appropriate PostgreSQL configurations (e.g., database URL, username, password).

3. **Frontend Setup:**
   ```bash
   cd WEB_PORTAL/OceanVistaPartnerPortal
   npm install
   npm run dev
   ```

4. **Mobile Setup:**
   For Android (Jetpack Compose):
   Open the Android project in Android Studio, build, and run it on an emulator or device.

5. **Docker Setup (Optional):**
   If using Docker, make sure Docker is installed.
   Build and run the Docker containers:
   ```bash
   docker-compose up --build
   ```

---

## Contributing

We welcome contributions! Please read our [Contributing Guidelines](CONTRIBUTING.md) for information on how to contribute.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Contact

For queries, contact Aryan Singh at work.singhtwenty2@gmail.com
