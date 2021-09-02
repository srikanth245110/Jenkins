
#### Paralelly execute stages

    pipeline {
      agent any
      stages {
        stage('Checkout') {
          steps {
            git(url: 'https://github.com/venkatasykam/DevOpsWebApp', branch: 'web', credentialsId: 'jengit')
          }
        }
        stage('Build') {
          parallel {
            stage('Build') {
              steps {
                sh '"/root/apache-maven-3.5.4/bin/mvn" -V clean install'
              }
            }
            stage('Build-Status') {
              steps {
                echo 'Build is successful'
              }
            }
          }
        }
        stage('Deploy') {
          steps {
            echo 'Deploy stage'
          }
        }
      }
    }
