pipeline {
    agent any

    tools {
        jdk 'JDK17'
        maven 'Maven3'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Test') {
            steps {
                script {
                    if (isUnix()) {
                        sh 'mvn clean test -Dheadless=true'
                    } else {
                        bat 'mvn clean test -Dheadless=true'
                    }
                }
            }
        }
    }

    post {
        always {
            junit allowEmptyResults: true, testResults: 'target/cucumber-reports/cucumber.xml,target/surefire-reports/*.xml'
            archiveArtifacts allowEmptyArchive: true, artifacts: 'target/cucumber-reports/**,target/screenshots/**,target/surefire-reports/**'
        }
    }
}