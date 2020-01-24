resource "aws_vpc" "main" {
  cidr_block = "192.168.0.0/16"

  tags = {
    Name = "vcarrilho-vpc"
  }
}

resource "aws_subnet" "private_subnet" {
  count = 3

  cidr_block = "${cidrsubnet(aws_vpc.main.cidr_block, 8, count.index + 10)}"
  vpc_id = "${aws_vpc.main.id}"
  availability_zone = "${var.avability_zones[count.index]}"

  tags = {
    Name = "vcarrilho-private-subnet-${count.index}"
  }
}

resource "aws_subnet" "public_subnet" {
  count = 3

  cidr_block = "${cidrsubnet(aws_vpc.main.cidr_block, 8, count.index + 20)}"
  vpc_id = "${aws_vpc.main.id}"
  availability_zone = "${var.avability_zones[count.index]}"
  map_public_ip_on_launch = true

  tags = {
    Name = "vcarrilho-public-subnet-${count.index}"
  }
}


resource "aws_internet_gateway" "intenet_gateway" {
  vpc_id = "${aws_vpc.main.id}"
}

resource "aws_route_table" "route_interenet_gateway" {
  vpc_id = "${aws_vpc.main.id}"
  route {
    cidr_block = "0.0.0.0/0"
    gateway_id = "${aws_internet_gateway.intenet_gateway.id}"
  }
}

resource "aws_route_table_association" "route_table_association" {
  count = 3

  route_table_id = "${aws_route_table.route_interenet_gateway.id}"
  subnet_id = "${element(aws_subnet.public_subnet.*.id, count.index)}"
}