pipeline {
   agent any
   
   environment {
       PROJECT_NAME="JSOAGGER Cloud Bridge"
       BRANCH_NAME= "master"
   }
   
   options {
        timestamps()
    }
   
   stages {
        stage ('Prepare') {
			steps {
				sh '''
					echo "PATH = ${PATH}"
					echo "M2_HOME = ${M2_HOME}"
					echo "JAVA_HOME = ${JAVA_HOME}"					
				'''
			}
		}
       
        stage('Build') {
            steps {
                sh "mvn -Dmaven.test.failure.ignore=true -DskipTests=true -Dmaven.javadoc.skip=true install"
            }
        }
        
        stage('Unit Tests') {
            steps {
            	sh '''
                	echo "Running unit tests"
                	mvn -Dmaven.test.failure.ignore=false -Dmaven.javadoc.skip=true verify
                '''
            }
        }
        
        stage('Integration Tests') {
            steps {
            	echo "My branch is: ${env.BRANCH_NAME}"
                echo "My branch is: ${env.BRANCH_NAME}"
                
                sh '''
                	echo "Running integration tests"
                	#mvn -Dmaven.test.failure.ignore=false -Dmaven.javadoc.skip=true verify
                '''
            }
        }
        
        
        stage('Deploy') {
		    steps {
                sh '''
				    echo "Deloying snapshots is not very usefull"
				    echo "You can be just share artifact or do a local build instead"
				    #mvn clean deploy --settings .maven.xml -DskipTests=true -Dmaven.javadoc.skip=true -B -U -Prelease
			    '''
            } 
            post {  
                success {  
                    emailext   	body: "$PROJECT_NAME, build success.<br/> You can check jenkins console output at $BUILD_URL to view full the results.<br/><br/>Jenkins", 
                            	subject: "$PROJECT_NAME, build Success", 
                            	from: "${env.JOB_EMAIL_SENDER}", 
                            	to: "${env.DEV_MAILING_LIST}", 
                            	attachLog: false;
                }  
             	failure {
                	emailext    body: "$PROJECT_NAME, build failed. <br/> You can check jenkins console output at $BUILD_URL to view full the results.<br/><br/>Jenkins", 
                            	subject: "$PROJECT_NAME - build Failure", 
                            	from: "${env.JOB_EMAIL_SENDER}", 
                            	to: "${env.DEV_MAILING_LIST}", 
                            	attachLog: true;
             	}  
        	}
      	}
    
      	stage('Perform release?') {
      		when {
      			branch 'master'
      		}
      		
        	steps {
        		timeout(time: 600, unit: 'SECONDS'){
        			script {
	                    def perfomRelease = input(
 							id: 'perfomRelease', message: 'Do you want to perform version release?', ok:'Release this build' 
						)
                	}	
        		}
        	}
      	}
      
      	stage('Release') {
      		when {
      			branch 'master'
      		}
      		
         	steps {
	         	withCredentials([usernamePassword(credentialsId: 'jenkins_github_credentials', passwordVariable: 'GIT_PASSWORD', usernameVariable: 'GIT_USERNAME')])
	         	{
	                sh 'mvn --settings .maven.xml -DENV_GIT_USERNAME=$GIT_USERNAME -DENV_GIT_PASSWORD=$GIT_PASSWORD -Dresume=false -DdryRun=true -Dmaven.test.failure.ignore=true -DskipTests=true -Darguments=\"-Dmaven.javadoc.skip=true\" release:prepare -B -V -Prelease'
			        sh 'mvn --settings .maven.xml -DENV_GIT_USERNAME=$GIT_USERNAME -DENV_GIT_PASSWORD=$GIT_PASSWORD -Dresume=false -Dmaven.test.failure.ignore=true -DskipTests=true -Darguments=\"-Dmaven.javadoc.skip=true\" -B -V release:prepare release:perform -Prelease'
	         	}
         	}
         	
         	post {  
         		 always {
         		 	sh '''
         		 		echo "End of job"
		         	'''
         		 }
				 success {  
					 emailext   body: "$PROJECT_NAME have been succesfully released.<br/> A new version of project $PROJECT_NAME is now avalaible.<br/><br/>Jenkins", 
								subject: "$PROJECT_NAME, released", 
								from: "${env.JOB_EMAIL_SENDER}", 
								to: "${env.DEV_MAILING_LIST}", 
								attachLog: false;
				 }  
				 failure {
					emailext    body: "$PROJECT_NAME, RELEASE failed. <br/> You can check jenkins console output at $BUILD_URL to view full the results.<br/><br/>Jenkins", 
								subject: "$PROJECT_NAME, RELEASE Failure", 
								from: "${env.JOB_EMAIL_SENDER}", 
								to: "${env.DEV_MAILING_LIST}", 
								attachLog: true;
				}  
			}
      	}
    }
}
