import jenkins.model.*

def instance = Jenkins.getInstance()

final String name = "{{proxy_host}}"
final int port = {{proxy_port}}
final String userName = "{{proxy_login}}"
final String password = "{{proxy_password}}"
final String noProxyHost = "localhost"

final def pc = new hudson.ProxyConfiguration(name, port, userName, password, noProxyHost)
instance.proxy = pc
instance.save()
println "Proxy settings updated!"
