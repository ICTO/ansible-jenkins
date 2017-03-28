# Readme

## Description

*ansible-jenkins* is an [Ansible](http://ansible.cc) role.
Use this role to install Jenkins and install/update plugins.

## Provides

1. Latest Jenkins server
2. Jenkins plugins support

## Requires

1. Ansible 2.2 or higher
2. Debian 8, Ubuntu 14, CentOS 7, FreeBSD 10 or later
3. Vagrant (optional)

## Usage

### Install from Ansible Galaxy

```bash
$ ansible-galaxy install flyapen.jenkins
```

### Or download manually

```bash
$ git clone https://github.com/ICTO/ansible-jenkins.git roles
```

The code should reside in the roles directory of ansible ( See [ansible documentation](http://www.ansibleworks.com/docs/playbooks.html#roles) for more information on roles ), in a folder jenkins.

### Create a host file

Following example make ansible aware of the Vagrant box reachable on localhost port 2222.

```bash
$ vi ansible.host
```

with

```ini
[jenkins]
127.0.0.1 ansible_ssh_port=2222 ansible_ssh_user=vagrant ansible_ssh_private_key_file=~/.vagrant.d/insecure_private_key
```

### Create host specific variables

Make the `host_vars` directory where *ansible.host* file is located.

```bash
$ mkdir host_vars
```

Create a file in the newly created directory matching your host.

```bash
$ cd host_vars
$ vi 127.0.0.1
```

with

```yaml
---
plugins:
  - 'ldap'
  - 'github'
  - 'translation'
  - 'preSCMbuildstep'
email:
  smtp_host: 'mail.example.com'
  smtp_ssl: 'true'
  default_email_suffix: '@example.com'
#jenkins_admin_user: admin
#jenkins_admin_password: admin
```

Take a look at the defaults/main.yml file which contains all possible variables.

### Run the playbook


First create a playbook including the jenkins role, naming it jenkins.yml.

```yml
- name: Jenkins
  hosts: jenkins
  roles:
    # install jenkins
    - { role: jenkins, become: yes, become_user: root }
```

Use *ansible.host* as inventory. Run the playbook only for the remote host *jenkins*. Use *vagrant* as the SSH user to connect to the remote host. *-k* enables the SSH password prompt.

```bash
$ ansible-playbook -i ansible.host jenkins.yml
```
