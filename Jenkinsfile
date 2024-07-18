pipeline{

  agent any
  
  tools{
    maven "MAVEN3"
	  jdk   "OracleJDK11"
  
   }

   environment {
        DOCKER_IMAGE = 'vayuputra123/amazonservice'
        DOCKER_CREDENTIALS_ID = 'DockerHubCreds'
	BUILD_NUMBER = "${env.BUILD_NUMBER}"
    }
  
  stages{

   

    stage('Get code from Git'){
       steps{
           
           git branch: 'main', url: 'https://github.com/Pavantalari33881186429/AmazonService.git'
       }
    }
    
    stage('Maven Build Code'){
      steps{
         sh 'mvn clean install -DskipTests'
      }
      post{
	      success{
		     echo 'Archiving artifact'
		    archiveArtifacts artifacts: '**/*.jar'
		    }
	    }
    }

  

    stage('Docker Build') {
            steps {
                script {
                    // Build Docker image
                    docker.build("${DOCKER_IMAGE}:V${env.BUILD_NUMBER}")
	            docker.build("${DOCKER_IMAGE}:latest")
                }
            }
        }
    
    stage(' Code check '){
      steps{
        sh 'mvn  checkstyle:checkstyle'
      }
    post {
        success {
          echo 'Generated Analysis Result'
                }
      }
    }

    stage('CODE ANALYSIS with SONARQUBE') {
          
		  environment {
             scannerHome = tool 'sonarscanner4'
          }

          steps {
            withSonarQubeEnv('sonar') {
               sh '''${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=AmazonService \
                   -Dsonar.projectName=AmazonService \
                   -Dsonar.projectVersion=1.0 \
                   -Dsonar.sources=src/main/java \
                   -Dsonar.java.binaries=target/classes \
                   -Dsonar.java.checkstyle.reportPaths=target/checkstyle-result.xml'''
            }

            
          }
        }

      stage("Quality Gate") {
            steps {
                timeout(time: 1, unit: 'HOURS') {
                    // Parameter indicates whether to set pipeline to UNSTABLE if Quality Gate fails
                    // true = set pipeline to UNSTABLE, false = don't
                    waitForQualityGate abortPipeline: true
                }
            }
        }

    stage('Docker Login') {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: "${DOCKER_CREDENTIALS_ID}", usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
                        sh 'echo $DOCKER_PASSWORD | docker login -u $DOCKER_USERNAME --password-stdin'
                    }
                }
            }
        }

    stage('Docker Push') {
            steps {
               sh 'docker push $DOCKER_IMAGE:V$BUILD_NUMBER'
	       sh 'docker push $DOCKER_IMAGE:latest'
            }
        }

	  stage('Docker Cleanup') {
            steps {
                script {
                    // Remove Docker image from local workspace
                    sh '''
                    docker rmi $DOCKER_IMAGE:V$BUILD_NUMBER
                    docker rmi $DOCKER_IMAGE:latest
                    '''
                }
            }
        }
         
    }
    }
  


