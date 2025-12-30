pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/ajit-kr-chaudhary/DevOps-Sample-API.git'
            }
        }

        stage('Build') {
            steps {
                sh './gradlew clean build -x test'
            }
        }

        stage('JUnit Tests') {
            steps {
                sh './gradlew test'
            }
            post {
                always {
                    junit 'build/test-results/test/*.xml'
                }
            }
        }
    }

    post {
        success {
            echo 'Build & Tests successful'
        }
        failure {
            echo 'Build or Tests failed'
        }
    }
}

