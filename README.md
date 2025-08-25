# 🩺 NFZ Data Explorer

## 📌 Overview
**NFZ Data Explorer** is a Java application that connects to the official  
[National Health Fund (NFZ) API](https://api.nfz.gov.pl/) to parse and display information about medical facilities, queues, benefits, and healthcare statistics in Poland.  

The system consists of:
- **Backend** – responsible for communication with NFZ REST API and parsing JSON responses.  
- **Frontend (JavaFX)** – provides an interactive GUI to browse and analyze the data.  

This project is designed for students, researchers, and healthcare analysts who want quick access to NFZ open data with a friendly interface.

---

## ✨ Features
### 🔙 Backend
- 🌍 **Medical Facilities Lookup** – search NFZ places by city, street, province.  
- 📅 **Queues & Waiting Times** – explore availability of benefits, providers, and waiting list lengths.  
- 💊 **Provisions & Medicines** – analyze drug provisions and refunds by region, ATC code, age, gender, privileges.  
- 📊 **Hospitalization Data** – retrieve aggregated NFZ statistics by year, catalog, and age groups.  
- ⚡ **OkHttp + JSON Parsing** – efficient REST API communication.

### 🎨 Frontend (JavaFX)
- Intuitive **search forms** for filtering by city, benefit, provider, etc.  
- **Dynamic tables and charts** with live NFZ data.  
- **Filter panel** for detailed queries (region, date range, ATC codes, age groups).  
- **Real-time refresh** using multithreaded requests.  

---

## 🛠️ Technologies
- **Java 17+**  
- **JavaFX** – GUI frontend  
- **OkHttp** – REST API client  
- **Jackson** – JSON parsing  
- **Maven** – build and dependency management  

---
