**Examples of environment section in declarative pipleine**

  
    sh 'printenv'
    
    pipeline{
        agent any
        stages{
            stage("Env example-1"){
                steps{
                    sh 'printenv'
                }
            }
        }
    }
    
    -------------------------------------------------------------------
    
    pipeline {
      agent any
      environment { 
          CC = 'clang'
      }
      stages {
          stage('Example1') {
              environment { 
                  DEBUG_FLAGS = '-g'
              }
              steps {
                  //sh 'printenv'

                  sh 'echo CC value in stage-1: ${CC}'
                  sh 'echo DEBUG_FLAGS value in stage-1: ${DEBUG_FLAGS}'
              }
          }
          stage('Example2') {
              steps {
                  sh 'echo CC value in stage-2: ${CC}'
                  sh 'echo DEBUG_FLAGS value in stage-2: ${DEBUG_FLAGS}'
              }
          }
      }
  }

-------------------------------------------

    pipeline {
        agent any
        environment{
            myVar = "V2DevOpsOnline"
        }
        stages {
            stage('Example') {
                steps {
                    echo "Running ${env.BUILD_ID} on ${env.JENKINS_URL} - ${myVar} - ${env.myVar}"


                    sh'echo Single quote Single line: Running ${BUILD_ID} on ${JENKINS_URL} - ${myVar} '

                    sh"echo Double quote Single line:Running ${BUILD_ID} on ${JENKINS_URL} - ${myVar} - ${env.myVar} "

                    sh'''
                        echo Single quote Multi line: Running ${BUILD_ID} on ${JENKINS_URL} - ${myVar}
                    '''

                    sh"""
                        echo Double quote Multi line: Running ${BUILD_ID} on ${JENKINS_URL} - ${myVar} - ${env.myVar} 
                    """
                }
            }
        }
    }
