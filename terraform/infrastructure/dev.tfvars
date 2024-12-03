s3_bucket_name = "matcher-bucket-dev"

region = "eu-north-1"

instance_type = "t3.micro"

aws_ami_value_name = "amzn2-ami-ecs-kernel-5.10-hvm-2.0.20240712-x86_64-ebs"

aws_key_pair_name = "terraform_ec2_matcher_key_pair"

repository_name = "matcher-service"

aws_iam_ex_role_name = "ecs-ex-role-matcher"

aws_iam_inst_role_name = "ecs-inst-role-matcher"

aws_iam_inst_role_name = "ecs-instance-profile-matcher"

security_group_name = "Security_group_for_matcher_project"