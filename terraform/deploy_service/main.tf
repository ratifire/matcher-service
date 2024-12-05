terraform {
  backend "s3" {}

  required_providers {
    mongodbatlas = {
      source  = "mongodb/mongodbatlas"
      version = "1.7.0"
    }
  }
  required_version = ">= 1.0.0"
}

provider "aws" {}

resource "aws_default_vpc" "default_backend_vpc" {}


