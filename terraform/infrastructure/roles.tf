locals {
  policies_for_ex = [
    "arn:aws:iam::aws:policy/AmazonECS_FullAccess",
    "arn:aws:iam::aws:policy/AmazonEC2ContainerRegistryFullAccess",
    "arn:aws:iam::aws:policy/CloudWatchFullAccess",
    "arn:aws:iam::aws:policy/AmazonS3FullAccess",
    "arn:aws:iam::aws:policy/ElasticLoadBalancingFullAccess",
    "arn:aws:iam::aws:policy/service-role/AmazonECSTaskExecutionRolePolicy",
  ]

  policies_for_inst = [
    "arn:aws:iam::aws:policy/service-role/AmazonEC2ContainerServiceforEC2Role",
    "arn:aws:iam::aws:policy/AmazonS3FullAccess",
    "arn:aws:iam::aws:policy/CloudWatchLogsFullAccess",
    "arn:aws:iam::aws:policy/AmazonRDSFullAccess",
    "arn:aws:iam::aws:policy/AutoScalingFullAccess",
  ]
}

resource "aws_iam_role" "ecs_ex_role_matcher" {
  name = var.aws_iam_ex_role_name
  assume_role_policy = jsonencode({
    "Version" : "2008-10-17",
    "Statement" : [
      {
        "Sid" : "",
        "Effect" : "Allow",
        "Principal" : {
          "Service" : "ecs-tasks.amazonaws.com"
        },
        "Action" : "sts:AssumeRole"
      }
    ]
  })
}

resource "aws_iam_role_policy_attachment" "ecs_policy_attachments_ex_matcher_role" {
  count = length(local.policies_for_ex)

  role       = aws_iam_role.ecs_ex_role_matcher.name
  policy_arn = local.policies_for_ex[count.index]
}

resource "aws_iam_role" "ecs_inst_role_matcher" {
  name = var.aws_iam_inst_role_name
  assume_role_policy = jsonencode({
    "Version" : "2008-10-17",
    "Statement" : [
      {
        "Sid" : "",
        "Effect" : "Allow",
        "Principal" : {
          "Service" : "ec2.amazonaws.com"
        },
        "Action" : "sts:AssumeRole"
      }
    ]
  })
}


resource "aws_iam_role_policy_attachment" "ecs_policy_attachments_inst_matcher_role" {
  count = length(local.policies_for_inst)

  role       = aws_iam_role.ecs_inst_role_matcher.name
  policy_arn = local.policies_for_inst[count.index]
}

resource "aws_iam_instance_profile" "instance_profile_matcher" {
  name = var.aws_inst_profile_name
  role = aws_iam_role.ecs_inst_role_matcher.name
}