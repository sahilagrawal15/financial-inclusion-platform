variable "aws_region" {
  default = "us-east-1"
}

variable "dynamodb_user_table" {
  default = "user-table"
}

variable "dynamodb_txn_table" {
  default = "transaction-table"
}

variable "s3_bucket" {
  default = "fin-inclusion-bucket-sahil15-001"
}
