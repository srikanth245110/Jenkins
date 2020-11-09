*Jenkins concepts*:

**Jenkins installation**: https://www.jenkins.io/doc/book/installing/

	container (docker)
	container (k8s) - pending
	war deployment to tomcat
	run as service (on Linux/Unix or windows) - https://www.jenkins.io/doc/book/installing/linux/#debianubuntu
		/var/lib/jenkins >> Jenkins Home
		sudo cat /var/lib/jenkins/secrets/initialAdminPassword >> initial admin password for the user admin
		/etc/init.d/jenkins  >> Setup Jenkins as a daemon launched on start
		/var/log/jenkins/jenkins.log >> console log output to the file

**Jenkins node setup (windows, linux)**

	Create an agent (manual)
	configure a cloud(docker) to create an agent - https://devopscube.com/docker-containers-as-build-slaves-jenkins/
			
			
			
	configure a cloud(kubernetes) to create an agent

**Manage Jenkins**:
