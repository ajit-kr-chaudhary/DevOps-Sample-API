pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh '''
                    export JAVA_HOME=/usr/lib/jvm/java-21-amazon-corretto.x86_64
                    export PATH=$JAVA_HOME/bin:$PATH
                    ./gradlew clean build -x test --no-daemon
                '''
            }
        }

        stage('JUnit Tests') {
            steps {
                sh '''
                    export JAVA_HOME=/usr/lib/jvm/java-21-amazon-corretto.x86_64
                    export PATH=$JAVA_HOME/bin:$PATH
                    ./gradlew test --no-daemon
                '''
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

