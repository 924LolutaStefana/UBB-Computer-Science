function[z,ni]=bisect(f,a,b,err,maxn)
fa=f(a);
fb=f(b);
if sign(fa)*sign(fb)>0
  error('wrong sign');
  return
end
c=(a+b)/2;
for k=1:maxn
  fc=f(c);
  if sign(fb)*sign(fc)<=0
    a=c;
    fa=fc;
  else
    b=c;
    fb=fc;
  endif
  if abs(b-a)<err
    z=c;
    ni=k;
    return
  endif
  c=(a+b)/2;
end
error('too difficult');
end
