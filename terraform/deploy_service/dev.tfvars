region = "eu-north-1"

domain_name = "skillzzy.com"

subdomain_name = "matcher-dev.skillzzy.com"

vpc = "vpc-0032e90317069a534"

instance_type = "t3.micro"

security_group_name = "Security_group_for_matcher_project"

aws_key_pair_name = "terraform_ec2_matcher_key_pair"

aws_inst_profile_name = "ecs-instance-profile-matcher"

aws_iam_ex_role_name = "ecs-ex-role-matcher"

aws_iam_inst_role_name = "ecs-inst-role-matcher"

aws_ami_value_name = "amzn2-ami-ecs-kernel-5.10-hvm-2.0.20240712-x86_64-ebs"

instance_name = "Ecs-Matcher-Instance-ASG"

aws_ecs_capacity_provider_name = "matcher-ec2-capacity-provider"

aws_ecs_service_name = "matcher-service"

aws_lb_name = "ecs-matcher-alb"

matcher_repository_name = "matcher-repository"

aws_ecs_task_definition_family = "matcher-td"

matcher_cluster_name = "matcher-cluster"

matcher_container_name = "matcher-container"

matched_participant_name = "matchedParticipantDev"

participant_queue_name = "participantQueueDev"
