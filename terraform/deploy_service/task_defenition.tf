resource "aws_ecs_task_definition" "task_definition" {

  family = var.aws_ecs_task_definition_family

  container_definitions = jsonencode([
    {
      name              = var.matcher_container_name,
      image             = "${data.aws_caller_identity.current_user.account_id}.dkr.ecr.${var.region}.amazonaws.com/${var.matcher_repository_name}:${var.image_tag}",
      cpu               = 0,
      memory            = 819,
      memoryReservation = 819,
      healthCheck : {
        "command" : ["CMD-SHELL", "curl -f https://${var.subdomain_name}/actuator/health || exit 1"],
        "interval" : 60,
        "timeout" : 5,
        "retries" : 2
      },
      portMappings = [
        {
          name          = "${var.matcher_container_name}-${var.matcher_port}-tcp",
          containerPort = var.matcher_port,
          hostPort      = var.matcher_port,
          protocol      = "tcp",
          appProtocol   = "http"
        }
      ],
      essential = true,
      environment = [
        {
          name  = "POSTGRES_USER",
          value = "backend"
        },
        {
          name  = "PG_USERNAME",
          value = "backend"
        },
        {
          name  = "PG_PASSWORD",
          value = "backenddb"
        },
        {
          name  = "PG_DATABASE",
          value = "backend"
        },
        {
          name  = "POSTGRES_PASSWORD",
          value = "backenddb"
        },
        {
          name  = "POSTGRES_DB",
          value = "backend"
        },
        {
          name  = "ACTIVE_PROFILE",
          value = "dev"
        }
      ],
      mountPoints = [],
      volumesFrom = [],
      logConfiguration = {
        logDriver = "awslogs",
        options = {
          awslogs-group         = "/ecs/${var.matcher_container_name}",
          awslogs-create-group  = "true",
          awslogs-region        = var.region,
          awslogs-stream-prefix = "ecs"
        },
        secretOptions = []
      },
      systemControls = []
    }
  ])

  task_role_arn      = data.aws_iam_role.ecs_task_execution_role_arn.arn
  execution_role_arn = data.aws_iam_role.ecs_task_execution_role_arn.arn
  network_mode       = "bridge"
  requires_compatibilities = [
    "EC2"
  ]
  cpu    = "2048"
  memory = "923"
  runtime_platform {
    operating_system_family = "LINUX"
    cpu_architecture        = "X86_64"
  }

}