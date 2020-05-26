pipeline {
   
   agent {label 'slave1'}
   
   environment {
       PROJECT_NAME="JSOAGGER Cloud Bridge"
   }
   
   options {
        timestamps()
    }
   
   stages {
        stage('Build') {
            steps {
            	sh '''
					mvn -version
					mvn -Dmaven.test.failure.ignore=true -DskipTests=false -Dmaven.javadoc.skip=true clean install
                '''
            }
        }
        
        stage('Integration Tests') {
            steps {
                sh '''
                	echo "Running integration tests"
                	#mvn -Dmaven.test.failure.ignore=false -Dmaven.javadoc.skip=true verify
                '''
            }
        }
        
      	stage('Release confirmation') {
        	steps {
        		timeout(time: 600, unit: 'SECONDS'){
        			script {
	                    def perfomRelease = input(
 							id: 'perfomRelease', message: 'Do you want to release?', ok:'Release this build' 
						)
                	}	
        		}
        	}
      	}
      
      	stage('Perform release') {
         	steps {
	         	withCredentials([usernamePassword(credentialsId: 'jenkins_github_credentials', passwordVariable: 'GIT_PASSWORD', usernameVariable: 'GIT_USERNAME')])
	         	{
	                sh 'mvn --settings .maven.xml -DENV_GIT_USERNAME=$GIT_USERNAME -DENV_GIT_PASSWORD=$GIT_PASSWORD -Dresume=false -DdryRun=true -Dmaven.test.failure.ignore=true -DskipTests=true -Darguments=\"-Dmaven.javadoc.skip=true\" release:prepare -B -V -Prelease'
			        sh 'mvn --settings .maven.xml -DENV_GIT_USERNAME=$GIT_USERNAME -DENV_GIT_PASSWORD=$GIT_PASSWORD -Dresume=false -Dmaven.test.failure.ignore=true -DskipTests=true -Darguments=\"-Dmaven.javadoc.skip=true\" -B -V release:prepare release:perform -Prelease'
	         	}
         	}
         	
         	post {  
				 success {  
					 emailext   to: "${env.DEV_MAILING_LIST}",
					 			subject: "$PROJECT_NAME, released",
					 			body: "$PROJECT_NAME have been succesfully released.<br/> A new version of project $PROJECT_NAME is now avalaible.<br/><br/>Jenkins", 
								from: "${env.JOB_EMAIL_SENDER}", 
								attachLog: false;
				 }  
				 failure {
					emailext    to: "${env.DEV_MAILING_LIST}",
								subject: "$PROJECT_NAME, RELEASE Failed",
								body: "$PROJECT_NAME, RELEASE failed. <br/> You can check jenkins console output at $BUILD_URL to view full the results.<br/><br/>Jenkins", 
								from: "${env.JOB_EMAIL_SENDER}", 
								attachLog: true;
				}  
			}
      	}
    }
    
    post {  
     	failure {
        	emailext	to: "${env.DEV_MAILING_LIST}",    
        				subject: "$PROJECT_NAME - Build Failed",
        				body: "$PROJECT_NAME, build failed. <br/> You can check jenkins console output at $BUILD_URL to view full the results.<br/><br/>Jenkins", 
                    	from: "${env.JOB_EMAIL_SENDER}", 
                    	attachLog: true;
     	}  
	}
}
