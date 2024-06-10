function f=lagrange_g(xi,fi,x)
  n=length(x);
  w=ones(1,n)
  for j=1:n
    w(j)=prod(xi(j)-xi([1:j-1,j+1:n]));
  endfor
  w=1./w;
  num=zeros(size(x));
  den=num;
  exact=den;
  for j=1:n
    xdiff=x-xi(j);
    term=w(j)./xdiff;
    num=num+fi(j)*term;
    den=den+term;
    exact(xdiff==0) =j;
  endfor
  f=num./den;
  k=find(exact);
  f(k)=fi(exact(k));

end
