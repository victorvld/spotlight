# Spotlight

[![Code Coverage](https://img.shields.io/badge/Code%20Coverage-85%25-brightgreen)](URL_TO_YOUR_COVERAGE_REPORT)

Spotlight is a project designed to help you enhance your productivity by tracking your focus units and linking them to specific tasks or resources. A focus unit (FU) represents the time you spend working on a task and the accompanying break time.

By diligently recording and analyzing your focus units, you can gain a deeper understanding of your work habits and identify areas for improvement. This self-reflective aspect of Spotlight empowers you to make informed decisions about how to optimize your productivity and achieve your goals.

- [Getting Started](#getting-started)
    - [Clone the Repository](#clone-the-repository)
    - [Run the Application](#run-the-application)
- [Usage](#usage)
    - [Time API](#time-api)
    - [Link API](#link-api)
- [Contributing](#contributors)
- [License](#license)

## Getting Started

### Clone the Repository

You can get started with Spotlight by cloning this repository to your local machine using the following command:

```bash
git clone https://github.com/victorvld/spotlight.git
```

### Run the Application

Spotlight uses Docker Compose to manage its services. To run the application, make sure you have Docker and Docker Compose installed. Then, navigate to the project's root directory and run the following command:

```bash
docker-compose up
```

This will start the Spotlight application and its dependencies.

## Usage

Spotlight comprises two main APIs: Time API and Link API, which allow you to manage your focus units and establish connections with your tasks and resources.

### Time API

The Time API allows you to record focus units (FUs) and retrieve information about your focus units. Here are some common use cases:

- **Record Focus Unit (FU)**: Use the API to track your focused work time and breaks. You can record FUs by specifying the task and its associated time intervals.

- **Get Focus Units**: Retrieve a list of your recorded focus units, including details such as task name, start time, end time, and break duration.

### Link API

The Link API provides functionality for linking focus units to specific resources or tasks. Use cases for this API include:

- **Get Project, Sprint, or Task with Linked FUs**: Retrieve project, sprint, or task information along with the focus units recorded for each. This feature allows you to see how your FUs are distributed across your work.

## Contributors

We welcome contributions to improve Spotlight from the open-source community. If you're interested in contributing, please fork the repository, make your changes, and submit a pull request.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
