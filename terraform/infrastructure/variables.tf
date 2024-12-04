variable "max_untagged_images" {
  description = "The maximum number of untagged images to retain in the repository."
  default     = 2
}

variable "region" {
  description = "AWS region to host your infrastructure"
  type        = string
}

variable "instance_type" {
  description = "AWS instance type"
  default     = "t3.micro"
}

variable "repository_name" {
  description = "Repository name"
  type = string
}

variable "list_of_ports" {
  description = "The list of ports the app will use for each other"
  default     = ["22", "80", "3000", "8080", "5432", "443"]
}

variable "cidr_blocks" {
  description = "The list of cidrs to use for each other"
  default     = ["0.0.0.0/0"]
}

variable "matcher_port" {
  description = "Port number on which back service is listening"
  default     = 8080
}

variable "s3_bucket_name" {
  description = "Name of the S3 bucket for the backend"
  type        = string
}

variable "aws_ami_value_name" {
  description = "The name of the AMI to use"
  type        = string
}

variable "aws_key_pair_name" {
  description = "The name of the key pair to use"
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

variable "aws_inst_profile_name" {
  description = "The name of the IAM instance profile to use"
  type        = string
}

variable "security_group_name" {
  description = "The name of the security group to use"
  type        = string
}