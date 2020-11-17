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

    pipeline {
      agent any

      environment {
        // FOO will be available in entire pipeline
        FOO = "PIPELINE"
      }

      stages {
        stage("local") {
          environment {
            // BAR will only be available in this stage
            BAR = "STAGE"
          }
          steps {
            sh 'echo "FOO is $FOO and BAR is $BAR"'
          }
        }
        stage("global") {
          steps {
            sh 'echo "FOO is $FOO and BAR is $BAR"'
          }
        }
      }
    }
    
----------------------------

    pipeline {
      /*
       * The environment section is used for setting environment variables and will allow for
       * expanding variable and other methods to set those values as long as the return type is a String.
       * String escaping in Groovy can affect the behavior here. Please refer here for detailed explainations
       * http://docs.groovy-lang.org/latest/html/documentation/#all-strings
       * The 'readMavenPom()' method is provided by the Pipeline Utility Steps plugin
       */
      environment {
        FOO = "BAR"
        BUILD_NUM_ENV = currentBuild.getNumber()
        ANOTHER_ENV = "${currentBuild.getNumber()}"
        INHERITED_ENV = "\${BUILD_NUM_ENV} is inherited"
        ACME_FUNC = readMavenPom().getArtifactId()
      }

      agent any

      stages {
        stage("Environment") {
          steps {
            sh 'echo "FOO is $FOO"'
            // returns 'FOO is BAR'

            sh 'echo "BUILD_NUM_ENV is $BUILD_NUM_ENV"'
            // returns 'BUILD_NUM_ENV is 4' depending on the build number

            sh 'echo "ANOTHER_ENV is $ANOTHER_ENV"'
            // returns 'ANOTHER_ENV is 4' like the previous depending on the build number

            sh 'echo "INHERITED_ENV is $INHERITED_ENV"'
            // returns 'INHERITED_ENV is ${BUILD_NUM_ENV} is inherited'
            // The \ escapes the $ so the variable is not expanded but becomes a literal

            sh 'echo "ACME_FUNC is $ACME_FUNC"'
            // returns 'ACME_FUNC is spring-petclinic' or the name of the artifact in the pom.xml
          }
        }
      }
    }
    
----------------------------

    pipeline {
      agent {
        // executes on an executor with the label 'some-label' or 'docker'
        label "some-label || docker"
      }

      stages {
        stage("foo") {
          steps {
            // variable assignment (other than environment variables) can only be done in a script block
            // complex global variables (with properties or methods) can only be run in a script block
            // env variables can also be set within a script block
            script {
              foo = docker.image('ubuntu')
              env.bar = "${foo.imageName()}"
              echo "foo: ${foo.imageName()}"
            }
          }
        }
        stage("bar") {
          steps{
            echo "bar: ${env.bar}"
            echo "foo: ${foo.imageName()}"
          }
        }
      }
    }
    
    
----------------------------

