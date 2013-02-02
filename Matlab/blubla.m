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
y=zeros(count);
for i=1:count
z=kalmanfilter(x(i));
y(i)=z(1);
end
subplot(2,1,1)
plot(x);
subplot(2,1,2)
plot(y);