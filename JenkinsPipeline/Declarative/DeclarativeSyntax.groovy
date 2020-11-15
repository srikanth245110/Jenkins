//declarative pipeline syntax
//Jenkinsfile (Declarative Pipeline)
//https://www.jenkins.io/doc/book/pipeline/syntax/
pipeline { 
    agent any or agent { label 'my-defined-label' } or agent { label 'my-label1 && my-label2' } or agent { label 'my-label1 || my-label2' } or agent { node { label 'labelName' } } or 
	agent {
		node {
			label 'my-defined-label'
			customWorkspace '/some/other/path'
		}
	} 
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
	
    options {
        skipStagesAfterUnstable()
    }
	
	parameters{
	}
	
	environment{
	}
	
	tools{
	}

    stages {
        stage('Build') {
			when { }
			options { }
			environment{ }
			matrix { }
			agent { }
			input{ }
			input { 
				parameters { }
			}
            steps { 
                sh '----'
				sh'''
					----
				'''
				sh"""
					=====
				"""
				def output=sh(returnStdout: true, script: "git log -n 1 --pretty=format:'%h'").trim()
				script{ }
            }
        }
        stage('Test'){
            steps {
                sh '-----'
				bat '----'
                junit 'reports/**/*.xml' 
            }
        }
        stage('Deploy') {
            steps {
                sh 'make publish'
            }
        }
    }
	post {
		always{ }
		success { }
		failure { }
		aborted { }
		unstable { }
	}
}
