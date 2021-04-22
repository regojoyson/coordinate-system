# Table of Contents
- [Introduction](#introduction)
- [Objectives of the Project](#objectives-of-the-project)
- [Setup](#setup) 
- [Dev Build](#dev-build)

# Introduction

This Application related to Cartesian Coordinate System, which has some utilities related to it.

# Dependencies
	Following are the basic requirements for the Service

		1. Java 16
		2. Gradle

# Objectives of the Project

	The Objectives of the Project are as follows:
	
	    1. Definition of a line by means of two points
        2. Definition of a line by means of gradient and y-intercept
        3. Condition of parallelism of two lines
        4. Condition of perpendicularity of two lines
        5. Condition of incidence of two lines and definition of the incidence point
		
# Setup 

	Not Applicable

# Dev Build

	How to run the application in Dev
		1. Package - gradle clean build
		2. Test - gradle test
		3. Run the System - gradle bootrun
				
## Unit Test (For Dev)

      1. Test cases result are found inside the \build\reports\tests\test\index.html  post build/test.

## Postman (For Dev)

      1. Import the json file in the Postman Collection from src/test/postman/cartesian-coordinate-system.postman_collection.json and run the Unit Tests for testing the API's
