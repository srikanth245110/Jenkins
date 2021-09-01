
#### Example scripted pipeline-1: Build on any available node.


    node{

      stage("checkout the code"){

        println "from build stage"

      }
      
    }

------

#### Example scripted pipeline-2: Build on any available node


    node("node name or lable"){

      stage("checkout the code"){

        println "from build stage"

      }
      stage("Build"){

        println "from build stage"

      }

      stage("sonar"){

        println "from sonar stage"
      }

      stage("deploy to nexus"){

        println "from deploy stage"
      }

      stage("deploy to server"){

        println "from deploy stage"
      }

    }

-----

#### Example-3:  Build on any available node (but below script compatible to Linux OS).

        node{

          stage("clone the code"){

            println "git cloning the code"

            git branch: 'web', credentialsId: 'jengitub', url: 'https://github.com/venkatasykam/DevOpsWebApp.git'

            println "git cloning completed"

          }

          stage("maven build"){

            println "maven build"

            println "build version(in pipeline script): ${params.releaseVersion}"

            sh'''

                echo "build version(inside the sh block): ${releaseVersion}"

                "/root/apache-maven-3.8.1/bin/mvn" clean install -DreleaseVersion=${releaseVersion}

                echo "====== Maven Build Successful============="

            '''

            //bat 'java -version'

            //bat 'mvn clean install'

          }

        }

------

#### Example-4: Build on any available node (below script is compatible to windows only - but make sure java, maven, git is already installed on jenkins windows node)

      node("maven-windows"){

          stage("clone the code"){

            println "git cloning the code"

            git branch: 'web', credentialsId: 'jengitub', url: 'https://github.com/venkatasykam/DevOpsWebApp.git'

            println "git cloning completed"

          }

          stage("maven build"){

            println "maven build"

            println "build version(in pipeline script): ${params.releaseVersion}"

            bat 'java -version'

            bat 'mvn clean install -DreleaseVersion=${releaseVersion}'

          }

        }


