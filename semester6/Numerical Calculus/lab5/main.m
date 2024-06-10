###1
##[A,b]=get_matrix(1000);
##x0=zeros(size(b));
##err=10^-5;
####[x,nit]=jacobi_it(A,b,x0,1000,err)
##
##[x,nit]=gauss_it(A,b,x0,1000,err)

#2
A=[10,7,8,7;7,5,6,5;8,6,10,9;7,5,9,10];
b=[32,23,33,31]';

x=A\b  %a
%b
b0=[32.1,22.9,33.1,30.9]';
xp=A\b0;
n=norm(x-xp)/norm(x);

n1=norm(b-b0)/norm(b);

%c
A0=[10,7,8.1,7.2;7.8,5.04,6,5;8,5.98, 9.89,9;6.99,4.99,9,9.98];
x2=A0\b;
x3=A0\b0;
n2=norm(x2-x3)/norm(x2);
n3=norm(A-A0)/norm(A);

c1=norm(A)*norm(inv(A))
c2=norm(A0)*norm(inv(A0))



