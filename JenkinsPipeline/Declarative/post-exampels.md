
    pipeline {
        // no agent required to run here. All steps run in flyweight executor on Master
        agent none

        stages {
            stage("foo") {
                steps {
                    echo "hello"
                }
            }
        }
        post {
        /*
         * These steps will run at the end of the pipeline based on the condition.
         * Post conditions run in order regardless of their place in pipeline
         * 1. always - always run
         * 2. changed - run if something changed from last run
         * 3. aborted, success, unstable or failure - depending on status
         */
            always {
                echo "I AM ALWAYS first"
            }
            changed {
                echo "CHANGED is run second"
            }
            aborted {
              echo "SUCCESS, FAILURE, UNSTABLE, or ABORTED are exclusive of each other"
            }
            success {
                echo "SUCCESS, FAILURE, UNSTABLE, or ABORTED runs last"
            }
            unstable {
              echo "SUCCESS, FAILURE, UNSTABLE, or ABORTED runs last"
            }
            failure {
                echo "SUCCESS, FAILURE, UNSTABLE, or ABORTED runs last"
            }
        }
    }

---------------------------

*post in stage*

    pipeline {
      agent any

      stages {
        stage("Hello") {
          steps {
            echo "hello"

            // Script blocks can run any Groovy script
            script {
              String res = env.MAKE_RESULT
              if (res != null) {
                echo "Setting build result ${res}"
                currentBuild.result = res
              } else {
                echo "All is well"
              }
            }
          }
          // Post in Stage executes at the end of Stage instead of end of Pipeline
          post {
            aborted {
              echo "Stage 'Hello' WAS ABORTED"
            }
            always {
              echo "Always: Stage 'Hello' finished"
            }
            changed {
              echo "Stage HAVE CHANGED"
            }
            failure {
              echo "Stage FAILED"
            }
            success {
              echo "Stage was Successful"
            }
            unstable {
              echo "Stage is Unstable"
            }
          }
        }
      }

      // All Stages and Pipeline can each have their own post section that is executed at different times
      post {
        always {
          echo "Pipeline is done"
        }
      }
    }

*post unstable*

    pipeline {
      agent none

      stages {
        stage("Hello") {
          steps {
            echo "hello"
            // need to use script block to assign value
            script {
              currentBuild.result = "UNSTABLE"
            }
          }
        }
      }
      post {
        always {
          echo "I ALWAYS run first"
        }
        unstable {
          echo "UNSTABLE runs after ALWAYS"
        }
      }
    }
