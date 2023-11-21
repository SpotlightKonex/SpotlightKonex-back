pipeline {
    agent any

    stages {
        stage('Clone repository') {
            steps {
                checkout scm
            }
        }

        stage('Build and package') {
            steps {
                script {
                    withGradle(gradle: 'gradle', wrapper: true) {
                        sh './gradlew clean build --scan'
                    }
                }
            }
        }

        stage('Build image') {
            steps {
                script {
                    app = docker.build("minseo205/spotlight-konex")
                }
            }
        }

        stage('Push image') {
            steps {
                script {
                    docker.withRegistry('https://registry.hub.docker.com', 'docker-credentials') {
                        app.push("${env.BUILD_NUMBER}")
                        app.push("latest")
                    }
                }
            }
        }
    }
}
