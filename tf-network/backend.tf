terraform {
  backend "s3" {
    bucket = "slipway-terraform-s3-west"
    key    = "network/terraform.tfstate"
    region = "us-west-1"
  }
}