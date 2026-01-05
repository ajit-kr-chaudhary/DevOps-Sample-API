pipeline {
    agent any

    environment {
        JAVA_HOME = "/usr/lib/jvm/java-21-amazon-corretto.x86_64"
        PATH = "${JAVA_HOME}/bin:${env.PATH}"
        ANSIBLE_HOME = "/root/ansible-lab"
    }

    stages {

        stage('Build') {
            steps {
                sh '''
                    ./gradlew clean build -x test --no-daemon
                '''
            }
        }

        stage('JUnit Tests') {
            steps {
                sh '''
                    ./gradlew test --no-daemon
                '''
            }
            post {
                always {
                    junit 'build/test-results/test/*.xml'
                }
            }
        }

        stage('Deploy to DEV') {
            steps {
                sh '''
                    cd $ANSIBLE_HOME
                    ansible-playbook playbooks/deploy-dev.yml
                '''
            }
        }
    }

    post {
        success {
            echo 'CI + CD to DEV successful 🚀'
        }
        failure {
            echo 'Pipeline failed ❌'
        }
    }
}
