# Jenkins Shared Library

This repository contains a Jenkins Shared Library with helper functions for Docker and Maven operations.

## Available Modules

### Docker

This module provides functions for interacting with Docker.

**Functions:**

- `buildD(imageName, buildNumber)`: Builds a Docker image.
  - `imageName`: The name of the Docker image.
  - `buildNumber`: The build number to tag the image with.
- `login(username, password)`: Logs into Docker Hub.
  - `username`: Your Docker Hub username.
  - `password`: Your Docker Hub password or access token.
- `push(imageName, buildNumber)`: Pushes a Docker image to a registry.
  - `imageName`: The name of the Docker image.
  - `buildNumber`: The build number of the image to push.
- `deploy(imageName, buildNumber, containerName, portMapping)`: Deploys a Docker container.
  - `imageName`: The name of the image to deploy.
  - `buildNumber`: The build number of the image.
  - `containerName`: The name for the new container.
  - `portMapping`: The port mapping (e.g., "8080:80").

### Maven

This module provides functions for Maven operations.

**Functions:**

- `buildM()`: Runs `mvn clean install`.
- `test()`: Runs `mvn test`.

## Usage Example

Here is an example of how to use this shared library in a `Jenkinsfile`:

```groovy
@Library('jenkins-shared-library') _

import edu.iti.Docker
import edu.iti.Maven

pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                script {
                    Maven.buildM()
                }
            }
        }
        stage('Test') {
            steps {
                script {
                    Maven.test()
                }
            }
        }
        stage('Docker Build and Push') {
            steps {
                script {
                    def dockerImage = 'my-docker-image'
                    Docker.buildD(dockerImage, env.BUILD_NUMBER)
                    Docker.login('my-username', 'my-password')
                    Docker.push(dockerImage, env.BUILD_NUMBER)
                }
            }
        }
        stage('Deploy') {
            steps {
                script {
                    def dockerImage = 'my-docker-image'
                    def containerName = 'my-app-container'
                    def portMapping = '8080:8080'
                    Docker.deploy(dockerImage, env.BUILD_NUMBER, containerName, portMapping)
                }
            }
        }
    }
}
```
