# Project Name shopperpersonaliezationservice

This project is a simple Java web application developed using Spring Boot. It serves as a template for creating RESTful APIs and includes basic functionalities such as user authentication, CRUD operations, and data caching. The project is designed to be easily customizable and extensible, making it suitable for various web application development needs.

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Installation](#installation)
- [Usage](#usage)
- [Endpoints](#endpoints)
- [Testing](#testing)
- [Contributing](#contributing)
- [License](#license)
- [Contact](#contact)

## Introduction

[Provide an overview of the project, its purpose, and its significance.]

## Features

- [List the main features of the project, highlighting its functionalities.]

## Technologies Used

- Java
- Spring Boot
- Hibernate
- MySQL
- Redis (for caching)
- Maven

[Add any other relevant technologies used in the project.]

## Installation

[Provide instructions on how to install and set up the project locally. Include steps for setting up the database, configuring environment variables, etc.]

## Usage

[Explain how to use the project, including any setup required before running the application.]

## Endpoints

http://localhost:8080/api/addProducts[post]
http://localhost:8080/api/fetchAllProductsPagenation?page=2&size =10[get]
http://localhost:8080/shelves/getAll[get]
http://localhost:8080/shelves/addShelvesByShopperId/2000[post]


### Example:

### 1.  Product
- Endpoint: `POST /api/addProducts`
- Description: Adds a list of products to the database.
- Request Body: JSON array of product objects.
```json
[
  {
    "productId": "BG-419110951",
    "category": "Babies",
    "brand": "Girlds"
  },
  {
    "productId": "BG-419110952",
    "category": "Babies",
    "brand": "Girlds"
  },
  ...
]

### 2. Fetch All Products with Pagination
  - Endpoint: `GET http://localhost:8080/api/fetchAllProductsPagenation?page=1&size=10`
  - Description: Retrieves a paginated list of products.
  - Query Parameters:
    - `page`: Page number (default: 0)
    - `size`: Page size (default: 10)
  - Response: JSON array of product objects.
```json
[
    {
        "id": 1,
        "productId": "BG-419110951",
        "category": "Babies",
        "brand": "Girlds"
    },

[List and describe the available endpoints of the REST API, including their methods, parameters, and responses.]



## Testing

[Provide instructions for running the unit tests and any other testing procedures.]

