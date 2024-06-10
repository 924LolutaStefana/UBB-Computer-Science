##xi= [0,1/3,1/2,1];
##fi= cos(pi*xi);
##d= div_diff(xi,fi);
##xx=linspace(0,1,100);
##plot(xx,cos(pi*xx),xx,newton_int(d,xi,xx));
##x=1/5;
##approx=lagrange_int(xi,fi,x);
##
##xi=1000:10:1050;
##fi=log10(xi);
##x=1001:1009;
##d= div_diff(xi,fi);
##newton_int(d,xi,x)
##log10(x)

%2
xi=linspace(-4,4,9);
fi=2.^xi;
n=length(xi);
P=zeros(length(xi));
P(:,1)=fi';
x=0.5;
for i=1:n
  for j=1:i-1
    P(i,j+1)=((x-xi(j))*P(i,j)-(x-xi(i))*P(j,j))/(xi(i)-xi(j))


  endfor
endfor





