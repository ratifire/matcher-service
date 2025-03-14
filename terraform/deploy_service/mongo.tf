
# resource "mongodbatlas_database_user" "db_user" {
#   project_id = var.project_id
#   username   = "testuser"
#   password   = var.db_user_password
#   roles {
#     role_name     = "testRole"
#     database_name = "matcherMongoDB"
#   }
#   auth_database_name = "matcherMongoDB"
# }
#
# resource "mongodbatlas_project_ip_access_list" "whitelist" {
#   project_id = var.project_id
#   cidr_block = "0.0.0.0/0"
# }
#
# provider "mongodbatlas" {
#   public_key  = var.mongodb_public_key
#   private_key = var.mongodb_private_key
# }