**pipeline script - agent examples**

	agent {
		node {
			label 'my-defined-label'
			customWorkspace '/some/other/path'
		}
	} 
	
	--------------------------
	
	pipeline{
	    agent {

		label 'master'

	    }

	    stages{
		stage('Build'){
		    agent {

			label 'build'

		    }
		    steps{
			println "I am from build stage"

			sh'''
			    echo sample-build > build.txt
		       '''
		    }
		}
		stage('Deploy'){
		    agent {

			label 'deploy'

		    }
		    steps{
			println "I am from deploy stage"

			sh'''
			    echo sample-deploy > deploy.txt
		       '''
		    }
		}
	    }
	}

	----------------------------
	
	or
	agent { docker 'maven:3-alpine' } //install 2 plugins: Docker plugin and Docker Pipeline and sudo chmod 666 /var/run/docker.sock
	or
	agent {
		docker {
			image 'maven:3-alpine'
			label 'my-defined-label'
			args  '-v /tmp:/tmp'
		}
	}
	or
	agent {
		docker {
			image 'myregistry.com/node'
			label 'my-defined-label'
			registryUrl 'https://myregistry.com/'
			registryCredentialsId 'myPredefinedCredentialsInJenkins'
		}
	}
	or
	agent {
		// Equivalent to "docker build -f Dockerfile.build --build-arg version=1.0.2 ./build/
		dockerfile {
			filename 'Dockerfile.build'
			dir 'build'
			label 'my-defined-label'
			additionalBuildArgs  '--build-arg version=1.0.2'
			args '-v /tmp:/tmp'
			registryUrl 'https://myregistry.com/'
			registryCredentialsId 'myPredefinedCredentialsInJenkins'
		}
	}
	or
	agent {
		kubernetes { / kuberneets plugin and kuberneets pipeline
			label podlabel
			yaml """
			"""
		}
	}

----------------------------------

	pipeline {
	  agent {
	    dockerfile { //make sure install docker plugin and docker pipeline plugins
	      /*
		* The Default is "Dockerfile" but this can be changed.
		* This will build a new container based on the contents of "Dockerfile.alternate"
		* and run the pipline inside this container
		*/
	      filename "Dockerfile.alternate"
	      args "-v /tmp:/tmp -p 8000:8000"
	    }
	  }
	  stages {
	    stage("foo") {
	      steps {
		sh 'cat /hi-there'
		sh 'echo "The answer is 42"'
	      }
	    }
	  }
	}
	
-------------------------

	def createDoc(){
	    writeFile file: 'Dockerfile', text: 'FROM ubuntu:latest'
	}

	pipeline {
	  agent any
	  stages {
	    stage('create dockerfile'){
		steps{
		    script{
			if(!fileExists('Dockerfile')){
			    createDoc()
			}
			sh'''
			    mkdir -p /var/lib/jenkins/workspace/pipelinejob@2
			    cp Dockerfile /var/lib/jenkins/workspace/pipelinejob@2/
			'''
		    }
		}
	    }
	    stage("foo") {
		agent {
		    dockerfile {
		      /*
			* The Default is "Dockerfile" but this can be changed.
			* This will build a new container based on the contents of "Dockerfile.alternate"
			* and run the pipline inside this container
			*/
		      //filename "Dockerfile.alternate"

		      args "-v /tmp:/tmp -p 8000:8000"
		    }
		}
	      steps {
		sh 'echo "The answer is 42"'
	      }
	    }
	  }
	}
