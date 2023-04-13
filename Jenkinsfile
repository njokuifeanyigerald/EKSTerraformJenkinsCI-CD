pipeline{
    agent any

    parameters {
        choice(name: 'action', choices: 'create\ndelete', description: 'choose create or destroy')
    }

    stages{
        
        stage("Git checkout"){
            when{expression { params.action == 'create'} }
            steps{
                echo "========executing Git checkout========"
                git 'https://github.com/njokuifeanyigerald/EKSTerraformJenkinsCI-CD.git'
            }
            post{
                success{
                    echo "========A executed successfully========"
                }
                failure{
                    echo "========A execution failed========"
                }
            }
        }
        stage("mvn testing"){
            when{expression { params.action == 'create'} }
            steps{
                echo "====++++executing mvn testing++++===="
                sh 'mvn test'
            }
            post{
                success{
                    echo "====++++mvn testing executed successfully++++===="
                }
                failure{
                    echo "====++++mvn testing execution failed++++===="
                }
        
            }
        }
        stage("Integration testing"){
            when{expression { params.action == 'create'} }
            steps{
                echo "====++++executing Integration testing++++===="
                sh 'mvn verify -DskipUnitTests'
            }
            post{
                success{
                    echo "====++++Integration testing executed successfully++++===="
                }
                failure{
                    echo "====++++Integration testing execution failed++++===="
                }
        
            }
        }
        stage("Maven Build"){
            when{expression { params.action == 'create'} }
            steps{
                echo "====++++executing Maven Build++++===="
                sh 'mvn clean install '
            }
            post{
                success{
                    echo "====++++Maven Build executed successfully++++===="
                }
                failure{
                    echo "====++++Maven Build execution failed++++===="
                }
        
            }
        }
        stage("Static Code Analysis"){
            steps{
                echo "====++++executing Static Code Analysis++++===="
                script{
                    // withSonarQubeEnv(credentialsId: 'sonarQubeToken') {
                    //     // some block
                    //     sh 'mvn clean package sonar:sonar'
                    // }
                    withSonarQubeEnv(credentialsId: 'sonarQube') {
                        // some block
                        // sh 'mvn clean package sonar:sonar'
                        sh 'mvn sonar:sonar \
                        -Dsonar.projectKey=com.minikube.sample:kubernetes-configmap-reload \
                        -Dsonar.host.url=http://13.244.116.61:9000 \
                        -Dsonar.login=jenkins'
                    }
                }  
            }
            post{
                success{
                    echo "====++++Static Code Analysis executed successfully++++===="
                }
                failure{
                    echo "====++++Static Code Analysis execution failed++++===="
                }
        
            }
        }
    }
    post{
        success{
            echo "========pipeline executed successfully ========"
        }
        failure{
            echo "========pipeline execution failed========"
        }
    }

    
}