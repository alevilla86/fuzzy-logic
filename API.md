# Fuzzy Logic REST API

This project provides REST API endpoints for fuzzy logic systems:
1. Hotel Recommendation System
2. Temperature/Humidity Risk Assessment System

## Running the Application

### Build the project
```bash
mvn clean package
```

### Run the application
```bash
java -jar target/fuzzy-logic.jar
```

The application will start on port 8080.

## API Endpoints

### 1. Hotel Recommendation

**Endpoint:** `POST /api/hotel/recommend`

**Description:** Get a hotel recommendation score based on budget and rating.

**Request Body:**
```json
{
  "budget": 1000,
  "rating": 8
}
```

**Response:**
```json
{
  "recommendation": 8.730801603206341,
  "budget": 1000,
  "rating": 8
}
```

**Example using curl:**
```bash
curl -X POST http://localhost:8080/api/hotel/recommend \
  -H "Content-Type: application/json" \
  -d '{"budget": 1000, "rating": 8}'
```

**Parameters:**
- `budget` (integer): Hotel budget in currency units (e.g., 0-3000)
- `rating` (integer): Hotel rating on a scale of 0-10

**Response:**
- `recommendation` (double): Recommendation score on a scale of 0-10
- `budget` (integer): The budget value from the request
- `rating` (integer): The rating value from the request

### 2. Temperature Risk Assessment

**Endpoint:** `POST /api/temperature/risk`

**Description:** Get a risk assessment based on temperature and humidity levels.

**Request Body:**
```json
{
  "temperature": 25,
  "humidity": 60
}
```

**Response:**
```json
{
  "risk": 4.999999999999998,
  "temperature": 25,
  "humidity": 60
}
```

**Example using curl:**
```bash
curl -X POST http://localhost:8080/api/temperature/risk \
  -H "Content-Type: application/json" \
  -d '{"temperature": 25, "humidity": 60}'
```

**Parameters:**
- `temperature` (integer): Temperature in Celsius
- `humidity` (integer): Humidity percentage (0-100)

**Response:**
- `risk` (double): Risk score on a scale of 0-10
- `temperature` (integer): The temperature value from the request
- `humidity` (integer): The humidity value from the request

## Example Use Cases

### Low Budget, Poor Rating
```bash
curl -X POST http://localhost:8080/api/hotel/recommend \
  -H "Content-Type: application/json" \
  -d '{"budget": 300, "rating": 2}'
```
Expected: Low recommendation score

### High Budget, Good Rating
```bash
curl -X POST http://localhost:8080/api/hotel/recommend \
  -H "Content-Type: application/json" \
  -d '{"budget": 2500, "rating": 9}'
```
Expected: High recommendation score

### High Temperature and Humidity
```bash
curl -X POST http://localhost:8080/api/temperature/risk \
  -H "Content-Type: application/json" \
  -d '{"temperature": 35, "humidity": 80}'
```
Expected: High risk score

## Technical Details

- **Framework:** Spring Boot 3.2.1
- **Java Version:** 17
- **Fuzzy Logic Engine:** jFuzzyLogic 3.3
- **Build Tool:** Maven

## Fuzzy Logic Rules

The fuzzy logic rules are defined in FCL (Fuzzy Control Language) files located in `src/main/resources/`:
- `recommend_rules.fcl` - Hotel recommendation rules
- `risk_rules.fcl` - Temperature/humidity risk assessment rules
