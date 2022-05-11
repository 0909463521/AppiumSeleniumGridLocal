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
        
        stage('Test') {
            steps {
                 script {
                    gv = load "script.groovy"

                    gv.deployApp()
                    gv.CopyAppToAutomation()
                    gv.deployAutomationAppium()
                    
                }
            }
            
        }
        stage('Staging') {
            steps {
                 script {
                    gv = load "script.groovy"

                    gv.deployApp()
                    gv.CopyAppToAutomation()
                    gv.deployAutomationAppium()
                    
                }
            }
            
        }
    }
   
}