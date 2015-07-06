name="{{proxy_host}}";
port = {{proxy_port}};
userName="{{proxy_login}}";
password ="{{proxy_password}}";
noProxyHost="localhost";

pc = new hudson.ProxyConfiguration(name, port, userName, password, noProxyHost);
jenkins.model.Jenkins.instance.proxy = pc;
println "Proxy settings updated!"
