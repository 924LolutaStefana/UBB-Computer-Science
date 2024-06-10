% 1.a
syms x;
f=exp(x);
Tf1=taylor(f,x,0, 'Order', 2);
Tf2=taylor(f,x,0, 'Order', 3);
Tf3=taylor(f,x,0, 'Order', 4);
Tf4=taylor(f,x,0, 'Order', 5);
ezplot(f,[-3,3])
hold on
ezplot(Tf1,[-3,3])
ezplot(Tf2,[-3,3])
ezplot(Tf3,[-3,3])
ezplot(Tf4,[-3,3])
hold off
clf
% 1.b
##vpa(exp(1),7);
##subs(Tf1,x,1);
##vpa(subs(Tf4,x,1),7)
##for i=2:15
##  T=taylor(f,x,0,'Order',i);
##  i
##  vpa(subs(T,x,1),7)
## end
##
## %2.a
## clf
## f=sin(x);
## Tf1=taylor(f,x,0, 'Order', 4);
## Tf2=taylor(f,x,0, 'Order', 6);
## ezplot(f,[-pi,pi])
## hold on
## ezplot(Tf1,[-pi,pi])
## hold on
## ezplot(Tf2,[-pi,pi])
##
## %2.b
## vpa(sin(pi/5),6);
##
##for i=2:15
##  T=taylor(f,x,0,'Order',i);
##  i
##  vpa(subs(T,x,pi/5),6)
## end
##
## %2.c
## vpa(subs(Tf2,x,10*pi/3),6)
##
##for i=2:15
##  T=taylor(f,x,0,'Order',i);
##  i
##  vpa(subs(T,x,-pi/3),6)
## end


 %3.a
 f=log(x+1);
 Tf1= taylor(f,x,0,'Order',3)
  Tf2= taylor(f,x,0,'Order',6)
  ezplot(Tf1,[-0.9,1])
  hold on
  ezplot(Tf2,[-0.9,1])
%3.b
##for i=2:15
##  T=taylor(f,x,0,'Order',i);
##  i
##  vpa(subs(T,x,log(2)),5)
## end
##  vpa(subs(T,x,log(2)),5);
##
%3.c
f=log(1-x)
Tf7=taylor(f,x,0,'Order',6)
%3.d
Tf8=Tf2-Tf7
%3.e
for i=2:15
  T=taylor(f,x,0,'Order',i);
  i
  vpa(subs(Tf8,x,1/3),5)
 end











