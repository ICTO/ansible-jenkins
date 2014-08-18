# -*- mode: ruby -*-
Vagrant.configure("2") do |config|
  config.vm.box     = "Ubuntu Server Precise 14.04 amd64"
  config.vm.box_url = "https://oss-binaries.phusionpassenger.com/vagrant/boxes/latest/ubuntu-14.04-amd64-vbox.box"
  config.vm.provider "virtualbox" do |v|
    v.name = "ansible-jenkins"
  end
  #config.vm.network "private_network", ip: "192.168.50.205"
end
