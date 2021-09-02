*Example-1*

    pipeline {
        
        agent any
        
        parameters {
            string(name: 'PERSON', defaultValue: 'Mr Jenkins', description: 'Who should I say hello to?')

            text(name: 'BIOGRAPHY', defaultValue: '', description: 'Enter some information about the person')

            booleanParam(name: 'TOGGLE', defaultValue: true, description: 'Toggle this value')

            choice(name: 'CHOICE', choices: ['One', 'Two', 'Three'], description: 'Pick something')

            password(name: 'PASSWORD', defaultValue: 'SECRET', description: 'Enter a password')
        }
        
        stages {
            stage('Example') {
                steps {
                    echo "Hello ${params.PERSON}"

                    echo "Biography: ${params.BIOGRAPHY}"

                    echo "Toggle: ${params.TOGGLE}"

                    echo "Choice: ${params.CHOICE}"

                    echo "Password: ${params.PASSWORD}"
                }
            }
        }
    }

-----

*Example-2*

    pipeline {
      agent any

      // This defines job parameters that are populated before job is run or default is used
      parameters {
        booleanParam(defaultValue: true, description: '', name: 'flag')
        string(defaultValue: 'V2DevOps', description: '', name: 'SOME_STRING')
      }

      // Triggers define how the job is triggered.
      // Jobs may still be triggered manually or by webhook as well here.
      triggers {
        cron('@daily')
      }

      // Options covers all other job properties or wrapper functions that apply to entire Pipeline.
      options {
        buildDiscarder(logRotator(numToKeepStr:'1'))
        disableConcurrentBuilds()
        skipDefaultCheckout(true)
        timeout(time: 5, unit: 'MINUTES')
        timestamps()
      }

      stages {
        stage("foo") {
          steps {
            echo "hello flag: ${params.flag} "
            
            println "SOME_STRING: ${params.SOME_STRING}"
            
            sh "echo double quote: ${params.SOME_STRING}"
            
            // not working sh 'echo single quote: ${params.SOME_STRING}'
            sh 'echo single quote: ${SOME_STRING}'
            
            sh'''
                # Not working echo single quote multiline: ${params.SOME_STRING}
                echo single quote multiline: ${SOME_STRING}
            '''
            
            sh"""
                echo double quote multiline: ${params.SOME_STRING}
            """
             
          }
        }
      }
    }
