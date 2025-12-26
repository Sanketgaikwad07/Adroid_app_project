# 🛒 Fake Store Android App

An Android application that displays products from a remote API with **offline caching support using Room Database**.
The app follows a **clean architecture** with Repository pattern and supports **online & offline usage**.

---

## 📌 Features

*  Fetch product list from REST API
*  Product listing using RecyclerView
*  Offline caching using Room Database
*  Automatic handling of online/offline state
*  Fast UI with cached data
*  Clean code structure (UI → Repository → Data)
*  Modern Android development practices

---

## 🏗️ Architecture

```
UI (Activity)
   ↓
Repository
   ↓
--------------------------------
| Retrofit API | Room Database |
--------------------------------
```

### Flow Explanation

* **Internet Available**

  * Fetch data from API
  * Save data into Room database
  * Display data in RecyclerView
* **Internet Not Available**

  * Load cached data from Room
  * Display data without API call

---

## 🛠️ Tech Stack

* **Language**: Java
* **UI**: XML, RecyclerView
* **Networking**: Retrofit
* **Image Loading**: Picasso
* **Database**: Room
* **Build System**: Gradle (Kotlin DSL)
* **Architecture**: Repository Pattern

---

## 📂 Project Structure

```
com.example.fake_store
│
├── adapter
│   └── ProductAdapter.java
│
├── api
│   ├── ApiService.java
│   └── RetrofitClient.java
│
├── model
│   └── Product.java
│
├── repository
│   └── ProductRepository.java
│
├── database
│   ├── AppDatabase.java
│   └── ProductDao.java
│
├── utils
│   └── NetworkUtils.java
│
└── ui
    └── MainActivity.java
```

---

## 🗄️ Offline Caching (Room Database)

### Why Room?

* Provides local storage
* Works without internet
* Improves performance
* Used in real production apps

### How It Works

1. Data fetched from API using Retrofit
2. Response stored in Room database
3. When offline, data is read from Room
4. UI works seamlessly without internet

---



## 🔌 API Used

* Fake Store API

``
https://fakestoreapi.com/products
```

---

## ▶️ How to Run the Project

1. Clone the repository

   ```bash
   git clone https://github.com/Sanketgaikwad07/Adroid_app_project
   ```

2. Open in **Android Studio**

3. Sync Gradle

4. Run on:

   * Android Emulator **OR**
   * Physical Android device

---

##  Testing Offline Mode

1. Run app with **internet ON**
2. Products load from API
3. Turn **internet OFF**
4. Restart app
5. Products load from **Room database** 

---

##  Output

link=https://github.com/user-attachments/assets/3e9cb945-35a0-4cfe-a611-28caf39b4e20

```

## 👨‍💻 Author

**Sanket Gaikwad**
Java Backend & Android Developer

---

