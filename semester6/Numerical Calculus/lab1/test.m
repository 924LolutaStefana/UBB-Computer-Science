%Ex1
p=[1,-5,-16,16,-17,21];
polyval(p,-3);
x=-4:0.1:7.2;
px=polyval(p,x);
plot(x,px);
roots(p);
%Ex2
x=linspace(0,2*pi,1000);  %defining the interval
subplot(3,1,1)
plot(x,sin(x))
subplot(3,1,2)
plot(x,sin(2*x))
subplot(3,1,3)
plot(x,sin(3*x))
%Ex3
t=linspace(0,10*pi,1000);
R=3.8;
r=1;
x=(R+r)*cos(t)-r*cos((R/r+1)*t);
y=(R+r)*sin(t)-r*sin((R/r+1)*t);
clf
%plot(x,y)
%Ex4
[x,y]=meshgrid(linspace(-2,2),linspace(0.5,4.5));
f=sin(exp(x)).*cos(log(y));
clf
mesh(x,y,f)
clf
%plot3(x,y,f)
%Ex5
s=1;
n=2024;
for i = 1:n
  s = 1 + 1/(s+1);
endfor
sqrt(2)
s

