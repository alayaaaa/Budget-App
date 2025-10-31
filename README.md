# Budget-App
Technologies used: Java, SQLite <br/>
A Budget app that tracks expenses using the 50/30/20 rule and budget based on pay frequency.

## Plans
- ~~Use a better json library to accomodate localDate data type~~
- ~~Create an add, edit, and delete method that can modify the json file~~
- ~~Implement SQLite to replace JSONArray and JSONParser for efficiency and better data management~~
- Add methods for computing and displaying transactions
- Add methods for budgeting based on 50/30/20 rule
- Create a UI

## Version Updates
### Version 0.0.4
- Finished Update part oF CRUD with methods that edit information based on user input
- Added Shell class to improve readability on main class
- Fixed issues in FinanceManager which causes database to be initialized twice every time the program runs
- Changed file path of the database to a folder named resources
- Improved error handling by specifying what error occurs
  
### Version 0.0.3
- Finished Create, Read, and Delete part of CRUD
- Added FinanceManager class to handle all mathematical computations
  
### Version 0.0.2
- Removed JSON system
- Replaced JSON System of storing user input/data with SQLite

### Version 0.0.1
- Used JSON to store user input
