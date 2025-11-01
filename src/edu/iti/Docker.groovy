package edu.iti

def buildD(imageName, buildNumber) {
    echo "Building Docker image: ${imageName}:${buildNumber}"
    sh "docker build -t ${imageName}:${buildNumber} ."
}

def login(username, password) {
    echo "Logging into Docker Hub"
    sh "echo ${password} | docker login -u ${username} --password-stdin"
}

def push(imageName, buildNumber) {
    echo "Pushing Docker image: ${imageName}:${buildNumber}..."
    sh "docker push ${imageName}:${buildNumber}"
}

def deploy(imageName, buildNumber, containerName, portMapping) {
    echo "Deploying Docker container: ${containerName} from image: ${imageName}:${buildNumber}"
    sh "docker run -d -p ${portMapping} --name ${containerName} ${imageName}:${buildNumber}"
}
