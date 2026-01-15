pipeline {
    agent any

    tools {
        maven 'Maven'
    }

    environment {
        DOCKER_USER = 'moustaphasy'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout([$class: 'GitSCM',
                    branches: [[name: '*/main']],
                    userRemoteConfigs: [[url: 'https://github.com/Moustapha-s6/inventorySonis']]])
            }
        }

        stage('Build Maven') {
            steps {
                bat 'mvn clean install -DskipTests'
            }
        }

        stage('Determine Docker Tag') {
            steps {
                script {
                    def major = 1
                    def minor = 0
                    if (currentBuild.previousBuild != null && currentBuild.previousBuild.description != null) {
                        def lastTag = currentBuild.previousBuild.description
                        def parts = lastTag.replace("inventory-", "").tokenize('.')
                        major = parts[0].toInteger()
                        minor = parts[1].toInteger() + 1
                        if (minor > 9) {
                            major += 1
                            minor = 0
                        }
                    }
                    env.IMAGE_TAG = "inventory-${major}.${minor}"
                    echo "Docker tag: ${env.IMAGE_TAG}"
                    currentBuild.description = env.IMAGE_TAG
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                bat "docker build -t ${DOCKER_USER}/inventorysonis-api:%IMAGE_TAG% ."
            }
        }

        stage('Push Docker Image') {
            steps {
                withCredentials([string(credentialsId: 'dockerhub-token', variable: 'DOCKER_TOKEN')]) {
                    bat """
                    @echo off
                    echo|set /p="%DOCKER_TOKEN%" | docker login -u %DOCKER_USER% --password-stdin
                    docker push ${DOCKER_USER}/inventorysonis-api:%IMAGE_TAG%
                    """
                }
            }
        }

    }

    post {
        always {
            echo 'Pipeline terminé'
        }
        success {
            echo "Build et Push réussis ! Docker tag: ${env.IMAGE_TAG}"
        }
        failure {
            echo 'Quelque chose a échoué.'
        }
    }
}
