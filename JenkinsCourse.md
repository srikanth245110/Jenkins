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
		Create an instance - Ubuntu
		Install docker on Ubuntu server
			sudo apt-get update
			sudo apt-get install docker.io -y
			sudo docker version
			
			Update the file with below line : /lib/systemd/system/docker.service Search for ExecStart and replace with below line
			ExecStart=/usr/bin/dockerd -H tcp://0.0.0.0:4243 -H unix:///var/run/docker.sock
			
			sudo systemctl daemon-reload
			sudo service docker restart
			
			curl http://localhost:4243/version
			curl http://{publicIP}:4243/version
			
			Install Dokcer plugin on Jenkins
			
			Manage Jenkins –> Manage Nodes and Clouds -> Select Docker 
			
			-> Enter "Docker Cloud Details"
				Docker Host URI "tcp://{privateIP}:4243"
				Enabled
				Expose DOCKER_HOST
			-> Enter "Docker Agent templates"
				Labels = "anyName"
				Enabled
				Docker Image = bibinwilson/jenkins-slave:latest
				Remote File System Root = /home/jenkins (user direcotry) or give different path which is accessible
				Connect method = Connect with SSH 
						SSH Key = Use Configured Credentials (add uname 'jenkins' & 'password')
					
			
			
			
	configure a cloud(kubernetes) to create an agent

**Manage Jenkins**:
