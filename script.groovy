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

return this