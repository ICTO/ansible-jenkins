import jenkins.model.*

def instance = Jenkins.getInstance()

final String name = "{{jenkins_proxy_host}}"
final int port = {{jenkins_proxy_port}}
final String userName = "{{jenkins_proxy_login}}"
final String password = "{{jenkins_proxy_password}}"
final String noProxyHost = "localhost"

final def pc = new hudson.ProxyConfiguration(name, port, userName, password, noProxyHost)
instance.proxy = pc
instance.save()
println "Proxy settings updated!"
