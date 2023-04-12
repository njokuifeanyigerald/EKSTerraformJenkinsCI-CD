pipeline{
    agent any
    stages{
        stage("Git checkout"){
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