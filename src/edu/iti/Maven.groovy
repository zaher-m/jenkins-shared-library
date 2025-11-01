package edu.iti

def buildM() {
    echo "Running Maven Build..."
    sh "/usr/bin/mvn clean install"
}

def test() {
    echo "Running Maven Tests..."
    sh "/usr/bin/mvn test"
}
