function plot_graph( data )
%PLOT_GRAPH Summary of this function goes here
%   Detailed explanation goes here

s = size(data);
if(s(2) ~= 3)
    return;
end

figure,subplot(3,1,1),plot(data(:,1)),title 'X axis';
subplot(3,1,2),plot(data(:,2)),title 'Y axis';
subplot(3,1,3),plot(data(:,3)),title 'Z axis';

end

