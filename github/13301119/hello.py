def applicationHallo(environ, start_response):
  from urlparse import urlparse
  o = urlparse(environ.url)
if o.URLpath=("hello.html"):
 return ['<h1>Hello, %s!</h1>' % (environ['PATH_INFO'][1:] or 'web')]
 else :
  return ('<h1>404,not found</h1>')