h=csvread('H:\ourwifidata\output_vectorsnew.csv');
h=h(:,2:83);
out_nn=h(:,1);
input=h(:,2:82);
c=input;
oo=out_nn';
c=abs(c)/100;
oo=abs(oo)/100;
c=c';
%nntool