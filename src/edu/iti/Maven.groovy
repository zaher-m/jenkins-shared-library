package edu.iti

def build() {
    echo "Running Maven Build..."
    sh "/usr/bin/mvn clean install"
}

def test() {
    echo "Running Maven Tests..."
    sh "/usr/bin/mvn test"
}
