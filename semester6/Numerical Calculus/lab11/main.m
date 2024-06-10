
##f=@(x)1./(2+sin(x));
##
##[I,nf]=romberg(f,0,pi/2,10^(-6),50)
##vpa((pi*sqrt(3))/9,6)

gauss_quad(@(x)(x.^2+1)./(x.^2+1),2,3)

