#### Example scripted pipeline:

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
