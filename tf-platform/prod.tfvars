region = "us-east-1" 

environment = "prod"

app_ami_id = "ami-04505e74c0741db8d" # "ami-0851b76e8b1bce90b"

bastion_ami_id = "ami-02358d9f5245918a3" # "ami-002d6833390304363"

bastion_instance_type = "t2.large"

app_instance_type = "t3.micro"

key_pair_name = "slipway-key-pair"

bastion_instance_count = "1"

app_instance_count = "2"

app_volume_size = "15"

bastion_volume_size = "10"
