
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

#### Example-4: Build on any available node (below script is compatible to windows only - but make sure java, maven, git is already installed on jenkins windows node). Configure git path on windows as shown below - Manage Jenkins >> Global Tool Configuration >> Add git and the path (you can ignore the red color errors)


![image](https://user-images.githubusercontent.com/24622526/131625287-a5b6c2e3-368d-4f0c-b919-3a19d2bc3db6.png)


Generate Pipeline script

![image](https://user-images.githubusercontent.com/24622526/131627100-3deca31d-76da-45c9-8e48-66d968dfaa64.png)

![image](https://user-images.githubusercontent.com/24622526/131627181-e4f1cb7d-43f5-4c9d-b464-9d33a284ae15.png)


          node("maven-windows"){

              stage("clone the code"){

                println "git cloning the code"

                //git branch: 'web', credentialsId: 'jengitub', url: 'https://github.com/venkatasykam/DevOpsWebApp.git'

                checkout([$class: 'GitSCM', branches: [[name: '*/web']], extensions: [], gitTool: 'Git_Windows', userRemoteConfigs: [[credentialsId: 'jengitub', url: 'https://github.com/venkatasykam/DevOpsWebApp.git']]])

                println "git cloning completed"

              }

              stage("maven build"){

                println "maven build"

                println "build version(in pipeline script): ${params.releaseVersion}"

                bat 'java -version'

                bat ' "C:\\ProgramData\\chocolatey\\lib\\maven\\apache-maven-3.8.2\\bin\\mvn" clean install -DreleaseVersion=%releaseVersion% '

              }

            }



