
aws_eks_cluster_config = {

      "demo-cluster" = {

        eks_cluster_name         = "demo-cluster1"
        eks_subnet_ids = ["subnet-0cf8b7fce1224afe3","subnet-01e46b1c2971b4755","subnet-0bc7a786f49cfb1a6"]
        tags = {
             "Name" =  "demo-cluster"
         }  
      }
}

eks_node_group_config = {

  "node1" = {

        eks_cluster_name         = "demo-cluster"
        node_group_name          = "mynode"
        nodes_iam_role           = "eks-node-group-general1"
        node_subnet_ids          = ["subnet-0cf8b7fce1224afe3","subnet-01e46b1c2971b4755","subnet-0bc7a786f49cfb1a6"]

        tags = {
             "Name" =  "node1"
         } 
  }
}