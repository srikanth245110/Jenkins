

		pipeline {
		  agent none
		  stages {
			stage("foo") {

			  agent any

			  /*
			   * Tools configured in Jenkins can be included at the Pipeline level or at the Stage Level.
			   * This allows you to use different tools on different agents.
			   * In this case the pipeline does not have a agent assigned, 'agent none', but
			   * the stage does have an agent, 'agent any'. If I use a different agent in a
			   * subsequent stage I can use a different version of the tool or different tools.
			   */
			  tools {
				maven "apache-maven-3.0.1"
			  }
			  steps {
				echo "hello"
			  }
			}
		  }
		}

-----------------

		pipeline {
		  // use the 'tools' section to use specific tool versions already defined in Jenkins config 
		  tools {
			maven "apache-maven-3.1.0"
			jdk "default"
		  }

		  // run on any available agent
		  agent any

		  stages {
			stage("build") {
			  steps {
				// create a directory called "tmp" and cd into that directory
				dir("tmp") {
				  // use git to retrieve the plugin-pom
				  git changelog: false, poll: false, url: 'git://github.com/jenkinsci/plugin-pom.git', branch: 'master'
				  sh 'echo "M2_HOME: ${M2_HOME}"'
				  sh 'echo "JAVA_HOME: ${JAVA_HOME}"'
				  sh 'mvn clean verify -Dmaven.test.failure.ignore=true'
				}
			  }
			}
		  }

		  post {
			always {
			  // always archive the target when stages are done
			  archive "target/**/*"
			}
		  }
		}

-----------------


