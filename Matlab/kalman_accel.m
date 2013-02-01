fid = fopen('H:\aishufol\indoorindia\sastra x_accel.dat','r','b'); % filename, read-only-mode, big-endian-format
fileinfo = dir('H:\aishufol\indoorindia\arch_accel.dat');
filesize = fileinfo.bytes;
maxCount = filesize/20;
timestamp = int64(zeros([maxCount 1]));
data = zeros([maxCount 3]);
for i=1:maxCount
    timestamp(i) = fread(fid,1,'*int64');
    data(i,:) = (fread(fid,3,'float32'))';
end
count = maxCount;
fclose(fid);

x=data(:,1);
d  =ones(count);  % Desired signal
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