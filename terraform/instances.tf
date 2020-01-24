resource "aws_key_pair" "key_pair" {
  public_key = "${file("key/beerstore_key.pub")}"
}

resource "aws_instance" "instances" {
  count = 3

  ami = "ami-062f7200baf2fa504"
  instance_type = "t2.micro"
  key_name = "${aws_key_pair.key_pair.key_name}"

  subnet_id = "${element(aws_subnet.public_subnet.*.id, count.index)}"

  tags = {
    Name = "vcarrilho-instances"
  }
}