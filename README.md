# Jenkins concepts  
### (not finalized yet)

**Jenkins installation**: https://www.jenkins.io/doc/book/installing/ 

	container (docker)
	container (k8s) - pending to prepare doc
	war deployment to tomcat
	run as service (on Linux/Unix or windows) - https://www.jenkins.io/doc/book/installing/linux/#debianubuntu (Or) https://github.com/DayToDayDevOpsCourse/JenkinsDayToDayCourse/blob/master/JenkinsSetup.md

**Jenkins node setup (windows, linux)**

	Create an agent (manual)
	configure a cloud(docker) to create an agent - https://devopscube.com/docker-containers-as-build-slaves-jenkins/
		Setup: https://github.com/DayToDayDevOpsCourse/JenkinsDayToDayCourse/blob/master/JenkinsSetup/JenkinsNodeDockerCloudSetup.md
	configure a cloud(kubernetes) to create an agent

**Manage Jenkins**:
	
	System Configuration
	Global Tools configuration
	
	
**Job Configuration**: Important features of Jenkins job configuration
	
	General Section:

                    2.1. This project is parameterized (String, boolean, File parameter, choice, etc)
                    2.2. Discard old builds.
                    2.3. Disable this project.
                    2.4. Use custom workspace.
                    2.5. Restrict where this job can run. (slave selection).
                    2.6. Execute concurrent builds if necessary - Job will create new workspace @2 and run the paralell build.
		    2.7. JDK selection. If we have multiple JDK paths confiured under "Global Tool Configuration", then we can run our Jenkins jobs on dofferent JDKs.
		    2.8. Description.
                    
            Source Code Management:
            
                    2.7. Git. (Repository URL, Credentials, Branch Specifier, Checkout to a sub-directpry, Checkout to specific local branch).
                    2.8. Subversion.  (if plugin already installed)
                    2.9. Rational Team Concert (RTC). (if plugin already installed)
            
             Build Triggers:
             
                    2.10. Build after other projects are built (for upstream job configuration).
                    2.11. Build periodically.
                    2.12. Poll SCM.
                    2.13. GitHub hook trigger for GITScm polling.
		    2.13. Trigger builds remotely (if you want to trigger the jobs from other Jenkins serevrs).
                    
             Build Environment:
                    
                    2.14. Delete workspace before build starts.
                    2.15. Mask passwords.
                    2.16. Maven release build (maven release plugin, its visible only for maven project).
		    Use secret text(s) or file(s)
		    Abort the build if it's stuck
		    Add timestamps to the Console Output
		    
             
             Pre-Steps: (maven project) Options are same as below "Build: for free style project".
             
             Build: for free style project.
                    2.16. Execute Groovy script.
                    2.17. Execute Windows batch command.
                    2.18. Execute shell.
                    2.19. Invoke Ant.
                    2.20. Invoke Gradle script.
                    2.21. Invoke top-level maven targets.
                    2.22. Use builders from another project.
               
              Build: for maven project.
                    
                    2.23. Maven Version.
                    2.24. Root POM.
                    2.25. Goals and options.
                    2.26. MAVEN_OPTS (Advanced..)
                    2.27. Use private Maven repository (Advanced..)
                    2.28. Use custom workspace - Settings, Global Settings files paths (Advanced..)                        
              
              Post-Steps: (maven project) Options are same as above section "Pre-Steps". Addition optiols are..
                    
                    2.29. Run only if build succeeds.
                    2.30. Run only if build succeeds or is unstable.
                    2.31. Run regardless of build result.
                
              Build Settings: (Maven Project)
                    
                    2.32. Email Nitification.
                    
              Post-build Actions: (for both maven & freestyle)
                    
                    2.33. Email Nitification. (only for freestyle)
                    2.34. Ediatble Email Notification.
                    2.35. Build other projects.
                    2.36. Groovy Postbuild.
                    2.37. Public HTML reports.
                    2.38. Record JaCoCo coverage report.
                    2.39. Delete workspace when build is done.
                    2.40. Public Junit test result report. (free style).
                    2.41. Trigger Parameterized builds on other projects.
                    2.42. SonarQube analysis with Maven.
