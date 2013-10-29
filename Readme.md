# Readme

## Description

*ansible-jenkins* is an [Ansible](http://ansible.cc) role.
Use this role to install Jenkins and install/update plugins.

## Requirements

### Ansible

Everything you should know about Ansible is documented on the [Ansible](http://ansible.cc/docs/gettingstarted.html) site...

### Supported platforms

#### Debian wheezy

Playbook tested on *Debian-7.1*.

#### Ansible >= 1.3

Any Ansible version >= 1.3 should work. If not, please use the issue tracker to report any bugs.

## Usage

### Get the code

```bash
$ git clone git@github.com:ICTO/ansible-jenkins.git
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
127.0.0.1 ansible_ssh_port=2222
```

### Create host specific variables

Make the host_vars directory where *ansible.host* file is located.

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
```

### Run the playbook


First create a playbook including the jenkins role, naming it jenkins.yml.

```yml
---
- hosts: jenkins
  roles:
    - jenkins
```

Use *ansible.host* as inventory. Run the playbook only for the remote host *jenkins*. Use *vagrant* as the SSH user to connect to the remote host. *-k* enables the SSH password prompt.

```bash
$ ansible-playbook -k -i ansible.host jenkins.yml --extra-vars="user=vagrant"
```

### Example output

```
SSH password: 

PLAY [jenkins] **************************************************************** 

GATHERING FACTS *************************************************************** 
ok: [127.0.0.1]

TASK: [Install python-software-properties] ************************************ 
ok: [127.0.0.1]

TASK: [Add jenkins apt-key] *************************************************** 
changed: [127.0.0.1]

TASK: [Add Jenkins repository] ************************************************ 
changed: [127.0.0.1]

TASK: [Remove invalid Jenkins src repository] ********************************* 
changed: [127.0.0.1]

TASK: [Install dependencies] ************************************************** 
changed: [127.0.0.1] => (item=openjdk-6-jre)
changed: [127.0.0.1] => (item=openjdk-6-jdk)
changed: [127.0.0.1] => (item=git)
ok: [127.0.0.1] => (item=curl)

TASK: [Install Jenkins] ******************************************************* 
changed: [127.0.0.1]

TASK: [10s delay while starting Jenkins] ************************************** 
ok: [127.0.0.1]

TASK: [Create Jenkins CLI destination directory: /opt/jenkins] **************** 
changed: [127.0.0.1]

TASK: [Get Jenkins CLI] ******************************************************* 
changed: [127.0.0.1]

TASK: [Get Jenkins updates] *************************************************** 
changed: [127.0.0.1]

TASK: [Update-center Jenkins] ************************************************* 
changed: [127.0.0.1]

TASK: [List plugins] ********************************************************** 
skipping: [127.0.0.1]

TASK: [Install/update plugins] ************************************************ 
skipping: [127.0.0.1] => (item=plugins)

TASK: [List plugins to be updated] ******************************************** 
changed: [127.0.0.1]

TASK: [Update plugins] ******************************************************** 
changed: [127.0.0.1]

NOTIFIED: [Restart Jenkins] *************************************************** 
changed: [127.0.0.1]

PLAY RECAP ******************************************************************** 
127.0.0.1              : ok=15   changed=7    unreachable=0    failed=0   
```

## Docs and contact

Read more on the Wiki pages about how this playbook works.

http://icto.ugent.be