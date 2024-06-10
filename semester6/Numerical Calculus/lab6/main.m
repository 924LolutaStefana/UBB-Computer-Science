lagrange_int([1 2 3], [1 4 9], [6 5]);
%1 a)
xi=linspace(-2,4,10);
fi=(xi+1)./(3*xi.^2 +2*xi+1)
lagrange_int(xi,fi,[-2 4])
%1 b)
x=linspace(-2,4,500);
f=(x+1)./(3*x.^2 +2*x+1);
L9f=lagrange_int(xi,fi,x);
plot(x,f,x,L9f,'g',xi,fi,'o')
%1 c)
plot(x,abs(f-L9f));
max(abs(f-L9f))

xi=1980:10:2020;
fi=[4451,5287,6090,6970,7821];
y=lagrange_g(xi,fi,[2005,2015])

xi=[100,121,144]
fi=[10,11,12]
lagrange_int(xi,fi,118)
lagrange_g(xi,fi,118)
