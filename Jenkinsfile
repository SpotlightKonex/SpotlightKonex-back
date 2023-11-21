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

        stage('Deploy') {
        	steps {
            	sshagent(credentials: ['nhn-key']) {
        			sh '''
        				ssh -o StrictHostKeyChecking=no ubuntu@10.2.1.58
                        scp /var/jenkins_home/workspace/spotlight-konex-pipeline/build/libs/spotlight-konex-pipeline-0.0.1-SNAPSHOT.jar ubuntu@10.2.1.58:/home/ubuntu
                        ssh -tt ubuntu@10.2.1.58 sh ./deploy.sh
                    '''
        		}
        	}
        }
    }
}
