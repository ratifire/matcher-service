resource "mongodbatlas_cluster" "mongo_cluster" {
  name                        = var.matcher_cluster_name
  provider_name               = "AWS"
  provider_region_name        = "EU_NORTH_1"
  project_id                  = var.project_id
  provider_instance_size_name = "M0"
}


resource "mongodbatlas_database_user" "db_user" {
  project_id = var.project_id
  username   = "matcheruser"
  password   = var.db_user_password
  roles {
    role_name     = "atlasAdmin"
    database_name = "matcherMongoDB"
  }
  auth_database_name = "admin"
}

resource "mongodbatlas_project_ip_access_list" "whitelist" {
  project_id = var.project_id
  cidr_block = "0.0.0.0/0"
}

provider "mongodbatlas" {
  public_key  = var.mongodb_public_key
  private_key = var.mongodb_private_key
}