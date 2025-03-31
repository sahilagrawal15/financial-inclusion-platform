output "dynamodb_user_table_name" {
  value = aws_dynamodb_table.user.name
}

output "dynamodb_txn_table_name" {
  value = aws_dynamodb_table.transaction.name
}

output "s3_bucket_name" {
  value = aws_s3_bucket.bucket.bucket
}
