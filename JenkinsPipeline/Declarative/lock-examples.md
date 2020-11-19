
    node('master'){
      stage('Build') {

          lock('myResource') {
            echo "locked build"
            sleep 60
          }
      }
    }
