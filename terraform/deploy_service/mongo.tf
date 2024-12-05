resource "mongodbatlas_cluster" "mongo_cluster" {
  project_id                  = var.project_id
  name                        = "matcher-cluster"
  cluster_type                = "SHARDED"
  provider_region_name        = "EU_NORTH_1"
  provider_instance_size_name = "M0"
  provider_name               = "AWS"

}

resource "mongodbatlas_database_user" "db_user" {
  project_id = var.project_id
  username   = "matcher_user"
  password   = var.db_user_password
  roles {
    role_name     = "RoleTest"
    database_name = "matcherMongoDB"
  }
  auth_database_name = "matcherMongoDB"
}

resource "mongodbatlas_project_ip_whitelist" "whitelist" {
  project_id = var.project_id
  cidr_block = "0.0.0.0/0"
}

provider "mongodbatlas" {
  public_key  = var.mongodb_public_key
  private_key = var.mongodb_private_key
}