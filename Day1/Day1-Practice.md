# Jenkins Setup

      1. Go to Phase-1 docs & setup Jenkins.
      2. Practice the simple example which is covered in Phase-1.
      3. Setup a job in Jenkins as to: Code checkout --> Build(compile-->test-->package-->deploy to Nexus) --> deploy to tomcat. (as guided in Phase-1 docs)
      4. Schedule the job to run everyday morning 6am & evening 6pm. (Build Periodical)
      5. Schedule the job to run for every check-in. (GitHub Push)
      6. Install the plugin "Nested view plugin" (Manage Jenkins --> Manage Plugins --> Available --> search for this and install).
      7. Setup a pipeline: Job1 --> Job2 --> Job3.
      
      
      
      
## What you learned

      1. Installation of Jenkins.
      2. Jenkins job configuration with Git URL to checkout the code.
      3. Jenkins job configuration with maven goals to build the code.
      4. Jenkins job configuration to deploy the package to Tomcat.
      5. settings.xml file configuration with Nexus server credentials.
      6. pom.xml file configuration with Nexus repositories details.
      7. Deploy the Package to Nexus.
      8. Downstream & Upstream job setup i.e., pipeline jobs setups.
      9. Installation of plugin in two ways.
      
      
      
