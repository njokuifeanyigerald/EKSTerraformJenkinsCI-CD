def dockerbuild(String project, String ImageTag, String hubUser){
    sh """
        docker image build -t ${hubUser}/${project} .
        docker image tag ${hubUser}/${project} ${hubUser}/${project}:${ImageTag}
        docker image tag ${hubUser}/${project} ${hubUser}/${project}:latest
       """
}

def dockerScan(String project, String ImageTag, String hubUser){
    sh """
        trivy image ${hubUser}/${project}:latest > scan.txt
        cat scan.txt
       """
}

def dockerPush(String project, String ImageTag, String hubUser){
    withCredentials([usernamePassword(
        credentialsId: 'dockerhub_cred', 
        passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh " docker login  -u '$USER' -p '$PASS' "
    }
    sh """
        docker image build -t ${hubUser}/${project} .
        docker image push  ${hubUser}/${project}:${ImageTag}
        docker image push ${hubUser}/${project}:latest
       """   
}
def dockerImageRemove(String project, String ImageTag, String hubUser){
    sh """
        docker  rmi ${hubUser}/${project}:latest 
        docker rmi ${hubUser}/${project}:${ImageTag}
       """
}



// FOR AWS ECR


return this