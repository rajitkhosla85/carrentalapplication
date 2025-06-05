# Car Rental Application

The Car Rental Application is a Java-based project designed to facilitate the process of renting cars. It provides functionalities for users to browse available vehicles, make reservations, and manage their bookings.

## Features

- **User Registration & Authentication**: Users can create accounts and log in to access personalized services.
- **Car Browsing**: View a list of available cars with details such as make, model, and rental price.
- **Booking System**: Reserve cars for specific dates and times.
- **Booking Management**: View, modify, or cancel existing bookings.
- **Admin Panel**: Admins can manage car listings and user bookings.

## Technologies Used

- **Programming Language**: Java
- **Build Tool**: Maven
- **Database**: Not specified
- **Frameworks/Libraries**: Not specified

## Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/rajitkhosla85/carrentalapplication.git
   ```

2. Navigate to the project directory:
   ```bash
   cd carrentalapplication
   ```

3. Build the project using Maven:
   ```bash
   mvn clean install
   ```

4. Run the application:
   ```bash
   mvn exec:java
   ```

## Usage

Upon running the application, users will be prompted to log in or register. After authentication, users can:

- **Browse Cars**: View available cars with their details.
- **Make a Reservation**: Select a car and specify the rental period.
- **Manage Bookings**: View, modify, or cancel existing reservations.

Admins have additional privileges to manage car listings and oversee user bookings.

## Configuration

Configuration settings such as database connections and application properties are managed through the `application.properties` file located in the `src/main/resources` directory.

## Contributing

Contributions are welcome! Please fork the repository and submit a pull request for any enhancements or bug fixes.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
