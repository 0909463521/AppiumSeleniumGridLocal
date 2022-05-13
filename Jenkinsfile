def gv
pipeline {
    agent any
    parameters {
        // string(name:'VERSION',defaultValue: '', description: 'version to deploy')
        choice(name: 'VERSION', choices: ['1.1.0','1.2.0','1.3.0'],description:'')
        booleanParam(name: 'excuteTests',defaultValue: true, description: '' )
    }
    environment {
        
        NEW_VERSION = '1.3.0'

    }
    stages {
        
        stage('Set up Grid') {
            
            parallel {
                stage('Hub') {
                    steps{
                        sh "java -jar ${WORKSPACE}/selenium-server-standalone-3.141.59.jar -role hub -hubConfig Hub.json"

                    }
                }
                stage('Node Browser') {
                    steps{
                        sleep 10

                        sh "java -jar -Dwebdriver.chrome.driver=${WORKSPACE}/chromedriver -Dwebdriver.gecko.driver=${WORKSPACE}/geckodriver ${WORKSPACE}/selenium-server-standalone-3.141.59.jar -role node -nodeConfig Node.json"

                    }
                }
                stage('Node Appium') {
                    steps{
                        sleep 10
                        // sh " appium -p 4725 --nodeconfig ${WORKSPACE}/Nodedevice1.json "

                    }
                }
                stage('Automation Test') {
                    steps{
                        sh "echo automation"
                        sleep 14

                        sh "cd ${WORKSPACE}/AppiumSeleniumGrid "
                        sh "mvn test -P BachVu"


                    }
                }
                

            }
            
        }
        
    }
   
}