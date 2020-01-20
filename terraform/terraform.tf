terraform {
  backend "s3" {
    bucket = "vcarrilho-terraform"
    key = "beerstore-curso-oline"
    region = "us-east-1"
    profile = "vitor-personal-credential"
  }
}