
##### Syntax

	timeout(time: 2, unit: 'DAYS') {
		input message: 'Deploy this build to SYST?'
		milestone()
	}


	timeout(time: 3, unit: "SECONDS") // under options block

------------------------

	pipeline {
	    agent any
	    options {
		timeout(time: 1, unit: 'MINUTES')
	    }
	    stages {
		stage('Example') {

		    steps {
			echo 'Hello World'
			
			sleep 70
		    }
		}
	    }
	}

------------------------

	pipeline{
	    agent any

	    stages{
		stage('Build'){
		    agent {

			label 'build'

		    }

		    steps{

			    println "I am from build stage"

			    sh'''
				echo sample-build > build.txt

				#sleep 60
			   '''
		    }
		}
		stage('Deploy'){
		    agent {

			label 'build'

		    }
		    steps{
			timeout(time: 15, unit: "SECONDS"){
			    input message: 'Shall I proceed with deployment?'

			    println "I am from deploy stage"

			    sh'''
				echo sample-deploy > deploy.txt
			   '''
			}   
		    }
		}
	    }
	}
