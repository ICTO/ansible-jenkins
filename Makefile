playbook: ssh-config
	ansible-playbook -i hosts playbook.yaml

ssh-config:
	vagrant ssh-config > ssh-config

natpf:
	VBoxManage controlvm "ansible-jenkins" natpf1 ",tcp,127.0.0.1,8080,,8080"

# Clean up
clean:
	rm ssh-config
clean_natpf:
	VBoxManage controlvm "ansible-jenkins" natpf1 delete tcp_8080_8080
distclean: clean clean_natpf
	rm -rf .e symlink.jenkins

# Install ansible
ansible: .e/bin/ansible
.e/bin/ansible: .e
	.e/bin/pip2.7 install ansible
.e/bin/aws: .e
	.e/bin/pip2.7 install awscli
.e:
	virtualenv .e

