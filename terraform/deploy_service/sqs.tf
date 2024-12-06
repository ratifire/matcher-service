resource "aws_sqs_queue" "matcher_queue" {
  name                       = "matcher-queue"
  visibility_timeout_seconds = 30
  delay_seconds              = 0
  message_retention_seconds  = 86400
}

resource "aws_iam_role" "sqs_access_role" {
  name = "SQSAccessRole"

  assume_role_policy = jsonencode({
    Version = "2012-10-17"
    Statement = [{
      Effect = "Allow"
      Principal = {
        Service = "ecs-tasks.amazonaws.com"
      }
      Action = "sts:AssumeRole"
    }]
  })
}

resource "aws_iam_policy" "sqs_policy" {
  name = "SQSPolicy"
  policy = jsonencode({
    Version = "2012-10-17"
    Statement = [{
      Effect   = "Allow"
      Action   = ["sqs:SendMessage", "sqs:ReceiveMessage"]
      Resource = "*"
    }]
  })
}
