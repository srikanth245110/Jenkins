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
----------------------------

    pipeline {
        agent any 
        environment {
            // Using returnStdout
            CC = """${sh(
                    returnStdout: true,
                    script: 'echo "clang"'
                )}""" 
            // Using returnStatus
            EXIT_STATUS = """${sh(
                    returnStatus: true,
                    script: 'exit 1'
                )}"""
        }
        stages {
            stage('Example') {
                environment {
                    DEBUG_FLAGS = '-g'
                }
                steps {
                    sh 'printenv'
                }
            }
        }
    }
--------------------------------

    pipeline {
        agent any 
        environment {
            MY_CREDS = credentials("v2devopsonline")
        }
        stages {
            stage('Example') {
                steps {
                    sh 'echo username: ${MY_CREDS_USR} and pwd: ${MY_CREDS_PSW}'
                    
                    //sh 'curl -u ${MY_CREDS_USR}:${MY_CREDS_PSW} http://www.something.com'
                }
            }
        }
    }
----------------------------------

    pipeline {
        agent {
            // Define agent details here
        }
        environment {
            // The MY_KUBECONFIG environment variable will be assigned
            // the value of a temporary file.  For example:
            //   /home/user/.jenkins/workspace/cred_test@tmp/secretFiles/546a5cf3-9b56-4165-a0fd-19e2afe6b31f/kubeconfig.txt
            MY_KUBECONFIG = credentials('my-kubeconfig')
        }
        stages {
            stage('Example stage 1') {
                steps {
                    sh("kubectl --kubeconfig $MY_KUBECONFIG get pods")
                }
            }
        }
    }
 -----------------------------
 
     pipeline {
        agent any
        environment {
            EXAMPLE_CREDS = credentials('example-credentials-id')
        }
        stages {
            stage('Example') {
                steps {
                    /* CORRECT */
                    sh('curl -u $EXAMPLE_CREDS_USR:$EXAMPLE_CREDS_PSW https://example.com/')
                }
            }
        }
    }
----------------------

    pipeline {
        agent any
        stages {
            stage('Example Username/Password') {
                environment {
                    SERVICE_CREDS = credentials('my-predefined-username-password')
                }
                steps {
                    sh 'echo "Service user is $SERVICE_CREDS_USR"'
                    sh 'echo "Service password is $SERVICE_CREDS_PSW"'
                    sh 'curl -u $SERVICE_CREDS https://myservice.example.com'
                }
            }
            stage('Example SSH Username with private key') {
                environment {
                    SSH_CREDS = credentials('my-predefined-ssh-creds')
                }
                steps {
                    sh 'echo "SSH private key is located at $SSH_CREDS"'
                    sh 'echo "SSH user is $SSH_CREDS_USR"'
                    sh 'echo "SSH passphrase is $SSH_CREDS_PSW"'
                }
            }
        }
    }
----------------------------

    pipeline {
      environment {
        // environment variables and credential retrieval can be interspersed
        SOME_VAR = "SOME VALUE"
        // this assumes that "cred1" has been created on Jenkins Credentials
        CRED1 = credentials("cred1")
        INBETWEEN = "Something in between"
        // this assumes that "cred2" has been created in Jenkins Credentials
        CRED2 = credentials("cred2")
        // Env variables can refer to other variables as well
        OTHER_VAR = "${SOME_VAR}"
      }

      agent any

      stages {
        stage("foo") {
          steps {
            // environment variables are not masked
            sh 'echo "SOME_VAR is $SOME_VAR"'
            sh 'echo "INBETWEEN is $INBETWEEN"'
            sh 'echo "OTHER_VAR is $OTHER_VAR"'

            // credential variables will be masked in console log but not in archived file
            sh 'echo $CRED1 > cred1.txt'
            sh 'echo $CRED2 > cred2.txt'
            archive "**/*.txt"
          }
        }
      }
    }
    
----------------------------

    pipeline {
        environment {
          /*
           * Uses a Jenkins credential called "FOOCredentials" and creates environment variables:
           * "$FOO" will contain string "USR:PSW"
           * "$FOO_USR" will contain string for Username
           * "$FOO_PSW" will contain string for Password
           */
          FOO = credentials("FOOcredentials")
        }

        agent any

        stages {
            stage("foo") {
                steps {
                    // all credential values are available for use but will be masked in console log
                    sh 'echo "FOO is $FOO"'
                    sh 'echo "FOO_USR is $FOO_USR"'
                    sh 'echo "FOO_PSW is $FOO_PSW"'

                    //Write to file
                    dir("combined") {
                        sh 'echo $FOO > foo.txt'
                    }
                    sh 'echo $FOO_PSW > foo_psw.txt'
                    sh 'echo $FOO_USR > foo_usr.txt'
                    archive "**/*.txt"
                }
            }
        }
    }

----------------------------

