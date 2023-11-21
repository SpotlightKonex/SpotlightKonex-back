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
                    withGradle(gradle: 'Gradle_Installation_Name', wrapper: true) {
                        sh './gradlew clean build'
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
