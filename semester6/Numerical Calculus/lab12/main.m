##f=@(x) x-exp(-x);
##fplot(f,[-1,2])
##[z,ni]=bisect(f,0,1,10^(-5),50)
##grid on
##[z,ni]=secant(f,0,1,10^(-5),50)
##fd=@(x)1+exp(-x);
##[z,ni]=newton(f,fd,0,10^(-5),50)

p=[1,2,-4,-14,-5];
roots(p)
syms x;
f=@(x) x.^4+2*x.^3+(-4)*x.^2+(-14)*x -5;
solve(f(x)==0)
fsolve(f,0)
bisect(f,-1,0,10^(-5),50)


