variable "matcher_cluster_name" {
  description = "Back cluster name."
  default     = "backend-cluster"
}

variable "matcher_container_name" {
  description = "Back container name."
  default     = "back-container"
}

variable "matcher_repository_name" {
  description = "Repository name"
  type        = string
}

variable "instance_type" {
  description = "AWS instance type"
  default     = "t3.micro"
}

variable "image_tag" {
  default = "latest"
}

variable "matcher_port" {
  description = "Port number on which back service is listening"
  default     = 8080
}

variable "subdomain_name" {
  description = "Subdomain name"
  type        = string
}

variable "domain_name" {
  description = "Domain name"
  type        = string
}

variable "region" {
  description = "AWS region to host your infrastructure"
  type        = string
}

variable "vpc" {
  default = "vpc-0032e90317069a534"
}

variable "target_group_name" {
  default = "http-ecs-back-tg"
}

variable "security_group_name" {
  description = "The name of the security group to use"
  type        = string
}

variable "aws_key_pair_name" {
  description = "The name of the key pair to use"
  type        = string
}

variable "aws_inst_profile_name" {
  description = "The name of the IAM instance profile to use"
  type        = string
}

variable "aws_iam_ex_role_name" {
  description = "The name of the IAM role to use"
  type        = string
}

variable "aws_iam_inst_role_name" {
  description = "The name of the IAM role to use"
  type        = string
}

variable "aws_ami_value_name" {
  description = "The name of the AMI to use"
  type        = string
}

variable "instance_name" {
  description = "The name of the instance to use"
  type        = string
}

variable "aws_ecs_capacity_provider_name" {
  description = "The name of the capacity provider to use"
  type        = string
}


variable "aws_ecs_service_name" {
  description = "The name of the service to use"
  type        = string
}
variable "aws_lb_name" {
  description = "The name of the load balancer to use"
  type        = string
}

variable "aws_ecs_task_definition_family" {
  description = "The name of the task definition to use"
  type        = string
}