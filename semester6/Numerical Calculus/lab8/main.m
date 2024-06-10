xi=[0,1,2]
fi=1./(1+xi);
d=div_diff(xi,fi);
xx=linspace(0,2,100);
Lf=newton_int(d,xi,xx);
plot(xx,Lf)

dfi=-1./(1+xi).^2;
[zi,d2]=div_diff2(xi,fi,dfi)
Hf=newton_int(d2,zi,xx);
hold on;
plot(xx,Hf)
x=[0,3,5,8,13];
f=[0,225,383,623,993];
df=[0,77,80,74,72];
t=10;
[zi,d2]=div_diff2(x,f,df);
some=newton_int(d2,zi,10)
d=div_diff(x,df)
newton_int(d,x,10)
d3=div_diff(f,df)
newton_int(d3,f,some)

