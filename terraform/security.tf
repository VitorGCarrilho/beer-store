resource "aws_security_group" "allow_ssh" {
  vpc_id = "${aws_vpc.main.id}"
  name = "vcarrilho_allo_ssh"

  ingress {
    from_port = 22
    to_port = 22
    protocol = "tcp"
    cidr_blocks = ["187.57.14.82/32"]
  }

}

resource "aws_security_group" "database_security_group" {
  vpc_id = "${aws_vpc.main.id}"
  name = "database_security_group"

  ingress {
    from_port = 5432
    protocol = "tcp"
    to_port = 5432
    self = true
  }
}