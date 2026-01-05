pipeline {
    agent any

    parameters {
        choice(
            name: 'DEPLOY_ENV',
            choices: ['dev', 'idev', 'test', 'uat'],
            description: 'Select environment to deploy'
        )
    }

    environment {
        JAVA_HOME = '/usr/lib/jvm/java-21-amazon-corretto.x86_64'
        PATH = "${JAVA_HOME}/bin:${env.PATH}"
        ANSIBLE_HOME = '/opt/ansible-lab'
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

        stage('Approval') {
            steps {
                input message: "Approve deployment to ${params.DEPLOY_ENV} environment?",
                      ok: "Deploy"
            }
        }

        stage('Deploy') {
            steps {
                sh '''
                    cd ${ANSIBLE_HOME}
                    ansible-playbook playbooks/deploy-${DEPLOY_ENV}.yml
                '''
            }
        }
    }

    post {
        success {
            echo "CI + CD completed successfully for ${params.DEPLOY_ENV}"
        }
        failure {
            echo "Pipeline failed"
        }
    }
}
