//declarative pipeline syntax
//Jenkinsfile (Declarative Pipeline)
//https://www.jenkins.io/doc/book/pipeline/syntax/
pipeline { 
	agent any or agent none or agent { }
	
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
			input{ 
				parameters { }
			}
			
			steps{ 
				milestone()
				timeout(10) { }
				dir() { }
				deleteDir()
				checkout scm
				println "--"
				sh('')
				sh '----'
				sh "----"
				sh'''
				    ----
				'''
				sh"""
				    =====
				"""
				def output=sh(returnStdout: true, script: "git log -n 1 --pretty=format:'%h'").trim()
				script{ }
				withCredentials(){ }
		    	}
			//https://www.jenkins.io/doc/pipeline/steps/email-ext/
			emailext body:'--' subject:'--' from:'--' to:'--' from:'--'
			email body:'--' subject:'--' from:'--' to:'--' from:'--'
			publishHTML 
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
