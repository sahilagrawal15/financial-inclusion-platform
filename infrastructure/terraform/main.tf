provider "aws" {
  region = var.aws_region
}

resource "aws_dynamodb_table" "user" {
  name           = var.dynamodb_user_table
  billing_mode   = "PAY_PER_REQUEST"
  hash_key       = "user_id"

  attribute {
    name = "user_id"
    type = "S"
  }
}

resource "aws_dynamodb_table" "transaction" {
  name           = var.dynamodb_txn_table
  billing_mode   = "PAY_PER_REQUEST"
  hash_key       = "transaction_id"

  attribute {
    name = "transaction_id"
    type = "S"
  }
}

resource "aws_s3_bucket" "bucket" {
  bucket = var.s3_bucket
}
