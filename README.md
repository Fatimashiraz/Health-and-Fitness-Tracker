# Health & Fitness Tracker - SDG 3: Good Health and Well-being

## Project Overview

This Java application is designed to address **SDG 3: Good Health and Well-being** by providing a comprehensive health and fitness tracking system. The application helps users monitor their health metrics, track fitness activities, set goals, and maintain a healthy lifestyle.

## Features

### 🏥 Health Tracking
- **BMI Calculator**: Automatically calculates Body Mass Index based on weight and height
- **Health Status Assessment**: Provides health status based on BMI ranges
- **Vital Signs Tracking**: Record blood pressure and other health metrics
- **Health Notes**: Add personal health notes and observations

### 🏃‍♂️ Fitness Tracking
- **Daily Steps Counter**: Track daily step count
- **Calorie Burn Monitor**: Record calories burned during activities
- **Exercise Log**: Log different types of exercises and duration
- **Fitness Notes**: Document workout routines and progress

### 🎯 Goal Setting
- **Weight Goals**: Set target weight goals
- **Fitness Goals**: Set daily step and calorie burn targets
- **Progress Tracking**: Monitor progress towards goals
- **Goal Notes**: Document goal strategies and motivations

### 📊 Reports and Analytics
- **Health Reports**: Generate comprehensive health reports
- **Progress Visualization**: View progress over time
- **Data Export**: Export health and fitness data
- **Trend Analysis**: Analyze health and fitness trends

## Technical Implementation

### Java Features Used
- **Object-Oriented Programming**: Classes for HealthRecord, FitnessRecord, and Goal
- **GUI Development**: Swing framework for user interface
- **Data Structures**: ArrayList for storing records
- **File I/O**: Data persistence using text files
- **Exception Handling**: Robust error handling throughout the application
- **Event Handling**: Action listeners for user interactions

### Application Structure
```
HealthFitnessTracker.java
├── Main Application Class
├── GUI Components (5 tabs)
├── Data Management Classes
└── File I/O Operations
```

## How to Run

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- Any Java IDE (IntelliJ IDEA, Eclipse, NetBeans) or command line

### Running the Application

#### Option 1: Using IDE
1. Open the project in your Java IDE
2. Compile the `HealthFitnessTracker.java` file
3. Run the main method

#### Option 2: Using Command Line
```bash
# Compile the Java file
javac HealthFitnessTracker.java

# Run the application
java HealthFitnessTracker
```

## SDG 3 Alignment

This application directly contributes to **SDG 3: Good Health and Well-being** by:

1. **Promoting Healthy Lifestyles**: Encourages regular physical activity and health monitoring
2. **Preventive Healthcare**: Helps users identify health trends and potential issues early
3. **Health Awareness**: Educates users about BMI, fitness goals, and healthy living
4. **Personal Health Management**: Empowers individuals to take control of their health
5. **Data-Driven Health Decisions**: Provides insights for better health choices

## Project Deliverables

### 1. Java Application (20% of total grade)
- ✅ Functional Java application with GUI
- ✅ Addresses SDG 3: Good Health and Well-being
- ✅ Uses Java IDE and compiler
- ✅ Implements programming concepts: variables, data types, selection, repetition, methods, arrays, file I/O, exception handling
- ✅ Simple GUI using Swing

### 2. Project Report (10% of total grade)
- Printed report (10-12 pages)
- A4 format with Calibri/Arial font, size 11, 1.5 line spacing
- Includes problem statement, objectives, system design, implementation details, testing, and conclusion

### 3. Presentation and Demonstration (10% of total grade)
- 7-10 minute presentation
- Live demonstration of the application
- Problem overview and SDG alignment explanation

## File Structure

```
Sulton Java Project/
├── HealthFitnessTracker.java    # Main application file
├── README.md                    # Project documentation
├── health_records.txt           # Health data storage (auto-generated)
├── fitness_records.txt          # Fitness data storage (auto-generated)
└── goals.txt                    # Goals data storage (auto-generated)
```

## Usage Instructions

1. **Dashboard**: Overview of health and fitness statistics
2. **Health Tracking**: Enter weight, height, age, and blood pressure to calculate BMI and health status
3. **Fitness Tracking**: Log daily steps, calories burned, and exercise activities
4. **Goals**: Set and monitor health and fitness goals
5. **Reports**: Generate and view detailed health reports

## Technical Requirements

- **Programming Language**: Java
- **GUI Framework**: Swing
- **Data Storage**: Text files for persistence
- **Java Version**: JDK 8 or higher
- **Platform**: Cross-platform (Windows, macOS, Linux)

## Learning Outcomes

This project demonstrates:
- **CLO3**: Building a system with Java programming language using a compiler
- **PLO6**: Utilizing digital skills for problem-solving in the field of study
- **Domain**: Practical Skills (Psychomotor Level P3)

## Future Enhancements

- Database integration for better data management
- Advanced analytics and visualization
- Mobile app version
- Integration with wearable devices
- Social features for community support
- Nutritional tracking capabilities

---

**Author**: Student  
**Course**: Java Programming  
**SDG Focus**: SDG 3 - Good Health and Well-being  
**Version**: 1.0
