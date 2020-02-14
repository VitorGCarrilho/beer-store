resource "aws_key_pair" "key_pair" {
  public_key = "${file("key/beerstore_key.pub")}"
}

resource "aws_instance" "instances" {
  count = 3

  ami = "ami-062f7200baf2fa504"
  instance_type = "t2.micro"
  key_name = "${aws_key_pair.key_pair.key_name}"

  subnet_id = "${element(aws_subnet.public_subnet.*.id, count.index)}"

  vpc_security_group_ids = ["${aws_security_group.allow_ssh.id}", "${aws_security_group.allow_outbound.id}"]

  tags = {
    Name = "vcarrilho-instances"
  }
}

data "template_file" "hosts" {
  template = "${file("./template/hosts.tpl")}"

  vars = {
    PUBLIC_IP_0 = "${aws_instance.instances.*.public_ip[0]}"
    PUBLIC_IP_1 = "${aws_instance.instances.*.public_ip[1]}"
    PUBLIC_IP_2 = "${aws_instance.instances.*.public_ip[2]}"
  }
}

resource "local_file" "hosts" {
  content = "${data.template_file.hosts.rendered}"
  filename = "./hosts"
}

output "public_ips" {
  value = "${join(", ", aws_instance.instances.*.public_ip)}"
}