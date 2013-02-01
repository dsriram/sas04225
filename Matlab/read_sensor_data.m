function [ timestamp, data,count ] = read_sensor_data( filename )
%READ_SENSOR_DATA Summary of this function goes here
%   Detailed explanation goes here

fid = fopen(filename,'r','b'); % filename, read-only-mode, big-endian-format
fileinfo = dir(filename);
filesize = fileinfo.bytes;
maxCount = filesize/20;
timestamp = int64(zeros([maxCount 1]));
data = zeros([maxCount 3]);
for i=1:maxCount
    timestamp(i) = fread(fid,1,'*int64');
    data(i,:) = (fread(fid,3,'float32'))';
end
count = maxCount
fclose(fid);
end

