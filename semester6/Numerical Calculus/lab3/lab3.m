##x=[0,1/6,1/2];
##f=[0,1/2,1];
##div_diff(x,f);
##x=[-1,1];
##f=[-3,1];
##df=[10,2];
##[z,tb]=div_diff2(x,f,df)

%1 a)
##x=[0,1,2];
##f=1./(1+x);
##div_diff(x,f);
##
##%1 b)
##x=[0,1,2];
##f=1./(1+x);
##df=-1./(1+x).^2;
##[z,tb]=div_diff2(x,f,df);

% 1 c)
##x=linspace(1,2,11);
##f=1./(1+x);
##div_diff(x,f)
##df=-1./(1+x).^2;
##[z,tb]=div_diff2(x,f,df)


%2 a)
x=[-2,-1,0,1,2,3,4];
f=[-5,1,1,1,7,25,60];
div_diff(x,f);

%2 b)
forward_diff(f);
%2 c)
back_diff(f)

