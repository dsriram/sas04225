/* 
 * File:   MatchResultAnalytics.cpp
 * Author: sriram
 *
 */

#include "MatchResultAnalytics.hpp"
#include <iostream>

using namespace sas04225;

DMatchAnalytics::DMatchAnalytics(const std::vector<cv::DMatch>& matches)
{
    this->matches = matches;
}

// compute the analytics for the given number of sample_points, inclusive of 100%
std::vector<DMatchAnalyticsResult> DMatchAnalytics::computeAnalytics(const int no_of_sample_points)
{
    std::sort(matches.begin(),matches.end());
    int size = matches.size();
    
    DMatchAnalyticsResult result[no_of_sample_points+1];
    int sample_point[no_of_sample_points];
    float _sample_pt_spacing = 1.0/no_of_sample_points;
    
    std::cout<<"Spacing : "<<_sample_pt_spacing<<std::endl;
    
    result[0].distance = matches[0].distance;
    result[0].percent = -1;
    result[no_of_sample_points].distance = matches[size-1].distance;
    result[no_of_sample_points].percent = 100;
    result[no_of_sample_points].count = size;
    
    for(int i=1;i<no_of_sample_points;i++)
    {
        float _pc = i*_sample_pt_spacing;
        result[i].percent = _pc*100;
        sample_point[i] = _pc*size;
    }
    
    for(int i=1; i<no_of_sample_points; i++)
    {
        result[i].distance = matches[sample_point[i]].distance;
    }
    
    std::vector<DMatchAnalyticsResult> _result;
    int cursor = 0;
    
    for(int i=0; i<size; i++)
    {
        if(result[cursor].distance<matches[i].distance)
        {
            result[cursor].count = i;
            _result.push_back(result[cursor]);
            cursor++;
            if(cursor == no_of_sample_points)
                break;
        }
    }
    _result.push_back(result[no_of_sample_points]);
    return _result;
    
}
