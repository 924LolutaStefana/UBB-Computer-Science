function x=gauss_elimination(A,b)
  [r,n]=size(A);
  x=zeros(size(b));
  A=[A,b];
  for j=1:n-1
    [v,p]=max(abs(A()));
    A([a b],:)= A([b a],:);
    for i=j+1:r
      m=   /    ;
      A()=A()-m*A();
    endfor
  endfor
  x=backsubst(A(:,1:n),A(:,n+1));
end


