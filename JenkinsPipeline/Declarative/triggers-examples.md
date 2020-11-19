Refer: https://www.jenkins.io/doc/book/pipeline/syntax/#triggers

    pipeline{
        agent any
        triggers { cron('01 16 * * 1-5') }
        stages{
            stage('Triggers demo'){
                steps{
                    println "Triggers - build periodical demo"
                }
            }
        }
    }
