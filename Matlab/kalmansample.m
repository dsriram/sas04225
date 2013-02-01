x  = 0.1*randn(1,500); % Input to the filter
b  = fir1(31,0.5);     % FIR system to be identified
t=0.02:0.02:10;
d  =sin(2*pi*t);    % Desired signal
w0 = zeros(1,32);      % Intial filter coefficients
k0 = 0.5*eye(32);      % Initial state error correlation matrix
qm = 2;                % Measurement noise covariance
qp = 0.1*eye(32);      % Process noise covariance  
s = initkalman(w0,k0,qm,qp);
[y,e,s] = adaptkalman(x,d,s);
subplot(3,1,1)
plot(x);
subplot(3,1,2)
plot(d);
subplot(3,1,3)
plot(y);
title('System Identification of an FIR filter via Kalman Filter');
grid on;