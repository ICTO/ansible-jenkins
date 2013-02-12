# Readme

## Description

*ansible-jenkins* is an [Ansible](http://ansible.cc) playbook.
The playbook contains tasks to install Jenkins and install/update plugins.

## Requirements

### Ansible

Everything is documented on the [Ansible](http://ansible.cc/docs/gettingstarted.html) site...

### Supported platforms

Playbook tested on *Debian-7.0-b4-amd64*, probably works on other Debian versions too. Heavily depends on *apt*, sorry CentOS users...

## Usage

### Get the code

```bash
$ git clone git@github.ugent.be:tberton/ansible-jenkins.git
```

### Create a host file

Following example make ansible aware of the Vagrant box reachable on localhost port 2222.

```bash
$ vi ansible.host
```

with

```ini
[vagrant]
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

Use *ansible.host* as inventory. Run the playbook only for the remote host *vagrant*. Use *vagrant* as the SSH user to connect to the remote host. *-k* enables the SSH password prompt.

```bash
$ ansible-playbook -k -i ansible.host ansible-jenkins/setup.yml --extra-vars="hosts=vagrant user=vagrant"
```

### Example output

```
SSH password: 

PLAY [vagrant] ********************* 

GATHERING FACTS ********************* 
ok: [127.0.0.1]

TASK: [Install python-software-properties] ********************* 
ok: [127.0.0.1]

TASK: [Add jenkins apt-key] ********************* 
ok: [127.0.0.1]

TASK: [Add Jenkins repository] ********************* 
ok: [127.0.0.1]

TASK: [Remove invalid Jenkins src repository] ********************* 
changed: [127.0.0.1]

TASK: [Install dependencies] ********************* 
ok: [127.0.0.1] => (item=openjdk-6-jre,openjdk-6-jdk,git,curl)

TASK: [Install Jenkins] ********************* 
ok: [127.0.0.1]

TASK: [Get Jenkins CLI] ********************* 
ok: [127.0.0.1]

TASK: [Get Jenkins updates] ********************* 
ok: [127.0.0.1]

TASK: [Update-center Jenkins] ********************* 
skipping: [127.0.0.1]

TASK: [Install/update plugins] ********************* 
skipping: [127.0.0.1] => (item=ldap)
skipping: [127.0.0.1] => (item=github)
skipping: [127.0.0.1] => (item=translation)
skipping: [127.0.0.1] => (item=preSCMbuildstep)

TASK: [10s delay while installing plugins] ********************* 
skipping: [127.0.0.1]

TASK: [Safe-restart Jenkins] ********************* 
skipping: [127.0.0.1]

PLAY RECAP ********************* 
127.0.0.1                      : ok=10   changed=1    unreachable=0    failed=0    
```

## Docs and contact

Read more on the Wiki pages about how this playbook works.

http://icto.ugent.be