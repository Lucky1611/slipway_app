provider "aws" {
  region = var.region
}

terraform {
  backend "s3" {
    bucket = "slipway-terraform-s3-east"
    key    = "platform/terraform.tfstate"
    region = "us-east-1"
  }
}

data "terraform_remote_state" "network" {
  backend = "s3"
  config = {
    bucket = "slipway-terraform-s3-east"
    key    = "network/terraform.tfstate"
    region = "us-east-1"
  }
}


