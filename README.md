# 🛍️ Test Shop

> A lightweight eCommerce automation testing project based on a local React application.  
> 🔗 GitHub Repo: [https://github.com/moaazawwad/test-shop](https://github.com/moaazawwad/test-shop)

---

## 📋 Table of Contents

- [Overview](#overview)
- [Key Features](#key-features)
- [Setup](#setup)
- [Local Testing Execution Example](#local-testing-execution-example)
- [Sample Test Scenarios](#sample-test-scenarios)
- [Contribute](#contribute)
- [License](#license)

---

## 🔍 Overview

**Test Shop** is a Selenium-TestNG automation framework that verifies the critical functionalities of a local React-based eCommerce platform. It covers real user flows including sign-up, login, product browsing, wishlist, cart, checkout, and currency change.

---

## ✨ Key Features

- ✅ **End-to-End user journey**: from dynamic user registration to successful order confirmation and payment validation  
- 🔐 Automated login and logout verification  
- 🔎 Product search testing (e.g., "Nike")  
- ❤️ Wishlist: add and verify random products  
- 🛒 Full cart workflow: add items, remove, and perform checkout  
- 💳 Payment flow simulation with Stripe test card  
- 💱 Currency switch: USD ↔ EUR with symbol validation  

---

## ⚙️ Setup

> This project depends on a **local React frontend**. Make sure it’s running before executing automation tests.

### 1. Run the React App

```bash
cd path-to-your-react-app
npm install
npm run dev
```

It should be available at: `http://localhost:5173/`

### 2. Run the Automation Tests

```bash
cd test-shop
mvn clean verify
```



## ✅ Sample Test Scenarios

| # | Scenario | Description |
|---|----------|-------------|
| 1 | **User Registration & Login** | Dynamic signup, logout, and login using same data |
| 2 | **Product Search** | Search for “Nike” and assert correct results |
| 3 | **Wishlist** | Add random items and verify on wishlist page |
| 4 | **Cart & Checkout** | Add items to cart, remove some, fill shipping & payment info |
| 5 | **Currency Switch** | Switch to EUR and validate currency symbols updated |

---

## 🤝 Contribute

Have improvements or ideas? Fork the repo, open issues, or submit a PR!

---

## 📄 License

MIT License © 2025 Moaaz Awwad
