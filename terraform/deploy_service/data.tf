data "aws_region" "current_region" {}

data "aws_caller_identity" "current_user" {}

data "aws_availability_zones" "availability_zones" {}

data "aws_security_group" "vpc_matcher_security_group" {
  name = var.security_group_name
}

data "aws_key_pair" "keypair" {
  key_name = var.aws_key_pair_name
}

data "aws_iam_instance_profile" "aws_iam_instance_profile_matcher" {
  name = var.aws_inst_profile_name
}

data "aws_vpcs" "all_vpcs" {}


data "aws_subnets" "default_subnets" {
  filter {
    name   = "vpc-id"
    values = [var.vpc]
  }


  filter {
    name = "tag:Name"
    values = [
      "Default subnet for eu-north-1b", "Default subnet for eu-north-1a",
      "Default subnet for eu-north-1c"
    ]
  }

}

data "aws_iam_role" "ecs_task_execution_role_arn" {
  name = var.aws_iam_ex_role_name
}

data "aws_iam_role" "ecs_instance_role" {
  name = var.aws_iam_inst_role_name
}

data "aws_ami" "aws_linux_latest_ecs" {
  most_recent = true
  owners      = ["amazon"]
  filter {
    name   = "name"
    values = [var.aws_ami_value_name]
  }
}

data "aws_instances" "filtered_instances" {
  filter {
    name   = "tag:Name"
    values = [var.instance_name]
  }
}

data "aws_instance" "filtered_instance_details" {
  for_each    = toset(data.aws_instances.filtered_instances.ids)
  instance_id = each.value
}

data "aws_lb" "lb" {
  name       = aws_lb.matcher_ecs_alb.name
  depends_on = [aws_lb.matcher_ecs_alb]
}

data "aws_route53_zone" "dns_back_zone" {
  name = var.domain_name
}

data "aws_acm_certificate" "domain_cert" {
  domain   = var.domain_name
  statuses = ["ISSUED"]
  tags = {
    "Name" = var.domain_name
  }
}
