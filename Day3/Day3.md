Maven - Day3 - topics need to be covered

        1. Different types of Jenkins job templates.
        
                1.1. Freestyle project.
                1.2. Maven project.
                1.3. Pipeline.
                1.4. Multibranch pipeline.
        
        2. Important feature of Jenkins job configuration.
                
                General Section:

                        2.1. This project is parameterized
                        2.2. Discard old builds.
                        2.3. Disable this project.
                        2.4. Use custom workspace.
                        2.5. Restrict where this jib can run. (slave selection).
                        2.6. Execute concurrent builds if necessary (JDK selection).
                        
                Source Code Management:
                
                        2.7. Git. (Repository URL, Credentials, Branch Specifier, Checkout to a sub-directpry, Checkout to specific local branch).
                        2.8. Subversion. 
                        2.9. Rational Team Concert (RTC).
                
                 Build Triggers:
                 
                        2.10. Build after other projects are built (for upstream job configuration).
                        2.11. Build periodically.
                        2.12. Poll SCM.
                        2.13. GitHub hook trigger for GITScm polling.
                        
                 Build Environment:
                        
                        Delete workspace before build starts
                   
                
        
        3. Jenkins + Maven + GitHub + Nexus examples.
        
                3.1. Freestyle job as to build & deploy the artifacts to nexus & tomcat.
                3.2. Maven job as to build & deploy & release the artifacts to nexus & tomcat.
                3.3. The same above scenario with pipeline script.
                3.4. Same above scenario with Blue ocean plugin.
                3.5. Maven release with manual steps. (without a maven release plugin & drawbacks and plus points with this).

        4. How to practice the covered topics in Day-3.
        

        5. Interview Questions:
           
           How to create a job in Jenkins? ((Click on NewItem--> explain all the configuration details)
           How to setup a CI job?
           How to setup a CD job?
           What is difference between Continuous Delivery & Continuous Deployment?
           Maven release steps?
           release:prepare & release:perfrom?
           What/why are <scm> tags in pom.xml?
           What is <distributedManagement> section in pom.xml file?
           Difference b/w SNAPSHOT & RELEASE versions?
           Freestyle job vs Maven job?
           Blue ocean vs pipleine script?
           What is multibranch pipleine & how to configure it?
