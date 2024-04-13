
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

## Examples:

### 3. Get All Shelves

- **Endpoint:** GET /shelves/getAll
- **Description:** Retrieves all shelves along with their products.
- **Response:**
```json
[
    {
        "id": 1,
        "shopperId": "1000",
        "shelf": [
            {
                "id": 1,
                "productId": "MB-2093193398",
                "relevancyScore": 31.089209569320897
            },
            {
                "id": 2,
                "productId": "BB-2144746855",
                "relevancyScore": 55.16626010671777
            }
        ]
    }
]

## Examples:

### 4. Add Shelves by Shopper ID

- **Endpoint:** POST /shelves/addShelvesByShopperId/{shopperId}
- **Description:** Adds shelves with products for a specific shopper.
- **Path Parameters:**
  - `shopperId`: The ID of the shopper.
- **Request Body:** JSON object containing a list of shelf items.
```json
{
  "shelf": [
    {
      "productId": "MB-2093193398",
      "relevancyScore": 31.089209569320897
    },
    {
      "productId": "BB-2144746855",
      "relevancyScore": 55.16626010671777
    }
  ]
}



## Testing

[Provide instructions for running the unit tests and any other testing procedures.]

