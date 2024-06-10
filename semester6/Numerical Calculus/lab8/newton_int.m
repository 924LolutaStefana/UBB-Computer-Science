function f=newton_int(d,xi,x)
  for k=1:length(x)
    v=x(k)-xi;
    f(k) = d(1,:)*[1,cumprod(v(1:end-1))]';

  endfor
end
