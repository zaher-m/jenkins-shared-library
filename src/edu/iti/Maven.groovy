package edu.iti

def mavenBuild() {
    echo "Running Maven Build..."
    sh "/usr/bin/mvn clean install"
}

def mavenTest() {
    echo "Running Maven Tests..."
    sh "/usr/bin/mvn test"
}
