pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh './gradlew clean build -x test --no-daemon'
            }
        }

        stage('JUnit Tests') {
            steps {
                sh './gradlew test --no-daemon'
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

